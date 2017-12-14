package phalaenopsis.illegalclue.entity;

/**
 * 界面返回值
 * @author dongdongt
 *
 */
public class ResultString {
	/**
	 * 返回值类型
	 */
	private ResultState resultState;
	/**
	 * id
	 */
	private String id;
	public ResultState getResultState() {
		return resultState;
	}
	public void setResultState(ResultState resultState) {
		this.resultState = resultState;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
}
