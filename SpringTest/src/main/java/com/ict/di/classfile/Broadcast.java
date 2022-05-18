package com.ict.di.classfile;

import org.springframework.stereotype.Component;

@Component
public class Broadcast {
	
	// Stage�� �־�� ����� �����ϹǷ� ����
	// ���뿡 ���� ������ ������ �� �ֵ��� ��������� ���븦 �����մϴ�.
	private Stage stage;
	
	// �����ڴ� Stage�� �Է¹޾ƾ߸� �۵��ϵ��� �����մϴ�.
	public Broadcast(Stage stage) {
		this.stage = stage;
		
	}
	// ��ۿ� ������ ��ü������ ���
	// ���Ĵ� stage�� perform�޼��尡 ���
	public void broad() {
		System.out.print("(��ۿ�)");
		this.stage.perform();
	}
}
