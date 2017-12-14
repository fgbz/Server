package phalaenopsis.common.controller;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import phalaenopsis.common.entity.Device;
import phalaenopsis.common.entity.Paging;
import phalaenopsis.common.service.DeviceService;

/**
 * 设备
 * 
 * @author chunhongl
 *
 */
@Controller
@RequestMapping("/Sys/Mgr/Device")
public class DeviceController {
	@Autowired
	private DeviceService deviceService;

	/**
	 * 获取设备列表
	 * @param pageNo 页数
	 * @param pageSize 每页获取的数量
	 * @param conditions 组织机构
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/GetDevices", method = RequestMethod.POST)
	@ResponseBody
	public Paging<Device> getDevices(@RequestBody Map map) {
		Paging<Device> page = deviceService.getDevices(map);
		return page;
	}

	/**
	 * 获取设备信息
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/GetDevice", method = RequestMethod.GET)
	@ResponseBody
	public Device getDevice(@PathParam("id")String id) {
		Device device =deviceService.getDevice(id);
		return device;
	}
	  
	/**
	 * 添加设备
	 * @param device
	 * @return
	 */
	@RequestMapping(value = "/AddDevice", method = RequestMethod.POST)
	@ResponseBody
	public int addDevice(@RequestBody Device device) {
		//判断设备名称是否存在
		if (deviceService.queryByBame(device.getName()) > 0) {
			return 1;
		}
		device.setID(UUID.randomUUID().toString());
		deviceService.addDevice(device);
		return 0;
	 }

	/**
	 * 更新设备信息
	 * @param device
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/UpdateDevice", method = RequestMethod.PUT)
	@ResponseBody
	public int updateDevice(@RequestBody Map map) {
		Device device = new Device();
		device.setID((String)map.get("ID"));
		device.setName((String)map.get("Name"));
		device.setOrganizationID((String)map.get("OrganizationID"));
		device.setOrganizationName((String)map.get("OrganizationName"));
		device.setPhone((String)map.get("Phone"));
		return deviceService.updateDevice(device);
	}

	/**
	 * 批量删除设备
	 * @param list
	 * @return
	 */
	@RequestMapping(value = "/DeleteDevices", method = RequestMethod.POST)
	@ResponseBody
	public boolean deleteDevices(@RequestBody List<String> list) {
		return deviceService.deleteDevices(list);
	}
}
