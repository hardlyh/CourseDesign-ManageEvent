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
						<div class="col-md-12" >
							<span class="" style="font-size: 20px;color:#708090;"><b>聊天框</b></span>
							
						</div>
				    </div>
				 
				    <br/>
					<div class="row">
						<div class="col-md-10" style="margin-left: 10%">
							<!-- BASIC TABLE -->
							<div class="panel">
								<div class="panel-body" >
									<hr></hr>
								
									<div style="width:100%;height: 300px;border:1px solid ;overflow-y:scroll;" >
										<div style="margin-top: 10px;margin-left: 10px; width: 90%;">
										<c:forEach items="${talk2.records }" var="t">
												${t.recordForm }&nbsp;&nbsp;&nbsp;&nbsp; ${t.recordTime }<br>
												&nbsp;&nbsp; ${t.recordContent }<br>
										 </c:forEach>
										 
										 <span id="mark"></span>
										</div>
										
									 </div>	
									<br/><br/>
							<c:if test="${admin.userPriviliage>2 }">
									<form id="chatForm" >
										<input type="hidden" value="${admin.userRealname }" name="fromName" id="fromName">
										<input type="hidden" name="id" value="${talk2.talkId }">
										<textarea name="information" style="width:100%;height: 100px;"></textarea>
									</form>
								</div>
							</div>
							
								<div class="panel" style="margin-left: 60%;">
										<button type="button" class="btn btn-info" style="margin-left: 5%;" id="eventOk" >事件完成</button>
										<button type="button" class="btn btn-success" style="margin-left: 5%;" id="feedBack" >添加反馈</button>
										<button type="button" class="btn btn-primary" style="margin-left: 5%;" id="send">发送</button>
								</div>
							</c:if>
							<!-- END BASIC TABLE -->
						</div>
						
					</div>
					
					
				</div>
			</div>
			<!-- END MAIN CONTENT -->
		</div>
		<!-- END MAIN -->
		<input type="hidden" value="${id }" id="eventId">
		<div class="clearfix"></div>
		<%@include file="footer.jsp" %>
	</div>
	<!-- END WRAPPER -->
	<!-- Javascript -->
	<script src="assets/vendor/jquery/jquery.min.js"></script>
	<script src="assets/vendor/bootstrap/js/bootstrap.min.js"></script>
	<script src="assets/vendor/jquery-slimscroll/jquery.slimscroll.min.js"></script>
	<script src="assets/scripts/klorofil-common.js"></script>

	<script type="text/javascript">
		
		$("#send").click(function(){
			var str=$("textarea[name='information']").val();
			var name=$("#fromName").val();
			$("#mark").before(name+"&nbsp;&nbsp;&nbsp;&nbsp;"+new Date().toLocaleString()+"<br>"+"&nbsp;&nbsp;"+str+"<br>");
			$.post("talkAction_saveInfor",$("#chatForm").serialize());
			$("textarea[name='information']").val("");

		});


		$("#eventOk").click(function(){
			if(confirm("是否确认事件完成")){
				$.post("tEventAction_achiveEvent",{id:$("#eventId").val()},function(data){
					alert(data);
				});
			}
		})

		$("#feedBack").click(function(){
			if(confirm("是否添加反馈")){
				$.post("tEventAction_feedBack",{id:$("#eventId").val()},function(data){
					alert(data);
				});
			}
		})	



	</script>
</body>

</html>
