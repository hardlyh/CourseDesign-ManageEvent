package com.event.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.event.domain.TEvent;
import com.event.domain.TUser;
import com.event.service.TUserService;
import com.event.service.base.impl.CommonServiceImpl;


public class TUserServiceImpl extends CommonServiceImpl<TUser> implements TUserService{

	@Override
	public List<TUser> login(TUser tUser) {
		DetachedCriteria dc = DetachedCriteria.forClass(TUser.class);
		dc.add(Restrictions.eq("userName",tUser.getUserName()))
		.add(Restrictions.eq("userPassword",tUser.getUserPassword()));
		List<TUser> list=getCommonDao().findByCondition(dc);
		return list;
	}
	
	public List<TUser> getAllCs(){
		DetachedCriteria dc = DetachedCriteria.forClass(TUser.class);
		dc.add(Restrictions.eq("userPriviliage",3));
		List<TUser> list=getCommonDao().findByCondition(dc);
		return list;
	}
	
	public Integer getUnEvenetByCSTotal(Integer id){
		DetachedCriteria dc = DetachedCriteria.forClass(TEvent.class);
		dc.add(Restrictions.eq("TUserByKefuId.userId",id)).add(Restrictions.eq("eventStatus",0));
		return getCommonDao().getTotalCount(dc);
	}

}
