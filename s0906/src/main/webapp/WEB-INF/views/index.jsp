<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>index</title>
	</head>
	<body>
		<h1>index</h1>
		<!-- 포트원 결제 -->
    <script src="https://cdn.iamport.kr/v1/iamport.js"></script>
    <!-- jQuery -->
    <script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
    <!-- iamport.payment.js -->
    <script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.2.0.js"></script>
<!-- 포트원 결제 -->

<button id="payment" onclick="tossPay()">구매하기</button>
<div id="title"></div>
<script type="text/javascript">
function tossPay(){
	
	var today = new Date();
	var hours = today.getHours(); // 시
	var minutes = today.getMinutes();  // 분
	var seconds = today.getSeconds();  // 초
	var milliseconds = today.getMilliseconds();
	var makeMerchantUid = `${hours}` + `${minutes}` + `${seconds}` + `${milliseconds}`;
	
	
	const userCode = "imp24133461";
	IMP.init(userCode); // 고객사 식별 코드를 넣어 모듈을 초기화해주세요.

	IMP.request_pay(
	  {
	    pg: "tosspayments", // 반드시 "tosspayments"임을 명시해주세요.
	    merchant_uid: "order_id_" + makeMerchantUid +"01", // 결제 고유 번호
	    name: "나이키 와플 트레이너 2 SD",
	    pay_method: "card",
	    escrow: false,
	    amount: "109000",
	    tax_free: 3000,
	    buyer_name: "홍길동",
	    buyer_email: "buyer@example.com",
	    buyer_tel: "02-1670-5176",
	    buyer_addr: "성수이로 20길 16",
	    buyer_postcode: "04783",
	    m_redirect_url: "https://helloworld.com/payments/result", // 모바일 환경에서 필수 입력
	    notice_url: "https://helloworld.com/api/v1/payments/notice",
	    confirm_url: "https://helloworld.com/api/v1/payments/confirm",
	    currency: "KRW",
	    locale: "ko",
	    custom_data: { userId: 30930 },
	    display: { card_quota: [0, 6] },
	    appCard: false,
	    useCardPoint: true,
	    bypass: {
	      tosspayments: {
	        useInternationalCardOnly: true, // 영어 결제창 활성화
	      },
	    },
	  },
	  (response) => {
	    // PC 환경에서 결제 프로세스 완료 후 실행 될 로직
	  },
	);
}


</script>
	</body>
</html>