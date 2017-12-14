package phalaenopsis.common.method;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class BinarySerializer {

	/**
	 * 深度克隆
	 * 
	 * @param t
	 * @return
	 */
	public static <T> T Clone(T t) {
		byte[] bytes = convertToBytes(t);
		return convertBytes(bytes);
	}

	public static <T> byte[] convertToBytes(T t) {
		try {
			ByteArrayOutputStream boStream = new ByteArrayOutputStream();
			ObjectOutputStream oStream = new ObjectOutputStream(boStream);
			oStream.writeObject(t);
			oStream.flush();
			oStream.close();
			boStream.close();
			return boStream.toByteArray();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public  static <T> T convertBytes(byte[] bytes) {
		if (null != bytes) {
			try {
				ByteArrayInputStream bInputStream = new ByteArrayInputStream(bytes);
				return (T) new ObjectInputStream(bInputStream).readObject();
			} catch (ClassNotFoundException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
}
