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
	
	public static final void init() {
		imgCurNormal = ImageUtil.loadImage("res/cur/NORMAL.png", new Point2D(48, 48), true);
		imgCurMove = ImageUtil.loadImage("res/cur/MOVE.png", new Point2D(48, 48), true);
		imgCurZoom = ImageUtil.loadImage("res/cur/ZOOM.png", new Point2D(48, 48), true);
		imgCurSelect = ImageUtil.loadImage("res/cur/SELECT.png", new Point2D(48, 48), true);
		
		imgIconPerson = ImageUtil.loadImage("res/avatar/png/boy-1.png", new Point2D(512, 512), true);
	}
	
}