package com.cjburkey.monopoly.state;

import com.cjburkey.monopoly.Monopoly;
import javafx.scene.canvas.GraphicsContext;

public class GameStateMainMenu extends GameState {

	public GameStateMainMenu() {
		super("gameStateMainMenu");
	}
	
	public void tick(float delta) {  }
	public void perSecond(int fps) { Monopoly.log(fps + " FPS"); }
	public void render(float delta, GraphicsContext gc) {  }
	public void enterState(GameState previous) {  }
	public void exitState(GameState next) {  }
	
}