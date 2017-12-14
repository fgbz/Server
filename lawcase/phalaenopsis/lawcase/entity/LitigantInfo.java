package phalaenopsis.lawcase.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 当事人信息
 * 
 */
public class LitigantInfo {

	/**
	 * 主键
	 */
	@JsonProperty("ID")
	private String id;

	/**
	 * 案件ID
	 */
	@JsonProperty("CaseID")
	private String caseID;

	/**
	 * 当事人类型
	 */
	@JsonProperty("Type")
	private Integer type;
	/**
	 * 版本号
	 */
	@JsonProperty("Version")
	private int version;

	/**
	 * 序号（客户端唯一编号）
	 */
	@JsonProperty("Num")
	private int num;

	/**
	 * 姓名
	 */
	@JsonProperty("PersonalName")
	private String personalName;

	/**
	 * 性别
	 */
	@JsonProperty("PersonalSex")
	private String personalSex;

	/**
	 * 年龄
	 */
	@JsonProperty("PersonalAge")
	private int personalAge;

	/**
	 * 身份证号
	 */
	@JsonProperty("PersonalIDCard")
	private String personalIDCard;

	/**
	 * 电话
	 */
	@JsonProperty("PersonalPhone")
	private String personalPhone;

	/**
	 * 政治面貌
	 */
	@JsonProperty("PersonalPoliticalStatus")
	private String personalPoliticalStatus;

	/**
	 * 县级以上人民代表
	 */
	@JsonProperty("PersonalCongressional")
	private String personalCongressional;

	/**
	 * 是否委托代理人
	 */
	@JsonProperty("PersonalIsAgent")
	private int personalIsAgent;

	/**
	 * 所属党组织
	 */
	@JsonProperty("PersonalPartyOrganization")
	private String personalPartyOrganization;

	/**
	 * 单位
	 */
	@JsonProperty("PersonalUnit")
	private String personalUnit;

	/**
	 * 职务
	 */
	@JsonProperty("PersonalDuty")
	private String personalDuty;

	/**
	 * 地址
	 */
	@JsonProperty("PersonalAddress")
	private String personalAddress;

	/**
	 * 通讯地址
	 */
	@JsonProperty("PersonalMailAddress")
	private String personalMailAddress;

	/**
	 * 邮政编码
	 */
	@JsonProperty("PersonalZipCode")
	private String personalZipCode;

	/**
	 * 传真
	 */
	@JsonProperty("PersonalFax")
	private String personalFax;

	/**
	 * 电子邮箱
	 */
	@JsonProperty("PersonalEmail")
	private String personalEmail;

	/**
	 * 手机
	 */
	@JsonProperty("PersonalMobile")
	private String personalMobile;

	/**
	 * 名称
	 */
	@JsonProperty("UnitName")
	private String unitName;

	/**
	 * 地址
	 */
	@JsonProperty("UnitAddress")
	private String unitAddress;

	/**
	 * 邮政编码
	 */
	@JsonProperty("UnitZipCode")
	private String unitZipCode;

	/**
	 * 电话
	 */
	@JsonProperty("UnitPhone")
	private String unitPhone;

	/**
	 * 营业执照
	 */
	@JsonProperty("UnitBusinessLicense")
	private String unitBusinessLicense;

	/**
	 * 组织机构代码证
	 */
	private String unitOrganizationCode;

	/**
	 * 姓名
	 */
	private String legalPersonName;

	/**
	 * 性别
	 */
	private String legalPersonSex;

	/**
	 * 年龄
	 */

	private int legalPersonAge;

	/**
	 * 身份证号
	 */
	private String legalPersonIDCard;

	/**
	 * 电话
	 */
	private String legalPersonPhone;

	/**
	 * 政治面貌
	 */
	private String legalPersonPoliticalStatus;

	/**
	 * 县级以上人民代表
	 */
	private String legalPersonCongressional;

	/**
	 * 单位
	 */
	private String legalPersonUnit;

	/**
	 * 职务
	 */
	private String legalPersonDuty;

	/**
	 * 住址
	 */
	private String legalPersonAddress;

	/**
	 * 通讯地址
	 */
	private String legalPersonMailAddress;

	/**
	 * 邮政编码
	 */
	private String legalPersonZipCode;

	/**
	 * 传真
	 */
	private String legalPersonFax;

	/**
	 * 电子邮箱
	 */
	private String legalPersonEmail;

	/**
	 * 手机
	 */
	private String legalPersonMobile;

	/**
	 * 姓名
	 */
	private String ContactPersonName;

	/**
	 * 性别
	 */
	private String contactPersonSex;

	/**
	 * 年龄
	 */
	private int contactPersonAge;

	/**
	 * 身份证号
	 */
	private String contactPersonIDCard;

	/**
	 * 电话
	 */
	private String contactPersonPhone;

	/**
	 * 单位
	 */
	private String contactPersonUnit;

	/**
	 * 职务
	 */
	private String contactPersonDuty;

	/**
	 * 住址
	 */
	private String contactPersonAddress;

