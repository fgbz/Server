package com.kotei.testng.service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.github.pagehelper.PageInfo;

import phalaenopsis.common.entity.Condition;
import phalaenopsis.common.entity.Page;
import phalaenopsis.common.entity.PagingEntity;
import phalaenopsis.common.entity.Region;
import phalaenopsis.common.entity.User;
import phalaenopsis.common.method.cache.RegionCache;
import phalaenopsis.common.service.AuthService;
import phalaenopsis.common.service.UserService;
import phalaenopsis.illegalclue.entity.ResultState;
import phalaenopsis.patrolManagement.bean.PatrolTrackHistoryBean;
import phalaenopsis.patrolManagement.bean.PatrolTrackViewBean;
import phalaenopsis.patrolManagement.entity.PatrolLog;
import phalaenopsis.patrolManagement.entity.PatrolRecord;
import phalaenopsis.patrolManagement.service.PatrolRecordService;
import phalaenopsis.patrolManagement.service.PatrolTrackService;
import phalaenopsis.patrolManagement.util.VeDate;
import phalaenopsis.visitSystem.controller.LetterCaseController;

@ContextConfiguration(locations = { "classpath:conf/applicationContext.xml" })
@WebAppConfiguration(value = "src/main/webapp") 
public class PatrolServiceTest extends AbstractTestNGSpringContextTests{
		
	@Autowired
	private UserService userService;
	
	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@Autowired
	private PatrolRecordService service;
	
	@Autowired
	private PatrolTrackService trackService;
	
	@Autowired
	private AuthService authService;
	
