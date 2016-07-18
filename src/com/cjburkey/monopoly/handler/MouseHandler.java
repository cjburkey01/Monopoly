package com.cjburkey.monopoly.handler;

import com.cjburkey.monopoly.Monopoly;
import com.cjburkey.monopoly.util.ImageUtil;
import javafx.geometry.Point2D;
import javafx.scene.ImageCursor;
import javafx.scene.image.Image;

public enum MouseHandler {
	
	NORMAL	(false),
	MOVE	(true),
	ZOOM	(true),
	SELECT	(new Point2D(5, 0));
	
	private boolean center;
	private Point2D point = Point2D.ZERO;
	MouseHandler(boolean center) {
		this.center = center;
	}
	MouseHandler(Point2D point) {
		this.point = point;
	}
	
	public static final ImageCursor getCursor(MouseHandler handler) {
		Image img = ImageUtil.loadImage("res/cur/" + handler + ".png", new Point2D(48, 48), false);
		if(img == null || img.isError()) {
			Monopoly.log("Couldn't load image.");
			img.getException().printStackTrace();
			return null;
		}

		Point2D point = new Point2D(img.getWidth() / 2, img.getHeight() / 2);
		if(!handler.center) point = handler.point;
		return new ImageCursor(img, point.getX(), point.getY());
	}
	
	public static MouseHandler cursor = NORMAL;
	
}