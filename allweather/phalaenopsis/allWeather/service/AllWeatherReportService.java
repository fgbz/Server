package phalaenopsis.allWeather.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

import phalaenopsis.allWeather.bean.WeatherBean;
import phalaenopsis.allWeather.dao.IallWeatherCitydao;
import phalaenopsis.allWeather.entity.HandleProgress;
import phalaenopsis.allWeather.entity.ResultSwLog;
import phalaenopsis.allWeather.entity.SwAuditspot;
import phalaenopsis.allWeather.entity.SwLog;
import phalaenopsis.allWeather.entity.SwMapSpotAuditStatistic;
import phalaenopsis.allWeather.entity.SwMapSpotStatistic;
import phalaenopsis.allWeather.entity.SwMapspot;
import phalaenopsis.allWeather.entity.YearAndPeriod;
import phalaenopsis.allWeather.enums.ReportEnum;
import phalaenopsis.common.entity.Condition;
import phalaenopsis.common.entity.DataDictionaryItem;
import phalaenopsis.common.entity.OrganizationGrade;
import phalaenopsis.common.entity.Page;
import phalaenopsis.common.entity.PagingEntity;
import phalaenopsis.common.entity.Region;
import phalaenopsis.common.entity.User;
import phalaenopsis.common.method.Basis;
import phalaenopsis.common.method.JsonResult;
import phalaenopsis.common.method.Tools.UUID64;
import phalaenopsis.common.method.cache.RegionCache;
import phalaenopsis.common.method.sort.SortList;
import phalaenopsis.common.service.DataDictionaryService;
import phalaenopsis.illegalclue.entity.ResultState;

/**
 * 
 * @author dongdongt
 *
 */
//@Service("allWeatherReportService")
@Service
public class AllWeatherReportService extends Basis {
	private static Logger logger = LoggerFactory.getLogger(AllWeatherReportService.class);

	@Autowired
	private IallWeatherCitydao allWeatherReportDao;
	@Autowired
	private DataDictionaryService dicService;
	@Autowired
	private AllWeatherService allWeatherService;
	
	/**
	 * 获取最新期数
	 * @return
	 */
	public Integer getNewPeroid(){
		return allWeatherReportDao.getNewPeroid();
	}
	
	/**
	 * 查询图斑进度条列表
	 * 
	 * @param page
	 * @return
	 */
	public ResultSwLog getReportQueryList(String mark) {
		User currentUser = getCurrentUser();
		// 组织机构ID
		Region region = currentUser.regions[0];
		int organizationGrade = currentUser.getOrgGrade();
		int id;
		if (organizationGrade == OrganizationGrade.Province) {
			Region r = (Region) RegionCache.get(String.valueOf(region.getParentID()));
			id = r.getParentID();
		} else {
			id = region.getParentID();
		}
		//获取最新期数
		Integer tt= getNewPeroid();
		
		Map<String, Object> map = new HashMap<>();
		map.put("id", id);
		map.put("period",Integer.parseInt(tt.toString().substring(tt.toString().length()-1)));
		map.put("organizationGrade", organizationGrade);
		map.put("year", Integer.parseInt(tt.toString().substring(0,tt.toString().length()-1)));
		map.put("mark", mark);
		// 查询待处理图斑信息
//		List<SwMapspot> mapspots = allWeatherReportDao.getAllMapSpot(map);
		List<HandleProgress> list=allWeatherReportDao.getAllMapSpotByRegion(map);
		// 计算返回的结果集
		ResultSwLog resultSt = getLogList(map, list);
		//查询当前登录区域是否已经上报到省级
//		map.put("type", 1);
		SwLog swLog=allWeatherReportDao.getLog(map);
		if(swLog!=null){
			resultSt.setSwLog(swLog);
		}
		return resultSt;
	}

