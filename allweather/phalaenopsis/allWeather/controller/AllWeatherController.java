
package phalaenopsis.allWeather.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.PathParam;

import com.alibaba.fastjson.JSON;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.fastjson.JSONObject;
import phalaenopsis.allWeather.entity.HandleProgress;
import phalaenopsis.allWeather.entity.MapSpotStatistics;
import phalaenopsis.allWeather.entity.SwLog;
import phalaenopsis.allWeather.entity.SwMapspot;
import phalaenopsis.allWeather.enums.NodeEnum;
import phalaenopsis.allWeather.service.AllWeatherService;
import phalaenopsis.allWeather.service.AllWeatherServiceI;
import phalaenopsis.common.annotation.Comment;
import phalaenopsis.common.annotation.Parameter;
import phalaenopsis.common.entity.*;
import phalaenopsis.common.entity.ResultEntity.ResultEnum;
import phalaenopsis.common.method.Basis;
import phalaenopsis.common.method.Tools.StrUtil;
import phalaenopsis.satellitegraph.entity.MapSpot;
import phalaenopsis.satellitegraph.entity.Polygon;
import phalaenopsis.satellitegraph.utils.ArcGISHelper.SpotArcGISHelper;
import phalaenopsis.workflowEngine.core.NetGraph;

/**
 * 全天候区县服务
 *
 * @author chunl
 * 2017年7月26日下午1:15:34
 */
@RestController
@RequestMapping("/allweather/allweatherservice")
public class AllWeatherController extends Basis {

//    /**
//     * 全天候Service
//     */
//    @Autowired
//    private AllWeatherService service;

    /**
     * 全天候serviceI
     */
    @Autowired
    private AllWeatherServiceI service;

    /**
     * 当前最新期数
     *
     * @return
     */
    @RequestMapping(value = "/getCurrentPeriod", method = RequestMethod.GET)
    @ResponseBody
    public List<String> getCurrentPeriod() {
        return service.getCurrentSeason();
    }

    /**
     * 得到图斑处理进度
     *
     * @return 返回进度
     */
    @Comment("图斑处理进度")
    @RequestMapping(value = "/getCountyProgress", method = RequestMethod.GET)
    @ResponseBody
    public HandleProgress getCountyHandleProgress(@RequestParam(value = "mark", required = false) @Comment(value = "标注类型", argName = "mark") Integer mark) {
        return service.getHandleProgress(mark);
    }

    /**
     * 得到所有类型的图斑处理进度
     *
     * @return 返回进度
     */
    @Comment("图斑处理进度")
    @RequestMapping(value = "/getCountyAllProgress", method = RequestMethod.GET)
    @ResponseBody
    public List<HandleProgress> getCountyHandleAllProgress() {
        return service.getHandleAllProgress();
    }

    /**
     * 上报
     *
     * @return
     */
    @RequestMapping(value = "/countyReport", method = RequestMethod.POST)
    public Boolean report(@RequestParam(value = "mark", required = false) String mark) {
        return service.report(mark);
    }

    /**
     * 获取回退信息
     *
     * @return
     */
    @RequestMapping(value = "/backInfo", method = RequestMethod.POST)
    public SwLog geBackInfo(@RequestParam(value = "mark", required = false) Integer mark) {
        return service.geBackInfo(mark);
    }

    /**
     * 确定违法
     *
     * @param id 违法图斑id号
     * @return
     */
    @RequestMapping(value = "/IllegalSpot", method = RequestMethod.PUT)
    public Boolean illegalSpot(@RequestParam("id") final Long id) {
        return service.changeNode(id, NodeEnum.IllegalSpot.getIntValue());
    }

