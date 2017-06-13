package com.event.action;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.event.domain.TEvent;
import com.event.service.TEventService;
import com.event.util.TimeUtils;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class HistoryEventAction extends ActionSupport {

	private int currentPage; // 当前页

	private Integer startPage;

	private int tag; // 分页标签

	private String startTime; // 结束时间

	private String endTime; // 结束时间



	private Integer eventId = 0;

	private String kefuName;

	private String kefuNumber;

	public Integer getEventId() {
		return eventId;
	}

	public void setEventId(Integer eventId) {
		this.eventId = eventId;
	}

	public String getKefuName() {
		return kefuName;
	}

	public void setKefuName(String kefuName) {
		this.kefuName = kefuName;
	}

	public String getKefuNumber() {
		return kefuNumber;
	}

	public void setKefuNumber(String kefuNumber) {
		this.kefuNumber = kefuNumber;
	}

	private TEventService tEventService;

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


	public TEventService gettEventService() {
		return tEventService;
	}

	public void settEventService(TEventService tEventService) {
		this.tEventService = tEventService;
	}
	
	//得到所有已经分配的历史事件
	
	public String getHtEvent() throws ParseException { 
		DetachedCriteria dc = DetachedCriteria.forClass(TEvent.class);
		dc.add(Restrictions.isNotNull("TUserByKefuId"));
		dc.addOrder(Order.desc("eventStarttime"));
		if (startTime!=null && endTime!=null) {
			if (this.startTime.length()>0 &&  this.endTime.length()>0) {
				dc.add(Restrictions.between("eventStarttime", TimeUtils.StringToDate(this.startTime), TimeUtils.StringToDate(this.endTime)));
			} else if (this.startTime.length()>0) {
				dc.add(Restrictions.between("eventStarttime", TimeUtils.StringToDate(this.startTime), new Date()));
			} else if (this.endTime.length()>0) {
				dc.add(Restrictions.between("eventStarttime", TimeUtils.StringToDate("2000-12-31"),TimeUtils.StringToDate(this.endTime) ));
			}
		}
		//PageBean pb = tEventService.getPageBean(dc, currentPage, 3, tag, startPage);
		List<TEvent> list=tEventService.findByCondition(dc);
		ActionContext.getContext().put("pageBean", list);
		return "historyEvent";
	}


}
