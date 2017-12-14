package phalaenopsis.lawcase.entity;

/**
 * 接受前端参数
 * @author yuhangc
 *
 */
public class RequestItem {
	
	/**
	 * 案件信息
	 */
	private CaseBaseInfo obj;
	/**
	 * 违法案件
	 */
	private IllegalClues clue;
	/**
	 * 转违法案件关联
	 */
	private DeliverLawCaseRelation rel;
	
	public CaseBaseInfo getObj() {
		return obj;
	}
	public void setObj(CaseBaseInfo obj) {
		this.obj = obj;
	}
	public IllegalClues getClue() {
		return clue;
	}
	public void setClue(IllegalClues clue) {
		this.clue = clue;
	}
	public DeliverLawCaseRelation getRel() {
		return rel;
	}
	public void setRel(DeliverLawCaseRelation rel) {
		this.rel = rel;
	}

}
