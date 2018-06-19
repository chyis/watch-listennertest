package org.andy.dao;

import java.io.Serializable;
import java.util.List;

public interface EntityDao<T> {

	/** 
     * 根据Id查找一个类型为T的对象。 
     *  
     * @param id 传入的Id的值 
     * @return 一个类型为T的对象 
     */  
    T get(Serializable id);  
      
    /** 
     * 获取对象的全部集合，集合中的对象为T 
     * @return 一组T对象的List集合 
     */  
    List<T> getAll();  
      
    /** 
     * 持久化一个对象，该对象类型为T 
     * @param newInstance 需要持久化的对象 
     */  
    void save(T newInstance);  
      
    /** 
     * 删除一个已经被持久化的对象，该对象类型为T 
     * @param transientObject 需要删除的持久化对象 
     */  
    void remove(T transientObject);  
      
    /** 
     * 根据对象id删除一个对象，该对象类型为T 
     * @param id 需要删除的对象的id 
     */  
    void removeById(Serializable id);  
      
    /** 
     * 获取Entity对像的主键名 
     * @param clazz 
     * @return 
     */  
    String getIdName(Class<?> clazz);  
}
