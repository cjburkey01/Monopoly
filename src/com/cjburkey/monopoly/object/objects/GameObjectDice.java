package com.cjburkey.monopoly.object.objects;

import com.cjburkey.monopoly.object.GameObject;
import com.cjburkey.monopoly.object.instance.ObjectInstance;
import javafx.scene.canvas.GraphicsContext;

public class GameObjectDice extends GameObject {
	
	public GameObjectDice() {
		super("gameObjectDice");
	}
	
	public void render(GraphicsContext gc, ObjectInstance inst) {
		Object data = inst.getData("gameObjectDice-shown");
		int shown = -1;
		if(data instanceof Integer) {
			shown = (int) data;
			if(shown >= 1 && shown <= 6) {
				
			}
		}
	}
	
}