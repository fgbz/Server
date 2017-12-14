package phalaenopsis.common.entity;

import java.util.List;

public class PagingEntity<T> {

	/// <summary>
	/// 当前页码
	/// </summary>
	public int PageNo;

	/// <summary>
	/// 总页数
	/// </summary>
	public int PageCount;

	/// <summary>
	/// 每页记录数
	/// </summary>
	public int PageSize;

	/// <summary>
	/// 总记录数
	/// </summary>
	public int RecordCount;

	/// <summary>
	/// 当前页的数据
	/// </summary>
	public List<T> CurrentList;

	public int getPageNo() {
		return PageNo;
	}

	public void setPageNo(int pageNo) {
		PageNo = pageNo;
	}

	public int getPageCount() {
		return PageCount;
	}

	public void setPageCount(int pageCount) {
		PageCount = pageCount;
	}

	public int getPageSize() {
		return PageSize;
	}

	public void setPageSize(int pageSize) {
		PageSize = pageSize;
	}

	public int getRecordCount() {
		return RecordCount;
	}

	public void setRecordCount(int recordCount) {
		RecordCount = recordCount;
	}

	public List<T> getCurrentList() {
		return CurrentList;
	}

	public void setCurrentList(List<T> currentList) {
		CurrentList = currentList;
	}
	
	public PagingEntity() {
		super();
	}

	public PagingEntity(int pageNo, int pageCount, int pageSize, int recordCount, List<T> currentList) {
		super();
		PageNo = pageNo;
		PageCount = pageCount;
		PageSize = pageSize;
		RecordCount = recordCount;
		CurrentList = currentList;
	}

}
