<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<!-- .css, .js, 그림파일 드은 src/main/webapp/resources폴더 아래에 저장한다음
	/resources/경로 형식으로 적으면 가져올 수 있습니다.
	이렇게 경로가 자동으로 잡히는 이유는 servlet-context.xml에 설정이 잡혀있기 때문입니다. -->
	<link rel="stylesheet" href="/resources/resttest/modal.css">
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<h2>Ajax 테스트</h2>
	
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
	<!--  <button id="ajaxRequestBtn">비동기 요청</button> -->
	<!--  jquery CDN -->
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script type="text/javascript">
		let bno = 1;
		// 버튼 클릭시
		/* function getAllList() {}
			$.getJSON("/replies/all/" + bno, function(data){
				$(data).each(
					function(){
						console.log(this)
						// 백틱 문자열 사이에 변수를 넣고 싶다면 \${변수명} 을 적습니다.
						// 원래는 \를 왼쪽에 붙일 필요는 없지만
						// jsp에서는 el표기문법이랑 겹치기 때문에 el이 아님을 보여주기 위해
						// 추가로 \를 붙입니다.
						str += `<li data-rno= '\${this.rno}' class='replyLi'>\${this.rno} : \${this.reply} 
							</li>`;
					});
					$("#replies").html(str);
				});
			});
		*/
		// 댓글 전체 불러오기
		function getAllList() {
			$.getJSON("/replies/all/" + bno, function(data){
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
		}); // 글 등록로직 종료 
		///////////////////////////////////
		// 댓글수정하기 이벤트 연결
		///////////////////////////////////
		// 이벤트 위임
		
		// 1. ul #replies가 이벤트를 걸고 싶은 버튼 전체의 집합이므로 먼저 집합 전체에 이벤트를 겁니다.
		// 2. #replies의 하위 항목중 최종 목표 태그를 기입해줍니다.
		// 3. 단, 여기서 #replies와 button 사이에 다른 태그가 끼어있다면 경유하는 형식으로 호출해도 됩니다.
		$('#replies').on("click", ".replyLi button", function(){
			// 4. 함수 내부으 this는 내가 클릭한 button이 됩니다.
			let reply = $(this).parent();
			// 화살표 함수는 this 키워드를 쓰지 않을때만 사용 가능하다. 여기서 this는 버튼이다
			// .attr("태그 내 속성명") => 해당 속성에 부여된 값을 가져옵니다.
			// ex) <li data-rno="33"> => rno에 33을 저장해줍니다.
			let rno = reply.attr("data-rno");
			let replytext = reply.text();
			
			$(".moda-title").html(rno);
			$("#replytext").val(replytext);
			$("#modDiv").show("slow");
		}); // 댓글 삽입
		
	</script>
</body>
</html>