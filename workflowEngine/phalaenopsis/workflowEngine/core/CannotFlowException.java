package phalaenopsis.workflowEngine.core;

/**
 * 流程无法继续流转时出现的异常
 * 
 * @author chunl
 *
 */
public class CannotFlowException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CannotFlowException() {
		super();
	}

	public CannotFlowException(String message) {
		super(message);
	}

	public CannotFlowException(String message, Exception ex) {
		super(message, ex);
	}

	public CannotFlowException(String currentNode, String nextNode) {
		super(String.format("CannotFlowFromTo%s%s", currentNode, nextNode));
		this.currentNode = currentNode;
		this.nextNode = nextNode;
	}

	public CannotFlowException(String currentNode, String nextNode, String message) {
		super(String.format("CannotFlowFromTo%s%s", currentNode, nextNode + message));
		this.currentNode = currentNode;
		this.nextNode = nextNode;
	}

	/**
	 * 流程当前所处的节点
	 */
	private String currentNode;

	/**
	 * 流程要流转的下一个节点
	 */
	private String nextNode;

	public String getCurrentNode() {
		return currentNode;
	}

	public String getNextNode() {
		return nextNode;
	}
}
