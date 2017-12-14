package com.kotei.testng.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.Assert;
import org.testng.annotations.Test;


import phalaenopsis.common.entity.User;
import phalaenopsis.common.service.UserService;
import phalaenopsis.patrolManagement.bean.PatrolTrackBean;
import phalaenopsis.patrolManagement.bean.PatrolTrackFormBean;
import phalaenopsis.patrolManagement.bean.PatrolTrackViewBean;
import phalaenopsis.patrolManagement.service.PatrolTrackService;
import phalaenopsis.patrolManagement.util.VeDate;

@ContextConfiguration(locations = { "classpath:conf/applicationContext.xml" })
@WebAppConfiguration(value = "src/main/webapp") 
public class PatrolTrackServiceTest extends AbstractTestNGSpringContextTests{
	@Autowired
	private PatrolTrackService patrolTrackService;
	
	@Autowired
	private UserService userService;
	
	@Before
	public void setUp() throws Exception {
		
	}
	
	/**
	 * 巡查监控轨插入轨迹坐标数据
	 * @author weiz2902
	 * @param patrolTrackFormBean
	 * 2017-9-20
	 */
	@Test
	public void insertTrackMonitoring(){
		
		PatrolTrackFormBean patrolTrackFormBean = new PatrolTrackFormBean();
		patrolTrackFormBean.setPatrolUserId("e9d0af70-6aa7-4108-8a08-ee4e5909ea68");
		List<PatrolTrackBean> patrolList = new ArrayList<PatrolTrackBean>();
		PatrolTrackBean patrolTrackBean = new PatrolTrackBean();
		patrolTrackBean.setPatrolDirection(123.213);
		patrolTrackBean.setPatrolOrgId("8d1f4e9e-d33d-49cf-a4c0-4994a74ad170");
		patrolTrackBean.setXpos(114.3969237);
		patrolTrackBean.setYpos(30.47926532);
		patrolTrackBean.setPatrolTime(new Date());
		patrolTrackBean.setTempPatrolTime("2017-9-27 15:45:00");
		patrolTrackBean.setPatrolUserId("e9d0af70-6aa7-4108-8a08-ee4e5909ea68");
		patrolList.add(patrolTrackBean);
		patrolTrackFormBean.setPatrolList(patrolList);
		patrolTrackService.insert(patrolTrackFormBean,patrolTrackFormBean.getPatrolUserId());
		PatrolTrackViewBean list = patrolTrackService.getTrackingAuditList(VeDate.strToDateLong("2017-9-27 15:43:00"),VeDate.strToDateLong("2017-9-27 15:49:00"),"e9d0af70-6aa7-4108-8a08-ee4e5909ea68",new PatrolTrackViewBean());
		//Assert.assertEquals(1,list.getPatrolData().size());
	}
	
	/**
	 * 巡查监控轨迹跟踪
	 * @author weiz2902
	 * @param patrolUserId,startTime,endTime
	 * 2017-9-20
	 */
	@Test
	public void getTrackingAuditList(){
		PatrolTrackViewBean patrolTrackViewBean = new PatrolTrackViewBean();
		String patrolUserId = "e9d0af70-6aa7-4108-8a08-ee4e5909ea68";
		User user = userService.getUser(patrolUserId);
		patrolTrackViewBean.setPatrolUsername(user.getName());
		patrolTrackViewBean.setPatrolOrgname(user.getOrganizationName());
		patrolTrackViewBean.setFalg("success");
		String startTime = "2017-9-27 09:27:00";
		String endTime = "2017-9-27 19:27:00";
		PatrolTrackViewBean list = patrolTrackService.getTrackingAuditList(VeDate.strToDateLong(startTime),VeDate.strToDateLong(endTime),patrolUserId,patrolTrackViewBean);
		
		//Assert.assertEquals(969,list.getPatrolData().size());
	}
	
	
}
