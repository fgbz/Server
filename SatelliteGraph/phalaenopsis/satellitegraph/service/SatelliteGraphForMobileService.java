/**
 * 
 */
package phalaenopsis.satellitegraph.service;

import java.util.List;

import javax.ws.rs.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import phalaenopsis.common.entity.DataDictionaryItem;
import phalaenopsis.common.entity.Page;
import phalaenopsis.common.entity.Paging;
import phalaenopsis.common.entity.Attachment.Attachment;
import phalaenopsis.satellitegraph.dao.SatelliteGraphForMobileDao;
import phalaenopsis.satellitegraph.entity.MapSpot2016;
import phalaenopsis.satellitegraph.entity.MobileSpot;
import phalaenopsis.satellitegraph.entity.SpotNumberSpotID;
import phalaenopsis.satellitegraph.entity.SpotShapeInfo;

/**
 * @author gaofengt
 *
 *         2017年4月21日下午2:08:28
 */
@Service("satelliteGraphForMobileService")
public class SatelliteGraphForMobileService {

	@Autowired
	private SatelliteGraphForMobileDao sateGraphForMobileDao;

	/**
	 * 查询图斑列表
	 * 
	 * @param string
	 * @return
	 */
	public Paging<MobileSpot> oilList(Page page) {
		return sateGraphForMobileDao.OilList(page);
	}

	/**
	 * 查询图斑基本信息
	 */

	public MapSpot2016 getSpotBaseInfoByid(long id) {
		return sateGraphForMobileDao.getSpotBaseInfoByid(id);
	}

	/**
	 * 上传图斑数据
	 */

	public boolean reportMapSpot(Long spotID) {
		return sateGraphForMobileDao.reportMapSpot(spotID);
	}

	/**
	 * 查询各个列表数组总数
	 */

	public List<Integer> getTabTotal() {
		return sateGraphForMobileDao.getTabTotal();
	}

	/**
	 * 查询地图范围内图斑shape信息
	 */

	public List<SpotShapeInfo> getSpotShapeByBounds(String bounds) {
		return sateGraphForMobileDao.getSpotShapeByBounds(bounds);
	}

	/**
	 * 查询图斑号
	 */
	public List<SpotNumberSpotID> getSpotNumbersByKey(String spotNumber) {
		return sateGraphForMobileDao.getSpotNumbersByKey(spotNumber);
	}

	/**
	 * 获取单个图斑by图斑号
	 */
	public SpotShapeInfo getSpotShapeInfoBySpotID(@PathParam("spotID") long spotID) {
		return sateGraphForMobileDao.getSpotShapeInfoBySpotID(spotID);
	}

	/**
	 * 检查是否成功连接后台
	 */
	public boolean checkConnection() {
		return sateGraphForMobileDao.checkConnection();
	}

	/**
	 * 获取拍照的距离限制
	 */
	public String getMinDistance() {
		return sateGraphForMobileDao.getMinDistance();
	}

	/**
	 * 保存图斑
	 */
	public boolean saveMapSpot(MapSpot2016 mapSpot) {
		return sateGraphForMobileDao.saveMapSpot(mapSpot);
	}

	/**
	 * 获取批准机关级别字典数据
	 */
	public List<DataDictionaryItem> getAllDictionaries() {
		return sateGraphForMobileDao.getAllDictionaries();
	}

	/**
	 * 获取附件
	 */
	public Attachment getAttachment(String attID) {
		return sateGraphForMobileDao.getAttachment(attID);
	}

	/**
	 * 查询图斑流程
	 */
	public boolean nodeCanUpload(long spotID, int spottype) {
		return sateGraphForMobileDao.nodeCanUpload(spotID, spottype);
	}

	/**
	 * 20170410先判断是否有内网同步到外网的照片，如果有就允许上传
	 */
	public boolean haveSynPhoto(long spotID) {
		return sateGraphForMobileDao.haveSynPhoto(spotID);
	}
}
