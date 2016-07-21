package com.cjburkey.monopoly.interfaces;

import com.cjburkey.monopoly.object.GameObjectInst;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;

public interface IGameObject {
	
	public String getName();
	public Point2D getSize();
	public void onAdd(GameObjectInst inst);
	public void tick(GameObjectInst inst);
	public void perSecond(int fps, GameObjectInst inst);
	public void render(GraphicsContext gc, GameObjectInst inst);
	
}