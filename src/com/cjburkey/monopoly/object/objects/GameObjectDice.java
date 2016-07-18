package com.cjburkey.monopoly.object.objects;

import com.cjburkey.monopoly.img.TextureManager;
import com.cjburkey.monopoly.object.GameObject;
import com.cjburkey.monopoly.object.instance.ObjectInstance;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;

public class GameObjectDice extends GameObject {
	
	public GameObjectDice() {
		super("gameObjectDice");
		this.setSize(new Point2D(GameObjectGameBoard.pixelPerTile * 2, GameObjectGameBoard.pixelPerTile * 2));
	}
	
	public void render(GraphicsContext gc, ObjectInstance inst) {
		Object data = inst.getData("gameObjectDice-shown");
		int shown = -1;
		if(data instanceof Integer) {
			shown = (int) data;
			if(shown >= 1 && shown <= 6) {
				drawImage(shown, inst.getPosition(), gc);
				return;
			}
		}
		drawImage(0, inst.getPosition(), gc);
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
	
	/*
		ObjectInstance inst1 = ObjectInstance.createInstance(GameObject.gameObjectBoardSlot, new Point2D(-GameObject.gameObjectGameBoard.getSize().getX() / 2,
				-32 * (i + 1) + GameObject.gameObjectGameBoard.getSize().getY() / 2));
		ObjectInstance inst2 = ObjectInstance.createInstance(GameObject.gameObjectBoardSlot,
				new Point2D(32 * i - GameObject.gameObjectGameBoard.getSize().getX() / 2, -GameObject.gameObjectGameBoard.getSize().getY() / 2));
		ObjectInstance inst3 = ObjectInstance.createInstance(GameObject.gameObjectBoardSlot,
				new Point2D(GameObject.gameObjectGameBoard.getSize().getX() / 2 - 32, 32 * i - GameObject.gameObjectGameBoard.getSize().getY() / 2));
		ObjectInstance inst4 = ObjectInstance.createInstance(GameObject.gameObjectBoardSlot,
				new Point2D(-32 * (i + 1) + GameObject.gameObjectGameBoard.getSize().getX() / 2, GameObject.gameObjectGameBoard.getSize().getY() / 2 - 32));
		
		inst1.setData("gameObjectBoardSlot-ID", i);
		inst2.setData("gameObjectBoardSlot-ID", i + (GameObjectGameBoard.numOfTiles - 1));
		inst3.setData("gameObjectBoardSlot-ID", i + (2 * (GameObjectGameBoard.numOfTiles - 1)));
		inst4.setData("gameObjectBoardSlot-ID", (i + (3 * (GameObjectGameBoard.numOfTiles - 1)) == (GameObjectGameBoard.numOfTiles - 1) * 4) ? 0 : i + 30);
		
		Monopoly.log(inst2.getPosition());
		
		Monopoly.log("Created game board slots.");
	*/
	
}