<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" lang="ko" xml:lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>MAIN</title>
<!-- bootstrap css -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/bootstrap.min.css">
</head>
<!-- pagination link -->
<script type="text/javascript" language="javascript" defer="defer">
	function fn_egov_link_page(pageNo) {
		document.listForm.pageIndex.value = pageNo;
		document.listForm.action = "<c:url value='/testList.do'/>";
		document.listForm.submit();
	}
	/* 글 목록 화면 function */
	function fn_egov_selectList() {
		document.listForm.action = "<c:url value='/testList.do'/>";
		document.listForm.submit();
	}
	function fn_egov_excel() {
		document.listForm.action = "<c:url value= '/excelDownload.do'/>"
		document.listForm.submit();
	}
</script>
<body
	style="text-align: center; margin: 0 auto; display: inline; padding-top: 100px;">
	<!-- nav bar -->
	<nav class="navbar" style="background-color: #d6e6f5;">
		<div class="container-fluid">
			<a class="navbar-brand">Main Board</a>
		</div>
	</nav>
	<form:form commandName="searchVO" id="listForm" name="listForm" method="post">
		<div class="container">
			<div id="table" margin="auto">
				<br><br>
				<!-- 게시글 리스트 테이블 -->
				<table width="100%" border="1" cellpadding="0" cellspacing="0" class = "table table-hover">
					<colgroup>
						<col width="10%" />
						<col width="50%" />
						<col width="20%" />
						<col width="20%" />
					</colgroup>
					<tr>
						<th align="center">No</th>
						<th align="center">TITLE</th>
						<th align="center">WRITER</th>
						<th align="center">DATE</th>
					</tr>
					<c:forEach var="result" items="${resultList}" varStatus="status">
						<tr>
							<td align="center" class="listtd"><c:out
									value="${result.rnum}" />&nbsp;</td>
							<td align="center" class="listtd"><a
								href="testListDetail.do?code=<c:out value="${result.code}"/>"><c:out
										value="${result.title}" />&nbsp;</a></td>
							<td align="center" class="listtd"><c:out
									value="${result.writer}" />&nbsp;</td>
							<td align="center" class="listtd"><c:out
									value="${result.regDate}" />&nbsp;</td>
						</tr>
					</c:forEach>
				</table>
				<br> 
				<!-- 글 작성 버튼 -->
				<a href="testListInsert.do" type="button" class="btn btn-sm btn-primary" style="float: right">글쓰기</a>
				<!-- excel -->
				<a href="javascript:fn_egov_excel();" type="button" class="btn btn-sm btn-info" id="excelDownload" style="float: right">EXCEL</a> <br>
			</div>
			<!-- searching -->
			<div id="search">
				<label for="searchCondition" style="visibility: hidden;"> 
				<spring:message code="search.choose" /></label>
				<form:select path="searchCondition" cssClass="use">
					<form:option value="writer" label="작성자" />
					<form:option value="idx" label="아이디" />
					<form:option value="title" label="제목" />
				</form:select>
				<label for="searchKeyword" style="visibility: hidden; display: none;">
					<spring:message code="search.keyword" />
				</label>
				<form:input path="searchKeyword" cssClass="txt" />
				<span> <a href="javascript:fn_egov_selectList();" class="btn btn-outline btn-primary"><spring:message code="button.search" /></a>
	
				</span>
			</div>
			<!-- paging -->
			<div id="paging">
				<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_egov_link_page" />
				<form:hidden path="pageIndex" />
			</div>
		</div>
	</form:form>
</body>
</html>