package com.cjburkey.monopoly.state;

import java.util.ArrayList;
import java.util.List;
import com.cjburkey.monopoly.Monopoly;
import com.cjburkey.monopoly.object.GameObject;
import com.cjburkey.monopoly.state.states.GameStateMainGame;
import com.cjburkey.monopoly.state.states.GameStateMainMenu;
import javafx.scene.canvas.GraphicsContext;

public class GameStateManager {
	
	private static final List<GameState> states = new ArrayList<GameState>();
	private GameState currentState = null;
	
	public void init() {
		GameState mainMenu = new GameStateMainMenu();
		GameState mainGame = new GameStateMainGame();
		
		addGameState(mainMenu);
		addGameState(mainGame);
		
		GameObject.initObjs();
		
		setGameState(mainGame);
	}
	
	public void setGameState(GameState into) {
		GameState state = null;
		for(GameState s : states) {
			if(into.equals(s)) {
				state = s;
			}
		}
		
		if(state != null && state != this.currentState) {
			GameState prev = this.currentState;
			Monopoly.log("Got previous GameState.");
			
			if(this.currentState != null) this.currentState.exitState(state);
			Monopoly.log("Exited previous GameState.");
			
			this.currentState = state;
			Monopoly.log("Set GameState: " + this.currentState);
			
			this.currentState.enterState(prev);
			Monopoly.log("Entered GameState.");
		} else if(state == null) {
			Monopoly.log("Could not set next game state.  It was not found in the list of states.");
		} else if(state != this.currentState) {
			Monopoly.log("Could not set next game state.  That is already the current state.");
		}
	}
	
	public void addGameState(GameState state) {
		states.add(state);
		Monopoly.log("Added GameState: " + state);
	}
	
	public void removeGameState(GameState state) {
		states.remove(state);
		Monopoly.log("Removed GameState: " + state);
	}
	
	public void tick(float delta) {
		if(this.currentState != null) {
			this.currentState.tick(delta);
		}
	}
	
	public void perSecond(int fps) {
		if(this.currentState != null) {
			this.currentState.perSecond(fps);
		}
	}
	
	public void render(float delta, GraphicsContext gc) {
		if(this.currentState != null) {
			this.currentState.render(delta, gc);
		}
	}
	
}