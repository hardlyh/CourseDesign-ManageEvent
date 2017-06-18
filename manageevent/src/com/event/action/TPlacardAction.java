package com.event.action;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;

import com.event.domain.TPlacard;
import com.event.domain.TUser;
import com.event.service.TPlacardService;
import com.event.util.PageBean;
import com.event.util.UploadUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class TPlacardAction extends ActionSupport {
	private int currentPage; // 当前页
	
	private Integer startPage;
	
	private int tag; // 分页标签
	private File file;

	private String title;

	private String content;

	private TPlacardService placardService;

	private TPlacard placard;

	private Integer id;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public int getTag() {
		return tag;
	}

	public void setTag(int tag) {
		this.tag = tag;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setPlacard(TPlacard placard) {
		this.placard = placard;
	}

	public TPlacardService getPlacardService() {
		return placardService;
	}

	public void setPlacardService(TPlacardService placardService) {
		this.placardService = placardService;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	// 得到公告
	public String getPlacard() {
		DetachedCriteria dc = DetachedCriteria.forClass(TPlacard.class);
		dc.addOrder(Order.desc("placardTime"));
		PageBean pageBean=placardService.getPageBean(dc, currentPage, 2, tag, startPage);
		ActionContext.getContext().put("pageBean", pageBean);
		return "placardList";
	}

	public String savePlacard() throws IOException {
		StringBuffer sb = new StringBuffer();
		TUser tUser = (TUser) ActionContext.getContext().getSession().get("admin");
		Date date = new Date();
		TPlacard placard=new TPlacard();
		placard.setPlacardTitle(this.title);
		placard.setPlacardContent(this.content);
		placard.setPlacardTime(date);
		placard.setUserName(tUser.getUserRealname());
		sb = UploadUtil.Upload(file, sb);
		String str = sb.substring(0, sb.length() - 1);
		placard.setPlacardUrl(str);
		placardService.saveEntry(placard);
		ActionContext.getContext().put("success", "保存公告成功");
		return "placardListAction";
	}

	public String deletePlacard() {
		placardService.deleteEntry(this.id);
		ActionContext.getContext().put("success", "删除公告成功");
		return "placardListAction";
	}

}
