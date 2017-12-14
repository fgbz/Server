package phalaenopsis.visitSystem.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import phalaenopsis.allWeather.entity.SwMapspot;
import phalaenopsis.common.entity.*;
import phalaenopsis.common.method.Basis;
import phalaenopsis.common.method.JsonResult;
import phalaenopsis.common.method.Tools.UUID64;
import phalaenopsis.common.method.cache.DataCache;
import phalaenopsis.common.method.cache.UserCache;
import phalaenopsis.illegalclue.entity.ResultState;
import phalaenopsis.systemmanagement.dao.ISsConfigDao;
import phalaenopsis.systemmanagement.entity.SsConfig;
import phalaenopsis.visitSystem.dao.LetterCaseMapper;
import phalaenopsis.visitSystem.entity.XfRegister;
import phalaenopsis.visitSystem.entity.XfRepeatItems;

@Service("letterCaseService")
public class LetterCaseService extends Basis {
    //自动注入mapper 用于访问数据库
    @Autowired
    private LetterCaseMapper mapper;

    @Autowired
    private ISsConfigDao dao;

    /**
     * 查询信访列表
     *
     * @param page 参数
     * @return
     */
    public PagingEntity<XfRegister> getList(Page page) {
        Map<String, Object> map = new HashMap<String, Object>();
        for (Condition condition : page.getConditions()) {
            map.put(condition.getKey(), condition.getValue());
        }
        map.put("startNum", page.getPageSize() * (page.getPageNo() - 1) + 1);
        map.put("endNum", page.getPageSize() * page.getPageNo());
        int organizationGrade = getCurrentUser().getOrgGrade();
        User currentUser = getCurrentUser();
        map.put("xfAudit", 1);//没有审核权限
        map.put("xfRegister", 1);//没有登记权限
        if (currentUser.getRoles().size() > 0) {
            for (Role role : currentUser.getRoles()) {
                if (role.getId().equals("xfAudit")) {
                    map.remove("xfAudit");
                    map.put("xfAudit", 2);//有审核权限
                }
                if(role.getId().equals("xfRegister")){
                	map.remove("xfRegister");
                	map.put("xfRegister", 2);
                }
            }
        }
        //组织机构
        map.put("organizationGrade", organizationGrade);
        //登录用户所在区域ID
        map.put("authId", currentUser.getId());
        map.put("transunit", currentUser.getOrganizationID());
        List<Integer> ids = new ArrayList<>();
        if (currentUser.getRegions().length > 0) {
            for (Region region : currentUser.getRegions()) {
                ids.add(region.getRegionID());
            }
        }
        ids.add(currentUser.getRegionId());
        map.put("regionList", ids);
        //总条数
        int count = mapper.getCount(map);
        List<XfRegister> list = mapper.getList(map);
        //读取快超期预警配置
        SsConfig config = dao.getSsConfigByType("overduedate");
        for (XfRegister register : list) {
            if (register.getExpirdate() != null) {
                int i = daysBetween(new Date(), register.getExpirdate());
                if (i > 0 && i <= Integer.parseInt(config.getContent())) {//这种情况下属于快超期
                    register.setWarnstaus(2);
                } else if (i < 0) {
                    register.setWarnstaus(1);//已经超期了
                }
            }
            User u = (User) UserCache.get(register.getResponsibleuserid());
            if (u != null) {
                register.setResposibleusername(u.getName());
            }
            String key = "Issuenature" + register.getIssuenature();
            DataDictionaryItem dictionaryItem = (DataDictionaryItem) DataCache.get(key);
            if (dictionaryItem != null) {
                register.setIssuenatureName(dictionaryItem.getText());
            }

            if (currentUser.getOrgGrade() == OrganizationGrade.Province) {
                if (null != register.getProstatus()) {
                    if (register.getProstatus() == LetterCaseServicePartial.StatusEnum.Register.getValue()) {
                        register.setProcess("待办");
                    } else if (register.getProstatus() == LetterCaseServicePartial.StatusEnum.Audit.getValue()) {
                        register.setProcess("待审");
                    } else if (register.getProstatus() == LetterCaseServicePartial.StatusEnum.Over.getValue()) {
                        register.setProcess("办结");
                        register.setWarnstaus(0);
                    } else if (register.getProstatus() == LetterCaseServicePartial.StatusEnum.Tranfer.getValue()) {
                        register.setProcess("省交办");
                    } else if (register.getProstatus() == LetterCaseServicePartial.StatusEnum.Transmit.getValue()) {
                        register.setProcess("办结");
                        register.setWarnstaus(0);
                    } else if (register.getProstatus() == LetterCaseServicePartial.StatusEnum.ReportAudit.getValue()) {
                        register.setProcess("地市回告");
                    }
                }
            } else if (currentUser.getOrgGrade() == OrganizationGrade.City) {
                if (null != register.getCitystatus()) {
                    if (register.getCitystatus() == LetterCaseServicePartial.StatusEnum.Register.getValue() && null != register.getProstatus() && register.getProstatus() == LetterCaseServicePartial.StatusEnum.Tranfer.getValue()) {
                        register.setProcess("省交办");
                    } else if (register.getCitystatus() == LetterCaseServicePartial.StatusEnum.Register.getValue() && null != register.getProstatus() && register.getProstatus() == LetterCaseServicePartial.StatusEnum.Transmit.getValue()) {
                        register.setProcess("省转办");
                    } else if (register.getCitystatus() == LetterCaseServicePartial.StatusEnum.Register.getValue()) {
                        register.setProcess("待办");
                    } else if (register.getCitystatus() == LetterCaseServicePartial.StatusEnum.Audit.getValue()) {
                        register.setProcess("待审");
                    } else if (register.getCitystatus() == LetterCaseServicePartial.StatusEnum.Over.getValue()) {
                        register.setProcess("办结");
                        register.setWarnstaus(0);
                    } else if (register.getCitystatus() == LetterCaseServicePartial.StatusEnum.Tranfer.getValue()) {
                        register.setProcess("市交办");
                    } else if (register.getCitystatus() == LetterCaseServicePartial.StatusEnum.Transmit.getValue()) {
                        register.setProcess("办结");
                        register.setWarnstaus(0);
                    } else if (register.getCitystatus() == LetterCaseServicePartial.StatusEnum.ReportAudit.getValue()) {
                        register.setProcess("区县回告");
                    }
                }

            } else if (currentUser.getOrgGrade() == OrganizationGrade.County) {
                if (null != register.getStatus()) {
                    if (register.getStatus() == LetterCaseServicePartial.StatusEnum.Register.getValue() && null != register.getCitystatus() && register.getCitystatus() == LetterCaseServicePartial.StatusEnum.Tranfer.getValue()) {
                        register.setProcess("市交办");
                    } else if (register.getStatus() == LetterCaseServicePartial.StatusEnum.Register.getValue() && null != register.getCitystatus() && register.getCitystatus() == LetterCaseServicePartial.StatusEnum.Transmit.getValue()) {
                        register.setProcess("市转办");
                    } else if (register.getStatus() == LetterCaseServicePartial.StatusEnum.Register.getValue()) {
                        register.setProcess("待办");
                    } else if (register.getStatus() == LetterCaseServicePartial.StatusEnum.Audit.getValue()) {
                        register.setProcess("待审");
                    } else if (register.getStatus() == LetterCaseServicePartial.StatusEnum.Over.getValue()) {
                        register.setProcess("办结");
                        register.setWarnstaus(0);
                    }
                }
            }
        }
        PagingEntity<XfRegister> entity = new PagingEntity<XfRegister>();
        entity.PageNo = page.getPageNo();//第几页
        entity.PageSize = page.getPageSize();//每页显示数
        entity.PageCount = count == 0 ? 0 : (count - 1) / page.getPageSize() + 1; // 由于计算pageCount存在整除的情况，所以计算的时候先减1在除以pageSize
        entity.RecordCount = count;//记录总条数
        entity.CurrentList = list;
        entity.PageCount = (entity.RecordCount / entity.PageSize) + (entity.RecordCount % entity.PageSize > 0 ? 1 : 0);//总页数
        return entity;
    }

