package phalaenopsis.satellitegraph.utils.ArcGISHelper;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import phalaenopsis.common.entity.AppSettings;
import phalaenopsis.common.method.Tools.StrUtil;
import phalaenopsis.satellitegraph.entity.GeometryPolygon;
import phalaenopsis.satellitegraph.entity.MultiPolygon;
import phalaenopsis.satellitegraph.entity.Polygon;
import phalaenopsis.satellitegraph.entity.SpatialReference;
import phalaenopsis.satellitegraph.utils.ExtensionMethods;

/**
 * 图斑地图服务工具类
 */
public class SpotArcGISHelper {
    @Autowired
    private static AppSettings settings;

    public static Polygon unionAction(String serviceUrl, MultiPolygon unionPolygon) {
        ArcGISUnionParam uparam1 = new ArcGISUnionParam(unionPolygon);
        String url = StrUtil.trim(serviceUrl, "/") + "/union";
        HttpEntity httpEntity = HttpHelper.HttpPost(url, uparam1.toList());
        GeometryPolygon polygons = null;
        try {
            JSONObject json = JSON.parseObject(EntityUtils.toString(httpEntity));
            polygons = JSON.toJavaObject(json, GeometryPolygon.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return polygons != null ? polygons.Geometry : null;
    }

    public static JSONObject identifyAction(String identifyUrl, Polygon geometry, String layerDefs) {
        double xmin = 0, ymin = 0, xmax = 0, ymax = 0;
        if (geometry == null)
            return null;

        for (int i = 0; i < geometry.getRings().size(); i++) {
            ArrayList<Double[]> r = geometry.getRings().get(i);

            for (int j = 0; j < r.size(); j++) {
                Double[] p = r.get(j);
                if (xmin == 0 || xmin > p[0]) {
                    xmin = p[0];
                }
                if (ymin == 0 || ymin > p[1]) {
                    ymin = p[1];
                }
                if (xmax == 0 || xmax < p[0]) {
                    xmax = p[0];
                }
                if (ymax == 0 || ymax < p[1]) {
                    ymax = p[1];
                }
            }
        }
        String extent = xmin + "," + ymin + "," + xmax + "," + ymax;

        ArcGISIdentifyParam param = new ArcGISIdentifyParam(geometry, null, extent, layerDefs);

        int removeIndex = identifyUrl.indexOf("/MapServer");
        if (removeIndex > 0) {
            identifyUrl = identifyUrl.substring(0, removeIndex);
        }

        String url = identifyUrl + "/MapServer/identify";
        HttpEntity httpEntity = HttpHelper.HttpPost(url, param.toList());
        String result;
        JSONObject json = null;
        try {
            result = EntityUtils.toString(httpEntity);
            json = JSON.parseObject(result);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return json;
    }

    public static MultiPolygon intersectAction(String serviceUrl, Polygon sPolygon, Polygon tPolygon) {
        if (sPolygon == null || tPolygon == null)
            return null;
        ArcGISIntersectParam param = new ArcGISIntersectParam(sPolygon);
        param.AddPolygon(tPolygon);
        serviceUrl = StrUtil.trim(serviceUrl, "/");
        String url = serviceUrl + "/intersect";
        url = StrUtil.trim(url, "/");
        HttpEntity httpEntity = HttpHelper.HttpPost(url, param.toList());
        MultiPolygon mps = null;
        try {
            JSONObject jsonObject = JSON.parseObject(EntityUtils.toString(httpEntity));
            mps = JSON.toJavaObject(jsonObject, MultiPolygon.class);
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mps != null && mps.Geometries.size() > 0 && mps.Geometries.get(0).getRings().size() > 0 ? mps : null;
    }

    public static MultiPolygon differenceAction(String serviceUrl, Polygon sPolygon, Polygon tPolygon) {
        if (sPolygon == null || tPolygon == null)
            return null;
        ArcGISDifferenceParam param = new ArcGISDifferenceParam(sPolygon);
        param.AddPolygon(tPolygon);
        serviceUrl = StrUtil.trim(serviceUrl, "/");
        String url = serviceUrl + "/difference";
        url = StrUtil.trim(url, "/");
        HttpEntity httpEntity = HttpHelper.HttpPost(url, param.toList());
        MultiPolygon mps = null;
        try {
            JSONObject jsonObject = JSON.parseObject(EntityUtils.toString(httpEntity));
            mps = JSON.toJavaObject(jsonObject, MultiPolygon.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mps != null && mps.getGeometries().size() > 0 && mps.getGeometries().get(0).getRings().size() > 0 ? mps
                : null;
    }

    public static ArcGISAreaAndLengthsResult areaAndLengthAction(String serviceUrl, MultiPolygon geometry) {
        serviceUrl = StrUtil.trim(serviceUrl, "/");
        String url = serviceUrl + "/areasAndLengths";
        url = StrUtil.trim(url, "/");
        String coordinates = JSON.toJSONString(geometry.Geometries);
        ArcGISAreaLengthParam param = new ArcGISAreaLengthParam(coordinates);
        HttpEntity httpEntity = HttpHelper.HttpPost(url, param.toList());
        ArcGISAreaAndLengthsResult areas = null;
        try {
            JSONObject jsonObject = JSON.parseObject(EntityUtils.toString(httpEntity));
            areas = JSON.toJavaObject(jsonObject, ArcGISAreaAndLengthsResult.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return areas;
    }

    public static MultiPolygon projectAction(String serviceUrl, MultiPolygon geometry, String inSR, String outSR) {
        serviceUrl = StrUtil.trim(serviceUrl, "/");
        String url = serviceUrl + "/project";
        url = StrUtil.trim(url, "/");
        ArcGISProjectParam param = new ArcGISProjectParam(geometry, inSR, outSR);
        HttpEntity httpEntity = HttpHelper.HttpPost(url, param.toList());
        MultiPolygon mps = null;
        try {
            JSONObject jsonObject = JSON.parseObject(EntityUtils.toString(httpEntity));
            mps = JSON.toJavaObject(jsonObject, MultiPolygon.class);
            if (mps != null && mps.getGeometries() != null && mps.getGeometries().size() > 0) {
                for (Polygon polygon : mps.getGeometries()) {
                    if (polygon.getRings() == null) {
                        polygon.setRings(new ArrayList<ArrayList<Double[]>>());
                    }
                }
                return mps;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static boolean addQTHFeatures(String serviceUrl, Polygon geometry, Dictionary<String, Object> dict) {
        boolean result = false;
        String geoWKID = new AppSettings().getGeoWKID();
        int WKID = geoWKID != null ? Integer.parseInt(geoWKID) : 4610;
        geometry.spatialReference = new SpatialReference(WKID);

        if (serviceUrl.contains("/FeatureServer")) {
            int removeIndex = serviceUrl.indexOf("/FeatureServer");
            serviceUrl = serviceUrl.substring(0, removeIndex);
        }

        String url = serviceUrl + "/FeatureServer/0/addFeatures";
        //String param = prepareQTHAddParams(dict, geometry);

        String attribute = getSpotAttribute(dict.get("DMID").toString());
        JSONObject jsonObject = JSON.parseObject(attribute);
        jsonObject.put("MID",dict.get("MID"));
        jsonObject.put("JCBH",dict.get("JCBH"));
        jsonObject.put("JCMJ",dict.get("JCMJ"));
        jsonObject.put("TBBH",dict.get("TBBH"));
        List<NameValuePair> params = addQTHParamsList(jsonObject.toJSONString(), geometry);

        try {
            HttpEntity httpEntity = HttpHelper.HttpPost(url, params);
            JSONObject results = JSON.parseObject(EntityUtils.toString(httpEntity));
            if (isError(results)) {
                // 记录日志
                Log(url, jsonObject.toJSONString(), results.toString());
                return false;
            }
            JSONArray ja = results.getJSONArray("addResults");
            if (ja != null && ja.size() > 0) {
                String isTrue = ja.getJSONObject(0).get("success").toString();
                if (isTrue.equals("true")) {
                    result = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    public static boolean addFeatures(String serviceUrl, Polygon geometry, Dictionary<String, Object> dict) {
        boolean result = false;
        String geoWKID = new AppSettings().getGeoWKID();
        int WKID = geoWKID != null ? Integer.parseInt(geoWKID) : 4610;
        geometry.spatialReference = new SpatialReference(WKID);

        if (serviceUrl.contains("/FeatureServer")) {
            int removeIndex = serviceUrl.indexOf("/FeatureServer");
            serviceUrl = serviceUrl.substring(0, removeIndex);
        }

        String url = serviceUrl + "/FeatureServer/0/addFeatures";
        String param = prepareAddParams(dict, geometry);
        List<NameValuePair> params = addParamsList(dict, geometry);

        try {
            HttpEntity httpEntity = HttpHelper.HttpPost(url, params);
            JSONObject results = JSON.parseObject(EntityUtils.toString(httpEntity));
            if (isError(results)) {
                // 记录日志
                Log(url, param, results.toString());
                return false;
            }
            JSONArray ja = results.getJSONArray("addResults");
            if (ja != null && ja.size() > 0) {
                String isTrue = ja.getJSONObject(0).get("success").toString();
                if (isTrue.equals("true")) {
                    result = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static Boolean updateFeatures(String serviceUrl, Polygon geometry, Dictionary<String, Object> dict,
                                         String objectID) {
        Boolean result = false;
        int WKID = settings.getGeoWKID() != null ? Integer.parseInt(settings.getGeoWKID()) : 4610;
        geometry.spatialReference = new SpatialReference(WKID);
        int removeIndex = serviceUrl.indexOf("/FeatureServer");
        serviceUrl = serviceUrl.substring(0, removeIndex);
        String url = serviceUrl + "/FeatureServer/0/updateFeatures";
        List<NameValuePair> param = updateParamsList(dict, geometry, objectID);
        HttpEntity httpEntity = HttpHelper.HttpPost(url, param);

        try {
            JSONObject results = JSON.parseObject(EntityUtils.toString(httpEntity));
            if (isError(results)) {
                // 记录日志
                Log(url, prepareUpdateParams(dict, geometry, objectID), results.toString());
                return false;
            }
            JSONArray ja = results.getJSONArray("updateResults");
            if (ja != null && ja.size() > 0) {
                String isTrue = ja.getJSONObject(0).get("success").toString();
                if (isTrue.equals("True")) {
                    result = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static boolean deleteFeatures(String serviceUrl, List<Long> spotID) {
        boolean result = false;
        int removeIndex = serviceUrl.indexOf("/FeatureServer");
        String url;
        if (removeIndex > 0) {
            url = serviceUrl.substring(0, removeIndex) + "/FeatureServer/0/deleteFeatures";
            ;
        } else {
            url = serviceUrl + "/FeatureServer/0/deleteFeatures";
        }

        //List<String> list = spotID.stream().map(Object::toString).collect(Collectors.toList());
        List<String> newList = new ArrayList<String>(spotID.size());
        for (Long l : spotID) {
            newList.add("MID=" + String.valueOf(l));
        }
//		StringBuilder conditions = null;
        String conditions = String.join("%20or%20", newList);
//		for (int i = 0; i < spotID.size(); i++) {
//			if (conditions == null) {
//				conditions = new StringBuilder();
//				conditions.append(StringEscapeUtils.escapeHtml("MID=" + spotID.get(i)));
//				if (spotID.size() > 1) {
//					conditions.append("%20or%20");
//				}
//			} else {
//				conditions.append(StringEscapeUtils.escapeHtml("MID=" + spotID.get(i)));
//			}
//		}

        String qUrl = serviceUrl + "/0/query";
        String qParam = "f=json&" + "where=" + conditions + "&returnIdsOnly=true";
        HttpEntity httpEntity = HttpHelper.HttpGet(qUrl + "?" + qParam);
        JSONArray ja = null;
        try {
            JSONObject json = JSON.parseObject(EntityUtils.toString(httpEntity));
            ja = json.getJSONArray("objectIds");
            if (isError(json)) {
                // 记录日志
                Log(qUrl, qParam, EntityUtils.toString(httpEntity));
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (ja != null && ja.size() > 0) {
            List<String> objectid = new ArrayList<String>();
            for (int i = 0; i < ja.size(); i++) {
                objectid.add(ja.get(i).toString());
            }
            String param = prepareDeleteParams(StrUtil.join(",", objectid));
            List<NameValuePair> params = deleteParamsList(StrUtil.join(",", objectid));
            HttpEntity httpEntity2 = HttpHelper.HttpPost(url, params);
            JSONObject results;
            try {
                results = JSON.parseObject(EntityUtils.toString(httpEntity2));
                if (isError(results)) {
                    // 记录日志
                    Log(url, param, results.toString());
                    return false;
                }
                JSONArray jaArray = results.getJSONArray("deleteResults");
                if (jaArray != null && jaArray.size() > 0) {
                    String isTrue = jaArray.getJSONObject(0).get("success").toString();
                    if (isTrue.equals("true")) {
                        result = true;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            result = true;
        }
        return result;
    }

    public static Polygon simplify(String serviceUrl, Polygon polygon) {
        String simplifyUrl = StrUtil.trimEnd(serviceUrl, "/") + "/simplify";

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("f", "json"));
        params.add(new BasicNameValuePair("sr", "4610"));
        String geometries = ("{\"geometryType\":\"esriGeometryPolygon\",\"geometries\":[" + JSON.toJSONString(polygon)
                + "]}");
        geometries = ExtensionMethods.EscapeLongString(geometries);
        params.add(new BasicNameValuePair("geometries", geometries));
        HttpEntity httpEntity = HttpHelper.HttpPost(simplifyUrl, params);
        Polygon newPolygon = null;
        try {
            JSONObject json = JSON.parseObject(EntityUtils.toString(httpEntity));
            if (isError(json)) {
                return null;
            }
            JSONArray ja = json.getJSONArray("geometries");
            JSONObject jsonObject = ja.getJSONObject(0);
            newPolygon = JSON.toJavaObject(jsonObject, Polygon.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newPolygon;
    }

    public static Polygon getSpotShape(String serviceUrl, String mid) {
        Polygon result = null;
        ArcGISWQueryParam param = new ArcGISWQueryParam(null, "MID=" + mid);
        int removeIndex = serviceUrl.indexOf("/FeatureServer");
        if (removeIndex >= 0) {
            serviceUrl = serviceUrl.substring(0, removeIndex);
        }

        String url = "";
        if (serviceUrl.indexOf("MapServer") > 0) {
            url = serviceUrl + "/0/query";
        } else {
            url = serviceUrl + "/MapServer/0/query";
        }

        HttpEntity httpEntity = HttpHelper.HttpGet(url + "?" + param.toString());
        try {
            String jsonResult = EntityUtils.toString(httpEntity);
            JSONObject jsonObject = JSON.parseObject(jsonResult);
            if (isError(jsonObject)) {
                // 记录日志
                Log(url, param.toString(), jsonResult);
                return null;
            }
            JSONArray ja = jsonObject.getJSONArray("features");
            if (ja != null && ja.size() > 0) {
                JSONObject item = ja.getJSONObject(0).getJSONObject("geometry");
                result = JSON.parseObject(item.toJSONString(), Polygon.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }



    public static String getSpotAttribute(String mid) {
        AppSettings appSettings = new AppSettings();
        String serviceUrl = appSettings.getAllWeatherSpotLocationService();

        ArcGISWQueryParam param = new ArcGISWQueryParam(null, "MID=" + mid);
        int removeIndex = serviceUrl.indexOf("/FeatureServer");
        if (removeIndex >= 0) {
            serviceUrl = serviceUrl.substring(0, removeIndex);
        }

        String url ="";
        if (serviceUrl.indexOf("MapServer") >0){
            url = serviceUrl + "/0/query";
        }else {
            url = serviceUrl + "/MapServer/0/query";
        }

        HttpEntity httpEntity = HttpHelper.HttpGet(url + "?" + param.toString());
        try {
            String jsonResult = EntityUtils.toString(httpEntity);
            JSONObject jsonObject = JSON.parseObject(jsonResult);
            if (isError(jsonObject)) {
                // 记录日志
                Log(url, param.toString(), jsonResult);
                return null;
            }
            JSONArray ja = jsonObject.getJSONArray("features");
            if (ja != null && ja.size() > 0) {
                String item = ja.getJSONObject(0).getJSONObject("attributes").toJSONString();
                return item;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    private static List<NameValuePair> addQTHParamsList(String dict, Polygon shape) {
        String features = "[{" + "\"geometry\":" + JSON.toJSONString(shape, SerializerFeature.WriteMapNullValue)
                + ",\"attributes\":" + dict + "}]";

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("f", "json"));
        params.add(new BasicNameValuePair("gdbVersion", "null"));
        params.add(new BasicNameValuePair("rollbackOnFailure", "true"));
        params.add(new BasicNameValuePair("features", features));
        return params;
    }

    private static List<NameValuePair> addQTHParamsList(Dictionary<String, Object> dict, Polygon shape) {
        @SuppressWarnings("deprecation")
        String features = "[{" + "\"geometry\":" + JSON.toJSONString(shape, SerializerFeature.WriteMapNullValue)
                + ",\"attributes\":{" + "\"JCBH\":\"" + (dict.get("JCBH") != null ? dict.get("JCBH").toString() : "")
                + "\"," + "\"XZQDM\":" + (dict.get("XZQDM") != null ? dict.get("XZQDM").toString() : "") + ","
                + "\"XMC\":\"" + (dict.get("XMC") != null ? (dict.get("XMC").toString()) : "") + "\"," + "\"GLQDM\":"
                + (dict.get("GLQDM") != null ? dict.get("GLQDM").toString() : "") + "," + "\"GLQMC\":\""
                + (dict.get("GLQMC") != null ? dict.get("GLQMC").toString() : "") + "\"," + "\"MID\":"
                + (dict.get("MID") != null ? dict.get("MID").toString() : "0") + "," + "\"JCMJ\":"
                + (dict.get("JCMJ") != null ? Double.parseDouble(dict.get("JCMJ").toString()) : 0) + "," + "\"JSYDMJ\":"
                + (dict.get("JSYDMJ") != null ? Double.parseDouble(dict.get("JSYDMJ").toString()) : 0) + ","
                + "\"GDMJ\":" + (dict.get("GDMJ") != null ? Double.parseDouble(dict.get("GDMJ").toString()) : 0) + ","
                + "\"TBLX\":" + (dict.get("TBLX") != null ? dict.get("TBLX").toString() : "") + ","
                //+ "\"TZ\":"	+ (dict.get("TZ") != null ? dict.get("TZ").toString() : "") + ","
                + "\"SJY\":" + (dict.get("SJY") != null ? dict.get("SJY").toString() : "") + ","
                + "\"QSX\":" + (dict.get("QSX") != null ? dict.get("QSX").toString() : "") + ","
                + "\"HSX\":" + (dict.get("HSX") != null ? dict.get("HSX").toString() : "") + ","
                + "\"XZB\":" + (dict.get("XZB") != null ? Double.parseDouble(dict.get("XZB").toString()) : 0) + ","
                + "\"YZB\":" + (dict.get("YZB") != null ? Double.parseDouble(dict.get("YZB").toString()) : 0) + ","
                + "\"JCMJ\":" + (dict.get("JCMJ") != null ? Double.parseDouble(dict.get("JCMJ").toString()) : 0) + ","
                + "\"MJFJ\":" + (dict.get("MJFJ") != null ? dict.get("MJFJ").toString() : 0) + ","
                + "\"NMJ\":" + (dict.get("NMJ") != null ? Double.parseDouble(dict.get("NMJ").toString()) : 0) + ","
                + "\"CMJ\":" + (dict.get("CMJ") != null ? Double.parseDouble(dict.get("CMJ").toString()) : 0) + ","
                + "\"FMJ\":" + (dict.get("FMJ") != null ? Double.parseDouble(dict.get("FMJ").toString()) : 0) + ","
                + "\"XFMJ\":" + (dict.get("XFMJ") != null ? Double.parseDouble(dict.get("XFMJ").toString()) : 0) + ","
                //+ "\"BHQMC\":" + (dict.get("BHQMC") != null ? dict.get("BHQMC").toString() : "") + ","
                + "\"BHQYMJ\":" + (dict.get("BHQYMJ") != null ? Double.parseDouble(dict.get("BHQYMJ").toString()) : 0) + ","
                + "\"BHQNMJ\":" + (dict.get("BHQNMJ") != null ? Double.parseDouble(dict.get("BHQNMJ").toString()) : 0) + ","
                + "\"DTFBL\":" + (dict.get("DTFBL") != null ? dict.get("DTFBL").toString() : 0)
                //+ "\"BZ\":" + (dict.get("BZ") != null ? dict.get("BZ").toString() : "")
                + "}}]";

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("f", "json"));
        params.add(new BasicNameValuePair("gdbVersion", "null"));
        params.add(new BasicNameValuePair("rollbackOnFailure", "true"));
        params.add(new BasicNameValuePair("features", features));
        return params;
    }

    private static List<NameValuePair> addParamsList(Dictionary<String, Object> dict, Polygon shape) {
        String features = "[{" + "\"geometry\":" + JSON.toJSONString(shape, SerializerFeature.WriteMapNullValue) + ",\"attributes\":{" + "\"TBBH\":\""
                + (dict.get("TBBH") != null ? dict.get("TBBH").toString() : "") + "\"," + "\"XZQDM\":"
                + (dict.get("XZQDM") != null ? dict.get("XZQDM").toString() : "") + "," + "\"XZQMC\":\""
                + (dict.get("XZQMC") != null ? dict.get("XZQMC").toString() : "") + "\"," + "\"GLQDM\":"
                + (dict.get("GLQDM") != null ? dict.get("GLQDM").toString() : "") + "," + "\"GLQMC\":\""
                + (dict.get("GLQMC") != null ? dict.get("GLQMC").toString() : "") + "\"," + "\"MID\":"
                + (dict.get("MID") != null ? dict.get("MID").toString() : "0") + "," + "\"JCMJ\":"
                + (dict.get("JCMJ") != null ? Double.parseDouble(dict.get("JCMJ").toString()) : 0) + "," + "\"JSYDMJ\":"
                + (dict.get("JSYDMJ") != null ? Double.parseDouble(dict.get("JSYDMJ").toString()) : 0) + ","
                + "\"GDMJ\":" + (dict.get("GDMJ") != null ? Double.parseDouble(dict.get("GDMJ").toString()) : 0)
                + "}}]";
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("f", "json"));
        params.add(new BasicNameValuePair("gdbVersion", "null"));
        params.add(new BasicNameValuePair("rollbackOnFailure", "true"));
        params.add(new BasicNameValuePair("features", features));
        return params;
    }

    private static String prepareQTHAddParams(Dictionary<String, Object> dict, Polygon shape) {
        return "f=json&" + "gdbVersion=null&" + "rollbackOnFailure=true&" + "features=" + "[{" + "\"geometry\":"
                + JSON.toJSONString(shape) + ",\"attributes\":{" + "\"JCBH\":\"" + (dict.get("JCBH") != null ? dict.get("JCBH").toString() : "")
                + "\","
                + "\"XZQDM\":" + (dict.get("XZQDM") != null ? dict.get("XZQDM").toString() : "") + ","
                + "\"XMC\":\"" + (dict.get("XMC") != null ? dict.get("XMC").toString() : "") + "\","
                + "\"GLQDM\":" + (dict.get("GLQDM") != null ? dict.get("GLQDM").toString() : "") + ","
                + "\"GLQMC\":\"" + (dict.get("GLQMC") != null ? dict.get("GLQMC").toString() : "") + "\","
                + "\"MID\":" + (dict.get("MID") != null ? dict.get("MID").toString() : "0") + ","
                + "\"JCMJ\":" + (dict.get("JCMJ") != null ? Double.parseDouble(dict.get("JCMJ").toString()) : 0) + ","
                + "\"JSYDMJ\":" + (dict.get("JSYDMJ") != null ? Double.parseDouble(dict.get("JSYDMJ").toString()) : 0) + ","
                + "\"GDMJ\":" + (dict.get("GDMJ") != null ? Double.parseDouble(dict.get("GDMJ").toString()) : 0) + ","
                + "\"TBLX\":" + (dict.get("TBLX") != null ? dict.get("TBLX").toString() : "") + ","
                + "\"SJY\":" + (dict.get("SJY") != null ? dict.get("SJY").toString() : "") + ","
                + "\"QSX\":" + (dict.get("QSX") != null ? dict.get("QSX").toString() : "") + ","
                + "\"HSX\":" + (dict.get("HSX") != null ? dict.get("HSX").toString() : "") + ","
                + "\"XZB\":" + (dict.get("XZB") != null ? Double.parseDouble(dict.get("XZB").toString()) : 0) + ","
                + "\"YZB\":" + (dict.get("YZB") != null ? Double.parseDouble(dict.get("YZB").toString()) : 0) + ","
                + "\"JCMJ\":" + (dict.get("JCMJ") != null ? Double.parseDouble(dict.get("JCMJ").toString()) : 0) + ","
                + "\"MJFJ\":" + (dict.get("MJFJ") != null ? dict.get("MJFJ").toString() : 0) + ","
                + "\"NMJ\":" + (dict.get("NMJ") != null ? Double.parseDouble(dict.get("NMJ").toString()) : 0) + ","
                + "\"CMJ\":" + (dict.get("CMJ") != null ? Double.parseDouble(dict.get("CMJ").toString()) : 0) + ","
                + "\"FMJ\":" + (dict.get("FMJ") != null ? Double.parseDouble(dict.get("FMJ").toString()) : 0) + ","
                + "\"XFMJ\":" + (dict.get("XFMJ") != null ? Double.parseDouble(dict.get("XFMJ").toString()) : 0) + ","
                //+ "\"BHQMC\":" + (dict.get("BHQMC") != null ? dict.get("BHQMC").toString() : "") + ","
                //+ "\"BZ\":" + (dict.get("BZ") != null ? dict.get("BZ").toString() : "")
                + "}}]";
    }

    private static String prepareAddParams(Dictionary<String, Object> dict, Polygon shape) {
        return "f=json&" + "gdbVersion=null&" + "rollbackOnFailure=true&" + "features=" + "[{" + "\"geometry\":"
                + JSON.toJSONString(shape) + ",\"attributes\":{" + "\"TBBH\":\""
                + (dict.get("TBBH") != null ? dict.get("TBBH").toString() : "") + "\"," + "\"XZQDM\":"
                + (dict.get("XZQDM") != null ? dict.get("XZQDM").toString() : "") + "," + "\"XZQMC\":\""
                + (dict.get("XZQMC") != null ? dict.get("XZQMC").toString() : "") + "\"," + "\"GLQDM\":"
                + (dict.get("GLQDM") != null ? dict.get("GLQDM").toString() : "") + "," + "\"GLQMC\":\""
                + (dict.get("GLQMC") != null ? dict.get("GLQMC").toString() : "") + "\"," + "\"MID\":"
                + (dict.get("MID") != null ? dict.get("MID").toString() : "0") + "," + "\"JCMJ\":"
                + (dict.get("JCMJ") != null ? Double.parseDouble(dict.get("JCMJ").toString()) : 0) + "," + "\"JSYDMJ\":"
                + (dict.get("JSYDMJ") != null ? Double.parseDouble(dict.get("JSYDMJ").toString()) : 0) + ","
                + "\"GDMJ\":" + (dict.get("GDMJ") != null ? Double.parseDouble(dict.get("GDMJ").toString()) : 0)
                + "}}]";
    }

    private static String prepareUpdateParams(Dictionary<String, Object> dict, Polygon shape, String objectID) {
        return "f=json&" + "gdbVersion=null&" + "rollbackOnFailure=true&" + "features=" + "[{" + "\"geometry\":"
                + JSON.toJSONString(shape) + ",\"attributes\":{" + "\"TBBH\":\""
                + (dict.get("TBBH") != null ? dict.get("TBBH").toString() : "") + "\"," + "\"OBJECTID\":" + objectID
                + "," + "\"JCMJ\":" + (dict.get("JCMJ") != null ? Double.parseDouble(dict.get("JCMJ").toString()) : 0)
                + "," + "\"JSYDMJ\":"
                + (dict.get("JSYDMJ") != null ? Double.parseDouble(dict.get("JSYDMJ").toString()) : 0) + ","
                + "\"GDMJ\":" + (dict.get("GDMJ") != null ? Double.parseDouble(dict.get("GDMJ").toString()) : 0)
                + "}}]";
    }

    private static List<NameValuePair> updateParamsList(Dictionary<String, Object> dict, Polygon shape,
                                                        String objectID) {
        String features = "[{" + "\"geometry\":" + JSON.toJSONString(shape) + ",\"attributes\":{" + "\"TBBH\":\""
                + (dict.get("TBBH") != null ? dict.get("TBBH").toString() : "") + "\"," + "\"OBJECTID\":" + objectID
                + "," + "\"JCMJ\":" + (dict.get("JCMJ") != null ? Double.parseDouble(dict.get("JCMJ").toString()) : 0)
                + "," + "\"JSYDMJ\":"
                + (dict.get("JSYDMJ") != null ? Double.parseDouble(dict.get("JSYDMJ").toString()) : 0) + ","
                + "\"GDMJ\":" + (dict.get("GDMJ") != null ? Double.parseDouble(dict.get("GDMJ").toString()) : 0)
                + "}}]";

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("f", "json"));
        params.add(new BasicNameValuePair("gdbVersion", "null"));
        params.add(new BasicNameValuePair("rollbackOnFailure", "true"));
        params.add(new BasicNameValuePair("features", features));
        return params;
    }

    private static String prepareDeleteParams(String objectid) {
        return "f=json&" + "gdbVersion=null&" + "rollbackOnFailure=true&" + "objectids=" + objectid;
    }

    private static List<NameValuePair> deleteParamsList(String objectid) {

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("f", "json"));
        params.add(new BasicNameValuePair("gdbVersion", "null"));
        params.add(new BasicNameValuePair("rollbackOnFailure", "true"));
        params.add(new BasicNameValuePair("objectids", objectid));
        return params;
    }

    /**
     * 服务调用是否产生了错误
     *
     * @param results 服务响应的Json对象
     * @return
     */
    private static boolean isError(JSONObject results) {
        if (results.containsKey("error")) {
            return true;
        }
        return false;
    }

    /**
     * 记录日志
     *
     * @param url        请求路径
     * @param param      请求参数
     * @param jsonResult 返回结果
     * @throws Exception
     */
    private static void Log(String url, String param, String jsonResult) throws Exception {
        String message = "ArcGis Feature Service请求出错：请求url：" + url + "参数:" + param + "返回结果:"
                + jsonResult;
        throw new Exception(message);
    }

    public static MultiPolygon DifferenceAction(String serviceUrl, Polygon sPolygon, Polygon tPolygon) {
        MultiPolygon mps;
        try {
            if (sPolygon == null || tPolygon == null)
                return null;
            ArcGISDifferenceParam param = new ArcGISDifferenceParam(sPolygon);
            param.AddPolygon(tPolygon);
            String url = String.format("%s/difference", serviceUrl.substring(0, serviceUrl.lastIndexOf("/")));
            // HttpCommunicator http = new
            // HttpCommunicator(HttpCommunicator.ContentType.Form);
            // MultiPolygon mps = http.Post<MultiPolygon>(url,
            // param.toString());
            List<NameValuePair> list = new ArrayList<>();
            list.add(new BasicNameValuePair("param", param.toString()));
            String json = EntityUtils.toString(HttpHelper.HttpPost(url, list));
            mps = new Gson().fromJson(json, MultiPolygon.class);
            return mps != null && mps.Geometries.size() > 0 && mps.Geometries.get(0).getRings().size() > 0 ? mps : null;
        } catch (JsonSyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

}
