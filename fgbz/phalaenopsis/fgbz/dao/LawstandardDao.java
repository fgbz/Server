package phalaenopsis.fgbz.dao;

import phalaenopsis.fgbz.entity.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface LawstandardDao {

    int testCount();

    List<LawstandardType> SelectLawstandardType();

    int  AddOrUpdateLawstandardType(LawstandardType lawstandardType);

    int  DeleteLawstandardType(LawstandardType lawstandardType);

    int  getLawstandardListCount(Map<String, Object>  map);

    /**
     * 获取全文检索数量
     * @param map
     * @return
     */
    int  getSolrListCount(Map<String, Object>  map);

    List<Lawstandard> getLawstandardList(Map<String, Object>  map);


    List<Lawstandard> getUptodateLawstandardList(Map<String, Object>  map);

    /**
     * 获取引用和替代关系列表
     * @param map
     * @return
     */
    List<Lawstandard> getReplaceLawstandardList(Map<String, Object>  map);

    /**
     * 获取引用和替代关系列表总数
     * @param map
     * @return
     */
    int getReplaceLawstandardCount(Map<String, Object>  map);
    /**
     *获取父级法规类别
     * @return
     */
    LawstandardType getParentLawstandardTypeById(String id);

    int changeLawstandardCount(Map<String, Object>  map);
    /**
     * 获取全文检索的列表
     * @param map
     * @return
     */
    List<Lawstandard> getSolrList(Map<String, Object>  map);

    int deleteLawstandardById(String id);

    /**
     * 新增或编辑法规标准
     * @param lawstandard
     * @return
     */
    int SaveOrUpdateLawstandard(Lawstandard lawstandard);

    /**
     * 保存法规文件与类别关联
     * @param lawstandard
     * @return
     */
    int SaveOrUpdateLawAndType(Lawstandard lawstandard);

    /**
     * 删除法规文件与类别关联
     * @param id
     * @return
     */
    int DeleteLawAndType(String id);

    /**
     * 获取最后的层级代码
     * @param id
     * @return
     */
    int getLastItemLevelcode(String id);

    /**
     *处理上移或下移
     * @param lawstandardType
     * @return
     */
    int handTreeLevel(LawstandardType lawstandardType);

    /**
     * 保存法规文件与部门关联
     * @param lawstandard
     * @return
     */
    int SaveOrUpdateLawAndPublish(Lawstandard lawstandard);

    int deletePubByLaw(String id);

    /**
     * 获取发布部门
     * @param id
     * @return
     */
    List<String> GetPubOrgList(String id);

    String GetpubOrgListName(Map<String, Object>  map);

    /**
     * 获取法规详情
     * @param id
     * @return
     */
    Lawstandard getLawstandardById(String id);

    /**
     * 获取引用关系
     * @param id
     * @return
     */
    List<Lawstandard> getRefenceList(String id);

    /**
     * 获取替代关系
     * @param id
     * @return
     */
    List<Lawstandard> getReplaceList(String id);

    /**
     * 获取详细界面的替代关系
     * @param id
     * @return
     */
    List<Lawstandard> getDetailReplaceList(String id);

    int deleteRefence(String id);
    //删除所有引用和被引用的
    int deleteRefenceAll(String id);

    int deleteReplace(String id);

    int deleteReplaceAll(String id);

    int addRefence(RefenceOrReplace refenceOrReplace);

    int addReplace(RefenceOrReplace refenceOrReplace);

    /**
     * 获取子节点
     * @param id
     * @return
     */
    List<LawstandardType> getChildNode(String id);

    /**
     * 获取子节点
     * @param id
     * @return
     */
    List<LawstandardType> getHomeChildNode(String id);

    /**
     * 点击次数加一
     * @param lawstandard
     */
    void AddLawstandardCount(Lawstandard lawstandard);

    String getLawStandardClickCount(Lawstandard lawstandard);

    void LawstandardIsTop(Lawstandard lawstandard);

    /**
     * 保存附件与法规的关系
     * @param lawstandard
     * @return
     */
    int SaveFileLink(Lawstandard lawstandard);

    /**
     * 更新法规发布状态
     * @param map
     * @return
     */
    int updateLawstandardStatus(Map<String, Object>  map);

    /**
     * 更新所有待审核的法规
     * @return
     */
    int updateAllCheckingLawstandard();

    /**
     * 获取上传数量前十人
     * @return
     */
    List<ChartInfo> getUploadPeople();
    /**
     * 获取上传部门统计
     * @return
     */
    List<ChartInfo> getUploadOrgname();
    /**
     * 获取上传分类
     * @return
     */
    List<ChartInfo> getUploadType();

    /**
     * 判断法规编号是否重复
     * @return
     */
    int checklawCode(Lawstandard lawstandard);

    /**
     * 判断临时法规编号是否重复
     * @param lawstandard
     * @return
     */
    int checklawTempCode(Lawstandard lawstandard);

    /**
     * 获取首页
     * @return
     */
    List<LawstandardType> getHomePageLawsType();

    /**
     * 更新被代替的法规状态
     * @param id
     * @return
     */
    int updateRleplaceStaus(String id);

    /**
     * 通过名称获取法规类别
     * @return
     */
    String getLawTypeByName(String name);

    /**
     * 通过名称获取发布部门id
     * @param name
     * @return
     */
    String getPubOrgnameByName(String name);

    /**
     * 通过名称获取状态id
     * @param name
     * @return
     */
    String getStatusIdByName(String name);

    /**
     * 获取首页导航数量
     * @param map
     * @return
     */
    int getHomePageLawCount(Map<String, Object>  map);

    /**
     * 通过附件id获取法规
     * @param id
     * @return
     */
    Lawstandard  getLawByFileId(String id);

    /**
     * 获取所有的法规
     * @return
     */
    List<Lawstandard> getAllLawstandard();

    /**
     * 更新验重编号
     * @param lawstandard
     * @return
     */
    int updateLawCheckCode(Lawstandard lawstandard);

    /**
     * 保存索引
     * @param id
     * @return
     */
    int SaveSolrTextById(String id);

    /**
     * 删除索引
     * @param id
     * @return
     */
    int DeleteSolrTextById(String id);

    /**
     *通过法规状态获取所有的法规
     * @return
     */
    List<Lawstandard> getLawsByStaus(String id);

    /**
     *通过发布部门获取所有的法规
     * @return
     */
    List<Lawstandard> getLawsByPubdep(String id);

    /**
     * 通过类别获取所有的法规
     * @param id
     * @return
     */
    List<Lawstandard> getLawsByLawType(String id);

    /**
     * 获取历史法规
     * @return
     */
    List<Lawstandard> getHistroyLaws();

    /**
     * 更新发布部门
     * @param lawstandard
     * @return
     */
    int updatePublishByOldID(Lawstandard lawstandard);

    /**
     * 更新替代关系sid
     * @param lawstandard
     * @return
     */
    int updateReplaceSidByOldID(Lawstandard lawstandard);

    /**
     * 更新替代关系pid
     * @param lawstandard
     * @return
     */
    int updateReplacePidByOldID(Lawstandard lawstandard);

    /**
     * 同步后更新solr
     * @return
     */
    int updateLawsolr();

    /**
     * 更新录入人
     * @param lawstandard
     * @return
     */
    int updateLawUser(Lawstandard lawstandard);

    /**
     * 保存时，替换临时法规
     * @return
     */
    Lawstandard getTempLaw(String code);

    /**
     * 更新引用
     * @param lawstandard
     * @return
     */
    int updateRefenceByOldID(Lawstandard lawstandard);

    /***********************处理***************************/

    /**
     * 获取所有需要处理的法规
     * @return
     */
     List<Lawstandard> handleHistoryLawList();

    /**
     * 获取历史法规
     * @param id
     * @return
     */
     HistroyLawType selectHistroyLaw(String id);

    /**
     * 获取历史法规
     * @param histroyLawType
     * @return
     */
     String getHistroyLawType(HistroyLawType histroyLawType);

    /**
     * 更新历史法规的编号
     * @param lawstandard
     * @return
     */
     int updateLawHistroyCode(Lawstandard lawstandard);

     String getDataChangeTypeid();

    /**
     * 获取有审核权限的人
     * @return
     */
     List<FG_User> getCheckPeople(String id);

     List<Lawstandard> getHistroyLawToUpdateCodeAndName();

     Slor getSolrById(String id);

    /**
     * 获取所有的法规
     * @return
     */
    List<Lawstandard> getAllPubLishLaw();

    List<FG_User> getAllPeopleEmail();

    /*****************统计*********************/
    List<ChartInfo> getChartByPub(Map<String, Object>  map);

    List<ChartInfo> getChartByPeople(Map<String, Object>  map);

    List<ChartInfo> getChartByPeopleNormal(Map<String, Object>  map);

    List<ChartInfo> getChartByType(Map<String, Object>  map);

    List<ChartInfo> getChartByPubTec(Map<String, Object>  map);

    List<ChartInfo> getChartByPeopleTec(Map<String, Object>  map);

    List<ChartInfo> getChartByTypeTec(Map<String, Object>  map);

    int setLawCounEmpty();

    List<Lawstandard> allPublshLaw();

    String getReleasedateByLawId(String id);
}
