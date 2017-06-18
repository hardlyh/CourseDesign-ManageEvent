package com.event.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.event.domain.TEvent;
import com.event.domain.TUser;
import com.event.service.TUserService;
import com.event.util.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONArray;

public class TUserAction extends ActionSupport {
	
	private int currentPage;
	
	private Integer startPage;
	
	private String condition;
	
	private int tag;

	private String id;

	private String test;

	private TUserService tUserService;

	private TUser user;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getTest() {
		return test;
	}

	public void setTest(String test) {
		this.test = test;
	}

	public TUser getUser() {
		return user;
	}

	public void setUser(TUser user) {
		this.user = user;
	}

	public TUserService gettUserService() {
		return tUserService;
	}

	public void settUserService(TUserService tUserService) {
		this.tUserService = tUserService;
	}

	// 登陆
	public String login() {
		List<TUser> list = tUserService.login(user);
		if (list.size() > 0) {
			TUser t = list.get(0);
			ActionContext.getContext().getSession().put("admin", t);
			return "login_ok";
		} else {
			return "login_error";
		}

	}
	
	//注销
	
	public String quit(){
		ActionContext.getContext().getSession().remove("admin");
		return "login_error";
	}

	// 删除用户
	public String delete() {
		tUserService.deleteEntry(Integer.parseInt(id));
		ActionContext.getContext().put("success", "删除用户成功");
		return "cslistAction";
	}

	// 获得全部的客服
	public String getAllServicer() {
		TUser tUser = (TUser) ActionContext.getContext().getSession().get("admin");
		DetachedCriteria dc = DetachedCriteria.forClass(TUser.class);
		dc.add(Restrictions.gt("userPriviliage", tUser.getUserPriviliage()));
		dc.addOrder(Order.desc("userId"));
		PageBean page=tUserService.getPageBean(dc, currentPage, 2, tag, startPage);
		ActionContext.getContext().put("pageBean", page);
		return "cslist";

	}

	// public String updateOne(){
	// TUser tUser=tUserService.getEntryById(Integer.parseInt(id));
	// System.out.println(tUser.getTKefus().getKefuNumber());
	// ActionContext.getContext().put("user", tUser);
	// return "update";
	// }
	//
	// 人员更新
	public String updateTwo() {
		TUser ori = tUserService.getEntryById(user.getUserId());

		ori.setUserRealname(user.getUserRealname());
		ori.setUserPriviliage(user.getUserPriviliage());
		ori.setUserPhone(user.getUserPhone());
		ori.setUserEmail(user.getUserEmail());
		ActionContext.getContext().put("success", "人员更新成功");
		tUserService.updateEntry(ori);
		return "cslistAction";
	}

	// 保存人员
	public String save() {
		tUserService.saveEntry(user);
		ActionContext.getContext().put("success", "人员保存成功");
		return "cslistAction";
	}

	public String updateServicer() {

		return null;
	}

	// 得到客户自己事件
	public String getEvent() {
		TUser tUser = (TUser) ActionContext.getContext().getSession().get("admin");
		System.out.println(tUser.getUserId());
		tUser = tUserService.getEntryById(tUser.getUserId());
		System.out.println("id2 " + tUser.getUserId());
		ActionContext.getContext().put("user2", tUser);
		return "event";
	}

	// 获得所有客服,并且以json的格式输出
	public String getKefu() throws IOException {
		
		Map<String, String> map = new HashMap<>();
		List<TUser> list = tUserService.getAllCs();
		for (TUser user : list) {
			map.put(String.valueOf(user.getUserId()), user.getUserRealname());
		}
		String json = JSONArray.fromObject(map).toString();
		ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
		ServletActionContext.getResponse().getWriter().println(json);
		return null;
	}

}
