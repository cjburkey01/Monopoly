package com.cjburkey.monopoly.interfaces;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;

public interface IGuiElement {
	
	public Rectangle2D getPosition();
	public boolean isHidden();
	
	public void setPosition(Rectangle2D pos);
	public void render(float delta, GraphicsContext gc);
	public void hide();
	public void show();
	public void toggleShow();
	
}