package phalaenopsis.illegalclue.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 线索初判实体
 * @author chunl
 *
 */
public class ClueJudge {
	
	/**
	 * 主键
	 */
	private String id;
	
	/**
	 * 外键，与线索表(CL_CLUE)主键关联
	 */
	private String clueId;
	
	/**
	 * 初判类型
	 */
	private Integer judgeType;
	
	/**
	 * 建议处理方式
	 */
	private Integer handleType;
	
	/**
	 * 是否重大线索
	 */
	private Integer majorClue;
	
	/**
	 * 办理建议
	 */
	private String handleOpinion;
	
	/**
	 * 初判备注
	 */
	private String handleRemark;
	
	/**
	 * 拟告知举报人内容
	 */
	private String intendContent;
	
	/**
	 * 初判人
	 */
	private String judger;
	
	/**
	 * 初判时间
	 */
//	@JsonSerialize(using=DateJsonSimpleSerializer.class)
//	@JsonDeserialize(using=DateJsonDeserializer.class)
	private Long judgeTime;
	
	/**
	 * 不予受理类型
	 */
	private String  unHandleType;
	
	/**
	 * 转办、交办单位
	 */
	private String unit;



	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getClueId() {
		return clueId;
	}

	public void setClueId(String clueId) {
		this.clueId = clueId;
	}

	public Integer getJudgeType() {
		return judgeType;
	}

	public void setJudgeType(Integer judgeType) {
		this.judgeType = judgeType;
	}

	public Integer getHandleType() {
		return handleType;
	}

	public void setHandleType(Integer handleType) {
		this.handleType = handleType;
	}

	public Integer getMajorClue() {
		return majorClue;
	}

	public void setMajorClue(Integer majorClue) {
		this.majorClue = majorClue;
	}

	public String getHandleOpinion() {
		return handleOpinion;
	}

	public void setHandleOpinion(String handleOpinion) {
		this.handleOpinion = handleOpinion;
	}

	public String getHandleRemark() {
		return handleRemark;
	}

	public void setHandleRemark(String handleRemark) {
		this.handleRemark = handleRemark;
	}

	public String getIntendContent() {
		return intendContent;
	}

	public void setIntendContent(String intendContent) {
		this.intendContent = intendContent;
	}

	public String getJudger() {
		return judger;
	}

	public void setJudger(String judger) {
		this.judger = judger;
	}

	public Long getJudgeTime() {
		return judgeTime;
	}

	public void setJudgeTime(Long judgeTime) {
		this.judgeTime = judgeTime;
	}
	
	public String getUnHandleType() {
		return unHandleType;
	}

	public void setUnHandleType(String unHandleType) {
		this.unHandleType = unHandleType;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
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
