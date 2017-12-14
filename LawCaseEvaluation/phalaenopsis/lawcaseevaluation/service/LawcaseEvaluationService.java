package phalaenopsis.lawcaseevaluation.service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;

import java.util.List;
import java.util.Map;

import javax.json.Json;
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
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

import phalaenopsis.common.entity.Page;
import phalaenopsis.common.entity.PagingEntity;
import phalaenopsis.lawcaseevaluation.dao.LawcaseEvaluationDAO;
import phalaenopsis.lawcaseevaluation.entity.*;
import phalaenopsis.lawcaseevaluation.entity.caseDetail.CaseJudge;
import phalaenopsis.lawcaseevaluation.entity.caseDetail.LawCaseUserMap;
import phalaenopsis.lawcaseevaluation.entity.caseDetail.SurveryStandard;

@Service("lawcaseEvaluationService")
public class LawcaseEvaluationService {
	@Autowired
	private LawcaseEvaluationDAO lawcaseEvaluationDAO;
	@Autowired
	private EvaluationStatisticsService evaluationStatisticsService;

	/**
	 * 
	 * @param account
	 * @param flag
	 * @return
	 */
	public List<FirstEvaluationCondition> getFirstEvaluationCondition(String account, boolean flag) {
		List<FirstEvaluationCondition> result = new ArrayList<FirstEvaluationCondition>();
		try {
			Map<Integer, List<KeyValueEntity>> map = lawcaseEvaluationDAO.getFirstEvaluationCondition(account, flag);
			for (Map.Entry<Integer, List<KeyValueEntity>> entry : map.entrySet()) {
				FirstEvaluationCondition condition = new FirstEvaluationCondition();
				condition.setYear(entry.getKey());
				condition.setRegions(entry.getValue());

				result.add(condition);
			}
		} catch (Exception e) {

		}

		return result;
	}

	/**
	 * 获取初评列表
	 * @param page
	 * @return
	 */
	public PagingEntity<FirstTrialLawcase> getFirstTrialLawcases(Page page) {
		return lawcaseEvaluationDAO.getFirstTrialLawcases(page);
	}

	/**
	 * 获取初评案件信息
	 * @param caseid 案件id
	 * @param userId 用户id
	 * @return
	 */
	public FirstTrialLawcase getFirstTrialLawcase(String caseid, String userId) {
		return lawcaseEvaluationDAO.getFirstTrialLawcase(caseid, userId);
	}

	/**
	 * 保存初评
	 * @param lawcase
	 * @return
	 */
	public int saveFirstTrialLawcase(FirstTrialLawcase lawcase) {
		return lawcaseEvaluationDAO.saveFirstTrialLawcase(lawcase);
	}

	/**
	 * 获取复核列表
	 * @param page
	 * @return
	 */
	public PagingEntity<ReviewLawcase> getReviewLawcases(Page page) {
		return lawcaseEvaluationDAO.getReviewLawcases(page);
	}

	/**
	 * 判断已经提交该案件的数量
	 * 
	 * @param accountId 案件id
	 * @return
	 */
	public int getFirstTrialSurveyCount(long accountId, long userid) {
		return lawcaseEvaluationDAO.getFirstTrialSurveyCount(accountId, userid);
	}

	/**
	 * 获取当年优秀、合格、不合格、需要复查的案件数量
	 * 
	 * @return
	 */
	public CaseGradeCount getCaseGradeCount(int year) {
		return lawcaseEvaluationDAO.getCaseGradeCount(year);
	}

	/**
	 * 复核完结
	 * 
	 * @return
	 */
	public int compeleteReview(long caseid) {
		return lawcaseEvaluationDAO.compeleteReview(caseid);
	}

	/**
	 * 通过id获取复核信息
	 * 
	 * @param id
	 * @return
	 */
	public ReviewLawcase getReviewInfoById(long id) {
		return lawcaseEvaluationDAO.getReviewInfoById(id);
	}

