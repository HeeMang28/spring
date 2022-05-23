package com.ict.di;

import org.springframework.context.support.GenericXmlApplicationContext;

import com.ict.di.classfile.BalladSinger;
import com.ict.di.classfile.Broadcast;
import com.ict.di.classfile.Satallite;
import com.ict.di.classfile.Singer;
import com.ict.di.classfile.Stage;

public class DIMainSpringver {

	public static void main(String[] args) {
		// root-context가 여러개라면 미리 문자 배열로 경로를 만들어두기.
		String[] path = { "file:src/main/webapp/WEB-INF/spring/root-context.xml", "file:src/main/webapp/WEB-INF/spring/root-context2.xml"};
		// 빈 컨테이너에 들어있는 객체를 꺼내기위해 호출기 생성
		// 목표 빈 컨테이너(root-context.xml)의 경로를 적어줘서
		//GenericXmlApplicationContext context = new GenericXmlApplicationContext("file:src/main/webapp/WEB-INF/spring/root-context.xml", "file:src/main/webapp/WEB-INF/spring/root-context2.xml");
		GenericXmlApplicationContext context = new GenericXmlApplicationContext("file:src/main/webapp/WEB-INF/spring/root-*.xml");
		
		// Singer없이 바로 다이렉트로 Stage를 만들어보겠습니다.
		//Stage stage = context.getBean("stage", Stage.class);
		//stage.perform();
		
		
		//Singer singer = context.getBean("singer", Singer.class);
		//singer.sing();
		
		//Broadcast broadcast = context.getBean("broadcast", Broadcast.class);
		//broadcast.broad();
		
		//Satallite satallite = context.getBean("satallite", Satallite.class);
		//satallite.satelliteBraod();
		
		// balladStage를 꺼내겟다.
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
