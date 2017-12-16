package phalaenopsis.fgbz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import phalaenopsis.fgbz.dao.FgbzDicDao;
import phalaenopsis.fgbz.entity.FG_Menu;
import phalaenopsis.fgbz.entity.FgbzDictory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 13260 on 2017/12/13.
 */
@Service("fgbzDicService")
public class FgbzDicService {

    @Autowired
    private FgbzDicDao fgbzDicDao;

    @Autowired
    private SystemServie systemServie;

    public Map<Object,Object> getAllFgbzDictory() {

        Map<Object,Object> map = new HashMap<>();

        List<FgbzDictory> listPub = fgbzDicDao.getPublishDep();
        List<FgbzDictory> listState= fgbzDicDao.getLawstandardState();
        List<FG_Menu> listMenus = systemServie.getAllMenus();

        map.put("Pub",listPub);
        map.put("State",listState);
        map.put("Menu",listMenus);

        return map;
    }
}
