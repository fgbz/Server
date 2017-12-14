package phalaenopsis.lawcaseevaluation.entity;

import javax.persistence.Id;

public enum ReviewState {

	UnKnow,NeedReview,UnNeedReview,Reviewed,unMatched;
	
	public static ReviewState getReviewState(String string)
	{
		if (string.equals("NeedReview"))
		{
			return NeedReview;
		}
		else if (string.equals("UnNeedReview"))
		{
			return UnNeedReview;
		}
		else if (string.equals("Reviewed"))
		{
			return Reviewed;
		}
		else if (string.equals("unMatched")) 
		{
			return unMatched;
		}
		else {
			return UnKnow;
		}
	}

}
