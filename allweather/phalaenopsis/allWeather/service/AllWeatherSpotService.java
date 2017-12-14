package phalaenopsis.allWeather.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.httpclient.util.DateUtil;
import org.apache.commons.io.FileUtils;
//import org.gdal.gdal.gdal;
//import org.gdal.ogr.DataSource;
//import org.gdal.ogr.Driver;
//import org.gdal.ogr.ogr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableLoadTimeWeaving;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.itextpdf.text.pdf.hyphenation.TernaryTree.Iterator;

//import oracle.net.aso.p;
import phalaenopsis.allWeather.dao.IallWeatherSpotdao;
import phalaenopsis.allWeather.dao.IallWeatherdao;
import phalaenopsis.allWeather.entity.ResultPolygon;
import phalaenopsis.allWeather.entity.ResultPolygon.ResultPolygonErrorEnum;
import phalaenopsis.allWeather.entity.SwMapspot;
import phalaenopsis.common.entity.AppSettings;
import phalaenopsis.common.entity.User;
import phalaenopsis.common.entity.Attachment.Attachment;
import phalaenopsis.common.entity.Attachment.FileState;
import phalaenopsis.common.method.Basis;
import phalaenopsis.common.method.Attachment.Multipart;
import phalaenopsis.common.method.Tools.DateUtils;
import phalaenopsis.common.method.Tools.Linq;
import phalaenopsis.common.method.Tools.StrUtil;
import phalaenopsis.common.method.Tools.UUID64;
import phalaenopsis.common.util.BLHCoordinate;
import phalaenopsis.common.util.CoordinateHelper;
import phalaenopsis.satellitegraph.dao.SatelliteGraphDaoPartial;
import phalaenopsis.satellitegraph.entity.Geometry;
import phalaenopsis.satellitegraph.entity.IMapSpot;
import phalaenopsis.satellitegraph.entity.Polygon;
import phalaenopsis.satellitegraph.utils.IMapSpotOperationExecutor;
import phalaenopsis.satellitegraph.utils.MapSpotOperator;
import phalaenopsis.satellitegraph.utils.ArcGISHelper.SpotArcGISHelper;

@Service
public class AllWeatherSpotService extends Basis implements IMapSpotOperationExecutor {

    @Autowired
    private SatelliteGraphDaoPartial sgService;

    public AllWeatherSpotService() {
        String projectPath = getProjectPath();
        File file = new File(projectPath);
        file = new File(file, "shape");
    }

//    @Autowired
//    private IallWeatherdao dao;

    public boolean segment(SwMapspot source, List<SwMapspot> spots) {
        boolean hasCoordinate = source.getFileServerPaths() != null && source.getFileServerPaths().length > 0;
        if (hasCoordinate) {
            String storagePath = getStorageFile(source);
            source.setCoordinateStorage(storagePath);
        }

        MapSpotOperator operator = new MapSpotOperator(this);
        boolean result = operator.segment(source, new ArrayList<IMapSpot>(spots));

        // 保留原有的分宗文件
        if (hasCoordinate) {
            copyCoordinateToStorage(source);
        }
        return result;
    }

    private String getStorageFile(SwMapspot source) {
        // 1.1,获取文件上传的路径下的coordinate文件夹
        //File file = new File(getCoordiatePath());

        // 1.2,创建存放坐标文件的文件夹
        StringBuffer string = new StringBuffer(coordinate);
        string.append(File.separator);

        string.append(DateUtils.formatDate(Calendar.getInstance().getTime()));
        string.append("_").append(source.getDistrictcounty()).append("_").append(source.getSpotnumber());
        //string = coordinate + File.pathSeparator + string;
        //file = new File(file, string.toString());
        return string.toString(); // file.getPath();
    }

