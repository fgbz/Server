package phalaenopsis.fgbz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import phalaenopsis.common.entity.Condition;
import phalaenopsis.common.entity.Page;
import phalaenopsis.common.entity.PagingEntity;
import phalaenopsis.fgbz.entity.Lawstandard;
import phalaenopsis.fgbz.entity.Technical;
import phalaenopsis.fgbz.entity.TechnicalType;
import phalaenopsis.fgbz.service.TechnicalService;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

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

}
