package phalaenopsis.common.entity;

/**
 * 导出统计分析用
 * @author dongdongt
 *
 */
public class ExportCondition {
	private String key;
	private Object value;
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
}
