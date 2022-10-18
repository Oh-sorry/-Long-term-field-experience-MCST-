<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- include libraries(jQuery, bootstrap) -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script>
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script>
<!-- include summernote css/js-->
<link href="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.11/summernote-bs4.css" rel="stylesheet">
<script src="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.11/summernote-bs4.js"></script>

<c:set var="registerFlag" value="${0 eq searchVO.code || empty searchVO.code ? 'create' : 'modify'}"/>
    <title>글  <c:if test="${registerFlag == 'create'}"><spring:message code="button.create" /></c:if>
			<c:if test="${registerFlag == 'modify'}"><spring:message code="button.modify" /></c:if>
    </title>
<script>
	$(document).ready(function() {
		$('#summernote').summernote({
			placeholder : 'content',
			minHeight : 370,
			maxHeight : null,
			focus : true,
			lang : 'ko-KR'
		});
	});
</script>
</head>
${searchVO.code}
${registerFlag}
<body style="text-align: auto; margin: 0 auto; display: inline; padding-top: 100px;">

	<!-- nav bar -->
	<nav class="navbar" style="background-color: #d6e6f5;">
		<div class="container-fluid"> 글 
 			<c:if test="${registerFlag == 'create'}"><spring:message code="button.create" /></c:if>
            <c:if test="${registerFlag == 'modify'}"><spring:message code="button.modify" /></c:if>
		</div>
	</nav>
	<br>
	
	<c:if test="${registerFlag == 'create'}">
	<article>
		<div class="container" role="main">
			<!-- 게시글 입력-->
			<form target="1" name="form" id="form" role="form" method="post" action="${pageContext.request.contextPath}/insertTest.do" encType = "multipart/form-data">
				<div class="form-group">
					<label for="title">제목</label> 
					<input type="text" class="form-control" name="title" id="title" placeholder="제목을 입력해 주세요">
				</div>
				<div class="form-group">
					<label for="writer">작성자</label>
					<input type="text" class="form-control" name="writer" id="writer" placeholder="이름을 입력해 주세요">
				</div>
				<div class="form-group">
					<label for="content">내용</label>
					<textarea id="summernote" rows="5" name="content" id="content" placeholder="내용을 입력해 주세요"></textarea>
				</div>
				<div class="form-group">
					<label for="idx">아이디</label>
					<input type="text" class="form-control" name="idx" id="idx" placeholder="ID를 입력해주세요">
				</div>
				<!-- 파일 입력 -->
				<div class="form-group" id="file-list">
					<label for="fileUpload">파일 첨부</label>
					<a href="#this" onclick="addFile()" class='btn btn-sm btn-primary' style="float: right">파일 추가</a>
					<c:if test="${orgFileName ne null}">
						<div class="form-group" id="file-list">
							<input type="file" name="file"><a href='#this' class='btn btn-sm btn-danger' name='file-delete'>삭제</a> 
						</div>
					</c:if>
				</div>
			</form>
			<div style="float: right">
				<button type="button" class="btn btn-sm btn-primary" id="btnSave">저장</button>
				<button type="button" class="btn btn-sm btn-primary" id="btnList">목록</button>
			</div>
		</div>
	</article>
	</c:if>
	<c:if test="${registerFlag == 'modify'}">
		<!-- 게시글 업데이트 -->
		<article>
			<div class="container" role="main">
				<form name="form" id="form" role="form" method="post" encType="multipart/form-data">
					<div class="form-group">
						<label for="title">제목</label>
						<input type="text" class="form-control" name="title" id="title" value="${title}">
					</div>
					<div class="form-group">
						<label for="writer">작성자</label>
						<input type="text" class="form-control" name="writer" id="writer" value="${writer}">
					</div>
					<div class="form-group">
						<label for="content">내용</label>
						<textarea id="summernote" rows="5" name="content" id="content">${content}</textarea>
					</div>
					<div class="form-group">
						<label for="idx"></label>
						<input type="text" class="form-control" name="idx" id="idx" value="${idx}" readonly>
					</div>
					<!-- 파일 입력 -->
					<div class="form-group" id="file-list">
						<label for="fileUpload">파일 첨부</label>
						<a href="#this" onclick="addFile()" class='btn btn-sm btn-primary' style="float: right">파일 추가</a> 
						<c:forEach items="${orgFileName}" var="result" varStatus="status">
							<div class="form-group" id="file-list">
								<input type="text" name="orgFileName" id="orgFileName" value='<c:out value="${result.orgFileName}"></c:out>' class="form-control" readonly="readonly"/>
								<a href='deleteFile.do?fileId=${result.fileId}&boardIdx=${result.boardIdx}' class='btn btn-sm btn-danger' id='file-delete' name='file-delete1' style="float: right">삭제</a>
							</div>
						</c:forEach>
					</div>
					<div class="form-group">
						<label for="idx">등록일</label> 
						<input type="text" class="form-control" name="regDate" id="regDate" value='<fmt:formatDate pattern="yyyy-MM-dd" value="${regDate}"/>' readonly>
					</div>
					<div class="form-group">
						<input type="hidden" class="form-control" name="code" id="code" value="${code}">
						<input type="hidden" class="form-control" name="fileId" id="fileId" value="${fileId}">
						<input type="hidden" class="form-control" name="saveFileName" id="saveFileName" value="${saveFileName}">
					</div>
					<div style="float: right">
						<button type="button" class="btn btn-sm btn-warning" id="btnUpdate">수정</button>
						<button type="button" class="btn btn-sm btn-info" id="btnList">목록</button>
						<button type="button" class="btn btn-sm btn-danger" id="btnDelete">삭제</button>
					</div>
				</form>
				<br>
			</div>
		</article>			
	</c:if>
