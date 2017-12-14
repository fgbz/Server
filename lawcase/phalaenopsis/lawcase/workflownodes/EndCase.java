package phalaenopsis.lawcase.workflownodes;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import phalaenopsis.common.method.Basis;
import phalaenopsis.common.method.ManualAssembly;
import phalaenopsis.common.method.cache.WFCache;
import phalaenopsis.lawcase.entity.CaseBaseInfo;
import phalaenopsis.lawcase.service.LawCaseService;
import phalaenopsis.workflowEngine.core.NodeFlowContext;
import phalaenopsis.workflowEngine.core.WorkflowNode;

/**
 * 结案
 * @author chunl
 *
 */
public class EndCase extends WorkflowNode{

	@Override
	protected void Next(NodeFlowContext flowContext) {
		JSONObject map = (JSONObject) WFCache.get(Basis.getAuthId() + "EndCase");
		Object object = map.get("CaseInfo");

		if (object != null) {
			CaseBaseInfo caseInfo = JSON.parseObject(object.toString(), CaseBaseInfo.class);
			LawCaseService service = ManualAssembly.getAssembly("lawCaseService");
			service.saveCaseInfo(caseInfo);
		}
	}
}
