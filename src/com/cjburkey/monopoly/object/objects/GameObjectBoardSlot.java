package com.cjburkey.monopoly.object.objects;

import com.cjburkey.monopoly.object.GameObject;
import com.cjburkey.monopoly.object.instance.ObjectInstance;
import javafx.geometry.Point2D;
import javafx.geometry.VPos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;

public class GameObjectBoardSlot extends GameObject {

	public GameObjectBoardSlot() {
		super("gameObjectBoardSlot");
		this.setSize(new Point2D(GameObjectGameBoard.pixelPerTile, GameObjectGameBoard.pixelPerTile));
	}
	
	public void render(GraphicsContext gc, ObjectInstance inst) {
		Object data = inst.getData("gameObjectBoardSlot-ID");
		int id = -1;
		if(data instanceof Integer) {
			id = (int) data;
		}
		if(data instanceof Double) {
			double tmp = (double) data;
			id = (int) tmp;
		}
		
		gc.setFill(Color.BLACK);
		gc.setTextAlign(TextAlignment.CENTER);
		gc.setTextBaseline(VPos.CENTER);
		gc.fillText(id + "", inst.getPosition().getX() + this.getSize().getX() / 2, inst.getPosition().getY() + this.getSize().getY() / 2);
		gc.setFill(Color.rgb(255, 0, 0, 0.2d));
		gc.fillRect(inst.getPosition().getX(), inst.getPosition().getY(), this.getSize().getX(), this.getSize().getY());
	}
	
}