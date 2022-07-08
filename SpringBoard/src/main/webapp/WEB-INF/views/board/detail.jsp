<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
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
	<!--  댓글달리는 영역 -->
	<div class="row">
		<h3 class="text-primary">댓글</h3>
		<div id="replies">
			<!-- 댓글이 들어갈 위치 -->
		</div>
	</div><!-- /.row -->
	
	<!--  댓글쓰기 -->
	<div class="row box-box-success">
		<div class="box-header">
			<h2 class="text-primary">댓글 작성</h2>
		</div><!-- header -->
		<div class="box-body">
			<strong>Writer</strong>
			<input type="text" placeholder="Replyer" id="newReplyWriter" class="form-control">
			<strong>ReplyText</strong>
			<input type="text" placeholder="ReplyText" id="newReplyText" class="form-control">>
		</div>
		<div class="box-footer">
		<button type="button" class="btn btn-success" id="replyAddBtn">ADD REPLY</button>
		</div>
	</div>
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
	<sec:authorize access="hasRole('ROLE_ADMIN','ROLE_MEMBER')">
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
	</sec:authorize>
		<!--  jquery CDN -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
		
	<script type="text/javascript">
	// 해당 글 번호
	let bno = ${board.bno};
	// 댓글 전체 불러오기
		function getAllList() {
			let str="";
			$.getJSON("/replies/all/" + bno, function(data){
				$(data).each(
					function(){
						console.log(this)
						// 백틱 문자열 사이에 변수를 넣고 싶다면 \${변수명} 을 적습니다.
						// 원래는 \를 왼쪽에 붙일 필요는 없지만
						// jsp에서는 el표기문법이랑 겹치기 때문에 el이 아님을 보여주기 위해
						// 추가로 \를 붙입니다.
						
						// UNIX 시간을 우리가 알고 있는 형식으로 바꿔보겠습니다.
						let timestamp = this.updateDate;
						// UNIX 시간이 저장된 timestamp를 Date 생성자로 변환합니다.
						let date = new Date(timestamp);
						// 변수 formateedTime에 변환된 시간을 저장해 출력해보겠습니다.
						let formattedTime = `게시일 : \${date.getFullYear()}년
													\${(date.getMonth()+1)}월
													\${date.getDate()}일`;
						
						str += 
							`<div class='replyLi' data-rno='\${this.rno}'>
							<strong>@\${this.replyer}</strong> - \${formattedTime}<br/>
							<div class='replytext'>\${this.reply}</div>
							<button type='button' class='btn btn-info'>수정/삭제</button>
							</div>`;
					});
					$("#replies").html(str);
				});
		}
		getAllList();
		
		 $("#replyAddBtn").on("click",function(){
				
				let replyer = $("#newReplyWriter").val();
				let reply = $("#newReplyText").val();
				
				$.ajax({
					type : 'post',
					url : '/replies',
					headers : {
						"Content-Type" : "application/json",
						"X-HTTP-Method-Override" : "POST"
					},
					dataType : 'text',
					data : JSON.stringify({
						bno : bno, // 스크립트 태그 맨위에 있는 let 글번호
						replyer : replyer, //  replyAddBtn 에 let으로 한 replyer
						reply : reply // replyAddBtn 에 let으로 한 reply
					}),
					success : function(result) {
						if(result == 'SUCCESS'){
							alert("등록 되었습니다.");
							getAllList();
							$("#newReplyWriter").val('');
							$("#newReplyText").val('');
							}
					}
				});
			});
		
		// 이벤트 위임
		$('#replies').on("click", ".replyLi button", function(){
			// 4. 함수 내부으 this는 내가 클릭한 button이 됩니다.
			// 선태 요소와 연관된 태그 고르기
			// 1. prev().prev()... 등과 같이 연쇄적으로 prev, next를 걸어서 고르기
			// 2. prev("태그선택자")를 써서 뒤쪽이나 앞쪽 형제 중 조건에 맞는것만 선택
			// 3. siblings("태그선택자")는 next, prev 모두를 범위로 조회합니다.
			// let reply = $(this).siblings(".replytext");
				// 4. 함수 내부으 this는 내가 클릭한 button이 됩니다.
				let reply = $(this).parent();
				// 화살표 함수는 this 키워드를 쓰지 않을때만 사용 가능하다. 여기서 this는 버튼이다
				// .attr("태그 내 속성명") => 해당 속성에 부여된 값을 가져옵니다.
				// ex) <li data-rno="33"> => rno에 33을 저장해줍니다.
				let rno = reply.attr("data-rno");
				let replytext = $(this).prev().html()// 본문만 가져오도록 살짝 수정해주세요.
				
				$(".modal-title").html(rno);
				$("#replyText").val(replytext);
				$("#modDiv").show("slow");
			});
	</script>
	<script src="/resources/resttest/delete.js" ></script>
	<script src="/resources/resttest/modify.js"></script>
	<script src="/resources/resttest/close.js"></script>
	<button><a href="/board/list?page=${param.page}&searchType=${param.searchType}&keyword=${param.keyword}">목록으로</a></button>

</body>
</html>