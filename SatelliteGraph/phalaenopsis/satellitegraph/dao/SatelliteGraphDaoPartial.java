package phalaenopsis.satellitegraph.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import phalaenopsis.common.dao.IWorkFlowDao;
import phalaenopsis.common.entity.AppSettings;
import phalaenopsis.common.entity.Condition;
import phalaenopsis.common.entity.OrganizationGrade;
import phalaenopsis.common.entity.Page;
import phalaenopsis.common.entity.Paging;
import phalaenopsis.common.entity.User;
import phalaenopsis.common.method.Basis;
import phalaenopsis.common.method.BinarySerializer;
import phalaenopsis.common.method.Tools.Linq;
import phalaenopsis.common.method.Tools.StrUtil;
import phalaenopsis.common.method.Tools.UUID64;
import phalaenopsis.satellitegraph.entity.ApproveState;
import phalaenopsis.satellitegraph.entity.AuditSpot;
import phalaenopsis.satellitegraph.entity.IMapSpot;
import phalaenopsis.satellitegraph.entity.MapSpot;
import phalaenopsis.satellitegraph.entity.MapSpot2016;
import phalaenopsis.satellitegraph.entity.MapSpotBackup;
import phalaenopsis.satellitegraph.entity.MapSpotRelation;
import phalaenopsis.satellitegraph.entity.MapSpotShadow;
import phalaenopsis.satellitegraph.entity.NodeStates;
import phalaenopsis.satellitegraph.entity.OperationLog;
import phalaenopsis.satellitegraph.entity.OperationType;
import phalaenopsis.satellitegraph.entity.SatelliteListResult;
import phalaenopsis.satellitegraph.utils.ExtensionMethods;
import phalaenopsis.satellitegraph.utils.IMapSpotOperationExecutor;
import phalaenopsis.satellitegraph.utils.MapSpotOperator;
import phalaenopsis.satellitegraph.utils.ArcGISHelper.SpotArcGISHelper;
import phalaenopsis.workflowEngine.storage.WFHistory;
import phalaenopsis.workflowEngine.storage.WFInstance;

@Repository("satelliteGraphDaoPartial")
public class SatelliteGraphDaoPartial extends Basis implements IMapSpotOperationExecutor {

	@Resource
	private SqlSession sqlSession;

	@Autowired
	private IWorkFlowDao workflowDao;

	@Autowired
	private AppSettings appSetting;

	public boolean getSpotStatus(long id) {
		String sql = "select count(1) from sg_spotshadow where mapspotid=" + id;
		int i = sqlSession.selectOne("satellitegraphservice.getSpotStatus", sql);
		return i > 0;
	}

	public List<MapSpot2016> getMapSpot2016sByConditions(Map<String, Object> map) {
		List<MapSpot2016> list = sqlSession.selectList("satellitegraphservice.getMapSpot2016sByConditions", map);
		for (MapSpot2016 mapSpot2016 : list) {
			AuditSpot theAuditSpot = sqlSession.selectOne("satellitegraphservice.queryAuditSpot", mapSpot2016.getID());
			mapSpot2016.setTheAuditSpot(theAuditSpot);
		}
		return list;
	}

	public int insertMapSpot2016(MapSpot2016 spot) {
		return sqlSession.insert("satellitegraphservice.insertMapSpot2016", spot);
	}

	public int deleteMapSpot2016s(List<Long> deleteIDList) {
		return sqlSession.delete("satellitegraphservice.deleteMapSpot2016s", deleteIDList);
	}

	public MapSpot2016 selectOneMapSpot2016(long id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("year", new AppSettings().getReportYear());
		map.put("id", id);
		MapSpot2016 mapSpot2016 = sqlSession.selectOne("satellitegraphservice.selectMapSpot2016", map);
		AuditSpot theAuditSpot = sqlSession.selectOne("satellitegraphservice.queryAuditSpot", id);
		mapSpot2016.setTheAuditSpot(theAuditSpot);
//		return sqlSession.selectOne("satellitegraphservice.selectOneMapSpot2016", id);
		return mapSpot2016;
	}

	public int queryCount(String sql) {
		return sqlSession.selectOne("satellitegraphservice.queryCount", sql);
	}

	public List<MapSpot2016> queryList(String sql) {
		return sqlSession.selectList("satellitegraphservice.queryList", sql);
	}

