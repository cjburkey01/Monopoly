package com.cjburkey.monopoly.handler;

import com.cjburkey.monopoly.Monopoly;
import com.cjburkey.monopoly.util.ImageUtil;
import javafx.geometry.Point2D;
import javafx.scene.ImageCursor;
import javafx.scene.image.Image;

public enum MouseHandler {
	
	NORMAL	(false),
	MOVE	(false),
	ZOOM	(false);
	
	private final boolean center;
	MouseHandler(boolean center) {
		this.center = center;
	}
	
	public static final ImageCursor getCursor(MouseHandler handler) {
		Image img = ImageUtil.loadImage("res/cur/" + handler + ".png");
		if(img == null || img.isError()) {
			Monopoly.log("Couldn't load image.");
			img.getException().printStackTrace();
			return null;
		}

		Point2D point = new Point2D(img.getWidth() / 2, img.getHeight() / 2);
		if(!handler.center) point = Point2D.ZERO;
		return new ImageCursor(img, point.getX(), point.getY());
	}
	
	public static MouseHandler cursor = NORMAL;
	
}