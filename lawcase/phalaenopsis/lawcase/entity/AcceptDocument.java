package phalaenopsis.lawcase.entity;

import java.util.ArrayList;

import phalaenopsis.lawcase.entity.document.Document;

/**
 * 受理节点表单
 * 
 */
public class AcceptDocument {
	/**
	 * 责令停止违法行为通知书（简易停建书）
	 * 
	 */
	private Document stopIrregularitiesNotice;
	/**
	 * 抄告单
	 * 
	 */
	private Document copyOrder;
	public Document getStopIrregularitiesNotice() {
		return stopIrregularitiesNotice;
	}
	public void setStopIrregularitiesNotice(Document stopIrregularitiesNotice) {
		this.stopIrregularitiesNotice = stopIrregularitiesNotice;
	}
	public Document getCopyOrder() {
		return copyOrder;
	}
	public void setCopyOrder(Document copyOrder) {
		this.copyOrder = copyOrder;
	}
	
}