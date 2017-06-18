package com.event.dao.base.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.event.dao.base.CommonDao;


public class CommonDaoImpl<T> implements CommonDao<T> {

	private HibernateTemplate hibernateTemplate; // spring 注入参数
	
	public HibernateTemplate getHibernateTemplate() {

		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	private Class classt;

	public CommonDaoImpl() {
		ParameterizedType parameterizedType = (ParameterizedType) this.getClass().getGenericSuperclass();
		this.classt = (Class) parameterizedType.getActualTypeArguments()[0];
	}

	//查询所有的可持久化对象
	public List<T> findAllEntry() {
		return (List<T>) this.hibernateTemplate.find("from " + this.classt.getName());
	}
	
	
	//保存可持久化对象
	public void saveEntry(T t) {
		this.hibernateTemplate.save(t);

	}
	
	
	public List selectByHql(String hql){
		List<T> list=hibernateTemplate.find(hql);
		return list;
	}
	
	//更新对象
	public void updateEntry(T t) {
		this.hibernateTemplate.merge(t);
	}

	//删除对象
	public void deleteEntry(Serializable id) {
		System.out.println("delete : " + id);
		T t = (T) this.hibernateTemplate.get(this.classt, id);
		this.hibernateTemplate.delete(t);
	}

	//通过主键来查询可持久化对象
	public T getEntryById(Serializable id) {
		return (T) this.hibernateTemplate.get(this.classt, id);
	}

	//返回分页list
	public List<T> getPageList(DetachedCriteria dc, Integer start, Integer pageSize) {

		List<T> list = (List<T>) this.hibernateTemplate.findByCriteria(dc, start, pageSize);
		return list;
	}
	
	//通过DetachedCriteria查询,返回list
	public List<T> findByCondition(DetachedCriteria dc) {
		List<T> list = (List<T>)this.hibernateTemplate.findByCriteria(dc);
		return list;
	}
	
	//返回指定表的目录条数
	public Integer getTotalCount(DetachedCriteria dc) {
		
		dc.setProjection(Projections.rowCount());
		List<Long> list = (List<Long>) getHibernateTemplate().findByCriteria(dc);
		
		dc.setProjection(null);
		if (list != null && list.size() > 0) {
			Long count = list.get(0);
			return count.intValue();
		} else {
			return null;
		}

	}

}
