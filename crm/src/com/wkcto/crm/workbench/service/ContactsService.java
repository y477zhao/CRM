package com.wkcto.crm.workbench.service;

import java.util.List;

import com.wkcto.crm.workbench.domain.Contacts;

public interface ContactsService {

	List<Contacts> getContactsByName(String cname);

}
