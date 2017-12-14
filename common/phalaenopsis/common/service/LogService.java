package phalaenopsis.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import phalaenopsis.common.dao.LogDao;
import phalaenopsis.common.entity.Ss_HITS;


@Service("logService")
public class LogService {
	
	
	@Autowired
	private LogDao logDao;
	
	/**
	 * 添加日志信息
	 * 
	 * @param user
	 * @return
	 */
	public void addLog(Ss_HITS record) {
		 logDao.insertSelective(record);
	}
	
	

}
