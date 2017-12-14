package phalaenopsis.common.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import phalaenopsis.common.dao.HomePageInfoDao;
import phalaenopsis.common.entity.HomePageCaseInfo;
import phalaenopsis.common.entity.HomePagePartolInfo;
import phalaenopsis.common.entity.HomePageSourceInfo;
import phalaenopsis.common.entity.HomePageVisitInfo;
import phalaenopsis.common.entity.User;

@Service("homePageInfoService")
public class HomePageInfoService {
	@Autowired
	private HomePageInfoDao dao;
	
	/**
	 * 获取案件统计信息
	 * @param 
	 * @return
	 */
	public List<HomePageCaseInfo> GetCaseInfo(User user) {
		// TODO Auto-generated method stub
		Integer regionId = user.getRegionId();
		List<HomePageCaseInfo> lists = new ArrayList<HomePageCaseInfo>();
		lists = dao.getChildRegionId(regionId);
		return lists;
	}
	
	/**
	 * 获取线索、信访统计信息
	 * @param 
	 * @return
	 */
	public HomePageVisitInfo GetVisitInfo(User user) {
		HomePageVisitInfo info = new HomePageVisitInfo();
		return info;
	}
	
	/**
	 * 获取巡查里程信息
	 * @param 
	 * @return
	 */
	public List<HomePagePartolInfo> GetPartolInfo(User user) {
		Integer regionId = user.getRegionId();
		List<HomePagePartolInfo> lists = new ArrayList<HomePagePartolInfo>();
		lists = dao.getPartolChildRegionId(regionId);
		return lists;
	}
	
	/**
	 * 获取线索来源
	 * @param 
	 * @return
	 */
	public List<HomePageSourceInfo> GetSourceInfo(User user) {
		List<HomePageSourceInfo> lists = new ArrayList<HomePageSourceInfo>();
		HomePageSourceInfo homePageSourceInfo = new HomePageSourceInfo();
		homePageSourceInfo.setCaseSource("信件");
		HomePageSourceInfo info = new HomePageSourceInfo();
		HomePageSourceInfo info2 = new HomePageSourceInfo();
		HomePageSourceInfo info3 = new HomePageSourceInfo();
		HomePageSourceInfo info4 = new HomePageSourceInfo();
		info2.setCaseSource("电子邮件");
		info3.setCaseSource("领导批办");
		info4.setCaseSource("媒体反映");
		lists.add(homePageSourceInfo);
		lists.add(info);
		lists.add(info2);
		lists.add(info3);
		lists.add(info4);
		return lists;
	}

}
