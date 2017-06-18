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
							<span class="" style="font-size: 20px;color:#708090;"><b>事件管理</b></span>
							<button  href="#k" data-toggle="collapse" type="button" class="btn btn-primary"  style="float: right;" id="addevent" >添加事件</button>
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
											<col style="width: 35%;">
											<col style="width: 10%">
											<col style="width: 10%">
											<col style="width: 8%">
											<col style="width: 12%">
										</colgroup>
										<thead>
											<tr>
												<th>#</th>
												<th>标题</th>
												<th>内容</th>
												<th>发布时间</th>
												<th>结束时间</th>
												<th>客服</th>
												<th>操作</th>
											</tr>
										</thead>
										<tbody>
										<c:forEach items="${user2.TEventsForEventCommitId }" var="commit" varStatus="status">
											<tr>
												<td>${status.count }</td>
												<td>${commit.eventTitle }</td>
												<td>${commit.eventContent }</td>
												<td>${commit.eventStarttime }</td>
												<td>${commit.eventEndtime }</td>
												<td>${commit.TUserByKefuId.userRealname  }</td>
												<td>
													<span class="label label-default"><a onclick="return confirm('是否删除');" href="tEventAction_deleteEvent?id=${commit.eventId }" style="color:#ffffff;cursor: pointer;" >删除</a></span>
													<c:if test="${!empty commit.TUserByKefuId  }">
													<span class="label label-default"><a  href="talkAction_getTalk?id=${commit.eventId }" style="color:#ffffff;cursor: pointer;" >聊天</a></span>
													</c:if>
												</td>
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
							<div class="col-md-6" style="margin-left: 50px; " id="allot">
								<!-- RECENT PURCHASES -->
								<div class="panel" id="k">
									<div class="panel-heading">
										<h3 class="panel-title" style="font-size: 18px;color:#708090;"><b>添加事件</b></h3>
										<div class="right">
											<button type="button" class="btn-toggle-collapse"><i class="lnr lnr-chevron-up"></i></button>
											<button type="button" class="btn-remove"><i class="lnr lnr-cross"></i></button>
										</div>
									</div>
									
										<form action="tEventAction_saveEvent" method="post" enctype="multipart/form-data">
										<div class="panel-body" id="forms" >									
											<input type="text" class="form-control" name="event.eventTitle" placeholder="标题" >
											<br>
											<textarea class="form-control" rows="10" name="event.eventContent" style="width: 100%" placeholder="请添加内容"></textarea>
											<br>
											<input type="file" id="file" class="form-control" name="files"  >
											<br>
											<button type="button" id="add" class="btn btn-default" style="margin-left: 80%"><i class="fa fa-plus-square"></i> 添加 </button>
											<br>
											<br>
											<button type="submit" class="btn btn-primary" style="float: right;" onclick="return confirm('是否添加');">确定</button>
										</div>
										</form>
									
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
		$("#sjgl").attr("class","active");
		$("#allot").hide();
		$("#addevent").click(function(){
			if ($("#allot").is(':hidden')) {
				$("#allot").show(1000, 'linear');
			} else {
				$("#allot").hide(1000, 'linear');
			}
			
		});
		
		
		$("#add").click(function(){
			$(this).before($("#file").clone());
			$(this).before("<br/>");
		})
	</script>
</body>

</html>
