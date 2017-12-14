package phalaenopsis.legalstatute.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 法律法规实体
 * @author jund
 * 2017年11月8日
 */
public class LegalStatute  implements Serializable {
	
	private static final long serialVersionUID = 1L;
	/**
	 *主键id 
	 */
	private Long id;
	/**
	 * 所属法律id
	 */
	private Long lawId;
	/**
	 * 所属章id
	 */
	private Integer chapter;
	/**
	 * 所属章名
	 */
	private String chapterName;
	/**
	 * 所属章内容
	 */
	private String chapterContent;
	/**
	 * 所属法律名称
	 */
	private String lawName;
	/**
	 * 所属节id
	 */
	private Integer section;
	/**
	 * 所属节名称
	 */
	private Integer sectionName;
	/**
	 * 所属节内容
	 */
	private Integer sectionContent;
	/**
	 * 所属条id
	 */
	private Integer entry;
	/**
	 * 所属条名称
	 */
	private Integer entryName;
	/**
	 * 法律法规内容
	 */
	private String content;
	/**
	 * 发布部门id
	 */
	private String  organizationId;
	/**
	 * 发布部门name
	 */
	private String organizationName;
	/**
	 * 发布文字号
	 */
	private String issueNumber;
	/**
	 * 发布日期
	 */
	private String issueTime;
	/**
	 * 实施日期
	 */
	private String enforceTime;
	/**
	 * 时效性
	 */
	private String effect;
	/**
	 * 效力级别
	 */
	private String lawLevell;
	/**
	 * 法规类别id
	 */
	private Integer lawtypeId;
	/**
	 * 法规类别name
	 */
	private String lawtypeName;
	/**
	 * 父级id
	 */
	private String parentId;
	/**
	 * 父级name
	 */
	private String parentName;
	/**
	 * 父级内容
	 */
	private String parentContent;
	/**
	 * 节点
	 */
	private Integer node;
	/**
	 * 标识
	 * @return
	 */
	private String remark;
	/**
	 * 排序
	 */
	private Integer sort;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getLawId() {
		return lawId;
	}

	public void setLawId(Long lawId) {
		this.lawId = lawId;
	}

	public Integer getChapter() {
		return chapter;
	}

	public void setChapter(Integer chapter) {
		this.chapter = chapter;
	}

	public Integer getSection() {
		return section;
	}

	public void setSection(Integer section) {
		this.section = section;
	}

	public Integer getEntry() {
		return entry;
	}

	public void setEntry(Integer entry) {
		this.entry = entry;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}
	
	public String getLawName() {
		return lawName;
	}

	public void setLawName(String lawName) {
		this.lawName = lawName;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public String getIssueNumber() {
		return issueNumber;
	}

	public void setIssueNumber(String issueNumber) {
		this.issueNumber = issueNumber;
	}

	public String getIssueTime() {
		return issueTime;
	}

	public void setIssueTime(String issueTime) {
		this.issueTime = issueTime;
	}

	public String getEnforceTime() {
		return enforceTime;
	}

	public void setEnforceTime(String enforceTime) {
		this.enforceTime = enforceTime;
	}

	
	public String getEffect() {
		return effect;
	}

	public void setEffect(String effect) {
		this.effect = effect;
	}

	public String getLawLevell() {
		return lawLevell;
	}

	public void setLawLevell(String lawLevell) {
		this.lawLevell = lawLevell;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getParentContent() {
		return parentContent;
	}

	public void setParentContent(String parentContent) {
		this.parentContent = parentContent;
	}

	public Integer getLawtypeId() {
		return lawtypeId;
	}

	public void setLawtypeId(Integer lawtypeId) {
		this.lawtypeId = lawtypeId;
	}

	public String getLawtypeName() {
		return lawtypeName;
	}

	public void setLawtypeName(String lawtypeName) {
		this.lawtypeName = lawtypeName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getChapterName() {
		return chapterName;
	}

	public void setChapterName(String chapterName) {
		this.chapterName = chapterName;
	}

	public Integer getSectionName() {
		return sectionName;
	}

	public void setSectionName(Integer sectionName) {
		this.sectionName = sectionName;
	}

	
	public Integer getNode() {
		return node;
	}

	public void setNode(Integer node) {
		this.node = node;
	}

	public String getChapterContent() {
		return chapterContent;
	}

	public void setChapterContent(String chapterContent) {
		this.chapterContent = chapterContent;
	}

	public Integer getSectionContent() {
		return sectionContent;
	}

	public void setSectionContent(Integer sectionContent) {
		this.sectionContent = sectionContent;
	}

	public Integer getEntryName() {
		return entryName;
	}

	public void setEntryName(Integer entryName) {
		this.entryName = entryName;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
}
