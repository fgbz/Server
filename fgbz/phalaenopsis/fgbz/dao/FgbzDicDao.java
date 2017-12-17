package phalaenopsis.fgbz.dao;

import phalaenopsis.fgbz.entity.FgbzDictory;
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
     * 验证发布部门名称是否重复
     * @return
     */
    int checkPublichName(Publishdep publishdep);

}
