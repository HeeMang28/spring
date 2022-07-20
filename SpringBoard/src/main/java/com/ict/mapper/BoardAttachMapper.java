package com.ict.mapper;

import java.util.List;

import com.ict.persistence.BoardAttachVO;

public interface BoardAttachMapper {

	public void insert(BoardAttachVO vo);
	
	public void delete(String uuid);
	
	public List<BoardAttachVO> findByBno(Long bno);
	
	public void deleteAll(Long bno);
	
<<<<<<< HEAD
	public List<BoardAttachVO> getOldFiles();
	
=======
>>>>>>> ee8fb589ffc06bd2314e15cf3077f15209b9d23a
}
