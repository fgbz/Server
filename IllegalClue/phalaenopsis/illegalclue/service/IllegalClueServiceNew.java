package phalaenopsis.illegalclue.service;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import phalaenopsis.common.dao.OrganizationDao;
import phalaenopsis.common.entity.Condition;
import phalaenopsis.common.entity.Page;
import phalaenopsis.common.entity.PagingEntity;
import phalaenopsis.common.entity.Region;
import phalaenopsis.common.entity.User;
import phalaenopsis.common.method.Basis;
import phalaenopsis.common.method.Tools.StrUtil;
import phalaenopsis.common.method.cache.DictCache;
import phalaenopsis.common.method.cache.RegionCache;
import phalaenopsis.illegalclue.dao.IIllegalClueDaoNew;
import phalaenopsis.illegalclue.entity.Clue;
import phalaenopsis.illegalclue.entity.ClueAudit;
import phalaenopsis.illegalclue.entity.ClueDictionary;
import phalaenopsis.illegalclue.entity.ClueEnd;
import phalaenopsis.illegalclue.entity.ClueJudge;
import phalaenopsis.illegalclue.entity.IllegalClueStatistic;
import phalaenopsis.illegalclue.entity.IllegalClueSum;
import phalaenopsis.illegalclue.entity.ResultState;
import phalaenopsis.illegalclue.entity.ResultString;
import phalaenopsis.illegalclue.enums.AuditType;
import phalaenopsis.illegalclue.enums.ClueAuditType;
import phalaenopsis.illegalclue.enums.ClueFKStatus;
import phalaenopsis.workflowEngine.controller.WFObject;

@Service("illegalClueServiceNew")
public class IllegalClueServiceNew extends Basis {

	@Autowired
	private IIllegalClueDaoNew dao;

	private static Logger logger = LoggerFactory.getLogger(IllegalClueServiceNew.class);

	/**
	 * 组织机构Dao
	 */
	@Autowired
	private OrganizationDao orgDao;

	/**
	 * 定义锁对象
	 */
	private Lock lock = new ReentrantLock();
	
	public ResultState deleteIllegalClue(String id) {
		dao.deleteClue(id);
		dao.deleteClueAudit(id);
		dao.deleteClueClose(id);
		dao.deleteClueIllegal(id);
		dao.deleteClueInvalid(id);
		return ResultState.Success;
	}

	// 保存线索初判信息
	@Transactional
	public ResultState saveOrUpdateClueJudge(ClueJudge clueJudge) {
		int count = 0;
		int count2 = 0;
		count = dao.isExistInnerClueJudge(clueJudge.getClueId());
		count2 = dao.isExistOuterClueJudge(clueJudge.getClueId());
		if (count == 0 && count2 == 0) {
			// 保存
			// dao.saveClueJudge(clueJudge);
			// 分两种情况(属于范围内和不属于范围内)
			if (clueJudge.getJudgeType() == 1) {
				return ResultState.Failed;
			} else if (clueJudge.getJudgeType() == 3) {
				/*
				 * //属于 插入初判类型 dao.saveInnerClueJudgeId(clueJudge); //插入 内容
				 * dao.saveInnerClueJudge(clueJudge);
				 */
				if (clueJudge.getId() == null) {
					clueJudge.setId(UUID.randomUUID().toString());
				}
				dao.insertclue_illegal(clueJudge);
				// dao.insertclue(clueJudge);
				// 更新clue表
				dao.updateclue(clueJudge);
			} else if (clueJudge.getJudgeType() == 2) {
				/*
				 * //不属于 插入初判类型 dao.saveOuterClueJudgeId(clueJudge); //插入 内容
				 * dao.saveOuterClueJudge(clueJudge);
				 */
				if (clueJudge.getId() == null) {
					clueJudge.setId(UUID.randomUUID().toString());
				}
				dao.insertclue_invalid(clueJudge);
				// dao.insertclue(clueJudge);
				// 更新clue表
				dao.updateclue(clueJudge);
			}
		} else {
			// 更新
			// 分两种情况(属于范围内和不属于范围内)
			if (clueJudge.getJudgeType() == 1) {
				return ResultState.Failed;
			} else if (clueJudge.getJudgeType() == 3) {
				// 属于
				// dao.updateInnerClueJudgeId(clueJudge);
				// dao.updateInnerClueJudge(clueJudge);
				dao.updateclue_illegal(clueJudge);
				// 更新clue表
				dao.updateclue(clueJudge);
			} else if (clueJudge.getJudgeType() == 2) {
				// 不属于
				// dao.updateOuterClueJudgeId(clueJudge);
				// dao.updateOuterClueJudge(clueJudge);
				dao.updateclue_invalid(clueJudge);
				// 更新clue表
				dao.updateclue(clueJudge);
			}
		}
		return ResultState.Success;
	}

