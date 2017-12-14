package phalaenopsis.fgbz.dao;

import phalaenopsis.fgbz.entity.FgbzDictory;

import java.util.List;

/**
 * Created by 13260 on 2017/12/13.
 */
public interface FgbzDicDao {

    List<FgbzDictory>  getPublishDep();

    List<FgbzDictory> getLawstandardState();
}
