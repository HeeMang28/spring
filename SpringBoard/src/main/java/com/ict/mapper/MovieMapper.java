package com.ict.mapper;

import java.util.List;

import com.ict.persistence.MovieVO;

public interface MovieMapper {
	
	// 전체 목록을 조회하는 메서드를 만들어주세요.
	// MovieVO를 만들어주세요. 테이블 구조는 oracle sql에 만든 movie_tbl을 따릅니다.
	// 영화 1개의 정보가 MovieVO이므로, 이걸 묶어서 가져오려면 리턴은 List<MovieVO> 가 되어야 합니다.
	public List<MovieVO> getMovieList();
	
}
