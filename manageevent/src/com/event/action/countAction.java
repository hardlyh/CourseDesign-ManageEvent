package com.event.action;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.event.domain.TEvent;
import com.event.domain.TUser;
import com.event.service.TEventService;
import com.event.service.TUserService;
import com.event.util.Count;
import com.event.util.PageBean;
import com.event.util.TimeUtils;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class countAction extends ActionSupport {

	private TEventService tEventService;
	
	private String startTime;

	private String endTime;
	
	private String number;

	private String condition;
	
	private TUserService service;
	
	private String dateString;

	private int currentPage;
	
	private Integer startPage;
	
	private int tag;

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getStartPage() {
		return startPage;
	}

	public void setStartPage(Integer startPage) {
		this.startPage = startPage;
	}

	public int getTag() {
		return tag;
	}

	public void setTag(int tag) {
		this.tag = tag;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public TUserService getService() {
		return service;
	}

	public void setService(TUserService service) {
		this.service = service;
	}

	public TEventService gettEventService() {
		return tEventService;
	}

	public void settEventService(TEventService tEventService) {
		this.tEventService = tEventService;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	// 事件总计
	public String getMouth() throws ParseException {
		DetachedCriteria dc;
		dc=getDc();
		dc.add(Restrictions.eq("eventStatus", 4));
		Integer num=tEventService.getTotal(dc);  //待反馈事件
		
		dc=getDc();
		dc.add(Restrictions.eq("eventStatus", 3));
		Integer num2=tEventService.getTotal(dc);  //完成事件
		
		DetachedCriteria dc2 = DetachedCriteria.forClass(TUser.class);
		dc2.add(Restrictions.eq("userPriviliage", 3));
		
		Integer num3=service.getTotal(dc2);  //客服总数
		ActionContext.getContext().put("treat", num);
		ActionContext.getContext().put("achieve", num2);
		ActionContext.getContext().put("custorm", num3);
		return "count";
	}
	
	// 每个客服人员的事件数量
	public String getCountByKefu() throws ParseException{
		List<Count> list2=new ArrayList<>();	
		DetachedCriteria dc = DetachedCriteria.forClass(TUser.class);
		DetachedCriteria dc2;
		dc.add(Restrictions.eq("userPriviliage", 3));
		if(this.condition!=null){
			if(this.condition.length()>0)
				dc.add(Restrictions.eq("userRealname",this.condition));
			if(this.number.length()>0){
				dc.add(Restrictions.eq("userId",Integer.parseInt(this.number)));
			}
		}
		PageBean pg=service.getPageBean(dc, currentPage, 2, tag, startPage);  //得到所有的客服
		List<TUser> list=pg.getList();
		for(TUser t:list){
			Count co=new Count();
			dc2=getDc();
			dc2.add(Restrictions.eq("eventStatus", 4));
			dc2.add(Restrictions.eq("TUserByKefuId.userId", t.getUserId()));
			Integer num1=tEventService.getTotal(dc2); 
			dc2=getDc();
			dc2.add(Restrictions.eq("eventStatus", 3));
			dc2.add(Restrictions.eq("TUserByKefuId.userId", t.getUserId()));
			Integer num2=tEventService.getTotal(dc2);  //完成事件
			co.setName(t.getUserRealname());
			co.setNum1(num1);
			co.setNum2(num2);
			co.setNumber(t.getUserId());
			list2.add(co);
		}
		
		ActionContext.getContext().put("pageBean", pg);
		ActionContext.getContext().put("list", list2);
		return "count2";
	}
	
	public DetachedCriteria getDc() throws ParseException{
		DetachedCriteria dc = DetachedCriteria.forClass(TEvent.class);
		if (startTime != null && endTime != null) {
			if (this.startTime.length() > 0 && this.endTime.length() > 0) {
				dc.add(Restrictions.between("eventStarttime", TimeUtils.StringToDate(this.startTime),
						TimeUtils.StringToDate(this.endTime)));
				dateString=startTime+" -- "+endTime;
				
			} else if (this.startTime.length() > 0) {
				dc.add(Restrictions.between("eventStarttime", TimeUtils.StringToDate(this.startTime), new Date()));
				dateString=startTime+" -- 2100-12-31";
				
			} else if (this.endTime.length() > 0) {
				dc.add(Restrictions.between("eventStarttime", TimeUtils.StringToDate("2000-12-31"),
						TimeUtils.StringToDate(this.endTime)));
				dateString="1990-01-01 -- "+this.startTime;
			
			}else{
				dateString="1990-01-01 -- 2100-12-31";
			}
			ActionContext.getContext().put("dateString", dateString);
		}
		return dc;
	}
	
	
	
}
