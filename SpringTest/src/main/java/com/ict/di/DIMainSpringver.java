package com.ict.di;

import org.springframework.context.support.GenericXmlApplicationContext;

import com.ict.di.classfile.Broadcast;
import com.ict.di.classfile.Satallite;
import com.ict.di.classfile.Singer;
import com.ict.di.classfile.Stage;

public class DIMainSpringver {

	public static void main(String[] args) {
		// �� �����̳ʿ� ����ִ� ��ü�� ���������� ȣ��� ����
		// ��ǥ �� �����̳�(root-context.xml)�� ��θ� �����༭
		GenericXmlApplicationContext context = new GenericXmlApplicationContext("file:src/main/webapp/WEB-INF/spring/root-context.xml");
		
		// Singer���� �ٷ� ���̷�Ʈ�� Stage�� �����ڽ��ϴ�.
		//Stage stage = context.getBean("stage", Stage.class);
		//stage.perform();
		
		
		//Singer singer = context.getBean("singer", Singer.class);
		//singer.sing();
		
		//Broadcast broadcast = context.getBean("broadcast", Broadcast.class);
		//broadcast.broad();
		
		Satallite satallite = context.getBean("satallite", Satallite.class);
		satallite.satelliteBraod();
	}

}
