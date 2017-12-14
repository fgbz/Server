package phalaenopsis.satellitegraph.utils.ArcGISHelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import javax.ws.rs.ClientErrorException;

import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

public class HttpHelper {
	/**
	 * 修改返回值为String json
	 * 
	 * @param url
	 * @param params
	 * @return
	 */
	public static HttpEntity HttpPost(String url, List<NameValuePair> params) {
		HttpClient client = new DefaultHttpClient();
		HttpPost request = new HttpPost(url);
		try {
			HttpEntity entity = new UrlEncodedFormEntity(params, HTTP.UTF_8);
			request.setEntity(entity); 
			HttpResponse response = client.execute(request);
			// 如果状态码为200,就是正常返回
			if (response.getStatusLine().getStatusCode() == 200) {
//				 String result = EntityUtils.toString(response.getEntity());
				return response.getEntity();
			}
		} catch (ClientErrorException e) {
			e.printStackTrace();
		} catch (IOException e) {
		}
		return null;
	}
	
	/**
     * 向指定 URL 发送POST方法的请求
     * 
     * @param url
     *            发送请求的 URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
	public static String sendPost(String url, String param) {
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*"); 
			conn.setRequestProperty("Charset", "utf-8");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 获取URLConnection对象对应的输出流
			out = new PrintWriter(conn.getOutputStream());
			// 发送请求参数
			out.print(param);
			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("发送 POST 请求出现异常！" + e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输出流、输入流
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}

	public static HttpEntity HttpGet(String url) {
		HttpClient client = new DefaultHttpClient();
		HttpGet request = new HttpGet(url);
		try {
			HttpResponse response = client.execute(request);
			// 如果状态码为200,就是正常返回
			if (response.getStatusLine().getStatusCode() == 200) {

				// String result = EntityUtils.toString(response.getEntity());
				return response.getEntity();
			}
		} catch (ClientErrorException e) {
			e.printStackTrace();
			// 进行处理操作
		} catch (IOException e) {
			// 进行处理操作
		}
		return null;
	}
}
