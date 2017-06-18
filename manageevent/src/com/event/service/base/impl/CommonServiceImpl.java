package com.event.service.base.impl;
import java.io.Serializable;
import java.util.List;
import org.hibernate.criterion.DetachedCriteria;
import com.event.dao.base.CommonDao;
import com.event.service.base.CommonService;
import com.event.util.PageBean;


public class CommonServiceImpl<T> implements CommonService<T>{
	
	private CommonDao commonDao;
	

	public CommonDao getCommonDao() {
		return commonDao;
	}

	public void setCommonDao(CommonDao commonDao) {
		this.commonDao = commonDao;
	}

	@Override
	public List<T> findAllEntry() {
		return commonDao.findAllEntry();
	}

	@Override
	public void saveEntry(T t) {
		commonDao.saveEntry(t);
	}

	@Override
	public void updateEntry(T t) {
		commonDao.updateEntry(t);
	}

	@Override
	public void deleteEntry(Serializable id) {
		commonDao.deleteEntry(id);
	}
	

	@Override
	public T getEntryById(Serializable id) {
		return (T) commonDao.getEntryById(id);
	}
	
	public PageBean getPageBean(DetachedCriteria dc, Integer currentPage, Integer pageSize,int tag,Integer startPage){
		Integer totalCount = commonDao.getTotalCount(dc);
		PageBean pb = new PageBean(currentPage, totalCount, pageSize,startPage);
		
		if(tag==1){
			pb.setStartPage();
		}else if(tag==2){
			pb.setEndPage();
		}
		
		List<T> list = commonDao.getPageList(dc,pb.getStart(),pb.getPageSize());
		pb.setList(list);
		return pb;
	}

	@Override
	public List<T> findByCondition(DetachedCriteria dc) {
		return commonDao.findByCondition(dc);
	}
	
	public Integer getTotal(DetachedCriteria dc){
		return commonDao.getTotalCount(dc);
	}

}
