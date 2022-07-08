package com.ict.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ict.persistence.AuthVO;
import com.ict.persistence.MemberVO;
import com.ict.service.SecurityService;

import lombok.extern.log4j.Log4j;

@Log4j
@RequestMapping("/secu/*")
@Controller
public class SecurityController {
	
	/* @Autowired
	private SecurityService service;
	
	@Autowired
	private PasswordEncoder pwen;
	
	
	@GetMapping("/all")
	public void doAll() {
		log.info("모든 사람이 접속 가능한 all 로직");
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MEMBER')")
	@GetMapping("/member")
	public void doMember() {
		log.info("회원들이 접속 가능한 member 로직");
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@GetMapping("/admin")
	public void doAdmin(Principal principal) {//Principal 타입을 파라미터에 선언하면 컨트롤러에서 제어가능
		log.info("운영자 접속 : " + principal.getName()); // 로그인한 사용자 아이디를 getName()으로 가져올수잇음
		log.info("운영자만 접속 가능한 admin 로직");
	}
	
	@PreAuthorize("permitAll")
	@GetMapping("/join")
	public void joinForm() {
		log.info("회원가입창 접속");
	}
	
	@PreAuthorize("permitAll")
	@PostMapping("/join")
	public void join(MemberVO vo, String[] role) {
		
		String beforeCrpw = vo.getUserpw();
		log.info("암호화 전 : " + vo.getUserpw());
		vo.setUserpw(pwen.encode(beforeCrpw));
		log.info("암호화 후 : " + vo.getUserpw());
		//log.info(Arrays.toString(role));
		
		log.info("권한 제대로 안 들어온거부터 체크 : " + vo.getAuthList());
		// null 인 상태인 authList에 빈 ArrayList를 먼저 배정
		vo.setAuthList(new ArrayList<AuthVO>());
		log.info("빈 ArrayList 배정 : " + vo.getAuthList());
		
		// authList는 List<authList>이므로 권한 개수에 맞게 넣어줘야함.
		/* for(int i = 0; i < role.length; i++) {
			vo.getAuthList().add(new AuthVO());
			vo.getAuthList().get(i).setAuth(role[i]);
			vo.getAuthList().get(i).setUserid(vo.getUserid());
		} */
		
		// 향상된 for문을 이용해 role 변수에 든 권한을 하나하나 순차적으로 뽑아서
		// ArrayList의 .add(자료) 기능을 이용해 넣어줍니다.
		 /* for(String roleItem : role) {
			AuthVO authVO = new AuthVO();
			authVO.setAuth(roleItem);
			authVO.setUserid(vo.getUserid());
			
			log.info("권환 갯수만큼 AuthVO 생성하는지 체크 : " + authVO);
			// 체크가 되었다면 vo내부의 authVO에 집어넣기
			vo.getAuthList().add(authVO);
		}
		
		service.insertMember(vo); 
	}  */
}
