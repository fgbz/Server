package phalaenopsis.common.entity;

public class Condition {
	
	private String Key;
	
	private String Value;

	public String getKey() {
		return Key;
	}

	public void setKey(String key) {
		Key = key;
	}

	public String getValue() {
		return Value;
	}

	public void setValue(String value) {
		Value = value;
	}
	
	
	
	public Condition() {
		super();
	}

	public Condition(String key, String value) {
		super();
		Key = key;
		Value = value;
	}

}
