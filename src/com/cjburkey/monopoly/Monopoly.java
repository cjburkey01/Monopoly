package com.cjburkey.monopoly;

import com.cjburkey.monopoly.gameloop.MainLoop;
import com.cjburkey.monopoly.state.GameStateManager;
import com.cjburkey.monopoly.util.Logger;
import com.cjburkey.monopoly.window.GameScene;
import com.cjburkey.monopoly.window.GameWindow;
import javafx.application.Application;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Monopoly extends Application {
	
	private static GameWindow window;
	private static MainLoop loop;
	private static Logger logger;
	private static GameStateManager stateManager;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public void start(Stage s) {
		logger = new Logger("Monopoly", "[%hour%:%minute%:%second%] %msg%");
		log("Created Logger.");
		
		stateManager = new GameStateManager();
		log("Created GameStateManager.");
		
		window = new GameWindow(s);
		log("Created GameWindow.");
		
		loop = MainLoop.createGameLoop();
		log("Created and started MainGameLoop.");
		
		stateManager.init();
		log("Initialized GameStateManager.");
		
		window.init();
		log("Initialized GameWindow.");
	}
	
	public static final void tick(float delta) {
		getStateManager().tick(delta);
	}
	
	public static final void perSecond(int fps) {
		getStateManager().perSecond(fps);
	}
	
	public static final void render(float delta) {
		Canvas canvas = getWindow().getScene().getGameCanvas();
		GraphicsContext gc = canvas.getGraphicsContext2D();
		
		gc.setFill(Color.WHITE);
		gc.setStroke(Color.WHITE);
		gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
		
		getStateManager().render(delta, gc);
	}
	
	public static final void log(Object msg) { logger.log("" + msg); }
	public static final Logger getLogger() { return logger; }
	public static final void closeGame() { getGameLoop().stop(); }
	public static final GameWindow getWindow() { return window; }
	public static final GameScene getScene() { return getWindow().getScene(); }
	public static final MainLoop getGameLoop() { return loop; }
	public static final GameStateManager getStateManager() { return stateManager; }
	
}