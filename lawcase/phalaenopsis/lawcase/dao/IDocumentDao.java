package phalaenopsis.lawcase.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import phalaenopsis.lawcase.entity.CaseDocument;

@Repository
@Transactional
public interface IDocumentDao  extends IBaseDao<CaseDocument>{
	

}
