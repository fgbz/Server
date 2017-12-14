package phalaenopsis.common.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import phalaenopsis.common.dao.IWorkFlowDao;
import phalaenopsis.workflowEngine.storage.IWorkflowStorage;
import phalaenopsis.workflowEngine.storage.WFHistory;
import phalaenopsis.workflowEngine.storage.WFInstance;

/**
 * 工作流数据存储程序（Oracle）
 * 
 * @author chunl
 *
 */
@Component("WFOracleDBStorage")
public class WFOracleDBStorage implements IWorkflowStorage {

	@Autowired
	private IWorkFlowDao workflowDao;

	@Override
	public void create(WFInstance instance, WFHistory history) {
		
		this.save(instance, history);

	}

	@Override
	public void save(WFInstance instance, WFHistory history) {
		workflowDao.saveOrUpdateWFInstance(instance);
		workflowDao.saveWFHistory(history);
	}

	@Override
	public WFInstance getInstance(String instanceID) {
		return workflowDao.getInstance(instanceID);
	}

	@Override
	public List<WFHistory> getHistories(String instanceID) {
		return workflowDao.getHistories(instanceID);
	}

	@Override
	@Transactional
	public void delete(String instanceID) {
		workflowDao.deleteInstance(instanceID);
		workflowDao.deleteHistory(instanceID);
	}

	@Override
	@Transactional
	public void saveAndRemoveLastHistory(WFInstance instance, WFHistory lastHistory) {
		workflowDao.saveOrUpdateWFInstance(instance);
		workflowDao.deleteLastHistory(lastHistory);
	}

}
