package phalaenopsis.common.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import phalaenopsis.common.dao.DeviceDao;
import phalaenopsis.common.entity.Device;
import phalaenopsis.common.entity.Paging;

@Service("deviceService")
public class DeviceService {
	@Autowired
	private DeviceDao deviceDao;

	@SuppressWarnings("rawtypes")
	public Paging<Device> getDevices(Map map) {
		return deviceDao.getDevices(map);
	}
	
	/**
	 * 获取设备信息
	 * @param id
	 * @return
	 */
	public Device getDevice(String id) {
		return deviceDao.getDevice(id);
	}
	
	/**
	 * 查询设备名称
	 * @param deviceName
	 * @return
	 */
	public int queryByBame(String deviceName) {
		return deviceDao.queryByBame(deviceName);
	}
	
	/**
	 * 添加设备
	 * @param device
	 */
	public void addDevice(Device device) {
		deviceDao.addDevice(device);
	}

	/**
	 * 修改设备信息
	 * @param device
	 */
	public int updateDevice(Device device) {
		return deviceDao.updateDevice(device);
	}

	/**
	 * 删除设备
	 * @param id
	 * @return
	 */
	public boolean deleteDevices(List<String> list) {
		return deviceDao.deleteDevices(list);
	}
}
