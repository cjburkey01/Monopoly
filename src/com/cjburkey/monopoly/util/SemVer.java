package com.cjburkey.monopoly.util;

public class SemVer {

	private int major = 0;
	private int minor = 0;
	private int patch = 0;
	
	private boolean editable = true;
	
	public SemVer(int major, int minor, int patch) {
		this(major, minor, patch, true);
	}
	
	public SemVer(String string) {
		this(string, true);
	}
	
	public SemVer(int major, int minor, int patch, boolean editable) {
		this.major = major;
		this.minor = minor;
		this.patch = patch;
		
		this.editable = editable;
	}
	
	public SemVer(String string, boolean editable) {
		verFromString(string, '.');
		this.editable = editable;
	}
	
	public void verFromString(String string, char separator) {
		if(this.isEditable()) {
			String[] split = string.split("" + separator);
			if(split.length >= 3) {
				try {
					this.setMajor(Integer.parseInt(split[0].trim()));
					this.setMinor(Integer.parseInt(split[1].trim()));
					this.setPatch(Integer.parseInt(split[2].trim()));
				} catch(Exception e) {  }
			}
		}
	}
	
	public int getMajor() { return this.major; }
	public int getMinor() { return this.minor; }
	public int getPatch() { return this.patch; }
	public boolean isEditable() { return this.editable; }
	
	public void setMajor(int major) { if(isEditable()) this.major = major; }
	public void setMinor(int minor) { if(isEditable()) this.minor = minor; }
	public void setPatch(int patch) { if(isEditable()) this.patch = patch; }
	
	public String toString() { return major + "." + minor + "." + patch; }
	
}