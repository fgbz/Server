package phalaenopsis.fgbz.common;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.web.context.support.WebApplicationContextUtils;
import phalaenopsis.common.method.Basis;
import phalaenopsis.fgbz.entity.ChartInfo;
import phalaenopsis.fgbz.entity.Fg_Log;
import phalaenopsis.fgbz.service.SystemServie;

import javax.servlet.ServletContextEvent;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimerTask;

/**
 * Created by 13260 on 2018/4/14.
 */
public class NFDFlightDataTimerTask extends TimerTask {

    private static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    private static SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private  ServletContextEvent sce;

    private SystemServie  configService;

    public NFDFlightDataTimerTask(ServletContextEvent sce1){
        sce = sce1;
        configService = WebApplicationContextUtils.getWebApplicationContext(sce.getServletContext()).getBean(SystemServie.class);
    }

    @Override
    public void run() {

        Basis basis = new Basis();

        try {
            //在这里写你要执行的内容
//            String title = formatter.format(Calendar.getInstance().getTime());

            Calendar theCa = Calendar.getInstance();
            theCa.setTime(new Date());
            theCa.add(theCa.DATE, -30);//最后一个数字30可改，30天的意思
            Date start = theCa.getTime();
            String title = formatter.format(start);//三十天之前日期

            //获取一个月之前的日志
            List<Fg_Log> list = configService.getLastMonthLog();

            HSSFWorkbook wb = new HSSFWorkbook();
            HSSFSheet sheet = wb.createSheet(title+"前的日志");
            HSSFCellStyle style = HssfHelper.getHssfCellStyle(wb, 3);

            String[] CloumnName = new String[]{"用户名称", "组织名称","操作名称","操作时间"};
            HSSFRow rowTitle = sheet.createRow(0);
            HSSFCellStyle headstyle = HssfHelper.getHssfCellStyle(wb, 1);
            HSSFCell cellTitle = rowTitle.createCell(1);
            cellTitle.setCellValue(title+"前日志");
            cellTitle.setCellStyle(headstyle);
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 1, 4));
            HSSFRow row = sheet.createRow(1);

            int nI;
            for (nI = 0; nI < CloumnName.length; nI++) {
                HSSFCell cell = row.createCell(nI + 1);
                cell.setCellValue(CloumnName[nI]);
                cell.setCellStyle(style);
            }

            for (nI = 0; nI < list.size(); nI++) {
                row = sheet.createRow(nI + 2);
                Fg_Log item = list.get(nI);
                row.createCell(1).setCellValue(item.getUsername());
                row.createCell(2).setCellValue(item.getOrganizationname());
                row.createCell(3).setCellValue(item.getOperationname());
                row.createCell(4).setCellValue(formatter1.format(item.getOperationtime()));
                HssfHelper.setRowStyle(row, 1, 4, style);
            }

            sheet.setColumnWidth(1, 9000);
            sheet.setColumnWidth(2, 9000);
            sheet.setColumnWidth(3, 9000);
            sheet.setColumnWidth(4, 9000);

            String tomcatPath = basis.getServerPath();

            File fileMk = new File(tomcatPath+"/HistoryLog");
            if (!fileMk.exists())
                fileMk.mkdir();


            File file = new File(tomcatPath+"/HistoryLog/"+title+"前.xls");

            if(!file.exists()){
                file.createNewFile();
            }
            OutputStream out =  new FileOutputStream(file);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            wb.write(baos);
            byte[] xlsBytes = baos.toByteArray();
            out.write(xlsBytes);
            out.close();

            //删除数据库记录
            configService.deleteLastMonthLog();

        } catch (Exception e) {
            System.out.println("-------------解析信息发生异常--------------");
        }
    }
}
