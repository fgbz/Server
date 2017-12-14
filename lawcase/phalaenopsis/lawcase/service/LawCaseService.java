package phalaenopsis.lawcase.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import phalaenopsis.common.dao.IWorkFlowDao;
import phalaenopsis.common.entity.Condition;
import phalaenopsis.common.entity.PagingEntity;
import phalaenopsis.common.entity.User;
import phalaenopsis.common.entity.Attachment.Attachment;
import phalaenopsis.common.method.Basis;
import phalaenopsis.common.method.BinarySerializer;
import phalaenopsis.common.method.Tools.GuidHelper;
import phalaenopsis.common.method.Tools.Linq;
import phalaenopsis.common.method.Tools.StrUtil;
import phalaenopsis.common.method.Tools.UUID64;
import phalaenopsis.common.method.cache.WFCache;
import phalaenopsis.common.service.AttachmentService;
import phalaenopsis.lawcase.dao.ILawCaseDao;
import phalaenopsis.lawcase.dao.ILawCaseServiceDao;
import phalaenopsis.lawcase.entity.CaseAcceptUser;
import phalaenopsis.lawcase.entity.CaseBaseInfo;
import phalaenopsis.lawcase.entity.DeliverLawCaseRelation;
import phalaenopsis.lawcase.entity.IllegalClues;
import phalaenopsis.lawcase.entity.LitigantInfo;
import phalaenopsis.lawcase.entity.LitigantType;
import phalaenopsis.lawcase.entity.SignLink;
import phalaenopsis.lawcase.entity.SignNode;
import phalaenopsis.lawcase.entity.SignNodeStatus;
import phalaenopsis.lawcase.entity.SignStatus;
import phalaenopsis.lawcase.entity.Signature;
import phalaenopsis.lawcase.sign.SignManager;
import phalaenopsis.workflowEngine.storage.WFInstance;

@Service("lawCaseService")
public class LawCaseService extends Basis {

	@Autowired
	private SignManager signManager;

	@Autowired
	private ILawCaseDao iLawCaseDao;

	@Autowired
	private IWorkFlowDao workflowDao;

   @Resource(name="attachmentService")
	private AttachmentService service;

	@Autowired
	private ILawCaseServiceDao lawCaseDao;

	/**
	 * 获取签字流程
	 * 
	 * @return
	 */
	public List<SignLink> getSignLink() {
		return signManager.GetSignLinkList();
	}

	/**
	 * 检查受理编号是否存在重复
	 * 
	 * @param obj
	 * @return
	 */
	public boolean checkRegisterCaseNo(CaseBaseInfo obj) {
		int num = iLawCaseDao.checkRegisterCaseNo(obj);
		return num > 0 ? true : false;
	}

	/**
	 * 保存案件（线索节点之后的节点）信息
	 * 
	 * @param obj
	 * @param clue
	 * @return
	 */
	public String saveCaseInfo(CaseBaseInfo obj, IllegalClues clue) {
		// 创建案件时
		if (obj.getCaseID() == null) {
			String guid = String.valueOf(UUID64.newUUID64().getValue());
			obj.setCaseID(guid);
		}
		// 为案件关联受理人赋caseID
		for (int i = 0; i < obj.getAcceptUsers().size(); i++) {
			obj.getAcceptUsers().get(i).setCaseID(obj.getCaseID());
		}
		// 判断编号是否重复
		if (checkRegisterCaseNo(obj))
			return "repeated";
		String id = saveCase(obj, clue, false);
		return id;
	}

	/**
	 * 保存案件（线索节点之后的节点）信息
	 * 
	 * @param obj
	 * @return
	 */
	public String saveCaseInfo(CaseBaseInfo obj) {
		String id = saveCase(obj, null, false);
		return id;
	}

	/**
	 * 保存线索信息（从线索到立案）
	 * 
	 * @param obj
	 * @param clue
	 * @return
	 */
	public String saveCaseNew(CaseBaseInfo obj, IllegalClues clue) {
		String id = saveCase(obj, clue, true);
		return id;
	}

