package phalaenopsis.lawcase.utility;

/** 
 二进制json序列化器
 
*/
public final class BinaryJsonSerializer
{
	/** 
	 从json数据的二进制表示形式反序列化为对象
	 <typeparam name="T"></typeparam>
	 @param bytes
	 @return 
	*/
	public static <T> T Deserialize(byte[] bytes)
	{
		return null;
//		if (bytes == null)
//		{
//			if (T.class.IsClass)
//			{
//				return null;
//			}
//			else
//			{
//				throw new ArgumentNullException();
//			}
//		}
//		DataContractJsonSerializer json = new DataContractJsonSerializer(T.class);
////C# TO JAVA CONVERTER NOTE: The following 'using' block is replaced by its Java equivalent:
////		using (MemoryStream mem = new MemoryStream(bytes))
//		MemoryStream mem = new MemoryStream(bytes);
//		try
//		{
//			return (T)json.ReadObject(mem);
//		}
//		finally
//		{
//			mem.dispose();
//		}
	}

	/** 
	 将对象序列化为json数据的二进制表示形式
	 
	 @param obj
	 @return 
	*/
	public static byte[] Serialize(Object obj)
	{
		return null;
//		if (obj == null)
//		{
//			return null;
//		}
//		DataContractJsonSerializer json = new DataContractJsonSerializer(obj.getClass());
////C# TO JAVA CONVERTER NOTE: The following 'using' block is replaced by its Java equivalent:
////		using (MemoryStream mem = new MemoryStream())
//		MemoryStream mem = new MemoryStream();
//		try
//		{
//			json.WriteObject(mem, obj);
//			return mem.toArray();
//		}
//		finally
//		{
//			mem.dispose();
//		}
	}
}