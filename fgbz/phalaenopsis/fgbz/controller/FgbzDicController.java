package phalaenopsis.fgbz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import phalaenopsis.common.entity.Condition;
import phalaenopsis.common.entity.OpResult;
import phalaenopsis.common.entity.Page;
import phalaenopsis.common.entity.PagingEntity;
import phalaenopsis.fgbz.entity.LawstandardStatus;
import phalaenopsis.fgbz.entity.Publishdep;
import phalaenopsis.fgbz.service.FgbzDicService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by 13260 on 2017/12/13.
 */
@Controller
@RequestMapping("/FgbzDic")
public class FgbzDicController {

    @Autowired
    private FgbzDicService fgbzDicService;

    /**
     * 获取所有法规字典
     * @return
     */
    @RequestMapping(value = "/getAllFgbzDictory", method = RequestMethod.POST)
    @ResponseBody
    public Map<Object,Object> getAllFgbzDictory() {

        return fgbzDicService.getAllFgbzDictory();
    }

    /**
     * 获取发布部门列表
     * @param page
     * @return
     */
    @RequestMapping(value = "/getPublishdepList", method = RequestMethod.POST)
    @ResponseBody
    public PagingEntity<Publishdep> getPublishdepList(@RequestBody Page page){

        return fgbzDicService.getPublishdepList(page);
    }

    @RequestMapping(value = "/HandlePublish", method = RequestMethod.POST)
    @ResponseBody
    public Publishdep HandlePublish(@RequestBody Publishdep publishdep){
        return fgbzDicService.HandlePublish(publishdep);
    }

    /**
     * 删除发布部门
     * @param id
     * @return
     */
    @RequestMapping(value = "/DeletePublishdepByID", method = RequestMethod.GET)
    @ResponseBody
    public int DeletePublishdepByID(String id){
        return fgbzDicService.DeletePublishdepByID(id);
    }

    /**
     * 新增或修改发布部门
     * @param publishdep
     * @return
     */
    @RequestMapping(value = "/SaveOrUpdatePublishdep", method = RequestMethod.POST)
    @ResponseBody
    public int SaveOrUpdatePublishdep(@RequestBody Publishdep publishdep){

        return fgbzDicService.SaveOrUpdatePublishdep(publishdep);
    }


    /**
     * 获取状态列表
     * @param page
     * @return
     */
    @RequestMapping(value = "/getLawstandardStatusList", method = RequestMethod.POST)
    @ResponseBody
    public PagingEntity<LawstandardStatus> getLawstandardStatusList(@RequestBody Page page){

        return fgbzDicService.getLawstandardStatusList(page);
    }

    /**
     * 删除状态
     * @param id
     * @return
     */
    @RequestMapping(value = "/DeleteLawstandardStatusByID", method = RequestMethod.GET)
    @ResponseBody
    public int DeleteLawstandardStatusByID(String id){

        return  fgbzDicService.DeleteLawstandardStatusByID(id);
    }

    /**
     * 新增或修改状态
     * @param lawstandardStatus
     * @return
     */
    @RequestMapping(value = "/SaveOrUpdateLawstandardStatus", method = RequestMethod.POST)
    @ResponseBody
    public int SaveOrUpdateLawstandardStatus(@RequestBody LawstandardStatus lawstandardStatus){

        return  fgbzDicService.SaveOrUpdateLawstandardStatus(lawstandardStatus);
    }
}
