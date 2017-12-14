/**
 * 
 */
package phalaenopsis.satellitegraph.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.ws.rs.PathParam;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import phalaenopsis.common.entity.AppSettings;
import phalaenopsis.common.entity.Condition;
import phalaenopsis.common.entity.DataDictionaryItem;
import phalaenopsis.common.entity.OrganizationGrade;
import phalaenopsis.common.entity.Page;
import phalaenopsis.common.entity.Paging;
import phalaenopsis.common.entity.Region;
import phalaenopsis.common.entity.Attachment.Attachment;
import phalaenopsis.common.entity.Attachment.AttachmentSource;
import phalaenopsis.common.method.Basis;
import phalaenopsis.common.method.Tools.Linq;
import phalaenopsis.common.method.Tools.StrUtil;
import phalaenopsis.satellitegraph.entity.LegalProofType;
import phalaenopsis.satellitegraph.entity.MapSpot2016;
import phalaenopsis.satellitegraph.entity.MobileSpot;
import phalaenopsis.satellitegraph.entity.MobileSpotType;
import phalaenopsis.satellitegraph.entity.NodeStates;
import phalaenopsis.satellitegraph.entity.Polygon;
import phalaenopsis.satellitegraph.entity.PolygonRegion;
import phalaenopsis.satellitegraph.entity.SpotNumberSpotID;
import phalaenopsis.satellitegraph.entity.SpotSDE;
import phalaenopsis.satellitegraph.entity.SpotShapeInfo;
import phalaenopsis.workflowEngine.core.WorkflowEngineInstance;

/**
 * @author gaofengt
 *
 *         2017年4月21日下午2:20:45
 */
@Repository("satelliteGraphForMobileDao")
public class SatelliteGraphForMobileDao extends Basis {

	@Resource
	private SqlSession sqlSession;

	@Autowired
	PolygonRegion polygonRegion;

	/**
	 * 查询图斑列表
	 * 
	 * @param Page
	 * @return
	 */
//	public SatelliteListResult<MobileSpot> oilList(Page param) {
//		Paging<MobileSpot> page1 = new Paging<MobileSpot>(param.getPageNo(), 0, param.getPageSize(),
//				new ArrayList<MobileSpot>());
//		Integer backlogCount = null;
//
//		SatelliteListResult<MobileSpot> page = new SatelliteListResult<MobileSpot>();
//		page.QueryListResult = page1;
//		page.backlogCount = null;
//
//		if (param.getConditions() == null) {
//			return page;
//		}
//
//		Condition condition = (Condition) Linq.extEquals(param.getConditions(), "Key", "Type");
//		if (null == condition)
//			return page;
//
//		String type = condition.getValue();
//
//		StringBuffer querySQL = new StringBuffer(
//				" from SG_MAPSPOT s " + "left join sg_auditspot p on s.id = p.mapspotid " + "where 1 = 1 ");
//
//		List<MapSpot2016> result;
//		if (param.getPageSize() == -1) {
//			String sql = "Select s.* " + querySQL + " Order by s.County, s.ID";
//			result = sqlSession.selectList("satellitegraphservice.queryList", sql);
//		} else {
//			int startNum = (param.getPageNo() - 1) * param.getPageSize();
//			int endNum = param.getPageNo() * param.getPageSize();
//			String sql = "select * from ( select l.*,rownum as rn from ( select s.* " + querySQL
//					+ " Order by s.County, s.ID ) l " + " ) where rn between " + startNum + "and " + endNum + " ";
//			result = sqlSession.selectList("satellitegraphservice.queryList", sql);
//		}
//
//		// 已拆除
//		if (param.getTabnumber() == 4) {
//
//			// 查询条件
//			Map<String, Object> map = commonQuery(param.getConditions());
//			List<MapSpot2016> list = sqlSession.selectList("satellitegraphservice.oilList", map);
//			int total = list.size();
//			int startNum = (param.getPageNo() - 1) * param.getPageSize();
//			int endNum = param.getPageNo() * param.getPageSize();
//			String sql = "select * from ( select l.*,rownum as rn from ( select s.* " + "from SG_MAPSPOT s "
//					+ "left join sg_auditspot p on s.id = p.mapspotid " + "where 1 = 1"
//					+ " Order by s.County, s.ID ) l " + " ) where rn between " + startNum + "and " + endNum + " ";
//			// List<MapSpot2016> result =
//			// sqlSession.selectList("satellitegraphservice.queryList", sql);
//			// 遍历
//			List<MobileSpot> mobileSpots = new ArrayList<>();
//			for (MapSpot2016 mapSpot2016 : result) {
//				MobileSpot mobileSpot = new MobileSpot();
//				mobileSpot.setID(mapSpot2016.getID());
//				mobileSpot.setSpotNumber(mapSpot2016.getSpotNumber());
//				mobileSpot.setCounty(mapSpot2016.getCounty());
//				mobileSpot.setCountyName(mapSpot2016.getCountyName());
//				mobileSpot.setSpotAreaMu(mapSpot2016.getSpotArea());
//				mobileSpot.setConstructionAcreageMu(mapSpot2016.getConstructionAcreage());
//				mobileSpot.setArableAcreageMu(mapSpot2016.getArableAcreage());
//				mobileSpots.add(mobileSpot);
//			}
//			page1.PageCount = (total + param.getPageSize() - 1) / param.getPageSize();
//			page1.RecordCount = total;
//			page1.CurrentList = mobileSpots;
//
//			page.QueryListResult = page1;
//			page.backlogCount = backlogCount;
//			List<String> theTypes = new ArrayList<String>();
//			return page;
//		} else {
//			// 查询实体
//			List<MapSpot2016> mapSpot2016List = sqlSession.selectList("satellitegraphservice.getMapSpot2016");
//			// 输入查询条件
//			// commonQuery(mapSpot2016, param.getConditions(),
//			// param.getTabnumber());
//			// 总数
//			backlogCount = mapSpot2016List.size();
//			int total = sqlSession.selectOne("satellitegraphservice.queryCountForMobile",
//					"Select Count(1)  " + querySQL);
//			// List<MobileSpot> list = new ArrayList<MobileSpot>(result);
//			page1.PageCount = (total + param.getPageSize() - 1) / param.getPageSize();
//			page1.RecordCount = total;
//			// page1.CurrentList = list;
//
//			page.QueryListResult = page1;
//			page.backlogCount = backlogCount;
//			return page;
//		}
//	}

