package phalaenopsis.common.method.cache;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import phalaenopsis.common.method.SpringContext;

public class DefaultCache {

	private static CacheManager cacheManager = ((CacheManager) SpringContext.getBean("cacheManager"));

	/**
	 * 缓存名称。默认为空。为空的情况下，使用的是defaultCache
	 */
	private static String cacheName;

	public String getCacheName() {
		return cacheName;
	}

	public void setCacheName(String cacheName) {
		DefaultCache.cacheName = cacheName;
	}

	/**
	 * 写入SYS_CACHE缓存
	 * 
	 * @param key
	 * @return
	 */
	public static void put(String key, Object value) {
		put(cacheName, key, value);
	}

	public static void put(String cacheName, String key, Object value) {
		Element element = new Element(key, value);
		getCache(cacheName).put(element);
	}

	/**
	 * 从缓存中移除
	 * 
	 * @param key
	 * @return
	 */
	public static void remove(String key) {
		remove(cacheName, key);
	}

	public static void remove(String cacheName, String key) {
		getCache(cacheName).remove(key);
	}

	/**
	 * 获取缓存中数据
	 * 
	 * @param key
	 * @return
	 */
	public static Object get(String key) {
		return get(cacheName, key);
	}

	public static Object get(String cacheName, String key) {
		Element element = getCache(cacheName).get(key);
		return element == null ? null : element.getObjectValue();
	}

	/**
	 * 获得一个Cache，没有则创建一个
	 * 
	 * @param cacheName
	 * @return
	 */
	private static Cache getCache(String cacheName) {
		net.sf.ehcache.Cache cache = cacheManager.getCache(cacheName);
		if (null == cache) {
			cacheManager.addCache(cacheName);
			cache = cacheManager.getCache(cacheName);
			cache.getCacheConfiguration().setEternal(true);
		}
		return cache;
	}

	public static CacheManager getCacheManager() {
		return cacheManager;
	}

}
