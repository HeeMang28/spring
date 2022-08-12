package com.ict.mapper;

import java.util.List;

import com.ict.persistence.ReplyVO;

public interface ReplyMapper {

	// 게시판 bno번 글의 전체 댓글 목록 가져오기
	public List<ReplyVO> getList(Long bno);
	
	public void create(ReplyVO vo);
	
	public void update(ReplyVO vo);
	
	public void delete(Long rno);
	
	// 댓글번호(rno)입력시 해당 댓글이 속한 글번호(bno)를 리턴받는 쿼리문
	public Long getBno(Long rno);
	
	public void deleteAll(Long bno);
}
