/**
 * Copyright 2017 www.kotei-info.com
 * 
 * All rights reserved.
 * 	
 */
package phalaenopsis.common.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import phalaenopsis.common.entity.OpResult;
import phalaenopsis.common.entity.Organization;
import phalaenopsis.common.entity.OrganizationGrade;
import phalaenopsis.common.entity.OrganizationRegion;
import phalaenopsis.common.entity.TreeNode;
import phalaenopsis.common.entity.User;
import phalaenopsis.common.method.Basis;

/**
 * @author yangw786
 *
 * 2017年3月7日下午2:09:57
 */
@Repository("organizationDao")
public class OrganizationDao extends Basis {
	@Resource
	private SqlSession sqlSession;

	/**
	 * 添加组织部门 
	 * @param org
	 * @return
	 */
	public int AddOrg(Organization org) {
		return sqlSession.insert("organization.addOrg", org);
	}

	/**
	 * 添加组织部门下属管理区域 
	 * @param reg
	 * @return
	 */
	public int AddOrgRegion(OrganizationRegion reg) {
		return sqlSession.insert("organization.addOrgRegion", reg);
	}

	/**
	 * 修改组织部门信息
	 * @param org
	 * @param request
	 * @return 
	 */
	public int UpdateOrg(Organization org) {
		int number = sqlSession.update("organization.updateOrg", org);
		number = sqlSession.delete("organization.delOrgRegion", org.getID());
		return number;
	}

	/**
	 * 删除组织机构
	 * @param id
	 * @return
	 */
	public int DeleteOrg(String id) {
		// 该节点有子节点，则不能删
		int number = sqlSession.selectOne("organization.getChildNumber", id);
		if (number > 0) {
			return OpResult.PreconditionFailed;
		}
		// 该部门下有用户，则不能删
		number = sqlSession.selectOne("auth.getUserNumber", id);
		if (number > 0) {
			return OpResult.PreconditionFailed;
		}
		// 该部门下有设备，则不能删
		//
		Organization organization = sqlSession.selectOne("organization.getOrganization", id);
		if (organization.getParentID() == null || organization.getParentID().equals("")) {
			// ParentID为null的根节点只能有一个，不能删，作为基础数据直接存在于数据库中
			return OpResult.Failed;
		}
		number = sqlSession.delete("organization.deleteOrg", id);
		number = sqlSession.delete("organization.delOrgRegion", id);
		return OpResult.Success;
	}

	/**
	 * 获取组织部门信息
	 * @param id
	 * @return
	 */
	public Organization GetOrg(String id) {
		Organization org = sqlSession.selectOne("organization.getOrganization", id);
		List<Integer> regions = sqlSession.selectList("organization.getOrgRegionID", id);
		org.setReg(regions.toArray(new Integer[regions.size()]));
		return org;
	}

	/**
	 * 获取树形结构的所有组织部门 
	 * @return
	 */
	public List<TreeNode> GetOrgsTree() {
		List<TreeNode> treeNodes = sqlSession.selectList("organization.getOrgTree");
		return treeNodes;
	}

	/**
	 * 根据获取树形结构本级及本级以下组织部门
	 * @param parentID
	 * @return
	 */
	public List<TreeNode> GetOrgsTreeByParentID(String parentID) {
		List<TreeNode> treeNodes = sqlSession.selectList("organization.getOrgsTreeByParentID", parentID);
		return treeNodes;
	}

	/**
	 * 根据组织id获取所在region的Name
	 * @param orgID
	 * @return
	 */
	public String GetRegionNameByOrgID(String orgID) {
		User user = getCurrentUser();

		String result = "";
		int orgGrade = user.getOrgGrade();
		switch (orgGrade) {
		case OrganizationGrade.Province:
			result = sqlSession.selectOne("organization.getRegionNameByOrgIDProvince", orgID);
			break;
		case OrganizationGrade.City:
			result = sqlSession.selectOne("organization.getRegionNameByOrgIDCity", orgID);
			break;
		case OrganizationGrade.County:
			result = sqlSession.selectOne("organization.getRegionNameByOrgIDCounty", orgID);
			break;
		default:
			break;
		}
		return "\"" + result + "\"";
	}

	/**
	 * 通过组织机构Id，查找对应的行政区Id
	 * @param orgID 组织结构Id
	 * @return 行政区Id
	 */
	public int getRegionIdByOrgID(String orgID) {
		User user = getCurrentUser();
		if (user.getOrgGrade() == OrganizationGrade.Province || user.getOrgGrade() == OrganizationGrade.City
				|| user.getOrgGrade() == OrganizationGrade.County) {
			int orgGrade = user.getOrgGrade();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("orgID", orgID);
			map.put("orgGrade", orgGrade);
			int regionId = sqlSession.selectOne("organization.getRegionIdByOrgID", map);
			return regionId;
		} else {
			return 0;
		}
	}

	public List<Organization> getSubOrgByCountryId(String reginId) {
		User user = getCurrentUser();
		if (user.getOrgGrade() == OrganizationGrade.Province || user.getOrgGrade() == OrganizationGrade.City
				|| user.getOrgGrade() == OrganizationGrade.County) {
			int orgGrade = user.getOrgGrade();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("orgID", user.getOrganizationID());
			map.put("orgGrade", orgGrade - 1);
			map.put("reginId", reginId);
			List<Organization> org = sqlSession.selectList("organization.getSubOrgByCountryId", map);
			return org;
		}
		return null;
	}
}