	public Map<String, List<ClueDictionary>> getClueDictionaries() {
		@SuppressWarnings("unchecked")
		Map<String, List<ClueDictionary>> map = (Map<String, List<ClueDictionary>>) DictCache
				.get("IllegalClueDictionary");
		if (null == map) {
			map = getIllegalClueDictionaries();
			DictCache.put("IllegalClueDictionary", map);
		}
		return map;
	}
	/**
	 * 线索饼图统计查询
	 * @param dataString
	 * @return
	 */
	public IllegalClueSum getClueStatistic(String dataString){
		JSONObject object =JSON.parseObject(dataString);//获取前端参进行解析
		String startTime =object.get("startTime").toString();
		String endTime =object.get("endTime").toString();
		String selectType=object.get("selectType").toString();
		Clue clue=new Clue();
		clue.setStartTime(Long.parseLong(startTime));
		clue.setEndTime(Long.parseLong(endTime));
		if(selectType.equals("1")){//如果是1，代表土地违法
			clue.setillegalTypePcode("010000");
		}else{
			clue.setillegalTypePcode("020000");//为2的情况代表矿产违法
		}
		List<Clue> clues=dao.getClueList(clue);//查询出来的结果集
		int count1=0;
		int count2=0;
		int count3=0;
		int count4=0;
		if(clues.size()>0){//判断是否有值
			for(Clue cl :clues){
				if(selectType.equals("1")){//土地违法
					if(cl.getIllegalType().equals("010100")){//违法批地
						count1++;
					}else if(cl.getIllegalType().equals("010200")){//违法占地
						count2++;
					}
					else if(cl.getIllegalType().equals("010300")){//违法转让
						count3++;
					}else if(cl.getIllegalType().equals("010400")){//破坏农用地
						count4++;
					}
				}else{//矿产违法
					if(cl.getIllegalType().equals("021010")){//违法勘查矿产资源
						count1++;
					}else if(cl.getIllegalType().equals("020200")){//违法开采矿产资源
						count2++;
					}
					else if(cl.getIllegalType().equals("020300")){//违法转让矿业权
						count3++;
					}else if(cl.getIllegalType().equals("020400")){//违法审批发放勘查或者采矿许可证
						count4++;
					}
				}
			}
		}
		IllegalClueSum clueSum=new IllegalClueSum();
		clueSum.setCount1(count1);
		clueSum.setCount2(count2);
		clueSum.setCount3(count3);
		clueSum.setCount4(count4);
		clueSum.setTotal(count1+count2+count3+count4);
		return clueSum;
	}
	/**
	 * 通过查询数据库的方式，获取违法线索字典
	 * 
	 * @return
	 */
	private Map<String, List<ClueDictionary>> getIllegalClueDictionaries() {
		Map<String, List<ClueDictionary>> map = new HashMap<String, List<ClueDictionary>>();
		List<ClueDictionary> dictionaries = dao.getClueDictionaries();
		// dictionaries.addAll(dao.getInvalidDictionaries());
		// 添加请选择
		// ClueDictionary firstClueDictionary = new ClueDictionary("0", "-");
		for (ClueDictionary clueDictionary : dictionaries) {
			if (map.containsKey(clueDictionary.getType())) {
				List<ClueDictionary> temp = map.get(clueDictionary.getType());
				temp.add(clueDictionary);
				map.put(clueDictionary.getType(), temp);
			} else {
				List<ClueDictionary> temp = new ArrayList<ClueDictionary>();
				// temp.add(firstClueDictionary);
				temp.add(clueDictionary);
				map.put(clueDictionary.getType(), temp);
			}
		}
		return map;
	}

