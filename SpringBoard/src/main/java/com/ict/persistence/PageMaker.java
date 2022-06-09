package com.ict.persistence;

import lombok.Data;

@Data
public class PageMaker {
	
	private int totalBoard;
	private int startPage;
	private int endPage;
	private boolean prev;
	private boolean next;
	private int displayPageNum;
	// 현재 조회중인 페이지 정보를 획득하기 위해 선언
	private Criteria cri;
	
	public void calcData() {
		this.displayPageNum = 10;
		
		this.endPage = (int)(Math.ceil(cri.getPage() / 
				(double) displayPageNum) * displayPageNum);
		
		this.startPage = (endPage - displayPageNum) + 1;
		
		int tempEndPage = (int)(Math.ceil(totalBoard / (double)cri.getNumber()));
		
		if(endPage > tempEndPage) {
			endPage = tempEndPage;
		}
		
		this.prev = this.startPage == 1 ? false : true;
		
		this.next = this.endPage * cri.getNumber() >= totalBoard ? false : true;
	}
	public void setTotalBoard(int totalBoard) {
		this.totalBoard = totalBoard;
		
		calcData();
	}

}
