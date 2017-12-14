package phalaenopsis.patrolManagement.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import phalaenopsis.common.dao.IAttachmentDao;
import phalaenopsis.common.dao.OrganizationDao;
import phalaenopsis.common.dao.UserDao;
import phalaenopsis.common.entity.Condition;
import phalaenopsis.common.entity.Organization;
import phalaenopsis.common.entity.OrganizationGrade;
import phalaenopsis.common.entity.Page;
import phalaenopsis.common.entity.PagingEntity;
import phalaenopsis.common.entity.TreeNode;
import phalaenopsis.common.entity.User;
import phalaenopsis.common.entity.Attachment.Attachment;
import phalaenopsis.illegalclue.entity.ResultState;
import phalaenopsis.patrolManagement.bean.PatrolTrackEntityBean;
import phalaenopsis.patrolManagement.bean.PatrolTrackHistoryBean;
import phalaenopsis.patrolManagement.bean.PatrolTrackViewBean;
import phalaenopsis.patrolManagement.dao.PatrolRecordMapper;
import phalaenopsis.patrolManagement.dao.PatrolTrackDao;
import phalaenopsis.patrolManagement.entity.PatrolLog;
import phalaenopsis.patrolManagement.entity.PatrolRecord;
import phalaenopsis.patrolManagement.entity.PatrolUser;
import phalaenopsis.patrolManagement.entity.PatrolUserInfo;
import phalaenopsis.patrolManagement.util.VeDate;

@Service("patrolRecordService")
public class PatrolRecordService {
	@Autowired
	private PatrolRecordMapper mapper;
	
	@Autowired
	@Qualifier("attachmentDao")
	private IAttachmentDao iAttachmentDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private PatrolTrackDao patrolDao;
	
	@Autowired
	private OrganizationDao organizationDao;
	@Autowired
	private PatrolTrackDao patrolTrackDao;
	
	public long addEntity(PatrolLog patrolLog) {
		long id = mapper.querySeq();
		patrolLog.setId(id);
		mapper.addEntity(patrolLog);
		return id;
	}
	
	public void editEntity(PatrolLog patrolLog) {
		mapper.editEntity(patrolLog);
	}
	
	public List<PatrolLog> getListByCreateTime(PatrolLog patrolLog) {
		return mapper.getListByCreateTime(patrolLog);
	}
	
