package phalaenopsis.allWeather.entity;

import org.omg.CORBA.DynAnyPackage.Invalid;

import phalaenopsis.satellitegraph.entity.Polygon;

public class ResultPolygon {

	public enum ResultPolygonErrorEnum {
		/**
		 * 上传文件格式错误
		 */
		InvalidFile,
		/**
		 * 文件读取错误
		 */
		ReadShapeFaild,
		/**
		 * 坐标错误
		 */
		IllegalShape,
		/**
		 * 代号错误
		 */
		UnCodeNum
	}

	/**
	 * 是否成功
	 */
	private boolean result;

	/**
	 * 返回前端geometry对象
	 */
	private Polygon geometry;
	
	/**
	 * 返回shape文件相对于服务器的路径
	 */
	private String fileServerPath;



	/**
	 * 如果错误。返回错误信息
	 */
	private ResultPolygonErrorEnum errorEnum;

	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

	public Polygon getGeometry() {
		return geometry;
	}

	public void setGeometry(Polygon geometry) {
		this.geometry = geometry;
	}

	public ResultPolygonErrorEnum getErrorEnum() {
		return errorEnum;
	}

	public void setErrorEnum(ResultPolygonErrorEnum errorEnum) {
		this.errorEnum = errorEnum;
	}
	
	public String getFileServerPath() {
		return fileServerPath;
	}

	public void setFileServerPath(String fileServerPath) {
		this.fileServerPath = fileServerPath;
	}

	public ResultPolygon(boolean result, ResultPolygonErrorEnum errorEnum) {
		super();
		this.result = result;
		this.errorEnum = errorEnum;
	}

	public ResultPolygon(boolean result, Polygon geometry, String fileServerPath) {
		super();
		this.result = result;
		this.geometry = geometry;
		this.fileServerPath = fileServerPath;
	}

}
