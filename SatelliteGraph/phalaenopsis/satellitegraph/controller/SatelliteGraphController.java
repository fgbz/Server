package phalaenopsis.satellitegraph.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.PathParam;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import phalaenopsis.common.entity.AppSettings;
import phalaenopsis.common.entity.Condition;
import phalaenopsis.common.entity.Page;
import phalaenopsis.common.entity.Paging;
import phalaenopsis.common.util.BLHCoordinate;
import phalaenopsis.common.util.CoordinateHelper;
import phalaenopsis.common.util.Param7Config;
import phalaenopsis.common.util.XYZCoordinate;
import phalaenopsis.satellitegraph.entity.AuditSpot;
import phalaenopsis.satellitegraph.entity.MapSpot;
import phalaenopsis.satellitegraph.entity.MapSpot2016;
import phalaenopsis.satellitegraph.entity.OperationLog;
import phalaenopsis.satellitegraph.entity.Polygon;
import phalaenopsis.satellitegraph.entity.ReportDetail;
import phalaenopsis.satellitegraph.entity.SatelliteListResult;
import phalaenopsis.satellitegraph.entity.StaticItem;
import phalaenopsis.satellitegraph.service.SatelliteGraphService;
import phalaenopsis.satellitegraph.service.SatelliteGraphServicePartial;
import phalaenopsis.satellitegraph.utils.ArcGISHelper.SpotArcGISHelper;
import phalaenopsis.workflowEngine.controller.WFObject;
import phalaenopsis.workflowEngine.core.WorkflowEngineInstance;

@Controller
@RequestMapping("/SatelliteGraph/Service")
public class SatelliteGraphController {

    Logger logger = Logger.getLogger(SatelliteGraphController.class);

    @Autowired
    private SatelliteGraphService service;

    @Autowired
    private SatelliteGraphServicePartial servicePartial;

    @Autowired
    private WorkflowEngineInstance engine;

    /**
     * 获取数据年份列表及当前年份
     */
    @RequestMapping(value = "/GetYears", method = RequestMethod.GET)
    @ResponseBody
    public List<Integer> getYears() {
        return service.getYears();
    }

    /**
     * 查询图斑列表 TODO
     *
     * @param string
     * @return
     */
    @RequestMapping(value = "/QueryList", method = RequestMethod.POST)
    @ResponseBody
    public SatelliteListResult<MapSpot> queryList(@RequestBody String string) {
        return servicePartial.queryList(JSON.parseObject(string, Page.class));
    }

    /**
     * 查询图斑列表 (卫片填报)
     *
     * @param string
     * @return
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "/SatelliteReportQueryList", method = RequestMethod.POST)
    @ResponseBody
    public Map satelliteReportQueryList(@RequestBody String string) {
        return servicePartial.satelliteReportQueryList(JSON.parseObject(string, Page.class));
    }

    /**
     * 获取图斑详细信息
     *
     * @param year 图斑年份
     * @param id   图斑ID
     * @return
     */
    @RequestMapping(value = "/GetMapSpot", method = RequestMethod.GET)
    @ResponseBody
    public MapSpot getMapSpot(@PathParam("year") int year, @PathParam("id") long id) {
    	MapSpot data = service.getMapSpot(year, id);
        if (data != null) {
            data.setWFObject(GetWFObject(data.getInstanceID()));
        }
        return data;
    }

    /**
     * 保存图斑
     *
     * @param string
     * @return
     */
    @RequestMapping(value = "/SaveMapSpot", method = RequestMethod.POST)
    @ResponseBody
    public boolean saveMapSpot(@RequestBody String mapSpot) {
        JSONObject jsonObject = JSON.parseObject(mapSpot);
        return service.saveMapSpot2016(JSON.parseObject(jsonObject.getString("mapSpot"), MapSpot2016.class));

//		return service.saveMapSpot(JSON.parseObject(jsonObject.getString("mapSpot"), MapSpot.class));
    }

