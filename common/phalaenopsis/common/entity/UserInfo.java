package phalaenopsis.common.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 用户信息
 *
 */
public class UserInfo  implements Serializable  {
	
	
	private static final long serialVersionUID = 4906665814417330369L;

	/**
	 * 用户ID
	 */
	public String userID;
	
	/**
	 * 用户姓名
	 */
	public String userName;
	
	/**
	 * 所属组织部门ID
	 */
	public String organizationID;
	
	/**
	 * 所属组织部门名称
	 */
	public String organizationName;
	
	/**
	 * 所属组织机构级别
	 */
	public  int orgGrade;
	
	/**
	 * 用户所属区域
	 */
	public List<Region> Regions;

	public UserInfo(String userID, String userName, String organizationID, String organizationName, int orgGrade,
			List<Region> regions) {
		super();
		this.userID = userID;
		this.userName = userName;
		this.organizationID = organizationID;
		this.organizationName = organizationName;
		this.orgGrade = orgGrade;
		Regions = regions;
	}
}
