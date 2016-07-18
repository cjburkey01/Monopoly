package com.cjburkey.monopoly;

import javafx.application.Preloader;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class LoadingScreen extends Preloader {
	
	private ProgressBar bar;
	private Stage stage;
	
	private Scene createPreloaderScene() {
		bar = new ProgressBar();
		BorderPane p = new BorderPane();
		p.setCenter(bar);
		return new Scene(p, 300, 150);
	}
	
	public void start(Stage s) {
		this.stage = s;
		this.stage.setScene(createPreloaderScene());
		this.stage.show();
	}
	
	public void handleProgressNotification(ProgressNotification pn) {
		this.bar.setProgress(pn.getProgress());
	}
	
	public void handleStateChangeNotification(StateChangeNotification e) {
		if(e.getType().equals(StateChangeNotification.Type.BEFORE_START)) {
			stage.hide();
		}
	}
	
}