	public OperationLog getOperationLog(Map<String, Object> map) {
		return sqlSession.selectOne("satellitegraphservice.getOperationLog", map);
	}

	public int insertMapSpotRelation(MapSpotRelation relation) {
		return sqlSession.insert("satellitegraphservice.insertMapSpotRelation", relation);
	}

	public int insertMapSpotShadow(MapSpotShadow shadow) {
		return sqlSession.insert("satellitegraphservice.insertMapSpotShadow", shadow);
	}

	public List<MapSpotRelation> getMapSpotRelation(MapSpotShadow shadow) {
		return sqlSession.selectList("satellitegraphservice.getMapSpotRelations", shadow);
	}

	public int insertMapSpotBackup(MapSpotBackup backup) {
		return sqlSession.insert("satellitegraphservice.insertMapSpotBackup", backup);
	}

	public MapSpotShadow getSpotShadowByShadowID(Long id) {
		return sqlSession.selectOne("satellitegraphservice.getSpotShadowByShadowID", id);
	}

	public int updateMapSpotShadow(MapSpotShadow shadow) {
		return sqlSession.update("satellitegraphservice.updateMapSpotShadow", shadow);
	}

	public List<MapSpotBackup> selectMapSpotBackup(MapSpotShadow shadow) {
		return sqlSession.selectList("satellitegraphservice.selectMapSpotBackup", shadow);
	}

	public MapSpotShadow getShadow(IMapSpot spot) {
		return sqlSession.selectOne("satellitegraphservice.getSpotShadowBySport", spot);
	}

	public int insertOperationLog(OperationLog log) {
		return sqlSession.insert("satellitegraphservice.insertOperationLog", log);
	}

	public boolean restore(MapSpot2016 spot) {
		MapSpotOperator op = new MapSpotOperator(this);
		boolean result = op.restoreAll(spot);
		return result;
	}

	/**
	 * 图斑分宗
	 * 
	 * @param source
	 *            原始图斑
	 * @param spots
	 *            分宗后的图斑
	 * @return
	 */
	public boolean segment(MapSpot2016 source, List<MapSpot2016> spots) {
		MapSpotOperator operator = new MapSpotOperator(this);
		boolean result = operator.segment(source, new ArrayList<IMapSpot>(spots));
		return result;
	}

	public boolean merge(List<MapSpot2016> sourceSpots, MapSpot2016 newSpot) {
		MapSpotOperator op = new MapSpotOperator(this);
		boolean result = op.merge(new ArrayList<IMapSpot>(sourceSpots), newSpot);
		return result;
	}

	@Override
	@Transactional
	public boolean onAddAndDelete(List<IMapSpot> addSpots, List<Long> deleteIDList) {
		boolean result = false;
		MapSpot2016 spot2016 = selectOneMapSpot2016(deleteIDList.get(0)); // sqlSession.selectOne("satellitegraphservice.selectOneMapSpot2016",
																			// deleteIDList.get(0));

		for (IMapSpot item : addSpots) {
			MapSpot2016 spot = (MapSpot2016) item;
			spot.setID(UUID64.newUUID64().getValue());
			spot.setInstanceID(saveInstances(spot2016.getInstanceID()));
			spot.setSpotKind(spot2016.getSpotKind());

			insertMapSpot2016(spot);

			Dictionary<String, Object> dict = new Hashtable<String, Object>();
			dict.put("TBBH", spot.getSpotNumber());
			dict.put("XZQDM", spot.getDistrictCounty());
			dict.put("XZQMC", spot.getDistrictCountyName());
			dict.put("GLQDM", spot.getCounty());
			dict.put("GLQMC", spot.getCountyName());
			dict.put("MID", spot.getID());
			dict.put("JCMJ", spot.getSpotArea());
			dict.put("JSYDMJ", spot.getConstructionAcreage());
			dict.put("GDMJ", spot.getArableAcreage());
			result = SpotArcGISHelper.addFeatures(new AppSettings().getMapSpotLocationService(), spot.getShape(), dict);
		}
		 result = SpotArcGISHelper.deleteFeatures(new AppSettings().getMapSpotLocationService(), deleteIDList);
		 deleteMapSpot2016s(deleteIDList);
		
		// sqlSession.delete("satellitegraphservice.deleteMapSpot2016s",
		// deleteIDList);

		return result;
	}

