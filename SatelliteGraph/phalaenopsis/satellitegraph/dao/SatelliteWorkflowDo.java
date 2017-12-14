package phalaenopsis.satellitegraph.dao;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import phalaenopsis.common.method.Basis;
import phalaenopsis.satellitegraph.entity.MapSpot2016;
import phalaenopsis.satellitegraph.entity.WFVerHistory;
import phalaenopsis.satellitegraph.entity.WFVerInstance;
import phalaenopsis.workflowEngine.controller.WFObject;

@Repository("satelliteWorkflowDo")
public class SatelliteWorkflowDo extends Basis {

	@Resource
	private SqlSession sqlSession;

	public void updateNode(WFObject next) {
		String instanceID = next.InstanceID;
		List<MapSpot2016> list = sqlSession.selectList("satelliteWorkflow.getSports", instanceID);
		for (MapSpot2016 spot : list) {
			spot.setNode(next.CurrentNode);
			sqlSession.update("satelliteWorkflow.updateSportInstance", spot);
		}
	}

	public void updateVersion(String instanceID) {
		WFVerInstance instance = sqlSession.selectOne("satelliteWorkflow.getWFVerInstance", instanceID);
		if (instance.Version != null) {
			List<WFVerHistory> histories = sqlSession.selectList("satelliteWorkflow.getWFVerHistorys", instanceID);
			for (WFVerHistory his : histories) {
				his.Version = instance.Version;
				sqlSession.update("satelliteWorkflow.updateWFVerHistory", his);
			}
		}
	}

//	public boolean report(int regionID) {
//		OperationLog log = new OperationLog();
//		// 测试 写一个实体
//		User user = new User();
//		user.id = "42876706-ad0b-40dd-b98a-0ec22fd06dbf";
//		user.name = "admin";
//		user.organizationID = "B9D58FB131282E42A1B63A550D3BCBF4";
//		///////////////
//		String id = /* getCurrentUser() */user.id;
//		String name = /* getCurrentUser() */user.name;
//		String organizationID = /* getCurrentUser() */user.organizationID;
//		return submit(regionID, true, log);
//	}
//
//	// TODO 20170412添加 上报的方法
//	private boolean submit(int regionID, boolean isReport, OperationLog log) {
////		String[] nodes = null;
////		// 暂时假设：每个组织机构只负责一个区域
////		switch (getCurrentUser().OrgGrade) {
////		case OrganizationGrade.County:
////			nodes = new String[] { NodeStates.ProofLegal, NodeStates.ProofNotNew, NodeStates.ProofMaybeNew };
////			regionID = /* getCurrentUser().regions[0].RegionID */370102;
////			// func = p => p.County == regionID;
////			log.getType() = OperationType.Report;
////			break;
////		case OrganizationGrade.City:
////			nodes = new String[] { NodeStates.ProofLegalCity, NodeStates.ProofNotNewCity,
////					NodeStates.ProofMaybeNewCity };
////			if (isReport) {
////				// 上报
////				regionID = /* getCurrentUser().regions[0].ParentID */370102;
////				// func = p => p.City == regionID;
////				log.Type = OperationType.Report;
////			} else {
////				// 回退
////				// func = p => p.County == regionID;
////				log.Type = OperationType.Back;
////			}
////			break;
////		case OrganizationGrade.Province:
////			// 审核完成
////			nodes = new String[] { NodeStates.ProofLegalProvince, NodeStates.ProofNotNewProvince,
////					NodeStates.ProofMaybeNewProvince };
////			// func = p => p.City == regionID;
////			log.Type = OperationType.AuditComplete;
////			break;
////		}
////		Map<String, Object> map = new HashMap<>();
////		map.put("regionID", regionID);
////		map.put("orgGrade", /* getCurrentUser().OrgGrade */3);
////		map.put("nodes", Arrays.asList(nodes));
////		List<MapSpot2016> list = sqlSession.selectList("satelliteWorkflow.queryOver", map);
////		for (MapSpot2016 mapSpot2016 : list) {
////			WFObject object = new WFObject();
////			object.InstanceID = mapSpot2016.getInstanceID();
////			object.ServerData = mapSpot2016;
////			Condition condition = new Condition();
////			condition.setKey("up");
////			condition.setValue(isReport ? "Report" : "Back");
////			if (object.ServerNamedDatas == null) {
////				object.ServerNamedDatas = new ArrayList<Condition>();
////			}
////			object.ServerNamedDatas.add(condition);
////			WorkflowEngineInstance engineInstance = new WorkflowEngineInstance();
////			engineInstance.next(object);
////			updateNode(object);
////		}
////		log.Year = Integer.parseInt(new AppSettings().getReportYear());
////		log.Region = regionID;
////		// 获取系统当前时间
////		try {
////			Date d = new Date();
////			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
////			String dateNowStr = sdf.format(d);
////			Date today = sdf.parse(dateNowStr);
////			log.OperationTime = today;
////			// 保存操作
////			return true;
////		} catch (ParseException e) {
////			e.printStackTrace();
////		}
//		return false;
//	}
//
//	// TODO 20170414添加 回退的方法
//	public boolean back(int regionID, OperationLog log) {
//		return submit(regionID, false, log);
//	}

}
