package phalaenopsis.legalstatute.dao;

import java.util.List;
import java.util.Map;

import phalaenopsis.legalstatute.entity.Illegal;

public interface IllegalDao {

	int saveIllegal(Illegal illegal);

	int editIllegal(Illegal illegal);

	int deleteIllegal(String illegalId);

	int getIllegalCount(Map<String, Object> map);

	List<Illegal> getIllegalList(Map<String, Object> map);

}
