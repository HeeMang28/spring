package com.ict.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ict.mapper.BoardMapper;
import com.ict.mapper.ReplyMapper;
import com.ict.persistence.ReplyVO;

@Service
public class ReplyServiceImpl implements ReplyService {
	
	// 서비스는 매퍼를 호출하기때문에 매퍼 생성
	@Autowired
	private ReplyMapper mapper;
	
	@Autowired
	private BoardMapper boardMapper; 
	
	public void addReply(ReplyVO vo) {
		Long bno = vo.getBno();
		mapper.create(vo);
		boardMapper.updateReplyCount(bno, +1);
	}
	
	@Override
	public List<ReplyVO> listReply(Long bno){
		return mapper.getList(bno);
	}
	
	@Override
	public void modifyReply(ReplyVO vo) {
		mapper.update(vo);
	}
	
	@Override
	@Transactional
	public void removeReply(Long rno) {
		// 내가 삭제하는 댓글이 몇 번 글에 달려있던건지 조회
		// mapper.delete가 실행되는순간, bno를 포함한 모든 rno번 로우가 날아감.
		// 먼저 bno를 얻어서 저장까지 한 다음, rno번 로우 삭제를 해야 마지막 로직 시랳ㅇ가능
		// 댓글 카운팅을 하기 위해서는 해당 댓글이 달려있던 bno에 대한 정보가 필요합니다.
		// 댓글이 삭제된 다음 bno를 얻어올 수는 없기때문에 제일 먼저 bno부터 얻어옵니다.
		Long bno = mapper.getBno(rno);
		// 댓글 삭제
		mapper.delete(rno);
		// 댓글 삭제 후에 updateReplyCount를 실행해 해당 bno번 글 정보의 댓글개수를 1개 차감
		// 테스트삼아 댓글 삭제를 SQLDeveloper에서 했을 경우, commit을 반드시 해 주신 다음
		// 해당 로직을 테스트해야 정상적으로 서버가 처리됩니다. commit을 안 하면
		// pending상태가 유지됩니다.
		boardMapper.updateReplyCount(bno, -1);
	}
	
}
