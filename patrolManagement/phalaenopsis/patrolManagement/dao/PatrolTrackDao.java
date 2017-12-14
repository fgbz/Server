package phalaenopsis.patrolManagement.dao;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import phalaenopsis.common.entity.AppSettings;
import phalaenopsis.patrolManagement.bean.PatrolTrackEntityBean;
import phalaenopsis.patrolManagement.bean.PatrolTrackViewBean;
import phalaenopsis.patrolManagement.dao.PatrolTrackDao;
import phalaenopsis.patrolManagement.entity.PatrolTrackEntity;
import phalaenopsis.patrolManagement.entity.PatrolTrackLocation;
import phalaenopsis.patrolManagement.util.VeDate;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.google.gson.Gson;
import com.mongodb.AggregationOutput;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

/**
 * 
 * @author weiz2902
 *
 */

@Repository
public class PatrolTrackDao {

	private static Logger logger = Logger.getLogger(PatrolTrackDao.class);

	AppSettings appSettings = new AppSettings();
	public String dataname = "patrolTrack";
	public PatrolTrackDao(){
		dataname = appSettings.getDataname();
	}
	//public static final String dataname = "patrolTrack";
	//public static final String dataname = "test";
	public static Map<String,String> map = new HashMap<String,String>();
	@Autowired
	private MongoTemplate mongoTemplate;

	public void createCollection() {
		if (!this.mongoTemplate.collectionExists(PatrolTrackEntity.class)) {
			this.mongoTemplate.createCollection(PatrolTrackEntity.class);
		}
	}

	public List<PatrolTrackEntity> findList(int skip, int limit) {
		Query query = new Query();
		// query.with(new Sort(new Order(Direction.ASC, "_id")));
		query.skip(skip).limit(limit);
		return this.mongoTemplate.find(query, PatrolTrackEntity.class);
	}

	

	public void getTracking1() {
			List<DBObject> ops = new ArrayList<DBObject>();
			String[] str = new String[1];
			str[0]="$distance";
			DBObject all = new BasicDBObject("_id",new BasicDBObject("userId","$patrol_usr_id"))
			.append("totalDistance",new BasicDBObject("$sum",new BasicDBObject("$multiply",str)))
			.append("averageDistance", new BasicDBObject("$avg","$distance"))
			.append("count", new BasicDBObject("$sum",1));

			DBObject count = new BasicDBObject("$group",all);

			ops.add(count);
			DBCollection collection = this.mongoTemplate.getCollection(dataname);
			AggregationOutput output = collection.aggregate(count);
			Iterable<DBObject> list= output.results();  
	        for(DBObject dbObject:list){  
	            System.out.println(dbObject.get("_id")+" -"+ dbObject.get("totalDistance") +"- "+dbObject.get("count")+"- "+dbObject.get("averageDistance"));  
	        }  
	}
	
	/**
	 * 算距离
	 * @param startTime
	 * @param endTime
	 * @param userId
	 * @return double
	 */
	public double getTrackingDistance(Date startTime, Date endTime, String userId) {
		double f1 = 0;
		 //match  
        BasicDBObject[] array = {    
                new BasicDBObject("post_time", new BasicDBObject("$gte",startTime)),    
                new BasicDBObject("post_time", new BasicDBObject("$lte",endTime)),
                new BasicDBObject("patrol_usr_id", new BasicDBObject("$eq",userId))};    
        BasicDBObject cond = new BasicDBObject();    
        cond.put("$and", array);    
        DBObject match = new BasicDBObject("$match", cond);    
        //group  
        DBObject groupFields = new BasicDBObject( "_id", "$patrol_usr_id");   
        //groupFields.put("count", new BasicDBObject( "$sum", 1));   
        groupFields.put("totalDistance", new BasicDBObject( "$sum", "$distance"));  
       // groupFields.put("averageDistance", new BasicDBObject( "$avg", "$distance"));   
        DBObject group = new BasicDBObject("$group", groupFields);  
        //sort  
        //DBObject sort = new BasicDBObject("$sort", new BasicDBObject("_id", 1));  
        //limit  
        //DBObject limit = new BasicDBObject("$limit", 50);  
  
        //AggregationOutput output = this.mongoTemplate.getCollection(dataname).aggregate(match,group,sort,limit);
        AggregationOutput output = this.mongoTemplate.getCollection(dataname).aggregate(match,group);
        Iterable<DBObject> list= output.results();  
        for(DBObject dbObject:list){  
            System.out.println(dbObject.get("_id") +" "+dbObject.get("totalDistance"));  
            String distance = String.valueOf(dbObject.get("totalDistance"));
            BigDecimal bg = new BigDecimal(Double.valueOf(distance)/1000);  
            f1 = bg.setScale(3, BigDecimal.ROUND_FLOOR).doubleValue(); 
        }
		return f1; 
}
	
