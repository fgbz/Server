package phalaenopsis.lawcaseevaluation.entity.caseDetail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import phalaenopsis.common.entity.Condition;
import phalaenopsis.lawcaseevaluation.entity.Qualified;

/**
 * 评查标准
 * 
 * @author chunl
 *
 */
public class SurveryStandard {

	/**
	 * 编号-主键
	 */
	private long id;

	/**
	 * 评分子项外键
	 */
	private int pcbzZxId;

	/**
	 * 名称
	 */
	private String name;

	/**
	 * 总分
	 */
	private float totalScore;

	/**
	 * 扣分间隔(如每次扣0.5)
	 */
	private float kfjg;

	/**
	 * 0 扣分项，1 加分项，2 不合格
	 */
	private int type;

	/**
	 * 初审分数
	 */
	private float firstScore;

	/**
	 * 复审分数
	 */
	private float reviewScore;

	/**
	 * 原因
	 */
	private String reason;
	/**
	 * 下拉列表
	 */
	private List<Float> optionList;
	/**
	 * 不合格列表
	 */
	private List<Qualified> unqualifiedList;
	
	/**
	 * 对应CE_SCORE表的主键Id
	 */
	private long scoreId;

	public List<Qualified> getUnqualifiedList() {
		if (unqualifiedList == null && totalScore == 0) {
			unqualifiedList = new ArrayList<Qualified>();
			Qualified qualified = new Qualified();
			qualified.setKey(0);
			qualified.setValue("合格");
			Qualified qualified1 = new Qualified();
			qualified1.setKey(1);
			qualified1.setValue("不合格");
			unqualifiedList.add(qualified);
			unqualifiedList.add(qualified1);
		}
		return unqualifiedList;
	}

	public void setUnqualifiedList(List<Qualified> unqualifiedList) {
		this.unqualifiedList = unqualifiedList;
	}

	public List<Float> getOptionList() {
		optionList = new ArrayList<Float>();
		Float po = (float) 0;
		try {
			if (totalScore != 0) {
				while (po <= totalScore) {
					optionList.add(po);
					po += kfjg;
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return optionList;
	}

	public void setOptionList(List<Float> optionList) {
		this.optionList = optionList;
	}

	public long getScoreId() {
		return scoreId;
	}

	public void setScoreId(long scoreId) {
		this.scoreId = scoreId;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public float getFirstScore() {
		return firstScore;
	}

	public void setFirstScore(float firstScore) {
		this.firstScore = firstScore;
	}

	public float getReviewScore() {
		return reviewScore;
	}

	public void setReviewScore(float reviewScore) {
		this.reviewScore = reviewScore;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getPcbzZxId() {
		return pcbzZxId;
	}

	public void setPcbzZxId(int pcbzZxId) {
		this.pcbzZxId = pcbzZxId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(float totalScore) {
		this.totalScore = totalScore;
	}

	public float getKfjg() {
		return kfjg;
	}

	public void setKfjg(float kfjg) {
		this.kfjg = kfjg;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
}
