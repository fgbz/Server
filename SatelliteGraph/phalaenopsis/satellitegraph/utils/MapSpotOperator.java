package phalaenopsis.satellitegraph.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Stack;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;

import net.sf.ehcache.store.disk.ElementSubstituteFilter;
import phalaenopsis.allWeather.entity.SwMapspot;
import phalaenopsis.common.method.ManualAssembly;
import phalaenopsis.common.method.Tools.UUID64;
import phalaenopsis.satellitegraph.dao.SatelliteGraphDaoPartial;
import phalaenopsis.satellitegraph.entity.IMapSpot;
import phalaenopsis.satellitegraph.entity.MapSpot;
import phalaenopsis.satellitegraph.entity.MapSpot2016;
import phalaenopsis.satellitegraph.entity.MapSpotBackup;
import phalaenopsis.satellitegraph.entity.MapSpotRelation;
import phalaenopsis.satellitegraph.entity.MapSpotShadow;
import phalaenopsis.satellitegraph.entity.ShadowType;

/**
 * 对图斑进行分宗、并宗、还原操作
 * 
 * @author chunl
 *
 */
public class MapSpotOperator {

	private IMapSpotOperationExecutor executor;

	@Resource
	private SqlSession sqlSession;
	
	@Autowired
	private SatelliteGraphDaoPartial daoPartial =  ManualAssembly.getAssembly("satelliteGraphDaoPartial");;

	public MapSpotOperator(IMapSpotOperationExecutor executor) {
		this.executor = executor;
	}

	/**
	 * 图斑分宗
	 * 
	 * @param source
	 *            原始图斑
	 * @return
	 */
	@Transactional
	public boolean segment(IMapSpot source, List<IMapSpot> spots) {
		List<Long> list = new ArrayList<Long>();
		list.add(source.getID());
		if (!executor.onAddAndDelete(spots, list)) {
			return false;
		}
		try {
			boolean result = false;
			// 更新原始图斑的影像点属性
			MapSpotShadow sourceShadow = daoPartial.getShadow(source);
			sourceShadow = updateSourceShadow(sourceShadow, source);

			// 保存分宗后的新图斑的影像点
			for (IMapSpot ms : spots) {
				MapSpotShadow shadow = saveNewShadow(ms);

				MapSpotRelation relation = new MapSpotRelation();
				relation.setId(UUID64.newUUID64().getValue());
				relation.setShadow1(sourceShadow.getShadowID());
				relation.setShadow2(shadow.getShadowID());

				daoPartial.insertMapSpotRelation(relation);
			}

			// 备份分宗前的图斑
			result = backupSpot(source);
			return result;
		} catch (Exception e) {
			return false;
		}
	}
	
	
	/**
	 * 图斑并宗
	 * @param sourceSpots  要并宗的图斑列表
	 * @param newSpot  并宗后的新图斑
	 * @return 
	 */
	@Transactional
	public boolean merge(List<IMapSpot> sourceSpots, IMapSpot newSpot)
	{
		List<IMapSpot> list = new ArrayList<IMapSpot>();
		list.add(newSpot);
		List<Long> longs = new ArrayList<Long>();
		for (IMapSpot item : sourceSpots) {
			longs.add(item.getID());
		}

		if (!executor.onAddAndDelete(list, longs)) {
			return false;
		}
		try {
			boolean result = false;
			// 保存并宗后的图斑的影像点
			MapSpotShadow newShadow = saveNewShadow(newSpot);

			// 更新原始图斑的影像点
			for (IMapSpot ms : sourceSpots) {
				MapSpotShadow shadow = daoPartial.getShadow(ms);
				shadow = updateSourceShadow(shadow, ms);

				MapSpotRelation relation = new MapSpotRelation();
				relation.setId(UUID64.newUUID64().getValue());
				relation.setShadow1(shadow.getShadowID());
				relation.setShadow2(newShadow.getShadowID());

				daoPartial.insertMapSpotRelation(relation);
				// 备份并宗前的图斑
				result = backupSpot(ms);
			}
			return result;
		} catch (Exception e) {
			return false;
		}
	}
	
	
	private boolean backupSpot(IMapSpot spot)
	{
		MapSpotBackup backup = new MapSpotBackup();
		backup.setId(UUID64.newUUID64().getValue());
		backup.setMapSpotID(spot.getID());
		backup.setBizType(spot.getBizType());
		backup.setEntity(JSON.toJSONString(spot));
		backup.setStrShape(JSON.toJSONString(spot.getShape()));
		int result = daoPartial.insertMapSpotBackup(backup);
		return result>0;
	}
	
	
	private MapSpotShadow saveNewShadow(IMapSpot spot)
	{
		MapSpotShadow shadow = new MapSpotShadow();
		shadow.setShadowID(UUID64.newUUID64().getValue());
		shadow.setMapSpotID(spot.getID());
		shadow.setShadowType(ShadowType.Shadow);
		shadow.setLeaf(true);
		shadow.setBizType(spot.getBizType());
		
		daoPartial.insertMapSpotShadow(shadow);
		//sqlSession.insert("satellitegraphservice.insertMapSpotShadow", shadow);
		return shadow;
	}
	
	
	