	public PatrolTrackViewBean getTrackingAuditList(Date startTime, Date endTime, String userId,
			PatrolTrackViewBean patrolTrackViewBean) {
		List<PatrolTrackEntityBean> list = new ArrayList<PatrolTrackEntityBean>();
		DBCollection collection = this.mongoTemplate.getCollection(dataname);

		BasicDBObject cond = new BasicDBObject();
		if (startTime != null && endTime != null) {
			cond.append("post_time", new BasicDBObject("$gte", startTime).append("$lte", endTime));
		}
		if (startTime != null && endTime == null) {
			cond.append("post_time", new BasicDBObject("$gte", startTime));
		}
		cond.append("patrol_usr_id", new BasicDBObject("$eq", userId));

		BasicDBList condList = new BasicDBList();
		condList.add(cond);

		BasicDBObject searchCond = new BasicDBObject();
		searchCond.put("$and", condList);

		double patrolDistance = 0l;
		DBCursor cursor = collection.find(searchCond).sort(new BasicDBObject("post_time", 1));// -1// desc// 1 asc
		Gson gson = new Gson();
		//Iterator<DBObject> it = cursor.iterator();
		while (cursor.hasNext()) {

			DBObject obj = cursor.next();

			PatrolTrackEntityBean patrolTrackEntity = gson.fromJson(obj.toString(), PatrolTrackEntityBean.class);
			String date = String.valueOf(patrolTrackEntity.getPost_time().get("$date"));
			date = date.replace("Z", " UTC");   
		    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");  
		    try {
				Date dt = sdf.parse(date);
				patrolTrackEntity.setDateHMS(VeDate.getHHmmss(dt));
			} catch (ParseException e) {
				e.printStackTrace();
			}   
			patrolDistance = patrolDistance + patrolTrackEntity.getDistance();
			list.add(patrolTrackEntity);

		}
		//修改时间转换问题
		BigDecimal bg = new BigDecimal(patrolDistance/1000);  
        double f1 = bg.setScale(3, BigDecimal.ROUND_FLOOR).doubleValue();  
		patrolTrackViewBean.setPatrolDistance(String.valueOf(f1));
		patrolTrackViewBean.setPatrolData(list);
		return patrolTrackViewBean;
	}

	public List<PatrolTrackEntity> findListBySum() {
		String start = "2017-09-12 15:30:11.979";
		String end = "2017-09-16 15:50:11.979";
		// match
		BasicDBObject[] array = { new BasicDBObject("birth", new BasicDBObject("$gte", VeDate.strToDateLong(start))),
				new BasicDBObject("birth", new BasicDBObject("$lte", VeDate.strToDateLong(end))) };
		BasicDBObject cond = new BasicDBObject();
		cond.put("$and", array);
		DBObject match = new BasicDBObject("$match", cond);
		// group
		DBObject groupFields = new BasicDBObject("_id", null);
		groupFields.put("sum", new BasicDBObject("$sum", "$works"));
		DBObject group = new BasicDBObject("$group", groupFields);
		// sort
		// DBObject sort = new BasicDBObject("$sort", new BasicDBObject("_id",
		// 1));
		// limit
		// DBObject limit = new BasicDBObject("$limit", 5);

		// AggregationOutput output =
		// mongoTemplate.getCollection("tsdata").aggregate(match,group,sort,limit);
		AggregationOutput output = mongoTemplate.getCollection("user").aggregate(match, group);
		Iterable<DBObject> list = output.results();
		for (DBObject dbObject : list) {
			System.out.println(dbObject.get("_id") + " " + dbObject.get("sum"));
		}
		return null;
	}

	public List<PatrolTrackEntity> findListByUserID(String userId) {
		List<PatrolTrackEntity> to = new ArrayList<PatrolTrackEntity>();
		DBCollection collection = this.mongoTemplate.getCollection("test");
		BasicDBObject queryObject = new BasicDBObject("patrol_usr_id", userId);
		DBCursor cursor = collection.find(queryObject);
		Gson gson = new Gson();
		while (cursor.hasNext()) {
			DBObject obj = cursor.next();
			PatrolTrackEntity u = gson.fromJson(obj.toString(), PatrolTrackEntity.class);
			to.add(u);
		}
		return to;
	}

	public PatrolTrackEntity findOne(String id) {
		Query query = new Query();
		query.addCriteria(new Criteria("patrol_usr_id").is(id));
		return this.mongoTemplate.findOne(query, PatrolTrackEntity.class);
	}

	public PatrolTrackEntity findOneByUsername(String username) {
		Query query = new Query();
		query.addCriteria(new Criteria("name.username").is(username));
		return this.mongoTemplate.findOne(query, PatrolTrackEntity.class);
	}

