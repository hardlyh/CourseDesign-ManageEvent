package com.event.util;

import java.util.List;

//分页封装对象
public class PageBean {
	// 当前页面
	private Integer currentPage;
	// 记录总条数
	private Integer totalCount;
	// 页面条数多少
	private Integer pageSize;
	// 总页面
	private Integer totalPage;
	// 返回数据
	private List list;
	// 开始也
	private Integer startPage;
	// 结束页
	private Integer endPage;

	public PageBean(Integer currentPage, Integer totalCount, Integer pageSize, Integer startPage) {
		this.totalCount = totalCount;

		this.pageSize = pageSize;

		this.currentPage = currentPage;
		// 默认当前页
		if (this.currentPage == null) {
			this.currentPage = 1;
		}
		// 默认当前页面大小
		if (this.pageSize == null) {
			this.pageSize = 5;
		}
		// 计算总页数
		this.totalPage = (totalCount % pageSize == 0) ? (totalCount / pageSize) : (totalCount / pageSize) + 1;
		// 判断当前页面的范围
		if (this.currentPage < 1) {
			this.currentPage = 1;
		}

		if (this.currentPage > this.totalPage) {
			this.currentPage = this.totalPage;
		}

		if (startPage == null)
			this.startPage = 1;
		else
			this.startPage = startPage;

		if (this.startPage + 5 > totalPage)
			this.endPage = totalPage;
		else
			this.endPage = this.startPage + 5;

	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage() {
		if (this.startPage - 5 < 1) {
			this.startPage = 1;
		} else {
			this.startPage -= 5;
			this.endPage = this.startPage + 5;
			this.currentPage = this.startPage;
		}

	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage() {
		if (this.startPage + 5 < this.totalPage) {
			if (this.endPage + 5 > this.totalPage) {
				this.startPage += 5;
				this.endPage = this.totalPage;
			} else {
				this.endPage += 5;
				this.startPage = this.endPage - 5;

			}
			this.currentPage = this.startPage;
		}

	}

	// 得到开始索引
	public int getStart() {
		return (this.currentPage - 1) * this.pageSize;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

}