	/**
	 * @param conditions
	 * @param tabnumber
	 */
	private Map<String, Object> commonQueryCategory(MapSpot2016 mapSpot2016, List<Condition> conditions,
			int tabnumber) {
		Map<String, Object> map = new HashMap<>();
		// 合法类型，油田用地
		List<String> theTypes = new ArrayList<>();
		switch (tabnumber) {
		case 1:// 油田用地
			theTypes.add(NodeStates.ProofingLegal);
			// query = query.Where(x => theTypes.Contains(x.Node));
			// query = query.Where(x => x.LegalProofType ==
			// LegalProofType.OilfieldLand);
			for (String string : theTypes) {
				if (string.contains(mapSpot2016.getNode())) {
					String node = string;
					map.put("node", node);
				}
			}
			if (mapSpot2016.getLegalProofType() == LegalProofType.OilfieldLand) {
				map.put("LegalProofType", mapSpot2016.getLegalProofType());
			}
			break;
		case 2:// 非新增
			theTypes.add(NodeStates.ProofingNotNew);
			for (String string : theTypes) {
				if (string.contains(mapSpot2016.getNode())) {
					String node = string;
					map.put("node_notnew", node);
				}
			}
			break;
		case 3:// 新增
			theTypes.add(NodeStates.ProofingMaybeNew);
			for (String string : theTypes) {
				if (string.contains(mapSpot2016.getNode())) {
					String node = string;
					map.put("node_new", node);
				}
			}
			break;

		default:
			break;
		}

		for (Condition item : conditions) {
			if (item.getKey().contains("uncheck")) {
				String value = item.getValue();
				if (value != null && value.equals("")) {
					if (!value.contains(String.valueOf(mapSpot2016.getID()))) {
						String query = value;
						map.put("uncheck", query);
					}
				}
			}
			if (item.getKey().contains("unupload")) {
				String value = item.getValue();
				if (value != null && value.equals("")) {
					if (value.contains(String.valueOf(mapSpot2016.getID()))) {
						String query = value;
						map.put("unupload", query);
					}
				}
			}
			// 输入文本框
			if (item.getKey().contains("SearchText")) {
				String value = item.getValue();
				if (value != null && value.equals("")) {
					if (mapSpot2016.getSpotNumber().contains(value)) {
						String query = value;
						map.put("SearchText", query);
					}
				}
			}
			// 区域
			List<Integer> regions = new ArrayList<>();
			if (getCurrentUser().OrgGrade != OrganizationGrade.Province) {
				for (Region region : getCurrentUser().regions) {
					regions.add(region.getRegionID());
					for (Integer integer : regions) {
						if (integer.equals(mapSpot2016.getCity()) || integer.equals(mapSpot2016.getCounty())) {
							String query = String.valueOf(integer);
							map.put("regions", query);
						}
					}
				}
			}
		}
		return map;
	}

//	private Map<String, Object> commonQuery(List<Condition> conditions) {
//		Map<String, Object> map = new HashMap<String, Object>();
//		// StringBuilder queryBuilder = new StringBuilder();
//		List<Integer> regions = new ArrayList<Integer>();
//		for (Condition condition : conditions) {
//			String key = condition.getKey();
//			String value = condition.getValue();
//			if (value.equals("")) {
//				continue;
//			}
//			// 关键字查询
//			if (key.contains("SearchText")) {
//				// queryBuilder.append(" and s.SpotNumber like
//				// '%").append(condition.getValue()).append("%'");
//				String searchText = condition.getValue();
//				map.put("searchText", searchText);
//			}
//			// 行政区域
//			if (key.contains("Region")) {
//				regions.add(Integer.parseInt(value));
//				map.put("regions", regions);
//			}
//			// uncheck
//			if (key.contains("uncheck")) {
//				// queryBuilder.append(" and s.LegalProofType =
//				// ").append(value);
//				String uncheck = condition.getValue();
//				map.put("uncheck", uncheck);
//			}
//			// unupload
//			if (key.contains("unupload")) {
//				String unupload = condition.getValue();
//				map.put("unupload", unupload);
//			}
//		}
//		// 行政区域查询
//		if (regions.size() == 0) {
//			User user = getCurrentUser();
//			if (user.OrgGrade != OrganizationGrade.Province) {
//				regions = Linq.extSelect(Arrays.asList(user.regions), "RegionID");
//			}
//		}
//		if (regions.size() > 0) {
//			String regionList = StrUtil.join(",", regions);
//			// queryBuilder.append(" and (s.City in (").append(regionList +
//			// ")").append(" or s.County in (")
//			// .append(regionList + "))");
//			map.put("regionList", regionList);
//		}
//
//		return map;
//	}

