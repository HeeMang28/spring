package com.ict.di.classfile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Broadcast {
	
	// Stage가 있어야 방송이 가능하므로 먼저
	// 무대에 대한 정보를 저장할 수 있도록 멤버변수로 무대를 선언합니다.
	//@Autowired
	private Stage stage; // 4 버전 이상부터 멤버변수가 한개면 autowired를 선언하지 않아도 빈을 쓸수잇다. 두개 이상부터는 안됨.
	
	// 생성자는 Stage를 입력받아야만 작동하도록 강제합니다.
	public Broadcast(Stage stage) {
		this.stage = stage;
		
	}
	// 방송용 까지만 자체적으로 담당
	// 이후는 stage의 perform메서드가 담당
	public void broad() {
		System.out.print("(방송용)");
		this.stage.perform();
	}
}