    /**
     * 计算两个日期之间相差的天数
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int daysBetween(Date date1, Date date2) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date1);
        long time1 = cal.getTimeInMillis();
        cal.setTime(date2);
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24);

        return Integer.parseInt(String.valueOf(between_days));
    }

    /**
     * 保存或更新登记信息
     *
     * @return
     */
    public JsonResult saveOrUpdateLetterCase(XfRegister register) {
        JsonResult jsonResult = new JsonResult();
        boolean flag = false;
        //如果为空 就new 一个
        if (register == null) {
            register = new XfRegister();
        }
        int t;
        int regionId = getCurrentUser().getRegionId();
        int organizationGrade = getCurrentUser().getOrgGrade();
        if (organizationGrade == 3) {
            register.setProstatus(register.getIsSave());
        } else if (organizationGrade == 2) {
            register.setCitystatus(register.getIsSave());
        } else {
        	register.setCountyid(getCurrentUser().getRegionId().longValue());
        	Long cityId=(long) getCurrentUser().getRegions()[0].ParentID;
        	register.setCityid(cityId);
            register.setStatus(register.getIsSave());
        }
        if (register.getRegisterid() == null) {
            register.setRegisterid(UUID64.newUUID64().getValue());
            register.setAccessnum(1);//信访次数
            register.setArealevel(organizationGrade);//行政区域级别
            register.setReglevel(organizationGrade);//登录级别
            register.setResponsregionid(new Long(regionId));//行政区域Id
            register.setResponsibleuserid(getCurrentUser().getId());
            register.setResposibleusername(getCurrentUser().getName());
            register.setTransunit(getCurrentUser().getOrganizationID());
//            register.setRegisterdate(new Date());
            //流水单号
            String num = getMaxNum("register");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            String ymd = sdf.format(new Date());
            String serialno = "xf" + register.getCountyid().toString() + String.valueOf(organizationGrade) + ymd + num;
            register.setSerialno(serialno);
            t = mapper.saveOrUpdate(register);
            //更新信访历史信息(重复信访表)
            XfRepeatItems repeatItems = new XfRepeatItems();
            repeatItems.setId(UUID64.newUUID64().getValue());
            repeatItems.setXfregisterid(register.getRegisterid());
            repeatItems.setXfdate(register.getRegisterdate());
            repeatItems.setXftype(register.getXftype());
            repeatItems.setXftypename(register.getXftypename());
            repeatItems.setXfpeopelcount(register.getXfpeoplecount());
            repeatItems.setXfremark("初次信访");
            int i = mapper.saveXfRepeatItems(repeatItems);
            if (t > 0 && i > 0) {
                Serialnumber serialnumber = new Serialnumber();
                Calendar calendar = Calendar.getInstance();
                Integer year = calendar.get(Calendar.YEAR);//得到年
                serialnumber.setType("register");
                serialnumber.setYear(year);
                int inum = Integer.parseInt(num);
                inum++;
                String str = String.format("%4d", inum).replace(" ", "0");
                serialnumber.setSerialnum(str);
                serialnumber.setModifier(getAuthId());
                serialnumber.setModifiertime(new Date());
                mapper.update(serialnumber);
            }

        } else {
            t = mapper.saveOrUpdate(register);
        }
        if (t > 0) {
            flag = true;
            jsonResult.setData(register);
        }
        jsonResult.setStatus(flag);
        return jsonResult;
    }

