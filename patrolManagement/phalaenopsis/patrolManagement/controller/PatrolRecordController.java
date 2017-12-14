package phalaenopsis.patrolManagement.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;

import phalaenopsis.allWeather.service.AllWeatherReportService;
import phalaenopsis.common.entity.Page;
import phalaenopsis.common.entity.PagingEntity;
import phalaenopsis.common.entity.TreeNode;
import phalaenopsis.common.entity.User;
import phalaenopsis.common.method.Basis;
import phalaenopsis.common.service.OrganizationService;
import phalaenopsis.common.service.UserService;
import phalaenopsis.illegalclue.entity.ResultState;
import phalaenopsis.patrolManagement.bean.PatrolTrackHistoryBean;
import phalaenopsis.patrolManagement.entity.PatrolLog;
import phalaenopsis.patrolManagement.entity.PatrolRecord;
import phalaenopsis.patrolManagement.entity.PatrolUser;
import phalaenopsis.patrolManagement.service.PatrolRecordService;
import phalaenopsis.patrolManagement.service.PatrolTrackService;
import phalaenopsis.patrolManagement.util.VeDate;

/**
 * 巡查记录接口
 * @author jund
 * 2017-9-15
 */
@RestController
@RequestMapping("/patrol/patrolRecordService")
public class PatrolRecordController extends Basis {
	
	@Autowired
	private UserService userService;
	@Autowired
	private OrganizationService organizationService;
	
	@Autowired
	private PatrolRecordService patrolRecordService;
	
	@Autowired
	private PatrolTrackService patrolTrackService;
	private static Logger logger = LoggerFactory.getLogger(AllWeatherReportService.class);

	/**
	 * 巡查记录开始巡查添加数据
	 * @author weiz2902
	 * @param patrolLog
	 * 2017-9-20
	 */
	@RequestMapping(value = "/addPatrolLog",method=RequestMethod.POST)
	@ResponseBody
	public long addPatrolLog(@RequestBody String json){
		long id=0l;
		try {
			ObjectMapper mapper = new ObjectMapper();
			PatrolLog patrolLog = mapper.readValue(json, PatrolLog.class);
			patrolLog.setStartDate(VeDate.strToDateLong(patrolLog.getTempStartDate()));
			patrolLog.setCreateDate(new Date());
			id = patrolRecordService.addEntity(patrolLog);
		} catch (Exception e) {
			logger.info("开始巡查记录添加失败!");
		}
		return id;
	}
	
	/**
	 * 巡查记录结束巡查编辑数据
	 * @author weiz2902
	 * @param patrolLog
	 * 2017-9-20
	 */
	@RequestMapping(value = "/updatePatrolLog",method=RequestMethod.POST)
	@ResponseBody
	public String updatePatrolLog(@RequestBody String json){
		try {
			ObjectMapper mapper = new ObjectMapper();
			PatrolLog patrolLog = mapper.readValue(json, PatrolLog.class);
			patrolLog.setStartDate(VeDate.strToDateLong(patrolLog.getTempStartDate()));
			patrolLog.setEndDate(VeDate.strToDateLong(patrolLog.getTempEndDate()));
			//patrolLog.setPatrolContent(patrolLog.getPatrolContent());
			patrolLog.setStatus(0);
			//mongodb获取总距离
			double distance = patrolTrackService.getTrackingDistance(patrolLog.getStartDate(), patrolLog.getEndDate(), patrolLog.getPatrolUserId());
			//PatrolTrackViewBean patrolTrackViewBean = patrolTrackService.getTrackingAuditList(patrolLog.getStartDate(), patrolLog.getEndDate(), patrolLog.getPatrolUserId(), new PatrolTrackViewBean());
			patrolLog.setPatrolDistance(distance);
			patrolRecordService.editEntity(patrolLog);
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("开始巡查记录编辑失败!");
			return "failed";
		}
	}
	
