package phalaenopsis.fgbz.service;

import com.alibaba.fastjson.JSON;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import phalaenopsis.common.entity.*;
import phalaenopsis.common.method.ExportExcel;
import phalaenopsis.common.method.Tools.StrUtil;
import phalaenopsis.fgbz.common.HssfHelper;
import phalaenopsis.fgbz.dao.ILog;
import phalaenopsis.fgbz.dao.LawstandardDao;
import phalaenopsis.fgbz.dao.UserCenterDao;
import phalaenopsis.fgbz.entity.*;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;



/**
 *  法规标准
 */
@Service("lawstandardService")
public class LawstandardService {

    @Autowired
    private LawstandardDao lawstandardDao;

    @Autowired
    private UserCenterDao userCenterDao;

    public List<LawstandardType> ids = new ArrayList<>();


    public int testCount(){
        int i = lawstandardDao.testCount();
        return i;
    }

    /**
     * 查询法规标准类别目录
     * @return
     */
    public List<LawstandardType> SelectLawstandardType() {
        return lawstandardDao.SelectLawstandardType();
    }

    /**
     * 新增或修改法规标准类别
     * @param lawstandardType
     * @return
     */
    @Transactional
    @ILog(description="保存法规标准类别")
    public int  AddOrUpdateLawstandardType(LawstandardType lawstandardType){
        //新增
        if(lawstandardType.getId().equals("")){
            UUID uuid=UUID.randomUUID();
            String guid=uuid.toString();
            lawstandardType.setId(guid);
        }
        int itemlevelcode = 0;
        //点击的对象
        LawstandardType handleitem = lawstandardType.getHandleitem();

        //处理不同的类型
        switch(lawstandardType.getHandletype()){
            case "addEqual":
                itemlevelcode=  getLastItemLevelcode(handleitem.getParentid());
                lawstandardType.setItemlevelcode(itemlevelcode);
                break;
            case "addDown":
                itemlevelcode=  getLastItemLevelcode(handleitem.getId());
                lawstandardType.setItemlevelcode(itemlevelcode);
                break;
            case "moveUp":
                if(!StrUtil.isNullOrEmpty(lawstandardType.getItemlevelcode().toString())){
                    handTreeLevel(lawstandardType);
                    lawstandardType.setItemlevelcode(lawstandardType.getItemlevelcode()-1);
                }
                break;
            case "moveDown":
                if(!StrUtil.isNullOrEmpty(lawstandardType.getItemlevelcode().toString())){
                    handTreeLevel(lawstandardType);
                    lawstandardType.setItemlevelcode(lawstandardType.getItemlevelcode()+1);
                }
                break;
        }

         lawstandardDao.AddOrUpdateLawstandardType(lawstandardType);

        return OpResult.Success;
    }

    /**
     * 获取当前层级最后的层级代码
     * @param id
     * @return
     */
    public int getLastItemLevelcode(String id){
        return lawstandardDao.getLastItemLevelcode(id);
    }

    /**
     * 处理上移和下移
     * @return
     */
    public int handTreeLevel(LawstandardType lawstandardType){
        return  lawstandardDao.handTreeLevel(lawstandardType);
    }

    /**
     * 删除法规标准类别
     * @param lawstandardType
     * @return
     */
    @Transactional
    @ILog(description="删除法规标准类别")
    public int  DeleteLawstandardType(LawstandardType lawstandardType){

        handTreeLevel(lawstandardType);
         lawstandardDao.DeleteLawstandardType(lawstandardType);
        return OpResult.Success;
    }

    /**
     * 获取子节点
     * @param id
     * @return
     */
    public List<LawstandardType> getChildNode(String id){
        return lawstandardDao.getChildNode(id);
    }

    /**
     * 获取父节点
     * @return
     */
    public LawstandardType getParentLawstandardTypeById(String id){
        return lawstandardDao.getParentLawstandardTypeById(id);
    }

    /**
     * 向下递归树
     * @param id
     * @return
     */
    public  List<LawstandardType> getLawsTree(String id){

        List<LawstandardType> list = getChildNode(id);

        while (list!=null&&list.size()>0){
            ids.addAll(list);
            for(LawstandardType lawstandardType:list){
                list=getLawsTree(lawstandardType.getId());
            }
        }
        return list;
    }