	/**
	 * 查询图斑基本信息
	 */
	public MapSpot2016 getSpotBaseInfoByid(long id) {
		MapSpot2016 spot = new MapSpot2016();
		if (id == 0) {
			return spot;
		}
		MapSpot2016 mapSpot2016 = sqlSession.selectOne("satellitegraphservice.getMapSpot2016FromId", id);
		return mapSpot2016;
	}

	/**
	 * 上传图斑数据
	 */

	public boolean reportMapSpot(Long spotID) {
		if (spotID == 0) {
			return false;
		}
		MapSpot2016 mapSpot2016 = null;
		// 保存图斑
		mapSpot2016 = sqlSession.selectOne("satellitegraphservice.getMapSpot2016FromId", spotID);
		WorkflowEngineInstance instance = new WorkflowEngineInstance();
		mapSpot2016.WFObject = instance.getCurrent(mapSpot2016.getInstanceID());
		//mapSpot2016.WFObject.ServerData = mapSpot2016;
		return instance.next(mapSpot2016.WFObject) != null;
	}

	/**
	 * 查询各个列表数组总数
	 */

	public List<Integer> getTabTotal() {
		List<Integer> list = new ArrayList<>();
		List<Condition> conditions = new ArrayList<>();
		List<MapSpot2016> mapSpot2016list = sqlSession.selectList("satellitegraphservice.getMapSpot2016");
		// Map<String, Object> map = commonQueryCategory(mapSpot2016list,
		// conditions, 1);
		// commonQueryCategory(mapSpot2016list, conditions, 1);
		int count1 = 0;
		int count2 = 0;
		int count3 = 0;
		int count4 = 0;
		for (MapSpot2016 mapSpot2016 : mapSpot2016list) {
			count1 += commonQueryCategory(mapSpot2016, conditions, 1).size();
			count2 += commonQueryCategory(mapSpot2016, conditions, 2).size();
			count3 += commonQueryCategory(mapSpot2016, conditions, 3).size();
		}
		// 已拆除
		StringBuilder sb = new StringBuilder();
		sb.append(
				"select t1.* from SG_MAPSPOT t1 left join sr_spotrelation t2 on t1.id=t2.spotid left join sr_landreport t3 on t2.reportid=t3.id where t3.yichaichu=1 and t3.istemporarystorage=0");
		// 区域
		List<Integer> regions = new ArrayList();
		if (getCurrentUser().OrgGrade != OrganizationGrade.Province) {
			regions = GetCurrentUserRegionIDs();
			String sql = "('" + StrUtil.join(",", regions) + "')";
			sb.append(" and t1.county in " + sql + " or t1.city in " + sql + " or t1.province in " + sql);
		}
		List<MapSpot2016> result = sqlSession.selectList("satellitegraphservice.getTabTotal", sb.toString());
		count4 = result.size();
		list.add(count1);
		list.add(count2);
		list.add(count3);
		list.add(count4);
		return list;
	}

	/**
	 * @return
	 */
	private List<Integer> GetCurrentUserRegionIDs() {
		List<Integer> list = new ArrayList<>();
		for (Region item : getCurrentUser().regions) {
			list.add(item.getRegionID());
		}
		return list;
	}

	/**
	 * 查询地图范围内图斑shape信息
	 */