	/**
	 * 保存
	 * 
	 * @param obj
	 * @param clue
	 * @param createNew
	 * @return
	 */
	@Transactional
	public String saveCase(CaseBaseInfo obj, IllegalClues clue, Boolean createNew) {
		// 立案阶段保存时，线索数据为null，做判断
		Map<String, CaseAcceptUser> userMap = new HashMap<>();
		if (clue != null) {
			// id为空,为id赋值
			if (clue.getId() == null) {
				clue.setId(String.valueOf(UUID64.newUUID64().getValue()));
			}
			// 保存违法线索登记
			clue.setCaseId(obj.getCaseID());// 原来写的忘记给clue赋caseID值
			iLawCaseDao.saveOrUpdateCluesRegister(clue);
			// 保存受理人信息,先删后插
			iLawCaseDao.deleteCaseAcceptUser(obj.getCaseID());
			List<CaseAcceptUser> acceptUsers = obj.getAcceptUsers();
			for (CaseAcceptUser acceptUser : acceptUsers) {
				if (acceptUser.getId() == null || acceptUser.getId().equals("")) {
					acceptUser.setId(String.valueOf(UUID64.newUUID64().getValue()));
				}
				userMap.put(acceptUser.getAcceptUserID(), acceptUser);
			}
			iLawCaseDao.saveCaseAcceptUser(acceptUsers);
			// 受理到 立案
			if (createNew)
				// 填写立案时间
				obj.setBuildTime(new Date());
		}

		// 保存案件信息
		if (StrUtil.isNullOrEmpty(obj.getId())) {
			// 测试用
			// obj.setId("59437360349184");
			// obj.setCaseID("59436898975744");
			obj.setId(String.valueOf(UUID64.newUUID64().getValue()));
		}
		iLawCaseDao.saveOrUpdateCaseBase(obj);

		if (obj.getLitigantInfos() != null && obj.getLitigantInfos().size() > 0) {
			// 先删除当事人
			iLawCaseDao.deleteLitigantInfo(obj.getCaseID());
			// 保存当事人
			for (int i = 0; i < obj.getLitigantInfos().size(); i++) {
				obj.getLitigantInfos().get(i).setId(String.valueOf(UUID64.newUUID64().getValue()));
				obj.getLitigantInfos().get(i).setType(LitigantType.Person);
				obj.getLitigantInfos().get(i).setCaseID(obj.getCaseID());
			}
			iLawCaseDao.saveLitigantInfo(obj.getLitigantInfos());
		}

		// 处理签字 去掉不合法的签字数据
		WFInstance wfInstance = workflowDao.getInstance(obj.getInstanceID());
		SignLink firstlink = signManager.GetSignLink(wfInstance.getNodeID());
		// SignLink firstlink=new SignLink(signLinkID, flowNode);
		SignNode firstnode = firstlink.GetFirst();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("caseID", obj.getCaseID());
		map.put("node", firstnode.getNode());
		map.put("signLinkID", firstlink.getSignLinkID());
		List<Signature> signs = iLawCaseDao.getSignatureList(map);
		List<Signature> addList = new ArrayList<Signature>();
		List<Signature> deleteList = new ArrayList<Signature>();
		// 当前用户的情况 在SaveSignature/SubmitSignature中进行处理 故在这里过滤掉当前用户
		User user = getCurrentUser();// 获取当前用户信息
		if (firstlink.getSignLinkID().equals("Clue")) {
			if (signs.size() > 0) {//
				for (Signature sg : signs) {
					if (!userMap.containsKey(sg.getSignedUserID())) {// 如果在经办人里面没有就过滤掉
						deleteList.add(sg);
					} else {
						userMap.remove(sg.getSignedUserID());// map里面移除上一次保存的
					}
				}
				for (String key : userMap.keySet()) {// 第二次保存的时候可能会修改用户
					if (!user.getId().equals(key)) {// 过滤当前用户
						Signature s = new Signature();
						s.setId(String.valueOf(UUID64.newUUID64().getValue()));
						s.setTemplateID(firstlink.getSignLinkID());
						s.setInstanceID(obj.getCaseID());
						s.setSignedNode(firstnode.getNode());
						s.setIsSendBack(0);
						s.setSignedUserID(key);
						s.setStatus(SignStatus.Unassigned);
						s.setNodeStatus(SignNodeStatus.NotFinished);
						addList.add(s);
					}
				}
			} else {
				// TODO 测试阶段
				if (obj.getAcceptUsers() != null && obj.getAcceptUsers().size() > 0) {
					for (int j = 0; j < obj.getAcceptUsers().size(); j++) {
						if (!user.getId().equals(obj.getAcceptUsers().get(j).getAcceptUserID())) {
							Signature s = new Signature();
							s.setId(String.valueOf(UUID64.newUUID64().getValue()));
							s.setTemplateID(firstlink.getSignLinkID());
							s.setInstanceID(obj.getCaseID());
							s.setSignedNode(firstnode.getNode());
							s.setIsSendBack(0);
							s.setSignedUserID(obj.getAcceptUsers().get(j).getAcceptUserID());
							s.setStatus(SignStatus.Unassigned);
							s.setNodeStatus(SignNodeStatus.NotFinished);
							addList.add(s);
						}
					}
				}
			}
		}
		if (addList.size() > 0) {
			iLawCaseDao.saveSignature(addList);
		}
		if (deleteList.size() > 0) {
			iLawCaseDao.deleteSignature(deleteList);
		}
		return obj.getCaseID();

	}