	public PageInfo<PatrolTrackHistoryBean> getHistoryList(List<User> userList,PatrolLog patrolLog, Integer pageNo,Integer pageSize) {
		//patrolLog.setCreateDate(VeDate.strToDate("2017-9-19"));
		//patrolLog.setPatrolUserId("b28057a5-03c4-44aa-9dd2-586476d79113");
		List<PatrolTrackHistoryBean> listpa = new ArrayList<PatrolTrackHistoryBean>();
//		List<PatrolLog> list = new ArrayList<PatrolLog>();
		//User user  = userDao.getUser(patrolLog.getPatrolUserId());
		PageHelper.startPage(pageNo, pageSize);
//		List<String> listUserId = patrolLog.getListPatrolUserId();
//		int size = listUserId.size();
//		int k = 0;
//		if(listUserId.size()>1000){
//			k = size/1000;
//		}
//		int f = size%1000;
//		if(f!=0){
//			k = k+1;
//		}
//		for(int i=0;i<k;i++){
//			int start = i*1000;
//			int end = i*1000 +1000;
//			if(end>size){
//				end = size-1;
//			}
//			List<String> ll = listUserId.subList(start, end);
//			patrolLog.setListPatrolUserId(ll);
//			list.addAll(mapper.getHistoryList(patrolLog));
//		}
		List<PatrolLog> list =mapper.getHistoryList(patrolLog);
		//用PageInfo对结果进行包装
        PageInfo<PatrolLog> page=new PageInfo<PatrolLog>(list);
        List<PatrolLog> ll = page.getList();
        if(ll!=null&&ll.size()>0){
        	 for(PatrolLog entity:list){
        		 PatrolTrackHistoryBean patrolTrackHistoryBean = new PatrolTrackHistoryBean();
     			//Date startdate = entity.getStartDate();
     			//Date enddate = entity.getEndDate();
        		if(userList!=null){
        			for(User user:userList){
        				if(user.getId().equals(entity.getPatrolUserId())&&user.getOrganizationID().equals(entity.getOrgId())){
        					patrolTrackHistoryBean.setPatrolUsername(user.getName());
        	     			patrolTrackHistoryBean.setPatrolOrgname(user.getOrganizationName());
        	     			break;
        				}
        			}
        		}
        		patrolTrackHistoryBean.setPatrolUserId(entity.getPatrolUserId());
     			patrolTrackHistoryBean.setPatrolTime(VeDate.dateToStr(entity.getCreateDate()));
     			
     			List listId= new ArrayList();
     			Map tempStartDateMap = new HashMap();
     			Map tempEndDateMap = new HashMap();
     			String tempStartDate = entity.getTempStartDate();
     			String tempEndDate = entity.getTempEndDate();
     			patrolTrackHistoryBean.setStartTime(tempStartDate);
     			patrolTrackHistoryBean.setEndTime(tempEndDate);
     			String id = entity.getTempId();
     			String []ids = id.split(",");
     			String []tempStartDates = tempStartDate.split(",");
     			String []tempEndDates = tempEndDate.split(",");
     			long diff = 0l;
     			for(int i=0;i<ids.length;i++){
     				tempStartDateMap.put(ids[i], tempStartDates[i]);
     				tempEndDateMap.put(ids[i], tempEndDates[i]);
     				listId.add(Long.valueOf(ids[i]));
     				Date startdate = VeDate.strToDateLong(tempStartDates[i]);
     				Date enddate = VeDate.strToDateLong(tempEndDates[i]);
     				if(enddate!=null&&startdate!=null){
        				 diff +=  enddate.getTime()-startdate.getTime();
        			}
     			}
     			//降序
     			Collections.sort(listId);
     			//获取巡查最新开始时间与最晚时间
     			String sd = (String) tempStartDateMap.get(String.valueOf(listId.get(0)));
     			String ed = (String) tempEndDateMap.get(String.valueOf(listId.get(listId.size()-1)));
     			
     			String start ="";
     			String end ="";
     			//时间排序
     			for(int i=0;i<listId.size();i++){
     				start += (String) tempStartDateMap.get(String.valueOf(listId.get(i)))+",";
         			end += (String) tempEndDateMap.get(String.valueOf(listId.get(i)))+",";
     			}
     			tempStartDate = start.substring(0, start.length()-1);
     			tempEndDate = end.substring(0, end.length()-1);
     			patrolTrackHistoryBean.setStartTime(tempStartDate);
     			patrolTrackHistoryBean.setEndTime(tempEndDate);
     			
     			String patrolSpacer = VeDate.getHHmmss(VeDate.strToDateLong(sd))+"-"+VeDate.getHHmmss(VeDate.strToDateLong(ed));
     			patrolTrackHistoryBean.setPatrolSpacer(patrolSpacer);
     			
     			long hours = diff/ (1000 * 60 * 60);
     			diff = diff - hours * (1000 * 60 * 60);
     	        long minutes = diff/ (1000 * 60);
     	        diff = diff - minutes*(1000 * 60);
     	        long seconds =diff/ 1000;
     	        String patrolTimetotal = hours+"小时"+minutes+"分"+seconds+"秒";
     	        patrolTrackHistoryBean.setPatrolTimetotal(patrolTimetotal);
     	        listpa.add(patrolTrackHistoryBean);
     		}
        }
        //转换bean
        PageInfo<PatrolTrackHistoryBean> pages = new PageInfo<PatrolTrackHistoryBean>();
        pages.setList(listpa);
        pages.setPageNum(page.getPageNum());
        pages.setPages(page.getPages());
        pages.setPages(page.getPages());
        pages.setTotal(page.getTotal());
	    return pages;
	}
	
