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
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * Pre-patch 7
 * @author cjburkey
 */
public class Monopoly extends Application {
	
	public static final SemVer GAME_VERSION = new SemVer(0, 0, 0, false);
	
	private static GameWindow window;
	private static MainLoop loop;
	private static Logger logger;
	private static GameStateManager stateManager;
	
	public static boolean guiScreenOpen = false;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	private void asciiArt() throws Exception {
		new Thread(() -> {
			Scanner s = new Scanner(getClass().getClassLoader().getResourceAsStream("res/fun/startText.txt"));
			while(s.hasNextLine()) {
				String line = s.nextLine();
				System.out.println(line);
				try {
					Thread.sleep(25);
				} catch (Exception e) {  }
			}
			s.close();
		}).start();
	}
	
	private static final void init(Stage s, double width, double height) {
		Thread.setDefaultUncaughtExceptionHandler((t, e) -> {
			genericError(e);
		});
		
		logger = new Logger("Monopoly", "[%hour%:%minute%:%second%] %msg%");
		log("Created Logger.");

		getLogger().lineBreak();
		log("\t--[ Version Check ]--");
		log("\t\tHi!  We're running jNopoly version v" + GAME_VERSION + "!");
		log("\t--[ Finished Version Check ]--");
		getLogger().lineBreak();
		
		stateManager = new GameStateManager();
		log("Created GameStateManager.");
		
		window = new GameWindow(s, width, height);
		log("Created GameWindow.");
		
		loop = MainLoop.createGameLoop();
		log("Created and started MainGameLoop.");
		
		stateManager.init();
		log("Initialized GameStateManager.");
		
		window.init();
		log("Initialized GameWindow.");
	}
	
	public void start(Stage s) {
		try {
			final Rectangle2D size = Screen.getPrimary().getVisualBounds();
			init(s, size.getWidth() / 1, size.getHeight() / 1);
			
			try {
				getLogger().lineBreak();
				log("Now some fun art as a test:");
				asciiArt();
			} catch(Exception e) { log("Ascii art failed to load :\\"); }
		} catch(Exception e) {
			genericError(e);
		}
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
	
	public static final void genericError(Throwable t) {
		t.printStackTrace();
		log("An error occurred.");
		log("Taht no güd..");
		System.exit(-1);
	}
	
	public static final void log(Object msg) { logger.log("" + msg); }
	public static final Logger getLogger() { return logger; }
	public static final GameWindow getWindow() { return window; }
	public static final GameScene getScene() { return getWindow().getScene(); }
	public static final MainLoop getGameLoop() { return loop; }
	public static final GameStateManager getStateManager() { return stateManager; }

	public static final void closeGame() {
		exitLoop(true);
		getWindow().getStage().close();
	}
	private static final void exitLoop(boolean killGameToo) { getGameLoop().stop(killGameToo); }
	
}