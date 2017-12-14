package phalaenopsis.visitSystem.service;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import phalaenopsis.common.dao.IAttachmentDao;
import phalaenopsis.common.entity.OrganizationGrade;
import phalaenopsis.common.entity.User;
import phalaenopsis.common.method.Basis;
import phalaenopsis.common.method.Tools.UUID64;
import phalaenopsis.illegalclue.entity.ResultState;
import phalaenopsis.illegalclue.entity.ResultString;
import phalaenopsis.visitSystem.dao.LetterCaseMapper;
import phalaenopsis.visitSystem.dao.LetterCaseMapperPartial;
import phalaenopsis.visitSystem.entity.XfDeals;
import phalaenopsis.visitSystem.entity.XfRegister;
import phalaenopsis.visitSystem.entity.XfResult;

@Service
public class LetterCaseServicePartial extends Basis {

    /**
     * 办理状态枚举
     * 0---保存状态；1--办理 ；2--审核 ;  3 --办结 ；4--回告办结,5--交办,6--回告审核,7-
     *
     * @author chunl
     * 2017年8月31日下午2:29:37
     */
    public enum StatusEnum {
        Save(0), Register(1), Audit(2), Over(3), BackOver(4), Tranfer(5), ReportAudit(6), Transmit(7);

        private int value;

        StatusEnum(int i) {
            this.value = i;
        }

        public int getValue() {
            return value;
        }
    }

    @Autowired
    private LetterCaseMapper mapper;

    @Autowired
    private LetterCaseMapperPartial mapperPartial;

    private IAttachmentDao attachmentDao;

    @Resource(name = "attachmentDao")
    public void setAttachmentDao(IAttachmentDao attachmentDao) {
        this.attachmentDao = attachmentDao;
    }

    /**
     * 获取办理中选择转办或交办的组织单位
     *
     * @param registerId
     * @return
     */
    private String getTraUnit(Long registerId) {
        User currentUser = getCurrentUser();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("orgId", currentUser.getOrganizationID());
        map.put("registerId", registerId);
        XfDeals handDeal = mapperPartial.getXfDeal(map);
        return handDeal.getOrganizationId();
    }

    /**
     * 经办人办理更新XF_Register表中流程信息
     *
     * @param xfDeals
     */
    @org.springframework.transaction.annotation.Transactional
    private ResultState updateHandleInfo(XfRegister register, XfDeals xfDeals) {
        User currentUser = getCurrentUser();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("registerId", xfDeals.getRegisterId());

        if (OrganizationGrade.Province == currentUser.getOrgGrade()) {
            map.put("PROPATTERN", xfDeals.getDealStatus());
            if (xfDeals.getDealStatus() == 2 || xfDeals.getDealStatus() == 3) { // 如果选择的是不再受理和不予受理
                map.put("PROSTATUS", StatusEnum.Over.getValue());
            } else {
                map.put("PROSTATUS", StatusEnum.Audit.getValue());
            }
        } else if (OrganizationGrade.City == currentUser.getOrgGrade()) {
            map.put("CITYPATTERN", xfDeals.getDealStatus());
            if (xfDeals.getDealStatus() == 2 || xfDeals.getDealStatus() == 3) { // 如果选择的是不再受理和不予受理
                map.put("CITYSTATUS", StatusEnum.Over.getValue());
            } else {
                map.put("CITYSTATUS", StatusEnum.Audit.getValue());
            }

//			if (null !=  register.getProstatus() && register.getProstatus() == StatusEnum.Transmit.getValue()) { //省转办
//				map.put("PROSTATUS", StatusEnum.Over.getValue());
//			}

        } else if (OrganizationGrade.County == currentUser.getOrgGrade()) {
            map.put("COUNTYPATTERN", xfDeals.getDealStatus());
            if (xfDeals.getDealStatus() == 2 || xfDeals.getDealStatus() == 3) { // 如果选择的是不再受理和不予受理
                map.put("STATUS", StatusEnum.Over.getValue());
            } else {
                map.put("STATUS", StatusEnum.Audit.getValue());
            }

//			if (null !=  register.getProstatus() && register.getProstatus() == StatusEnum.Transmit.getValue()) { //省转办
//				map.put("PROSTATUS", StatusEnum.Over.getValue());
//			}
//
//			if (null !=  register.getCitystatus() && register.getCitystatus() == StatusEnum.Transmit.getValue()) { //市转办
//				map.put("CITYSTATUS", StatusEnum.Over.getValue());
//			}

        }
        mapperPartial.updateXfRegisterAudit(map);

        return ResultState.Success;
    }

