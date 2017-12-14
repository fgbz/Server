package phalaenopsis.pjmapspot.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import phalaenopsis.common.entity.AppSettings;
import phalaenopsis.common.entity.Condition;
import phalaenopsis.common.entity.OrganizationGrade;
import phalaenopsis.common.entity.Page;
import phalaenopsis.common.entity.Paging;
import phalaenopsis.common.entity.Region;
import phalaenopsis.common.entity.User;
import phalaenopsis.common.method.ExportExcel;
import phalaenopsis.pjmapspot.bean.PjMapSpotFlowAuditBean;
import phalaenopsis.pjmapspot.dao.PjMapSpotDao;
import phalaenopsis.pjmapspot.entity.PjMapSpot;
import phalaenopsis.pjmapspot.entity.PjMapSpotReport;
import phalaenopsis.satellitegraph.entity.Polygon;
import phalaenopsis.satellitegraph.utils.ArcGISHelper.SpotArcGISHelper;

/**
 * 项目名称：Phalaenopsis
 * 创建日期：2017/10/23
 * 修改历史：
 * 1. [2017/10/23]创建文件
 *
 * @author chunl
 */
@Service
public class PjMapSpotService {

    @Autowired
    private PjMapSpotDao dao;

    public Paging<PjMapSpot> queryList(User user, Page page) {
        Map<String, Object> map = page.getQueryCondition();
        Paging<PjMapSpot> result = new Paging<PjMapSpot>();

        if (user.getOrgGrade() == OrganizationGrade.County) {
            map.put("county", user.getRegions()[0].getRegionID());
        } else if (user.getOrgGrade() == OrganizationGrade.City) {
            map.put("isCounty", user.getRegions()[0].getParentID());
        } else if (user.getOrgGrade() == OrganizationGrade.Province) {
            map.put("isProvince", 370000);
        }

        AppSettings appSettings = new AppSettings();
        String year = appSettings.getReportYear();
        map.put("year", Integer.parseInt(year));

        result.CurrentList = dao.listPjMapSpot(map);
        result.RecordCount = dao.countPjMapSpot(map);
        result.calculatePageCount(page.getPageSize());
        return result;
    }

    public PjMapSpot getPJMapSpot(long id) {
        return dao.getPjMapSpot(id);
    }



    public Polygon getSpotShape(String id) {
        AppSettings appSettings = new AppSettings();
        //调用定位服务获取坐标
        String serviceUrl = appSettings.getPJMapSpotLocationService();
        return SpotArcGISHelper.getSpotShape(serviceUrl, id);
    }


    public void export(User user, Map<String, Object> map, HttpServletResponse response) {
        if (user.getOrgGrade() == OrganizationGrade.County) {
            map.put("county", user.getRegions()[0].getRegionID());
        } else if (user.getOrgGrade() == OrganizationGrade.City) {
            map.put("isCounty", user.getRegions()[0].getParentID());
        } else if (user.getOrgGrade() == OrganizationGrade.Province) {
            map.put("isProvince", 370000);
        }

        map.put("startNum", 1);
        map.put("endNum", Integer.MAX_VALUE);

        AppSettings appSettings = new AppSettings();
        String year = appSettings.getReportYear();
        map.put("year", year);

        List<PjMapSpot> list = dao.listPjMapSpot(map);
        ExportExcel exportExcel = new ExportExcel();
        String[] fields = {
                "num",
                "districtCounty",
                "districtCountyName",
                "countyName",
                "spotNumber",
                "identificationCode",
                "ownershipDepartmentName",
                "locatedDepartmentName",
                "landtypeName",
                "approvalFile",
                "acreageMu",
                "arableLandAreaAfterMu",
                "adjustableAreaAfterMu",
                "remark"
        };
        exportExcel.exportExcel(fields, new PjMapSpot(), list, "PJ图斑", response);
    }

    /**
     * PJ图斑统计
     * 2017年10月25日
     *
     * @param page 查询信息
     * @return 返回查询的数据
     * @author jund
     */
    public Paging<PjMapSpot> listMapSpot(Page page, User user) {
        Map<String, Object> map = new HashMap<String, Object>();
        for (Condition condition : page.getConditions()) {
            map.put(condition.getKey(), condition.getValue());
        }
        map.put("startNum", page.getPageSize() * (page.getPageNo() - 1) + 1);
        map.put("endNum", page.getPageSize() * page.getPageNo());
        Integer regionId = user.getRegionId();
        map.put("regionId", regionId);
        Paging<PjMapSpot> entity = new Paging<PjMapSpot>();
        int orgGrade = user.getOrgGrade();
        map.put("orgGrade", orgGrade);
        entity.CurrentList = dao.listMapSpot(map);
        int count = dao.listMapSpotCount(map);

        entity.PageNo = page.getPageNo();//第几页
        entity.PageSize = page.getPageSize();//每页显示数
        entity.PageCount = count == 0 ? 0 : (count - 1) / page.getPageSize() + 1; // 由于计算pageCount存在整除的情况，所以计算的时候先减1在除以pageSize
        entity.RecordCount = count;//记录总条数
        entity.PageCount = (entity.RecordCount / entity.PageSize) + (entity.RecordCount % entity.PageSize > 0 ? 1 : 0);//总页数
        return entity;
    }

