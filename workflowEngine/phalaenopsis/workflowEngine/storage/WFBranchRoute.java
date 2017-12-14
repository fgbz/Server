/**
 * Copyright 2017 www.kotei-info.com
 * 
 * All rights reserved.
 * 	
 */
package phalaenopsis.workflowEngine.storage;

import com.alibaba.fastjson.JSON;

/**
 * @author yangw786
 *
 * 2017年4月5日下午1:18:32
 * 流程分支路线信息
 */
public class WFBranchRoute {
	/**
	 * 流程是从那个节点分支的
	 */
    public String NodeID;

    /**
     * 流程走的是节点的哪个分支 
     */
    public String BranchName; 

    /**
     * 流程走到分支的第几个节点了（从0开始） 
     */
    public int Index;

    /**
     * 转换为json字符串进行存储
     */
	public String toString() {
		return JSON.toJSONString(this);
	}

    public static WFBranchRoute from(String route) {
        if (route == null || route.equals(""))
            return null; 
        return JSON.parseObject(route, WFBranchRoute.class);
    }

	public String getNodeID() {
		return NodeID;
	}

	public void setNodeID(String nodeID) {
		NodeID = nodeID;
	}

	public String getBranchName() {
		return BranchName;
	}

	public void setBranchName(String branchName) {
		BranchName = branchName;
	}

	public int getIndex() {
		return Index;
	}

	public void setIndex(int index) {
		Index = index;
	}
    
}	