	@Transactional
	private String saveInstances(String instanceID) {
		String newInstanceID = UUID.randomUUID().toString();
		WFInstance instance = workflowDao.getInstance(instanceID);
		List<WFHistory> histories = workflowDao.getHistories(instanceID);
		WFInstance wf = BinarySerializer.Clone(instance);
		wf.setInstanceID(newInstanceID);
	    workflowDao.saveOrUpdateWFInstance(wf);
		List<WFHistory> list = BinarySerializer.Clone(histories);
		Iterator<WFHistory> iterators = list.iterator();
		while (iterators.hasNext()) {
			WFHistory wfh = BinarySerializer.Clone(iterators.next());
			wfh.setInstanceID(newInstanceID);
			wfh.setID(UUID.randomUUID().toString());
			workflowDao.saveWFHistory(wfh);
		}
		return newInstanceID;
	}

	@SuppressWarnings("rawtypes")
	public SatelliteListResult queryList(Page param) {

		Paging<MapSpot> page1 = new Paging<MapSpot>(param.getPageNo(), 0, param.getPageSize(),
				new ArrayList<MapSpot>());
		Integer backlogCount = 0;

		SatelliteListResult<MapSpot> page = new SatelliteListResult<MapSpot>();
		page.QueryListResult = page1;
		page.backlogCount = null;

		if (param.getYear() == 0 || param.getConditions() == null) {
			return page;
		}

		Condition condition = (Condition) Linq.extEquals(param.getConditions(), "Key", "Type");
		if (null == condition)
			return page;

		String type = condition.getValue();

		// 查询条件
		String commonQuery = commonQuery(param.getYear(), param.getConditions());

		List<String> theTypes = new ArrayList<String>();
		// 是否立案
		condition = Linq.extEquals(param.getConditions(), "Key", "IsBuildCase");
		Boolean isBuildCase = null == condition ? null : Boolean.parseBoolean(condition.getValue());

		User currentUser = (User) getCurrentUser();
		// User currentUser = new User();
		// currentUser.setOrgGrade(OrganizationGrade.County);

		StringBuffer querySQL = new StringBuffer(
				" from SG_MAPSPOT s " + "left join sg_auditspot p on s.id = p.mapspotid " + "where 1 = 1 ");

		StringBuffer queryCountSQL = new StringBuffer(" select count(1)  from SG_MAPSPOT s "
				+ "left join sg_auditspot p on s.id = p.mapspotid " + "where 1 = 1 ");

		if (!type.equals("All")) {

			switch (currentUser.OrgGrade) {
			case OrganizationGrade.County:

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
					if (isBuildCase != null && true == isBuildCase) {
						querySQL.append(" and s.node = '" + NodeStates.ReallyIllegal + "' ");
						querySQL.append(
								"and (p.CityAuditIsPass is null or p.ProvinceAuditIsPass =" + ApproveState.IllegalPass
										+ "  or p.ProvinceAuditIsPass =" + ApproveState.IllegalNotPass + ") ");
					} else if (isBuildCase != null && false == isBuildCase) {
						querySQL.append(" and s.node = '" + NodeStates.ReallyIllegal + "' and p.ProvinceAuditIsPass = "
								+ ApproveState.IllegalNotCase + " ");
					} else {
						theTypes.add(NodeStates.ReallyIllegal);
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

				List<String> backlogStatus = new ArrayList<String>();
				backlogStatus.add(NodeStates.DoubtIllegal);
				backlogStatus.add(NodeStates.DoubtNotNew);

				String typeStr = "('" + StrUtil.join("','", backlogStatus) + "') ";
				if (type.indexOf("待办") > -1) {
					queryCountSQL.append("and  s.node in " + typeStr + " ");
				} else if (type.indexOf("审批未通过") > -1) {
					queryCountSQL.append(
							"and  (s.node = in " + typeStr + " and p.CityAuditIsPass = " + ApproveState.NoPass + " ");
				}
				break;

			case OrganizationGrade.City:
				switch (type) {
				case "待办-合法图斑举证":
					theTypes.add(NodeStates.ProofLegalCity);
					querySQL.append(" and p.IsCityAudit is null ");
					break;
				case "待办-非新增建设用地图斑举证":
					theTypes.add(NodeStates.ProofNotNewCity);
					querySQL.append(" and p.IsCityAudit is null ");
					break;
				case "待办-新增建设用地图斑举证":
					theTypes.add(NodeStates.ProofMaybeNewCity);
					querySQL.append(" and p.IsCityAudit is null ");
					break;
				case "已办理-合法图斑举证":
					theTypes.add(NodeStates.ProofLegalCity);
					querySQL.append(" and p.IsCityAudit = 1 ");
					break;
				case "已办理-非新增建设用地图斑举证":
					theTypes.add(NodeStates.ProofNotNewCity);
					querySQL.append(" and p.IsCityAudit = 1 ");
					break;
				case "已办理-新增建设用地图斑举证":
					theTypes.add(NodeStates.ProofMaybeNewCity);
					querySQL.append(" and p.IsCityAudit = 1 ");
					break;
				case "合法图斑":
					theTypes.add(NodeStates.LegalSpot);
					break;
				case "违法图斑":
					if (isBuildCase != null && true == isBuildCase) {
						querySQL.append(" and s.node = '" + NodeStates.ReallyIllegal
								+ "' and (p.CityAuditIsPass is null or p.ProvinceAuditIsPass = "
								+ ApproveState.IllegalPass + " or p.ProvinceAuditIsPass = "
								+ ApproveState.IllegalNotPass + " )");
					} else if (isBuildCase != null && false == isBuildCase) {
						querySQL.append(" and s.node = '" + NodeStates.ReallyIllegal + "' and p.ProvinceAuditIsPass = "
								+ ApproveState.IllegalNotCase + " ");
					} else {
						theTypes.add(NodeStates.ReallyIllegal);
					}
					break;
				case "非新增建设用地":
					theTypes.add(NodeStates.NotNewSpot);
					break;
				default:
					return page;
				}

				List<String> proofCityStatus = new ArrayList<String>();
                proofCityStatus.add(NodeStates.ProofLegalCity);
                proofCityStatus.add(NodeStates.ProofNotNewCity);
                proofCityStatus.add(NodeStates.ProofMaybeNewCity);
                typeStr = "('" + StrUtil.join("','", proofCityStatus) + "')";
				if (type.indexOf("待办") > -1) {
					queryCountSQL.append(" and s.Node  in " + typeStr + " and p.IsCityAudit is null ");
				} else if (type.indexOf("已办理") > -1) {
					queryCountSQL.append(" and s.Node in " + typeStr + " and p.IsCityAudit = 1 ");
				}
				break;

			case OrganizationGrade.Province:
				switch (type) {
				case "待办-合法图斑举证":
					querySQL.append(
							" and s.node= '" + NodeStates.ProofLegalProvince + "' and p.ProvinceAuditIsPass is null ");
					break;
				case "待办-非新增建设用地图斑举证":
					querySQL.append(
							" and s.node= '" + NodeStates.ProofNotNewProvince + "' and p.ProvinceAuditIsPass is null ");
					break;
				case "待办-新增建设用地图斑举证":
					querySQL.append(" and s.node= '" + NodeStates.ProofMaybeNewProvince
							+ "' and p.ProvinceAuditIsPass is null ");
					break;
				case "合法图斑":
					querySQL.append("  and s.node = '" + NodeStates.LegalSpot + "'  or  (s.node = '"
							+ NodeStates.ProofLegalProvince + "' and p.ProvinceAuditIsPass = " + ApproveState.IegalPass
							+ ") or  " + "(s.node = '" + NodeStates.ProofMaybeNewProvince
							+ "'  and  p.ProvinceAuditIsPass =" + ApproveState.IegalPass + ") ");
					break;
				case "违法图斑":
					if (isBuildCase != null && true == isBuildCase) {
						querySQL.append(" ( and s.Node = '" + NodeStates.ReallyIllegal
								+ "' and p.CityAuditIsPass is null) or " + " ((s.Node = '" + NodeStates.ReallyIllegal
								+ "' or s.Node = '" + NodeStates.ProofLegalProvince + "' or s.node = '"
								+ NodeStates.ProofMaybeNewProvince + "') and " + " (p.ProvinceAuditIsPass = "
								+ ApproveState.IllegalNotPass + " or p.ProvinceAuditIsPass = "
								+ ApproveState.IllegalPass + ")) or " + "(x.Node ='" + NodeStates.ProofNotNewProvince
								+ "' and p.ProvinceAuditIsPass = " + ApproveState.IllegalNotPass + ") ");
					} else if (isBuildCase != null && false == isBuildCase) {
						querySQL.append(" ( and s.node = '" + NodeStates.ReallyIllegal + "' or s.node = '"
								+ NodeStates.ProofLegalProvince + "' or s.node = '" + NodeStates.ProofMaybeNewProvince
								+ "') and p.ProvinceAuditIsPass = " + ApproveState.IllegalNotCase + " ");
					} else {
						List<Integer> states = new ArrayList<Integer>();
						states.add(ApproveState.IllegalNotPass);
						states.add(ApproveState.IllegalNotCase);
						states.add(ApproveState.IllegalPass);
						typeStr = "(" + StrUtil.join(",", states) + ")";
						querySQL.append(" and  s.node = '" + NodeStates.ReallyIllegal + "' or ( (s.node = '"
								+ NodeStates.ProofLegalProvince + "' or s.node = '" + NodeStates.ProofMaybeNewProvince
								+ "')" + " and p.ProvinceAuditIsPass in " + typeStr + ")  or  ( s.node = '"
								+ NodeStates.ProofNotNewProvince + "' and p.ProvinceAuditIsPass = "
								+ ApproveState.IllegalNotPass + ") ");
					}
					break;
				case "非新增建设用地":
					querySQL.append("  and s.node = '" + NodeStates.NotNewSpot + "' or (s.node = '"
							+ NodeStates.ProofMaybeNewProvince + "'  and  p.ProvinceAuditIsPass = "
							+ ApproveState.NotNewSpotNotPass + ") " + "or (s.node = '" + NodeStates.ProofNotNewProvince
							+ "' and p.ProvinceAuditIsPass = " + ApproveState.NotNewSpotPass + ") ");
					break;
				default:
					return page;
				}

				List<String> proofProvinceStatus = new ArrayList<String>();
				proofProvinceStatus.add(NodeStates.ProofLegalProvince);
				proofProvinceStatus.add(NodeStates.ProofNotNewProvince);
				proofProvinceStatus.add(NodeStates.ProofMaybeNewProvince);
				typeStr = "('" + StrUtil.join(",", proofProvinceStatus) + "')";
				if (type.indexOf("待办") > -1) {
					queryCountSQL.append(" and s.node in " + typeStr + "  and p.ProvinceAuditIsPass is null");
				}
				break;

			default:
				break;
			}
		}

		if (currentUser.getOrgGrade() != OrganizationGrade.Province && theTypes.size() > 0) {
			String typeStr = "('" + StrUtil.join("','", theTypes) + "')";
			querySQL.append(" and s. node in " + typeStr);
		}

		if (type.contains("待办") || type.contains("已办理") || type.contains("审批未通过")) {
			if (!commonQuery.equals("")) {
				queryCountSQL.append(commonQuery);
			}
			backlogCount = queryCount(queryCountSQL.toString()); // sqlSession.selectOne("satellitegraphservice.queryCount",
																	// queryCountSQL.toString());
		}
		if (!commonQuery.equals("")) {
			querySQL.append(commonQuery);
		}
		int total = queryCount("Select Count(1)  " + querySQL); // sqlSession.selectOne("satellitegraphservice.queryCount",
																// "Select
																// Count(1) " +
																// querySQL);

		List<MapSpot2016> result;
		if (param.getPageSize() == -1) {
			String sql = "Select s.* " + querySQL + " Order by s.County, s.ID";
			result = queryList(sql); // sqlSession.selectList("satellitegraphservice.queryList",
										// sql);
		} else {
			int startNum = (param.getPageNo() - 1) * param.getPageSize();
			int endNum = param.getPageNo() * param.getPageSize();
			String sql = "select * from ( select l.*,rownum as rn from ( select s.* " + querySQL
					+ " Order by s.County, s.ID ) l " + " ) where rn between " + startNum + "and " + endNum + " ";
			result = queryList(sql); // sqlSession.selectList("satellitegraphservice.queryList",
										// sql);
		}

		// 状态展示
		switch (currentUser.getOrgGrade()) {
		case OrganizationGrade.County:
			// 待处理
			List<String> nodes1 = new ArrayList<String>();
			nodes1.add(NodeStates.DoubtIllegal);
			nodes1.add(NodeStates.DoubtNotNew);
			// 举证中
			List<String> nodes2 = new ArrayList<String>();
			nodes2.add(NodeStates.ProofingLegal);
			nodes2.add(NodeStates.ProofingNotNew);
			nodes2.add(NodeStates.ProofingMaybeNew);
			// 已举证
			List<String> nodes3 = new ArrayList<String>();
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
			// 暂时假设：每个组织机构只负责一个区域
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("RegionID", currentUser.regions[0].getRegionID());
			// map.put("RegionID", 370882);
			map.put("Year", param.getYear());
			OperationLog log = getOperationLog(map); // sqlSession.selectOne("satellitegraphservice.getOperationLog",
														// map);

			Iterator<MapSpot2016> iterator = result.iterator();
			while (iterator.hasNext()) {
				MapSpot2016 mapSpot2016 = iterator.next();
				mapSpot2016.setTheAuditSpot(getAuditSpot(mapSpot2016.getID()));
				if (type.contains("疑似新增建设用地-疑似违法图斑") || type.contains("疑似非新增建设用地-疑似非新增建设用地")) {
					String status = nodes1.contains(mapSpot2016.getNode()) ? "待处理"
							: nodes2.contains(mapSpot2016.getNode()) ? "举证中"
									: nodes3.contains(mapSpot2016.getNode()) ? "已举证" : null;
					mapSpot2016.setSpotHandlingStatus(status);
				}

				if (null != log && log.getType() == OperationType.Back && mapSpot2016.getTheAuditSpot() != null) {
					switch (mapSpot2016.getTheAuditSpot().getCityAuditIsPass()) {
					case ApproveState.Pass:
						mapSpot2016.getTheAuditSpot().setAuditStatus("市级通过");
						break;
					case ApproveState.NoPass:
						mapSpot2016.getTheAuditSpot().setAuditStatus("市级不通过");
						break;
					default:
						break;
					}
				}
			}
			break;
		case OrganizationGrade.City:
			Iterator<MapSpot2016> iterator2 = result.iterator();
			while (iterator2.hasNext()) {
				MapSpot2016 mapSpot2016 = iterator2.next();
				mapSpot2016.setTheAuditSpot(getAuditSpot(mapSpot2016.getID()));
				if (mapSpot2016.getTheAuditSpot() != null && true == mapSpot2016.getTheAuditSpot().isCityAudit()
						&& mapSpot2016.getTheAuditSpot().getCityAuditIsPass() != null) {
					switch (mapSpot2016.getTheAuditSpot().getCityAuditIsPass()) {
					case ApproveState.Pass:
						mapSpot2016.getTheAuditSpot().setAuditStatus("通过");
						break;
					case ApproveState.NoPass:
						mapSpot2016.getTheAuditSpot().setAuditStatus("不通过");
					default:
						break;
					}
				}
			}
			break;

		case OrganizationGrade.Province:
			Iterator<MapSpot2016> iterator3 = result.iterator();
			while (iterator3.hasNext()) {
				MapSpot2016 mapSpot2016 = iterator3.next();
				mapSpot2016.setTheAuditSpot(getAuditSpot(mapSpot2016.getID()));
				if (null != mapSpot2016.getTheAuditSpot()
						&& mapSpot2016.getTheAuditSpot().getProvinceAuditIsPass() != null) {
					switch (mapSpot2016.getTheAuditSpot().getProvinceAuditIsPass()) {
					case ApproveState.IegalPass:
					case ApproveState.IllegalPass:
					case ApproveState.NotNewSpotPass:
						mapSpot2016.getTheAuditSpot().setAuditStatus("通过");
						break;
					case ApproveState.IllegalNotPass:
					case ApproveState.IllegalNotCase:
					case ApproveState.NotNewSpotNotPass:
						mapSpot2016.getTheAuditSpot().setAuditStatus("不通过");
					default:
						break;
					}
				}

			}
			break;

		default:
			break;
		}

		List<MapSpot> list = new ArrayList<MapSpot>(result);
		page1.PageCount = (total + param.getPageSize() - 1) / param.getPageSize();
		page1.RecordCount = total;
		page1.CurrentList = list;

		page.QueryListResult = page1;
		page.backlogCount = backlogCount;
		return page;
	}

	@Autowired
	private AppSettings appSettings;

	/**
	 * 卫片填报列表查询
	 * 
	 * @param param
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map satelliteReportQueryList(Page param) {
		Map map = new HashMap<String, Object>();
		Integer backlogCount = 0;
		Paging<MapSpot> page = new Paging<MapSpot>();
		map.put("backlogCount", backlogCount);
		map.put("satelliteReportQueryListResult", page);
		if (param.getYear() == 0 || param.getConditions() == null) {
			return map;
		}
		StringBuffer querySQL = new StringBuffer(" from sg_mapspot s ");
		// 违法图斑，去掉已填报数据
		querySQL.append(" where s.Node =" + "'" + NodeStates.ReallyIllegal + "'");
		querySQL.append(" and (s.isfillreport is null or s.isfillreport = 0)");
		String commonQuery = commonQuery(param.getYear(), param.getConditions());
		querySQL.append(commonQuery);

		// int total =
		// sqlSession.selectOne("satellitegraphservice.satelliteReportQueryCount",
		// "Select Count(1)" + querySQL.toString());
		int total = queryCount("Select Count(1)" + querySQL.toString());
		backlogCount = total;
		// 分页
		int startNum = (param.getPageNo() - 1) * param.getPageSize();
		int endNum = param.getPageNo() * param.getPageSize();
		querySQL.append(" Order By s.County ,s.ID ");
		String sql = "select * from ( select s.*" + ",rownum as rn " + querySQL + " ) where rn between " + startNum
				+ "and " + endNum + " ";

		List<MapSpot2016> result = queryList(sql); // sqlSession.selectList("satellitegraphservice.satelliteReportQueryList",
													// sql);

		List<MapSpot> list = new ArrayList<MapSpot>(result);
		page.PageCount = (total + param.getPageSize() - 1) / param.getPageSize();
		page.RecordCount = total;
		page.CurrentList = list;

		return map;
	}

	/**
	 * 通用类查询条件
	 * 
	 * @param year
	 * @param conditions
	 * @return
	 */
	private String commonQuery(int year, List<Condition> conditions) {
		StringBuilder queryBuilder = new StringBuilder();
		// 年份查询
		if (year != 0) {
			queryBuilder.append(" and s.Year = ").append(year);
		}
		List<Integer> regions = new ArrayList<Integer>();
		for (Condition condition : conditions) {
			String key = condition.getKey();
			String value = condition.getValue();
			if (value.equals("")) {
				continue;
			}
			// 关键字查询
			if (key.contains("SearchText")) {
				queryBuilder.append(" and s.SpotNumber like '%").append(condition.getValue()).append("%'");
			}
			// 行政区域
			if (key.contains("Region")) {
				regions.add(Integer.parseInt(value));
			}
			// 合法举证类型
			if (key.contains("LegalProofType")) {
				queryBuilder.append(" and s.LegalProofType = ").append(value);
			}
		}
		// 行政区域查询
		if (regions.size() == 0) {
			User user = getCurrentUser();
			if (user.OrgGrade != OrganizationGrade.Province) {
				regions = Linq.extSelect(Arrays.asList(user.regions), "RegionID");
			}
		}
		if (regions.size() > 0) {
			String regionList = StrUtil.join(",", regions);
			queryBuilder.append(" and (s.City in (").append(regionList + ")").append(" or s.County in (")
					.append(regionList + "))");
		}

		return queryBuilder.toString();
	}

//	/**
//	 * 测试使用，勿删
//	 */
//	
//	public void test() {
//
////		//Map<String, Object> map = new HashMap<String, Object>();
////		Test test = new Test();
////		test.setId(StatusEnum.SUCCESS);
////		//sqlSession.selectOne("satellitegraphservice.testEnumInsert", test);
////		
////		Test test1 = sqlSession.selectOne("satellitegraphservice.testEnumSelect");
////		
////		Object bObject = test1;
//	}

	/**
	 *  获取审核信息
	 * @param id
	 * @return
	 */
	private AuditSpot getAuditSpot(long id) {
		AuditSpot theAuditSpot = sqlSession.selectOne("satellitegraphservice.queryAuditSpot", id);
		return theAuditSpot;
	}

	/**
	 * 图斑还原删除
	 */
	@Transactional
	public void  restoreDelete(List<MapSpotShadow> deletingList) {
		for (MapSpotShadow mapSpotShadow : deletingList) {
			sqlSession.delete("satellitegraphservice.deleteSpotRelation", mapSpotShadow);
			sqlSession.delete("satellitegraphservice.deleteSpotBackup", mapSpotShadow);
			sqlSession.delete("satellitegraphservice.deleteMapSpotShadow", mapSpotShadow);
		}
	}

}
