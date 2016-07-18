package com.cjburkey.monopoly.object;

import java.util.ArrayList;
import java.util.List;
import com.cjburkey.monopoly.interfaces.IGameObject;
import com.cjburkey.monopoly.object.instance.ObjectInstance;
import com.cjburkey.monopoly.object.objects.GameObjectGameBoard;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;

public class GameObject implements IGameObject {
	
	private String name;
	private Point2D size;
	
	public static GameObject gameObjectGameBoard;
	
	public GameObject(String name) {
		this.name = name;
		size = new Point2D(32, 32);
	}
	
	public void onAdd(ObjectInstance inst) {  }
	public void tick(ObjectInstance inst) {  }
	public void perSecond(int fps, ObjectInstance inst) {  }
	public void render(GraphicsContext gc, ObjectInstance inst) {  }
	public void setSize(Point2D size) { this.size = size; }
	public String getName() { return this.name; }
	public Point2D getSize() { return this.size; }
	
	public static final List<GameObject> gameObjs = new ArrayList<GameObject>();
	
	public static final void initObjs() {
		gameObjectGameBoard = new GameObjectGameBoard();
	}
	
}