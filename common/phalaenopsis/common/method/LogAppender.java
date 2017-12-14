package phalaenopsis.common.method;

public class LogAppender extends org.apache.log4j.DailyRollingFileAppender  {
	
	/**
	 * 文件最新
	 */
	private String maxFileSize;

	public String getMaxFileSize() {
		return maxFileSize;
	}

	public void setMaxFileSize(String maxFileSize) {
		this.maxFileSize = maxFileSize;
	}


}
