package com.ict.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

// 빈 컨테이너에 넣어주세요 (등록된 컨트롤러만 동작합니다.)
@Controller
public class MVCController {

	// 기본주소(localhost:8181)뒤에 /goA를 붙이면 goA() 메서드 실행
	@RequestMapping(value="/goA")
	public String goA() {
		System.out.println("goA 주소 접속 감지");
		// 결과 페이지는 views 폴더 아래의 A.jsp
		return "A";
	}
	
	// 주소 /goB를 쓰는걸 직접 만들어주세요.
	@RequestMapping(value="/goB")
	public String goB() {
		System.out.println("ABCDEFGHIJKLNM");
		
		return "alphabet";
	}
	
	// 주소 /goC로 접속하는 컨트롤러를 만들어주세요.
	// 결과 페이지도 goC이고, 접속시 " 바인딩 테스트" 라는 문자열을 저장하세요.
	
	@RequestMapping(value="/goC")
	// Model 을 선언해주면 바인딩 및 포워딩으로 .jsp파일에 데이터를 발송할 수 있습니다. 
	public String goC(Model model) {
		// model.addAtrribute("전달명", 자료);
		// 바인딩해서 보낸 자료는 .jsp파일에서 ${명칭} 으로 el을 사용해 출력할 수 있습니다,
		// goC.jsp에서 아래 문자열을 body태그 내에 띄워보세요.
		model.addAttribute("test", "goC에서 보내온 문자열");
		return "goC";
		
	}
	
	//goD는 파라미터를 입력받을 수 있도록 해보겠습니다.
	@RequestMapping(value="/goD")
	// 주소 뒤 ?dNum=값 형태로 들어오는 자료를 로직 내 dNum변수에 대입해줍니다.
	// 들어온 파라미터를 .jsp로 보내주기 위해서는 역시 Model model을 파라미터에 선언해줍니다.
	public String goD(int dNum, Model model) {
		System.out.println("주소로 전달받은 값 : " + dNum);
		model.addAttribute("dNum", dNum);
		return "D";
		
	}
}
