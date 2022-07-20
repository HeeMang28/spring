package com.ict.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
// 이 컨트롤러 내부 모든 메서드는 공통주소로 앞에 /spring/을 갖습니다.
@RequestMapping("/spring/")
public class SpringController {

	@RequestMapping("")
	public void basic() {
		System.out.println("기본 URL 주소입니다.");
	}
	
	@RequestMapping(value="/base", method={RequestMethod.GET, RequestMethod.POST})
	public void baseGet() {
		System.out.println("base get");
	}
	
	@PostMapping("/basePost")
	public void baseOnlyPost() {
		System.out.println("base only post");
	}
		
}
