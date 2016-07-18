package com.cjburkey.monopoly.state;

import javafx.scene.canvas.GraphicsContext;

public class GameStateEXAMPLE extends GameState {

	public GameStateEXAMPLE() {
		super("gameStateExample");
	}
	
	public void tick() {  }
	public void perSecond(int fps) {  }
	public void render(GraphicsContext gc) {  }
	public void enterState(GameState previous) {  }
	public void exitState(GameState next) {  }
	
}