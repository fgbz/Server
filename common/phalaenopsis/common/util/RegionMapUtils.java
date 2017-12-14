package phalaenopsis.common.util;

import phalaenopsis.common.entity.OrganizationGrade;
import phalaenopsis.common.entity.User;

/**
 * 
 * @author langl
 *
 */
public class RegionMapUtils {
	
	private static final String REGION_MAP_CACHE = "regionMapCache";
	
	
	
	public static String getRegionMapKey(User user, String regionType) {
		String key = null;
        switch (user.OrgGrade) {
            case OrganizationGrade.Province:
                key = "Province";
                break;
            case OrganizationGrade.City:
                key = String.format("%d%s", user.regions[0].ParentID, regionType == "管理区" ? "GLQ" : "XZQ"); 
                break;
            case OrganizationGrade.County:
                if (user.regions.length == 1)
                    key = String.valueOf(user.regions[0].RegionID);
                break;
        }
        if (key != null)
            return String.format("%s%s", REGION_MAP_CACHE, key);
        else
            return key;
	}
	
	

}
