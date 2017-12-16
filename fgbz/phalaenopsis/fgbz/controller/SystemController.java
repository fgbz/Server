package phalaenopsis.fgbz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import phalaenopsis.common.entity.Page;
import phalaenopsis.common.entity.PagingEntity;
import phalaenopsis.fgbz.entity.FG_Organization;
import phalaenopsis.fgbz.entity.FG_Role;
import phalaenopsis.fgbz.service.SystemServie;

import java.util.List;

@Controller
@RequestMapping("/System")
public class SystemController {

    @Autowired
    private SystemServie systemServie;
    /**
     * 获取组织机构
     *
     * @return
     */
    @RequestMapping(value = "/getOrganizationList", method = RequestMethod.POST)
    @ResponseBody
    public List<FG_Organization> getOrganizationList() {
        return systemServie.getOrganizationList();
    }


    /**
     * 新增或修改组织机构
     * @param organization
     * @return
     */
    @RequestMapping(value = "/AddOrUpdateOrganizationType", method = RequestMethod.POST)
    @ResponseBody
    public int  AddOrUpdateOrganizationType( @RequestBody FG_Organization organization){

        return systemServie.AddOrUpdateOrganizationType(organization);
    }


    /**
     * 删除组织机构
     *
     * @param organization
     * @return
     */
    @RequestMapping(value = "/DeleteOrganization", method = RequestMethod.POST)
    @ResponseBody
    public int DeleteOrganization(@RequestBody FG_Organization organization) {
        return systemServie.DeleteOrganization(organization);
    }

    /**
     * 获取权限列表
     * @param page
     * @return
     */
    @RequestMapping(value = "/getRoles", method = RequestMethod.POST)
    @ResponseBody
    public PagingEntity<FG_Role> getRoles(@RequestBody Page page){
        return systemServie.getRoles(page);
    }

    /**
     * 删除角色
     * @param id
     * @return
     */
    @RequestMapping(value = "/deleteRoleByID", method = RequestMethod.GET)
    @ResponseBody
    public int deleteRoleByID(String id){
        return  systemServie.deleteRoleByID(id);
    }

    /**
     * 新增或编辑权限
     * @param role
     * @return
     */
    @RequestMapping(value = "/SaveOrEditRole", method = RequestMethod.POST)
    @ResponseBody
    public int SaveOrEditRole(@RequestBody FG_Role role){

        return systemServie.SaveOrEditRole(role);
    }

}
