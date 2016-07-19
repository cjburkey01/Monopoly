package com.cjburkey.monopoly.render.inworldgui;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;

public class IWElement {
	
	private Point2D pos;
	
	public IWElement(int x, int y) {
		this.pos = new Point2D(x, y);
	}
	
	public void render(GraphicsContext gc) {
		
	}
	
	public Point2D getPosition() { return this.pos;  }
	
}