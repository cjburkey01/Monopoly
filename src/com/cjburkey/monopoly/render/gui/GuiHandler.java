package com.cjburkey.monopoly.render.gui;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.canvas.GraphicsContext;

public class GuiHandler {
	
	private final List<GuiElement> elements = new ArrayList<GuiElement>();
	private static final List<GuiHandler> handlers = new ArrayList<GuiHandler>();
	
	public void drawElements(float delta, GraphicsContext gc) {
		for(GuiElement e : elements) {
			if(!e.isHidden()) {
				e.render(delta, gc);
			}
		}
	}
	
	public void addElement(GuiElement e) {
		elements.add(e);
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
	
	public static final void addGuiHandler(GuiHandler h) {
		handlers.add(h);
	}
	
	public static final GuiHandler[] getHandlers() {
		GuiHandler[] hs = new GuiHandler[handlers.size()];
		hs = handlers.toArray(hs);
		return hs;
	}
	
}