package phalaenopsis.satellitegraph.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import phalaenopsis.common.dao.DataDictionaryDao;
import phalaenopsis.common.entity.AppSettings;
import phalaenopsis.common.entity.OrganizationGrade;
import phalaenopsis.common.entity.Page;
import phalaenopsis.common.entity.Paging;
import phalaenopsis.common.entity.Region;
import phalaenopsis.common.entity.User;
import phalaenopsis.common.method.Basis;
import phalaenopsis.satellitegraph.dao.SatelliteGraphDao;
import phalaenopsis.satellitegraph.entity.ApproveState;
import phalaenopsis.satellitegraph.entity.AuditSpot;
import phalaenopsis.satellitegraph.entity.LegalProofType;
import phalaenopsis.satellitegraph.entity.LowerTreeLeaf;
import phalaenopsis.satellitegraph.entity.LowerTreeNode;
import phalaenopsis.satellitegraph.entity.MapSpot;
import phalaenopsis.satellitegraph.entity.MapSpot2016;
import phalaenopsis.satellitegraph.entity.NodeStates;
import phalaenopsis.satellitegraph.entity.OperationLog;
import phalaenopsis.satellitegraph.entity.OperationType;
import phalaenopsis.satellitegraph.entity.ReportDetail;
import phalaenopsis.satellitegraph.entity.StaticItem;

@Service("satelliteGraphService")
public class SatelliteGraphService extends Basis {

	@Autowired
	private DataDictionaryDao daoDicDao;

	@Autowired
	private SatelliteGraphDao sateGraphDao;

	public List<Integer> getYears() {

		return sateGraphDao.getYears();
	}

	public List<StaticItem> getRemoteSpotCount(int year, String regiontype) {
		User currentUser = getCurrentUser();

		Set<Integer> set = new HashSet<Integer>();
		for (int i = 0; i < currentUser.regions.length; i++) {
			set.add(currentUser.regions[i].RegionID);
		}

		Integer[] regionIds = set.toArray(new Integer[set.size()]);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("year", year);
		map.put("OrgGrade", currentUser.OrgGrade);
		map.put("regiontype", regiontype);
		map.put("cityRegion", currentUser.regions[0].ParentID);
		map.put("regions", regionIds);

		if (currentUser.OrgGrade == 3) {
			map.put("Code", "City");
		} else if (currentUser.OrgGrade == 2) {
			if (regiontype == "管理区") {
				map.put("Code", "County");
			} else {
				map.put("Code", "DistrictCounty");
			}
		} else if (currentUser.OrgGrade == 1) {
			map.put("Code", "County");
		}

		List<StaticItem> staticItems = sateGraphDao.getRemoteSpotCount(map);

		return staticItems;
	}

	public List<StaticItem> getIllegalSpotCount(int year, String regiontype) {
		User user = getCurrentUser();

		Set<Integer> set = new HashSet<Integer>();
		for (int i = 0; i < user.regions.length; i++) {
			set.add(user.regions[i].RegionID);
		}

		Integer[] regionIds = set.toArray(new Integer[set.size()]);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("year", year);
		map.put("OrgGrade", user.OrgGrade);
		map.put("regiontype", regiontype);
		map.put("cityRegion", user.getRegions()[0].ParentID);
		map.put("regions", regionIds);
		map.put("node", NodeStates.ReallyIllegal);

		if (user.OrgGrade == 3) {
			map.put("Code", "City");
		} else if (user.OrgGrade == 2) {
			if (regiontype == "管理区") {
				map.put("Code", "County");
			} else {
				map.put("Code", "DistrictCounty");
			}
		} else if (user.OrgGrade == 1) {
			map.put("Code", "County");
		}

		List<StaticItem> staticItems = sateGraphDao.getIllegalSpotCount(map);

		return staticItems;
	}

