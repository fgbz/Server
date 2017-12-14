package phalaenopsis.satellitegraph.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import phalaenopsis.common.entity.AppSettings;
import phalaenopsis.common.entity.Condition;
import phalaenopsis.common.entity.OrganizationGrade;
import phalaenopsis.common.entity.Page;
import phalaenopsis.common.entity.Paging;
import phalaenopsis.common.entity.Region;
import phalaenopsis.common.method.Basis;
import phalaenopsis.common.method.Tools.UUID64;
import phalaenopsis.common.method.cache.SysCache;
import phalaenopsis.satellitegraph.entity.AuditSpot;
import phalaenopsis.satellitegraph.entity.LegalProofType;
import phalaenopsis.satellitegraph.entity.LowerTreeLeaf;
import phalaenopsis.satellitegraph.entity.LowerTreeNode;
import phalaenopsis.satellitegraph.entity.MapSpot;
import phalaenopsis.satellitegraph.entity.MapSpot2016;
import phalaenopsis.satellitegraph.entity.MultiPolygon;
import phalaenopsis.satellitegraph.entity.NodeStates;
import phalaenopsis.satellitegraph.entity.OperationLog;
import phalaenopsis.satellitegraph.entity.Polygon;
import phalaenopsis.satellitegraph.entity.ReportDetail;
import phalaenopsis.satellitegraph.entity.SpotLog;
import phalaenopsis.satellitegraph.entity.SpotLogType;
import phalaenopsis.satellitegraph.entity.StaticItem;
import phalaenopsis.satellitegraph.entity.WFVerHistory;
import phalaenopsis.satellitegraph.entity.WFVerInstance;
import phalaenopsis.satellitegraph.utils.ArcGISHelper.ArcGISAreaAndLengthsResult;
import phalaenopsis.satellitegraph.utils.ArcGISHelper.ArcGISWQueryParam;
import phalaenopsis.satellitegraph.utils.ArcGISHelper.HttpHelper;
import phalaenopsis.satellitegraph.utils.ArcGISHelper.SpotArcGISHelper;

@Repository("satelliteGraphDao")
public class SatelliteGraphDao extends Basis {

	@Resource
	private SqlSession sqlSession;

	public List<Integer> getYears() {
		return sqlSession.selectList("satellitegraphservice.getYears");
	}

	public List<StaticItem> getRemoteSpotCount(Map<String, Object> map) {
		return sqlSession.selectList("satellitegraphservice.getRemoteSpotCount", map);
	}

	public List<StaticItem> getIllegalSpotCount(Map<String, Object> map) {
		return sqlSession.selectList("satellitegraphservice.getIllegalSpotCount", map);
	}

	public List<StaticItem> getIllegalSpotPercent(Map<String, Object> map) {
		return sqlSession.selectList("satellitegraphservice.getIllegalSpotPercent", map);
	}

	public int getBuildSpotCount(Map<String, Object> map) {
		return sqlSession.selectOne("satellitegraphservice.getBuildSpotCount", map);
	}

	// TODO 查询图斑列表
	private String type = "";
	private String tempVariable = "";

