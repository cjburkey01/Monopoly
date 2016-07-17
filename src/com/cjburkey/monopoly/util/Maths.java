package com.cjburkey.monopoly.util;

public class Maths {
	
	public static final double clamp(double value, double min, double max) {
		if(value > min && value < max) return value;
		if(value <= min) return min;
		if(value >= max) return max;
		return value;
	}
	
}