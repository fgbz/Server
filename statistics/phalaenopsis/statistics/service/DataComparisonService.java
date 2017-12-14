package phalaenopsis.statistics.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import phalaenopsis.common.entity.User;
import phalaenopsis.common.method.Basis;
import phalaenopsis.common.util.QueryUtil;
import phalaenopsis.statistics.entity.SatelliteAndReport;
import phalaenopsis.statistics.entity.ContrastYear;
import phalaenopsis.statistics.entity.PatroAndReport;
import phalaenopsis.statistics.entity.ReportAndillegal;

/**
 * 统计分析
 * 
 * @author dongdongt
 *
 */
@Service("dataComparisonService")
public class DataComparisonService extends Basis {
	// 设置日志
	private static Logger logger = LoggerFactory.getLogger(DataComparisonService.class);

	/**
	 * 市统计上报潍坊案件数及违法用地面积数比对
	 * 
	 * @return
	 */
	public List<ReportAndillegal> getReportAndillegal(QueryUtil query) {
		User currentUser = getCurrentUser();
		List<ReportAndillegal> list = new ArrayList<ReportAndillegal>();
		if (currentUser.getRegionList().size() > 0) {
			for (int i = 0; i < currentUser.getRegionList().size(); i++) {
				ReportAndillegal rai = new ReportAndillegal();
				rai.setRegionName(currentUser.getRegionList().get(i).getRegionName());
				rai.setReportCount(i * 100);
				rai.setIllegalArea(i * 200);
				list.add(rai);
			}
		}
		return list;
	}

	/**
	 * 省级巡查与统计上报数据比对
	 * 
	 * @return
	 */
	public List<PatroAndReport> getPatroAndReport(QueryUtil query) {
		User currentUser = getCurrentUser();
		List<PatroAndReport> list = new ArrayList<>();
		if (currentUser.getRegionList().size() > 0) {
			for (int i = 0; i < currentUser.getRegionList().size(); i++) {
				PatroAndReport par = new PatroAndReport();
				par.setRegionName(currentUser.getRegionList().get(i).getRegionName());
				par.setPatroArea(i * 58);
				par.setPatroCount(i * 8);
				par.setReportArea(i * 68);
				par.setReportCount(i * 9);
				list.add(par);
			}
		}
		return list;
	}

	/**
	 * 卫片数据与统计上报数据比对
	 * 
	 * @return
	 */
	public List<SatelliteAndReport> getSatelliteAndReport(QueryUtil query) {
		User currentUser = getCurrentUser();
		List<SatelliteAndReport> list = new ArrayList<>();
		if (currentUser.getRegionList().size() > 0) {
			for (int i = 0; i < currentUser.getRegionList().size(); i++) {
				SatelliteAndReport par = new SatelliteAndReport();
				par.setRegionName(currentUser.getRegionList().get(i).getRegionName());
				par.setSatelliteArea(i * 68);
				par.setSatelliteCount(i * 6);
				par.setReportArea(i * 88);
				par.setReportCount(i * 9);
				list.add(par);
			}
		}
		return list;
	}

	/**
	 * 市分年度数据比对
	 * 
	 * @return
	 */
	public List<ContrastYear> getContrastYear(QueryUtil query) {
		User currentUser = getCurrentUser();
		List<ContrastYear> list = new ArrayList<>();
		if (currentUser.getRegionList().size() > 0) {
			for (int i = 0; i < currentUser.getRegionList().size(); i++) {
				ContrastYear cy = new ContrastYear();
				cy.setRegionName(currentUser.getRegionList().get(i).getRegionName());
				cy.setMaxYearArea(i * 66);
				cy.setMaxYearCount(i * 9);
				cy.setMinYearArea(i * 88);
				cy.setMinYearCount(i * 8);
				list.add(cy);
			}
		}
		return list;
	}
}
