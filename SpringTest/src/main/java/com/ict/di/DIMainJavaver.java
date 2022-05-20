package com.ict.di;

import com.ict.di.classfile.BalladSinger;
import com.ict.di.classfile.Broadcast;
import com.ict.di.classfile.HiphopSinger;
import com.ict.di.classfile.Satallite;
import com.ict.di.classfile.Singer;
import com.ict.di.classfile.Stage;

public class DIMainJavaver {

	public static void main(String[] args) {
		
		BalladSinger singer = new BalladSinger();
		singer.sing();
		
		Stage stage = new Stage(singer);
		stage.perform();
		
		Broadcast broadcast = new Broadcast(stage);
		broadcast.broad();
		
		Satallite satallite = new Satallite(broadcast);
		satallite.satelliteBroad();
		


	}

}
