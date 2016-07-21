package com.cjburkey.monopoly.money;

import java.util.ArrayList;
import java.util.List;

public class PlayerMoney {
	
	public List<PlayerBill> bills = new ArrayList<PlayerBill>();
	
	public PlayerMoney(boolean empty) {
		if(!empty) { this.set(0, 0, 0, 0, 0, 0, 0); }
	}
	
	public int idToAmount(int id) {
		return bills.get(id).amount;
	}
	
	public int worthToAmount(int worth) {
		for(PlayerBill b : bills) {
			if(b.worth == worth) {
				return b.amount;
			}
		}
		return -1;
	}
	
	public void setAmountFromWorth(int worth, int amt) {
		for(PlayerBill b : bills) {
			if(b.worth == worth) {
				b.amount = amt;
			}
		}
	}
	
	public int totalMoney() {
		int total = 0;
		for(PlayerBill b : bills) {
			total += b.worth * b.amount;
		}
		return total;
	}
	
	public boolean takeAmountOfBill(int billWorth, int numberToTake, boolean simulate) {
		if(numberToTake <= this.worthToAmount(billWorth)) {
			if(!simulate) this.setAmountFromWorth(billWorth, this.worthToAmount(billWorth) - numberToTake);
			return true;
		}
		return false;
	}
	
	public void addAmountToBill(int billWorth, int numberToAdd) {
		this.setAmountFromWorth(billWorth, this.worthToAmount(billWorth) + numberToAdd);
	}
	
	private void set(int one, int five, int ten, int twnt, int fifty, int hundred, int fihund) {
		this.bills.clear();
		
		this.bills.add(0, new PlayerBill(1, one));
		this.bills.add(1, new PlayerBill(5, five));
		this.bills.add(2, new PlayerBill(10, ten));
		this.bills.add(3, new PlayerBill(20, twnt));
		this.bills.add(4, new PlayerBill(50, fifty));
		this.bills.add(5, new PlayerBill(100, hundred));
		this.bills.add(6, new PlayerBill(500, fihund));
	}
	
	public static PlayerMoney getDefault() {
		PlayerMoney m = new PlayerMoney(true);
		m.set(5, 5, 5, 6, 2, 2, 2);
		return m;
	}
	
	public static PlayerMoney getEmpty() {
		PlayerMoney m = new PlayerMoney(false);
		return m;
	}
	
	public int worthToId(int worth) {
		for(int i = 0; i < this.bills.size(); i ++) {
			if(bills.get(i).worth == worth) {
				return i;
			}
		}
		return -1;
	}
	
	public int idToWorth(int id) {
		return this.bills.get(id).worth;
	}
	
}