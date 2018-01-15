package phalaenopsis.fgbz.service;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import phalaenopsis.common.entity.Condition;
import phalaenopsis.common.entity.OpResult;
import phalaenopsis.common.entity.Page;
import phalaenopsis.common.entity.PagingEntity;
import phalaenopsis.common.method.ExportExcel;
import phalaenopsis.common.method.Tools.StrUtil;
import phalaenopsis.fgbz.dao.ILog;
import phalaenopsis.fgbz.dao.TechnicalDao;
import phalaenopsis.fgbz.dao.UserCenterDao;
import phalaenopsis.fgbz.entity.*;

import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service("technicalService")
public class TechnicalService {

    @Autowired
    private TechnicalDao technicalDao;

    @Autowired
    private UserCenterDao userCenterDao;

    private  List<TechnicalType> ids = new ArrayList<>();

    /**
     * 查询所有的技术文件
     *
     * @return
     */
    public  List<TechnicalType> SelectTechnicalType() {
        return technicalDao.SelectTechnicalType();
    }

    /**
     * 新增或修改技术文件
     *
     * @param technicalType
     * @return
     */
    @Transactional
    @ILog(description="保存技术文档")
    public int AddOrUpdateTechnicalType(TechnicalType technicalType) {

        //新增
        if(technicalType.getId().equals("")){
            UUID uuid=UUID.randomUUID();
            String guid=uuid.toString();
            technicalType.setId(guid);
        }
        int itemlevelcode = 0;
        //点击的对象
        TechnicalType handleitem = technicalType.getHandleitem();
        //处理不同的类型
        switch(technicalType.getHandletype()){
            case "addEqual":
                itemlevelcode=  getLastItemLevelcode(handleitem.getParentid());
                technicalType.setItemlevelcode(itemlevelcode);
                break;
            case "addDown":
                itemlevelcode=  getLastItemLevelcode(handleitem.getId());
                technicalType.setItemlevelcode(itemlevelcode);
                break;
            case "moveUp":
                if(!StrUtil.isNullOrEmpty(technicalType.getItemlevelcode().toString())){
                    handTreeLevel(technicalType);
                    technicalType.setItemlevelcode(technicalType.getItemlevelcode()-1);
                }
                break;
            case "moveDown":
                if(!StrUtil.isNullOrEmpty(technicalType.getItemlevelcode().toString())){
                    handTreeLevel(technicalType);
                    technicalType.setItemlevelcode(technicalType.getItemlevelcode()+1);
                }
                break;
        }


         technicalDao.AddOrUpdateTechnicalType(technicalType);

         return OpResult.Success;
    }

    /**
     * 获取当前层级最后的层级代码
     * @param id
     * @return
     */
    public int getLastItemLevelcode(String id){
        return technicalDao.getLastItemLevelcode(id);
    }

    /**
     * 处理上移和下移
     * @return
     */
    public int handTreeLevel(TechnicalType technicalType){
        return  technicalDao.handTreeLevel(technicalType);
    }



    /**
     * 删除技术文件类别
     * @param technicalType
     * @return
     */
    @Transactional
    @ILog(description="删除技术文档类别")
    public int  DeleteTechnicalType(TechnicalType technicalType){
        handTreeLevel(technicalType);
        technicalDao.DeleteTechnicalType(technicalType);
        return OpResult.Success;
    }

    /**
     * 获取子节点
     * @param id
     * @return
     */
    public List<TechnicalType> getChildNode(String id){
        return technicalDao.getChildNode(id);
    }

    public  List<TechnicalType> getLawsTree(String id){

        List<TechnicalType> list = getChildNode(id);

        while (list!=null&&list.size()>0){
            ids.addAll(list);
            for(TechnicalType technicalType:list){
                list=getLawsTree(technicalType.getId());
            }
        }
        return list;
    }

