package com.wkcto.crm.settings.service.impl;

import java.util.List;

import com.wkcto.crm.exceptions.LoginException;
import com.wkcto.crm.settings.dao.UserDao;
import com.wkcto.crm.settings.domain.User;
import com.wkcto.crm.settings.service.UserService;
import com.wkcto.crm.utils.DateUtil;
import com.wkcto.crm.utils.SqlSessionUtil;

public class UserServiceImpl implements UserService {

	private UserDao userDao = SqlSessionUtil.getCurrentSqlSession().getMapper(UserDao.class);
	
	@Override
	public boolean save(User user) {
		return userDao.save(user) == 1;
	}

	@Override
	public User login(String loginAct, String loginPwd, String clientIp) throws LoginException {
		// 判断用户名和密码是否正确
		User user = userDao.getByLoginActAndLoginPwd(loginAct , loginPwd);
		if(user == null){
			throw new LoginException("账号或密码错误！");
		}
		// 判断账号是否锁定
		if("0".equals(user.getLockState())){
			throw new LoginException("账户被锁定，请联系管理员！");
		}
		// 判断账号是否失效
		if(user.getExpireTime() != null && !"".equals(user.getExpireTime())){
			if(user.getExpireTime().compareTo(DateUtil.getSysTime()) < 0){
				// 失效了
				throw new LoginException("账户已失效，请联系管理员！");
			}
		}
		// 判断账号IP地址是否受限
		if(user.getAllowIps() != null && !"".equals(user.getAllowIps())){
			if(!user.getAllowIps().contains(clientIp)){
				throw new LoginException("IP地址受限，请联系管理员！");
			}
		}
		return user;
	}

	@Override
	public List<User> getAll() {
		return userDao.getAll();
	}

}




















