<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script>
<title>로그인 페이지</title>

<script type="text/javascript">
	$(document).ready(function() {
		$("#btnLogin").click(function previous(){
			var userId = $("#userId").val();
			var userPw = $("#userPw").val();
			if(userId == "") {
				alert("아이디를 입력하세요");
				$("#userId").focus();
				return;
			}
			if(userPw == "") {
				alert("비밀번호를 입력하세요");
				$("#userPw").focus();
				return;
			}
			document.form.action="${pageContext.request.contextPath}/loginCheck.do"
			document.form.submit();
		});
	});
</script>
</head>
<body text-align="center">
<%@ include file = "menu.jsp" %>
	<form name="form" method="post">
	
		<table border="0" width="400px" style= "margin:auto;">
			<h2 style="text-align:center">로그인</h2>
			<tr>
				<td>아이디</td>
				<td><input name="userId" id="userId"></td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td><input type="password" name="userPw" id="userPw"></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<button type="button" id="btnLogin">로그인</button>
					<button type="button" onclick="location.href='${pageContext.request.contextPath}/memberWrite.do'">회원가입</button>
					<c:if test="${msg == 'failure'}">
						<div style="color: red">아이디 또는 비밀번호가 일치하지 않습니다.</div>
					</c:if> 
					<c:if test="${msg == 'logout'}">
						<div style="color: red">로그아웃되었습니다.</div>
					</c:if>
					
				</td>
			</tr>
		</table>
	</form>
</body>
</html>