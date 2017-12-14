package phalaenopsis.workflowEngine.core;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import phalaenopsis.common.method.Tools.Linq;
import phalaenopsis.workflowEngine.configuration.WorkflowsConfigurationLoader;
import phalaenopsis.workflowEngine.controller.WFObject;
import phalaenopsis.workflowEngine.storage.IWorkflowStorage;
import phalaenopsis.workflowEngine.storage.WFInstance;

/**
 * 
 * @author chunhongl
 *
 *         2017年4月6日下午4:23:04
 * 
 *         工作流引擎实例
 */
@Service
public class WorkflowEngineInstance implements IRuntimeEnvironment {
	// 流程引擎包含的所有流程图
	private WorkflowMapCollection mapCollection;

	// 流程数据存储程序的创建器
	public IWorkflowStorage StorageBuilder;



	public IWorkflowStorage getStorageBuilder() {
		return StorageBuilder;
	}

	public void setStorageBuilder(IWorkflowStorage storageBuilder) {
		StorageBuilder = storageBuilder;
	}

	public WorkflowEngineInstance() {
		mapCollection = new WorkflowMapCollection();
		ClassPathResource resource = new ClassPathResource("conf/workflow-config.xml");
		String path;
		try {
			path = resource.getFile().getAbsolutePath();
			loadFile(path);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 从配置文件中加载流程配置
	 * 
	 * @param configFile
	 *            流程图的xml配置文件路径
	 */
	public void loadFile(String configFile) {
		// 以静态配置文件加载工作流
		load(configFile);
	}

	/**
	 * 从xml字符串中加载流程图配置
	 * 
	 * @param xml
	 */
	public void loadXml(String xml) {
	}

	/**
	 * 从配置文件中加载流程图集合
	 * 
	 * @param xmlFile
	 *            xml配置文件路径
	 */
	private void load(String xmlFile) {
		try {
			if (new File(xmlFile).exists()) {
				WorkflowsConfigurationLoader loader = new WorkflowsConfigurationLoader();
				WorkflowMapCollection workflowMapCollection = loader.loadFile(xmlFile);
				if (workflowMapCollection != null) {
					List<IWorkflowMap> list = workflowMapCollection.getEnumerator();
					for (IWorkflowMap map : list) {
						mapCollection.add(map);
					}
				}
			}

		} catch (FileNotFoundException e) {
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//@Autowired
	private FlowManager manager;
	
	
	/**
	 * 发起一个新的流程
	 * 
	 * @param mapID
	 *            要发起的流程的流程图ID
	 * @return 如果一个流程发起成功，则返回流程实例的ID
	 */
	public WFObject start(String mapID) {
		if (!mapCollection.contains(mapID))
			return null;
		
		WorkflowMap workflowMap = (WorkflowMap)mapCollection.get(mapID);  //需要替换的代码
		
		IWorkflowInstance instance = workflowMap.createInstance(this);

		WFObject wfObject = new WFObject();
		wfObject.setInstanceID(instance.getInstanceID());
		wfObject.setCurrentNode(instance.getCurrent());
		wfObject.setIsFinished(instance.getFinished());
		return wfObject;
	}

	/**
	 * 流程实例流转到下一步 返回与参数相同的同一个WFObject对象，更新其对应的属性，使得可以连续调用的功能
	 * 
	 * @param obj
	 *            要流转的流程实例
	 * @return 如果成功流转到下一步，则返回流转到的下一步的节点ID
	 */
	public WFObject next(WFObject obj) {
		if (obj == null) {
			return obj;
		}
		IWorkflowInstance instance = getInstance(obj.InstanceID);
		if (instance == null)
			return null;
		// BindDataContext(instance.getDataContext(), obj);
		String current = instance.getCurrent();// 当前节点

		String next = instance.next();
		if (next == instance.getMap().getFinishedNode()) {
			// 流程结束，更新CurrentNode为最后一个结束的节点而不是FinishedNode，更符合逻辑
			// 表示流程是从该节点结束的
			obj.CurrentNode = current;
			obj.IsFinished = true;
		} else {
			// 更新当前节点
			obj.CurrentNode = next;
			obj.IsFinished = false;
		}
		return obj;
	}

	public WFObject previous(WFObject obj) {
		if (obj != null) {
			IWorkflowInstance instance = getInstance(obj.InstanceID);
			if (instance == null)
				return null;
			// BindDataContext(instance.getDataContext(), obj);
			String current = instance.getCurrent();

			String previous = instance.previous();
			obj.CurrentNode = previous;
			obj.IsFinished = false;
		}
		return obj;
	}

	public String[] getHistoryNodeSequences(String instanceID) {
		IWorkflowInstance instance = getInstance(instanceID);
		if (instance == null)
			return null;
		List<String> list = Linq.extSelect(instance.getHistoryNodeSequences(), "NodeID");
		return (String[]) list.toArray(new String[list.size()]);
	}

	public String getStartNode(String mapID) {
		if (!mapCollection.contains(mapID))
			return null;
		IWorkflowMap map = mapCollection.get(mapID);
		return map.getStartNode().NodeID;
	}

	public String[] getEndNodes(String mapID) {
		if (!mapCollection.contains(mapID))
			return null;
		IWorkflowMap map = mapCollection.get(mapID);
		List<String> list = Linq.extSelect(map.getEndNodes(), "NodeID");
		return (String[]) list.toArray(new String[list.size()]);
	}

	public WFObject getCurrent(String instanceID) {
		IWorkflowInstance instance = getInstance(instanceID);
		if (instance == null)
			return null;
		String current = instance.getCurrent();
		if (instance.getCurrent().equals(instance.getMap().getFinishedNode())) {
			List<NodeInfo> histories = instance.getHistoryNodeSequences();
			current = histories.get(histories.size() - 1).NodeID;
		}

		WFObject wfObject = new WFObject();
		wfObject.setInstanceID(instance.getInstanceID());
		wfObject.setCurrentNode(current);
		wfObject.setIsFinished(instance.getFinished());
		return wfObject;
	}

	public void delete(String instanceID) {
		IWorkflowStorage storage = getStorageBuilder();
		storage.delete(instanceID);
	}

	/**
	 * 绑定传递给流程节点的数据
	 * 
	 * @param dataContext
	 * @param obj
	 */
	private void bindDataContext(NodeDataContext dataContext, WFObject obj) {
		// 设置服务端传递的数据
		dataContext.ServerData = obj.ServerData;
		if (obj.getServerNamedDatas() != null && obj.getServerNamedDatas().size() > 0) {
			dataContext.getServerNamedDatas().addAll(obj.getServerNamedDatas());
		}
	}

	@Autowired
	private IWorkflowStorage storage;

	private IWorkflowInstance getInstance(String instanceID) {	
		WFInstance obj = storage.getInstance(instanceID);
		if (obj == null)
			return null;
		IWorkflowMap map = mapCollection.get(obj.MapID);
		IWorkflowInstance instance = map.getInstance(obj, this);
		return instance;
	}

	public InstanceRuntimeContext createContext() {
		InstanceRuntimeContext context = new InstanceRuntimeContext();
		context.setStorage(getStorageBuilder());
		return context;
	}
}
