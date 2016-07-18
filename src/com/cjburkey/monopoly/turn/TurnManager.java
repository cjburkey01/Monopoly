package com.cjburkey.monopoly.turn;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import com.cjburkey.monopoly.object.GameObject;
import com.cjburkey.monopoly.object.instance.ObjectInstance;
import com.cjburkey.monopoly.object.objects.GameObjectGameBoard;
import javafx.geometry.Point2D;

public class TurnManager {
	
	private static final List<Player> players = new ArrayList<Player>();
	private static Player currentPlayer;
	private static ObjectInstance[] dice = new ObjectInstance[2];
	
	public static final void addPlayer(Player p) { players.add(p); }
	public static final void removePlayer(Player p) { players.remove(p); }
	public static final void removePlayer(int p) { players.remove(p); }
	
	public static final void startGame() {
		currentPlayer = players.get(0);
		initDice();
	}
	
	public static final int[] rollDice() {
		int[] nums = new int[2];
		dice[0].setData("gameObjectDice-shown", nums[0] = ThreadLocalRandom.current().nextInt(1, 7));
		dice[1].setData("gameObjectDice-shown", nums[1] = ThreadLocalRandom.current().nextInt(1, 7));
		return nums;
	}
	
	private static final void initDice() {
		double padding = 16;
		
		dice[0] = ObjectInstance.createInstance(GameObject.gameObjectDice,
				new Point2D(-GameObjectGameBoard.sizeWidth / 2 - (GameObject.gameObjectDice.getSize().getX() + padding),
						-GameObjectGameBoard.sizeWidth / 2));
		dice[1] = ObjectInstance.createInstance(GameObject.gameObjectDice,
				new Point2D(dice[0].getPosition().getX(), dice[0].getPosition().getY() + dice[0].parent.getSize().getX() + padding));
	}
	
	public static final Player[] getPlayers() {
		Player[] ps = new Player[players.size()];
		ps = players.toArray(ps);
		return ps;
	}
	
	public static final Player getCurrentPlayer() {
		return currentPlayer;
	}
	
	public static final int playerToId(Player player) {
		for(int i = 0; i < players.size(); i ++) {
			if(player.equals(players.get(i))) {
				return i;
			}
		}
		return -1;
	}
	
	public static final Player nextTurn() {
		int currentId = playerToId(getCurrentPlayer());
		int nextId = -1;
		if(currentId >= 0) {
			if(currentId + 1 >= players.size()) {
				nextId = 0;
			} else {
				nextId = currentId ++;
			}
			getCurrentPlayer().tickTurn();
			currentPlayer = players.get(nextId);
			return currentPlayer;
		}
		return null;
	}
	
}