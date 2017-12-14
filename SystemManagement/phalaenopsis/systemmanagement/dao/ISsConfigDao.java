package phalaenopsis.systemmanagement.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import phalaenopsis.systemmanagement.entity.SsConfig;

public interface ISsConfigDao {
	 /**
	  * 保存或更新信息
	  * @param config
	  */
	 int saveOrUpdate(SsConfig config);
	 /**
	  * 查询所有配置项信息
	  * @return
	  */
	 List<SsConfig> getList();
	 /**
	  * 获取单个配置
	  * @param type
	  * @return
	  */
	 SsConfig getSsConfigByType(@Param("type")String type);
}
