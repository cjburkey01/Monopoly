package com.cjburkey.monopoly.handler;

import com.cjburkey.monopoly.img.TextureManager;
import javafx.geometry.Point2D;
import javafx.scene.ImageCursor;
import javafx.scene.image.Image;

public enum CursorHandler {
	
	NORMAL	(false, TextureManager.imgCurNormal),
	MOVE	(true, TextureManager.imgCurMove),
	ZOOM	(true, TextureManager.imgCurZoom),
	SELECT	(new Point2D(11, 0), TextureManager.imgCurSelect);
	
	private boolean center;
	private Point2D point = Point2D.ZERO;
	Image img;
	CursorHandler(boolean center, Image img) {
		this.center = center;
		this.img = img;
	}
	CursorHandler(Point2D point, Image img) {
		this.point = point;
		this.img = img;
	}
	
	public static final ImageCursor getCursor(CursorHandler handler) {
		Point2D point = new Point2D(handler.img.getWidth() / 2, handler.img.getHeight() / 2);
		if(!handler.center) point = handler.point;
		return new ImageCursor(handler.img, point.getX(), point.getY());
	}
	
	public static CursorHandler cursor = NORMAL;
	
}