    /**
     * 查询列表
     *
     * @param param 查询参数
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public PagingEntityForMobile<SwMapspot> getMapspotList(@RequestBody final String param) {
        Page page = JSONObject.parseObject(param, Page.class);
        return service.getMapspotList(page, false);
    }

    /**
     * 举证
     *
     * @param mapSpot 举证的图斑
     * @return
     */
    @RequestMapping(value = "/proofSpot", method = RequestMethod.POST)
    public boolean proofSpot(@RequestBody final SwMapspot mapSpot) {
        // 1: 设施农用地,2: 临时用地,3: 油田用地,4: 农村道路,5: 实地未变化
        // 1: 农转用,2: 增减挂钩,3: 其他; 31: 有“海域使用证”的图斑,32: 有未利用地使用手续的图斑
        if (mapSpot.getNode() == 2 || mapSpot.getNode() == 3 || mapSpot.getNode() == 4 || mapSpot.getNode() == 5)
            mapSpot.setTiming(Calendar.getInstance().getTime());

        if (mapSpot.getNode() == 2 || mapSpot.getNode() == 4) {
            if (mapSpot.getLegalprooftype() == 1 || mapSpot.getLegalprooftype() == 3) // 农用、其他
            {
                if (mapSpot.getLegalprooftype() == 1) { // 如果是农转用情况类型
                    mapSpot.setOtherSpottype(null);
                }

                mapSpot.setZjgIsnestedsuccess(null);
                mapSpot.setZjgNewblockarea(null);
                mapSpot.setZjgNewblockname(null);
                mapSpot.setZjgProjectname(null);
                mapSpot.setZjgProjectnumber(null);
                mapSpot.setZjgRemarks(null);
            } else if (mapSpot.getLegalprooftype() == 2) {
                mapSpot.setOtherApprovalarea(null);
                mapSpot.setOtherApprovallevel(null);
                mapSpot.setOtherApprovalname(null);
                mapSpot.setOtherApprovalnumber(null);
                mapSpot.setOtherApprovaltime(null);
                mapSpot.setOtherProjectname(null);
                mapSpot.setOtherRemarks(null);
                mapSpot.setOtherSpottype(null);
            }

            if (mapSpot.getLegalprooftype() == 4) {
                mapSpot.setOtherSpottype(null);
            }

            // 清空非新增
            mapSpot.setNotnewSpotclassification(null);
            mapSpot.setNotnewRemarks(null);

            mapSpot.setOilIssuednumber(null);
            mapSpot.setOilIssuedtime(null);
            mapSpot.setOilProjectname(null);

            mapSpot.setVillageIsharden(null);
            mapSpot.setVillageRoadwidth(null);

            mapSpot.setTempApprovalnumber(null);
            mapSpot.setTempApprovaltime(null);
            mapSpot.setTempApprovalusetype(null);
            mapSpot.setTempArea(null);
            mapSpot.setTempProjectusetype(null);
            mapSpot.setTempUsetype(null);
            mapSpot.setTempProjectName(null);

            mapSpot.setSsnydApprovalarea(null);
            mapSpot.setSsnydApprovalnumber(null);
            mapSpot.setSsnydApprovaltime(null);
            mapSpot.setSsnydApprovalusetype(null);
            mapSpot.setSsnydBackupnumber(null);
            mapSpot.setSsnydUsetype(null);
            mapSpot.setSsnydProjectName(null);

        } else if (mapSpot.getNode() == 3 || mapSpot.getNode() == 5) {

            if (null == mapSpot.getNotnewSpotclassification()) {
                mapSpot.setOilIssuednumber(null);
                mapSpot.setOilIssuedtime(null);
                mapSpot.setOilProjectname(null);

                mapSpot.setVillageIsharden(null);
                mapSpot.setVillageRoadwidth(null);

                mapSpot.setTempApprovalnumber(null);
                mapSpot.setTempApprovaltime(null);
                mapSpot.setTempApprovalusetype(null);
                mapSpot.setTempArea(null);
                mapSpot.setTempProjectusetype(null);
                mapSpot.setTempUsetype(null);
                mapSpot.setTempProjectName(null);

                mapSpot.setSsnydApprovalarea(null);
                mapSpot.setSsnydApprovalnumber(null);
                mapSpot.setSsnydApprovaltime(null);
                mapSpot.setSsnydApprovalusetype(null);
                mapSpot.setSsnydBackupnumber(null);
                mapSpot.setSsnydUsetype(null);
                mapSpot.setSsnydProjectName(null);

            } else {

                clearNotNewData(mapSpot);

            }
        }

        if (mapSpot.getNode() == 2 || mapSpot.getNode() == 3 || mapSpot.getNode() == 4) {
            mapSpot.setProveuserId(getCurrentUser().getId());
        }

        return service.proofSpot(mapSpot);
    }

