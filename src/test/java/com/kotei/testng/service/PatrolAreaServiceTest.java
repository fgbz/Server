package com.kotei.testng.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

import phalaenopsis.common.entity.Condition;
import phalaenopsis.common.entity.Page;
import phalaenopsis.common.entity.PagingEntity;
import phalaenopsis.common.entity.Region;
import phalaenopsis.common.entity.User;
import phalaenopsis.common.method.Tools.UUID64;
import phalaenopsis.common.method.cache.RegionCache;
import phalaenopsis.common.service.AuthService;
import phalaenopsis.illegalclue.entity.ResultState;
import phalaenopsis.patrolManagement.dao.PatrolAreaMapper;
import phalaenopsis.patrolManagement.entity.PatrolArea;
import phalaenopsis.patrolManagement.service.PatrolAreaService;
import phalaenopsis.visitSystem.controller.LetterCaseController;

@ContextConfiguration(locations = { "classpath:conf/applicationContext.xml" })
@WebAppConfiguration(value = "src/main/webapp") 
public class PatrolAreaServiceTest extends AbstractTestNGSpringContextTests{
		
	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@Autowired
	private PatrolAreaService service;
	
	@Autowired
	private AuthService authService;
	
	@Autowired
	private PatrolAreaMapper mapper;
	
	@Before
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.standaloneSetup(new LetterCaseController()).build();
	}
	
	/**
	 * 保存巡查区域
	 * @param patrolarea
	 * @return
	 */
	@Test
	@Rollback(true)
	public void saveAreaTest(){
		PatrolArea patrolArea = new PatrolArea();
						 
		patrolArea.setAreaName("巡查区域单元测试");
		patrolArea.setAreaType(1);
		patrolArea.setCoordi("{ COORDIS:[ [ 0 , 0 ] , [ 3 , 6 ] , [ 6 , 1 ] ]}");
		patrolArea.setPatrolLevel(1);
		patrolArea.setPatrolOrgid("8d1f4e9e-d33d-49cf-a4c0-4994a74ad170");
		
		ResultState state = service.saveArea(patrolArea);
		Assert.assertEquals(state.getValue(), 200);
	}
	
	/**
	 * 巡查区域列表
	 * @param page
	 * @param user
	 * @return
	 */
	@Test
	public void getAreaListTest() {
		PatrolArea patrolArea = new PatrolArea();
		patrolArea.setAreaName("巡查区域单元测试");
		patrolArea.setAreaType(1);
		patrolArea.setCoordi("{ COORDIS:[ [ 0 , 0 ] , [ 3 , 6 ] , [ 6 , 1 ] ]}");
		patrolArea.setPatrolLevel(1);
		patrolArea.setPatrolOrgid("8d1f4e9e-d33d-49cf-a4c0-4994a74ad170");
		ResultState state = service.saveArea(patrolArea);
		
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
    	condition1.setKey("patrolOrg");
    	//condition1.setValue("");
    	Condition condition2 = new Condition();
    	condition2.setKey("areaName");
    	condition2.setValue("");
    	Condition condition3 = new Condition();
    	condition3.setKey("areaType");
    	//condition3.setValue("2017-09-19");
    	Condition condition4 = new Condition();
    	condition4.setKey("patrolLevel");
    	condition4.setValue("3");
    	conditions.add(condition1);
    	conditions.add(condition2);
    	conditions.add(condition3);
    	conditions.add(condition4);
    	page.setConditions(conditions);
		PagingEntity<PatrolArea> entity = service.getAreaList(page, user);
		List<PatrolArea> currentList = entity.getCurrentList();
		//String areaName = currentList.get(0).getAreaName();
		Assert.assertNotEquals(currentList.size(), 0);
		
	}
	
	/**
	 * 删除巡查区域
	 * @param id
	 * @return
	 */
	@Test
	@Rollback(true)
	public void deleteAreaTest(){
		PatrolArea patrolArea = new PatrolArea();
		patrolArea.setAreaName("巡查区域单元测试");
		patrolArea.setAreaType(1);
		patrolArea.setCoordi("{ COORDIS:[ [ 0 , 0 ] , [ 3 , 6 ] , [ 6 , 1 ] ]}");
		patrolArea.setPatrolLevel(1);
		patrolArea.setPatrolOrgid("8d1f4e9e-d33d-49cf-a4c0-4994a74ad170");
		long value = UUID64.newUUID64().getValue();
		patrolArea.setId(value);
		Date createDate = new Date();
		patrolArea.setCreateDate(createDate);
		int i = mapper.saveArea(patrolArea);
		
		
		ResultState state = service.deleteArea(String.valueOf(value));
		Assert.assertEquals(state.getValue(), 200);
	}
	
}