    /**
     * 上报明细 & 是否可以上报 TODO
     *
     * @return
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "/ReportDetailsAndCanReport", method = RequestMethod.GET)
    @ResponseBody
    public Map reportDetailsAndCanReport() {
        return service.reportDetailsAndCanReport();
    }

    /**
     * 获取回退原因 TODO
     *
     * @param regionID
     * @return
     */
    @RequestMapping(value = "/GetSendBackReason", method = RequestMethod.GET)
    @ResponseBody
    public OperationLog getSendBackReason(@PathParam("regionID") int regionID) {
        return service.getSendBackReason(regionID);
    }

    /**
     * 查询上报明细 TODO
     *
     * @return
     */
    @RequestMapping(value = "/ReportDetails", method = RequestMethod.GET)
    @ResponseBody
    public List<ReportDetail> reportDetails() {
        return service.reportDetails();
    }

    /**
     * 查询下级工作进度树 TODO
     *
     * @return
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "/LowerDetails", method = RequestMethod.GET)
    @ResponseBody
    public Map LowerDetails() {
        return service.LowerDetails();
    }

    /**
     * 是否可以上报 TODO
     *
     * @return
     */
    @RequestMapping(value = "/CanReport", method = RequestMethod.GET)
    @ResponseBody
    public String canReport() {
        return service.canReport();
    }

    /**
     * 保存市级审核信息 TODO
     *
     * @param auditSpot
     * @return
     */
    @RequestMapping(value = "/SaveCityAuditInfo", method = RequestMethod.POST)
    @ResponseBody
    public boolean saveCityAuditInfo(@RequestBody String auditSpot) {
        return service.saveCityAuditInfo(JSON.parseObject(auditSpot, AuditSpot.class));
    }

    /**
     * 保存省级审核信息 TODO
     *
     * @param auditSpot
     * @return
     */
    @RequestMapping(value = "/SaveProvinceAuditInfo", method = RequestMethod.POST)
    @ResponseBody
    public boolean saveProvinceAuditInfo(@RequestBody String auditSpot) {
        return service.saveProvinceAuditInfo(JSON.parseObject(auditSpot, AuditSpot.class));
    }

    /**
     * 获取卫片坐标 TODO
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/GetSpotShape", method = RequestMethod.GET)
    @ResponseBody
    public Polygon getSpotShape(@PathParam("id") String id) {
        AppSettings appSettings = new AppSettings();
        String serviceUrl = appSettings.getMapSpotLocationService();
        return SpotArcGISHelper.getSpotShape(serviceUrl, id);
    }

    /**
     * 是否已经进行过规划分析
     *
     * @param region
     * @return
     */
    @RequestMapping(value = "/IsPlanningAnalysis", method = RequestMethod.GET)
    @ResponseBody
    public boolean isPlanningAnalysis(@PathParam("region") int region) {
        return service.isPlanningAnalysis(region);
    }

    /**
     * 规划分析 TODO 20170418完善
     *
     * @param region
     * @return
     */
    @RequestMapping(value = "/PlanningAnalysis", method = RequestMethod.GET)
    @ResponseBody
    public boolean planningAnalysis(@PathParam("region") int region) {
        return service.planningAnalysis(region);
    }

    /**
     * 获取规划分析进度 TODO 20170420完善
     *
     * @return
     */
    @RequestMapping(value = "/GetPlanningAnalysisProgress", method = RequestMethod.GET)
    @ResponseBody
    public int getPlanningAnalysisProgress() {
        return service.getPlanningAnalysisProgress();
    }

    /**
     * 判断图斑号是否存在 TODO
     *
     * @param year
     * @param regionID 行政区代码
     * @param nums
     * @return 不存在的图斑的图斑号
     */
    @RequestMapping(value = "/IsSpotExists", method = RequestMethod.POST)
    @ResponseBody
    public List<String> isSpotExists(@RequestBody int year, @RequestBody int regionID, @RequestBody List<String> nums) {
        return service.isSpotExists(year, regionID, nums);
    }

