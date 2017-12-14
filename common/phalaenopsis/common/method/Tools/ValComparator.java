package phalaenopsis.common.method.Tools;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import phalaenopsis.common.entity.analysis.AnalysisRowItem;

public class ValComparator implements Comparator<AnalysisRowItem>{

	

	
	
	public static void main(String[]args){
		AnalysisRowItem a=new AnalysisRowItem();
		a.setArea(100.2);
		AnalysisRowItem b=new AnalysisRowItem();
		b.setArea(101.2);
		AnalysisRowItem c=new AnalysisRowItem();
		c.setArea(19.2);
		List<AnalysisRowItem> list=new ArrayList<AnalysisRowItem>();
		list.add(a);
		list.add(b);
		list.add(c);
		for(AnalysisRowItem i:list){
			i.Area=0.0;
			
			//System.out.println("排序前："+i.getArea());
		}
		for(AnalysisRowItem i:list){
			System.out.println("排序后："+i.getArea());
		}
		/*Collections.sort(list,new ValComparator());
		Collections.reverse(list);
		//ListSortUtil.sort(list, "Area", "desc");
		for(AnalysisRowItem i:list){
			System.out.println("排序后："+i.getArea());
		}*/
	}

	@Override
	public int compare(AnalysisRowItem o1, AnalysisRowItem o2) {
		return (int) (o1.getArea()-o2.getArea());
	}

}



