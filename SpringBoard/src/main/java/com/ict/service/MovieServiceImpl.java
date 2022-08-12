package com.ict.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.mapper.MovieMapper;
import com.ict.persistence.MovieVO;

@Service
public class MovieServiceImpl implements MovieService {
	
	@Autowired
	private MovieMapper mapper;
	
	@Override
	public List<MovieVO> listMovie(){
		return mapper.getMovieList();
	}

}
