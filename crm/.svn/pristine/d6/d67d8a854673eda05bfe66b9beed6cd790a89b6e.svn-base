package com.wkcto.crm.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 响应json到浏览器
 * @author Administrator
 *
 */
public class OutJson {
	
	private OutJson(){
		
	}
	
	/**
	 * 响应json，响应：{"success" : true} 或者 {"success" : false}
	 * @param response
	 * @param success
	 */
	public static void print(HttpServletResponse response , boolean success){
		try {
			Map<String, Object> jsonMap = new HashMap<>();
			jsonMap.put("success", success);
			ObjectMapper om = new ObjectMapper();
			String json = om.writeValueAsString(jsonMap);
			response.setContentType("text/json;charset=UTF-8");
			response.getWriter().print(json);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 响应json，将Object转换成json格式的字符串，输出到浏览器。
	 * @param response
	 * @param obj
	 */
	public static void print(HttpServletResponse response , Object obj){
		try {
			ObjectMapper om = new ObjectMapper();
			String json = om.writeValueAsString(obj);
			response.setContentType("text/json;charset=UTF-8");
			response.getWriter().print(json);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
