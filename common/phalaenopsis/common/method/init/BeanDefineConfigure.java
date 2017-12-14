package phalaenopsis.common.method.init;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import phalaenopsis.common.method.SpringContext;
import phalaenopsis.common.method.Tools.StrUtil;
import phalaenopsis.common.service.DataDictionaryService;

@Controller
public class BeanDefineConfigure implements ApplicationListener<ApplicationEvent> {

	@Autowired
	private DataDictionaryService service;

	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		if (event instanceof ContextRefreshedEvent) {

			// try {
			// Class.forName("phalaenopsis.common.util.CharsetUtil");
			// } catch (ClassNotFoundException e1) {
			// // TODO Auto-generated catch block
			// e1.printStackTrace();
			// }

			try {
				init();
			} catch (InstantiationException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// Object object = service.getAllDictionaries();
			System.out.println("spring初始化完毕================================================888");
		}
	}

	private void init() throws InstantiationException, IllegalAccessException {

		WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();

		String[] beanNames = webApplicationContext.getBeanDefinitionNames();
//		int allBeansCount = webApplicationContext.getBeanDefinitionCount();

//		System.out.println("所有beans的数量是:"+allBeansCount);
		for(String beanName:beanNames){
			Class<?> beanType = webApplicationContext.getType(beanName);

			if (null != beanType) {
				if (ILoad.class.isAssignableFrom(beanType)) {
					ILoad bean = (ILoad) webApplicationContext.getBean(beanName);
					//Runnable runnable = ()->(bean.load());
					new Thread(()->bean.load()).start();//.start();
					//bean.load();
				}
			}

//
//			Package beanPackage = beanType.getPackage();
//
//			Object bean = webApplicationContext.getBean(beanName);
//
//
//			System.out.println("BeanName: " +beanName);
//			System.out.println("Bean的类型：" + beanType);
//			System.out.println("Bean所在的包：" + beanPackage);
//			System.out.println("\r\n");
		}

//
//		Class<?> initClass = ILoad.class;
//		List<Class<?>> classlist = ClassUtil.getAllClass(initClass);
//		for (final Class<?> item : classlist) {
//			final Thread thread = new Thread() {
//				public void run() {
//					String name = item.getName();
//					String className = name.split("\\.")[name.split("\\.").length - 1];
//					className = StrUtil.firstCharsetLower(className);
//					ILoad iLoad = SpringContext.getBean(className);
//					iLoad.load();
//				}
//			};
//			thread.start();
//		}

	}

}
