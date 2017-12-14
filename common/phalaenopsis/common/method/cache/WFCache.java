package phalaenopsis.common.method.cache;

import org.springframework.stereotype.Repository;

public class WFCache extends DefaultCache {

	private static final String WF_CACHE = "workFlowCache";

	/**
	 * 写入SYS_CACHE缓存
	 * 
	 * @param key
	 * @return
	 */
	public static void put(String key, Object value) {
		put(WF_CACHE, key, value);
	}

	/**
	 * 从缓存中移除
	 * 
	 * @param key
	 * @return
	 */
	public static void remove(String key) {
		remove(WF_CACHE, key);
	}
	

	/**
	 * 获取缓存中数据
	 * 
	 * @param key
	 * @return
	 */
	public static Object get(String key) {
		Object object = get(WF_CACHE, key);
		remove(key);
		return object;
	}
	
	/**
	 * 获取缓存中数据
	 * 
	 * @param key
	 * @return
	 */
	public static Object getData(String key) {
		Object object = get(WF_CACHE, key);
		return object;
	}

}
