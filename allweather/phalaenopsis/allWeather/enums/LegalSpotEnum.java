package phalaenopsis.allWeather.enums;

import phalaenopsis.common.method.Enum.IntEnum;
import phalaenopsis.common.method.Enum.StatusEnum;

/**
 *  合法图斑类型
 * @author chunl
 *
 */
public enum LegalSpotEnum implements IntEnum<LegalSpotEnum>{
	
	/**
	 * 有农用地转用批准手续的图斑
	 */
	FarmlandTransference(1),
	/**
	 * 有“海域使用证”的图斑
	 */
	AreaLicence(2),
	
	/**
	 * 有未利用地使用手续的图斑
	 */
	UnusedLand(3);
	
	private LegalSpotEnum(int v){
		this.value = v;
	}
	
	private int value;
	
	@Override
	public int getIntValue(){
		return this.value;
	}

}