    /**
     * PJ图斑详情
     * 2017年10月26日
     *
     * @param id
     * @return 返回查询的数据
     * @author jund
     */
    public PjMapSpot getPJMapSpotInfo(long id) {
        //是否被市级打回
        /*PjMapSpot pjMapSpot = dao.getPJMapSpotFlowInfo(id);
        if(pjMapSpot == null || pjMapSpot.equals("")){    //未被市级打回
			return dao.getPJMapSpotInfo(id);
		}else{
			return pjMapSpot;
		}*/
        PjMapSpot pjMapSpot = dao.getPJMapSpotInfo(id);
        return pjMapSpot;
    }

    /**
     * 区县图斑填报
     * 2017年10月27日
     *
     * @param 图斑对象
     * @return true/false
     * @author jund
     */
    public boolean updatePJMapSpotInfo(PjMapSpot spot) {
        boolean flag = false;
        // 修改后耕地+可调<=修改前耕地+可调+建
        BigDecimal arableLandArea = spot.getArableLandArea();
        BigDecimal adjustableArea = spot.getAdjustableArea();
        BigDecimal jsydthmj = spot.getJsydthmj();
        BigDecimal beforArea = arableLandArea.add(adjustableArea).add(jsydthmj);

        BigDecimal arableLandAreaAfter = spot.getArableLandAreaAfter();
        BigDecimal adjustableAreaAfter = BigDecimal.valueOf(spot.getAdjustableAreaAfter());
        BigDecimal afterArea = arableLandAreaAfter.add(adjustableAreaAfter);

        int compareTo = afterArea.compareTo(beforArea);
        if (compareTo == -1 || compareTo == 0) {
        	BigDecimal mu = new BigDecimal(666.666);
        	spot.setArableLandAreaAfter(spot.getArableLandAreaAfter().multiply(mu));
        	spot.setAdjustableAreaAfter(spot.getAdjustableAreaAfter()*666.666);
            int i = dao.updatePJMapSpotInfo(spot);
            if (i > 0) {
                flag = true;
                return flag;
            }
        }
        return flag;
    }

    /**
     * 区县图斑填报
     * 2017年10月27日
     *
     * @param 图斑对象
     * @return true/false
     * @author jund
     */
    public boolean updatePJMapSpotInfoed(PjMapSpot spot) {
        boolean flag = false;
        // 修改后耕地+可调<=修改前耕地+可调+建
        BigDecimal arableLandArea = spot.getArableLandArea();
        BigDecimal adjustableArea = spot.getAdjustableArea();
        BigDecimal jsydthmj = spot.getJsydthmj();
        BigDecimal beforArea = arableLandArea.add(adjustableArea).add(jsydthmj);

        BigDecimal arableLandAreaAfter = spot.getArableLandAreaAfter();
        BigDecimal adjustableAreaAfter = BigDecimal.valueOf(spot.getAdjustableAreaAfter());
        BigDecimal afterArea = arableLandAreaAfter.add(adjustableAreaAfter);

        int compareTo = afterArea.compareTo(beforArea);
        if (compareTo == -1 || compareTo == 0) {
        	BigDecimal mu = new BigDecimal(666.666);
        	spot.setArableLandAreaAfter(spot.getArableLandAreaAfter().multiply(mu));
        	spot.setAdjustableAreaAfter(spot.getAdjustableAreaAfter()*666.666);
            int i = dao.updatePJMapSpotInfoed(spot);
            if (i > 0) {
                flag = true;
                return flag;
            }
        }
        return flag;
    }