    /**
     * 清空非新增的数据
     *
     * @param mapSpot
     */
    private void clearNotNewData(final SwMapspot mapSpot) {
        if (null == mapSpot.getNotnewSpotclassification()) {
            return;
        }

        if (1 == mapSpot.getNotnewSpotclassification()) { // 设施农用地

            mapSpot.setOilIssuednumber(null);
            mapSpot.setOilIssuedtime(null);
            mapSpot.setOilProjectname(null);

            mapSpot.setVillageIsharden(null);
            mapSpot.setVillageRoadwidth(null);

            mapSpot.setTempApprovalnumber(null);
            mapSpot.setTempApprovaltime(null);
            mapSpot.setTempApprovalusetype(null);
            mapSpot.setTempArea(null);
            mapSpot.setTempProjectusetype(null);
            mapSpot.setTempUsetype(null);
            mapSpot.setTempProjectName(null);

        } else if (2 == mapSpot.getNotnewSpotclassification()) { // 临时用地
            mapSpot.setOilIssuednumber(null);
            mapSpot.setOilIssuedtime(null);
            mapSpot.setOilProjectname(null);

            mapSpot.setVillageIsharden(null);
            mapSpot.setVillageRoadwidth(null);

            mapSpot.setSsnydApprovalarea(null);
            mapSpot.setSsnydApprovalnumber(null);
            mapSpot.setSsnydApprovaltime(null);
            mapSpot.setSsnydApprovalusetype(null);
            mapSpot.setSsnydBackupnumber(null);
            mapSpot.setSsnydUsetype(null);
            mapSpot.setSsnydProjectName(null);

        } else if (4 == mapSpot.getNotnewSpotclassification()) { // 农村道路

            mapSpot.setOilIssuednumber(null);
            mapSpot.setOilIssuedtime(null);
            mapSpot.setOilProjectname(null);

            mapSpot.setTempApprovalnumber(null);
            mapSpot.setTempApprovaltime(null);
            mapSpot.setTempApprovalusetype(null);
            mapSpot.setTempArea(null);
            mapSpot.setTempProjectusetype(null);
            mapSpot.setTempUsetype(null);
            mapSpot.setTempProjectName(null);

            mapSpot.setSsnydApprovalarea(null);
            mapSpot.setSsnydApprovalnumber(null);
            mapSpot.setSsnydApprovaltime(null);
            mapSpot.setSsnydApprovalusetype(null);
            mapSpot.setSsnydBackupnumber(null);
            mapSpot.setSsnydUsetype(null);
            mapSpot.setSsnydProjectName(null);

        } else if (3 == mapSpot.getNotnewSpotclassification()) { // 油田用地

            mapSpot.setVillageIsharden(null);
            mapSpot.setVillageRoadwidth(null);

            mapSpot.setTempApprovalnumber(null);
            mapSpot.setTempApprovaltime(null);
            mapSpot.setTempApprovalusetype(null);
            mapSpot.setTempArea(null);
            mapSpot.setTempProjectusetype(null);
            mapSpot.setTempUsetype(null);
            mapSpot.setTempProjectName(null);

            mapSpot.setSsnydApprovalarea(null);
            mapSpot.setSsnydApprovalnumber(null);
            mapSpot.setSsnydApprovaltime(null);
            mapSpot.setSsnydApprovalusetype(null);
            mapSpot.setSsnydBackupnumber(null);
            mapSpot.setSsnydUsetype(null);
            mapSpot.setSsnydProjectName(null);

        } else if (5 == mapSpot.getNotnewSpotclassification()) { // 实地未变化
            mapSpot.setOilIssuednumber(null);
            mapSpot.setOilIssuedtime(null);
            mapSpot.setOilProjectname(null);

            mapSpot.setVillageIsharden(null);
            mapSpot.setVillageRoadwidth(null);

            mapSpot.setTempApprovalnumber(null);
            mapSpot.setTempApprovaltime(null);
            mapSpot.setTempApprovalusetype(null);
            mapSpot.setTempArea(null);
            mapSpot.setTempProjectusetype(null);
            mapSpot.setTempUsetype(null);
            mapSpot.setTempProjectName(null);

            mapSpot.setSsnydApprovalarea(null);
            mapSpot.setSsnydApprovalnumber(null);
            mapSpot.setSsnydApprovaltime(null);
            mapSpot.setSsnydApprovalusetype(null);
            mapSpot.setSsnydBackupnumber(null);
            mapSpot.setSsnydUsetype(null);
            mapSpot.setSsnydProjectName(null);
        }

        // 清空合法字段
        mapSpot.setLegalprooftype(null);

        mapSpot.setZjgIsnestedsuccess(null);
        mapSpot.setZjgNewblockarea(null);
        mapSpot.setZjgNewblockname(null);
        mapSpot.setZjgProjectname(null);
        mapSpot.setZjgProjectnumber(null);
        mapSpot.setZjgRemarks(null);

        mapSpot.setOtherApprovalarea(null);
        mapSpot.setOtherApprovallevel(null);
        mapSpot.setOtherApprovalname(null);
        mapSpot.setOtherApprovalnumber(null);
        mapSpot.setOtherApprovaltime(null);
        mapSpot.setOtherProjectname(null);
        mapSpot.setOtherRemarks(null);
        mapSpot.setOtherSpottype(null);
    }

