package com.common.pojo;

import javax.persistence.*;


@Entity  
@Table(name= "user")  
public class User 
{

	public User() 
	{
		// TODO Auto-generated constructor stub
	}
	
	//主键
	@Id
	@Column(name= "uid")
	@GeneratedValue
	private Integer uid;  
	@Column(name= "user_name")  
	private String user_name;  
	@Column(name= "user_pwd")  
	private String user_pwd;  
	@Column(name= "user_email")  
	private String user_email;
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_pwd() {
		return user_pwd;
	}
	public void setUser_pwd(String user_pwd) {
		this.user_pwd = user_pwd;
	}
	public String getUser_email() {
		return user_email;
	}
	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}
	
	
	
}
