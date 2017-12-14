package phalaenopsis.common.method;


import javax.servlet.http.HttpServletResponse;
import java.util.*;

public class ExportExcel {

    /**
     * 导出Excel
     *
     * @param fields    要导出的字段
     * @param t         实例化空的T对象
     * @param list      要导出数据的集合
     * @param excelName 导出excel名称
     * @param response  导出excel序列化到Response中
     * @param <T>       导出Excel的T对象
     */
    public <T> void exportExcel(String[] fields, T t, List<T> list, String excelName, HttpServletResponse response) {
        CreatExcel creatExcel = new CreatExcel().newInstance(t, list, fields, excelName);
        creatExcel.drawExcel();
        creatExcel.saveAsResponse(response);
    }


    /**
     * 导出excel包含单位
     *
     * @param fields    要导出的字段
     * @param t         实例化空的T对象
     * @param list      要导出数据的集合
     * @param excelName 导出excel名称
     * @param unit      单位名称
     * @param response  导出excel序列化到Response中
     * @param <T>       导出Excel的T对象
     */
    public <T> void exportExcelWithUnit(String[] fields, T t, List<T> list, String excelName, String unit, HttpServletResponse response) {
        CreatExcel creatExcel = new CreatExcel().newInstance(t, list, fields, excelName);
        creatExcel.setUnit("单位：亩");
        creatExcel.drawExcel();
        creatExcel.saveAsResponse(response);
    }

    /**
     * 导出excel包含字典
     *
     * @param fields    要导出的字段
     * @param t         实例化空的T对象
     * @param list      要导出数据的集合
     * @param excelName 导出excel名称
     * @param map       字典集合
     * @param response  导出excel序列化到Response中
     * @param <T>       导出Excel的T对象
     */
    public <T> void exportExcelWithDic(String[] fields, T t, List<T> list, String excelName, Map<String, Map<Object, Object>> map, HttpServletResponse response) {
        CreatExcel creatExcel = new CreatExcel().newInstance(t, list, fields, excelName);
        creatExcel.setDicMap(map);
        creatExcel.drawExcel();
        creatExcel.saveAsResponse(response);
    }


    /**
     * 导出excel包含单位和字典
     *
     * @param fields    要导出的字段
     * @param t         实例化空的T对象
     * @param list      要导出数据的集合
     * @param excelName 导出excel名称
     * @param unit      单位名称
     * @param map       字典集合
     * @param response  导出excel序列化到Response中
     * @param <T>       导出Excel的T对象
     */
    public <T> void exportExcelWithUnitAndDic(String[] fields, T t, List<T> list, String excelName, String unit, Map<String, Map<Object, Object>> map, HttpServletResponse response) {
        CreatExcel creatExcel = new CreatExcel().newInstance(t, list, fields, excelName);
        creatExcel.setUnit(unit);
        creatExcel.setDicMap(map);
        creatExcel.drawExcel();
        creatExcel.saveAsResponse(response);
    }


}