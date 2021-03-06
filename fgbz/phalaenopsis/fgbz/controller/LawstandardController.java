package phalaenopsis.fgbz.controller;

import com.alibaba.fastjson.JSON;
import org.apache.lucene.queryparser.classic.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import phalaenopsis.common.entity.*;
import phalaenopsis.fgbz.common.ExcelHelper;
import phalaenopsis.fgbz.entity.*;
import phalaenopsis.fgbz.service.LawstandardService;
import phalaenopsis.fgbz.service.SystemServie;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

import static phalaenopsis.common.method.Basis.getCurrentFGUser;


@Controller
@RequestMapping("/Lawstandard")
public class LawstandardController {

    @Autowired
    private LawstandardService lawstandardService;

    @Autowired
    private SystemServie systemServie;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ResponseBody
    public Map<String,String> saveAduit(){
        int j = lawstandardService.testCount();
        Map<String,String> map =new HashMap<>();
        map.put("count",Integer.toString(j));
        return map;
    }


    /**
     * 查询法规标准类别目录
     * @return
     */
    @RequestMapping(value = "/SelectLawstandardType", method = RequestMethod.POST)
    @ResponseBody
    public List<LawstandardType> SelectLawstandardType() {
        return lawstandardService.SelectLawstandardType();
    }

    /**
     * 新增或修改法规标准类别
     * @param lawstandardType
     * @return
     */
    @RequestMapping(value = "/AddOrUpdateLawstandardType", method = RequestMethod.POST)
    @ResponseBody
    public int  AddOrUpdateLawstandardType(@RequestBody LawstandardType lawstandardType){
        return lawstandardService.AddOrUpdateLawstandardType(lawstandardType);
    }

    /**
     * 删除法规标准类别
     * @param lawstandardType
     * @return
     */
    @RequestMapping(value = "/DeleteLawstandardType", method = RequestMethod.POST)
    @ResponseBody
    public int  DeleteLawstandardType(@RequestBody LawstandardType lawstandardType){
        return lawstandardService.DeleteLawstandardType(lawstandardType);
    }

    /**
     * 获取法规标准列表
     * @param page
     * @return
     */
    @RequestMapping(value = "/getLawstandardList", method = RequestMethod.POST)
    @ResponseBody
    public PagingEntity<Lawstandard> getLawstandardList(@RequestBody Page page){
        return  lawstandardService.getLawstandardList(page);
    }

    /**
     * 获取法规标准列表
     * @param page
     * @return
     */
    @RequestMapping(value = "/getSolrList", method = RequestMethod.POST)
    @ResponseBody
    public PagingEntity<Lawstandard> getSolrList(@RequestBody Page page) throws IOException, ParseException {
        return  lawstandardService.getSolrList(page);
    }


    /**
     * 导出
     *
     * @param response 返回导出的excel
     */
    @RequestMapping(value = "/ExportLaw", method = RequestMethod.GET)
    public void export(
            @RequestParam(value = "Number", required = false) String Number,
            @RequestParam(value = "Title", required = false) String Title,
            @RequestParam(value = "FiledTimeStart", required = false) String FiledTimeStart,
            @RequestParam(value = "FiledTimeEnd", required = false) String FiledTimeEnd,
            @RequestParam(value = "State", required = false) String State,
            @RequestParam(value = "organization", required = false) String organization,
            @RequestParam(value = "MaterialTmeStart", required = false) String MaterialTmeStart,
            @RequestParam(value = "MaterialTmeEnd", required = false) String MaterialTmeEnd,
            @RequestParam(value = "TreeValue", required = false) String TreeValue,
            @RequestParam(value = "ApproveStatus", required = false) String ApproveStatus,
            @RequestParam(value = "SearchOrdertype", required = false) String SearchOrdertype,
            HttpServletResponse response) {
        List<Condition> list = new ArrayList<>();
        if (Number != null&&!Number.equals("null")) {
            list.add(new Condition("Number", Number));
        }
        if (Title != null&&!Title.equals("null")) {
            list.add(new Condition("Title", Title));
        }
        if (FiledTimeStart != null&&!FiledTimeStart.equals("null")) {
            list.add(new Condition("FiledTimeStart", FiledTimeStart));
        }
        if (FiledTimeEnd != null&&!FiledTimeEnd.equals("null")) {
            list.add(new Condition("FiledTimeEnd", FiledTimeEnd));
        }
        if (State != null&&!State.equals("null")) {
            list.add(new Condition("State", State));
        }
        if (organization != null&&!organization.equals("null")) {
            list.add(new Condition("organization", organization));
        }
        if (MaterialTmeStart != null&&!MaterialTmeStart.equals("null")) {
            list.add(new Condition("MaterialTmeStart", MaterialTmeStart));
        }
        if (MaterialTmeEnd != null&&!MaterialTmeEnd.equals("null")) {
            list.add(new Condition("MaterialTmeEnd", MaterialTmeEnd));
        }
        if (TreeValue != null&&!TreeValue.equals("null")) {
            list.add(new Condition("TreeValue", TreeValue));
        }
        if (ApproveStatus != null&&!ApproveStatus.equals("null")) {
            list.add(new Condition("ApproveStatus", ApproveStatus));
        }
        if (SearchOrdertype != null&&!SearchOrdertype.equals("null")) {
            list.add(new Condition("SearchOrdertype", SearchOrdertype));
        }


        lawstandardService.exportExcel(list, response);

    }

