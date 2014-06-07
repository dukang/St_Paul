package com.framework.service;

import java.io.Serializable;

import java.util.List;
import java.util.Map;



import org.springframework.transaction.annotation.*;
import com.framework.dao.BaseDao;


@Transactional(propagation=Propagation.REQUIRED,isolation = Isolation.DEFAULT)
public class BaseServiceImpl implements BaseService
{
	private BaseDao dao;
	String namespace;
	
	
	public BaseServiceImpl()
	{
		this.namespace = "";
	}
	public BaseServiceImpl(String namespace)
	{
		this.namespace = namespace;
	}
	
		public BaseDao getDao() {
		return dao;
	}

	public void setDao(BaseDao dao) 
	{
		
		this.dao = dao;
	}

	@Override
	public void save(Object entity) 
	{
		// TODO Auto-generated method stub
		this.dao.save(entity);
	}

	@Override
	public void update(Object entity) 
	{
		// TODO Auto-generated method stub
		this.dao.update(entity);
	}

	@Override
	public void delete(Object entity) 
	{
		// TODO Auto-generated method stub
		this.dao.delete(entity);
	}

	@Override
	public void delete(Serializable id) 
	{
		// TODO Auto-generated method stub
		this.dao.delete(id);
	}

	@Override
	public List<Object> findBySQL(String sql, Object... params) 
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object findById(Serializable id) 
	{
		// TODO Auto-generated method stub
		return this.dao.findById(id);
		
	}



	@Override
	public List<Object> getAll(String orderByProperty) 
	{
		// TODO Auto-generated method stub
		return null;
	}

	


	@Override
	public int batchExecute(String hql, Object... values) 
	{
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int batchExecute(String hql, Map<String, ?> values) 
	{
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public boolean isExist(String propertyName,String value) 
	{
		
		if(this.dao.findBy(propertyName, value).isEmpty()
		)
		{
			return false;
		}
		
		return true;
	}

	
	
	

}
