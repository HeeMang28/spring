package com.ict.service;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ict.persistence.BoardVO;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
	"file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardSeriveTests {
	
	@Autowired
	private BoardService service;
	
	//@Test
	public void testGetList() {
		log.info(service.getList());
	}
	
	//@Test
	public void testBoardInsert() {
		BoardVO vo = new BoardVO();
		
		vo.setTitle("보드서비스테스트");
		vo.setContent("보드서비스테스트중");
		vo.setWriter("보드서비스");
		
		// log.info(vo);
		service.insert(vo);
		log.info(service.getList());
	}
	
	//@Test
	public void testBoardDelete() {
		service.delete(2L);
	}
	
	//@Test
	public void testBoardUpdate() {
		BoardVO vo = new BoardVO();
		
		vo.setTitle("보드 서비스 업데이트");
		vo.setContent("보드 서비스 업데이트 입니다.");
		vo.setWriter("보드 업데이트");
		vo.setBno(3L);
		service.update(vo);
	}
	
	//@Test
	//public void testBoardDetail {
	//	BoardVO vo = new BoardVO();
	//	vo.setBno(3L);
	//	service.boardDetail
		
	//}
}
