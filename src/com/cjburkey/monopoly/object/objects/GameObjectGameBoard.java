package com.cjburkey.monopoly.object.objects;

import com.cjburkey.monopoly.object.GameObject;
import com.cjburkey.monopoly.object.instance.ObjectInstance;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class GameObjectGameBoard extends GameObject {
	
	// Numbers of tiles on each side(11) * Pixels per tile(32).
	public static final double sizeWidth = 11 * 32;
	
	public GameObjectGameBoard() {
		super("gameObjectGameBoard");
		
		this.setSize(new Point2D(sizeWidth, sizeWidth));
	}
	
	public void onAdd(ObjectInstance inst) {
		inst.position = new Point2D(-this.getSize().getX() / 2, -this.getSize().getY() / 2);
	}
	
	/*public void perSecond(int fps, ObjectInstance inst) {
		Monopoly.log(fps + " FPS");
	}*/
	
	public void render(float delta, GraphicsContext gc, ObjectInstance inst) {
		Rectangle2D pos = new Rectangle2D(inst.getPosition().getX(), inst.getPosition().getY(), this.getSize().getX(), this.getSize().getY());
		
		gc.setStroke(Color.BLACK);
		gc.strokeRect(pos.getMinX(), pos.getMinY(), pos.getWidth(), pos.getHeight());
		gc.strokeRect(pos.getMinX() + 32, pos.getMinY() + 32, pos.getWidth() - 64, pos.getHeight() - 64);
		
		for(int i = 1; i < 11; i ++) {
			gc.strokeLine(pos.getMinX() + (i * 32), pos.getMinY(), pos.getMinX() + (i * 32), pos.getMinY() + 32);
			gc.strokeLine(pos.getMinX(), pos.getMinX() + (i * 32), pos.getMinX() + 32, pos.getMinX() + (i * 32));
			gc.strokeLine(pos.getMinX() + (i * 32), pos.getMaxY(), pos.getMinX() + (i * 32), pos.getMaxY() - 32);
			gc.strokeLine(pos.getMaxX(), pos.getMinX() + (i * 32), pos.getMaxX() - 32, pos.getMinX() + (i * 32));
		}
		
		double x = pos.getMinX() + sizeWidth / 2;
		double y = pos.getMinY() + sizeWidth / 2;
		
		gc.strokeLine(pos.getMinX() + 32, y, pos.getMaxX() - 32, y);
		gc.strokeLine(x, pos.getMinY() + 32, x, pos.getMaxY() - 32);
	}
	
}