package com.wkcto.crm.web.listener;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.wkcto.crm.settings.domain.DicValue;
import com.wkcto.crm.settings.service.DicValueService;
import com.wkcto.crm.settings.service.impl.DicValueServiceImpl;
import com.wkcto.crm.utils.TransactionHandler;

public class SystemInitListener implements ServletContextListener {
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("-----------------------------------------system init begin--------------------------------------------");
		ServletContext application = sce.getServletContext();
		// 调用service，获取所有的字典值
		DicValueService dvs = (DicValueService)new TransactionHandler(new DicValueServiceImpl()).getProxy();
		Map<String,List<DicValue>> dvMap = dvs.getAll();
		// 遍历Map集合
		Set<String> keys = dvMap.keySet();
		for(String key : keys){
			application.setAttribute(key, dvMap.get(key));
		}
		System.out.println("-----------------------------------------system init complete--------------------------------------------");
	
		//ResourceBundle专业读取properties文件的，比Properties类好用
		ResourceBundle rb = ResourceBundle.getBundle("Stage2Possibility");
		
		Enumeration<String> keyss = rb.getKeys();
		
		Map<String,String> pMap = new HashMap<String,String>();
		
		while(keyss.hasMoreElements()){
			
			String key = keyss.nextElement();	//07成交
				
			String value = rb.getString(key);	//100
			
			pMap.put(key, value);
			
		}
		
		application.setAttribute("pMap",pMap);
		
	}
}














