package phalaenopsis.legalstatute.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import phalaenopsis.common.entity.Condition;
import phalaenopsis.common.entity.Page;
import phalaenopsis.common.entity.PagingEntity;
import phalaenopsis.illegalclue.entity.ResultState;
import phalaenopsis.legalstatute.dao.IllegalDao;
import phalaenopsis.legalstatute.entity.Illegal;
import phalaenopsis.legalstatute.entity.LegalStatute;
import phalaenopsis.patrolManagement.entity.PatrolRecord;

@Service("illegalService")
public class IllegalService {
	
	@Autowired
	private IllegalDao dao;
	
	public ResultState saveIllegal(Illegal statute) {
		if(statute == null){
			return ResultState.Failed;
		}
		int i = dao.saveIllegal(statute);
		
		if(i == 1){
			return ResultState.Success;
		}else{
			return ResultState.Failed;
		}
	}

	public ResultState editIllegal(Illegal statute) {
		if(statute == null){
			return ResultState.Failed;
		}
		int i = dao.editIllegal(statute);
		if(i == 1){
			return ResultState.Success;
		}else{
			return ResultState.Failed;
		}
	}

	public ResultState deleteIllegal(String id) {
		int i = dao.deleteIllegal(id);
		if(i == 1){
			return ResultState.Success;
		}else{
			return ResultState.Failed;
		}
	}

	public PagingEntity<Illegal> getIllegalList(Page page) {
		Map<String, Object> map = new HashMap<String, Object>();
		for (Condition condition : page.getConditions()) {
			map.put(condition.getKey(), condition.getValue());
		}
		map.put("startNum", page.getPageSize() * (page.getPageNo() - 1) + 1);
		map.put("endNum", page.getPageSize() * page.getPageNo());
		int count = 0;
		count = dao.getIllegalCount(map);
		List<Illegal> list = dao.getIllegalList(map);
		
		PagingEntity<Illegal> entity = new PagingEntity<Illegal>();
		entity.PageNo = page.getPageNo();//第几页
		entity.PageSize = page.getPageSize();//每页显示数
		entity.PageCount = count == 0 ? 0 : (count - 1) / page.getPageSize() + 1; // 由于计算pageCount存在整除的情况，所以计算的时候先减1在除以pageSize
		entity.RecordCount = count;//记录总条数
		entity.CurrentList = list;
		entity.PageCount = (entity.RecordCount / entity.PageSize) + (entity.RecordCount % entity.PageSize > 0 ? 1 : 0);//总页数
		return entity;
	}

}
