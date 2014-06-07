package com.framework.dao;
import java.io.Serializable;  
import java.util.Collection;
import java.util.List;  
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Criterion;

public interface BaseDao<T> 
{

	public void save(T entity);  
	public void update(T entity);  
    public void delete(final T entity) ;
    public void delete(Serializable id);  
    public List<T> findBySQL(String sql, Object... params); 
    public T findById(Serializable id);  
    public List<T> findByIds(final Collection<Serializable> ids); 
    
    /** 
     * 取得对象的主键名. 
     */  
    public String getIdName();
    
    /** 
     *      获取全部对象, 支持按属性行序. 
     */  
    public List<T> getAll(String orderByProperty, boolean isAsc) ;
    
   
    
    /** 
     * 按Criteria查询对象列表. 
     *  
     * @param criterions 数量可变的Criterion. 
     */  
    public List<T> find(final Criterion... criterions);
    
    
    /** 
     * 按属性查找对象列表, 匹配方式为相等. 
     */  
    public List<T> findBy(final String propertyName, final Object value);
    
    
    /** 
     * 按属性查找唯一对象, 匹配方式为相等. 
     */  
    public T findUniqueBy(final String propertyName, final Object value);
    
    
    /** 
     * 根据查询HQL与参数列表创建Query对象. 
     * 与find()函数可进行更加灵活的操作. 
     *  
     * @param values 数量可变的参数,按顺序绑定. 
     */  
    public Query createQuery(final String queryString, final Object... values) ;
    
    
    /** 
     * 根据查询HQL与参数列表创建Query对象. 
     * 与find()函数可进行更加灵活的操作. 
     *  
     * @param values 命名参数,按名称绑定. 
     */  
    public Query createQuery(final String queryString, final Map<String, ?> values);
    
    
    /** 
     * 执行HQL进行批量修改/删除操作. 
     *  
     * @param values 命名参数,按名称绑定. 
     * @return 更新记录数.  map
     */  
    public int batchExecute(final String hql, final Map<String, ?> values);
    
    
    /** 
     * 执行HQL进行批量修改/删除操作. 
     *  
     * @param values 数量可变的参数,按顺序绑定. 
     * @return 更新记录数.  obj
     */  
    public int batchExecute(final String hql, final Object... values);
    
    
    /** 
     * 根据Criterion条件创建Criteria. 
     * 与find()函数可进行更加灵活的操作. 
     *  
     * @param criterions 数量可变的Criterion. 
     */  
    public Criteria createCriteria(final Criterion... criterions);
    
    
    
    /** 
     * 判断对象的属性值在数据库内是否唯一. 
     *  
     * 在修改对象的情景下,如果属性新修改的值(value)等于属性原来的值(orgValue)则不作比较. 
     */  
    public boolean isPropertyUnique(final String propertyName, final Object newValue, final Object oldValue);
}
