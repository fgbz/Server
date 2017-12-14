package phalaenopsis.workflowEngine.core;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import phalaenopsis.common.entity.Condition;
import phalaenopsis.workflowEngine.storage.WFInstance;

/**
 * 
 * @author chunhongl
 *
 * 2017年4月6日下午4:44:02
 * 
 * 工作流节点数据上下文，用于在流程节点流转过程中传递数据
 */

public class NodeDataContext {
	// 上一个节点传递的FlowData
	String fromNodeData;
	// 本次节点传递给下一个节点的FlowData
	String toNodeData;

	private WFInstance instanceData;

	public NodeDataContext(WFInstance instanceData) {
		this.instanceData = instanceData;
		this.ServerNamedDatas = new ArrayList<Condition>();
	}

	/**
	 * 流程实例ID
	 */
	public String InstanceID;

	/**
	 * 获取或设置在本次流转中，服务端传递给流程节点的数据
	 */
	public Object ServerData;

	/**
	 * 获取或设置在本次流转中，服务端传递给流程节点的多个命名的数据
	 */
	public List<Condition> ServerNamedDatas;
	
	public String getInstanceID() {
		return InstanceID;
	}

	public void setInstanceID(String instanceID) {
		InstanceID = instanceID;
	}

	public Object getServerData() {
		return ServerData;
	}

	public void setServerData(Object serverData) {
		ServerData = serverData;
	}

	public List<Condition>getServerNamedDatas() {
		return ServerNamedDatas;
	}

	public void setServerNamedDatas(List<Condition> serverNamedDatas) {
		ServerNamedDatas = serverNamedDatas;
	}

	public void SetFlowData(String name, Object data) {
	}

	/// <summary>
	/// 获取客户端传递给流程节点的数据。
	/// 如果成功返回true，如果没有传递数据，返回false。
	/// </summary>
	/// <typeparam name="T"></typeparam>
	/// <param name="data"></param>
	/// <returns></returns>
	
//	public bool TryGetClientData<T>(
//	out T data)
//	{
//            ClientDataStore dataStore = ClientDataStore.GetCurrent();
//            if (dataStore == null) {
//                data = default(T);
//                return false;
//            }
//            return dataStore.TryGetClientData<T>(out data);
//        }

	/// <summary>
	/// 获取客户端传递给流程节点的命名的数据。
	/// 如果成功返回true，如果没有该命名的数据，返回false。
	/// </summary>
	/// <typeparam name="T"></typeparam>
	/// <param name="name"></param>
	/// <param name="data"></param>
	/// <returns></returns>
	
//	public bool TryGetClientNamedData<T>(
//	string name, out
//	T data)
//	{
//            ClientDataStore dataStore = ClientDataStore.GetCurrent();
//            if (dataStore == null) {
//                data = default(T);
//                return false;
//            }
//            return dataStore.TryGetClientNamedData<T>(name, out data);
//        }

	/// <summary>
	/// 获取流程实例从上一个节点传递到该节点的命名数据
	/// </summary>
	/// <typeparam name="T"></typeparam>
	/// <param name="name"></param>
	/// <param name="data"></param>
	/// <returns></returns>
	
//	public bool TryGetFlowData<T>(
//	string name, out
//	T data)
//	{
//            //获取或设置由工作流引擎传递给下一个节点的数据
//            //只传递一次
//            if (string.IsNullOrEmpty(instanceData.FlowData)) {
//                data = default(T);
//                return false;
//            }
//            JObject obj = JObject.Parse(instanceData.FlowData);
//            JToken token;
//            if (obj.TryGetValue(name, out token)) {
//                data = token.ToObject<T>();
//                return true;
//            }
//            else {
//                data = default(T);
//                return false;
//            }
//        }

	/// <summary>
	///
	/// </summary>
	/// <typeparam name="T"></typeparam>
	/// <param name="name"></param>
	/// <param name="data"></param>
	
//	public void SetFlowData<T>(
//	string name, T data)
//	{
//	}

	

	/// <summary>
	/// 获取流程实例流转时在所有节点共享的命名数据
	/// </summary>
	/// <typeparam name="T"></typeparam>
	/// <param name="name"></param>
	/// <param name="data"></param>
	/// <returns></returns>
	
//	public bool TryGetInstanceData<T>(
//	string name, out
//	T data)
//	{
//            data = default(T);
//            return false;
//        }

	/// <summary>
	/// 设置流程实例流转时的共享命名数据
	/// </summary>
	/// <typeparam name="T"></typeparam>
	/// <param name="name"></param>
	/// <param name="data"></param>
	
//	public void SetInstanceData<T>(
//	string name, T data)
//	{

		// object 序列化为json后保存在工作流表中，作为节点的附加状态数据
		// 可以代替前端在遇到分支节点时，必须赋值的情况，可以在服务端处理某个节点时，直接给下一个节点传递持久化数据
//	}

	/// <summary>
	/// 设置流程实例流转时的共享命名数据
	/// </summary>
	/// <param name="name"></param>
	/// <param name="data"></param>
	public void SetInstanceData(String name, Object data) {

	}
}
