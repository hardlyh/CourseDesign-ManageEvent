<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<nav class="navbar navbar-default navbar-fixed-top">
			<div class="brand">
				<a href="index.html"><img src="assets/img/logo-dark.png" alt="Klorofil Logo" class="img-responsive logo"></a>
			</div>
			<div class="container-fluid">
				<div class="navbar-btn">
					<button type="button" class="btn-toggle-fullwidth"><i class="lnr lnr-arrow-left-circle"></i></button>
				</div>
				<form class="navbar-form navbar-left">
					<div class="input-group">
						<input type="text" value="" class="form-control" placeholder="搜 索...">
						<span class="input-group-btn"><button type="button" class="btn btn-primary">Go</button></span>
					</div>
				</form>
				<div class="navbar-btn navbar-btn-right">
					
				</div>
				<div id="navbar-menu">
					<ul class="nav navbar-nav navbar-right">
						
						<li class="dropdown">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown"><img src="assets/img/user.png" class="img-circle" alt="Avatar"> <span>Samuel</span> <i class="icon-submenu lnr lnr-chevron-down"></i></a>
							<ul class="dropdown-menu">
								<li><a href="#"><i class="lnr lnr-user"></i> <span>我的信息</span></a></li>
								
								<li><a href="#"><i class="lnr lnr-exit"></i> <span>注销</span></a></li>
							</ul>
						</li>
						<!-- <li>
							<a class="update-pro" href="#downloads/klorofil-pro-bootstrap-admin-dashboard-template/?utm_source=klorofil&utm_medium=template&utm_campaign=KlorofilPro" title="Upgrade to Pro" target="_blank"><i class="fa fa-rocket"></i> <span>UPGRADE TO PRO</span></a>
						</li> -->
					</ul>
				</div>
			</div>
		</nav>
		<div id="sidebar-nav" class="sidebar">
			<div class="sidebar-scroll">
				<nav>
					<ul class="nav">
						
						<li>
							<a href="#xtht"  class="active" data-toggle="collapse" class="collapsed"><i class="lnr lnr-apartment"></i> <span>系统后台</span> <i class="icon-submenu lnr lnr-chevron-left"></i></a>
							<div id="xtht" class="collapse ">
								<ul class="nav">
									<li><a href="member.html" class="">成员管理</a></li>
									
								</ul>
							</div>
						</li>
						<li>
							<a href="#kh" data-toggle="collapse" class="collapsed"><i class="lnr lnr-user"></i> <span>客户</span> <i class="icon-submenu lnr lnr-chevron-left"></i></a>
							<div id="kh" class="collapse ">
								<ul class="nav">
									<li><a href="event_management.html" class="">事件管理</a></li>
								</ul>
							</div>
						</li>
						<li>
							<a href="#zwcl" data-toggle="collapse" class="collapsed"><i class="lnr lnr-file-empty"></i> <span>总未处理事件</span> <i class="icon-submenu lnr lnr-chevron-left"></i></a>
							<div id="zwcl" class="collapse ">
								<ul class="nav">
									<li><a href="unhandled_event.html" class="">未处理事件</a></li>
								</ul>
							</div>
						</li>
						<li>
							<a href="#zls" data-toggle="collapse" class="collapsed"><i class="lnr lnr-map"></i> <span>总历史处理事件</span> <i class="icon-submenu lnr lnr-chevron-left"></i></a>
							<div id="zls" class="collapse ">
								<ul class="nav">
									<li><a href="history_event.html" class="">历史事件</a></li>
								</ul>
							</div>
						</li>
						<li>
							<a href="#wdcl" data-toggle="collapse" class="collapsed"><i class="lnr lnr-inbox"></i> <span>我的处理事件</span> <i class="icon-submenu lnr lnr-chevron-left"></i></a>
							<div id="wdcl" class="collapse ">
								<ul class="nav">
									<li><a href="handle_event.html" class="">处理事件</a></li>
								</ul>
							</div>
						</li>
						<li>
							<a href="#wddfk" data-toggle="collapse" class="collapsed"><i class="fa fa-credit-card"></i> <span>我的待反馈事件</span> <i class="icon-submenu lnr lnr-chevron-left"></i></a>
							<div id="wddfk" class="collapse ">
								<ul class="nav">
									<li><a href="feedback_event.html" class="">待部门反馈事件</a></li>
								</ul>
							</div>
						</li>
						<li>
							<a href="#lt" data-toggle="collapse" class="collapsed"><i class="lnr lnr-heart"></i> <span>聊天页</span> <i class="icon-submenu lnr lnr-chevron-left"></i></a>
							<div id="lt" class="collapse ">
								<ul class="nav">
									<li><a href="chat.html" class="">聊天框</a></li>
								</ul>
							</div>
						</li>
						<li>
							<a href="#tj" data-toggle="collapse" class="collapsed"><i class="fa fa-database"></i> <span>统计</span> <i class="icon-submenu lnr lnr-chevron-left"></i></a>
							<div id="tj" class="collapse ">
								<ul class="nav">
									<li><a href="personnel.html" class="">人员</a></li>
									<li><a href="event.html" class="">事件</a></li>
								</ul>
							</div>
						</li>
						<li>
							<a href="#gg" data-toggle="collapse" class="collapsed"><i class="fa fa-calendar"></i> <span>公告</span> <i class="icon-submenu lnr lnr-chevron-left"></i></a>
							<div id="gg" class="collapse ">
								<ul class="nav">
									<li><a href="notice.html" class="">公告</a></li>
							
								</ul>
							</div>
						</li>

					</ul>
				</nav>
			</div>
		</div>