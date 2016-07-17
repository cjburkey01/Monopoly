package com.cjburkey.monopoly.object;

import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;

public class ObjInst {
	
	public GameObject parent;
	public Rectangle2D positionAndSize;
	
	public ObjInst(GameObject parent) {
		this.parent = parent;
	}
	
	public void tick(float delta) { parent.tick(delta, this); }
	public void perSecond(int fps) { parent.perSecond(fps, this); }
	public void render(float delta, GraphicsContext gc) { parent.render(delta, gc, this); }
	
	public static final List<ObjInst> objInstances = new ArrayList<ObjInst>();
	
}