	public List<SpotShapeInfo> getSpotShapeByBounds(String bounds) {
		// 范围--多边形
		Polygon sourcePolygon = getPolygonByBounds(bounds);
		if (sourcePolygon == null) {
			return null;
		}
		// 查询地图获取shape
		List<SpotShapeInfo> spotShapeInfos = GetSpotShapeInfoByPolygon(sourcePolygon);
		if (spotShapeInfos == null || spotShapeInfos.size() == 0) {
			return null;
		}
		// 过滤出权限内有的 //查询 当前使用人组织机构包含的 && 图斑状态是核查中 && id 在ids 内 的所有图斑
		List<Long> ids = new ArrayList<>();
		for (SpotShapeInfo spotShapeInfo : spotShapeInfos) {
			ids.add(spotShapeInfo.getSpotID());
		}
		List<MapSpot2016> mapSpotList = getMapSpot2016ByShapeInfoIds(ids);
		if (mapSpotList.size() == 0) {
			return null;
		}
		// 移动端图斑类型赋值
		List<Long> newids = new ArrayList<>();
		for (MapSpot2016 item : mapSpotList) {
			newids.add(item.getID());
		}
		List<SpotShapeInfo> spots = new ArrayList<>();
		for (Long item : newids) {
			for (SpotShapeInfo info : spotShapeInfos) {
				if (item == info.SpotID) {
					spots.add(info);
				}
			}
		}
		List<SpotShapeInfo> result = new ArrayList<>();
		for (MapSpot2016 mapSpot2016 : mapSpotList) {
			for (SpotShapeInfo spotShapeInfo : spots) {
				if (mapSpot2016.getID() == spotShapeInfo.SpotID) {
					spotShapeInfo.SpotType = getSpotType(mapSpot2016.getNode());
					result.add(spotShapeInfo);
				}
			}
		}

		return result;
	}

	/**
	 * @param ids
	 */
	private List<MapSpot2016> getMapSpot2016ByShapeInfoIds(List<Long> ids) {
		List<Integer> regions = GetCurrentUserRegionIDs();
		StringBuilder sb = new StringBuilder();
		sb.append("select t1.* from SG_MAPSPOT t1");
		String node1 = "('" + NodeStates.ProofingLegal + "')";
		String node2 = "('" + NodeStates.ProofingMaybeNew + "')";
		String node3 = "('" + NodeStates.ProofingNotNew + "')";
		String sql = "('" + StrUtil.join(",", regions) + "')";
		sb.append(" where t1.county in" + sql + " or t1.city in " + sql + ")");
		sb.append("and" + "(" + "t1.node in " + node1 + " or t1.node in " + node2 + " or t1.node in " + node3 + ")");
		// mapSpot =
		// sqlSession.selectOne("satellitegraphservice.getSpotBySpotID",
		// sb.toString());
		for (Map.Entry<String, Object> entry : getCurrentUser().getSession().entrySet()) {
			if (!entry.getKey().contains("UserSpotsCache")) {
				List<MapSpot2016> list = sqlSession.selectList("satellitegraphservice.getMapSpot2016ByShapeInfoIds",
						sb.toString());
				StringBuilder s = new StringBuilder();
				s.append(
						"select t1.*from SG_MAPSPOT t1 left join sr_spotrelation t2 on t1.id=t2.spotid left join sr_landreport t3 on t2.reportid=t3.id where t3.yichaichu=1 and t3.istemporarystorage=0 ");
				s.append(
						" and t1.county in (" + sql + ") or t1.city in (" + sql + ") or t1.province in (" + sql + ") ");
				List<MapSpot2016> result = sqlSession.selectList("satellitegraphservice.queryDemolition", s.toString());
				list.addAll(result);
				// SortedDictionary<Long, MapSpot2016> sets = new
				// SortedDictionary<Long, MapSpot2016>();
				Map<Long, MapSpot2016> sets = new HashMap<Long, MapSpot2016>();
				for (MapSpot2016 mapSpot2016 : list) {
					sets.put(mapSpot2016.getID(), mapSpot2016);
				}
				Map<String, Object> map = new HashMap<>();
				map.put("UserSpotsCache", sets);
				getCurrentUser().setSession(map);

			}
		}
		Map<Long, MapSpot2016> userSpots = (Map<Long, MapSpot2016>) getCurrentUser().getSession().get("UserSpotsCache");
		List<MapSpot2016> currentSpots = new ArrayList<MapSpot2016>();
		for (Long item : ids) {
			for (Map.Entry<Long, MapSpot2016> entry : userSpots.entrySet()) {
				if (entry.getKey() == item) {
					currentSpots.add(entry.getValue());
				}
			}
		}
		return currentSpots;
	}

	/**
	 * @param sourcePolygon
	 */
	private List<SpotShapeInfo> GetSpotShapeInfoByPolygon(Polygon sourcePolygon) {
		List<SpotSDE> spotSDEs = polygonRegion.query(sourcePolygon);
		if (spotSDEs == null || spotSDEs.size() == 0) {
			return null;
		}
		List<SpotShapeInfo> spotShapeInfos = new ArrayList<>();
		for (SpotSDE item : spotSDEs) {
			SpotShapeInfo info = new SpotShapeInfo();
			info.SpotID = item.MID;
			info.Shape = item.Shape;
			spotShapeInfos.add(info);
		}
		return spotShapeInfos;
	}

	/**
	 * @param bounds
	 * @desc 多边形
	 */
	private Polygon getPolygonByBounds(String bounds) {
		if (bounds == null || bounds.equals("")) {
			return null;
		}
		ArrayList<Double[]> points = new ArrayList<>();
		String[] pointsArray = bounds.split(",");
		for (int i = 0; i < pointsArray.length; i += 2) {
			// points.add(new Double[2])
			Double[] d = new Double[2];
			d[0] = Double.parseDouble(pointsArray[i]);
			d[1] = Double.parseDouble(pointsArray[i + 1]);
			points.add(d);
		}
		Polygon sourcePolygon = new Polygon();
		sourcePolygon.rings.add(points);
		return sourcePolygon;
	}

