/**
 * Copyright 2017 www.kotei-info.com
 * 
 * All rights reserved.
 * 	
 */
package phalaenopsis.workflowEngine.storage;

import java.util.List;

import org.springframework.stereotype.Component;

/**
 * @author yangw786
 *
 * 2017年4月5日下午1:38:53
 * 工作流数据存储程序
 */
public interface IWorkflowStorage {
	/**
	 * 创建流程数据 
	 * @param instance
	 * @param history
	 */
    void create(WFInstance instance, WFHistory history);

    /**
     * 更新保存流程数据
     * @param instance
     * @param history
     */
    void save(WFInstance instance, WFHistory history);

    WFInstance getInstance(String instanceID);

    /**
     * 已经排序
     * @param instanceID
     * @return
     */
    List<WFHistory> getHistories(String instanceID);


    void delete(String instanceID);

    //保存流程信息，并删除最后一条流转历史，用于退回上一步时不保留节点流转过程
    void saveAndRemoveLastHistory(WFInstance instance, WFHistory lastHistory);
}
