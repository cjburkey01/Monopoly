package com.cjburkey.monopoly.turn;

import com.cjburkey.monopoly.Monopoly;
import com.cjburkey.monopoly.img.TextureManager;
import com.cjburkey.monopoly.money.PlayerMoney;
import com.cjburkey.monopoly.object.GameObjectInst;
import com.cjburkey.monopoly.object.objects.GameObjectBoardSlot;
import com.cjburkey.monopoly.object.objects.GameObjectGameBoard;
import com.cjburkey.monopoly.util.Maths;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Player {
	
	private String name;
	private PlayerMoney money;
	private GameObjectInst inst;
	private Image avatar;
	private int inJail = 0;
	private int turn = 0;
	private int place = 0;
	private int rounds = 0;
	private int ds = 0;
	
	public Player(String name, GameObjectInst inst) {
		this.name = name;
		this.inst = inst;
		this.money = PlayerMoney.getDefault();
		
		int boyGirlMan = Maths.randomRange(0, 2);
		if(boyGirlMan == 0) {
			this.avatar = TextureManager.avatarBoy[Maths.randomRange(0, TextureManager.avatarBoy.length - 1)];
		} else if(boyGirlMan == 1) {
			this.avatar = TextureManager.avatarGirl[Maths.randomRange(0, TextureManager.avatarGirl.length - 1)];
		} else if(boyGirlMan == 2) {
			this.avatar = TextureManager.avatarMan[Maths.randomRange(0, TextureManager.avatarMan.length - 1)];
		} else {
			this.avatar = TextureManager.unknown;
		}
		
		this.inst.setData("playerColor", Color.rgb(Maths.randomRange(0, 255), Maths.randomRange(0, 255), Maths.randomRange(0, 255), 0.4d));
		this.inst.setData("playerAvatar", this.avatar);
	}
	
	public void resetDoubles() {
		ds = 0;
	}
	
	public void doubles() {
		ds ++;
		if(ds >= 3) {
			ds = 0;
			putInJail();
		}
	}
	
	public void setName(String name) { this.name = name; }
	public void putInJail() { this.place = 10; this.inJail = 4; }
	public void tickTurn() { turn ++; }
	public void moveForward(int amt) {
		if(!this.getInJail()) {
			int total = place + amt;
			if(total < (GameObjectGameBoard.numOfTiles - 1) * 4) {
				place = total;
			} else {
				int overflow = total - ((int) ((GameObjectGameBoard.numOfTiles - 1) * 4 - 1)) - 1;
				this.money.addAmountToBill(100, 2);
				rounds ++;
				place = overflow;
			}
			
			GameObjectInst s = GameObjectBoardSlot.getInstFromId(place);
			if(s != null) {
				inst.moveToPos(s.getPos());
			} else {
				Monopoly.log(place);
			}
		}
	}
	
	public GameObjectInst getInst() { return this.inst; }
	
	public String getName() { return name; }
	public int getTurnsLeftInJail() { return this.inJail; }
	public int getTimesAroundBoard() { return rounds; }
	public int getTurnNumber() { return turn; }
	public boolean getInJail() { return getTurnsLeftInJail() > 0; }
	public PlayerMoney getMoney() { return this.money; }
	
	public int getTotalMoney() {
		return this.money.totalMoney();
	}
	
	public boolean takeBills(int worth, int numberToTake, boolean simulate) {
		return this.money.takeAmountOfBill(worth, numberToTake, simulate);
	}
	
	public void addBills(int worth, int numberToAdd) {
		this.money.addAmountToBill(worth, numberToAdd);
	}
	
}