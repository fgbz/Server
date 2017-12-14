package phalaenopsis.allWeather.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;

import phalaenopsis.allWeather.entity.SwMapspot;
import phalaenopsis.allWeather.entity.SwSde;
import phalaenopsis.allWeather.service.AllWeatherService;
import phalaenopsis.allWeather.service.AllWeatherServiceI;
import phalaenopsis.allWeather.service.PolygonRegionService;
import phalaenopsis.common.entity.DataDictionaryItem;
import phalaenopsis.common.entity.Page;
import phalaenopsis.common.entity.PagingEntityForMobile;

@RestController
@RequestMapping("/allweather/moblieservice")
public class AllWeatherMobileController {

    @Autowired
    private AllWeatherServiceI service;

    @Autowired
    private PolygonRegionService regionService;

    /**
     * 移动端查询列表
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public PagingEntityForMobile<SwMapspot> getMapspotList(@RequestBody final String param) {
        Page page = JSONObject.parseObject(param, Page.class);
        return service.getMapspotList(page, true);
    }

    /**
     * 移动端获取图斑详情
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public SwMapspot getMapspot(@RequestParam("id") final Long id) {
        return service.getSwMapspot(id);
    }

    /**
     * 判断图斑是否已举证
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/proofed", method = RequestMethod.PUT)
    public Boolean proofed(@RequestParam("id") Long id) {
        return service.getSpotNode(id);
    }

    /**
     * 图片上传完后，修改Node值
     *
     * @param id
     * @param node
     * @return
     */
    @RequestMapping(value = "/proof", method = RequestMethod.PUT)
    public Integer proof(@RequestParam("id") Long id, @RequestParam("node") final int node) {
        return service.changeMobileNode(id, node);
    }

    /**
     * 获取区县用户下的所有坐标
     *
     * @return
     */
    @RequestMapping(value = "/mobileSpotCoordinate", method = RequestMethod.GET)
    public List<SwSde> mobileSpotCoordinate(@RequestParam("spotnumber") final String spotNumber) {
        return service.mobileSpotCoordinate(spotNumber);
    }

    /**
     * 获取全天候标记类型字典
     * @return
     */
    @RequestMapping(value = "/getMapSpotMarkDic", method = RequestMethod.GET)
    public List<DataDictionaryItem> getMapSpotMarkDic(@RequestParam(value = "type", required = false) String type) {
        return service.getMapSpotMarkVisDic(type);
    }

    /**
     * 移动端定位
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/coordinate", method = RequestMethod.GET)
    public String getMapspotCoordinate(@PathParam("id") final Long id) {
        // 通过sg_sde查询坐标，还需要解密
        // 密钥是：PolygonKey = "Phalaenopsis$MapSpot$Polygon@17!";
        return service.getMapspotCoordinate(id);
    }

    /**
     * 获取移动端指定区域内的所有图标
     *
     * @param minx 最小的x
     * @param maxx 最大的x
     * @param miny 最小的y
     * @param maxy 最大的y
     * @return 返回指定区域内的图斑
     */
    public List<SwSde> query(final double minx, final double maxx, final double miny, final double maxy) {
        return regionService.query(minx, maxx, miny, maxy);
    }

}
