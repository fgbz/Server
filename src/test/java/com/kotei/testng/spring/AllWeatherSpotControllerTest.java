package com.kotei.testng.spring;

import org.testng.annotations.Test;

import phalaenopsis.allWeather.controller.AllWeatherSpotController;
import phalaenopsis.allWeather.service.AllWeatherSpotService;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


@ContextConfiguration(locations = { "classpath:conf/applicationContext.xml" })
@WebAppConfiguration(value = "src/main/webapp") 
public class AllWeatherSpotControllerTest extends AbstractTestNGSpringContextTests{
	
	
	@Autowired
    private WebApplicationContext wac;
	
	private MockMvc mockMvc;
	
	@Mock
    private AllWeatherSpotService service;
	
	@InjectMocks
	private AllWeatherSpotController controller;
	
	
	
	
	@Test(dataProvider = "dp")
	public void f(Integer n, String s) {
	}

	@BeforeMethod
	public void beforeMethod() {
	}

	@AfterMethod
	public void afterMethod() {
	}

	
	@BeforeClass
	public void beforeClass() {
	}

	@AfterClass
	public void afterClass() {
	}

	@BeforeTest
	public void beforeTest() {
		 MockitoAnnotations.initMocks(this);
	     this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	
	}

	@AfterTest
	public void afterTest() {
	}

	@BeforeSuite
	public void beforeSuite() {
	
	}

	@AfterSuite
	public void afterSuite() {
	}
	
	
	
	

