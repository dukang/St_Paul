package com.framework.dao;

import java.lang.reflect.ParameterizedType;
import java.io.Serializable;  
import java.util.Collection;  
import java.util.List;  
import java.util.Map;  
  
import org.hibernate.Criteria;  
import org.hibernate.Hibernate;  
import org.hibernate.Query;  
import org.hibernate.Session;  
import org.hibernate.SessionFactory;  
import org.hibernate.criterion.CriteriaSpecification;  
import org.hibernate.criterion.Criterion;  
import org.hibernate.criterion.Order;  
import org.hibernate.criterion.Restrictions;  
import org.hibernate.metadata.ClassMetadata;  
import org.slf4j.Logger;  
import org.slf4j.LoggerFactory;  
import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.util.Assert;  

public class BaseDaoImpl<T> implements BaseDao<T> {  

	protected Class<T> entityClass;  
	  
	    /** 
	     * 通过构造方法指定DAO的具体实现类 
	     */  
    @SuppressWarnings("unchecked")
	public BaseDaoImpl() 
    {  
	        ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();  
	        entityClass = (Class<T>) type.getActualTypeArguments()[0];  
	        //System.out.println("DAO的真实实现类是：" + this.clazz.getName());  
	}  
    
    //@Resource  
    private SessionFactory sessionFactory;  
    
    public SessionFactory getSessionFactory() 
    {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) 
	{
		this.sessionFactory = sessionFactory;
		
	}

	protected Session getSession() 
	{
		//return this.sessionFactory.openSession();
        return this.sessionFactory.getCurrentSession();  
    }  
	
	@Override
	public void save(T entity)
    {
		Assert.notNull(entity, "entity不能为空");  
		// TODO Auto-generated method stub
		this.getSession().persist(entity);  
	}

	@Override
	public void update(T entity) 
	{
		Assert.notNull(entity, "entity不能为空");  
		// TODO Auto-generated method stub
		this.getSession().update(entity);  
	}
	@Override
	public void delete(final T entity) {  
        Assert.notNull(entity, "entity不能为空");  
        getSession().delete(entity);  
	}  
	@Override
	public void delete(Serializable id) 
	{
		Assert.notNull(id, "id不能为空");  
		// TODO Auto-generated method stub
		delete(this.findById(id));  
	}

	
    public String getIdName() {  
            ClassMetadata meta = getSessionFactory().getClassMetadata(entityClass);  
            return meta.getIdentifierPropertyName();  
    }  
	
	@SuppressWarnings("unchecked")
	@Override
	public T findById(Serializable id) 
	{
		Assert.notNull(id, "id不能为空");  
		// TODO Auto-generated method stub
		return (T) this.getSession().get(this.entityClass, id);  
	}
	
	@Override
	public List<T> findByIds(final Collection<Serializable> ids) {  
        return find(Restrictions.in(getIdName(), ids));  
	}  
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findBySQL(String sql, Object... params) 
	{
		Assert.notNull(sql, "sql不能为空");  
		// TODO Auto-generated method stub
		Query query = this.getSession().createQuery(sql);  
        for (int i = 0; params != null && i < params.length; i++) {  
            query.setParameter(i, params);  
        }  
        return query.list();  
	}
	
	public List<T> getAll(String orderByProperty, boolean isAsc) {  
        Criteria c = createCriteria();  
        if (isAsc) {  
                c.addOrder(Order.asc(orderByProperty));  
        } else {  
                c.addOrder(Order.desc(orderByProperty));  
        }  
        return c.list();  
	}
	 
    public List<T> findBy(final String propertyName, final Object value) {  
            Assert.hasText(propertyName, "propertyName不能为空");  
            Criterion criterion = Restrictions.eq(propertyName, value);  
            return find(criterion);  
    }  
	
    
    public T findUniqueBy(final String propertyName, final Object value) {  
            Assert.hasText(propertyName, "propertyName不能为空");  
            Criterion criterion = Restrictions.eq(propertyName, value);  
            return (T) createCriteria(criterion).uniqueResult();  
    }  
    
    public List<T> find(final Criterion... criterions) 
    {  
    	
            return createCriteria(criterions).list();  
    }  
    
    
    public Query createQuery(final String queryString, final Object... values) 
    {  
            Assert.hasText(queryString, "queryString不能为空");  
            Query query = getSession().createQuery(queryString);  
            if (values != null) {  
                    for (int i = 0; i < values.length; i++) {  
                            query.setParameter(i, values[i]);  
                    }  
            }  
            return query;  
    }  
    
  
    public Query createQuery(final String queryString, final Map<String, ?> values) {  
            Assert.hasText(queryString, "queryString不能为空");  
            Query query = getSession().createQuery(queryString);  
            if (values != null) {  
                    query.setProperties(values);  
            }  
            return query;  
    }  
    
    public int batchExecute(final String hql, final Object... values) 
    {  
            return createQuery(hql, values).executeUpdate();  
    }  

   
    public int batchExecute(final String hql, final Map<String, ?> values) {  
            return createQuery(hql, values).executeUpdate();  
    }  
    
    public Criteria createCriteria(final Criterion... criterions) {  
            Criteria criteria = getSession().createCriteria(entityClass);  
            for (Criterion c : criterions) {  
                    criteria.add(c);  
            }  
            return criteria;  
    }  
    
  
    public boolean isPropertyUnique(final String propertyName, final Object newValue, final Object oldValue) 
    {  
            if (newValue == null || newValue.equals(oldValue)) 
            {  
                    return true;  
            }  
            Object object = findUniqueBy(propertyName, newValue);  
            return (object == null);  
    }  
	
}
