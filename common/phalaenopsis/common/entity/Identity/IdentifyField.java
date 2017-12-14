package phalaenopsis.common.entity.Identity;

import phalaenopsis.common.entity.MapLayer;

public class IdentifyField {
	public String ID;

	/**
	 * 字段名称
	 */
	public String Name;

	/**
	 * 字段别名
	 */
	public String Alias;

	/**
	 * 显示顺序
	 */
	public int DisplayOrder;

	/**
	 * 字段用途
	 */
	public FieldUseType UseType;

	/**
	 * 是否关键字 用于地图搜索界面列表展示
	 */
	public boolean IsKeyValue;

	/**
	 * 所属图层
	 */
	public MapLayer Maplayer;

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getAlias() {
		return Alias;
	}

	public void setAlias(String alias) {
		Alias = alias;
	}

	public int getDisplayOrder() {
		return DisplayOrder;
	}

	public void setDisplayOrder(int displayOrder) {
		DisplayOrder = displayOrder;
	}

	public FieldUseType getUseType() {
		return UseType;
	}

	public void setUseType(FieldUseType useType) {
		UseType = useType;
	}

	public boolean isIsKeyValue() {
		return IsKeyValue;
	}

	public void setIsKeyValue(boolean isKeyValue) {
		IsKeyValue = isKeyValue;
	}

	public MapLayer getMaplayer() {
		return Maplayer;
	}

	public void setMaplayer(MapLayer maplayer) {
		Maplayer = maplayer;
	}
	
}
