package phalaenopsis.common.controller;

import java.util.List;
import java.util.Map;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import phalaenopsis.common.entity.*;
import phalaenopsis.common.service.DataDictionaryService;

@RestController
@RequestMapping("/Foundation/DataDictionary")
public class DataDictionaryController {

    @Autowired
    private DataDictionaryService service;

    /**
     * 获取所有数据字典
     *
     * @return
     */
    @RequestMapping(value = "/GetAllDictionaries", method = RequestMethod.GET)
    @ResponseBody
    public List<DataDictionaryItem> getAllDictionaries() {
        return service.getAllDictionaries();
    }

    /**
     * 保存或更新数据字典
     *
     * @param item
     * @return
     */
    @RequestMapping(value = "/saveDictionary", method = RequestMethod.POST)
    public boolean saveDictionary(@RequestBody final DataDictionaryItem item) {
        return service.saveDictionary(item);
    }

    /**
     * 删除数据字典
     *
     * @param ids 字典id集合
     * @return 返回删除结果
     */
    @RequestMapping(value = "/deleteDictionary", method = RequestMethod.DELETE)
    public boolean deleteDictionary(@RequestParam("ids") List<Long> ids) {
        return service.deleteDictionary(ids);
    }

    /**
     * 根据条件筛选字典表数据
     * @param page
     * @return
     */
    @RequestMapping(value = "/listDataDictionary", method = RequestMethod.POST)
    public PagingEntityForMobile<DataDictionaryItem> listDataDictionary(Page page) {
        return service.listDataDictionary(page);
    }


    /**
     * 获取所有数据字典信息 根据类型名分类
     *
     * @return
     */
    @RequestMapping(value = "/getAllDictionariesByType", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, List<DataDictionaryItem>> getAllDictionariesByType() {
        return service.getAllDictionariesByType();
    }

    /**
     * 获取当前用户有权限的区域
     *
     * @return
     */
    @RequestMapping(value = "/GetAuthorizeRegions", method = RequestMethod.GET)
    @ResponseBody
    public List<Region> getAuthorizeRegions() {
        return service.getAuthorizeRegions();
    }

    /**
     * 获取所有区域
     *
     * @return
     */
    @RequestMapping(value = "/GetAllRegions", method = RequestMethod.GET)
    @ResponseBody
    public List<Region> getAllRegions() {
        return service.getAllRegions();
    }

    /**
     * 获取所有市级区域
     *
     * @return
     */
    @RequestMapping(value = "/GetCityRegions", method = RequestMethod.GET)
    @ResponseBody
    public List<Region> getCityRegions() {
        return service.getCityRegions();
    }

    /**
     * 根据市编码获取县集合
     *
     * @param cityCode
     * @return
     */
    @RequestMapping(value = "/GetCountyRegionByCity", method = RequestMethod.GET)
    @ResponseBody
    public List<Region> getCountyRegionByCity(int cityCode) {
        return service.getCountyRegionsByCity(cityCode);
    }

    @RequestMapping(value = "/GetRegionsByIDs", method = RequestMethod.GET)
    @ResponseBody
    public List<Region> getRegionsByIDs(@RequestParam("ids[]") int[] ids) {
        return service.GetRegionsByIDs(ids);
    }

    @RequestMapping(value = "/GetRegionMap", method = RequestMethod.GET)
    @ResponseBody
    public GeoJsonMap getRegionMap(@PathParam("regiontype") String regiontype) {
        return service.getRegionMap(regiontype);
    }


}
