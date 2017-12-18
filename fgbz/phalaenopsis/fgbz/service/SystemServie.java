package phalaenopsis.fgbz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import phalaenopsis.common.entity.Condition;
import phalaenopsis.common.entity.OpResult;
import phalaenopsis.common.entity.Page;
import phalaenopsis.common.entity.PagingEntity;
import phalaenopsis.fgbz.dao.SystemDao;
import phalaenopsis.fgbz.entity.FG_Menu;
import phalaenopsis.fgbz.entity.FG_Organization;
import phalaenopsis.fgbz.entity.FG_Role;
import phalaenopsis.fgbz.entity.FG_User;

import java.util.*;

@Service("systemServie")
public class SystemServie {

    @Autowired
    private SystemDao systemDao;

    private  List<FG_Organization> ids = new ArrayList<FG_Organization>();


    public FG_User login(String accountJm, String passwordJm){

        Map<String,Object> map = new HashMap<>();

        map.put("account",accountJm);
        map.put("password",passwordJm);
        FG_User user = systemDao.getUserByAccount(map);

        if(user!=null&&user.getRoles()!=null&&user.getRoles().size()>0){
            //获取用户所有权限
            user.setMenus(systemDao.getUserMenu(user));
        }

        return user;

    }
    /**
     * 获取组织机构
     *
     * @return
     */
    public List<FG_Organization> getOrganizationList() {
        return systemDao.getOrganizationList();
    }


    /**
     * 新增或修改组织机构
     * @param organization
     * @return
     */
    public int  AddOrUpdateOrganizationType(FG_Organization organization){
        if(organization.getId()==null||organization.getId().equals("")){
            UUID uuid=UUID.randomUUID();
            String guid=uuid.toString();
            organization.setId(guid);
        }
        return systemDao.AddOrUpdateOrganizationType(organization);
    }


    /**
     * 删除组织机构
     *
     * @param organization
     * @return
     */
    public int DeleteOrganization(FG_Organization organization) {
        return systemDao.DeleteOrganization(organization);
    }

    /**
     * 获取子节点
     * @param id
     * @return
     */
    public List<FG_Organization> getChildNode(String id){
        return systemDao.getChildNode(id);
    }

    /**
     * 获取所有的权限菜单
     * @return
     */
    public  List<FG_Menu>  getAllMenus(){
        return systemDao.getAllMenus();
    }

    /**
     * 获取所有的角色
     * @return
     */
    public List<FG_Role>  getAllRoles(){
        return systemDao.getAllRoles();
    }

    /**
     * 获取权限列表
     * @param page
     * @return
     */
    public PagingEntity<FG_Role> getRoles(Page page){
        Map<String, Object> conditions = new HashMap<String, Object>();
        // 1,根据条件一共查询到的数据条数
        int count = systemDao.getRolesCount();

        if(page.getConditions()!=null) {
            //查询条件
            for (Condition condition : page.getConditions()) {
                if (condition.getKey().equals("Name")) {
                    conditions.put("Name", condition.getValue());
                }
            }
        }
        if(page.getPageNo()==1){
            conditions.put("startRow", 0 );
        }else{
            conditions.put("startRow", page.getPageSize() * (page.getPageNo() -1) );
        }
        conditions.put("endRow", page.getPageSize() * page.getPageNo());

        // 2, 查询到当前页数的数据
        List<FG_Role> list = systemDao.getRoles(conditions);

        PagingEntity<FG_Role> result = new PagingEntity<FG_Role>();
        result.setPageCount(count);

        int pageCount = 0 == count ? 1 : (count - 1) / page.getPageSize() + 1; // 由于计算pageCount存在整除的情况，所以计算的时候先减1在除以pageSize
        result.setPageNo(page.getPageNo());
        result.setPageSize(page.getPageSize());
        result.setPageCount(pageCount);
        result.setRecordCount(count);
        result.setCurrentList(list);

        return result;
    }

    /**
     * 删除角色
     * @param id
     * @return
     */
    public int deleteRoleByID(String id){
        return  systemDao.deleteRoleByID(id);
    }

