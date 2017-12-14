package phalaenopsis.lawcase.entity;

import phalaenopsis.lawcase.entity.document.Document;

/**
 * 告知节点表单
 * 
 */
public class NotifyDocument {
	/**
	 * 行政处罚告知书
	 * 
	 */
	private Document administrativePenaltyNotice;
	/**
	 * 听证告知书
	 * 
	 */
	private Document  hearingNotice;
	public Document getAdministrativePenaltyNotice() {
		return administrativePenaltyNotice;
	}
	public void setAdministrativePenaltyNotice(Document administrativePenaltyNotice) {
		this.administrativePenaltyNotice = administrativePenaltyNotice;
	}
	public Document getHearingNotice() {
		return hearingNotice;
	}
	public void setHearingNotice(Document hearingNotice) {
		this.hearingNotice = hearingNotice;
	}
	
}