package com.wkcto.crm.workbench.service.impl;

import java.util.List;

import com.wkcto.crm.utils.SqlSessionUtil;
import com.wkcto.crm.workbench.dao.ContactsDao;
import com.wkcto.crm.workbench.domain.Contacts;
import com.wkcto.crm.workbench.service.ContactsService;

public class ContactsServiceImpl implements ContactsService {
	
	private ContactsDao contactsDao = SqlSessionUtil.getCurrentSqlSession().getMapper(ContactsDao.class);

	@Override
	public List<Contacts> getContactsByName(String cname) {
		
		List<Contacts> cList = contactsDao.getContactsByName(cname);
		
		return cList;
	}
	
	
	
}
