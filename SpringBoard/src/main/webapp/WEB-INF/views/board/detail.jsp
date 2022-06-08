<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="container">
			<h1> 게시판 디테일 페이지입니다. </h1>
			<hr/>
		<div class="row">
	제목 : ${board.title }<br/>
	작성자 : ${board.writer}<br/>
	내용 : <br/>
	<textarea>${board.content }</textarea> <br/>
	수정날짜 :${board.updateDate }<br/>
		</div>
	</div>
	<hr/>
	<form action="/board/delete" method="post">
		<input type="hidden" name="bno" value="${board.bno }">
		<input type="submit" value="삭제하기">
	</form>
	<form action="/board/updateForm" method="post">
		<input type="hidden" name="bno" value="${board.bno }">
		<input type="submit" value="수정하기">
	</form>
	<form action="/board/list" method="get">
		<input type="submit" value="목록으로">
	</form>
</body>
</html>