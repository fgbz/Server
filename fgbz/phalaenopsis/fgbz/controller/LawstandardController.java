package phalaenopsis.fgbz.controller;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import phalaenopsis.common.entity.Page;
import phalaenopsis.common.entity.PagingEntity;
import phalaenopsis.fgbz.entity.ChartInfo;
import phalaenopsis.fgbz.entity.Lawstandard;
import phalaenopsis.fgbz.entity.LawstandardType;
import phalaenopsis.fgbz.service.LawstandardService;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @RequestMapping(value = "/export", method = RequestMethod.GET)
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
        Map<String, Object> map = new HashedMap();
        map.put("Number", Number);
        map.put("Title", Title);
        map.put("FiledTimeStart", FiledTimeStart);
        map.put("FiledTimeEnd", FiledTimeEnd);
        map.put("State", State);
        map.put("organization", organization);
        map.put("MaterialTmeStart", MaterialTmeStart);
        map.put("MaterialTmeEnd", MaterialTmeEnd);
        map.put("TreeValue", TreeValue);
        map.put("ApproveStatus", ApproveStatus);
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
     * 导出export
     */
    @RequestMapping(value = "/ExportLaw", method = RequestMethod.POST)
    @ResponseBody
    public void ExportLaw(@RequestBody Lawstandard lawstandard){

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

}
