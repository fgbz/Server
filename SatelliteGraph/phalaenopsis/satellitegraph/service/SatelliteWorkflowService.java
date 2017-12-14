package phalaenopsis.satellitegraph.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import phalaenopsis.common.entity.AppSettings;
import phalaenopsis.common.entity.Condition;
import phalaenopsis.common.entity.OrganizationGrade;
import phalaenopsis.common.entity.User;
import phalaenopsis.common.method.Basis;
import phalaenopsis.common.method.Tools.UUID64;
import phalaenopsis.satellitegraph.dao.SatelliteGraphDaoPartial;
import phalaenopsis.satellitegraph.dao.SatelliteWorkflowDo;
import phalaenopsis.satellitegraph.entity.MapSpot2016;
import phalaenopsis.satellitegraph.entity.NodeStates;
import phalaenopsis.satellitegraph.entity.OperationLog;
import phalaenopsis.satellitegraph.entity.OperationType;
import phalaenopsis.workflowEngine.controller.WFObject;
import phalaenopsis.workflowEngine.core.WorkflowEngineInstance;

@Service("satelliteWorkflowService")
public class SatelliteWorkflowService extends Basis {
	@Autowired
	private SatelliteWorkflowDo satelliteWorkflowDo;

	@Autowired
	private SatelliteGraphDaoPartial satelliteGraphDaoPatial;
	
	@Autowired
	private AppSettings appSettings;
	
	@Autowired
	private WorkflowEngineInstance engine;

	public void updateNode(WFObject next) {
		satelliteWorkflowDo.updateNode(next);
	}

	public void updateVersion(String instanceID) {
		satelliteWorkflowDo.updateVersion(instanceID);
	}

	public boolean report(int regionID) {
		User currentUser = getCurrentUser();
		OperationLog log = new OperationLog(UUID64.newUUID64().getValue(), currentUser.getId(), currentUser.getName(),
				currentUser.getOrganizationID());

		return submit(regionID, true, log);
	}
	
	

	private boolean submit(int regionId, boolean isReport, OperationLog log) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		String[] nodes = null;
		User currentUser = getCurrentUser();

		Map<String, Object> map = new HashMap<String, Object>();
		// 暂时假设：每个组织机构只负责一个区域
		switch (currentUser.getOrgGrade()) {
		case OrganizationGrade.County:
			nodes = new String[] { NodeStates.ProofLegal, NodeStates.ProofNotNew, NodeStates.ProofMaybeNew };
			regionId = currentUser.regions[0].RegionID;
			map.put("County", regionId);
			log.setType(OperationType.Report);
			break;
		case OrganizationGrade.City:
			nodes = new String[] { NodeStates.ProofLegalCity, NodeStates.ProofNotNewCity,
					NodeStates.ProofMaybeNewCity };
			if (isReport) {
				// 上报
				regionId = currentUser.regions[0].ParentID;
				map.put("City", regionId);
				log.setType(OperationType.Report);
			} else {
				// 回退
				map.put("County", regionId);
				log.setType(OperationType.Back);
			}
			break;
		case OrganizationGrade.Province:
			// 审核完成
			nodes = new String[] { NodeStates.ProofLegalProvince, NodeStates.ProofNotNewProvince,
					NodeStates.ProofMaybeNewProvince };
			map.put("City", regionId);
			log.setType(OperationType.AuditComplete);
			break;
		default:
			break;
		}

		map.put("Array", Arrays.asList(nodes));
		List<MapSpot2016> list = satelliteGraphDaoPatial.getMapSpot2016sByConditions(map);
		Iterator<MapSpot2016> iterators = list.iterator();
		while (iterators.hasNext()) {
			MapSpot2016 spot2016 = iterators.next();
			WFObject wfObject = new WFObject();
			wfObject.setInstanceID(spot2016.getInstanceID());
			//wfObject.setServerData(spot2016);
			List<Condition> conditions = new ArrayList<Condition>();
			conditions.add(new Condition("op", isReport ? "Report" : "Back"));
			wfObject.setServerNamedDatas(conditions);
			//上下文数据
			request.setAttribute("MapSpotData", spot2016);
			request.setAttribute("op", isReport ? "Report" : "Back");
			WFObject next = engine.next(wfObject);
			updateNode(next);
		}
		log.setYear(Integer.parseInt(appSettings.getReportYear()));
		log.setRegion(regionId);
		Calendar calendar = Calendar.getInstance();
		log.setOperationTime(calendar.getTime());
		log.setID(UUID64.newUUID64().getValue());
		satelliteGraphDaoPatial.insertOperationLog(log);
		
		return true;
	}

	public boolean back(int regionID, OperationLog log) {
		return this.submit(regionID, false, log);
		//return satelliteWorkflowDo.back(regionID, log);
	}
}
