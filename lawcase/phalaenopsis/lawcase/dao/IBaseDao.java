package phalaenopsis.lawcase.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 公共类
 * @author dongdongt
 *
 * @param <T>
 */
@Repository("iBaseDao")
public interface IBaseDao<T>{
	
	public int saveOrUpdate(T obj);
	/**
	 * 根据id获取
	 * @param caseID
	 * @param type
	 * @return
	 */
	public T getDocument(@Param("caseID") String caseID, @Param("type") Integer type);
	
}
