package com.event.dao.base;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

public interface CommonDao<T> {
	
	/**
	 * 查询全部的可持久化对象
	 * @return
	 */
	public List<T> findAllEntry();
	
	/**
	 * 保存可持久化对象
	 * @param
	 */
	public void saveEntry(T t);
	
	/**
	 * 更细可持久化对象
	 * @param t 可持久化对象
	 */
	public void updateEntry(T t);
	
	/**
	 * 删除对象
	 * @param id 删除对象的主键
	 */
	public void deleteEntry(Serializable id);
	
	/**
	 * 通过主键得到可持久化对象
	 * @param id 需要查询对象的主键
	 * @return
	 */
	public T getEntryById(Serializable id);
	
	
	/**
	 * 通过条件查询
	 * @param dc DetachedCriteria
	 * @return
	 */
	public Integer getTotalCount(DetachedCriteria dc);
	
	/**
	 * 返回分页对象
	 * @param dc DetachedCriteria  
	 * @param start 开始页
	 * @param pageSize  页面长度
	 * @return
	 */
	
	public List<T> getPageList(DetachedCriteria dc,Integer start,Integer pageSize);
	
	
	/**
	 * DetachedCriteria
	 * @param dc
	 * @return
	 */
	public List<T> findByCondition(DetachedCriteria dc) ;
	
	
	/**
	 * 通过hql语句查询
	 * @param hql
	 * @return
	 */
	public List selectByHql(String hql);

}
