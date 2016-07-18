package com.cjburkey.monopoly.state.states;

import com.cjburkey.monopoly.Monopoly;
import com.cjburkey.monopoly.render.gui.GuiHandler;
import com.cjburkey.monopoly.render.gui.elements.GuiButtonCentered;
import com.cjburkey.monopoly.state.GameState;
import com.cjburkey.monopoly.state.GameStateManager;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class GameStateMainMenu extends GameState {
	
	GuiHandler handler;

	public GameStateMainMenu() {
		super("gameStateMainMenu");
	}
	
	public void tick(float delta) {  }
	public void perSecond(int fps) {  }
	public void render(float delta, GraphicsContext gc) {
		//Canvas c = Monopoly.getWindow().getScene().getGameCanvas();
		
		//gc.setFill(Color.BLACK);
		//gc.fillRect(-c.getWidth() / 2, -c.getWidth() / 2, c.getWidth(), c.getWidth());
	}
	
	public void enterState(GameState previous) {
		handler = new GuiHandler(this);
		GuiHandler.addGuiHandler(handler);
		Canvas c = Monopoly.getWindow().getScene().getGameCanvas();
		GameStateManager stateManager = Monopoly.getStateManager();
		
		GuiButtonCentered play = new GuiButtonCentered(new Point2D(0, 0), () -> { stateManager.setGameState(GameStateManager.mainGame); },
				"Play Game", 15, c.getWidth() - 30);
		GuiButtonCentered quit = new GuiButtonCentered(new Point2D(0, 0), () -> { Monopoly.closeGame(); }, "Close Game", 15, c.getWidth() - 30);
		
		handler.addElement(play);
		handler.addElement(quit);
		
		play.setPosition(new Point2D(c.getWidth() / 2, c.getHeight() / 2 - play.getPosition().getHeight() / 2));
		quit.setPosition(new Point2D(c.getWidth() / 2, c.getHeight() / 2 + quit.getPosition().getHeight() / 2));
		
		//play.setColorScheme(Color.BLACK, Color.gray(0.75d), Color.WHITE, Color.gray(0.25d));
		play.setColorScheme(Color.WHITE, Color.rgb(0, 0, 100), Color.WHITE, Color.rgb(0, 0, 150));
		quit.setColorScheme(Color.WHITE, Color.rgb(100, 0, 0), Color.WHITE, Color.rgb(150, 0, 0));
		
		play.show();
		quit.show();
	}
	
	public void exitState(GameState next) {
		handler.clear();
		Monopoly.log("Cleared gui.");
		GuiHandler.removeHandler(handler);
		handler = null;
		Monopoly.log("Disabled GUI handler.");
	}
	
}