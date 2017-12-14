package phalaenopsis.fgbz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import phalaenopsis.common.entity.Page;
import phalaenopsis.common.entity.PagingEntity;
import phalaenopsis.fgbz.entity.Lawstandard;
import phalaenopsis.fgbz.entity.Technical;
import phalaenopsis.fgbz.entity.TechnicalType;
import phalaenopsis.fgbz.service.TechnicalService;

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
     * 获取法规标准列表
     * @param page
     * @return
     */
    @RequestMapping(value = "/getTechnicalList", method = RequestMethod.POST)
    @ResponseBody
    public PagingEntity<Technical> getTechnicalList(@RequestBody Page page){
        return  technicalService.getTechnicalList(page);
    }


    /**
     * 删除法规标准
     * @return
     */
    @RequestMapping(value = "/DeleteTechnicalById", method = RequestMethod.POST)
    @ResponseBody
    public int  DeleteTechnicalById(@RequestBody TechnicalType technicalType){
        return technicalService.DeleteTechnicalById(technicalType.getId());
    }
    /**
     * 新增和编辑法规标准
     * @return
     */
    @RequestMapping(value = "/SaveOrUpdateTechnical", method = RequestMethod.POST)
    @ResponseBody
    public int SaveOrUpdateTechnical(@RequestBody TechnicalType technicalType){

        return technicalService.SaveOrUpdateTechnical(technicalType);
    }

    /**
     * 查看
     * @return
     */
    @RequestMapping(value = "/getLawstandardById", method = RequestMethod.POST)
    @ResponseBody
    public Technical getTechnicalById(@RequestBody Technical technicalType){

        return technicalService.getTechnicalById(technicalType.getId());
    }

}
