package com.wkcto.crm.workbench.dao;

import java.util.List;

import com.wkcto.crm.workbench.domain.Contacts;

public interface ContactsDao {

	/**
	 * 保存联系人
	 * @param contacts
	 */
	void save(Contacts contacts);

	List<Contacts> getContactsByName(String cname);

}
