package phalaenopsis.fgbz.dao;

import phalaenopsis.fgbz.entity.FgbzDictory;
import phalaenopsis.fgbz.entity.LawstandardStatus;
import phalaenopsis.fgbz.entity.Publishdep;

import java.util.List;
import java.util.Map;

/**
 * Created by 13260 on 2017/12/13.
 */
public interface FgbzDicDao {

    List<FgbzDictory>  getPublishDep();

    List<FgbzDictory> getLawstandardState();

    /**
     * 获取发布部门list
     * @param map
     * @return
     */
    List<Publishdep>  getPublishdepList(Map<String, Object> map);

    /**
     * 获取所有发布部门数量
     * @param map
     * @return
     */
    int getPublishdepListCount(Map<String, Object> map);

    /**
     * 删除发布部门
     * @param id
     * @return
     */
    int DeletePublishdepByID(String id);

    /**
     * 新增或修改发布部门
     * @param publishdep
     * @return
     */
    int SaveOrUpdatePublishdep(Publishdep publishdep);

    /**
     * 处理发布部门
     * @return
     */
    int handPubLevel(Publishdep publishdep);

    /**
     * 获取单位
     * @param id
     * @return
     */
    Publishdep getPublishdepById(String id);

    int getMaxPubNum();

    /**
     * 验证发布部门名称是否重复
     * @return
     */
    int checkPublichName(Publishdep publishdep);

    /**********************************状态*******************************/
    /**
     * 获取状态list
     * @param map
     * @return
     */
    List<LawstandardStatus>  getLawstandardStatusList(Map<String, Object> map);

    /**
     * 获取所有状态数量
     * @param map
     * @return
     */
    int getLawstandardStatusListCount(Map<String, Object> map);

    /**
     * 删除状态
     * @param id
     * @return
     */
    int DeleteLawstandardStatusByID(String id);

    /**
     * 新增或修改状态
     * @param lawstandardStatus
     * @return
     */
    int SaveOrUpdateLawstandardStatus(LawstandardStatus lawstandardStatus);

    /**
     * 验证状态是否重复
     * @return
     */
    int checkLawstandardStatusName(LawstandardStatus lawstandardStatus);

    /**
     * 获取配置
     * @param key
     * @return
     */
    String getSettingByKey(String key);
}
