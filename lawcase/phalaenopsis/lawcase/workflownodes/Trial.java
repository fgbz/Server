package phalaenopsis.lawcase.workflownodes;

import java.io.IOException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.alibaba.fastjson.JSONObject;

import phalaenopsis.common.method.Basis;
import phalaenopsis.common.method.ManualAssembly;
import phalaenopsis.common.method.cache.WFCache;
import phalaenopsis.lawcase.entity.document.Document;
import phalaenopsis.lawcase.service.LawCaseDocument;
import phalaenopsis.workflowEngine.core.NodeFlowContext;
import phalaenopsis.workflowEngine.core.WorkflowNode;

/**
 * 审理
 * @author chunl
 *
 */
public class Trial extends WorkflowNode {

	@Override
	protected void Next(NodeFlowContext flowContext) {
		JSONObject map = (JSONObject) WFCache.get(Basis.getAuthId() + "Trial");
		Object object = map.get("TrialRecordDoc");

		if (null != object) {
			ObjectMapper mapper = new ObjectMapper();
			try {
				Document trialDoc = mapper.readValue(object.toString(), Document.class);
				LawCaseDocument service = ManualAssembly.getAssembly("lawCaseDocument");
				service.saveTrialRecord(trialDoc);
			} catch (JsonParseException e) {
				e.printStackTrace();
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
