package phalaenopsis.lawcaseevaluation.entity;

public enum ScoreGrade {
	
	UnKnow(0),Good(1),Grade(2),UnGrade(3);
	
	private int value;
	
	private ScoreGrade(int i)
	{
		this.value = i;
	}

	public static ScoreGrade getGrade(float score)
	{
		if (score>=90)
		{
			return Good;
		}
		else if (score >= 70)
		{
			return Grade;
		}
		else {
			return UnGrade;
		}
	}
}
