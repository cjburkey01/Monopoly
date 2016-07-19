package com.cjburkey.monopoly.util;

import java.util.concurrent.ThreadLocalRandom;

public class Maths {
	
	public static final double clamp(double value, double min, double max) {
		if(value > min && value < max) return value;
		if(value <= min) return min;
		if(value >= max) return max;
		return value;
	}
	
	public static final int randomRange(int min, int max) {
		return ThreadLocalRandom.current().nextInt(min, max + 1);
	}
	
}