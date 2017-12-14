package phalaenopsis.common.method.http;

import com.google.gson.Gson;
import okhttp3.*;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 项目名称：Phalaenopsis
 * 创建日期：2017/10/27
 * 修改历史：
 * 1. [2017/10/27]创建文件
 *
 * @author chunl
 */
public class HttpCommon {

    /**
     * 设置连接超时时间为30秒
     */
    private final static int CONNECT_TIMT_OUT = 30;

    /**
     * 设置写超时时间为30秒
     */
    private final static int WRITE_TIME_OUT = 30;


    static {
        final OkHttpClient.Builder httpBuilder = new OkHttpClient.Builder();
        okHttpClient = httpBuilder.connectTimeout(CONNECT_TIMT_OUT, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIME_OUT, TimeUnit.SECONDS).build();

    }

    /**
     * get请求
     *
     * @param url url地址
     * @param map 请求参数
     * @return 返回结果。如果为“”表示失败
     */
    public static String get(String url, Map<Object, Object> map) {

        url = wrapUrl(url, map);

        // 创建请求参数
        Request request = new Request.Builder().url(url).build();

        //创建请求对象
        Call call = okHttpClient.newCall(request);

        try {
            Response response = call.execute();
            if (response.isSuccessful()) {
                return response.body().string();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }

    /**
     * post请求
     *
     * @param url post请求的url
     * @param t   post请求的表单实体
     * @return 返回结果。如果为“”表示失败
     */
    private <T> String post(String url, T t) {
        String json = new Gson().toJson(t);
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder().url(url).post(body).build();

        Response response = null;
        try {
            response = okHttpClient.newCall(request).execute();
            if (response.isSuccessful()) {
                return response.body().string();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }


    /**
     * 上传文件请求
     *
     * @param url      请求url
     * @param map      请求参数
     * @param filePath 文件路径
     * @return 返回结果。结果为""表示失败
     */
    private String uploadFile(String url, Map<Object, Object> map, String filePath) {
        url = wrapUrl(url, map);

        File file = new File(filePath);
        RequestBody fileBody = RequestBody.create(OCTET, file);
        RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("image", file.getName(), fileBody).build();
        Request request = new Request.Builder().url(url).post(requestBody).build();


        try {
            Response response = okHttpClient.newCall(request).execute();
            if (response.isSuccessful()) {
                return response.body().string();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }

    /**
     * 上传多个文件请求
     *
     * @param url      请求url
     * @param map      请求参数
     * @param filePaths 文件路径
     * @return 返回结果。结果为""表示失败
     */
    private String uploadFiles(String url, Map<Object, Object> map, List<String> filePaths) {
        url = wrapUrl(url, map);

        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.setType(MultipartBody.FORM);
        for(String str: filePaths){
            File file = new File(str);
            RequestBody fileBody = RequestBody.create(OCTET, file);
            builder.addFormDataPart("image", file.getName(), fileBody);
        }
        RequestBody requestBody = builder.build();
        Request request = new Request.Builder().url(url).post(requestBody).build();


        try {
            Response response = okHttpClient.newCall(request).execute();
            if (response.isSuccessful()) {
                return response.body().string();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }



    /**
     * 拼接get请求url
     *
     * @param url 请求url
     * @param map 参数
     * @return 返回拼接完的url地址
     */
    private static String wrapUrl(String url, Map<Object, Object> map) {
        url += "?";
        for (Map.Entry entry : map.entrySet()) {
            url += entry.getKey() + "=" + entry.getValue() + "&";
        }

        if (url.endsWith("&")) {
            url = url.substring(0, url.length() - 1);
        }
        return url;
    }


    /**
     * 请求客户端
     */
    private static OkHttpClient okHttpClient;


    /**
     * Json媒体类型
     */
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    /**
     * 二进制流的媒体类型
     */
    private static final MediaType OCTET = MediaType.parse("application/octet-stream");

}
