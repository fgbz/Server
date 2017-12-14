package phalaenopsis.common.dao;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import phalaenopsis.common.entity.Ss_HITS;

@Repository("logDao")

public class LogDao {
	
	
	@Resource
	private SqlSession sqlSession;
	
	
	
	/**
	 *   v_sessionid varchar2, -- 访问sessionID
        v_url IN  VARCHAR2, -- 访问的uri
        v_useragent in varchar2 , --用户UA数据
        v_side in varchar2, --是否内外网 in-内网 out -外网
        v_processtime number, --处理时间
        v_username in varchar2, --用户名
        v_name in varchar2, --登录用户名
        v_ip  in varchar2 --IP地址
	 * 
	 * 
	 * 
	 * 
	 * @param record
	 * @return
	 */
	public void insertSelective(Ss_HITS record){
		final Map<String, Object> param = new HashMap<String, Object>();  
		param.put("v_sessionid", record.getSessionid());
		param.put("v_url", record.getUrl());
		param.put("v_useragent", record.getUseragent());
		param.put("v_side", record.getSide());
		param.put("v_processtime", record.getProcesstime());
		param.put("v_username", record.getUsername());
		param.put("v_name", record.getUsername());
		param.put("v_ip", record.getIp());
	    sqlSession.selectOne("SS_HITS.loggingAccess", param);
	}
	

}
