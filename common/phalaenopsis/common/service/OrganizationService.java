/**
 * Copyright 2017 www.kotei-info.com
 * 
 * All rights reserved.
 * 	
 */
package phalaenopsis.common.service;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import phalaenopsis.common.dao.OrganizationDao;
import phalaenopsis.common.entity.Organization;
import phalaenopsis.common.entity.OrganizationRegion;
import phalaenopsis.common.entity.TreeNode;

/**
 * @author yangw786
 *
 * 2017年3月7日下午2:09:39
 */
@Service("organizationService")
public class OrganizationService {
	@Autowired
	private OrganizationDao organizationDao;

	/**
	 * 添加组织部门 
	 * @param org
	 * @return
	 */
	public boolean AddOrg(Organization org) {
		if (org.getType() == 2){
			org.setGrade(GetOrg(org.ParentID).Grade);
		}

		int result = organizationDao.AddOrg(org);
		for (int regionId : org.getReg()) {
			OrganizationRegion reg = new OrganizationRegion();
			reg.ID = UUID.randomUUID().toString();
			reg.OrganizationID = org.ID;
			reg.RegionID = regionId;
			result = organizationDao.AddOrgRegion(reg);
		}
		return result > 0 ? true : false;
	}

	/**
	 * 修改组织部门信息
	 * @param org
	 * @param request
	 * @return
	 */
	public boolean UpdateOrg(Organization org) {
		if (org.getType() == 2){
			org.setGrade(GetOrg(org.ParentID).Grade);
		}

		int number = organizationDao.UpdateOrg(org);
		for (int regionId : org.getReg()) {
			OrganizationRegion reg = new OrganizationRegion();
			reg.ID = UUID.randomUUID().toString();
			reg.OrganizationID = org.ID;
			reg.RegionID = regionId;
			number = organizationDao.AddOrgRegion(reg);
		}

		return number > 0 ? true : false;
	}

	/**
	 * 删除组织机构
	 * @param id
	 * @return
	 */
	public int DeleteOrg(String id) {
		return organizationDao.DeleteOrg(id);
	}

	/**
	 * 获取组织部门信息
	 * @param id
	 * @return
	 */
	public Organization GetOrg(String id) {
		return organizationDao.GetOrg(id);
	}

	/**
	 * 获取树形结构的所有组织部门 
	 * @return
	 */
	public List<TreeNode> GetOrgsTree() {
		return organizationDao.GetOrgsTree();
	}

	/**
	 * 根据获取树形结构本级及本级以下组织部门
	 * @param parentID
	 * @return
	 */
	public List<TreeNode> GetOrgsTreeByParentID(String parentID) {
		return organizationDao.GetOrgsTreeByParentID(parentID);
	}

	/**
	 * 根据组织id获取所在region的Name
	 * @param orgID
	 * @return
	 */
	public String GetRegionNameByOrgID(String orgID) {
		return organizationDao.GetRegionNameByOrgID(orgID);
	}

	public List<Organization> getSubOrgByCountryId(String reginId) {
		return organizationDao.getSubOrgByCountryId(reginId);
	}

}
