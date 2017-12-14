package phalaenopsis.lawcaseevaluation.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ImportResult {

	@JsonProperty("Success")
	private boolean success = true;
	/**
	 * 返回结果码
	 */
	private int code;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
}
