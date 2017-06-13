<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<form id="ajaxform" action="tUserAction_updateTwo" method="post">
		<table align="center">
			<input type="hidden" name="user.userId" value="${user.userId }">
			<tr>
				<td>姓名:<input type="text" name="user.userRealname" value="${user.userRealname }"></td>
			</tr>
			<tr>
				<td>工号:<input type="text" name="user.TKefus.kefuNumber" value="${user.TKefus.kefuNumber }"></td>
			</tr>
			<tr>
				<td>邮箱:<input type="text" name="user.userEmail" value="${user.userEmail }"></td>
			</tr>
			<tr>
				<td>手机号:<input type="text" name="user.userPhone" value="${user.userPhone }"></td>
			</tr>
			<tr>
				<td>权限:
					<select name="user.userPrviliage">
						<option value="1"></option>
						<option value="2"></option>
						<option value="3"></option>
						<option value="4"></option>
					</select>
				</td>
			</tr>
			<tr>
				<td>当前状态:<input type="text"></td>
			</tr>

			<input type="submit" value="提交" id="add">
		</table>
	</form>

</body>
</html>