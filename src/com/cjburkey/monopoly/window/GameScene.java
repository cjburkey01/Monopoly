package com.cjburkey.monopoly.window;

import com.cjburkey.monopoly.Monopoly;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;

public class GameScene extends Scene {

	private static boolean init = false;
	
	private Canvas gameDrawing;
	private VBox root;
	private Rectangle2D size = Screen.getPrimary().getVisualBounds();
	
	public GameScene(VBox root) {
		super(root);
		
		this.root = root;
		Monopoly.log("Set GameScene root.");
		
		this.gameDrawing = new Canvas(size.getWidth(), size.getHeight());
		Monopoly.log("Set GameDrawing size.");
		
		init();
	}
	
	public void init() {
		if(init) return;
		init = true;
		
		this.root.getChildren().addAll(gameDrawing);
		Monopoly.log("Added GameDrawing.");
	}
	
	public Canvas getGameCanvas() { return this.gameDrawing; }
	
}