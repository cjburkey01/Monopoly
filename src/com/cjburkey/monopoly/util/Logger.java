package com.cjburkey.monopoly.util;

import java.util.Calendar;

public class Logger {
	
	private String format;
	private String owner;
	
	public Logger(String owner) { this.owner = owner; }
	public Logger(String owner, String format) {
		this.format = format;
		this.owner = owner;
	}
	
	public void log(String msg) {
		System.out.println("[" + owner + "] " + 
				format
					.replaceAll("%second%", Calendar.getInstance().get(Calendar.SECOND) + "")
					.replaceAll("%minute%", Calendar.getInstance().get(Calendar.MINUTE) + "")
					.replaceAll("%hour%", Calendar.getInstance().get(Calendar.HOUR) + "")
					.replaceAll("%msg%", msg)
		);
	}
	
}