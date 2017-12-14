package phalaenopsis.common.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Date;
import java.util.UUID;

import com.alibaba.fastjson.JSON;

import phalaenopsis.common.entity.AppSettings;
import phalaenopsis.patrolManagement.entity.PatrolTrackEntity;
import phalaenopsis.patrolManagement.util.VeDate;

public class RederFileHelper {
	
	public static synchronized void writerPropertiesMongoDb(PatrolTrackEntity patrolTrackEntity,AppSettings appSettings) {
		ObjectOutputStream os = null;
		String name = UUID.randomUUID().toString()+".d";
		String path = appSettings.getPath();
		try {
			File f = new File(path);
			if(!f.exists()){
				f.mkdir();
			}
			String ps = path + File.separator+name;
			File file = new File(ps);
			os = new ObjectOutputStream(new FileOutputStream(file));
			os.writeObject(patrolTrackEntity);//entity入文件
			os.flush();
		} catch (Exception e) {
			LogUtil.error(RederFileHelper.class,JSON.toJSONString(patrolTrackEntity),e);
		} finally {
			try {
				os.close();
			} catch (IOException e) {
				
			}
		}
		writerPropertiesBackMongoDb(patrolTrackEntity,appSettings,name);
		
	}
	

	public static synchronized void writerPropertiesBackMongoDb(PatrolTrackEntity patrolTrackEntity,AppSettings appSettings,String name) {
		ObjectOutputStream os = null;
		//String name = value+".d";
		try {
			String path = appSettings.getBackupsPath();
			File f = new File(path);
			if(!f.exists()){
				f.mkdir();
			}
			String date = VeDate.dateToStr(new Date());
			String p = appSettings.getBackupsPath() + File.separator + date;
			File fi = new File(p);
			if(!fi.exists()){
				fi.mkdir();
			}
			String pp = path + File.separator + date+File.separator+patrolTrackEntity.getPatrol_usr_id();
			File fis = new File(pp);
			if(!fis.exists()){
				fis.mkdir();
			}
			String ps = path + File.separator + date+File.separator+patrolTrackEntity.getPatrol_usr_id()+ File.separator+name;
			File file = new File(ps);
			os = new ObjectOutputStream(new FileOutputStream(file));
			os.writeObject(patrolTrackEntity);//entity入文件
			os.flush();
		} catch (Exception e) {
			LogUtil.error(RederFileHelper.class,JSON.toJSONString(patrolTrackEntity),e);
		} finally {
			try {
				os.close();
			} catch (IOException e) {
				
			}
		}

	}
	
}
