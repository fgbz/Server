package phalaenopsis.pjmapspot.controller;

import com.alibaba.fastjson.JSON;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import phalaenopsis.common.entity.User;
import phalaenopsis.common.entity.Page;
import phalaenopsis.common.entity.Paging;
import phalaenopsis.common.exception.IllegalParameterException;
import phalaenopsis.common.method.Basis;
import phalaenopsis.pjmapspot.entity.PjMapSpot;
import phalaenopsis.pjmapspot.entity.PjMapSpotReport;
import phalaenopsis.pjmapspot.service.PjMapSpotService;
import phalaenopsis.satellitegraph.entity.Polygon;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.PathParam;

import java.util.List;
import java.util.Map;

/**
 * 项目名称：Phalaenopsis
 * 创建日期：2017/10/23
 * 修改历史：
 * 1. [2017/10/23]创建文件
 *
 * @author chunl
 */
@RestController
@RequestMapping("/PJGraph/PJGraphService/")
public class MapSpotController extends Basis {

    /**
     * 注入PJGraphService
     */
    @Autowired
    private PjMapSpotService service;

    /**
     * 查询PJ图斑
     *
     * @param str 查询信息
     * @return 返回查询的数据
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public Paging<PjMapSpot> list(@RequestBody final String str) {
        final Page page = JSON.parseObject(str, Page.class);
        return service.queryList(Basis.getCurrentUser(), page);
    }

    /**
     * 获取信息
     *
     * @param id 图斑ID
     * @return 返回单个PJ图斑
     */
    @RequestMapping(value = "/getPJMapSpot", method = RequestMethod.GET)
    public PjMapSpot getPJMapSpot(@RequestParam("id") final long id) {
        return service.getPJMapSpot(id);
    }


    /**
     * 获取卫片坐标
     *
     * @param id 图斑ID
     * @return 返回坐标信息
     */
    @RequestMapping(value = "/getSpotShape", method = RequestMethod.GET)
    public Polygon getSpotShape(@RequestParam("id") final String id) {
        return service.getSpotShape(id);
    }


    /**
     * 导出
     *
     * @param response 返回导出的excel
     */
    @RequestMapping(value = "/export", method = RequestMethod.GET)
    public void export(
            @RequestParam(value = "spotNumber", required = false) String spotNumber,
            @RequestParam(value = "spotType", required = false) Integer spotType,
            @RequestParam(value = "isBefore09", required = false) Integer isBefore09,
            @RequestParam(value = "cityAudit", required = false) Integer cityAudit,
            @RequestParam(value = "city", required = false) Integer city,
            @RequestParam(value = "county", required = false) Integer county,
            @RequestParam(value = "auditState", required = false) Integer auditState,
            HttpServletResponse response) {
        Map<String, Object> map = new HashedMap();
        map.put("spotNumber", spotNumber);
        map.put("spotType", spotType);
        map.put("isBefore09", isBefore09);
        map.put("cityAudit", cityAudit);
        map.put("city", city);
        map.put("county", county);
        map.put("auditState", auditState);
        service.export(Basis.getCurrentUser(), map, response);
    }
    
    /**
     * PJ图斑统计
     * 2017年10月25日
     * @param page 查询信息
     * @return 返回查询的数据
     * @author jund
     */
    @RequestMapping(value = "/listMapSpot", method = RequestMethod.POST)
    public Paging<PjMapSpot> listMapSpot(@RequestBody final Page page) {
    	User user = getCurrentUser();
        return service.listMapSpot(page, user);
    }
    
    /**
     * PJ图斑统计导出
     * @param response 返回导出的excel
     */
    @RequestMapping(value = "/listMapSpotExport", method = RequestMethod.GET)
    public void listMapSpotExport(
            @RequestParam(value = "isBefore", required = false) String isBefore,
            @RequestParam(value = "city", required = false) Integer city,
            @RequestParam(value = "county", required = false) Integer county,
            @RequestParam(value = "year", required = false) Integer year,
            HttpServletResponse response) {
        Map<String, Object> map = new HashedMap();
        map.put("isBefore", isBefore);
        map.put("city", city);
        map.put("county", county);
        map.put("year", year);
        service.listMapSpotExport(Basis.getCurrentUser(), map, response);
    }
    
    /**
     * 获取图斑详情展示
     * 2017年10月26日
     * @param id 图斑ID
     * @return 返回单个PJ图斑
     * @author jund
     */
    @RequestMapping(value = "/getPJMapSpotInfo", method = RequestMethod.GET)
    public PjMapSpot getPJMapSpotInfo(@RequestParam("id") final long id) {
        return service.getPJMapSpotInfo(id);
    }
    
    /**
     * 区县图斑填报
     * 2017年10月27日
     * @param  图斑对象
     * @return true/false
     * @author jund
     */
    @RequestMapping(value = "/updatePJMapSpotInfo", method = RequestMethod.POST)
    public boolean updatePJMapSpotInfo(@RequestBody final PjMapSpot spot) {
    	if(spot == null || spot.equals("")){
    		boolean flag = false;
    		return flag;
    	}
        return service.updatePJMapSpotInfo(spot);
    }
    
    /**
     * 区县图斑提交
     * 2017年10月27日
     * @param  图斑对象
     * @return true/false
     * @author jund
     */
    @RequestMapping(value = "/updatePJMapSpotInfoed", method = RequestMethod.POST)
    public boolean updatePJMapSpotInfoed(@RequestBody final PjMapSpot spot) {
    	if(spot == null || spot.equals("")){
    		boolean flag = false;
    		return flag;
    	}
        return service.updatePJMapSpotInfoed(spot);
    }
    
    /**
     * 图斑上报查看
     * 2017年10月30日
     * @return 返回上报信息列表
     * @author jund
     */
    @RequestMapping(value = "/getPJMapSpotReport", method = RequestMethod.GET)
    public List<PjMapSpotReport> getPJMapSpotReport() {
    	User user = getCurrentUser();
        return service.getPJMapSpotReport(user);
    }
    
    /**
     * PJ图斑  获取城市信息
     * 2017年11月7日
     * @return 返回城市信息列表
     * @author jund
     */
    @RequestMapping(value = "/getPJMapSpotRegion", method = RequestMethod.GET)
    public Map<String,Object> getPJMapSpotRegion() {
    	User user = getCurrentUser();
        return service.getPJMapSpotRegion(user);
    }
}
