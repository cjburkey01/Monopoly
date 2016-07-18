package com.cjburkey.monopoly.render.gui.elements;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;

public class GuiButtonCentered extends GuiButton {

	public GuiButtonCentered(Point2D pos, Runnable click, String text) {
		super(pos, click, text);
		
		double width = this.getPosition().getWidth();
		double height = this.getPosition().getHeight();
		Rectangle2D rect = new Rectangle2D(pos.getX() - width / 2, pos.getY() - height / 2, width, height);
		this.setPosition(rect);
	}
	
}