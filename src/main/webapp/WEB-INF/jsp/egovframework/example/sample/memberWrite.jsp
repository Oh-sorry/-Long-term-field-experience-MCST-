<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>회원등록 페이지</title>
</head>
<body>
	
	<form name="form" method="post" action="${pageContext.request.contextPath}/memberInsert.do">
		<table border="0" width="400px" style="margin:auto;">
			<h2 style="text-align:center">회원 등록</h2>
			<tr>
				<td>아이디</td>
				<td><input name="userId"></td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td><input type="password" name="userPw"></td>
			</tr>
			<tr>
				<td>이름</td>
				<td><input name="userName"></td>
			</tr>
			<tr>
				<td>이메일</td>
				<td><input name="userEmail"></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="submit" value="확인">
					<input type="reset" value="취소">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>