    /**
     * 改变类别数量
     * @param list
     * @return
     */
    public int changeLawstandardCount(List<LawstandardType> list,String type){

        Map<String, Object>  map =new HashMap<>();
        map.put("type",type);
        map.put("TreeValue",list);
        return lawstandardDao.changeLawstandardCount(map);
    }


    /**
     * 获取法规标准列表
     * @param page
     * @return
     */
    public PagingEntity<Lawstandard> getLawstandardList(Page page){

        Map<String, Object> conditions = new HashMap<String, Object>();

        if(page.getConditions()!=null) {
            //查询条件
            for (Condition condition : page.getConditions()) {
                if (condition.getKey().equals("Number")) {
                    conditions.put("Number", condition.getValue());
                } else if (condition.getKey().equals("Title")) {
                    conditions.put("Title", condition.getValue());
                } else if (condition.getKey().equals("FiledTimeStart")) {
                    conditions.put("FiledTimeStart", condition.getValue());
                } else if (condition.getKey().equals("FiledTimeEnd")) {
                    conditions.put("FiledTimeEnd", condition.getValue());
                } else if (condition.getKey().equals("State")) {
                    conditions.put("State", condition.getValue());
                } else if (condition.getKey().equals("organization")) {
                    conditions.put("organization", condition.getValue());
                } else if (condition.getKey().equals("MaterialTmeStart")) {
                    conditions.put("MaterialTmeStart", condition.getValue());
                } else if (condition.getKey().equals("MaterialTmeEnd")) {
                    conditions.put("MaterialTmeEnd", condition.getValue());
                } else if (condition.getKey().equals("TreeValue")) {
                    ids =new ArrayList<>();
                    LawstandardType lawSelf = new LawstandardType();
                    lawSelf.setId(condition.getValue());
                    ids.add(lawSelf);
                    getLawsTree(condition.getValue());
                    conditions.put("TreeValue",ids );
                }else if (condition.getKey().equals("KeyWords")){
                    conditions.put("KeyWords", condition.getValue());
                }else if(condition.getKey().equals("ApproveStatus")){
                    conditions.put("ApproveStatus", condition.getValue());
                }else if(condition.getKey().equals("KeyWordsSingle")){
                    conditions.put("KeyWordsSingle", condition.getValue());
                }else if(condition.getKey().equals("ReplaceOrRefenceid")){
                    conditions.put("ReplaceOrRefenceid", condition.getValue());
                }else if(condition.getKey().equals("IsBatch")){
                    conditions.put("IsBatch", condition.getValue());
                }else if(condition.getKey().equals("Userid")){
                    conditions.put("Userid", condition.getValue());
                }else if(condition.getKey().equals("LawInputuserid")){
                    conditions.put("LawInputuserid", condition.getValue());
                }else if(condition.getKey().equals("selectInputUser")){
                    conditions.put("selectInputUser", condition.getValue());
                }else if(condition.getKey().equals("OrgList")){

                    FG_Organization org = JSON.parseObject(condition.getValue(),FG_Organization.class);
                    conditions.put("OrgList", org.getChildsorg());
                }else if(condition.getKey().equals("Ordertype")){
                    String ordertest ="";
                    String ordertest1 ="";
                    //排序
                    switch (condition.getValue()){
                        case "0":
                            ordertest = " t.APPROVESTATUS ,t.INPUTDATE ";
                            ordertest1 = " t1.APPROVESTATUS,t1.INPUTDATE ";
                            break;
                        case "1":
                            ordertest = " t.APPROVESTATUS  ,t.INPUTDATE DESC ";
                            ordertest1 = " t1.APPROVESTATUS,t1.INPUTDATE DESC ";
                            break;
                        case "2":
                            ordertest = " t.APPROVESTATUS,t.MODIFYDATE ";
                            ordertest1 = " t1.APPROVESTATUS,t1.MODIFYDATE  ";
                            break;
                        case "3":
                            ordertest = " t.APPROVESTATUS  ,t.MODIFYDATE DESC ";
                            ordertest1 = " t1.APPROVESTATUS ,t1.MODIFYDATE DESC ";
                            break;
                    }
                    conditions.put("Ordertype",ordertest);
                    conditions.put("Ordertype1",ordertest1);
                }else if(condition.getKey().equals("SearchOrdertype")){
                    String ordertest ="";
                    String ordertest1 ="";
                    //排序
                    switch (condition.getValue()){
                        case "0":
                            ordertest = " if(ISNULL(t.ISTOP),0,t.ISTOP) desc  ,t.INPUTDATE ";
                            ordertest1 = " if(ISNULL(t1.ISTOP),0,t1.ISTOP) desc ,t1.INPUTDATE ";
                            break;
                        case "1":
                            ordertest = " if(ISNULL(t.ISTOP),0,t.ISTOP) desc  ,t.INPUTDATE DESC ";
                            ordertest1 = " if(ISNULL(t1.ISTOP),0,t1.ISTOP) desc ,t1.INPUTDATE DESC ";
                            break;
                        case "2":
                            ordertest = " if(ISNULL(t.ISTOP),0,t.ISTOP) desc ,t.IMPDATE ";
                            ordertest1 = " if(ISNULL(t1.ISTOP),0,t1.ISTOP) desc ,t1.IMPDATE  ";
                            break;
                        case "3":
                            ordertest = " if(ISNULL(t.ISTOP),0,t.ISTOP) desc  ,t.IMPDATE DESC ";
                            ordertest1 = " if(ISNULL(t1.ISTOP),0,t1.ISTOP) desc  ,t1.IMPDATE DESC ";
                            break;
                    }
                    conditions.put("Ordertype",ordertest);
                    conditions.put("Ordertype1",ordertest1);
                }

            }
        }


        // 1,根据条件一共查询到的数据条数
        int count = lawstandardDao.getLawstandardListCount(conditions);

            if(page.getPageNo()==1){
                conditions.put("startRow", 0 );
            }else{
                conditions.put("startRow", page.getPageSize() * (page.getPageNo() - 1) );
            }
            conditions.put("endRow", page.getPageSize());

        // 2, 查询到当前页数的数据
        List<Lawstandard> list = lawstandardDao.getLawstandardList(conditions);

        PagingEntity<Lawstandard> result = new PagingEntity<Lawstandard>();
        result.setPageCount(count);

        int pageCount = 0 == count ? 1 : (count - 1) / page.getPageSize() + 1; // 由于计算pageCount存在整除的情况，所以计算的时候先减1在除以pageSize
        result.setPageNo(page.getPageNo());
        result.setPageSize(page.getPageSize());
        result.setPageCount(pageCount);
        result.setRecordCount(count);
        result.setCurrentList(list);

        return result;
    }