    /**
     * 新增或编辑权限
     * @param role
     * @return
     */
    @Transactional
    public int SaveOrEditRole(FG_Role role){
        if(role.getId()==null||role.getId().equals("")){
            UUID uuid=UUID.randomUUID();
            String guid=uuid.toString();
            role.setId(guid);
        }
        return systemDao.SaveOrEditRole(role);
    }

    /**
     * 保存或编辑用户
     * @param user
     * @return
     */
    @Transactional
    public int SaveOrUpdateUser(FG_User user){

        int num = systemDao.checkUserRepeat(user);
        if(num>0){
            int isWorking = 461;
            OpResult opResult = new OpResult(isWorking);
            return opResult.Code;
        }

        if(user.getId()==null||user.getId().equals("")){
            UUID uuid=UUID.randomUUID();
            String guid=uuid.toString();
            user.setId(guid);

            user.setFavoriteid(UUID.randomUUID().toString());
            systemDao.SaveUserFavorite(user);
        }else{
            systemDao.DeleteUserRolesByUserID(user.getId());
        }
         systemDao.SaveOrUpdateUser(user);

        if(user.getRoles()!=null&&user.getRoles().size()>0){
            systemDao.SaveUserRoles(user);
        }


        return OpResult.Success;
    }

    public  List<FG_Organization> getOrgsTree(String id){

        List<FG_Organization> list = getChildNode(id);

        while (list!=null&&list.size()>0){
            ids.addAll(list);
            for(FG_Organization organization:list){
                list=getOrgsTree(organization.getId());
            }
        }
        return list;
    }

    /**
     * 获取技术标准列表
     * @param page
     * @return
     */
    public PagingEntity<FG_User> getUserList(Page page){

        Map<String, Object> conditions = new HashMap<String, Object>();

        if(page.getConditions()!=null) {
            //查询条件
            for (Condition condition : page.getConditions()) {
                if (condition.getKey().equals("Name")) {
                    conditions.put("Name", condition.getValue());
                }  else if (condition.getKey().equals("TreeValue")) {
                    ids =new ArrayList<>();
                    FG_Organization orgSelf = new FG_Organization();
                    orgSelf.setId(condition.getValue());
                    ids.add(orgSelf);
                    getOrgsTree(condition.getValue());
                    conditions.put("TreeValue",ids );
                }

            }
        }


        // 1,根据条件一共查询到的数据条数
        int count = systemDao.getUserListCount(conditions);

        if(page.getPageNo()==1){
            conditions.put("startRow", 0 );
        }else{
            conditions.put("startRow", page.getPageSize() * (page.getPageNo() - 1) );
        }
        conditions.put("endRow", page.getPageSize());

        // 2, 查询到当前页数的数据
        List<FG_User> list = systemDao.getUsersList(conditions);

        PagingEntity<FG_User> result = new PagingEntity<FG_User>();
        result.setPageCount(count);

        int pageCount = 0 == count ? 1 : (count - 1) / page.getPageSize() + 1; // 由于计算pageCount存在整除的情况，所以计算的时候先减1在除以pageSize
        result.setPageNo(page.getPageNo());
        result.setPageSize(page.getPageSize());
        result.setPageCount(pageCount);
        result.setRecordCount(count);
        result.setCurrentList(list);

        return result;
    }

    @Transactional
    public int deleteUserByID(FG_User user){
        systemDao.deleteUserFav(user.getFavoriteid());
        systemDao.DeleteUserRolesByUserID(user.getId());
        systemDao.deleteUser(user.getId());
        return OpResult.Success;
    }

    /**
     * 获取审核设置
     * @return
     */
   public int getApproveSetting(){
       return systemDao.getApproveSetting();
   }

    /**
     * 保存审核设置
     * @return
     */
    public int SaveOrUpdateApproveSetting(int status){
        Map<String, Object> map = new HashMap<>();
        map.put("key","isApprove");
        map.put("value",status);
        map.put("count",0);
        systemDao.SaveOrUpdateApproveSetting(map);
        return OpResult.Success;
    }
}
