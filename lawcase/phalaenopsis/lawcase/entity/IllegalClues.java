package phalaenopsis.lawcase.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;


/**
 * 违法线索登记
 * 
 * @author dongdongt
 *
 */
public class IllegalClues {
	@JsonProperty("ID")
	public String id;
	@JsonProperty("CaseID")
	public String caseid;// 案件ID
	@JsonProperty("CluesCode")
	public String cluesCode;// 违法线索编号
	@JsonProperty("CaseSource")
	public String caseSource;// 线索来源
	@JsonProperty("Person")
	public String person;// 联系人
	@JsonProperty("PhoneNum")
	public String phonenum;// 联系电话
	@JsonProperty("Address")
	public String address;// 联系地址
	@JsonProperty("District")
	public Integer district;// 县区
	@JsonProperty("IllegalAddress")
	public String illegaladdress;// 违法行为发生地详细地址
	@JsonProperty("CluesContent")
	public String cluescontent;// 线索内容
	@JsonProperty("Attnopinion")
	public String attnopinion;// 经办人员意见
	@JsonProperty("Attnsign")
	public String attnsign;// 经办人员签字
	@JsonProperty("AttnsignDate")
	public Date attnsignDate;// 经办人员签字日期
	@JsonProperty("Leaderopinion")
	public String leaderopinion;// 机构负责人意见
	@JsonProperty("Leadesign")
	public String leadesign;// 机构负责人签字
	@JsonProperty("LedersignDate")
	public Date ledersignDate;// 机构负责人签字日期
	public Integer count;// 辅助字段

	// TODO 20170606
	public Date stringDate;// 日期辅助

	public List<String> acceptUsers;// 辅助受理人

	public Date getStringDate() {
		return stringDate;
	}

	public void setStringDate(Date stringDate) {
		this.stringDate = stringDate;
	}

	public List<String> getAcceptUsers() {
		return acceptUsers;
	}

	public void setAcceptUsers(List<String> acceptUsers) {
		this.acceptUsers = acceptUsers;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	/**
	 * 签字信息，供导出使用
	 */
	private List<Signature> Signatures;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCasei() {
		return caseid;
	}

	public void setCaseId(String caseid) {
		this.caseid = caseid;
	}

	public String getCluesCode() {
		return cluesCode;
	}

	public void setCluesCode(String cluesCode) {
		this.cluesCode = cluesCode;
	}

	public String getCaseSource() {
		return caseSource;
	}

	public void setCaseSource(String caseSource) {
		this.caseSource = caseSource;
	}

	public String getPerson() {
		return person;
	}

	public void setPerson(String person) {
		this.person = person;
	}

	public String getPhonenum() {
		return phonenum;
	}

	public void setPhonenum(String phonenum) {
		this.phonenum = phonenum;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getDistrict() {
		return district;
	}

	public void setDistrict(Integer district) {
		this.district = district;
	}

	public String getIllegaladdress() {
		return illegaladdress;
	}

	public void setIllegaladdress(String illegaladdress) {
		this.illegaladdress = illegaladdress;
	}

	public String getCluescontent() {
		return cluescontent;
	}

	public void setCluescontent(String cluescontent) {
		this.cluescontent = cluescontent;
	}

	public String getAttnopinion() {
		return attnopinion;
	}

	public void setAttnopinion(String attnopinion) {
		this.attnopinion = attnopinion;
	}

	public String getAttnsign() {
		return attnsign;
	}

	public void setAttnsign(String attnsign) {
		this.attnsign = attnsign;
	}

	public Date getAttnsignDate() {
		return attnsignDate;
	}

	public void setAttnsignDate(Date attnsignDate) {
		this.attnsignDate = attnsignDate;
	}

	public String getLeaderopinion() {
		return leaderopinion;
	}

	public void setLeaderopinion(String leaderopinion) {
		this.leaderopinion = leaderopinion;
	}

	public String getLeadesign() {
		return leadesign;
	}

	public void setLeadesign(String leadesign) {
		this.leadesign = leadesign;
	}

	public Date getLedersignDate() {
		return ledersignDate;
	}

	public void setLedersignDate(Date ledersignDate) {
		this.ledersignDate = ledersignDate;
	}

	public List<Signature> getSignatures() {
		return Signatures;
	}

	public void setSignatures(List<Signature> signatures) {
		Signatures = signatures;
	}

}