	/**
	 * 查询案件列表
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @param conditions
	 * @param sortProperty
	 * @param direction
	 * @return
	 */
	public PagingEntity<CaseBaseInfo> getCaseList(int pageNo, int pageSize, List<Condition> conditions,
			String sortProperty, String direction) {

		// 违法案件签字节点
		List<String> signLinkIDs = signManager.GetSignLinkIDList();
		// 获取持久化的CurrentUser
		User user = getCurrentUser();

		Map<String, Object> map = new HashMap<String, Object>();
		// 获取查询条件
		for (Condition entry : conditions) {

			if (entry.getKey().equals("办案状态")) {
				map.put("NodeID", entry.getValue());
			} else if (entry.getKey().equals("案件来源")) {
				map.put("CaseSource", entry.getValue());
			} else if (entry.getKey().equals("发生时间（开始）")) {
				map.put("HappenedTimeBegin", entry.getValue());
			} else if (entry.getKey().equals("发生时间（结束）")) {
				map.put("HappenedTimeEnd", entry.getValue());
			} else if (entry.getKey().equals("区域")) {
				map.put("District", entry.getValue());
			} else if (entry.getKey().equals("立案时间（开始）")) {
				map.put("BuildTimeBegin", entry.getValue());
			} else if (entry.getKey().equals("立案时间（结束）")) {
				map.put("BuildTimeEnd", entry.getValue());
			} else if (entry.getKey().equals("CaseID")) {
				map.put("CaseID", entry.getValue());
			} else if (entry.getKey().equals("案发地")) {
				map.put("CasePlace", entry.getValue());
			} else if (entry.getKey().equals("listType")) {
				if (entry.getValue().equals("待办")) {
					map.put("handle", "handle");
				} else if (entry.getValue().equals("已办")) {
					map.put("handed", "handed");
				} else if (entry.getValue().equals("办结")) {
					map.put("handOver", "handOver");
				}
			} else if (entry.getKey().equals("立案编号")) {// TODO
														// 20170609由getValue()修改为getKey
				map.put("RegisterCaseNo", entry.getValue());
			} else if (entry.getKey().equals("当事人")) {// TODO
														// 20170609由getValue()修改为getKey
				map.put("PersonalName", entry.getValue());
			}
		}

		if (direction != null && direction.equals("DESC")) {
			map.put("direction", direction);
		}
		// 判断非空
		if (sortProperty == null) {
			// 给个默认顺序(按编号排序)
			sortProperty = "RegisterCaseNo";
		}
		// 按案件编号排序
		if (sortProperty.equals("RegisterCaseNo")) {
			map.put("sortPropertyByCaseNo", sortProperty);
		} else if (sortProperty.equals("NodeID")) {
			// 按立案状态排序
			map.put("sortPropertyByNodeID", sortProperty);
		} else if (sortProperty.equals("District")) {
			// 按区域排序
			map.put("sortPropertyByDistrict", sortProperty);
		}
		// 登陆人id
		map.put("userid", user.getId());
		// 签证流程linkID
		map.put("signLinkIDs", signLinkIDs);
		// 用户的组织机构
		map.put("organizationid", user.getOrganizationID());
		// 分页
		map.put("startNum", pageSize * (pageNo - 1) + 1);
		map.put("endNum", pageSize * pageNo);
		List<CaseBaseInfo> list = iLawCaseDao.getCaseBaseInfoList(map);
		List<CaseBaseInfo> tempList = new ArrayList<>();
		for (CaseBaseInfo caseBaseInfo : list) {
			caseBaseInfo.setAcceptUsers(lawCaseDao.getAcceptUser(caseBaseInfo.getCaseID()));
			// 获取当事人
			List<LitigantInfo> infos = iLawCaseDao.getLitigantInfo(caseBaseInfo.getCaseID());
			caseBaseInfo.setLitigantInfos(infos);
			tempList.add(caseBaseInfo);
		}

		PagingEntity<CaseBaseInfo> caseBaseInfoResult = new PagingEntity<CaseBaseInfo>();

		int count = iLawCaseDao.getCaseBaseInfoTotal(map);
		caseBaseInfoResult.setPageCount(count);
		int pageCount = 0 == count ? 1 : (count - 1) / pageSize + 1; // 由于计算pageCount存在整除的情况，所以计算的时候先减1在除以pageSize

		caseBaseInfoResult.setPageNo(pageNo);
		caseBaseInfoResult.setPageSize(pageSize);
		caseBaseInfoResult.setPageCount(pageCount);
		caseBaseInfoResult.setRecordCount(count);
		caseBaseInfoResult.setCurrentList(tempList);

		return caseBaseInfoResult;
	}

