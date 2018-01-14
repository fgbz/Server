package phalaenopsis.fgbz.service;

import org.apache.commons.io.FilenameUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import phalaenopsis.common.dao.IAttachmentDao;
import phalaenopsis.common.entity.Attachment.Attachment;
import phalaenopsis.common.entity.Attachment.AttachmentSource;
import phalaenopsis.common.entity.Condition;
import phalaenopsis.common.entity.OpResult;
import phalaenopsis.common.entity.Page;
import phalaenopsis.common.entity.PagingEntity;
import phalaenopsis.common.method.Attachment.Multipart;
import phalaenopsis.common.method.Tools.StrUtil;
import phalaenopsis.common.method.cache.UserCache;
import phalaenopsis.common.service.AttachmentService;
import phalaenopsis.fgbz.common.office2PDF;
import phalaenopsis.fgbz.dao.ILog;
import phalaenopsis.fgbz.dao.LawstandardDao;
import phalaenopsis.fgbz.dao.SystemDao;
import phalaenopsis.fgbz.entity.*;

import javax.annotation.Resource;
import java.io.*;
import java.util.*;

import static phalaenopsis.common.method.Basis.getCurrentFGUser;

@Service("systemServie")
public class SystemServie {

    @Autowired
    private SystemDao systemDao;

    @Autowired
    private LawstandardDao lawstandardDao;

    public IAttachmentDao dao;

    @Resource(name = "attachmentDao")
    public void setDao(IAttachmentDao dao) {
        this.dao = dao;
    }

    @Autowired
    private AttachmentService attachmentService;

    private  List<FG_Organization> ids = new ArrayList<FG_Organization>();

    private List<File> filelist = new ArrayList<>();

    public  Map<String, Object> login(String accountJm, String passwordJm){

        Map<String, Object> map = new HashMap<String, Object>();

        Map<String,Object> map1 = new HashMap<>();

        map1.put("account",accountJm);
        map1.put("password",passwordJm);
        FG_User user = systemDao.getUserByAccount(map1);

        if(user!=null&&user.getRoles()!=null&&user.getRoles().size()>0){
            //获取用户所有权限
            user.setMenus(systemDao.getUserMenu(user));
        }

        if(user==null){
            map.put("LoginState", false);
        }else{
            Map<String, Object> mapList =  grtUserListByOrgId(user.getOrgid());
            user.setUserList((List<FG_User>)mapList.get("UserList"));
            user.setOrgList((List<FG_Organization>) mapList.get("OrgList"));
            map.put("LoginState", true);
        }
        map.put("LoginResult", user);
        String ticket = UUID.randomUUID().toString();
        map.put("ticket", ticket);
        UserCache.put(ticket, user);

        return map;

    }

    //提取中文数字和字母
    public String filter(String character)
    {
        character = character.replaceAll("[^a-zA-Z0-9\\u4e00-\\u9fa5]", "");
        return character;
    }

    @Transactional
    public int handleHistory(String path) throws IOException {

        filelist = new ArrayList<>();
        getFileList(path);

        if(filelist.size()==0){

            return  OpResult.PreconditionFailed;
        }
        //历史法规
        List<Lawstandard> histroyLaws = lawstandardDao.getHistroyLaws();

        FG_User user = getCurrentFGUser();
        if(histroyLaws!=null&&histroyLaws.size()>0) {

            for (Lawstandard lawstandard:histroyLaws) {
                List<Attachment> getAttachments =dao.getAttachments(lawstandard.getOldid());

                lawstandard.setInputuserid(user.getId());
                lawstandard.setLawtype("0055d568-536b-4275-8e69-d5be69e3112c");
                //处理编码，以便查重
                String checkcode = filter(lawstandard.getCode());
                lawstandard.setCheckcode(checkcode);
                lawstandard.setApprovestatus(3);
                lawstandard.setClickcount(0);
                lawstandard.setIstop(0);

                lawstandardDao.SaveOrUpdateLawstandard(lawstandard);
                lawstandardDao.SaveOrUpdateLawAndType(lawstandard);

                lawstandardDao.updateLawUser(lawstandard);

                //替换历史id
                lawstandardDao.updatePublishByOldID(lawstandard);
                lawstandardDao.updateReplaceSidByOldID(lawstandard);
                lawstandardDao.updateReplacePidByOldID(lawstandard);

                //处理附件
                if(getAttachments!=null&&getAttachments.size()>0){
                    for (Attachment attachment:getAttachments) {
                        attachment.setRefid(lawstandard.getId());
                        handleFile(attachment);
                    }
                }
                //更新solr
//                lawstandardDao.DeleteSolrTextById(lawstandard.getId());
//                lawstandardDao.SaveSolrTextById(lawstandard.getId());



            }

        }

        return  OpResult.Success;
    }

