package phalaenopsis.common.entity;

import java.util.List;


import com.fasterxml.jackson.annotation.JsonProperty;
import phalaenopsis.common.entity.Identity.IdentifyField;
import phalaenopsis.common.method.Basis;

public class MapLayer extends Basis {

	/**
	 * 主键ID
	 */
	@JsonProperty("ID")
	public String ID;

	/**
	 * 图层ID
	 */
	@JsonProperty("LayerID")
	public String LayerID;

	/**
	 * 图层名称
	 */
	@JsonProperty("LayerName")
	public String LayerName;

	/**
	 * 图层URL
	 */
	@JsonProperty("LayerUrl")
	private String LayerUrl;

	/**
	 * 图层URL对外映射地址
	 */
	@JsonProperty("MapedUrl")
	public String MapedUrl;

	/**
	 * 图层父节点ID
	 */
	@JsonProperty("FatherLayerID")
	public String FatherLayerID;

	/**
	 * 图层类型
	 */
	@JsonProperty("LayerType")
	public String LayerType;

	/**
	 * 图层叠加显示顺序
	 */
	@JsonProperty("LayerOrder")
	public int LayerOrder;

	/**
	 * 图层集合父级节点显示顺序
	 */
	@JsonProperty("GroupOrder")
	public int GroupOrder;

	/**
	 * 是否为BaseMap(1：是，0：否)
	 */
	@JsonProperty("IsBaseMap")
	public int IsBaseMap;

	/**
	 * 图层初始化时是否默认勾选(1：是，0：否)
	 */
	@JsonProperty("IsChecked")
	public int IsChecked;

	/**
	 * 图层透明度
	 */
	@JsonProperty("Opacity")
	public double Opacity;

	/**
	 * 图层所属设备
	 */
	@JsonProperty("LayerBelong")
	public int LayerBelong;

	/**
	 * 备注标签，用于部分特殊用途标记(暂时用于是否分宗地图加载)
	 */
	@JsonProperty("Tag")
	public String Tag;

	/**
	 * 图层范围最小x
	 */
	@JsonProperty("MinX")
	public double MinX;

	/**
	 * 图层范围最小y
	 */
	@JsonProperty("MinY")
	public double MinY;

	/**
	 * 图层范围最大x
	 */
	@JsonProperty("MaxX")
	public double MaxX;

	/**
	 * 图层范围最大y
	 */
	@JsonProperty("MaxY")
	public double MaxY;

	/**
	 * 地图服务协议版本号
	 */
	@JsonProperty("ServiceVersion")
	public String ServiceVersion;

	/**
	 * 坐标参考系编码
	 */
	@JsonProperty("Wkid")
	public int Wkid;

	private List<IdentifyField> identifyFields;

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public List<IdentifyField> getIdentifyField() {
		return identifyFields;
	}

	public void setIdentifyField(List<IdentifyField> identifyField) {
		this.identifyFields = identifyField;
	}

	public String getLayerID() {
		return LayerID;
	}

	public void setLayerID(String layerID) {
		LayerID = layerID;
	}

	public String getLayerName() {
		return LayerName;
	}

	public void setLayerName(String layerName) {
		LayerName = layerName;
	}

	public String getLayerUrl() {
//		User user = getCurrentUser();
//		if (null != user && null != user.getMapLayerGrade() && user.getMapLayerGrade() == 3){
//				return LayerUrl;
//		}
//		return MapedUrl;
		return LayerUrl;
	}

	public void setLayerUrl(String layerUrl) {
		LayerUrl = layerUrl;
	}

	public String getMapedUrl() {
		return MapedUrl;
	}

	public void setMapedUrl(String mapedUrl) {
		MapedUrl = mapedUrl;
	}

	public String getFatherLayerID() {
		return FatherLayerID;
	}

	public void setFatherLayerID(String fatherLayerID) {
		FatherLayerID = fatherLayerID;
	}

	public String getLayerType() {
		return LayerType;
	}

	public void setLayerType(String layerType) {
		LayerType = layerType;
	}

	public int getLayerOrder() {
		return LayerOrder;
	}

	public void setLayerOrder(int layerOrder) {
		LayerOrder = layerOrder;
	}

	public int getGroupOrder() {
		return GroupOrder;
	}

	public void setGroupOrder(int groupOrder) {
		GroupOrder = groupOrder;
	}

	public int getIsBaseMap() {
		return IsBaseMap;
	}

	public void setIsBaseMap(int isBaseMap) {
		IsBaseMap = isBaseMap;
	}

	public int getIsChecked() {
		return IsChecked;
	}

	public void setIsChecked(int isChecked) {
		IsChecked = isChecked;
	}

	public double getOpacity() {
		return Opacity;
	}

	public void setOpacity(double opacity) {
		Opacity = opacity;
	}

	public int getLayerBelong() {
		return LayerBelong;
	}

	public void setLayerBelong(int layerBelong) {
		LayerBelong = layerBelong;
	}

	public String getTag() {
		return Tag;
	}

	public void setTag(String tag) {
		Tag = tag;
	}

	public double getMinX() {
		return MinX;
	}

	public void setMinX(double minX) {
		MinX = minX;
	}

	public double getMinY() {
		return MinY;
	}

	public void setMinY(double minY) {
		MinY = minY;
	}

	public double getMaxX() {
		return MaxX;
	}

	public void setMaxX(double maxX) {
		MaxX = maxX;
	}

	public double getMaxY() {
		return MaxY;
	}

	public void setMaxY(double maxY) {
		MaxY = maxY;
	}

	public String getServiceVersion() {
		return ServiceVersion;
	}

	public void setServiceVersion(String serviceVersion) {
		ServiceVersion = serviceVersion;
	}

	public int getWkid() {
		return Wkid;
	}

	public void setWkid(int wkid) {
		Wkid = wkid;
	}

}
