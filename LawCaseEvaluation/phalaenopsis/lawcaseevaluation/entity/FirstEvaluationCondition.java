package phalaenopsis.lawcaseevaluation.entity;

import java.util.List;

public class FirstEvaluationCondition {

    private int year;
    
    private List<KeyValueEntity> regions;

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public List<KeyValueEntity> getRegions() {
        return regions;
    }

    public void setRegions(List<KeyValueEntity> regions) {
        this.regions = regions;
    }
}
