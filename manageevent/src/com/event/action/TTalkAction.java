package com.event.action;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.event.domain.TEvent;
import com.event.domain.TRecord;
import com.event.domain.TTalk;
import com.event.domain.TUser;
import com.event.service.TRecordService;
import com.event.service.TTalkService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class TTalkAction extends ActionSupport {

	private Integer id;

	private TTalkService talkService;
	
	private String fromName;

	private String information;

	private TRecordService recordService;

	
	
	public String getFromName() {
		return fromName;
	}

	public void setFromName(String fromName) {
		this.fromName = fromName;
	}

	public TTalkService getTalkService() {
		return talkService;
	}

	public void setTalkService(TTalkService talkService) {
		this.talkService = talkService;
	}

	public String getInformation() {
		return information;
	}

	public void setInformation(String information) {
		this.information = information;
	}

	public TRecordService getRecordService() {
		return recordService;
	}

	public void setRecordService(TRecordService recordService) {
		this.recordService = recordService;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	// 在分配客服的同时插入这个事件对应的talk
	public String allotTalk() {
		TEvent event = (TEvent) ActionContext.getContext().get("talkEvent");
		TTalk talk = new TTalk();
		TUser tUser=event.getTUserByKefuId();
		TUser tUser2=event.getTUserByEventCommitId();
		talk.setTUserByKefuId(tUser);
		talk.setTUserByKehuId(tUser2);
		talk.setEventId(event.getEventId());
		talkService.saveEntry(talk);
		return "uneventlistAction";
	}

	// 得到指定id对应的会话,得到会话对象,然后跳转到对话框窗口
	public String getTalk() {
		DetachedCriteria dc = DetachedCriteria.forClass(TTalk.class);
		dc.add(Restrictions.eq("eventId", id));
		List<TTalk> list = talkService.findByCondition(dc);
		TTalk talk = list.get(0);
		System.out.println(talk.getRecords());
		ActionContext.getContext().put("talk2", talk);
		return "chat";
	}
	
	// 得到指定id对应的会话,得到会话对象,跳转历史回话窗口
		public String getTalkH() {
			DetachedCriteria dc = DetachedCriteria.forClass(TTalk.class);
			dc.add(Restrictions.eq("eventId", id));
			List<TTalk> list = talkService.findByCondition(dc);
			TTalk talk = list.get(0);
			ActionContext.getContext().put("talk2", talk);
			return "chat2";
		}
	
	// 保存发送的信息到数据库并且返回json格式
	public String saveInfor(){
		System.out.println("ok1 :"+id);
		TRecord record=new TRecord();
		TTalk t=talkService.getEntryById(this.id);
		record.setRecordContent(this.information);
		record.setRecordForm(this.fromName);
		record.setTalk(t);
		record.setRecordTime(new Date());
		recordService.saveEntry(record);
		System.out.println("ok");
		return null;
	}

}
