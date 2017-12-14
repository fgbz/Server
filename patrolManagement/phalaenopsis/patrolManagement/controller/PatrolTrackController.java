package phalaenopsis.patrolManagement.controller;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import phalaenopsis.common.entity.User;
import phalaenopsis.common.method.Basis;
import phalaenopsis.common.service.UserService;
import phalaenopsis.patrolManagement.bean.PatrolTrackAuditBean;
import phalaenopsis.patrolManagement.bean.PatrolTrackEntity;
import phalaenopsis.patrolManagement.bean.PatrolTrackFormBean;
import phalaenopsis.patrolManagement.bean.PatrolTrackViewBean;
import phalaenopsis.patrolManagement.entity.PatrolLog;
import phalaenopsis.patrolManagement.service.PatrolRecordService;
import phalaenopsis.patrolManagement.service.PatrolTrackService;
import phalaenopsis.patrolManagement.util.VeDate;

/**
 * 巡查监控
 * @author weiz2902
 *
 */
@RestController
@RequestMapping("/patrol/trackService")
public class PatrolTrackController extends Basis{
	private static Logger logger = Logger.getLogger(PatrolTrackController.class);
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PatrolTrackService patrolTrackService;
	
	@Autowired
	private PatrolRecordService patrolRecordService;
	
	/**
	 * 巡查监控轨插入轨迹坐标数据
	 * @author weiz2902
	 * @param patrolTrackFormBean
	 * 2017-9-20
	 */
	@RequestMapping(value="/insertTrackMonitoring",method=RequestMethod.POST)
	@ResponseBody
	public String insertTrackMonitoring(@RequestBody String json){
		try {
			ObjectMapper mapper = new ObjectMapper();
			PatrolTrackFormBean patrolTrackFormBean = mapper.readValue(json, PatrolTrackFormBean.class);
			patrolTrackService.insert(patrolTrackFormBean,patrolTrackFormBean.getPatrolUserId());
			return "success";
		} catch (Exception e) {
			logger.info("巡查监控坐标点入库失败!");
			return "failed";
		}
		
	}
	
	
	/**
	 * 巡查监控轨迹跟踪
	 * @author weiz2902
	 * @param patrolUserId,startTime,endTime
	 * 2017-9-20
	 */
	@RequestMapping(value="/getTrackingAuditList",method=RequestMethod.POST)
	@ResponseBody
	public PatrolTrackEntity getTrackingAuditList(@RequestBody PatrolTrackAuditBean patrolTrackAuditBean){
		PatrolTrackEntity patrolTrackEntity = new PatrolTrackEntity();
		double distance = 0d;
		List<Map> data = new ArrayList<Map>();
		PatrolTrackViewBean patrolTrackViewBean = new PatrolTrackViewBean();
		User user = userService.getUser(patrolTrackAuditBean.getPatrolUserId());
		patrolTrackViewBean.setPatrolUsername(user.getName());
		patrolTrackViewBean.setPatrolOrgname(user.getOrganizationName());
		patrolTrackViewBean.setFalg("success");
		String startTime = patrolTrackAuditBean.getStartTime();
		String endTime = patrolTrackAuditBean.getEndTime();
		String []sts = startTime.split(",");
		String []ets = endTime.split(",");
		long diff = 0l;
		if(ets!=null&&ets.length>0){
			patrolTrackEntity.setStartTime(sts[0]);
			patrolTrackEntity.setEndTime(ets[ets.length-1]);
			for(int i=0;i<ets.length;i++){
				Map map = new HashMap();
				String patrolSpacer = VeDate.getHHmmss(VeDate.strToDateLong(sts[i]))+"-"+VeDate.getHHmmss(VeDate.strToDateLong(ets[i]));
				//patrolTrackViewBean.setTime(patrolSpacer);
				PatrolTrackViewBean pb = patrolTrackService.getTrackingAuditList(VeDate.strToDateLong(sts[i]),VeDate.strToDateLong(ets[i]),patrolTrackAuditBean.getPatrolUserId(),patrolTrackViewBean);
				//时间段
				map.put("time", patrolSpacer);
				map.put("patrolData", pb.getPatrolData());
				distance+= Double.valueOf(pb.getPatrolDistance());
				
				//计算时间
				Date startdate = VeDate.strToDateLong(sts[i]);
 				Date enddate = VeDate.strToDateLong(ets[i]);
 				long total =  enddate.getTime()-startdate.getTime();
 				if(enddate!=null&&startdate!=null){
    				 diff += total;
    			}
 				
 				//每一段时间秒数
				map.put("timeTotal", total/1000);
 				data.add(map);
 				
			}
			patrolTrackEntity.setData(data);
			//获取实时距离
			if(ets.length==1){
				PatrolLog patrolLog = new PatrolLog();
				patrolLog.setCreateDate(VeDate.strToDate(VeDate.getStringDateShort()));
				patrolLog.setPatrolUserId(patrolTrackAuditBean.getPatrolUserId());
				List<PatrolLog> list = patrolRecordService.getListByCreateTime(patrolLog);
				if(list!=null&&list.size()>0){
					PatrolLog pa = list.get(0);
					PatrolLog paindex = list.get(list.size()-1);
					if(paindex.getEndDate()==null){
						distance = patrolTrackService.getTrackingDistance(pa.getStartDate(), VeDate.strToDateLong(ets[0]), patrolTrackAuditBean.getPatrolUserId());
					}
				}
			}
		}
		
		long hours = diff/ (1000 * 60 * 60);
		diff = diff - hours * (1000 * 60 * 60);
        long minutes = diff/ (1000 * 60);
        diff = diff - minutes*(1000 * 60);
        long seconds =diff/ 1000;
        String h = "";
        if(hours<10){
        	h = "0"+hours;
        }else{
        	h = hours+"";
        }
        String m = "";
        if(minutes<10){
        	m = "0"+minutes;
        }else{
        	m = minutes+"";
        }
        String s = "";
        if(seconds<10){
        	s = "0"+seconds;
        }else{
        	s = seconds+"";
        }
        String patrolTimetotal = h+":"+m+":"+s;
        patrolTrackEntity.setTimeSpace(patrolTimetotal);
		patrolTrackEntity.setFalg(patrolTrackViewBean.getFalg());
		patrolTrackEntity.setPatrolDistance(String.valueOf(distance));
		patrolTrackEntity.setPatrolOrgname(user.getOrganizationName());
		patrolTrackEntity.setPatrolUsername(user.getName());
		return patrolTrackEntity;
	}
	
}
