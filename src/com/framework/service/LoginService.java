package com.framework.service;

import com.common.pojo.User;


public interface LoginService extends BaseService 
{

	public boolean register(User user);
	public boolean login(User user) ;
	public boolean isLogin() ;
	public User findUserByName(String name);

}
