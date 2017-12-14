package phalaenopsis.illegalclue.entity;

import java.io.Serializable;


/**
 * 违法线索统计结果表
 * */
public class IllegalClueStatistic implements Serializable  {

	
	private static final long serialVersionUID = 9051931172273945817L;
	
	//总受理量
	private Long totalAcceptClueNum;
	//有效违法线索总数
	private Long illegalClueNum;
	//举报土地线索数
	private Long reportLandNum;
	//违法转让数量
	private Long illegalTransferNum;
	//破坏农用地数量
	private Long destructionFarmlandNum;
	//违法占地
	private Long illegalCoversLandNum;
	//违法批地
	private Long illegalLandNum;

	//举报矿产线索数
	private Long reportMineralNum;
	//违法勘查矿产资源
	private Long illegalExplorationMineralsNum;
	//违法开采矿产资源
	private Long illegalminingMineralNum;
	//违法转让矿业
	private Long illegalTransferMiningNum;
	//违法审批发放勘查或者采矿许可证
	private Long illegalApprovalNum;

	//其他线索数
	private Long otherNum;
	//线索查询
	private Long cluesQueryNum;
	//土地出让
	private Long landTransferNum;
	//土地闲置
	private Long landIdleNum;
	//不动产登记
	private Long realEstateEegistraterNum;
	//土地权属
	private Long landOwnershipNum;
	//征地纠纷
	private Long landDisputesNum;
	//矿业权纠纷
	private Long miningDisputesNum;
	//涉访涉诉
	private Long wadeVisitNum;
	//信息政务
	private Long infoGovernmentAffairsNum;
	//其他部门业务
	private Long otherDepartbusinessNum;

	//已办结数量
	private Long transferredNum;
	//其中属实线索
	private Long trueClueNum;
	//其中已立案线索数量
	private Long putOnRecordNum;
	
	
	//开始时间
	private Long startTime;
	
	//结束时间
	private Long endTime;

	public Long getTotalAcceptClueNum() {
		return totalAcceptClueNum;
	}

	public void setTotalAcceptClueNum(Long totalAcceptClueNum) {
		this.totalAcceptClueNum = totalAcceptClueNum;
	}

	public Long getIllegalClueNum() {
		return illegalClueNum;
	}

	public void setIllegalClueNum(Long illegalClueNum) {
		this.illegalClueNum = illegalClueNum;
	}

	public Long getReportLandNum() {
		return reportLandNum;
	}

	public void setReportLandNum(Long reportLandNum) {
		this.reportLandNum = reportLandNum;
	}

	public Long getIllegalTransferNum() {
		return illegalTransferNum;
	}

	public void setIllegalTransferNum(Long illegalTransferNum) {
		this.illegalTransferNum = illegalTransferNum;
	}

	public Long getDestructionFarmlandNum() {
		return destructionFarmlandNum;
	}

	public void setDestructionFarmlandNum(Long destructionFarmlandNum) {
		this.destructionFarmlandNum = destructionFarmlandNum;
	}

	public Long getIllegalCoversLandNum() {
		return illegalCoversLandNum;
	}

	public void setIllegalCoversLandNum(Long illegalCoversLandNum) {
		this.illegalCoversLandNum = illegalCoversLandNum;
	}

	public Long getIllegalLandNum() {
		return illegalLandNum;
	}

	public void setIllegalLandNum(Long illegalLandNum) {
		this.illegalLandNum = illegalLandNum;
	}

	public Long getReportMineralNum() {
		return reportMineralNum;
	}

	public void setReportMineralNum(Long reportMineralNum) {
		this.reportMineralNum = reportMineralNum;
	}

	public Long getIllegalExplorationMineralsNum() {
		return illegalExplorationMineralsNum;
	}

	public void setIllegalExplorationMineralsNum(Long illegalExplorationMineralsNum) {
		this.illegalExplorationMineralsNum = illegalExplorationMineralsNum;
	}

