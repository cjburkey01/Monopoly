package com.cjburkey.monopoly.object;

import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;

public class GameObject {
	
	public String name;
	public Point2D size;
	
	public static GameObject gameObjectGameBoard;
	
	public GameObject(String name) {
		this.name = name;
		size = new Point2D(32, 32);
	}
	
	public void onAdd(ObjInst inst) {  }
	public void tick(float delta, ObjInst inst) {  }
	public void perSecond(int fps, ObjInst inst) {  }
	public void render(float delta, GraphicsContext gc, ObjInst inst) {  }
	
	public static final List<GameObject> gameObjs = new ArrayList<GameObject>();
	
	public static final void initObjs() {
		gameObjectGameBoard = new GameObjectGameBoard();
	}
	
}