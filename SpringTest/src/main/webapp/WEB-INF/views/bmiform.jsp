<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<h1> bmi ���� ������ �Դϴ�.</h1>
	<form action="/bmi" method="post">
	Ű : <input type="number" name="height" required> <br/>
	������ : <input type="number" name="kg" required> <br/>
	<hr/>
	<input type="submit" value="����"><br/>
	</form>
</body>
</html>