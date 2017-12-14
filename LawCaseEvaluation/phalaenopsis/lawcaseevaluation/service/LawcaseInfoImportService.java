package phalaenopsis.lawcaseevaluation.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import phalaenopsis.lawcaseevaluation.entity.*;
import phalaenopsis.common.dao.UserDao;
import phalaenopsis.common.entity.*;
import phalaenopsis.common.method.Tools.UUID64;
import phalaenopsis.lawcaseevaluation.dao.EvaluationPersonDAO;
import phalaenopsis.lawcaseevaluation.dao.LawcaseInfoImportDAO;

@Service("lawcaseInfoImportService")
public class LawcaseInfoImportService {
	/**
	 * 导入案件台账
	 * 
	 * @return
	 */
	public int importLawcaseAccount(List<LawcaseAccountExcel> list, int year) {

		// 判断是否已经开始评查
		if (lawcaseInfoImportDAO.checkLawcaseAccountWork(year) > 0) {
			// 已开始工作，不能导入
			int isWorking = 471;
			OpResult opResult = new OpResult(isWorking);
			return opResult.Code;
		}

		// 案件台账集合
		List<LawcaseAccount> lawcaseAccountList = new ArrayList<LawcaseAccount>();
		for (int i = 0; i < list.size(); i++) {
			LawcaseAccount lawcaseAccount = new LawcaseAccount();
			LawcaseAccountExcel lawcaseAccountExcel = list.get(i);
			// 映射
			String xh = lawcaseAccountExcel.getXH();
			// 去掉小数点后的字符
			if (xh.indexOf('.') != -1) {
				xh = xh.substring(0, xh.indexOf('.'));
			}
			lawcaseAccount.setXH(xh);
			// 必填字段验证
			if (lawcaseAccountExcel.getRegionName() == null || lawcaseAccountExcel.getRegionName().equals("")) {
				return OpResult.PreconditionFailed;
			} else {
				lawcaseAccount.setRegionName(lawcaseAccountExcel.getRegionName());
			}
			// 执法单位必填
			if (lawcaseAccountExcel.getRegionCode() == null || lawcaseAccountExcel.getRegionCode().equals("")) {
				return OpResult.PreconditionFailed;
			} else {
				String regionCode = lawcaseAccountExcel.getRegionCode();
				if (regionCode.indexOf('.') != -1) {
					regionCode = regionCode.substring(0, regionCode.indexOf('.'));
				}
				lawcaseAccount.setRegionCode(regionCode);
				lawcaseAccount.setParentRegionCode(lawcaseInfoImportDAO.getParentRegionCode(regionCode));
			}
			// 案卷编号必填
			if (lawcaseAccountExcel.getCaseCode() == null || lawcaseAccountExcel.getCaseCode().equals("")) {
				return OpResult.PreconditionFailed;
			} else {
				lawcaseAccount.setCaseCode(lawcaseAccountExcel.getCaseCode());
			}
			// 案卷名称必填
			if (lawcaseAccountExcel.getCaseName() == null || lawcaseAccountExcel.getCaseName().equals("")) {
				return OpResult.PreconditionFailed;
			} else {
				lawcaseAccount.setCaseName(lawcaseAccountExcel.getCaseName());
			}
			lawcaseAccount.setMainTruth(lawcaseAccountExcel.getMainTruth());
			lawcaseAccount.setIsClosed(lawcaseAccountExcel.getIsClosed());
			lawcaseAccount.setIsImportant(lawcaseAccountExcel.getIsImportant());
			lawcaseAccount.setIsChecked(lawcaseAccountExcel.getIsChecked());
			lawcaseAccount.setIsCityChecked(lawcaseAccountExcel.getIsCityChecked());
			// 主键ID
			long uuid = UUID64.newUUID64().getValue();
			lawcaseAccount.setID(uuid);
			// 获取评查标准主键id
			int pcbzId = lawcaseInfoImportDAO.getPcbzIdByYear(year);
			lawcaseAccount.setBelongedYear(year);
			// 评查标准ID
			lawcaseAccount.setPcbzID(pcbzId);
			lawcaseAccountList.add(lawcaseAccount);
		}
		// 导入
		if (!lawcaseInfoImportDAO.addLawcaseAccount(lawcaseAccountList, year)) {
			return OpResult.Failed;
		}
		return OpResult.Success;

	}

