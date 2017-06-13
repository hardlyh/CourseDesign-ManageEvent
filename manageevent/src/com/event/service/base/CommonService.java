package com.event.service.base;
import java.io.Serializable;
import java.util.List;
import org.hibernate.criterion.DetachedCriteria;
import com.event.util.PageBean;

public interface CommonService<T> {

	/**
	 * 查询表中所有的数据
	 * @return
	 */
	public List<T> findAllEntry();

	/**
	 * 保存数据
	 * 
	 * @param t
	 */
	public void saveEntry(T t);

	/**
	 * ���¶���
	 * 
	 * @param t
	 */
	public void updateEntry(T t);

	/**
	 * 更新数据
	 * 
	 * @param id
	 */
	public void deleteEntry(Serializable id);

	/**
	 * 根据id得到持久化的对象
	 * 
	 * @param id
	 * @return
	 */
	public T getEntryById(Serializable id);

	/**
	 * 分页封装对象
	 * 
	 * @param dc
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public PageBean getPageBean(DetachedCriteria dc, Integer currentPage, Integer pageSize,int tag,Integer startPage);
	
	public List<T> findByCondition(DetachedCriteria dc);

}
