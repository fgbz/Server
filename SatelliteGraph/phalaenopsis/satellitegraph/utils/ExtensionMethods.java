package phalaenopsis.satellitegraph.utils;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;

import phalaenopsis.workflowEngine.storage.WFHistory;

public class ExtensionMethods {
	/// <summary>
	/// 克隆一个数据契约的实体对象的深度副本
	/// </summary>
	/// <typeparam name="T"></typeparam>
	/// <param name="obj"></param>
	/// <returns></returns>
	
	public static  Object Clone(Object obj){
		List<Object> list= new ArrayList<Object>();
		return list;
		
	}
	
    

	/**
	 * 对字符串进行url编码
	 * 
	 * @param longString
	 * @return
	 */
	public static String EscapeLongString(String longString) {
		final int groupSize = 512;
		int groups = longString.length() / groupSize + (longString.length() % groupSize == 0 ? 0 : 1);
		StringBuilder sb = new StringBuilder(longString.length());
		int len = longString.length();
		for (int i = 0; i < groups; i++) {
			String s = longString.substring(i * groupSize, i * groupSize + Math.min(groupSize, len));
			sb.append(StringEscapeUtils.escapeHtml(s));
			len -= groupSize;
		}
		return sb.toString();
	}
	
	/**
	 * 对字符串进行url编码
	 * 
	 * @param longString
	 * @return
	 */
	public static String EscapeLongString1(String longString) {
		final int groupSize = 512;
		int groups = longString.length() / groupSize + (longString.length() % groupSize == 0 ? 0 : 1);
		StringBuilder sb = new StringBuilder(longString.length());
		int len = longString.length();
		for (int i = 0; i < groups; i++) {
			String s = longString.substring(i * groupSize, Math.min(groupSize, len));
			sb.append(URLEncoder.encode(s));
			len -= groupSize;
		}
		return sb.toString();
	}
}