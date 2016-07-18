package com.cjburkey.monopoly.render.gui.elements;

import com.cjburkey.monopoly.render.gui.GuiElement;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.geometry.VPos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class GuiLabel extends GuiElement {
	
	private String text;
	private Font font;
	private Color color;
	private boolean center;
	
	public GuiLabel(Point2D pos) {
		this("", pos, Font.getDefault(), Color.BLACK, false);
	}
	
	public GuiLabel(String label, Point2D pos, Font font, Color color, boolean center) {
		super(new Rectangle2D(pos.getX(), pos.getY(), 0, 0));
		this.font = font;
		this.text = label;
		this.color = color;
		this.center = center;
	}
	
	public void render(float delta, GraphicsContext gc) {
		gc.setTextBaseline(VPos.TOP);
		gc.setFill(this.color);
		gc.setFont(this.font);
		if(this.center) gc.setTextAlign(TextAlignment.CENTER); else gc.setTextAlign(TextAlignment.LEFT);
		gc.fillText(this.text, this.getPosition().getMinX(), this.getPosition().getMinY());
	}
	
	public String getText() { return this.text; }
	public Font getFont() { return this.font; }
	public Color getColor() { return this.color; }
	public boolean isCentered() { return this.center; }
	
	public void setText(String label) { this.text = label; }
	public void setFont(Font font) { this.font = font; }
	public void setColor(Color color) { this.color = color; }
	public void setCentered(boolean center) { this.center = center; }
	
}