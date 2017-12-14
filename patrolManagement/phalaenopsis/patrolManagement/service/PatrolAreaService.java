package phalaenopsis.patrolManagement.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import phalaenopsis.common.dao.OrganizationDao;
import phalaenopsis.common.entity.Condition;
import phalaenopsis.common.entity.OrganizationGrade;
import phalaenopsis.common.entity.Page;
import phalaenopsis.common.entity.PagingEntity;
import phalaenopsis.common.entity.TreeNode;
import phalaenopsis.common.entity.User;
import phalaenopsis.common.method.Tools.UUID64;
import phalaenopsis.illegalclue.entity.ResultState;
import phalaenopsis.patrolManagement.dao.PatrolAreaMapper;
import phalaenopsis.patrolManagement.entity.PatrolArea;

@Service("patrolAreaService")
public class PatrolAreaService {
	
	@Autowired
	private PatrolAreaMapper mapper;
	
	@Autowired
	private OrganizationDao organizationDao;
	
	/**
	 * 巡查区域列表
	 * @param page
	 * @param user
	 * @return
	 */
	public PagingEntity<PatrolArea> getAreaList(Page page, User user) {
		//String parentID = user.getOrganizationID();
		//List<TreeNode> orgsTrees = organizationController.GetOrgsTreeByParentID(parentID );
		Map<String, Object> map = new HashMap<String, Object>();
		for (Condition condition : page.getConditions()) {
			map.put(condition.getKey(), condition.getValue());
		}
		if(map.get("patrolLevel").equals("3")){
			map.put("patrolLevel", null);
		}
		map.put("startNum", page.getPageSize() * (page.getPageNo() - 1) + 1);
		map.put("endNum", page.getPageSize() * page.getPageNo());
		String organizationID = user.getOrganizationID();
		map.put("organizationID", organizationID);
		
		int count = 0;
		List<PatrolArea> list; 
		if (OrganizationGrade.Province == user.getOrgGrade()) {
			//查找所有的巡查区域
			count = mapper.getAllAreaCount(map);
			list = mapper.getAllAreaList(map);
		}else{
			//查找该市及其下级的巡查区域
			count = mapper.getAreaCount(map);
			list = mapper.getAreaList(map);
		}
		
		PagingEntity<PatrolArea> entity = new PagingEntity<PatrolArea>();
		entity.PageNo = page.getPageNo();//第几页
		entity.PageSize = page.getPageSize();//每页显示数
		entity.PageCount = count == 0 ? 0 : (count - 1) / page.getPageSize() + 1; // 由于计算pageCount存在整除的情况，所以计算的时候先减1在除以pageSize
		entity.RecordCount = count;//记录总条数
		entity.CurrentList = list;
		entity.PageCount = (entity.RecordCount / entity.PageSize) + (entity.RecordCount % entity.PageSize > 0 ? 1 : 0);//总页数
		return entity;
	}
	
	/**
	 * 删除巡查区域
	 * @param id
	 * @return
	 */
	public ResultState deleteArea(String id) {
		int i = mapper.deleteArea(id);
		if(i == 1){
			return ResultState.Success;
		}else{
			return ResultState.Failed;
		}
	}
	
	/**
	 * 获取巡查单位
	 * @param user
	 * @return
	 */
	public List<TreeNode> getPatrolOrg(User user) {
		String parentID = user.getOrganizationID();
		List<TreeNode> treeNode = organizationDao.GetOrgsTreeByParentID(parentID);
		return treeNode;
	}
	
	/**
	 * 保存巡查区域
	 * @param patrolarea
	 * @return
	 */
	public ResultState saveArea(PatrolArea patrolarea) {
		if(patrolarea == null){
			return ResultState.Failed;
		}
		patrolarea.setId(UUID64.newUUID64().getValue());
		Date createDate = new Date();
		patrolarea.setCreateDate(createDate);
		int i = mapper.saveArea(patrolarea);
		if(i == 1){
			return ResultState.Success;
		}else{
			return ResultState.Failed;
		}
	}

}