    /**
     * 根据ID查询信访信息
     *
     * @return
     */
    public XfRegister getLetterCaseById(Long id) {
        XfRegister register = mapper.getLetterCaseById(id);
        User u = (User) UserCache.get(register.getResponsibleuserid());
        if (u != null) {
            register.setResposibleusername(u.getName());
        }
        String key = "Issuenature" + register.getIssuenature();
        DataDictionaryItem dictionaryItem = (DataDictionaryItem) DataCache.get(key);
        if (dictionaryItem != null) {
            register.setIssuenatureName(dictionaryItem.getText());
        }
        return register;
    }

    /**
     * 查询重复信访详情列表
     *
     * @param registerId
     * @return
     */
    public List<XfRepeatItems> getRepeatListByRegisterId(Long registerId) {
        List<XfRepeatItems> repeatItems = new ArrayList<XfRepeatItems>();
        if (registerId != null) {
            repeatItems = mapper.selectRepeatItems(registerId);
        }
        return repeatItems;
    }

    /**
     * 获取最大编号
     */
    public String getMaxNum(String type) {
        //编号自动生成
        //先查出编号最大的出来(查询的时候用数据库行锁),如果没有就添加一条原始数据
        //，取当前年份跟数据库年份比较，如果数据库年份不等于当前年份就更新数据库年份和编号为0001，再做下面的操作
        //，取当前年月日 登录级别拼接起来 ，在保存成功后更新数据库最大值(更新完后解开行锁)，
        Serialnumber number = mapper.getMaxNum(type);
        String num = "";
        Calendar calendar = Calendar.getInstance();
        Integer year = calendar.get(Calendar.YEAR);//得到年
        if (number == null) {
            //如果不存在就添加一条记录
            Serialnumber serialnumber = new Serialnumber();
            serialnumber.setId(UUID64.newUUID64().getValue());
            serialnumber.setCreator(getAuthId());
            serialnumber.setCreationtime(new Date());
            serialnumber.setSerialnum("0001");
            serialnumber.setYear(year);
            serialnumber.setType(type);
            mapper.insert(serialnumber);
            num = serialnumber.getSerialnum();
        } else {
            if (!year.equals(number.getYear())) {
                //如果不是数据库存的年份跟当前年份不一致更新年份和初始流水单号
                number.setYear(year);
                number.setSerialnum("0001");
                number.setModifier(getAuthId());
                number.setModifiertime(new Date());
                mapper.update(number);
            }
            num = number.getSerialnum();
        }
        return num;
    }

