package phalaenopsis.lawcase.entity;

import java.util.Date;
import java.util.List;

import phalaenopsis.common.entity.Attachment.Attachment;
/**
 * 历史案件表
 * @author dongdongt
 *
 */
public class HistoryCase {
	public String id;//主键
	public String landuser;//违法用地单位
	public Integer  district;//项目地址：区域
	public String street;//项目地址：街道
	public String detailaddress;//项目地址：详细地址
	public String landtype;//违法用地类型
	public String punishdecisionnum;//行政处罚决定书编号
	public Integer punishacreage;//处罚面积（亩）
	public Date buildtime;//立案查处时间
	public Date givepunishtime;//下达处罚时间
	public Date endcasetime;//结案时间
	public String undertkedepartment;//承办部门
	public String shape;//坐标
	public String listedsupervise;//是否挂牌督办案件
	 /// <summary>
    /// 行政处罚决定书编号
    /// </summary>
    public String punishDecisionNum;
    /// <summary>
    /// 处罚面积（亩）
    /// </summary>
    public double punishAcreage;
    /// <summary>
    /// 立案查处时间（准确到年月）
    /// </summary>
    public Date buildTime;
    /// <summary>
    /// 下达处罚时间（准确到年月）
    /// </summary>
    public Date givePunishTime;

    /// <summary>
    /// 结案时间（准确到年月）
    /// </summary>
    public Date endCaseTime;

    /// <summary>
    /// 承办部门（支队一大队——四大队、各区局）
    /// </summary>
    public String undertakeDepartment;
    /// <summary>
    /// HistoryCase视图信息
    /// </summary>
    public VHistoryCase Ref;

    /// <summary>
    /// 附件列表
    /// </summary>
    public List<Attachment> Attachments;
	public String getPunishDecisionNum() {
		return punishDecisionNum;
	}
	public void setPunishDecisionNum(String punishDecisionNum) {
		this.punishDecisionNum = punishDecisionNum;
	}
	public double getPunishAcreage() {
		return punishAcreage;
	}
	public void setPunishAcreage(double punishAcreage) {
		this.punishAcreage = punishAcreage;
	}
	public Date getBuildTime() {
		return buildTime;
	}
	public void setBuildTime(Date buildTime) {
		this.buildTime = buildTime;
	}
	public Date getGivePunishTime() {
		return givePunishTime;
	}
	public void setGivePunishTime(Date givePunishTime) {
		this.givePunishTime = givePunishTime;
	}
	public Date getEndCaseTime() {
		return endCaseTime;
	}
	public void setEndCaseTime(Date endCaseTime) {
		this.endCaseTime = endCaseTime;
	}
	public String getUndertakeDepartment() {
		return undertakeDepartment;
	}
	public void setUndertakeDepartment(String undertakeDepartment) {
		this.undertakeDepartment = undertakeDepartment;
	}
	public VHistoryCase getRef() {
		return Ref;
	}
	public void setRef(VHistoryCase ref) {
		Ref = ref;
	}
	public List<Attachment> getAttachments() {
		return Attachments;
	}
	public void setAttachments(List<Attachment> attachments) {
		Attachments = attachments;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLanduser() {
		return landuser;
	}
	public void setLanduser(String landuser) {
		this.landuser = landuser;
	}
	public Integer getDistrict() {
		return district;
	}
	public void setDistrict(Integer district) {
		this.district = district;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getDetailaddress() {
		return detailaddress;
	}
	public void setDetailaddress(String detailaddress) {
		this.detailaddress = detailaddress;
	}
	public String getLandtype() {
		return landtype;
	}
	public void setLandtype(String landtype) {
		this.landtype = landtype;
	}
	public String getPunishdecisionnum() {
		return punishdecisionnum;
	}
	public void setPunishdecisionnum(String punishdecisionnum) {
		this.punishdecisionnum = punishdecisionnum;
	}
	public Integer getPunishacreage() {
		return punishacreage;
	}
	public void setPunishacreage(Integer punishacreage) {
		this.punishacreage = punishacreage;
	}
	public Date getBuildtime() {
		return buildtime;
	}
	public void setBuildtime(Date buildtime) {
		this.buildtime = buildtime;
	}
	public Date getGivepunishtime() {
		return givepunishtime;
	}
	public void setGivepunishtime(Date givepunishtime) {
		this.givepunishtime = givepunishtime;
	}
	public Date getEndcasetime() {
		return endcasetime;
	}
	public void setEndcasetime(Date endcasetime) {
		this.endcasetime = endcasetime;
	}
	public String getUndertkedepartment() {
		return undertkedepartment;
	}
	public void setUndertkedepartment(String undertkedepartment) {
		this.undertkedepartment = undertkedepartment;
	}
	public String getShape() {
		return shape;
	}
	public void setShape(String shape) {
		this.shape = shape;
	}
	public String getListedsupervise() {
		return listedsupervise;
	}
	public void setListedsupervise(String listedsupervise) {
		this.listedsupervise = listedsupervise;
	}
	
}
