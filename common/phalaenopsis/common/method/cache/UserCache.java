package phalaenopsis.common.method.cache;

public class UserCache extends DefaultCache {

	private static final String USER_CACHE = "userCache";

	/**
	 * 写入SYS_CACHE缓存
	 * 
	 * @param key
	 * @return
	 */
	public static void put(String key, Object value) {
		put(USER_CACHE, key, value);
	}

	/**
	 * 从缓存中移除
	 * 
	 * @param key
	 * @return
	 */
	public static void remove(String key) {
		remove(USER_CACHE, key);
	}
	

	/**
	 * 获取缓存中数据
	 * 
	 * @param key
	 * @return
	 */
	public static Object get(String key) {
		return get(USER_CACHE, key);
	}


}
