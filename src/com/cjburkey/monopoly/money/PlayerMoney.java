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
	
	public void add(PlayerMoney m) {
		if(this.bills.size() >= 1) { this.bills.add(0, new PlayerBill(1, this.bills.get(0).amount += m.bills.get(0).amount)); }
			else { this.bills.add(0, new PlayerBill(1, m.bills.get(0).amount)); }
		if(this.bills.size() >= 2) { this.bills.add(1, new PlayerBill(5, this.bills.get(1).amount += m.bills.get(1).amount)); }
			else { this.bills.add(1, new PlayerBill(5, m.bills.get(1).amount)); }
		if(this.bills.size() >= 3) { this.bills.add(2, new PlayerBill(10, this.bills.get(2).amount += m.bills.get(2).amount)); }
			else { this.bills.add(2, new PlayerBill(10, m.bills.get(2).amount)); }
		if(this.bills.size() >= 4) { this.bills.add(3, new PlayerBill(20, this.bills.get(3).amount += m.bills.get(3).amount)); }
			else { this.bills.add(3, new PlayerBill(20, m.bills.get(3).amount)); }
		if(this.bills.size() >= 5) { this.bills.add(4, new PlayerBill(50, this.bills.get(4).amount += m.bills.get(4).amount)); }
			else { this.bills.add(4, new PlayerBill(50, m.bills.get(4).amount)); }
		if(this.bills.size() >= 6) { this.bills.add(5, new PlayerBill(100, this.bills.get(5).amount += m.bills.get(5).amount)); }
			else { this.bills.add(5, new PlayerBill(100, m.bills.get(5).amount)); }
		if(this.bills.size() >= 7) { this.bills.add(6, new PlayerBill(500, this.bills.get(6).amount += m.bills.get(6).amount)); }
			else { this.bills.add(6, new PlayerBill(500, m.bills.get(6).amount)); }
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