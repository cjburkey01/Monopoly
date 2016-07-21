package com.cjburkey.monopoly.img;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;

public class TextureManager {
	
	public static Image imgCurNormal;
	public static Image imgCurMove;
	public static Image imgCurZoom;
	public static Image imgCurSelect;
	
	//public static Image imgIconPerson;
	public static Image[] avatarBoy = new Image[6];
	public static Image[] avatarGirl = new Image[8];
	public static Image[] avatarMan = new Image[2];
	public static Image unknown;
	
	public static Image imgDice0;
	public static Image imgDice1;
	public static Image imgDice2;
	public static Image imgDice3;
	public static Image imgDice4;
	public static Image imgDice5;
	public static Image imgDice6;
	
	public static final void init() {
		imgCurNormal = ImageUtil.loadImage("res/cur/NORMAL.png", new Point2D(48, 48), true);
		imgCurMove = ImageUtil.loadImage("res/cur/MOVE.png", new Point2D(48, 48), true);
		imgCurZoom = ImageUtil.loadImage("res/cur/ZOOM.png", new Point2D(48, 48), true);
		imgCurSelect = ImageUtil.loadImage("res/cur/SELECT.png", new Point2D(48, 48), true);
		
		//imgIconPerson = ImageUtil.loadImage("res/avatar/boy-1.png", new Point2D(512, 512), true);
		
		imgDice0 = ImageUtil.loadImage("res/dice/0.png", new Point2D(512, 512), true);
		imgDice1 = ImageUtil.loadImage("res/dice/1.png", new Point2D(512, 512), true);
		imgDice2 = ImageUtil.loadImage("res/dice/2.png", new Point2D(512, 512), true);
		imgDice3 = ImageUtil.loadImage("res/dice/3.png", new Point2D(512, 512), true);
		imgDice4 = ImageUtil.loadImage("res/dice/4.png", new Point2D(512, 512), true);
		imgDice5 = ImageUtil.loadImage("res/dice/5.png", new Point2D(512, 512), true);
		imgDice6 = ImageUtil.loadImage("res/dice/6.png", new Point2D(512, 512), true);
		
		for(int i = 0; i < avatarBoy.length; i ++) {
			String url = "res/avatar/boy";
			if(i != 0) url += "-" + i;
			url += ".png";
			avatarBoy[i] = ImageUtil.loadImage(url, new Point2D(512, 512), true);
		}
		
		for(int i = 0; i < avatarGirl.length; i ++) {
			String url = "res/avatar/girl";
			if(i != 0) url += "-" + i;
			url += ".png";
			avatarGirl[i] = ImageUtil.loadImage(url, new Point2D(512, 512), true);
		}
		
		for(int i = 0; i < avatarMan.length; i ++) {
			String url = "res/avatar/man";
			if(i != 0) url += "-" + i;
			url += ".png";
			avatarMan[i] = ImageUtil.loadImage(url, new Point2D(512, 512), true);
		}
		
		unknown = ImageUtil.loadImage("res/icons/avatar.png", new Point2D(512, 512), true);
	}
	
}