package phalaenopsis.common.method;

import org.apache.commons.collections.map.HashedMap;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFFont;
import phalaenopsis.common.annotation.ExcelTitle;
import phalaenopsis.common.method.Tools.StrUtil;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class CreatExcel<T> {

    /**
     * 创建一个excel
     */
    private HSSFWorkbook wb;

    /**
     * 创建单个sheet
     */
    private HSSFSheet sheet;

    private HSSFCellStyle normalStyle;
    private HSSFCellStyle doubleCellStyle;
    private HSSFCellStyle dateCellStyle;
    private HSSFCellStyle fontCellStyle;

    private boolean bTitleVis = true;

    /**
     * 绘制表头
     */
    private T t;

    /**
     * 填写表内容
     */
    private List<T> arrays;

    /**
     * excel的名称，用于画表头，sheet名称，输出文件名称
     */
    private String excelName;

    private String unit;

    private String[] fields;

    private int index = 0;

    private Map<String, Map<Object, Object>> dicMap = new HashedMap();


    public void setbTitleVis(boolean bTitleVis) {
        this.bTitleVis = bTitleVis;
    }

    public HSSFWorkbook getWb() {
        return wb;
    }

    public void setDicMap(Map<String, Map<Object, Object>> dicMap) {
        this.dicMap = dicMap;
    }

    /**
     * 实例化一个excel
     *
     * @return
     */
    public CreatExcel newInstance(T t, List<T> arrays, String[] fields, String excelName) {

        this.t = t;
        this.arrays = arrays;
        this.excelName = excelName;
        this.fields = fields;

        return this;
    }


    public void drawExcel() {
        createSheet(); //创建表格
        createTitle();  //绘制表格头部
        createUnit();   // 绘制单位
        createTableTitle();  // 绘制表格表头
        createTableContent();   //填充表格内容
    }


    /**
     * 将excel数据缓存到response中
     *
     * @param response
     */
    public void saveAsResponse(HttpServletResponse response) {
        try {
            response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(this.excelName, "utf-8") + ".xls");
            OutputStream out = response.getOutputStream();
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            wb.write(stream);
            byte[] bytes = stream.toByteArray();
            out.write(bytes);
            out.close();
        } catch (
                UnsupportedEncodingException e)

        {
            e.printStackTrace();
        } catch (
                IOException e)

        {
            e.printStackTrace();
        }
    }

    public void saveAsFile(String filePath) {
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                file.createNewFile();
            }
            //保存excel
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            wb.write(fileOutputStream);
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createSheet() {

        // 第一步，创建一个webbook，对应一个Excel文件
        this.wb = new HSSFWorkbook();
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        this.sheet = wb.createSheet(excelName);

        // 设置样式
        this.normalStyle = setNormalStyle(wb.createCellStyle());
        this.dateCellStyle = setDateStyle(wb.createCellStyle());
        this.doubleCellStyle = setDoubleStyle(wb.createCellStyle());
        this.fontCellStyle = setFontStyle(wb.createCellStyle());
    }


    /**
     * 绘制表的title
     */
    private void createTitle() {
        if (!StrUtil.isNullOrEmpty(this.excelName) && true == bTitleVis) {
            HSSFRow row = sheet.createRow(index++);
            row.setHeight((short) (30 * 20));
            //setCellBorder(row); //设置边框


            HSSFCell cell = row.createCell(0);
            cell.setCellValue(excelName);
            cell.setCellStyle(this.fontCellStyle);

            sheet.addMergedRegion(new CellRangeAddress(index - 1, index - 1, 0, fields.length - 1));
        }
    }

    /**
     * 添加单位
     */
    private void createUnit() {
        if (!StrUtil.isNullOrEmpty(unit)) {
            HSSFRow row = sheet.createRow(index++);

            //setCellBorder(row); //设置边框

            HSSFCell cell = row.createCell(0);
            cell.setCellValue(unit);
        }
    }


    /**
     * 绘制表头
     */
    private void createTableTitle() {
        HSSFRow row = sheet.createRow(index++);
        setCellBorder(row);

        for (int i = 0; i < fields.length; i++) {
            HSSFCell cell = row.getCell(i);

            try {
                ExcelTitle excelTitle = t.getClass().getDeclaredField(fields[i]).getAnnotation(ExcelTitle.class);
                if (null != excelTitle) {
                    cell.setCellValue(excelTitle.value());
                } else {
                    cell.setCellValue("");
                }
            } catch (NoSuchFieldException e) {
                //    e.printStackTrace();
                cell.setCellValue("");
            }

            this.sheet.setColumnWidth(i, 15 * 256);
        }
    }


    /**
     * 绘制表的内容
     */
    private void createTableContent() {
        if (0 != arrays.size()) {
            for (int i = 0; i < arrays.size(); i++) {
                HSSFRow row = sheet.createRow(index++);
                setCellBorder(row);
                for (int j = 0; j < fields.length; j++) {
                    Object value = null;
                    Field field = null;

                    try {
                        field = arrays.get(i).getClass().getDeclaredField(fields[j]);
                        field.setAccessible(true);
                        value = field.get(arrays.get(i));

                        if (null != value && null != dicMap && 0 != dicMap.size()){  //将字典转换为实际值
                            if (null != dicMap.get(fields[j])){
                                value = dicMap.get(fields[j]).get(value);
                            }
                        }



                    } catch (NoSuchFieldException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    setCellValue(row, j, value);
                }
            }
        }
    }


    private void setCellValue(HSSFRow row, int colIndex, Object value) {

        HSSFCell hssfCell = row.createCell(colIndex);
        hssfCell.setCellStyle(this.normalStyle);
        if (null == value) {
            hssfCell.setCellValue("");
        } else if (value instanceof Integer) {
            hssfCell.setCellValue((Integer) value);
        } else if (value instanceof Short) {
            hssfCell.setCellValue((Short) value);
        } else if (value instanceof Double) {
            hssfCell.setCellValue((Double) value);
            hssfCell.setCellStyle(this.doubleCellStyle);
        } else if (value instanceof Boolean) {
            hssfCell.setCellValue((Boolean) value);
        } else if (value instanceof Short) {
            hssfCell.setCellValue((Short) value);
        } else if (value instanceof Date) {
            hssfCell.setCellValue((Date) value);
            hssfCell.setCellStyle(this.dateCellStyle);
        } else if (value instanceof Long) {
            hssfCell.setCellValue((Long) value);
        } else if (value instanceof BigDecimal) {
            hssfCell.setCellValue(((BigDecimal) value).doubleValue());
            hssfCell.setCellStyle(this.doubleCellStyle);
        }

        else {
            hssfCell.setCellValue((String) value);
        }

    }


    /**
     * 公共样式
     *
     * @param style
     * @return
     */
    private HSSFCellStyle setNormalStyle(HSSFCellStyle style) {
        style.setAlignment(HSSFCellStyle.VERTICAL_CENTER);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);

        style.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框

        return style;
    }

    private HSSFCellStyle setFontStyle(HSSFCellStyle style) {
        //设置字体
        HSSFFont font = this.wb.createFont();
        font.setFontHeightInPoints((short) 18);//.setFontHeight();

        style.setFont(font);
        style.setAlignment(HSSFCellStyle.VERTICAL_CENTER);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        return style;
    }

    /**
     * 设置double类型格式数据
     *
     * @param style
     * @return
     */
    private HSSFCellStyle setDoubleStyle(HSSFCellStyle style) {
        setNormalStyle(style);
        style.setDataFormat(HSSFDataFormat.getBuiltinFormat("0.00"));
        return style;
    }

    /**
     * 设置日期类型格式数据
     *
     * @param style
     * @return
     */
    private HSSFCellStyle setDateStyle(HSSFCellStyle style) {
        setNormalStyle(style);
        HSSFDataFormat format = wb.createDataFormat();
        style.setDataFormat(format.getFormat("yyyy年m月d日"));
        return style;
    }

    private void setCellBorder(HSSFRow row) {
        for (int i = 0; i < fields.length; i++) {
            HSSFCell cell = row.createCell(i);
            cell.setCellStyle(normalStyle);
        }

    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
