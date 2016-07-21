package com.cjburkey.monopoly.object.objects;

import com.cjburkey.monopoly.object.GameObject;
import com.cjburkey.monopoly.object.instance.ObjectInstance;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class GameObjectPlayer extends GameObject {
	
	public GameObjectPlayer() {
		super("gameObjectPlayer");
		this.setSize(new Point2D(GameObjectGameBoard.pixelPerTile, GameObjectGameBoard.pixelPerTile));
	}
	
	public void render(GraphicsContext gc, ObjectInstance inst) {
		Object data = inst.getData("currentPlayer");
		if(data != null && data instanceof Boolean) {
			boolean current = (boolean) data;
			if(current) {
				gc.setStroke(Color.gray(0.5d));
				gc.setLineWidth(5d);
				gc.strokeRect(inst.getPos().getX() + 16, inst.getPos().getY() + 16, GameObjectGameBoard.pixelPerTile - 32, GameObjectGameBoard.pixelPerTile - 32);
			}
		}
		
		Object c = inst.getData("playerColor");
		if(c != null && c instanceof Paint) {
			gc.setFill((Paint) c);
			gc.fillRect(inst.getPos().getX() + 16, inst.getPos().getY() + 16, GameObjectGameBoard.pixelPerTile - 32, GameObjectGameBoard.pixelPerTile - 32);
		}
		
		Object d = inst.getData("playerAvatar");
		if(d != null && d instanceof Image) {
			Image avatar = (Image) d;
			gc.drawImage(avatar, inst.getPos().getX() + 16, inst.getPos().getY() + 16, GameObjectGameBoard.pixelPerTile - 32,
					GameObjectGameBoard.pixelPerTile - 32);
			
		}
	}
	
	public void tick(ObjectInstance inst) {  }
	
}