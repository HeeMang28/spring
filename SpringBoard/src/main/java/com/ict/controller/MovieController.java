package com.ict.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ict.persistence.BoardVO;
import com.ict.persistence.MovieVO;
import com.ict.persistence.PageMaker;
import com.ict.persistence.SearchCriteria;
import com.ict.service.MovieService;



@RestController
@RequestMapping("/movie")
public class MovieController {
	
	/*
	// 메서드를 만들어주세요.
	// get방식으로 접속하고 전체 영화 목록을 result.jsp에 출력하도록 세팅해주세요.
	// mvc 패턴
	@RequestMapping("/list")
	public String getBoardList(Model model) {
		List<MovieVO> movieList = service.listMovie()
		model.addAttribute("movieList", movieList);
		return "/movie/result"; 
	}  
	 
	 */
	@Autowired
	private MovieService service;
	
	@GetMapping(value="/all",
			// 단일 숫자데이터 bno만 넣기도 하고
			// PathVariable 어노테이션으로 이미 입력데이터가
			//명시 되었으므로 consumes는 따로 주지 않아도 됩니다.
			// produces는 댓글 목록이 XML로도, JSON으로도 표현될수있또록
			// 아래와같이 2개를 모두 얹습니다.
			// jackson-dataformat-xml을 추가해야 xml도 작동합니다.
			produces= {MediaType.APPLICATION_XML_VALUE,
						MediaType.APPLICATION_JSON_UTF8_VALUE})
public ResponseEntity<List<MovieVO>> list () {
	
		ResponseEntity<List<MovieVO>> entity = null;
		
		try {
			entity = new ResponseEntity<>(
					service.listMovie(), HttpStatus.OK);			
		} catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return entity;
			}
}
