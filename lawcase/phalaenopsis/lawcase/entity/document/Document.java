package phalaenopsis.lawcase.entity.document;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.apache.commons.io.filefilter.FalseFileFilter;


import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONCreator;

import phalaenopsis.common.method.BinarySerializer;
import phalaenopsis.lawcase.entity.CaseDocument;
import phalaenopsis.lawcase.entity.serializer.DocumentTypeDeserializer;

/**
 * 案件文档：用于存储序列化前的数据
 * 
 */
public class Document {

	/**
	 * 将CaseDocument反序列化为Document&lt;T&gt;
	 * 
	 * @param doc
	 * @return
	 */
	public Document GetDocument(CaseDocument doc) {

		if (doc == null) {
			return null;
		}

		Document tempVar = new Document();
		tempVar.setId(doc.getId());
		tempVar.setCaseID(doc.getCaseID());
		tempVar.setType(doc.getType());
		JSONObject t = BinarySerializer.convertBytes(doc.getInfo());
		tempVar.setInfo(t);
		return tempVar;
	}

	/**
	 * 主键
	 */
	@JsonProperty("ID")
	private String id;

	/**
	 * 案件ID
	 */
	@JsonProperty("CaseID")
	private String caseID;

	/**
	 * 类型
	 */
	@JsonProperty("Type")
	@JsonDeserialize(using = DocumentTypeDeserializer.class)
	//@com.alibaba.fastjson.annotation.JSONField(e)
	//@com.alibaba.fastjson.annotation.JSONField(deserialize=false)
	private DocumentType type;

	/**
	 * 文档信息
	 */
	@JsonProperty("Info")
	private JSONObject info;

	public JSONObject getInfo() {
		return info;
	}

	public void setInfo(JSONObject info) {
		this.info = info;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCaseID() {
		return caseID;
	}

	public void setCaseID(String caseID) {
		this.caseID = caseID;
	}

	public DocumentType getType() {
		return type;
	}

	public void setType(DocumentType type) {
		this.type = type;
	}

}