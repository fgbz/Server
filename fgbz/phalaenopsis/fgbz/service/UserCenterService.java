package phalaenopsis.fgbz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import phalaenopsis.common.entity.Condition;
import phalaenopsis.common.entity.OpResult;
import phalaenopsis.common.entity.Page;
import phalaenopsis.common.entity.PagingEntity;
import phalaenopsis.common.method.Tools.StrUtil;
import phalaenopsis.fgbz.dao.ILog;
import phalaenopsis.fgbz.dao.LawstandardDao;
import phalaenopsis.fgbz.dao.UserCenterDao;
import phalaenopsis.fgbz.entity.*;

import java.util.*;

import static phalaenopsis.common.method.Basis.getCurrentFGUser;

/**
 * Created by 13260 on 2017/12/17.
 */
@Service("userCenterService")
public class UserCenterService {

    @Autowired
    private UserCenterDao userCenterDao;

    @Autowired
    private LawstandardDao lawstandardDao;

    @Autowired
    private SystemServie systemServie;

    private  List<FG_Organization> ids = new ArrayList<FG_Organization>();

    public  List<FG_Organization> getOrgsTree(String id){

        List<FG_Organization> list = systemServie.getChildNode(id);

        while (list!=null&&list.size()>0){
            ids.addAll(list);
            for(FG_Organization organization:list){
                list=getOrgsTree(organization.getId());
            }
        }
        return list;
    }
    /**
     * 保存或编辑通知
     * @param adviceinfo
     * @return
     */
    @Transactional
    @ILog(description="保存通知")
    public int SaveOrUpdateAdvice(Adviceinfo adviceinfo){
        if(adviceinfo.getId()==null||adviceinfo.getId().equals("")){
            adviceinfo.setId(UUID.randomUUID().toString());
        }
        //建立与附件的关系
        if(adviceinfo.getFileids().size()>0){
            userCenterDao.SaveAdviceinfoFileLink(adviceinfo);
        }
        userCenterDao.SaveOrUpdateAdvice(adviceinfo);
        return OpResult.Success;
    }