	/**
	 * 案件查询
	 * 
	 * @param caseID
	 * @return
	 */
	public CaseBaseInfo getCase(String caseID) {

		Map<String, Object> map = new HashMap<String, Object>();
		// 获取最大的version
		int version = iLawCaseDao.getMaxVersion(caseID);
		map.put("version", version);
		map.put("caseID", caseID);
		CaseBaseInfo caseBaseInfo = iLawCaseDao.getCaseBaseByCaseID(map);
		return caseBaseInfo;
	}

	/**
	 * 通过流程id获取caseId
	 * 
	 * @param instanceID
	 * @return
	 */
	public String getCaseIDByInstanceID(String instanceID) {
		return iLawCaseDao.getCaseIDByInstanceID(instanceID);
	}

	/**
	 * 删除案件
	 * 
	 * @param caseID
	 * @param instanceID
	 * @return
	 */
	@Transactional
	public boolean deleteCase(String caseID, String instanceID) {

		// 删除流程信息
		workflowDao.deleteInstance(instanceID);
		// 删除附件信息
		List<Attachment> listAttachment = service.getAttachments(caseID);
		if (listAttachment != null && listAttachment.size() > 0) {
			// 获取附件id集合
			List<String> ids = Linq.extSelect(listAttachment, "id");
			service.delete(ids);
		}
		// 删除案件相关数据
		iLawCaseDao.deleteCase(caseID);

		return true;
	}

	/**
	 * 查询线索登记表信息
	 * 
	 * @param caseID
	 * @return
	 */
	public IllegalClues getIllegalClues(String caseID) {
		IllegalClues illegalClues = iLawCaseDao.getIllegalClues(caseID);
		illegalClues.setSignatures(iLawCaseDao.getSignatureByCaseID(caseID));
		return illegalClues;

	}

	/**
	 * 保存当事人
	 * 
	 * @param litigantInfos
	 * @return
	 */
	public List<LitigantInfo> saveLitigantInfos(List<LitigantInfo> litigantInfos) {

		for (int i = 0; i < litigantInfos.size(); i++) {
			if (StrUtil.isNullOrEmpty(litigantInfos.get(i).getId())) {
				litigantInfos.get(i).setId(String.valueOf(UUID64.newUUID64().getValue()));
			}
		}

		iLawCaseDao.saveLitigantInfo(litigantInfos);

		return litigantInfos;
	}

