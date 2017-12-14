package phalaenopsis.lawcaseevaluation.service;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import phalaenopsis.lawcaseevaluation.dao.EvaluationStatisticsDao;
import phalaenopsis.lawcaseevaluation.entity.EvaluationPerson;
import phalaenopsis.lawcaseevaluation.entity.ExportSchedule;
import phalaenopsis.lawcaseevaluation.entity.ExportScheduleTotal;
import phalaenopsis.lawcaseevaluation.entity.ReviewLawcase;
import phalaenopsis.lawcaseevaluation.entity.ReviewState;
import phalaenopsis.lawcaseevaluation.entity.caseDetail.LawCaseUserMap;

@Service("evaluationStatisticsService")
public class EvaluationStatisticsService {

	@Autowired
	private EvaluationStatisticsDao evaluationStatisticsDao;

	/**
	 * 获取各市各案卷得分一览表
	 * 
	 * @param code
	 *            执法单位
	 * @param year
	 *            年份
	 * @return
	 */
	public List<ReviewLawcase> getAllEvaluationResultAccount(long code, int year, long userid) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("Code", code);
		map.put("Year", year);
		map.put("Userid", userid);
		List<ReviewLawcase> list = evaluationStatisticsDao.getAllEvaluationResultAccount(map);
		// 设置不符合评查条件的项的内容
		for (int i = 0; i < list.size(); i++) {
			int firstnum = 0;
			if (list.get(i).getUserMaps() != null) {
				firstnum = list.get(i).getUserMaps().size();
			}

			// 不符合评查条件
			if (list.get(i).getReviewState() == ReviewState.unMatched) {
				LawCaseUserMap lawCaseUserMap = new LawCaseUserMap();
				lawCaseUserMap.setStatisticRemark("不符合评查条件");
				List<LawCaseUserMap> listUserMap = new ArrayList<LawCaseUserMap>();
				listUserMap.add(lawCaseUserMap);
				list.get(i).setUserMaps(listUserMap);
				list.get(i).setPcCount(firstnum);
			} else {
				// 评查人数
				int num = 0;
				for (int j = 0; j < list.get(i).getUserMaps().size(); j++) {
					LawCaseUserMap lawCaseUserMap = list.get(i).getUserMaps().get(j);
					if (lawCaseUserMap.getReviewUserName() != null && !lawCaseUserMap.getReviewUserName().equals("")) {
						num++;
					}
				}

				list.get(i).setPcCount(num + firstnum);
			}
		}
		return list;
	}

	/**
	 * 获取各市案卷一览表信息
	 * 
	 * @param year
	 *            年份
	 * @return
	 */
	public ExportScheduleTotal getStatisticSchedule(int year) {
		List<ExportSchedule> list = evaluationStatisticsDao.getStatisticSchedule(year);
		ExportScheduleTotal exportScheduleTotal = new ExportScheduleTotal();
		// 通过实体的set方法，统计总数
		exportScheduleTotal.setExportScheduleList(list);
		return exportScheduleTotal;
	}

	/**
	 * 导出各市各案卷一览表
	 * 
	 * @param year
	 * @param response
	 * @return
	 */
	public HttpServletResponse ExportSchedule(int year, HttpServletResponse response) {

		ExportScheduleTotal exportScheduleTotal = getStatisticSchedule(year);
		String[] CloumnName = { "序号", "市名", "各市参评案卷数", "90分以上卷宗", "89-70分卷宗", "70分以下卷宗", "备注" };
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("各市各案卷一览表");
		String title = "各市各案卷一览表";
		String unit = "单位: 个";
		// 创建导出样式
		HSSFCellStyle style = wb.createCellStyle();
		style = setCommonStyle(style);
		// 创建导出表头样式
		HSSFCellStyle styleHead = wb.createCellStyle();
		styleHead = setCommonStyle(styleHead);
		HSSFFont font = wb.createFont();
		font.setFontName("黑体");
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 粗体显示
		styleHead.setFont(font);
		// 标题样式
		HSSFCellStyle styleTitle = wb.createCellStyle();
		styleTitle.setAlignment(HSSFCellStyle.VERTICAL_CENTER);
		styleTitle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		HSSFFont fontTitle = wb.createFont();
		fontTitle.setFontName("黑体");
		fontTitle.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 粗体显示
		fontTitle.setFontHeightInPoints((short) (16));
		styleTitle.setFont(fontTitle);
		// 标题
		HSSFRow rowTitle = sheet.createRow(0);
		Cell cellTitle = rowTitle.createCell(0);
		cellTitle.setCellValue(title);
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, CloumnName.length - 1));
		rowTitle.getCell(0).setCellStyle(styleTitle);
		// 单位
		HSSFRow rowUnit = sheet.createRow(1);
		Cell cellUnit = rowUnit.createCell(0);
		cellUnit.setCellValue(unit);
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, CloumnName.length - 1));
		HSSFCellStyle styleUnit = wb.createCellStyle();
		// 水平向右
		styleUnit.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
		rowUnit.getCell(0).setCellStyle(styleUnit);
		// 创建第一行 列头
		HSSFRow row = sheet.createRow(2);
		for (int nK = 0; nK < CloumnName.length; nK++) {
			HSSFCell cell = row.createCell(nK);
			cell.setCellValue(CloumnName[nK]);
			cell.setCellStyle(styleHead);
		}
		// 填充值
		for (int nI = 0; nI < exportScheduleTotal.getExportScheduleList().size(); nI++) {
			row = sheet.createRow(nI + 3);
			ExportSchedule item = exportScheduleTotal.getExportScheduleList().get(nI);
			row.createCell(0).setCellValue(nI + 1);
			row.createCell(1).setCellValue(item.getRegionName());
			row.createCell(2).setCellValue(item.getCaseCount());
			row.createCell(3).setCellValue(item.getGoodCaseCount());
			row.createCell(4).setCellValue(item.getGradeCaseCount());
			row.createCell(5).setCellValue(item.getUnGradeCaseCount());
			if (item.getUnMatchedCaseCount() > 0) {
				row.createCell(6).setCellValue("不符合评查条件卷宗" + item.getUnMatchedCaseCount() + "个");
			} else {
				row.createCell(6).setCellValue("");
			}

			row = setCellStyle(row, style, CloumnName.length);
		}

		// 最后一行合计
		row = sheet.createRow(exportScheduleTotal.getExportScheduleList().size() + 3);
		row.createCell(0).setCellValue("合计");
		row.createCell(1).setCellValue("");
		row.createCell(2).setCellValue(exportScheduleTotal.getCaseCountTotal());
		row.createCell(3).setCellValue(exportScheduleTotal.getGoodCaseCountTotal());
		row.createCell(4).setCellValue(exportScheduleTotal.getGradeCaseCountTotal());
		row.createCell(5).setCellValue(exportScheduleTotal.getUnGradeCaseCountTotal());
		row.createCell(6).setCellValue("不符合评查条件卷宗" + exportScheduleTotal.getUnMatchedCaseCountTotal() + "个");
		row = setCellStyle(row, style, CloumnName.length);
		// 合并单元格
		sheet.addMergedRegion(new CellRangeAddress(exportScheduleTotal.getExportScheduleList().size() + 3,
				exportScheduleTotal.getExportScheduleList().size() + 3, 0, 1));
		// 每列宽度
		sheet.setColumnWidth(0, (short) 2000);
		sheet.setColumnWidth(1, (short) 3000);
		sheet.setColumnWidth(2, (short) 4000);
		sheet.setColumnWidth(3, (short) 4000);
		sheet.setColumnWidth(4, (short) 4000);
		sheet.setColumnWidth(5, (short) 4000);
		sheet.setColumnWidth(6, (short) 6000);
		try {
			response.setHeader("content-disposition",
					"attachment;filename=" + URLEncoder.encode("各市各案卷一览表", "utf-8") + ".xls");
			OutputStream out = response.getOutputStream();
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			wb.write(baos);
			byte[] xlsBytes = baos.toByteArray();
			out.write(xlsBytes);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 把对应的action的返回设置为空，避免java.lang.IllegalStateException:
		// getOutputStream() has already been called for this respons
		return null;
	}

	/**
	 * 导出案卷明细表
	 * 
	 * @param year
	 * @param regionCode
	 * @param response
	 * @return
	 */
	public HttpServletResponse ExportDetail(int year, long regionCode, long userid,
			@Context HttpServletResponse response) {

		List<ReviewLawcase> list = getAllEvaluationResultAccount(regionCode, year, userid);
		String[] CloumnName = { "序号", "执法单位市本级/县（市、区)", "案卷名称", "评查人数", "最后得分", "评卷人员", "评查得分", "复核人员", "复核得分",
				"评查人员提出的问题", "备注" };
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet(year + "年度国土资源执法监察案卷评查结果汇总表");

		String title = year + "年度国土资源执法监察案卷评查结果汇总表";
		// 创建导出居中样式
		HSSFCellStyle style = wb.createCellStyle();
		style = setCommonStyle(style);
		// 自动换行
		style.setWrapText(true);
		// 创建导出表头样式
		HSSFCellStyle styleHead = wb.createCellStyle();
		styleHead = setCommonStyle(styleHead);
		HSSFFont font = wb.createFont();
		font.setFontName("黑体");
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 粗体显示
		styleHead.setFont(font);
		// 标题样式
		HSSFCellStyle styleTitle = wb.createCellStyle();
		styleTitle = setCommonStyle(styleTitle);
		HSSFFont fontTitle = wb.createFont();
		fontTitle.setFontName("黑体");
		fontTitle.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 粗体显示
		fontTitle.setFontHeightInPoints((short) (16));
		styleTitle.setFont(fontTitle);
		// 标题
		HSSFRow rowTitle = sheet.createRow(0);
		Cell cellTitle = rowTitle.createCell(0);
		cellTitle.setCellValue(title);
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, CloumnName.length - 1));
		rowTitle.getCell(0).setCellStyle(styleTitle);

		// 创建第一行 列头
		HSSFRow row = sheet.createRow(1);
		for (int nK = 0; nK < CloumnName.length; nK++) {
			HSSFCell cell = row.createCell(nK);
			cell.setCellValue(CloumnName[nK]);
			cell.setCellStyle(styleHead);
		}

		// 填充值
		for (int nI = 0; nI < list.size(); nI++) {
			ReviewLawcase item = list.get(nI);
			if (item.getUserMaps() != null && item.getUserMaps().size() > 0) {
				int currentRownum = sheet.getLastRowNum() + 1;
				for (int j = 0; j < item.getUserMaps().size(); j++) {
					LawCaseUserMap lawCaseUserMap = item.getUserMaps().get(j);
					row = sheet.createRow(currentRownum + j);
					row.createCell(0).setCellValue(nI + 1);
					row.createCell(1).setCellValue(item.getRegionName());
					row.createCell(2).setCellValue(item.getCaseName());
					if(item.getReviewState() == ReviewState.unMatched){
						row.createCell(3).setCellValue("");
					}else{
						row.createCell(3).setCellValue(item.getPcCount());
					}
					//不符合评查条件和能否复核
					if (item.getReviewState() == ReviewState.unMatched || !item.isCanReview()) {
						row.createCell(4).setCellValue("");
					} else {
						row.createCell(4).setCellValue(item.getFinalScore() + "");
					}
					row.createCell(5).setCellValue(lawCaseUserMap.getEvaluationUserName());
					//不符合评查条件和初评未提交
					if(item.getReviewState() == ReviewState.unMatched||!lawCaseUserMap.isFirstSubmitted()){
						row.createCell(6).setCellValue("");
					}else{
						row.createCell(6).setCellValue(lawCaseUserMap.getFirstTotalScore());
					}
					row.createCell(7).setCellValue(lawCaseUserMap.getReviewUserName());
					if (lawCaseUserMap.isReviewSubmitted()) {
						row.createCell(8).setCellValue(lawCaseUserMap.getReviewTotalScore());
					} else {
						row.createCell(8).setCellValue("");
					}
					row.createCell(9).setCellValue(lawCaseUserMap.getReasons());
					row.createCell(10).setCellValue(lawCaseUserMap.getStatisticRemark());
					row = setCellStyle(row, style, CloumnName.length);
				}
				// 合并前五列
				sheet.addMergedRegion(
						new CellRangeAddress(currentRownum, currentRownum + item.getUserMaps().size() - 1, 0, 0));
				// 设置单元格样式
				sheet.getRow(currentRownum).getCell(0).setCellStyle(style);
				sheet.addMergedRegion(
						new CellRangeAddress(currentRownum, currentRownum + item.getUserMaps().size() - 1, 1, 1));
				sheet.getRow(currentRownum).getCell(0).setCellStyle(style);
				sheet.addMergedRegion(
						new CellRangeAddress(currentRownum, currentRownum + item.getUserMaps().size() - 1, 2, 2));
				sheet.getRow(currentRownum).getCell(0).setCellStyle(style);
				sheet.addMergedRegion(
						new CellRangeAddress(currentRownum, currentRownum + item.getUserMaps().size() - 1, 3, 3));
				sheet.getRow(currentRownum).getCell(0).setCellStyle(style);
				sheet.addMergedRegion(
						new CellRangeAddress(currentRownum, currentRownum + item.getUserMaps().size() - 1, 4, 4));
				sheet.getRow(currentRownum).getCell(0).setCellStyle(style);
			} else {
				int currentRownum = sheet.getLastRowNum() + 1;
				row = sheet.createRow(currentRownum);
				row.createCell(0).setCellValue(nI + 1);
				row.createCell(1).setCellValue(item.getRegionName());
				row.createCell(2).setCellValue(item.getCaseName());
				row.createCell(3).setCellValue(item.getPcCount());
				row.createCell(4).setCellValue("");
				row.createCell(5).setCellValue("");
				row.createCell(6).setCellValue("");
				row.createCell(7).setCellValue("");
				row.createCell(8).setCellValue("");
				row.createCell(9).setCellValue("");
				row.createCell(10).setCellValue("");
				row = setCellStyle(row, style, CloumnName.length);
			}

		}
		// 每列宽度
		sheet.setColumnWidth(0, (short) 2000);
		sheet.setColumnWidth(1, (short) 7000);
		sheet.setColumnWidth(2, (short) 8000);
		sheet.setColumnWidth(3, (short) 3000);
		sheet.setColumnWidth(4, (short) 3000);
		sheet.setColumnWidth(5, (short) 3000);
		sheet.setColumnWidth(6, (short) 3000);
		sheet.setColumnWidth(7, (short) 3000);
		sheet.setColumnWidth(8, (short) 3000);
		sheet.setColumnWidth(9, (short) 8000);
		sheet.setColumnWidth(10, (short) 4000);
		try {
			response.setHeader("content-disposition",
					"attachment;filename=" + URLEncoder.encode(year + "年度国土资源执法监察案卷评查结果汇总表", "utf-8") + ".xls");
			OutputStream out = response.getOutputStream();
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			wb.write(baos);
			byte[] xlsBytes = baos.toByteArray();
			out.write(xlsBytes);
			out.close();
		} catch (

		Exception e)

		{
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * 公共样式
	 * 
	 * @param style
	 * @return
	 */
	public HSSFCellStyle setCommonStyle(HSSFCellStyle style) {
		style.setAlignment(HSSFCellStyle.VERTICAL_CENTER);
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);

		style.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框

		return style;
	}

	/**
	 * 设置每行单元格样式
	 * 
	 * @return
	 */
	public HSSFRow setCellStyle(HSSFRow row, HSSFCellStyle style, int size) {
		for (int nK = 0; nK < size; nK++) {
			HSSFCell cell = row.getCell(nK);
			cell.setCellStyle(style);
		}
		return row;
	}

	/**
	 * 根据年份和执法单位获取评查人员
	 * 
	 * @param year
	 * @param regionCode
	 * @return
	 */
	public List<EvaluationPerson> getUserByYearAndRegionCode(int year) {
		return evaluationStatisticsDao.getUserByYearAndRegionCode(year);
	}
}
