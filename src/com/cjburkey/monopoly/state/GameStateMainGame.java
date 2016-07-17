package com.cjburkey.monopoly.state;

import com.cjburkey.monopoly.object.ObjInst;
import javafx.scene.canvas.GraphicsContext;

public class GameStateMainGame extends GameState {

	public GameStateMainGame() {
		super("gameStateMainGame");
	}
	
	float offset = 0;
	
	public void tick(float delta) {
		for(ObjInst inst : ObjInst.objInstances) {
			inst.tick(delta);
		}
	}
	
	public void perSecond(int fps) {
		for(ObjInst inst : ObjInst.objInstances) {
			inst.perSecond(fps);
		}
	}
	
	public void render(float delta, GraphicsContext gc) {
		for(ObjInst inst : ObjInst.objInstances) {
			inst.render(delta, gc);
		}
	}
	
	public void enterState(GameState previous) {  }
	public void exitState(GameState next) {  }
	
}