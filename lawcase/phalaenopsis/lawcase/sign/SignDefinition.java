package phalaenopsis.lawcase.sign;

import java.util.AbstractMap.SimpleEntry;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import phalaenopsis.common.method.ManualAssembly;
import phalaenopsis.lawcase.entity.SignLink;
import phalaenopsis.lawcase.entity.SignNode;

@Controller
public class SignDefinition {

	/**
	 * 线索签字流程
	 */
	public static final String Clue = "Clue";

	/**
	 * 立案签字流程
	 */
	public static final String Build = "Build";

	/**
	 * 处理决定呈批表签字流程
	 */
	public static final String Deal = "Deal";

	/**
	 * 结案呈批表签字流程
	 */
	public static final String EndCase = "EndCase";

	@Autowired
	private SignManager signMgr;

	public SignDefinition() {
		Register();
	}

	/**
	 * 注册违法案件签字流程
	 * 
	 * @param signMgr
	 */
	@SuppressWarnings("unchecked")
	public void Register() {
		// 经办人 部门负责人 分管负责人 局负责人 局分管领导 局主管领导
		SignNode sp1, sp2, sp3, sp4, sp5, sp6;

		Map<String, String> map = new HashMap<String, String>();
		map.entrySet();

		// 线索签字流程
		SignLink s1 = new SignLink(Clue, "Clue");
		sp1 = new SignNode("Operator", 2, new SimpleEntry<String, Object>("SignRole", "Operator"));
		sp2 = new SignNode("DepartmentCharge", 1, new SimpleEntry<String, Object>("SignRole", "DepartmentCharge"));
//		sp3 = new SignNode("BranchCharge", 1, new SimpleEntry<String, Object>("SignRole", "BranchCharge"));
//		sp4 = new SignNode("BureauCharge", 1, new SimpleEntry<String, Object>("SignRole", "BureauCharge"));
		s1.Set(sp1, sp2);

		// 立案签字流程
		SignLink s2 = new SignLink(Build, "Build");
		sp1 = new SignNode("Operator", 2, new SimpleEntry<String, Object>("SignRole", "Operator"));
		sp2 = new SignNode("DepartmentCharge", 1, new SimpleEntry<String, Object>("SignRole", "DepartmentCharge"));
		sp3 = new SignNode("BranchCharge", 1, new SimpleEntry<String, Object>("SignRole", "BranchCharge"));
		sp4 = new SignNode("BureauCharge", 1, new SimpleEntry<String, Object>("SignRole", "BureauCharge"));
		sp5 = new SignNode("BureauLeader", 1, new SimpleEntry<String, Object>("SignRole", "BureauLeader"));
		sp6 = new SignNode("BureauManager", 1, new SimpleEntry<String, Object>("SignRole", "BureauManager"));
		s2.Set(sp1, sp2, sp3, sp4, sp5, sp6);

		// 处理决定呈批表签字流程
		SignLink s3 = new SignLink(Deal, "Deal");
		sp1 = new SignNode("Operator", 2, new SimpleEntry<String, Object>("SignRole", "Operator"));
		sp2 = new SignNode("DepartmentCharge", 1, new SimpleEntry<String, Object>("SignRole", "DepartmentCharge"));
		sp3 = new SignNode("BranchCharge", 1, new SimpleEntry<String, Object>("SignRole", "BranchCharge"));
		sp4 = new SignNode("BureauCharge", 1, new SimpleEntry<String, Object>("SignRole", "BureauCharge"));
		sp5 = new SignNode("BureauLeader", 1, new SimpleEntry<String, Object>("SignRole", "BureauLeader"));
		sp6 = new SignNode("BureauManager", 1, new SimpleEntry<String, Object>("SignRole", "BureauManager"));
		s3.Set(sp1, sp2, sp3, sp4, sp5, sp6);

		// 结案呈批表签字流程
		SignLink s4 = new SignLink(EndCase, "EndCase");
		sp1 = new SignNode("Operator", 2, new SimpleEntry<String, Object>("SignRole", "Operator"));
		sp2 = new SignNode("DepartmentCharge", 1, new SimpleEntry<String, Object>("SignRole", "DepartmentCharge"));
		sp3 = new SignNode("BranchCharge", 1, new SimpleEntry<String, Object>("SignRole", "BranchCharge"));
		sp4 = new SignNode("BureauCharge", 1, new SimpleEntry<String, Object>("SignRole", "BureauCharge"));
		sp5 = new SignNode("BureauLeader", 1, new SimpleEntry<String, Object>("SignRole", "BureauLeader"));
		sp6 = new SignNode("BureauManager", 1, new SimpleEntry<String, Object>("SignRole", "BureauManager"));
		s4.Set(sp1, sp2, sp3, sp4, sp5, sp6);
		
		if (null == signMgr)
			signMgr = ManualAssembly.getAssembly("signManager");

		signMgr.Register(s1);
		signMgr.Register(s2);
		signMgr.Register(s3);
		signMgr.Register(s4);
	}

}
