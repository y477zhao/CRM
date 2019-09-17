package com.wkcto.crm.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	
	private DateUtil(){
		
	}
	
	/**
	 * 获取系统当前时间
	 * @return
	 */
	public static String getSysTime(){
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
	}
	
}