    /**
     * 获取建设用地统计
     *
     * @param year
     * @param regiontype
     * @return
     */
    // GET方式直接从path中取参数
    @RequestMapping(value = "/GetBuildSpotCount", method = RequestMethod.GET)
    @ResponseBody
    // int year, String regiontype
    public List<StaticItem> getBuildSpotCount(@PathParam("year") int year, @PathParam("regiontype") String regiontype) {
        return service.getBuildSpotCount(year, regiontype);
    }

    /**
     * 获取遥感监测图斑统计
     *
     * @param year
     * @param regiontype
     * @return
     */
    @RequestMapping(value = "/GetRemoteSpotCount", method = RequestMethod.GET)
    @ResponseBody
    public List<StaticItem> getRemoteSpotCount(@PathParam("year") int year,
                                               @PathParam("regiontype") String regiontype) {
        return service.getRemoteSpotCount(year, regiontype);
    }

    /**
     * 获取违法图斑统计
     *
     * @param year
     * @param regiontype
     * @return
     */
    @RequestMapping(value = "/GetIllegalSpotCount", method = RequestMethod.GET)
    @ResponseBody
    public List<StaticItem> getIllegalSpotCount(@PathParam("year") int year,
                                                @PathParam("regiontype") String regiontype) {
        regiontype = convertCharacter(regiontype);
        return service.getIllegalSpotCount(year, regiontype);
    }

    /**
     * 获取违法图斑比例
     *
     * @param year
     * @param regiontype
     * @return
     */
    @RequestMapping(value = "/GetIllegalSpotPercent", method = RequestMethod.GET)
    @ResponseBody
    public List<StaticItem> getIllegalSpotPercent(@PathParam("year") int year,
                                                  @PathParam("regiontype") String regiontype) {
        regiontype = convertCharacter(regiontype);
        return service.getIllegalSpotPercent(year, regiontype);
    }

    /**
     * 根据图斑号进行模糊查询 TODO
     *
     * @param year 年份
     * @param num  图斑号
     * @return
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    @RequestMapping(value = "/GetSpotsByNum", method = RequestMethod.GET)
    @ResponseBody
    public List<MapSpot> getSpotsByNum(@PathParam("year") int year, @PathParam("num") String num) {
        Page page = new Page();
        List<Condition> conditions = new ArrayList<Condition>();
        conditions.add(new Condition("Type", "All"));
        conditions.add(new Condition("SearchText", num));
        page.setConditions(conditions);
        page.setYear(year);
        page.setPageSize(-1);
        SatelliteListResult listResult = servicePartial.queryList(page);
        Paging paging = listResult.getQueryListResult();
        return paging.CurrentList;
    }

    /**
     * 图斑分宗 TODO
     *
     * @param source 原始图斑
     * @param spots  分宗后的图斑
     * @return
     */
    @RequestMapping(value = "/Segment", method = RequestMethod.PUT)
    @ResponseBody
//	public boolean segment(MapSpot source, List<MapSpot> spots) {
    public boolean segment(@RequestBody String json) {
        JSONObject jo = JSON.parseObject(json);
        MapSpot2016 source = JSON.parseObject(jo.getString("source"), MapSpot2016.class);
        List<MapSpot2016> spots = JSON.parseArray(jo.getString("spots"), MapSpot2016.class);
        return servicePartial.segment(source, spots);
    }

    /**
     * 图斑并宗 TODO
     *
     * @param sourceSpots 要并宗的图斑ID列表
     * @param newSpot     并宗后的新图斑
     * @return
     */
    @RequestMapping(value = "/Merge", method = RequestMethod.PUT)
    @ResponseBody
    public boolean merge(@RequestBody String json) {
        JSONObject jo = JSON.parseObject(json);
        MapSpot2016 newSpot = JSON.parseObject(jo.getString("newSpot"), MapSpot2016.class);
        List<MapSpot2016> sourceSpots = JSON.parseArray(jo.getString("sourceSpots"), MapSpot2016.class);
        return servicePartial.merge(sourceSpots, newSpot);
    }