    @RequestMapping(value = "/ExportManager", method = RequestMethod.GET)
    public void ExportManager(  @RequestParam(value = "KeyWords", required = false) String KeyWords,
                                @RequestParam(value = "ApproveStatus", required = false) String ApproveStatus,
                                @RequestParam(value = "IsBatch", required = false) String IsBatch,
                                @RequestParam(value = "TreeValue", required = false) String TreeValue,
                                @RequestParam(value = "Ordertype", required = false) String Ordertype,
                                @RequestParam(value = "Duty", required = false) String Duty,
                                @RequestParam(value = "LawInputuserid", required = false) String LawInputuserid,
                                @RequestParam(value = "OrgList", required = false) String OrgList,
                                @RequestParam(value = "InputUserMySelf", required = false) String InputUserMySelf,
                                @RequestParam(value = "selectInputUser", required = false) String selectInputUser,
                                HttpServletResponse response){

        List<Condition> list = new ArrayList<>();
        if (KeyWords != null&&!KeyWords.equals("null")) {
            list.add(new Condition("KeyWords", KeyWords));
        }
        if (ApproveStatus != null&&!ApproveStatus.equals("null")) {
            list.add(new Condition("ApproveStatus", ApproveStatus));
        }
        if (IsBatch != null&&!IsBatch.equals("null")) {
            list.add(new Condition("IsBatch", IsBatch));
        }
        if (TreeValue != null&&!TreeValue.equals("null")) {
            list.add(new Condition("TreeValue", TreeValue));
        }
        if (Ordertype != null&&!Ordertype.equals("null")) {
            list.add(new Condition("Ordertype", Ordertype));
        }
        if (Duty != null&&!Duty.equals("null")) {
            list.add(new Condition("Duty", Duty));
        }
        if (LawInputuserid != null&&!LawInputuserid.equals("null")) {
            list.add(new Condition("LawInputuserid", LawInputuserid));
        }
        if (OrgList != null&&!OrgList.equals("null")) {
            Map<String, Object> mapList  = systemServie.grtUserListByOrgId(OrgList);

            FG_Organization org = new FG_Organization();
            org.setChildsorg( (List<FG_Organization>) mapList.get("OrgList"));

            list.add(new Condition("OrgList", JSON.toJSONString(org)));
        }
        if (InputUserMySelf != null&&!InputUserMySelf.equals("null")) {
            list.add(new Condition("InputUserMySelf", InputUserMySelf));
        }
        if (selectInputUser != null&&!selectInputUser.equals("null")) {
            list.add(new Condition("selectInputUser", selectInputUser));
        }

        lawstandardService.exportExcel(list, response);
    }

    /**
     * 获取法规标准列表前十
     * @param page
     * @return
     */
    @RequestMapping(value = "/getUptodateLawstandardList", method = RequestMethod.POST)
    @ResponseBody
    public List<Lawstandard> getUptodateLawstandardList(@RequestBody Page page){
        return  lawstandardService.getUptodateLawstandardList(page);
    }

