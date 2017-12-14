package phalaenopsis.lawcase.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import phalaenopsis.lawcase.dao.ILawCaseDao;
import phalaenopsis.lawcase.dao.ILawCaseServiceDao;
import phalaenopsis.lawcase.dao.ISignDao;
import phalaenopsis.lawcase.entity.CaseAcceptUser;
import phalaenopsis.lawcase.entity.CaseBaseInfo;
import phalaenopsis.lawcase.entity.SignLink;
import phalaenopsis.lawcase.entity.SignNodeStatus;
import phalaenopsis.lawcase.entity.SignStatus;
import phalaenopsis.lawcase.entity.Signature;
import phalaenopsis.lawcase.sign.SignManager;
import phalaenopsis.workflowEngine.controller.WFObject;
import phalaenopsis.workflowEngine.core.WorkflowEngineInstance;

@Service("lawCaseWorkflowService")
public class LawCaseWorkflowService {

	@Autowired
	private WorkflowEngineInstance engine;

	@Autowired
	private SignManager mgr;

	@Autowired
	private ILawCaseDao caseServiceDao;

	@Autowired
	private LawCaseService lawCaseService;

	@Autowired
	private ISignDao signDao;

	@Transactional
	/**
	 * 下一步
	 * @param obj
	 * @return
	 */
	public WFObject next(WFObject obj) {
		// TODO Auto-generated method stub
		WFObject next = engine.next(obj);
		SignLink signLink = mgr.GetNextSignLink(next.getCurrentNode());
		if (null != signLink) {
			String caseID = lawCaseService.getCaseIDByInstanceID(next.InstanceID);
			String node = signLink.GetFirst().getNode();

			int nextCount = signDao.getSignCount(caseID, signLink.getSignLinkID(), node);
			if (0 == nextCount) {
				CaseBaseInfo caseInfo = lawCaseService.getCase(caseID);
				for (CaseAcceptUser user : caseInfo.getAcceptUsers()) {
					Signature signature = new Signature(UUID.randomUUID().toString(), signLink.getSignLinkID(), caseID,
							node, user.getAcceptUserID(), 0, SignStatus.Unassigned, SignNodeStatus.NotFinished);
					//caseServiceDao.saveOrUpdateSignature(signature);
					signDao.saveSign(signature);
				}
			}
		}
		return next;
	}
	/**
	 * 上一步，退回流程（节点与节点之间的退回）
	 * @param obj
	 * @return
	 */
	public WFObject Previous(WFObject obj)
    {
        WFObject previous = engine.previous(obj);
        SignLink signLink = mgr.GetNextSignLink(previous.CurrentNode);
        if (null!=signLink)
        {
        	String caseID = lawCaseService.getCaseIDByInstanceID(previous.InstanceID);//获取案件CaseID
        	String node = signLink.GetLast().getNode();//获取最后一个签字节点属性
        	Map<String, Object> map = new HashMap<String, Object>();
    		map.put("caseID", caseID);
    		map.put("node", node);
    		map.put("signLinkID", signLink.getSignLinkID());
    		List<Signature> signs = caseServiceDao.getSignatureList(map);//查询上一个节点最后一个签字人的签字信息
    		if(signs!=null && signs.size()>0){//对返回的list进行判断
    			for(Signature sign:signs){
    				sign.setStatus(SignStatus.Unassigned);//修改节点签字状态
    				sign.setNodeStatus(SignNodeStatus.NotFinished);//修改节点签字状态
    				caseServiceDao.updateSignatureFromNodeByEndCase(sign);//因为签字人数不多 这里就不提出for循环更新
    			}
    		}
        }
        return previous;
    }
}
