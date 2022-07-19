package com.ict.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ict.mapper.BoardAttachMapper;
import com.ict.mapper.BoardMapper;
import com.ict.mapper.ReplyMapper;
import com.ict.persistence.BoardAttachVO;
import com.ict.persistence.BoardVO;
import com.ict.persistence.Criteria;
import com.ict.persistence.SearchCriteria;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardMapper mapper;
	
	@Autowired
	private ReplyMapper replyMapper;
	
	@Autowired
	private BoardAttachMapper attachMapper;
	

	@Override
	public List<BoardVO> getList(SearchCriteria cri) {
		return mapper.getList(cri);
	}

	@Transactional
	@Override
	public void insert(BoardVO vo) {
		mapper.insert(vo);
		if(vo.getAttachList() == null || vo.getAttachList().size() <= 0) {
			return;
		}
		vo.getAttachList().forEach(attach -> {
			attach.setBno(vo.getBno());
			attachMapper.insert(attach);
		});
	}

	@Transactional
	@Override
	public void delete(Long bno) {
		replyMapper.deleteAll(bno);
		attachMapper.deleteAll(bno);
		mapper.delete(bno);
	}

	@Override
	public void update(BoardVO vo) {
		mapper.update(vo);
	}

	@Override
	public BoardVO getDetail(Long bno) {
		return mapper.getDetail(bno);
	}

	@Override
	public Long getBoardCount(SearchCriteria cri) {
		return mapper.getBoardCount(cri);
	}

	@Override
	public List<BoardAttachVO> getAttachList(Long bno) {
		return attachMapper.findByBno(bno);
	}
	
	
	
}
