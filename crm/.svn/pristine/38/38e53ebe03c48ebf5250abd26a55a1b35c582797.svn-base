package com.wkcto.crm.workbench.service.impl;

import java.util.List;

import com.wkcto.crm.utils.SqlSessionUtil;
import com.wkcto.crm.workbench.dao.CustomerDao;
import com.wkcto.crm.workbench.service.CustomerService;

public class CustomerServiceImpl implements CustomerService {
	
	private CustomerDao customerDao = SqlSessionUtil.getCurrentSqlSession().getMapper(CustomerDao.class);

	@Override
	public List<String> getCustomerNameByName(String name) {
		
		List<String> sList = customerDao.getCustomerNameByName(name);
		
		return sList;
	}
	
	
	
}
