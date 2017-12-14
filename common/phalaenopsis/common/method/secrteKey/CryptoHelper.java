package phalaenopsis.common.method.secrteKey;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 *AES加解密类 
 * @author chunl
 * 2017年7月27日上午9:11:38
 */
public class CryptoHelper {

	/**
	 * 密钥算法
	 */
	private static final String KEY_ALGORITHM = "AES";

	private static final String DEFAULT_CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";

	public static byte[] newKey(int len) {

		// 返回生成指定算法的秘密密钥的 KeyGenerator 对象
		KeyGenerator kg = null;
		try {
			kg = KeyGenerator.getInstance(KEY_ALGORITHM);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return new byte[0];
		}

		// 初始化此密钥生成器，使其具有确定的密钥大小
		// AES 要求密钥长度为 128
		kg.init(len);
		// 生成一个密钥
		SecretKey secretKey = kg.generateKey();
		return secretKey.getEncoded();
	}

	/**
	 * 转换密钥
	 * 
	 * @param key
	 *            二进制密钥
	 * @return 密钥
	 */
	private static Key toKey(byte[] key) {
		// 生成密钥
		return new SecretKeySpec(key, KEY_ALGORITHM);
	}

	public static String toRijndael(String key, byte[] input) {
		try {

			byte[] keyBytes = key.getBytes("UTF-8");
			if (keyBytes.length != 32)
				throw new IllegalArgumentException();

			SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
			IvParameterSpec iv = new IvParameterSpec(keyBytes);
			Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
			// 使用密钥初始化，设置为加密模式
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

			// 执行操作
			byte[] bytes = cipher.doFinal(input);
			bytes =	org.apache.commons.codec.binary.Base64.decodeBase64(bytes);
			return new String(bytes);

		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			e.printStackTrace();
		}
		return null;
	}


	public static byte[] fromRijndael(String key, String base64) {
		try {
			byte[] keyBytes = key.getBytes("UTF-8");
			if (keyBytes.length != 32)
				throw new IllegalArgumentException();

			byte[] input = org.apache.commons.codec.binary.Base64.decodeBase64(base64.getBytes());

			// 还原密钥
			Key k = toKey(keyBytes);

			// 实例化
			Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);

			// 使用密钥初始化，设置为解密模式
			cipher.init(Cipher.DECRYPT_MODE, k);
			// 执行操作
			return cipher.doFinal(input);

		} catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String to3DES(byte[] key, byte[] iv, byte[] input) {
		try {
			if (key.length != 24)
				throw new IllegalArgumentException();

			SecretKeySpec skeySpec = new SecretKeySpec(key, "AES");
			IvParameterSpec ivp = new IvParameterSpec(iv);

			Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);

			// 使用密钥初始化，设置为加密模式
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec, ivp);

			// 执行操作
			byte[] bytes = cipher.doFinal(input);
			bytes = org.apache.commons.codec.binary.Base64.encodeBase64(bytes);
			return new String(bytes);
		} catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static byte[] from3DES(byte[] key, byte[] iv, String base64) {
		try {

			if (key.length != 24)
				throw new IllegalArgumentException();

			byte[] input = org.apache.commons.codec.binary.Base64.decodeBase64(base64.getBytes());

			// 实例化
			Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);

			SecretKeySpec skeySpec = new SecretKeySpec(key, "AES");
			IvParameterSpec ivp = new IvParameterSpec(iv);

			// 使用密钥初始化，设置为解密模式
			cipher.init(Cipher.DECRYPT_MODE, skeySpec, ivp);
			// 执行操作
			return cipher.doFinal(input);

		} catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			e.printStackTrace();
		}
		return null;
	}
}