	public List<StaticItem> getIllegalSpotPercent(int year, String regiontype) {
		User currentUser = getCurrentUser();

		Set<Integer> set = new HashSet<Integer>();
		Set<Integer> set1 = new HashSet<Integer>();
		Set<Region> currentRegions = new HashSet<Region>();

		for (int i = 0; i < currentUser.regions.length; i++) {
			set.add(currentUser.regions[i].RegionID);
			set1.add(currentUser.regions[i].ParentID);
			currentRegions.add(currentUser.regions[i]);
		}

		Integer[] regionIds = set.toArray(new Integer[set.size()]); // new
																	// int[currentUser.regions.length];
		Integer[] parentids = set1.toArray(new Integer[] { set1.size() }); // new
																			// int[currentUser.regions.length];

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("year", year);
		map.put("node", NodeStates.ReallyIllegal);
		map.put("OrgGrade", currentUser.OrgGrade);
		map.put("regiontype", regiontype);
		map.put("cityRegion", currentUser.regions[0].ParentID);
		map.put("parentids", parentids);
		map.put("regionIds", regionIds);
		if (currentUser.OrgGrade == 3) {
			map.put("Code", "City");
		} else if (currentUser.OrgGrade == 2) {
			if (regiontype == "管理区") {
				map.put("Code", "County");
			} else {
				map.put("Code", "DistrictCounty");
			}
		} else if (currentUser.OrgGrade == 1) {
			map.put("Code", "County");
		}

		List<StaticItem> queryResult = sateGraphDao.getIllegalSpotPercent(map);

		List<Region> allRegion = daoDicDao.getAllRegions();

		List<StaticItem> result = new ArrayList<StaticItem>();
		if (currentUser.OrgGrade == 3) {
			for (Integer str : set1) {
				for (Region region : allRegion) {
					if (region.RegionID == str) {
						StaticItem item = new StaticItem();
						item.Code = String.valueOf(region.RegionID);
						item.Key = region.RegionName;

						for (StaticItem staticItem : queryResult) {
							if (staticItem.Key.equals(item.Key)) {
								item.Value = staticItem.Value;
							}
						}
						result.add(item);
					}
				}
			}
		} else if (currentUser.OrgGrade == 2) {
			int cityRegion = currentUser.regions[0].ParentID;
			if (regiontype == "管理区") {

				for (Region region : allRegion) {
					if (region.ParentID == cityRegion && (region.regionType == 1 || region.regionType == 2)) {
						StaticItem item = new StaticItem();
						item.Code = String.valueOf(region.RegionID);
						item.Key = region.RegionName;

						for (StaticItem staticItem : queryResult) {
							if (staticItem.Key.equals(item.Key)) {
								item.Value = staticItem.Value;
							}
						}
						result.add(item);
					}
				}
			} else {

				for (Region region : allRegion) {
					if (region.ParentID == cityRegion && (region.regionType == 0 || region.regionType == 2)) {
						StaticItem item = new StaticItem();
						item.Code = String.valueOf(region.RegionID);
						item.Key = region.RegionName;

						for (StaticItem staticItem : queryResult) {
							if (staticItem.Key.equals(item.Key)) {
								item.Value = staticItem.Value;
							}
						}
						result.add(item);
					}
				}
			}
		} else if (currentUser.OrgGrade == 1) {
			for (Region region : currentRegions) {
				StaticItem item = new StaticItem();
				item.Code = String.valueOf(region.RegionID);
				item.Key = region.RegionName;

				for (StaticItem staticItem : queryResult) {
					if (staticItem.Key.equals(item.Key)) {
						item.Value = staticItem.Value;
					}
				}
				result.add(item);
			}
		}
		return result;
	}

	public List<StaticItem> getBuildSpotCount(int year, String regiontype) {
		User user = getCurrentUser();

		Map<String, Object> map1 = new HashMap<String, Object>();
		Map<String, Object> map2 = new HashMap<String, Object>();
		map1.put("OrgGrade", user.OrgGrade);
		map2.put("OrgGrade", user.OrgGrade);
		map1.put("Node", NodeStates.LegalSpot);
		map2.put("Node", NodeStates.NotNewSpot);
		map1.put("cityRegion", user.regions[0].ParentID);
		map2.put("cityRegion", user.regions[0].ParentID);
		map1.put("year", year);
		map2.put("year", year);

		Set<Integer> set = new HashSet<Integer>();
		for (int i = 0; i < user.regions.length; i++) {
			set.add(user.regions[i].RegionID);
		}

		Integer[] regions = set.toArray(new Integer[set.size()]);
		map1.put("regions", regions);
		map2.put("regions", regions);

		List<StaticItem> result = new ArrayList<StaticItem>();
		int count = sateGraphDao.getBuildSpotCount(map1);
		int count1 = sateGraphDao.getBuildSpotCount(map2);

		StaticItem item = new StaticItem();
		item.Key = "新增建设用地";
		item.Value = count;
		StaticItem item1 = new StaticItem();
		item1.Key = "非新增建设用地";
		item1.Value = count1;

		result.add(item);
		result.add(item1);

		return result;
	}

	// TODO 查询图斑列表
	public Paging<MapSpot> queryList(Page page) {
		return sateGraphDao.queryList(page);
	}

