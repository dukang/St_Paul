package com.framework.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Criterion;

public interface BaseService 
{
	public void save(Object entity);  
	public void update(Object entity);  
    public void delete(final Object entity) ;
    public void delete(Serializable id);  
    public List<Object> findBySQL(String sql, Object... params); 
    public Object findById(Serializable id);  
    
    
    /** 
     *      获取全部对象, 支持按属性行序. 
     */  
    public List<Object> getAll(String orderByProperty) ;
    
   

  
    
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
     * 判断对象的属性值在数据库内是否存在. 
     *  
     * 
     */  
    public boolean isExist(final String propertyName, String Value);
   
    
}