	/**
	 * 巡查记录列表
	 * @param page
	 * @param user
	 * @return
	 * 
	 */
	public PagingEntity<PatrolRecord> getPatrolRecords(Page page, User user) {
		//String parentID = user.getOrganizationID();
		//List<TreeNode> orgsTrees = organizationController.GetOrgsTreeByParentID(parentID );
		Map<String, Object> map = new HashMap<String, Object>();
		for (Condition condition : page.getConditions()) {
			map.put(condition.getKey(), condition.getValue());
		}
		if(map.containsKey("startDate") && map.get("startDate") != null && !map.get("startDate").equals("")){
			map.put("startDate", VeDate.strToDate(String.valueOf(map.get("startDate"))));
		}
		if(map.containsKey("endDate") && map.get("endDate") != null && !map.get("endDate").equals("")){
			map.put("endDate", VeDate.strToDate(String.valueOf(map.get("endDate"))));
		}
		map.put("startNum", page.getPageSize() * (page.getPageNo() - 1) + 1);
		map.put("endNum", page.getPageSize() * page.getPageNo());
		String organizationID = user.getOrganizationID();
		map.put("organizationID", organizationID);
		
		int count = 0;
		List<PatrolRecord> list = null;
		if (OrganizationGrade.Province == user.getOrgGrade()) {
			//查找所有的巡查记录
			count = mapper.getAllRecordCount(map);
			list = mapper.getAllRecordList(map);
		}else{
			//查找该市及其下级的巡查记录
			count = mapper.getRecordCount(map);
			list = mapper.getRecordList(map);
		}
		
		if(list != null && list.size() > 0){
			for (int i = 0; i < list.size(); i++) {
				Date nowDate = list.get(i).getCreateDate();
				String patrolUserId = list.get(i).getPatrolUserId();
				//某人员同一天的巡查记录 order by starttime
				Map<String, Object> mapSames = new HashMap<String, Object>();
				mapSames.put("createDate", nowDate);
				mapSames.put("patrolUserId", patrolUserId);
				List<PatrolLog> sames =  mapper.getSamePatrolRecord(mapSames);
				Date patrolStartDate = sames.get(sames.size() - 1).getPatrolStartDate();
				Date patrolEndDate = sames.get(0).getPatrolEndDate();
				long patrolId = sames.get(sames.size() - 1).getId();
				Double patrolDisdance = mapper.getSamePatrolDistance(mapSames);
				String stringId = String.valueOf(patrolId);
				List<Attachment> attachments = iAttachmentDao.getAttachments(stringId);
				User patrolUser = userDao.getUser(patrolUserId);
				
				list.get(i).setId(patrolId);
				list.get(i).setStartDate(VeDate.dateToStrLong(patrolStartDate));
				if(patrolEndDate == null || patrolEndDate.equals("")){
					list.get(i).setEndDate("");
				}else{
					list.get(i).setEndDate(VeDate.dateToStrLong(patrolEndDate));
				}
				if(patrolDisdance == null || patrolDisdance.equals("")){
					patrolDisdance = (double) 0;
				}
				list.get(i).setPatrolDistance(patrolDisdance);
				list.get(i).setImageCount(attachments.size());
				list.get(i).setPatrolUserName(patrolUser.getName());
				
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
				String createDateStr = sdf.format(nowDate);
				list.get(i).setCreateDateStr(createDateStr);
			}
		}
		
		PagingEntity<PatrolRecord> entity = new PagingEntity<PatrolRecord>();
		entity.PageNo = page.getPageNo();//第几页
		entity.PageSize = page.getPageSize();//每页显示数
		entity.PageCount = count == 0 ? 0 : (count - 1) / page.getPageSize() + 1; // 由于计算pageCount存在整除的情况，所以计算的时候先减1在除以pageSize
		entity.RecordCount = count;//记录总条数
		entity.CurrentList = list;
		entity.PageCount = (entity.RecordCount / entity.PageSize) + (entity.RecordCount % entity.PageSize > 0 ? 1 : 0);//总页数
		return entity;
	}
	
	/**
	 * 删除巡查记录
	 * @param id
	 * @return
	 */
	public ResultState deletePatrolRecords(String id) {
		PatrolLog patrolLog = mapper.selectPatrolById(id);
		if(patrolLog == null || patrolLog.equals("")){
			return ResultState.Failed;
		}else{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("createDate", patrolLog.getCreateDate());
			map.put("patrolUserId", patrolLog.getPatrolUserId());
			int i = mapper.deletePatrolRecords(map);
			if(i > 0){
				return ResultState.Success;
			}else{
				return ResultState.Failed;
			}
		}
		
	}
	
