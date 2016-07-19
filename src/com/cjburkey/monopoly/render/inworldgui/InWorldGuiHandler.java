package com.cjburkey.monopoly.render.inworldgui;

import java.util.ArrayList;
import java.util.List;
import com.cjburkey.monopoly.Monopoly;
import com.cjburkey.monopoly.state.GameState;
import javafx.scene.canvas.GraphicsContext;

public class InWorldGuiHandler {
	
	private GameState state;
	private List<IWElement> elements = new ArrayList<IWElement>();
	
	public InWorldGuiHandler(GameState gameState) {
		this.state = gameState;
	}
	
	public void render(GraphicsContext gc) {
		if(Monopoly.getStateManager().getCurrent().equals(this.getGameState())) {
			for(IWElement e : elements) {
				e.render(gc);
			}
		}
	}
	
	public GameState getGameState() {  return this.state; }
	
}