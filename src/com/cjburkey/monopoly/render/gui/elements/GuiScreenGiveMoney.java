package com.cjburkey.monopoly.render.gui.elements;

import com.cjburkey.monopoly.Monopoly;
import com.cjburkey.monopoly.money.PlayerMoney;
import com.cjburkey.monopoly.turn.Player;
import javafx.geometry.Point2D;
import javafx.geometry.VPos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class GuiScreenGiveMoney extends GuiScreen {
	
	private GuiLabel text;
	private GuiLabel amount;
	private int moneyOwed = 0;
	private int moneyLeft = 0;
	private Player to;
	private Player from;
	private PlayerMoney money;
	
	public GuiScreenGiveMoney(String title, int owed, Player from, Player to) {
		super(new Point2D(Monopoly.canvasSize().getX() / 1.3, Monopoly.canvasSize().getY() / 1.3));
		
		this.to = to;
		this.from = from;
		this.moneyOwed = owed;
		this.money = PlayerMoney.getEmpty();
		
		this.text = new GuiLabel(title, new Point2D(Monopoly.canvasSize().getX() / 2, this.getPosition().getMinY() + 10), Font.font(40), Color.WHITE, true, VPos.TOP);
		while(Monopoly.widthOfText(this.text.getText(), this.text.getFont()) > this.getPosition().getWidth() + 5) {
			this.text.setFont(Font.font(this.text.getFont().getSize() - 1));
		}
		this.text.calcSize();
		
		this.amount = new GuiLabel("", new Point2D(Monopoly.canvasSize().getX() / 2, text.getPosition().getMaxY() + 10), Font.font(25), Color.WHITE, true,VPos.TOP);
		while(Monopoly.widthOfText(this.amount.getText(), this.amount.getFont()) > this.getPosition().getWidth() + 5) {
			this.amount.setFont(Font.font(this.amount.getFont().getSize() - 1));
		}
		this.amount.calcSize();
		
		int[] has = new int[] {
			this.from.getMoney().bills.get(0).amount,
			this.from.getMoney().bills.get(1).amount,
			this.from.getMoney().bills.get(2).amount,
			this.from.getMoney().bills.get(3).amount,
			this.from.getMoney().bills.get(4).amount,
			this.from.getMoney().bills.get(5).amount,
			this.from.getMoney().bills.get(6).amount,
		};
		
		GuiButton add1 = new GuiButtonCentered(new Point2D(Monopoly.canvasSize().getX() / 2, this.amount.getPosition().getMaxY() + 50),
				() -> { add(0); }, "$1 Bill(" + has[0] + ")", 5, this.getPosition().getWidth() - 10);
		GuiButton add5 = new GuiButtonCentered(new Point2D(Monopoly.canvasSize().getX() / 2, add1.getPosition().getMaxY() + add1.getPosition().getHeight() / 2),
				() -> { add(1); }, "$5 Bill(" + has[1] + ")", 5, this.getPosition().getWidth() - 10);
		GuiButton add10 = new GuiButtonCentered(new Point2D(Monopoly.canvasSize().getX() / 2, add5.getPosition().getMaxY() + add1.getPosition().getHeight() / 2),
				() -> { add(2); }, "$10 Bill(" + has[2] + ")", 5, this.getPosition().getWidth() - 10);
		GuiButton add20 = new GuiButtonCentered(new Point2D(Monopoly.canvasSize().getX() / 2, add10.getPosition().getMaxY() + add1.getPosition().getHeight() / 2),
				() -> { add(3); }, "$20 Bill(" + has[3] + ")", 5, this.getPosition().getWidth() - 10);
		GuiButton add50 = new GuiButtonCentered(new Point2D(Monopoly.canvasSize().getX() / 2, add20.getPosition().getMaxY() + add1.getPosition().getHeight() / 2),
				() -> { add(4); }, "$50 Bill(" + has[4] + ")", 5, this.getPosition().getWidth() - 10);
		GuiButton add100 = new GuiButtonCentered(new Point2D(Monopoly.canvasSize().getX() / 2, add50.getPosition().getMaxY() + add1.getPosition().getHeight() / 2),
				() -> { add(5); }, "$100 Bill(" + has[5] + ")", 5, this.getPosition().getWidth() - 10);
		GuiButton add500 = new GuiButtonCentered(new Point2D(Monopoly.canvasSize().getX() / 2, add100.getPosition().getMaxY() + add1.getPosition().getHeight() / 2),
				() -> { add(6); }, "$500 Bill(" + has[6] + ")", 5, this.getPosition().getWidth() - 10);
		
		this.addElement(text);
		this.addElement(amount);
		
		this.addElement(add1);
		this.addElement(add5);
		this.addElement(add10);
		this.addElement(add20);
		this.addElement(add50);
		this.addElement(add100);
		this.addElement(add500);
	}
	
	private void add(int id) {
		int worth = this.from.getMoney().idToWorth(id);
		Monopoly.log(this.from.getMoney().takeAmountOfBill(worth, 1, true));
		if(this.from.getMoney().takeAmountOfBill(worth, 1, true)) {
			this.from.getMoney().takeAmountOfBill(worth, 1, false);
			this.money.addAmountToBill(this.money.idToWorth(id), 1);
		}
	}
	
	private void send() {
		
	}
	
	public void render(GraphicsContext gc) {
		super.render(gc);
		this.moneyLeft = this.moneyOwed - this.money.totalMoney();
		this.amount.setText("$" + this.moneyOwed + " owed to " + this.to.getName() + ".  $" + this.moneyLeft + " left.");
	}
	
}