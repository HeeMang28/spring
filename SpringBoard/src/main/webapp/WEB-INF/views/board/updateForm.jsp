<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="/board/update" method="post">
	제목 : <input type="text" name="title" value="${board.title }"> <br/>
	글쓴이 : <input type="text" name="writer" value="${board.writer }" readonly> <br/>
	내용 : <textarea name="content" >${board.content }</textarea> <br/>
	쓴 날짜 : <input type="text" name="regdate" value="${board.regdate }" readonly> <br/>
	<input type="hidden" name="bno" value="${board.bno }">
	<input type="submit" value="수정하기">
	</form>
	<form action="/board/list" method="get">
		<input type="submit" value="목록으로">
	</form>
</body>
</html>