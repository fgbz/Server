package phalaenopsis.fgbz.dao;

import phalaenopsis.fgbz.entity.Adviceinfo;

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
}
