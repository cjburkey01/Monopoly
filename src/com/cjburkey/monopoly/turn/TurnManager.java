package com.cjburkey.monopoly.turn;

import java.util.ArrayList;
import java.util.List;
import com.cjburkey.monopoly.object.GameObject;
import com.cjburkey.monopoly.object.GameObjectInst;
import com.cjburkey.monopoly.object.objects.GameObjectGameBoard;
import com.cjburkey.monopoly.state.GameStateManager;
import com.cjburkey.monopoly.state.states.GameStateMainGame;
import com.cjburkey.monopoly.util.Maths;
import javafx.geometry.Point2D;

public class TurnManager {
	
	private final List<Player> players = new ArrayList<Player>();
	private Player currentPlayer;
	private GameObjectInst[] dice = new GameObjectInst[2];
	
	public GameObjectInst[] bills = new GameObjectInst[7];
	public final void addPlayer(Player p) { players.add(p); }
	public final void removePlayer(Player p) { players.remove(p); }
	public final void removePlayer(int p) { players.remove(p); }
	
	public final void startGame() {
		currentPlayer = players.get(0);
		initDice();
		initBills();
	}
	
	private final void initBills() {
		for(int i = 0; i < bills.length; i ++) {
			bills[i] = GameObjectInst.createInstance(GameObject.gameObjectBill,
					new Point2D((i * GameObject.gameObjectBill.getSize().getX()) - (GameObjectGameBoard.sizeWidth / 2), GameObjectGameBoard.sizeWidth / 2 + 16));
		}
	}
	
	public final int[] rollDice(boolean setCooldown) {
		int[] nums = new int[2];
		dice[0].setData("gameObjectDice-shown", nums[0] = Maths.randomRange(1, 6));
		dice[1].setData("gameObjectDice-shown", nums[1] = Maths.randomRange(1, 6));
		if(setCooldown) {
			((GameStateMainGame) GameStateManager.mainGame).cooldown = 75;
		}
		return nums;
	}
	
	private final void initDice() {
		double padding = 16;
		
		dice[0] = GameObjectInst.createInstance(GameObject.gameObjectDice,
				new Point2D(-GameObjectGameBoard.sizeWidth / 2 - (GameObject.gameObjectDice.getSize().getX() + padding),
						-GameObjectGameBoard.sizeWidth / 2));
		dice[1] = GameObjectInst.createInstance(GameObject.gameObjectDice,
				new Point2D(dice[0].getPos().getX(), dice[0].getPos().getY() + dice[0].parent.getSize().getX() + padding));
	}
	
	public final Player[] getPlayers() {
		Player[] ps = new Player[players.size()];
		ps = players.toArray(ps);
		return ps;
	}
	
	public final Player getCurrentPlayer() {
		return currentPlayer;
	}
	
	public final int playerToId(Player player) {
		for(int i = 0; i < players.size(); i ++) {
			if(player.equals(players.get(i))) {
				return i;
			}
		}
		return -1;
	}
	
	public final Player nextTurn() {
		int currentId = playerToId(getCurrentPlayer());
		int nextId = -1;
		if(currentId >= 0) {
			if(currentId + 1 >= players.size()) {
				nextId = 0;
			} else {
				nextId = currentId + 1;
			}
			getCurrentPlayer().tickTurn();
			//GameStateManager.mainGame.showMoneyScreen("Send money", 100, getCurrentPlayer(), players.get(nextId));
			currentPlayer = players.get(nextId);
			return currentPlayer;
		}
		return null;
	}
	
}