	/**
	 * 通过id删除当事人信息
	 * 
	 * @param id
	 * @return
	 */
	public boolean deleteLitigantInfo(String id) {
		int num = iLawCaseDao.deleteLitigantInfoByID(id);
		return num > 0 ? true : false;
	}

	/**
	 * 档案目录 获取整个案件所有节点的附件
	 * 
	 * @param caseID
	 * @return
	 */
	public Map<String, List<Attachment>> getCaseAttachments(String caseID) {
		Map<String, List<Attachment>> map = new HashMap<>();
		List<Attachment> listResult = iLawCaseDao.getAttachmentByLinkID(caseID);

		for (int i = 0; i < listResult.size(); i++) {
			JSONObject jsonObject = (JSONObject) JSON.parse(listResult.get(i).getExtraInfo());
			String Node = (String) jsonObject.get("Node");
			// 封装结果集
			if (map.containsKey(Node)) {
				map.get(Node).add(listResult.get(i));
			} else {
				List<Attachment> list = new ArrayList<>();
				list.add(listResult.get(i));
				map.put(Node, list);
			}
		}
		return map;
	}

	/**
	 * 判断是否已转违法案件
	 * 
	 * @param ids
	 * @return
	 */
	public boolean isDeliverToLawCase(List<String> ids) {
		int result = iLawCaseDao.isDeliverToLawCase(ids);
		return result > 0 ? true : false;
	}

	/**
	 * 保存转案件
	 * 
	 * @param requestItem
	 * @return
	 */
	@Transactional
	public String saveDeliverLawCase(DeliverLawCaseRelation rel, CaseBaseInfo obj, IllegalClues clue) {
		String id = saveCaseInfo(obj, clue);
		//DeliverLawCaseRelation
		if (StrUtil.isNullOrEmpty(rel.getId()))
			rel.setId(String.valueOf(UUID64.newUUID64().getValue()));
		rel.setCaseID(id);
		
		iLawCaseDao.saveOrUpdateDeliverLawCase(rel);
		
//		// 创建案件时
//		if (obj.getCaseID() == null) {
//			// 获取持久化的CurrentUser
//			User user = getCurrentUser();
//			String caseID = String.valueOf(UUID64.newUUID64().getValue());
//			obj.setCaseID(caseID);
//			clue.setCaseId(caseID);
//			obj.setCreateUser(user.getId());
//		}
//		for (int i = 0; i < obj.getAcceptUsers().size(); i++) {
//			obj.getAcceptUsers().get(i).setCaseID(obj.getCaseID());
//		}
//		for (int i = 0; i < obj.getLitigantInfos().size(); i++) {
//			obj.getLitigantInfos().get(i).setCaseID(obj.getCaseID());
//		}
//
//		if (StrUtil.isNullOrEmpty(rel.getId()))
//			rel.setId(String.valueOf(UUID64.newUUID64().getValue()));
//
//		rel.setCaseID(obj.getCaseID());
//		// 保存转违法案件信息
//		iLawCaseDao.saveOrUpdateDeliverLawCase(rel);
//
//		String id = saveCase(obj, clue, false);
		return id;
	}

	/**
	 * 根据caseID查询违法线索表信息
	 * 
	 * @param caseID
	 * @return
	 */
	public IllegalClues GetIllegalClues(String caseID) {
		IllegalClues clues = iLawCaseDao.getIllegalClues(caseID);
		return clues;
	}

	/**
	 * @param ids
	 * @return
	 * @desc /// 供日常巡查使用：根据ID列表获取案件列表
	 */
	public List<CaseBaseInfo> getCasesByIDs(List<String> ids) {
		Map<String, Object> map = new HashMap<>();
		for (String string : ids) {
			map.put("CaseID", string);
		}
		List<CaseBaseInfo> caseBaseInfoList = iLawCaseDao.getCaseBaseInfoList(map);
		for (CaseBaseInfo caseBaseInfo : caseBaseInfoList) {
			// 给案件基本信息中的当事人赋值
			List<LitigantInfo> litigantInfos = iLawCaseDao.queryLitigantInfo(caseBaseInfo);
			caseBaseInfo.setLitigantInfos(litigantInfos);
			// 给案件基本信息中的受理人赋值、
			List<CaseAcceptUser> acceptUsers = iLawCaseDao.queryCaseAcceptUser(caseBaseInfo);
			caseBaseInfo.setAcceptUsers(acceptUsers);
		}
		return caseBaseInfoList;
	}

