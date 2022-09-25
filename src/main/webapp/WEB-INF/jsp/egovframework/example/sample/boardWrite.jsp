<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>파일 업로드 테스트</title>
</head>
<body>

<!-- 
/uploadTest/uploadFormAction 
=> 컨트롤러에 있는 메소드로 요청  
-->
<form action="${pageContext.request.contextPath}/boardWrite.do" method="post" enctype="multipart/form-data">
	<input type="file" name="uploadFile" multiple />
	<input class="btn btn-primary btn-sm" type="submit" value="업로드"/>
</form>

</body>
</html>