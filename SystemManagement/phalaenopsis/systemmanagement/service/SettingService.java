package phalaenopsis.systemmanagement.service;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import phalaenopsis.common.entity.AppSettings;
import phalaenopsis.common.method.Basis;
import phalaenopsis.common.method.Tools.UUID64;
import phalaenopsis.common.util.BLHCoordinate;
import phalaenopsis.common.util.CoordinateHelper;
import phalaenopsis.common.util.Param7Config;
import phalaenopsis.common.util.XYZCoordinate;
import phalaenopsis.illegalclue.entity.ResultState;
import phalaenopsis.systemmanagement.dao.ISsConfigDao;
import phalaenopsis.systemmanagement.entity.SsConfig;


@Service("settingService")
public class SettingService extends Basis {

    @Autowired
    private ISsConfigDao dao;

    public Map<String, String> getConfigs() {
        Map<String, String> configs = new HashMap<String, String>();
        AppSettings settings = new AppSettings();
        configs.put("ReportYear", settings.getReportYear());
        configs.put("Ratio", settings.getRatio());
        return null;
    }

    public Boolean setRatio(double ratio) {
        return null;
    }

    public Boolean setReportYear(int year) {
        return null;
    }

    /**
     * 保存或修改配置项
     *
     * @return
     */
    public ResultState saveOrUpdate(SsConfig config) {
        if (config == null) {
            config = new SsConfig();
        }
        int t = 0;
        if (config.getId() == null) {//如果为null就是刚添加
            config.setId(UUID64.newUUID64().getValue());
            config.setCreator(getCurrentUser().getId());
            config.setCreatime(new Date());
            t = dao.saveOrUpdate(config);
        } else {//修改
            config.setModifier(getCurrentUser().getId());
            config.setModifitime(new Date());
            t = dao.saveOrUpdate(config);
        }
        return 1 == t ? ResultState.Success : ResultState.Failed;
    }

    /**
     * 获取所有配置项信息
     *
     * @return
     */
    public Map<String, SsConfig> getList() {
        List<SsConfig> configs = dao.getList();
        Map<String, SsConfig> map = new HashMap<>();
        for (SsConfig config : configs) {
            map.put(config.getType(), config);
        }
        return map;
    }

    public String caclShift(double jx, double jy, double px, double py) {
        //StringBuilder resultBuilder = new StringBuilder();

        Param7Config param7 = new Param7Config();
        param7 = param7.GetConfig();

        BLHCoordinate blh_84 = new BLHCoordinate(jx, jy, 0d);
        XYZCoordinate xyz_84 = CoordinateHelper.BLHtoXYZ(blh_84, CoordinateHelper.WGS84Datum);
        XYZCoordinate xyz_80 = CoordinateHelper.Param7(xyz_84, param7.DeltaX, param7.DeltaY, param7.DeltaZ, param7.Rx, param7.Ry, param7.Rz, param7.K);
        BLHCoordinate blh_80 = CoordinateHelper.XYZtoBLH(xyz_80, CoordinateHelper.Xian80Datum);

        DecimalFormat decimalFormat = new DecimalFormat("#,###############0.000000000000000");

        return "\"X偏差:" + decimalFormat.format(px - blh_80.getLongitude()) + ";Y偏差:" + decimalFormat.format(py - blh_80.getLatitude()) +"\"";
        //resultBuilder.append("x="+ blh_80.getLongitude()+";");
        //resultBuilder.append("y=" + blh_80.getLatitude());
    }
}
