package com.cjburkey.monopoly.turn;

import com.cjburkey.monopoly.money.PlayerBill;

public class Player {
	
	private String name;
	private PlayerBill[] money;
	private int inJail = 0;
	private int turn = 0;
	private int place = 0;
	private int rounds = 0;
	
	public Player(String name) {
		this.name = name;
		money = PlayerBill.getDefaultStartingBills();
	}
	
	public void setName(String name) { this.name = name; }
	public void putInJail() { this.inJail = 4; }
	public void tickTurn() { turn ++; }
	public void moveForward(int amt) {
		int total = place + amt;
		if(total < 44) {
			place = total;
		} else {
			int overflow = total - 44;
			rounds ++;
			place = overflow;
		}
	}
	
	public String getName() { return name; }
	public int getTurnsLeftInJail() { return this.inJail; }
	public int getTimesAroundBoard() { return rounds; }
	public int getTurnNumber() { return turn; }
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