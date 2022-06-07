<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>boardList </h1>
	<table class="table table-hover">
		<thead>
			<th>글번호</th>
			<th>제목</th>
			<th>내용</th>
			<th>작성자</th>			
			<th>작성날짜</th>			
			<th>수정날짜</th>			
		</thead>
		<tbody>
			<c:forEach var="board" items="${boardList }">
				<tr>
					<td>${board.bno}</td>
					<td>${board.title}</td>
					<td>${board.content}</td>
					<td>${board.writer}</td>
					<td>${board.regdate}</td>
					<td>${board.updateDate}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>