package com.cjburkey.monopoly.object;

import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;

public class ObjInst {
	
	public GameObject parent;
	public Point2D position;
	
	public ObjInst(GameObject parent) {
		this.parent = parent;
		this.position = new Point2D(0, 0);
	}
	
	public Point2D getPosition() {
		return this.position;
	}
	
	public void onAdd() { parent.onAdd(this); }
	public void tick(float delta) { parent.tick(delta, this); }
	public void perSecond(int fps) { parent.perSecond(fps, this); }
	public void render(float delta, GraphicsContext gc) { parent.render(delta, gc, this); }
	
	public static final List<ObjInst> objInstances = new ArrayList<ObjInst>();
	
	public static final ObjInst createInstance(GameObject obj, Point2D pos) {
		ObjInst inst = new ObjInst(obj);
		inst.position = pos;
		objInstances.add(inst);
		inst.onAdd();
		return inst;
	}
	
}