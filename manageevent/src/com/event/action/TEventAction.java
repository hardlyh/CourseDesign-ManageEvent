package com.event.action;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.event.domain.TEvent;
import com.event.domain.TUser;
import com.event.service.TEventService;
import com.event.service.TUserService;
import com.event.util.PageBean;
import com.event.util.TimeUtils;
import com.event.util.UploadUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class TEventAction extends ActionSupport {
	private String id;

	private int currentPage;
	
	private TUserService service;
	
	private Integer startPage;

	private int tag;

	private String people;

	private String condition;

	private TEvent event;

	private File[] files;

	private String startTime;

	private String endTime;

	private String status = "0";

	private TEventService tEventService;



	public TUserService getService() {
		return service;
	}

	public void setService(TUserService service) {
		this.service = service;
	}

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

	// 保存事件
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
		event.setEventUrl(sb.toString());
		tEventService.saveEntry(event);
		ActionContext.getContext().put("success", "保存事件成功");
		return "eventlist";
	}

	// 得到所有为分配的事件
	public String getUnEvent() throws ParseException {
		DetachedCriteria dc = DetachedCriteria.forClass(TEvent.class);
		if (this.status.equals("0")) {
			dc.add(Restrictions.eq("eventStatus", 0));
		} else if (this.status.equals("1")) {
			dc.add(Restrictions.ne("eventStatus", 3));
			dc.add(Restrictions.ne("eventStatus", 0));
		}
		

		dc.addOrder(Order.desc("eventStarttime"));
		if (StringUtils.isNotBlank(this.condition)) {
			dc.add(Restrictions.like("eventTitle", "%" + this.condition + "%"));
		}
		if (startTime != null && endTime != null) {

			if (this.startTime.length() > 0 && this.endTime.length() > 0) {
				dc.add(Restrictions.between("eventStarttime", TimeUtils.StringToDate(this.startTime),
						TimeUtils.StringToDate(this.endTime)));
			} else if (this.startTime.length() > 0) {
				dc.add(Restrictions.between("eventStarttime", TimeUtils.StringToDate(this.startTime), new Date()));
			} else if (this.endTime.length() > 0) {
				dc.add(Restrictions.between("eventStarttime", TimeUtils.StringToDate("2000-12-31"),
						TimeUtils.StringToDate(this.endTime)));
			}
		}
		PageBean pb = tEventService.getPageBean(dc, currentPage, 2, tag, startPage);
		ActionContext.getContext().put("pageBean", pb);
		return "uneventlist";
	}

	// 删除事件,跳转到客户事件管理页面
	public String deleteEvent() {
		tEventService.deleteEntry(Integer.parseInt(id));
		ActionContext.getContext().put("success", "删除事件成功");
		return "eventlist";
	}
	
	// 删除事件,跳转到分配事件
	public String deleteEvent2(){
		tEventService.deleteEntry(Integer.parseInt(id));
		ActionContext.getContext().put("success", "删除事件成功");
		return "uneventAction";
	}

	// 分配事件,并且添加此事件对应的回话记录
	public String allotByEvent() {
		System.out.println("people : "+people);
		TUser user2 = (TUser) ActionContext.getContext().getSession().get("admin");
		TEvent event = tEventService.getEntryById(Integer.parseInt(id));  //获得事件 
		TUser tUser2 = service.getEntryById(Integer.parseInt(people)); //获得客服
		//得到客服对象并且将分配事件+1
		
		tUser2.setUserComplete(tUser2.getUserComplete()+1);
		// 设置客服id
		
		event.setEventStatus(1);
		event.setTUserByKefuId(tUser2);
		
		// 设置分配人id
		event.setTUserByLeadId(user2);
		
		tEventService.updateEntry(event);
		ActionContext.getContext().put("talkEvent", event);
		return "allotTalkAction";
	}

	// 得到关于客服所有的事件
	public String treatByKefu() throws ParseException {
		TUser tUser = (TUser) ActionContext.getContext().getSession().get("admin");
		DetachedCriteria dc = DetachedCriteria.forClass(TEvent.class);
		dc.add(Restrictions.ne("eventStatus", 3));
		dc.addOrder(Order.desc("eventStarttime"));

		if (startTime != null && endTime != null) {
			if (this.startTime.length() > 0 && this.endTime.length() > 0) {
				dc.add(Restrictions.between("eventStarttime", TimeUtils.StringToDate(this.startTime),
						TimeUtils.StringToDate(this.endTime)));
			} else if (this.startTime.length() > 0) {
				dc.add(Restrictions.between("eventStarttime", TimeUtils.StringToDate(this.startTime), new Date()));
			} else if (this.endTime.length() > 0) {
				dc.add(Restrictions.between("eventStarttime", TimeUtils.StringToDate("2000-12-31"),
						TimeUtils.StringToDate(this.endTime)));
			} else if (this.condition.length() > 0) {
				dc.add(Restrictions.like("eventTitle", "%" + this.condition + "%"));
			}

		}
		dc.add(Restrictions.eq("TUserByKefuId.userId", tUser.getUserId()));
		PageBean pageBean=tEventService.getPageBean(dc, currentPage, 2, tag, startPage);
		ActionContext.getContext().put("pageBean", pageBean);
		return "treatByKefu";
	}

	// 事件完成
	public String achiveEvent() throws IOException {
		TEvent event = this.tEventService.getEntryById(Integer.parseInt(this.id));
		ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
		if (event.getEventStatus() != 3) {
			event.setEventStatus(3);
			ServletActionContext.getResponse().getWriter().write("事件完成");
		} else {
			ServletActionContext.getResponse().getWriter().write("事件已完成");
		}
		event.setEventEndtime(new Date());
		tEventService.updateEntry(event);
		return null;
	}

	// 添加反馈
	public String feedBack() throws IOException {
		TUser tUser=(TUser) ActionContext.getContext().getSession().get("admin");
		TEvent event = this.tEventService.getEntryById(Integer.parseInt(this.id));
		ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
		if (event.getEventStatus() != 4) {
			event.setEventStatus(4);
			ServletActionContext.getResponse().getWriter().write("添加反馈成功");
		} else {
			ServletActionContext.getResponse().getWriter().write("事件已在反馈列表");
		}
		
		tUser.setUserDepartfb(tUser.getUserDepartfb()+1);
		service.updateEntry(tUser);
		tEventService.updateEntry(event);
		return null;
	}

	// 跳转待部门反馈
	public String goFeedBack() throws ParseException {
		TUser tUser = (TUser) ActionContext.getContext().getSession().get("admin");
		DetachedCriteria dc = DetachedCriteria.forClass(TEvent.class);
		dc.add(Restrictions.eq("TUserByKefuId.userId", tUser.getUserId()));
		dc.add(Restrictions.eq("eventStatus", 4));
		dc.addOrder(Order.desc("eventStarttime"));
		// 可以封装
		if (startTime != null && endTime != null) {
			if (this.startTime.length() > 0 && this.endTime.length() > 0) {
				dc.add(Restrictions.between("eventStarttime", TimeUtils.StringToDate(this.startTime),
						TimeUtils.StringToDate(this.endTime)));
			} else if (this.startTime.length() > 0) {
				dc.add(Restrictions.between("eventStarttime", TimeUtils.StringToDate(this.startTime), new Date()));
			} else if (this.endTime.length() > 0) {
				dc.add(Restrictions.between("eventStarttime", TimeUtils.StringToDate("2000-12-31"),
						TimeUtils.StringToDate(this.endTime)));
			} else if (this.condition.length() > 0) {
				dc.add(Restrictions.like("eventTitle", "%" + this.condition + "%"));
			}
		}
		PageBean pageBean=tEventService.getPageBean(dc, currentPage, 2, tag, startPage);
		ActionContext.getContext().put("pageBean", pageBean);
		return "feedBack";
	}
	
	
	// 所有待部门反馈事件
	public String goAllFeedBack() throws ParseException{
		DetachedCriteria dc = DetachedCriteria.forClass(TEvent.class);
		dc.add(Restrictions.eq("eventStatus", 4));
		dc.addOrder(Order.desc("eventStarttime"));
		// 可以封装
		if (startTime != null && endTime != null) {
			if (this.startTime.length() > 0 && this.endTime.length() > 0) {
				dc.add(Restrictions.between("eventStarttime", TimeUtils.StringToDate(this.startTime),
						TimeUtils.StringToDate(this.endTime)));
			} else if (this.startTime.length() > 0) {
				dc.add(Restrictions.between("eventStarttime", TimeUtils.StringToDate(this.startTime), new Date()));
			} else if (this.endTime.length() > 0) {
				dc.add(Restrictions.between("eventStarttime", TimeUtils.StringToDate("2000-12-31"),
						TimeUtils.StringToDate(this.endTime)));
			} else if (this.condition.length() > 0) {
				dc.add(Restrictions.like("eventTitle", "%" + this.condition + "%"));
			}
		}
		PageBean pageBean=tEventService.getPageBean(dc, currentPage, 2, tag, startPage);
		ActionContext.getContext().put("pageBean", pageBean);
		return "allFeedBack";
		
	}
	
	// 个人历史事件
	public String getSelfH() throws ParseException{
		TUser tUser=(TUser) ActionContext.getContext().getSession().get("admin");
		DetachedCriteria dc = DetachedCriteria.forClass(TEvent.class);
		dc.add(Restrictions.eq("eventStatus", 3));
		dc.add(Restrictions.eq("TUserByKefuId.userId", tUser.getUserId()));
		dc.addOrder(Order.desc("eventStarttime"));
	
		if (startTime != null && endTime != null) {
			if (this.startTime.length() > 0 && this.endTime.length() > 0) {
				dc.add(Restrictions.between("eventStarttime", TimeUtils.StringToDate(this.startTime),
						TimeUtils.StringToDate(this.endTime)));
			} else if (this.startTime.length() > 0) {
				dc.add(Restrictions.between("eventStarttime", TimeUtils.StringToDate(this.startTime), new Date()));
			} else if (this.endTime.length() > 0) {
				dc.add(Restrictions.between("eventStarttime", TimeUtils.StringToDate("2000-12-31"),
						TimeUtils.StringToDate(this.endTime)));
			} else if (this.condition.length() > 0) {
				dc.add(Restrictions.like("eventTitle", "%" + this.condition + "%"));
			}
		}
		PageBean pageBean=tEventService.getPageBean(dc, currentPage, 2, tag, startPage);
		ActionContext.getContext().put("pageBean", pageBean);
		return "myhistory";
	}
	
	

}