	/**
	 * 返回界面显示信息
	 * 
	 * @param list
	 * @param maps
	 * @param auditList
	 * @return
	 */
	public ResultSwLog getLogList(Map<String, Object> map, List<HandleProgress> progresses) {
		User currentUser = getCurrentUser();
		// 百分比进度条
		Map<String, HandleProgress> messMaps = new HashMap<>();
		int allAuditCount = 0;// 审核通过总数量
		if(progresses.size()>0){
			for(HandleProgress progress:progresses){
				allAuditCount=progress.getAuditCount()+allAuditCount;
				Region region = (Region) RegionCache.get(String.valueOf(progress.getRegionId()));
				progress.setRegionName(region.getRegionName());
				messMaps.put(String.valueOf(progress.getRegionId()), progress);
			}
		}		
		Map<String, SwLog> mapLog = new HashMap<>();
		// 遍历结果集
		List<SwLog> list = allWeatherReportDao.getLogs(map);
		if (list.size() > 0) {
			for (SwLog swLog : list) {
				Region region = (Region) RegionCache.get(String.valueOf(swLog.getRegion()));
				swLog.setRegionName(region.getRegionName());
				mapLog.put(String.valueOf(swLog.getRegion()), swLog);
			}
		}
		// 组合上报和未上报信息
		for (Entry<String, HandleProgress> message : messMaps.entrySet()) {
			Region region = (Region) RegionCache.get(message.getKey());
			// 如果这个区域没有图斑信息
			if (!mapLog.isEmpty() && mapLog.containsKey(message.getKey())) {
				SwLog swLog = mapLog.get(message.getKey());
				swLog.setRegionName(region.getRegionName());
				swLog.setDenominator(message.getValue().getDenominator());
				swLog.setMember(message.getValue().getNumerator());
				swLog.setProUnAuditCount(message.getValue().getProUnAuditCount());
				swLog.setAuditCount(message.getValue().getAuditCount());
			} else {
				SwLog log = new SwLog();
				log.setRegion(Integer.parseInt(message.getKey()));
				if (region != null) {
					log.setRegionName(region.getRegionName());
				}
				log.setProUnAuditCount(message.getValue().getProUnAuditCount());
				log.setDenominator(message.getValue().getDenominator());
				log.setMember(message.getValue().getNumerator());
				mapLog.put(message.getKey(), log);
			}
		}
		// 如果该区域没有任何图斑信息也显示出来，但是分子，分母都为0
		if (currentUser.getRegionList().size() > 0) {
			for (Region region : currentUser.getRegionList()) {
				// 这里要特别注意，如果key值得类型不一样是匹配不到
				if (mapLog.containsKey(String.valueOf(region.getRegionID()))) {
					continue;
				} else {
					SwLog log = new SwLog();
					log.setRegion(region.getRegionID());
					if (region != null) {
						log.setRegionName(region.getRegionName());
					}
					log.setDenominator(0);
					log.setMember(0);
					log.setProUnAuditCount(0);
					mapLog.put(String.valueOf(region.getRegionID()), log);
				}
			}
		}
		ResultSwLog resultString = new ResultSwLog();
		if (!mapLog.isEmpty()) {
			List<SwLog> swLogs = new ArrayList<>();
			for (Entry<String, SwLog> swLog : mapLog.entrySet()) {
				swLogs.add(swLog.getValue());
			}
			//对list排序
		    SortList<SwLog> sortList = new SortList<SwLog>();  
	        sortList.sort(swLogs, "region", "asc");  
			resultString.setAllAuditCount(allAuditCount);
			resultString.setSwLogs(swLogs);
		}
		return resultString;
	}
	/**
	 * 获取历史核查期数
	 * @return
	 */
	public List<YearAndPeriod> getHistoryPeroid(){
		//获取历史期数
		List<YearAndPeriod>  list=allWeatherReportDao.getHistoryPeroid();
		return list;
	}
	/**
	 * 获取历史下发期数
	 * @return
	 */
	public List<YearAndPeriod> getHistoryxfPeroid(){
		//获取历史期数
		List<YearAndPeriod>  list=allWeatherReportDao.getHistoryxfPeroid();
		return list;
	}
	/**
	 * 图斑审核情况统计表查询
	 * @param page
	 * @return
	 */
	public PagingEntity<SwMapSpotAuditStatistic> getAuditStatisticList(Page page){
		Map<String, Object> map = new HashMap<String, Object>();
		for (Condition condition : page.getConditions()) {
			map.put(condition.getKey(), condition.getValue());
		}
		map.put("startNum", page.getPageSize() * (page.getPageNo() - 1) + 1);
		map.put("endNum", page.getPageSize() * page.getPageNo());
		int count = allWeatherReportDao.getAuditStatisticCount(map);
		SwMapSpotAuditStatistic sumMapSpotAuditStatistic=allWeatherReportDao.getSumAuditStatistic(map);
		List<SwMapSpotAuditStatistic> listaudits=allWeatherReportDao.getAuditStatistic(map);
		//排序list
		SortList<SwMapSpotAuditStatistic> sortList = new SortList<SwMapSpotAuditStatistic>();  
		if(null!=map.get("selectCounty")){
			sumMapSpotAuditStatistic.setSumId(map.get("selectCounty").toString());
			Region region=(Region) RegionCache.get(map.get("selectCounty").toString());
			sumMapSpotAuditStatistic.setSumName(region.getRegionName());
		}
		if(null!=map.get("selectCity") && null==map.get("selectCounty")){
			sumMapSpotAuditStatistic.setSumId(map.get("selectCity").toString());
			Region region=(Region) RegionCache.get(map.get("selectCity").toString());
			sumMapSpotAuditStatistic.setSumName(region.getRegionName());
		}
		if(null==map.get("selectCity") && null ==map.get("selectCounty")){
			sumMapSpotAuditStatistic.setSumId(getCurrentUser().getRegionId().toString());
			Region region=(Region) RegionCache.get(getCurrentUser().getRegionId().toString());
			sumMapSpotAuditStatistic.setSumName(region.getRegionName());
			//对list排序
	        sortList.sort(listaudits, "cityId", "asc");
		}
		//对list排序
		if(map.get("areaType").equals("1") && (null!=map.get("selectCounty") || null!=map.get("selectCity"))){
			sortList.sort(listaudits, "districtcounty", "asc");
		}
		if(map.get("areaType").equals("2") && (null!=map.get("selectCounty") || null!=map.get("selectCity"))){
			sortList.sort(listaudits, "countyId", "asc");
		}
		List<SwMapSpotAuditStatistic> list =new ArrayList<>();
		list.add(sumMapSpotAuditStatistic);
		for(SwMapSpotAuditStatistic auditStatistic:listaudits){
			Region region=(Region) RegionCache.get(auditStatistic.getCityId());
			auditStatistic.setCityName(region.getRegionName());
		}
		list.addAll(listaudits);
		PagingEntity<SwMapSpotAuditStatistic> entity = new PagingEntity<SwMapSpotAuditStatistic>();
		entity.PageNo = page.getPageNo();
		entity.PageSize = page.getPageSize();
		entity.PageCount = count == 0 ? 0 : (count - 1) / page.getPageSize() + 1; // 由于计算pageCount存在整除的情况，所以计算的时候先减1在除以pageSize
		entity.RecordCount = count;
		entity.CurrentList = list;
		entity.PageCount = (entity.RecordCount / entity.PageSize) + (entity.RecordCount % entity.PageSize > 0 ? 1 : 0);
		return entity;
	}
	/**
	 * 根据图斑举证情况统计分页查询
	 * 
	 * @param page
	 * @return
	 */
	public PagingEntity<SwMapSpotStatistic> getLegalproofStatisticList(Page page){
		Map<String, Object> map = new HashMap<String, Object>();
		for (Condition condition : page.getConditions()) {
			map.put(condition.getKey(), condition.getValue());
		}
		map.put("startNum", page.getPageSize() * (page.getPageNo() - 1) + 1);
		map.put("endNum", page.getPageSize() * page.getPageNo());
		int count = allWeatherReportDao.getLegalproofStatisticCount(map);
		SwMapSpotStatistic sumMapSpotStatistic=allWeatherReportDao.getSumSpotStatistic(map);
		List<SwMapSpotStatistic> statistics=allWeatherReportDao.getLegalproofStatistic(map);
		//排序list
		SortList<SwMapSpotStatistic> sortList = new SortList<SwMapSpotStatistic>();
		if(null!=map.get("selectCounty")){
			sumMapSpotStatistic.setSumId(map.get("selectCounty").toString());
			Region region=(Region) RegionCache.get(map.get("selectCounty").toString());
			sumMapSpotStatistic.setSumName(region.getRegionName());
		}
		if(null!=map.get("selectCity") && null==map.get("selectCounty")){
			sumMapSpotStatistic.setSumId(map.get("selectCity").toString());
			Region region=(Region) RegionCache.get(map.get("selectCity").toString());
			sumMapSpotStatistic.setSumName(region.getRegionName());
		}
		if(null==map.get("selectCity") && null ==map.get("selectCounty")){
			sumMapSpotStatistic.setSumId(getCurrentUser().getRegionId().toString());
			Region region=(Region) RegionCache.get(getCurrentUser().getRegionId().toString());
			sumMapSpotStatistic.setSumName(region.getRegionName());
			//对list排序
	        sortList.sort(statistics, "cityId", "asc");
		}
		//对list排序
		if(map.get("areaType").equals("1") && (null!=map.get("selectCounty") || null!=map.get("selectCity"))){
			sortList.sort(statistics, "districtcounty", "asc");
		}
		if(map.get("areaType").equals("2") && (null!=map.get("selectCounty") || null!=map.get("selectCity"))){
			sortList.sort(statistics, "countyId", "asc");
		}
		List<SwMapSpotStatistic> list =new ArrayList<>();
		list.add(sumMapSpotStatistic);
		for(SwMapSpotStatistic spotStatistic:statistics){
			Region region=(Region) RegionCache.get(spotStatistic.getCityId());
			spotStatistic.setCityName(region.getRegionName());
		}
		list.addAll(statistics);
		PagingEntity<SwMapSpotStatistic> entity = new PagingEntity<SwMapSpotStatistic>();
		entity.PageNo = page.getPageNo();
		entity.PageSize = page.getPageSize();
		entity.PageCount = count == 0 ? 0 : (count - 1) / page.getPageSize() + 1; // 由于计算pageCount存在整除的情况，所以计算的时候先减1在除以pageSize
		entity.RecordCount = count;
		entity.CurrentList = list;
		entity.PageCount = (entity.RecordCount / entity.PageSize) + (entity.RecordCount % entity.PageSize > 0 ? 1 : 0);
		return entity;
	}
	
