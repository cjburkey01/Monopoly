package com.cjburkey.monopoly.state.states;

import com.cjburkey.monopoly.Monopoly;
import com.cjburkey.monopoly.building.Property;
import com.cjburkey.monopoly.handler.CursorHandler;
import com.cjburkey.monopoly.object.GameObject;
import com.cjburkey.monopoly.object.GameObjectInst;
import com.cjburkey.monopoly.object.objects.GameObjectBoardSlot;
import com.cjburkey.monopoly.object.objects.GameObjectGameBoard;
import com.cjburkey.monopoly.render.gui.GuiHandler;
import com.cjburkey.monopoly.render.gui.elements.GuiButton;
import com.cjburkey.monopoly.render.gui.elements.GuiButtonCentered;
import com.cjburkey.monopoly.render.gui.elements.GuiLabel;
import com.cjburkey.monopoly.render.gui.elements.GuiScreen;
import com.cjburkey.monopoly.render.gui.elements.GuiScreenGiveMoney;
import com.cjburkey.monopoly.state.GameState;
import com.cjburkey.monopoly.state.GameStateManager;
import com.cjburkey.monopoly.turn.Player;
import com.cjburkey.monopoly.turn.TurnManager;
import com.cjburkey.monopoly.util.Maths;
import javafx.geometry.Point2D;
import javafx.geometry.VPos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class GameStateMainGame extends GameState {
	
	public Point2D offset = Point2D.ZERO;
	public float zoom = 1f;
	
	private Point2D mouse;
	private float lastZoom;
	private int lastZoomTime = 2;
	private boolean checkZoom = false;
	private GuiHandler guiHandler;
	private TurnManager turns;
	
	private int players = 2;
	private GuiLabel playerLabel;
	private GuiLabel currentPlayer;
	private GuiButton roll;
	private GuiButton next;
	private boolean lastGui = false;
	private boolean rolled = false;
	
	public int cooldown = 75;
	
	public Point2D minMaxZoom = new Point2D(0.6f, 1.3);
	public Point2D[] minMaxOffset = {
		new Point2D(-GameObjectGameBoard.sizeWidth / 2, GameObjectGameBoard.sizeWidth / 2),
		new Point2D(-GameObjectGameBoard.sizeWidth / 2, GameObjectGameBoard.sizeWidth / 2)
	};
	
	public GameStateMainGame() {
		super("gameStateMainGame");
	}
	
	public void showMoneyScreen(String title, int owed, Player from, Player to) {
		GuiScreenGiveMoney screen = new GuiScreenGiveMoney(title, owed, from, to);
		this.guiHandler.addElement(screen);
		screen.show();
	}
	
	public void tick() {
		for(GameObjectInst inst : GameObjectInst.objInstances) {
			inst.tick();
		}
		
		if(cooldown > 0) {
			cooldown --;
		}
	}
	
	public void perSecond(int fps) {
		for(GameObjectInst inst : GameObjectInst.objInstances) {
			inst.perSecond(fps);
		}
	}
	
	public void render(GraphicsContext gc) {
		gc.save();
		
		zoom = (float) Maths.clamp(zoom, minMaxZoom.getX(), minMaxZoom.getY());
		offset = new Point2D(Maths.clamp(offset.getX(), minMaxOffset[0].getX(), minMaxOffset[0].getY()),
				Maths.clamp(offset.getY(), minMaxOffset[1].getX(), minMaxOffset[1].getY()));
		
		if(lastZoomTime <= 0) {
			lastZoomTime = 2;
			if(checkZoom) {
				if(lastZoom == zoom) {
					checkZoom = false;
					CursorHandler.cursor = CursorHandler.NORMAL;
				} else {
					checkZoom = true;
					CursorHandler.cursor = CursorHandler.ZOOM;
				}
			}
		}
		
		lastZoom = zoom;
		lastZoomTime --;
		
		gc.scale(zoom, zoom);
		gc.translate(offset.getX(), offset.getY());
		
		for(GameObjectInst inst : GameObjectInst.objInstances) {
			gc.save();
			inst.render(gc);
			gc.restore();
		}
		
		
		gc.restore();
		
		players = (int) Maths.clamp(players, 2, 8);
		playerLabel.setText(players + " Players");
		
		if(currentPlayer != null && turns.getCurrentPlayer() != null)
			currentPlayer.setText(turns.getCurrentPlayer().getName() + "'s Turn!");
		
		for(int i = 0; i < turns.bills.length; i ++) {
			if(turns.getCurrentPlayer() != null) {
				turns.bills[i].setData("gameObjectBill-worth", turns.getCurrentPlayer().getMoney().idToWorth(i));
				turns.bills[i].setData("gameObjectBill-amount", turns.getCurrentPlayer().getMoney().idToAmount(i));
			}
		}
		
		for(Player p : turns.getPlayers()) {
			if(p.equals(turns.getCurrentPlayer())) {
				p.getInst().setData("currentPlayer", true);
			} else {
				p.getInst().setData("currentPlayer", false);
			}
		}
		
		if(Monopoly.guiScreenOpen != this.lastGui && this.exit != null) {
			if(Monopoly.guiScreenOpen) {
				this.lastGui = true;
				this.exit.hide();
			} else {
				this.lastGui = false;
				this.exit.show();
			}
		}
		
		if(this.roll != null && this.next != null) {
			this.next.hide();
			this.roll.hide();
			if(this.rolled && !Monopoly.guiScreenOpen) {
				this.next.show();
			} else if(!Monopoly.guiScreenOpen) {
				this.roll.show();
			}
		}
	}
	
	private void initTurns() {
		turnsGui();
	}
	
	private void turnsGui() {
		GuiScreen selectionWindow = new GuiScreen(new Point2D(Monopoly.canvasSize().getX() / 1.5, Monopoly.canvasSize().getY() / 1.5));
		GuiButtonCentered plus = new GuiButtonCentered(Point2D.ZERO, () -> { players ++; }, "+", 5d, 50d);
		GuiButtonCentered minus = new GuiButtonCentered(Point2D.ZERO, () -> { players --; }, "-", 5d, 50d);
		GuiButtonCentered go = new GuiButtonCentered(Point2D.ZERO,
				() -> {
					setupGame(players);
					initInGame();
					selectionWindow.hide();
				}, "Start Game", 5d);
		
		playerLabel = new GuiLabel(players + " Players", new Point2D(Monopoly.canvasSize().getX() / 2,
				selectionWindow.getPosition().getMinY() + go.getPosition().getHeight() + 25), Font.font(45), Color.WHITE, true);
		
		plus.setPosition(new Point2D(Monopoly.canvasSize().getX() / 2 - plus.getPosition().getWidth() / 2,
				selectionWindow.getPosition().getMinY() + plus.getPosition().getWidth() / 2 + 10));
		minus.setPosition(new Point2D(Monopoly.canvasSize().getX() / 2 + minus.getPosition().getWidth() / 2,
				selectionWindow.getPosition().getMinY() + minus.getPosition().getWidth() / 2 + 10));
		go.setPosition(new Point2D(Monopoly.canvasSize().getX() / 2,
				selectionWindow.getPosition().getMinY() + selectionWindow.getPosition().getHeight() - go.getPosition().getHeight() / 2 - 10));

		plus.setColorScheme(Color.WHITE, Color.rgb(0, 100, 0), Color.WHITE, Color.rgb(0, 150, 0));
		minus.setColorScheme(Color.WHITE, Color.rgb(100, 0, 0), Color.WHITE, Color.rgb(150, 0, 0));
		go.setColorScheme(Color.WHITE, Color.rgb(0, 0, 100), Color.WHITE, Color.rgb(0, 0, 150));
		
		guiHandler.addElement(selectionWindow);
		
		selectionWindow.addElement(plus);
		selectionWindow.addElement(minus);
		selectionWindow.addElement(go);
		selectionWindow.addElement(playerLabel);
		
		selectionWindow.show();
	}
	
	private void setupGame(int players) {
		for(int i = 1; i < GameObjectGameBoard.numOfTiles; i ++) {
			GameObjectInst inst1 = GameObjectInst.createInstance(GameObject.gameObjectBoardSlot, new Point2D(-GameObject.gameObjectGameBoard.getSize().getX() / 2,
					-GameObjectGameBoard.pixelPerTile * (i + 1) + GameObject.gameObjectGameBoard.getSize().getY() / 2));
			GameObjectInst inst2 = GameObjectInst.createInstance(GameObject.gameObjectBoardSlot,
					new Point2D(GameObjectGameBoard.pixelPerTile * i - GameObject.gameObjectGameBoard.getSize().getX() / 2, -GameObject.gameObjectGameBoard.getSize().getY() / 2));
			GameObjectInst inst3 = GameObjectInst.createInstance(GameObject.gameObjectBoardSlot,
					new Point2D(GameObject.gameObjectGameBoard.getSize().getX() / 2 - GameObjectGameBoard.pixelPerTile, GameObjectGameBoard.pixelPerTile * i - GameObject.gameObjectGameBoard.getSize().getY() / 2));
			GameObjectInst inst4 = GameObjectInst.createInstance(GameObject.gameObjectBoardSlot,
					new Point2D(-GameObjectGameBoard.pixelPerTile * (i + 1) + GameObject.gameObjectGameBoard.getSize().getX() / 2, GameObject.gameObjectGameBoard.getSize().getY() / 2 - GameObjectGameBoard.pixelPerTile));
			
			inst1.setData("gameObjectBoardSlot-ID", i);
			inst2.setData("gameObjectBoardSlot-ID", i + (GameObjectGameBoard.numOfTiles - 1));
			inst3.setData("gameObjectBoardSlot-ID", i + (2 * (GameObjectGameBoard.numOfTiles - 1)));
			inst4.setData("gameObjectBoardSlot-ID", ((i + (3 * (GameObjectGameBoard.numOfTiles - 1))) == (4 * (GameObjectGameBoard.numOfTiles - 1))) ? (int) 0 : (i + (3 * (GameObjectGameBoard.numOfTiles - 1))));
		}
		
		Property.loadProperties();
		
		for(int i = 0; i < players; i ++) {
			Object fromInst = GameObjectBoardSlot.getInstFromId(0);
			if(fromInst != null) {
				Player p = new Player("Player " + (i + 1), GameObjectInst.createInstance(GameObject.gameObjectPlayer, ((GameObjectInst) fromInst).getPos()));
				turns.addPlayer(p);
			}
		}
		
		turns.startGame();
	}
	
	public void enterState(GameState previous) {
		GameObjectInst.createInstance(GameObject.gameObjectGameBoard, Point2D.ZERO);
		turns = new TurnManager();
		
		Monopoly.getWindow().getScene().getGameCanvas().addEventHandler(MouseEvent.MOUSE_DRAGGED, e -> {
			if(!Monopoly.guiScreenOpen && e.getButton().equals(MouseButton.MIDDLE)) {
				CursorHandler.cursor = CursorHandler.MOVE;
				Point2D now = new Point2D(e.getX() / zoom, e.getY() / zoom);
				if(mouse != null) {
					Point2D diff = now.subtract(mouse);
					offset = offset.add(diff);
				}
				mouse = now;
			}
		});
		
		Monopoly.getWindow().getScene().getGameCanvas().addEventHandler(MouseEvent.MOUSE_RELEASED, e -> {
			mouse = null;
			CursorHandler.cursor = CursorHandler.NORMAL;
		});
		
		Monopoly.getWindow().getScene().getGameCanvas().addEventHandler(ScrollEvent.SCROLL, e -> {
			if(!Monopoly.guiScreenOpen) {
				zoom += e.getDeltaY() * 0.01;
				checkZoom = true;
			}
		});
		
		initGui();
		initTurns();
	}
	
	private void initGui() {
		guiHandler = new GuiHandler(this);
		GuiHandler.addGuiHandler(guiHandler);
		
		initGotoMenu();
	}
	
	GuiScreen gotoMenuScreen;
	GuiButton exit;
	private void initInGame() {
		exit = new GuiButton(new Point2D(2, 2), () -> { gotoMenuScreen.show(); }, "Main Menu", 15);
		
		roll = new GuiButton(new Point2D(2, 2 + exit.getPosition().getMinY() + exit.getPosition().getHeight()), () -> {
			if(cooldown <= 0) {
				int[] dice = turns.rollDice(true);
				int total = dice[0] + dice[1];
				turns.getCurrentPlayer().moveForward(total);
				
				if(dice[0] != dice[1]) {
					this.rolled = true;
					turns.getCurrentPlayer().resetDoubles();
				} else {
					Monopoly.log("DOUBLES!");
					turns.getCurrentPlayer().doubles();
				}
			}
		}, "Roll Dice", 15);
		
		next = new GuiButton(new Point2D(2, 2 + roll.getPosition().getMinY() + roll.getPosition().getHeight()), () -> {
			if(cooldown <= 0) {
				this.rolled = false;
				turns.nextTurn();
			}
		}, "Next Turn", 15);
		
		currentPlayer = new GuiLabel("player.turn", new Point2D(Monopoly.canvasSize().getX() / 2,
				Monopoly.canvasSize().getY() - 24), Font.font(48), Color.BLACK, true, VPos.BOTTOM);
		
		guiHandler.addElement(exit);
		guiHandler.addElement(roll);
		guiHandler.addElement(next);
		guiHandler.addElement(currentPlayer);
		
		exit.show();
		roll.show();
		currentPlayer.show();
	}
	
	private void initGotoMenu() {
		gotoMenuScreen = new GuiScreen(new Point2D(Monopoly.canvasSize().getX() / 2, Monopoly.canvasSize().getY() / 2));
		guiHandler.addElement(gotoMenuScreen);
		
		GuiLabel label = new GuiLabel("Go to main menu?", new Point2D(gotoMenuScreen.getPosition().getMinX() + gotoMenuScreen.getPosition().getWidth() / 2,
				gotoMenuScreen.getPosition().getMinY() + 10), Font.font(24), Color.WHITE, true);
		
		GuiButtonCentered no = new GuiButtonCentered(new Point2D(gotoMenuScreen.getPosition().getMinX() + gotoMenuScreen.getPosition().getWidth() / 2,
				gotoMenuScreen.getPosition().getMinY() + gotoMenuScreen.getPosition().getHeight() - 100), () -> {
					gotoMenuScreen.hide();
				}, "No", 5, gotoMenuScreen.getPosition().getWidth() / 1.2);
		no.setColorScheme(Color.WHITE, Color.rgb(0, 0, 100), Color.WHITE, Color.rgb(0, 0, 150));
		
		GuiButtonCentered yes = new GuiButtonCentered(new Point2D(gotoMenuScreen.getPosition().getMinX() + gotoMenuScreen.getPosition().getWidth() / 2,
			no.getPosition().getMinY() + no.getPosition().getHeight() * 1.5), () -> {
				Monopoly.getStateManager().setGameState(GameStateManager.mainMenu);
			}, "Yes", 5, gotoMenuScreen.getPosition().getWidth() / 1.2);
		yes.setColorScheme(Color.WHITE, Color.rgb(100, 0, 0), Color.WHITE, Color.rgb(150, 0, 0));
		
		gotoMenuScreen.addElement(label);
		gotoMenuScreen.addElement(no);
		gotoMenuScreen.addElement(yes);
	}
	
	public void exitState(GameState next) {
		GameObjectInst.objInstances.clear();
		Monopoly.log("Cleared instances");
		GameObject.gameObjs.clear();
		Monopoly.log("Cleared objects.");
		guiHandler.clear();
		Monopoly.log("Cleared gui.");
		GuiHandler.removeHandler(guiHandler);
		guiHandler = null;
		Monopoly.log("Disabled GUI handler.");
	}
	
}