	/**
	 * 查询图斑号
	 */
	public List<SpotNumberSpotID> getSpotNumbersByKey(String spotNumber) {
		// 查询当前人组织机构包含的&&核查中&&图斑号包含spotNumber 的图斑的id 和 图斑node
		List<Integer> regions = GetCurrentUserRegionIDs();
		String reStr = "('" + StrUtil.join(",", regions) + "')";
		String node1 = "('" + NodeStates.ProofingLegal + "')";
		String node2 = "('" + NodeStates.ProofingMaybeNew + "')";
		String node3 = "('" + NodeStates.ProofingNotNew + "')";
		StringBuilder sb = new StringBuilder();
		sb.append("select * from SG_MAPSPOT t1 where")
				.append("(" + "t1.county in " + reStr + " or t1.city in " + reStr + " or t1.province in " + reStr + ")")
				.append("and t1.spotnumber=" + spotNumber).append("and" + "(" + "t1.node in " + node1
						+ " or t1.node in " + node2 + " or t1.node in " + node3 + ")");
		List<MapSpot2016> mapSpot2016s = sqlSession.selectList("satellitegraphservice.querySpotNumbers", sb.toString());
		List<SpotNumberSpotID> result = new ArrayList<>();
		for (MapSpot2016 mapSpot2016 : mapSpot2016s) {
			SpotNumberSpotID id = new SpotNumberSpotID();
			id.setSpotID(mapSpot2016.getID());
			id.setSpotNumber(mapSpot2016.getSpotNumber());
			result.add(id);
		}
		// 已拆除
		result.addAll(getDemolitionSpotNumber(spotNumber));
		return result;
	}

	/**
	 * @param spotNumber
	 */
	private List<SpotNumberSpotID> getDemolitionSpotNumber(String spotNumber) {
		List<SpotNumberSpotID> result = new ArrayList<>();
		StringBuilder sb = new StringBuilder();
		sb.append(
				"select t1.*from SG_MAPSPOT t1 left join sr_spotrelation t2 on t1.id=t2.spotid left join sr_landreport t3 on t2.reportid=t3.id where t3.yichaichu=1 and t3.istemporarystorage=0  and instr(t1.SpotNumber,'"
						+ spotNumber + "')>0 ");
		// 区域
		List<Integer> regions = new ArrayList<>();
		if (getCurrentUser().OrgGrade != OrganizationGrade.Province) {
			regions = GetCurrentUserRegionIDs();
			String sql = "('" + StrUtil.join(",", regions) + "')";
			sb.append(" and t1.county in " + sql + " or t1.city in " + sql + " or t1.province in " + sql);
		}
		List<MapSpot2016> mapSpot2016s = sqlSession.selectList("satellitegraphservice.getDemolitionSpotNumber",
				sb.toString());
		for (MapSpot2016 mapSpot2016 : mapSpot2016s) {
			SpotNumberSpotID id = new SpotNumberSpotID();
			id.setSpotID(mapSpot2016.getID());
			id.setSpotNumber(mapSpot2016.getSpotNumber());
			result.add(id);
		}
		return result;
	}

	/**
	 * 获取单个图斑by图斑号
	 */
	public SpotShapeInfo getSpotShapeInfoBySpotID(@PathParam("spotID") long spotID) {
		//// 查询mid 为id 的图形shape信息 和 图斑类型
		List<Integer> regions = GetCurrentUserRegionIDs();
		MapSpot2016 mapSpot;
		String node1 = "('" + NodeStates.ProofingLegal + "')";
		String node2 = "('" + NodeStates.ProofingMaybeNew + "')";
		String node3 = "('" + NodeStates.ProofingNotNew + "')";
		String sql = "('" + StrUtil.join(",", regions) + "')";
		StringBuilder sb = new StringBuilder();
		sb.append("select t1.* from SG_MAPSPOT t1");
		sb.append(" where id = " + spotID + " and " + "(" + "t1.county in" + sql + " or t1.city in " + sql
				+ " or t1.province in " + sql + ")");
		sb.append("and" + "(" + "t1.node in " + node1 + " or t1.node in " + node2 + " or t1.node in " + node3 + ")");
		mapSpot = sqlSession.selectOne("satellitegraphservice.getSpotBySpotID", sb.toString());
		if (mapSpot == null) {
			mapSpot = getDemolition(spotID).get(0);
		}
		if (mapSpot == null) {
			return null;
		}
		SpotSDE spotSDE = polygonRegion.single(mapSpot.getID());
		if (spotSDE == null) {
			return null;
		}
		SpotShapeInfo spotShapeInfo = new SpotShapeInfo();
		spotShapeInfo.Shape = spotSDE.getShape();
		spotShapeInfo.SpotID = mapSpot.getID();
		spotShapeInfo.SpotType = getSpotType(mapSpot.getNode());
		return spotShapeInfo;
	}

