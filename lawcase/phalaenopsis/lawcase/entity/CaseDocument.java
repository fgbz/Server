package phalaenopsis.lawcase.entity;



import com.fasterxml.jackson.annotation.JsonIgnore;
import phalaenopsis.common.method.BinarySerializer;
import phalaenopsis.lawcase.entity.document.Document;
import phalaenopsis.lawcase.entity.document.DocumentType;
import phalaenopsis.lawcase.utility.*;


/** 
 案件文档：用于存储序列化后的数据（用于存储数据库）
 
*/
public class CaseDocument
{
	/** 
	 将Document中的信息序列化
	 @param doc
	 @return 
	*/
	public static  CaseDocument GetCaseDocument(Document doc)
	{
		if (doc == null)
		{
			return null;
		}
		CaseDocument tempVar = new CaseDocument();
		tempVar.setId(doc.getId());
		tempVar.setCaseID(doc.getCaseID());
		tempVar.setType(doc.getType());
		tempVar.setInfo(BinarySerializer.convertToBytes(doc.getInfo()));
		//tempVar.setInfo(BinaryJsonSerializer.Serialize(doc.getInfo()));
		return tempVar;
	}
	/** 
	 主键
	*/
	private String id;
	/** 
	 案件ID
	*/
	private String caseID;
	/** 
	 文档类型
	*/
	private DocumentType type;
	/** 
	 序列化后的文档流
	*/
	private byte[] info;
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
	public byte[] getInfo() {
		return info;
	}
	public void setInfo(byte[] info) {
		this.info = info;
	}
	/**
	 * 用于保存或更新的字段。跟实际业务无关
	 */
	@JsonIgnore
	private int count;

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
}