	/**
	 * 根据图斑信息统计分页查询
	 * 
	 * @param page
	 * @return
	 */
	public PagingEntity<SwMapspot> getSwMapspotStatistic(Page page) {
		Map<String, Object> map = new HashMap<String, Object>();
		for (Condition condition : page.getConditions()) {
			map.put(condition.getKey(), condition.getValue());
		}
		map.put("startNum", page.getPageSize() * (page.getPageNo() - 1) + 1);
		map.put("endNum", page.getPageSize() * page.getPageNo());
		map.put("year", page.getYear());
		User currentUser = getCurrentUser();
		// 组织机构级别
		int organizationGrade = currentUser.getOrgGrade();
		
		if (organizationGrade == OrganizationGrade.Province) {
			map.put("organizationGrade", OrganizationGrade.City);
		} else {
			map.put("organizationGrade", OrganizationGrade.County);
		}
		int count = allWeatherReportDao.getStatisticCount(map);
		List<SwMapspot> list = allWeatherReportDao.getStatisticSwMapspots(map);
		if (organizationGrade == OrganizationGrade.Province) {
			list = allWeatherService.markAllVisAlias(list);
		} else {
			list = allWeatherService.markAlias(list);
		}
//		if(list!=null && list.size()>0){
//			List<DataDictionaryItem> listdac = dicService.getDataDictionaryItems("AllWeather", "Focus");
//			if (organizationGrade == OrganizationGrade.Province) {
//				for(SwMapspot swMapspot :list){
//					String value="";
//					Region region= (Region) RegionCache.get(swMapspot.getCity().toString());
//					if(region!=null){
//						swMapspot.setCityName(region.getRegionName());
//					}
//					for(DataDictionaryItem dataDictionaryItem :listdac){
//						if(swMapspot.getMark()!=null&&String.valueOf(swMapspot.getMark()).contains(dataDictionaryItem.getValue())){
//							String tempRemark = dataDictionaryItem.getRemarks();
//							if(!StringUtils.isEmpty(tempRemark)){
//								 JSONObject jsonObject = JSONObject.parseObject(tempRemark);
//						         String alias = String.valueOf(jsonObject.get("alias"));
//						         value += alias+",";
//							}
//						}
//					}
//					if(!StringUtils.isEmpty(value)){
//						value = value.substring(0, value.length()-1);
//					}
//					 swMapspot.setTempRemark(value);
//				}
//			} else {
//				for(SwMapspot swMapspot :list){
//					String value="";
//					Region region= (Region) RegionCache.get(swMapspot.getCity().toString());
//					if(region!=null){
//						swMapspot.setCityName(region.getRegionName());
//					}
//					for(DataDictionaryItem dataDictionaryItem :listdac){
//						if(swMapspot.getMark()!=null&&String.valueOf(swMapspot.getMark()).contains(dataDictionaryItem.getValue())){
//							String tempRemark = dataDictionaryItem.getRemarks();
//							if(!StringUtils.isEmpty(tempRemark)){
//								 JSONObject jsonObject = JSONObject.parseObject(tempRemark);
//						         String report =  jsonObject.get("report").toString();
//						         String visible =jsonObject.get("visible").toString();
//						         String alias = jsonObject.get("alias").toString();
//						         if("1".equals(visible)){
//						        	 value += alias+",";
//						         }
//							}
//						}
//					}
//					if(!StringUtils.isEmpty(value)){
//						value = value.substring(0, value.length()-1);
//					}
//					 swMapspot.setTempRemark(value);
//				}
//			}
//			
//		}
		
		PagingEntity<SwMapspot> entity = new PagingEntity<SwMapspot>();
		entity.PageNo = page.getPageNo();
		entity.PageSize = page.getPageSize();
		entity.PageCount = count == 0 ? 0 : (count - 1) / page.getPageSize() + 1; // 由于计算pageCount存在整除的情况，所以计算的时候先减1在除以pageSize
		entity.RecordCount = count;
		entity.CurrentList = list;
		entity.PageCount = (entity.RecordCount / entity.PageSize) + (entity.RecordCount % entity.PageSize > 0 ? 1 : 0);
		return entity;
	}
	/**
	 * 根据RegionId,期数，分页查询
	 * 
	 * @param page
	 * @return
	 */
	public PagingEntity<SwMapspot> getSwMapspotByRegion(WeatherBean weatherBean) {
		Page page = weatherBean.getPage();
		Map<String, Object> map = new HashMap<String, Object>();
		for (Condition condition : page.getConditions()) {
			map.put(condition.getKey(), condition.getValue());
		}
		map.put("startNum", page.getPageSize() * (page.getPageNo() - 1) + 1);
		map.put("endNum", page.getPageSize() * page.getPageNo());
		map.put("year", page.getYear());
		map.put("mark", weatherBean.getMark());
		User currentUser = getCurrentUser();
		// 组织机构级别
		int organizationGrade = currentUser.getOrgGrade();
		//当前登录用户的ID
		if(map.get("isDaiban").equals("2")){//已办
			map.put("loginRegionId", currentUser.getRegionId());
		}
		//没有说明查全部
		if(map.get("juzhengType")==null){
			map.put("juzhengType", 0);
		}
		if (organizationGrade == OrganizationGrade.Province) {
			map.put("organizationGrade", OrganizationGrade.City);
		} else {
			map.put("organizationGrade", OrganizationGrade.County);
		}
		int count = allWeatherReportDao.getMapspotCount(map);
		List<SwMapspot> list = allWeatherReportDao.getSwMapspots(map);
		if (organizationGrade == OrganizationGrade.Province) {
			list = allWeatherService.markAllVisAlias(list);
		} else {
			list = allWeatherService.markAlias(list);
		}
//		if(list!=null&&list.size()>0){
//			List<DataDictionaryItem> listdac = dicService.getDataDictionaryItems("AllWeather", "Focus");
//			if (organizationGrade == OrganizationGrade.Province) {
//				for(SwMapspot swMapspot :list){
//					String value="";
//					for(DataDictionaryItem dataDictionaryItem :listdac){
//						if(swMapspot.getMark()!=null&&String.valueOf(swMapspot.getMark()).contains(dataDictionaryItem.getValue())){
//							String tempRemark = dataDictionaryItem.getRemarks();
//							if(!StringUtils.isEmpty(tempRemark)){
//								 JSONObject jsonObject = JSONObject.parseObject(tempRemark);
//						         String alias = String.valueOf(jsonObject.get("alias"));
//						         value += alias+",";
//							}
//						}
//					}
//					if(!StringUtils.isEmpty(value)){
//						value = value.substring(0, value.length()-1);
//					}
//					 swMapspot.setTempRemark(value);
//				}
//			} else {
//				for(SwMapspot swMapspot :list){
//					String value="";
//					for(DataDictionaryItem dataDictionaryItem :listdac){
//						if(swMapspot.getMark()!=null&&String.valueOf(swMapspot.getMark()).contains(dataDictionaryItem.getValue())){
//							String tempRemark = dataDictionaryItem.getRemarks();
//							if(!StringUtils.isEmpty(tempRemark)){
//								 JSONObject jsonObject = JSONObject.parseObject(tempRemark);
//						         String report =  jsonObject.get("report").toString();
//						         String visible =jsonObject.get("visible").toString();
//						         String alias = jsonObject.get("alias").toString();
//						         if("1".equals(visible)){
//						        	 value += alias+",";
//						         }
//							}
//						}
//					}
//					if(!StringUtils.isEmpty(value)){
//						value = value.substring(0, value.length()-1);
//					}
//					 swMapspot.setTempRemark(value);
//				}
//			}
//			
//		}
		
		// 这里对查询的结果进行遍历 给行政区域赋值
		PagingEntity<SwMapspot> entity = new PagingEntity<SwMapspot>();
		entity.PageNo = page.getPageNo();
		entity.PageSize = page.getPageSize();
		entity.PageCount = count == 0 ? 0 : (count - 1) / page.getPageSize() + 1; // 由于计算pageCount存在整除的情况，所以计算的时候先减1在除以pageSize
		entity.RecordCount = count;
		entity.CurrentList = list;
		entity.PageCount = (entity.RecordCount / entity.PageSize) + (entity.RecordCount % entity.PageSize > 0 ? 1 : 0);
		return entity;
	}