	public Paging<MapSpot> queryList(Page page1) {
		// Map<String, Object> map = new HashMap<>();
		Paging<MapSpot> page = new Paging<>();
		page.PageNo = page1.getPageNo();
		page.PageSize = page1.getPageNo();
		// page.PageCount = page1.getPageNo();
		page.CurrentList = new ArrayList<MapSpot>();
		if (page1.getConditions() == null) {
			return page;
		}
		if (page1.getConditions().size() > 0) {
			for (Condition condition : page1.getConditions()) {
				if (!condition.getKey().contains("Type")) {
					return page;
				}
			}
		}
		// 获取MapSpot2016
		List<MapSpot2016> mapSpot2016 = sqlSession.selectList("satellitegraphservice.getMapSpot2016");
		// 获取总数
		int queryCount = mapSpot2016.size();
		List<MapSpot2016> listFinal = commonQuery(mapSpot2016, page1.getYear(), page1.getConditions());
		int queryCountFinal = listFinal.size();
		// 判断是否立案
		boolean isBuildCase = false;
		for (Condition item : page1.getConditions()) {
			String key = item.getKey();
			type = item.getValue();
			if (key.contains("IsBuildCase")) {
				isBuildCase = true;
				tempVariable = "true";
			} else {
				isBuildCase = false;
				tempVariable = "false";
			}
		}
		// tab 条件查询
		List<String> theTypes = new ArrayList<>();
		if (type != "All") {
			switch (getCurrentUser().OrgGrade) {
			case OrganizationGrade.County:// 区县
				switch (type) {
				case "待办-疑似违法图斑":
					theTypes.add(NodeStates.DoubtIllegal);
					break;
				case "待办-疑似非新增建设用地":
					theTypes.add(NodeStates.DoubtNotNew);
					break;
				case "新增建设用地-处理中图斑-外业核查中":
					theTypes.add(NodeStates.ProofingLegal);
					theTypes.add(NodeStates.ProofingNotNew);
					break;
				case "新增建设用地-处理中图斑-已举证":
					theTypes.add(NodeStates.ProofLegal);
					theTypes.add(NodeStates.ProofLegalCity);
					theTypes.add(NodeStates.ProofLegalProvince);
					theTypes.add(NodeStates.ProofNotNew);
					theTypes.add(NodeStates.ProofNotNewCity);
					theTypes.add(NodeStates.ProofNotNewProvince);
					break;
				case "新增建设用地-合法图斑":
					theTypes.add(NodeStates.LegalSpot);
					break;
				case "新增建设用地-违法图斑":
					switch (tempVariable) {
					case "true":

						break;
					case "false":

						break;
					case "":
						theTypes.add(NodeStates.ReallyIllegal);
						break;
					}
					break;
				case "非新增建设用地-待处理图斑-外业核查中":
					theTypes.add(NodeStates.ProofingMaybeNew);
					break;
				case "非新增建设用地-待处理图斑-已举证":
					theTypes.add(NodeStates.ProofMaybeNew);
					theTypes.add(NodeStates.ProofMaybeNewCity);
					theTypes.add(NodeStates.ProofMaybeNewProvince);
					break;
				case "非新增建设用地-已确认非新增图斑":
					theTypes.add(NodeStates.NotNewSpot);
					break;

				default:
					return page;
				}
				////// 集合
				List<String> backlogStatus = new ArrayList<>();
				backlogStatus.add(NodeStates.DoubtIllegal);
				backlogStatus.add(NodeStates.DoubtNotNew);
				if (type.indexOf("待办") > -1) {
					//// 条件
				} else if (type.indexOf("审批未通过") > -1) {
					//// 查询条件
				}
				break;
			case OrganizationGrade.City:// 市
				switch (type) {
				case "待办-合法图斑举证":
					theTypes.add(NodeStates.ProofLegalCity);
					// 查询条件
					break;
				case "待办-非新增建设用地图斑举证":
					theTypes.add(NodeStates.ProofNotNewCity);
					// 查询条件
					break;
				case "待办-新增建设用地图斑举证":
					theTypes.add(NodeStates.ProofMaybeNewCity);
					// 查询条件
					break;
				case "已办理-合法图斑举证":
					theTypes.add(NodeStates.ProofLegalCity);
					// 查询条件
					break;
				case "已办理-非新增建设用地图斑举证":
					theTypes.add(NodeStates.ProofNotNewCity);
					// 查询条件
					break;
				case "已办理-新增建设用地图斑举证":
					theTypes.add(NodeStates.ProofMaybeNewCity);
					// 查询条件
					break;
				case "合法图斑":
					theTypes.add(NodeStates.LegalSpot);
					break;
				case "违法图斑":
					switch (tempVariable) {
					case "true":
						// 查询条件
						break;
					case "false":
						// 查询条件
						break;
					case "":
						theTypes.add(NodeStates.ReallyIllegal);
						break;

					default:
						break;
					}
					break;
				case "非新增建设用地":
					theTypes.add(NodeStates.NotNewSpot);
					break;

				default:
					return page;
				}
				List<String> proofCityStatus = new ArrayList<>();
				proofCityStatus.add(NodeStates.ProofLegalCity);
				proofCityStatus.add(NodeStates.ProofNotNewCity);
				proofCityStatus.add(NodeStates.ProofMaybeNewCity);
				if (type.indexOf("待办") > -1) {
					// 查询条件
				} else if (type.indexOf("已办理") > -1) {
					// 查询条件
				}
				break;
			case OrganizationGrade.Province:// 省
				switch (type) {
				case "待办-合法图斑举证":
					// 查询条件
					break;
				case "待办-非新增建设用地图斑举证":
					// 查询条件
					break;
				case "待办-新增建设用地图斑举证":
					// 查询条件
					break;
				case "合法图斑":
					// 查询条件
					break;
				case "违法图斑":
					switch (tempVariable) {
					case "true":
						// 查询条件
						break;
					case "false":
						// 查询条件
					case "":
						// 查询条件
						break;

					default:
						break;
					}
					break;
				case "非新增建设用地":
					// 查询条件
					break;

				default:
					return page;
				}
				List<String> proofProvinceStatus = new ArrayList<>();
				// NodeStates.ProofLegalProvince,
				// NodeStates.ProofNotNewProvince,
				// NodeStates.ProofMaybeNewProvince
				proofProvinceStatus.add(NodeStates.ProofLegalProvince);
				proofProvinceStatus.add(NodeStates.ProofNotNewProvince);
				proofProvinceStatus.add(NodeStates.ProofMaybeNewProvince);
				if (type.indexOf("待办") > -1) {
					// 查询条件
				}
				break;

			default:
				break;
			}
		}

		if (getCurrentUser().OrgGrade != OrganizationGrade.Province && theTypes.size() > 0) {
			// 查询条件
		}
		if (type.contains("待办") || type.contains("已办理") || type.contains("审批未通过")) {

		}
		List<MapSpot2016> result;
		if (page.PageSize == -1) {
			result = /* 查询条件 */new ArrayList<>();
		} else {
			result = /* 查询条件 */new ArrayList<>();
		}

		// 状态显示
		switch (getCurrentUser().OrgGrade) {
		case OrganizationGrade.County:
			// 待处理
			List<String> nodes1 = new ArrayList<>();
			nodes1.add(NodeStates.DoubtIllegal);
			nodes1.add(NodeStates.DoubtNotNew);
			// 举证中
			List<String> nodes2 = new ArrayList<>();
			nodes2.add(NodeStates.ProofingLegal);
			nodes2.add(NodeStates.ProofingNotNew);
			nodes2.add(NodeStates.ProofingMaybeNew);
			// 已举证
			List<String> nodes3 = new ArrayList<>();
			nodes3.add(NodeStates.ProofLegal);
			nodes3.add(NodeStates.ProofLegalCity);
			nodes3.add(NodeStates.ProofLegalProvince);
			nodes3.add(NodeStates.ProofNotNew);
			nodes3.add(NodeStates.ProofNotNewCity);
			nodes3.add(NodeStates.ProofNotNewProvince);
			nodes3.add(NodeStates.ProofMaybeNew);
			nodes3.add(NodeStates.ProofMaybeNewCity);
			nodes3.add(NodeStates.ProofMaybeNewProvince);
			// 当前区域的操作日志
			// 暂时为每个组织机构只负责一个区域
			sqlSession.selectList("satellitegraphservice.queryOperationLog");
			break;

		default:
			break;
		}

		return page;
		// if(page1.getConditions().size()>0){
		// for (Condition condition : page1.getConditions()) {
		// if(condition.getKey().equals("Type")){
		// map.put("Type", condition.getValue());
		// }else if (condition.getKey().equals("SearchText")){
		// map.put("SearchText", condition.getValue());
		// }
		// }
		// int count =
		// sqlSession.selectOne("satellitegraphservice.getMapSpotsCount", map);
		// map.put("startNum", page1.getPageSize() * (page1.getPageNo() - 1) +
		// 1);
		// map.put("endNum", page1.getPageSize() * page1.getPageNo());
		//
		// List<MapSpot> list =
		// sqlSession.selectList("satellitegraphservice.getMapSpotList", map);
		//
		// page = new Paging<MapSpot>();
		// page.PageNo = page1.getPageNo();
		// page.PageSize = page1.getPageSize();
		// page.PageCount = count == 0 ? 0 : (count - 1) / page1.getPageSize() +
		// 1; // 由于计算pageCount存在整除的情况，所以计算的时候先减1在除以pageSize
		// page.RecordCount = count;
		// page.CurrentList = list;
		// page.PageCount = (page.RecordCount / page.PageSize) +
		// (page.RecordCount % page.PageSize > 0 ? 1 : 0);
		//
		// return page;
		// }
	}