    /**
     * 判断图斑是否能还原
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/canRestore", method = RequestMethod.GET)
    public ResultEntity canRestore(Long id) {

        SwMapspot mapspot = service.getSwMapspot(id);

        if (!mapspot.getMapspotLevel().equals("1")) {
            return new ResultEntity(ResultEnum.Faild, "已上报，不允许还原！");
        }

        String yearPeriod = service.getCurrentSeason().get(0);
        String[] strings = yearPeriod.split("-");

        if (!(mapspot.getYear().toString().equals(strings[0])
                && mapspot.getCheckperiod().toString().equals(strings[1]))) {
            return new ResultEntity(ResultEnum.Faild, "历史期数的数据不允许还原！");
        }

        if (!(mapspot.getNode() == 1 || mapspot.getNode() == 3 || mapspot.getNode() == 4 || mapspot.getNode() == 5)) {
            return new ResultEntity(ResultEnum.Faild, "审核通过不允许还原！");
        }

        return new ResultEntity(ResultEnum.Success);
    }

    /**
     * 图斑还原服务
     *
     * @param ids
     * @return
     */
    @RequestMapping(value = "/restore", method = RequestMethod.PUT)
    public ResultEntity restore(Long id) {
        //SwLog swLog = geBackInfo(null);

        SwMapspot mapspot = service.getSwMapspot(id);

        if (mapspot.getNode() > 5) {
            return new ResultEntity(ResultEnum.Faild, "已上报，不允许还原！");
        }


        String yearPeriod = service.getCurrentSeason().get(0);
        String[] strings = yearPeriod.split("-");

        if (!(mapspot.getYear().toString().equals(strings[0])
                && mapspot.getCheckperiod().toString().equals(strings[1]))) {
            return new ResultEntity(ResultEnum.Faild, "历史期数的数据不允许还原！");
        }

        if (!(mapspot.getNode() == 1 || mapspot.getNode() == 3 || mapspot.getNode() == 4 || mapspot.getNode() == 5)) {
            return new ResultEntity(ResultEnum.Faild, "省级审核通过不允许还原！");
        }

        if (service.restore(id)) {
            return new ResultEntity(ResultEnum.Success);
        } else {
            return new ResultEntity(ResultEnum.Faild, "图斑还原失败！");
        }

    }

    /**
     * 保存图斑
     *
     * @param mapSpot 举证的图斑
     * @return
     */
    @RequestMapping(value = "/saveSpot", method = RequestMethod.POST)
    public boolean saveSpot(@RequestBody final SwMapspot mapSpot) {
        Integer node = service.getNode(mapSpot.getID());

        if (node == 3)
            mapSpot.setTiming(Calendar.getInstance().getTime());

        if (node == null || node == 0 || node == 3 || node == 4 || node == 5) {
            clearNotNewData(mapSpot);
            return service.proofSpot(mapSpot);
        }
        return false;
    }

