package phalaenopsis.fgbz.common;

//import com.sun.org.glassfish.external.statistics.Statistic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by 13260 on 2018/3/25.
 */
public class LiceneEncrypt {

    public final static String ENCODING = "UTF-8";
    /**
     * 加密
     * @return
     */
    public static  byte[] getAESEncoded(String jsonArray){

        byte[] result=null;

        try {

            String hexKey = AlgorithmUtil.getAESKey();

            System.out.print(hexKey);

            byte[] encoded = AlgorithmUtil.getAESEncode(hexKey, jsonArray);

            result = encoded;

        } catch (Exception e) {

            e.printStackTrace();
        }

        return result;
    }


    public static String getAESdecoded( byte[] bytes) throws Exception {

        String hexKey = AlgorithmUtil.getAESKey();
        System.out.print(hexKey);
        byte[] decoded = AlgorithmUtil.getAESDecode(hexKey,bytes);

       return new String(AlgorithmUtil.parseHexStr2Byte(AlgorithmUtil.parseByte2HexStr(decoded)), ENCODING);
    }


    public static String streamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        //new一个StringBuffer用于字符串拼接
        StringBuffer sb = new StringBuffer();
        String line = null;
        try {
            //当输入流内容读取完毕时
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            //记得关闭流数据 节约内存消耗
            is.close();
            reader.close();
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
