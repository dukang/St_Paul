package com.framework.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import com.common.pojo.User;

import com.framework.dao.BaseDao;
import com.framework.dao.UserDao;




@Transactional(propagation=Propagation.REQUIRED,isolation = Isolation.DEFAULT)
public class LoginServiceImpl extends BaseServiceImpl  implements LoginService 
{

	//@Resource(name = "userdao")  
	public UserDao userdao;
	
	@Autowired  
	private  HttpServletRequest request; 
	
    public UserDao getUserdao() {
		return userdao;
	}


	public void setUserdao(UserDao userdao) 
	{
		
		this.userdao = userdao;
		super.setDao(userdao) ;
	}


	@Override
	public boolean isLogin() 
	{
		/*HttpSession session = request.getSession();
		if(session.getAttribute("user")==null)
		// TODO Auto-generated method stub
		return false;
		else
			return true;*/
		return false;
	}


	@SuppressWarnings("unchecked")
	@Override
	public boolean login(User user) 
	{
		// TODO Auto-generated method stub
		Map<String,String> user_login = new HashMap<String,String>();
		String user_name= "";
		String user_pwd= "";
		user_name = user.getUser_name();
		user_pwd  = user.getUser_pwd();
		user_login.put("user_name", user_name+"");
		user_login.put("user_pwd", user_pwd+"");
		//注意“:”
		String hql = "from User u where u.user_name = :user_name and u.user_pwd = :user_pwd";
		
		List<User> list =  this.userdao.createQuery(hql, user_login).list();
		
		if(list.isEmpty())
			return false;
		else
		{
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			return true;
		}
		
	}


	@Override
	public boolean register(User user) 
	{
		
		
		
		// TODO Auto-generated method stub
		if(this.userdao.findBy("user_name", user.getUser_name()).isEmpty())
		{
			userdao.save(user);
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public User findUserByName(String name)
	{
		HttpSession session = request.getSession();
		User my_user = (User) session.getAttribute("user");
		if(my_user==null)
		{
			my_user=userdao.findBy("user_name", name).get(0);
			
			return my_user;
		}
		else
		{
			
			return my_user;
		}
	}

	




}
