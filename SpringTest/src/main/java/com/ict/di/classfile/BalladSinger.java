package com.ict.di.classfile;

import org.springframework.stereotype.Component;

@Component
public class BalladSinger extends Singer {
	
	@Override
	public void sing() {
		System.out.println("�߶�� ������ �뷡�� �մϴ�");
	}

}
