package com.cjburkey.monopoly.turn;

import java.util.ArrayList;
import java.util.List;

public class TurnManager {
	
	private static final List<Player> players = new ArrayList<Player>();
	private static Player currentPlayer;
	
	public static final void addPlayer(Player p) { players.add(p); }
	public static final void removePlayer(Player p) { players.remove(p); }
	public static final void removePlayer(int p) { players.remove(p); }
	
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