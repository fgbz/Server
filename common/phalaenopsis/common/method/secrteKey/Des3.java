package phalaenopsis.common.method.secrteKey;

import javax.crypto.*;
import javax.crypto.spec.DESedeKeySpec;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * 项目名称：Phalaenopsis
 * 创建日期：2017/11/22
 * 修改历史：
 * 1. [2017/11/22]创建文件
 *
 * @author chunl
 */
public class Des3 {

    /**
     * ECB加密,不要IV
     *
     * @param key  密钥
     * @param data 明文
     * @return 返回Base64编码的密文
     * @throws InvalidKeyException 无效的key
     */
    public static byte[] des3EncodeECB(byte[] key, byte[] data) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, BadPaddingException, IllegalBlockSizeException {
        Key desKey = null;
        DESedeKeySpec spec = new DESedeKeySpec(key);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("desede");
        desKey = keyFactory.generateSecret(spec);
        Cipher cipher = Cipher.getInstance("desede" + "/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, desKey);
        byte[] bOut = cipher.doFinal(data);
        return bOut;
    }


}
