package phalaenopsis.fgbz.dao;

import phalaenopsis.fgbz.entity.*;

import java.util.List;
import java.util.Map;

/**
 * Created by 13260 on 2017/12/17.
 */
public interface UserCenterDao {

    /**
     * 保存或编辑通知
     * @param adviceinfo
     * @return
     */
    int SaveOrUpdateAdvice(Adviceinfo adviceinfo);

    /**
     * 删除通知
     * @param id
     * @return
     */
    int DeleteAdviceByID(String id);

    /**
     * 获取通知信息
     * @param id
     * @return
     */
    Adviceinfo GetAdviceByID(String id);

    /**
     * 获取通知列表总数
     * @param map
     * @return
     */
    int  getAdviceListCount(Map<String, Object> map);

    /**
     * 获取通知列表
     * @param map
     * @return
     */
    List<Adviceinfo> getAdviceList(Map<String, Object>  map);


    /***********************************用户留言*********************************/
    int SaveOrUpdateSuggestion(Suggestion suggestion);

    int DeleteSuggestionByID(String id);

    /**
     * 获取留言列表
     * @param map
     * @return
     */
    List<Suggestion> getSuggestionList(Map<String, Object>  map);

    /**
     * 获取留言数量
     * @param map
     * @return
     */
    int getSuggestionListCount(Map<String, Object>  map);

    /**
     * 保存反馈
     * @return
     */
    int SaveSuggestionFeedBack(SuggestionFeedBack suggestionFeedBack);

    /**
     * 删除留言反馈
     * @return
     */
    int DeleteSuggestionFeedBack(String id);

    /**
     * 获取留言反馈信息
     * @return
     */
    List<SuggestionFeedBack> getFeedBackList(String id);



    /**********************************审核******************************/

    /**
     * 保存审核信息
     * @param lawstandardApprove
     * @return
     */
    int SaveApprove(LawstandardApprove lawstandardApprove);

    /**
     * 通过法规id获取审核历史记录
     * @param id
     * @return
     */
    List<LawstandardApprove> getApproveHistroy(String id);

    /***********************************收藏夹********************************/

    /**
     * 通过父节点获取子节点记录
     * @return
     */
    List<Favorite>  getFavoriteListByParentID(String id);

    /**
     * 通过id获取收藏夹信息
     * @param id
     * @return
     */
     Favorite  getFavoriteListByID(String id);

    /**
     * 删除收藏夹
     * @return
     */
    int DeleteFavoriteByID(String id);

    /**
     * 删除收藏夹和法规关联
     * @return
     */
    int DeleteFavoriteLawsLink(Map<String,Object> map);

    /**
     * 通过根目录删除所有的收藏夹
     * @param list
     * @return
     */
    int DeleteAllFavorite(List<Favorite> list);

    /**
     * 通过根目录删除所有的收藏夹与法规的关联
     * @param list
     * @return
     */
    int DeleteAllFavoriteLawsLink(List<Favorite> list);

    /**
     * 保存或新增收藏夹
     * @param favorite
     * @return
     */
    int SaveOrUpdateFavorite(Favorite favorite);

    /**
     * 保存法规与收藏夹关联
     * @param map
     * @return
     */
    int SaveFavoriteLawsLink(Map<String,Object> map);

    /**
     * 获取收藏夹相关的法规
     * @return
     */
    List<Lawstandard> getLawsByLinkID(Map<String,Object> map);

    /**
     * 获取收藏相关法规总数
     * @param map
     * @return
     */
    int getLawsByLinkIDCount(Map<String,Object> map);

    /**
     * 获取法规对应的收藏夹Favorite
     * @return
     */
    List<Favorite> getFavoriteListByLawID(String id);

    /**
     * 取消收藏
     * @param map
     * @return
     */
    int DismissFavorite(Map<String,Object> map);

    /**
     * 获取最后的层级代码
     * @param id
     * @return
     */
    int getLastItemLevelcode(String id);

    /**
     *处理上移或下移
     * @param favorite
     * @return
     */
    int handTreeLevel(Favorite favorite);

    /***************************修改密码*****************************/
    int getUserByPasswordAndId(Map<String,Object> map);

    int updateUserPassword(Map<String,Object> map);
}
