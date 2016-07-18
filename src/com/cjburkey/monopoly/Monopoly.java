package com.cjburkey.monopoly;

import java.util.Scanner;
import com.cjburkey.monopoly.gameloop.MainLoop;
import com.cjburkey.monopoly.handler.MouseHandler;
import com.cjburkey.monopoly.render.gui.GuiHandler;
import com.cjburkey.monopoly.state.GameStateManager;
import com.cjburkey.monopoly.util.Logger;
import com.cjburkey.monopoly.util.SemVer;
import com.cjburkey.monopoly.window.GameScene;
import com.cjburkey.monopoly.window.GameWindow;
import javafx.application.Application;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Monopoly extends Application {
	
	public static final SemVer GAME_VERSION = new SemVer("0.0.0", false);
	private static double transY;
	
	private static GameWindow window;
	private static MainLoop loop;
	private static Logger logger;
	private static GameStateManager stateManager;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	private void asciiArt() throws Exception {
		Scanner s = new Scanner(getClass().getClassLoader().getResourceAsStream("res/fun/startText.txt"));
		while(s.hasNextLine()) {
			String line = s.nextLine();
			System.out.println(line);
		}
		s.close();
	}
	
	public void start(Stage s) {
		logger = new Logger("Monopoly", "[%hour%:%minute%:%second%] %msg%");
		log("Created Logger.");

		getLogger().lineBreak();
		log("\t--[ Version Check ]--");
		log("\t\tHi!  We're running Monopoly version ".toUpperCase() + GAME_VERSION + "!");
		log("\t--[ Finished Version Check ]--");
		getLogger().lineBreak();
		
		try {
			getLogger().lineBreak();
			log("Now some fun art as a test:");
			getLogger().lineBreak();
			asciiArt();
			getLogger().lineBreak();
			log("Did you like it?");
			getLogger().lineBreak();
			
		} catch(Exception e) { log("Ascii art failed to load :\\"); }
		
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
		
		double offsetY = getWindow().getScene().getWindow().getHeight() - getWindow().getScene().getHeight();
		log("Calculated offset on Y.");
		
		getWindow().getScene().getGameCanvas().getGraphicsContext2D().translate(0, offsetY);
		log("Set offset on Y.");
	}
	
	public static final void tick(float delta) {
		getWindow().getScene().setCursor(MouseHandler.getCursor(MouseHandler.cursor));
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
		gc.fillRect(-100, -100, canvas.getWidth() + 100, canvas.getHeight() + 100);
		
		gc.translate(canvas.getWidth() / 2, canvas.getHeight() / 2);
		getStateManager().render(delta, gc);
		gc.translate(-(canvas.getWidth() / 2), -(canvas.getHeight() / 2));
		
		for(GuiHandler h : GuiHandler.getHandlers()) {
			h.drawElements(delta, gc);
		}
	}
	
	public static final void log(Object msg) { logger.log("" + msg); }
	public static final Logger getLogger() { return logger; }
	public static final void closeGame() { getGameLoop().stop(); }
	public static final GameWindow getWindow() { return window; }
	public static final GameScene getScene() { return getWindow().getScene(); }
	public static final MainLoop getGameLoop() { return loop; }
	public static final GameStateManager getStateManager() { return stateManager; }
	
}