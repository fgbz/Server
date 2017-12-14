package phalaenopsis.satellitegraph.entity;


import com.fasterxml.jackson.annotation.JsonProperty;

public class StaticItem {

	public String Key;

	@JsonProperty("Count")
	public int count;

	public double Value;
	
	public String Code;

	public String getCode() {
		return Code;
	}

	public void setCode(String code) {
		Code = code;
	}

	public String getKey() {
		return Key;
	}

	public void setKey(String key) {
		Key = key;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public double getValue() {
		return Value;
	}

	public void setValue(double value) {
		Value = value;
	}

	public StaticItem() {

	}

	public StaticItem(String key, double value) {
		Key = key;
		Value = value;
	}

}
