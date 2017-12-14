package phalaenopsis.lawcase.entity;

import phalaenopsis.lawcase.entity.document.Document;

/**
 * 移送节点表单
 * 
 */
public class TransferDocument {
	/**
	 * 涉嫌犯罪案件移送书
	 * 
	 */
	private Document suspectedCriminal;
	/**
	 * 行政处罚建议书
	 * 
	 */
	private Document punishAdvice;
	public Document getSuspectedCriminal() {
		return suspectedCriminal;
	}
	public void setSuspectedCriminal(Document suspectedCriminal) {
		this.suspectedCriminal = suspectedCriminal;
	}
	public Document getPunishAdvice() {
		return punishAdvice;
	}
	public void setPunishAdvice(Document punishAdvice) {
		this.punishAdvice = punishAdvice;
	}
	
}