	public List<Condition> getHandleOrganizationList(String id) {
		return dao.getHandleOrganizationList(id);
	}
	/**
	 * 保存或更新办结信息
	 * @param clueEnd
	 * @return
	 */
    public ResultString saveOrUpdateClueEnd(ClueEnd clueEnd){
    	if(clueEnd.getStatus()==null || clueEnd.getStatus().equals("")){
    		clueEnd.setStatus(ClueFKStatus.NO.getIndex());
    	}
    	if(clueEnd.getIs_Audit()==null || clueEnd.getIs_Audit().equals("")){
    		clueEnd.setIs_Audit(0);
    	}
    	if(clueEnd.getIs_Notify()==null || clueEnd.getIs_Notify().equals("")){
    		clueEnd.setIs_Notify(0);
    	}
    	clueEnd.setOperate_organizattion_Id(getCurrentUser().getOrganizationID());
    	ResultString rstr=new ResultString();//设置返回值
	    if (null == clueEnd.getId()){
	    	//是否告知 ，看后面需求是否添加
	    	clueEnd.setId(UUID.randomUUID().toString());// 设置主键
	    	int count=dao.saveClueEnd(clueEnd);
	    	if(count==1){
	    		rstr.setId(clueEnd.getId());
	    		rstr.setResultState(ResultState.Success);
	    	}else{
	    		rstr.setId(clueEnd.getId());
	    		rstr.setResultState(ResultState.Failed);
	    	}
	    	return rstr;
		} else {
			int count=dao.updateClueEnd(clueEnd);
	    	if(count==1){
	    		rstr.setId(clueEnd.getId());
	    		rstr.setResultState(ResultState.Success);
	    	}else{
	    		rstr.setId(clueEnd.getId());
	    		rstr.setResultState(ResultState.Failed);
	    	}
			return rstr;
	    }
    }
	/**
	 * 保存或更新审核信息
	 * @param clueAudit
	 * @author chiz
	 * @return
	 */
	public ResultString saveOrUpdateClueAudit(ClueAudit clueAudit) {
		if(clueAudit.getAuditType()==AuditType.CHU.getIndex()){
			if(clueAudit.getType()==null || clueAudit.getType().equals("")){
				if(clueAudit.getJudgeType()==3)//属于受理范围的情况
				{
					if(clueAudit.getHandleType()==1){//本级办理
						clueAudit.setType(ClueAuditType.BEN.getIndex());
						clueAudit.setProcessId(UUID.randomUUID().toString());
					}else if(clueAudit.getHandleType()==3 || clueAudit.getHandleType()==4){//转办
						clueAudit.setType(ClueAuditType.ZHUAN.getIndex());
						clueAudit.setAssignId(UUID.randomUUID().toString());
					}
				}else if(clueAudit.getJudgeType()==2){//不属于受理范围的情况
					//后面流程加入后再进行筛选
					clueAudit.setType(ClueAuditType.OVER.getIndex());
				}
			}
		}else{
			clueAudit.setType(ClueAuditType.BEN.getIndex());
		}
		ResultString resultStr=new ResultString();
		if (null == clueAudit.getId()) {// 判断主键id是否为空
			clueAudit.setOperator(getAuthId());
			clueAudit.setOperatorTime(clueAudit.getAuditorTime());
			if(clueAudit.getStatus()==null || clueAudit.getStatus().equals("")){
				clueAudit.setStatus(ClueFKStatus.NO.getIndex());
			}
			clueAudit.setId(UUID.randomUUID().toString());// 设置主键
//			clueAudit.setAuditType(AuditType.CHU.getIndex());
			int count=dao.saveClueAudit(clueAudit);
			if(count==1){
				resultStr.setId(clueAudit.getId());
				resultStr.setResultState(ResultState.Success);
			}else{
				resultStr.setId(clueAudit.getId());
				resultStr.setResultState(ResultState.Failed);
			}
			return resultStr;
		} else {
			int count=dao.updateClueAudit(clueAudit);
			if(count==1){
				resultStr.setId(clueAudit.getId());
				resultStr.setResultState(ResultState.Success);
			}else{
				resultStr.setId(clueAudit.getId());
				resultStr.setResultState(ResultState.Failed);
			}
			return resultStr;
		}
	}

