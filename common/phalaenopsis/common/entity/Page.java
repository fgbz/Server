package phalaenopsis.common.entity;

import org.apache.commons.collections.map.HashedMap;

import java.util.List;
import java.util.Map;

public class Page {

    // TODO 新加tab number数
    private int tabnumber;

    public int getTabnumber() {
        return tabnumber;
    }

    public void setTabnumber(int tabnumber) {
        this.tabnumber = tabnumber;
    }

    private int pageNo;

    private int pageSize;

    private List<Condition> conditions;

    private int year;

    private String sortProperty;

    private String direction;

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<Condition> getConditions() {
        return conditions;
    }

    public void setConditions(List<Condition> conditions) {
        this.conditions = conditions;
    }

    public String getSortProperty() {
        return sortProperty;
    }

    public void setSortProperty(String sortProperty) {
        this.sortProperty = sortProperty;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public Page() {
        super();
    }

    public Page(int pageNo, int pageSize, int year, List<Condition> conditions) {
        super();
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.conditions = conditions;
        this.year = year;
    }

    public int getStartNum() {
        return this.pageSize * (this.pageNo - 1) + 1;
    }

    public int getEndNum() {
        return this.pageSize * this.pageNo;
    }

    public Map<String, Object> getQueryCondition() {
        Map<String, Object> map = new HashedMap();
        map.put("startNum", getStartNum());
        map.put("endNum", getEndNum());
        for (final Condition cond : conditions) {
            map.put(cond.getKey(), cond.getValue());
        }
        return map;
    }
}
