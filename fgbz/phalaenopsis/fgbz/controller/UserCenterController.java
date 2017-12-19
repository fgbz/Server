package phalaenopsis.fgbz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import phalaenopsis.common.entity.Page;
import phalaenopsis.common.entity.PagingEntity;
import phalaenopsis.fgbz.entity.*;
import phalaenopsis.fgbz.service.UserCenterService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 13260 on 2017/12/17.
 */
@Controller
@RequestMapping("/UserCenter")
public class UserCenterController {

    @Autowired
    private UserCenterService userCenterService;

    /**
     * 保存或编辑通知
     * @param adviceinfo
     * @return
     */
    @RequestMapping(value = "/SaveOrUpdateAdvice", method = RequestMethod.POST)
    @ResponseBody
    public int SaveOrUpdateAdvice(@RequestBody Adviceinfo adviceinfo){

      return  userCenterService.SaveOrUpdateAdvice(adviceinfo);
    }

    /**
     * 删除通知
     * @param id
     * @return
     */
    @RequestMapping(value = "/DeleteAdviceByID", method = RequestMethod.GET)
    @ResponseBody
    public int DeleteAdviceByID(String id){
        return  userCenterService.DeleteAdviceByID(id);
    }

    /**
     * 获取通知信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/GetAdviceByID", method = RequestMethod.GET)
    @ResponseBody
    public Adviceinfo GetAdviceByID(String id){
        return userCenterService.GetAdviceByID(id);
    }

    /**
     * 获取通知列表
     * @param page
     * @return
     */
    @RequestMapping(value = "/getAdviceList", method = RequestMethod.POST)
    @ResponseBody
    public PagingEntity<Adviceinfo> getAdviceList(@RequestBody Page page){
        return userCenterService.gettAdviceList(page);
    }

    /**
     * 获取最新的通知
     * @return
     */
    @RequestMapping(value = "/getUpToDateAdviceinfos", method = RequestMethod.POST)
    @ResponseBody
    public PagingEntity<Adviceinfo> getUpToDateAdviceinfos(@RequestBody Page page){
        return  userCenterService.getUpToDateAdviceinfos(page);
    }

    /**
     * 保存或编辑留言
     * @param suggestion
     * @return
     */
    @RequestMapping(value = "/SaveOrUpdateSuggestion", method = RequestMethod.POST)
    @ResponseBody
    public int SaveOrUpdateSuggestion(@RequestBody Suggestion suggestion){
        return userCenterService.SaveOrUpdateSuggestion(suggestion);
    }

    /**
     * 删除留言
     * @param id
     * @return
     */
    @RequestMapping(value = "/DeleteSuggestionByID", method = RequestMethod.GET)
    @ResponseBody
    public int DeleteSuggestionByID(String id){

        return userCenterService.DeleteSuggestionByID(id);
    }

    /**
     * 获取留言列表
     * @param page
     * @return
     */
    @RequestMapping(value = "/getSuggestionList", method = RequestMethod.POST)
    @ResponseBody
    public PagingEntity<Suggestion> getSuggestionList(@RequestBody Page page){
        return  userCenterService.getSuggestionList(page);
    }

    /**********************************审核******************************/

    /**
     * 保存审核信息
     * @param lawstandardApprove
     * @return
     */
    @RequestMapping(value = "/SaveApprove", method = RequestMethod.POST)
    @ResponseBody
    public int SaveApprove(@RequestBody LawstandardApprove lawstandardApprove){

        return userCenterService.SaveApprove(lawstandardApprove);
    }

    /**
     * 通过法规id获取审核历史记录
     * @param id
     * @return
     */
    @RequestMapping(value = "/getApproveHistroy", method = RequestMethod.GET)
    @ResponseBody
    public List<LawstandardApprove> getApproveHistroy(String id){
        return userCenterService.getApproveHistroy(id);
    }

    /****************************************************************************************/
    /**
     * 获取用户收藏夹
     * @param id
     * @return
     */
    @RequestMapping(value = "/getFavoriteList", method = RequestMethod.GET)
    @ResponseBody
    public List<Favorite>  getFavoriteList(String id){
        return userCenterService.getFavoriteList(id);
    }

    /**
     * 删除搜藏夹
     * @return
     */
    @RequestMapping(value = "/DeleteFavoriteByID", method = RequestMethod.GET)
    @ResponseBody
    public int DeleteFavoriteByID(String id){
        return userCenterService.DeleteFavoriteByID(id);
    }

    /**
     * 保存或新增收藏夹
     * @return
     */
    @RequestMapping(value = "/SaveOrUpdateFavorite", method = RequestMethod.POST)
    @ResponseBody
    public int SaveOrUpdateFavorite(@RequestBody Favorite favorite){
        return userCenterService.SaveOrUpdateFavorite(favorite);
    }
    /**
     * 保存收藏夹与法规的关联
     * @param lawstandard
     * @return
     */
    @RequestMapping(value = "/SaveFavoriteLawsLink", method = RequestMethod.POST)
    @ResponseBody
    public int SaveFavoriteLawsLink(@RequestBody Lawstandard lawstandard){
        return userCenterService.SaveFavoriteLawsLink(lawstandard);
    }

    /**
     *获取收藏夹对应的法规
     * @param page
     * @return
     */
    @RequestMapping(value = "/getLawsByLinkID", method = RequestMethod.POST)
    @ResponseBody
    public PagingEntity<Lawstandard> getLawsByLinkID(@RequestBody Page page){
        return userCenterService.getLawsByLinkID(page);
    }

    /**
     * 获取法规对应的收藏夹Favorite
     * @param id
     * @return
     */
    @RequestMapping(value = "/getFavoriteListByLawID", method = RequestMethod.GET)
    @ResponseBody
    public List<Favorite> getFavoriteListByLawID(String id){
        return userCenterService.getFavoriteListByLawID(id);
    }

    /**
     * 取消收藏
     * @return
     */
    @RequestMapping(value = "/DismissFavorite", method = RequestMethod.GET)
    @ResponseBody
    public int DismissFavorite(String favid,String lawid){
        return userCenterService.DismissFavorite(favid,lawid);
    }








}
