package phalaenopsis.common.method.Tools;

import java.util.UUID;

public class GuidHelper {

	/**
	 * 获取guid
	 * @return
	 */
	public static String getGuid(){
		UUID uuid=UUID.randomUUID();
		String guid=uuid.toString();
		return	 guid.replaceAll("-", "");
	}
	
}