    /**
     * 查询重复信访信息
     *
     * @param name
     * @param idCard
     * @return
     */
    public List<XfRegister> getRepeatList(String name, String idCard) {
        Map<String, Object> map = new HashMap<>();
        map.put("name", name);
        map.put("idCard", idCard);
        int organizationGrade = getCurrentUser().getOrgGrade();
        int regionId = getCurrentUser().getRegionId();
        //组织机构
        map.put("organizationGrade", organizationGrade);
        //登录用户所在区域ID
        map.put("regionId", regionId);
        List<XfRegister> repeatList = mapper.getRepeatList(map);
        for(XfRegister register : repeatList){
        	String key = "Issuenature" + register.getIssuenature();
            DataDictionaryItem dictionaryItem = (DataDictionaryItem) DataCache.get(key);
            if (dictionaryItem != null) {
                register.setIssuenatureName(dictionaryItem.getText());
            }
        }
        return repeatList;
    }

    /**
     * 保存，创建重复件信息
     *
     * @param repeatItems
     * @return
     */
    @SuppressWarnings("unused")
    public ResultState saveRepeat(XfRepeatItems repeatItems) {
        if (repeatItems.getXfregisterid() != null) {
            XfRegister register = new XfRegister();
            register.setRegisterid(repeatItems.getXfregisterid());
            register.setAccessnum(repeatItems.getXfCount() + 1);
            mapper.saveOrUpdate(register);
        }
        int t = 0;
        if (repeatItems == null) {
            repeatItems = new XfRepeatItems();
        }
        if (repeatItems.getId() == null) {
            repeatItems.setId(UUID64.newUUID64().getValue());
            t = mapper.saveXfRepeatItems(repeatItems);
        }
        return 1 == t ? ResultState.Success : ResultState.Failed;
    }

    public static void main(String[] args) {
        int a = 9999;
        a++;
        //String format = String.format("%06",a);
        String str = String.format("%4d", a).replace(" ", "0");
        System.out.println(str);

    }
}
