package com.kotei.testng.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
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
import phalaenopsis.common.method.Basis;
import phalaenopsis.common.method.cache.RegionCache;
import phalaenopsis.common.method.cache.UserCache;
import phalaenopsis.common.service.AuthService;
import phalaenopsis.illegalclue.entity.ResultState;
import phalaenopsis.visitSystem.controller.LetterCaseController;
import phalaenopsis.visitSystem.entity.XfDeals;
import phalaenopsis.visitSystem.entity.XfRegister;
import phalaenopsis.visitSystem.entity.XfRepeatItems;
import phalaenopsis.visitSystem.service.LetterCaseService;
import phalaenopsis.visitSystem.service.LetterCaseServicePartial;  

@ContextConfiguration(locations = { "classpath:conf/applicationContext.xml" })
@WebAppConfiguration(value = "src/main/webapp") 
public class ServiceNameTest extends AbstractTestNGSpringContextTests{
	
	
	@Autowired
    private WebApplicationContext wac;
	
	private MockMvc mockMvc;
	
	@Autowired
	private LetterCaseService service;
	@Autowired
	private LetterCaseServicePartial servicePartial;
	
	
	@Before  
    public void setUp() throws Exception {  
        mockMvc = MockMvcBuilders.standaloneSetup(new LetterCaseController()).build(); 
        
    }  
	
	

	/**
	 * 查询列表
	 * 
	 * @param page
	 *            参数
	 * @return PagingEntity
	 */
	/*@Test
	public void TestGetList() {
		Basis basis = new Basis();
		
    	Page page = new Page();
    	page.setPageNo(1);
    	page.setPageSize(10);
    	List<Condition> conditions = new ArrayList<Condition>();
    	Condition condition1 = new Condition();
    	condition1.setKey("type");
    	condition1.setValue("4");
    	Condition condition2 = new Condition();
    	condition2.setKey("provinceid");
    	condition2.setValue("370000");
    	conditions.add(condition1);
    	conditions.add(condition2);
    	page.setConditions(conditions);
    	PagingEntity<XfRegister> list = service.getList(page);
    	int size = list.CurrentList.size();
    	System.out.println("----------返回的结果集-------------"+list.CurrentList.size());
    	Assert.assertEquals(size, 2);
	}*/
	
	/**
	 * 保存或更新信访登记信息，重新信访登记信息
	 * 
	 * @param letterCase
	 * @return resultState
	 */
	/*@Test
	public void TestSaveOrUpdate() {
		ResultState resultState = null;
		for (int i = 0; i < 11; i++) {
			XfRegister register = new XfRegister();
			Date registerdate = new Date();
			register.setHomeaddress("武汉2");
			register.setGender(1);
			register.setAge(52);
			register.setPhoneno("15888888888");
			register.setIdentityno("320000195803226245");
			register.setVipornot(1);
			register.setXfpeoplecount(1);
			register.setSerialno("11111");
			register.setIssuedesc("土地纠纷问题");
			register.setXftype(1);
			register.setIssueaddress("新房归属地：光谷");
			register.setResposibleusername("admin");
			register.setArealevel(3);
			register.setRegisterdate(registerdate);
			register.setStatus(5);
			register.setXfname("信访人姓名1");
			register.setIssuenature("问题性质很严重");
			register.setLitiornot(0);
			register.setProvinceid((long) 370000);
			register.setCityid((long) 371600);
			register.setCountyid((long) 371626);
			register.setProstatus(2);
			register.setCitystatus(2);
			//ResultState resultState = service.saveOrUpdateLetterCase(register);
			
			resultState = service.saveOrUpdateLetterCase(register);
		}
    	System.out.println("----------返回的结果集-------------"+resultState);
    	Assert.assertEquals(resultState.getValue(), 200);
	}*/
	
	/**
	 * 根据信访Id查询信访信息
	 * @param id
	 * @return
	 */
	/*@Test
	public void TestGetLetterCaseById() {
		String id = "89789680320512";
		long registerId = Long.parseLong(id) ;
		XfRegister xfRegister = service.getLetterCaseById(registerId);
		long registerid = xfRegister.getRegisterid();
		System.out.println("----------返回的结果集-------------"+registerid);
		Assert.assertEquals(registerId, registerid);
	}*/
	
	
	
	/**
	 * 查询重复信访
	 * @param name 姓名
	 * @param idCard 身份证
	 * @return
	 */
	/*@Test
	public void TestGetRepeatList() {
		String name = "张三";
		String idCard = "420222199009180094";
		List<XfRegister> repeatList = service.getRepeatList(name, idCard);
		int size = repeatList.size();
		System.out.println("----------返回的结果集-------------"+size);
		Assert.assertEquals(size, 2);
	}*/
	 
	/**
	 * 审核意见
	 * @param xfDeals
	 * @return
	 */
	/*@Test
	public void TestAudit() {
		XfDeals xfDeals = new XfDeals();
		String registerId = "88454595608576";
		xfDeals.setRegisterId(Long.parseLong(registerId));
		xfDeals.setDealAdvice("不通过");
		xfDeals.setDealStatus((short) 2);
		ResultState resultState = servicePartial.audit(xfDeals);
		
		System.out.println("----------返回的结果集-------------"+resultState);
    	Assert.assertEquals(resultState.getValue(), 200);
	}*/
	
	/**
	 * 获取所有的审核信息
	 * @param registerId
	 * @return
	 */
	@Test
	public void TestGetXfDeals() {
		String registerId = "87728347676672";
		List<XfDeals> xfDeals = servicePartial.getXfDeals(registerId);
		System.out.println("----------返回的结果集-------------"+xfDeals.size());
		Assert.assertEquals(xfDeals.size(),19);
		
	}
	
	/**
	  * 删除信访
	  * @param login
	  * @return
	  * TODO
	  */
	@Test
	public void TestDelete() {
		String registerId = "89791341264900";
		ResultState resultState = servicePartial.delete(registerId);
		System.out.println("----------返回的结果集-------------"+resultState);
    	Assert.assertEquals(resultState.getValue(), 200);
	}
	
	/**
	 * 保存，创建重复件信息
	 * @param repeatItems
	 * @return
	 */
	/*@Test
	public void TestSaveRepeat() {
		XfRepeatItems repeatItems = new XfRepeatItems();
		Long register = (long) 111;
		repeatItems.setXfregisterid(register);
		ResultState saveRepeat = service.saveRepeat(repeatItems);
		System.out.println("----------返回的结果集-------------"+saveRepeat.getValue());
		Assert.assertEquals(saveRepeat.getValue(),200);
	}*/
	/**
	  * 登录
	  * @param login
	  * @return
	  * TODO
	  *//*
	@Test
	public void TestLogin() {
		Region r = new Region();
		r.setParentID(370100);
		RegionCache.put("370100", r);
		String account = "admin";
		String password = "eaa67f3a93d0acb08d8a5e8ff9866f51983b3c3b";
		User user = authService.login(account, password);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("LoginResult", user);
		String ticket = UUID.randomUUID().toString();
		map.put("ticket", ticket);
		UserCache.put(ticket, user);
	}*/
	
	
	
}
