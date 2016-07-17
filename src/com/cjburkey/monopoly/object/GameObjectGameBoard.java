package com.cjburkey.monopoly.object;

import com.cjburkey.monopoly.Monopoly;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class GameObjectGameBoard extends GameObject {
	
	public GameObjectGameBoard() {
		super("gameObjectGameBoard");
		
		this.size = new Point2D(11 * 32, 11 * 32);
	}
	
	public void onAdd(ObjInst inst) {
		inst.position = inst.position.subtract(this.size.getX() / 2, this.size.getY() / 2);
	}
	
	public void perSecond(int fps, ObjInst inst) {
		Monopoly.log(fps + " FPS");
	}
	
	public void render(float delta, GraphicsContext gc, ObjInst inst) {
		Rectangle2D pos = new Rectangle2D(inst.getPosition().getX(), inst.getPosition().getY(), this.size.getX(), this.size.getY());
		
		gc.setStroke(Color.BLACK);
		gc.strokeRect(pos.getMinX(), pos.getMinY(), pos.getWidth(), pos.getHeight());
		gc.strokeRect(pos.getMinX() + 32, pos.getMinY() + 32, pos.getWidth() - 64, pos.getHeight() - 64);
	}
	
}