	public ResultState saveIllegalClue(Clue clue) {

		if (clue.getmClueJudge().getJudgeType() == 3) {
			clue.setType(1);
		} else if (clue.getmClueJudge().getJudgeType() == 2) {
			clue.setType(2);
		} else {
			clue.setType(0);
		}

		// 线索记录人
		if (StrUtil.isNullOrEmpty(clue.getRecordStaffId())) {
			User user = getCurrentUser();
			clue.setRecordStaffId(user.getId());
			clue.setRecordOrganizationId(user.getOrganizationID());
//			clue.setRecordTime(java.util.Calendar.getInstance().getTime().getTime());
			clue.setIsDuplicated(0);
			clue.setIsRemoved(0);
			clue.setIsTemporary(0);
			clue.setIsAudit(0);
			clue.setIsTransfer(0);
			// clue.setSerialNumber("0");
		}

		if (StrUtil.isNullOrEmpty(clue.getSerialNumber())) {
			clue.setSerialNumber(autoGenerateNum());
		}

		if (0 == dao.isExistClue(clue.getId())) {
			dao.saveClue(clue);
		} else {
			dao.updateClue(clue);
		}
		// TODO 20170522添加将保存初判的方法移至这里
		saveOrUpdateClueJudge(clue.getmClueJudge());
		return ResultState.Success;
	}

	public Clue getClue(String id) {
		// return dao.getClue(id);
		// TODO 20170522添加
		Clue clue = dao.getClue(id);
		ClueJudge clueJudge = new ClueJudge();
//		ClueAudit clueAudit = new ClueAudit();
		List<ClueAudit> audits=dao.getClueAuditListByClueID(id);//查询审核列表 初判为1，办结为2 到前台进行筛选
		ClueEnd clueEnd=new ClueEnd();
		// 获取初判类型
		int clueType = dao.getClueType(clue.getId());
		if (clueType == 3) {
			// 属于范围
			clueJudge = dao.getClueJudgeFromShuYu(clue);
//			clueAudit = dao.getClueAuditByClueID(id);
			clueEnd=dao.getClueEndByClueID(id);
		} else if (clueType == 2) {
			// 不属于
			clueJudge = dao.getClueJudgeFromNotShuYu(clue);
		}
//		if(clueAudit!=null){
//			clue.setcAudit(clueAudit);
//		}
		if(audits.size()>0){
			clue.setClueAudits(audits);
		}
		if(clueEnd!=null){
			clue.setcEnd(clueEnd);
		}
		clue.setmClueJudge(clueJudge);

		return clue;
	}

