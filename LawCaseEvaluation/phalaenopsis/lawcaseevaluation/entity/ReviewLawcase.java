/**
 * Description 案件复核实体类
 * Author lih
 * Date 2017-04-06
 */
package phalaenopsis.lawcaseevaluation.entity;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import phalaenopsis.lawcaseevaluation.entity.caseDetail.LawCaseUserMap;

public class ReviewLawcase extends LawcaseAccount {

	/**
	 * 关联人员
	 */
	private List<LawCaseUserMap> userMaps;

	/**
	 * 最终得分
	 */
	private float finalScore;
	
	/**
	 * 是否可以复核
	 */
	private boolean canReview = false;

	private HashMap<Long, Float> reviewCaseMap = new HashMap<Long, Float>();

	public boolean isCanReview() {
		return canReview;
	}

	public void setCanReview(boolean canReview) {
		this.canReview = canReview;
	}

	public float getFinalScore() {
		return finalScore;
	}

	public void setFinalScore(float finalScore) {
		this.finalScore = finalScore;
	}

	public HashMap<Long, Float> getReviewCaseMap() {
		return reviewCaseMap;
	}

	public void setReviewCaseMap(HashMap<Long, Float> reviewCaseMap) {
		this.reviewCaseMap = reviewCaseMap;
	}

	public List<LawCaseUserMap> getUserMaps() {
		if (null != userMaps && userMaps.size() > 0) {
			float scores = 0;
			//获取该案件初评人员评分情况集合
			for (LawCaseUserMap lawCaseUserMap : userMaps) {
				//判断是否提交
				if (lawCaseUserMap.isFirstSubmitted()) {
					if (!reviewCaseMap.containsKey(lawCaseUserMap.getId())) {
						reviewCaseMap.put(lawCaseUserMap.getId(), lawCaseUserMap.isReviewSubmitted()
								? lawCaseUserMap.getReviewTotalScore() : lawCaseUserMap.getFirstTotalScore());
					}
				}
			}
			//遍历map
			for (Map.Entry<Long, Float> entry : reviewCaseMap.entrySet()) {
				scores += entry.getValue();
			}
			//有3个初评人员对该案件评查并提交，该案件才可复核
			if (reviewCaseMap.size() < 3) {
				canReview = false;
			} else {
				canReview = true;	 
				//保留一位小数
				DecimalFormat df = new DecimalFormat("#.#");
				String  str = df.format(scores / reviewCaseMap.size()).toString();
				if(reviewState == ReviewState.unMatched){
					finalScore =0;
				}else{
					finalScore =Float.parseFloat(str);
				}
			
			}
		}

		return userMaps;
	}

	public void setUserMaps(List<LawCaseUserMap> userMaps) {
		this.userMaps = userMaps;
	}
}
