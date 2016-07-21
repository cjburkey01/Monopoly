package com.cjburkey.monopoly.object.objects;

import com.cjburkey.monopoly.img.TextureManager;
import com.cjburkey.monopoly.object.GameObject;
import com.cjburkey.monopoly.object.GameObjectInst;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;

public class GameObjectDice extends GameObject {
	
	public GameObjectDice() {
		super("gameObjectDice");
		this.setSize(new Point2D(GameObjectGameBoard.pixelPerTile * 2, GameObjectGameBoard.pixelPerTile * 2));
	}
	
	public void render(GraphicsContext gc, GameObjectInst inst) {
		Object data = inst.getData("gameObjectDice-shown");
		int shown = -1;
		if(data instanceof Integer) {
			shown = (int) data;
			if(shown >= 1 && shown <= 6) {
				drawImage(shown, inst.getPos(), gc);
				return;
			}
		}
		drawImage(0, inst.getPos(), gc);
	}
	
	private void drawImage(int dice, Point2D loc, GraphicsContext gc) {
		if(dice == 0) gc.drawImage(TextureManager.imgDice0, loc.getX(), loc.getY(), this.getSize().getX(), this.getSize().getY());
		if(dice == 1) gc.drawImage(TextureManager.imgDice1, loc.getX(), loc.getY(), this.getSize().getX(), this.getSize().getY());
		if(dice == 2) gc.drawImage(TextureManager.imgDice2, loc.getX(), loc.getY(), this.getSize().getX(), this.getSize().getY());
		if(dice == 3) gc.drawImage(TextureManager.imgDice3, loc.getX(), loc.getY(), this.getSize().getX(), this.getSize().getY());
		if(dice == 4) gc.drawImage(TextureManager.imgDice4, loc.getX(), loc.getY(), this.getSize().getX(), this.getSize().getY());
		if(dice == 5) gc.drawImage(TextureManager.imgDice5, loc.getX(), loc.getY(), this.getSize().getX(), this.getSize().getY());
		if(dice == 6) gc.drawImage(TextureManager.imgDice6, loc.getX(), loc.getY(), this.getSize().getX(), this.getSize().getY());
	}
	
}