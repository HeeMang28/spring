<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="/getVO" method="get">
	이름 : <input type="text" name="name"></br>
	나이 : <input type="number" name="age"></br>
	레벨 : <input type="number" name="level"></br>
	주소: <input type="text" name="address"></br>
	<hr/>
	<input type="submit" value="제출"></br/>
	</form>
</body>
</html>