</body>

<c:if test="${registerFlag == 'create'}">
	<script type="text/javascript">
		/* 다중 파일 */
		$(document).ready(function() {
			$("a[name='file-delete']").on("click", function(e) {
				e.preventDefault();
				deleteFile($(this));
			});
		})
		function addFile() {
			var str = "<div class='form-group' id='file-list'><input type='file' name = 'file'><a href='#this' class='btn btn-sm btn-danger' name = 'file-delete'>삭제</a></div>";
			$("#file-list").append(str);
			$("a[name='file-delete']").on("click", function(e) {
				e.preventDefault();
				deleteFile($(this));
			});
		}
		function deleteFile(obj) {
			obj.parent().remove();
		}
		//이전 클릭 시 testList로 이동
		$("#btnList").click(function previous() {
			$(location).attr('href', 'testList.do');
		});
		//글쓰기
		$(document).on('click', '#btnSave', function(e) {
			$("#form").submit();
		});
	</script>
</c:if>
<c:if test="${registerFlag == 'modify'}">
	<script type="text/javascript">
		/* 다중 파일 */
		$(document).ready(function() {
			$("a[name='file-delete']").on("click", function(e) {
				e.preventDefault();
				deleteFile($(this));
			});
			$("a[id='file-delete']").click(function previous() {
				alert("선택한 파일을 삭제합니다.")
				$(location).attr('href', 'deleteFile.do?fileId=${fileId}&boardIdx=${result.boardIdx}');
			});
		})
		function addFile() {
			var str = "<div class='form-group' id='file-list'><input type='file' name = 'file'><a href='#this' class='btn btn-sm btn-danger' id='file-delete1' name = 'file-delete'>삭제</a></div>";
			$("#file-list").append(str);
			$("a[name='file-delete']").on("click", function(e) {
				e.preventDefault();
				deleteFile($(this));
			});
		}
		function deleteFile(obj) {
			obj.parent().remove();
		}
		$(document)
				.ready(
						function() {
							$("#btnUpdate")
									.click(
											function() {
												document.form.action = "${pageContext.request.contextPath}/updateTest.do"
												alert("수정하시겠습니까?")
												document.form.submit();
											});
							$("#btnList").click(function previous() {
								$(location).attr('href', 'testList.do');
							});
							$("#btnDelete")
									.click(
											function previous() {
												alert("( TITLE : ${title} )인 글을 삭제합니다.")
												$(location)
														.attr('href',
																'deleteTest.do?code=${code}&saveFileName=${result.saveFileName}');
											});
						});
	</script>
</c:if>
</html>