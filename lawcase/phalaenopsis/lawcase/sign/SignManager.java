package phalaenopsis.lawcase.sign;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.NullArgumentException;
import org.springframework.stereotype.Service;

import phalaenopsis.common.method.Tools.Linq;
import phalaenopsis.lawcase.entity.SignLink;
import phalaenopsis.lawcase.entity.SignNode;

import static org.springframework.util.Assert.notNull;
import static org.springframework.util.Assert.isTrue;

/**
 * 签字流程链管理
 * 
 * @author yuhangc
 *
 */
@Service("signManager")
public class SignManager {

	private static List<SignLink> signLinkList = new ArrayList<SignLink>();
	
	/**
	 * 注册一个签字流程
	 * 
	 * @param signLink
	 */
	public void Register(SignLink signLink) {
		notNull(signLink);
		//签字流程链ID不能重复
		boolean b = !Linq.extExists(signLinkList, "signLinkID", signLink.getSignLinkID());
		signLinkList.add(signLink);
	}

	/**
	 * 获取签字链
	 * 
	 * @param signLinkID
	 * @return
	 */
	public SignLink GetSignLink(String signLinkID) {
		SignLink signLink = Linq.extEquals(signLinkList, "signLinkID", signLinkID); 
		if (null == signLink)
			throw new IllegalArgumentException();
		return signLink;
	}

	/**
	 * 获取流程节点对应的签字链
	 * 
	 * @param flowNode
	 * @return
	 */

	public SignLink GetNextSignLink(String flowNode) {
		SignLink link = Linq.extEquals(signLinkList, "flowNode", flowNode);
		return link;
	}

	/**
	 * 获取签字节点
	 * 
	 * @param signLinkID
	 * @param node
	 * @return
	 */

	public SignNode GetSignNode(String signLinkID, String node) {
		SignLink link = GetSignLink(signLinkID);
		SignNode signNode = GetSignNode(link, node);
		return signNode;
	}

	/**
	 * 获取签字节点
	 * 
	 * @param link
	 * @param node
	 * @return
	 */

	public SignNode GetSignNode(SignLink link, String node) {
		SignNode signNode = link.Get(node);
		if (signNode == null)
			throw new NullArgumentException(node);
		return signNode;
	}

	/**
	 * 获取所有签字链的ID
	 * 
	 * @return
	 */
	public List<String> GetSignLinkIDList() {
		return Linq.extSelect(signLinkList, "signLinkID");
	}

	/**
	 * 获取所有签字链列表
	 * 
	 * @return
	 */

	public List<SignLink> GetSignLinkList() {
		return signLinkList;
	}
}