	/**
	 * @param node
	 * @return
	 * @desc 获取图斑类型
	 */
	private MobileSpotType getSpotType(String node) {
		switch (node) {
		case NodeStates.ProofingLegal:
			return MobileSpotType.Oil;
		case NodeStates.ProofingMaybeNew:
			return MobileSpotType.New;
		case NodeStates.ProofingNotNew:
			return MobileSpotType.NotNew;
		default:
			return MobileSpotType.Demolition;
		}
	}

	/**
	 * @param spotID
	 * @return
	 */
	private List<MapSpot2016> getDemolition(long spotID) {
		StringBuilder sb = new StringBuilder();
		sb.append(
				"select t1.* from SG_MAPSPOT t1 left join sr_spotrelation t2 on t1.id=t2.spotid left join sr_landreport t3 on t2.reportid=t3.id where t3.yichaichu=1 and t3.istemporarystorage=0 and t1.ID='"
						+ spotID + "' ");
		// 区域
		List<Integer> regions = new ArrayList<>();
		if (getCurrentUser().OrgGrade != OrganizationGrade.Province) {
			regions = GetCurrentUserRegionIDs();
			String sql = "('" + StrUtil.join(",", regions) + "')";
			sb.append(" and t1.county in " + sql + " or t1.city in " + sql + " or t1.province in " + sql);
		}
		List<MapSpot2016> result = sqlSession.selectList("satellitegraphservice.getDemolition", sb.toString());
		return result;
	}

	/**
	 * 检查是否成功连接后台
	 */
	public boolean checkConnection() {
		return true;
	}

	/**
	 * 获取拍照的距离限制
	 */
	public String getMinDistance() {
		AppSettings appSettings = new AppSettings();
		appSettings.getMinDistance();
		return null;
	}

	/**
	 * 保存图斑
	 */
	public boolean saveMapSpot(MapSpot2016 mapSpot) {
		if (mapSpot == null || mapSpot.getID() == 0) {
			return false;
		}
		StringBuilder sb = new StringBuilder();
		sb.append("select * from SG_MAPSPOT where id=" + mapSpot.getID());
		MapSpot2016 map = sqlSession.selectOne("satellitegraphservice.saveMapSpotForMobile", sb.toString());
		map.setNotNewSpotClassification(mapSpot.getNotNewSpotClassification());
		map.setNotNewRemarks(mapSpot.getNotNewRemarks());
		sb = new StringBuilder();
		sb.append("update SG_MAPSPOT set NotNew_SpotClassification=" + map.getNotNewSpotClassification()
				+ " and NotNew_Remarks = " + map.getNotNewRemarks() + "where id=" + map.getID());
		sqlSession.update("satellitegraphservice.updateMapSpot", sb.toString());
		return true;
	}

	/**
	 * 获取批准机关级别字典数据
	 */
	public List<DataDictionaryItem> getAllDictionaries() {
		StringBuilder sb = new StringBuilder();
		List<DataDictionaryItem> list = new ArrayList<>();
		sb.append("select * from ss_datadictionary t where t.type='PiZhunJiGuanJiBie' order by t.value asc");
		list = sqlSession.selectList("satellitegraphservice.getAllDictionaries", sb.toString());
		return list;
	}

	/**
	 * 获取附件
	 */
	public Attachment getAttachment(String attID) {
		StringBuilder sb = new StringBuilder();
		sb.append("select * from ss_attachment t where t.id=" + attID);
		Attachment att = sqlSession.selectOne("satellitegraphservice.getAttachment", sb.toString());
		return att;
	}

	/**
	 * 查询图斑流程
	 */
	public boolean nodeCanUpload(long spotID, int spottype) {
		MapSpot2016 spot = new MapSpot2016();
		if (spotID == 0 || spottype == 0) {
			return false;
		}
		StringBuilder sb = new StringBuilder();
		sb.append("select * from SG_MAPSPOT where id=" + spotID);
		spot = sqlSession.selectOne("satellitegraphservice.nodeCanUpload", sb.toString());
		if (spot == null) {
			return false;
		}
		switch (spottype) {
		case 1:
			if (spot.getNode() != NodeStates.ProofingLegal) {
				return false;
			}
			break;
		case 2:
			if (spot.getNode() != NodeStates.ProofingNotNew) {
				return false;
			}
			break;
		case 3:
			if (spot.getNode() != NodeStates.ProofingMaybeNew) {
				return false;
			}
			break;
		default:
			break;
		}
		return true;
	}

	/**
	 * 20170410先判断是否有内网同步到外网的照片，如果有就允许上传
	 */
	public boolean haveSynPhoto(long spotID) {
		StringBuilder sb = new StringBuilder();
		sb.append("select * from ss_attachment t where t.bizid=" + String.valueOf(spotID) + " and t.source="
				+ AttachmentSource.Client);
		Attachment att = sqlSession.selectOne("satellitegraphservice.getAttachment", sb.toString());
		return att != null;
	}

