package phalaenopsis.common.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import phalaenopsis.common.entity.HomePageCaseInfo;
import phalaenopsis.common.entity.HomePagePartolInfo;
import phalaenopsis.common.entity.HomePageSourceInfo;
import phalaenopsis.common.entity.HomePageVisitInfo;
import phalaenopsis.common.entity.User;
import phalaenopsis.common.method.Basis;
import phalaenopsis.common.service.HomePageInfoService;

/**
 * 首页信息展示
 * @author Dj
 */
@Controller
@RequestMapping("/Foundation/HomePage")
public class HomePageInfoController extends Basis{
	@Autowired
	private HomePageInfoService service;
	
	/**
	 * 获取案件统计信息
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/GetCaseInfo", method = RequestMethod.GET)
	@ResponseBody
	public List<HomePageCaseInfo> GetCaseInfo() {
		User user = getCurrentUser();
		return service.GetCaseInfo(user);
	}
	
	/**
	 * 获取线索、信访统计信息
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/GetVisitInfo", method = RequestMethod.GET)
	@ResponseBody
	public HomePageVisitInfo GetVisitInfo() {
		User user = getCurrentUser();
		return service.GetVisitInfo(user);
	}
	
	/**
	 * 获取巡查里程信息
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/GetPartolInfo", method = RequestMethod.GET)
	@ResponseBody
	public List<HomePagePartolInfo> GetPartolInfo() {
		User user = getCurrentUser();
		return service.GetPartolInfo(user);
	}
	
	/**
	 * 获取线索来源
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/GetSourceInfo", method = RequestMethod.GET)
	@ResponseBody
	public List<HomePageSourceInfo> GetSourceInfo() {
		User user = getCurrentUser();
		return service.GetSourceInfo(user);
	}
}