    /* 保存图斑
    * @param mapSpot 举证的图斑
    * @return
    */
    @RequestMapping(value = "/saveMobileSpot", method = RequestMethod.POST)
    public boolean saveMobileSpot(@RequestBody final String Str) {
        SwMapspot mapSpot = JSONObject.parseObject(Str, SwMapspot.class);
        Integer node = service.getNode(mapSpot.getID());

        if (node == 3)
            mapSpot.setTiming(Calendar.getInstance().getTime());

        if (node == null || node == 0 || node == 3 || node == 4 || node == 5) {
            clearNotNewData(mapSpot);
            return service.proofSpot(mapSpot);
        }
        return false;
    }

    /**
     * 获取单个图斑信息
     *
     * @param id 图斑id号
     * @return
     */
    @RequestMapping(value = "/SwMapspot", method = RequestMethod.GET)
    public SwMapspot getSwMapspot(@RequestParam("id") final Long id) {
        return service.getSwMapspot(id);
    }

    /**
     * 获取图斑坐标
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/GetSpotShape", method = RequestMethod.GET)
    @ResponseBody
    public Polygon getSpotShape(@PathParam("id") String id) {
        AppSettings appSettings = new AppSettings();
        String serviceUrl = appSettings.getAllWeatherSpotLocationService();
        return SpotArcGISHelper.getSpotShape(serviceUrl, id);
    }


    /**
     * 导出excel
     *
     * @param page     导出条件
     * @param response 返回excel文件
     */
    @RequestMapping(value = "/exportExcel", method = RequestMethod.GET)
    public void exportExcel(@RequestParam(value = "checkperiod", required = false) Integer checkperiod,
                            @RequestParam(value = "period", required = false) Integer period,
                            @RequestParam(value = "selectCity", required = false) String selectCity,
                            @RequestParam(value = "selectCounty", required = false) String selectCounty,
                            @RequestParam(value = "selectStatus", required = false) String selectStatus,
                            @RequestParam(value = "selectType", required = false) String selectType,
                            @RequestParam(value = "selectCityStatus", required = false) String selectCityStatus,
                            @RequestParam(value = "selectSpotSort", required = false) String selectSpotSort,
                            @RequestParam(value = "selectSpotType", required = false) String selectSpotType,
                            @RequestParam(value = "mark", required = false) String mark,
                            @RequestParam(value = "year", required = false) Integer year,
                            HttpServletResponse response) {
        Map<String, Object> map = new HashedMap();
        map.put("checkperiod", checkperiod);
        map.put("period", period);
        map.put("selectCity", selectCity);
        map.put("selectCounty", selectCounty);
        map.put("selectStatus", selectStatus);
        map.put("selectType", selectType);
        map.put("selectCityStatus", selectCityStatus);
        map.put("selectSpotSort", selectSpotSort);
        map.put("selectSpotType", selectSpotType);
        map.put("mark", mark);
        map.put("year", year);

        User user = getCurrentUser();

        service.exportExcel(user, map, response);
    }


    /**
     * 图斑统计（2017-10-10）
     *
     * @param page
     * @return
     */
    @RequestMapping(value = "/searchMapSpot", method = RequestMethod.POST)
    @ResponseBody
    private PagingEntity<MapSpotStatistics> searchMapSpot(@RequestBody Page page) {

        return service.searchMapSpot(page);
    }


    /**
     * 图斑统计（2017-10-10）
     *
     * @param page
     * @return
     */
    @RequestMapping(value = "/exprtMapSpotStatistic", method = RequestMethod.GET)
    @ResponseBody
    private void exprtMapSpotStatistic(@RequestParam("period") int period,
                                       @RequestParam("city") String city,
                                       @RequestParam("county") String county,
                                       @RequestParam("year") int year,
                                       @RequestParam("AUTH_ID") String authId,
                                       @RequestParam("mark") String mark,
                                       HttpServletResponse response) {

        Map<String, Object> map = new HashedMap();
        map.put("period", period);
        if (StrUtil.isNullOrEmpty(city)) {
            map.put("city", null);
        } else {
            map.put("city", city);
        }

        if (StrUtil.isNullOrEmpty(county)) {
            map.put(county, "");
        } else {
            map.put("county", county);
        }
        map.put("year", year);

        map.put("mark",mark);


        service.exportSearchMapSpot( map, response);
    }


    /**
     * 查询标记列表
     *
     * @param page 查询条件
     * @return 返回列表
     */
    @RequestMapping(value = "/listMapSpotMark", method = RequestMethod.POST)
    public PagingEntityForMobile<SwMapspot> listMapSpotMark(@RequestBody Page page) {

        return service.listMapSpotMark(page);
    }


