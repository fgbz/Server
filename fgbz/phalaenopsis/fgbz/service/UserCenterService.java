package phalaenopsis.fgbz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import phalaenopsis.common.entity.Condition;
import phalaenopsis.common.entity.OpResult;
import phalaenopsis.common.entity.Page;
import phalaenopsis.common.entity.PagingEntity;
import phalaenopsis.fgbz.dao.LawstandardDao;
import phalaenopsis.fgbz.dao.UserCenterDao;
import phalaenopsis.fgbz.entity.Adviceinfo;
import phalaenopsis.fgbz.entity.LawstandardApprove;
import phalaenopsis.fgbz.entity.Suggestion;
import phalaenopsis.lawcase.workflownodes.Punish;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by 13260 on 2017/12/17.
 */
@Service("userCenterService")
public class UserCenterService {

    @Autowired
    private UserCenterDao userCenterDao;

    @Autowired
    private LawstandardDao lawstandardDao;
    /**
     * 保存或编辑通知
     * @param adviceinfo
     * @return
     */
    public int SaveOrUpdateAdvice(Adviceinfo adviceinfo){
        if(adviceinfo.getId()==null||adviceinfo.getId().equals("")){
            adviceinfo.setId(UUID.randomUUID().toString());
        }
        userCenterDao.SaveOrUpdateAdvice(adviceinfo);
        return OpResult.Success;
    }

    /**
     * 删除通知
     * @param id
     * @return
     */
    public int DeleteAdviceByID(String id){
        userCenterDao.DeleteAdviceByID(id);
        return OpResult.Success;
    }

    /**
     * 获取通知信息
     * @param id
     * @return
     */
   public Adviceinfo GetAdviceByID(String id){
       return userCenterDao.GetAdviceByID(id);
   }

    /**
     * 获取通知列表
     * @param page
     * @return
     */
    public PagingEntity<Adviceinfo> gettAdviceList(Page page){

        Map<String, Object> conditions = new HashMap<String, Object>();

        if(page.getConditions()!=null) {
            //查询条件
            for (Condition condition : page.getConditions()) {
                if (condition.getKey().equals("Title")) {
                    conditions.put("Title", condition.getValue());
                }

            }
        }


        // 1,根据条件一共查询到的数据条数
        int count = userCenterDao.getAdviceListCount(conditions);

        if(page.getPageNo()==1){
            conditions.put("startRow", 0 );
        }else{
            conditions.put("startRow", page.getPageSize() * (page.getPageNo() - 1) );
        }
        conditions.put("endRow", page.getPageSize());

        // 2, 查询到当前页数的数据
        List<Adviceinfo> list = userCenterDao.getAdviceList(conditions);

        PagingEntity<Adviceinfo> result = new PagingEntity<Adviceinfo>();
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
     * 获取最新的通知
     * @return
     */
    public PagingEntity<Adviceinfo> getUpToDateAdviceinfos(Page page){

        Map<String, Object> conditions = new HashMap<String, Object>();

        if(page.getPageNo()==1){
            conditions.put("startRow", 0 );
        }else{
            conditions.put("startRow", page.getPageSize() * (page.getPageNo() - 1) );
        }
        int finalcount =  page.getPageSize() * page.getPageNo();

        if(finalcount>=10){
            conditions.put("endRow", 10);
        }else{
            conditions.put("endRow", page.getPageSize());
        }
        int count = userCenterDao.getAdviceListCount(conditions);
        List<Adviceinfo> list = userCenterDao.getAdviceList(conditions);

        PagingEntity<Adviceinfo> result = new PagingEntity<Adviceinfo>();
        result.setPageCount(count);

        int pageCount = 0 == count ? 1 : (count - 1) / page.getPageSize() + 1; // 由于计算pageCount存在整除的情况，所以计算的时候先减1在除以pageSize
        result.setPageNo(page.getPageNo());
        result.setPageSize(page.getPageSize());
        result.setPageCount(pageCount);
        result.setRecordCount(count);
        result.setCurrentList(list);

        return result;
    }

    /*******************************************用户留言************************************************/

    /**
     * 保存或编辑留言
     * @param suggestion
     * @return
     */
    public int SaveOrUpdateSuggestion(Suggestion suggestion){
        if(suggestion.getId()==null||suggestion.getId().equals("")){
            suggestion.setId(UUID.randomUUID().toString());
        }
        userCenterDao.SaveOrUpdateSuggestion(suggestion);
        return OpResult.Success;
    }

    /**
     * 删除留言
     * @param id
     * @return
     */
    public int DeleteSuggestionByID(String id){
        userCenterDao.DeleteSuggestionByID(id);
        return OpResult.Success;
    }

    /**
     * 获取留言列表
     * @param page
     * @return
     */
    public PagingEntity<Suggestion> getSuggestionList(Page page){

        Map<String, Object> conditions = new HashMap<String, Object>();

        if(page.getConditions()!=null) {
            //查询条件
            for (Condition condition : page.getConditions()) {
                if (condition.getKey().equals("Title")) {
                    conditions.put("Title", condition.getValue());
                }else if(condition.getKey().equals("Type")){
                    conditions.put("Type", condition.getValue());
                }

            }
        }


        // 1,根据条件一共查询到的数据条数
        int count = userCenterDao.getSuggestionListCount(conditions);

        if(conditions.get("Type")!=null){
            //最新10条
            if(page.getPageNo()==1){
                conditions.put("startRow", 0 );
            }else{
                conditions.put("startRow", page.getPageSize() * (page.getPageNo() - 1) );
            }
            int finalcount =  page.getPageSize() * page.getPageNo();

            if(finalcount>=10){
                conditions.put("endRow", 10);
            }else{
                conditions.put("endRow", page.getPageSize());
            }
        }else{
            if(page.getPageNo()==1){
                conditions.put("startRow", 0 );
            }else{
                conditions.put("startRow", page.getPageSize() * (page.getPageNo() - 1) );
            }
            conditions.put("endRow", page.getPageSize());
        }

        // 2, 查询到当前页数的数据
        List<Suggestion> list = userCenterDao.getSuggestionList(conditions);

        PagingEntity<Suggestion> result = new PagingEntity<Suggestion>();
        result.setPageCount(count);

        int pageCount = 0 == count ? 1 : (count - 1) / page.getPageSize() + 1; // 由于计算pageCount存在整除的情况，所以计算的时候先减1在除以pageSize
        result.setPageNo(page.getPageNo());
        result.setPageSize(page.getPageSize());
        result.setPageCount(pageCount);
        result.setRecordCount(count);
        result.setCurrentList(list);

        return result;
    }


    /**********************************审核******************************/

    /**
     * 保存审核信息
     * @param lawstandardApprove
     * @return
     */
    @Transactional
    public int SaveApprove(LawstandardApprove lawstandardApprove){
        if(lawstandardApprove.getId()==null||lawstandardApprove.getId().equals("")){
            lawstandardApprove.setId(UUID.randomUUID().toString());
        }
        userCenterDao.SaveApprove(lawstandardApprove);

        int status=1;
        if(lawstandardApprove.getStatus()==0){
            status = 1;
        }else{
            status = 3;
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id",lawstandardApprove.getLawstandardID());
        map.put("status",status);
        lawstandardDao.updateLawstandardStatus(map);
        return OpResult.Success;
    }

    /**
     * 通过法规id获取审核历史记录
     * @param id
     * @return
     */
    public List<LawstandardApprove> getApproveHistroy(String id){
        return userCenterDao.getApproveHistroy(id);
    }
}
