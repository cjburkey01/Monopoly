package com.cjburkey.monopoly.interfaces;

import com.cjburkey.monopoly.object.instance.ObjectInstance;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;

public interface IGameObject {
	
	public String getName();
	public Point2D getSize();
	public void onAdd(ObjectInstance inst);
	public void tick(ObjectInstance inst);
	public void perSecond(int fps, ObjectInstance inst);
	public void render(GraphicsContext gc, ObjectInstance inst);
	
}