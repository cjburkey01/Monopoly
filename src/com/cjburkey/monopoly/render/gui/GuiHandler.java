package com.cjburkey.monopoly.render.gui;

import java.util.ArrayList;
import java.util.List;
import com.cjburkey.monopoly.render.gui.elements.GuiScreen;
import com.cjburkey.monopoly.state.GameState;
import javafx.scene.canvas.GraphicsContext;

public class GuiHandler {
	
	private final List<GuiElement> elements = new ArrayList<GuiElement>();
	private static final List<GuiHandler> handlers = new ArrayList<GuiHandler>();
	
	private GameState state;
	
	public GuiHandler(GameState state) {
		this.state = state;
	}
	
	public void drawElements(GraphicsContext gc) {
		for(GuiElement e : elements) {
			if(!e.isHidden()) {
				gc.save();
				e.render(gc);
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
	
	public GameState getState() {
		return this.state;
	}
	
	public static final void removeHandler(GuiHandler h) {
		handlers.remove(h);
	}
	
	public static final void removeHandler(int h) {
		handlers.remove(h);
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