    //首页查询法规单独写
    public List<Lawstandard> getUptodateLawstandardList(Page page) {
        Map<String, Object> conditions = new HashMap<String, Object>();
        if (page.getConditions() != null) {
            //查询条件
            for (Condition condition : page.getConditions()) {
                if (condition.getKey().equals("TreeValue")) {
                    ids = new ArrayList<>();
                    LawstandardType lawSelf = new LawstandardType();
                    lawSelf.setId(condition.getValue());
                    ids.add(lawSelf);
                    getLawsTree(condition.getValue());
                    conditions.put("TreeValue", ids);
                } else if (condition.getKey().equals("ApproveStatus")) {
                    conditions.put("ApproveStatus", condition.getValue());
                }
            }
        }
        conditions.put("startRow", 0);
        conditions.put("endRow", 10);

        List<Lawstandard> list = lawstandardDao.getUptodateLawstandardList(conditions);

        return list;
    }
    /**
     * 获取全文检索法规标准列表
     * @param page
     * @return
     */
    public PagingEntity<Lawstandard> getSolrList(@RequestBody Page page){

        Map<String, Object> conditions = new HashMap<String, Object>();

        if(page.getConditions()!=null) {
            //查询条件
            for (Condition condition : page.getConditions()) {
                if (condition.getKey().equals("Number")) {
                    conditions.put("Number", condition.getValue());
                } else if (condition.getKey().equals("Title")) {
                    conditions.put("Title", condition.getValue());
                } else if (condition.getKey().equals("FiledTimeStart")) {
                    conditions.put("FiledTimeStart", condition.getValue());
                } else if (condition.getKey().equals("FiledTimeEnd")) {
                    conditions.put("FiledTimeEnd", condition.getValue());
                } else if (condition.getKey().equals("State")) {
                    conditions.put("State", condition.getValue());
                }   else if (condition.getKey().equals("TreeValue")) {
                    ids =new ArrayList<>();
                    LawstandardType lawSelf = new LawstandardType();
                    lawSelf.setId(condition.getValue());
                    ids.add(lawSelf);
                    getLawsTree(condition.getValue());
                    conditions.put("TreeValue",ids );
                }else if (condition.getKey().equals("KeyWords")){
                    conditions.put("KeyWords", condition.getValue());
                }else if(condition.getKey().equals("ApproveStatus")){
                    conditions.put("ApproveStatus", condition.getValue());
                }else if(condition.getKey().equals("EnglishTitle")){
                    conditions.put("EnglishTitle", condition.getValue());
                }else if(condition.getKey().equals("KeyWordsSingle")){
                    conditions.put("KeyWordsSingle", condition.getValue());
                }else if(condition.getKey().equals("Summaryinfo")){
                    conditions.put("Summaryinfo", condition.getValue());
                }else if(condition.getKey().equals("Solr")){
                    String[] solrList  = condition.getValue().split(" ");
                    conditions.put("Solr", solrList);
                }

            }
        }


        // 1,根据条件一共查询到的数据条数
        int count = lawstandardDao.getSolrListCount(conditions);


            if(page.getPageNo()==1){
                conditions.put("startRow", 0 );
            }else{
                conditions.put("startRow", page.getPageSize() * (page.getPageNo() - 1) );
            }
            conditions.put("endRow", page.getPageSize());


        // 2, 查询到当前页数的数据
        List<Lawstandard> list = lawstandardDao.getSolrList(conditions);

        PagingEntity<Lawstandard> result = new PagingEntity<Lawstandard>();
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
     * 导出法规列表
     */
    @ILog(description="导出法规列表")
    public void exportExcel( List<Condition> list,HttpServletResponse response){
        Page page = new Page();
        page.setConditions(list);
        page.setPageNo(1);
        page.setPageSize(Integer.MAX_VALUE);

        List<Lawstandard>  listLaws = getLawstandardList(page).getCurrentList();

        ExportExcel exportExcel = new ExportExcel();

        String[] fields = {

                "chinesename",
                "englishname",
                "keywords",
                "code",
                "releasedate",
                "impdate",
                "summaryinfo",
                "memo",
                "typename",
                "pubdepname",
                "statusname"

        };
        exportExcel.exportExcel(fields,new Lawstandard(),listLaws,"法规标准",response);

    }

    /**
     * 批量导入
     * @return
     */
    @Transactional
    @ILog(description="批量导入法规")
    public  Map<String,Object> importLawstandard( List<LawstandardExcel> list,FG_User user) throws ParseException {

        Map<String,Object> map = new HashMap<>();

        List<Lawstandard> listImport = new ArrayList<>();

        int rownum =0;
        for (int i=0;i<list.size();i++ ) {

            rownum=i+3;
            LawstandardExcel excel = list.get(i);
            Lawstandard lawstandard = new Lawstandard();
            //中文标题不能为空
            if(StrUtil.isNullOrEmpty(excel.getChinesename())){
                map.put("Result",OpResult.Failed);
                map.put("Msg","第"+rownum+"行中文标题为空");
                return map;
            }else {
                lawstandard.setChinesename(excel.getChinesename());
            }
            //验证编号不能为空
            if(StrUtil.isNullOrEmpty(excel.getCode())){
                map.put("Result",OpResult.Failed);
                map.put("Msg","第"+rownum+"行编号为空");
                return map;
            }else{
                lawstandard.setCode(excel.getCode());
                String checkcode = filter(lawstandard.getCode());
                lawstandard.setCheckcode(checkcode);
            }
            if(StrUtil.isNullOrEmpty(excel.getTypename())){
                map.put("Result",OpResult.Failed);
                map.put("Msg","第"+rownum+"行类别为空");
                return map;
            }else{
               String lawtypeid= lawstandardDao.getLawTypeByName(excel.getTypename());
               if(StrUtil.isNullOrEmpty(lawtypeid)){
                   map.put("Result",OpResult.Failed);
                   map.put("Msg","第"+rownum+"行类别不存在");
                   return map;
               }else{
                   lawstandard.setLawtype(lawtypeid);
               }
            }
            if(!StrUtil.isNullOrEmpty(excel.getPubdepname())){
                String pubid= lawstandardDao.getPubOrgnameByName(excel.getPubdepname());
                if(StrUtil.isNullOrEmpty(pubid)){
                    map.put("Result",OpResult.Failed);
                    map.put("Msg","第"+rownum+"行发布部门不存在");
                    return map;
                }else{
                    lawstandard.setOrganization(pubid);
                }
            }

            if(!StrUtil.isNullOrEmpty(excel.getStatusname())){
                String staid= lawstandardDao.getStatusIdByName(excel.getStatusname());
                if(StrUtil.isNullOrEmpty(staid)){
                    map.put("Result",OpResult.Failed);
                    map.put("Msg","第"+rownum+"行状态不存在");
                    return map;
                }else{
                    lawstandard.setStatus(staid);
                }
            }

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd ");
            lawstandard.setEnglishname(excel.getEnglishname());
            lawstandard.setKeywords(excel.getKeywords());

            if(!StrUtil.isNullOrEmpty(excel.getReleasedate())){
                lawstandard.setReleasedate( sdf.parse(excel.getReleasedate()));
            }
            if(!StrUtil.isNullOrEmpty(excel.getImpdate())){
                lawstandard.setImpdate( sdf.parse(excel.getImpdate()));
            }

            if(!StrUtil.isNullOrEmpty(excel.getReleasedate())&&!StrUtil.isNullOrEmpty(excel.getImpdate())){
                if(lawstandard.getReleasedate().getTime()>lawstandard.getImpdate().getTime()){
                    map.put("Result",OpResult.Failed);
                    map.put("Msg","第"+rownum+"行发布日期大于实施日期");
                    return map;
                }
            }

            lawstandard.setSummaryinfo(excel.getSummaryinfo());
            lawstandard.setMemo(excel.getMemo());
            lawstandard.setId(UUID.randomUUID().toString());
            lawstandard.setApprovestatus(1);
            lawstandard.setIsbatch(1);
            lawstandard.setInputuserid(user.getId());
            listImport.add(lawstandard);
        }
        for(Lawstandard law:listImport){

            lawstandardDao.SaveOrUpdateLawAndType(law);
            if(!StrUtil.isNullOrEmpty(law.getOrganization())){
                lawstandardDao.SaveOrUpdateLawAndPublish(law);
            }
            lawstandardDao.SaveOrUpdateLawstandard(law);

        }
        map.put("Result",OpResult.Success);
        return map;
    }
    /**
     * 删除法规标准
     * @return
     */
    @Transactional
    @ILog(description="删除法规标准")
    public int  DeleteLawstandardById(String id){

        lawstandardDao.deleteLawstandardById(id);
        lawstandardDao.deleteRefenceAll(id);
        lawstandardDao.deleteReplaceAll(id);
        lawstandardDao.DeleteLawAndType(id);

        //删除索引
        lawstandardDao.DeleteSolrTextById(id);

        //删除法规收藏夹关联
        Map<String,Object> map1 =new HashMap<>();
        map1.put("id",id);
        map1.put("type","law");
        userCenterDao.DeleteFavoriteLawsLink(map1);

        return OpResult.Success;
    }

    /**
     * 批量删除法规
     * @return
     */
    @Transactional
    @ILog(description="批量删除法规")
    public int DeleteAllSelectLawstandard(List<String> list){
        for (String str:list
             ) {
            DeleteLawstandardById(str);
        }
        return  OpResult.Success;
    }

    /**
     * 新增法规标准
     * @param lawstandard
     * @return
     */
    @Transactional
    @ILog(description="保存法规标准")
    public int SaveOrUpdateLawstandard(Lawstandard lawstandard){

        if(lawstandard.getId()==null||lawstandard.getId().equals("")){
            UUID uuid=UUID.randomUUID();
            String guid=uuid.toString();
            lawstandard.setId(guid);
        }

        //处理编码，以便查重
        String checkcode = filter(lawstandard.getCode());
        lawstandard.setCheckcode(checkcode);

        int num = lawstandardDao.checklawCode(lawstandard);

        if(num>0){
            int isWorking = 461;
            OpResult opResult = new OpResult(isWorking);
            return opResult.Code;
        }

        lawstandardDao.deleteRefence(lawstandard.getId());
        lawstandardDao.deleteReplace(lawstandard.getId());
        lawstandardDao.SaveOrUpdateLawstandard(lawstandard);

        if(!lawstandard.getLawtype().isEmpty()){
            lawstandardDao.SaveOrUpdateLawAndType(lawstandard);
        }

            if(lawstandard.getOrganization()!=null){
                lawstandardDao.SaveOrUpdateLawAndPublish(lawstandard);
            }



        //建立与附件的关系
        if(lawstandard.getFileids().size()>0){
            lawstandardDao.SaveFileLink(lawstandard);
        }


        //添加引用关系
        if(lawstandard.getRefence()!=null&&lawstandard.getRefence().size()>0){

            for(Lawstandard item:lawstandard.getRefence()){
                UUID uuidRefence=UUID.randomUUID();
                String guidRefence=uuidRefence.toString();

                RefenceOrReplace refenceOrReplace = new RefenceOrReplace();
                refenceOrReplace.setId(guidRefence);
                refenceOrReplace.setSid(lawstandard.getId());
                refenceOrReplace.setRelatedid(item.getId());
                lawstandardDao.addRefence(refenceOrReplace);
            }

        }

        //添加替换关系
        if(lawstandard.getReplace()!=null&&lawstandard.getReplace().size()>0){

            for(Lawstandard item:lawstandard.getReplace()){
                UUID uuidReplace=UUID.randomUUID();
                String guidReplace=uuidReplace.toString();

                RefenceOrReplace refenceOrReplace = new RefenceOrReplace();
                refenceOrReplace.setId(guidReplace);
                refenceOrReplace.setSid(item.getId());
                refenceOrReplace.setRelatedid(lawstandard.getId());
                lawstandardDao.addReplace(refenceOrReplace);
                //发布时修改替代关系
                if(lawstandard.getApprovestatus()==3){
                    lawstandardDao.updateRleplaceStaus(item.getId());
                }
            }

        }

        return OpResult.Success;
    }

    //更新所有法规的编号
    public int UpdateAllLawstandardCode(){
        List<Lawstandard> list =lawstandardDao.getAllLawstandard();
        if(list!=null&&list.size()>0){
            for (Lawstandard lawstandard:list){
                //处理编码，以便查重
                String checkcode = filter(lawstandard.getCode());
                lawstandard.setCheckcode(checkcode);
                lawstandardDao.updateLawCheckCode(lawstandard);
            }
        }
        return OpResult.Success;
    }

    //提取中文数字和字母
    public String filter(String character)
    {
        character = character.replaceAll("[^a-zA-Z0-9\\u4e00-\\u9fa5]", "");
        return character;
    }
    /**
     * 获取法规信息
     * @param id
     * @return
     */
    public Lawstandard getLawstandardById(String id){

        Lawstandard lawstandard = lawstandardDao.getLawstandardById(id);

        //获取引用关系
        lawstandard.setRefence(lawstandardDao.getRefenceList(id));
        //获取替代关系
        lawstandard.setReplace(lawstandardDao.getReplaceList(id));

        return lawstandard;
    }

    public  void AddLawstandardCount(Lawstandard lawstandard){

        if(lawstandard.getClickcount()==null||lawstandard.getClickcount().equals("")){
            lawstandard.setClickcount(1);
        }else{
            int num = lawstandard.getClickcount()+1;
            lawstandard.setClickcount(num);
        }
        lawstandardDao.AddLawstandardCount(lawstandard);

    }

    /**
     * 置顶
     * @param lawstandard
     */
    @ILog(description="置顶法规标准")
    public  void LawstandardIsTop(Lawstandard lawstandard){
        lawstandardDao.LawstandardIsTop(lawstandard);
    }

    /**************************首页统计************************************/


    public Map<Object,Object> getHomeChart() {

        Map<Object,Object> map = new HashMap<>();

        List<ChartInfo> listdPeople = lawstandardDao.getUploadPeople();
        List<ChartInfo> listOrgname= lawstandardDao.getUploadOrgname();
        List<ChartInfo> listType= lawstandardDao.getUploadType();

        map.put("People",listdPeople);
        map.put("Orgname",listOrgname);
        map.put("Type",listType);

        return map;
    }

    /**
     * 首页导出
     */
    @ILog(description="首页统计导出")
    public void downHomeChart(HttpServletResponse response) throws IOException {

        List<ChartInfo> listdPeople = lawstandardDao.getUploadPeople();
        List<ChartInfo> listOrgname = lawstandardDao.getUploadOrgname();
        List<ChartInfo> listType = lawstandardDao.getUploadType();

        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("数据统计");
        HSSFCellStyle style = HssfHelper.getHssfCellStyle(wb, 3);

        String[] CloumnName = new String[]{"用户姓名", "个数"};
        HSSFRow rowTitle = sheet.createRow(0);
        HSSFCellStyle headstyle = HssfHelper.getHssfCellStyle(wb, 1);
        HSSFCell cellTitle = rowTitle.createCell(1);
        cellTitle.setCellValue("上传前十统计");
        cellTitle.setCellStyle(headstyle);
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 1, 2));
        HSSFRow row = sheet.createRow(1);

