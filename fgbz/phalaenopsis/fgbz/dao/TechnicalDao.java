package phalaenopsis.fgbz.dao;

import phalaenopsis.fgbz.entity.Lawstandard;
import phalaenopsis.fgbz.entity.Technical;
import phalaenopsis.fgbz.entity.TechnicalType;

import java.util.List;
import java.util.Map;

public interface TechnicalDao {

    /**
     * 查询所有的技术文件
     * @return
     */
    List<TechnicalType> SelectTechnicalType();

    /**
     * 新增或修改技术文件
     * @param technicalType
     * @return
     */
    int  AddOrUpdateTechnicalType(TechnicalType technicalType);

    /**
     * 删除技术文件类别
     * @param technicalType
     * @return
     */
    int  DeleteTechnicalType(TechnicalType technicalType);

    /**
     * 获取子节点
     * @param id
     * @return
     */
    List<TechnicalType> getChildNode(String id);

    /**
     * 获取技术文件列表总数
     * @param map
     * @return
     */
    int  getTechnicalListCount(Map<String, Object> map);

    /**
     * 获取技术文件列表
     * @param map
     * @return
     */
    List<Technical> getTechnicalList(Map<String, Object>  map);

    /**
     * 新增或编辑技术文件
     * @param technicalType
     * @return
     */
    int SaveOrUpdateTechnical(TechnicalType technicalType);

    /**
     * 通过id删除技术文件
     * @param id
     * @return
     */
    int  DeleteTechnicalById(String id);

    /**
     * 保存技术文件与类别关联
     * @param technicalType
     * @return
     */
    int SaveOrUpdateTecAndType(TechnicalType technicalType);

    /**
     * 删除技术文件与类别关联
     * @param id
     * @return
     */
    int DeleteTecAndType(String id);

    /**
     * 查看
     * @param id
     * @return
     */
    Technical getTechnicalById(String id);
}