    public void handleFile(Attachment attachment) throws IOException {

        FG_User user = getCurrentFGUser();
        for(int i=0;i<filelist.size();i++){
            if(filelist.get(i).getName().equals(attachment.getActualFile())){
                String oldid = attachment.getId();

                String guid = UUID.randomUUID().toString();
                String fileName = attachment.getActualFile();
                String ext = FilenameUtils.getExtension(fileName); // fileName.split("\\.")[1];
                String storeFile =guid + "." + ext;

                // 保存文件
                String storageFolder = Attachment.GetFileStorageFolder(guid);
                File file = new File(storageFolder + storeFile);

                if (!file.exists())
                    file.createNewFile();
                InputStream ii = null;
                OutputStream oo = null;
                try {
                    ii = new FileInputStream(filelist.get(i));
                    oo = new FileOutputStream(file);
                    int size = 0;
                    byte[] buf = new byte[1024];
                    while ((size = ii.read(buf)) != -1) {
                        oo.write(buf,0,size);
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                finally
                {
                    oo.flush();
                    oo.close();
                    ii.close();
                }
                attachment.setId(UUID.randomUUID().toString());
                attachment.setFileExt(ext);
                attachment.setActualFile(storeFile);
                attachment.setPath(storageFolder);
                attachment.setInputuserid(user.getId());
                //法规上传需要解析获取pdf中的文字
                if(ext.toLowerCase().equals("pdf")){
                    try {
                        PDDocument document=PDDocument.load(file);
                        // 获取页码
                        int pages = document.getNumberOfPages();

                        // 读文本内容
                        PDFTextStripper stripper=new PDFTextStripper();
                        // 设置按顺序输出
                        stripper.setSortByPosition(true);
                        stripper.setStartPage(1);
                        stripper.setEndPage(pages);
                        String content = stripper.getText(document);
                        attachment.setContent(content);
                    }catch (Exception e){

                    }



                }
                if(isDocFile(ext)){
                    //转化office pdf
                    office2PDF.office2PDF(storageFolder + storeFile,storageFolder+guid+".pdf");
                }
                dao.deleteFgbzfile(oldid);
                dao.saveFgbz(attachment);
                break;
            }
        }
    }

    private boolean isDocFile(String extName) {
        boolean result = false;
        String fileExt = ".doc;.txt;.docx;";
        result = fileExt.contains(extName.toLowerCase());

        return result;
    }

    public  void getFileList(String strPath) {
        File dir = new File(strPath);
        File[] files = dir.listFiles(); // 该文件目录下文件全部放入数组
        if (files != null) {
            for (int i = 0; i < files.length; i++) {
                if (files[i].isDirectory()) { // 判断是文件还是文件夹
                    getFileList(files[i].getAbsolutePath()); // 获取文件绝对路径
                } else {
                    filelist.add(files[i]);
                }
            }
        }
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
    @Transactional
    @ILog(description="保存组织机构")
    public int  AddOrUpdateOrganizationType(FG_Organization organization){
        if(organization.getId()==null||organization.getId().equals("")){
            UUID uuid=UUID.randomUUID();
            String guid=uuid.toString();
            organization.setId(guid);
        }

        int itemlevelcode = 0;
        //点击的对象
        FG_Organization handleitem = organization.getHandleitem();

        //处理不同的类型
        switch(organization.getHandletype()){
            case "addEqual":
                itemlevelcode=  getLastItemLevelcode(handleitem.getParentid());
                organization.setItemlevelcode(itemlevelcode);
                break;
            case "addDown":
                itemlevelcode=  getLastItemLevelcode(handleitem.getId());
                organization.setItemlevelcode(itemlevelcode);
                break;
            case "moveUp":
                if(!StrUtil.isNullOrEmpty(organization.getItemlevelcode().toString())){
                    handTreeLevel(organization);
                    organization.setItemlevelcode(organization.getItemlevelcode()-1);
                }
                break;
            case "moveDown":
                if(!StrUtil.isNullOrEmpty(organization.getItemlevelcode().toString())){
                    handTreeLevel(organization);
                    organization.setItemlevelcode(organization.getItemlevelcode()+1);
                }
                break;
        }
         systemDao.AddOrUpdateOrganizationType(organization);
        return OpResult.Success;
    }

    /**
     * 获取当前层级最后的层级代码
     * @param id
     * @return
     */
    public int getLastItemLevelcode(String id){
        return systemDao.getLastItemLevelcode(id);
    }

    /**
     * 处理上移和下移
     * @return
     */
    public int handTreeLevel(FG_Organization organization){
        return  systemDao.handTreeLevel(organization);
    }
    /**
     * 删除组织机构
     *
     * @param organization
     * @return
     */
    @Transactional
    @ILog(description="删除组织机构")
    public int DeleteOrganization(FG_Organization organization) {
        handTreeLevel(organization);
         systemDao.DeleteOrganization(organization);
        return OpResult.Success;
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
    @ILog(description="删除角色")
    public int deleteRoleByID(String id){
        return  systemDao.deleteRoleByID(id);
    }

    /**
     * 新增或编辑权限
     * @param role
     * @return
     */
    @Transactional
    @ILog(description="保存权限")
    public int SaveOrEditRole(FG_Role role){
        if(role.getId()==null||role.getId().equals("")){
            UUID uuid=UUID.randomUUID();
            String guid=uuid.toString();
            role.setId(guid);
        }

        if(role.getMenus()!=null&&role.getMenus().size()>0){
            for (FG_Menu  m:role.getMenus()
                 ) {
                m.setTableid(UUID.randomUUID().toString());
            }
        }
        return systemDao.SaveOrEditRole(role);
    }

    /**
     * 保存或编辑用户
     * @param user
     * @return
     */
    @Transactional
    @ILog(description="保存用户")
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

                for (FG_Role r:user.getRoles()
                        ) {
                    r.setTableid(UUID.randomUUID().toString());
                }

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
     * 获取人员
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

    /**
     * 获取组织机构下的人员以及组织信息
     * @return
     */
    public Map<String, Object> grtUserListByOrgId(String orgid){

        Map<String, Object> conditions = new HashMap<String, Object>();
        ids =new ArrayList<>();
        FG_Organization orgSelf = new FG_Organization();
        orgSelf.setId(orgid);
        ids.add(orgSelf);
        getOrgsTree(orgid);
        conditions.put("TreeValue",ids );

        List<FG_User> list= systemDao.getUserListByOrgId(conditions);
//        String str = "";
//        for(int i=0;i<ids.size();i++){
//            if(i<ids.size()-1){
//                str += "'"+ ids.get(i).getId()+"'"+",";
//            }else{
//                str += "'"+ids.get(i).getId()+"'";
//            }
//        }
        //结果集
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("OrgList",ids);
        result.put("UserList",list);
        return result;
    }

    @Transactional
    @ILog(description="删除用户")
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
       String str =  systemDao.getApproveSetting();
       if(str!=null){
           return Integer.parseInt(str);
       }
       else{
           return 1;
       }
   }

    /**
     * 保存设置
     * @return
     */
    public int SaveOrUpdateSettingValue(Map<String,String> map){
        systemDao.SaveOrUpdateSettingValue(map);
        return OpResult.Success;
    }

    /**
     * 保存审核设置
     * @return
     */
    @Transactional
    @ILog(description="修改审核设置")
    public Map<String,Object> SaveOrUpdateApproveSetting(int status){

        int num =0;
        //查询待审核的数量
        if(status==0){
            num= systemDao.getNeedCheckLawCount();
        }
        Map<String, Object> map = new HashMap<>();
        map.put("key","isApprove");
        map.put("value",status);
        map.put("count",0);
        Map<String,Object> result = new HashMap<>();
        if(num==0){
            systemDao.SaveOrUpdateApproveSetting(map);
        }
        result.put("count",num);
        result.put("Result",OpResult.Success);
        return result;
    }

    /**
     * 获取日志
     * @param page
     * @return
     */
    public PagingEntity<Fg_Log> getLogList(Page page){

        Map<String, Object> conditions = new HashMap<String, Object>();

        if(page.getConditions()!=null) {
            //查询条件
            for (Condition condition : page.getConditions()) {
                if (condition.getKey().equals("OperationName")) {
                    conditions.put("OperationName", condition.getValue());
                }   if (condition.getKey().equals("Userid")) {
                    conditions.put("Userid", condition.getValue());
                }  if (condition.getKey().equals("FiledTimeStart")) {
                    conditions.put("FiledTimeStart", condition.getValue());
                }  if (condition.getKey().equals("FiledTimeEnd")) {
                    conditions.put("FiledTimeEnd", condition.getValue());
                }

            }
        }


        // 1,根据条件一共查询到的数据条数
        int count = systemDao.SelectLogCount(conditions);

        if(page.getPageNo()==1){
            conditions.put("startRow", 0 );
        }else{
            conditions.put("startRow", page.getPageSize() * (page.getPageNo() - 1) );
        }
        conditions.put("endRow", page.getPageSize());

        // 2, 查询到当前页数的数据
        List<Fg_Log> list = systemDao.SelectLog(conditions);

        PagingEntity<Fg_Log> result = new PagingEntity<Fg_Log>();
        result.setPageCount(count);

        int pageCount = 0 == count ? 1 : (count - 1) / page.getPageSize() + 1; // 由于计算pageCount存在整除的情况，所以计算的时候先减1在除以pageSize
        result.setPageNo(page.getPageNo());
        result.setPageSize(page.getPageSize());
        result.setPageCount(pageCount);
        result.setRecordCount(count);
        result.setCurrentList(list);

        return result;
    }
}
