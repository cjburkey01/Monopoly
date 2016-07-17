package com.cjburkey.monopoly.handler;

import java.util.ArrayList;
import java.util.List;

public class EventHandler {
	
	private String name;
	private Runnable action;
	
	private EventHandler(String name, Runnable action) {
		this.name = name;
		this.action = action;
	}
	
	private static final List<EventHandler> events = new ArrayList<EventHandler>();
	
	public static final void triggerEvent(String name) {
		for(EventHandler e : events) {
			if(e.name.equals(name)) {
				e.action.run();
			}
		}
	}
	
	public static final EventHandler registerEvent(String name, Runnable action) {
		EventHandler handler = new EventHandler(name, action);
		events.add(handler);
		return handler;
	}
	
	public static final void unregisterEvent(EventHandler e) {
		events.remove(e);
	}
	
}