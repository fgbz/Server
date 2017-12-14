/**
 * 
 */
package phalaenopsis.lawcase.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;



/**
 * @author yuhangc 签字环节节点
 */
public class SignNode {
	/**
	 * 节点需要所有人签字
	 */
	public final int ALL = -1;

	private Map<String, Object> dict;

	/**
	 * 创建一个签字节点
	 * 
	 * @param node
	 * @param number
	 * @param KeyValuePair
	 */
	public SignNode(String node, int number, Entry<String, Object>... kvs) {
		this.node = node;
		this.number = number;
		this.ignore = false;
		this.properties = new LinkedHashMap<String, Object>(); // new ArrayList<Entry<String, Object>>();
		for (Entry<String, Object> entry : kvs) {
			this.properties.put(entry.getKey(), entry.getValue());
		}
	}

	/**
	 * 签字节点名称（在同一签字链中唯一）
	 */
	@JsonProperty("Node")
	private String node;

	/**
	 * 签字节点在流程链中的序号
	 */
	@JsonProperty("Sequence")
	private int sequence;

	/**
	 * 签字节点需要签字人的数量
	 */
	@JsonProperty("Number")
	private int number;

	/**
	 * 是否忽略该节点（若为true，则在签字时跳过该节点的签字）
	 */
	@JsonProperty("Ignore")
	private boolean ignore;

	/**
	 * 签字节点附加属性
	 */
	@JsonIgnore
	private Map<String, Object> properties;

	public String getNode() {
		return node;
	}

	public void setNode(String node) {
		this.node = node;
	}

	public int getSequence() {
		return sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public boolean isIgnore() {
		return ignore;
	}

	public void setIgnore(boolean ignore) {
		this.ignore = ignore;
	}

	public Map<String, Object> getProperties() {
//		if (dict == null) {
//			properties = new LinkedHashMap<>(); // ArrayList<Entry<String, Object>>();
//		} else {
//			properties = dict;
//		}
		return properties;
	}

}
