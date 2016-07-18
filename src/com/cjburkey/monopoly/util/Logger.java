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
	
	public void lineBreak() { System.out.println(); }
	
	public void log(String msg) {
		log(msg, false);
	}
	
	public void log(String msg, boolean error) {
		String out = "[" + owner + "] " + format.replaceAll("%second%", Calendar.getInstance().get(Calendar.SECOND) + "")
				.replaceAll("%minute%", Calendar.getInstance().get(Calendar.MINUTE) + "")
				.replaceAll("%hour%", Calendar.getInstance().get(Calendar.HOUR) + "")
				.replaceAll("%msg%", msg);
		if(error) System.err.println(out); else System.out.println(out);
	}
	
}