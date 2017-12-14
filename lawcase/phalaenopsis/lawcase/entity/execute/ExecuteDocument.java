package phalaenopsis.lawcase.entity.execute;


import com.fasterxml.jackson.annotation.JsonProperty;
import phalaenopsis.lawcase.entity.document.Document;

/**
 * 执行节点表单
 * 
 */
public class ExecuteDocument {
	/**
	 * 非法财物移交书
	 * 
	 */
	private Document  illegalityPropertyTransferBook;
	/**
	 * 履行行政处罚决定催告函
	 * 
	 */
	private Document exigentNotice;


	/**
	 * 强制执行申请书
	 * 
	 */
	private Document enforceApplicationForm;
	/**
	 * 土地违法违规处理移送书
	 * 
	 */
	private Document handleTransferBook;

	/**
	 * 行政处罚决定执行记录
	 * 
	 */
	@JsonProperty("PunishExecutionRecord")
	private Document punishExecutionRecord;

	public Document getIllegalityPropertyTransferBook() {
		return illegalityPropertyTransferBook;
	}

	public void setIllegalityPropertyTransferBook(Document illegalityPropertyTransferBook) {
		this.illegalityPropertyTransferBook = illegalityPropertyTransferBook;
	}

	public Document getExigentNotice() {
		return exigentNotice;
	}

	public void setExigentNotice(Document exigentNotice) {
		this.exigentNotice = exigentNotice;
	}

	public Document getEnforceApplicationForm() {
		return enforceApplicationForm;
	}

	public void setEnforceApplicationForm(Document enforceApplicationForm) {
		this.enforceApplicationForm = enforceApplicationForm;
	}

	public Document getHandleTransferBook() {
		return handleTransferBook;
	}

	public void setHandleTransferBook(Document handleTransferBook) {
		this.handleTransferBook = handleTransferBook;
	}

	public Document getPunishExecutionRecord() {
		return punishExecutionRecord;
	}

	public void setPunishExecutionRecord(Document punishExecutionRecord) {
		this.punishExecutionRecord = punishExecutionRecord;
	}

	
	
}