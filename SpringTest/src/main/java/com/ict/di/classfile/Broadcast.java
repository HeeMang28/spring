package com.ict.di.classfile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Broadcast {
	
	// Stage�� �־�� ����� �����ϹǷ� ����
	// ���뿡 ���� ������ ������ �� �ֵ��� ��������� ���븦 �����մϴ�.
	//@Autowired
	private Stage stage; // 4 ���� �̻���� ��������� �Ѱ��� autowired�� �������� �ʾƵ� ���� �����մ�. �ΰ� �̻���ʹ� �ȵ�.
	
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
