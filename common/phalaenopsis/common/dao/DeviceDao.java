package phalaenopsis.common.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import phalaenopsis.common.entity.Device;
import phalaenopsis.common.entity.Paging;

@Repository("deviceDao")
public class DeviceDao {
	@Resource
	private SqlSession sqlSession;

	/**
	 * 获取设备列表
	 * @param pageNo
	 * @param pageSize
	 * @param conditions
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Paging<Device> getDevices(Map conditions) {
		int pageNo = (Integer) conditions.get("pageNo");
		int pageSize = (Integer) conditions.get("pageSize");
		Map<String, Object> map = new HashMap<String, Object>();
		//查询条件
		@SuppressWarnings("unchecked")
		ArrayList<String> list = (ArrayList<String>) conditions.get("conditions");
		for (int i = 0; i < list.size(); i++) {
			Map param = JSON.toJavaObject((JSONObject) JSON.toJSON(list.get(i)), Map.class);
			if (param.containsKey("Key")) {
				if (param.get("Key").equals("Org")) {
					map.put("Org", (String) param.get("Value"));
				}
				if (param.get("Key").equals("SearchText")) {
					map.put("SearchText", (String) param.get("Value"));
				}
			}
		}
		map.put("startNum", pageSize * (pageNo - 1) + 1);
		map.put("endNum", pageSize * pageNo);
		
		// 查询总数
		int count = sqlSession.selectOne("device.getDevicesCount", map);
		// 分页查询
		List<Device> results = sqlSession.selectList("device.getDevices", map);

		Paging<Device> page = new Paging<Device>();
		page.PageNo = pageNo;
		page.PageSize = pageSize;
		page.RecordCount = count;
		page.CurrentList = results;
		page.PageCount = (page.RecordCount / page.PageSize) + (page.RecordCount % page.PageSize > 0 ? 1 : 0);
		return page;
	}
	
	/**
	 * 获取设备信息
	 * @param id
	 * @return
	 */
	public Device getDevice(String id) {
		Device device = sqlSession.selectOne("device.getDevice", id);
		//查询组机构名称
		String orgName = (String) sqlSession.selectOne("organization.getOrgName", device.getOrganizationID());
		device.setOrganizationName(orgName);
		return device;
	}

	/**
	 * 查询设备名称
	 * @param deviceName
	 * @return
	 */
	public int queryByBame(String deviceName) {
		return sqlSession.selectOne("device.queryByName", deviceName);
	}

	/**
	 * 添加设备
	 * @param device
	 */
	public void addDevice(Device device) {
		sqlSession.insert("device.addDevice", device);
	}

	/**
	 * 修改设备信息
	 * @param device
	 */
	public int updateDevice(Device device) {
		// 判断是否与其他设备同名
		int count = sqlSession.selectOne("device.isHaveName", device);
		if (count > 0) {
			return 1;
		} else {
			sqlSession.update("device.updateDevice", device);
			return 0;
		}
	}

	/**
	 * 删除设备
	 * @param id
	 * @return
	 */
	public boolean deleteDevices(List<String> list) {
		int result = sqlSession.delete("device.deleteDevices", list);
		return result > 0 ? true : false;
	}
}
