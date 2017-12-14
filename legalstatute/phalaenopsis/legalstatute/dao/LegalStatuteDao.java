package phalaenopsis.legalstatute.dao;

import java.util.List;
import java.util.Map;

import phalaenopsis.illegalclue.entity.ResultState;
import phalaenopsis.legalstatute.entity.LegalStatute;


public interface LegalStatuteDao {

	int saveLegalStatute(LegalStatute statute);

	LegalStatute getLegalStatuteById(String id);

	int editLegalStatuteById(LegalStatute statute);

	int deleteLegalStatute(String id);

	List<LegalStatute> getLegalStatuteByLegalStatute(LegalStatute legalStatute);

	int updateLegalStatuteById(LegalStatute statute);

	List<LegalStatute> searchLegalStatuteByStr(String each);

	List<LegalStatute> getLegalTree();

	Integer getLegalStatuteSort(String parentId);

	LegalStatute getLegalStatuteSortUp(Map<String, Object> map);

	int updateLegalStatuteSort(Map<String, Object> map);

	LegalStatute getLegalStatuteSortDown(Map<String, Object> map1);

	List<LegalStatute> getLegalStatuteByIdList(Long[] idList);

	Integer getLegalStatuteTopSort();

	LegalStatute getLegalStatuteTopSortUp(Map<String, Object> map1);

	LegalStatute getLegalStatuteTopSortDown(Map<String, Object> map1);

}
