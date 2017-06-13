package com.event.action;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.event.domain.TEvent;
import com.event.domain.TUser;
import com.event.service.TEventService;
import com.event.util.ContidionBean;
import com.event.util.EmptyString;
import com.event.util.PageBean;
import com.event.util.TimeUtils;
import com.event.util.UploadUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class TEventAction extends ActionSupport {
	private String id;

	private int currentPage;

	private Integer startPage;

	private int tag;

	private String people;

	private String condition;

	private TEvent event;

	private File[] files;
	
	private String startTime;
	
	private String endTime;
	
	private String status="0";
	
	private TEventService tEventService;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public int getTag() {
		return tag;
	}

	public void setTag(int tag) {
		this.tag = tag;
	}

	public String getPeople() {
		return people;
	}

	public void setPeople(String people) {
		this.people = people;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	

	public File[] getFiles() {
		return files;
	}

	public void setFiles(File[] files) {
		this.files = files;
	}

	public TEvent getEvent() {
		return event;
	}

	public void setEvent(TEvent event) {
		this.event = event;
	}

	public TEventService gettEventService() {
		return tEventService;
	}

	public void settEventService(TEventService tEventService) {
		this.tEventService = tEventService;
	}

	public String saveEvent() throws IOException {
		StringBuffer sb = new StringBuffer();
		TUser tUser = (TUser) ActionContext.getContext().getSession().get("admin");
		event.setTUserByEventCommitId(tUser);
		event.setEventStarttime(new Date());
		event.setEventStatus(0);

		if (files != null) {
			for (File f : files) {
				sb = UploadUtil.Upload(f, sb);
			}
		}

		System.out.println(sb);
		event.setEventUrl(sb.toString());
		tEventService.saveEntry(event);
		return "eventlist";
	}

	public String getUnEvent() throws ParseException {
		DetachedCriteria dc = DetachedCriteria.forClass(TEvent.class);
		if(this.status.equals("0")){
			dc.add(Restrictions.eq("eventStatus", 0));
		}else if(this.status.equals("1")){
			dc.add(Restrictions.eq("eventStatus", 1));
		}
		
		dc.addOrder(Order.desc("eventStarttime"));
		if (StringUtils.isNotBlank(this.condition)) {
			dc.add(Restrictions.like("eventTitle", "%" + this.condition + "%"));
		}
		if (startTime!=null && endTime!=null) {
			System.out.println("start "+ startTime);
			System.out.println("end "+endTime);
			if (this.startTime.length()>0 &&  this.endTime.length()>0) {
				dc.add(Restrictions.between("eventStarttime", TimeUtils.StringToDate(this.startTime), TimeUtils.StringToDate(this.endTime)));
			} else if (this.startTime.length()>0) {
				dc.add(Restrictions.between("eventStarttime", TimeUtils.StringToDate(this.startTime), new Date()));
			} else if (this.endTime.length()>0) {
				dc.add(Restrictions.between("eventStarttime", TimeUtils.StringToDate("2000-12-31"),TimeUtils.StringToDate(this.endTime) ));
			}
		}
		PageBean pb = tEventService.getPageBean(dc, currentPage, 3, tag, startPage);
		ActionContext.getContext().put("pageBean", pb);
		return "uneventlist";
	}

	public String deleteEvent() {
		tEventService.deleteEntry(Integer.parseInt(id));
		return "eventlist";
	}

	public String allotByEvent() {
		TEvent event = tEventService.getEntryById(Integer.parseInt(id));
		TUser tUser = new TUser();
		tUser.setUserId(Integer.parseInt(people));
		event.setEventStatus(1);
		event.setTUserByKefuId(tUser);
		tEventService.updateEntry(event);
		return "uneventlistAction";
	}

}
