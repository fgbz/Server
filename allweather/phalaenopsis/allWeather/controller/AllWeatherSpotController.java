package phalaenopsis.allWeather.controller;

import java.awt.PageAttributes.MediaType;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.print.attribute.standard.Media;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import phalaenopsis.allWeather.entity.ResultPolygon;
import phalaenopsis.allWeather.entity.SwMapspot;
import phalaenopsis.allWeather.service.AllWeatherSpotService;
import phalaenopsis.common.entity.AppSettings;
import phalaenopsis.common.entity.Condition;
import phalaenopsis.common.entity.Page;
import phalaenopsis.common.entity.Paging;
import phalaenopsis.common.method.Tools.UUID64;
import phalaenopsis.satellitegraph.entity.MapSpot;
import phalaenopsis.satellitegraph.entity.MapSpot2016;
import phalaenopsis.satellitegraph.entity.SatelliteListResult;
import phalaenopsis.satellitegraph.utils.ArcGISHelper.SpotArcGISHelper;

@RestController
@RequestMapping("/allweather/spotservice")
public class AllWeatherSpotController {

    @Autowired
    private AllWeatherSpotService service;

    @Autowired
    private HttpServletRequest request;

    /**
     * 图斑分宗 TODO
     *
     * @param source 原始图斑
     * @param spots  分宗后的图斑
     * @return
     */
    @RequestMapping(value = "/Segment", method = RequestMethod.PUT)
    public boolean segment(@RequestBody String json) {
        JSONObject jo = JSON.parseObject(json);
        String sourceStr = JSON.parseObject(jo.getString("source")).getString("Spot");
        SwMapspot source = JSON.parseObject(sourceStr, SwMapspot.class);

        JSONArray filearray = JSON.parseObject(jo.getString("source")).getJSONArray("fileServerPaths");
        if (filearray != null && filearray.size() > 0) {
            String[] fileServerPaths = new String[filearray.size()];
            for (int i = 0; i < filearray.size(); i++) {
                fileServerPaths[i] = filearray.getString(i);
            }
            source.setFileServerPaths(fileServerPaths);
        }

        JSONArray array = JSON.parseArray(jo.getString("spots"));
        List<SwMapspot> sourceSpots = new ArrayList<SwMapspot>();
        for (Object item : array) {
            JSONObject jItem = JSON.parseObject(item.toString());
            SwMapspot newSwMapspot = JSON.parseObject(jItem.getString("Spot"), SwMapspot.class);
            newSwMapspot.setID(UUID64.newUUID64().getValue());
            sourceSpots.add(newSwMapspot);
        }
        // List<SwMapspot> spots = JSON.parseArray(jo.getString("spots"),
        // SwMapspot.class);
        return service.segment(source, sourceSpots);
    }

    /**
     * 图斑并宗 TODO
     *
     * @param sourceSpots 要并宗的图斑ID列表
     * @param newSpot     并宗后的新图斑
     * @return
     */
    @RequestMapping(value = "/Merge", method = RequestMethod.PUT)
    public boolean merge(@RequestBody String json) {
        JSONObject jo = JSON.parseObject(json);
        SwMapspot newSpot = JSON.parseObject(JSON.parseObject(jo.getString("newSpot")).getString("Spot"),
                SwMapspot.class);
        newSpot.setID(UUID64.newUUID64().getValue());

        List<SwMapspot> list = new ArrayList<SwMapspot>();
        JSONArray jsonArray = JSON.parseArray(jo.getString("sourceSpots"));
        for (Object item : jsonArray) {
            SwMapspot oldMS = JSON.parseObject(JSON.parseObject(item.toString()).getString("Spot"), SwMapspot.class);
            if (null == oldMS) {
                oldMS = JSON.parseObject(item.toString(), SwMapspot.class);
            }
            list.add(oldMS);
        }

        return service.merge(list, newSpot);
    }

    /**
     * 图斑还原 TODO
     *
     * @param spot
     * @return
     */
    @RequestMapping(value = "/Restore", method = RequestMethod.PUT)
    public boolean restore(@RequestBody String spot) {
        JSONObject jo = JSON.parseObject(spot);
        jo = JSON.parseObject(jo.getString("spot"));
        SwMapspot mapSpot2016 = JSON.parseObject(jo.getString("Spot"), SwMapspot.class);
        return service.restore(mapSpot2016);
    }

    /**
     * 根据图斑号进行模糊查询 TODO
     *
     * @param year 年份
     * @param num  图斑号
     * @return
     */
    @RequestMapping(value = "/GetSpotsByNum", method = RequestMethod.GET)
    public List<SwMapspot> getSpotsByNum(@RequestParam(value = "num", required = true) String num,
                                         @RequestParam(value = "period", required = true) Integer period,
                                         @RequestParam(value = "mapratio", required = true) Integer mapratio) {
        return service.getSpotsByNum(num, period, mapratio);
    }

    /**
     * 判断图斑是否已进行过分宗或并宗
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/GetSpotStatus", method = RequestMethod.GET)
    public boolean getSpotStatus(@PathParam("id") long id) {
        return service.getSpotStatus(id);
    }

    /**
     * 合并图斑
     *
     * @param num
     * @return
     */
    @RequestMapping(value = "/GetMergeSpots", method = RequestMethod.GET)
    public List<SwMapspot> getMergeSpots(@RequestParam(value = "num", required = true) String num) {
        List<SwMapspot> result = service.getSpotsByNum(num, null, null);
        for (SwMapspot swMapspot : result) {
            AppSettings appSettings = new AppSettings();
            String serviceUrl = appSettings.getAllWeatherSpotLocationService();
            swMapspot.setShape(SpotArcGISHelper.getSpotShape(serviceUrl, String.valueOf(swMapspot.getID())));
        }
        return result;
    }

    /**
     * 读取Shape文件
     *
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/readShape", method = RequestMethod.POST)
    public ResultPolygon readShape(@RequestParam("files[]") CommonsMultipartFile[] file) throws IOException {
        return service.readShape(file);
    }

    /**
     * 读取Shape文件
     *
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/readTxt", method = RequestMethod.POST)
    public ResultPolygon readTxt(HttpServletRequest request) throws IOException {
        return service.readTxt(request);
    }
}
