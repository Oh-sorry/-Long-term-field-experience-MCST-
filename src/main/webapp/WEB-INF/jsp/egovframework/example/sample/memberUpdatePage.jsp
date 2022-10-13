<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script>
<title>회원정보 수정 페이지</title>
<script>
	$(document).ready(function() {
		$("#btnUpdate").click(function() {
			if(confirm("수정하시겠습니까?")) {
				document.form.action="${pageContext.request.contextPath}/memberUpdate.do";
				document.form.submit();
			}
		});
	});
	$(document).ready(function() {
		$("#btnDelete").click(function() {
			if(confirm("삭제하시겠습니까?")) {
				document.form.action="${pageContext.request.contextPath}/memberDelete.do";
				document.form.submit();
			}
		});
	});
</script>
</head>
<body>
<%@ include file = "menu.jsp" %>
	<form name="form" method="post">
		<table border="0" width="400px" style= "margin:auto;">
		<h2 style="text-align:center">회원 정보 수정</h2>
			<tr>
				<td>아이디</td>
				<td><input name="userId" value="${list.userId}" readonly="readonly"></td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td><input type="password" name="userPw" placeholder="비밀번호를 입력해주세요"></td>
			</tr>
			<tr>
				<td>이름</td>
				<td><input name="userName" value="${list.userName}"></td>
			</tr>
			<tr>
				<td>이메일</td>
				<td><input name="userEmail" value="${list.userEmail}"></td>
			</tr>
			<tr>
				<td>가입 일자</td>
				<td><fmt:formatDate value="${list.userRegdate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<button type="button" id="btnUpdate">수정</button>
					<button type="button" id="btnDelete">삭제</button>
					<div style="color : red">${message}</div>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>