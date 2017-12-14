package phalaenopsis.allWeather.service;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Random;

import org.apache.commons.httpclient.util.DateUtil;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import phalaenopsis.common.entity.AppSettings;
import phalaenopsis.common.method.Basis;
import phalaenopsis.common.method.Tools.DateUtils;

@Service("CoordiateJob")
public class CoordiateJobService extends Basis {

	public synchronized void deleteFiles() {
		File file = new File(getShapFolder()); // 坐标路径
		if (file.exists()) {
			File[] files = file.listFiles();
			Calendar nowTime = Calendar.getInstance();
			nowTime.add(Calendar.MINUTE, -30);
			Random random = new Random();
			String path = DateUtils.formatDate(nowTime.getTime()) + random.nextInt(10000);;
			
			for (int i = 0; i < files.length; i++) {
				if (path.compareTo(files[i].getName())>0){
					try {
						FileUtils.deleteDirectory(files[i]);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}
}
