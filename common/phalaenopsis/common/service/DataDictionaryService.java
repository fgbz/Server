package phalaenopsis.common.service;

import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import phalaenopsis.common.dao.DataDictionaryDao;
import phalaenopsis.common.dao.UserDao;
import phalaenopsis.common.entity.*;
import phalaenopsis.common.method.Tools.Linq;
import phalaenopsis.common.method.Tools.StrUtil;
import phalaenopsis.common.method.cache.DataCache;
import phalaenopsis.common.method.cache.RegionCache;
import phalaenopsis.common.method.cache.SysCache;
import phalaenopsis.common.method.cache.SysMapCache;
import phalaenopsis.common.method.cache.UserCache;
import phalaenopsis.common.method.sort.SortList;

@Service("dataDictionaryService")
public class DataDictionaryService {

    @Autowired
    private DataDictionaryDao dataDicDao;
    @Autowired
    private UserDao userDao;

    private List<DataDictionaryItem> dataDictionaryItems = null;

    private Lock lock = new ReentrantLock(); //锁对象

    // /**
    // * 第一个key是Module，第二个key是Type
    // */
    // private Map<String, Map<String, List<DataDictionaryItem>>>
    // dictionariesMap = new HashMap<String, Map<String,
    // List<DataDictionaryItem>>>();

    /**
     * 提供根据Model和Tyep，找到对应Value的Text值
     *
     * @param value  字典key值
     * @param type   字典类型
     * @param module 模块名称
     * @return
     */
    @SuppressWarnings("unchecked")
    public String getDictionaryText(String value, String type, String module) {
        if (StrUtil.isNullOrEmpty(value) || StrUtil.isNullOrEmpty(type) || StrUtil.isNullOrEmpty(module)) {
            return "";
        }

        final Map<String, Map<String, List<DataDictionaryItem>>> map;
        if (null == SysCache.get("SysMapDictionary")) {
            map = getDictionariesMap();
        } else {
            map = (Map<String, Map<String, List<DataDictionaryItem>>>) SysCache.get("SysMapDictionary");
        }

        if (null != map) {
            List<DataDictionaryItem> list = (List<DataDictionaryItem>) map.get(module).get(type);
            if (null != list) {
                DataDictionaryItem data = Linq.extEquals(list, "value", value);
                if (null != data) {
                    return data.getText();
                }
            }
        }
        return "";
    }

    /**
     * 获取对应模块下，对应类型的字段
     *
     * @param module 模块
     * @param type   类型
     * @return 返回对应字典数据
     */
    public List<DataDictionaryItem> getDataDictionaryItems(String module, String type) {
        if (StrUtil.isNullOrEmpty(module) || StrUtil.isNullOrEmpty(type)) {
            return null;
        }

        final Map<String, Map<String, List<DataDictionaryItem>>> map;
        if (null == SysCache.get("SysMapDictionary")) {
            map = getDictionariesMap();
        } else {
            map = (Map<String, Map<String, List<DataDictionaryItem>>>) SysCache.get("SysMapDictionary");
        }

        if (null != map) {

            return map.get(module).get(type) == null ? new ArrayList<DataDictionaryItem>() : (List<DataDictionaryItem>) map.get(module).get(type);
        }

        return null;
    }


    /**
     * 将所有用户信息放入缓存中
     */
    public void getAllUserMap() {
        List<User> userlist = userDao.getAllUser();
        if (userlist.size() > 0) {
            for (User user : userlist) {
                User u = (User) UserCache.get(user.getId());
                if (u == null) {
                    UserCache.put(user.getId(), user);
                }
            }
        }
    }