    /**
     * 获取上报信息展示
     * 2017年10月30日
     *
     * @return PjMapSpotReport上报信息
     * @author jund
     */
    public List<PjMapSpotReport> getPJMapSpotReport(User user) {
        //获取当前年份
        AppSettings appSettings = new AppSettings();
        String year = appSettings.getReportYear();
		Integer regionId = user.getRegionId();
		Map<String, String> map = new HashMap<String, String>();
		map.put("year", year);
		map.put("regionId", String.valueOf(regionId));
		
		PjMapSpotReport spotReport = dao.getPJMapSpotIsReport(map);
		int isRreport = 0;
		int regionState = 0;
		if(spotReport !=null && !spotReport.equals("")){	//已经上报过
			isRreport = 1;//已经上报过
		}
		int orgGrade = user.getOrgGrade();
		List<PjMapSpotReport> pjMapSpotReport = new ArrayList<PjMapSpotReport>();
		if(orgGrade == 1){
			pjMapSpotReport = dao.getPJMapSpotCountyReport(map);
		}
		//市级，展示下属区县的填报情况
		if(orgGrade == 2){
			pjMapSpotReport = dao.getPJMapSpotReport(map);
		}
		//省级，展示下属市的上报情况
		if(orgGrade == 3){
			pjMapSpotReport = dao.getPJMapSpotProReport(year);
		}
		
		if(pjMapSpotReport != null && pjMapSpotReport.size() > 0){
			for (int i = 0; i < pjMapSpotReport.size(); i++) {
				int reportNum = pjMapSpotReport.get(i).getIsReportNum();
				if(reportNum == 0){
					pjMapSpotReport.get(i).setIsReport(false);
				}else{
					pjMapSpotReport.get(i).setIsReport(true);
				}
				if(pjMapSpotReport.get(i).getAmount() == 0 && reportNum != 0 && isRreport ==0){
					pjMapSpotReport.get(i).setCanReport(true);
				}else{
					pjMapSpotReport.get(i).setCanReport(false);
				}
				regionState = pjMapSpotReport.get(i).getRegionState();
			}
		}else{
			PjMapSpotReport pjMap = new PjMapSpotReport();
			pjMap.setRegionId(regionId);
			pjMap.setName("");
			int unRreport = 0;
			pjMap = insertUnPjMap(pjMap, isRreport, unRreport);
			pjMapSpotReport = new ArrayList<PjMapSpotReport>();
			pjMapSpotReport.add(pjMap);
		}
		if(orgGrade == 2){
			List<PjMapSpotReport> unpjMapSpotReport = dao.getUnPJMapSpotReport(map);
			if(unpjMapSpotReport != null && unpjMapSpotReport.size() > 0){
				for (int i = 0; i < unpjMapSpotReport.size(); i++) {
					Map<String, String> unMap = new HashMap<String, String>();
					unMap.put("year", year);
					unMap.put("regionId", String.valueOf(unpjMapSpotReport.get(i).getRegionId()));
					PjMapSpotReport unSpotReport = dao.getPJMapSpotIsReport(unMap);
					int unRreport = 0;
					if(unSpotReport !=null && !unSpotReport.equals("")){	//已经上报过
						unRreport = 1;//已经上报过
					}
					PjMapSpotReport pjMap = unpjMapSpotReport.get(i);
					pjMap = insertUnPjMap(pjMap, isRreport, unRreport);
					pjMap.setRegionState(regionState);
					if(unRreport == 0){
						pjMap.setCanReport(false);
					}else{
						pjMap.setCanReport(true);
					}
					pjMapSpotReport.add(pjMap);
				}
			}
		}
		return pjMapSpotReport;
	}
    
    public PjMapSpotReport insertUnPjMap(PjMapSpotReport pjMap ,int isRreport,int unRreport){
    	pjMap.setRegionId(pjMap.getRegionId());
		pjMap.setName(pjMap.getName());
		pjMap.setAmount(0);
		pjMap.setPassCount(0);
		pjMap.setUnPassCount(0);
		pjMap.setTotal(0);
		pjMap.setAdjustmentTotal(0);
		pjMap.setPercent(0);
		if(unRreport == 0){
			pjMap.setIsReport(false);
		}else{
			pjMap.setIsReport(true);
		}
		if(isRreport ==0){
			pjMap.setCanReport(true);
			pjMap.setRegionState(0);
		}else{
			pjMap.setCanReport(false);
			pjMap.setRegionState(1);
		}
    	return pjMap;
    }
	
    public Integer flowAudit(PjMapSpotFlowAuditBean pjMapSpotFlowAuditBean) {
        return dao.flowAudit(pjMapSpotFlowAuditBean);
    }

    /**
     * PJ图斑统计导出
     *
     * @param response 返回导出的excel
     */
    public void listMapSpotExport(User user, Map<String, Object> map, HttpServletResponse response) {
        if (user.getOrgGrade() == OrganizationGrade.County) {
            map.put("county", user.getRegions()[0].getRegionID());
        }

        map.put("startNum", 1);
        map.put("endNum", Integer.MAX_VALUE);
        Integer regionId = user.getRegionId();
        map.put("regionId", regionId);
        int orgGrade = user.getOrgGrade();
        map.put("orgGrade", orgGrade);
        List<PjMapSpot> list = dao.listMapSpot(map);
        int num = 0;
        for (int i = 0; i < list.size(); i++) {
            num++;
            list.get(i).setNum(num);
        }
        ExportExcel exportExcel = new ExportExcel();
        String[] fields = {
                "num",
                "districtCounty",
                "districtCountyName",
                "countyName",
                "spotNumber",
                "identificationCode",
                "ownershipDepartmentName",
                "locatedDepartmentName",
                "landtypeName",
                "approvalFile",
                "acreageMu",
                "arableLandAreaAfterMu",
                "adjustableAreaAfterMu",
                "remark"
        };
        exportExcel.exportExcel(fields, new PjMapSpot(), list, "PJ图斑", response);
    }
    
    /**
     * PJ图斑  获取城市信息
     * 2017年11月7日
     * @return 返回城市信息列表
     */
	public Map<String, Object> getPJMapSpotRegion(User user) {
		Map<String, Object> map = new HashMap<String, Object>();
		Integer parentID = user.getRegionId();
		List<Region> regions = dao.getRegionTree(String.valueOf(parentID));
		map.put("regions", regions);
		List<String> years = dao.getPjMapSpotYear();
		map.put("years", years);
		return map;
	}
}
