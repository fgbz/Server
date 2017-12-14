/**
 * Copyright 2017 www.kotei-info.com
 * 
 * All rights reserved.
 * 	
 */
package phalaenopsis.common.method.cache;
 
import phalaenopsis.common.entity.OrganizationGrade;
import phalaenopsis.common.entity.User;

/**
 * 缓存地图首页数据  
 * @author yangw786
 * 2017年7月19日上午11:52:22
 */
public class RegionMapCache extends DefaultCache {

	private static final String REGION_MAP_CACHE = "regionMapCache";

	/**
	 * 写入REGION_MAP_CACHE缓存
	 * 
	 * @param key
	 * @return
	 */
	public static void setRegionMap(User user, String regionType, Object value) {
//		String key = getRegionMapKey(user, regionType);
//		if (null != key && !key.equals(""))
//			put(REGION_MAP_CACHE, key, value);
	} 
	
	/**
	 * 获取缓存中数据
	 * 
	 * @param key
	 * @return
	 */
	public static Object getRegionMap(User user, String regionType) {
		String key = getRegionMapKey(user, regionType);
		return get(REGION_MAP_CACHE, key);
	}
	
	public static String getRegionMapKey(User user, String regionType) {
		String key = null;
//        switch (user.OrgGrade) {
//            case OrganizationGrade.Province:
//                key = "Province";
//                break;
//            case OrganizationGrade.City:
//                key = String.format("%d%s", user.regions[0].ParentID, regionType == "管理区" ? "GLQ" : "XZQ");
//                break;
//            case OrganizationGrade.County:
//                if (user.regions.length == 1)
//                    key = String.valueOf(user.regions[0].RegionID);
//                break;
//        }
//        if (key != null)
//            return String.format("%s%s", REGION_MAP_CACHE, key);
//        else
            return key;
	}
}
