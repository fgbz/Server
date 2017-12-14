package phalaenopsis.fgbz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import phalaenopsis.common.entity.Condition;
import phalaenopsis.common.entity.Page;
import phalaenopsis.common.entity.PagingEntity;
import phalaenopsis.common.method.Tools.GuidHelper;
import phalaenopsis.fgbz.dao.LawstandardDao;
import phalaenopsis.fgbz.entity.Lawstandard;
import phalaenopsis.fgbz.entity.LawstandardType;
import phalaenopsis.fgbz.entity.RefenceOrReplace;

import java.util.*;


/**
 *  法规标准
 */
@Service("lawstandardService")
public class LawstandardService {

    @Autowired
    private LawstandardDao lawstandardDao;

    private  List<LawstandardType> ids = new ArrayList<>();

    public int testCount(){
        int i = lawstandardDao.testCount();
        return i;
    }

    /**
     * 查询法规标准类别目录
     * @return
     */
    public List<LawstandardType> SelectLawstandardType() {
        return lawstandardDao.SelectLawstandardType();
    }

    /**
     * 新增或修改法规标准类别
     * @param lawstandardType
     * @return
     */
    public int  AddOrUpdateLawstandardType(LawstandardType lawstandardType){
        //新增
        if(lawstandardType.getId().equals("")){
            UUID uuid=UUID.randomUUID();
            String guid=uuid.toString();
            lawstandardType.setId(guid);
        }
        int num = lawstandardDao.AddOrUpdateLawstandardType(lawstandardType);

        return  num;
    }

    /**
     * 删除法规标准类别
     * @param lawstandardType
     * @return
     */
    public int  DeleteLawstandardType(LawstandardType lawstandardType){
        return lawstandardDao.DeleteLawstandardType(lawstandardType);
    }

    /**
     * 获取子节点
     * @param id
     * @return
     */
    public List<LawstandardType> getChildNode(String id){
        return lawstandardDao.getChildNode(id);
    }

    public  List<LawstandardType> getLawsTree(String id){

        List<LawstandardType> list = getChildNode(id);

        while (list!=null&&list.size()>0){
            ids.addAll(list);
            for(LawstandardType lawstandardType:list){
                list=getLawsTree(lawstandardType.getId());
            }
        }
        return list;
    }


    /**
     * 获取法规标准列表
     * @param page
     * @return
     */
    public PagingEntity<Lawstandard> getLawstandardList(@RequestBody Page page){

        Map<String, Object> conditions = new HashMap<String, Object>();

        if(page.getConditions()!=null) {
            //查询条件
            for (Condition condition : page.getConditions()) {
                if (condition.getKey().equals("Number")) {
                    conditions.put("Number", condition.getValue());
                } else if (condition.getKey().equals("Title")) {
                    conditions.put("Title", condition.getValue());
                } else if (condition.getKey().equals("FiledTimeStart")) {
                    conditions.put("FiledTimeStart", condition.getValue());
                } else if (condition.getKey().equals("FiledTimeEnd")) {
                    conditions.put("FiledTimeEnd", condition.getValue());
                } else if (condition.getKey().equals("State")) {
                    conditions.put("State", condition.getValue());
                } else if (condition.getKey().equals("organization")) {
                    conditions.put("organization", condition.getValue());
                } else if (condition.getKey().equals("MaterialTmeStart")) {
                    conditions.put("MaterialTmeStart", condition.getValue());
                } else if (condition.getKey().equals("MaterialTmeEnd")) {
                    conditions.put("MaterialTmeEnd", condition.getValue());
                } else if (condition.getKey().equals("TreeValue")) {
                    ids =new ArrayList<>();
                    LawstandardType lawSelf = new LawstandardType();
                    lawSelf.setId(condition.getValue());
                    ids.add(lawSelf);
                    getLawsTree(condition.getValue());
                    conditions.put("TreeValue",ids );
                }else if (condition.getKey().equals("KeyWords")){
                    conditions.put("KeyWords", condition.getValue());
                }else if(condition.getKey().equals("ApproveStatus")){
                    conditions.put("ApproveStatus", condition.getValue());
                }

            }
        }


        // 1,根据条件一共查询到的数据条数
        int count = lawstandardDao.getLawstandardListCount(conditions);

        if(page.getPageNo()==1){
            conditions.put("startRow", 0 );
        }else{
            conditions.put("startRow", page.getPageSize() * (page.getPageNo() + 1) );
        }
        conditions.put("endRow", page.getPageSize() * page.getPageNo());

        // 2, 查询到当前页数的数据
        List<Lawstandard> list = lawstandardDao.getLawstandardList(conditions);

        PagingEntity<Lawstandard> result = new PagingEntity<Lawstandard>();
        result.setPageCount(count);

        int pageCount = 0 == count ? 1 : (count - 1) / page.getPageSize() + 1; // 由于计算pageCount存在整除的情况，所以计算的时候先减1在除以pageSize
        result.setPageNo(page.getPageNo());
        result.setPageSize(page.getPageSize());
        result.setPageCount(pageCount);
        result.setRecordCount(count);
        result.setCurrentList(list);

        return result;
    }

