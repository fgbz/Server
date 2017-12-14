package phalaenopsis.lawcase.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import phalaenopsis.lawcase.entity.document.Document;

/**
 * 处罚决定节点表单
 * 
 */
public class PunishDocument {
	/**
	 * 行政处罚决定书
	 * 
	 */
	@JsonProperty("PenaltyDecision")
	private Document penaltyDecision;

	public Document getPenaltyDecision() {
		return penaltyDecision;
	}

	public void setPenaltyDecision(Document penaltyDecision) {
		this.penaltyDecision = penaltyDecision;
	}
	
}