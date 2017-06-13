<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<ul class="pagination" id="paging">
	<li><a id="1"  class="lnr lnr-chevron-left-circle"
		href="${targetAction }?tag=1&startPage=${pageBean.startPage}&currentPage=${pageBean.currentPage}&condition=${condition}&startTime=${startTime}&endTime=${endTime}&status=${status}"></a></li>
	<c:forEach begin="${pageBean.startPage }" end="${pageBean.endPage }"
		varStatus="s">
		<li class="${s.index==pageBean.currentPage?'active':'' }"><a id="0" 
			href="${targetAction }?tag=0&currentPage=${s.index}&startPage=${pageBean.startPage}&condition=${condition}&startTime=${startTime}&endTime=${endTime}&status=${status}">${s.index }</a></li>
	</c:forEach>

	<li><a id="2" class="lnr lnr-chevron-right-circle"
		href="${targetAction }?tag=2&startPage=${pageBean.startPage}&currentPage=${pageBean.currentPage}&condition=${condition}&startTime=${startTime}&endTime=${endTime}&status=${status}"></a></li>
	<li class="disabled"><a>共${pageBean.totalPage }页</a></li>
</ul>





