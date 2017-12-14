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
 * 立案
 * 
 * @author chunl
 *
 */
public class Build extends WorkflowNode {

	@Override
	protected void Next(NodeFlowContext flowContext) {

		JSONObject map = (JSONObject) WFCache.get(Basis.getAuthId() + "Build");
		Object object = map.get("BuildInfo");
		if (null != object) {
				CaseBaseInfo baseInfo =  JSON.parseObject(object.toString(), CaseBaseInfo.class);
				LawCaseService service = ManualAssembly.getAssembly("lawCaseService");
				service.saveCaseInfo(baseInfo);
		}
	}
}
