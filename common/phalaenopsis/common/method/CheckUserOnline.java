package phalaenopsis.common.method;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import phalaenopsis.common.entity.AppSettings;
import phalaenopsis.common.entity.User;
import phalaenopsis.common.service.UserService;
import phalaenopsis.common.util.CheckUserUtil;

@Component
public class CheckUserOnline {
	@Autowired
	private UserService userService;
	private AppSettings appSettings = new AppSettings();
	private static Logger logger = Logger.getLogger(CheckUserOnline.class);
	public CheckUserOnline() {
//		System.out.println("Bootstrapping");
//
//		FutureTask<String> task = new FutureTask<String>(new Callable<String>() {
//			@Override
//			public String call() throws Exception {
//				taskstart(); // 使用另一个线程来执行该方法，会避免占用Tomcat的启动时间
//				return "Collection Completed";
//			}
//		});
//
//		new Thread(task).start();
		
	}

	public void taskstart() throws InterruptedException {
		System.out.println("taskstart");
		if("true".equals(appSettings.getIsStartCheck())){
			Map<String,String> newmap =CheckUserUtil.newDataOnline;
			Map<String,String> oldmap =CheckUserUtil.oldDataOnline;
			if (!newmap.isEmpty()) {
				for (Map.Entry<String, String> entry : newmap.entrySet()) {
					String oldvalue = oldmap.get(entry.getKey());
					String newvalue =  entry.getValue();
					logger.info("CheckUserOnline在线离线：["+oldvalue+"]--------------["+newvalue+"]");
					User u = userService.getUserById(entry.getKey());
					if (oldvalue!=null&&oldvalue.equals(newvalue)) {
						if(u!=null){
							if(!"off".equals(u.getIsOnline())){
								User user = new User();
								user.setId(entry.getKey());
								user.setIsOnline("off");
								userService.updateUserIsOnline(user);
							}
							// 离线修改在线状态
							CheckUserUtil.oldDataOnline.put(entry.getKey(), newvalue);
							CheckUserUtil.newDataOnline.put(entry.getKey(), newvalue);
						}
					} else {
						if(u!=null){
//							long olds = Long.valueOf(oldvalue);
//							long news = Long.valueOf(newvalue);
//							long v = (news-olds)/1000;
							if(!"on".equals(u.getIsOnline())){
								// 离线修改在线状态
								User user = new User();
								user.setId(entry.getKey());
								user.setIsOnline("on");
								userService.updateUserIsOnline(user);
							}
//							if(v>15){
//								CheckUserUtil.oldDataOnline.put(entry.getKey(), entry.getValue());
//							}
							// 在线更新最新时间
							//CheckUserUtil.oldDataOnline.put(entry.getKey(), entry.getValue());
							CheckUserUtil.oldDataOnline.put(entry.getKey(), entry.getValue());
							
						}
					}
				} 
			}
		}
		System.out.println("taskend");
	}

}
