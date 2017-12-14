package phalaenopsis.fgbz.dao;

import phalaenopsis.fgbz.entity.*;

import java.util.List;
import java.util.Map;

public interface LawstandardDao {

    int testCount();

    List<LawstandardType> SelectLawstandardType();

    int  AddOrUpdateLawstandardType(LawstandardType lawstandardType);

    int  DeleteLawstandardType(LawstandardType lawstandardType);

    int  getLawstandardListCount(Map<java.lang.String, Object>  map);

    List<Lawstandard> getLawstandardList(Map<java.lang.String, Object>  map);

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
     * 保存法规文件与部门关联
     * @param lawstandard
     * @return
     */
    int SaveOrUpdateLawAndPublish(Lawstandard lawstandard);

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

    int deleteRefence(String id);

    int deleteReplace(String id);

    int addRefence(RefenceOrReplace refenceOrReplace);

    int addReplace(RefenceOrReplace refenceOrReplace);

    /**
     * 获取子节点
     * @param id
     * @return
     */
    List<LawstandardType> getChildNode(String id);

    /**
     * 点击次数加一
     * @param lawstandard
     */
    void AddLawstandardCount(Lawstandard lawstandard);

    void LawstandardIsTop(Lawstandard lawstandard);

    /**
     * 保存附件与法规的关系
     * @param lawstandard
     * @return
     */
    int SaveFileLink(Lawstandard lawstandard);

}