	public Paging<MobileSpot> OilList(Page param){
         //注意： 搜索的时候用管理区     移动端显示的时候用行政区
         Paging<MobileSpot> page = new Paging<>(param.getPageNo(), 0, param.getPageSize(), new ArrayList<MobileSpot>());
         Integer backlogCount = 0;
         List<Condition> conditions = param.getConditions();
         StringBuffer querySQL = new StringBuffer(
 				" from SG_MAPSPOT s " + "left join sg_auditspot p on s.id = p.mapspotid " + "where 1 = 1 ");
         StringBuffer queryCountSQL = new StringBuffer(" select count(1)  from SG_MAPSPOT s "
 				+ "left join sg_auditspot p on s.id = p.mapspotid " + "where 1 = 1 ");
         if (conditions == null)
             return page;
             //已拆除
             if (param.getTabnumber() == 4){
                 StringBuilder sb = new StringBuilder();
                 sb.append("select t1.* from SG_MAPSPOT t1 left join sr_spotrelation t2 on t1.id=t2.spotid left join sr_landreport t3 on t2.reportid=t3.id where t3.yichaichu=1 and t3.istemporarystorage=0 ");
                 //输入框文本
                 String SearchText = "";
//                 if (conditions.ContainsKey("SearchText"))
//                 {
//                     SearchText = string.IsNullOrEmpty(conditions["SearchText"])?"0":conditions["SearchText"];
//                     sb.Append(" and instr(t1.SpotNumber," + SearchText + ")>0 ");
//                 }
                 Condition condition = (Condition) Linq.extEquals(param.getConditions(), "Key", "SearchText");
                 if(condition!=null){
                	 if(condition.getValue()!=null && !condition.getValue().equals("")){
                		 SearchText =  condition.getValue();
                		 sb.append(" and instr(t1.SpotNumber," + SearchText + ")>0 ");
                	 }
                 }
                 Condition condition2 = (Condition) Linq.extEquals(param.getConditions(), "Key", "uncheck");
                 if(condition2!=null){
                	 if(condition2.getValue()!=null && !condition2.getValue().equals("")){
                		 String ids=  condition2.getValue();
                		 sb.append(" and instr(" + ids + ",t1.ID)=0 ");
                	 }
                 }
                 //unupload
                 Condition condition3 = (Condition) Linq.extEquals(param.getConditions(), "Key", "unupload");
                 if(condition3!=null){
                	 if(condition3.getValue()!=null && !condition3.getValue().equals("")){
                		 String ids=  condition3.getValue();
                		 sb.append(" and instr(" + ids + ",t1.ID)>0 ");
                	 }
                 }
                 //区域
                 List<Integer> regions =GetCurrentUserRegionIDs();
                 if (getCurrentUser().OrgGrade != OrganizationGrade.Province){
             			regions = GetCurrentUserRegionIDs();
             			String sql = "('" + StrUtil.join(",", regions) + "')";
             			sb.append(" and t1.county in " + sql + " or t1.city in " + sql + " or t1.province in " + sql);
                 }
                 List<MapSpot2016> temp = sqlSession.selectList("satellitegraphservice.OilList", sb.toString());
                 int total = temp.size();
                 backlogCount = total;
                 List<MobileSpot> list = new ArrayList<MobileSpot>();
                 for (MapSpot2016 mapSpot2016 : temp) {
                	 MobileSpot mobilespot = new MobileSpot();
                	 mobilespot.setID(mapSpot2016.getID());
                	 mobilespot.setSpotNumber(mapSpot2016.getSpotNumber());
                   mobilespot.setCounty(mapSpot2016.getDistrictCounty());
                   mobilespot.setCountyName(mapSpot2016.getCountyName());
                   mobilespot.setSpotAreaMu(mapSpot2016.getSpotAreaMu());
                   mobilespot.setConstructionAcreageMu(mapSpot2016.getConstructionAcreageMu());
                   mobilespot.setArableAcreageMu(mapSpot2016.getArableAcreageMu());
                   list.add(mobilespot);
				}
                 page.PageCount = (total + param.getPageSize() - 1) / param.getPageSize();
                 page.RecordCount = total;
                 page.CurrentList = list;
                 return page;
             }else {
//                 var query = session.Query<MapSpot2016>();
//                 var queryCount = session.Query<MapSpot2016>();
                 //输入的查询条件
                 String querySql = commonQuery(param.getTabnumber(), conditions);
//                 backlogCount = CommonQuery(queryCount, conditions, tabnumber);
                List<MapSpot2016>  query = sqlSession.selectList("satellitegraphservice.queryCountForMobile", querySql.toString());
                //总数
                backlogCount = sqlSession.selectOne("satellitegraphservice.backlogCount", queryCountSQL.toString());
                 //分页
                 int total = query.size();
                 List<MapSpot2016> result = new ArrayList<>();
                 int startNum = (param.getPageNo() - 1) * param.getPageSize();
     			int endNum = param.getPageNo() * param.getPageSize();
    			String sql = "select * from ( select l.*,rownum as rn from ( select s.* " + querySQL
    					+ " Order by s.County, s.ID ) l " + " ) where rn between " + startNum + "and " + endNum + " ";
    			result = sqlSession.selectList("satellitegraphservice.queryListForMobile",sql);
                 List<MobileSpot> list = new ArrayList();
//                 result.ForEach(x =>
//                 {
//                     MobileSpot mobilespot = new MobileSpot();
//                     mobilespot.ID = x.ID;
//                     mobilespot.SpotNumber = x.SpotNumber;
//                     mobilespot.County = x.DistrictCounty;
//                     mobilespot.CountyName = x.CountyName;
//                     mobilespot.SpotAreaMu = x.SpotAreaMu;
//                     mobilespot.ConstructionAcreageMu = x.ConstructionAcreageMu;
//                     mobilespot.ArableAcreageMu = x.ArableAcreageMu;
//                     list.Add(mobilespot);
//                 });
                for (MapSpot2016 mapSpot2016 : result) {
                	MobileSpot mobilespot = new MobileSpot();
                	mobilespot.setID(mapSpot2016.getID());
                	mobilespot.setSpotNumber(mapSpot2016.getSpotNumber());
                	mobilespot.setCounty(mapSpot2016.getDistrictCounty());
                  mobilespot.setCountyName(mapSpot2016.getCountyName());
                  mobilespot.setSpotAreaMu(mapSpot2016.getSpotAreaMu());
                  mobilespot.setConstructionAcreageMu(mapSpot2016.getConstructionAcreageMu());
                  mobilespot.setArableAcreageMu(mapSpot2016.getArableAcreageMu());
                  list.add(mobilespot);
				}
                 page.PageCount = (total + param.getPageSize() - 1) / param.getPageSize();
                 page.RecordCount = total;
                 page.CurrentList = list;
                 return page;
             }
         }

