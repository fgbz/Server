package phalaenopsis.fgbz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import phalaenopsis.common.entity.OpResult;
import phalaenopsis.common.entity.Page;
import phalaenopsis.common.entity.PagingEntity;
import phalaenopsis.common.method.cache.UserCache;
import phalaenopsis.fgbz.common.CryptUtils;
import phalaenopsis.fgbz.dao.LawstandardDao;
import phalaenopsis.fgbz.entity.FG_Organization;
import phalaenopsis.fgbz.entity.FG_Role;
import phalaenopsis.fgbz.entity.FG_User;
import phalaenopsis.fgbz.entity.Fg_Log;
import phalaenopsis.fgbz.service.SystemServie;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/System")
public class SystemController {

    @Autowired
    private SystemServie systemServie;


    @RequestMapping(value = "/Login", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> login(@RequestBody Map<String, String> map1){
        final String accountJm = (String) map1.get("account");
        final String passwordJm = (String) map1.get("password");
        Map<String, Object> map = new HashMap<String, Object>();

        map = systemServie.login(accountJm, passwordJm);



        return map;
    }

    /**
     * 邮箱设置
     * @return
     */
    @RequestMapping(value = "/MailSetting", method = RequestMethod.POST)
    @ResponseBody
    public int MailSetting(@RequestBody Map<String, String> map){
        return systemServie.MailSetting(map);
    }

    /**
     * 邮件设置
     * @return
     */
    @RequestMapping(value = "/getMailSetting", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> getMailSetting(){
        return systemServie.getMailSetting();
    }

    @RequestMapping(value = "/handleHistory", method = RequestMethod.POST)
    @ResponseBody
    public int handleHistory(@RequestBody Map<String, String> map) throws IOException {
            String path =  (String) map.get("path");
            return systemServie.handleHistory(path);
    }
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
     * 获取所有的角色
     * @return
     */
    @RequestMapping(value = "/getAllRoles", method = RequestMethod.POST)
    @ResponseBody
    public List<FG_Role>  getAllRoles(){
        return systemServie.getAllRoles();
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
    /**
     * 查询用户列表
     * @param page
     * @return
     */
    @RequestMapping(value = "/getUserList", method = RequestMethod.POST)
    @ResponseBody
    public PagingEntity<FG_User> getUserList(@RequestBody Page page){
        return  systemServie.getUserList(page);
    }

    /**
     * 删除用户
     * @param user
     * @return
     */
    @RequestMapping(value = "/deleteUserByID", method = RequestMethod.POST)
    @ResponseBody
    public int deleteUserByID(@RequestBody FG_User user){

        return systemServie.deleteUserByID(user);
    }

    /**
     * 新增或编辑用户
     * @param user
     * @return
     */
    @RequestMapping(value = "/SaveOrUpdateUser", method = RequestMethod.POST)
    @ResponseBody
    public int SaveOrUpdateUser(@RequestBody FG_User user){
        return systemServie.SaveOrUpdateUser(user);
    }

    /**
     * 设置用户停用
     * @return
     */
    @RequestMapping(value = "/SaveUserStatus", method = RequestMethod.GET)
    @ResponseBody
    public int SaveUserStatus(String id,int type){
        return systemServie.SaveUserStatus(id,type);
    }
    /**
     * 获取审核设置
     * @return
     */
    @RequestMapping(value = "/getApproveSetting", method = RequestMethod.GET)
    @ResponseBody
    public int getApproveSetting(){
        return systemServie.getApproveSetting();
    }

    @RequestMapping(value = "/getSendMailSetting", method = RequestMethod.GET)
    @ResponseBody
    public int getSendMailSetting(){
        return systemServie.getSendMailSetting();
    }

    /**
     * 保存审核设置
     * @return
     */
    @RequestMapping(value = "/SaveOrUpdateApproveSetting", method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> SaveOrUpdateApproveSetting(int status){

        return systemServie.SaveOrUpdateApproveSetting(status);
    }

    @RequestMapping(value = "/SaveOrUpdateMailSetting", method = RequestMethod.GET)
    @ResponseBody
    public int SaveOrUpdateMailSetting(int status){
        return systemServie.SaveOrUpdateMailSetting(status);
    }

    /**
     * 保存设置
     * @return
     */
    @RequestMapping(value = "/SaveOrUpdateSettingValue", method = RequestMethod.POST)
    @ResponseBody
    public int SaveOrUpdateSettingValue(@RequestBody Map<String,String> map){
        return systemServie.SaveOrUpdateSettingValue(map);
    }


    /**
     * 获取组织机构下的人员以及组织信息
     * @return
     */
    @RequestMapping(value = "/grtUserListByOrgId", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> grtUserListByOrgId(String orgid){
        return systemServie.grtUserListByOrgId(orgid);
    }

    @RequestMapping(value = "/getLogList", method = RequestMethod.POST)
    @ResponseBody
    public PagingEntity<Fg_Log> getLogList(@RequestBody Page page){
        return systemServie.getLogList(page);
    }

    @RequestMapping(value = "/getUserMailById", method = RequestMethod.GET)
    @ResponseBody
    public Map<String,String> getUserMailById(String id){
        return systemServie.getUserMailById(id);
    }

    @RequestMapping(value = "/makeLicense", method = RequestMethod.GET)
    public void makeLicense( @RequestParam(value = "FiledTimeStartLicense", required = false) String FiledTimeStartLicense,
                             @RequestParam(value = "FiledTimeEndLicense", required = false) String FiledTimeEndLicense,HttpServletResponse response){

        systemServie.makeLicense(FiledTimeStartLicense,FiledTimeEndLicense,response);

    }
}
