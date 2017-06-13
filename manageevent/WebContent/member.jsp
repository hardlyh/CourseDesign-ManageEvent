<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>
<title>Dashboard | Klorofil - Free Bootstrap Dashboard Template</title>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
<!-- VENDOR CSS -->
<link rel="stylesheet" href="assets/vendor/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="assets/vendor/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" href="assets/vendor/linearicons/style.css">
<link rel="stylesheet" href="assets/vendor/chartist/css/chartist-custom.css">
<!-- MAIN CSS -->
<link rel="stylesheet" href="assets/css/main.css">
<!-- FOR DEMO PURPOSES ONLY. You should remove this in your project -->
<link rel="stylesheet" href="assets/css/demo.css">
<!-- GOOGLE FONTS -->
<link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700" rel="stylesheet">
<!-- ICONS -->
<link rel="apple-touch-icon" sizes="76x76" href="assets/img/apple-icon.png">
<link rel="icon" type="image/png" sizes="96x96" href="assets/img/favicon.png">
</head>

<body>
	<!-- WRAPPER -->
	<div id="wrapper">
		<!-- NAVBAR -->
		<%@include file="head.jsp"%>
		<!-- END NAVBAR -->
		<!-- LEFT SIDEBAR -->

		<!-- END LEFT SIDEBAR -->
		<!-- MAIN -->
		<div class="main">
			<!-- MAIN CONTENT -->
			<div class="main-content">
				<div class="container-fluid">
					<div class="row">
						<div class="col-md-12">
							<span class="" style="font-size: 20px; color: #708090;"><b>成员管理</b></span>
							
								
							<button href="#k" id="addcustorm" data-toggle="collapse" type="button" class="btn btn-primary" style="float: right;">添加客户</button>
						</div>
					</div>
				</div>
				<br />
				<div class="row">
					<div class="col-md-12">
						<!-- BASIC TABLE -->
						<div class="panel">


							<div class="panel-body">
								<table class="table" id="edittable">
									<thead>
										<tr>
											<th>#</th>
											<th>姓名</th>
											<th>电话</th>
											<th>邮箱</th>
											<th>权限</th>
											<th>工号</th>
											<th>最大接单数</th>
											<th>完成单数</th>
											<th>待部门反馈</th>
											<th>待用户反馈</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${sclist }" var="s">
											<tr>
												<td>${s.userId }</td>
												<td>${s.userRealname }</td>
												<td>${s.userPhone }</td>
												<td>${s.userEmail }</td>
												<td value="${s.userPriviliage }">${s.userPriviliage==1?'管理员':(s.userPriviliage==2?'组长':(s.userPriviliage==3?'客服':'客户'))}</td>
												<td>${s.userMember }</td>
												<td>${s.userMax }</td>
												<td>${s.userComplete }</td>
												<td>${s.userDepartfb }</td>
												<td>${s.userUserfb }</td>
												<td><span class="label label-primary" id="editcustorm" style="cursor: pointer">编辑</span> &nbsp; <span class="label label-default">重置</span></td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
						<!-- END BASIC TABLE -->
					</div>

				</div>
				<div class="row">
					<div class="col-md-6" id="edit">
						<!-- RECENT PURCHASES -->
						<div class="panel">
							<div class="panel-heading">
								<h3 class="panel-title" style="font-size: 18px; color: #708090;">
									<b>编辑</b>
								</h3>
								<div class="right">
									<button type="button" class="btn-toggle-collapse">
										<i class="lnr lnr-chevron-up"></i>
									</button>
									<button type="button" class="btn-remove">
										<i class="lnr lnr-cross"></i>
									</button>
								</div>
							</div>
							<div class="panel-body">
								<form id="editform" method="post" action="tUserAction_updateTwo">
									<input type="hidden" name="user.userId"> 
									<label>姓名:</label> <input type="text" class="form-control" name="user.userRealname" placeholder="姓名"> <br> 
									<label>电话:</label> 
									<input type="text" class="form-control" name="user.userPhone" placeholder="电话"> <br> 
									<label>邮箱:</label> 
									<input type="email" class="form-control" name="user.userEmail" placeholder="邮箱"><br>
									<label>权限:</label> <select class="form-control" name="user.userPriviliage">
										<option value="4">客户</option>
										<option value="3">客服</option>
										<option value="2">组长</option>
										<option value="1">管理员</option>
									</select> <br /> 
									<button type="submit" class="btn btn-primary" style="float: right;" onclick="return confirm('是否修改');">确定</button>
								</form>
							</div>
						</div>
						<!-- END RECENT PURCHASES -->
					</div>

					<div class="col-md-6" id="add">
						<!-- RECENT PURCHASES -->
						<div class="panel" id="k">
							<div class="panel-heading">
								<h3 class="panel-title" style="font-size: 18px; color: #708090;">
									<b>客户添加</b>
								</h3>
								<div class="right">
									<button type="button" class="btn-toggle-collapse">
										<i class="lnr lnr-chevron-up"></i>
									</button>
									<button type="button" class="btn-remove">
										<i class="lnr lnr-cross"></i>
									</button>
								</div>
							</div>

							<form action="tUserAction_save" method="post">
							<div class="panel-body">
								<label>用户名:</label> 
								<input type="text" class="form-control" name="user.userName" placeholder="账号"> <br>
								<label>密码:</label> 
								<input type="text" class="form-control" name="user.userPassword" placeholder="密码"> <br> 
								<label>邮箱:</label> 
								<input type="email" class="form-control" name="user.userEmail" placeholder="邮箱"><br>
								<label>电话:</label> 
								<input type="text" class="form-control" name="user.userPhone" placeholder="电话"><br> 
								<label>姓名:</label> 
								<input type="text" class="form-control" name="user.userRealname" placeholder="姓名"><br>  
								<label>权限:</label>
								<select class="form-control" name="user.userPriviliage">
									<option value="4">客户</option>
									<option value="3">客服</option>
									<option value="2">组长</option>
									<option value="1">管理员</option>
								</select> 
								<br /> 
								<button type="submit" class="btn btn-primary" style="float: right;" onclick="return confirm('是否添加');">确定</button>
							</div>
						</div>
						</form>
						<!-- END RECENT PURCHASES -->
					</div>
				</div>

			</div>
		</div>
		<!-- END MAIN CONTENT -->
	</div>
	<!-- END MAIN -->
	<div class="clearfix"></div>
	<%@include file="footer.jsp"%>
	</div>
	<!-- END WRAPPER -->
	<!-- Javascript -->
	<script src="assets/vendor/jquery/jquery.min.js"></script>
	<script src="assets/vendor/bootstrap/js/bootstrap.min.js"></script>
	<script src="assets/vendor/jquery-slimscroll/jquery.slimscroll.min.js"></script>
	<script src="assets/scripts/klorofil-common.js"></script>
</body>
<script type="text/javascript">
	$("#edit").hide();
	$("#add").hide();

	$("#edittable").delegate("span", "click", function(){
			
			if($("#edit").is(':hidden')){
				$("#edit").show(1000, 'linear');
			}else{
				$("#edit").hide(1000, 'linear');
			}
	
			var tds = $(this).parent().siblings();
	
			var inputs = $("#editform input ").add("#editform select");
	
			for (var i = 0; i < inputs.length; i++) {
	
				if (i <= 3) {
					$(inputs[i]).val($(tds[i]).text());
				} else {
					$(inputs[i]).val($(tds[i]).attr("value"));
				}
			}
	});

	$("#addcustorm").click(function() {
		$("#add").fadeIn(1000, 'linear');

	})

	
</script>
</html>
