package phalaenopsis.illegalclue.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 违法线索字典
 * @author chunl
 *
 */
@SuppressWarnings("serial")
public class ClueDictionary  implements Serializable {
	
	/**
	 * Id主键
	 */
	private String id;
	
	/**
	 * 名称
	 */
	private String name;
	
	/**
	 * 父节点Id
	 */
	private String pId;
	
	/**
	 * 字典类型
	 */
	private String type;
	
	/**
	 * 排序字段
	 */
	private Integer rank;
	
	/**
	 * 子级
	 */
	private List<ClueDictionary> childDictionaries;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getpId() {
		return pId;
	}

	public void setpId(String pId) {
		this.pId = pId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}
	
	public List<ClueDictionary> getChildDictionary() {
		return childDictionaries;
	}

	public void setChildDictionary(List<ClueDictionary> childDictionary) {
		this.childDictionaries = childDictionary;
	}

	public ClueDictionary() {
		super();
	}

	public ClueDictionary(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	
}