        int nI;
        for (nI = 0; nI < CloumnName.length; nI++) {
            HSSFCell cell = row.createCell(nI + 1);
            cell.setCellValue(CloumnName[nI]);
            cell.setCellStyle(style);
        }

        for (nI = 0; nI < listdPeople.size(); nI++) {
            row = sheet.createRow(nI + 2);
            ChartInfo item = listdPeople.get(nI);
            row.createCell(1).setCellValue(item.getName());
            row.createCell(2).setCellValue(item.getCount());
            HssfHelper.setRowStyle(row, 1, 2, style);
        }

        /***************************上传部门***************************/
        int rowOrgname = listdPeople.size() + 3;
        String[] CloumnNameOrgname = new String[]{"组织名称", "个数"};
        rowTitle = sheet.createRow(rowOrgname);
        headstyle = HssfHelper.getHssfCellStyle(wb, 1);
        cellTitle = rowTitle.createCell(1);
        cellTitle.setCellValue("上传部门统计");
        cellTitle.setCellStyle(headstyle);
        sheet.addMergedRegion(new CellRangeAddress(rowOrgname, rowOrgname, 1, 2));
        row = sheet.createRow(1);

        for (nI = 0; nI < CloumnNameOrgname.length; nI++) {
            HSSFCell cell = row.createCell(nI + 1);
            cell.setCellValue(CloumnName[nI]);
            cell.setCellStyle(style);
        }

