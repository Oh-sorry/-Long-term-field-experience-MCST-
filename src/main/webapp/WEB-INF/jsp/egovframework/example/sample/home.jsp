<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- include libraries(jQuery, bootstrap) -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/bootstrap.min.css">
<script
	src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script>
<script
	src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script>
<title>login</title>
</head>
<a href="/board/list">�Խ���</a>
<br />
<script type="text/javascript">

</script>
<body>
	<h2>�α���</h2>
	<form name="form1" method="post">    
		<table width="400px">
			<tr>
				<td>���̵�</td>
				<td><input name="userId" id="userId"></td>
			</tr>
			<tr>
				<td>��й�ȣ</td>
				<td><input type="password" name="userPw" id="userPw"></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<button type="button" id="btnLogin">�α���</button> 
					<c:if test="${msg == 'failure'}">
						<div style="color: red">���̵� �Ǵ� ��й�ȣ�� ��ġ���� �ʽ��ϴ�.</div>
					</c:if> 
					<c:if test="${msg == 'logout'}">
						<div style="color: red">�α׾ƿ� �Ǿ����ϴ�.</div>
					</c:if>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>