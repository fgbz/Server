package phalaenopsis.allWeather.service;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.URLEncoder;

import java.util.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.collections.map.HashedMap;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import phalaenopsis.allWeather.dao.IallWeatherCitydao;
import phalaenopsis.allWeather.dao.IallWeatherdao;
import phalaenopsis.allWeather.entity.HandleProgress;
import phalaenopsis.allWeather.entity.MapSpotStatistics;
import phalaenopsis.allWeather.entity.SwLog;
import phalaenopsis.allWeather.entity.SwMapspot;
import phalaenopsis.allWeather.entity.SwSde;
import phalaenopsis.allWeather.entity.YearAndPeriod;
import phalaenopsis.allWeather.enums.ReportEnum;
import phalaenopsis.common.annotation.ExcelTitle;
import phalaenopsis.common.entity.*;
import phalaenopsis.common.method.Basis;
import phalaenopsis.common.method.Tools.Linq;
import phalaenopsis.common.method.Tools.StrUtil;
import phalaenopsis.common.method.Tools.UUID64;
import phalaenopsis.common.method.cache.RegionCache;
import phalaenopsis.common.method.cache.UserCache;
import phalaenopsis.common.method.secrteKey.EncryptHelper;
import phalaenopsis.common.service.DataDictionaryService;
import phalaenopsis.satellitegraph.entity.MapSpot;

@Service("allWeatherService")
public class AllWeatherService extends Basis {


    @Autowired
    private IallWeatherdao dao;

    @Autowired
    private IallWeatherCitydao weatherCitydao;

    @Autowired
    private DataDictionaryService dicService;

    private static Pattern chinaPattern = Pattern.compile("([\\u4e00-\\u9fa5]+)");

    private Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();


    /**
     * 获取当前最新核查期数
     *
     * @return
     */
    public List<String> getCurrentSeason() {
//        return dao.getCurrentPeriod();
        return null;
    }

    /**
     * 获取处理进度
     */
    public HandleProgress getHandleProgress(Integer mark) {
//        User currentUser = getCurrentUser();
//        HandleProgress message = new HandleProgress();
//        if (currentUser.getOrgGrade() == OrganizationGrade.County) {
//            Region region = currentUser.getRegions()[0];
//            // 要传当前最新一期的核查期数，传一个regionId是不周全的
//            YearAndPeriod maxPeroid = weatherCitydao.getHistoryPeroid().get(0);// 第一条就是最大期数
//            Map<String, Object> map = new HashMap<>();
//            map.put("id", region.getRegionID());// 区域ID
//            map.put("year", maxPeroid.getYear());// 年份
//            map.put("period", maxPeroid.getCheckperiod());// 核查期数
//            map.put("mark", mark);
//            // 查询当前区域最新一期核查图斑
//            message = dao.getCountyProgress(map);
//        }
        return null;
    }

    /**
     * 上报
     *
     * @return
     */
    @org.springframework.transaction.annotation.Transactional
    public synchronized Boolean report() {
        User currentUser = getCurrentUser();
        if (currentUser.getOrgGrade() == OrganizationGrade.County) {

//            String cuperiod = dao.getCurrentPeriod().get(0);
//            String[] ps = cuperiod.split("-");
//            Integer year = Integer.valueOf(ps[0]);
//            Integer period = Integer.valueOf(ps[1]);
//
//            Region region = currentUser.getRegions()[0];
//
//            SwLog swLog = new SwLog(UUID64.newUUID64().getValue(), year, period, region.getRegionID(),
//                    ReportEnum.Report.getIntValue(), Calendar.getInstance().getTime(), currentUser.getOrganizationID(),
//                    String.valueOf(region.getParentID()), currentUser.getName(), currentUser.getId());
//            dao.saveOrUpdateSwLog(swLog);
//            Map<String, Object> map = new HashMap<>();
//            map.put("year", year);
//            map.put("period", period);
//            map.put("regionId", region.getRegionID());
//            dao.updateNodeCityState(map);

            return true;
        }
        return null;
    }

    public SwLog geBackInfo(Integer mark) {
//        User currentUser = getCurrentUser();
//        if (currentUser.getOrgGrade() == OrganizationGrade.County) {
//
//            String cuperiod = dao.getCurrentPeriod().get(0);
//            String[] ps = cuperiod.split("-");
//            Integer year = Integer.valueOf(ps[0]);
//            Integer period = Integer.valueOf(ps[1]);
//
//            Map<String, Object> map = new HashMap<>();
//            map.put("year", year);
//            map.put("period", period);
//            map.put("regionId", currentUser.getRegionId());
//            map.put("mark", mark);
//            SwLog swLog = dao.geBackInfo(map);
//            return swLog;
//        }

        return null;
    }

    /**
     * 确认违法
     *
     * @param spotNum
     * @return
     */
    @org.springframework.transaction.annotation.Transactional
    public Boolean changeNode(Long id, int node) {
        User currentUser = getCurrentUser();

        if (currentUser.getOrgGrade() == OrganizationGrade.County) {
            SwMapspot mapspot = new SwMapspot();
            mapspot.setID(id);
            mapspot.setNode(node);
            mapspot.setProveuserId(currentUser.getId());
            // mapspot.setSpotnumber(spotNum);
            // mapspot.setYear(getCurrentYear());
            // mapspot.setPeriod(getCurrentSeason());
            // mapspot.setNode(NodeEnum.IllegalSpot.getIntValue());

            dao.deleteSpotExpert(id);
            dao.updateSwMapSpot(mapspot);

            return true;
        }
        return null;
    }

    public Integer changeMobileNode(Long id, int node) {
        User currentUser = getCurrentUser();

        if (currentUser.getOrgGrade() == OrganizationGrade.County) {
            Integer oldNode = dao.getSpotNode(id);
            if (oldNode == 4 || oldNode == 5)
                return 2;

            // SwMapspot mapspot = new SwMapspot();
            // mapspot.setId(id);
            // mapspot.setNode(node);
            // mapspot.setTiming(Calendar.getInstance().getTime());
            // dao.updateSwMapSpot(mapspot);
            updateSpotNode(node, id, true);

            return 1;
        }
        return 0;
    }

    public Integer getNode(Long id) {
        return dao.getSpotNode(id);
    }

    public Boolean getSpotNode(Long id) {
        User currentUser = getCurrentUser();

        if (currentUser.getOrgGrade() == OrganizationGrade.County) {
            Integer oldNode = dao.getSpotNode(id);
            if (oldNode == 4 || oldNode == 5)
                return true;

            return false;
        }

        return null;
    }

    public boolean restore(Long id) {
        String yearPeriod = getCurrentSeason().get(0);
        String[] strings = yearPeriod.split("-");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("year", strings[0]);
        map.put("period", strings[1]);
        map.put("id", id);
        return dao.restore(map) > 0 ? true : false;
    }