        for (nI = 0; nI < listOrgname.size(); nI++) {
            row = sheet.createRow(rowOrgname+nI+ 1);
            ChartInfo item = listOrgname.get(nI);
            row.createCell(1).setCellValue(item.getName());
            row.createCell(2).setCellValue(item.getCount());
            HssfHelper.setRowStyle(row, 1, 2, style);
        }
        /***************************上传分类***************************/
        int rowType = rowOrgname + listOrgname.size() + 2;
        String[] CloumnNameType = new String[]{"类型名称", "个数"};
        rowTitle = sheet.createRow(rowType);
        headstyle = HssfHelper.getHssfCellStyle(wb, 1);
        cellTitle = rowTitle.createCell(1);
        cellTitle.setCellValue("上传分类统计");
        cellTitle.setCellStyle(headstyle);
        sheet.addMergedRegion(new CellRangeAddress(rowType, rowType, 1, 2));
        row = sheet.createRow(1);

        for (nI = 0; nI < CloumnNameType.length; nI++) {
            HSSFCell cell = row.createCell(nI + 1);
            cell.setCellValue(CloumnName[nI]);
            cell.setCellStyle(style);
        }

        for (nI = 0; nI < listType.size();nI++) {
            row = sheet.createRow(rowType+nI+1);
            ChartInfo item = listType.get(nI);
            row.createCell(1).setCellValue(item.getName());
            row.createCell(2).setCellValue(item.getCount());
            HssfHelper.setRowStyle(row, 1, 2, style);
        }

        sheet.setColumnWidth(1, 9000);
        sheet.setColumnWidth(2, 5000);

        response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode("数据统计", "utf-8") + ".xls");
        OutputStream out = response.getOutputStream();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        wb.write(baos);
        byte[] xlsBytes = baos.toByteArray();
        out.write(xlsBytes);
        out.close();

    }

    /**************************首页类别导航**************************************/

    public List<LawstandardType> getHomePageLawsType(){

        List<LawstandardType> result = new ArrayList<>();
        result = lawstandardDao.getHomePageLawsType();
        if(result.size()>0){
            for (LawstandardType lawstandardType:result ) {

                lawstandardType.setChildLists(getChildNode(lawstandardType.getId()));
            }
        }
        return  result;
    }

    public int getHomePageLawCount(String id){

        Map<String, Object> conditions = new HashMap<String, Object>();

        ids =new ArrayList<>();
        LawstandardType lawSelf = new LawstandardType();
        lawSelf.setId(id);
        ids.add(lawSelf);
        getLawsTree(id);
        conditions.put("TreeValue",ids );
        return  lawstandardDao.getHomePageLawCount(conditions);
    }
}
