package phalaenopsis.common.entity;

import java.util.List;

public class Paging<T> {
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

	public Paging() {
		super();
	}

	public int RecordCount;

	/// <summary>
	/// 当前页的数据
	/// </summary>

	public List<T> CurrentList;

	public void calculatePageCount(int pagesize){
		this.PageCount = (this.RecordCount / pagesize) + (this.RecordCount % pagesize > 0 ? 1 : 0);
	}

	public Paging(int pageNo, int pageCount, int pageSize, List<T> currentList) {
		super();
		PageNo = pageNo;
		PageCount = pageCount;
		PageSize = pageSize;
		CurrentList = currentList;
	}
	
	public Paging(int pageNo, int pageCount, int pageSize, int recordCount, List<T> currentList) {
		super();
		PageNo = pageNo;
		PageCount = pageCount;
		PageSize = pageSize;
		RecordCount = recordCount;
		CurrentList = currentList;
	}
}
