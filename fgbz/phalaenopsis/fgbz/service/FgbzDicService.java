package phalaenopsis.fgbz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import phalaenopsis.common.entity.Condition;
import phalaenopsis.common.entity.OpResult;
import phalaenopsis.common.entity.Page;
import phalaenopsis.common.entity.PagingEntity;
import phalaenopsis.common.method.Tools.StrUtil;
import phalaenopsis.fgbz.dao.FgbzDicDao;
import phalaenopsis.fgbz.dao.ILog;
import phalaenopsis.fgbz.entity.FG_Menu;
import phalaenopsis.fgbz.entity.FgbzDictory;
import phalaenopsis.fgbz.entity.LawstandardStatus;
import phalaenopsis.fgbz.entity.Publishdep;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by 13260 on 2017/12/13.
 */
@Service("fgbzDicService")
public class FgbzDicService {

    @Autowired
    private FgbzDicDao fgbzDicDao;

    @Autowired
    private SystemServie systemServie;

    public Map<Object,Object> getAllFgbzDictory() {

        Map<Object,Object> map = new HashMap<>();

        List<FgbzDictory> listPub = fgbzDicDao.getPublishDep();
        List<FgbzDictory> listState= fgbzDicDao.getLawstandardState();
        List<FG_Menu> listMenus = systemServie.getAllMenus();
        String PageSize=fgbzDicDao.getSettingByKey("PageSize");
        if(PageSize!=null){
            map.put("PageSize",Integer.parseInt(PageSize));
        }else{
            map.put("PageSize",20);
        }
        map.put("Pub",listPub);
        map.put("State",listState);
        map.put("Menu",listMenus);

        return map;
    }


    /**
     * 获取发布部门列表
     * @param page
     * @return
     */
    public PagingEntity<Publishdep> getPublishdepList(Page page){

        Map<String, Object> conditions = new HashMap<String, Object>();

        if(page.getConditions()!=null) {
            //查询条件
            for (Condition condition : page.getConditions()) {
                if (condition.getKey().equals("Name")) {
                    conditions.put("Name", condition.getValue());
                }

            }
        }


        // 1,根据条件一共查询到的数据条数
        int count = fgbzDicDao.getPublishdepListCount(conditions);

        if(page.getPageNo()==1){
            conditions.put("startRow", 0 );
        }else{
            conditions.put("startRow", page.getPageSize() * (page.getPageNo() - 1) );
        }
//        conditions.put("endRow", page.getPageSize());

        conditions.put("endRow", Integer.MAX_VALUE);
        // 2, 查询到当前页数的数据
        List<Publishdep> list = fgbzDicDao.getPublishdepList(conditions);

        PagingEntity<Publishdep> result = new PagingEntity<Publishdep>();
        result.setPageCount(count);

        int pageCount = 0 == count ? 1 : (count - 1) / page.getPageSize() + 1; // 由于计算pageCount存在整除的情况，所以计算的时候先减1在除以pageSize
        result.setPageNo(page.getPageNo());
        result.setPageSize(page.getPageSize());
        result.setPageCount(pageCount);
        result.setRecordCount(count);
        result.setCurrentList(list);

        return result;
    }

    /**
     * 删除发布部门
     * @param id
     * @return
     */
    @ILog(description="删除发布部门")
    @Transactional
    public int DeletePublishdepByID(String id){


        Publishdep publishdep = fgbzDicDao.getPublishdepById(id);
        publishdep.setHandletype("delete");

        fgbzDicDao.handPubLevel(publishdep);

        fgbzDicDao.DeletePublishdepByID(id);
        return OpResult.Success;
    }

    /**
     * 新增或修改发布部门
     * @param publishdep
     * @return
     */
    @ILog(description="保存发布部门")
    public int SaveOrUpdatePublishdep(Publishdep publishdep){

        int num =fgbzDicDao.checkPublichName(publishdep);

        //验证发布部门名称是否重复
        if(num>0){
            int isWorking = 461;
            OpResult opResult = new OpResult(isWorking);
            return opResult.Code;
        }
        if(publishdep.getId() == null||publishdep.getId().equals("")) {
            int pubnum = fgbzDicDao.getMaxPubNum();
            publishdep.setRanknum(pubnum+1);

            publishdep.setId(UUID.randomUUID().toString());
        }

        fgbzDicDao.SaveOrUpdatePublishdep(publishdep);
        return OpResult.Success;
    }

    //处理单位移动
    @Transactional
    public Publishdep HandlePublish(Publishdep publishdep){

        //处理不同的类型
        switch(publishdep.getHandletype()){

            case "moveUp":

                fgbzDicDao.handPubLevel(publishdep);
                publishdep.setRanknum(publishdep.getRanknum()-1);

                break;
            case "moveDown":

                fgbzDicDao.handPubLevel(publishdep);
                publishdep.setRanknum(publishdep.getRanknum()+1);
                break;
        }

        SaveOrUpdatePublishdep(publishdep);

        return publishdep;
    }

    /********************************************状态******************************************/


    /**
     * 获取状态列表
     * @param page
     * @return
     */
    public PagingEntity<LawstandardStatus> getLawstandardStatusList(Page page){

        Map<String, Object> conditions = new HashMap<String, Object>();

        if(page.getConditions()!=null) {
            //查询条件
            for (Condition condition : page.getConditions()) {
                if (condition.getKey().equals("Name")) {
                    conditions.put("Name", condition.getValue());
                }

            }
        }


        // 1,根据条件一共查询到的数据条数
        int count = fgbzDicDao.getLawstandardStatusListCount(conditions);

        if(page.getPageNo()==1){
            conditions.put("startRow", 0 );
        }else{
            conditions.put("startRow", page.getPageSize() * (page.getPageNo() - 1) );
        }
        conditions.put("endRow", page.getPageSize());



        // 2, 查询到当前页数的数据
        List<LawstandardStatus> list = fgbzDicDao.getLawstandardStatusList(conditions);

        PagingEntity<LawstandardStatus> result = new PagingEntity<LawstandardStatus>();
        result.setPageCount(count);

        int pageCount = 0 == count ? 1 : (count - 1) / page.getPageSize() + 1; // 由于计算pageCount存在整除的情况，所以计算的时候先减1在除以pageSize
        result.setPageNo(page.getPageNo());
        result.setPageSize(page.getPageSize());
        result.setPageCount(pageCount);
        result.setRecordCount(count);
        result.setCurrentList(list);

        return result;
    }

    /**
     * 删除状态
     * @param id
     * @return
     */
    @ILog(description="删除执行状态")
    public int DeleteLawstandardStatusByID(String id){
        fgbzDicDao.DeleteLawstandardStatusByID(id);
        return OpResult.Success;
    }

    /**
     * 新增或修改状态
     * @param lawstandardStatus
     * @return
     */
    @ILog(description="保存执行状态")
    public int SaveOrUpdateLawstandardStatus(LawstandardStatus lawstandardStatus){

        int num =fgbzDicDao.checkLawstandardStatusName(lawstandardStatus);

        //验证发布部门名称是否重复
        if(num>0){
            int isWorking = 461;
            OpResult opResult = new OpResult(isWorking);
            return opResult.Code;
        }
        if(lawstandardStatus.getId()==null||lawstandardStatus.getId().equals("")){
            lawstandardStatus.setId(UUID.randomUUID().toString());
        }

        fgbzDicDao.SaveOrUpdateLawstandardStatus(lawstandardStatus);
        return OpResult.Success;
    }
}
