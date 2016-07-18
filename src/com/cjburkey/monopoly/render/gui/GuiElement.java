package com.cjburkey.monopoly.render.gui;

import com.cjburkey.monopoly.interfaces.IGuiElement;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;

public class GuiElement implements IGuiElement {
	
	private Rectangle2D position;
	private boolean hidden = true;;
	
	public GuiElement() { position = Rectangle2D.EMPTY; }
	public GuiElement(Rectangle2D pos) { this.position = pos; }
	
	public Rectangle2D getPosition() { return this.position; }
	public boolean isHidden() { return this.hidden; }
	
	public void setPosition(Rectangle2D pos) { this.position = pos; }
	public void render(GraphicsContext gc) {  }
	public void hide() { this.hidden = true; }
	public void show() { this.hidden = false; }
	public void toggleShow() { if(this.isHidden()) this.show(); else this.hide(); }
	
}