    /**
     * 将字典数据整理成Map数据，方便取值
     *
     * @return
     */
    private Map<String, Map<String, List<DataDictionaryItem>>> getDictionariesMap() {
        Map<String, Map<String, List<DataDictionaryItem>>> dictionariesMap = new LinkedHashMap<String, Map<String, List<DataDictionaryItem>>>();
        dataDictionaryItems = getAllDictionaries();
        for (DataDictionaryItem dataDictionaryItem : dataDictionaryItems) {
            if (dictionariesMap.containsKey(dataDictionaryItem.getModule())) {
                Map<String, List<DataDictionaryItem>> map = dictionariesMap.get(dataDictionaryItem.getModule());
                if (map.containsKey(dataDictionaryItem.getType())) {
                    List<DataDictionaryItem> list = (List<DataDictionaryItem>) map.get(dataDictionaryItem.getType());
                    list.add(dataDictionaryItem);
                } else {
                    List<DataDictionaryItem> list = new ArrayList<DataDictionaryItem>();
                    list.add(dataDictionaryItem);
                    map.put(dataDictionaryItem.getType(), list);
                }
            } else {
                Map<String, List<DataDictionaryItem>> map = new LinkedHashMap<String, List<DataDictionaryItem>>();
                List<DataDictionaryItem> list = new ArrayList<DataDictionaryItem>();
                list.add(dataDictionaryItem);
                map.put(dataDictionaryItem.getType(), list);
                dictionariesMap.put(dataDictionaryItem.getModule(), map);
            }
        }
        SysCache.put("SysMapDictionary", dictionariesMap);
        return dictionariesMap;
    }

    /**
     * 获取所有数据字典 缓存处理
     *
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<DataDictionaryItem> getAllDictionaries() {
        //return dataDicDao.getAllDictionaries();

		final List<DataDictionaryItem> list;
		if (null == SysCache.get("SysDictionary")) {
			list = dataDicDao.getAllDictionaries();
			SysCache.put("SysDictionary", list);
		} else {
			list = (List<DataDictionaryItem>) SysCache.get("SysDictionary");
		}
		return list;

        // return dataDicDao.getAllDictionaries();
    }

    /**
     * 获取所有数据字典 缓存处理（字典缓存）
     */
    public void getAllDictionariesCache() {
        List<DataDictionaryItem> list = dataDicDao.getAllDictionaries();
        Map<String, List<DataDictionaryItem>> map = new HashMap<>();
        for (DataDictionaryItem item : list) {
            String key = item.getType() + item.getValue();
            DataDictionaryItem dictionaryItem = (DataDictionaryItem) DataCache.get(key);
            //将字典信息放入缓存中
            if (dictionaryItem == null) {
                DataCache.put(key, item);
            }
            if (map.containsKey(item.getType())) {
                List<DataDictionaryItem> temp = map.get(item.getType());
                temp.add(item);
                map.put(item.getType(), temp);
            } else {
                List<DataDictionaryItem> temp = new ArrayList<DataDictionaryItem>();
                temp.add(item);
                map.put(item.getType(), temp);
            }
        }
        for (Entry<String, List<DataDictionaryItem>> i : map.entrySet()) {
            SortList<DataDictionaryItem> sortList = new SortList<DataDictionaryItem>();
            sortList.sort(i.getValue(), "id", "asc");
        }
        SysMapCache.put("SysDictionary", map);
    }

    /**
     * 获取所有数据字典 缓存处理(根据类型分类)
     *
     * @return
     */
    @SuppressWarnings({"unchecked", "null"})
    public Map<String, List<DataDictionaryItem>> getAllDictionariesByType() {
        Map<String, List<DataDictionaryItem>> map = (Map<String, List<DataDictionaryItem>>) SysMapCache.get("SysDictionary");
        if (map == null) {
            List<DataDictionaryItem> list = dataDicDao.getAllDictionaries();
            for (DataDictionaryItem item : list) {
                String key = item.getType() + item.getValue();
                DataDictionaryItem dictionaryItem = (DataDictionaryItem) DataCache.get(key);
                //将字典信息放入缓存中
                if (dictionaryItem == null) {
                    DataCache.put(key, item);
                }
                if (map.containsKey(item.getType())) {
                    List<DataDictionaryItem> temp = map.get(item.getType());
                    temp.add(item);
                    map.put(item.getType(), temp);
                } else {
                    List<DataDictionaryItem> temp = new ArrayList<DataDictionaryItem>();
                    temp.add(item);
                    map.put(item.getType(), temp);
                }
            }
            for (Entry<String, List<DataDictionaryItem>> i : map.entrySet()) {
                SortList<DataDictionaryItem> sortList = new SortList<DataDictionaryItem>();
                sortList.sort(i.getValue(), "id", "asc");
            }
        }
        return map;
    }

