package phalaenopsis.visitSystem.entity;

import phalaenopsis.illegalclue.entity.ResultState;

/**
 * 信访返回实体
 */
public class XfResult {

    /**
     *枚举类。表示成果或失败
     */
    private ResultState resultState;

    /**
     * 返回值。
     */
    private Long dealId;

    public ResultState getResultState() {
        return resultState;
    }

    public void setResultState(ResultState resultState) {
        this.resultState = resultState;
    }

    public Long getDealId() {
        return dealId;
    }

    public void setDealId(Long dealId) {
        this.dealId = dealId;
    }

    public XfResult() {
    }

    public XfResult(ResultState resultState, Long dealId) {
        this.resultState = resultState;
        this.dealId = dealId;
    }
}