    /**
     * 删除法规标准
     * @return
     */
    @RequestMapping(value = "/DeleteLawstandardById", method = RequestMethod.POST)
    @ResponseBody
    public int  DeleteLawstandardById(@RequestBody Lawstandard lawstandard) throws IOException {
        return lawstandardService.DeleteLawstandardById(lawstandard.getId());
    }

    /**
     * 批量删除
     * @return
     */
    @RequestMapping(value = "/DeleteAllSelectLawstandard", method = RequestMethod.POST)
    @ResponseBody
    public int DeleteAllSelectLawstandard(@RequestBody List<String> list) throws IOException {
        return lawstandardService.DeleteAllSelectLawstandard(list);
    }
    /**
     * 新增和编辑法规标准
     * @param lawstandard
     * @return
     */
    @RequestMapping(value = "/SaveOrUpdateLawstandard", method = RequestMethod.POST)
    @ResponseBody
    public int SaveOrUpdateLawstandard(@RequestBody Lawstandard lawstandard){

        return lawstandardService.SaveOrUpdateLawstandard(lawstandard);
    }

    /**
     * 查看法规标准
     * @return
     */
    @RequestMapping(value = "/getLawstandardById", method = RequestMethod.GET)
    @ResponseBody
    public Lawstandard getLawstandardById(String id){

        return lawstandardService.getLawstandardById(id);
    }

    @RequestMapping(value = "/getDetailLawstandardById", method = RequestMethod.GET)
    @ResponseBody
    public Lawstandard getDetailLawstandardById(String id){
        return lawstandardService.getDetailLawstandardById(id);
    }

    /**
     * 点击次数+1
     * @return
     */
    @RequestMapping(value = "/AddLawstandardCount", method = RequestMethod.POST)
    @ResponseBody
    public  void AddLawstandardCount(@RequestBody Lawstandard lawstandard){
        lawstandardService.AddLawstandardCount(lawstandard);
    }

    /**
     * 置顶
     * @return
     */
    @RequestMapping(value = "/LawstandardIsTop", method = RequestMethod.POST)
    @ResponseBody
    public  void LawstandardIsTop(@RequestBody Lawstandard lawstandard){
        lawstandardService.LawstandardIsTop(lawstandard);
    }


    /**
     * 首页统计
     * @return
     */
    @RequestMapping(value = "/getHomeChart", method = RequestMethod.GET)
    @ResponseBody
    public Map<Object,Object> getHomeChart() {

        return lawstandardService.getHomeChart();
    }

    /**
     * 首页统计导出
     */
    @RequestMapping(value = "/downHomeChart", method = RequestMethod.GET)
    public void downHomeChart(HttpServletResponse response) throws IOException {
         lawstandardService.downHomeChart(response);
    }