	// 筛选
	private List<MapSpot2016> commonQuery(List<MapSpot2016> mapSpot2016, int year, List<Condition> conditions) {
		// 过滤集合
		List<MapSpot2016> list = new ArrayList<>();// 过滤年份
		List<MapSpot2016> list2 = new ArrayList<>();// 过滤图斑号
		List<MapSpot2016> list3 = new ArrayList<>();// 过滤区域
		List<MapSpot2016> list4 = new ArrayList<>();// 合法举证类型
		for (MapSpot2016 item : mapSpot2016) {
			if (item.getYear() == year) {
				list.add(item);
			}
		}
		// 输入框
		for (Condition condition : conditions) {
			if (condition.getKey().contains("SearchText")) {
				String condiationValue = condition.getValue();
				for (MapSpot2016 item : list) {
					if (item.getSpotNumber().contains(condiationValue)) {
						list2.add(item);
					}
				}
			}
		}
		// 区域
		List<Integer> regions = new ArrayList<>();
		for (Condition condition : conditions) {
			if (condition.getKey().contains("Region")) {
				String condiationValue = condition.getValue();
				regions.add(Integer.parseInt(condiationValue));
				for (MapSpot2016 item : list2) {
					for (Integer integer : regions) {
						if (integer.equals(item.getCity()) || integer.equals(item.getCounty())) {
							list3.add(item);
						}

					}
				}
			} else {
				if (getCurrentUser().OrgGrade != OrganizationGrade.Province) {
					if (getCurrentUser().regions != null) {
						for (Region item : getCurrentUser().regions) {
							regions.add(item.RegionID);
							for (MapSpot2016 item2 : list2) {
								for (Integer integer : regions) {
									if (integer.equals(item2.getCity()) || integer.equals(item2.getCounty())) {
										list3.add(item2);
									}

								}
							}
						}
					}

				}
			}
		}
		// 合法举证类型
		for (Condition condition : conditions) {
			if (condition.getKey().contains("LegalProofType")) {
				String LegalProofType = condition.getValue();
				for (MapSpot2016 item : list3) {
					if (item.getLegalProofType() == Integer.parseInt(LegalProofType)) {
						list4.add(item);
					}
				}
			}
		}
		return list4;
	}

	/**
	 * 获取查询条件
	 * 
	 * @return
	 */
	// private String[] queryWhere(int year,List<Condition> conditionList) {
	// List<Object> list = new ArrayList<>();
	// list.add(year);
	// for (Condition condition : conditionList) {
	// if(condition.getKey().contains("SearchText")){
	// //获取图斑号
	// String searchValue = condition.getValue();
	// list.add(searchValue);
	// }
	// }
	// //区域
	//// List<Integer> regions = new ArrayList<>();
	// for (Condition condition : conditionList) {
	// if(condition.getKey().contains("Region")){
	// //获取图斑号
	// list.add(condition.getValue());
	// }else{
	// if(getCurrentUser().OrgGrade!=OrganizationGrade.Province){
	// //通过用户的organizationid获取regionId
	// Region[] region = getCurrentUser().regions;
	// for (Region item : region) {
	// if(item.RegionID==){
	//
	// }
	// }
	// }
	// }
	// }
	//
	// return null;
	// }

