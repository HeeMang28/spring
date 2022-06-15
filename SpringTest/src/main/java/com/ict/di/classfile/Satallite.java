package com.ict.di.classfile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Satallite {
	
	@Autowired
	private Broadcast broadcast;
	
	public Satallite(Broadcast broadcast) {
		this.broadcast = broadcast;
	}
	public void satelliteBroad() {
		broadcast.broad();
	}
}