    /**
     * 删除通知
     * @param id
     * @return
     */
    @ILog(description="删除通知")
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
                if (condition.getKey().equals("keyWords")) {
                    conditions.put("keyWords", condition.getValue());
                }else if (condition.getKey().equals("Title")) {
                    conditions.put("Title", condition.getValue());
                } else if (condition.getKey().equals("FiledTimeStart")) {
                    conditions.put("FiledTimeStart", condition.getValue());
                } else if (condition.getKey().equals("FiledTimeEnd")) {
                    conditions.put("FiledTimeEnd", condition.getValue());
                }  else if (condition.getKey().equals("organization")) {

                    ids =new ArrayList<>();
                    FG_Organization orgSelf = new FG_Organization();
                    orgSelf.setId(condition.getValue());
                    ids.add(orgSelf);
                    getOrgsTree(condition.getValue());
                    conditions.put("TreeValue",ids );

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
    @ILog(description="保存留言")
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
    @Transactional
    @ILog(description="删除留言")
    public int DeleteSuggestionByID(String id){
        userCenterDao.DeleteSuggestionByID(id);
        userCenterDao.DeleteSuggestionFeedBack(id);
        return OpResult.Success;
    }

    /**
     * 保存反馈
     * @return
     */
    @ILog(description="保存反馈")
    public int SaveSuggestionFeedBack(SuggestionFeedBack suggestionFeedBack){

        if(suggestionFeedBack.getId()==null&&suggestionFeedBack.getId().equals("")){
            suggestionFeedBack.setId(UUID.randomUUID().toString());
        }
        userCenterDao.SaveSuggestionFeedBack(suggestionFeedBack);
        return OpResult.Success;
    }

    /**
     * 获取反馈列表
     * @return
     */
    public List<SuggestionFeedBack> getFeedBackList(String id){
        return userCenterDao.getFeedBackList(id);
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
                if (condition.getKey().equals("KeyWords")) {
                    conditions.put("KeyWords", condition.getValue());
                }else if(condition.getKey().equals("Type")){
                    conditions.put("Type", condition.getValue());
                } else if (condition.getKey().equals("FiledTimeStart")) {
                    conditions.put("FiledTimeStart", condition.getValue());
                } else if (condition.getKey().equals("FiledTimeEnd")) {
                    conditions.put("FiledTimeEnd", condition.getValue());
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
    @ILog(description="保存审核信息")
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
            //通过,如果有替代关系，更新状态
            List<Lawstandard> listReplace= lawstandardDao.getReplaceList(lawstandardApprove.getLawstandardID());

            if(listReplace!=null&&listReplace.size()>0){
                for (Lawstandard lawstandard:listReplace) {
                    lawstandardDao.updateRleplaceStaus(lawstandard.getId());
                }
            }
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

    /*****************************获取用户收藏夹********************************/

    private List<Favorite>  facList = new ArrayList<>();

    /**
     * 获取用户收藏夹
     * @param id
     * @return
     */
    public List<Favorite>  getFavoriteList(String id){

        facList = new ArrayList<>();
        Favorite favorite = userCenterDao.getFavoriteListByID(id);
        facList.add(favorite);

        getFavsTree(id);
        return facList;
    }

    /*
    通过父节点id获取子收藏夹内容
     */
    public List<Favorite> getFavoriteListByParentID(String id){
        return  userCenterDao.getFavoriteListByParentID(id);
    }

    /**
     * 递归获取树
     * @param id
     * @return
     */
    public  List<Favorite> getFavsTree(String id){

        List<Favorite> list = getFavoriteListByParentID(id);

        while (list!=null&&list.size()>0){
            facList.addAll(list);
            for(Favorite favorite:list){
                list=getFavsTree(favorite.getId());
            }
        }
        return list;
    }

    /**
     * 删除搜藏夹
     * @return
     */
    @Transactional
    @ILog(description="删除搜藏夹")
    public int DeleteFavoriteByID(Favorite favorite){

        userCenterDao.DeleteFavoriteByID(favorite.getId());

        Map<String,Object> map =new HashMap<>();
        map.put("id",favorite.getId());
        map.put("type","fav");
        userCenterDao.DeleteFavoriteLawsLink(map);

        handTreeLevel(favorite);
        return OpResult.Success;
    }

    /**
     * 保存或新增收藏夹
     * @return
     */
    @Transactional
    @ILog(description="保存收藏夹")
    public int SaveOrUpdateFavorite(Favorite favorite){
        if(favorite.getId()==null||favorite.getId().equals("")){
            favorite.setId(UUID.randomUUID().toString());
        }

        int itemlevelcode = 0;
        //点击的对象
        Favorite handleitem = favorite.getHandleitem();

        //处理不同的类型
        switch(favorite.getHandletype()){
            case "addEqual":
                itemlevelcode=  getLastItemLevelcode(handleitem.getParentid());
                favorite.setItemlevelcode(itemlevelcode);
                break;
            case "addDown":
                itemlevelcode=  getLastItemLevelcode(handleitem.getId());
                favorite.setItemlevelcode(itemlevelcode);
                break;
            case "moveUp":
                if(!StrUtil.isNullOrEmpty(favorite.getItemlevelcode().toString())){
                    handTreeLevel(favorite);
                    favorite.setItemlevelcode(favorite.getItemlevelcode()-1);
                }
                break;
            case "moveDown":
                if(!StrUtil.isNullOrEmpty(favorite.getItemlevelcode().toString())){
                    handTreeLevel(favorite);
                    favorite.setItemlevelcode(favorite.getItemlevelcode()+1);
                }
                break;
        }

        userCenterDao.SaveOrUpdateFavorite(favorite);
        return OpResult.Success;
    }

    /**
     * 获取当前层级最后的层级代码
     * @param id
     * @return
     */
    public int getLastItemLevelcode(String id){
        return userCenterDao.getLastItemLevelcode(id);
    }

    /**
     * 处理上移和下移
     * @return
     */
    public int handTreeLevel(Favorite favorite){
        return  userCenterDao.handTreeLevel(favorite);
    }
    /**
     * 保存收藏夹与法规的关联
     * @param lawstandard
     * @return
     */
    @Transactional
    @ILog(description="收藏法规标准")
    public int SaveFavoriteLawsLink(Lawstandard lawstandard){

        Map<String,Object> map  = new HashMap<>();
        map.put("sid",lawstandard.getId());
        if(lawstandard.getFavs()!=null&&lawstandard.getFavs().size()>0){
            for (Favorite f:lawstandard.getFavs() ) {
                f.setTableid(UUID.randomUUID().toString());
            }
        }
        map.put("fevlists",lawstandard.getFavs());
        map.put("type",0);

        Map<String,Object> map1 =new HashMap<>();
        map1.put("id",lawstandard.getId());
        map1.put("type","law");
        userCenterDao.DeleteFavoriteLawsLink(map1);

        if(lawstandard.getFavs()!=null&&lawstandard.getFavs().size()>0){
            userCenterDao.SaveFavoriteLawsLink(map);
        }

        return OpResult.Success;
    }

    /**
     * 保存收藏夹与技术文档的关联
     * @param technical
     * @return
     */
    @Transactional
    @ILog(description="收藏技术文档")
    public int SaveFavoriteTecsLink(Technical technical){

        Map<String,Object> map  = new HashMap<>();
        map.put("sid",technical.getId());
        if(technical.getFavs()!=null&&technical.getFavs().size()>0){
            for (Favorite f:technical.getFavs() ) {
                f.setTableid(UUID.randomUUID().toString());
            }
        }
        map.put("fevlists",technical.getFavs());
        map.put("type",1);

        Map<String,Object> map1 =new HashMap<>();
        map1.put("id",technical.getId());
        map1.put("type","tec");
        userCenterDao.DeleteFavoriteLawsLink(map1);

        if(technical.getFavs()!=null&&technical.getFavs().size()>0){
            userCenterDao.SaveFavoriteLawsLink(map);
        }

        return OpResult.Success;
    }

    /**
     * 获取收藏夹相关的法规和技术文件
     * @return
     */
   public PagingEntity<FavoriteLink> getLawsAndTecByLinkID(Page page){


       Map<String, Object> conditions = new HashMap<String, Object>();

       if(page.getConditions()!=null) {
           //查询条件
           for (Condition condition : page.getConditions()) {
               if (condition.getKey().equals("TreeValue")) {
                   List<Favorite> list= getFavoriteList(condition.getValue());
                   conditions.put("TreeValue", list);
               }

           }
       }

       // 1,根据条件一共查询到的数据条数
       int count = userCenterDao.getLawsAndTecByLinkIDCount(conditions);

       if(page.getPageNo()==1){
           conditions.put("startRow", 0 );
       }else{
           conditions.put("startRow", page.getPageSize() * (page.getPageNo() - 1) );
       }
       conditions.put("endRow", page.getPageSize());

       // 2, 查询到当前页数的数据
       List<FavoriteLink> list = userCenterDao.getLawsAndTecByLinkID(conditions);

       PagingEntity<FavoriteLink> result = new PagingEntity<FavoriteLink>();
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
     * 获取法规对应的收藏夹Favorite
     * @param id
     * @return
     */
    public List<Favorite> getFavoriteListByLawID(String id){
        return userCenterDao.getFavoriteListByLawID(id);
    }

    /**
     * 取消收藏
     * @return
     */
    @ILog(description="取消收藏")
   public int DismissFavorite(String favid,String sid){

       Map<String,Object> map =new HashMap<>();
       map.put("favid",favid);
       map.put("sid",sid);
       userCenterDao.DismissFavorite(map);
       return OpResult.Success;
   }

    /**
     * 更新用户密码
     * @return
     */
    @ILog(description="更新用户密码")
    public int  updateUserPassword(String id,String oldpassword,String newpassword){

        Map<String,Object> map = new HashMap<>();
        map.put("id",id);
        map.put("oldpassword",oldpassword);
        map.put("newpassword",newpassword);

        int num =userCenterDao.getUserByPasswordAndId(map);
        if(num==0){
            int isWorking = 403;
            OpResult opResult = new OpResult(isWorking);
            return opResult.Code;
        }
        userCenterDao.updateUserPassword(map);
        return  OpResult.Success;

    }
}
