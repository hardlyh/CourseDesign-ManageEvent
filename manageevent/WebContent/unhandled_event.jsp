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
							<span class="" style="font-size: 20px;color:#708090;"><b>分配未处理事件</b></span>
							
						</div>
				    </div>
				   <div class="row">
				   	  <form method="post" action="tEventAction_getUnEvent">
						<div class="col-md-12" >
							<br/>
							<p style="font-size: 15px;color:#708090;" >
								开始时间: <input type="date" name="startTime" style="width: 15%;height: 30px;">
								&nbsp;结束时间: <input name="endTime" type="date" style="width: 15%;height: 30px;">
								&nbsp;&nbsp;
								状态 : &nbsp;&nbsp;
								<select name="status" >
									<option value="0" ${status=='0'?'selected':'' }>未分配</option>
									<option value="1" ${status=='1'?'selected':'' }>已分配</option>
								</select>
								<input type="text" name="condition" placeholder="关键词" style="height: 30px;width: 15%;margin-left: 15%;">
								<button type="submit" class="btn btn-primary" style="height: 30px;">确定</button>
							</p>
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
											<col style="width: 3%">
											<col style="width: 15%">
											<col style="width: 30%;">
											<col style="width: 15%">
											<col style="width: 10%">
											<col style="width: 10%">
											<col style="width: 17%">
										</colgroup>
										<thead>
											<tr>
												<th>#</th>
												<th>标题</th>
												<th>内容</th>
												<th>状态</th>
												<th>发布时间</th>
												<th>结束时间</th>
												<th>操作</th>
											</tr>
										</thead>
										<tbody>
										<c:forEach items="${pageBean.list }" var="ev" varStatus="status">
											<tr>
												<td id="u_id" value="${ev.eventId }">${status.count }</td>
												<td>${ev.eventTitle }</td>
												<td>${ev.eventContent }</td>
												<c:if test="${empty ev.TUserByKefuId }">
													<td>未分配</td>
												</c:if>
												<c:if test="${!empty ev.TUserByKefuId }">
													<td>${ev.TUserByKefuId.userRealname }正在处理</td>
												</c:if>
												
												<td>${ev.eventStarttime }</td>
												<td>${ev.eventEndtime }</td>
												<td>
													<span class="label label-primary tupian" style="cursor: pointer">图片</span>
                                                    <span class="label label-success distribution" style="cursor: pointer">分配</span>
                                                    <span class="label label-default" style="cursor: pointer" onclick="return confirm('是否删除')"><a href="tEventAction_deleteEvent2?id=${ev.eventId }" style="color: #FFFFFF">删除</a></span>
												</td>
												<input type="hidden" id="urls" value="${ev.eventUrl }">
											</tr>
											</c:forEach>
										</tbody>
									</table>
									
									<!-- 分页 -->
									<ul class="pagination" id="paging">
										<li><a id="1"  class="lnr lnr-chevron-left-circle"
											href="tEventAction_getUnEvent?tag=1&startPage=${pageBean.startPage}&currentPage=${pageBean.currentPage}&condition=${condition}&startTime=${startTime}&endTime=${endTime}&status=${status}"></a></li>
										<c:forEach begin="${pageBean.startPage }" end="${pageBean.endPage }"
											varStatus="s">
											<li class="${s.index==pageBean.currentPage?'active':'' }"><a id="0" 
												href="tEventAction_getUnEvent?tag=0&currentPage=${s.index}&startPage=${pageBean.startPage}&condition=${condition}&startTime=${startTime}&endTime=${endTime}&status=${status}">${s.index }</a></li>
										</c:forEach>
									
										<li><a id="2" class="lnr lnr-chevron-right-circle"
											href="tEventAction_getUnEvent	?tag=2&startPage=${pageBean.startPage}&currentPage=${pageBean.currentPage}&condition=${condition}&startTime=${startTime}&endTime=${endTime}&status=${status}"></a></li>
										<li class="disabled"><a>共${pageBean.totalPage }页</a></li>
									</ul>
									<!-- end 分页 -->
								</div>
							</div>
							<!-- END BASIC TABLE -->
						</div>
						
					</div>
				  <div class="row">
                        <div class="col-md-6" id="imagelist">
                            <!-- RECENT PURCHASES -->
                            <div class="panel">
                                <div class="panel-heading">
                                    <h3 class="panel-title" style="font-size: 18px;color:#708090;"><b>查看图片</b></h3>
                                    <div class="right">
                                        <button type="button" class="btn-toggle-collapse"><i class="lnr lnr-chevron-up"></i></button>
                                        <button type="button" class="btn-remove"><i class="lnr lnr-cross"></i></button>
                                    </div>
                                </div>
                                <div class="panel-body">
                                    <ul class="list-unstyled row" id="imags">
                                        <li style="margin-left: 10%;" class="image" >
                                            <img src="assets/img/user-medium.png" alt="提示" style="max-width: 350px; max-height: 170px; ">
                                        </li>

                                        <li id="bound""></li>
                                    </ul>
                                </div>
                            </div>
                            <!-- END RECENT PURCHASES -->
                        </div>
                        <div class="col-md-6" id="allot">
                            <!-- RECENT PURCHASES -->
                            <div class="panel">
                                <div class="panel-heading">
                                    <h3 class="panel-title" style="font-size: 18px;color:#708090;"><b>分配客服</b></h3>
                                    <div class="right">
                                        <button type="button" class="btn-toggle-collapse"><i class="lnr lnr-chevron-up"></i></button>
                                        <button type="button" class="btn-remove"><i class="lnr lnr-cross"></i></button>
                                    </div>
                                </div>
                                <div class="panel-body">
                                    <ul class="list-unstyled row">
                                    <form method="post" action="tEventAction_allotByEvent">
                                    	<input type="hidden" name="id">
	                                        <li style="margin-left: 10%;" id="kefu">
	                                            <label class="fancy-radio">
	                                                <input name="people" value="male" type="radio">
	                                                <span><i class="i"></i></span>
	                                            </label>
	                                        </li>
											
	                                       <li id="c_bottom"></li>
	                                    </ul>
	                                    <button type="submit" class="btn btn-primary" style="float: right;" onclick="return confirm('是否确定分配');">确定</button>
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
	
		$("#wcl").attr("class","active");
		$("#imagelist").hide();

		$("#allot").hide();
		

		$(".tupian").click(function() {
			
		    if ($("#imagelist").is(':hidden')) {
		    	 $(".image").show();
		        $("#imagelist").show(1000, 'linear');
		        var input=$(this).parent().siblings(" :input");
			   var urls=$(input).val();
			   var url=urls.split(",");
			  
			   
			   for(var i=0;i<url.length;i++){
				   if(url[i]!=''){
			   			$("#bound").before($(".image").clone().attr("class","clone2")).append("<br>");
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


		$(".distribution").click(function() {
			var value=$(this).parent().siblings("#u_id").attr("value");
			$("input[name='id']").val(value);
			if ($("#allot").is(':hidden')) {
				$("#allot").show(1000, 'linear');
			} else {
				$("#allot").hide(1000, 'linear');
			}

			var array;

			$.post("tUserAction_getKefu",function(data){
				array=eval("("+data+")");

				for(var i in array[0]){

				var li=$("#kefu").clone().attr("id","");
					$(li).find("input").val(i);
					$(li).find(".i").after(array[0][i]);
					$("#c_bottom").before(li).append("<br/>");
				}
				$("#kefu").hide();	

			});		
		});
    </script>
</body>

</html>
