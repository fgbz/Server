package phalaenopsis.fgbz.service;

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
import phalaenopsis.fgbz.dao.LawstandardDao;
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

    private  List<LawstandardType> ids = new ArrayList<>();

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
    public int  AddOrUpdateLawstandardType(LawstandardType lawstandardType){
        //新增
        if(lawstandardType.getId().equals("")){
            UUID uuid=UUID.randomUUID();
            String guid=uuid.toString();
            lawstandardType.setId(guid);
        }
        int num = lawstandardDao.AddOrUpdateLawstandardType(lawstandardType);

        return  num;
    }

    /**
     * 删除法规标准类别
     * @param lawstandardType
     * @return
     */
    public int  DeleteLawstandardType(LawstandardType lawstandardType){
        return lawstandardDao.DeleteLawstandardType(lawstandardType);
    }

    /**
     * 获取子节点
     * @param id
     * @return
     */
    public List<LawstandardType> getChildNode(String id){
        return lawstandardDao.getChildNode(id);
    }

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
     * 获取法规标准列表
     * @param page
     * @return
     */
    public PagingEntity<Lawstandard> getLawstandardList(@RequestBody Page page,String type){

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
                }else if(condition.getKey().equals("EnglishTitle")){
                    conditions.put("EnglishTitle", condition.getValue());
                }else if(condition.getKey().equals("KeyWordsSingle")){
                    conditions.put("KeyWordsSingle", condition.getValue());
                }else if(condition.getKey().equals("Summaryinfo")){
                    conditions.put("Summaryinfo", condition.getValue());
                }else if(condition.getKey().equals("Solr")){
                    String[] solrList  = condition.getValue().split(" ");
                    conditions.put("Solr", solrList);
                } else if(condition.getKey().equals("ReplaceOrRefenceid")){
                    conditions.put("ReplaceOrRefenceid", condition.getValue());
                }else if(condition.getKey().equals("IsBatch")){
                    conditions.put("IsBatch", condition.getValue());
                }else if(condition.getKey().equals("Userid")){
                    conditions.put("Userid", condition.getValue());
                }else if(condition.getKey().equals("LawInputuserid")){
                    conditions.put("LawInputuserid", condition.getValue());
                }

            }
        }


        // 1,根据条件一共查询到的数据条数
        int count = lawstandardDao.getLawstandardListCount(conditions);

        if(type!=null&&type.equals("uptodate")){
            conditions.put("startRow", 0 );
            conditions.put("endRow", 10);
        }else{
            if(page.getPageNo()==1){
                conditions.put("startRow", 0 );
            }else{
                conditions.put("startRow", page.getPageSize() * (page.getPageNo() - 1) );
            }
            conditions.put("endRow", page.getPageSize());
        }


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

    /**
     * 导出法规列表
     */
    public void exportExcel( List<Condition> list,HttpServletResponse response){
        Page page = new Page();
        page.setConditions(list);
        page.setPageNo(1);
        page.setPageSize(Integer.MAX_VALUE);

        List<Lawstandard>  listLaws = getLawstandardList(page,null).getCurrentList();

        ExportExcel exportExcel = new ExportExcel();

        String[] fields = {

                "chinesename",
                "englishname",
                "keywords",
                "code",
                "releasedate",
                "impdate",
                "summaryinfo",
                "memo"

        };
        exportExcel.exportExcel(fields,new Lawstandard(),listLaws,"法规标准",response);

    }

    /**
     * 批量导入
     * @return
     */
    @Transactional
    public int importLawstandard( List<LawstandardExcel> list,FG_User user) throws ParseException {

        List<Lawstandard> listImport = new ArrayList<>();

        for (LawstandardExcel excel:list ) {
            Lawstandard lawstandard = new Lawstandard();
            //中文标题不能为空
            if(StrUtil.isNullOrEmpty(excel.getChinesename())){
                int isChinesenameEmpty =1;
                OpResult opResult = new OpResult(isChinesenameEmpty);
                return opResult.Code;
            }else {
                lawstandard.setChinesename(excel.getChinesename());
            }
            //验证编号不能为空
            if(StrUtil.isNullOrEmpty(excel.getCode())){
                int isodeEmpty =2;
                OpResult opResult = new OpResult(isodeEmpty);
                return opResult.Code;
            }else{
                lawstandard.setCode(excel.getCode());
            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd ");
            lawstandard.setEnglishname(excel.getEnglishname());
            lawstandard.setKeywords(excel.getKeywords());
            lawstandard.setReleasedate( sdf.parse(excel.getReleasedate()));
            lawstandard.setImpdate( sdf.parse(excel.getImpdate()));
            lawstandard.setSummaryinfo(excel.getSummaryinfo());
            lawstandard.setMemo(excel.getMemo());
            lawstandard.setId(UUID.randomUUID().toString());
            lawstandard.setApprovestatus(1);
            lawstandard.setIsbatch(1);
            lawstandard.setInputuserid(user.getId());
            listImport.add(lawstandard);
        }
        for(Lawstandard law:listImport){
            lawstandardDao.SaveOrUpdateLawstandard(law);

        }
        return OpResult.Success;
    }
    /**
     * 删除法规标准
     * @return
     */
    @Transactional
    public int  DeleteLawstandardById(String id){

        lawstandardDao.deleteLawstandardById(id);
        lawstandardDao.deleteRefenceAll(id);
        lawstandardDao.deleteReplaceAll(id);
        lawstandardDao.DeleteLawAndType(id);


        return OpResult.Success;
    }

    /**
     * 批量删除法规
     * @return
     */
    @Transactional
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
    public int SaveOrUpdateLawstandard(Lawstandard lawstandard){

        if(lawstandard.getId()==null||lawstandard.getId().equals("")){
            UUID uuid=UUID.randomUUID();
            String guid=uuid.toString();
            lawstandard.setId(guid);
        }
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
                lawstandardDao.updateRleplaceStaus(item.getId());
            }

        }

        return OpResult.Success;
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
            for (LawstandardType lawstandardType:result
                 ) {
                lawstandardType.setChildLists(getChildNode(lawstandardType.getId()));
            }
        }
        return  result;
    }
}