    public PagingEntityForMobile<SwMapspot> getMapspotList(Page page, boolean isMobile) {
        User currentUser = getCurrentUser();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("isMobile", true);
        map.put("startNum", page.getStartNum());
        map.put("endNum", page.getEndNum());
        map.put("regionId", currentUser.getRegionId());
        map.put("year", page.getYear());
        if (null != page.getConditions()) {
            for (Condition condition : page.getConditions()) {
                if (null != condition.getKey() && null != condition.getValue()) {
                    map.put(condition.getKey(), condition.getValue());
                }
            }
        }

        if (!map.containsKey("sort")) {
            map.put("sort", "asc");
        }

        int count = dao.getMapspotCount(map);

        PagingEntityForMobile<SwMapspot> result = new PagingEntityForMobile<SwMapspot>();
        result.PageSize = page.getPageSize();
        result.PageCount = count == 0 ? 0 : (count - 1) / page.getPageSize() + 1; // 由于计算
        result.PageNo = page.getPageNo();
        result.RecordCount = count;
        result.CurrentList = markAlias(dao.getMapspotList(map));

        return result;
    }


    /**
     * 标记别名转换
     *
     * @param list 图标集合
     * @return 返回转换好的集合
     */
    public List<SwMapspot> markAlias(List<SwMapspot> list) {

        List<DataDictionaryItem> dics = getMapSpotMarkVisDic(null);
        list.forEach((item) -> {
            Region region = (Region) RegionCache.get(item.getCity().toString());
            if (region != null) {
                item.setCityName(region.getRegionName());
            }
            item.setTempRemark(gson.toJson(getNameMap(item, dics)));
        });

        return list;
    }

    private Map<String, String> getNameMap(SwMapspot item, List<DataDictionaryItem> dics) {
        StringBuilder markName = new StringBuilder();
        StringBuilder markAlias = new StringBuilder();

        Map<String, String> map = new HashMap<String, String>(2);

        for (DataDictionaryItem dic : dics) {
            if (!StrUtil.isNullOrEmpty(item.getMark()) && item.getMark().indexOf(dic.getValue()) > -1) {

                if (dic.getValue().equals("1000")) {
                    continue;
                }

                markName.append(dic.getText()).append(",");

                Matcher matcher = chinaPattern.matcher(dic.getRemarks());
                if (matcher.find()) {
                    markAlias.append(matcher.group());
                    markAlias.append(",");
                }
            }
        }
        map.put("name", markName.toString());
        map.put("alias", markAlias.toString());
        return map;
    }

    /**
     * 标记别名转换
     *
     * @param list 图标集合
     * @return 返回转换好的集合
     */
    public List<SwMapspot> markAllVisAlias(List<SwMapspot> list) {

        List<DataDictionaryItem> dics = dicService.getDataDictionaryItems("AllWeather", "Focus");

        list.forEach((item) -> {
            Region region = (Region) RegionCache.get(item.getCity().toString());
            if (region != null) {
                item.setCityName(region.getRegionName());
            }
            item.setTempRemark(gson.toJson(getNameMap(item, dics)));
        });

        return list;
    }

    private String fullAlias(List<DataDictionaryItem> dics, SwMapspot item) {
        StringBuilder markAlias = new StringBuilder();

        for (DataDictionaryItem dic : dics) {

            if (dic.getValue().equals("1000")) {
                continue;
            }

            if (!StrUtil.isNullOrEmpty(item.getMark()) && item.getMark().indexOf(dic.getValue()) > -1) {
                markAlias.append(dic.getText());
                markAlias.append(",");
            }
        }

        if (markAlias.length() > 0) {
            markAlias = markAlias.deleteCharAt(markAlias.length() - 1);
        }

        return markAlias.toString();
    }


    /**
     * 标记完整名称转换
     *
     * @param list 图标集合
     * @return 返回转换好的集合
     */
    public List<SwMapspot> markFullAlias(List<SwMapspot> list) {

        List<DataDictionaryItem> dics = dicService.getDataDictionaryItems("AllWeather", "Focus");

        list.forEach((item) -> {


            item.setTempRemark(fullAlias(dics, item));
        });

        return list;
    }

    /**
     * 标记完整名称转换
     *
     * @param list 图标集合
     * @return 返回转换好的集合
     */
    public List<SwMapspot> markVisAlias(List<SwMapspot> list) {

        List<DataDictionaryItem> dics = getMapSpotMarkVisDic(null);

        list.forEach((item) -> {

            item.setTempRemark(fullAlias(dics, item));
        });

        return list;
    }

    @Transactional
    public boolean proofSpot(SwMapspot mapSpot) {
        if (null != mapSpot.getSwExpert()) {
            if (null == mapSpot.getSwExpert().getId()) {
                Long expertid = UUID64.newUUID64().getValue();
                mapSpot.getSwExpert().setId(expertid);
                mapSpot.setExpertid(expertid);
            }
            mapSpot.getSwExpert().setTiming(new Date());
            dao.saveOrUpdateSwExpert(mapSpot.getSwExpert());
        }
        dao.updateSwMapSpot(mapSpot);
        return true;
    }

    public SwMapspot getSwMapspot(Long id) {
        SwMapspot mapspot = dao.getSwMapspot(id);
        if (mapspot.getExpertid() != null) {
            mapspot.setSwExpert(dao.getSwExpert(mapspot.getExpertid()));
        }
        return mapspot;
    }

