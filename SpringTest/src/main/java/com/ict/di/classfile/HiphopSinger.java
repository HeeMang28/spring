package com.ict.di.classfile;

import org.springframework.stereotype.Component;

@Component
public class HiphopSinger extends Singer {
	
	@Override
	public void sing() {
		System.out.println("���� ������ ���� �մϴ�.");
	}

}
