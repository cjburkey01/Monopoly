package com.cjburkey.monopoly.object.objects;

import com.cjburkey.monopoly.img.TextureManager;
import com.cjburkey.monopoly.object.GameObject;
import com.cjburkey.monopoly.object.instance.ObjectInstance;
import com.cjburkey.monopoly.turn.Player;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;

public class GameObjectPlayer extends GameObject {
	
	public GameObjectPlayer() {
		super("gameObjectPlayer");
		this.setSize(new Point2D(32, 32));
	}
	
	public void render(GraphicsContext gc, ObjectInstance inst) {
		gc.drawImage(TextureManager.imgIconPerson, inst.getPosition().getX(), inst.getPosition().getY(), 32, 32);
	}
	
	public void tick(ObjectInstance inst) {  }
	
}