	public void insert(PatrolTrackEntity entity) {
		DBCollection collection = this.mongoTemplate.getCollection(dataname);
		//Gson gson = new Gson();
		//DBObject dbObject = (DBObject) JSON.parse(gson.toJson(entity));
		 BasicDBObject attrs = new BasicDBObject(); 
		 attrs.put("_id", entity.get_id());
		 attrs.put("patrol_usr_id", entity.getPatrol_usr_id());
		 attrs.put("patrol_org_id", entity.getPatrol_org_id());
		 attrs.put("post_time", entity.getPost_time());
		 attrs.put("distance", entity.getDistance());
		 attrs.put("patrol_direction", entity.getPatrol_direction());
		 attrs.put("flag", entity.getFlag());
		 BasicDBObject attrsl = new BasicDBObject(); 
		 PatrolTrackLocation loc = entity.getLocation();
		 attrsl.put("type", loc.getType());
		 attrsl.put("coordinates", loc.getCoordinates());
		 attrs.put("location", attrsl);
		 logger.info(attrs.toString());
		collection.save(attrs);
//		if(!map.containsKey("patrol_usr_id")){
//			DBObject match = new BasicDBObject("post_time", 1).append("patrol_usr_id", 1);
//			collection.createIndex(match);
//			map.put("patrol_usr_id", "patrol_usr_id");
//		}
		
	}
	
	public List<PatrolTrackEntityBean> getAuditList(List list){
		List<PatrolTrackEntityBean> listp = new ArrayList<PatrolTrackEntityBean>();
		DBCollection collection = this.mongoTemplate.getCollection(dataname);
		BasicDBObject cond = new BasicDBObject();
		cond.append("patrol_usr_id", new BasicDBObject("$in",list));
		DBObject match = new BasicDBObject("$match", cond);
		DBObject sort1 = new BasicDBObject("patrol_usr_id", -1);
		sort1.put("post_time", -1);
		DBObject sort = new BasicDBObject("$sort", sort1);
		DBObject basicDBObject = new BasicDBObject("_id","$patrol_usr_id");
		basicDBObject.put("post_time", new BasicDBObject("$first","$post_time"));
		basicDBObject.put("location", new BasicDBObject("$first","$location"));
		
		DBObject group = new BasicDBObject("$group", basicDBObject);
		AggregationOutput output = collection.aggregate(match,sort,group);
		Gson gson = new Gson();
		PatrolTrackEntityBean patrolTrackEntity = new PatrolTrackEntityBean();
		
		Iterable<DBObject> listpa = output.results();
		for (DBObject dbObject : listpa) {
			patrolTrackEntity = gson.fromJson(dbObject.toString(), PatrolTrackEntityBean.class);
			String patrol_usr_id = (String) dbObject.get("_id");
			patrolTrackEntity.setPatrol_usr_id(patrol_usr_id);
			listp.add(patrolTrackEntity);
		}
		return listp;
	}
	
	public PatrolTrackEntityBean getAudit(String userId) {
		DBCollection collection = this.mongoTemplate.getCollection(dataname);
		PatrolTrackEntityBean patrolTrackEntity = new PatrolTrackEntityBean();
		BasicDBObject cond = new BasicDBObject();
		cond.append("patrol_usr_id", new BasicDBObject("$eq", userId));
		BasicDBList condList = new BasicDBList();
		condList.add(cond);
		BasicDBObject searchCond = new BasicDBObject();
		searchCond.put("$and", condList);

		DBCursor cursor = collection.find(searchCond).sort(new BasicDBObject("post_time", -1)).limit(1);// -1// desc// 1 asc
		Gson gson = new Gson();
		while (cursor.hasNext()) {
			DBObject obj = cursor.next();
			patrolTrackEntity = gson.fromJson(obj.toString(), PatrolTrackEntityBean.class);
		}
		return patrolTrackEntity;
	}
	
	public double getDistance(String userId,Date startTime,Date endTime) {
		List<PatrolTrackEntityBean> list = new ArrayList<PatrolTrackEntityBean>();
		PatrolTrackEntityBean patrolTrackEntityBean = new PatrolTrackEntityBean();
		DBCollection collection = this.mongoTemplate.getCollection(dataname);

		BasicDBObject cond = new BasicDBObject();
		if (startTime != null && endTime != null) {
			cond.append("post_time", new BasicDBObject("$gte", startTime).append("$lte", endTime));
		}
		
		cond.append("patrol_usr_id", new BasicDBObject("$eq", userId));

		BasicDBList condList = new BasicDBList();
		condList.add(cond);

		BasicDBObject searchCond = new BasicDBObject();
		searchCond.put("$and", condList);

		double patrolDistance = 0l;
		
		DBCursor cursor = collection.find(searchCond).sort(new BasicDBObject("post_time", 1));// -1// desc// 1 asc
		Gson gson = new Gson();
		while (cursor.hasNext()) {
			DBObject obj = cursor.next();
			PatrolTrackEntityBean patrolTrackEntity = gson.fromJson(obj.toString(), PatrolTrackEntityBean.class);
			patrolDistance = patrolDistance + patrolTrackEntity.getDistance();
			list.add(patrolTrackEntity);

		}
		//修改时间转换问题
		BigDecimal bg = new BigDecimal(patrolDistance/1000);  
        double f1 = bg.setScale(3, BigDecimal.ROUND_FLOOR).doubleValue();  
        patrolTrackEntityBean.setDistance(f1);
		return f1;
	}

}
