package phalaenopsis.common.method;

import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

@SuppressWarnings({"unchecked"})
public class ManualAssembly {
	
	public static <T> T getAssembly(String id)
	{
		WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
		T t = (T)webApplicationContext.getBean(id);
		return t;
	}

}