	public PagingEntity<Clue> getClues(Page page) {
		Map<String, Object> map = new HashMap<String, Object>();
		for (Condition condition : page.getConditions()) {
			if (!StrUtil.isNullOrEmpty(condition.getValue())) {
				//判断线索状态筛选
				if(condition.getKey().equals("trialState")){
					if(condition.getValue().equals("0")){
//						condition.setValue("RegistNode or t.NODE = EndNode");
						map.put("RegistNode", "RegistNode");
						map.put("EndNode", "EndNode");
					}else if(condition.getValue().equals("1")){
//						condition.setValue("JudgeAuditNode or t.NODE = EndAuditNode");
						map.put("JudgeAuditNode", "JudgeAuditNode");
						map.put("EndAuditNode", "EndAuditNode");
					}else if(condition.getValue().equals("2")){
//						condition.setValue("UnHandleNode or t.NODE = SubHandleNode or t.NODE = FinishNode");
						map.put("UnHandleNode", "UnHandleNode");
						map.put("SubHandleNode", "SubHandleNode");
						map.put("FinishNode", "FinishNode");
					}
					map.put(condition.getKey(), condition.getValue());
				}else if(condition.getKey().equals("illegalType")&&condition.getValue().equals("0800")){
					//TODO 0800表示查询全部的违法类型，如果前端传这个，就过滤掉这个查询条件
				}else if(condition.getKey().equals("source")&&condition.getValue().equals("0900")){
					//TODO 0900表示查询全部的线索来源，如果前端传这个，就过滤掉这个查询条件
				}else{
					map.put(condition.getKey(), condition.getValue());
				}
			}
		}

		map.put("startNum", page.getPageSize() * (page.getPageNo() - 1) + 1);
		map.put("endNum", page.getPageSize() * page.getPageNo());

		int count = dao.getClueCount(map);
		List<Clue> list = dao.getClues(map);
		for(Clue clue:list){
			Region region= (Region) RegionCache.get(clue.getRegionId());
			clue.setregionIdName(region.getRegionName());
		}
        //这里对查询的结果进行遍历 给行政区域赋值
		PagingEntity<Clue> entity = new PagingEntity<Clue>();
		entity.PageNo = page.getPageNo();
		entity.PageSize = page.getPageSize();
		entity.PageCount = count == 0 ? 0 : (count - 1) / page.getPageSize() + 1; // 由于计算pageCount存在整除的情况，所以计算的时候先减1在除以pageSize
		entity.RecordCount = count;
		entity.CurrentList = list;
		entity.PageCount = (entity.RecordCount / entity.PageSize) + (entity.RecordCount % entity.PageSize > 0 ? 1 : 0);
		return entity;
	}

	public List<IllegalClueStatistic> getIllegalClueStatistic(IllegalClueStatistic i) {
		return dao.getIllegalClueStatistic(i);
	}

	public void updateNode(WFObject next) {
		dao.updateCurrentNode(next.getInstanceID(), next.CurrentNode);

	}

	/**
	 * 生成举报线索编号
	 * 
	 * @return
	 */
	private String autoGenerateNum() {
		try {
			// 加锁
			lock.lock();
			User user = getCurrentUser();
			int regionId = orgDao.getRegionIdByOrgID(user.getOrganizationID());
			// Region region = user.getRegions()[0];
			Date date = Calendar.getInstance().getTime();
			String prefixNum = regionId + String.format("%tF", date).replace("-", "");

			int sequenceNum = 0;
			Object object = DictCache.get("IllegalClueNum" + prefixNum);
//			if (null == object) {
//				String strNum = dao.getNum(prefixNum);
//				sequenceNum = StrUtil.ConvertToInt(strNum);
//			} else {
//				sequenceNum = StrUtil.ConvertToInt(object.toString());
//			}
			if (null == object) {
				String strNum = dao.getNum(prefixNum);
				if (StrUtil.isNullOrEmpty(strNum)) {
					sequenceNum = 1;
				} else {
					strNum = strNum.substring(strNum.length() - 4);
					sequenceNum = StrUtil.ConvertToInt(strNum);
				}
			} else {
				sequenceNum = StrUtil.ConvertToInt(object.toString());
			}
			sequenceNum++;
			DictCache.put("IllegalClueNum" + prefixNum, sequenceNum);
			return prefixNum + String.format("%04d", sequenceNum);
		} catch (Exception e) {
			logger.debug(e.getMessage());
			return "";
		} finally {
			// 释放锁
			lock.unlock();
		}
	}



}
