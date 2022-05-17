package com.ict.di.classfile;

public class Stage {
	
	private Singer singer; // 무대에 서는 가수
	
	// 무대는 가수가 있어야 성립합니다.
	public Stage(Singer singer) {
		this.singer = singer; // 무대에 설 가수를 입력해야 생성자 실행이 가능하게 처리
	}
	
	public void perform() {
		System.out.print("무대에서 ");
		this.singer.sing();
	}
}
