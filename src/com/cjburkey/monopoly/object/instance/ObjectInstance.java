package com.cjburkey.monopoly.object.instance;

import java.util.ArrayList;
import java.util.List;
import com.cjburkey.monopoly.object.GameObject;
import com.cjburkey.monopoly.util.Data;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;

public class ObjectInstance {
	
	public GameObject parent;
	public Point2D position;
	
	private final List<Data> data = new ArrayList<Data>();
	
	public ObjectInstance(GameObject parent) {
		this.parent = parent;
		this.position = new Point2D(0, 0);
	}
	
	public void setData(String key, Object value) {
		for(Data data : data) {
			if(data.getKey().equals(key)) {
				data.setValue(value);
				return;
			}
		}
		
		data.add(new Data(key, value));
	}
	
	public Object getData(String key) {
		for(Data data : data) {
			if(data != null && data.getKey() != null && data.getKey().equals(key)) {
				return data.getValue();
			}
		}
		return null;
	}
	
	public Point2D getPosition() {
		return this.position;
	}
	
	public void onAdd() { parent.onAdd(this); }
	public void tick() { parent.tick(this); }
	public void perSecond(int fps) { parent.perSecond(fps, this); }
	public void render(GraphicsContext gc) { parent.render(gc, this); }
	
	public static final ObjectInstance getInstFromId(int id) {
		for(ObjectInstance inst : objInstances) {
			Object data = inst.getData("gameObjectBoardSlot-ID");
			if(data != null) {
				if(data instanceof Integer) {
					int did = (int) data;
					if(did == id) {
						return inst;
					}
				} else if(data instanceof Double) {
					double tmp = (double) data;
					int did = (int) tmp;
					if(did == id) {
						return inst;
					}
				}
			}
		}
		return null;
	}
	
	public static final List<ObjectInstance> objInstances = new ArrayList<ObjectInstance>();
	
	public static final ObjectInstance createInstance(GameObject obj, Point2D pos) {
		ObjectInstance inst = new ObjectInstance(obj);
		inst.position = pos;
		objInstances.add(inst);
		inst.onAdd();
		return inst;
	}
	
}