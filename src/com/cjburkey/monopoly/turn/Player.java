package com.cjburkey.monopoly.turn;

import com.cjburkey.monopoly.Monopoly;
import com.cjburkey.monopoly.money.PlayerBill;
import com.cjburkey.monopoly.object.instance.ObjectInstance;
import com.cjburkey.monopoly.object.objects.GameObjectGameBoard;
import com.cjburkey.monopoly.util.Maths;
import javafx.scene.paint.Color;

public class Player {
	
	private String name;
	private PlayerBill[] money;
	private ObjectInstance inst;
	private int inJail = 0;
	private int turn = 0;
	private int place = 0;
	private int rounds = 0;
	
	public Player(String name, ObjectInstance inst) {
		this.name = name;
		this.inst = inst;
		money = PlayerBill.getDefaultStartingBills();
		
		this.inst.setData("playerColor", Color.rgb(Maths.randomRange(0, 255), Maths.randomRange(0, 255), Maths.randomRange(0, 255), 0.4d));
	}
	
	public void setName(String name) { this.name = name; }
	public void putInJail() { this.inJail = 4; }
	public void tickTurn() { turn ++; }
	public void moveForward(int amt) {
		int total = place + amt;
		if(total < (GameObjectGameBoard.numOfTiles - 1) * 4) {
			place = total;
		} else {
			int overflow = total - ((int) ((GameObjectGameBoard.numOfTiles - 1) * 4 - 1)) - 1;
			money[5].amount += 2;
			rounds ++;
			place = overflow;
		}
		
		ObjectInstance s = ObjectInstance.getInstFromId(place);
		if(s != null) {
			inst.moveToPos(s.getPos());
		} else {
			Monopoly.log(place);
		}
	}
	
	public ObjectInstance getInst() { return this.inst; }
	
	public String getName() { return name; }
	public int getTurnsLeftInJail() { return this.inJail; }
	public int getTimesAroundBoard() { return rounds; }
	public int getTurnNumber() { return turn; }
	public PlayerBill getAmountOfBill(int worth) { return this.money[PlayerBill.worthToId(this.money, worth)]; }
	public PlayerBill getAmountOfBillFromId(int id) { return this.money[id]; }
	public boolean getInJail() { return getTurnsLeftInJail() > 0; }
	
	public int getTotalMoney() {
		return PlayerBill.totalAmount(money);
	}
	
	public boolean takeBills(int worth, int numberToTake, boolean simulate) {
		int id = PlayerBill.worthToId(money, worth);
		if(id >= 0) {
			int amt = money[id].amount - numberToTake;
			if(amt >= 0) {
				if(!simulate) money[id].amount = amt;
				return true;
			}
		}
		return false;
	}
	
	public boolean addBills(int worth, int numberToAdd, boolean simulate) {
		int id = PlayerBill.worthToId(money, worth);
		if(id >= 0) {
			if(!simulate) money[id].amount += numberToAdd;
			return true;
		}
		return false;
	}
	
}