package com.wkcto.crm.settings.dao;

import java.util.List;

import com.wkcto.crm.settings.domain.User;

public interface UserDao {

	/**
	 * 
	 * @param user
	 * @return
	 */
	int save(User user);

	/**
	 * 根据账号和密码获取用户信息。
	 * @param loginAct
	 * @param loginPwd
	 * @return
	 */
	User getByLoginActAndLoginPwd(String loginAct, String loginPwd);

	/**
	 * 
	 * @return
	 */
	List<User> getAll();

}
