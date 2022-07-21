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

<script>
	function iamport(){
		IMP.init('imp23066347'); // 가맹점번호(본인 아이디에 입력된 번호로)
		IMP.request_pay({
			pg : 'html5_inicis', //KG이니시스
			pay_method : 'card', //결제수단
			merchant_uid : "order_no_041331", // 상점에서 관리하는 주문 번호를 전달
			name : '주문명:결제테스트', //결제창에 뜰 상품명
			amount : 14000, //금액
			buyer_email : 'iampoer@siot.do', //구매자 이메일
			buyer_name : '구매자이름', //구매 이름
			buyer_tel : '010-1234-5678', //구매자 번호
			buyer_addr : '서울특별시 강남구 삼성동', //구매자 주소
			buyer_postcode : '123-456', //구매자 우펴번호
		}, function(rsp) {
			console.log(rsp);
			if (rsp.success) {//결제 성공시 처리할 내역
				var msg = '결제가 완료되었습니다.';
				msg += '고유ID : ' + rsp.imp_uid;
				msg += '상점 거래 ID : ' + rsp.merchant_uid;
				msg += '결제 금액 : ' + rsp.paid_amount;
				msg += '카드 승인번호 : ' + rsp.apply_num;
		} else {//결제 실패시 처리할 내역
			var msg = '결제에 실패하였습니다.';
			msg += '에러내용 : ' + rsp.error_msg;
		}
		alert(msg); //여기서는 alert창만 띄우고 끝나지만 리다이렉트 등의 방법이 있음
		});
	}
	iamport(); //실제로 실행 호출하기
</script>
</body>
</html>