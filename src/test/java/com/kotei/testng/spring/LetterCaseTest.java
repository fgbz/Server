package com.kotei.testng.spring;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSONObject;

import phalaenopsis.common.entity.Condition;
import phalaenopsis.common.entity.Page;
import phalaenopsis.common.entity.PagingEntity;
import phalaenopsis.common.method.JsonResult;
import phalaenopsis.illegalclue.entity.ResultState;
import phalaenopsis.visitSystem.controller.LetterCaseController;
import phalaenopsis.visitSystem.entity.XfDeals;
import phalaenopsis.visitSystem.entity.XfRegister;
import phalaenopsis.visitSystem.service.LetterCaseService;
import phalaenopsis.visitSystem.service.LetterCaseServicePartial;


@ContextConfiguration(locations = { "classpath:conf/applicationContext.xml" })
@WebAppConfiguration(value = "src/main/webapp") 
public class LetterCaseTest extends AbstractTestNGSpringContextTests{
		
		
	@Autowired
	private WebApplicationContext wac;
		
	private MockMvc mockMvc;
		
	@Mock
	private LetterCaseService service;
	@Mock
	private LetterCaseServicePartial servicePartial;
		
	@InjectMocks
	private LetterCaseController controller;
		
	@BeforeTest
	public void beforeTest() {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	} 
	
	/*@Test  
    public void getAllBanners() throws Exception{
		Region r = new Region();
		r.setParentID(370100);
		
		//RegionCache.put("370100", r);
		String ticket = UUID.randomUUID().toString();
		TestLogin(ticket);
	
		Page pages = new Page();
		String page = JSONObject.toJSONString(pages);  
		String responseString = mockMvc.perform( post("/letterCase/letterCaseService/getList").contentType(MediaType.APPLICATION_JSON).content(page)).andDo(print())  
			.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();   
		
        String responseString = mockMvc.perform(post("/letterCase/letterCaseService/saveSumbit")    //请求的url,请求的方法是get  
                            .contentType(MediaType.APPLICATION_JSON)  //数据的格式  
                            .param("register",register)         //添加参数  
            ).andExpect(status().isOk())    //返回的状态是200  
                    .andDo(print())         //打印出请求和相应的内容  
                    .andReturn().getResponse().getContentAsString();   //将相应的数据转换为字符串 
        	
		
        System.out.println("--------返回的json = " + responseString);  
    } */ 
	
	/**
	 * 查询列表
	 * 
	 * @param page
	 *            参数
	 * @return PagingEntity
	 */
	@Test
	public void TestPagingEntity() {
		
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
    	
    	PagingEntity<XfRegister> list = controller.getList(page);
    	System.out.println("----------返回的结果集-------------"+list);
    	Assert.assertEquals(list, null);
	}
	
	/**
	 * 保存或更新信访登记信息，重新信访登记信息
	 * 
	 * @param letterCase
	 * @return resultState
	 */
	@Test
	public void TestSaveOrUpdate() {
		XfRegister register = new XfRegister();
		Date registerdate = new Date();
		register.setHomeaddress("武汉");
		register.setGender(1);
		register.setAge(52);
		register.setPhoneno("15888888888");
		register.setIdentityno("320000195803226245");
		register.setVipornot(1);
		register.setXfpeoplecount(1);
		register.setSerialno("11111");
		register.setIssuedesc("土地纠纷问题");
		//register.setXftype("信访方式");
		register.setIssueaddress("新房归属地：光谷");
		register.setResposibleusername("经办人员1");
		//register.setResponsibleuserid((long) 10086);
		register.setArealevel(2);
		register.setRegisterdate(registerdate);
		register.setStatus(0);
		register.setXfname("信访人姓名1");
		register.setIssuenature("问题性质很严重");
		register.setLitiornot(0);
		register.setProvinceid((long) 222);
		register.setCityid((long) 333);
		register.setCountyid((long) 444);
		String requestJson = JSONObject.toJSONString(register);
    	JsonResult jsonResult = controller.saveSubmit(requestJson);
    	System.out.println("----------返回的结果集-------------"+jsonResult);
    	Assert.assertEquals(jsonResult, null);
	}
	
	/**
	 * 根据信访Id查询信访信息
	 * @param id
	 * @return
	 */
	@Test
	public void TestGetLetterCaseById() {
		long id = 10086;
		XfRegister xfRegister = controller.getLetterCaseById(id);
		System.out.println("----------返回的结果集-------------"+xfRegister);
		Assert.assertEquals(xfRegister, null);
	}
	
	/**
	 * 查询重复信访
	 * @param name 姓名
	 * @param idCard 身份证
	 * @return
	 */
	@Test
	public void TestGetRepeatList() {
		String name = "张三";
		String idCard = "420222199009180094";
		List<XfRegister> repeatList = controller.getRepeatList(name, idCard);
		System.out.println("----------返回的结果集-------------"+repeatList.size());
		Assert.assertEquals(repeatList.size(), 0);
	}
	 
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
		ResultState resultState = controller.audit(xfDeals);
		System.out.println("----------返回的结果集-------------"+resultState);
    	Assert.assertEquals(resultState, null);
	}*/
	
	/**
	 * 获取所有的审核信息
	 * @param registerId
	 * @return
	 */
	@Test
	public void TestGetXfDeals() {
		String registerId = "87728347676672";
		List<XfDeals> xfDeals = controller.getXfDeals(registerId);
		System.out.println("----------返回的结果集-------------"+xfDeals.size());
		Assert.assertEquals(xfDeals.size(),0);
	}
	
	/**
	  * 登录
	  * @param login
	  * @return
	  * TODO
	  *//*
	public void TestLogin(String ticket) {
		
		String account = "admin";
		String password = "eaa67f3a93d0acb08d8a5e8ff9866f51983b3c3b";
		User user = authService.login(account, password);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("LoginResult", user);
		
		map.put("ticket", ticket);
		UserCache.put(ticket, user);
	}*/
	
}
