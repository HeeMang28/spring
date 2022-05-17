package com.ict.di;

import com.ict.di.classfile.Singer;
import com.ict.di.classfile.Stage;

public class DIMainJavaver {

	public static void main(String[] args) {
		
		Singer s1 = new Singer();
		s1.sing();
		
		Stage sta1 = new Stage(s1);
		sta1.perform();
		

	}

}
