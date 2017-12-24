package phalaenopsis.fgbz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import phalaenopsis.common.entity.Condition;
import phalaenopsis.common.entity.OpResult;
import phalaenopsis.common.entity.Page;
import phalaenopsis.common.entity.PagingEntity;
import phalaenopsis.fgbz.common.ExcelHelper;
import phalaenopsis.fgbz.entity.*;
import phalaenopsis.fgbz.service.TechnicalService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.util.*;

import static phalaenopsis.common.method.Basis.getCurrentFGUser;

@Controller
@RequestMapping("/Technical")
public class TechnicalController {

    @Autowired
    private TechnicalService technicalService;
    /**
     * 查询所有的技术文件
     *
     * @return
     */
    @RequestMapping(value = "/SelectTechnicalType", method = RequestMethod.POST)
    @ResponseBody
    public List<TechnicalType> SelectTechnicalType() {
        return technicalService.SelectTechnicalType();
    }

    /**
     * 新增或修改技术文件
     *
     * @param technicalType
     * @return
     */
    @RequestMapping(value = "/AddOrUpdateTechnicalType", method = RequestMethod.POST)
    @ResponseBody
    public int AddOrUpdateTechnicalType(@RequestBody TechnicalType technicalType) {
        return technicalService.AddOrUpdateTechnicalType(technicalType);
    }

    /**
     * 删除技术文件类别
     * @param technicalType
     * @return
     */
    @RequestMapping(value = "/DeleteTechnicalType", method = RequestMethod.POST)
    @ResponseBody
    public int  DeleteTechnicalType(@RequestBody TechnicalType technicalType){
        return  technicalService.DeleteTechnicalType(technicalType);
    }

    /**
     * 获取技术文件列表
     * @param page
     * @return
     */
    @RequestMapping(value = "/getTechnicalList", method = RequestMethod.POST)
    @ResponseBody
    public PagingEntity<Technical> getTechnicalList(@RequestBody Page page){
        return  technicalService.getTechnicalList(page);
    }


    /**
     * 删除技术文件
     * @return
     */
    @RequestMapping(value = "/DeleteTechnicalById", method = RequestMethod.POST)
    @ResponseBody
    public int  DeleteTechnicalById(@RequestBody Technical technical){
        return technicalService.DeleteTechnicalById(technical.getId());
    }
    /**
     * 新增和编辑技术文件
     * @return
     */
    @RequestMapping(value = "/SaveOrUpdateTechnical", method = RequestMethod.POST)
    @ResponseBody
    public int SaveOrUpdateTechnical(@RequestBody Technical technical){

        return technicalService.SaveOrUpdateTechnical(technical);
    }

    /**
     * 查看
     * @return
     */
    @RequestMapping(value = "/getTechnicalById", method = RequestMethod.POST)
    @ResponseBody
    public Technical getTechnicalById(@RequestBody Technical technical){

        return technicalService.getTechnicalById(technical.getId());
    }

    /**
     * 导出
     *
     * @param response 返回导出的excel
     */
    @RequestMapping(value = "/ExportTec", method = RequestMethod.GET)
    public void export(
            @RequestParam(value = "Number", required = false) String Number,
            @RequestParam(value = "Title", required = false) String Title,
            @RequestParam(value = "FiledTimeStart", required = false) String FiledTimeStart,
            @RequestParam(value = "FiledTimeEnd", required = false) String FiledTimeEnd,
            @RequestParam(value = "KeyWordsSingle", required = false) String KeyWordsSingle,
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
        if (KeyWordsSingle != null&&!KeyWordsSingle.equals("null")) {
            list.add(new Condition("KeyWordsSingle", KeyWordsSingle));
        }
        if (TreeValue != null&&!TreeValue.equals("null")) {
            list.add(new Condition("TreeValue", TreeValue));
        }
        if (ApproveStatus != null&&!ApproveStatus.equals("null")) {
            list.add(new Condition("ApproveStatus", ApproveStatus));
        }


        technicalService.exportExcel(list, response);

    }

    /**
     * 批量导入
     * @param request
     * @return
     */
    @RequestMapping(value = "/ImportTec", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> importTechnical(HttpServletRequest request) {
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
                List<TechnicalExcel> list = ExcelHelper.convertToList(TechnicalExcel.class, fileName, inputStream, 2, 9);

                if(list.size()==0){
                    map.put("Result",OpResult.Failed);
                    map.put("Msg","文件内容为空");
                    return map;
                }
                FG_User user = getCurrentFGUser();
                //插入法规
                map= technicalService.importTechnical(list,user);
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
    @RequestMapping(value = "/getHomePageTecsType", method = RequestMethod.GET)
    @ResponseBody
    public List<TechnicalType> getHomePageTecsType(){


        return  technicalService.getHomePageTecsType();
    }
}
