package com.cjburkey.monopoly.render.gui;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class GuiLabel extends GuiElement {
	
	private String text;
	
	public GuiLabel(Point2D pos) {
		this("", pos);
	}
	
	public GuiLabel(String label, Point2D pos) {
		super(new Rectangle2D(pos.getX(), pos.getY(), 0, 0));
		this.text = label;
	}
	
	public void render(float delta, GraphicsContext gc) {
		gc.setFill(Color.BLACK);
		gc.fillText(this.text, this.getPosition().getMinX(), this.getPosition().getMinY());
	}
	
	public String getText() { return this.text; }
	public void setText(String label) { this.text = label; }
	
}