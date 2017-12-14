/**
 * Copyright 2017 www.kotei-info.com
 * 
 * All rights reserved.
 * 	
 */
package phalaenopsis.common.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import phalaenopsis.common.entity.Organization;
import phalaenopsis.common.entity.TreeNode;
import phalaenopsis.common.service.OrganizationService;

/**
 * @author yangw786
 *
 * 2017年3月7日下午2:10:28
 */
//@Controller
@RestController
@RequestMapping("/Sys/Mgr/Org")
public class OrganizationController {
	@Autowired
	private OrganizationService organizationService;
	

	/**
	 * 添加组织部门
	 * 
	 * @param org
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/AddOrg", method = RequestMethod.POST)
	@ResponseBody
	public boolean AddOrg(@RequestBody Organization org) {
		if (org.getParentID() == null || org.getParentID().equals("")) {
			return false;
		}
		// org.setReg(regions);
		org.setID(UUID.randomUUID().toString());
		return organizationService.AddOrg(org);
	}

	/**
	 * 修改组织部门信息
	 * 
	 * @param org
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/UpdateOrg", method = RequestMethod.PUT)
	@ResponseBody
	public boolean UpdateOrg(@RequestBody Organization org) {
		return organizationService.UpdateOrg(org);
	}

	/**
	 * 删除组织机构
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/DeleteOrg", method = RequestMethod.DELETE)
	@ResponseBody
	public int DeleteOrg(String id) {
		return organizationService.DeleteOrg(id);
	}

	/**
	 * 获取组织部门信息
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/GetOrg", method = RequestMethod.GET)
	@ResponseBody
	public Organization GetOrg(String id) {
		return organizationService.GetOrg(id);
	}

	/**
	 * 获取树形结构的所有组织部门
	 * 
	 * @return
	 */
	@RequestMapping(value = "/GetOrgsTree", method = RequestMethod.GET)
	@ResponseBody
	public List<TreeNode> GetOrgsTree() {
		return organizationService.GetOrgsTree();
	}

	/**
	 * 根据获取树形结构本级及本级以下组织部门
	 * 
	 * @param parentID
	 * @return
	 */
	@RequestMapping(value = "/GetOrgsTreeByParentID", method = RequestMethod.GET)
	@ResponseBody
	public List<TreeNode> GetOrgsTreeByParentID(String parentID) {
		return organizationService.GetOrgsTreeByParentID(parentID);
	}

	/**
	 * 根据组织id获取所在region的Name
	 * 
	 * @param orgID
	 * @return
	 */
	@RequestMapping(value = "/GetRegionNameByOrgID", method = RequestMethod.GET)
	@ResponseBody
	public String GetRegionNameByOrgID(String orgID) {
		return organizationService.GetRegionNameByOrgID(orgID);
	}
	
	/**
	 * 根据传递的区县id，查询下级组织单位
	 * @param reginId
	 * @return
	 */
	@RequestMapping(value="/getSubOrgByCountryId", method = RequestMethod.GET)
	public List<Organization> getSubOrgByCountryId(String countryCode){
		return organizationService.getSubOrgByCountryId(countryCode);
	}
	
}
