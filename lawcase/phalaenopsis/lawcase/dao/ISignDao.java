package phalaenopsis.lawcase.dao;

import org.apache.ibatis.annotations.Param;

import phalaenopsis.lawcase.entity.Signature;


public interface ISignDao {
	
	int getSignCount(@Param("caseID") String caseID, @Param("signLinkID")  String signLinkID, @Param("node") String node);
	
	int saveSign(Signature sign);

}
