package com.cjburkey.monopoly.state;

import javafx.scene.canvas.GraphicsContext;

/**
 * Make sure not to implement this class.
 * @author cjburkey
 * @see com.cjburkey.monopoly.state.GameState
 * @deprecated Extend {@link com.cjburkey.monopoly.state.GameState}, do not implement this.
 */
public interface IGameState {
	
	void tick(float delta);
	void perSecond(int fps);
	void render(float delta, GraphicsContext gc);
	void enterState(GameState previous);
	void exitState(GameState next);
	
	String getStateName();
	
}