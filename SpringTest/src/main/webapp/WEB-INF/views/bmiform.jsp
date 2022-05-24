<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<h1> bmi 측정 페이지 입니다.</h1>
	<form action="/bmi" method="post">
	키 : <input type="number" name="height" required> <br/>
	몸무게 : <input type="number" name="kg" required> <br/>
	<hr/>
	<input type="submit" value="제출"><br/>
	</form>
</body>
</html>