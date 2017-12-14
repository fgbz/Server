package phalaenopsis.common.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Menu implements Serializable {

	/// <summary>
	/// 主键ID
	/// </summary>
	public int ID;

	/// <summary>
	/// 菜单ID
	/// </summary>
	public String MenuID;

	/// <summary>
	/// 菜单名称
	/// </summary>
	public String MenuName;

	/// <summary>
	/// 菜单排序顺序
	/// </summary>
	public int Rank;

	/// <summary>
	/// 菜单的class样式类
	/// </summary>
	public String CssClass;

	/// <summary>
	/// 菜单类型
	/// 1：业务功能菜单 2：系统管理菜单
	/// </summary>
	public int Type;
	
	public String parentMenuId;
	
	//二级菜单
	public List<Menu> itemMenus = new ArrayList<Menu>();

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getMenuID() {
		return MenuID;
	}

	public void setMenuID(String menuID) {
		MenuID = menuID;
	}

	public String getMenuName() {
		return MenuName;
	}

	public void setMenuName(String menuName) {
		MenuName = menuName;
	}

	public int getRank() {
		return Rank;
	}

	public void setRank(int rank) {
		Rank = rank;
	}

	public String getCssClass() {
		return CssClass;
	}

	public void setCssClass(String cssClass) {
		CssClass = cssClass;
	}

	public int getType() {
		return Type;
	}

	public void setType(int type) {
		Type = type;
	}

	public String getParentMenuId() {
		return parentMenuId;
	}

	public void setParentMenuId(String parentMenuId) {
		this.parentMenuId = parentMenuId;
	}

	public List<Menu> getItemMenus() {
		return itemMenus;
	}

	public void setItemMenus(List<Menu> itemMenus) {
		this.itemMenus = itemMenus;
	}

}
