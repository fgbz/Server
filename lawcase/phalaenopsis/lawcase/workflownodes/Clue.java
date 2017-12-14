package phalaenopsis.lawcase.workflownodes;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import phalaenopsis.common.method.Basis;
import phalaenopsis.common.method.ManualAssembly;
import phalaenopsis.common.method.cache.WFCache;
import phalaenopsis.lawcase.entity.CaseBaseInfo;
import phalaenopsis.lawcase.entity.IllegalClues;
import phalaenopsis.lawcase.service.LawCaseService;
import phalaenopsis.workflowEngine.core.NodeFlowContext;
import phalaenopsis.workflowEngine.core.WorkflowNode;

/**
 * 线索
 * @author chunl
 *
 */
public class Clue extends WorkflowNode {

	@Override
	protected void Next(NodeFlowContext flowContext) {
		JSONObject map = (JSONObject) WFCache.get(Basis.getAuthId() + "Clue");

		IllegalClues cluesInfo;
		CaseBaseInfo acceptInfo;

		Object objCluesInfo = map.get("CluesInfo");
		Object objectAcceptInfo = map.get("AcceptInfo");
		if (null != objCluesInfo) {

			acceptInfo = JSON.parseObject(objectAcceptInfo.toString(), CaseBaseInfo.class);

			cluesInfo = JSON.parseObject(objCluesInfo.toString(), IllegalClues.class);
			LawCaseService service = ManualAssembly.getAssembly("lawCaseService");
			service.saveCaseNew(acceptInfo, cluesInfo);

		}

	}

}
