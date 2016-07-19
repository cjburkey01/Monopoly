package com.cjburkey.monopoly.object.objects;

import com.cjburkey.monopoly.money.PlayerBill;
import com.cjburkey.monopoly.object.GameObject;
import com.cjburkey.monopoly.object.instance.ObjectInstance;
import javafx.geometry.Point2D;
import javafx.geometry.VPos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class GameObjectBill extends GameObject {
	
	public GameObjectBill() {
		super("gameObjectBill");
		this.setSize(new Point2D(GameObjectGameBoard.sizeWidth / 7, GameObjectGameBoard.sizeWidth / 3.5));
	}
	
	public void render(GraphicsContext gc, ObjectInstance inst) {
		gc.setStroke(Color.BLACK);
		gc.strokeRect(inst.getPos().getX(), inst.getPos().getY(), this.getSize().getX(), this.getSize().getY());
		
		Object data = inst.getData("gameObjectBill-bill");
		if(data != null && data instanceof PlayerBill) {
			PlayerBill bill = (PlayerBill) data;
			
			int billWorth = bill.worth;
			int billAmount = bill.amount;
			
			gc.setTextAlign(TextAlignment.CENTER);
			gc.setTextBaseline(VPos.TOP);
			gc.setFont(Font.font(35d));
			gc.setFill(Color.BLACK);
			
			gc.save();
			gc.rotate(90);
			gc.fillText("$" + billWorth, inst.getPos().getY() + this.getSize().getY() / 2, -inst.getPos().getX() - 45);
			gc.restore();

			gc.setFont(Font.font(25d));
			gc.fillText(billAmount + " Bills", inst.getPos().getX() + this.getSize().getX() / 2, inst.getPos().getY() + this.getSize().getY() / 2);
		}
	}
	
}