    public List<SwSde> mobileSpotCoordinate(String spotNumber) {
        User currentUser = getCurrentUser();
        String season = getCurrentSeason().get(0);
        String year = season.split("-")[0];
        String peroid = season.split("-")[1];
        if (currentUser.getOrgGrade() == OrganizationGrade.County) {
            try {
                List<SwSde> result = dao.getSwSde(currentUser.getRegions()[0].getRegionID(), spotNumber, year, peroid);
                for (SwSde swSde : result) {
                    swSde.setShape(EncryptHelper.decryptDES(swSde.getShape(), EncryptHelper.key));
                }
                return result;
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
            // return dao.getSwSde(currentUser.getRegions()[0].getRegionID(),
            // spotNumber);
        }

        return null;
    }

    public String getMapspotCoordinate(Long id) {
        String coordinate = dao.getSwSdeById(id);
        try {
            User currentUser = getCurrentUser();
            coordinate = EncryptHelper.decryptDES(coordinate, EncryptHelper.key);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return coordinate;
    }

    public int updateSpotNode(int node, Long id, boolean mTiming) {

        SwMapspot mapspot = new SwMapspot();
        mapspot.setID(id); // .setId(id);
        mapspot.setNode(node);
        if (mTiming)
            mapspot.setTiming(Calendar.getInstance().getTime());

        return dao.updateSpotNode(mapspot);
    }

    public String[] getTitleName(Map<String, Object> map) {
        return null;
    }

    public Map<String, Object> getFieldName(Map<String, Object> map) {
        Map<String, Object> resultMap = new HashMap<>();
        String selectType = (String) map.get("selectType");
        if (selectType.equals("1")) {
            //合法图斑、违法图斑（1）
            String[] valuesName = {
                    "index",                    //"序号"
                    "tempRemark",               //图斑标记
                    "cityName",               //"市级名称",
                    "county",                   //"管理区划代码"
                    "countyname",               //"管理名称",
                    "spotnumber",               //"监测编号",
                    "spottype",                 //"图斑类型",
                    "mapratio",                 //"地图分辨率",
                    "spotarea",                 //"监测面积",
                    "arableAcreage",            //"占用耕地面积"
                    "adjustablearea",           //"可调整面积",
                    "beforetime",               //"前时项",
                    "aftertime",                //"后时项",
                    "remark"                    //"备注",
            };
            resultMap.put("exelType", "1");        //只有一层表头
            //resultMap.put("firstName", null); //表头名
            resultMap.put("valuesName", valuesName);
            resultMap.put("title", "合法图斑");
            return resultMap;
        }
        if (selectType.equals("2")) {
            //合法图斑、违法图斑（1）
            String[] valuesName = {
                    "index",                    //"序号"
                    "tempRemark",               //图斑标记
                    "cityName",               //"市级名称",
                    "county",                   //"管理区划代码"
                    "countyname",               //"管理名称",
                    "spotnumber",               //"监测编号",
                    "spottype",                 //"图斑类型",
                    "mapratio",                 //"地图分辨率",
                    "spotarea",                 //"监测面积",
                    "arableAcreage",            //"占用耕地面积"
                    "adjustablearea",           //"可调整面积",
                    "beforetime",               //"前时项",
                    "aftertime",                //"后时项",
                    "remark"                    //"备注",
                    // "",                       //"图斑分类",
                    /*"cityauditispass",        //"市级审核状态"
                    "cityauditremarks",         //"市级审核备注"
                    "provinceauditispass",      //"省级审核状态"
                    "provinceauditremarks",     //"省级审核备注*/
            };
            resultMap.put("exelType", "1");        //只有一层表头
            //resultMap.put("firstName", null); //表头名
            resultMap.put("valuesName", valuesName);
            resultMap.put("title", "违法图斑");
            return resultMap;
        }

        if (selectType.equals("3")) {
            //非新增图斑（2）
            String[] valuesName = {
                    "index",                    //"序号"
                    "tempRemark",               //图斑标记
                    "cityName",               //"市级名称",
                    "county",                   //"管理区划代码"
                    "countyname",               //"管理名称",
                    "spotnumber",               //"监测编号",
                    "spottype",                 //"图斑类型",
                    "mapratio",                 //"地图分辨率",
                    "spotarea",                 //"监测面积",
                    "arableAcreage",            //"占用耕地面积"
                    "adjustablearea",           //"可调整面积",
                    "beforetime",               //"前时项",
                    "aftertime",                //"后时项",
                    "notnewSpotclassification",               //"图斑分类",
                    "remark"                    //"备注",
                    /*"cityauditispass",        //"市级审核状态"
                    "cityauditremarks",         //"市级审核备注"
                    "provinceauditispass",      //"省级审核状态"
                    "provinceauditremarks",     //"省级审核备注*/
            };
            resultMap.put("exelType", "1");        //只有一层表头
            //resultMap.put("firstName", null); //表头名
            resultMap.put("valuesName", valuesName);
            resultMap.put("title", "非新增图斑");
            return resultMap;
        }

        if (selectType.equals("4") && (map.get("selectSpotType") == null || map.get("selectSpotType").equals(""))) {
            //举证合法图斑、图斑分类为全部（3）
            String[] valuesName = {
                    "index",                    //"序号"
                    "tempRemark",               //图斑标记
                    "cityName",               //"市级名称",
                    "county",                   //"管理区划代码"
                    "countyname",               //"管理名称",
                    "spotnumber",               //"监测编号",
                    "spottype",                 //"图斑类型",
                    "mapratio",                 //"地图分辨率",
                    "spotarea",                 //"监测面积",
                    "arableAcreage",            //"占用耕地面积"
                    "adjustablearea",           //"可调整面积",
                    "beforetime",               //"前时项",
                    "aftertime",                //"后时项",
                    "legalprooftype",               //"图斑分类",
                    "remark",                   //"备注",
                    "cityauditispass",          //"市级审核状态"
                    "cityauditremarks",         //"市级审核备注"
                    "provinceauditispass",      //"省级审核状态"
                    "provinceauditremarks"      //"省级审核备注
            };
            resultMap.put("exelType", "1");        //只有一层表头
            //resultMap.put("firstName", null); //表头名
            resultMap.put("valuesName", valuesName);
            resultMap.put("title", "举证合法图斑");
            return resultMap;
        }

        if (selectType.equals("5") && (map.get("selectSpotSort") == null || map.get("selectSpotSort").equals(""))) {
            //举证分新增图斑、图斑分类为全部（3）
            String[] valuesName = {
                    "index",                    //"序号"
                    "tempRemark",               //图斑标记
                    "cityName",               //"市级名称",
                    "county",                   //"管理区划代码"
                    "countyname",               //"管理名称",
                    "spotnumber",               //"监测编号",
                    "spottype",                 //"图斑类型",
                    "mapratio",                 //"地图分辨率",
                    "spotarea",                 //"监测面积",
                    "arableAcreage",            //"占用耕地面积"
                    "adjustablearea",           //"可调整面积",
                    "beforetime",               //"前时项",
                    "aftertime",                //"后时项",
                    "notnewSpotclassification",               //"图斑分类",
                    "remark",                   //"备注",
                    "cityauditispass",          //"市级审核状态"
                    "cityauditremarks",         //"市级审核备注"
                    "provinceauditispass",      //"省级审核状态"
                    "provinceauditremarks"      //"省级审核备注
            };
            resultMap.put("exelType", "1");        //只有一层表头
            //resultMap.put("firstName", null); //表头名
            //resultMap.put("startNo", null);   //二级表头起始
            //resultMap.put("endNo", null);     //二级表头截止
            resultMap.put("valuesName", valuesName);
            resultMap.put("title", "举证非新增图斑");
            return resultMap;
        }

        if (selectType.equals("5") && (((String) map.get("selectSpotSort")).equals("1"))) {
            //举证分新增图斑、设施农用地
            String[] valuesName = {
                    "index",                    //"序号"
                    "tempRemark",               //图斑标记
                    "cityName",               //"市级名称",
                    "county",                   //"管理区划代码"
                    "countyname",               //"管理名称",
                    "spotnumber",               //"监测编号",
                    "spottype",                 //"图斑类型",
                    "mapratio",                 //"地图分辨率",
                    "spotarea",                 //"监测面积",
                    "arableAcreage",            //"占用耕地面积"
                    "adjustablearea",           //"可调整面积",
                    "beforetime",               //"前时项",
                    "aftertime",                //"后时项",
                    "notnewSpotclassification",               //"图斑分类",
                    "remark",                   //"备注",
                    "ssnydProjectName",                   //"项目名称",
                    "ssnydApprovalusetype",                   //"批准用途",
                    "ssnydUsetype",                   //"实际用途",
                    "ssnydApprovalnumber",                   //"批准文号",
                    "ssnydApprovaltime",                   //"批准时间",
                    "ssnydApprovalarea",                   //"批准面积",
                    "ssnydBackupnumber",                   //"用地协议备案编号",
                    "notnewRemarks",                   //"备注",
                    "cityauditispass",          //"市级审核状态"
                    "cityauditremarks",         //"市级审核备注"
                    "provinceauditispass",      //"省级审核状态"
                    "provinceauditremarks"      //"省级审核备注
            };
            resultMap.put("exelType", "2");        //只有一层表头
            resultMap.put("firstName", "“设施农用地”基本情况"); //表头名
            resultMap.put("startNo", "16");   //二级表头起始
            resultMap.put("endNo", "23");     //二级表头截止
            resultMap.put("valuesName", valuesName);
            resultMap.put("title", "举证非新增图斑");
            return resultMap;
        }
        if (selectType.equals("5") && (((String) map.get("selectSpotSort")).equals("2"))) {
            //举证分新增图斑、临时用地
            String[] valuesName = {
                    "index",                    //"序号"
                    "tempRemark",               //图斑标记
                    "cityName",               //"市级名称",
                    "county",                   //"管理区划代码"
                    "countyname",               //"管理名称",
                    "spotnumber",               //"监测编号",
                    "spottype",                 //"图斑类型",
                    "mapratio",                 //"地图分辨率",
                    "spotarea",                 //"监测面积",
                    "arableAcreage",            //"占用耕地面积"
                    "adjustablearea",           //"可调整面积",
                    "beforetime",               //"前时项",
                    "aftertime",                //"后时项",
                    "notnewSpotclassification",               //"图斑分类",
                    "remark",                   //"备注",
                    "tempProjectName",                   //"项目名称",
                    "tempApprovalusetype",                   //"批准用途",
                    "tempUsetype",                   //"实际用途",
                    "tempApprovalnumber",                   //"批准文号",
                    "tempApprovaltime",                   //"批准时间",
                    "tempArea",                   //"批准面积",
                    "notnewRemarks",                   //"备注",
                    "cityauditispass",          //"市级审核状态"
                    "cityauditremarks",         //"市级审核备注"
                    "provinceauditispass",      //"省级审核状态"
                    "provinceauditremarks"      //"省级审核备注
            };
            resultMap.put("exelType", "2");        //只有一层表头
            resultMap.put("firstName", "“临时用地”基本情况"); //表头名
            resultMap.put("startNo", "16");   //二级表头起始
            resultMap.put("endNo", "22");     //二级表头截止
            resultMap.put("valuesName", valuesName);
            resultMap.put("title", "举证非新增图斑");
            return resultMap;
        }
        if (selectType.equals("5") && (((String) map.get("selectSpotSort")).equals("3"))) {
            //举证分新增图斑、油田用地
            String[] valuesName = {
                    "index",                    //"序号"
                    "tempRemark",               //图斑标记
                    "cityName",               //"市级名称",
                    "county",                   //"管理区划代码"
                    "countyname",               //"管理名称",
                    "spotnumber",               //"监测编号",
                    "spottype",                 //"图斑类型",
                    "mapratio",                 //"地图分辨率",
                    "spotarea",                 //"监测面积",
                    "arableAcreage",            //"占用耕地面积"
                    "adjustablearea",           //"可调整面积",
                    "beforetime",               //"前时项",
                    "aftertime",                //"后时项",
                    "notnewSpotclassification",               //"图斑分类",
                    "remark",                   //"备注",
                    "oilProjectname",                   //"项目名称",
                    "oilIssuedtime",                   //"下达时间",
                    "oilIssuednumber",                   //"下达文号",
                    "notnewRemarks",                   //"备注",
                    "cityauditispass",          //"市级审核状态"
                    "cityauditremarks",         //"市级审核备注"
                    "provinceauditispass",      //"省级审核状态"
                    "provinceauditremarks"      //"省级审核备注
            };
            resultMap.put("exelType", "2");        //只有一层表头
            resultMap.put("firstName", "上级能源部门的项目立项或计划下达情况"); //表头名
            resultMap.put("startNo", "16");   //二级表头起始
            resultMap.put("endNo", "19");     //二级表头截止
            resultMap.put("valuesName", valuesName);
            resultMap.put("title", "举证非新增图斑");
            return resultMap;
        }
        if (selectType.equals("5") && (((String) map.get("selectSpotSort")).equals("4"))) {
            //举证分新增图斑、农村道路
            String[] valuesName = {
                    "index",                    //"序号"
                    "tempRemark",               //图斑标记
                    "cityName",               //"市级名称",
                    "county",                   //"管理区划代码"
                    "countyname",               //"管理名称",
                    "spotnumber",               //"监测编号",
                    "spottype",                 //"图斑类型",
                    "mapratio",                 //"地图分辨率",
                    "spotarea",                 //"监测面积",
                    "arableAcreage",            //"占用耕地面积"
                    "adjustablearea",           //"可调整面积",
                    "beforetime",               //"前时项",
                    "aftertime",                //"后时项",
                    "notnewSpotclassification",               //"图斑分类",
                    "remark",                   //"备注",
                    "villageRoadwidth",                   //"道路宽度（米）",
                    "villageIsharden",                   //"硬化情况",
                    "notnewRemarks",                   //"备注",
                    "cityauditispass",          //"市级审核状态"
                    "cityauditremarks",         //"市级审核备注"
                    "provinceauditispass",      //"省级审核状态"
                    "provinceauditremarks"      //"省级审核备注
            };
            resultMap.put("exelType", "2");        //只有一层表头
            resultMap.put("firstName", "“农村道路”基本情况"); //表头名
            resultMap.put("startNo", "16");   //二级表头起始
            resultMap.put("endNo", "18");     //二级表头截止
            resultMap.put("valuesName", valuesName);
            resultMap.put("title", "举证非新增图斑");
            return resultMap;
        }
        if (selectType.equals("5") && (((String) map.get("selectSpotSort")).equals("5"))) {
            //举证分新增图斑、实地未变化
            String[] valuesName = {
                    "index",                    //"序号"
                    "tempRemark",               //图斑标记
                    "cityName",               //"市级名称",
                    "county",                   //"管理区划代码"
                    "countyname",               //"管理名称",
                    "spotnumber",               //"监测编号",
                    "spottype",                 //"图斑类型",
                    "mapratio",                 //"地图分辨率",
                    "spotarea",                 //"监测面积",
                    "arableAcreage",            //"占用耕地面积"
                    "adjustablearea",           //"可调整面积",
                    "beforetime",               //"前时项",
                    "aftertime",                //"后时项",
                    "notnewSpotclassification",               //"图斑分类",
                    "remark",                   //"备注",
                    "notnewRemarks",                   //"备注",
                    "cityauditispass",          //"市级审核状态"
                    "cityauditremarks",         //"市级审核备注"
                    "provinceauditispass",      //"省级审核状态"
                    "provinceauditremarks"      //"省级审核备注
            };
            resultMap.put("exelType", "2");        //只有一层表头
            resultMap.put("firstName", "“实地未变化”基本情况"); //表头名
            resultMap.put("startNo", "16");   //二级表头起始
            resultMap.put("endNo", "16");     //二级表头截止
            resultMap.put("valuesName", valuesName);
            resultMap.put("title", "举证非新增图斑");
            return resultMap;
        }

        if (selectType.equals("4") && ((String) map.get("selectSpotType")).equals("1")) {
            //举证分新增图斑、图斑分类为农转用
            String[] valuesName = {
                    "index",                    //"序号"
                    "tempRemark",               //图斑标记
                    "cityName",               //"市级名称",
                    "county",                   //"管理区划代码"
                    "countyname",               //"管理名称",
                    "spotnumber",               //"监测编号",
                    "spottype",                 //"图斑类型",
                    "mapratio",                 //"地图分辨率",
                    "spotarea",                 //"监测面积",
                    "arableAcreage",            //"占用耕地面积"
                    "adjustablearea",           //"可调整面积",
                    "beforetime",               //"前时项",
                    "aftertime",                //"后时项",
                    "legalprooftype",               //"图斑分类",
                    "remark",                   //"备注",
                    "otherProjectname",                   //"项目名称",
                    "otherApprovallevel",                   //"批准机关级别",
                    "otherApprovalname",                   //"批准机关名称",
                    "otherApprovalnumber",                   //"批准文号",
                    "otherApprovaltime",                   //"批准时间",
                    "otherApprovalarea",                   //"批准面积",
                    "otherRemarks",                   //"备注",
                    "cityauditispass",          //"市级审核状态"
                    "cityauditremarks",         //"市级审核备注"
                    "provinceauditispass",      //"省级审核状态"
                    "provinceauditremarks"      //"省级审核备注
            };
            resultMap.put("exelType", "2");        //只有一层表头
            resultMap.put("firstName", "农用地转建设用地批准情况"); //表头名
            resultMap.put("startNo", "16");   //二级表头起始
            resultMap.put("endNo", "22");     //二级表头截止
            resultMap.put("valuesName", valuesName);
            resultMap.put("title", "举证合法图斑");
            return resultMap;
        }
        if (selectType.equals("4") && ((String) map.get("selectSpotType")).equals("2")) {
            //举证分新增图斑、图斑分类为增减挂钩
            String[] valuesName = {
                    "index",                    //"序号"
                    "tempRemark",               //图斑标记
                    "cityName",               //"市级名称",
                    "county",                   //"管理区划代码"
                    "countyname",               //"管理名称",
                    "spotnumber",               //"监测编号",
                    "spottype",                 //"图斑类型",
                    "mapratio",                 //"地图分辨率",
                    "spotarea",                 //"监测面积",
                    "arableAcreage",            //"占用耕地面积"
                    "adjustablearea",           //"可调整面积",
                    "beforetime",               //"前时项",
                    "aftertime",                //"后时项",
                    "legalprooftype",               //"图斑分类",
                    "remark",                   //"备注",
                    "zjgProjectname",                   //"项目名称",
                    "zjgProjectnumber",                   //"项目编号",
                    "zjgNewblockname",                   //"新建地块名称",
                    "zjgNewblockarea",                   //"新建地面积",
                    "zjgRemarks",                   //"备注",
                    "cityauditispass",          //"市级审核状态"
                    "cityauditremarks",         //"市级审核备注"
                    "provinceauditispass",      //"省级审核状态"
                    "provinceauditremarks"      //"省级审核备注
            };
            resultMap.put("exelType", "2");        //只有一层表头
            resultMap.put("firstName", "城乡建设用地增减挂钩项目备案情况"); //表头名
            resultMap.put("startNo", "16");   //二级表头起始
            resultMap.put("endNo", "20");     //二级表头截止
            resultMap.put("valuesName", valuesName);
            resultMap.put("title", "举证合法图斑");
            return resultMap;
        }
        if (selectType.equals("4") && ((String) map.get("selectSpotType")).equals("4")) {
            //举证分新增图斑、图斑分类为历史批文
            String[] valuesName = {
                    "index",                    //"序号"
                    "tempRemark",               //图斑标记
                    "cityName",               //"市级名称",
                    "county",                   //"管理区划代码"
                    "countyname",               //"管理名称",
                    "spotnumber",               //"监测编号",
                    "spottype",                 //"图斑类型",
                    "mapratio",                 //"地图分辨率",
                    "spotarea",                 //"监测面积",
                    "arableAcreage",            //"占用耕地面积"
                    "adjustablearea",           //"可调整面积",
                    "beforetime",               //"前时项",
                    "aftertime",                //"后时项",
                    "legalprooftype",               //"图斑分类",
                    "remark",                   //"备注",
                    "otherProjectname",                   //"项目名称",
                    "otherApprovallevel",                   //"批准机关级别",
                    "otherApprovalname",                   //"批准机关名称",
                    "otherApprovalnumber",                   //"批准文号",
                    "otherApprovaltime",                   //"批准时间",
                    "otherApprovalarea",                   //"批准面积",
                    "otherRemarks",                   //"备注",
                    "cityauditispass",          //"市级审核状态"
                    "cityauditremarks",         //"市级审核备注"
                    "provinceauditispass",      //"省级审核状态"
                    "provinceauditremarks"      //"省级审核备注
            };
            resultMap.put("exelType", "2");        //只有一层表头
            resultMap.put("firstName", "用地手续办理情况"); //表头名
            resultMap.put("startNo", "16");   //二级表头起始
            resultMap.put("endNo", "22");     //二级表头截止
            resultMap.put("valuesName", valuesName);
            resultMap.put("title", "举证合法图斑");
            return resultMap;
        }
        if (selectType.equals("4") && ((String) map.get("selectSpotType")).equals("3")) {
            //举证分新增图斑、图斑分类为其他
            String[] valuesName = {
                    "index",                    //"序号"
                    "tempRemark",               //图斑标记
                    "cityName",               //"市级名称",
                    "county",                   //"管理区划代码"
                    "countyname",               //"管理名称",
                    "spotnumber",               //"监测编号",
                    "spottype",                 //"图斑类型",
                    "mapratio",                 //"地图分辨率",
                    "spotarea",                 //"监测面积",
                    "arableAcreage",            //"占用耕地面积"
                    "adjustablearea",           //"可调整面积",
                    "beforetime",               //"前时项",
                    "aftertime",                //"后时项",
                    "legalprooftype",               //"图斑分类",
                    "remark",                   //"备注",
                    "otherSpottype",                   //"类别",
                    "otherProjectname",                   //"项目名称",
                    "otherApprovallevel",                   //"批准机关级别",
                    "otherApprovalname",                   //"批准机关名称",
                    "otherApprovalnumber",                   //"批准文号",
                    "otherApprovaltime",                   //"批准时间",
                    "otherApprovalarea",                   //"批准面积",
                    "otherRemarks",                   //"备注",
                    "cityauditispass",          //"市级审核状态"
                    "cityauditremarks",         //"市级审核备注"
                    "provinceauditispass",      //"省级审核状态"
                    "provinceauditremarks"      //"省级审核备注
            };
            resultMap.put("exelType", "2");        //只有一层表头
            resultMap.put("firstName", "其他情况"); //表头名
            resultMap.put("startNo", "16");   //二级表头起始
            resultMap.put("endNo", "23");     //二级表头截止
            resultMap.put("valuesName", valuesName);
            resultMap.put("title", "举证合法图斑");
            return resultMap;
        }

        return null;
    }



    /**
     * 绘制表的title
     */
    private void createTitle(HSSFWorkbook wb, HSSFSheet sheet, String titleName, String[] fields) {
        HSSFRow row = sheet.createRow(0);
        row.setHeight((short) (30 * 20));
        //setCellBorder(row); //设置边框


        HSSFCell cell = row.createCell(0);
        cell.setCellValue("全天候图斑信息查询-" + titleName);

        HSSFCellStyle fontCellStyle = wb.createCellStyle();
        HSSFFont font = wb.createFont();
        font.setFontHeightInPoints((short) 18);

        cell.setCellStyle(fontCellStyle);

        fontCellStyle.setFont(font);
        fontCellStyle.setAlignment(HSSFCellStyle.VERTICAL_CENTER);
        fontCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);

        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, fields.length - 1));
    }


    public void exportExcel(User user, Map<String, Object> map, HttpServletResponse response) {

        map.put("startNum", 1);
        map.put("endNum", 1000000000);
        //User currentUser = (User) UserCache.get(authId); // getCurrentUser();
        // 组织机构级别
        int organizationGrade = user.getOrgGrade();

        if (organizationGrade == OrganizationGrade.Province) {
            map.put("organizationGrade", OrganizationGrade.City);
        } else {
            map.put("organizationGrade", OrganizationGrade.County);
        }

        List<SwMapspot> list = weatherCitydao.getStatisticSwMapspots(map);
        if (user.getOrgGrade() == OrganizationGrade.Province) {
            list = markFullAlias(list);
        } else {
            list = markVisAlias(list);
        }

        for (int i = 0; i < list.size(); i++) {
            Region region = (Region) RegionCache.get(list.get(i).getCity().toString());
            if (region != null) {
                list.get(i).setCityName(region.getRegionName());
            }
            list.get(i).setIndex(i + 1);
        }

        Map<String, Object> fieldName = getFieldName(map);
        String[] valuesName = (String[]) fieldName.get("valuesName");

        //可调整后面加不符合规范面积
        List<String> arrayslist = Arrays.asList(valuesName);
        List arraylist = new ArrayList(arrayslist);
        int index = arraylist.indexOf("adjustablearea");
        if (index > -1) {
            arraylist.add(index + 1, "unPlaningArea");
        }

        // 只有省级用户才能看到审核人
        if (user.getOrgGrade() == OrganizationGrade.Province) {
            index = arraylist.indexOf("provinceauditispass");
            if (index > -1) {
                arraylist.add(index + 1, "provinceauditpersonname");
            }

            index = arraylist.indexOf("cityauditispass");
            if (index > -1) {
                arraylist.add(index + 1, "cityauditpersonname");
            }
        }

        valuesName = new String[arraylist.size()];
        arraylist.toArray(valuesName);

        String exelType = (String) fieldName.get("exelType");
        String titleName = (String) fieldName.get("title"); //表名

        // 第一步，创建一个webbook，对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet();

        //String title = "年度国土资源执法监察案卷评查结果汇总表";

        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
        createTitle(wb, sheet, titleName, valuesName);

        HSSFRow dwrow = sheet.createRow(1);
        HSSFCell swCell = dwrow.createCell(0);
        swCell.setCellValue("单位：亩");


        HSSFRow row = sheet.createRow(2);


        HSSFCellStyle valueStyle = wb.createCellStyle();
        valueStyle = setCommonStyle(valueStyle);

        int titleCount = 2;





        HSSFRow row1 = sheet.createRow(3);
        if (exelType.equals("1")) {
            createTitle(valuesName, new SwMapspot(), row, valueStyle, sheet); //情况一
            titleCount = 3;
        }
        if (exelType.equals("2")) {
            String firstName = (String) fieldName.get("firstName");
            int startNo = Integer.parseInt((String) fieldName.get("startNo"));
            int endNo = Integer.parseInt((String) fieldName.get("endNo"));
            createTitle(valuesName, new SwMapspot(), firstName, startNo, endNo, 2, row, row1, valueStyle, sheet);
            titleCount = 4;
        }


        HSSFCellStyle doubleStyle = wb.createCellStyle();
        if (list.size() != 0) {
            for (int i = 0; i < list.size(); i++) {
                row = sheet.createRow(i + titleCount);
                for (int j = 0; j < valuesName.length; j++) {
                    Field f = getDeclaredField(list.get(i), valuesName[j]);
                    Object value = null;

                    if (null == f) {
                        setCellValue(row, valueStyle, j, "", wb, doubleStyle);
                    } else {
                        f.setAccessible(true);
                        try {
                            value = f.get(list.get(i));
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }


                    if (valuesName[j].compareToIgnoreCase("mapratio") == 0) {
                        setCellValue(row, valueStyle, j, value == null ? "" : ((Integer) value == 2 ? "2-5米" : "16米"), wb, doubleStyle);
                    } else if (valuesName[j].compareToIgnoreCase("cityauditispass") == 0 || valuesName[j].compareToIgnoreCase("provinceauditispass") == 0) {
                        setCellValue(row, valueStyle, j, value == null ? "未审核" : ((Integer) value == 1 ? "已通过" : (Integer) value == 2 ? "未通过" : "未审核"), wb, doubleStyle);
                    } else if (valuesName[j].compareToIgnoreCase("ssnydApprovalnumber") == 0 ||
                            valuesName[j].compareToIgnoreCase("tempApprovalnumber") == 0 ||
                            valuesName[j].compareToIgnoreCase("oilIssuednumber") == 0 ||
                            valuesName[j].compareToIgnoreCase("zjgProjectnumber") == 0 ||
                            valuesName[j].compareToIgnoreCase("otherApprovalnumber") == 0) {
                        setCellValue(row, valueStyle, j, value == null ? "" : value.toString() + "号", wb, doubleStyle);
                    } else if (valuesName[j].compareToIgnoreCase("villageIsharden") == 0) {
                        setCellValue(row, valueStyle, j, value == null ? "" : ((Integer) value == 1 ? "是" : "否"), wb, doubleStyle);
                    } else if (valuesName[j].compareToIgnoreCase("legalprooftype") == 0) {
                        if (null == value) {
                            setCellValue(row, valueStyle, j, "", wb, doubleStyle);
                        } else {
                            setCellValue(row, valueStyle, j, (Integer) value == 1 ? "农转用" : ((Integer) value == 2 ? "增减挂钩" : (Integer) value == 3 ? "其他" : "历史批文"), wb, doubleStyle);
                        }
                    } else if (valuesName[j].compareToIgnoreCase("notnewSpotclassification") == 0) {
                        setCellValue(row, valueStyle, j, null == value ? "" : (Integer) value == 1 ? "设施农用地" : ((Integer) value == 2 ? "临时用地" : (Integer) value == 3 ? "油田用地" : (Integer) value == 4 ? "农村道路" : "实地未变化"), wb, doubleStyle);
                    } else if (valuesName[j].compareToIgnoreCase("otherSpottype") == 0) {
                        setCellValue(row, valueStyle, j, null == value ? "" : ((Double) value).intValue() == 31 ? "" +
                                "海域" : ((Double) value).intValue() == 32 ? "未利用" : "", wb, doubleStyle)
                        ;
                    } else {
                        setCellValue(row, valueStyle, j, value, wb, doubleStyle);
                    }
                }
            }
        }

        try

        {
            response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode("全天候图斑信息查询-" + titleName, "utf-8") + ".xls");
            OutputStream out = response.getOutputStream();
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            wb.write(stream);
            byte[] bytes = stream.toByteArray();
            out.write(bytes);
            out.close();
        } catch (
                UnsupportedEncodingException e)

        {
            e.printStackTrace();
        } catch (
                IOException e)

        {
            e.printStackTrace();
        }

    }


    private void createTitle(String[] titleNames, SwMapspot mapspot, HSSFRow row, HSSFCellStyle style, HSSFSheet sheet) {
        //int index = 0;
        for (int i = 0; i < titleNames.length; i++) {
            Field field = getDeclaredField(mapspot, titleNames[i]);
            field.setAccessible(true);
            ExcelTitle excelTitle = field.getAnnotation(ExcelTitle.class);
            createTitleCell(row, style, excelTitle.value(), i);

            sheet.setColumnWidth(i, 15 * 256);
        }
    }

    private <T> void createTitle(String[] titleNames, T mapspot, String firstTitle, int start, int end, int titleIndex, HSSFRow row, HSSFRow row1, HSSFCellStyle style, HSSFSheet sheet) {

        for (int i = 0; i < titleNames.length; i++) {

            Field field = getDeclaredField(mapspot, titleNames[i]);

            field.setAccessible(true);
            ExcelTitle excelTitle = field.getAnnotation(ExcelTitle.class);
            if (i < start || i > end) {
                sheet.addMergedRegion(new CellRangeAddress(titleIndex, titleIndex + 1, i, i));
                createTitleCell(row, style, excelTitle.value(), i);
                createTitleCell(row1, style, "", i);
            } else if (i == start) {
                sheet.addMergedRegion(new CellRangeAddress(titleIndex, titleIndex, start, end));
                createTitleCell(row, style, firstTitle, i);

                createTitleCell(row1, style, excelTitle.value(), i);
            } else {
                createTitleCell(row, style, excelTitle.value(), i);
                createTitleCell(row1, style, excelTitle.value(), i);
            }

            sheet.setColumnWidth(i, 15 * 256);

        }
    }

    private <T> Field getDeclaredField(T mapspot, String fileName) {
        Field field = null;
        try {
            field = mapspot.getClass().getDeclaredField(fileName);
        } catch (NoSuchFieldException e) {
            try {
                field = mapspot.getClass().getSuperclass().getDeclaredField(fileName);
            } catch (NoSuchFieldException e1) {
                e1.printStackTrace();
            }
        }
        return field;
    }


    /**
     * 公共样式
     *
     * @param style
     * @return
     */
    private HSSFCellStyle setCommonStyle(HSSFCellStyle style) {
        style.setAlignment(HSSFCellStyle.VERTICAL_CENTER);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);

        style.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框

        return style;
    }

    private HSSFCellStyle setDoubleStyle(HSSFCellStyle style) {
        setCommonStyle(style);
        style.setDataFormat(HSSFDataFormat.getBuiltinFormat("0.00"));
        return style;
    }

    private HSSFCellStyle setDateStyle(HSSFCellStyle style, HSSFWorkbook wb) {
        setCommonStyle(style);
        HSSFDataFormat format = wb.createDataFormat();
        style.setDataFormat(format.getFormat("yyyy年m月d日"));
        return style;
    }


    private void createTitleCell(HSSFRow row, HSSFCellStyle style, String titleName, int index) {
        HSSFCell cell = row.createCell(index);
        cell.setCellValue(titleName);
        cell.setCellStyle(style);
    }

    private void setCellValue(HSSFRow row, CellStyle cellStyle, int colIndex, Object value, HSSFWorkbook wb, HSSFCellStyle doubleStyle) {

        HSSFCell hssfCell = row.createCell(colIndex);
        hssfCell.setCellStyle(cellStyle);
        if (null == value) {
            hssfCell.setCellValue("");
        } else if (value instanceof Integer) {
            hssfCell.setCellValue((Integer) value);
        } else if (value instanceof Short) {
            hssfCell.setCellValue((Short) value);
        } else if (value instanceof Double) {
            hssfCell.setCellValue((Double) value);

            //HSSFCellStyle doubleStyle = wb.createCellStyle();
            setDoubleStyle(doubleStyle);

            hssfCell.setCellStyle(doubleStyle);
        } else if (value instanceof Boolean) {
            hssfCell.setCellValue((Boolean) value);
        } else if (value instanceof Short) {
            hssfCell.setCellValue((Short) value);
        } else if (value instanceof Date) {
            hssfCell.setCellValue((Date) value);

            HSSFCellStyle dateStype = wb.createCellStyle();
            setDateStyle(dateStype, wb);

            hssfCell.setCellStyle(dateStype);
        } else {
            hssfCell.setCellValue((String) value);
        }

    }

    public void exportSearchMapSpot(Map<String, Object> map, HttpServletResponse response) {
        map.put("startNum", 1);
        map.put("endNum", 1000000000);
        List<MapSpotStatistics> list = getMapSpotStatis(1, map);

        // 第一步，创建一个webbook，对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet();

//        HSSFRow dwrow = sheet.createRow(0);
//        HSSFCell swCell = dwrow.createCell(0);
//        swCell.setCellValue("单位：亩");

        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
        HSSFRow row = sheet.createRow(0);

        HSSFCellStyle valueStyle = wb.createCellStyle();
        valueStyle = setCommonStyle(valueStyle);

        int titleCount = 2;
        String[] valuesName = {
                "cityName",
                "countyId",
                "countyName",
                "mapSpotNum",
                "unLegalproofNum",
                "illegalNum",
                "legitimateNum",
                "notNewMapSpotNum",
                "notNewLegalproofingNum",
                "notNewLegalproofedNum",
                "toCity",
                "toProvince"
        };

        HSSFRow row1 = sheet.createRow(1);

        createTitle(valuesName, new MapSpotStatistics(), "举证非新增", 7, 9, 0, row, row1, valueStyle, sheet);

        HSSFCellStyle doubleStyle = wb.createCellStyle();
        if (list.size() != 0) {
            for (int i = 0; i < list.size(); i++) {
                row = sheet.createRow(i + titleCount);
                for (int j = 0; j < valuesName.length; j++) {
                    Field f = getDeclaredField(list.get(i), valuesName[j]);
                    Object value = null;

                    if (null == f) {
                        setCellValue(row, valueStyle, j, "", wb, doubleStyle);
                    } else {
                        f.setAccessible(true);
                        try {
                            value = f.get(list.get(i));
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }

                    setCellValue(row, valueStyle, j, value, wb, doubleStyle);
                }

                if (i == 0) {
                    sheet.addMergedRegion(new CellRangeAddress(i + titleCount, i + titleCount, 0, 2));
                }
            }
        }

        try

        {
            response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode("全天候图斑图斑统计", "utf-8") + ".xls");
            OutputStream out = response.getOutputStream();
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            wb.write(stream);
            byte[] bytes = stream.toByteArray();
            out.write(bytes);
            out.close();
        } catch (
                UnsupportedEncodingException e)

        {
            e.printStackTrace();
        } catch (
                IOException e)

        {
            e.printStackTrace();
        }
    }

    /**
     * 图斑统计（2017-10-10）
     *
     * @param page
     * @return
     */
    @SuppressWarnings("null")
    public PagingEntity<MapSpotStatistics> searchMapSpot(Page page) {
        Map<String, Object> map = new HashMap<String, Object>();
        for (Condition condition : page.getConditions()) {
            map.put(condition.getKey(), condition.getValue());
        }
        map.put("startNum", page.getPageSize() * (page.getPageNo() - 1) + 1);
        map.put("endNum", page.getPageSize() * page.getPageNo());
        //map.get("");
        int count = 0;
        count = dao.getCityListCount(map);

        PagingEntity<MapSpotStatistics> entity = new PagingEntity<MapSpotStatistics>();
        entity.PageNo = page.getPageNo();//第几页
        entity.PageSize = page.getPageSize();//每页显示数
        entity.PageCount = count == 0 ? 0 : (count - 1) / page.getPageSize() + 1; // 由于计算pageCount存在整除的情况，所以计算的时候先减1在除以pageSize
        entity.RecordCount = count;//记录总条数
        entity.CurrentList = getMapSpotStatis(page.getPageNo(), map);
        entity.PageCount = (entity.RecordCount / entity.PageSize) + (entity.RecordCount % entity.PageSize > 0 ? 1 : 0);//总页数
        return entity;
    }

    private List<MapSpotStatistics> getMapSpotStatis(int pageNo, Map<String, Object> map) {
        List<MapSpotStatistics> list = new ArrayList<MapSpotStatistics>();
        //获取城市，区域的分页list
        List<MapSpotStatistics> cityList;
        cityList = dao.getCityList(map);

        if (pageNo == 1) {
            MapSpotStatistics sum = dao.searchMapSpotSum(map);
            if (sum != null && map.get("city") == null && map.get("county") == null) {
                //if (sum != null &&  map.get("city") == null && StrUtil.isNullOrEmpty(map.get("city").toString()) && map.get("county") == null && StrUtil.isNullOrEmpty(map.get("county").toString())) {
                sum.setCityName("山东省");
                sum.setCountyName("山东省");
                list.add(sum);
            } else if (sum != null && map.get("city") != null && map.get("county") == null) {
                //} else if (sum != null && map.get("city") != null &&  !StrUtil.isNullOrEmpty(map.get("city").toString())  && map.get("county") == null && StrUtil.isNullOrEmpty(map.get("county").toString())) {
                String cityName = dao.getCityNameById((String) map.get("city"));
                sum.setCountyName(cityName);
                sum.setCityName(cityName);
                list.add(sum);
            } else if (sum != null && map.get("city") != null && map.get("county") != null) {
                //} else if (sum != null && map.get("city") != null &&!StrUtil.isNullOrEmpty(map.get("city").toString()) && map.get("county") != null && !StrUtil.isNullOrEmpty(map.get("county").toString())) {
                String cityName = dao.getCityNameById((String) map.get("county"));
                sum.setCountyName(cityName);
                sum.setCityName(cityName);
                list.add(sum);
            }
        }

        for (int i = 0; i < cityList.size(); i++) {
            MapSpotStatistics mapSpotStatistics = cityList.get(i);
            if (map.get("period") != null && !map.get("period").equals("")) {
                mapSpotStatistics.setPeriod((Integer.valueOf(map.get("period").toString())));
            }
            if (map.get("year") != null && !map.get("year").equals("")) {
                mapSpotStatistics.setYear((Integer.valueOf(map.get("year").toString())));
            }
            if (null != map.get("mark") && !map.get("mark").equals("")) {
                mapSpotStatistics.setMark(map.get("mark").toString());
            }
            MapSpotStatistics mapSpotNum = dao.searchMapSpotNum(mapSpotStatistics);
//            if (mapSpotNum.getToCity() == null || mapSpotNum.getToCity().equals("")) {
//                mapSpotNum.setToCity("否");
//            } else {
//                mapSpotNum.setToCity("是");
//            }
//            if (mapSpotNum.getToProvince() == null || mapSpotNum.getToProvince().equals("")) {
//                mapSpotNum.setToProvince("否");
//            } else {
//                mapSpotNum.setToProvince("是");
//            }
            list.add(mapSpotNum);
        }
        return list;
    }


    /**
     * 获取标记字典
     *
     * @return
     */
    public List<DataDictionaryItem> getMapSpotMarkDic() {
        List<DataDictionaryItem> result = new ArrayList<DataDictionaryItem>();
        List<DataDictionaryItem> diclist = dicService.getDataDictionaryItems("AllWeather", "Focus");
        for (DataDictionaryItem item : diclist) {
            if (item.remarks.indexOf("\"report\":1") > 0) {
                result.add(item);
            }
        }
        return result;
    }

    /**
     * 获取标记字典
     *
     * @return
     */
    public List<DataDictionaryItem> getMapSpotMarkVisDic(String type) {
        List<DataDictionaryItem> result = new ArrayList<DataDictionaryItem>();
        List<DataDictionaryItem> diclist = dicService.getDataDictionaryItems("AllWeather", "Focus");
        for (DataDictionaryItem item : diclist) {

//            if (item.getValue().equals("1000")){
//                continue;
//            }
            if ("1".equals(type)) {
                result.add(item);
            } else {
                if (item.remarks.indexOf("\"visible\":1") > 0) {
                    result.add(item);
                }
            }
        }
        return result;
    }

    /**
     * 获取标记字典
     *
     * @return
     */
    public List<DataDictionaryItem> getMapSpotMarkReportDic(String type) {
        List<DataDictionaryItem> result = new ArrayList<DataDictionaryItem>();
        List<DataDictionaryItem> diclist = dicService.getDataDictionaryItems("AllWeather", "Focus");
        for (DataDictionaryItem item : diclist) {

//            if (item.getValue().equals("1000")){
//                continue;
//            }
            if ("1".equals(type)) {
                result.add(item);
            } else {
                if (item.remarks.indexOf("\"report\":1") > 0) {
                    result.add(item);
                }
            }
        }
        return result;
    }

    public Map getCurrentPeriod() {
//        String cuperiod = dao.getCurrentPeriod().get(0);
//        String[] ps = cuperiod.split("-");
        Map<String, Integer> map = new HashMap<String, Integer>();
//        map.put("year", Integer.valueOf(ps[0]));
//        map.put("period", Integer.valueOf(ps[1]));
        return map;
    }


    public boolean updateMarkMapSpot(List<Condition> maps) {
        for (Condition condition : maps) {
            dao.updateMarkMapSpot(condition.getValue(), Long.valueOf(condition.getKey()));
        }
        return true;
    }

}

