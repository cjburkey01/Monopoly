package com.cjburkey.monopoly.render.gui.elements;

import com.cjburkey.monopoly.Monopoly;
import com.cjburkey.monopoly.render.gui.GuiElement;
import com.sun.javafx.tk.FontLoader;
import com.sun.javafx.tk.Toolkit;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.geometry.VPos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class GuiButton extends GuiElement {
	
	private Runnable onClick;
	private String text;
	
	private static FontLoader fl = Toolkit.getToolkit().getFontLoader();
	
	public GuiButton(Point2D pos, Runnable click, String text) {
		super(new Rectangle2D(pos.getX(), pos.getY(), fl.computeStringWidth(text, Font.font(35)), fl.getFontMetrics(Font.font(35)).getLineHeight()));
		this.onClick = click;
		this.text = text;
	}
	
	public void show() {
		super.show();
		
		Monopoly.getWindow().getScene().getGameCanvas().addEventHandler(MouseEvent.MOUSE_PRESSED, e -> {
			Point2D point = new Point2D(e.getX(), e.getY());
			if(e.getButton().equals(MouseButton.PRIMARY) && this.getPosition().contains(point)) {
				this.onClick.run();
			}
		});
	}
	
	public void render(float delta, GraphicsContext gc) {
		gc.setFill(Color.BLACK);
		gc.fillRect(this.getPosition().getMinX(), this.getPosition().getMinY(), this.getPosition().getWidth(), this.getPosition().getHeight());
		
		gc.setFill(Color.WHITE);
		gc.setTextAlign(TextAlignment.CENTER);
		gc.setTextBaseline(VPos.CENTER);
		gc.setFont(Font.font(35));
		gc.fillText(this.text, this.getPosition().getMinX() + this.getPosition().getWidth() / 2, this.getPosition().getMinY() + this.getPosition().getHeight() / 2);
	}
	
	public Runnable getOnClick() { return this.onClick; }
	public void setOnClick(Runnable onClick) { this.onClick = onClick; }
	
}