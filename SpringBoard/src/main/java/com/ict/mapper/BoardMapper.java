package com.ict.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ict.persistence.BoardVO;
import com.ict.persistence.Criteria;
import com.ict.persistence.SearchCriteria;

public interface BoardMapper {
	
	// board_tbl에서 글번호 3번 이하만 조회하는 쿼리문을
	// 어노테이션을 이용해 작성해주세요.
	// @Select("SELECT * FROM board_tbl WHERE bno < 4")
	public List<BoardVO> getList(SearchCriteria cri);
	
	public Long getBoardCount(SearchCriteria cri);
	// insert구문 실행용으로 메서드를 선언합니다.
	// VO내부에 적혀있는 정보를 이용해 insert를 합니다.
	// BoardVO를 매개로 insert 정보를 전달받음.
	public void insert(BoardVO vo);
	
	// delete를 만들어주세요.
	// 글번호(Long타입) 을 입력받아 해당 글번호를 삭제해줍니다.
	public void delete(Long bno);
	
	public void update(BoardVO vo);
	
	public BoardVO boardDetail(Long bno);
	// 기본적으로 vo로 정보를 모두 보내줘도되지만 단일 변수로 두개 이상 보내고 싶을때는 앞에 @Param을 붙여줘야한다. 아니면 에러 발생
	public void updateReplyCount(@Param("bno") Long bno,
								@Param("amount") int amount);
}
