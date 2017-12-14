package phalaenopsis.patrolManagement.service;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import phalaenopsis.common.entity.AppSettings;
import phalaenopsis.common.util.RederFileHelper;
import phalaenopsis.patrolManagement.bean.PatrolTrackBean;
import phalaenopsis.patrolManagement.bean.PatrolTrackFormBean;
import phalaenopsis.patrolManagement.bean.PatrolTrackViewBean;
import phalaenopsis.patrolManagement.dao.PatrolTrackDao;
import phalaenopsis.patrolManagement.entity.PatrolTrackEntity;
import phalaenopsis.patrolManagement.entity.PatrolTrackLocation;
import phalaenopsis.patrolManagement.service.PatrolTrackService;
import phalaenopsis.patrolManagement.util.CountDistance;
import phalaenopsis.patrolManagement.util.VeDate;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSON;

/**
 * 
 * @author weiz2902
 *
 */
@Repository
public class PatrolTrackService {

	public static Map<String,List<Double>> trackData = new HashMap<String,List<Double>>();
	
	private static Logger logger = Logger.getLogger(PatrolTrackService.class);
	private AppSettings appSettings = new AppSettings();
	@Autowired
	private PatrolTrackDao patrolDao;

	public List<PatrolTrackEntity> findList(int skip, int limit) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<PatrolTrackEntity> findListByAge(int age) {
		// TODO Auto-generated method stub
		return null;
	}

	public PatrolTrackEntity findOne(String id) {
		patrolDao.findOne(id);
		return null;
	}

	public List<PatrolTrackEntity> findListByUserID(String userId) {
		
		return patrolDao.findListByUserID(userId);
	}
	
	public PatrolTrackViewBean getTrackingAuditList(Date startTime,Date endTime,String userId,PatrolTrackViewBean patrolTrackViewBean) {
		return patrolDao.getTrackingAuditList(startTime,endTime,userId,patrolTrackViewBean);
		
	}
	public double getTrackingDistance(Date startTime, Date endTime, String userId) {
		return patrolDao.getTrackingDistance(startTime,endTime,userId);
		
	}
	
	public PatrolTrackEntity findOneByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	public PatrolTrackEntity tranEntity(double xpos,double ypos,double num,PatrolTrackBean patrolTrackBean){
		PatrolTrackEntity patrolTrackEntity = new PatrolTrackEntity();
		patrolTrackEntity.set_id(UUID.randomUUID().toString());
		patrolTrackEntity.setDistance(num);
		patrolTrackEntity.setPatrol_direction(patrolTrackBean.getPatrolDirection());
		patrolTrackEntity.setPatrol_org_id(patrolTrackBean.getPatrolOrgId());
		patrolTrackEntity.setPatrol_usr_id(patrolTrackBean.getPatrolUserId());
		patrolTrackEntity.setPost_time(VeDate.strToDateLong(patrolTrackBean.getTempPatrolTime()));
		patrolTrackEntity.setFlag(patrolTrackBean.getFlag());
		//鍧愭爣
		PatrolTrackLocation patrolTrackLocation = new PatrolTrackLocation();
		List<Double> coordinates = new ArrayList<Double>();
		coordinates.add(xpos);
		coordinates.add(ypos);
		patrolTrackLocation.setCoordinates(coordinates);
		//----------------------------------------------
		patrolTrackEntity.setLocation(patrolTrackLocation);
		return  patrolTrackEntity;
	} 
	
	
	public void insert(PatrolTrackFormBean entity,String userId) {
		double firstx = 0d;
		double firsty = 0d;
		List<PatrolTrackBean> list = entity.getPatrolList();
		if(list!=null&&list.size()>0){
			//第一个点坐标 
			PatrolTrackBean bean = list.get(0);
			firstx = bean.getXpos();
			firsty = bean.getYpos();
			for(PatrolTrackBean patrolTrackBean :list){
				//获取最新坐标点存入内存
				double secondXpos = patrolTrackBean.getXpos();
				double secondYpos = patrolTrackBean.getYpos();
				//计算坐标点距离
				double num = CountDistance.GetDistance(firstx, firsty, secondXpos, secondYpos);
				//all.add(tranEntity(secondXpos,secondYpos,num,patrolTrackBean));
				//有效点入库
				PatrolTrackEntity patrolTrackEntity = tranEntity(secondXpos,secondYpos,num,patrolTrackBean);
				logger.info(JSON.toJSONString(patrolTrackEntity));
				patrolDao.insert(patrolTrackEntity);
				if("true".equals(appSettings.getIsSyncMongodb())){
					RederFileHelper.writerPropertiesMongoDb(patrolTrackEntity,appSettings);
					//RederFileHelper.writerPropertiesBackMongoDb(patrolTrackEntity);
				}
				firstx = secondXpos;
				firsty = secondYpos;
				}
			}
			//patrolDao.insert(all);
	}

	public void update(PatrolTrackEntity entity) {
		// TODO Auto-generated method stub
		
	}


}