	/**
	 * 通讯地址
	 */
	private String contactPersonMailAddress;

	/**
	 * 邮政编码
	 */
	private String contactPersonZipCode;

	/**
	 * 传真
	 */
	private String contactPersonFax;

	/**
	 * 电子邮箱
	 */
	private String contactPersonEmail;

	/**
	 * 手机
	 */
	private String contactPersonMobile;

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

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getPersonalName() {
		return personalName;
	}

	public void setPersonalName(String personalName) {
		this.personalName = personalName;
	}

	public String getPersonalSex() {
		return personalSex;
	}

	public void setPersonalSex(String personalSex) {
		this.personalSex = personalSex;
	}

	public int getPersonalAge() {
		return personalAge;
	}

	public void setPersonalAge(int personalAge) {
		this.personalAge = personalAge;
	}

	public String getPersonalIDCard() {
		return personalIDCard;
	}

	public void setPersonalIDCard(String personalIDCard) {
		this.personalIDCard = personalIDCard;
	}

	public String getPersonalPhone() {
		return personalPhone;
	}

	public void setPersonalPhone(String personalPhone) {
		this.personalPhone = personalPhone;
	}

	public String getPersonalPoliticalStatus() {
		return personalPoliticalStatus;
	}

	public void setPersonalPoliticalStatus(String personalPoliticalStatus) {
		this.personalPoliticalStatus = personalPoliticalStatus;
	}

	public String getPersonalCongressional() {
		return personalCongressional;
	}

	public void setPersonalCongressional(String personalCongressional) {
		this.personalCongressional = personalCongressional;
	}

	public int getPersonalIsAgent() {
		return personalIsAgent;
	}

	public void setPersonalIsAgent(int personalIsAgent) {
		this.personalIsAgent = personalIsAgent;
	}

	public String getPersonalPartyOrganization() {
		return personalPartyOrganization;
	}

	public void setPersonalPartyOrganization(String personalPartyOrganization) {
		this.personalPartyOrganization = personalPartyOrganization;
	}

	public String getPersonalUnit() {
		return personalUnit;
	}

	public void setPersonalUnit(String personalUnit) {
		this.personalUnit = personalUnit;
	}

	public String getPersonalDuty() {
		return personalDuty;
	}

	public void setPersonalDuty(String personalDuty) {
		this.personalDuty = personalDuty;
	}

	public String getPersonalAddress() {
		return personalAddress;
	}

	public void setPersonalAddress(String personalAddress) {
		this.personalAddress = personalAddress;
	}

	public String getPersonalMailAddress() {
		return personalMailAddress;
	}

	public void setPersonalMailAddress(String personalMailAddress) {
		this.personalMailAddress = personalMailAddress;
	}

	public String getPersonalZipCode() {
		return personalZipCode;
	}

	public void setPersonalZipCode(String personalZipCode) {
		this.personalZipCode = personalZipCode;
	}

	public String getPersonalFax() {
		return personalFax;
	}

	public void setPersonalFax(String personalFax) {
		this.personalFax = personalFax;
	}

	public String getPersonalEmail() {
		return personalEmail;
	}

	public void setPersonalEmail(String personalEmail) {
		this.personalEmail = personalEmail;
	}

	public String getPersonalMobile() {
		return personalMobile;
	}

