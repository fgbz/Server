package phalaenopsis.illegalclue.workflownodes;

import phalaenopsis.common.method.Basis;
import phalaenopsis.common.method.ManualAssembly;
import phalaenopsis.common.method.cache.WFCache;
import phalaenopsis.illegalclue.entity.Clue;
import phalaenopsis.illegalclue.service.IllegalClueServiceNew;
import phalaenopsis.workflowEngine.core.NodeFlowContext;
import phalaenopsis.workflowEngine.core.WorkflowNode;

/**
 * 登记节点
 * @author chunl
 *
 */
public class RegistNode extends WorkflowNode{

	@Override
	protected void Next(NodeFlowContext flowContext) {
//		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
//				.getRequest();
//		Object object = request.getAttribute("ServerData");
//		JSONObject jsonObject = JSON.parseObject(request.getAttribute("ServerData").toString()) ;
//		
//		//保存登记信息
//		IllegalClue illegalClue = JSON.parseObject(jsonObject.getString("IllegalClue"), IllegalClue.class);
//		IllegalClueService service = ManualAssembly.getAssembly("illegalClueService");
//		service.saveOrUpdateIllegalClue(illegalClue);
//		
//		//保存初判信息
//		ClueJudge clueJudge = JSON.parseObject(jsonObject.getString("ClueJudge"), ClueJudge.class);
//		IIllegalClueDao iIllegalClueDao = ManualAssembly.getAssembly("iIllegalClueDao");
//		iIllegalClueDao.saveOrUpdateClueJudge(clueJudge);
		

		//IllegalClue illegalClue = (IllegalClue) request.getAttribute("ServerData");
		Clue illegalClue = (Clue)WFCache.getData(Basis.getAuthId()+ "RegistNode");
		IllegalClueServiceNew service = ManualAssembly.getAssembly("illegalClueServiceNew");
		service.saveIllegalClue(illegalClue); // .saveOrUpdateIllegalClue(illegalClue);
//		
	}

}