	/**
	 * 巡查记录根据时间查询记录
	 * @param patrolLog //查询条件
	 * @param pageNo  //第几页
	 * @param pageSize //每页多少条
	 * 2017-9-20
	 * @return PageInfo<PatrolTrackHistoryBean>
	 */
	@RequestMapping(value = "/getHistoryList",method=RequestMethod.POST)
	@ResponseBody
	public PageInfo<PatrolTrackHistoryBean> getHistoryList(@RequestBody PatrolLog patrolLog){
		List<String> listUserId = new ArrayList<String>();
//		List<String> listOrgId = new ArrayList<String>();
		List<User> list = new ArrayList<User>();
		List<TreeNode> listTree = organizationService.GetOrgsTreeByParentID(patrolLog.getOrgId());
		List<String> tree = new ArrayList<String>();
		if(listTree!=null&&listTree.size()>0){
			for(TreeNode tr:listTree){
				tree.add(tr.getObjectID());
			}
		}
		if(StringUtils.isEmpty(patrolLog.getTempName())){
			if(tree!=null&&tree.size()>0){
				User user = new User();
				user.setOrganizationsID(tree);
				list = userService.getUserByOrganizationsID(user);
			}
		}else{
			list = userService.getUserByName(patrolLog.getTempName());
		}
		if(list!=null&&list.size()>0){
			for(User user:list){
				listUserId.add(user.getId());
				//listOrgId.add(user.getOrganizationID());
			}
		}
//		else{
//			return  new PageInfo<PatrolTrackHistoryBean>();
//		}
		patrolLog.setListPatrolUserId(listUserId);
		patrolLog.setListPatrolOrgId(tree);
		patrolLog.setStartDate(VeDate.strToDate(patrolLog.getTempStartDate()));
		patrolLog.setEndDate(VeDate.strToDate(patrolLog.getTempEndDate()));
		return patrolRecordService.getHistoryList(list,patrolLog, patrolLog.getPageNo(), patrolLog.getPageSize());
	}
	
	/**
	 * 获取巡查记录列表
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/getPatrolRecords",method = RequestMethod.POST)
	@ResponseBody
	public PagingEntity<PatrolRecord> getPatrolRecords(@RequestBody Page page){
		User user = getCurrentUser();
		return patrolRecordService.getPatrolRecords(page,user);
	}
	
	/**
	 * 删除巡查记录
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/deletePatrolRecords",method = RequestMethod.DELETE)
	@ResponseBody
	public ResultState deletePatrolRecords(String id){
		if(id == null || id.equals("")){
			return ResultState.Failed;
		}
		return patrolRecordService.deletePatrolRecords(id);
	}
	
	/**
	 * 查看巡查记录详情
	 * @return
	 */
	@RequestMapping(value = "/getPatrolLogInfo",method = RequestMethod.GET)
	@ResponseBody
	public PatrolRecord getPatrolLogInfo(String patrolLogUserId, String createDate){
		if(patrolLogUserId == null || createDate == null || patrolLogUserId.equals("") || createDate.equals("")){
			PatrolRecord patrolRecord = new PatrolRecord();
			return patrolRecord;
		}
		User user = getCurrentUser();
		
		return patrolRecordService.getPatrolLogInfo(patrolLogUserId,createDate,user);
	}
	
	/**
	 * 查看巡查记录
	 * @return
	 */
	@RequestMapping(value = "/getFirstPatrolId",method = RequestMethod.GET)
	@ResponseBody
	public String getFirstPatrolId(String patrolLogId){
		if(patrolLogId == null || patrolLogId.equals("")){
			return patrolLogId;
		}
		
		return patrolRecordService.getFirstPatrolId(patrolLogId);
	}
	
	/**
	 * 根据检索内容查询用户和所属机构
	 * @return
	 */
	@RequestMapping(value = "/getOrgId",method = RequestMethod.GET)
	@ResponseBody
	public List<PatrolUser> getOrgId(String info){
		if(info == null || info.equals("")){
			return null;
		}
		User user = getCurrentUser();
		return patrolRecordService.getOrgId(info,user);
	}
	
	/**
	 * 根据检索内容查询用户的位置和在线状态
	 * @return
	 */
	@RequestMapping(value = "/getUserInfo",method = RequestMethod.POST)
	@ResponseBody
	public PagingEntity<User> getUserInfo(@RequestBody Page page) {
		return patrolRecordService.getUserInfo(page);
	}
	
	/**
	 * 根据检索内容查询用户的位置和在线状态(在线用户)
	 * @return
	 */
	@RequestMapping(value = "/getOnlineUserInfo",method = RequestMethod.GET)
	@ResponseBody
	public List<User> getOnlineUserInfo(String orgId) {
		return patrolRecordService.getOnlineUserInfo(orgId);
	}
	/**
	 * 获取组织在线离线人数
	 * @return
	 */
	@RequestMapping(value = "/getOrgInfo",method = RequestMethod.GET)
	@ResponseBody
	public List<TreeNode> getOrgInfo() {
		User user = getCurrentUser();
		return patrolRecordService.getOrgInfo(user);
	}
	/**
	 * 获取人员坐标，距离
	 * @return
	 */
	@RequestMapping(value = "/getPointUserInfo",method = RequestMethod.GET)
	@ResponseBody
	public Double getPointUserInfo(String userId) {
		return patrolRecordService.getPointUserInfo(userId);
	}
}
