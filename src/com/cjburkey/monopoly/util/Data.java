package com.cjburkey.monopoly.util;

public class Data {
	
	private String key;
	private Object value;
	
	public Data(String key) { this.key = key; }
	public Data(String key, Object value) { this.key = key; this.value = value; }
	
	public String getKey() { return this.key; }
	public Object getValue() { return this.value; }
	
	public void setValue(Object value) { this.value = value; }
	
	public Data copy() { return new Data(this.key, this.value); }
	
}