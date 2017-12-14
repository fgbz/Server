package phalaenopsis.satellitegraph.entity;

import phalaenopsis.common.entity.Paging;

public class  SatelliteListResult<T>{
	
	@SuppressWarnings("rawtypes")
	public Paging getQueryListResult() {
		return QueryListResult;
	}

	@SuppressWarnings("rawtypes")
	public void setQueryListResult(Paging queryListResult) {
		QueryListResult = queryListResult;
	}

	public int getBacklogCount() {
		return backlogCount;
	}

	public void setBacklogCount(int backlogCount) {
		this.backlogCount = backlogCount;
	}

	@SuppressWarnings("rawtypes")
	public Paging  QueryListResult;
	
	public Integer  backlogCount;
	
	

	public SatelliteListResult() {
		super();
	}

	public SatelliteListResult(@SuppressWarnings("rawtypes") Paging queryListResult, int backlogCount) {
		super();
		QueryListResult = queryListResult;
		this.backlogCount = backlogCount;
	}

}
