package phalaenopsis.common.method.cache;

import org.springframework.stereotype.Repository;

public class SysMapCache extends DefaultCache {
	
	private static final String SysMap_CACHE = "sysMapCache";

	/**
	 * 写入SYS_CACHE缓存
	 * 
	 * @param key
	 * @return
	 */
	public static void put(String key, Object value) {
		put(SysMap_CACHE, key, value);
	}

	/**
	 * 从缓存中移除
	 * 
	 * @param key
	 * @return
	 */
	public static void remove(String key) {
		remove(SysMap_CACHE, key);
	}
	

	/**
	 * 获取缓存中数据
	 * 
	 * @param key
	 * @return
	 */
	public static Object get(String key) {
		return get(SysMap_CACHE, key);
	}
}
