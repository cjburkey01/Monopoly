package com.cjburkey.monopoly.object.instance;

import java.util.ArrayList;
import java.util.List;
import com.cjburkey.monopoly.object.GameObject;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;

public class ObjectInstance {
	
	public GameObject parent;
	public Point2D position;
	
	public ObjectInstance(GameObject parent) {
		this.parent = parent;
		this.position = new Point2D(0, 0);
	}
	
	public Point2D getPosition() {
		return this.position;
	}
	
	public void onAdd() { parent.onAdd(this); }
	public void tick() { parent.tick(this); }
	public void perSecond(int fps) { parent.perSecond(fps, this); }
	public void render(GraphicsContext gc) { parent.render(gc, this); }
	
	public static final List<ObjectInstance> objInstances = new ArrayList<ObjectInstance>();
	
	public static final ObjectInstance createInstance(GameObject obj, Point2D pos) {
		ObjectInstance inst = new ObjectInstance(obj);
		inst.position = pos;
		objInstances.add(inst);
		inst.onAdd();
		return inst;
	}
	
}