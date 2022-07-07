<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>admin 주소</h1>
	<h2>다양한 페이지 정보</h2>
	<!-- principle 멤버를 쓸수 있는이유는 customuser에서 getter 어노테이션을 넣어줬기 때문 -->
	<p>principal : <sec:authentication property="principal"/></p>
	<p>MemberVO : <sec:authentication property="principal.member"/></p>
	<p>사용자의 이름 : <sec:authentication property="principal.member.userName"/></p>
	<p>사용자의 아이디 : <sec:authentication property="principal.member.userid"/></p>
	<p>사용자 권한목록 : <sec:authentication property="principal.member.authList"/></p>
	
	<hr>
	<a href="/customLogout">로그아웃 페이지 이동</a>
</body>
</html>