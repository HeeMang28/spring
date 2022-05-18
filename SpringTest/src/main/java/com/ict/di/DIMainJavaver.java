package com.ict.di;

import com.ict.di.classfile.Broadcast;
import com.ict.di.classfile.Singer;
import com.ict.di.classfile.Stage;

public class DIMainJavaver {

	public static void main(String[] args) {
		
		Singer singer = new Singer();
		singer.sing();
		
		Stage stage = new Stage(singer);
		stage.perform();
		
		Broadcast broadcast = new Broadcast(stage);
		broadcast.broad();
		
		

	}

}
