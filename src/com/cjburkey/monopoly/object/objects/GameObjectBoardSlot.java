package com.cjburkey.monopoly.object.objects;

import com.cjburkey.monopoly.building.Property;
import com.cjburkey.monopoly.object.GameObject;
import com.cjburkey.monopoly.object.GameObjectInst;
import com.sun.javafx.tk.Toolkit;
import javafx.geometry.Point2D;
import javafx.geometry.VPos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class GameObjectBoardSlot extends GameObject {

	public GameObjectBoardSlot() {
		super("gameObjectBoardSlot");
		this.setSize(new Point2D(GameObjectGameBoard.pixelPerTile, GameObjectGameBoard.pixelPerTile));
	}
	
	public void render(GraphicsContext gc, GameObjectInst inst) {
		Object data = inst.getData("gameObjectBoardSlot-ID");
		int id = -1;
		if(data instanceof Integer) {
			id = (int) data;
		}
		if(data instanceof Double) {
			double tmp = (double) data;
			id = (int) tmp;
		}
		
		Property p = Property.getFromID(id);
		
		gc.setFont(Font.font(75));
		if(p != null) {
			while(width(p.build.name, gc.getFont()) > GameObjectGameBoard.pixelPerTile - 5) {
				gc.setFont(Font.font(gc.getFont().getSize() - 1));
			}
		}
		
		gc.setFill(Color.BLACK);
		gc.setTextAlign(TextAlignment.CENTER);
		gc.setTextBaseline(VPos.CENTER);
		gc.fillText((p != null) ? p.build.name : id + "", inst.getPos().getX() + this.getSize().getX() / 2, inst.getPos().getY() + this.getSize().getY() / 2);
	}
	
	private static final float width(String text, Font font) {
		return Toolkit.getToolkit().getFontLoader().computeStringWidth(text, font);
	}
	
	public static final GameObjectInst getInstFromId(int id) {
		for(GameObjectInst inst : GameObjectInst.objInstances) {
			Object data = inst.getData("gameObjectBoardSlot-ID");
			if(data != null) {
				if(data instanceof Integer) {
					int did = (int) data;
					if(did == id) {
						return inst;
					}
				} else if(data instanceof Double) {
					double tmp = (double) data;
					int did = (int) tmp;
					if(did == id) {
						return inst;
					}
				}
			}
		}
		return null;
	}
	
}