package com.cjburkey.monopoly.object.objects;

import com.cjburkey.monopoly.object.GameObject;
import com.cjburkey.monopoly.object.instance.ObjectInstance;
import javafx.geometry.VPos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;

public class GameObjectBoardSlot extends GameObject {

	public GameObjectBoardSlot() {
		super("gameObjectBoardSlot");
	}
	
	public void render(GraphicsContext gc, ObjectInstance inst) {
		Object data = inst.getData("gameObjectBoardSlot-ID");
		int id = -1;
		if(data instanceof Integer) {
			id = (int) data;
			
			gc.setFill(Color.BLACK);
			gc.setTextAlign(TextAlignment.CENTER);
			gc.setTextBaseline(VPos.CENTER);
			gc.fillText(id + "", inst.getPosition().getX() + 16, inst.getPosition().getY() + 16);
		}
	}
	
}