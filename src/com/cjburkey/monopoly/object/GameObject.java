package com.cjburkey.monopoly.object;

import java.util.ArrayList;
import java.util.List;
import com.cjburkey.monopoly.interfaces.IGameObject;
import com.cjburkey.monopoly.object.objects.GameObjectBill;
import com.cjburkey.monopoly.object.objects.GameObjectBoardSlot;
import com.cjburkey.monopoly.object.objects.GameObjectDice;
import com.cjburkey.monopoly.object.objects.GameObjectGameBoard;
import com.cjburkey.monopoly.object.objects.GameObjectPlayer;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;

public class GameObject implements IGameObject {
	
	private String name;
	private Point2D size;
	
	public static GameObject gameObjectGameBoard;
	public static GameObject gameObjectBoardSlot;
	public static GameObject gameObjectPlayer;
	public static GameObject gameObjectDice;
	public static GameObject gameObjectBill;
	
	public GameObject(String name) {
		this.name = name;
		size = new Point2D(32, 32);
	}
	
	public void onAdd(GameObjectInst inst) {  }
	public void tick(GameObjectInst inst) {  }
	public void perSecond(int fps, GameObjectInst inst) {  }
	public void render(GraphicsContext gc, GameObjectInst inst) {  }
	public void setSize(Point2D size) { this.size = size; }
	public String getName() { return this.name; }
	public Point2D getSize() { return this.size; }
	
	public static final List<GameObject> gameObjs = new ArrayList<GameObject>();
	
	public static final void initObjs() {
		gameObjectGameBoard = new GameObjectGameBoard();
		gameObjectBoardSlot = new GameObjectBoardSlot();
		gameObjectPlayer = new GameObjectPlayer();
		gameObjectDice = new GameObjectDice();
		gameObjectBill = new GameObjectBill();
	}
	
}