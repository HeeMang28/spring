package com.ict.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ict.domain.TestVO;

//빈 컨테이너에 넣어주세요 (등록된 컨트롤러만 동작합니다.)
@Controller
public class MVCController {

	// 기본주소(localhost:8181)뒤에 /goA를 붙이면 goA() 메서드 실행
	@RequestMapping(value="/goA")
	public String goA() {
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
		model.addAttribute("test", "goC���� ������ ���ڿ�");
		return "goC";
		
	}
	
	//goD는 파라미터를 입력받을 수 있도록 해보겠습니다.
	@RequestMapping(value="/goD")
	// 주소 뒤 ?dNum=값 형태로 들어오는 자료를 로직 내 dNum변수에 대입해줍니다.
	// 들어온 파라미터를 .jsp로 보내주기 위해서는 역시 Model model을 파라미터에 선언해줍니다.
	public String goD(int dNum, Model model) {
		model.addAttribute("dNum", dNum);
		return "D";
		
	}
	
	// cToF 메서드를 만들겠습니다.
	// 섭씨 온도를 입력받아 화씨 온도로 바꿔서 출력해주는 로직을 작성해주세요.
	// (화씨 - 32) / 1.8 = 섭씨온도 입니다.
	// 파일 이름은 ctof.jsp입니다.
	
	@RequestMapping(value="/ctof", method=RequestMethod.POST)
	public String cToF(double c, Model model) {
		double f = c * 1.8 + 32;
		model.addAttribute("f", f);
		model.addAttribute("c", c);
		return "ctof";	
	}
	
	// 폼을 만들어서 폼에서 입력된 온도를 그대로 섭씨온도로 처리하도록 만듦
	@RequestMapping(value="/ctof", method=RequestMethod.GET)
	public String cToFform() {
		return "ctofform";
	}
	
	// 위와 같은 방식으로 bmi측정페이지를 만들어보겠습니다.
	// 폼페이지와 결과 페이지 조합으로 구성되며 접근주소는 /bmi로 통일합니다.
	// bmi 공식은 체중 / 키(m) ^ 2 로 나오는 결과입니다.
	@RequestMapping(value="/bmi", method=RequestMethod.POST)
	public String bmi(@RequestParam("height") int cm, int kg, Model model) { 
		// requestparam 사용시 cm의 값을 더이상 받지 못함.
		double m = cm / 100.0;
		double bmi = kg / (m * m);
		model.addAttribute("bmi", bmi);
		model.addAttribute("cm", cm);
		model.addAttribute("kg", kg);
		return "bmi";
	}
	@RequestMapping(value="/bmi", method=RequestMethod.GET)
	public String bmiForm() {
		return "bmiform";
	}
	
	// PathVariable을 이용하면 url패턴만으로도 특정 파라미터를 받아올 수 있습니다.
	// reset방식으로 url을 처리할때 주료 사용하는 방식입니다.
	// /pathtest/숫자 중 숫자 위치에 온것을 page변수에 전달할 값으로 간주합니다.
	@RequestMapping(value="/pathtest/{page}")
	public String pathTest(@PathVariable int page, Model model) {
		System.out.println(page);
		model.addAttribute("page", page);
		// 받아온 page 변수를 path.jsp로 보내주세요
		// path.jsp에는 {path}페이지 조회중입니다 라는 문장이 뜨게 해주세요.
		return "path";
}

	@RequestMapping(value="/exchange/{won}")
	public String exchange(@PathVariable int won, Model model) {
		double yen = won / 995;
		model.addAttribute("won", won);
		model.addAttribute("yen", yen);
		
		return "exchange";
	}
	

	@RequestMapping("/spring")
	public void spring() {
<<<<<<< HEAD
		System.out.println("/spirng 주소 감지");
=======
>>>>>>> 8cc671c2c54393b78818241c2f165a9990c144e3
	}
	

	@GetMapping("/list")
	public String getList(@RequestParam("ids") ArrayList<String> ids, Model model) {
		System.out.println(ids);
		model.addAttribute("ids", ids);
		return "list";
	}
	

	@GetMapping("/getVO")
	public String getVO(TestVO vo, Model model) {
		
		System.out.println("받아온 객체 : " + vo);
		model.addAttribute("vo", vo);
		return "voview";
	}
	@GetMapping("/voform")
	public void voForm() {
	}
}