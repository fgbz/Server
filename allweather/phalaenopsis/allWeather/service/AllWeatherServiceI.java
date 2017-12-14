package phalaenopsis.allWeather.service;


import org.aspectj.apache.bcel.verifier.statics.LONG_Upper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import phalaenopsis.allWeather.dao.IallWeatherCitydao;
import phalaenopsis.allWeather.dao.IallWeatherdao;
import phalaenopsis.allWeather.entity.HandleProgress;
import phalaenopsis.allWeather.entity.SwLog;
import phalaenopsis.allWeather.entity.SwMapspot;
import phalaenopsis.allWeather.enums.ReportEnum;
import phalaenopsis.common.entity.*;
import phalaenopsis.common.method.ExportExcel;
import phalaenopsis.common.method.Tools.UUID64;
import phalaenopsis.satellitegraph.utils.ArcGISHelper.SpotArcGISHelper;


import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 项目名称：Phalaenopsis
 * 创建日期：2017/11/8
 * 修改历史：
 * 1. [2017/11/8]创建文件
 *
 * @author chunl
 */
@Service
public class AllWeatherServiceI extends AllWeatherService {

    @Autowired
    private IallWeatherdao dao;
    @Autowired
    private IallWeatherCitydao weatherCitydao;

    /**
     * 根据传的mark标记值，来获取对应标记下的处理进度，处理百分比
     *
     * @param mark 标记值
     * @return 返回对应的处理进度和上报情况
     */
    @Override
    public HandleProgress getHandleProgress(Integer mark) {
        return super.getHandleProgress(mark);
    }

    /**
     * 返回所有标记类型的处理进度，包括全部。其中全部index值为0
     *
     * @return 返回所有的处理进度
     */
    public List<HandleProgress> getHandleAllProgress() {
        List<HandleProgress> resultList = new ArrayList<HandleProgress>();
        List<DataDictionaryItem> list = getMapSpotMarkDic();
        //resultList.add(super.getHandleProgress(null));
        for (DataDictionaryItem item : list) {
            HandleProgress progress = super.getHandleProgress(Integer.valueOf(item.getValue()));
            progress.setMark(item.getValue());
            resultList.add(progress);
        }
        return resultList;
    }

    /**
     * 上报。根据标记类型上报，全部为上报所有标记类型的数据
     *
     * @param mark 标记类型
     * @return 返回上报结果
     */
    public synchronized Boolean report(String mark) {
        User currentUser = getCurrentUser();
        if (currentUser.getOrgGrade() == OrganizationGrade.County) {
            Map periodMap = getCurrentPeriod();

            SwLog swLog = new SwLog(
                    UUID64.newUUID64().getValue(),
                    (Integer) periodMap.get("year"),
                    (Integer) periodMap.get("period"),
                    currentUser.getRegions()[0].getRegionID(),
                    ReportEnum.Report.getIntValue(),
                    Calendar.getInstance().getTime(),
                    currentUser.getOrganizationID(),
                    String.valueOf(currentUser.getRegions()[0].getParentID()),
                    currentUser.getName(),
                    currentUser.getId());
            if (null == mark) {
                List<DataDictionaryItem> list = getMapSpotMarkDic();

                for (DataDictionaryItem item : list) {
                    swLog.setId(UUID64.newUUID64().getValue());
                    swLog.setMark(item.getValue());
                    dao.saveOrUpdateSwLog(swLog);
                }

                swLog.setId(UUID64.newUUID64().getValue());
                swLog.setMark(null);
                dao.saveOrUpdateSwLog(swLog);

            } else {
                swLog.setMark(mark);
                dao.saveOrUpdateSwLog(swLog);
            }

            Map<String, Object> map = new HashMap<>();
            map.put("year", (Integer) periodMap.get("year"));
            map.put("period", (Integer) periodMap.get("period"));
            map.put("regionId", currentUser.getRegions()[0].getRegionID());
            map.put("mark", mark);
            dao.updateNodeCityState(map);

            dao.updateMapSpotLevel(map);

            return true;
        }
        return null;
    }


    public PagingEntityForMobile<SwMapspot> listMapSpotMark(Page page) {
        Map<String, Object> map = page.getQueryCondition();
        PagingEntityForMobile<SwMapspot> result = new PagingEntityForMobile<SwMapspot>();
        result.CurrentList = markAllVisAlias(dao.listMapSpotMark(map));
        result.RecordCount = dao.countMapSpotMark(map);
        result.calculatePageCount(page.getPageSize());
        return result;

    }

    public void exportMarkExcel(Map<String, Object> map, HttpServletResponse response) {
        map.put("startNum", 1);
        map.put("endNum", Integer.MAX_VALUE);

        List<SwMapspot> list = markFullAlias(dao.listMapSpotMark(map));
        ExportExcel exportExcel = new ExportExcel();
        String[] fields = {
                "county",
                "countyname",
                "districtcounty",
                "districtcountyname",
                "spotnumber",
                "spottype",
                "spotarea",
                "arableAcreage",
                "adjustablearea",
                "tempRemark"
        };
        exportExcel.exportExcel(fields, new SwMapspot(), list, "标记图斑", response);

    }


    public boolean deleteMapSpot(String[] ids) {

        if( dao.deleteMapSpot(ids) > 0)
        {
            List<Long> list = new ArrayList<Long>();
            for(String id :ids){
                list.add(Long.valueOf(id));
            }

            SpotArcGISHelper.deleteFeatures(new AppSettings().getAllWeatherSpotLocationService(), list);
            return  true;
        }

        return false;
    }


    public boolean markMapSpotList(Page page, String type, String operate) {

        List<DataDictionaryItem> list = getMapSpotMarkReportDic(null);

        Map<String, Object> map = page.getQueryCondition();
        map.put("markOperate", operate);
        map.put("markType", type);

        DataDictionaryItem item = list.stream().filter(t -> type.equals(t.getValue())).findAny().orElse(null);
        if (null == item) {
            map.put("visible", 0);
        } else {
            map.put("visible", 1);
        }

        dao.markMapSpotList(map);
        return true;
    }
}