	/**
	 * 省市审核
	 * 
	 * @param id
	 * @return
	 */
	public ResultState saveAudit(SwAuditspot auditspot) {
		User currentUser = getCurrentUser();
		if (currentUser.getOrgGrade()==OrganizationGrade.City) {// 为空市级审核
			auditspot.setCityauditpersonid(currentUser.getId());
			auditspot.setIscityaudit(1);
			auditspot.setOrganizationType(1);
			auditspot.setCityauditpersonname(currentUser.getName());
			return 1 == allWeatherReportDao.saveOrUpdateSwAudit(auditspot) ? ResultState.Success : ResultState.Failed;
		} else {// 省级审核
			// 省级审核的更新状态为已定性
			auditspot.setProvinceauditpersonid(currentUser.getId());
			auditspot.setOrganizationType(2);
			auditspot.setProvinceauditpersonname(currentUser.getName());
//			auditspot.setProvinceauditispass(auditspot.getCityauditispass());
			return 1 == allWeatherReportDao.saveOrUpdateSwAudit(auditspot) ? ResultState.Success : ResultState.Failed;
		}
	}

	/**
	 * 批量审核
	 * 
	 * @return
	 */
	public JsonResult saveAudits(SwLog swLog) {
		JsonResult jsonResult = new JsonResult();
		boolean flag = false;
		if (swLog.getId() != null) {
			User currentUser = getCurrentUser();
			int organizationGrade = currentUser.getOrgGrade();// 级别
			int regionId = swLog.getRegion();// reigonId
			int year;
			int period;
			if (swLog.getYear() == null) {
				Integer tt=getNewPeroid();//获取最大期数
				year = Integer.parseInt(tt.toString().substring(4));// 当前年份
				period = Integer.parseInt(tt.toString().substring(tt.toString().length()-3));
				swLog.setYear(year);
				swLog.setPeriod(period);
			} else {
				year = swLog.getYear();// 年份
				period = swLog.getPeriod();// 期数
			}
			Map<String, Object> map = new HashMap<>();
			// 如果是省级批量通过，批量更新图斑为定性图斑
			map.put("historyState", swLog.getHistoryState());
			if (swLog.getPassOrNo() == 1) {
				map.put("isPassOrNo", 1);// 审核通过
				swLog.setHistoryState(1);
			} else {
				map.put("isPassOrNo", 2);
				swLog.setHistoryState(2);
			}
			map.put("id", regionId);
			map.put("period", period);
			map.put("organizationGrade", organizationGrade);
			map.put("year", year);
			map.put("operater", currentUser.getId());
			map.put("operatername", currentUser.getName());
			map.put("operationtime", new Date());
			int count = allWeatherReportDao.saveAudits(map);
			// 更新审核状态
			if(count>0){
				flag=true;
				allWeatherReportDao.updateLogState(swLog);
				jsonResult.setMessage(swLog.getHistoryState().toString());
			}else{
				flag=true;
				if(null!=map.get("historyState")){
					jsonResult.setMessage(map.get("historyState").toString());
				}
			}
		}
		jsonResult.setStatus(flag);
		return jsonResult;
	}

