<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<!--  form을 만들어주세요.
	목적지는 /ctof이며 전송메서드는 post입니다.
	cel이라는 number 타입의 input을 이용해 온도를 입력받으며
	제출버튼을 누르면 해당 주소에 post방식으로 접근해 폼 내부의 온도를 전달하고
	그 결과를 화면에 뿌려줍니다. -->
	<form action="/ctof" method="post">
	c 온도 : <input type="number" name="c" required><br/>
	<hr/>
	<input type="submit" value="제출"><br/>
	</form>
</body>
</html>