    /**
     * 获取当前用户有权限的区域
     *
     * @return
     */
    public List<Region> getAuthorizeRegions() {
        return dataDicDao.getRegions(true);
    }

    /**
     * 获取所有区域
     *
     * @return
     */
    public List<Region> getAllRegions() {
        return dataDicDao.getRegions(false);
    }

    public List<Region> getRegions(boolean authorized) {
        return dataDicDao.getRegions(authorized);
    }

    /**
     * 获取所有市级区域
     *
     * @return
     */
    public List<Region> getCityRegions() {
        List<Region> allRegions = dataDicDao.getAllRegions();
        if (allRegions.size() > 0) {
            for (Region region : allRegions) {
                Region r = (Region) RegionCache.get(String.valueOf(region.getRegionID()));
                if (r == null) {
                    RegionCache.put(String.valueOf(region.getRegionID()), region);
                }
            }
        }
        List<Region> regions = dataDicDao.getCityRegions();
        return regions;
    }

    /**
     * 根据市编码获得县集合
     *
     * @param cityCode
     * @return
     */
    public List<Region> getCountyRegionsByCity(int cityCode) {
        List<Region> result = null;
        if (0 == cityCode)
            result = new ArrayList<Region>();
        else {
            result = dataDicDao.getCountyRegionsByCity(cityCode); // sqlSession.selectList("datadictionary.getCountyRegionsByCity",
            // cityCode);
        }

        return result;
    }

    public List<Region> GetRegionsByIDs(int[] ids) {
        return dataDicDao.GetRegionsByIDs(ids);
    }

    public List<Region> getOrganRegion(List<Region> regions) {
        return dataDicDao.getOrganRegion(regions);
    }

    public GeoJsonMap getRegionMap(String regiontype) {
        return dataDicDao.getRegionMap(regiontype);
    }


    @Transactional
    public boolean saveDictionary(DataDictionaryItem item) {

        if (item.getId() == 0) {
            lock.lock();
            try {
                item.setId(dataDicDao.getMaxIdPlus());
                dataDicDao.saveDictionary(item);
                List<DataDictionaryItem> items = getAllDictionaries();
                items.add(item);
                SysCache.put("SysDictionary", items);
                getDictionariesMap();
                return  true;
            }finally {
                lock.unlock();
            }
        }else {

            dataDicDao.saveDictionary(item);
            SysCache.remove("SysDictionary");
            getAllDictionaries();
            getDictionariesMap();
            return true;
        }
    }

    @Transactional
    public boolean deleteDictionary(List<Long> ids) {
//        List<DataDictionaryItem> items =  getAllDictionaries();
//        while (items.iterator().hasNext()){
//            DataDictionaryItem item = items.iterator().next();
//            if (ids.contains(item.getId())){
//                items.remove(item);
//            }
//        }

        dataDicDao.deleteDictionary(ids);
        SysCache.remove("SysDictionary");
        getAllDictionaries();
        getDictionariesMap();

        return true;
    }

    public PagingEntityForMobile<DataDictionaryItem> listDataDictionary(Page page) {
        Map<String, Object> map = page.getQueryCondition();
        PagingEntityForMobile<DataDictionaryItem> result = new PagingEntityForMobile<DataDictionaryItem>();
        result.CurrentList = dataDicDao.listDataDictionary(map);
        result.RecordCount = dataDicDao.countDataDictionary(map);
        result.calculatePageCount(page.getPageSize());
        return result;
    }
}
