<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!--  글쓸때, 제목 글쓴이, 본문 을 채우고 submit을 눌러야 합니다.
	vo에 적힌 명칭을 감안해서 제목, 글쓴이 본문을 쓸 수 있도록 폼태그를 완성시켜주세요. -->
	<form action="/board/insert" method="post">
	제목 : <input type="text" name="title"> <br/>
	글쓴이 : <input type="text" name="writer"> <br/>
	내용 : <textarea name="content"></textarea> <br/>
	<input type="submit" value="글쓰기">
	</form>
	<form action="/board/list" method="get">
		<input type="submit" value="목록으로">
	</form>
</body>
</html>