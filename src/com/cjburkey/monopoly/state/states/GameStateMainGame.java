package com.cjburkey.monopoly.state.states;

import com.cjburkey.monopoly.Monopoly;
import com.cjburkey.monopoly.handler.MouseHandler;
import com.cjburkey.monopoly.object.GameObject;
import com.cjburkey.monopoly.object.instance.ObjectInstance;
import com.cjburkey.monopoly.object.objects.GameObjectGameBoard;
import com.cjburkey.monopoly.render.gui.GuiHandler;
import com.cjburkey.monopoly.render.gui.elements.GuiButton;
import com.cjburkey.monopoly.render.gui.elements.GuiButtonCentered;
import com.cjburkey.monopoly.render.gui.elements.GuiLabel;
import com.cjburkey.monopoly.render.gui.elements.GuiScreen;
import com.cjburkey.monopoly.state.GameState;
import com.cjburkey.monopoly.state.GameStateManager;
import com.cjburkey.monopoly.turn.Player;
import com.cjburkey.monopoly.turn.TurnManager;
import com.cjburkey.monopoly.util.Maths;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class GameStateMainGame extends GameState {
	
	private static Point2D offset = Point2D.ZERO;
	private static float zoom = 1;
	
	private static Point2D mouse;
	private static float lastZoom;
	private static int lastZoomTime = 4;
	private static boolean checkZoom = false;
	private static GuiHandler guiHandler;
	
	public static Point2D minMaxZoom = new Point2D(1.3, 3.8);
	public static Point2D[] minMaxOffset = {
		new Point2D(-GameObjectGameBoard.sizeWidth / 2, GameObjectGameBoard.sizeWidth / 2),
		new Point2D(-GameObjectGameBoard.sizeWidth / 2, GameObjectGameBoard.sizeWidth / 2)
	};
	
	public GameStateMainGame() {
		super("gameStateMainGame");
	}
	
	public void tick() {
		for(ObjectInstance inst : ObjectInstance.objInstances) {
			inst.tick();
		}
	}
	
	public void perSecond(int fps) {
		for(ObjectInstance inst : ObjectInstance.objInstances) {
			inst.perSecond(fps);
		}
	}
	
	public void render(GraphicsContext gc) {
		gc.save();
		
		zoom = (float) Maths.clamp(zoom, minMaxZoom.getX(), minMaxZoom.getY());
		offset = new Point2D(Maths.clamp(offset.getX(), minMaxOffset[0].getX(), minMaxOffset[0].getY()),
				Maths.clamp(offset.getY(), minMaxOffset[1].getX(), minMaxOffset[1].getY()));
		
		if(lastZoomTime <= 0) {
			lastZoomTime = 4;
			if(checkZoom) {
				if(lastZoom == zoom) {
					checkZoom = false;
					MouseHandler.cursor = MouseHandler.NORMAL;
				} else {
					checkZoom = true;
					MouseHandler.cursor = MouseHandler.ZOOM;
				}
			}
		}
		
		lastZoom = zoom;
		lastZoomTime --;
		
		gc.scale(zoom, zoom);
		gc.translate(offset.getX(), offset.getY());
		
		for(ObjectInstance inst : ObjectInstance.objInstances) {
			gc.save();
			inst.render(gc);
			gc.restore();
		}
		
		gc.translate(-offset.getX(), -offset.getY());
		gc.scale(1 / zoom, 1 / zoom);
		
		gc.restore();
		
		players = (int) Maths.clamp(players, 2, 8);
		playerLabel.setText(players + " Players");
	}

	int players = 2;
	GuiLabel playerLabel;
	GuiButton exit;
	private void initTurns() {
		Canvas canvas = Monopoly.getWindow().getScene().getGameCanvas();
		GuiScreen selectionWindow = new GuiScreen(new Point2D(canvas.getWidth() / 1.5, canvas.getHeight() / 1.5), true);
		guiHandler.addElement(selectionWindow);
		
		GuiButtonCentered plus = new GuiButtonCentered(Point2D.ZERO, () -> { players ++; }, "+", 5d, 50d);
		GuiButtonCentered minus = new GuiButtonCentered(Point2D.ZERO, () -> { players --; }, "-", 5d, 50d);
		GuiButtonCentered go = new GuiButtonCentered(Point2D.ZERO, () -> { setupGame(players); exit.show(); selectionWindow.hide(); }, "Start Game", 5d);
		
		playerLabel = new GuiLabel(players + " Players", new Point2D(canvas.getWidth() / 2,
				selectionWindow.getPosition().getMinY() + go.getPosition().getHeight() + 25), Font.font(45), Color.WHITE, true);
		
		plus.setPosition(new Point2D(canvas.getWidth() / 2 - plus.getPosition().getWidth() / 2,
				selectionWindow.getPosition().getMinY() + plus.getPosition().getWidth() / 2 + 10));
		minus.setPosition(new Point2D(canvas.getWidth() / 2 + minus.getPosition().getWidth() / 2,
				selectionWindow.getPosition().getMinY() + minus.getPosition().getWidth() / 2 + 10));
		go.setPosition(new Point2D(canvas.getWidth() / 2,
				selectionWindow.getPosition().getMinY() + selectionWindow.getPosition().getHeight() - go.getPosition().getHeight() / 2 - 10));

		plus.setColorScheme(Color.WHITE, Color.rgb(0, 100, 0), Color.WHITE, Color.rgb(0, 150, 0));
		minus.setColorScheme(Color.WHITE, Color.rgb(100, 0, 0), Color.WHITE, Color.rgb(150, 0, 0));
		go.setColorScheme(Color.WHITE, Color.rgb(0, 0, 100), Color.WHITE, Color.rgb(0, 0, 150));
		
		selectionWindow.addElement(plus);
		selectionWindow.addElement(minus);
		selectionWindow.addElement(go);
		selectionWindow.addElement(playerLabel);
		
		selectionWindow.show();
	}
	
	private void setupGame(int players) {
		for(int i = 0; i < players; i ++) {
			Player p = new Player("Player " + (i + 1));
			TurnManager.addPlayer(p);
		}
		
		for(int i = 1; i < 11; i ++) {
			ObjectInstance inst1 = ObjectInstance.createInstance(GameObject.gameObjectBoardSlot, new Point2D(-GameObject.gameObjectGameBoard.getSize().getX() / 2,
					-32 * (i + 1) + GameObject.gameObjectGameBoard.getSize().getY() / 2));
			inst1.setData("gameObjectBoardSlot-ID", i);
			
			ObjectInstance inst2 = ObjectInstance.createInstance(GameObject.gameObjectBoardSlot,
					new Point2D(32 * i - GameObject.gameObjectGameBoard.getSize().getX() / 2, -GameObject.gameObjectGameBoard.getSize().getY() / 2));
			inst2.setData("gameObjectBoardSlot-ID", i + 10);
			
			ObjectInstance inst3 = ObjectInstance.createInstance(GameObject.gameObjectBoardSlot,
					new Point2D(GameObject.gameObjectGameBoard.getSize().getX() / 2 - 32, 32 * i - GameObject.gameObjectGameBoard.getSize().getY() / 2));
			inst3.setData("gameObjectBoardSlot-ID", i + 20);
			
			ObjectInstance inst4 = ObjectInstance.createInstance(GameObject.gameObjectBoardSlot,
					new Point2D(-32 * (i + 1) + GameObject.gameObjectGameBoard.getSize().getX() / 2, GameObject.gameObjectGameBoard.getSize().getY() / 2 - 32));
			inst4.setData("gameObjectBoardSlot-ID", (i + 30 == 40) ? 0 : i + 30);
		}
	}
	
	public void enterState(GameState previous) {
		ObjectInstance.createInstance(GameObject.gameObjectGameBoard, Point2D.ZERO);
		Monopoly.getWindow().getScene().getGameCanvas().addEventHandler(MouseEvent.MOUSE_DRAGGED, e -> {
			if(!Monopoly.guiScreenOpen && e.getButton().equals(MouseButton.MIDDLE)) {
				MouseHandler.cursor = MouseHandler.MOVE;
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
			MouseHandler.cursor = MouseHandler.NORMAL;
		});
		
		Monopoly.getWindow().getScene().getGameCanvas().addEventHandler(ScrollEvent.SCROLL, e -> {
			if(!Monopoly.guiScreenOpen) {
				zoom += e.getDeltaY() * 0.01;
				checkZoom = true;
			}
		});
		
		guiHandler = new GuiHandler(this);
		GuiHandler.addGuiHandler(guiHandler);
		
		initTurns();
		
		Canvas canvas = Monopoly.getWindow().getScene().getGameCanvas();
		GuiScreen screen = new GuiScreen(new Point2D(canvas.getWidth() / 2, canvas.getHeight() / 2), true);
		guiHandler.addElement(screen);
		
		GuiLabel label = new GuiLabel("Go to main menu?", new Point2D(screen.getPosition().getMinX() + screen.getPosition().getWidth() / 2,
				screen.getPosition().getMinY() + 10), Font.font(24), Color.WHITE, true);
		screen.addElement(label);
		
		GuiButtonCentered no = new GuiButtonCentered(new Point2D(screen.getPosition().getMinX() + screen.getPosition().getWidth() / 2,
				screen.getPosition().getMinY() + screen.getPosition().getHeight() - 100), () -> {
					screen.hide();
				}, "No", 5, screen.getPosition().getWidth() / 1.2);
		screen.addElement(no);
		no.setColorScheme(Color.WHITE, Color.rgb(0, 0, 100), Color.WHITE, Color.rgb(0, 0, 150));
		
		GuiButtonCentered yes = new GuiButtonCentered(new Point2D(screen.getPosition().getMinX() + screen.getPosition().getWidth() / 2,
			no.getPosition().getMinY() + no.getPosition().getHeight() * 1.5), () -> {
				Monopoly.getStateManager().setGameState(GameStateManager.mainMenu);
			}, "Yes", 5, screen.getPosition().getWidth() / 1.2);
		screen.addElement(yes);
		yes.setColorScheme(Color.WHITE, Color.rgb(100, 0, 0), Color.WHITE, Color.rgb(150, 0, 0));
		
		exit = new GuiButton(new Point2D(2, 2), () -> { screen.show(); }, "Main Menu", 15);
		guiHandler.addElement(exit);
	}
	
	public void exitState(GameState next) {
		ObjectInstance.objInstances.clear();
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