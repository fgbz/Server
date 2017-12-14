package phalaenopsis.common.method;


import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.springframework.web.context.support.WebApplicationContextUtils;
import phalaenopsis.common.service.DataDictionaryService;

/**
 * 初始化信息
 * @author dongdongt
 *
 */
public class ConfigListener implements ServletContextListener{

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		//初始化
//		DataDictionaryService  configService = WebApplicationContextUtils.getWebApplicationContext(sce.getServletContext()).getBean(DataDictionaryService.class);
//		System.out.println("初始化字典表信息到缓存");
//		//字典表信息
//		configService.getAllDictionariesCache();
//		//region信息
//		configService.getCityRegions();
//		//用户信息
//		configService.getAllUserMap();
//		System.out.println("初始化字典表结束");
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		
	}

}