	public Long getIllegalminingMineralNum() {
		return illegalminingMineralNum;
	}

	public void setIllegalminingMineralNum(Long illegalminingMineralNum) {
		this.illegalminingMineralNum = illegalminingMineralNum;
	}

	public Long getIllegalTransferMiningNum() {
		return illegalTransferMiningNum;
	}

	public void setIllegalTransferMiningNum(Long illegalTransferMiningNum) {
		this.illegalTransferMiningNum = illegalTransferMiningNum;
	}

	public Long getIllegalApprovalNum() {
		return illegalApprovalNum;
	}

	public void setIllegalApprovalNum(Long illegalApprovalNum) {
		this.illegalApprovalNum = illegalApprovalNum;
	}

	public Long getOtherNum() {
		return otherNum;
	}

	public void setOtherNum(Long otherNum) {
		this.otherNum = otherNum;
	}

	public Long getCluesQueryNum() {
		return cluesQueryNum;
	}

	public void setCluesQueryNum(Long cluesQueryNum) {
		this.cluesQueryNum = cluesQueryNum;
	}

	public Long getLandTransferNum() {
		return landTransferNum;
	}

	public void setLandTransferNum(Long landTransferNum) {
		this.landTransferNum = landTransferNum;
	}

	public Long getLandIdleNum() {
		return landIdleNum;
	}

	public void setLandIdleNum(Long landIdleNum) {
		this.landIdleNum = landIdleNum;
	}

	public Long getRealEstateEegistraterNum() {
		return realEstateEegistraterNum;
	}

	public void setRealEstateEegistraterNum(Long realEstateEegistraterNum) {
		this.realEstateEegistraterNum = realEstateEegistraterNum;
	}

	public Long getLandOwnershipNum() {
		return landOwnershipNum;
	}

	public void setLandOwnershipNum(Long landOwnershipNum) {
		this.landOwnershipNum = landOwnershipNum;
	}

	public Long getLandDisputesNum() {
		return landDisputesNum;
	}

	public void setLandDisputesNum(Long landDisputesNum) {
		this.landDisputesNum = landDisputesNum;
	}

	public Long getMiningDisputesNum() {
		return miningDisputesNum;
	}

	public void setMiningDisputesNum(Long miningDisputesNum) {
		this.miningDisputesNum = miningDisputesNum;
	}

	public Long getWadeVisitNum() {
		return wadeVisitNum;
	}

	public void setWadeVisitNum(Long wadeVisitNum) {
		this.wadeVisitNum = wadeVisitNum;
	}

	public Long getInfoGovernmentAffairsNum() {
		return infoGovernmentAffairsNum;
	}

	public void setInfoGovernmentAffairsNum(Long infoGovernmentAffairsNum) {
		this.infoGovernmentAffairsNum = infoGovernmentAffairsNum;
	}

	public Long getOtherDepartbusinessNum() {
		return otherDepartbusinessNum;
	}

	public void setOtherDepartbusinessNum(Long otherDepartbusinessNum) {
		this.otherDepartbusinessNum = otherDepartbusinessNum;
	}

	public Long getTransferredNum() {
		return transferredNum;
	}

	public void setTransferredNum(Long transferredNum) {
		this.transferredNum = transferredNum;
	}

	public Long getTrueClueNum() {
		return trueClueNum;
	}

	public void setTrueClueNum(Long trueClueNum) {
		this.trueClueNum = trueClueNum;
	}

	public Long getPutOnRecordNum() {
		return putOnRecordNum;
	}

	public void setPutOnRecordNum(Long putOnRecordNum) {
		this.putOnRecordNum = putOnRecordNum;
	}

	public Long getStartTime() {
		return startTime;
	}

	public void setStartTime(Long startTime) {
		this.startTime = startTime;
	}

	public Long getEndTime() {
		return endTime;
	}

	public void setEndTime(Long endTime) {
		this.endTime = endTime;
	}
	

}