	/**
	 * @param sign
	 * @return
	 * @desc 保存签字信息
	 */
	public String saveSignatureByEndCase(Signature sign) {
		if (sign.getId() == null || sign.getId().equals("")) {
			sign.setId(String.valueOf(UUID64.newUUID64().getValue()));
			iLawCaseDao.saveSignatureFromNodeByEndCase(sign);
		}
		// 先查询出所有的签字信息
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("caseID", sign.getInstanceID());
		map.put("node", sign.getSignedNode());
		map.put("signLinkID", sign.getTemplateID());
		List<Signature> signs = iLawCaseDao.getSignatureList(map);
		if (signs.size() > 0) {// 说明已经签过至少一次
			for (Signature signature : signs) {
				if (signature.getSignedUserID().equals(sign.getSignedUserID())) {
					signature.setStatus(sign.getStatus());
				}
				signature.setSignDate(sign.getSignDate());
				signature.setIdea(sign.getIdea());
				signature.setIsSendBack(sign.getIsSendBack());
				iLawCaseDao.updateSignatureFromNodeByEndCase(signature);
			}
		} else {// 反之说明该流程一次没有签过，直接添加签字信息
			iLawCaseDao.saveSignatureFromNodeByEndCase(sign);
		}
		return JSON.toJSONString(sign.getId());
	}

	/**
	 * @param currIDs
	 * @param lastIDs
	 * @param reason
	 * @return
	 * @desc 退回签字流程
	 */
	public boolean sendBackSignature(List<String> currIDs, List<String> lastIDs, String reason) {
		for (String string : currIDs) {
			// 删除签字
			iLawCaseDao.deleteSignatureByBack(string);
		}
		if (lastIDs != null) {
			for (String string : lastIDs) {
				// 先查询签字
				Signature s = iLawCaseDao.querySignature(string);
				s.setIsSendBack(1);// 0：否，1：是
				s.setSendBackReason(reason);
				s.setStatus(SignStatus.Unassigned);
				s.setNodeStatus(SignNodeStatus.NotFinished);
				// 后更新签字
				iLawCaseDao.updateSignature(s);
			}
		}
		return true;
	}