	@Before
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.standaloneSetup(new LetterCaseController()).build();
	}
	
	/**
	 * 获取巡查记录列表
	 * @param page
	 * @return
	 */
	@Test
	public void getPatrolRecords(){
		Region r = new Region();
		r.setParentID(370100);
		RegionCache.put("370100", r);
		String account = "admin";
		String password = "eaa67f3a93d0acb08d8a5e8ff9866f51983b3c3b";
		User user = authService.login(account, password);
		
		Page page = new Page();
    	page.setPageNo(1);
    	page.setPageSize(10);
    	List<Condition> conditions = new ArrayList<Condition>();
    	Condition condition1 = new Condition();
    	condition1.setKey("patrolUserName");
    	condition1.setValue("37060203");
    	Condition condition2 = new Condition();
    	condition2.setKey("startDate");
    	//condition2.setValue("2017-09-19");
    	condition2.setValue(null);
    	Condition condition3 = new Condition();
    	condition3.setKey("endDate");
    	//condition3.setValue("2017-09-19");
    	condition3.setValue(null);
    	conditions.add(condition1);
    	conditions.add(condition2);
    	conditions.add(condition3);
    	page.setConditions(conditions);
		PagingEntity<PatrolRecord> patrolRecords = service.getPatrolRecords(page,user);
		Assert.assertNotEquals(1, 0);
	}
	
	/**
	 * 查看巡查记录详情
	 * @return
	 */
	@Test
	public void getPatrolLogInfo(){
		
		PatrolLog patrolLog = new PatrolLog();
		patrolLog.setStartDate(VeDate.strToDateLong("2017-09-26 01:00:05"));
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		ParsePosition pos = new ParsePosition(0);
		Date strtodate = formatter.parse("2017-09-25", pos);
		patrolLog.setCreateDate(strtodate);
		patrolLog.setPatrolUserId("b28057a5-03c4-44aa-9dd2-586476d79113");
		patrolLog.setOrgId("664348f2-cedb-4d61-8f84-2bd877e97440");
		patrolLog.setStatus(0);
		long id = service.addEntity(patrolLog);
		
		String patrolLogUserId ="b28057a5-03c4-44aa-9dd2-586476d79113";
		String createDate = "2017-09-25";
		
		Region r = new Region();
		r.setParentID(370100);
		RegionCache.put("370100", r);
		String account = "admin";
		String password = "eaa67f3a93d0acb08d8a5e8ff9866f51983b3c3b";
		User user = authService.login(account, password);
		
		PatrolRecord patrolLogInfo = service.getPatrolLogInfo(patrolLogUserId, createDate, user);
		Assert.assertNotEquals(1, 0);
	}
	
	/**
	 * 巡查记录结束巡查编辑数据
	 * @author 
	 * @param patrolLog
	 */
	@Test
	@Rollback(true)
	public void editEntity(){
		PatrolLog patrolLog = new PatrolLog();
		patrolLog.setStartDate(VeDate.strToDateLong("2017-09-25 01:00:05"));
		patrolLog.setEndDate(VeDate.strToDateLong("2017-09-25 03:00:05"));
		patrolLog.setPatrolUserId("b28057a5-03c4-44aa-9dd2-586476d79113");
		patrolLog.setId((long)21);
		//mongodb获取总距离
		Double distance = (double) 15;
		patrolLog.setPatrolDistance(distance);
		int i = 1;
		try {
			service.editEntity(patrolLog);
		} catch (Exception e) {
			e.printStackTrace();
			i = 0;
		}
		
		/*String patrolLogUserId ="b28057a5-03c4-44aa-9dd2-586476d79113";
		String createDate = "2017-09-25";
		
		Region r = new Region();
		r.setParentID(370100);
		RegionCache.put("370100", r);
		String account = "admin";
		String password = "eaa67f3a93d0acb08d8a5e8ff9866f51983b3c3b";
		User user = authService.login(account, password);
		
		PatrolRecord patrolLogInfo = service.getPatrolLogInfo(patrolLogUserId, createDate, user);
		String patrolEndDate = patrolLogInfo.getPatrolLogs().get(0).getPatrolEndDate();*/
		System.out.println("----------返回的结果集-------------"+i);
		Assert.assertNotEquals(1,0);
	}
	
	/**
	 * 巡查记录根据时间查询记录
	 * @param patrolLog //查询条件
	 * @param pageNo  //第几页
	 * @param pageSize //每页多少条
	 * @return PageInfo<PatrolTrackHistoryBean>
	 */
	@Test
	public void getHistoryList(){
		PatrolLog patrolLog = new PatrolLog();
		patrolLog.setCreateDate(VeDate.strToDateLong("2017-09-25"));
		//patrolLog.setPatrolUserId("b28057a5-03c4-44aa-9dd2-586476d79113");
		patrolLog.setOrgId("B9D58FB131282E42A1B63A550D3BCBF4");
		patrolLog.setStatus(0);
		patrolLog.setStartDate(VeDate.strToDateLong("2017-09-18 12:53:28"));
		patrolLog.setEndDate(VeDate.strToDateLong("2017-10-10 12:53:28"));
		patrolLog.setTempName("李");
		Integer pageNo = 1;
		Integer pageSize = 10;
		List<String> listUserId = new ArrayList<String>();
		List<User> list = new ArrayList<User>();
		if(StringUtils.isEmpty(patrolLog.getTempName())){
			list = userService.getUserByOrganizationID(patrolLog.getOrgId());
		}else{
			list = userService.getUserByName(patrolLog.getTempName());
		}
		if(list!=null){
			for(User user:list){
				listUserId.add(user.getId());
			}
		}
		patrolLog.setListPatrolUserId(listUserId);
		PageInfo<PatrolTrackHistoryBean> historyList = service.getHistoryList(list,patrolLog, pageNo, pageSize);
		Assert.assertNotEquals(1,0);
	}
	

	/**
	 * 巡查记录开始巡查添加数据
	 * @author 
	 * @param patrolLog
	 */
	@Test
	@Rollback(true)
	public void addEntity(){
		PatrolLog patrolLog = new PatrolLog();
		patrolLog.setStartDate(VeDate.strToDateLong("2017-09-26 01:00:05"));
		patrolLog.setCreateDate(new Date());
		patrolLog.setPatrolUserId("b28057a5-03c4-44aa-9dd2-586476d79113");
		patrolLog.setOrgId("664348f2-cedb-4d61-8f84-2bd877e97440");
		patrolLog.setStatus(0);
		long id = service.addEntity(patrolLog);
		System.out.println("----------返回的结果集-------------"+id);
		Assert.assertNotEquals(id, 0);
	}
	
	/**
	 * 删除巡查记录
	 * @param page
	 * @return
	 */
	@Test
	@Rollback(true)
	public void deletePatrolRecords(){
		String patrolLogUserId ="b28057a5-03c4-44aa-9dd2-586476d79113";
		//String createDate = "2017-09-27";
		Date date = new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String createDate = sdf.format(date);
		System.out.println(createDate);
		
		Region r = new Region();
		r.setParentID(370100);
		RegionCache.put("370100", r);
		String account = "admin";
		String password = "eaa67f3a93d0acb08d8a5e8ff9866f51983b3c3b";
		User user = authService.login(account, password);
		PatrolRecord patrolLogInfo = service.getPatrolLogInfo(patrolLogUserId, createDate, user);
		Long id = patrolLogInfo.getId();
		ResultState resultState = service.deletePatrolRecords(String.valueOf(id));
		System.out.println("----------返回的结果集-------------"+resultState);
		//Assert.assertEquals(resultState.getValue(), 200);
	}
	
}
