package com.ict.service;

import java.util.List;

import com.ict.persistence.BoardAttachVO;
import com.ict.persistence.BoardVO;
import com.ict.persistence.Criteria;
import com.ict.persistence.SearchCriteria;

public interface BoardService {

	// Service는 원래 하나의 동작(사용자 기준)을 선언하고
	// Mapper는 하나의 호출(쿼리문) 을 선언하는 용도입니다.
	// 그런데 기본적인 로직은 하나의 동작이 하나의 쿼리문이므로
	// 현재는 그냥 로직별로 하나씩 메서드를 만들어주시면 됩니다.
	// 단, 나중에 사용자에게는 글삭제 이지만, 백로직에서는 글과 댓글이 모두 삭제된다던지... 하는 식으로
	// 사용자 기준의 하나의 동작과 로직개념적 하나의 동작이 일치하지 않을수도 있으니 주의해야합니다.
	public List<BoardVO> getList(SearchCriteria cri);
	
	// insert로직 역시 mapper쪽의 insert를 실행해줄 수 있는 메서드를 만드는게 먼저입니다.
	public void insert(BoardVO vo);
	
	// delete로직은 직접 해 보세요.
	public void delete(Long bno);
	
	// update로직도 mapper에 작성된 로직을 그대로 가져옵니다.
	public void update(BoardVO vo);
	
	// detail 로직도 mapper에 작성된 로직을 그대로 가져옵니다.
	public BoardVO getDetail(Long bno);
	
	// 따로 달라질 내용이 없으므로 mapper에서 그대로 가져옵니다.
	public Long getBoardCount(SearchCriteria cri);
	
	// 특정 글 번호 입력시 해당 글번호에 연동된 첨부파일 목록 가져오기
	public List<BoardAttachVO> getAttachList(Long bno);
	
}