	/**
	 * 省市回退功能
	 * 
	 * @param regionId
	 * @return
	 */
	public boolean updateSwLog(SwLog swLog) {
		// 先更新是否回退，更新回退信息
		boolean flag = false;  
		User currentUser = getCurrentUser();
		int organizationGrade = currentUser.getOrgGrade();
		if (swLog.getId() != null && swLog.getType() == 1) {
			swLog.setId(UUID64.newUUID64().getValue());
			swLog.setType(ReportEnum.Back.getIntValue());
			swLog.setOperater(currentUser.getId());
			swLog.setOperationtime(new Date());
			swLog.setOperatername(currentUser.getName());
			swLog.setOrganizationGrade(organizationGrade);
			flag = allWeatherReportDao.saveOrUpdateSwLog(swLog);
			// 查询出改回退地区的图斑信息，将审核不通过的更新为初始状态
			Map<String, Object> map = new HashMap<>();
			map.put("id", swLog.getRegion());
			map.put("period", swLog.getPeriod());// 当前季度
			map.put("organizationGrade", organizationGrade);
			map.put("year", swLog.getYear());// 当前年份
			map.put("mark", swLog.getMark());// 标记
			List<SwMapspot> swMapspots = allWeatherReportDao.getMapSpotByNoPass(map);// 审核未通过的图斑信息列表并且排除已经上报的数据
			List<Long> ids = new ArrayList<>();
			if (swMapspots.size() > 0) {
				for (SwMapspot mapspot : swMapspots) {
					ids.add(mapspot.getID());
				}
			}  
			if (ids.size() > 0) {
				allWeatherReportDao.batchUpdate(ids, organizationGrade);
			}
			
			// 退回更新级别状态1区县2市3省
			List<SwMapspot> swMapspotslevel = allWeatherReportDao.getMapSpotLevel(map);
			List<Long> idslevel = new ArrayList<>();
			if (swMapspotslevel.size() > 0) {
				for (SwMapspot mapspot : swMapspotslevel) {
					idslevel.add(mapspot.getID());
				}
			}  
			if (idslevel.size() > 0) {
				allWeatherReportDao.batchUpdateLevel(idslevel, organizationGrade);
			}
		}
		return flag;
	}

