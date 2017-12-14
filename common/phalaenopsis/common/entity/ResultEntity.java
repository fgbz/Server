package phalaenopsis.common.entity;

public class ResultEntity {

	public enum ResultEnum {
		Success, Faild
	}

	/**
	 * 返回状态
	 */
	private ResultEnum result;

	/**
	 * 如果失败，返回消息
	 */
	private String resMsg;

	public ResultEnum getResult() {
		return result;
	}

	public void setResult(ResultEnum result) {
		this.result = result;
	}

	public String getResMsg() {
		return resMsg;
	}

	public void setResMsg(String resMsg) {
		this.resMsg = resMsg;
	}

	public ResultEntity() {
		super();
	}

	public ResultEntity(ResultEnum result) {
		super();
		this.result = result;
	}

	public ResultEntity(ResultEnum result, String resMsg) {
		super();
		this.result = result;
		this.resMsg = resMsg;
	}

}
