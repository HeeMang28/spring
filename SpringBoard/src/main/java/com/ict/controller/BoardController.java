package com.ict.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ict.persistence.BoardVO;
import com.ict.persistence.Criteria;
import com.ict.persistence.PageMaker;
import com.ict.persistence.SearchCriteria;
import com.ict.service.BoardService;

import lombok.extern.log4j.Log4j;



//bean container에 넣어보세요. controller를 걸면 서블릿 컨테스트에 들어간다.
// 주소 /board가 붙도록 처리해주세요.
@Controller
@RequestMapping("/board/")
@Log4j
public class BoardController {
	
	// 컨트롤러는 ???를 호출합니다. autowired로 주입해주세요.
	@Autowired
	private BoardService service;
	
	// /board/list 주소로 게시물 전체의 목록을 표현하는 컨트롤러를 만들어 주세요.
	// list.jsp로 연결되면 되고, getList() 메서드로 가져온 전체 글 목록을
	// 포워딩해서 화면에 뿌려주면, 글번호, 글제목, 글쓴이, 날짜, 수정날짜를 화면에 출력해줍니다.
	@RequestMapping("/list")
							//@RequestParam의 defaultValue를 통해 값이 안들어올때 자동으로 배정할 값을 정할수 있음
	public String getBoardList(SearchCriteria cri, Model model) {
		if(cri.getPage() == 0 ) {
			cri.setPage(1);
		}
		// 글 전체 목록 가져오기
		List<BoardVO> boardList = service.getList(cri);
		// 바인딩
		model.addAttribute("boardList", boardList);
		//model.addAttribute("boardList", service.get());
		
		// 페이지네이터 그리기 위해 처리 정보도 같이 전달하기
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		// pageMaker.setTotalBoard(131);
		pageMaker.setTotalBoard(service.getBoardCount());
		model.addAttribute("pageMaker", pageMaker);
		// 리턴 구문을 적어서 원하는 파일로 데이터 보내기.
		return "/board/list";
	}
	
	// 글 번호를 입력받아서(주소창에서 ?bno=번호 형식으로) 해당 글의 디테일 페이지를 보여주는
	// 로직을 완성시켜주세요.
	// board/detail.jsp입니다.
	// getBoardList처럼 포워딩해서 화면에 해당 글 하나에 대한 정보만 보여주면 됩니다.
	// mapper쪽에 먼저 bno를 이용해 특정 글 하나의 VO만 얻어오는 로직을 만들고
	// 쿼리문까지 연결해주세요.
	
	@GetMapping(value= "/detail")
	public String getBoardDetail(Long bno, Model model) {
		BoardVO board = service.boardDetail(bno);
		model.addAttribute("board", board);
		return "/board/detail";
	}
	
	// 글쓰기는 말 그대로 글을 써주는 로직인데
	// 폼으로 연결되는 페이지가 하나 있어야하고
	// 그다음 폼에서 날려주는 로직을 처리해주는 페이지가 하나 더 있어야 합니다.
	// /board/insert 를 get방식으로 접속시
	// bardForm.jsp로 연결되도록 만들어주세요.
	
	@GetMapping("/insert")
	public String insertForm() {
		return "/board/boardForm";
	}
	
	// post방식으로 /insert로 들어오는 자료를 받아 콘솔에 찍어주세요.
	@PostMapping("/insert")
	public String insertBoard(BoardVO board) {
		log.info(board);
		service.insert(board);
		// redirect를 사용해야 전체 글 목록을 로딩해온 다음 화면을 열어줍니다.
		// 스프링 컨트롤러에서 리다이렉트를 할 때는
		// 목적주소 앞에 redirect: 을 추가로 붙입니다.
		return "redirect:/board/list";
	}
	
	// 글삭제도 post방식으로 처리하도록 합니다.
	@PostMapping("/delete")
	public String deleteBoard(Long bno) {
		// 삭제 후 리스트로 돌아갈 수 있도록 내부 로직을 만들어주시고
		// 디테일 페이지에 삭제 요청을 넣을 수 있는 폼을 만들어주세요.
		service.delete(bno);
		return "redirect:/board/list";
	}
	
	@PostMapping("/updateForm")
	public String updateForm(Long bno, Model model) {
		// 해당 bno의 글 정보만 뽑아서 저장한 다음
		// 포워딩을 이용해 updateForm.jsp에 보내줍니다.
		BoardVO board = service.boardDetail(bno);
		model.addAttribute("board", board);
		return "/board/updateForm";
	}
	
	@PostMapping("/update")
	public String updateBoard(BoardVO board) {
		// 받아온 vo를 이용해 update를 실행한 다음
		service.update(board);
		// 해당 글 번호의 디테일 페이지로 들어가서 수정 여부를 바로 확인할 수 있게 도와줍니다.
		return "redirect:/board/detail?bno=" + board.getBno();
	}
}
