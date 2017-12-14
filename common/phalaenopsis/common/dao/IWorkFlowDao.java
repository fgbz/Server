package phalaenopsis.common.dao;

import java.util.List;

import phalaenopsis.workflowEngine.storage.WFHistory;
import phalaenopsis.workflowEngine.storage.WFInstance;


public interface IWorkFlowDao {

	public void saveOrUpdateWFInstance(WFInstance instance);

	public void saveWFHistory(WFHistory history);

	public WFInstance getInstance(String instanceID);


	public List<WFHistory> getHistories(String instanceID);
	
	public void deleteInstance(String instanceID);
	
	public void deleteHistory(String instanceID);
	
	public void deleteLastHistory(WFHistory lastHistory);

	// public void delete(String instanceID);

}
