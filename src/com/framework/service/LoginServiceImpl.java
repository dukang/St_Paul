package com.framework.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.framework.dao.BaseDao;
import com.framework.dao.UserDao;




@Transactional(propagation=Propagation.REQUIRED,isolation = Isolation.DEFAULT)
public class LoginServiceImpl extends BaseServiceImpl  implements LoginService 
{

	//@Resource(name = "userdao")  
	public UserDao userdao;
	
	
    public UserDao getUserdao() {
		return userdao;
	}


	public void setUserdao(UserDao userdao) 
	{
		
		this.userdao = userdao;
		super.setDao(userdao) ;
	}

	




}
