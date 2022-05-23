package com.ict.di;

import org.springframework.context.support.GenericXmlApplicationContext;

import com.ict.di.classfile.BalladSinger;
import com.ict.di.classfile.Broadcast;
import com.ict.di.classfile.Satallite;
import com.ict.di.classfile.Singer;
import com.ict.di.classfile.Stage;

public class DIMainSpringver {

	public static void main(String[] args) {
		// root-context�� ��������� �̸� ���� �迭�� ��θ� �����α�.
		String[] path = { "file:src/main/webapp/WEB-INF/spring/root-context.xml", "file:src/main/webapp/WEB-INF/spring/root-context2.xml"};
		// �� �����̳ʿ� ����ִ� ��ü�� ���������� ȣ��� ����
		// ��ǥ �� �����̳�(root-context.xml)�� ��θ� �����༭
		//GenericXmlApplicationContext context = new GenericXmlApplicationContext("file:src/main/webapp/WEB-INF/spring/root-context.xml", "file:src/main/webapp/WEB-INF/spring/root-context2.xml");
		GenericXmlApplicationContext context = new GenericXmlApplicationContext("file:src/main/webapp/WEB-INF/spring/root-*.xml");
		
		// Singer���� �ٷ� ���̷�Ʈ�� Stage�� �����ڽ��ϴ�.
		//Stage stage = context.getBean("stage", Stage.class);
		//stage.perform();
		
		
		//Singer singer = context.getBean("singer", Singer.class);
		//singer.sing();
		
		//Broadcast broadcast = context.getBean("broadcast", Broadcast.class);
		//broadcast.broad();
		
		//Satallite satallite = context.getBean("satallite", Satallite.class);
		//satallite.satelliteBraod();
		
		// balladStage�� �����ٴ�.
		//Stage stage = context.getBean("balladStage", Stage.class);
		//stage.perform();
		
		Broadcast balladBroadcast = context.getBean("balladBroadcast", Broadcast.class);
		balladBroadcast.broad();
		
		//Broadcast hiphopBroadcast = context.getBean("hiphopBroadcast", Broadcast.class);
		//hiphopBroadcast.broad();
		
		 //Satallite balladSat = context.getBean("balladSatallite", Satallite.class);
		 //Satallite hiphopSat = context.getBean("hiphopSatallite", Satallite.class);
		 
		 //balladSat.satelliteBroad();
		 //hiphopSat.satelliteBroad();
	}

}