    /**
     * 图斑还原 TODO
     *
     * @param spot
     * @return
     */
    @RequestMapping(value = "/Restore", method = RequestMethod.PUT)
    @ResponseBody
    public boolean restore(@RequestBody String spot) {
        JSONObject jo = JSON.parseObject(spot);
        MapSpot2016 mapSpot2016 = JSON.parseObject(jo.getString("spot"), MapSpot2016.class);
        return servicePartial.restore(mapSpot2016);
    }

    /**
     * 判断图斑是否已进行过分宗或并宗
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/GetSpotStatus", method = RequestMethod.GET)
    @ResponseBody
    public boolean getSpotStatus(@PathParam("id") long id) {
        return servicePartial.getSpotStatus(id);
    }

    /**
     * 图斑并宗根据图斑号模糊查询
     *
     * @param year
     * @param type
     * @return
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    @RequestMapping(value = "GetMergeSpots", method = RequestMethod.GET)
    @ResponseBody
    public List<MapSpot> GetMergeSpots(@PathParam("year") int year, @PathParam("type") String type,
                                       @PathParam("num") String num) {
        //type = convertCharacter(type);
        Page page = new Page();
        List<Condition> conditions = new ArrayList<Condition>();
        conditions.add(new Condition("Type", type));
        conditions.add(new Condition("SearchText", num));
        page.setConditions(conditions);
        page.setYear(year);
        page.setPageSize(-1);
        SatelliteListResult listResult = servicePartial.queryList(page);
        Paging paging = listResult.getQueryListResult();
        for (int i = 0; i < paging.CurrentList.size(); i++) {
            MapSpot mapSpot = (MapSpot) paging.CurrentList.get(i);
            mapSpot.setShape(getSpotShape(String.valueOf(mapSpot.getID())));
        }
        return paging.CurrentList;
    }


    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ResponseBody
    public String test(@RequestParam("x") double x, @RequestParam("y") double y) {

        StringBuilder resultBuilder = new StringBuilder();

        Param7Config param7 = new Param7Config();
        param7 = param7.GetConfig();

        BLHCoordinate blh_84 = new BLHCoordinate(x, y, 0d);
        XYZCoordinate xyz_84 = CoordinateHelper.BLHtoXYZ(blh_84, CoordinateHelper.WGS84Datum);
        XYZCoordinate xyz_80 = CoordinateHelper.Param7(xyz_84, param7.DeltaX, param7.DeltaY, param7.DeltaZ, param7.Rx, param7.Ry, param7.Rz, param7.K);
        BLHCoordinate blh_80 = CoordinateHelper.XYZtoBLH(xyz_80, CoordinateHelper.Xian80Datum);
        resultBuilder.append("x="+ blh_80.getLongitude()+";");
        resultBuilder.append("y=" + blh_80.getLatitude());
//        this.x = blh_80.getLongitude();// .Longitude;
//        this.y = blh_80.getLatitude();//.Latitude;

        //	return new TestDate(java.util.Calendar.getInstance().getTime());
//		servicePartial.test();
        logger.info("cscscinfo");
        logger.debug("cscscinfo");
        logger.error("cscscinfo");

        return  resultBuilder.toString();
//        throw new IllegalArgumentException();

        //return "";
    }


    /**
     * 中文乱码转换
     *
     * @param str
     * @return
     */
    private String convertCharacter(String str) {
        try {
            str = new String(str.getBytes("iso8859-1"), "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return str;
    }

    /**
     * 获取流程信息
     *
     * @param instanceId
     * @return
     */
    private WFObject GetWFObject(String instanceId) {
        if (instanceId == null || instanceId.equals("")) {
            return null;
        }
        return engine.getCurrent(instanceId);
    }

}
