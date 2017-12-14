package phalaenopsis.visitSystem.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import phalaenopsis.common.entity.Serialnumber;
import phalaenopsis.visitSystem.entity.XfRegister;
import phalaenopsis.visitSystem.entity.XfRepeatItems;

/**
 * 
 * @author dongdongt
 *
 */
public interface LetterCaseMapper {
	 /**
	  * 查询信访列表
	  * @param map
	  * @return
	  */
	 List<XfRegister> getList(Map<String, Object> map);
	 
	 /**
	  * 查询总条数
	  * @param map
	  * @return
	  */
	 int getCount(Map<String, Object> map);
	 
	 /**
	  * 保存或更新登记信息
	  * @param register
	  */
	 int saveOrUpdate(XfRegister register);
	 /**
	  * 根据ID查询信访信息
	  * @param id
	  * @return
	  */
	 XfRegister getLetterCaseById(@Param("id") Long id);
	 
	 /**
	  * 查询重复信访
	  * @param map
	  * @return
	  */
	 List<XfRegister> getRepeatList(Map<String,Object> map);
	 /**
	  * 获取
	  * @return
	  */
	 Serialnumber getMaxNum(@Param("type") String type);
	 /**
	  * 添加流水单号初始记录
	  * @param serialnumber
	  * @return
	  */
	 int insert(Serialnumber serialnumber);
	 /**
	  * 修改流水单号记录
	  * @param serialnumber
	  * @return
	  */
	 void update(Serialnumber serialnumber);
	 
	 int deleteXfRegister(@Param("registerid") String registerid);
	 /**
	  * 查询重复件列表
	  * @param registerid
	  * @return
	  */
	 List<XfRepeatItems> selectRepeatItems(@Param("xfregisterid") Long registerid);
	 
	 /**
	  * 删除重复信访信息(当信访信息被删除时要删除对应的重复信访历史信息)
	  * @param registerid
	  * @return
	  */
	 int deleteRepeatItems(@Param("registerid") String registerid);
	 /**
	  * 保存重复件
	  * @param repeatItems
	  * @return
	  */
	 int saveXfRepeatItems(XfRepeatItems repeatItems);
}