	// TODO 获取图斑详细信息
	public MapSpot getMapSpot(int year, long id) {
		return sateGraphDao.selectMapSpot2016(year, id);
	}

	// TODO 保存图斑
	public boolean saveMapSpot(MapSpot mapSpot) {
		return sateGraphDao.saveMapSpot(mapSpot);
	}
	// TODO 保存图斑
	public boolean saveMapSpot2016(MapSpot2016 mapSpot) {
		return sateGraphDao.saveMapSpot2016(mapSpot);
	}

	// TODO 判断图斑号是否存在
	public List<String> isSpotExists(int year, int regionID, List<String> nums) {

		return sateGraphDao.isSpotExists(year, regionID, nums);
	}


	// 是否已经进行过规划分析
	public boolean isPlanningAnalysis(int City) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("City", City);
		map.put("LegalProofType", LegalProofType.History);
		return sateGraphDao.isPlanningAnalysis(map);
	}

	// 获取回退原因
	public OperationLog getSendBackReason(int regionID) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("Region", regionID);
		map.put("Year", new AppSettings().getReportYear());
		User user = getCurrentUser();
		if (user.OrgGrade == OrganizationGrade.County) {
			OperationLog log = sateGraphDao.getSendBackReason(map);
			if (log != null && log.getType().equals(OperationType.Back))
				return log;
		}
		return null;
	}

	// 是否可以上报
	public String canReport() {
		OperationLog log = null;
		String year = new AppSettings().getReportYear();
		User user = getCurrentUser();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("year", year);
		List<Integer> regions = new ArrayList<Integer>();
		for (Region region : user.regions) {
			regions.add(region.RegionID);
		}
		switch (user.OrgGrade) {
		case OrganizationGrade.County:
			// 查询区县级上报信息，已经上报过了，不允许上报
			map.put("regions", regions);
			log = sateGraphDao.queryReportedCounty(map);
			break;
		case OrganizationGrade.City:
			// 判断市级审核是否通过
			List<String> nodeStates = new ArrayList<String>();
			nodeStates.add(NodeStates.ProofLegalCity);
			nodeStates.add(NodeStates.ProofNotNewCity);
			nodeStates.add(NodeStates.ProofMaybeNewCity);
			map.put("regions", regions);
			map.put("nodeStates", nodeStates);
			map.put("approveState", ApproveState.NoPass);
			int count = sateGraphDao.queryRefuseReported(map);
			if (count > 0) {
				// 市级审核不通过，不允许上报
				return "false";
			}
			// 查询市级上报信息， 已经上报过了，不允许上报
			map = new HashMap<String, Object>();
			map.put("year", year);
			map.put("region", user.regions[0].ParentID);
			log = sateGraphDao.queryReportedCity(map);
			break;
		default:
			return "false";
		}
		return log == null ? "none"
				: log.getType().equals(OperationType.Report )|| log.getType().equals(OperationType.AuditComplete) ? "reported" : "true";

	}

	// 查询上报明细
	public List<ReportDetail> reportDetails() {
		String year = new AppSettings().getReportYear();
		User user = getCurrentUser();

		Boolean isImportMapSpot = sateGraphDao.isImportMapSpot(year);
		List<ReportDetail> list = null;
		Map<String, Object> map = new HashMap<String, Object>();
		switch (user.OrgGrade) {
		case OrganizationGrade.County:
			map.put("year", year);
			map.put("organizationID", user.organizationID);
			list = sateGraphDao.queryReportDetailCounty(map);
			for (int i = 0; i < list.size(); i++) {
				ReportDetail p = list.get(i);
				if (p.Total == 0) {
					p.Percent = 100;
				} else {
					p.Percent = 100 - (int) (p.Amount / (double) p.Total * 100);
				}
				if (!isImportMapSpot)
					p.Percent = 0;
				p.IsReport = true;
			}
			break;
		case OrganizationGrade.City:
			map.put("year", year);
			map.put("organizationID", user.organizationID);
			list = sateGraphDao.queryReportDetailCity(map);
			for (int i = 0; i < list.size(); i++) {
				ReportDetail p = list.get(i);
				if (p.Type != null && p.Type.equals(OperationType.Report)) {
					p.IsReport = true;
				}
				if (p.IsReport) {
					p.Percent = p.Total == 0 ? 100 : 100 - (int) (p.Amount / (double) p.Total * 100);
				}
			}
			break;
		case OrganizationGrade.Province:
			list = sateGraphDao.queryReportDetailProvince(year);
			for (int i = 0; i < list.size(); i++) {
				ReportDetail p = list.get(i);
				if (p.Type != null
						&& (p.Type.equals(OperationType.Report) || p.Type.equals(OperationType.AuditComplete))) {
					p.IsReport = true;
				}
				if (p.IsReport) {
					p.Percent = p.Total == 0 ? 100 : 100 - (int) (p.Amount / (double) p.Total * 100);
				}
			}
			break;
		}
		return list;
	}

	// 上报明细 & 是否可以上报
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map reportDetailsAndCanReport() {
		String canReport = "";
		Boolean isSendBack = false;
		List<ReportDetail> list = new ArrayList<ReportDetail>();
		Map map = new HashMap<String, Object>();
		list = reportDetails();
		User user = getCurrentUser();

		List<ReportDetail> reportedList = new ArrayList<ReportDetail>();
		List<ReportDetail> unreportedList = new ArrayList<ReportDetail>();
		for (ReportDetail reportDetail : list) {
			if (reportDetail.IsReport) {
				reportedList.add(reportDetail);
			} else {
				unreportedList.add(reportDetail);
			}
		}
		canReport = canReport();
		OperationLog log = getSendBackReason(user.regions[0].RegionID);
		isSendBack = log != null;
		if (canReport.equals("none") || canReport.equals("true")) {
			canReport = "true";
			switch (user.OrgGrade) {
			case OrganizationGrade.County:
				// 全部处理完才能上报
				for (ReportDetail item : reportedList) {
					if (item.Percent < 100) {
						canReport = "false";
						break;
					}
				}
				break;
			case OrganizationGrade.City:
				// 区县是否都已上报
				if (unreportedList.size() > 0) {
					canReport = "false";
					break;
				}
				// 是否都已审核完毕
				for (ReportDetail item : reportedList) {
					if (item.Percent < 100) {
						canReport = "false";
						break;
					}
				}
				break;
			default:
				canReport = "false";
			}
		}
		map.put("list", list);
		map.put("canReport", canReport);
		map.put("isSendBack", isSendBack);
		return map;
	}

	// 保存市级审核信息
	public boolean saveCityAuditInfo(AuditSpot auditSpot) {
		return sateGraphDao.saveCityAuditInfo(auditSpot);
	}

	// 保存省级审核信息
	public boolean saveProvinceAuditInfo(AuditSpot auditSpot) {
		return sateGraphDao.saveProvinceAuditInfo(auditSpot);
	}
	// 查询下级工作进度树
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map LowerDetails() {
		int doing = 0;
		int done = 0;
		Map map = new HashMap<String, Object>();
		User user = getCurrentUser();
		String year = new AppSettings().getReportYear();
		
		// 只有省级用户才能看到
		if (user.OrgGrade != OrganizationGrade.Province) {
			doing = done = 0;
			map.put("doing", doing);
			map.put("done", done);
			map.put("list", null);
			return null;
		}
		boolean isImportMapSpot = sateGraphDao.isImportMapSpot(year);
		List<LowerTreeNode> nodes = sateGraphDao.queryLowerTreeNodes();
		List<LowerTreeLeaf> leafs = sateGraphDao.queryLowerTreeLeafs(year);
		int theDoing = 0;
		int theDone = 0;
		for (int i = 0; i < nodes.size(); i++) {
			LowerTreeNode node = nodes.get(i);
			node.Children = new ArrayList<LowerTreeLeaf>();
			for (int j = 0; j < leafs.size(); j++) {
				LowerTreeLeaf leaf = leafs.get(j);
				if (node.RegionID == leaf.ParentID) {
					node.Children.add(leaf);
					if (!isImportMapSpot)
						leaf.Percent = 0;
					if (leaf.Type != null
							&& (leaf.Type.equals(OperationType.Report) || leaf.Type.equals(OperationType.AuditComplete))) {
						theDone++;
					} else {
						theDoing++;
					}
				}
			}
		}
        doing = theDoing;
        done = theDone;
		map.put("doing", doing);
		map.put("done", done);
		map.put("LowerDetailsResult", nodes);
		return map;
	}
	
	/**
	 * 规划分析 TODO 20170418完善
	 * 
	 * @param region
	 * @return
	 */
	public boolean planningAnalysis(int region) {
		return sateGraphDao.planningAnalysis(region);
	}
	
	/**
	 * 获取规划分析进度 TODO 20170420完善
	 * 
	 * @return
	 */
	public int getPlanningAnalysisProgress() {
		return sateGraphDao.getPlanningAnalysisProgress();
	}
}
