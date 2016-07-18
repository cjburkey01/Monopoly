package com.cjburkey.monopoly.img;

import com.cjburkey.monopoly.util.ImageUtil;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;

public class TextureManager {
	
	public static Image imgCurNormal;
	public static Image imgCurMove;
	public static Image imgCurZoom;
	public static Image imgCurSelect;
	
	public static Image imgIconPerson;
	
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
		
		imgIconPerson = ImageUtil.loadImage("res/avatar/png/boy-1.png", new Point2D(512, 512), true);
		
		imgDice0 = ImageUtil.loadImage("res/dice/png/0.png", new Point2D(512, 512), true);
		imgDice1 = ImageUtil.loadImage("res/dice/png/1.png", new Point2D(512, 512), true);
		imgDice2 = ImageUtil.loadImage("res/dice/png/2.png", new Point2D(512, 512), true);
		imgDice3 = ImageUtil.loadImage("res/dice/png/3.png", new Point2D(512, 512), true);
		imgDice4 = ImageUtil.loadImage("res/dice/png/4.png", new Point2D(512, 512), true);
		imgDice5 = ImageUtil.loadImage("res/dice/png/5.png", new Point2D(512, 512), true);
		imgDice6 = ImageUtil.loadImage("res/dice/png/6.png", new Point2D(512, 512), true);
	}
	
}