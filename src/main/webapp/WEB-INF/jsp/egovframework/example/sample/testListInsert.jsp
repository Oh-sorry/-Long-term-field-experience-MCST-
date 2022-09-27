<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

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
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/bootstrap.min.css">
<script
	src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script>
<script
	src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script>
<!-- include summernote css/js-->
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.11/summernote-bs4.css"
	rel="stylesheet">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.11/summernote-bs4.js"></script>
<!-- include summernote-ko-KR -->
<script src="/resources/js/summernote-ko-KR.js"></script>
<title>글쓰기</title>

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
<body
	style="text-align: auto; margin: 0 auto; display: inline; padding-top: 100px;">
	<!-- nav bar -->
	<nav class="navbar" style="background-color: #d6e6f5;">
		<div class="container-fluid">
			<a class="navbar-brand">글 쓰기</a>
			<form class="d-flex" role="search">
				<input class="form-control me-2" type="search" placeholder="Search"
					aria-label="Search">
				<button class="btn btn-outline-success" type="submit">Search</button>
			</form>
		</div>
	</nav>
	<br>

	<article>
		<div class="container" role="main">
			<!-- 게시글 입력-->
			<form target="1" name="form" id="form" role="form" method="post"
				action="${pageContext.request.contextPath}/insertTest.do" encType = "multipart/form-data">
				<div class="form-group">
					<label for="title">제목</label> <input type="text"
						class="form-control" name="title" id="title"
						placeholder="제목을 입력해 주세요">
				</div>
				<div class="form-group">
					<label for="writer">작성자</label> <input type="text"
						class="form-control" name="writer" id="writer"
						placeholder="이름을 입력해 주세요">
				</div>
				<div class="form-group">
					<label for="content">내용</label>
					<textarea id="summernote" rows="5" name="content" id="content"
						placeholder="내용을 입력해 주세요"></textarea>
				</div>
				<div class="form-group">
					<label for="idx">아이디</label> <input type="text"
						class="form-control" name="idx" id="idx" placeholder="ID를 입력해주세요">
				</div>
				<!-- 파일 입력 -->
				<div class="form-group" id="file-list">
					<label for="fileUpload">파일 첨부</label>
					<!-- <input type="file" class="form-control" name="uploadFile" size ="70" multiple> -->
					<a href="#this" onclick="addFile()" class='btn btn-sm btn-primary' style="float: right">파일 추가</a> 
					<div class="form-group" id="file-list">
						<input type="file" name="file"><a href='#this' class='btn btn-sm btn-danger' name='file-delete'>삭제</a> 
					</div>
				</div>
			</form>
			<div style="float: right">
				<!-- <input type="button" class="btn btn-sm btn-primary" onclick="TwoSubmit()" value="저장"> -->
				<button type="button" class="btn btn-sm btn-primary" id="btnSave">저장</button>
				<button type="button" class="btn btn-sm btn-primary" id="btnList">목록</button>
				
				
			</div>
		</div>
	</article>

</body>

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

</html>