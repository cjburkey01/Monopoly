package com.cjburkey.monopoly.gameloop;

import com.cjburkey.monopoly.Monopoly;
import javafx.application.Platform;
import javafx.concurrent.Task;

public class MainLoop {
	
	private static boolean init = false;
	private boolean running = false;
	
	public static final double GAME_HERTZ = 30.0d;
	public static final double TIME_BETWEEN_UPDATES = 1000000000 / GAME_HERTZ;
	public static final int MAX_UPDATES_BEFORE_RENDER = 5;
	public static final double TARGET_FPS = 60;
	public static final double TARGET_TIME_BETWEEN_RENDERS = 1000000000 / TARGET_FPS;
	
	private double lastUpdateTime = System.nanoTime();
	private double lastRenderTime = System.nanoTime();
	private int lastSecondTime = (int) (lastUpdateTime / 1000000000);
	private float delta = 1;
	
	private int fps;
	private int frameCount;
	
	public static final MainLoop createGameLoop() {
		MainLoop loop = new MainLoop();
		Task<Void> task = new Task<Void>() {
			public Void call() {
				loop.start();
				return null;
			}
		};
		
		Thread thread = new Thread(task);
		thread.start();
		
		return loop;
	}
	
	public void start() {
		Thread.setDefaultUncaughtExceptionHandler((t, e) -> {
			Monopoly.genericError(e);
		});
		
		if(init) return;
		init = true;
		running = true;
		
		Monopoly.log("Started MainGameLoop");
		
		while(running) {
			double now = System.nanoTime();
			int updateCount = 0;
			
			while(now - lastUpdateTime > TIME_BETWEEN_UPDATES && updateCount < MAX_UPDATES_BEFORE_RENDER) {
				updateGame(delta);
				lastUpdateTime += TIME_BETWEEN_UPDATES;
				updateCount ++;
			}
			
			if(now - lastUpdateTime > TIME_BETWEEN_UPDATES) {
				lastUpdateTime = now - TIME_BETWEEN_UPDATES;
			}
			
			delta = Math.min(1.0f, (float) ((now - lastUpdateTime) / TIME_BETWEEN_UPDATES));
			drawGame(delta);
			lastRenderTime = now;
			
			int thisSecond = (int) (lastUpdateTime / 1000000000);
			if(thisSecond > lastSecondTime) {
				fps = frameCount;
				frameCount = 0;
				lastSecondTime = thisSecond;
				perSecond(fps);
			}
			
			while(now - lastRenderTime < TARGET_TIME_BETWEEN_RENDERS && now - lastUpdateTime < TIME_BETWEEN_UPDATES) {
				Thread.yield();
				
				try {
					Thread.sleep(1);
				} catch(Exception e) {  }
				
				now = System.nanoTime();
			}
		}
		
		try {
			Monopoly.log("Giving everything in the FX thread 1000 miliseconds to finish up.");
			Thread.sleep(1000);
			Monopoly.log("Done.");
		} catch(Exception e) {
			e.printStackTrace();
			Monopoly.log("There was an error during the wait.  Exit now.");
		}

		Monopoly.log("Closing.");
		Platform.exit();
	}
	
	public void updateGame(float delta) {
		Monopoly.tick(delta);
	}
	
	public void perSecond(int fps) {
		Monopoly.perSecond(fps);
	}
	
	public void drawGame(float delta) {
		frameCount ++;
		Monopoly.render(delta);
	}
	
	public void stop() {
		this.running = false;
	}
	
	public boolean isRunning() {
		return this.running;
	}
	
}