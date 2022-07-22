<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<!-- 아임포트 모듈 -->
<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>
<!-- 제이쿼리 임포트(제이쿼리가 입력되어야 위의 모듈이 작동합니다.) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1>품질좋은 상품목록</h1>

<div class="itemSection">
	<div class="itemCard">
		<div class="itemTitle">
			<h2>헬창을 위한 근육보충제</h2>
		</div>
		<div class="itemContent">
			<h2>맛없지만 단백질 보충이 됩니다.</h2>
		</div>
		<div class="itemPrice">
			<p data-price="40000">40000원</p>
		</div>
		<div class="itemButton">
			<button class="orderBtn">주문하기</button>
		</div>
	</div>
	
	<div class="itemCard">
		<div class="itemTitle">
			<h2>개발자를 위한 키보드</h2>
		</div>
		<div class="itemContent">
			<h2>타건감이 죽여주는 키보드</h2>
		</div>
		<div class="itemPrice">
			<p data-price="200000">200000원</p>
		</div>
		<div class="itemButton">
			<button class="orderBtn">주문하기</button>
		</div>
	</div>
	
	<div class="itemCard">
		<div class="itemTitle">
			<h2>게이밍마우스</h2>
		</div>
		<div class="itemContent">
			<h2>로지텍 지슈라 지프로 슈퍼라이트 G Pro X Superlight</h2>
		</div>
		<div class="itemPrice">
			<p data-price="199000">199000원</p>
		</div>
		<div class="itemButton">
			<button class="orderBtn">주문하기</button>
		</div>
	</div>
		
</div>

<script>

// name, amount, merchant_uid에 대해서 변수를 전역변수로 미리 선언해두기
let itemPrice = 0; // 가격
let itemTitle = ""; // 물건이름
let merchant_uid = ""; // 주문 번호
//위임 처리로 어떤 상품을 클릭했을때 그 상품에 대한
$(".itemSection").on("click", ".orderBtn", function(){
	itemPrice = $(this).parent().siblings(".itemPrice").children().attr("data-price");
	itemTitle = $(this).parent().siblings(".itemTitle").children().text();
	d = new Date(); // 현 시간
	merchant_uid = "hee" + d.getTime(); // 현시간을 유닉스시간으로 변경해 order에 붙임
	// 입력정보를 가져오고 나서 함수 도출
	iamport();
});
   
	function iamport(){
		IMP.init('imp23066347'); // 가맹점번호(본인 아이디에 입력된 번호로)
		IMP.request_pay({
			pg : 'html5_inicis', //KG이니시스
			pay_method : 'card', //결제수단
			merchant_uid : merchant_uid, // 주문번호는 order+유닉스시간으로 처리해 겹치지 않게
			name : itemTitle, //결제창에 뜰 상품명
			amount : itemPrice, //금액
			buyer_email : 'iampoer@siot.do', //구매자 이메일
			buyer_name : '구매자이름', //구매 이름
			buyer_tel : '010-1234-5678', //구매자 번호
			buyer_addr : '서울특별시 강남구 삼성동', //구매자 주소
			buyer_postcode : '123-456', //구매자 우펴번호
		}, function(rsp) {
			console.log(rsp);
			if (rsp.success) {//결제 성공시 처리할 내역
				
				$.ajax({
					type: 'post',
					url: '/order',
					headers:{
						"Content-Type":"application/json",
						"X-HTTP-Method-Override":"POST"
					},
					dataType:"text",
					data: JSON.stringify({
						itemname : itemTitle,
						amount : itemPrice,
						merchant_uid : merchant_uid
					}),
					success: function(){
						alert(itemTitle + " 결제완료!");
					}
				});
		} else {//결제 실패시 처리할 내역
			var msg = '결제에 실패하였습니다.';
			msg += '에러내용 : ' + rsp.error_msg;
			alert(msg);
		}
	});
}  
</script>
</body>
</html>