	/**
	 * 查看巡查记录详情
	 * @param patrolLogUserId
	 * @param createDate
	 * @param user
	 * @return
	 */
	public PatrolRecord getPatrolLogInfo(String patrolLogUserId,String createDate,User user){
		Map<String, Object> mapSames = new HashMap<String, Object>();

		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date = null;
		try {
			date=sdf.parse(createDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		mapSames.put("createDate", date);
		mapSames.put("patrolUserId", patrolLogUserId);
		User patrolUser = userDao.getUser(patrolLogUserId);
		List<PatrolLog> sames =  mapper.getSamePatrolRecord(mapSames);
		Double patrolDisdance = mapper.getSamePatrolDistance(mapSames);
		List<Attachment> attachments = iAttachmentDao.getDescAttachments(String.valueOf(sames.get(sames.size() - 1).getId()));
		for (int i = 0; i < sames.size(); i++) {
			Date startTime = sames.get(i).getPatrolStartDate();
			Date endTime = sames.get(i).getPatrolEndDate();
			String toStrLong = VeDate.getHHmmss(startTime);
			String dateToStrLong = VeDate.getHHmmss(endTime);
			sames.get(i).setPatrolStartDateStr(toStrLong);
			sames.get(i).setPatrolEndDateStr(dateToStrLong);
			PatrolTrackViewBean patrolTrackViewBean = new PatrolTrackViewBean();
			patrolTrackViewBean.setPatrolOrgname(user.getName());
			patrolTrackViewBean.setPatrolOrgname(user.getOrganizationName());
			//PatrolTrackViewBean trackList = patrolDao.getTrackingAuditList(VeDate.strToDateLong(startTime),VeDate.strToDateLong(endTime),patrolLogUserId,patrolTrackViewBean);
			PatrolTrackViewBean trackList = patrolDao.getTrackingAuditList(startTime,endTime,patrolLogUserId,patrolTrackViewBean);
			
			sames.get(i).setPatrolTrackViewBean(trackList);
		}
		
		PatrolRecord patrolRecord = new PatrolRecord();
		patrolRecord.setId(sames.get(0).getId());
		patrolRecord.setPatrolUserId(sames.get(0).getPatrolUserId());
		patrolRecord.setPatrolUserName(patrolUser.getName());
		patrolRecord.setAttachments(attachments);
		patrolRecord.setPatrolLogs(sames);
		patrolRecord.setCreateDate(sames.get(0).getCreateDate());
		if(patrolDisdance == null || patrolDisdance.equals("")){
			patrolDisdance = (double) 0;
		}
		patrolRecord.setPatrolDistance(patrolDisdance);
		/*Map<String,String> attachmentsInfo = new HashMap<String, String>();
		for (int i = 0; i < attachments.size(); i++) {
			attachmentsInfo.put(attachments.get(i).getId(), attachments.get(i).getExplain());
		}
		patrolRecord.setAttachmentsInfo(attachmentsInfo);;*/
		patrolRecord.setCreateDateStr(createDate);
		return patrolRecord;
	}
	
	/**
	 * 查看上传图片所属BIZID
	 * @return
	 */
	public String getFirstPatrolId(String patrolLogId) {
		PatrolLog patrolLog = mapper.selectPatrolById(patrolLogId);
		if(patrolLog == null || patrolLog.equals("")){
			return patrolLogId;
		}
		String id = mapper.getFirstPatrolId(patrolLog);
		if(id == null || id.equals("")){
			return patrolLogId;
		}
		return id;
	}
	
	/**
	 * 根据检索内容查询用户和所属机构
	 * @return
	 */
	public List<PatrolUser> getOrgId(String info,User user) {
		String organizationID = user.getOrganizationID();
		List<PatrolUser> list = new ArrayList<>();
		//检索是否存在对应的机构号(当前用户权限下)
		Map<String,String> map = new HashMap<String, String>();
		map.put("info", info);
		map.put("organizationID", organizationID);
		//获取省级id，name
		Organization proOrg = mapper.getproOrg();
		List<Organization> organizations = mapper.getOrgInfoByName(map);
		if(organizations != null && organizations.size() != 0){
			for (int i = 0; i < organizations.size(); i++) {
				Organization organization = organizations.get(i);
				int grade = organization.getGrade();
				String organizationId = organization.getID();
				String organizationName = organization.getName();
				String parentId = organization.getParentID();
				PatrolUser patrolUser = getPatrolUserInfo(organizationId, organizationName, parentId, grade, proOrg);
				list.add(patrolUser);
			}
		}
		//检索是否存在对应的人员
		List<PatrolUserInfo> userInfos = mapper.getUserInfoByName(map);
		//获取省级id，name
		if(userInfos != null && userInfos.size() != 0){
			for (int i = 0; i < userInfos.size(); i++) {
				PatrolUserInfo userInfo = userInfos.get(i);
				int grade = userInfo.getOrgGrade();
				String organizationId = userInfo.getOrganizationID();
				String organizationName = userInfo.getOrganizationName();
				String parentId = userInfo.getParentId();
				PatrolUser patrolUser = getPatrolUserInfo(organizationId, organizationName, parentId, grade, proOrg);
				patrolUser.setUserId(userInfo.getUserID());
				patrolUser.setUserName(userInfo.getUserName());
				patrolUser.setIsOnline(userInfo.getIsOnline());
				PatrolTrackEntityBean patrolTrackEntityBean = patrolTrackDao.getAudit(userInfo.getUserID());
				patrolUser.setPatrolTrackEntityBean(patrolTrackEntityBean);
				
				list.add(patrolUser);
			}
		}
		
		return list;
	}
	
	/**
	 * 根据检索内容查询用户的位置和在线状态
	 * @return
	 */
	public PagingEntity<User> getUserInfo(Page page) {
		Map<String, Object> map = new HashMap<String, Object>();
		for (Condition condition : page.getConditions()) {
			map.put(condition.getKey(), condition.getValue());
		}
		map.put("startNum", page.getPageSize() * (page.getPageNo() - 1) + 1);
		map.put("endNum", page.getPageSize() * page.getPageNo());
		List<User> users = new ArrayList<User>();
		users = mapper.getUserByOrgId(map);
		int count = 0;
		count = mapper.getUserCountByOrgId(map);
		PagingEntity<User> entity = new PagingEntity<User>();
		entity.PageNo = page.getPageNo();//第几页
		entity.PageSize = page.getPageSize();//每页显示数
		entity.PageCount = count == 0 ? 0 : (count - 1) / page.getPageSize() + 1; // 由于计算pageCount存在整除的情况，所以计算的时候先减1在除以pageSize
		entity.RecordCount = count;//记录总条数
		entity.CurrentList = users;
		entity.PageCount = (entity.RecordCount / entity.PageSize) + (entity.RecordCount % entity.PageSize > 0 ? 1 : 0);//总页数
		return entity;
		
		
		/*Organization getproOrg = mapper.getproOrg();
		if(getproOrg.getID().equals(orgId)){
			user = mapper.getUserByGrade(orgId);
		}else{
			user = mapper.getUserByOrgId(orgId);
		}
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < user.size(); i++) {
			String userId = user.get(i).getId();
			list.add(userId);
		}
		List<PatrolTrackEntityBean> patrolTrackEntityBeans = patrolTrackDao.getAuditList(list);
		PatrolTrackEntityBean patrolTrackEntityBean = new PatrolTrackEntityBean();
		String userId = "";
		if(patrolTrackEntityBeans != null && patrolTrackEntityBeans.size() > 0){
			for (int i = 0; i < patrolTrackEntityBeans.size(); i++) {
				patrolTrackEntityBean = patrolTrackEntityBeans.get(i);
				userId = patrolTrackEntityBeans.get(i).getPatrol_usr_id();
				for(int j = 0; j < list.size(); j++){
					if(userId.equals(list.get(j))){
						user.get(j).setPatrolTrackEntityBean(patrolTrackEntityBean);
					}
				}
			}
		}
		return user;*/
	}
	
	/**
	 * 根据检索内容查询用户的位置和在线状态(在线用户)
	 * @return
	 */
	public List<User> getOnlineUserInfo(String orgId) {
		List<User> user = mapper.getOnlineUserByOrgId(orgId);
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < user.size(); i++) {
			String userId = user.get(i).getId();
			list.add(userId);
		}
		List<PatrolTrackEntityBean> patrolTrackEntityBeans;
		try {
			patrolTrackEntityBeans = patrolTrackDao.getAuditList(list);
		} catch (Exception e) {
			e.printStackTrace();
			return user;
		}
		PatrolTrackEntityBean patrolTrackEntityBean = new PatrolTrackEntityBean();
		String userId = "";
		if(patrolTrackEntityBeans != null && patrolTrackEntityBeans.size() > 0){
			for (int i = 0; i < patrolTrackEntityBeans.size(); i++) {
				patrolTrackEntityBean = patrolTrackEntityBeans.get(i);
				userId = patrolTrackEntityBeans.get(i).getPatrol_usr_id();
				for(int j = 0; j < list.size(); j++){
					if(userId.equals(list.get(j))){
						user.get(j).setPatrolTrackEntityBean(patrolTrackEntityBean);
					}
				}
			}
		}
		return user;
	}
	
	/**
	 * 获取组织在线离线人数
	 * @return
	 */
	public List<TreeNode> getOrgInfo(User user) {
		String organizationID = user.getOrganizationID();
		int onlineNum = 0;
		int lineNum = 0;
		int currentOnlineNum = 0;
		int currentLineNum = 0;
		List<TreeNode> orgsTree = organizationDao.GetOrgsTreeByParentID(organizationID);
		for (int i = 0; i < orgsTree.size(); i++) {
			onlineNum = mapper.getOnlineNum(orgsTree.get(i).getObjectID());
			lineNum = mapper.getlineNum(orgsTree.get(i).getObjectID());
			orgsTree.get(i).setLineNum(lineNum);
			orgsTree.get(i).setOnlineNum(onlineNum);
			//获取当前级的在线离线人数
			currentOnlineNum = mapper.getCurrentOnlineNum(orgsTree.get(i).getObjectID());
			currentLineNum = mapper.getCurrentlineNum(orgsTree.get(i).getObjectID());
			orgsTree.get(i).setCurrentOnlineNum(currentOnlineNum);
			orgsTree.get(i).setCurrentLineNum(currentLineNum);
		}
		return orgsTree;
	}
	
	public PatrolUser getPatrolUserInfo(String organizationId, String organizationName, String parentId, int grade, Organization proOrg) {

		PatrolUser patrolUser = new PatrolUser();
		patrolUser.setGrade(grade);
		patrolUser.setProOrgId(proOrg.getID());
		patrolUser.setProOrgName(proOrg.getName());
		patrolUser.setProOnlineNum(mapper.getOnlineNum(proOrg.getID()));
		patrolUser.setProlineNum(mapper.getlineNum(proOrg.getID()));
		int onlineNum = mapper.getOnlineNum(organizationId);
		int lineNum = mapper.getlineNum(organizationId);
		if(grade == 3){
			patrolUser.setProOrgId(organizationId);
			patrolUser.setProOrgName(organizationName);
			patrolUser.setProOnlineNum(mapper.getOnlineNum(organizationId));
			patrolUser.setProlineNum(mapper.getlineNum(organizationId));
		}
		if(grade == 2){
			patrolUser.setCityOrgId(organizationId);
			patrolUser.setCityOrgName(organizationName);
			patrolUser.setCityOnlineNum(onlineNum);
			patrolUser.setCitylineNum(lineNum);
		}if(grade == 1){
			patrolUser.setCityOrgId(parentId);
			//根据父级组织号获取组织名称
			Organization org = organizationDao.GetOrg(parentId);
			patrolUser.setCityOrgName(org.getName());
			patrolUser.setCityOnlineNum(mapper.getOnlineNum(parentId));
			patrolUser.setCitylineNum(mapper.getlineNum(parentId));
			/**
			 * 区县级
			 */
			patrolUser.setCountyOrgId(organizationId);
			patrolUser.setCountyOrgName(organizationName);
			patrolUser.setCountyOnlineNum(onlineNum);
			patrolUser.setCountylineNum(lineNum);
		}
		return patrolUser;
	}
	/**
	 * 获取距离
	 * @param userId
	 * @return
	 */
	public Double getPointUserInfo(String userId) {
		Date startTime = VeDate.getNowDateShort();
		Date endTime = VeDate.getNextDateShort();
		double distance = patrolTrackDao.getDistance(userId, startTime, endTime);
		return distance;
	}
}