	/**
	 * 市级上报,修改状态
	 * 
	 * @return
	 */
	public boolean saveAppear(String mark) {
		// 插入上报数据
		User currentUser = getCurrentUser();
		Integer tt=getNewPeroid();//获取最大期数
		int year =Integer.parseInt(tt.toString().substring(0,tt.toString().length()-1));// 当前年份
		int period =Integer.parseInt(tt.toString().substring(tt.toString().length()-1));
		boolean flag = false;
		// 如果已经上报过了要进行处理
		if (currentUser.getOrgGrade() == OrganizationGrade.City) {
			Region region = currentUser.getRegions()[0];
			Region regionMax = (Region) RegionCache.get(String.valueOf(region.getParentID()));
			Map<String, Object> map = new HashMap<>();
			map.put("id", currentUser.getRegionId());
			map.put("period",period);
			map.put("organizationGrade", currentUser.getOrgGrade());
			map.put("year", year);
			map.put("type", ReportEnum.Back.getIntValue());
			map.put("mark", mark);
			//查询是否有回退的信息
			SwLog sl = allWeatherReportDao.getLog(map);
			if (sl!=null) {
				//先删除
				allWeatherReportDao.deleteById(sl.getId());
			}
			if(StringUtils.isEmpty(mark)){
				List<DataDictionaryItem> listdac = dicService.getDataDictionaryItems("AllWeather", "Focus");
				if(listdac!=null&&listdac.size()>0){
					for(DataDictionaryItem dataDictionaryItem :listdac){
						SwLog swLog = new SwLog(UUID64.newUUID64().getValue(), year,period , region.getParentID(),
								ReportEnum.Report.getIntValue(), Calendar.getInstance().getTime(),
								currentUser.getOrganizationID(), String.valueOf(regionMax.getParentID()), currentUser.getName(),
								currentUser.getId(),dataDictionaryItem.getValue());
						flag = allWeatherReportDao.saveOrUpdateSwLog(swLog);
							// 更新状态
						SwMapspot mapspot = new SwMapspot();
						mapspot.setYear(year);
						mapspot.setPeriod(period);
						mapspot.setCity(currentUser.getRegionId());
						mapspot.setMark(dataDictionaryItem.getValue());
						allWeatherReportDao.batchUpdateAppear(mapspot);
						//修改level=2图斑值，更新为3代表上报省级
						allWeatherReportDao.batchUpdateAppearLevel(mapspot);
					}
				}
			}
			SwLog swLog = new SwLog(UUID64.newUUID64().getValue(), year,period , region.getParentID(),
					ReportEnum.Report.getIntValue(), Calendar.getInstance().getTime(),
					currentUser.getOrganizationID(), String.valueOf(regionMax.getParentID()), currentUser.getName(),
					currentUser.getId(),mark);
			flag = allWeatherReportDao.saveOrUpdateSwLog(swLog);
				// 更新状态
			SwMapspot mapspot = new SwMapspot();
			mapspot.setYear(year);
			mapspot.setPeriod(period);
			mapspot.setCity(currentUser.getRegionId());
			mapspot.setMark(mark);
			allWeatherReportDao.batchUpdateAppear(mapspot);
			//修改level=2图斑值，更新为3代表上报省级
			allWeatherReportDao.batchUpdateAppearLevel(mapspot);
			
		}
		return flag;
	}
}
