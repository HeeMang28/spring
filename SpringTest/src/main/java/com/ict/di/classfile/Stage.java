package com.ict.di.classfile;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Stage {
	
	
	//@Autowired
	//@Qualifier("balladSinger")
	//@Resource(name="balladSinger")
	private Singer singer; // ���뿡 ���� ����
	
	//Qualifier�� ���Ŷ�� �ƹ��͵� �Է¹��� �ʰ� �ƹ����൵ ���� �ʴ� �����ڸ� �߰��ؾ��մϴ�.
	public Stage() {}
	
	// ����� ������ �־�� �����մϴ�.
	//public Stage(Singer singer) {
	//	this.singer = singer; // ���뿡 �� ������ �Է��ؾ� ������ ������ �����ϰ� ó��
	//}
	
	// setter�� �̿��� ������ ����
	public void setSinger(Singer singer) {
		this.singer = singer;
	}
	
	public void perform() {
		System.out.print("���뿡�� ");
		this.singer.sing();
	}
}
