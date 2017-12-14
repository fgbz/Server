package phalaenopsis.lawcase.entity;

/**
 * 七参数
 * 
 */
public class SevenParamInfo {
	/**
	 * 主键
	 * 
	 */
	private String id;
	/**
	 * 名称
	 * 
	 */
	private String name;
	/**
	 * X平移
	 * 
	 */
	private double dx;
	/**
	 * Y平移
	 * 
	 */
	private double dy;
	/**
	 * Z平移
	 * 
	 */
	private double dz;
	/**
	 * X旋转
	 * 
	 */
	private double rx;
	/**
	 * Y旋转
	 * 
	 */
	private double ry;
	/**
	 * Z旋转
	 * 
	 */
	private double rz;
	/**
	 * 比例因子
	 * 
	 */
	private double k;
	/**
	 * 东向增量
	 * 
	 */
	private double e;
	/**
	 * 北向增量
	 * 
	 */
	private double n;
	/**
	 * 投影方法
	 * 
	 */
	private String methrod;
	/**
	 * 原中央经线
	 * 
	 */
	private double oldCenter;
	/**
	 * 新中央经线
	 * 
	 */
	private double newCenter;
	/**
	 * 计算先后
	 * 
	 */
	private String calculateOrder;
	/**
	 * 原坐标系ID
	 * 
	 */
	private String oldsrid;
	/**
	 * 原椭球体
	 * 
	 */
	private String fromSpheroidID;
	/**
	 * 目标椭球体
	 * 
	 */
	private String toSpheroidID;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getDx() {
		return dx;
	}
	public void setDx(double dx) {
		this.dx = dx;
	}
	public double getDy() {
		return dy;
	}
	public void setDy(double dy) {
		this.dy = dy;
	}
	public double getDz() {
		return dz;
	}
	public void setDz(double dz) {
		this.dz = dz;
	}
	public double getRx() {
		return rx;
	}
	public void setRx(double rx) {
		this.rx = rx;
	}
	public double getRy() {
		return ry;
	}
	public void setRy(double ry) {
		this.ry = ry;
	}
	public double getRz() {
		return rz;
	}
	public void setRz(double rz) {
		this.rz = rz;
	}
	public double getK() {
		return k;
	}
	public void setK(double k) {
		this.k = k;
	}
	public double getE() {
		return e;
	}
	public void setE(double e) {
		this.e = e;
	}
	public double getN() {
		return n;
	}
	public void setN(double n) {
		this.n = n;
	}
	public String getMethrod() {
		return methrod;
	}
	public void setMethrod(String methrod) {
		this.methrod = methrod;
	}
	public double getOldCenter() {
		return oldCenter;
	}
	public void setOldCenter(double oldCenter) {
		this.oldCenter = oldCenter;
	}
	public double getNewCenter() {
		return newCenter;
	}
	public void setNewCenter(double newCenter) {
		this.newCenter = newCenter;
	}
	public String getCalculateOrder() {
		return calculateOrder;
	}
	public void setCalculateOrder(String calculateOrder) {
		this.calculateOrder = calculateOrder;
	}
	public String getOldsrid() {
		return oldsrid;
	}
	public void setOldsrid(String oldsrid) {
		this.oldsrid = oldsrid;
	}
	public String getFromSpheroidID() {
		return fromSpheroidID;
	}
	public void setFromSpheroidID(String fromSpheroidID) {
		this.fromSpheroidID = fromSpheroidID;
	}
	public String getToSpheroidID() {
		return toSpheroidID;
	}
	public void setToSpheroidID(String toSpheroidID) {
		this.toSpheroidID = toSpheroidID;
	}
	
}