package phalaenopsis.fgbz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import phalaenopsis.common.entity.Page;
import phalaenopsis.common.entity.PagingEntity;
import phalaenopsis.fgbz.dao.SystemDao;
import phalaenopsis.fgbz.entity.FG_Menu;
import phalaenopsis.fgbz.entity.FG_Organization;
import phalaenopsis.fgbz.entity.FG_Role;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service("systemServie")
public class SystemServie {

    @Autowired
    private SystemDao systemDao;
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
     * 获取权限列表
     * @param page
     * @return
     */
    public PagingEntity<FG_Role> getRoles(Page page){
        Map<String, Object> conditions = new HashMap<String, Object>();
        // 1,根据条件一共查询到的数据条数
        int count = systemDao.getRolesCount();

        if(page.getPageNo()==1){
            conditions.put("startRow", 0 );
        }else{
            conditions.put("startRow", page.getPageSize() * (page.getPageNo() + 1) );
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
    public int SaveOrEditRole(FG_Role role){
        if(role.getId()==null||role.getId().equals("")){
            UUID uuid=UUID.randomUUID();
            String guid=uuid.toString();
            role.setId(guid);
        }
        return systemDao.SaveOrEditRole(role);
    }
}
