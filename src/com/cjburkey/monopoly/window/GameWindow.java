package com.cjburkey.monopoly.window;

import com.cjburkey.monopoly.Monopoly;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class GameWindow {
	
	private GameScene gameScene;
	private Stage gameStage;
	
	public GameWindow(Stage s, double width, double height) {
		VBox root = new VBox();
		this.gameScene = new GameScene(root, width, height);
		this.gameStage = s;
	}
	
	public void init() {
		this.getStage().setScene(this.getScene());
		Monopoly.log("Set GameWindow scene.");
		
		this.getStage().sizeToScene();
		Monopoly.log("Set GameWindow size.");
		
		this.getStage().centerOnScreen();
		Monopoly.log("Centered GameWindow.");
		
		this.getStage().setResizable(false);
		Monopoly.log("Disabled GameWindow's Resizability.");
		
		this.getStage().setTitle("jNopoly");
		Monopoly.log("Set GameWindow's title.");
		
		this.getStage().show();
		Monopoly.log("Displayed GameWindow.");

		this.getStage().setFullScreenExitHint("");
		this.getStage().setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
		this.getStage().setFullScreen(true);
		
		this.getStage().addEventHandler(WindowEvent.WINDOW_CLOSE_REQUEST, e -> {
			e.consume();
			Monopoly.closeGame();
		});
		Monopoly.log("Registered GameWindow OnCloseRequest handler.");
	}
	
	public GameScene getScene() { return this.gameScene; }
	public Stage getStage() { return this.gameStage; }
	
}