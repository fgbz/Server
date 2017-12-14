package phalaenopsis.lawcase.controller;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import phalaenopsis.lawcase.entity.CaseBaseInfo;
import phalaenopsis.lawcase.service.LawCaseCoordinate;
import phalaenopsis.satellitegraph.entity.Polygon;

/**
 * 案件坐标操作服务.
 * @author chunl
 */
@Controller
@RequestMapping("/LawCase/LawCaseService")
//@RestController
public class LawCaseCoordinateController {

	/**
	 * 坐标Service.
	 */
	@Autowired
	private LawCaseCoordinate coordinateService;

	/**
	 * 获取案件坐标数据.
	 * @param id 案件Id号
	 * @return 返回坐标信息
	 */
	@RequestMapping(value = "/GetCaseShape", method = RequestMethod.GET)
	@ResponseBody
	public Polygon getCaseShape(@PathParam("id") final String id) {
		return coordinateService.getCaseShape(id);
	}

	/**
	 * 导入坐标.
	 * @param info 案件数据
	 * @return 返回是否导入成功
	 */
	@RequestMapping(value = "/ImportCoordinate", method = RequestMethod.PUT)
	@ResponseBody
	public boolean importCoordinate(@RequestBody final  CaseBaseInfo info) {
		return coordinateService.importCoordinate(info);
	}

	/**
	 * 删除坐标.
	 * @param info 案件信息
	 * @return 返回是否删除成功
	 */
	@RequestMapping(value = "/DeleteCoordinate", method = RequestMethod.PUT)
	public boolean deleteCoordinate(@RequestBody final CaseBaseInfo info) {
		return coordinateService.deleteCoordinate(info);
	}


	/**
	 * 获取案件实测坐标信息.
	 * @param id 传递案件Id
	 * @return 返回坐标数据
	 */
	@RequestMapping(value = "/GetCaseRealShape", method = RequestMethod.GET)
	@ResponseBody
	public Polygon getCaseRealShape(@RequestParam final String id) {
		return coordinateService.getCaseShape(id, true);
	}

	/**
	 * 导入实测坐标.
	 * @param info 案件信息
	 * @return 返回是否导入成功
	 */
	@RequestMapping(value = "/ImportRealCoordinate", method = RequestMethod.PUT)
	@ResponseBody
	public boolean importRealCoordinate(@RequestBody final CaseBaseInfo info) {
		return coordinateService.importCaseCoordinate(info, true);
	}

	/**
	 * 导入坐标信息.
	 * @param info 案件信息
	 * @param realShape 是否为真是坐标
	 * @return 返回是否成功
	 */
	@RequestMapping(value = "/ImportCaseCoordinate", method = RequestMethod.PUT)
	@ResponseBody
	public boolean importCaseCoordinate(final CaseBaseInfo info, final boolean realShape) {
		return coordinateService.importCaseCoordinate(info, realShape);
	}

	/**
	 * 删除实测坐标信息. 
	 * @param info 案件信息
	 * @return 返回是否成功
	 */
	@RequestMapping(value = "/DeleteRealCoordinate", method = RequestMethod.POST)
	@ResponseBody
	public boolean deleteRealCoordinate(@RequestBody final CaseBaseInfo info) {
		return coordinateService.deleteCoordinate(info);
	}

	/**
	 * 删除坐标信息.
	 * @param info 案件信息
	 * @param realShape 是否真实坐标
	 * @return 返回是否成功
	 */
	@RequestMapping(value = "/DeleteCaseCoordinate", method = RequestMethod.POST)
	@ResponseBody
	public boolean deleteCaseCoordinate(final CaseBaseInfo info, final boolean realShape) {
		return coordinateService.deleteCoordinate(info, realShape);
	}

}