    /**
     * 导出excel
     *
     * @param page     导出条件
     * @param response 返回excel文件
     */
    @RequestMapping(value = "/exportMarkExcel", method = RequestMethod.GET)
    public void exportMarkExcel(@RequestParam(value = "year", required = false) String year,
                            @RequestParam(value = "period", required = false) String period,
                            @RequestParam(value = "cityid", required = false) String cityid,
                            @RequestParam(value = "regionCategory", required = false) String regionCategory,
                            @RequestParam(value = "countyid", required = false) String countyid,
                            @RequestParam(value = "spotAreaCompare", required = false) String spotAreaCompare,
                            @RequestParam(value = "spotArea", required = false) String spotArea,
                            @RequestParam(value = "arableAcreageCompare", required = false) String arableAcreageCompare,
                            @RequestParam(value = "arableAcreage", required = false) String arableAcreage,
                            @RequestParam(value = "adjustableareaCompare", required = false) String adjustableareaCompare,
                            @RequestParam(value = "adjustablearea", required = false) String adjustablearea,
                            @RequestParam(value = "markCategory", required = false) String markCategory,
                            @RequestParam(value = "spotNumber", required = false) String spotNumber,
                            @RequestParam(value = "sumArea", required = false) String sumArea,
                            @RequestParam(value = "sumAreaCompare", required = false) String sumAreaCompare,
                            HttpServletResponse response) {
        Map<String, Object> map = new HashedMap();
        map.put("year", year);
        map.put("period", period);
        map.put("cityid", cityid);
        map.put("regionCategory", regionCategory);
        map.put("countyid", countyid);
        map.put("spotAreaCompare", spotAreaCompare);
        map.put("spotArea", spotArea);
        map.put("arableAcreageCompare", arableAcreageCompare);
        map.put("arableAcreage", arableAcreage);
        map.put("adjustableareaCompare", adjustableareaCompare);
        map.put("adjustablearea", adjustablearea);
        map.put("adjustableareaCompare", adjustableareaCompare);
        map.put("adjustablearea", adjustablearea);
        map.put("markCategory", markCategory);
        map.put("spotNumber", spotNumber);
        map.put("sumArea", sumArea);
        map.put("sumAreaCompare", sumAreaCompare);
        service.exportMarkExcel(map, response);
    }


    /**
     * 图标标记
     * @param maps key为 图标id号，value为标记类型
     * @return 返回是否标记成功
     */
    @Comment(value = "图标标记", returnVal = "返回是否标记成功")
    @RequestMapping(value = "/markMapSpot", method = RequestMethod.PUT)
//    public boolean markMapSpot(@RequestParam("id") @Comment(value = "图标Id", argName = "id") Long id,
//                               @RequestParam("mark") @Comment(value = "标记类型", argName = "mark") Integer mark) {
    public boolean markMapSpot(@RequestBody Map<String, String> maps) {

        List<Condition> list = new ArrayList<Condition>();

        for (Map.Entry<String, String> m : maps.entrySet()){
            list.add(new Condition(m.getKey(), m.getValue()));
        }

        return service.updateMarkMapSpot(list);
    }

    /**
     * 全天候图斑软删除
     * @param ids 删除图斑的id
     * @return 返回是否成功
     */
    @Comment(value = "全天候图斑软删除", returnVal = "返回是否成功")
    @RequestMapping(value = "/deleteMapSpot", method = RequestMethod.PUT)
    public boolean deleteMapSpot(@RequestBody String[] ids){
        return service.deleteMapSpot(ids);
    }


    /**
     * 根据查询条件统一标注或取消标注
     * @param param 查询条件
     * @return 返回是否成
     */
    @RequestMapping(value = "/markMapSpotList", method = RequestMethod.POST)
    public boolean markMapSpotList(@RequestBody String param) {

        JSONObject jsonObject = JSON.parseObject(param);
        Page page = JSON.parseObject(jsonObject.getString("page"),Page.class);

        String type = jsonObject.getString("type");
        String operate = jsonObject.getString("operate");

        return service.markMapSpotList(page,type ,operate);
    }
}
