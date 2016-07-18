package com.cjburkey.monopoly.object.objects;

import com.cjburkey.monopoly.object.GameObject;
import com.cjburkey.monopoly.object.instance.ObjectInstance;
import com.cjburkey.monopoly.turn.Player;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class GameObjectPlayer extends GameObject {
	
	private Player playerObj;
	
	public GameObjectPlayer(Player playerObj) {
		super("gameObjectPlayer");
		
		this.playerObj = playerObj;
		this.setSize(new Point2D(32, 32));
	}
	
	public void render(GraphicsContext gc, ObjectInstance inst) {
		Rectangle2D pos = new Rectangle2D(inst.getPosition().getX(), inst.getPosition().getY(), this.getSize().getX(), this.getSize().getY());
		
		gc.setFill(Color.rgb(0, 255, 0, 0.5d));
		gc.fillRect(pos.getMinX(), pos.getMinY(), pos.getWidth(), pos.getHeight());
	}
	
	public void tick(ObjectInstance inst) {  }
	
	public Player getPlayerObject() { return this.playerObj; }
	
}