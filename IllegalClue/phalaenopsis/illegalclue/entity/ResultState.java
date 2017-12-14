package phalaenopsis.illegalclue.entity;

/**
 * 返回值状态枚举
 * @author chunl
 *
 */
public enum ResultState {
	
	Invalid(0),Success(200),Failed(400),InvalidArgument(417),PreconditionFailed(412),Overflow(423);
	
	private int value;
	
	private ResultState(int value)
	{
		this.value = value;
	}
	
	public int getValue()
	{
		return this.value;
	}
}
