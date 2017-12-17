package phalaenopsis.fgbz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import phalaenopsis.common.entity.Condition;
import phalaenopsis.common.entity.OpResult;
import phalaenopsis.common.entity.Page;
import phalaenopsis.common.entity.PagingEntity;
import phalaenopsis.fgbz.dao.UserCenterDao;
import phalaenopsis.fgbz.entity.Adviceinfo;
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
}
