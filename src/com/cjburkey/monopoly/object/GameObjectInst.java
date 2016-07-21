package com.cjburkey.monopoly.object;

import java.util.ArrayList;
import java.util.List;
import com.cjburkey.monopoly.util.Data;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class GameObjectInst {
	
	public GameObject parent;
	private Point2D position;
	
	private double tmpPosX = Integer.MAX_VALUE;
	private double tmpPosY = Integer.MAX_VALUE;
	
	private final List<Data> data = new ArrayList<Data>();
	
	public GameObjectInst(GameObject parent) {
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
	
	public Point2D getPos() {
		if(tmpPosX != Integer.MAX_VALUE && tmpPosY != Integer.MAX_VALUE) {
			return new Point2D(tmpPosX, tmpPosY);
		}
		return this.position;
	}
	
	public void setPos(Point2D pos) {
		this.position = pos;
	}
	
	private Rectangle rect = new Rectangle(Integer.MAX_VALUE, Integer.MAX_VALUE, 0, 0);
	public void moveToPos(Point2D point) {
		rect = new Rectangle(this.position.getX(), this.position.getY(), 0, 0);
		
		KeyValue x = new KeyValue(rect.xProperty(), point.getX());
		KeyValue y = new KeyValue(rect.yProperty(), point.getY());
		
		KeyFrame frame = new KeyFrame(Duration.millis(1000), x, y);
		
		Timeline tl  = new Timeline();
		tl.setCycleCount(1);
		tl.setAutoReverse(false);
		tl.getKeyFrames().add(frame);
		tl.play();
		this.position = point;
		
		tl.setOnFinished(e -> {
			tmpPosX = Integer.MAX_VALUE;
			tmpPosY = Integer.MAX_VALUE;
			rect = new Rectangle(Integer.MAX_VALUE, Integer.MAX_VALUE, 0, 0);
		});
	}
	
	public Object getData(String key) {
		for(Data data : data) {
			if(data != null && data.getKey() != null && data.getKey().equals(key)) {
				return data.getValue();
			}
		}
		return null;
	}
	
	public void onAdd() { parent.onAdd(this); }
	public void tick() { parent.tick(this); }
	public void perSecond(int fps) { parent.perSecond(fps, this); }
	public void render(GraphicsContext gc) {
		if(rect.getX() != Integer.MAX_VALUE && rect.getY() != Integer.MAX_VALUE) {
			this.tmpPosX = rect.getX();
			this.tmpPosY = rect.getY();
		}
		parent.render(gc, this);
	}
	
	public static final List<GameObjectInst> objInstances = new ArrayList<GameObjectInst>();
	
	public static final GameObjectInst createInstance(GameObject obj, Point2D pos) {
		GameObjectInst inst = new GameObjectInst(obj);
		inst.position = pos;
		objInstances.add(inst);
		inst.onAdd();
		return inst;
	}
	
}