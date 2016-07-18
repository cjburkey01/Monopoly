package com.cjburkey.monopoly.interfaces;

import com.cjburkey.monopoly.state.GameState;
import javafx.scene.canvas.GraphicsContext;

/**
 * Make sure not to implement this class.
 * @author cjburkey
 * @see com.cjburkey.monopoly.state.GameState
 * @deprecated Extend {@link com.cjburkey.monopoly.state.GameState}, do not implement this.
 */
public interface IGameState {
	
	void tick();
	void perSecond(int fps);
	void render( GraphicsContext gc);
	void enterState(GameState previous);
	void exitState(GameState next);
	
	String getStateName();
	
}