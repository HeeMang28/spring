package com.ict.mapper;

import com.ict.persistence.MemberVO;

public interface MemberMapper {

		public MemberVO read(String userid);
		
		public void insertMemberTbl(MemberVO vo);
		
		public void insertMemberAuth(MemberVO vo);
}

