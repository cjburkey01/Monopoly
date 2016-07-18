package com.cjburkey.monopoly.gameloop;

import com.cjburkey.monopoly.Monopoly;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.concurrent.Task;

public class NewLoop {
	
	private AnimationTimer timer;
	private Thread thread;
	
	public static final NewLoop createLoop() {
		final NewLoop loop = new NewLoop();
		loop.thread = new Thread(new Task<Void> () {
			public Void call() {
				loop.start();
				return null;
			}
		});
		loop.thread.start();
		return loop;
	}
	long starttimeNano = System.nanoTime();
	int fps = 0;
	long lasttimeFPS = 0;
	public void start() {
		timer = new AnimationTimer() {
			public void handle(long now) {
				fps ++;
				long currenttimeNano = System.nanoTime();
				if(currenttimeNano > lasttimeFPS + 1000000000) {
					Monopoly.log(fps + " FPS");
					fps = 0;
					lasttimeFPS = currenttimeNano;
				}
				
				tick();
				render();
			}
		};
		
		timer.start();
	}
	
	public void stop() {
		timer.stop();

		Monopoly.log("Closing.");
		Platform.exit();
	}
	
	private void tick() {
		Monopoly.tick();
	}
	
	private void render() {
		Monopoly.render();
	}
	
}