package com.yqsh.ifmp.util;

import net.sf.json.JSONObject;

/**
 * JSON转换工具类
 * @author Administrator
 *
 */
public class JSONUtils {
 
	private JSONUtils(){}
	
	/**
	 * JSON字符串转换成对象
	 * @param jsonStr
	 * @param clazz
	 * @return 
	 * @return
	 * @throws IfmpException
	 */
	public static Object jsonStrToObj(String jsonStr,Class<?> clazz) throws IfmpException{
		try {
			JSONObject jsonObject = JSONObject.fromObject(jsonStr);
			return JSONObject.toBean(jsonObject, clazz);
		} catch (Exception e) {
			throw new IfmpException("JSON字符串格式错误");
		}
	}
	
	/**
	 * 对象转换成JSON字符串
	 * @param obj
	 * @return
	 */
	public static String objToJsonStr(Object obj){
		JSONObject jsonObject = JSONObject.fromObject(obj);
		return jsonObject.toString();
	}
	
}
