package phalaenopsis.satellitegraph.utils;

import java.util.List;

import phalaenopsis.satellitegraph.entity.IMapSpot;

public interface IMapSpotOperationExecutor {
	
	/**
	 * 添加 &amp; 删除图斑
	 * @param addSpots  要添加的图斑。执行成功后，需要更新spots中的图斑ID
	 * @param deleteIDList  要删除的图斑的ID列表
	 * @return  如果整体操作成功，则返回true
	 */
	boolean onAddAndDelete(List<IMapSpot> addSpots, List<Long> deleteIDList);

}