	@Test
	public void segment() {
		String json="{'source':{'Spot':{'cityauditispass':null,'id':82636299042816,'provinceauditpersonid':null,'cityauditpersonid':null,'iscityaudit':null,'mapspotid':null,'provinceauditpersonname':null,'provinceaudittime':null,'provinceauditremarks':null,'provinceauditispass':null,'cityauditpersonname':null,'cityaudittime':null,'cityauditremarks':null,'nowNode':null,'organizationType':null,'cityborderarea':0,'doubtillegalarea':0,'arealevel':'10','spotarea':14.5,'coordinateY':3947220.9,'coordinateX':39485969.7,'aftertime':'20170417','beforetime':'20161115','datasource':'RE','tz':' ','spottype':'2','spotnumber':'1','expertid':null,'checkperiod':2,'period':1,'year':2017,'lastupdate':1500452762000,'notnewRemarks':null,'notnewSpotclassification':null,'otherApprovalname':null,'otherRemarks':null,'otherApprovalarea':null,'otherApprovaltime':null,'otherApprovalnumber':null,'otherApprovallevel':null,'otherProjectname':null,'otherSpottype':null,'zjgRemarks':null,'zjgNewblockarea':null,'zjgNewblockname':null,'zjgProjectnumber':null,'zjgProjectname':null,'zjgIsnestedsuccess':null,'legalprooftype':null,'node':null,'instanceid':null,'prespotnumber':null,'province':370000,'city':370800,'countyname':'兖州区','county':370882,'districtcountyname':'兖州区','districtcounty':370882,'mapratio':2,'bzX':' ','spotnature':' ','remark':' ','protectfailarea':0,'protectpassarea':0,'protectname':' ','xhdjbntarea':0,'jbntarea':0,'swExpert':null,'arableAcreage':0,'timing':1500449911000,'ssnydBackupnumber':null,'ssnydApprovalnumber':null,'ssnydUsetype':null,'ssnydApprovalusetype':null,'ssnydApprovalarea':null,'ssnydProjectName':null,'ssnydApprovaltime':null,'villageIsharden':null,'villageRoadwidth':null,'oilProjectname':null,'oilIssuedtime':null,'oilIssuednumber':null,'adjustablearea':null,'tempApprovalnumber':null,'tempProjectusetype':null,'tempUsetype':null,'tempArea':null,'tempApprovalusetype':null,'tempProjectName':null,'tempApprovaltime':null,'organizationGrade':null,'proveuserId':null,'proveuserName':null,'proveuserAccount':null,'shape':null,'bizType':'allWeather','businessType':1,'displayType':2,'Shape':{'type':'polygon','spatialReference':{'wkid':4610},'rings':[[[116.84482242900003,35.655435341999976],[116.84533846700003,35.655436006],[116.84530855399998,35.65357080299998],[116.84478579699999,35.65358654099998],[116.84482242900003,35.655435341999976]]]}},'ID':82636299042816,'displayType':'List','type':'allWeather','source':'2','Num':'370882_1','MonitorAcreage':14.5},'spots':[{'Spot':{'cityauditispass':null,'id':82636299042816,'provinceauditpersonid':null,'cityauditpersonid':null,'iscityaudit':null,'mapspotid':null,'provinceauditpersonname':null,'provinceaudittime':null,'provinceauditremarks':null,'provinceauditispass':null,'cityauditpersonname':null,'cityaudittime':null,'cityauditremarks':null,'nowNode':null,'organizationType':null,'cityborderarea':0,'doubtillegalarea':0,'arealevel':'10','spotarea':14.5,'coordinateY':3947220.9,'coordinateX':39485969.7,'aftertime':'20170417','beforetime':'20161115','datasource':'RE','tz':' ','spottype':'2','spotnumber':'1','expertid':null,'checkperiod':2,'period':1,'year':2017,'lastupdate':1500452762000,'notnewRemarks':null,'notnewSpotclassification':null,'otherApprovalname':null,'otherRemarks':null,'otherApprovalarea':null,'otherApprovaltime':null,'otherApprovalnumber':null,'otherApprovallevel':null,'otherProjectname':null,'otherSpottype':null,'zjgRemarks':null,'zjgNewblockarea':null,'zjgNewblockname':null,'zjgProjectnumber':null,'zjgProjectname':null,'zjgIsnestedsuccess':null,'legalprooftype':null,'node':null,'instanceid':null,'prespotnumber':null,'province':370000,'city':370800,'countyname':'兖州区','county':370882,'districtcountyname':'兖州区','districtcounty':370882,'mapratio':2,'bzX':' ','spotnature':' ','remark':' ','protectfailarea':0,'protectpassarea':0,'protectname':' ','xhdjbntarea':0,'jbntarea':0,'swExpert':null,'arableAcreage':0,'timing':1500449911000,'ssnydBackupnumber':null,'ssnydApprovalnumber':null,'ssnydUsetype':null,'ssnydApprovalusetype':null,'ssnydApprovalarea':null,'ssnydProjectName':null,'ssnydApprovaltime':null,'villageIsharden':null,'villageRoadwidth':null,'oilProjectname':null,'oilIssuedtime':null,'oilIssuednumber':null,'adjustablearea':null,'tempApprovalnumber':null,'tempProjectusetype':null,'tempUsetype':null,'tempArea':null,'tempApprovalusetype':null,'tempProjectName':null,'tempApprovaltime':null,'organizationGrade':null,'proveuserId':null,'proveuserName':null,'proveuserAccount':null,'shape':{'type':'polygon','rings':[[[116.84532301700006,35.654472621000025],[116.8448086190001,35.65473835200004],[116.84482242900003,35.65543534200003],[116.84533846700003,35.65543600600006],[116.84532301700006,35.654472621000025]]],'_ring':0,'spatialReference':{'wkid':4610},'cache':{'_extent':{'xmin':116.8448086190001,'ymin':35.654472621000025,'xmax':116.84533846700003,'ymax':35.65543600600006,'spatialReference':{'wkid':4610}},'_partwise':null}},'bizType':'allWeather','businessType':1,'displayType':2,'Shape':{'type':'polygon','spatialReference':{'wkid':4610},'rings':[[[116.84482242900003,35.655435341999976],[116.84533846700003,35.655436006],[116.84530855399998,35.65357080299998],[116.84478579699999,35.65358654099998],[116.84482242900003,35.655435341999976]]]},'spotArea':4317.006333333334},'ID':82636299042816,'displayType':'List','type':'allWeather','source':'2','Num':'370882_1','MonitorAcreage':6.4755095,'ObjectID':0,'spotnumber':'1','spotArea':4317.006333333334,'Shape':{'type':'polygon','rings':[[[116.84532301700006,35.654472621000025],[116.8448086190001,35.65473835200004],[116.84482242900003,35.65543534200003],[116.84533846700003,35.65543600600006],[116.84532301700006,35.654472621000025]]],'_ring':0,'spatialReference':{'wkid':4610},'cache':{'_extent':{'xmin':116.8448086190001,'ymin':35.654472621000025,'xmax':116.84533846700003,'ymax':35.65543600600006,'spatialReference':{'wkid':4610}},'_partwise':null}}},{'Spot':{'cityauditispass':null,'id':82636299042816,'provinceauditpersonid':null,'cityauditpersonid':null,'iscityaudit':null,'mapspotid':null,'provinceauditpersonname':null,'provinceaudittime':null,'provinceauditremarks':null,'provinceauditispass':null,'cityauditpersonname':null,'cityaudittime':null,'cityauditremarks':null,'nowNode':null,'organizationType':null,'cityborderarea':0,'doubtillegalarea':0,'arealevel':'10','spotarea':14.5,'coordinateY':3947220.9,'coordinateX':39485969.7,'aftertime':'20170417','beforetime':'20161115','datasource':'RE','tz':' ','spottype':'2','spotnumber':'1-3','expertid':null,'checkperiod':2,'period':1,'year':2017,'lastupdate':1500452762000,'notnewRemarks':null,'notnewSpotclassification':null,'otherApprovalname':null,'otherRemarks':null,'otherApprovalarea':null,'otherApprovaltime':null,'otherApprovalnumber':null,'otherApprovallevel':null,'otherProjectname':null,'otherSpottype':null,'zjgRemarks':null,'zjgNewblockarea':null,'zjgNewblockname':null,'zjgProjectnumber':null,'zjgProjectname':null,'zjgIsnestedsuccess':null,'legalprooftype':null,'node':null,'instanceid':null,'prespotnumber':null,'province':370000,'city':370800,'countyname':'兖州区','county':370882,'districtcountyname':'兖州区','districtcounty':370882,'mapratio':2,'bzX':' ','spotnature':' ','remark':' ','protectfailarea':0,'protectpassarea':0,'protectname':' ','xhdjbntarea':0,'jbntarea':0,'swExpert':null,'arableAcreage':0,'timing':1500449911000,'ssnydBackupnumber':null,'ssnydApprovalnumber':null,'ssnydUsetype':null,'ssnydApprovalusetype':null,'ssnydApprovalarea':null,'ssnydProjectName':null,'ssnydApprovaltime':null,'villageIsharden':null,'villageRoadwidth':null,'oilProjectname':null,'oilIssuedtime':null,'oilIssuednumber':null,'adjustablearea':null,'tempApprovalnumber':null,'tempProjectusetype':null,'tempUsetype':null,'tempArea':null,'tempApprovalusetype':null,'tempProjectName':null,'tempApprovaltime':null,'organizationGrade':null,'proveuserId':null,'proveuserName':null,'proveuserAccount':null,'shape':{'type':'polygon','rings':[[[116.84532301700006,35.654472621000025],[116.8453085540001,35.65357080300004],[116.8447857970001,35.65358654100004],[116.8448086190001,35.65473835200004],[116.84532301700006,35.654472621000025]]],'_ring':0,'spatialReference':{'wkid':4610},'cache':{'_extent':{'xmin':116.8447857970001,'ymin':35.65357080300004,'xmax':116.84532301700006,'ymax':35.65473835200004,'spatialReference':{'wkid':4610}},'_partwise':null}},'bizType':'allWeather','businessType':1,'displayType':2,'Shape':{'type':'polygon','spatialReference':{'wkid':4610},'rings':[[[116.84482242900003,35.655435341999976],[116.84533846700003,35.655436006],[116.84530855399998,35.65357080299998],[116.84478579699999,35.65358654099998],[116.84482242900003,35.655435341999976]]]},'spotArea':5376.461560000001},'ID':82636299042816,'displayType':'List','type':'allWeather','source':'2','Num':'370882_1-3','MonitorAcreage':8.06469234,'ObjectID':0,'spotnumber':'1-3','spotArea':5376.461560000001,'Shape':{'type':'polygon','rings':[[[116.84532301700006,35.654472621000025],[116.8453085540001,35.65357080300004],[116.8447857970001,35.65358654100004],[116.8448086190001,35.65473835200004],[116.84532301700006,35.654472621000025]]],'_ring':0,'spatialReference':{'wkid':4610},'cache':{'_extent':{'xmin':116.8447857970001,'ymin':35.65357080300004,'xmax':116.84532301700006,'ymax':35.65473835200004,'spatialReference':{'wkid':4610}},'_partwise':null}}}]}";
    	boolean flag=controller.segment(json);
    	System.out.println("----------返回的结果集-------------"+flag);
	}
}
