package com.cjburkey.monopoly.object.objects;

import com.cjburkey.monopoly.img.TextureManager;
import com.cjburkey.monopoly.object.GameObject;
import com.cjburkey.monopoly.object.instance.ObjectInstance;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;

public class GameObjectPlayer extends GameObject {
	
	public GameObjectPlayer() {
		super("gameObjectPlayer");
		this.setSize(new Point2D(GameObjectGameBoard.pixelPerTile, GameObjectGameBoard.pixelPerTile));
	}
	
	public void render(GraphicsContext gc, ObjectInstance inst) {
		gc.drawImage(TextureManager.imgIconPerson, inst.getPosition().getX() + 16, inst.getPosition().getY() + 16,
				GameObjectGameBoard.pixelPerTile - 32, GameObjectGameBoard.pixelPerTile - 32);
	}
	
	public void tick(ObjectInstance inst) {  }
	
}