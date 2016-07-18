package com.cjburkey.monopoly.state;

import com.cjburkey.monopoly.Monopoly;
import com.cjburkey.monopoly.handler.MouseHandler;
import com.cjburkey.monopoly.object.GameObject;
import com.cjburkey.monopoly.object.ObjInst;
import com.cjburkey.monopoly.util.Maths;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;

public class GameStateMainGame extends GameState {
	
	private static Point2D offset = Point2D.ZERO;
	private static float zoom = 1;
	
	private static Point2D mouse;
	private static float lastZoom;
	private static int lastZoomTime = 4;
	private static boolean checkZoom = false;
	
	public static Point2D minMaxZoom = new Point2D(1.3, 3.8);
	public static Point2D[] minMaxOffset = {
		new Point2D(-176, 176),
		new Point2D(-176, 176)
	};
	
	public GameStateMainGame() {
		super("gameStateMainGame");
	}
	
	public void tick(float delta) {
		for(ObjInst inst : ObjInst.objInstances) {
			inst.tick(delta);
		}
	}
	
	public void perSecond(int fps) {
		for(ObjInst inst : ObjInst.objInstances) {
			inst.perSecond(fps);
		}
	}
	
	public void render(float delta, GraphicsContext gc) {
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
		
		for(ObjInst inst : ObjInst.objInstances) {
			inst.render(delta, gc);
		}
		
		gc.translate(-offset.getX(), -offset.getY());
		gc.scale(1 / zoom, 1 / zoom);
	}
	
	public void enterState(GameState previous) {
		ObjInst.createInstance(GameObject.gameObjectGameBoard, Point2D.ZERO);
		Monopoly.getWindow().getScene().getGameCanvas().addEventHandler(MouseEvent.MOUSE_DRAGGED, e -> {
			if(e.getButton().equals(MouseButton.MIDDLE)) {
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
			if(e.getButton().equals(MouseButton.MIDDLE)) {
				mouse = null;
				MouseHandler.cursor = MouseHandler.NORMAL;
			}
		});
		
		Monopoly.getWindow().getScene().getGameCanvas().addEventHandler(ScrollEvent.SCROLL, e -> {
			zoom += e.getDeltaY() * 0.001;
			checkZoom = true;
		});
	}
	
	public void exitState(GameState next) {  }
	
}