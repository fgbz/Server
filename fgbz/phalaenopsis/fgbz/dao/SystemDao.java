package phalaenopsis.fgbz.dao;

import phalaenopsis.fgbz.entity.FG_Menu;
import phalaenopsis.fgbz.entity.FG_Organization;
import phalaenopsis.fgbz.entity.FG_Role;

import java.util.List;
import java.util.Map;

public interface SystemDao {

    /**
     * 获取组织机构
     * @return
     */
    List<FG_Organization>  getOrganizationList();


    /**
     * 新增或修改组织机构
     * @param organization
     * @return
     */
    int  AddOrUpdateOrganizationType(FG_Organization organization);


    /**
     * 删除组织机构
     * @param organization
     * @return
     */
    int  DeleteOrganization(FG_Organization organization);

    /**
     * 获取子节点
     * @param id
     * @return
     */
    List<FG_Organization> getChildNode(String id);

    /**
     * 获取所有的菜单
     * @return
     */
    List<FG_Menu>  getAllMenus();

    /**
     * 获取角色
     * @return
     */
    List<FG_Role> getRoles(Map<String, Object> map);

    /**
     * 获取总数
     * @return
     */
    int getRolesCount();

    /**
     * 删除角色
     * @return
     */
    int deleteRoleByID(String id);

    /**
     * 新增或编辑角色
     * @param role
     * @return
     */
    int SaveOrEditRole(FG_Role role);

}
