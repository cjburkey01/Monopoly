package com.cjburkey.monopoly.render.gui.elements;

import java.util.ArrayList;
import java.util.List;
import com.cjburkey.monopoly.Monopoly;
import com.cjburkey.monopoly.render.gui.GuiElement;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class GuiScreen extends GuiElement {
	
	private final List<GuiElement> elements = new ArrayList<GuiElement>();
	
	private boolean pauses;
	
	public GuiScreen(Point2D pos, boolean pauses) {
		super(new Rectangle2D(pos.getX() / 2, pos.getY() / 2, pos.getX(), pos.getY()));
		this.pauses = pauses;
	}
	
	public boolean doesPause() { return this.pauses; }
	
	public void show() {
		super.show();
		Monopoly.guiScreenOpen = true;
		for(GuiElement e : elements) {
			e.show();
		}
	}
	
	public void hide() {
		super.hide();
		Monopoly.guiScreenOpen = false;
		for(GuiElement e : elements) {
			e.hide();
		}
	}
	
	public void render(float delta, GraphicsContext gc) {
		gc.setFill(Color.rgb(0, 0, 0, 0.75d));
		gc.fillRect(this.getPosition().getMinX(), this.getPosition().getMinY(), this.getPosition().getWidth(), this.getPosition().getHeight());
		
		for(GuiElement e : elements) {
			if(!e.isHidden()) {
				gc.save();
				e.render(delta, gc);
				gc.restore();
			}
		}
	}
	
	public void clear() {
		for(GuiElement e : elements) {
			e.hide();
			if(e instanceof GuiScreen) {
				((GuiScreen) e).clear();
			}
			e = null;
		}
		elements.clear();
	}
	
	public void addElement(GuiElement e) {
		if(!(e instanceof GuiScreen)) elements.add(e);
	}
	
	public void removeElement(int i) {
		elements.remove(i);
	}
	
	public void removeElement(GuiElement i) {
		elements.remove(i);
	}
	
	public GuiElement getElement(int i) {
		return elements.get(i);
	}
	
	public final GuiElement[] getElements() {
		GuiElement[] elems = new GuiElement[elements.size()];
		elems = elements.toArray(elems);
		return elems;
	}
	
}