    /**
     * 获取技术标准列表
     * @param page
     * @return
     */
    public PagingEntity<Technical> getTechnicalList(Page page){

        Map<String, Object> conditions = new HashMap<String, Object>();

        boolean isOrder = false;

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
                }  else if (condition.getKey().equals("TreeValue")) {
                    ids =new ArrayList<>();
                    TechnicalType tecSelf = new TechnicalType();
                    tecSelf.setId(condition.getValue());
                    ids.add(tecSelf);
                    getLawsTree(condition.getValue());
                    conditions.put("TreeValue",ids );
                }else if (condition.getKey().equals("KeyWords")){
                    conditions.put("KeyWords", condition.getValue());
                }else if(condition.getKey().equals("KeyWordsSingle")){
                    conditions.put("KeyWordsSingle", condition.getValue());
                }else if(condition.getKey().equals("ApproveStatus")){
                    conditions.put("ApproveStatus", condition.getValue());
                }else if(condition.getKey().equals("IsBatch")){
                    conditions.put("IsBatch", condition.getValue());
                }else if(condition.getKey().equals("TecInputuserid")){
                    conditions.put("TecInputuserid", condition.getValue());
                }else if(condition.getKey().equals("selectInputUser")){
                    conditions.put("selectInputUser", condition.getValue());
                }else if(condition.getKey().equals("Duty")){
                    conditions.put("Duty", condition.getValue());
                }else if(condition.getKey().equals("OrgList")){

                    FG_Organization org = JSON.parseObject(condition.getValue(),FG_Organization.class);
                    conditions.put("OrgList", org.getChildsorg());
                }else if(condition.getKey().equals("Ordertype")){
                    String ordertest ="";
                    isOrder = true;
                    //排序
                    switch (condition.getValue()){
                        case "0":
                            ordertest = " t.approvestatus,t.INPUTDATE ";
                            break;
                        case "1":
                            ordertest = " t.approvestatus,t.INPUTDATE DESC ";
                            break;
                        case "2":
                            ordertest = " t.approvestatus,t.MODIFYDATE ";
                            break;
                        case "3":
                            ordertest = " t.approvestatus,t.MODIFYDATE DESC ";
                            break;
                    }
                    conditions.put("Ordertype",ordertest);
                }else if(condition.getKey().equals("SearchOrdertype")){
                    String ordertest ="";

                    isOrder = true;
                    //排序
                    switch (condition.getValue()){
                        case "0":
                            ordertest = " t.INPUTDATE ";
                            break;
                        case "1":
                            ordertest = " t.INPUTDATE DESC ";
                            break;
                        case "2":
                            ordertest = " t.RELEASEDATE ";
                            break;
                        case "3":
                            ordertest = " t.RELEASEDATE DESC ";
                            break;
                    }
                    conditions.put("Ordertype",ordertest);
                }

            }
        }

        if(!isOrder){
            conditions.put("Ordertype"," t.MODIFYDATE DESC ");
        }
        // 1,根据条件一共查询到的数据条数
        int count = technicalDao.getTechnicalListCount(conditions);

        if(page.getPageNo()==1){
            conditions.put("startRow", 0 );
        }else{
            conditions.put("startRow", page.getPageSize() * (page.getPageNo() -1) );
        }
        conditions.put("endRow", page.getPageSize());

        // 2, 查询到当前页数的数据
        List<Technical> list = technicalDao.getTechnicalList(conditions);

        PagingEntity<Technical> result = new PagingEntity<Technical>();
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
     * 导出技术文档
     */
    @ILog(description="导出技术文档")
    public void exportExcel( List<Condition> list,HttpServletResponse response){

        Page page = new Page();
        page.setConditions(list);
        page.setPageNo(1);
        page.setPageSize(Integer.MAX_VALUE);

        List<Technical>  listTecs = getTechnicalList(page).getCurrentList();

        ExportExcel exportExcel = new ExportExcel();

        String[] fields = {

                "chinesename",
                "englishname",
                "keywords",
                "code",
                "releasedate",
                "summaryinfo",
                "memo",
                "typename"

        };
        exportExcel.exportExcel(fields,new Technical(),listTecs,"技术文档",response);

    }

    /**
     * 批量导入
     * @return
     */
    @Transactional
    @ILog(description="批量导入技术文档")
    public Map<String,Object> importTechnical( List<TechnicalExcel> list,FG_User user) throws ParseException {

        Map<String,Object> map = new HashMap<>();
        List<Technical> listImport = new ArrayList<>();

        int rownum =0;
        for (int i=0;i<list.size();i++ ) {

            rownum=i+3;
            TechnicalExcel excel = list.get(i);
            Technical technical = new Technical();
            //中文标题不能为空
            if(StrUtil.isNullOrEmpty(excel.getChinesename())){
                map.put("Result",OpResult.Failed);
                map.put("Msg","第"+rownum+"行中文标题为空");
                return map;
            }else {
                technical.setChinesename(excel.getChinesename());
            }
            //验证编号不能为空
//            if(StrUtil.isNullOrEmpty(excel.getCode())){
//                map.put("Result",OpResult.Failed);
//                map.put("Msg","第"+rownum+"行编号为空");
//                return map;
//            }else{
//
//            }
            technical.setCode(excel.getCode());
            if(StrUtil.isNullOrEmpty(excel.getTypename())){
                map.put("Result",OpResult.Failed);
                map.put("Msg","第"+rownum+"行类别为空");
                return map;
            }else{
                String lawtypeid= technicalDao.getLawTypeByName(excel.getTypename());
                if(StrUtil.isNullOrEmpty(lawtypeid)){
                    map.put("Result",OpResult.Failed);
                    map.put("Msg","第"+rownum+"行类别不存在");
                    return map;
                }else{
                    technical.setTectype(lawtypeid);
                }
            }

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd ");
            technical.setEnglishname(excel.getEnglishname());
            technical.setKeywords(excel.getKeywords());
            if(!StrUtil.isNullOrEmpty(excel.getReleasedate())){
                technical.setReleasedate( sdf.parse(excel.getReleasedate()));
            }
            technical.setSummaryinfo(excel.getSummaryinfo());
            technical.setMemo(excel.getMemo());
            technical.setId(UUID.randomUUID().toString());
            technical.setApprovestatus(1);
            technical.setIsbatch(1);
            technical.setInputuserid(user.getId());
            listImport.add(technical);
        }
        for(Technical tec:listImport){
            technicalDao.SaveOrUpdateTechnical(tec);
            technicalDao.SaveOrUpdateTecAndType(tec);
        }
        map.put("Result",OpResult.Success);
        return map;
    }
    /**
     * 新增法规标准
     * @param technical
     * @return
     */
    @Transactional
    @ILog(description="保存技术文档")
    public int SaveOrUpdateTechnical(Technical technical){

        if(technical.getId()==null||technical.getId().equals("")){
            UUID uuid=UUID.randomUUID();
            String guid=uuid.toString();
            technical.setId(guid);
        }
        int num = technicalDao.checkTecName(technical);
        if(num>0){
            int isRepeat = 461;
            OpResult opResult = new OpResult(isRepeat);
            return opResult.Code;
        }

        technicalDao.SaveOrUpdateTechnical(technical);
        technicalDao.SaveOrUpdateTecAndType(technical);

        //建立与附件的关系
        if(technical.getFileids().size()>0){
            technicalDao.SaveFileLink(technical);
        }


        return OpResult.Success;
    }

    /**
     * 通过id删除技术文件
     * @param id
     * @return
     */
    @Transactional
    @ILog(description="删除技术文档")
    public int  DeleteTechnicalById( String id){
         technicalDao.DeleteTechnicalById(id);
         technicalDao.DeleteTecAndType(id);

        //删除法规收藏夹关联
        Map<String,Object> map1 =new HashMap<>();
        map1.put("id",id);
        map1.put("type","tec");

        userCenterDao.DeleteFavoriteLawsLink(map1);
        return OpResult.Success;
    }

    /**
     * 查看
     * @param id
     * @return
     */
    public Technical getTechnicalById(String id){
        return technicalDao.getTechnicalById(id);
    }

    /**************************首页类别导航**************************************/

    public List<TechnicalType> getHomePageTecsType(){

        List<TechnicalType> result = new ArrayList<>();
        result = technicalDao.getHomePageTecsType();
        if(result.size()>0){
            for (TechnicalType technicalType:result
                    ) {
                technicalType.setChildLists(getChildNode(technicalType.getId()));
            }
        }
        return  result;
    }
}
