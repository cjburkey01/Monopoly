package com.cjburkey.monopoly.state;

import javafx.scene.canvas.GraphicsContext;

/**
 * Extend this class, DO NOT IMPLEMENT {@link com.cjburkey.monopoly.state.IGameState}
 * @author cjburkey
 */
@SuppressWarnings("deprecation")
public class GameState implements IGameState {
	
	private String name;
	
	public GameState(String name) { this.name = name; }
	public void tick(float delta) {  }
	public void perSecond(int fps) {  }
	public void render(float delta, GraphicsContext gc) {  }
	public void enterState(GameState previous) {  }
	public void exitState(GameState next) {  }
	
	public String getStateName() {
		return this.name;
	}
	
	public String toString() {
		return this.getStateName();
	}
	
}