	/**
	 * 图斑还原为初始状态
	 * @param spot 要还原的图斑类型
	 * @return 要还原的图斑
	 */
	@Transactional

	public boolean restoreAll(IMapSpot spot) {
		MapSpotShadow p0 = daoPartial.getShadow(spot);

		if (null == p0)
			return false;

		Stack<MapSpotShadow> visitingNodes = new Stack<MapSpotShadow>();
		HashSet<Long> visitedNodes = new HashSet<Long>();
		List<MapSpotShadow> deletingList = new ArrayList<MapSpotShadow>();
		List<MapSpotShadow> restoringList = new ArrayList<MapSpotShadow>();

		visitedNodes.add(p0.getShadowID());
		visitingNodes.push(p0);

		if (p0.getShadowType() == ShadowType.Origin)
			restoringList.add(p0);
		deletingList.add(p0);

		 outer: while (visitingNodes.size() > 0) {
			MapSpotShadow top =visitingNodes.peek();
			List<MapSpotRelation> joints = daoPartial.getMapSpotRelation(top); // sqlSession.selectList("satellitegraphservice.getMapSpotRelation", top);
			for (MapSpotRelation r : joints) {
				MapSpotShadow pi = null;
				if (!visitedNodes.contains(r.getShadow1())) {
					visitedNodes.add(r.getShadow1());
					pi = daoPartial.getSpotShadowByShadowID(r.getShadow1()) ;  //sqlSession.selectOne("satellitegraphservice.getSpotShadowByShadowID", r.getShadow1());
				} else if (!visitedNodes.contains(r.getShadow2())) {
					visitedNodes.add(r.getShadow2());
					pi = daoPartial.getSpotShadowByShadowID(r.getShadow2()) ; //sqlSession.selectOne("satellitegraphservice.getSpotShadowByShadowID", r.getShadow2());
				}

				if (null != pi) {
					visitingNodes.push(pi);
					if (pi.getShadowType() == ShadowType.Origin)
						restoringList.add(pi);
					deletingList.add(pi);
					//break;
					continue outer;
				}
			}
			visitingNodes.pop();
		}
		try {
			// 还原原始图斑数据
			List<IMapSpot> restoredList = new ArrayList<IMapSpot>();
			for (MapSpotShadow shadow : restoringList) {
				IMapSpot origin = restoreSpot(shadow);
				restoredList.add(origin);
			}

			// 非叶子节点的图斑数据在分宗、并宗时已经删除了
			List<Long> list = new ArrayList<Long>();
			for (MapSpotShadow mapSpotShadow: deletingList) {
				if (mapSpotShadow.isLeaf()) {
					list.add(mapSpotShadow.getMapSpotID());
				}
			}
			if (!executor.onAddAndDelete(restoredList, list)) {
				return false;
			}
			daoPartial.restoreDelete(deletingList);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	private MapSpotShadow updateSourceShadow(MapSpotShadow shadow, IMapSpot spot) {
		if (null == shadow) {
			shadow = new MapSpotShadow();
			shadow.setShadowID(UUID64.newUUID64().getValue());
			shadow.setMapSpotID(spot.getID());
			shadow.setShadowType(ShadowType.Origin);
			shadow.setLeaf(false);
			shadow.setBizType(spot.getBizType());
			daoPartial.insertMapSpotShadow(shadow);
		} else {
			shadow.setLeaf(false);
			daoPartial.updateMapSpotShadow(shadow);
		}
		return shadow;
	}


	private IMapSpot restoreSpot(MapSpotShadow shadow) {
		//List<MapSpotBackup> lista = daoPartial.selectMapSpotBackup(shadow);
		MapSpotBackup backup = daoPartial.selectMapSpotBackup(shadow).get(0); // sqlSession.selectOne("satellitegraphservice.selectMapSpotBackup",
																		// shadow);
		if (null == backup)
			return null;
		
		IMapSpot result = null;
		if ("AllWeather".equalsIgnoreCase(shadow.getBizType())) {
			result = JSON.parseObject(backup.getEntity(), SwMapspot.class);
		}else if ("YearSatellite".equalsIgnoreCase(shadow.getBizType())){
			result = JSON.parseObject(backup.getEntity(), MapSpot2016.class);
		}

		result.setShape(backup.getShape());
		return result;
	}


}