    /**
     * 将坐标文件由临时文件夹，copy到服务器指定的文件夹中($\{UploadFolder}\coordinate)
     *
     * @param source
     * @return
     */
    private boolean copyCoordinateToStorage(SwMapspot source) {
        String shapeFolder = getShapFolder(); // 获取坐标临时存放的文件夹
        File file = new File(getUplodFolder(), source.getCoordinateStorage());
        if (!file.exists()) {
            file.mkdirs();
        }

        for (String serverPath : source.getFileServerPaths()) {
            File sourceFolder = new File(shapeFolder, serverPath);
            try {
                FileUtils.copyDirectory(sourceFolder, file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    public boolean merge(List<SwMapspot> sourceSpots, SwMapspot newSpot) {
        MapSpotOperator op = new MapSpotOperator(this);
        boolean result = op.merge(new ArrayList<IMapSpot>(sourceSpots), newSpot);
        return result;
    }

    public boolean restore(SwMapspot mapSpot2016) {
        MapSpotOperator op = new MapSpotOperator(this);
        boolean result = op.restoreAll(mapSpot2016);
        return result;
    }

    @Override
    public boolean onAddAndDelete(List<IMapSpot> addSpots, List<Long> deleteIDList) {
        boolean result = false;

        // MapSpot2016 spot2016 = selectOneMapSpot2016(deleteIDList.get(0)); //
        // sqlSession.selectOne("satellitegraphservice.selectOneMapSpot2016",
        // deleteIDList.get(0));

        for (IMapSpot item : addSpots) {
            SwMapspot spot = (SwMapspot) item;
            // spot.setID(UUID64.newUUID64().getValue());
            // spot.setInstanceID(saveInstances(spot2016.getInstanceID()));
            // spot.setSpotKind(spot2016.getSpotKind());

            insertMapSpot2016(spot);

            Dictionary<String, Object> dict = new Hashtable<String, Object>();
            dict.put("DMID", deleteIDList.get(0));
            dict.put("MID", spot.getID());
            dict.put("XZQDM", spot.getCounty());
            //dict.put("XMC", spot.getCountyname());
            dict.put("XMC", spot.getDistrictcountyname());
            dict.put("JCBH", spot.getSpotnumber());
            dict.put("TBLX", spot.getSpottype());

            if (!StrUtil.isNullOrEmpty(spot.getTz())) {
                dict.put("TZ", spot.getTz());
            }

            if (!StrUtil.isNullOrEmpty(spot.getDatasource())) {
                dict.put("SJY", spot.getDatasource());
            }

            if (!StrUtil.isNullOrEmpty(spot.getBeforetime())) {
                dict.put("QSX", spot.getBeforetime());
            }

            if (!StrUtil.isNullOrEmpty(spot.getAftertime())) {
                dict.put("HSX", spot.getAftertime());
            }

            if (null != spot.getCoordinateX()) {
                dict.put("XZB", spot.getCoordinateX());
            }

            if (null != spot.getCoordinateY()) {
                dict.put("YZB", spot.getCoordinateY());
            }

            if (null != spot.getSpotarea()) {
                dict.put("JCMJ", spot.getSpotarea());
            }

            if (!StrUtil.isNullOrEmpty(spot.getArealevel())) {
                dict.put("MJFJ", spot.getArealevel());
            }

            if (null != spot.getDoubtillegalarea()) {
                dict.put("NMJ", spot.getDoubtillegalarea());
            }

            if (null != spot.getCityborderarea()) {
                dict.put("CMJ", spot.getCityborderarea());
            }

            if (null != spot.getJbntarea()) {
                dict.put("FMJ", spot.getJbntarea());
            }

            if (null != spot.getXhdjbntarea()) {
                dict.put("XFMJ", spot.getXhdjbntarea());
            }

            if (!StrUtil.isNullOrEmpty(spot.getProtectname())) {
                dict.put("BHQMC", spot.getProtectname());
            }

            if (null != spot.getProtectpassarea()) {
                dict.put("BHQYMJ", spot.getProtectpassarea());
            }

            if (null != spot.getProtectfailarea()) {
                dict.put("BHQNMJ", spot.getProtectfailarea());
            }

            if (!StrUtil.isNullOrEmpty(spot.getRemark())) {
                dict.put("BZ", spot.getRemark());
            }

            if (!StrUtil.isNullOrEmpty(spot.getSpotnumber())) {
                dict.put("TBBH", spot.getSpotnumber());
            }

            if (null != spot.getDistrictcounty()) {
                dict.put("XZQDM", spot.getDistrictcounty());
            }

            if (!StrUtil.isNullOrEmpty(spot.getDistrictcountyname())) {
                dict.put("XZQMC", spot.getDistrictcountyname());
            }

            if (null != spot.getCounty()) {
                dict.put("GLQDM", spot.getCounty());
            }

            if (!StrUtil.isNullOrEmpty(spot.getCountyname())) {
                dict.put("GLQMC", spot.getCountyname());
            }

            //dict.put("MID", spot.getID());

            if (null != spot.getSpotarea()) {
                dict.put("JCMJ", spot.getSpotarea());
            }

            if (null != spot.getMapratio()) {
                dict.put("DTFBL", spot.getMapratio());
            }

            // dict.put("JSYDMJ", spot.getConstructionAcreage());
            dict.put("GDMJ", spot.getArableAcreage());

            result = SpotArcGISHelper.addQTHFeatures(new AppSettings().getAllWeatherSpotLocationService(),
                    spot.getShape(), dict);
        }
        result = SpotArcGISHelper.deleteFeatures(new AppSettings().getAllWeatherSpotLocationService(), deleteIDList);
        deleteMapSpot2016s(deleteIDList);

        return result;
    }

    // ================================================//

    /**
     * 获取当前核查年份
     *
     * @return
     */
    public int getCurrentYear() {
        // notNull(allCheckPeriod);
//        List<String> allCheckPeriod = dao.getCurrentPeriod();
//        String string = allCheckPeriod.get(0).split("-")[0];
//        return Integer.valueOf(string);
        return 1;
    }

    /**
     * 获取当前核查期数
     *
     * @return
     */
    public int getCurrentCheckPeriod() {
//        List<String> allCheckPeriod = dao.getCurrentPeriod();
//        String string = allCheckPeriod.get(0).split("-")[1];
//        return Integer.valueOf(string);
        return 1;
    }

//    @Autowired
//    private IallWeatherSpotdao allWeatherSpotdao;

    private int deleteMapSpot2016s(List<Long> ids) {
//        int year = getCurrentYear(), checkPeriod = getCurrentCheckPeriod();
//        return allWeatherSpotdao.deleteSwMapSpot(ids, year, checkPeriod);
        return 1;
    }

    private int insertMapSpot2016(SwMapspot mapspot) {
        int year = getCurrentYear(), checkPeriod = getCurrentCheckPeriod();
        mapspot.setYear(year);
        mapspot.setCheckperiod(checkPeriod);
        if (!StrUtil.isNullOrEmpty(mapspot.getCoordinateStorage())) {
            File file = new File(getUplodFolder(), mapspot.getCoordinateStorage());
            try {
                FileUtils.deleteDirectory(file);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return 1;
    }

    public List<SwMapspot> getSpotsByNum(String num, Integer period, Integer mapratio) {
        User currentUser = getCurrentUser();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("startNum", 1);
        map.put("endNum", 100);
        map.put("spotnumber", num);
        map.put("period", period);
        map.put("mapratio", mapratio);
        map.put("regionId", currentUser.getRegionId());

        int year = getCurrentYear(), checkPeriod = getCurrentCheckPeriod();
        map.put("year", year);
        map.put("checkPeriod", checkPeriod);
        map.put("regionId", currentUser.getRegions()[0].getRegionID());
        map.put("node", "all");
        map.put("sort", "desc");

        // List<SwMapspot> result = dao.getMapspotList(map);
//        List<SwMapspot> result = dao.getMapspotListBlur(map);

        return null;
    }

    public boolean getSpotStatus(long id) {
        boolean result = sgService.getSpotStatus(id);
        return result;
    }

    private static final java.util.Random random = new java.util.Random();

    /**
     * shape目录下存放shape文件的目录
     *
     * @return
     */
    private String getShapFileFolder() {
        String randomPath = DateUtils.formatDate(Calendar.getInstance().getTime()) + random.nextInt(10000);
        File file = new File(getShapFolder(), randomPath);

        if (!file.exists()) {
            file.mkdirs();
        }
        return file.getPath();
    }

    public ResultPolygon readShape(CommonsMultipartFile[] commonsMultipartFiles) throws IOException {

        String shapeFolder = getShapFileFolder();
        File shapeRandomFolder = new File(shapeFolder);
        String shapeFile = null;

        for (CommonsMultipartFile commonsMultipartFile : commonsMultipartFiles) {
            String fileName = new String(commonsMultipartFile.getOriginalFilename().getBytes("ISO8859-1"), "UTF-8");

            if (null != fileName
                    && fileName.substring(fileName.length() - 4, fileName.length() - 0).equalsIgnoreCase(".shp")) {
                shapeFile = fileName;
            }

            File file = new File(shapeFolder, fileName);
            commonsMultipartFile.transferTo(file);
        }

        if (null == shapeFile) {
            return new ResultPolygon(false, ResultPolygonErrorEnum.InvalidFile);
        }

//        ogr.RegisterAll();
//        // 支持中文路径
//        gdal.SetConfigOption("GDAL_FILENAME_IS_UTF8", "YES");
//        // 支持中文字段
//        gdal.SetConfigOption("SHAPE_ENCODING", "CP936");
//
//        DataSource inputSource = ogr.Open(new File(shapeFolder, shapeFile).getPath());
//
//        if (inputSource == null) {
//            return new ResultPolygon(false, ResultPolygonErrorEnum.ReadShapeFaild);
//        }
//
//        Driver dv = ogr.GetDriverByName("GeoJSON");
//        if (dv == null) {
//            return new ResultPolygon(false, ResultPolygonErrorEnum.ReadShapeFaild);
//        }
//
//        String jsonPath = shapeFolder + "\\1.json";
//        inputSource = dv.CopyDataSource(inputSource, jsonPath);

//        File file = new File(jsonPath);
//        if (!file.exists()) {
//            return new ResultPolygon(false, ResultPolygonErrorEnum.ReadShapeFaild);
//        }

//        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
//        StringBuffer stringBuffer = new StringBuffer();
//        String s = null;
//        Polygon polygon = new Polygon();
//        while ((s = bufferedReader.readLine()) != null) {
//            stringBuffer.append(s);
//        }
//        bufferedReader.close();

//        int index = stringBuffer.toString().indexOf("\"coordinates\":");
//        String points = stringBuffer.toString().substring(index + "\"coordinates\":".length(),
//                stringBuffer.toString().length() - 3);
//        JSONArray jsonArray = JSONObject.parseArray(points);

        List<ArrayList<Double[]>> list = new ArrayList<ArrayList<Double[]>>();
//
//        for (Object object1 : jsonArray) {
//            JSONArray jsonArray1 = JSONObject.parseArray(object1.toString());
//            ArrayList<Double[]> doubles = new ArrayList<>();
//            for (Object object3 : jsonArray1) {
//                String kk = object3.toString();
//                kk = kk.substring(1, kk.length() - 2);
//                String[] array = kk.split(",");
//
//                Double xDouble = Double.valueOf(array[0]), yDouble = Double.valueOf(array[1]);
//                if (xDouble > 999.99 || yDouble > 999.99) {
//                    if (xDouble < 999999.99) {
//                        return new ResultPolygon(false, ResultPolygonErrorEnum.UnCodeNum);
//                    }
//
//                    BLHCoordinate blhCoordinate = CoordinateHelper.GaussProjInvCal(xDouble, yDouble, 0);
//                    xDouble = blhCoordinate.getLatitude(); // blhCoordinate.getLongitude();
//                    yDouble = blhCoordinate.getLongitude(); // blhCoordinate.getLatitude();
//                }
//
//                Double[] doubles2 = {xDouble, yDouble};
//                doubles.add(doubles2);
//            }
//
//            if (!Linq.equals(doubles.get(0), doubles.get(doubles.size() - 1)))
//                return new ResultPolygon(false, ResultPolygonErrorEnum.IllegalShape);
//
//            list.add(doubles);
//        }
//
//        polygon.setRings(list);
//        polygon.setType("polygon");

        // deleteFolder(shapeFolder);

        return null;
    }

    public ResultPolygon readTxt(HttpServletRequest request) throws IOException {

        Multipart part = new Multipart();
        MultipartFile multifile = part.getUploadFile(request);

        if (null == multifile) {
            return null;
        }

        String fileName = multifile.getOriginalFilename();
        fileName = new String(fileName.getBytes("ISO8859-1"), "UTF-8");

        if (!fileName.substring(fileName.length() - 4, fileName.length()).equals(".txt")) {
            return new ResultPolygon(false, ResultPolygonErrorEnum.InvalidFile);
        }

        String shapeFolder = getShapFileFolder();
        File shapeRandomFolder = new File(shapeFolder);
        File file = new File(shapeFolder, fileName);
        multifile.transferTo(file);

        Map<String, String> resultMap = new LinkedHashMap<String, String>();

        @SuppressWarnings("resource")
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String s = null;
        while ((s = bufferedReader.readLine()) != null) {
            if (s.indexOf("J") > -1) {
                String[] strings = s.split(",");
                if (strings.length == 4) {
                    if (resultMap.containsKey(strings[1])) {
                        resultMap.put(strings[1], resultMap.get(strings[1]) + ";" + strings[2] + "," + strings[3]);
                    } else {
                        resultMap.put(strings[1], strings[2] + "," + strings[3]);
                    }
                    System.out.println(strings[2] + "," + strings[3]);
                }
            }
        }

        Polygon polygon = new Polygon();
        List<ArrayList<Double[]>> list = new ArrayList<ArrayList<Double[]>>();
        for (Entry<String, String> entry : resultMap.entrySet()) {
            String vString = entry.getValue();
            String[] strings = vString.split(";");
            ArrayList<Double[]> doubles = new ArrayList<>();
            for (String strItem : strings) {
                String[] array = strItem.split(",");
                Double xDouble = Double.valueOf(array[1]), yDouble = Double.valueOf(array[0]);
                if (xDouble > 999.99 || yDouble > 999.99) {
                    if (xDouble < 999999.99) {
                        return new ResultPolygon(false, ResultPolygonErrorEnum.UnCodeNum);
                    }
                    BLHCoordinate blhCoordinate = CoordinateHelper.GaussProjInvCal(xDouble, yDouble, 0);
                    xDouble = blhCoordinate.getLatitude(); // blhCoordinate.getLongitude();
                    yDouble = blhCoordinate.getLongitude(); // blhCoordinate.getLatitude();
                }

                Double[] doubles2 = {xDouble, yDouble};
                doubles.add(doubles2);
            }

            if (strings[0].equals(strings[strings.length - 1]))
                polygon.setType("polygon");
            else
                polygon.setType("polyline");

            list.add(doubles);
        }
        polygon.setRings(list);

        // deleteFolder(shapeFolder);

        return new ResultPolygon(true, polygon, shapeRandomFolder.getName());
    }

}
