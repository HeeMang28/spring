<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
<head>
	<link rel="stylesheet" href="/resources/resttest/modal.css">
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="container">
			<h1> 게시판 디테일 페이지입니다. </h1>
			<hr/>
		<div class="row">
	제목 : ${board.title }<br/>
	작성자 : ${board.writer}<br/>
	내용 : <br/>
	<textarea>${board.content }</textarea> <br/>
	수정날짜 :${board.updateDate }<br/>
		</div>
	</div>
	<hr/>
		<div>
		<div>
			REPLYER <input type="text" name="replyer" id="newReplyWriter">
		</div>
		<div>
			REPLY TEXT <input type="text" name="reply" id="newReplyText">
		</div>
		<button id="replyAddBtn">ADD REPLY</button>
	</div>
	
	<ul id="replies">
	
	</ul>
		<div id="modDiv" style="display:none;">
			<div class="modal-title"></div>
			<div>
				<input type="text" id="replyText">
			</div>
			<div>
				<button type="button" id="replyModBtn">Modify</button>
				<button type="button" id="replyDelBtn">Delete</button>
				<button type="button" id="closeBtn">Close</button>
			</div>
		</div>
	<form action="/board/delete" method="post">
		<input type="hidden" name="bno" value="${board.bno }">
		<input type="submit" value="삭제하기">
	</form>
	<form action="/board/updateForm" method="post">
		<input type="hidden" name="bno" value="${board.bno }">
		<input type="hidden" name="page" value="${param.page }">
		<input type="hidden" name="searchType" value="${param.searchType }">
		<input type="hidden" name="keyword" value="${param.keyword }">
		<input type="submit" value="수정하기">
	</form>
		<!--  jquery CDN -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
		
	<script type="text/javascript">
	// 댓글 전체 불러오기
		function getAllList() {
			$.getJSON("/replies/all/" + bno, function(data){
				let bno = ${board.bno};
				let str="";
				$(data).each(
					function(){
						console.log(this)
						// 백틱 문자열 사이에 변수를 넣고 싶다면 \${변수명} 을 적습니다.
						// 원래는 \를 왼쪽에 붙일 필요는 없지만
						// jsp에서는 el표기문법이랑 겹치기 때문에 el이 아님을 보여주기 위해
						// 추가로 \를 붙입니다.
						str += `<li data-rno= '\${this.rno}' class='replyLi'>\${this.rno} : \${this.reply} 
							<button>수정/삭제</button></li>`;
					});
					$("#replies").html(str);
				});
		}
		getAllList();
		
		// 이벤트 위임
		$('#replies').on("click", ".replyLi button", function(){
			// 4. 함수 내부으 this는 내가 클릭한 button이 됩니다.
			let reply = $(this).parent();
			// 화살표 함수는 this 키워드를 쓰지 않을때만 사용 가능하다. 여기서 this는 버튼이다
			// .attr("태그 내 속성명") => 해당 속성에 부여된 값을 가져옵니다.
			// ex) <li data-rno="33"> => rno에 33을 저장해줍니다.
			let rno = reply.attr("data-rno");
			let replytext = reply.text();
			
			$(".modal-title").html(rno);
			$("#replyText").val(replytext);
			$("#modDiv").show("slow");
		});
	</script>
	<button><a href="/board/list?page=${param.page}&searchType=${param.searchType}&keyword=${param.keyword}">목록으로</a></button>
	
</body>
</html>