	/**
	 * 导出复核分数
	 * 
	 * @param
	 * @return
	 */
	public void ExportReviewList(long caseid, int xh, HttpServletResponse response) {
		ReviewLawcase item = getReviewInfoById(caseid);
		String[] CloumnName = { "序号", "执法单位市本级/县(市、区)","案卷名称", "评查结果", "评查人员", "评查得分", "复核人员", "复核得分", "评查人员提出的问题" };
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("复核分数列表");

		String title = "复核分数列表";
		// 创建导出居中样式
		HSSFCellStyle style = wb.createCellStyle();
		style = evaluationStatisticsService.setCommonStyle(style);
		// 自动换行
		style.setWrapText(true);
		// 创建导出表头样式
		HSSFCellStyle styleHead = wb.createCellStyle();
		styleHead = evaluationStatisticsService.setCommonStyle(styleHead);
		HSSFFont font = wb.createFont();
		font.setFontName("黑体");
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 粗体显示
		styleHead.setFont(font);
		// 标题样式
		HSSFCellStyle styleTitle = wb.createCellStyle();
		styleTitle = evaluationStatisticsService.setCommonStyle(styleTitle);
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
		if (item.getUserMaps() != null && item.getUserMaps().size() > 0) {
			int currentRownum = sheet.getLastRowNum() + 1;
			for (int j = 0; j < item.getUserMaps().size(); j++) {
				LawCaseUserMap lawCaseUserMap = item.getUserMaps().get(j);
				row = sheet.createRow(currentRownum + j);
				row.createCell(0).setCellValue(xh);
				row.createCell(1).setCellValue(item.getRegionName());
				row.createCell(2).setCellValue(item.getCaseName());
				row.createCell(3).setCellValue(item.getFinalScore() + "");
				row.createCell(4).setCellValue(lawCaseUserMap.getEvaluationUserName());
				row.createCell(5).setCellValue(lawCaseUserMap.getFirstTotalScore());
				row.createCell(6).setCellValue(lawCaseUserMap.getReviewUserName());
				if (lawCaseUserMap.isReviewSubmitted()) {
					row.createCell(7).setCellValue(lawCaseUserMap.getReviewTotalScore());
				} else {
					row.createCell(7).setCellValue("");
				}
				row.createCell(8).setCellValue(lawCaseUserMap.getReasons());
				row = evaluationStatisticsService.setCellStyle(row, style, CloumnName.length);
			}
			// 合并前四列
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
		} else {
			int currentRownum = sheet.getLastRowNum() + 1;
			row = sheet.createRow(currentRownum);
			row.createCell(0).setCellValue(xh);
			row.createCell(1).setCellValue(item.getRegionName());
			row.createCell(2).setCellValue(item.getCaseName());
			row.createCell(3).setCellValue("");
			row.createCell(4).setCellValue("");
			row.createCell(5).setCellValue("");
			row.createCell(6).setCellValue("");
			row.createCell(7).setCellValue("");
			row.createCell(8).setCellValue("");
			row = evaluationStatisticsService.setCellStyle(row, style, CloumnName.length);
		}

		// 每列宽度
		sheet.setColumnWidth(0, (short) 2000);
		sheet.setColumnWidth(1, (short) 8000);
		sheet.setColumnWidth(2, (short) 8000);
		sheet.setColumnWidth(3, (short) 3000);
		sheet.setColumnWidth(4, (short) 3000);
		sheet.setColumnWidth(5, (short) 3000);
		sheet.setColumnWidth(6, (short) 3000);
		sheet.setColumnWidth(7, (short) 3000);
		sheet.setColumnWidth(8, (short) 8000);
		try {
			response.setHeader("content-disposition",
					"attachment;filename=" + URLEncoder.encode(item.getCaseCode()+"复核分数列表", "utf-8") + ".xls");
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
	}

	/**
	 * 导出初评信息
	 * 
	 * @param response
	 * @throws IOException
	 */
	public void ExportFirstTrialSurvey(String accountId, String userid, String name, String item,
			HttpServletResponse response) throws IOException {

		// 获取初评信息
		FirstTrialLawcase firstTrialLawcase = getFirstTrialLawcase(accountId, userid);
		LawcaseAccount lawcaseAccount = JSON.parseObject(item, LawcaseAccount.class);
		
		//获取文件路径 
		String path = LawcaseEvaluationService.class.getClassLoader().getResource("").getPath();
		InputStream inputStream = new FileInputStream(path + "/conf/standard.doc");
		
		//初始化doc对象 
		HWPFDocument doc = new HWPFDocument(inputStream);
		
		//定义doc作用范围
		Range range = doc.getRange();
		if (firstTrialLawcase.isFirstSubmitted()) {
			range.replaceText("${total}", firstTrialLawcase.getFirstTotalScore() + "");
		} else {
			range.replaceText("${total}", "100");
		}

		range.replaceText("${name}", name);

		range.replaceText("${regionName}", lawcaseAccount.getRegionName());
		range.replaceText("${casecode}", lawcaseAccount.getCaseCode());
		range.replaceText("${casename}", lawcaseAccount.getCaseName());
		range.replaceText("${maintruth}", lawcaseAccount.getMainTruth());
		range.replaceText("${end}", lawcaseAccount.getIsClosed());
		range.replaceText("${import}", lawcaseAccount.getIsImportant());
		range.replaceText("${self}", lawcaseAccount.getIsChecked());
		range.replaceText("${city}", lawcaseAccount.getIsCityChecked());

		CaseJudge caseJudge = firstTrialLawcase.getCaseJudge();
		int count = 1;

		// 赋值
		for (int i = 0; i < caseJudge.getSurveyProjects().size(); i++) {
			for (int j = 0; j < caseJudge.getSurveyProjects().get(i).getSurveyContents().size(); j++) {
				for (int k = 0; k < caseJudge.getSurveyProjects().get(i).getSurveyContents().get(j)
						.getSurveryStandards().size(); k++) {
					SurveryStandard surveryStandard = caseJudge.getSurveyProjects().get(i).getSurveyContents().get(j)
							.getSurveryStandards().get(k);
					
					//不为空时赋值
					if (surveryStandard.getReason() != null) {
						range.replaceText("${respon" + count + "}", surveryStandard.getReason());
					} else {
						range.replaceText("${respon" + count + "}", "");
					}
					// 是否不合格
					if (count >= 59) {
						if (surveryStandard.getFirstScore() == 0) {
							range.replaceText("${value" + count + "}", "合格");
						} else {
							range.replaceText("${value" + count + "}", "不合格");
						}
					} else {
						range.replaceText("${value" + count + "}", surveryStandard.getFirstScore() + "");
					}
					count++;
				}
			}
		}

		try {
			response.setContentType("application/x-msdownload");
			response.setHeader("content-disposition",
					"attachment;filename=" + URLEncoder.encode(lawcaseAccount.getCaseCode()+"-初评", "utf-8") + ".doc");
			OutputStream out = response.getOutputStream();
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			doc.write(baos);
			byte[] xlsBytes = baos.toByteArray();
			out.write(xlsBytes);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 保存复核
	 * @param lawcase
	 * @return
	 */
	public int saveReviewLawcase(FirstTrialLawcase lawcase) {
		return lawcaseEvaluationDAO.saveReviewLawcase(lawcase);
	}

}
