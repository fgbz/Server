package phalaenopsis.illegalclue.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import phalaenopsis.illegalclue.entity.IllegalClueSum;
import phalaenopsis.illegalclue.service.IllegalClueServiceNew;

@Controller
@RequestMapping("/IllegalClueService")
public class IllegalClueStatisticsController {
	@Autowired
	private IllegalClueServiceNew service;
	/**
	 * 查询线索统计报表(分矿产和土地两种)
	 * @return
	 */
	@RequestMapping(value = "/getIllegalClueStatistic", method = RequestMethod.POST)
	@ResponseBody
	public   IllegalClueSum  getIllegalClueStatistic(@RequestBody String obj){
		//先去数据库查询出这段时间内的所有线索信息 然后进行统计
		IllegalClueSum illegalClueSum=service.getClueStatistic(obj);
		return illegalClueSum;
	}
}
