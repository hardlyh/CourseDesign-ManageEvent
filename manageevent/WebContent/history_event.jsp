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
						<div class="col-md-12" >
							<span class="" style="font-size: 20px;color:#708090;"><b>总历史事件</b></span>
							
						</div>
				    </div>
				   <div class="row">
				   	  <form>
						<div class="col-md-12" >
							<br/>
							<form action="hisAction_getHtEvent" method="post">
							<p style="font-size: 15px;color:#708090;" >
								开始时间: <input type="date" name="startTime" style="width: 15%;height: 30px;">
								&nbsp;结束时间: <input type="date" name="endTime" style="width: 15%;height: 30px;">
								<input type="text" name="title" placeholder="标题" style="width: 150px;height: 30px;margin-left: 10px;">
								<button type="submit" class="btn btn-primary" style="height: 30px;">确定</button>
							</p>
							</form>
						</div>
					  </form>
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
											<col style="width: 15%">
											<col style="width: 30%;">
											<col style="width: 10%">
											<col style="width: 10%">
											<col style="width: 11%">
											<col style="width: 12%">
											<col style="width: 9%">
										</colgroup>
										<thead>
											<tr>
												<th>#</th>
												<th>标题</th>
												<th>内容</th>
												<th>处理人</th>
												<th>处理人工号</th>
												<th>发布时间</th>
												<th>结束时间</th>
												<th>操作</th>
											</tr>
										</thead>
										<tbody>
										<c:forEach items="${pageBean.list }" var="pb" varStatus="status">
											<tr>
												<td>${status.count }</td>
												<td>${pb.eventTitle }</td>
												<td>${pb.eventContent }</td>
												<td>${pb.TUserByKefuId.userRealname }</td>
												<td>${pb.TUserByKefuId.userMember  }</td>
												<td>${pb.eventStarttime }</td>
												<td>${pb.eventEndtime }</td>
												<td>
												 	<span class="label label-primary tupian" style="cursor: pointer">图片</span>
												</td>
												<input type="hidden" id="urls" value="${pb.eventUrl }">
											</tr>
										</c:forEach>
										</tbody>
									</table>
										<!-- 分页 -->
									<ul class="pagination" id="paging">
										<li><a id="1"  class="lnr lnr-chevron-left-circle"
											href="${targetAction }?tag=1&startPage=${pageBean.startPage}&currentPage=${pageBean.currentPage}&title=${condition}&startTime=${startTime}&endTime=${endTime}&kefuName=${kefuName}"></a></li>
										<c:forEach begin="${pageBean.startPage }" end="${pageBean.endPage }"
											varStatus="s">
											<li class="${s.index==pageBean.currentPage?'active':'' }"><a id="0" 
												href="${targetAction }?tag=0&currentPage=${s.index}&startPage=${pageBean.startPage}&title=${condition}&startTime=${startTime}&endTime=${endTime}&kefuName=${kefuName}">${s.index }</a></li>
										</c:forEach>
									
										<li><a id="2" class="lnr lnr-chevron-right-circle"
											href="${targetAction }?tag=2&startPage=${pageBean.startPage}&currentPage=${pageBean.currentPage}&title=${condition}&startTime=${startTime}&endTime=${endTime}&kefuName=${kefuName}"></a></li>
										<li class="disabled"><a>共${pageBean.totalPage }页</a></li>
									</ul>
									<!-- end 分页 -->
								</div>
							</div>
							<!-- END BASIC TABLE -->
						</div>
						
					</div>
					<div class="row">
						<div class="col-md-8" id="imagelist" >
							<!-- RECENT PURCHASES -->
							<div class="panel" style="margin-left: 3s0%;">
								<div class="panel-heading">
									<h3 class="panel-title" style="font-size: 18px;color:#708090;"><b>查看图片</b></h3>
									<div class="right">
										<button type="button" class="btn-toggle-collapse"><i class="lnr lnr-chevron-up"></i></button>
										<button type="button" class="btn-remove"><i class="lnr lnr-cross"></i></button>
									</div>
								</div>
								
		
								<div class="panel-body">
									<ul class="list-unstyled row" id="imags">
										<li  style="margin-left: 10%;" class="image">
											 <img src="" alt="提示" style="max-width: 557px;  ">
										</li>
										<br>
										
										<li id="bound"></li>
									</ul>
									
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
	$("#lishia").attr("class","active");
		$("#imagelist").hide();

	
		$(".tupian").click(function() {
			
		    if ($("#imagelist").is(':hidden')) {
		    	 $(".image").show();
		        $("#imagelist").show(1000, 'linear');
		        var input=$(this).parent().siblings(" :input");
			   var urls=$(input).val();
			   var url=urls.split(",");
			  
			   
			   for(var i=0;i<url.length;i++){
				   if(url[i]!=''){
			   			$("#bound").before($(".image").clone().attr("class","clone2"));
				   }
			   }
			   
			   $(".image").hide();
			   
		    } else {
		    	
		    	
		    	$(".clone2").each(function(){
		    		$(this).remove();
		    	});
		        $("#imagelist").hide(1000, 'linear');
		      
		    }
		   
		  var imags=$("#imags .clone2 img");
		  
		   for(var i=0;i<imags.length;i++){
			    $(imags[i]).attr("src","./images/"+url[i]);
		   }              
		
		});
	</script>
	
</body>

</html>
