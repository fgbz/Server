package phalaenopsis.common.dao;

import java.util.List;

import phalaenopsis.common.entity.HomePageCaseInfo;
import phalaenopsis.common.entity.HomePagePartolInfo;

public interface HomePageInfoDao {

	List<HomePageCaseInfo> getChildRegionId(Integer regionId);

	List<HomePagePartolInfo> getPartolChildRegionId(Integer regionId);

}
