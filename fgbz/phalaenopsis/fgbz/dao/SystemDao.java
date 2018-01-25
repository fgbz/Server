package phalaenopsis.fgbz.dao;

import phalaenopsis.fgbz.entity.*;

import java.util.List;
import java.util.Map;

public interface SystemDao {

     FG_User getUserByAccount(Map<String, Object> map);

    /**
     * 获取用户所有权限
     * @param user
     * @return
     */
     List<FG_Menu> getUserMenu(FG_User user);
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
     * 获取最后的层级代码
     * @param id
     * @return
     */
    int getLastItemLevelcode(String id);

    /**
     *处理上移或下移
     * @param organization
     * @return
     */
    int handTreeLevel(FG_Organization organization);
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
     * 获取所有的角色
     * @return
     */
    List<FG_Role>  getAllRoles();

    /**
     * 获取角色
     * @return
     */
    List<FG_Role> getRoles(Map<String, Object> map);

    /**
     * 用户停用
     * @param map
     * @return
     */
    int SaveUserStatus(Map<String, Object> map);

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

    /**
     * 获取用户
     * @param map
     * @return
     */
    List<FG_User> getUsersList(Map<String, Object> map);

    /**
     * 通过组织机构获取其下人员
     * @param map
     * @return
     */
    List<FG_User> getUserListByOrgId(Map<String, Object> map);

    /**
     * 获取用户个数
     * @param map
     * @return
     */
    int getUserListCount(Map<String, Object> map);
    /**
     * 新增或编辑用户
     * @return
     */
    int SaveOrUpdateUser(FG_User user);

    /**
     * 绑定用户收藏夹
     * @param user
     * @return
     */
    int SaveUserFavorite(FG_User user);

    /**
     * 通过用户id删除用户角色
     * @param id
     * @return
     */
    int DeleteUserRolesByUserID(String id);

    /**
     * 保存用户角色
     * @return
     */
    int SaveUserRoles(FG_User user);

    /**
     * 删除用户
     * @return
     */
    int deleteUser(String id);

    /**
     * 删除用户收藏夹
     * @param id
     * @return
     */
    int deleteUserFav(String id);

    /**
     * 验证用户是否重复
     * @param user
     * @return
     */
    int checkUserRepeat(FG_User user);

    /**
     * 获取审核设置
     * @return
     */
    String getApproveSetting();

    /**
     * 设置配置
     * @return
     */
    int SaveOrUpdateSettingValue(Map<String, String> map);

    /**
     * 保存审核设置
     * @return
     */
    int SaveOrUpdateApproveSetting(Map<String, Object> map);

    /**
     *通过用户名和密码获取用户
     * @return
     */
    int getUserByPasswordAndId(Map<String, Object> map);

    /**
     * 获取待审核的法规数量
     * @return
     */
    int getNeedCheckLawCount();

    /**
     * 保存日志
     * @return
     */
    int SaveFgLog(Fg_Log log);

    /**
     * 查询日志
     * @param map
     * @return
     */
    List<Fg_Log> SelectLog(Map<String, Object> map);

    int SelectLogCount(Map<String, Object> map);


    String getUserMailById(String id);

}