	public void setPersonalMobile(String personalMobile) {
		this.personalMobile = personalMobile;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getUnitAddress() {
		return unitAddress;
	}

	public void setUnitAddress(String unitAddress) {
		this.unitAddress = unitAddress;
	}

	public String getUnitZipCode() {
		return unitZipCode;
	}

	public void setUnitZipCode(String unitZipCode) {
		this.unitZipCode = unitZipCode;
	}

	public String getUnitPhone() {
		return unitPhone;
	}

	public void setUnitPhone(String unitPhone) {
		this.unitPhone = unitPhone;
	}

	public String getUnitBusinessLicense() {
		return unitBusinessLicense;
	}

	public void setUnitBusinessLicense(String unitBusinessLicense) {
		this.unitBusinessLicense = unitBusinessLicense;
	}

	public String getUnitOrganizationCode() {
		return unitOrganizationCode;
	}

	public void setUnitOrganizationCode(String unitOrganizationCode) {
		this.unitOrganizationCode = unitOrganizationCode;
	}

	public String getLegalPersonName() {
		return legalPersonName;
	}

	public void setLegalPersonName(String legalPersonName) {
		this.legalPersonName = legalPersonName;
	}

	public String getLegalPersonSex() {
		return legalPersonSex;
	}

	public void setLegalPersonSex(String legalPersonSex) {
		this.legalPersonSex = legalPersonSex;
	}

	public int getLegalPersonAge() {
		return legalPersonAge;
	}

	public void setLegalPersonAge(int legalPersonAge) {
		this.legalPersonAge = legalPersonAge;
	}

	public String getLegalPersonIDCard() {
		return legalPersonIDCard;
	}

	public void setLegalPersonIDCard(String legalPersonIDCard) {
		this.legalPersonIDCard = legalPersonIDCard;
	}

	public String getLegalPersonPhone() {
		return legalPersonPhone;
	}

	public void setLegalPersonPhone(String legalPersonPhone) {
		this.legalPersonPhone = legalPersonPhone;
	}

	public String getLegalPersonPoliticalStatus() {
		return legalPersonPoliticalStatus;
	}

	public void setLegalPersonPoliticalStatus(String legalPersonPoliticalStatus) {
		this.legalPersonPoliticalStatus = legalPersonPoliticalStatus;
	}

	public String getLegalPersonCongressional() {
		return legalPersonCongressional;
	}

	public void setLegalPersonCongressional(String legalPersonCongressional) {
		this.legalPersonCongressional = legalPersonCongressional;
	}

	public String getLegalPersonUnit() {
		return legalPersonUnit;
	}

	public void setLegalPersonUnit(String legalPersonUnit) {
		this.legalPersonUnit = legalPersonUnit;
	}

	public String getLegalPersonDuty() {
		return legalPersonDuty;
	}

	public void setLegalPersonDuty(String legalPersonDuty) {
		this.legalPersonDuty = legalPersonDuty;
	}

	public String getLegalPersonAddress() {
		return legalPersonAddress;
	}

	public void setLegalPersonAddress(String legalPersonAddress) {
		this.legalPersonAddress = legalPersonAddress;
	}

	public String getLegalPersonMailAddress() {
		return legalPersonMailAddress;
	}

	public void setLegalPersonMailAddress(String legalPersonMailAddress) {
		this.legalPersonMailAddress = legalPersonMailAddress;
	}

	public String getLegalPersonZipCode() {
		return legalPersonZipCode;
	}

	public void setLegalPersonZipCode(String legalPersonZipCode) {
		this.legalPersonZipCode = legalPersonZipCode;
	}

	public String getLegalPersonFax() {
		return legalPersonFax;
	}

	public void setLegalPersonFax(String legalPersonFax) {
		this.legalPersonFax = legalPersonFax;
	}

	public String getLegalPersonEmail() {
		return legalPersonEmail;
	}

	public void setLegalPersonEmail(String legalPersonEmail) {
		this.legalPersonEmail = legalPersonEmail;
	}

	public String getLegalPersonMobile() {
		return legalPersonMobile;
	}

	public void setLegalPersonMobile(String legalPersonMobile) {
		this.legalPersonMobile = legalPersonMobile;
	}

	public String getContactPersonName() {
		return ContactPersonName;
	}

	public void setContactPersonName(String contactPersonName) {
		ContactPersonName = contactPersonName;
	}

	public String getContactPersonSex() {
		return contactPersonSex;
	}

	public void setContactPersonSex(String contactPersonSex) {
		this.contactPersonSex = contactPersonSex;
	}

	public int getContactPersonAge() {
		return contactPersonAge;
	}

	public void setContactPersonAge(int contactPersonAge) {
		this.contactPersonAge = contactPersonAge;
	}

	public String getContactPersonIDCard() {
		return contactPersonIDCard;
	}

	public void setContactPersonIDCard(String contactPersonIDCard) {
		this.contactPersonIDCard = contactPersonIDCard;
	}

	public String getContactPersonPhone() {
		return contactPersonPhone;
	}

	public void setContactPersonPhone(String contactPersonPhone) {
		this.contactPersonPhone = contactPersonPhone;
	}

	public String getContactPersonUnit() {
		return contactPersonUnit;
	}

	public void setContactPersonUnit(String contactPersonUnit) {
		this.contactPersonUnit = contactPersonUnit;
	}

	public String getContactPersonDuty() {
		return contactPersonDuty;
	}

	public void setContactPersonDuty(String contactPersonDuty) {
		this.contactPersonDuty = contactPersonDuty;
	}

	public String getContactPersonAddress() {
		return contactPersonAddress;
	}

	public void setContactPersonAddress(String contactPersonAddress) {
		this.contactPersonAddress = contactPersonAddress;
	}

	public String getContactPersonMailAddress() {
		return contactPersonMailAddress;
	}

	public void setContactPersonMailAddress(String contactPersonMailAddress) {
		this.contactPersonMailAddress = contactPersonMailAddress;
	}

	public String getContactPersonZipCode() {
		return contactPersonZipCode;
	}

	public void setContactPersonZipCode(String contactPersonZipCode) {
		this.contactPersonZipCode = contactPersonZipCode;
	}

	public String getContactPersonFax() {
		return contactPersonFax;
	}

	public void setContactPersonFax(String contactPersonFax) {
		this.contactPersonFax = contactPersonFax;
	}

	public String getContactPersonEmail() {
		return contactPersonEmail;
	}

	public void setContactPersonEmail(String contactPersonEmail) {
		this.contactPersonEmail = contactPersonEmail;
	}

	public String getContactPersonMobile() {
		return contactPersonMobile;
	}

	public void setContactPersonMobile(String contactPersonMobile) {
		this.contactPersonMobile = contactPersonMobile;
	}

}