    /**
     * 领导审核
     *
     * @param register
     * @param xfDeals
     * @return
     */
    @org.springframework.transaction.annotation.Transactional
    private ResultState UpdateRegisterAuditInfo(XfRegister register, XfDeals xfDeals) {
        User currentUser = getCurrentUser();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("registerId", xfDeals.getRegisterId());

        if (xfDeals.getAgree() == 1) { // 同意
            Map<String, Object> xfMap = new HashMap<String, Object>();
            xfMap.put("registerId", xfDeals.getRegisterId());
            xfMap.put("orgId", currentUser.getOrganizationID());
            XfDeals handleDeals = mapperPartial.getXfDeal(xfMap);
            if (currentUser.getOrgGrade() == OrganizationGrade.Province) { // 省级
                if (4 == register.getPropattern()) { // 转办
                    // 更新办理级别、办理单位、办理状态
                    map.put("TRASLEVEL", OrganizationGrade.City);
                    map.put("EXPIRDATE", handleDeals.getExpirDate());
                    map.put("TRANSUNIT", mapperPartial.getNextOrg(register.getRegisterid(), OrganizationGrade.City));
                    map.put("PROSTATUS", StatusEnum.Transmit.getValue());
                    map.put("CITYSTATUS", StatusEnum.Register.getValue());
                } else if (5 == register.getPropattern()) { // 获取省级办理方式，如果是交办
                    if (null != register.getCitystatus() && register.getCitystatus() == StatusEnum.Over.getValue()) {  //如果区县回告
                        map.put("PROSTATUS", StatusEnum.Over.getValue());
                    } else {
                        // 更新办理级别、办理单位、办理状态
                        map.put("TRASLEVEL", OrganizationGrade.City);
                        map.put("EXPIRDATE", handleDeals.getExpirDate());
                        map.put("TRANSUNIT", mapperPartial.getNextOrg(register.getRegisterid(), OrganizationGrade.City));
                        map.put("PROSTATUS", StatusEnum.Tranfer.getValue());
                        map.put("CITYSTATUS", StatusEnum.Register.getValue());
                    }
                } else if (6 == register.getPropattern()) { // 办结
                    map.put("PROSTATUS", StatusEnum.Over.getValue());
                }
            } else if (currentUser.getOrgGrade() == OrganizationGrade.City) { // 市级
                if (4 == register.getCitypattern()) { // 转办
                    // 更新办理级别、办理单位、办理状态
                    map.put("TRASLEVEL", OrganizationGrade.County);
                    map.put("EXPIRDATE", handleDeals.getExpirDate());
                    map.put("TRANSUNIT", mapperPartial.getNextOrg(register.getRegisterid(), OrganizationGrade.County));
                    map.put("CITYSTATUS", StatusEnum.Transmit.getValue());
                    map.put("STATUS", StatusEnum.Register.getValue());
                } else if (5 == register.getCitypattern()) { // 获取省级办理方式，如果是交办
                    if (null != register.getStatus() && register.getStatus() == StatusEnum.Over.getValue()) {  //如果区县回告
                        map.put("CITYSTATUS", StatusEnum.Over.getValue());

                        if (null != register.getProstatus() && register.getProstatus() == StatusEnum.Tranfer.getValue()) { //省交办
                            // 更新办理级别、办理单位、办理状态
                            map.put("TRASLEVEL", OrganizationGrade.Province);
                            map.put("TRANSUNIT", mapperPartial.getParentOrgId(register.getRegisterid(), register.getTransunit()));
                            map.put("PROSTATUS", StatusEnum.ReportAudit.getValue());
                        }

//						if (null !=  register.getProstatus() && register.getProstatus() == StatusEnum.Transmit.getValue()) { //省转办
//							map.put("PROSTATUS", StatusEnum.Over.getValue());
//						}

                    } else {
                        // 更新办理级别、办理单位、办理状态
                        map.put("TRASLEVEL", OrganizationGrade.County);
                        map.put("EXPIRDATE", handleDeals.getExpirDate());
                        map.put("TRANSUNIT", mapperPartial.getNextOrg(register.getRegisterid(), OrganizationGrade.County));
                        map.put("CITYSTATUS", StatusEnum.Tranfer.getValue());
                        map.put("STATUS", StatusEnum.Register.getValue());
                    }

                } else if (6 == register.getCitypattern()) { // 办结
                    map.put("CITYSTATUS", StatusEnum.Over.getValue());
                    if (null != register.getProstatus() && register.getProstatus() == StatusEnum.Tranfer.getValue()) { //省交办
                        // 更新办理级别、办理单位、办理状态
                        map.put("TRASLEVEL", OrganizationGrade.Province);
                        map.put("TRANSUNIT", mapperPartial.getParentOrgId(register.getRegisterid(), register.getTransunit()));
                        map.put("PROSTATUS", StatusEnum.ReportAudit.getValue());
                    }

//					if (null !=  register.getProstatus() && register.getProstatus() == StatusEnum.Transmit.getValue()) { //省转办
//						map.put("PROSTATUS", StatusEnum.Over.getValue());
//					}
                }
            } else if (currentUser.getOrgGrade() == OrganizationGrade.County) { // 区县
                if (6 == register.getCountypattern()) { // 办结
                    map.put("STATUS", StatusEnum.Over.getValue());
                    if (null != register.getCitystatus() && register.getCitystatus() == StatusEnum.Tranfer.getValue()) {
                        // 更新办理级别、办理单位、办理状态
                        map.put("TRASLEVEL", OrganizationGrade.City);
                        map.put("TRANSUNIT", mapperPartial.getParentOrgId(register.getRegisterid(), register.getTransunit()));
                        map.put("CITYSTATUS", StatusEnum.ReportAudit.getValue());
                    }

//					if (null !=  register.getProstatus() && register.getProstatus() == StatusEnum.Transmit.getValue()) { //省转办
//						map.put("PROSTATUS", StatusEnum.Over.getValue());
//					}
//
//					if (null !=  register.getCitystatus() && register.getCitystatus() == StatusEnum.Transmit.getValue()) { //市转办
//						map.put("CITYSTATUS", StatusEnum.Over.getValue());
//					}
                }
            }

            mapperPartial.updateXfRegisterAudit(map);

        } else if (xfDeals.getAgree() == 0) {
            if (currentUser.getOrgGrade() == OrganizationGrade.Province) { // 省级
                if (register.getProstatus() != null && register.getProstatus() == StatusEnum.ReportAudit.getValue()) { //如果是交办回退
                    map.put("TRASLEVEL", OrganizationGrade.City);
                    map.put("TRANSUNIT", mapperPartial.getNextOrg(register.getRegisterid(), OrganizationGrade.City));
                    if (null !=  register.getPropattern() && 5 == register.getPropattern()){
                        map.put("CITYSTATUS", StatusEnum.ReportAudit.getValue());
                    }else {
                        map.put("CITYSTATUS", StatusEnum.Audit.getValue());
                    }
                    map.put("PROSTATUS", StatusEnum.Tranfer.getValue());
                } else {
                    map.put("PROSTATUS", StatusEnum.Register.getValue());
                }
            } else if (currentUser.getOrgGrade() == OrganizationGrade.City) {  // 市级
                if (register.getCitystatus() != null && register.getCitystatus() == StatusEnum.ReportAudit.getValue()) { //如果是交办回退
                    map.put("TRASLEVEL", OrganizationGrade.County);
                    map.put("TRANSUNIT", mapperPartial.getNextOrg(register.getRegisterid(), OrganizationGrade.County));
                    map.put("STATUS", StatusEnum.Audit.getValue());
                    map.put("CITYSTATUS", StatusEnum.Tranfer.getValue());
                } else {
                    map.put("CITYSTATUS", StatusEnum.Register.getValue());
                }
            } else if (currentUser.getOrgGrade() == OrganizationGrade.County) { //区县
                map.put("STATUS", StatusEnum.Register.getValue());
            }
            mapperPartial.updateXfRegisterAudit(map);
        }

        return ResultState.Success;
    }

