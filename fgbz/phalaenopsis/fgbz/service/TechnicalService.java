package phalaenopsis.fgbz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import phalaenopsis.common.entity.Condition;
import phalaenopsis.common.entity.Page;
import phalaenopsis.common.entity.PagingEntity;
import phalaenopsis.fgbz.dao.TechnicalDao;
import phalaenopsis.fgbz.entity.*;

import java.util.*;

@Service("technicalService")
public class TechnicalService {

    @Autowired
    private TechnicalDao technicalDao;


    private  List<TechnicalType> ids = new ArrayList<>();

    /**
     * 查询所有的技术文件
     *
     * @return
     */
    public  List<TechnicalType> SelectTechnicalType() {
        return technicalDao.SelectTechnicalType();
    }

    /**
     * 新增或修改技术文件
     *
     * @param technicalType
     * @return
     */
    public int AddOrUpdateTechnicalType(TechnicalType technicalType) {
        return technicalDao.AddOrUpdateTechnicalType(technicalType);
    }

    /**
     * 删除技术文件类别
     * @param technicalType
     * @return
     */
    public int  DeleteTechnicalType(TechnicalType technicalType){
        return  technicalDao.DeleteTechnicalType(technicalType);
    }

    /**
     * 获取子节点
     * @param id
     * @return
     */
    public List<TechnicalType> getChildNode(String id){
        return technicalDao.getChildNode(id);
    }

    public  List<TechnicalType> getLawsTree(String id){

        List<TechnicalType> list = getChildNode(id);

        while (list!=null&&list.size()>0){
            ids.addAll(list);
            for(TechnicalType technicalType:list){
                list=getLawsTree(technicalType.getId());
            }
        }
        return list;
    }

    /**
     * 获取技术标准列表
     * @param page
     * @return
     */
    public PagingEntity<Technical> getTechnicalList(@RequestBody Page page){

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
                    TechnicalType tecSelf = new TechnicalType();
                    tecSelf.setId(condition.getValue());
                    ids.add(tecSelf);
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
        int count = technicalDao.getTechnicalListCount(conditions);

        if(page.getPageNo()==1){
            conditions.put("startRow", 0 );
        }else{
            conditions.put("startRow", page.getPageSize() * (page.getPageNo() + 1) );
        }
        conditions.put("endRow", page.getPageSize() * page.getPageNo());

        // 2, 查询到当前页数的数据
        List<Technical> list = technicalDao.getTechnicalList(conditions);

        PagingEntity<Technical> result = new PagingEntity<Technical>();
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
     * 新增法规标准
     * @param technicalType
     * @return
     */
    @Transactional
    public int SaveOrUpdateTechnical(TechnicalType technicalType){

        if(technicalType.getId()==null||technicalType.getId().equals("")){
            UUID uuid=UUID.randomUUID();
            String guid=uuid.toString();
            technicalType.setId(guid);
        }

        int num = technicalDao.SaveOrUpdateTechnical(technicalType);
        technicalDao.SaveOrUpdateTecAndType(technicalType);


        return num;
    }

    /**
     * 通过id删除技术文件
     * @param id
     * @return
     */
    @Transactional
    public int  DeleteTechnicalById( String id){
        int num1 = technicalDao.DeleteTechnicalById(id);
        int num2 = technicalDao.DeleteTecAndType(id);
        return num1+num2;
    }

    /**
     * 查看
     * @param id
     * @return
     */
    public Technical getTechnicalById(String id){
        return technicalDao.getTechnicalById(id);
    }


}
