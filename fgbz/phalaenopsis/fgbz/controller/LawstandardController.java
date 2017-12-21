package phalaenopsis.fgbz.controller;

import org.apache.commons.collections.map.HashedMap;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.util.*;

import static phalaenopsis.common.method.Basis.getCurrentFGUser;
import static phalaenopsis.common.method.Basis.getCurrentUser;


@Controller
@RequestMapping("/Lawstandard")
public class LawstandardController {

    @Autowired
    private LawstandardService lawstandardService;

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
        return  lawstandardService.getLawstandardList(page,null);
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


        lawstandardService.exportExcel(list, response);

    }

    /**
     * 获取法规标准列表前十
     * @param page
     * @return
     */
    @RequestMapping(value = "/getUptodateLawstandardList", method = RequestMethod.POST)
    @ResponseBody
    public PagingEntity<Lawstandard> getUptodateLawstandardList(@RequestBody Page page){
        return  lawstandardService.getLawstandardList(page,"uptodate");
    }

    /**
     * 删除法规标准
     * @return
     */
    @RequestMapping(value = "/DeleteLawstandardById", method = RequestMethod.POST)
    @ResponseBody
    public int  DeleteLawstandardById(@RequestBody Lawstandard lawstandard){
        return lawstandardService.DeleteLawstandardById(lawstandard.getId());
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
     * 新增和编辑法规标准
     * @return
     */
    @RequestMapping(value = "/getLawstandardById", method = RequestMethod.POST)
    @ResponseBody
    public Lawstandard getLawstandardById(@RequestBody Lawstandard lawstandard){

        return lawstandardService.getLawstandardById(lawstandard.getId());
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
     * 批量导入
     * @param request
     * @return
     */
    @RequestMapping(value = "/ImportLaw", method = RequestMethod.POST)
    @ResponseBody
    public int importLawcaseAccount(HttpServletRequest request) {
        //获取前端传过来的file
        MultipartFile file = getFile(request);
        FileInputStream inputStream = null;
        int result = 0;
        try {
            if (file != null) {
                //转化文件名，避免乱码
                String fileName = new String(file.getOriginalFilename().getBytes("ISO-8859-1"), "UTF-8");
                inputStream = (FileInputStream) file.getInputStream();
                //将导入的excel转化为实体
                List<LawstandardExcel> list = ExcelHelper.convertToList(LawstandardExcel.class, fileName, inputStream, 2, 8);

                if(list.size()==0){
                    int isEmpty= 404;
                    OpResult opResult = new OpResult(isEmpty);
                    return opResult.Code;
                }
                FG_User user = getCurrentFGUser();
                //插入法规
                result= lawstandardService.importLawstandard(list,user);
                inputStream.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
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

}
