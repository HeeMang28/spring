<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
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
					<td><a href="/board/detail?bno=${board.bno}&page=${pageMaker.cri.page}&searchType=${pageMakger.cri.searchType}&keyword=${pageMaker.cri.keyword}&page=${pageMaker.cri.page}">${board.title}<span class="badge bg-success">${board.replycount}</span></a></td>
					<td>${board.content}</td>
					<td>${board.writer}</td>
					<td>${board.regdate}</td>
					<td>${board.updateDate}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<hr/>
	<a href="/board/insert"><button class="btn btn-primary">글쓰기</button></a>
	<ul class="pagination justify-content-center">
		<c:if test="${pageMaker.prev }">
			<li class="page-item">
				<a class="page-link" href="/board/list?page=${pageMaker.startPage - 1}&searchType=${pageMaker.cri.searchType}&keyword=${pageMaker.cri.keyword}">&laquo; </a>
			</li>
		</c:if>
		<c:forEach begin="${pageMaker.startPage }" end ="${pageMaker.endPage }" var="pNum">
			<li class="page-item
			<c:out value="${pageMaker.cri.page == pNum ? 'active' : '' }" />">
				<a class="page-link" href="/board/list?page=${pNum }&searchType=${pageMaker.cri.searchType}&keyword=${pageMaker.cri.keyword}">${pNum }</a></li>
		</c:forEach>
		<c:if test="${pageMaker.next && pageMaker.endPage > 0 }">
			<li class="page-item">
				<a class="page-link" href="/board/list?page=${pageMaker.endPage +1 }&searchType=${pageMaker.cri.searchType}&keyword=${pageMaker.cri.keyword}">&raquo;</a>
			</li>
		</c:if>
	</ul>
	<!--  검색창 -->
	<form action="/board/list" method="get">
	<!-- option태그를 이용해 검색조건 선택창을 만들어주세요. -->
		<select name="searchType">
			<option value="n" <c:out value="${pageMaker.cri.searchType == null ? 'selected' : '' }" />>
			-
			</option>
			<option value="t" <c:out value="${pageMaker.cri.searchType eq 't' ? 'selected' : '' }" />>
			제목
			</option>
			<option value="c" <c:out value="${pageMaker.cri.searchType eq 'c' ? 'selected' : '' }" />>
			본문
			</option>
			<option value="w" <c:out value="${pageMaker.cri.searchType eq 'w' ? 'selected' : '' }" />>
			글쓴이
			</option>
			<option value="tc" <c:out value="${pageMaker.cri.searchType eq 'tc' ? 'selected' : '' }" />>
			제목 + 본문
			</option>
			<option value="cw" <c:out value="${pageMaker.cri.searchType eq 'cw' ? 'selected' : '' }" />>
			본문 + 글쓴이
			</option>
			<option value="tcw" <c:out value="${pageMaker.cri.searchType eq 'tcw' ? 'selected' : '' }" />>
			제목 + 본문 + 글쓴이 
			</option>
		</select>
		
		<input type="text" name="keyword" id="keywordInput" value="${pageMaker.cri.keyword }">
		<button id="searchBtn">Search</button><br/>
	</form>
	<script>
		// 검색버튼 작동
		$('#searchBtn').on("click", function(event){
			
			self.location = "list"
				+ "?page=1"
				+ "&searchType="
				+ $("select option:selected").val()
				+ "&keyword=" + $("#keywordInput").val(); 
		});
	</script>
	${pageMaker }
</body>
</html>