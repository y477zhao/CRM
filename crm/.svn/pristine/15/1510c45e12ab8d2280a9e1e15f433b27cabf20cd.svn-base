package com.wkcto.crm.utils;

public class ServiceFactory {
	
	public static Object getService(Object service){
		
		return new TransactionHandler(service).getProxy();
		
	}
	
}
