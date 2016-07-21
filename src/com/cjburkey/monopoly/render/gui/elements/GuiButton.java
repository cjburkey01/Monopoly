package com.cjburkey.monopoly.render.gui.elements;

import com.cjburkey.monopoly.Monopoly;
import com.cjburkey.monopoly.handler.CursorHandler;
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
	private Point2D lastMouse = Point2D.ZERO;
	private double padding;
	private double prefWidth;
	
	public Color[] colors = new Color[] {
		Color.BLACK,		// Default text color
		Color.gray(0.75d),	// Default button background
		
		Color.WHITE,		// Hover Text Color
		Color.gray(0.25d),	// Hover button background
	};
	
	private static FontLoader fl = Toolkit.getToolkit().getFontLoader();
	
	public static int cooldown = 3;
	
	public GuiButton(Point2D pos, Runnable click, String text, double padding, double prefWidth) {
		super(new Rectangle2D(pos.getX(), pos.getY(), calcWidth(text, prefWidth, padding), calcHeight(padding)));
		this.onClick = click;
		this.text = text;
	}
	
	public void setColorScheme(Color text, Color back, Color hovText, Color hovBack) {
		colors[0] = text;
		colors[1] = back;
		colors[2] = hovText;
		colors[3] = hovBack;
	}
	
	public void render(GraphicsContext gc) {
		cooldown --;
		
		if(!this.getPosition().contains(lastMouse)) gc.setFill(colors[1]); else gc.setFill(colors[3]);
		gc.fillRect(this.getPosition().getMinX(), this.getPosition().getMinY(), this.getPosition().getWidth(), this.getPosition().getHeight());
		
		if(!this.getPosition().contains(lastMouse)) gc.setFill(colors[0]); else gc.setFill(colors[2]);
		gc.setTextAlign(TextAlignment.CENTER);
		gc.setTextBaseline(VPos.CENTER);
		gc.setFont(Font.font(35));
		gc.fillText(this.text, this.getPosition().getMinX() + this.getPosition().getWidth() / 2, this.getPosition().getMinY() + this.getPosition().getHeight() / 2);
	}
	
	private static double calcWidth(String text, double prefWidth, double padding) {
		return ((prefWidth < 0) ? fl.computeStringWidth(text, Font.font(35)) : prefWidth) + 2 * padding;
	}
	
	private static double calcHeight(double padding) {
		return fl.getFontMetrics(Font.font(35)).getLineHeight() + 2 * padding;
	}
	
	public GuiButton(Point2D pos, Runnable click, String text, double padding) {
		this(pos, click, text, padding, -1);
	}
	
	public void show() {
		super.show();
		
		Monopoly.getWindow().getScene().getGameCanvas().addEventHandler(MouseEvent.MOUSE_RELEASED, e -> {
			Point2D point = new Point2D(e.getX(), e.getY());
			if(cooldown <= 0 && !this.isHidden() && e.getButton().equals(MouseButton.PRIMARY) && this.getPosition().contains(point)) {
				e.consume();
				cooldown = 4;
				this.onClick.run();
			}
		});
		
		Monopoly.getWindow().getScene().getGameCanvas().addEventHandler(MouseEvent.MOUSE_MOVED, e -> {
			Point2D point = new Point2D(e.getX(), e.getY());
			if(!this.isHidden()) {
				if(this.getPosition().contains(point)) {
					CursorHandler.cursor = CursorHandler.SELECT;
				} else if(this.getPosition().contains(lastMouse)) {
					CursorHandler.cursor = CursorHandler.NORMAL;
				}
			}
			lastMouse = point;
		});
	}
	
	public Runnable getOnClick() { return this.onClick; }
	public void setOnClick(Runnable onClick) { this.onClick = onClick; }
	public double getPadding() { return this.padding; }
	public String getText() { return this.text; }
	public double getPrefWidth() { return this.prefWidth; }
	
}