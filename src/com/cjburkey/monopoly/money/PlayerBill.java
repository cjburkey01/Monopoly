package com.cjburkey.monopoly.money;

public class PlayerBill {
	
	public int worth;
	public int amount;
	
	public PlayerBill(int worth, int amt) {
		this.worth = worth;
		this.amount = amt;
	}
	
	public static final int worthToId(PlayerBill[] money, int worth) {
		for(int i = 0; i < money.length; i ++) {
			if(money[i].worth == worth) {
				return i;
			}
		}
		return -1;
	}
	
	public static final PlayerBill[] getDefaultStartingBills() {
		return new PlayerBill[] {
			new PlayerBill(1, 5),
			new PlayerBill(5, 5),
			new PlayerBill(10, 5),
			new PlayerBill(20, 6),
			new PlayerBill(50, 2),
			new PlayerBill(100, 2),
			new PlayerBill(500, 2),
		};
	}
	
	public static final int totalAmount(PlayerBill[] money) {
		int total = 0;
		for(PlayerBill b : money) {
			total += b.worth * b.amount;
		}
		return total;
	}
	
}