	// TODO 获取图斑详细信息
	public MapSpot2016 selectMapSpot2016(int year, long id) {
		if (year == 0 || id == 0) {
			return null;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("year", year);
		map.put("id", id);
//		 return sqlSession.selectOne("satellitegraphservice.selectOneMapSpot2016");
		MapSpot2016 mapSpot2016 = sqlSession.selectOne("satellitegraphservice.selectMapSpot2016", map);
		AuditSpot theAuditSpot = sqlSession.selectOne("satellitegraphservice.queryAuditSpot", id);
		mapSpot2016.setTheAuditSpot(theAuditSpot);
		return mapSpot2016;
	}

	// TODO 保存图斑信息
	public boolean saveMapSpot(MapSpot mapSpot) {
		if (mapSpot == null || mapSpot.getID() == 0) {
			return false;
		}
		int result = sqlSession.update("satellitegraphservice.saveMapSpot", mapSpot);
		return result > 0;
	}

	// TODO 保存图斑信息
	public boolean saveMapSpot2016(MapSpot2016 mapSpot216) {
		if (mapSpot216 == null || mapSpot216.getID() == 0) {
			return false;
		}
		int result = sqlSession.update("satellitegraphservice.updateMapSpot2016", mapSpot216);
		return result > 0;
	}
	// TODO 保存图斑审核信息
	public boolean saveAuditSpot(AuditSpot auditSpot) {
		int result = sqlSession.update("satellitegraphservice.updateAuditSpot", auditSpot);
		return result > 0;
	}

	// TODO 判断图斑号是否存在
	public List<String> isSpotExists(int year, int regionID, List<String> nums) {
		if (nums == null) {
			return new ArrayList<String>();
		}
		Map<String, Object> map = new HashMap<>();
		map.put("year", year);
		map.put("regionID", regionID);
		map.put("nums", nums);
		// 数据库中存在的监测编号
		List<String> notExistsNums = new ArrayList<>();
		List<String> temp = sqlSession.selectList("satellitegraphservice.isSpotExists", map);
		for (String numString : nums) {
			for (String tempString : temp) {
				if (!numString.equals(tempString)) {
					// 装入图斑号不存在的集合中
					notExistsNums.add(numString);
				}
			}
		}
		return notExistsNums;
	}

	// 是否已经进行过规划分析
	public boolean isPlanningAnalysis(Map<String, Object> map) {
		int count = sqlSession.selectOne("satellitegraphservice.isPlanningAnalysis", map);
		return count > 0;
	}

	// 获取回退原因
	public OperationLog getSendBackReason(Map<String, Object> map) {
		OperationLog log = sqlSession.selectOne("satellitegraphservice.getSendBackReason", map);
		return log;
	}

	/**
	 * 是否可以上报相关操作
	 */
	// 查询区县级上报信息
	public OperationLog queryReportedCounty(Map<String, Object> map) {
		return sqlSession.selectOne("satellitegraphservice.queryReportedCounty", map);
	}

	// 判断市级审核是否通过
	public int queryRefuseReported(Map<String, Object> map) {
		int count = sqlSession.selectOne("satellitegraphservice.queryRefuseReported", map);
		return count;
	}

	// 查询市级上报信息
	public OperationLog queryReportedCity(Map<String, Object> map) {
		OperationLog log = sqlSession.selectOne("satellitegraphservice.queryReportedCity", map);
		return log;
	}

	/**
	 * 查询上报明细相关操作
	 */
	// 是否已导入图斑
	public boolean isImportMapSpot(String year) {
		int count = sqlSession.selectOne("satellitegraphservice.isImportMapSpot", year);
		return count > 0 ? true : false;
	}

	// 区县级图斑处理进度
	public List<ReportDetail> queryReportDetailCounty(Map<String, Object> map) {
		return sqlSession.selectList("satellitegraphservice.queryReportDetailCounty", map);
	}

	// 市级审核进度
	public List<ReportDetail> queryReportDetailCity(Map<String, Object> map) {
		return sqlSession.selectList("satellitegraphservice.queryReportDetailCity", map);
	}

	// 省级审核进度
	public List<ReportDetail> queryReportDetailProvince(String year) {
		return sqlSession.selectList("satellitegraphservice.queryReportDetailProvince", year);
	}

	/**
	 * 保存市级审核信息
	 */
	public boolean saveCityAuditInfo(AuditSpot auditSpot) {
		auditSpot.setCityAudit(true);
		return saveAuditInfo(auditSpot, "CITY");

	}

	/**
	 * 保存省级审核信息
	 */
	public boolean saveProvinceAuditInfo(AuditSpot auditSpot) {
		auditSpot.setCityAudit(true);
		return saveAuditInfo(auditSpot, "PROVINCE");

	}
	@Transactional
	private boolean saveAuditInfo(AuditSpot auditSpot, String level) {
		if (auditSpot == null || auditSpot.getMapSpotId() == 0) {
			return false;
		}
		if (level == null || level.equals("")) {
			return false;
		}
		if (auditSpot.getId() == 0) {
			// 保存操作
			auditSpot.setId(UUID64.newUUID64().getValue());
			sqlSession.insert("satellitegraphservice.saveAuditInfo", auditSpot);
		} else {
			// 更新操作
			sqlSession.update("satellitegraphservice.updateAuditSpot", auditSpot);
		}
		SpotLog spotLog = new SpotLog();
			spotLog.CreateTime = new Date();
			
		if (level.equals("CITY")) {
			spotLog.setSpotLogType(SpotLogType.CityAudit);
			spotLog.setUserID(auditSpot.getCityAuditPersonID());
			String cityNote = auditSpot.getCityAuditIsPass() == 0 ? "" 
					: String.valueOf(auditSpot.getCityAuditIsPass());
			spotLog.setNote(cityNote);
		}
		if (level.equals("PROVINCE")) {
			spotLog.setSpotLogType(SpotLogType.ProvinceAudit);
			spotLog.setUserID(auditSpot.getProvinceAuditPersonID());
			String provinceNote = auditSpot.getProvinceAuditIsPass() == 0 ? ""
					: String.valueOf(auditSpot.getProvinceAuditIsPass());
			spotLog.setNote(provinceNote);
		}
		spotLog.setMapSpotId(auditSpot.getMapSpotId());
		// 保存
		spotLog.setID (UUID64.newUUID64().getValue());
		sqlSession.insert("satellitegraphservice.saveSpotLog", spotLog);
		return true;

	}

	/**
	 * 查询下级工作进度树相关
	 * 
	 */
	public List<LowerTreeNode> queryLowerTreeNodes() {
		return sqlSession.selectList("satellitegraphservice.queryLowerTreeNodes");
	}

	public List<LowerTreeLeaf> queryLowerTreeLeafs(String year) {
		return sqlSession.selectList("satellitegraphservice.queryLowerTreeLeafs", year);
	}

	/**
	 * 规划分析 TODO 20170418完善
	 * 
	 * @param region
	 * @return
	 */
	public boolean planningAnalysis(int region) {
		// 查询mapspot2016实体
		List<MapSpot2016> listTemp = sqlSession.selectList("satellitegraphservice.queryMapSpot2016");
		List<MapSpot2016> reallyList = new ArrayList<>();
		for (MapSpot2016 mapSpot2016 : listTemp) {
			if (mapSpot2016.getCity() == region && mapSpot2016.getLegalProofType() == LegalProofType.History
					&& mapSpot2016.getNode().equals(NodeStates.ProofLegalProvince)
					&& mapSpot2016.getTheAuditSpot().getProvinceAuditIsPass() == 0) {
				reallyList.add(mapSpot2016);
			}
		}
		try {
			spotPlanSuitAnalyze(reallyList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	// 图斑规划套合分析
	private void spotPlanSuitAnalyze(List<MapSpot2016> reallyList) throws Exception {
		if (reallyList == null || reallyList.size() == 0) {
			return;
		}
		// HttpCommunicator http = new
		// HttpCommunicator(HttpCommunicator.ContentType.Form);
		// HttpCommunicator(Nefarian.Utility.HttpCommunicator.ContentType.Form);
		AppSettings appSettings = new AppSettings();
		String serviceUrl = appSettings.getMapSpotLocationService();
		String geometryService = appSettings.getGeometryService();
		String geoWKID = appSettings.getGeoWKID();
		String projWKID = appSettings.getProjWKID();
		int i = 0; // 用于显示进度
		for (MapSpot2016 mapSpot2016 : reallyList) {
			Polygon shape = SpotArcGISHelper.getSpotShape(serviceUrl, String.valueOf(mapSpot2016.getID()));
			if (shape == null)
				return;
			int maxBranchNo = 0;// 记录最大图斑分支号
			int removeIndex = serviceUrl.lastIndexOf("/FeatureServer");
			serviceUrl = serviceUrl.subSequence(removeIndex, serviceUrl.length()).toString();
			String url = serviceUrl + "/MapServer/0/query";
			ArcGISWQueryParam param = new ArcGISWQueryParam("TBBH,OBJECTID",
					"TBBH like'%" + mapSpot2016.getSpotNumber().split("-")[0] + "%'");
			// String jsonResult = http.postString(url, param.toString());
			List<NameValuePair> list = new ArrayList<>();
			list.add(new BasicNameValuePair("param", param.toString()));
			String jsonResult = EntityUtils.toString(HttpHelper.HttpPost(url, list));
			// JsonObject json =
			// JsonConvert.DeserializeObject<JObject>(jsonResult);
			// 将json解析成JSONObject
			JSONObject json = JSONObject.parseObject(jsonResult);
			JSONArray results = json != null ? (JSONArray) json.get("features") : null;
			String ObjectID = null;
			if (results != null && results.size() > 0) {
				for (int j = 0; j < results.size(); j++) {
					JSONObject jsonObject = (JSONObject) results.get(j);
					String TBBH = jsonObject.getString("TBBH");
					if (TBBH != null) {
						if (TBBH.equals(mapSpot2016.getSpotNumber())) {
							ObjectID = jsonObject.getString("OBJECTID");
						}
						String[] nums = TBBH.split("-");
						if (nums.length > 1) {
							maxBranchNo = maxBranchNo < Integer.parseInt(nums[1]) ? Integer.parseInt(nums[1])
									: maxBranchNo;
						}
					}
				}
			}
			Dictionary<String, Object> intersectDict = new Hashtable<>();
			Dictionary<String, Object> differenceDict = new Hashtable<>();
			/* 1.将选中图斑与规划地图服务进行identify操作，判断是否存在套合关系 */
			JSONObject jsonIT = SpotArcGISHelper.identifyAction(appSettings.getPlanSpotService(), shape, null);
			// JArray resultsIL = jsonIT != null ? (JArray)jsonIT["results"] :
			// null;
			JSONArray resultsIL = jsonIT != null ? (JSONArray) jsonIT.get("results") : null;
			if (resultsIL != null && resultsIL.size() > 0) {
				Polygon targetLPolygon = null;// 求取相交目标空间信息
				MultiPolygon mpL = new MultiPolygon();
				for (int x = 0; x < resultsIL.size(); x++) {
					mpL.Geometries.add((Polygon) ((JSONObject) resultsIL.get(x)).parse("geometry"));
				}
				if (mpL.Geometries.size() > 1) {
					targetLPolygon = SpotArcGISHelper.unionAction(geometryService, mpL);
				} else {
					// targetLPolygon = mpL.Geometries[0];
					if (mpL.Geometries != null && mpL.Geometries.size() > 0) {
						targetLPolygon = mpL.Geometries.get(0);
					}
				}
				/* 2.存在套合，则将套合部分独立出来 */
				MultiPolygon intersectL = SpotArcGISHelper.intersectAction(geometryService, shape, targetLPolygon);
				if (intersectL != null && intersectL.Geometries.size() > 0) {
					Polygon targetIntersectLPolygon = null;// 求取相交目标空间信息
					if (intersectL.Geometries.size() > 1) {
						targetIntersectLPolygon = SpotArcGISHelper.unionAction(geometryService, intersectL);
					} else
						targetIntersectLPolygon = intersectL.Geometries.get(0);
					// 和建设用地套合，求取套合面积
					/* 3.求取套合部分面积，作为该图斑的总面积 */
					MultiPolygon projectPolygon = SpotArcGISHelper.projectAction(geometryService, intersectL, geoWKID,
							projWKID);
					ArcGISAreaAndLengthsResult jcmjAL = SpotArcGISHelper.areaAndLengthAction(geometryService,
							projectPolygon);
					double temp = 0.0;
					if (jcmjAL.getAreas() != null) {
						for (double item : jcmjAL.getAreas()) {
							temp += item;
						}
					}
					double jcmj = jcmjAL != null ? temp : 0.0;
					/*
					 * 4.将套合部分与建设用地进行identity操作，求取相交部分作为该图斑的建设用地面积，
					 * 并在图斑处理结果服务中将选中的图斑属性更新为该图斑的属性，包括空间信息
					 */
					JSONObject jsonJ = SpotArcGISHelper.identifyAction(appSettings.getConstructionLandService(),
							targetIntersectLPolygon, null);
					JSONArray resultsJ = jsonJ != null ? (JSONArray) jsonJ.get("results") : null;
					if (resultsJ != null && resultsJ.size() > 0) {
						Polygon targetIntersectPolygon = null;// 求取相交目标空间信息
						MultiPolygon mp = new MultiPolygon();
						for (int q = 0; q < resultsJ.size(); q++) {
							mp.Geometries.add((Polygon) ((JSONObject) resultsIL.get(q)).parse("geometry"));
						}
						if (mp.Geometries.size() > 1) {
							targetIntersectPolygon = SpotArcGISHelper.unionAction(geometryService, mp);
						} else {
							targetIntersectPolygon = mp.Geometries.get(0);
						}
						// 求取原始图斑与建设用地图斑相交部分，即为新增建设用地
						MultiPolygon intersects = SpotArcGISHelper.intersectAction(geometryService,
								targetIntersectLPolygon, targetIntersectPolygon);
						if (intersects != null && intersects.Geometries.size() > 0) {
							// 求新增建设用地图斑面积
							MultiPolygon projectJPolygon = SpotArcGISHelper.projectAction(geometryService, intersects,
									geoWKID, projWKID);
							ArcGISAreaAndLengthsResult newBuiltAL = SpotArcGISHelper
									.areaAndLengthAction(geometryService, projectJPolygon);
							double temp_newBuiltAL = 0.0;
							if (newBuiltAL.getAreas() != null) {
								for (double item : newBuiltAL.getAreas()) {
									temp_newBuiltAL += item;
								}
							}
							double newBuiltSpotArea = newBuiltAL != null ? temp_newBuiltAL : 0.0;
							intersectDict.put("targetIntersectLPolygon", targetIntersectLPolygon);
							intersectDict.put("JCMJ", jcmj);
							intersectDict.put("JSYDMJ", newBuiltSpotArea);
							double gdmj = CaculateArableAcreage(geometryService, targetIntersectLPolygon, appSettings);
							intersectDict.put("GDMJ", gdmj);
							// TODO:"是否符合规划"赋值为是
							intersectDict.put("His_IsConfirmPlanning", true);
						}
					} else {
						/* 5.不相交时，建设用地面积为0，不用再求建设用地面积 */
						intersectDict.put("targetIntersectLPolygon", targetIntersectLPolygon);
						intersectDict.put("JCMJ", jcmj);
						intersectDict.put("JSYDMJ", 0);
						double gdmj = CaculateArableAcreage(geometryService, targetIntersectLPolygon, appSettings);
						intersectDict.put("GDMJ", gdmj);
						// TODO:"是否符合规划"赋值为是
						intersectDict.put("His_IsConfirmPlanning", true);
					}
					/* 6.通过difference方法，求得不相交部分，求取相关面积，将该部分独立成单独图斑，导入地图服务中 */
					MultiPolygon differenceL = SpotArcGISHelper.DifferenceAction(geometryService,
							targetIntersectLPolygon, shape);
					if (differenceL != null && differenceL.Geometries.size() > 0) {
						Polygon targetPolygonL = null;// 目标空间信息
						if (differenceL.Geometries.size() > 1) {
							targetPolygonL = SpotArcGISHelper.unionAction(geometryService, differenceL);
						} else {
							targetPolygonL = differenceL.Geometries.get(0);
						}
						// 求取未套合面积，即该图斑的监测面积
						MultiPolygon projectWPolygon = SpotArcGISHelper.projectAction(geometryService, differenceL,
								geoWKID, projWKID);
						ArcGISAreaAndLengthsResult wjcmjAL = SpotArcGISHelper.areaAndLengthAction(geometryService,
								projectWPolygon);
						double temp_wjcmjAL = 0.0;
						if (wjcmjAL.getAreas() != null) {
							for (double item : wjcmjAL.getAreas()) {
								temp_wjcmjAL += item;
							}
						}
						double wjcmj = wjcmjAL != null ? temp_wjcmjAL : 0.0;
						/* 7.将不相交部分与建设用地进行identity操作，求取相交部分作为该图斑的建设用地面积 */
						JSONObject jsonWJ = SpotArcGISHelper.identifyAction(appSettings.getConstructionLandService(),
								targetIntersectLPolygon, null);
						JSONArray resultsWJ = jsonWJ != null ? (JSONArray) jsonWJ.get("results") : null;
						if (resultsWJ != null && resultsWJ.size() > 0) {
							Polygon targetIntersectWPolygon = null;// 求取相交目标空间信息
							MultiPolygon mp = new MultiPolygon();
							for (int e = 0; e < resultsWJ.size(); e++) {
								mp.Geometries.add((Polygon) ((JSONObject) resultsWJ.get(e)).parse("geometry"));
							}
							if (mp.Geometries.size() > 1) {
								targetIntersectWPolygon = SpotArcGISHelper.unionAction(geometryService, mp);
							} else {
								targetIntersectWPolygon = mp.Geometries.get(0);
							}
							// 求取原始图斑与建设用地图斑相交部分，即为新增建设用地
							MultiPolygon intersectWs = SpotArcGISHelper.intersectAction(geometryService, targetPolygonL,
									targetIntersectWPolygon);
							if (intersectWs != null && intersectWs.Geometries.size() > 0) {
								// 求新增建设用地图斑面积
								MultiPolygon projectWJPolygon = SpotArcGISHelper.projectAction(geometryService,
										intersectWs, geoWKID, projWKID);
								ArcGISAreaAndLengthsResult newBuiltWAL = SpotArcGISHelper
										.areaAndLengthAction(geometryService, projectWJPolygon);
								double temp_newBuiltWAL = 0.0;
								if (newBuiltWAL.getAreas() != null) {
									for (double item : newBuiltWAL.getAreas()) {
										temp_newBuiltWAL += item;
									}
								}
								double newBuiltWSpotArea = newBuiltWAL != null ? temp_newBuiltWAL : 0.0;
								/* 8.将该图斑作为一个新图斑，导入地图服务中 */
								differenceDict.put("JCMJ", wjcmj);
								differenceDict.put("JSYDMJ", newBuiltWSpotArea);
								// TODO:"是否符合规划"赋值为否
								differenceDict.put("His_IsConfirmPlanning", false);
							} else {
								/* 9.不相交时，建设用地面积为0，不用再求建设用地面积，直接导入地图服务中 */
								differenceDict.put("JCMJ", wjcmj);
								differenceDict.put("JSYDMJ", 0);
								// TODO:"是否符合规划"赋值为否
								differenceDict.put("His_IsConfirmPlanning", false);
							}
						} else {
							/* 9.不相交时，建设用地面积为0，不用再求建设用地面积，直接导入地图服务中 */
							differenceDict.put("JCMJ", wjcmj);
							differenceDict.put("JSYDMJ", 0);
							// TODO:"是否符合规划"赋值为否
							differenceDict.put("His_IsConfirmPlanning", false);
						}
						differenceDict.put("TBBH", getSpotNumber(mapSpot2016.getSpotNumber(), ++maxBranchNo));
						differenceDict.put("XZQDM", mapSpot2016.getDistrictCounty());
						differenceDict.put("XZQMC", mapSpot2016.getDistrictCountyName());
						differenceDict.put("GLQDM", mapSpot2016.getCounty());
						differenceDict.put("GLQMC", mapSpot2016.getCountyName());
						double gdmj = CaculateArableAcreage(geometryService, targetPolygonL, appSettings);
						differenceDict.put("GDMJ", gdmj);
						differenceDict.put("MID", UUID64.newUUID64().getValue());
						SpotImportAction(null, differenceDict, geometryService, targetPolygonL, null, appSettings);
						CopyMapSpotInfo(mapSpot2016, differenceDict);
					}
				}
			} else {
				/* 10.与规划地图服务不相交，则整个图斑判定为不符合规划 */
				// TODO:"是否符合规划"赋值为否
				mapSpot2016.setHisIsConfirmPlanning(false);
			}
			if (differenceDict.size() > 0 && mapSpot2016.getSpotNumber().indexOf("-") < 0) {
				mapSpot2016.setSpotNumber(getSpotNumber(mapSpot2016.getSpotNumber(), ++maxBranchNo));
			}
			if (intersectDict.size() > 0) {
				intersectDict.put("TBBH", mapSpot2016.getSpotNumber());
				mapSpot2016.setSpotArea(Double.valueOf((String) intersectDict.get("JCMJ")));
				mapSpot2016.setConstructionAcreage(Double.valueOf((String) intersectDict.get("JSYDMJ")));
				mapSpot2016.setArableAcreage(Double.valueOf((String) intersectDict.get("GDMJ")));
				// 遍历集合拿到key与His_IsConfirmPlanning相比
				Enumeration<String> keys = intersectDict.keys();
				// for (Hashtable<String, Object> entry : intersectDict.) {
				//// System.out.println("Key = " + entry.getKey() + ", Value = "
				// + entry.getValue());
				// }
				for (Map.Entry<String, Object> entry : ((Hashtable<String, Object>) intersectDict).entrySet()) {
					// System.out.println("Key = " + entry.getKey() + ", Value =
					// " + entry.getValue());
					String key = entry.getKey();
					if (key.contains("His_IsConfirmPlanning")) {
						mapSpot2016.setHisIsConfirmPlanning(
								Boolean.valueOf((String) intersectDict.get("His_IsConfirmPlanning")));
						Polygon tlp = (Polygon) intersectDict.get("targetIntersectLPolygon");
						SpotArcGISHelper.updateFeatures(appSettings.getMapSpotLocationService(), tlp, intersectDict,
								ObjectID);
					}
				}
			}
			saveMapSpotInfo(mapSpot2016);
			// 将进度放进缓存
			SysCache.put("progress", (int) (++i / (double) reallyList.size() * 100));
		}
	}

	/**
	 * 更新
	 * 
	 * @param spot
	 */
	private void saveMapSpotInfo(MapSpot2016 spot) {
		// session.Update(spot);
		sqlSession.update("satellitegraphservice.updateMapSpot2016", spot);
	}

	private void CopyMapSpotInfo(MapSpot2016 spot, Dictionary<String, Object> dict) {
		List<WFVerInstance> listTemp = sqlSession.selectList("satellitegraphservice.queryWFVerInstance");
		List<WFVerHistory> listTempHistorys = sqlSession.selectList("satellitegraphservice.queryWFVerInstanceHistorys");
		List<WFVerInstance> listReally = new ArrayList<>();
		List<WFVerHistory> listReallyHistorys = new ArrayList<>();
		for (WFVerInstance wfVerInstance : listTemp) {
			if (spot.getInstanceID().equals(wfVerInstance.InstanceID)) {
				listReally.add(wfVerInstance);
			}
		}
		for (WFVerHistory wfVerHistory : listTempHistorys) {
			if (spot.getInstanceID().equals(wfVerHistory.InstanceID)) {
				listReallyHistorys.add(wfVerHistory);
			}
		}
		String newInstanceID = String.valueOf(UUID64.newUUID64().getValue());
		MapSpot2016 newSpot = (MapSpot2016) spot.clone();
		newSpot.setID(Long.valueOf((String) dict.get("MID")));
		newSpot.setSpotNumber((String) dict.get("TBBH"));
		newSpot.setInstanceID(newInstanceID);
		newSpot.setSpotArea((Double.valueOf((String) dict.get("JCMJ"))));
		newSpot.setConstructionAcreage((Double.valueOf((String) dict.get("JSYDMJ"))));
		newSpot.setHisIsConfirmPlanning((Boolean.valueOf((String) dict.get("His_IsConfirmPlanning"))));
		// 保存克隆的实体到数据库
		sqlSession.insert("satellitegraphservice.saveNewSpot", newSpot);
		AuditSpot audit = newSpot.getTheAuditSpot();
		audit.setMapSpotId(newSpot.getID());
		// 保存AuditSpot到数据库
		sqlSession.insert("satellitegraphservice.saveAuditSpot", audit);
		// 保存WFVerInstance到数据库
		List<WFVerInstance> list = new ArrayList<>();
		for (WFVerInstance wfInstance : listReally) {
			WFVerInstance newWFInstance = new WFVerInstance();
			newWFInstance.FlowData = wfInstance.FlowData;
			newWFInstance.InstanceData = wfInstance.InstanceData;
			newWFInstance.InstanceID = newInstanceID;
			newWFInstance.MapID = wfInstance.MapID;
			newWFInstance.NodeID = wfInstance.NodeID;
			newWFInstance.Route = wfInstance.Route;
			newWFInstance.SequencePtr = wfInstance.SequencePtr;
			newWFInstance.State = wfInstance.State;
			list.add(newWFInstance);
		}
		sqlSession.insert("satellitegraphservice.saveNewWFInstance", list);
		// 保存WFVerHistory到数据库
		List<WFVerHistory> list2 = new ArrayList<>();
		for (WFVerHistory wfVerHistory : listReallyHistorys) {
			WFVerHistory newHis = new WFVerHistory();
			// 主键
			newHis.ID = String.valueOf(UUID64.newUUID64().getValue());
			newHis.InstanceID = newInstanceID;
			newHis.NodeID = wfVerHistory.NodeID;
			newHis.Sequence = wfVerHistory.Sequence;
			list2.add(newHis);
		}
		sqlSession.insert("satellitegraphservice.saveNewWFVerHistory", list2);
	}

	/// 图斑入库操作
	/// </summary>
	/// <param name="dr"></param>
	/// <param name="dict"></param>
	/// <param name="serviceUrl"></param>
	/// <param name="geometry"></param>
	/// <returns></returns>
	private boolean SpotImportAction(/* DataRow dr */String dr, Dictionary<String, Object> dict, String serviceUrl,
			Polygon geometry, List<Region> regions, AppSettings appSettings) {
		if (dr != null) {
			dict.put("XZQDM", "XZQDM");
			dict.put("XZQMC", "XMC");
			// dict.put("GLQDM", regions.Where(r => r.RegionName ==
			// dr["GLQHMC"].ToString()).FirstOrDefault().RegionID);
			for (Region region : regions) {
				if (region.getRegionName().equals("GLQHMC")) {
					dict.put("GLQDM", "GLQHMC");
				} else {
					dict.put("GLQDM", region.getRegionName());
				}
			}
			dict.put("GLQMC", "GLQHMC");
		}
		String geometryService = appSettings.getGeometryService();
		Polygon newPolygon = SpotArcGISHelper.simplify(geometryService, geometry);
		geometry.setRings(newPolygon.getRings());
		return SpotArcGISHelper.addFeatures(appSettings.getMapSpotLocationService(), geometry, dict);
	}

	/**
	 * 获取规划分析进度 TODO 20170420完善
	 * 
	 * @return
	 */
	public int getPlanningAnalysisProgress() {
		return (int) SysCache.get("progress");
	}

	/**
	 * 获取图斑编号
	 * 
	 * @param spotNumber
	 * @param maxBatch
	 * @return
	 */
	private String getSpotNumber(String spotNumber, int maxBatch) {
		spotNumber = spotNumber.split("-")[0];
		return spotNumber + "-" + maxBatch;
	}

	/// <summary>
	/// 与现状图层中的耕地类型("DLBM"为"01"开头的)进行叠加，求取对应图形所占耕地面积
	/// </summary>
	/// <param name="geometryUrl"></param>
	/// <param name="polygon"></param>
	/// <returns></returns>
	private double CaculateArableAcreage(String geometryUrl, Polygon polygon, AppSettings appSettings) {
		double arableAcreage = 0.0;
		String xzUrl = appSettings.getExistingMapService();
		String geoWKID = appSettings.getGeoWKID();
		String projWKID = appSettings.getProjWKID();
		String dlbm = appSettings.getDLBM();
		// string layerDefs = string.Format("{{\"0\":\"DLBM like '{0}%'\" }}",
		// dlbm);
		JSONObject result = SpotArcGISHelper.identifyAction(xzUrl, polygon, dlbm);
		JSONArray resultsA = result != null ? (JSONArray) result.get("results") : null;
		if (resultsA != null && resultsA.size() > 0) {
			Polygon targetAPolygon = null;// 求取相交目标空间信息
			MultiPolygon mpA = new MultiPolygon();
			for (int w = 0; w < resultsA.size(); w++) {
				mpA.Geometries.add((Polygon) ((JSONObject) resultsA.get(w)).parse("geometry"));
			}
			if (mpA.Geometries.size() > 1) {
				targetAPolygon = SpotArcGISHelper.unionAction(geometryUrl, mpA);
			} else {
				targetAPolygon = mpA.Geometries.get(0);
			}
			/* 存在套合，则通过intersect方法求出套合相交的部分（正常情况下，存在套合，则intersect相交结果一定不为空） */
			MultiPolygon intersectA = SpotArcGISHelper.intersectAction(geometryUrl, polygon, targetAPolygon);
			if (intersectA != null && intersectA.Geometries.size() > 0) {
				MultiPolygon projectPolygon = SpotArcGISHelper.projectAction(geometryUrl, intersectA, geoWKID,
						projWKID);
				// 当范围非常小，超出精度，面积几乎为0的时候会返回空 null
				if (projectPolygon != null) {
					ArcGISAreaAndLengthsResult areas = SpotArcGISHelper.areaAndLengthAction(geometryUrl,
							projectPolygon);
					double temp_areas = 0.0;
					if (areas.getAreas() != null) {
						for (double item : areas.getAreas()) {
							temp_areas += item;
						}
					}
					arableAcreage = areas != null ? temp_areas : 0.0;
				}
			}
		}
		return arableAcreage;
	}


}
