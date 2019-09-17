package com.wkcto.crm.settings.service;

import java.util.List;

import com.wkcto.crm.exceptions.LoginException;
import com.wkcto.crm.settings.domain.User;

public interface UserService {

	/**
	 * 保存用户
	 * @param user
	 * @return
	 */
	boolean save(User user);

	/**
	 * 用户登录
	 * @param loginAct
	 * @param loginPwd
	 * @param clientIp
	 * @return
	 */
	User login(String loginAct, String loginPwd, String clientIp) throws LoginException;

	/**
	 * 获取所有的用户
	 * @return
	 */
	List<User> getAll();

}
