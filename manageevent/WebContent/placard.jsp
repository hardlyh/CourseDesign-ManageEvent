<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	<link rel="stylesheet" href="assets/vendor/toastr/toastr.min.css">
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
		<%@include file="head.jsp" %>
		<!-- END LEFT SIDEBAR -->
		<!-- MAIN -->
		<div class="main">
			<!-- MAIN CONTENT -->
			<div class="main-content">
				<div class="container-fluid">
					<div class="row">
						<div class="col-md-12">
							<span class="" style="font-size: 20px;color:#708090;"><b>公告</b></span>
							<c:if test="${admin.userPriviliage<3 }">
							<button id="bu"  href="#k" data-toggle="collapse" type="button" class="btn btn-primary"  style="float: right;" >添加公告</button>
							</c:if>
						</div>
						</div>
				    </div>
				    <br/>
					<div class="row">
						<div class="col-md-12">
							<!-- BASIC TABLE -->
							<div class="panel">

								
								<div class="panel-body">
									<table class="table" >
										<colgroup>
											<col style="width: 5%">
											<col style="width: 20%">
											<col style="width: 40%;">
											<col style="width: 10%">
											<col style="width: 10%">
											<col style="width: 10%">
										</colgroup>
										<thead>
											<tr>
												<th>#</th>
												<th>标题</th>
												<th>内容</th>
												<th>公告logo</th>
												<th>发布时间</th>
												<th>操作</th>
											</tr>
										</thead>
										<tbody>
										<c:forEach items="${pageBean.list }" var="pb" varStatus="status">
											<tr>
												<td>${status.count }</td>
												<td>${pb.placardTitle }</td>
												<td>${pb.placardContent }</td>
												<td><img src="./images/${pb.placardUrl }" style="width: 50px;height: 50px;"></img></td>
												<td>${pb.placardTime }</td>
												<td>
													<c:if test="${admin.userPriviliage<3 }">
														<span class="label label-default">
														<a href="placardAction_deletePlacard?id=${pb.placardId }" 
														style="color:#ffffff;cursor: pointer;" onclick="return confirm('是否删除');">删除</a></span>
													</c:if>
												</td>
											</tr>
										</c:forEach>
										</tbody>
									</table>
									
										<!-- 分页 -->
									<ul class="pagination" id="paging">
										<li><a id="1"  class="lnr lnr-chevron-left-circle"
											href="placardAction_getPlacard?tag=1&startPage=${pageBean.startPage}&currentPage=${pageBean.currentPage}"></a></li>
										<c:forEach begin="${pageBean.startPage }" end="${pageBean.endPage }"
											varStatus="s">
											<li class="${s.index==pageBean.currentPage?'active':'' }"><a id="0" 
												href="placardAction_getPlacard?tag=0&currentPage=${s.index}&startPage=${pageBean.startPage}">${s.index }</a></li>
										</c:forEach>
									
										<li><a id="2" class="lnr lnr-chevron-right-circle"
											href="placardAction_getPlacard?tag=2&startPage=${pageBean.startPage}&currentPage=${pageBean.currentPage}"></a></li>
										<li class="disabled"><a>共${pageBean.totalPage }页</a></li>
									</ul>
									<!-- end 分页 -->
								</div>
							</div>
							<!-- END BASIC TABLE -->
						</div>
						
					</div>
					<div class="row">
						
						<div class="col-md-6" style="margin-left: 50px; " id="add">
							<!-- RECENT PURCHASES -->
							<div class="panel" id="k">
								<div class="panel-heading">
									<h3 class="panel-title" style="font-size: 18px;color:#708090;"><b>添加公告</b></h3>
									<div class="right">
										<button type="button" class="btn-toggle-collapse"><i class="lnr lnr-chevron-up"></i></button>
										<button type="button" class="btn-remove"><i class="lnr lnr-cross"></i></button>
									</div>
								</div>
								
		
								<div class="panel-body" >
								<form action="placardAction_savePlacard" method="post" enctype="multipart/form-data">
									<input type="text" name="title" class="form-control" placeholder="标题">
									<br>
									<textarea placeholder="内容" name="content" style="width: 100%;height: 150px;"></textarea>
									<br>
									<br>
									<input type="file" id="file" class="form-control" name="file"  >
									<br>
									<button type="submit" class="btn btn-primary" onclick="return confirm('是否添加公告')" style="float: right;">确定</button>
								</form>
								</div>
							</div>						
							<!-- END RECENT PURCHASES -->
						</div>								
					</div>
					
				</div>
			</div>
			<!-- END MAIN CONTENT -->
		</div>
		<!-- END MAIN -->
		<div class="clearfix"></div>
		<%@include file="footer.jsp" %>
	</div>
	<!-- END WRAPPER -->
	<!-- Javascript -->
	<script src="assets/vendor/jquery/jquery.min.js"></script>
	<script src="assets/vendor/bootstrap/js/bootstrap.min.js"></script>
	<script src="assets/vendor/jquery-slimscroll/jquery.slimscroll.min.js"></script>
	<script src="assets/scripts/klorofil-common.js"></script>
	<script src="assets/vendor/toastr/toastr.min.js"></script>
	<script type="text/javascript">
	$("#gonggao").attr("class","active");
		$("#add").hide();
		
		
		$("#bu").click(function(){
			if ($("#add").is(':hidden')) {
				$("#add").show(1000, 'linear');
			} else {
				$("#add").hide(1000, 'linear');
			}
		});
		
		
	
	</script>
</body>

</html>
