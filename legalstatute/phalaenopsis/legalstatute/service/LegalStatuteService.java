package phalaenopsis.legalstatute.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import phalaenopsis.common.method.Tools.UUID64;
import phalaenopsis.illegalclue.entity.ResultState;
import phalaenopsis.legalstatute.dao.LegalStatuteDao;
import phalaenopsis.legalstatute.entity.LegalStatute;
import phalaenopsis.legalstatute.entity.LegalStatuteList;

@Service("legalStatuteService")
public class LegalStatuteService {
	@Autowired
	private LegalStatuteDao dao;
	
	/**
	 * 新增法律
	 * @param statute
	 * @return
	 */
	public ResultState saveLegalStatute(LegalStatute statute) {
		if(statute == null){
			return ResultState.Failed;
		}
		Integer sort = 1;
		if(statute.getParentId() != null && !statute.getParentId().equals("")){
			//通过parentId获取当前顺序最大值
			Integer sortNum = dao.getLegalStatuteSort(statute.getParentId());
			if(sortNum != null && sortNum != 0){
				sort = sortNum + 1;
			}
		}else{
			Integer sortNum = dao.getLegalStatuteTopSort();
			if(sortNum != null && sortNum != 0){
				sort = sortNum + 1;
			}
		}
		statute.setSort(sort);
		int i = dao.saveLegalStatute(statute);
		
		if(i == 1){
			return ResultState.Success;
		}else{
			return ResultState.Failed;
		}
	}
	
	/**
	 * 编辑法律条文
	 * @param statute
	 * @return
	 */
	public ResultState editLegalStatute(LegalStatute statute) {
		if(statute == null || statute.getId() == null){
			return ResultState.Failed;
		}
		int i = dao.updateLegalStatuteById(statute);
		if(i == 1){
			return ResultState.Success;
		}else{
			return ResultState.Failed;
		}
	}  
	
	/**
	 * 删除法律条文
	 * @param id
	 * @return
	 */
	public ResultState deleteLegalStatute(String id) {
		int i = dao.deleteLegalStatute(id);
		if(i != 0){
			return ResultState.Success;
		}else{
			return ResultState.Failed;
		}
	}
	
	/**
	 * 检索法律条文
	 * @param id
	 * @return
	 */
	public List<LegalStatute> getLegalStatuteList(String condition) {
		String[] a = condition.split(" ");
		List<LegalStatuteList> lists = new ArrayList<LegalStatuteList>();
		for (String each:a) {
			LegalStatuteList legalStatuteList = new LegalStatuteList();
			System.out.println(each);
			List<LegalStatute> statute = dao.searchLegalStatuteByStr(each);
			if(statute != null && !statute.equals("")){
				legalStatuteList.setLegalStatuteList(statute);
				lists.add(legalStatuteList);
			}
		}
		List<LegalStatute> tempList = new ArrayList<LegalStatute>();
		if(lists.size() == 1){
			tempList = lists.get(0).getLegalStatuteList();
			Long[] idList = new Long[tempList.size()];
			for (int i = 0; i < tempList.size(); i++) {
				Long id = tempList.get(i).getId();
				idList[i] = id;
			}
			List<LegalStatute> results = dao.getLegalStatuteByIdList(idList);
			return results;
		}
		for (int i = 0; i < lists.size() - 1; i++) {
			List<LegalStatute> resultList = new ArrayList<LegalStatute>();
			if(i == 0){
				tempList = lists.get(0).getLegalStatuteList();
			}
			List<LegalStatute> nextList = lists.get(i+1).getLegalStatuteList();
			for (int j = 0; j < tempList.size(); j++) {
				Long idj = tempList.get(j).getId();
				for (int k = 0; k < nextList.size(); k++) {
					if(idj == nextList.get(k).getId() || idj.equals(nextList.get(k).getId())){
						resultList.add(nextList.get(k));
						break;
					}
				}
			}
			tempList = resultList;
		}
		Long[] idList = new Long[tempList.size()];
		for (int i = 0; i < tempList.size(); i++) {
			Long id = tempList.get(i).getId();
			idList[i] = id;
		}
		List<LegalStatute> results = dao.getLegalStatuteByIdList(idList);
		return results;
	}
	
	/**
	 * 获取所有法律法规
	 * @return
	 */
	public List<LegalStatute> getLegalTree() {
		List<LegalStatute> legalStatutes = dao.getLegalTree();
		return legalStatutes;
	}
	
	
	/**
	 * 上移下移法律法规
	 * @param LegalStatute
	 * @return
	 */
	public ResultState moveLegalStatute(String upOrDown, String parentId, String id){
		Map<String, Object> map1 = new HashMap<String, Object>();
		Map<String, Object> map2 = new HashMap<String, Object>();
		LegalStatute statute  = dao.getLegalStatuteById(id);
		Integer sort = statute.getSort();
		map1.put("sort", sort);
		map1.put("id", id);
		map1.put("parentId", parentId);
		LegalStatute legalStatutes = new LegalStatute();
		if(upOrDown != null && upOrDown.equals("up")){
			if(parentId == null || parentId.equals("")){
				legalStatutes = dao.getLegalStatuteTopSortUp(map1);
			}else{
				legalStatutes = dao.getLegalStatuteSortUp(map1);
			}
		}else{
			if(parentId == null || parentId.equals("")){
				legalStatutes = dao.getLegalStatuteTopSortDown(map1);
			}else{
				legalStatutes = dao.getLegalStatuteSortDown(map1);
			}
		}
		map1.put("sort", legalStatutes.getSort());
		int i = dao.updateLegalStatuteSort(map1);
		//map2.put("parentId", legalStatutes.getParentId());
		map2.put("sort", sort);
		map2.put("id", legalStatutes.getId());
		int j = dao.updateLegalStatuteSort(map2);
		if(i == 1 && j==1){
			return ResultState.Success;
		}else{
			return ResultState.Failed;
		}
	}

	/**
	 * 返回一个随机数
	 * 
	 * @param i
	 * @return
	 */
	public static String getRandom(int i) {
		Random random = new Random();
		String result = "";
		for (int k = 0; k < i; k++) {
			result = result + random.nextInt(9);
		}
		return result;
	}
	
	public static int getSecondTimestamp(Date date){  
	    if (null == date) {  
	        return 0;  
	    }  
	    String timestamp = String.valueOf(date.getTime());  
	    int length = timestamp.length();  
	    if (length > 3) { 
	    	String random = getRandom(3);
	    	String result = random + timestamp.substring(4,length-3);
	        return Integer.parseInt(result);  
	    } else {  
	        return 0;  
	    }  
	}

	

}
