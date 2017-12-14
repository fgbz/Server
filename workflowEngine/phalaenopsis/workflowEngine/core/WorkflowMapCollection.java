/**
 * Copyright 2017 www.kotei-info.com
 * 
 * All rights reserved.
 * 	
 */
package phalaenopsis.workflowEngine.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author yangw786
 *
 *         2017年4月6日下午4:01:07
 */
public class WorkflowMapCollection {
	// 流程图集合
	private Map<String, IWorkflowMap> mapCollection;

	public WorkflowMapCollection() {
		mapCollection = new TreeMap<String, IWorkflowMap>();
	}

	public void add(IWorkflowMap item) {
		if (item != null) {
			mapCollection.put(item.getMapID(), item);
		}
	}

	public void clear() {
		mapCollection.clear();
	}

	public boolean contains(IWorkflowMap item) {
		if (item != null) {
			return mapCollection.containsKey(item.getMapID());
		}
		return false;
	}

	public boolean contains(String mapID) {
		return mapCollection.containsKey(mapID);
	}

	public void copyTo(IWorkflowMap[] array, int arrayIndex) {
		IWorkflowMap[] maps = new IWorkflowMap[arrayIndex + mapCollection.size()];
		System.arraycopy(array, 0, maps, 0, arrayIndex);
		System.arraycopy(mapCollection.values(), 0, maps, arrayIndex, mapCollection.size());
		array = maps;
		// mapCollection.Select(p => p.Value).ToArray().CopyTo(array,
		// arrayIndex);
	}

	public int count() {
		return mapCollection.size();
	}

	public boolean isReadOnly() {
		return false;
	}

	public boolean remove(IWorkflowMap item) {
		if (item != null) {
			return mapCollection.remove(item.getMapID()) != null ? true : false;
		}
		return false;
	}

	public List<IWorkflowMap> getEnumerator() {
		List<IWorkflowMap> result = new ArrayList<IWorkflowMap>();
		for (Map.Entry<String, IWorkflowMap> item : mapCollection.entrySet()) {
			result.add(item.getValue());
		}
		return result;
	}

	/// <summary>
	/// 获取mapID对应的流程图
	/// </summary>
	/// <param name="mapID"></param>
	/// <returns></returns>
	public IWorkflowMap get(String mapID) {
		if (mapID == null || mapID.equals("")) {
			return null;
		}
		return mapCollection.get(mapID);
	}
}
