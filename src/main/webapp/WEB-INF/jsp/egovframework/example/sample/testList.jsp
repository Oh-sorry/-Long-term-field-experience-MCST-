<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" lang="ko" xml:lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>MAIN</title>

<!-- bootstrap css -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">


</head>
<!-- pagination link -->
<script type="text/javascript" language="javascript" defer="defer">
	function fn_egov_link_page(pageNo) {
		document.listForm.pageIndex.value = pageNo;
		document.listForm.action = "<c:url value='/testList.do'/>";
		document.listForm.submit();
	}
</script>


<body style="text-align: center; margin:0 auto; display: inline; padding-top: 100px;">
	<!-- nav bar -->
	<nav class="navbar" style="background-color: #d6e6f5;">
		<div class="container-fluid">
			<a class="navbar-brand" style = "bold">Main Board</a>
			<form class="d-flex" role="search">
				<input class="form-control me-2" type="search" placeholder="Search"
					aria-label="Search">
				<button class="btn btn-outline-success" type="submit">Search</button>
			</form>
		</div>
	</nav>

	<form:form commandName="searchVO" id="listForm" name="listForm" method="post">
		<div id="table" margin="auto">
			<br><br>
			<table width="100%" border="1" cellpadding="0" cellspacing="0">
				<colgroup>
					<col width="10%"/>
					<col width="50%"/>
					<col width="20%"/>
					<col width="20%"/>
				</colgroup>
				<tr>
					<th align="center">No</th>
					<th align="center">TITLE</th>
					<th align="center">WRITER</th>
					<th align="center">DATE</th>
				</tr>
				<c:forEach var="result" items="${resultList}" varStatus="status">
					<tr>
						<td align="center" class="listtd"><c:out value="${result.rnum}"/>&nbsp;</td>
						<td align="center" class="listtd"><a href="testListDetail.do?title=<c:out value="${result.title}"/>"><c:out value="${result.title}"/>&nbsp;</a></td>
						<td align="center" class="listtd"><c:out value="${result.writer}"/>&nbsp;</td>
						<td align="center" class="listtd"><c:out value="${result.regDate}"/>&nbsp;</td>
					</tr>
				</c:forEach>	
				
			</table>
			<br>
			<a href="testListInsert.do" type="button" class="btn btn-sm btn-primary"style="float: right">글쓰기</a>
			
		</div>
		<!-- paging -->
		<div id="paging">
			<ui:pagination paginationInfo = "${paginationInfo}" type="image" jsFunction="fn_egov_link_page" />
			<form:hidden path="pageIndex"/>
		</div>
		
	</form:form>
</body>
</html>