    /**
     * 批量导入
     * @param request
     * @return
     */
    @RequestMapping(value = "/ImportLaw", method = RequestMethod.POST)
    @ResponseBody
    public  Map<String,Object> importLawcaseAccount(HttpServletRequest request) {
        //获取前端传过来的file
        MultipartFile file = getFile(request);
        FileInputStream inputStream = null;

        Map<String,Object> map = new HashMap<>();
        try {
            if (file != null) {
                //转化文件名，避免乱码
                String fileName = new String(file.getOriginalFilename().getBytes("ISO-8859-1"), "UTF-8");
                inputStream = (FileInputStream) file.getInputStream();
                //将导入的excel转化为实体
                List<LawstandardExcel> list = ExcelHelper.convertToList(LawstandardExcel.class, fileName, inputStream, 3, 11);

                if(list.size()==0){
                    map.put("Result",OpResult.Failed);
                    map.put("Msg","文件内容为空");
                    return map;
                }
                FG_User user = getCurrentFGUser();
                //插入法规
                map= lawstandardService.importLawstandard(list,user);
                inputStream.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return map;
    }

    /**
     * 获取客户端上传的文件
     *
     * @param request
     * @return
     */
    public MultipartFile getFile(HttpServletRequest request) {

        MultipartFile file = null;

        // 解析器解析request的上下文
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
                request.getSession().getServletContext());

        // 先判断request中是否包涵multipart类型的数据
        if (multipartResolver.isMultipart(request)) {
            // 再将request中的数据转化成multipart类型的数据
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;

            Iterator iter = multiRequest.getFileNames();
            while (iter.hasNext()) {
                String name = (String) iter.next();
                // 根据name值拿取文件
                file = multiRequest.getFile(name);
            }

        }
        return file;

    }
    /**************************首页类别导航**************************************/
    @RequestMapping(value = "/getHomePageLawsType", method = RequestMethod.GET)
    @ResponseBody
    public List<LawstandardType> getHomePageLawsType(){


        return  lawstandardService.getHomePageLawsType();
    }


    @RequestMapping(value = "/getHomeLawsCount", method = RequestMethod.GET)
    @ResponseBody
    public List<LawstandardType> getHomeLawsCount(){


        return  lawstandardService.getHomeLawsCount();
    }

    @RequestMapping(value = "/UpdateAllLawstandardCode", method = RequestMethod.GET)
    @ResponseBody
    public int UpdateAllLawstandardCode(){
        return lawstandardService.UpdateAllLawstandardCode();
    }


    /***************************引用与替代****************************************/
    @RequestMapping(value = "/SaveReplaceOrRefence", method = RequestMethod.POST)
    @ResponseBody
    public int SaveReplaceOrRefence(@RequestBody Lawstandard lawstandard){
        return lawstandardService.SaveReplaceOrRefence(lawstandard);
    }

    /**
     * 获取引用与替代列表
     * @return
     */
    @RequestMapping(value = "/getReplaceLawstandardList", method = RequestMethod.POST)
    @ResponseBody
    public PagingEntity<Lawstandard> getReplaceLawstandardList(@RequestBody Page page){
        return lawstandardService.getReplaceLawstandardList(page);
    }

    /**
     * 删除临时法规
     * @return
     */
    @RequestMapping(value = "/DeleteReplece", method = RequestMethod.GET)
    @ResponseBody
    public int DeleteReplece(String id){
        return lawstandardService.DeleteReplece(id);
    }

    /**
     * 处理历史数据类别
     * @return
     */
    @RequestMapping(value = "/hangldHistroyType", method = RequestMethod.GET)
    @ResponseBody
    public int hangldHistroyType(){
        return lawstandardService.hangldHistroyType();
    }

    @RequestMapping(value = "/initSolr", method = RequestMethod.GET)
    @ResponseBody
    public int initSolr() throws IOException, ParseException {
        return lawstandardService.initSolr();
    }

    @RequestMapping(value = "/getChartStatistic", method = RequestMethod.POST)
    @ResponseBody
    public List<ChartInfo> getChartStatistic(@RequestBody Page page,String type,String clickvalue){
        return lawstandardService.getChartStatistic(page,type,clickvalue);
    }

    @RequestMapping(value = "/downStatistic", method = RequestMethod.GET)
    public  void downStatistic(
            @RequestParam(value = "Organization", required = false) String Organization,
            @RequestParam(value = "FiledTimeStart", required = false) String FiledTimeStart,
            @RequestParam(value = "FiledTimeEnd", required = false) String FiledTimeEnd,
            @RequestParam(value = "TreeValue", required = false) String TreeValue,
            @RequestParam(value = "Userid", required = false) String Userid,
            @RequestParam(value = "type", required = false) String type,
            @RequestParam(value = "clickvalue", required = false) String clickvalue,
            HttpServletResponse response
    ) throws IOException {

        List<Condition> list = new ArrayList<>();
        if (Organization != null&&!Organization.equals("null")) {
            list.add(new Condition("Organization", Organization));
        }
        if (FiledTimeStart != null&&!FiledTimeStart.equals("null")) {
            list.add(new Condition("FiledTimeStart", FiledTimeStart));
        }
        if (FiledTimeEnd != null&&!FiledTimeEnd.equals("null")) {
            list.add(new Condition("FiledTimeEnd", FiledTimeEnd));
        }
        if (TreeValue != null&&!TreeValue.equals("null")) {
            list.add(new Condition("TreeValue", TreeValue));
        }
        if (Userid != null&&!Userid.equals("null")) {
            list.add(new Condition("Userid", Userid));
        }

        Page page = new Page();
        page.setConditions(list);

        lawstandardService.downStatistic(response,page,type,clickvalue);

    }
}