	/**
	 * @param sign
	 * @param signLinkID
	 * @param nextNode
	 * @return
	 * @desc 提交签字流程
	 */
	public List<Signature> submitSignature(Signature sign, String signLinkID, String nextNode) {
		List<Signature> list = new ArrayList<>();
		if (sign.getId() == null || sign.getId().equals("")) {
			sign.setId(String.valueOf(UUID64.newUUID64().getValue()));
		}
		// 先查询出所有的签字信息
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("caseID", sign.getInstanceID());
		map.put("node", sign.getSignedNode());
		map.put("signLinkID", sign.getTemplateID());
		List<Signature> signs = iLawCaseDao.getSignatureList(map);
		SignManager mgr = new SignManager();
		map.put("status", SignStatus.SignedFinished);
		// 查询签字总数
		int signedCount = iLawCaseDao.getSignatureCount(map);
		// 获取签字节点
		SignNode current = mgr.GetSignNode(signLinkID, sign.getSignedNode());
		Integer nodeStatus = signedCount + 1 >= current.getNumber() ? SignNodeStatus.Finished
				: SignNodeStatus.NotFinished;

		// 获取当前时间
		Date date = new Date(System.currentTimeMillis());
		sign.setOperateTime(date);
		sign.setStatus(SignStatus.SignedFinished);
		sign.setNodeStatus(nodeStatus);
		sign.setIsSendBack(0); // 0：否，1：是
		sign.setSendBackReason(null);
		// 保存或更新
		iLawCaseDao.saveOrUpdateSignature(sign);
		for (Signature signature : signs) {
			if (signature.getSignedUserID().equals(sign.getSignedUserID())) {
				signature.setStatus(SignStatus.SignedFinished);
			}
			signature.setIdea(sign.getIdea());
			signature.setSignDate(sign.getSignDate());
			signature.setNodeStatus(sign.getNodeStatus());
			signature.setIsSendBack(sign.getIsSendBack());
			signature.setSendBackReason(sign.getSendBackReason());
			iLawCaseDao.saveOrUpdateSignature(signature);// 这里的for循环里面更新后面要修改
		}
		// 获取下一环节个数
		map = new HashMap<>();
		map.put("instanceID", sign.getInstanceID());
		map.put("templateID", sign.getTemplateID());
		map.put("signedNode", nextNode);
		int nextCount = iLawCaseDao.getNextNodeCount(map);
		// 获取下一步签字
		if (nextNode != null && nextNode != "null" && !nextNode.equals("") && nodeStatus == SignNodeStatus.Finished
				&& nextCount == 0) {
			SignNode next = mgr.GetSignNode(signLinkID, nextNode);
			String signrole = String.valueOf(next.getProperties().get("SignRole"));
			// 获取创建人ID
			// 根据ss_sign表中的InstanceID和law_casebaseinfo表中的CaseID相关联获取到law_casebaseinfo表中的CREATEUSER字段
			// String createuserid = iLawCaseDao.queryCreateUserId(sign);
			List<String> signUserIDs = new ArrayList<>();
			String signorg = getCurrentUser().organizationID;// 根据当前用户区获取所属组织机构/部门ID
			String roleid = null;
			switch (signrole) {
			case "DepartmentCharge":
				roleid = "zhiduicharge";
				break;
			case "BranchCharge":
				if (!signLinkID.equals("Clue")) {// 线索界面这些签字过滤掉
					roleid = "zhiduilead";
				}
				break;
			case "BureauCharge":
				if (!signLinkID.equals("Clue")) {// 线索界面这些签字过滤掉
					roleid = "jufuzeren";
				}
				break;
			case "BureauLeader":
				if (!signLinkID.equals("Clue")) {// 线索界面这些签字过滤掉
					roleid = "jufenguan";
				}
				break;
			case "BureauManager":
				if (!signLinkID.equals("Clue")) {// 线索界面这些签字过滤掉
					roleid = "juzhuguan";
				}
				break;
			default: // 经办人查找放到流程流转处理中
				break;
			}

			signUserIDs = getSignUserIDs(signorg, roleid);
			if (signUserIDs.size() == 0) {
				return list;
			}
			for (String id : signUserIDs) {
				Signature signature = new Signature();
				signature.setId(String.valueOf(UUID64.newUUID64().getValue()));
				signature.setTemplateID(signLinkID);
				signature.setInstanceID(sign.getInstanceID());
				signature.setSignedNode(nextNode);
				signature.setSignedUserID(id);
				signature.setStatus(SignStatus.Unassigned);
				// 保存
				iLawCaseDao.saveSignatureFromNodeByEndCase(signature);
				list.add(signature);
			}
		}
		return list;
	}

	/**
	 * @param signorg
	 * @param roleid
	 * @return 根据用户角色及所负责的组织机构，批量获取待签字人ID
	 */
	private List<String> getSignUserIDs(String signorg, String roleid) {
		// List<String> userOrgs =
		// iLawCaseDao.getUserIdByOrganizationID(signorg);
		// List<String> userRoles = iLawCaseDao.getUserIdByRoleID(roleid);
		// List<String> users = new ArrayList<>();
		// for (String string : userOrgs) {
		// for (String item : userRoles) {
		// if (string.contains(item)) {
		// users.add(item);
		// }
		// }
		// }
		List<User> userOrgs = iLawCaseDao.getUserIdByOrganizationID(signorg);
		List<String> userRoles = iLawCaseDao.getUserIdByRoleID(roleid);
		List<String> users = new ArrayList<>();
		Map<String, Object> userOrgMap = new HashMap<>();
		for (User user : userOrgs) {
			userOrgMap.put(user.getId(), user);
		}
		for (String roUser : userRoles) {
			if (userOrgMap.keySet().contains(roUser)) {
				users.add(roUser);
			}
		}
		return users;
	}

}
