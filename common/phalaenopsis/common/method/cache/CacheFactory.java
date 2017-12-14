package phalaenopsis.common.method.cache;

import java.util.HashMap;
import java.util.Map;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.management.Cache;
import phalaenopsis.common.method.SpringContext;

public class CacheFactory {

	//Map<String, Cache> map = new HashMap<String, Cache>();
	
	private static CacheManager cacheManager = ((CacheManager) SpringContext.getBean("cacheManager"));

	public net.sf.ehcache.Cache getCahceh(String cacheName)
	{
		net.sf.ehcache.Cache cache = cacheManager.getCache(cacheName);
		if (null == cache) {
			cacheManager.addCache(cacheName);
			cache = cacheManager.getCache(cacheName);
			cache.getCacheConfiguration().setEternal(true);
		}
		return cache;
	}

}
