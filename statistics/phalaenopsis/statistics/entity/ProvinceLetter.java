package phalaenopsis.statistics.entity;

/**
 * 省级信访情况
 * @author chunl
 * 2017年8月7日下午2:24:47
 */
public class ProvinceLetter {
	
	/**
	 * 日期
	 */
	private String date;
	
	/**
	 * 来信
	 */
	private Integer letter;
	
	/**
	 * 来访
	 */
	private Integer vister;

	/**
	 * 人次
	 */
	private Integer timer;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Integer getLetter() {
		return letter;
	}

	public void setLetter(Integer letter) {
		this.letter = letter;
	}

	public Integer getVister() {
		return vister;
	}

	public void setVister(Integer vister) {
		this.vister = vister;
	}

	public Integer getTimer() {
		return timer;
	}

	public void setTimer(Integer timer) {
		this.timer = timer;
	}
	
	
}