	/**
	 * 导入评查人员
	 * 
	 * @param persons
	 * @return
	 */
	@Transactional
	public int importEvaluationPerson(List<ImportingEvaluationPerson> persons, long id, boolean isImport) {
		int cheakYear = 0;
		// 获取市级行政区划
		List<SSRegion> listSsregion = evaluationPersonService.getRegionList();
		// 判断能否导入
		if (persons.size() > 0 && isImport) {
			cheakYear = Integer
					.parseInt(persons.get(0).getYear());
			if (lawcaseInfoImportDAO.checkUserWork(cheakYear) > 0) {
				// 人员已开始工作，不能导入，只能新增
				int isWorking = 471;
				OpResult opResult = new OpResult(isWorking);
				return opResult.Code;
			}
		}
		// 导入的人员账号存在重复
		if (checkPersonAccount(persons)) {
			int isRepeat = 461;
			OpResult opResult = new OpResult(isRepeat);

			return opResult.Code;
		}

		int addUserResult = 0;
		int pcbzId = 0;
		List<EvaluationPerson> evaPersonList = new ArrayList<EvaluationPerson>();
		getCasePermission();

		for (ImportingEvaluationPerson person : persons) {
			// 账号必填
			if (person.getAccount() == null || person.getAccount().equals("")) {
				return OpResult.PreconditionFailed;
			}
			// 行政区划必填
			if (person.getRegionCode() == null || person.getRegionCode().equals("")) {
				return OpResult.PreconditionFailed;
			}
			//姓名必填
			if (person.getName() == null || person.getName().equals("")) {
				return OpResult.PreconditionFailed;
			}
			//保存系统用户
			addUserResult = saveUser(person);
			int year = 0;
			if (addUserResult == 1) {
				// 年份
				if (person.getYear().contains(".")) {
					year = Integer.parseInt(person.getYear().substring(0, person.getYear().lastIndexOf(".")));
				} else {
					year = Integer.parseInt(person.getYear());
				}
				// 评查标准Id
				if (pcbzId == 0) {
					pcbzId = lawcaseInfoImportDAO.getPcbzIdByYear(year);
				}
				EvaluationPerson evaluationPerson = new EvaluationPerson();
				// 新增给id赋值
				if (id == -1) {
					evaluationPerson.setId(UUID64.newUUID64().getValue());
				} else {
					evaluationPerson.setId(id);
				}
				evaluationPerson.setDuty(person.getDuty());
				evaluationPerson.setPcbzId(pcbzId);
				String regionId = person.getRegionCode();
				if (person.getRegionCode().contains(".")) {
					regionId = person.getRegionCode().substring(0, person.getRegionCode().lastIndexOf("."));
				}
				evaluationPerson.setRegionCode(regionId);
				evaluationPerson.setRemark(person.getRemark());
				String contract = person.getContract();
				evaluationPerson.setContract(contract);
				evaluationPerson.setUserAccount(person.getAccount());
				evaluationPerson.setYear(year);
				//导入时分配区域 
				if (isImport) {
					lawcaseInfoImportDAO.deleteMapImport(year);
					getListCode(evaluationPerson.getRegionCode(), listSsregion, evaluationPerson.getId());
				}

				evaPersonList.add(evaluationPerson);
			}
		}
		//导入
		if (lawcaseInfoImportDAO.addLawcasePersons(evaPersonList, isImport, cheakYear)) {
			return OpResult.Success;
		}

		return OpResult.Failed;
	}

	/**
	 * 判断excel中账号是否重复
	 * @param persons
	 * @return
	 */
	private boolean checkPersonAccount(List<ImportingEvaluationPerson> persons) {
		Map<String, String> accountMap = new HashMap<String, String>();
		for (ImportingEvaluationPerson person : persons) {
			if (accountMap.containsKey(person.getAccount())) {
				return true;
			} else {
				accountMap.put(person.getAccount(), person.getAccount());
			}
		}

		return false;
	}

	/**
	 * 保存系统用户
	 * @param person
	 * @return
	 */
	private int saveUser(ImportingEvaluationPerson person) {
		getCaseRole();
		List<Role> roles = new ArrayList<Role>();
		roles.add(_role);

		User user = new User();
		user.setAccount(person.getAccount());
		user.setName(person.getName());
		user.setOrganizationID(ORGID);
		user.setRemarks("");
		user.setHasPhoto(false);
		user.setSignaturePhoto(null);
		user.setRoles(roles);
		// 案件评查的用户密码规则改成：必须包含大写+小写+字符，字符长度不小于6
		user.setPassword("Pc8888888");
		try {
			userDao.addPcUser(user);
		} catch (Exception e) {
			return -1;
		}

		return 1;
	}

	private void getCasePermission() {
		if (_permission == null) {
			_permission = new Permission(CASEPERMISSIONCODE, CASEPERMISSIONNAME, CASEPERSSIONTYPE);
		}
	}

	private void getCaseRole() {
		if (_role == null) {
			List<Permission> permissions = new ArrayList<Permission>();
			permissions.add(_permission);
			_role = new Role(EVALUATIONPERSONROLEID, EVALUATIONPERSONROLENAME, false, permissions);
		}
	}

	/**
	 * 获取区域关联的信息
	 */
	public void getListCode(String regionid, List<SSRegion> listRegion, long evaluationUserID) {
		List<EvaluationAreaUserMap> list = new ArrayList<EvaluationAreaUserMap>();
		long regionidLong = Long.parseLong(regionid);
		// 为人员分配区域,除自身区域外的所有区域
		for (int i = 0; i < listRegion.size(); i++) {
			if (listRegion.get(i).getRegionCode() != regionidLong) {
				EvaluationAreaUserMap evaluationAreaUserMap = new EvaluationAreaUserMap();
				evaluationAreaUserMap.setId(UUID64.newUUID64().getValue());
				evaluationAreaUserMap.setRegionID(listRegion.get(i).getRegionCode());
				evaluationAreaUserMap.setEvaluationUserID(evaluationUserID);
				list.add(evaluationAreaUserMap);
			}
		}
		//分配区域人员
		evaluationPersonDAO.importEvaluationUser(list);

	}

	private final String EVALUATIONPERSONROLEID = "00000000000000000000000000000000";
	private final String EVALUATIONPERSONROLENAME = "初评人员";
	private final String CASEPERMISSIONCODE = "LawcasePraeiudicium";
	private final String CASEPERMISSIONNAME = "案件初评";
	private final int CASEPERSSIONTYPE = 1;
	private final String ORGID = "00000000-0000-0000-0000-000000000000";
	private Permission _permission = null;
	private Role _role = null;

	@Autowired
	private UserDao userDao;

	@Autowired
	private LawcaseInfoImportDAO lawcaseInfoImportDAO;
	@Autowired
	private EvaluationPersonService evaluationPersonService;
	@Autowired
	private EvaluationPersonDAO evaluationPersonDAO;
}
