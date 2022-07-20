<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table class="table table-hover">
		<thead>
			<tr>
				<th>글번호</th>
				<th>제목</th>
				<th>글쓴이</th>
				<th>쓴날짜</th>
				<th>수정날짜</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="board" items="${boardList }">
				<tr>
					<td>${board.bno }</td>
					<td><a href="http://localhost:8181/board/detail?bno=${board.bno }&keyword=${pageMaker.cri.keyword}&searchType=${pageMaker.cri.searchType}&page=${pageMaker.cri.page}">${board.title }<span class="badge bg-dark">${board.replycount}</span></a></td>
					<td>${board.writer }</td>
					<td>${board.regDate }</td>
					<td>${board.updateDate }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<a href="/board/insert"><button class="btn btn-primary">글쓰기</button></a>
	
	
	<ul class="pagination">
		<c:if test="${pageMaker.prev }">
			<li class="page-item">
		      <a class="page-link" href="http://localhost:8181/board/list?page=${pageMaker.startPage -1}&searchType=${pageMaker.cri.searchType}&keyword=${pageMaker.cri.keyword}" aria-label="Previous">
		        <span aria-hidden="true">&laquo;</span>
		      </a>
		    </li>
		 </c:if>
		 <c:forEach begin="${pageMaker.startPage }" end="${pageMaker.endPage }" var="pNum">								
		 	<li class="page-item ${pNum eq pageMaker.cri.page ? 'active' : '' }"><a class="page-link" href="http://localhost:8181/board/list?page=${pNum }&searchType=${pageMaker.cri.searchType}&keyword=${pageMaker.cri.keyword}">${pNum }</a></li>
		 </c:forEach>
		 <c:if test="${pageMaker.next }">
		     <li class="page-item">
		      <a class="page-link" href="http://localhost:8181/board/list?page=${pageMaker.endPage +1}&searchType=${pageMaker.cri.searchType}&keyword=${pageMaker.cri.keyword}" aria-label="Next">
		        <span aria-hidden="true">&raquo;</span>
		      </a>
		    </li>
		 </c:if>
	</ul>
	
	<!-- 검색창 위치 -->
	<select name="seatchType">
		<option value="n" ${pageMaker.cri.searchType == null ? 'selected' : '' }>
		-
		</option>
		<option value="t" ${pageMaker.cri.searchType == 't' ? 'selected' : '' }>
		제목
		</option>
		<option value="c" ${pageMaker.cri.searchType == 'c' ? 'selected' : '' }>
		본문
		</option>
		<option value="w" ${pageMaker.cri.searchType == 'w' ? 'selected' : '' }>
		글쓴이
		</option>
		<option value="tc" ${pageMaker.cri.searchType == 'tc' ? 'selected' : '' }>
		제목 + 본문
		</option>
		<option value="cw" ${pageMaker.cri.searchType == 'cw' ? 'selected' : '' }>
		본문 + 글쓴이
		</option>
		<option value="tcw" ${pageMaker.cri.searchType == 'tcw' ? 'selected' : '' }>
		제목 + 본문 + 글쓴이
		</option>
	</select>
	
	<input type="text" name="keyword" id="keywordInput" value="${pageMaker.cri.keyword }">
	<button id="searchBtn">검색하기</button><br>
	
	${pageMaker}
	<script>
		//검색버튼 작동
		$('#searchBtn').on("click", function(event){
			self.location = "list"
				+ "?page=1"
				+ "&searchType="
				+ $('select option:selected').val()
				+ "&keyword=" + $('#keywordInput').val();
		});
	</script>
</body>
</html>






