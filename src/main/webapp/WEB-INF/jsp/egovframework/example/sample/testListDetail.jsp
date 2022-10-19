<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- include libraries(jQuery, bootstrap) -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script>
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script>
<link href="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.11/summernote-bs4.css" rel="stylesheet">
<script src="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.11/summernote-bs4.js"></script>
<title>글 상세 조회</title>
<script>
	$(document).ready(function() {
		$('#summernote').summernote({
			placeholder : 'content',
			minHeight : 300,
			maxHeight : null,
			focus : true,
			lang : 'ko-KR',
			toolbar : null
		});
		$('#summernote').summernote('disable');
	});
</script>
</head>
<body style="text-align: auto; margin: 0 auto; display: inline; padding-top: 200px;">
	<nav class="navbar" style="background-color: #d6e6f5;">
		<div class="container-fluid">
			<a class="navbar-brand">글 상세 조회</a>
		</div>
	</nav>
	<br>
	<!-- 게시글 상세 조회 -->
	<article>
		<div class="container" role="main">
			<form name="form" id="form" role="form" method="post" action="updateTest.do" encType="multipart/form-data" >
				<input type="hidden" id="pageIndex" name="pageIndex" value="${paginationInfo.currentPageNo}">
				<input type="hidden" id="searchCondition" name="searchCondition" value="${searchFormData.searchCondition}">
				<input type="hidden" id="searchKeyword" name="searchKeyword" value="${searchFormData.searchKeyword}">
				<div class="form-group">
					<label for="title">제목</label> <input type="text" class="form-control" name="title" id="title" value='<c:out value="${resultList.title}"></c:out>' readonly>
				</div>
				<div class="form-group">
					<label for="writer">작성자</label> <input type="text" class="form-control" name="writer" id="writer" value='<c:out value="${resultList.writer}"></c:out>' readonly>
				</div>
				<div class="form-group">
					<label for="content">내용</label>
					<textarea id="summernote" rows="5" name="content" id="content">${resultList.content}</textarea>
				</div>
				<div class="form-group">
					<label for="idx">아이디</label> <input type="text" class="form-control" name="idx" id="idx" value='<c:out value="${resultList.idx}"></c:out>' readonly>
				</div>
				<!-- 첨부 파일 시작 -->
				<div class="form-group">
					<label for="orgFileName">첨부 파일</label>
					<c:forEach items="${orgFileName}" var="result" varStatus="status">
						<c:if test="${result.saveFileName ne null}">
							<%-- <a href="fileDownload.do?saveFileName=${result.saveFileName}&code=${result.boardIdx}&orgFileName=${result.orgFileName}"> --%>
							<a href="fileDownload.do?fileId=${result.fileId}">
								<input type="text" name="orgFileName" id="orgFileName" value='<c:out value="${result.orgFileName}"></c:out>' class="form-control" readonly="readonly" />
							</a>
						</c:if>
					</c:forEach>
				</div>
				<!-- 첨부 파일 끝 -->
				<div class="form-group">
					<label for="idx">등록일</label> <input type="text" class="form-control" name="regDate" id="regDate" value='<fmt:formatDate pattern="yyyy-MM-dd" value="${resultList.regDate}"/>' readonly>
				</div>
				<div class="form-group">
					<label for="code"></label> <input type="hidden" class="form-control" name="code" id="code" value='<c:out value="${resultList.code}"></c:out>'>
				</div>
				<div style="float: right">
					<button type="button" class="btn btn-sm btn-primary" id="btnUpdate">수정</button>
					<button type="button" class="btn btn-sm btn-primary" id="btnList">목록</button>
				</div>
				<br>
				<!-- 댓글 시작 -->
				<hr />
				<div class="form-group" id="file-list">
					<div class="form-group" id="file-list">
						<label for="replyWriter">댓글 작성자</label><br> 
						<input type="text" name="replyWriter" id="replyWriter" placeholder="ID를 입력해주세요"><br>
						<textarea rows="3" cols="100" name="replyContent" id="replyContent"></textarea>
						<button type="button" style="float: right" class="btn btn-sm btn-primary" id="replyInsert">댓글 작성</button>
						<!-- <a href="replyInsert.do" type="button" class="btn btn-sm btn-primary" id="replyInsert">댓글 작성</a> -->
					</div>
					<c:forEach items="${reply}" var="reply" varStatus="status">
						<a>${reply.replyWriter} / <fmt:formatDate value="${reply.replyDate}" pattern="yyyy-MM-dd" />
						</a>
						<br>
						<a>${reply.replyContent}</a>
						<div style="float: right">
							<a href="replyDelete.do?rno=${reply.rno}&replyIdx=${reply.replyIdx}" type="button" class="btn btn-sm btn-danger" id="replyDelete">삭제</a>
						</div>
						<hr>
						
					</c:forEach>
				</div>
				<!-- 댓글 끝 -->
			</form>
			<br>
		</div>
	</article>
</body>
<script type="text/javascript">
	/* 수정 버튼 클릭 시 */
	$("#btnUpdate").click(function previous() {
		document.form.action = "${pageContext.request.contextPath}/testListInsert.do"
		document.form.submit();
	});
	/* 목록 버튼 클릭 시 */
	$("#btnList").click(function previous() {
		document.form.action = "${pageContext.request.contextPath}/testList.do"
		document.form.submit();
	});
	$("#replyInsert").click(function previous() {
		alert("댓글을 입력합니다.");
		document.form.action = "${pageContext.request.contextPath}/replyInsert.do"
		document.form.submit();
	});
	
	$("#replyDelete").click(function previous() {
		alert("선택한 댓글을 삭제합니다.")
	});

</script>
</html>