    /**
     * 删除法规标准
     * @return
     */
    @Transactional
    public int  DeleteLawstandardById(String id){

        int num1= lawstandardDao.deleteLawstandardById(id);
        int num2 = lawstandardDao.deleteRefence(id);
        int num3 = lawstandardDao.deleteReplace(id);
        int num4 = lawstandardDao.DeleteLawAndType(id);

        int result = num1+num2+num3+num4;

        return result;
    }

    /**
     * 新增法规标准
     * @param lawstandard
     * @return
     */
    @Transactional
    public int SaveOrUpdateLawstandard(Lawstandard lawstandard){

        if(lawstandard.getId()==null||lawstandard.getId().equals("")){
            UUID uuid=UUID.randomUUID();
            String guid=uuid.toString();
            lawstandard.setId(guid);
        }else{
            lawstandardDao.deleteRefence(lawstandard.getId());
            lawstandardDao.deleteReplace(lawstandard.getId());
        }
        int num = lawstandardDao.SaveOrUpdateLawstandard(lawstandard);

        lawstandardDao.SaveOrUpdateLawAndType(lawstandard);
        lawstandardDao.SaveOrUpdateLawAndPublish(lawstandard);
        //建立与附件的关系
        if(lawstandard.getFileids().size()>0){
            lawstandardDao.SaveFileLink(lawstandard);
        }


        //添加引用关系
        if(lawstandard.getRefence()!=null&&lawstandard.getRefence().size()>0){

            for(Lawstandard item:lawstandard.getRefence()){
                UUID uuidRefence=UUID.randomUUID();
                String guidRefence=uuidRefence.toString();

                RefenceOrReplace refenceOrReplace = new RefenceOrReplace();
                refenceOrReplace.setId(guidRefence);
                refenceOrReplace.setSid(lawstandard.getId());
                refenceOrReplace.setRelatedid(item.getId());
                lawstandardDao.addRefence(refenceOrReplace);
            }

        }

        //添加替换关系
        if(lawstandard.getReplace()!=null&&lawstandard.getReplace().size()>0){

            for(Lawstandard item:lawstandard.getReplace()){
                UUID uuidReplace=UUID.randomUUID();
                String guidReplace=uuidReplace.toString();

                RefenceOrReplace refenceOrReplace = new RefenceOrReplace();
                refenceOrReplace.setId(guidReplace);
                refenceOrReplace.setSid(item.getId());
                refenceOrReplace.setRelatedid(lawstandard.getId());
                lawstandardDao.addReplace(refenceOrReplace);
            }

        }

        return num;
    }

    /**
     * 获取法规信息
     * @param id
     * @return
     */
    public Lawstandard getLawstandardById(String id){

        Lawstandard lawstandard = lawstandardDao.getLawstandardById(id);

        //获取引用关系
        lawstandard.setRefence(lawstandardDao.getRefenceList(id));
        //获取替代关系
        lawstandard.setReplace(lawstandardDao.getReplaceList(id));

        return lawstandard;
    }

    public  void AddLawstandardCount(Lawstandard lawstandard){

        if(lawstandard.getClickcount()==null||lawstandard.getClickcount().equals("")){
            lawstandard.setClickcount(1);
        }else{
            int num = lawstandard.getClickcount()+1;
            lawstandard.setClickcount(num);
        }
        lawstandardDao.AddLawstandardCount(lawstandard);

    }

    /**
     * 置顶
     * @param lawstandard
     */
    public  void LawstandardIsTop(Lawstandard lawstandard){
        lawstandardDao.LawstandardIsTop(lawstandard);
    }

}