	/**
	 * 通用类查询条件
	 * 
	 * @param year
	 * @param conditions
	 * @return
	 */
	private String commonQuery(int tabnumber, List<Condition> conditions) {
		// 合法类型,油田用地
		List<String> theTypes = new ArrayList<>();
		StringBuilder sb = new StringBuilder();
		switch (tabnumber) {
		case 1:// 油田用地
			theTypes.add(NodeStates.ProofingLegal);
			// query = query.Where(x => theTypes.Contains(x.Node));
			// query = query.Where(x => x.LegalProofType ==
			// LegalProofType.OilfieldLand);
			for (String string : theTypes) {
				sb.append(" and s.Node = ").append(string);
			}
			sb.append(" and s.LegalProofType = ").append(LegalProofType.OilfieldLand);
			break;
		case 2:// 非新增
			theTypes.add(NodeStates.ProofingNotNew);
			// query = query.Where(x => theTypes.Contains(x.Node));
			for (String string : theTypes) {
				sb.append(" and s.Node = ").append(string);
			}
			break;
		case 3:// 新增
			theTypes.add(NodeStates.ProofingMaybeNew);
			// query = query.Where(x => theTypes.Contains(x.Node));
			for (String string : theTypes) {
				sb.append(" and s.Node = ").append(string);
			}
			break;
		// case 4://已拆除
		// theTypes.Add("nodata");
		// query = query.Where(x => theTypes.Contains(x.Node));
		// break;
		default:
			break;
		}
		for (Condition condition : conditions) {
			String key = condition.getKey();
			String value = condition.getValue();
			if (value.equals("")) {
				continue;
			}
			// uncheck查询
			if (key.contains("uncheck")) {
				// queryBuilder.append(" and s.SpotNumber like
				// '%").append(condition.getValue()).append("%'");
				String value_uncheck = condition.getValue();
				if (value_uncheck != null && !value_uncheck.equals("")) {
					sb.append(" and s.ID = ").append(value_uncheck);
				}
			}
			// unupload查询
			if (key.contains("unupload")) {
				String value_unupload = condition.getValue();
				sb.append(" and s.ID = ").append(value_unupload);
			}
			// 关键字查询
			if (key.contains("SearchText")) {
				sb.append(" and s.SpotNumber like '%").append(condition.getValue()).append("%'");
			}
			// // 行政区域
			// if (key.contains("Region")) {
			// regions.add(Integer.parseInt(value));
			// }
		}
		// 行政区域查询
		// if (regions.size() == 0) {
		// User user = getCurrentUser();
		// if (user.OrgGrade != OrganizationGrade.Province) {
		// regions = Linq.extSelect(Arrays.asList(user.regions), "RegionID");
		// }
		// }
		// if (regions.size() > 0) {
		// String regionList = StrUtil.join(",", regions);
		// queryBuilder.append(" and (s.City in (").append(regionList +
		// ")").append(" or s.County in (")
		// .append(regionList + "))");
		// }
		// 区域
		if (getCurrentUser().OrgGrade != OrganizationGrade.Province) {
			List<Integer> regions = GetCurrentUserRegionIDs();
			String regionList = StrUtil.join(",", regions);
			sb.append(" and (s.City in (").append(regionList + ")").append(" or s.County in (")
					.append(regionList + "))");
		}

		return sb.toString();
	}
}
