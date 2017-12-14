/**
 * Copyright 2017 www.kotei-info.com
 * 
 * All rights reserved.
 * 	
 */
package phalaenopsis.workflowEngine.storage;

/**
 * @author yangw786
 *
 * 2017年4月5日下午1:06:14
 * 流程实例状态
 */
public class WFInstanceState {
	/**
	 * 流程已经发起，已经进入开始节点（流程实例数据已经创建）
	 */
	public static final int Created = 1; 
     
    /**
     * 流程正在进行 
     */
	public static final int Running = 2;
    /**
     * 流程已经完成结束
     */
	public static final int Finished = 3;
}
