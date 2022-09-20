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
			<!-- <h2>board Form</h2> -->
			<form name="form" id="form" role="form" method="post"
				action="${pageContext.request.contextPath}/insertTest.do">
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
				<div class="mb-3">
					<label for="formFileMultiple" class="form-label">파일 첨부</label>
					<input class="form-control" type="file" id="formFileMultiple" multiple>
				</div>
			</form>
			<div style="float: right">
				<button type="button" class="btn btn-sm btn-primary" id="btnSave">저장</button>
				<button type="button" class="btn btn-sm btn-primary" id="btnList">목록</button>
			</div>
		</div>
	</article>

</body>
<!-- <script>
	function go() {
		
		if (document.getElementById("title").value == "") {
			alert("제목을 입력하세요");
			document.form.title.focus();
			exit;
		}
		else if (document.getElementById("writer").value == "") {
			alert("작성자를 입력하세요");
			document.form.writer.focus();
			exit;
		}
		else if (document.getElementById("content").value == "") {
			alert("내용을 입력하세요");
			document.form.content.focus();
			exit;
		}
		else (document.getElementById("idx").value == "") {
			alert("아이디를 입력하세요");
			document.form.idx.focus();
			exit;
		}
		document.submit();
	}
</script> -->
<script type="text/javascript">
	    //글쓰기
	$(document).on('click', '#btnSave', function(e) {
		$("#form").submit();
	});

	//이전 클릭 시 testList로 이동
	$("#btnList").click(function previous() {
		$(location).attr('href', 'testList.do');

	});
</script>

</html>