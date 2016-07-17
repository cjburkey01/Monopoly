package com.cjburkey.monopoly.object;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.canvas.GraphicsContext;

public class GameObject {
	
	public String name;
	
	public GameObject(String name) {
		this.name = name;
	}
	
	public void tick(float delta, ObjInst inst) {  }
	public void perSecond(int fps, ObjInst inst) {  }
	public void render(float delta, GraphicsContext gc, ObjInst inst) {  }
	
	public static final List<GameObject> gameObjs = new ArrayList<GameObject>();
	
}