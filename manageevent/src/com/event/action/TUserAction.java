package com.event.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.event.domain.TUser;
import com.event.service.TUserService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONArray;

public class TUserAction extends ActionSupport{
	
	private String id;
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	private String test;

	private TUserService tUserService;

	private TUser user;

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

	public String login() {
		System.out.println("username: " + user.getUserName());

		List<TUser> list = tUserService.login(user);
		if (list.size() > 0) {
			TUser t = list.get(0);
			ActionContext.getContext().getSession().put("admin", t);
			return "login_ok";
		} else {
			return "login_error";
		}

	}

	// 获得全部的客服
	public String getAllServicer() {
		List<TUser> list = tUserService.findAllEntry();
		ActionContext.getContext().put("sclist", list);
		return "cslist";

	}
	
//	public String updateOne(){
//		TUser tUser=tUserService.getEntryById(Integer.parseInt(id));
//		System.out.println(tUser.getTKefus().getKefuNumber());
//		ActionContext.getContext().put("user", tUser);
//		return "update";
//	}
//	
	public String updateTwo(){
		TUser ori=tUserService.getEntryById(user.getUserId());
		System.out.println("update : "+ori.getUserName());
		System.out.println(user.getUserPriviliage());
		ori.setUserRealname(user.getUserRealname());
		ori.setUserPriviliage(user.getUserPriviliage());
		ori.setUserPhone(user.getUserPhone());
		ori.setUserEmail(user.getUserEmail());
		
		tUserService.updateEntry(ori);
		return "cslistAction";
	}
	
	public String save(){
		tUserService.saveEntry(user);
		return "cslistAction";
	}
	
	public String updateServicer(){
		
		return null;
	}
	
	public String getEvent(){
		TUser tUser=(TUser)ActionContext.getContext().getSession().get("admin");
		System.out.println(tUser.getUserId());
		tUser=tUserService.getEntryById(tUser.getUserId());
		System.out.println("id2 "+tUser.getUserId());
		ActionContext.getContext().put("user2", tUser);
		return "event";
	}
	
	public String getKefu() throws IOException{
		
		Map<String,String> map=new HashMap<>();
		List<TUser> list=tUserService.getAllCs();
		for(TUser user:list){
			map.put(String.valueOf(user.getUserId()), user.getUserRealname());
		}
		String json=JSONArray.fromObject(map).toString();
		ServletActionContext.getResponse().getWriter().println(json);
		return null;
	}
	


	
}
