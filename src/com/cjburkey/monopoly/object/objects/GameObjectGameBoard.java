package com.cjburkey.monopoly.object.objects;

import com.cjburkey.monopoly.object.GameObject;
import com.cjburkey.monopoly.object.instance.ObjectInstance;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class GameObjectGameBoard extends GameObject {
	
	public static final double numOfTiles = 11;
	public static final double pixelPerTile = 128;
	public static final double sizeWidth = numOfTiles * pixelPerTile;
	
	public GameObjectGameBoard() {
		super("gameObjectGameBoard");
		
		this.setSize(new Point2D(sizeWidth, sizeWidth));
	}
	
	public void onAdd(ObjectInstance inst) {
		inst.setPos(new Point2D(-this.getSize().getX() / 2, -this.getSize().getY() / 2));
	}
	
	public void render(GraphicsContext gc, ObjectInstance inst) {
		Rectangle2D pos = new Rectangle2D(inst.getPos().getX(), inst.getPos().getY(), this.getSize().getX(), this.getSize().getY());
		
		gc.setStroke(Color.BLACK);
		gc.strokeRect(pos.getMinX(), pos.getMinY(), pos.getWidth(), pos.getHeight());
		gc.strokeRect(pos.getMinX() + pixelPerTile, pos.getMinY() + pixelPerTile, pos.getWidth() - 2 * pixelPerTile, pos.getHeight() - 2 * pixelPerTile);
		
		for(int i = 1; i < numOfTiles; i ++) {
			gc.strokeLine(pos.getMinX() + (i * pixelPerTile), pos.getMinY(), pos.getMinX() + (i * pixelPerTile), pos.getMinY() + pixelPerTile);
			gc.strokeLine(pos.getMinX(), pos.getMinX() + (i * pixelPerTile), pos.getMinX() + pixelPerTile, pos.getMinX() + (i * pixelPerTile));
			gc.strokeLine(pos.getMinX() + (i * pixelPerTile), pos.getMaxY(), pos.getMinX() + (i * pixelPerTile), pos.getMaxY() - pixelPerTile);
			gc.strokeLine(pos.getMaxX(), pos.getMinX() + (i * pixelPerTile), pos.getMaxX() - pixelPerTile, pos.getMinX() + (i * pixelPerTile));
		}
		
		double x = pos.getMinX() + sizeWidth / 2;
		double y = pos.getMinY() + sizeWidth / 2;
		
		gc.strokeLine(pos.getMinX() + pixelPerTile, y, pos.getMaxX() - pixelPerTile, y);
		gc.strokeLine(x, pos.getMinY() + pixelPerTile, x, pos.getMaxY() - pixelPerTile);
	}
	
}