    /**
     * 审核意见
     *
     * @param xfDeals
     * @return
     */
    @org.springframework.transaction.annotation.Transactional
    public XfResult audit(XfDeals xfDeals) {
        User currentUser = getCurrentUser();

        // Map<String, Object> map = new HashMap<String, Object>(); //
        XfRegister register = mapper.getLetterCaseById(xfDeals.getRegisterId());

        // 1,保存流程详细信息.
        if (null == xfDeals.getDealId())
            xfDeals.setDealId(UUID64.newUUID64().getValue());


        xfDeals.setDealUserId(currentUser.getId());
        xfDeals.setOrgId(currentUser.getOrganizationID());
        xfDeals.setCreateTime(Calendar.getInstance().getTime());
        mapperPartial.saveXfDeal(xfDeals);

        // 2,更新XF_register表中流程信息
        if (xfDeals.getCommit() == 1) { // 提交
            if (xfDeals.getAuditType() == 1) { // 办理
                ResultState state = updateHandleInfo(register, xfDeals);
                return new XfResult(state, xfDeals.getDealId());
            } else if (xfDeals.getAuditType() == 2) { // 领导审核
                ResultState state = UpdateRegisterAuditInfo(register, xfDeals);
                return new XfResult(state, xfDeals.getDealId());
            }
        } else {
            return new XfResult(ResultState.Success, xfDeals.getDealId());
        }

        return new XfResult(ResultState.Failed, xfDeals.getDealId());
    }

    /**
     * 获取所有的审核信息
     *
     * @param registerId
     * @return
     */
    public List<XfDeals> getXfDeals(String registerId) {
        return mapperPartial.getXfDeals(registerId);
    }


    @org.springframework.transaction.annotation.Transactional
    public ResultState delete(String registerId) {
        //删除信访
        mapper.deleteXfRegister(registerId);
        //删除重复信访
        mapper.deleteRepeatItems(registerId);
        //mapperPartial.deleteXfDeals(registerId);
        attachmentDao.deleteByBizID(registerId);
        return ResultState.Success;
    }

}
