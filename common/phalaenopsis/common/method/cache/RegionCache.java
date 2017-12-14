package phalaenopsis.common.method.cache;

import net.sf.ehcache.CacheManager;

public class RegionCache extends DefaultCache{
	private static final String REGION_CACHE = "regionCache";
	/**
	 * 写入SYS_CACHE缓存
	 * 
	 * @param key
	 * @return
	 */
	public static void put(String key, Object value) {
		put(REGION_CACHE, key, value);
	}

	/**
	 * 从缓存中移除
	 * 
	 * @param key
	 * @return
	 */
	public static void remove(String key) {
		remove(REGION_CACHE, key);
	}
	

	/**
	 * 获取缓存中数据
	 * 
	 * @param key
	 * @return
	 */
	public static Object get(String key) {
		Object object = get(REGION_CACHE, key);
		return object;
	}
	/**
	 * 清除
	 * @param cacheName
	 * @return
	 * @throws Exception
	 */
	public static boolean removeAllEhcache(String cacheName) throws Exception {  
         try {  
             CacheManager myManager = CacheManager.create();  
             myManager.removalAll();  
             return true;  
         } catch (Exception e) {  
             throw new Exception("remove cache " + cacheName + " failed!!!");  
         }  
    }  
}
