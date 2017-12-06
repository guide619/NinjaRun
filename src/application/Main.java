package application;

import Manager.Keys;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {
	private static Canvas panelCanvas = new GamePanel();
	private static Scene panelScene = new Scene(new Pane(panelCanvas));
	
	@Override
	public void start(Stage primaryStage) {
		try {
			this.addKeyEventHandler();
			primaryStage.setTitle("NINJA RUN");
			primaryStage.centerOnScreen();
			primaryStage.setScene(panelScene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void stop() throws Exception {
		// TODO Auto-generated method stub
		GamePanel.stopAnimation();
	}
	private void addKeyEventHandler() {
		panelScene.setOnKeyPressed(new EventHandler<KeyEvent>(){
			public void handle(KeyEvent key) {
				Keys.keySet(key, true);
			}
		});
		panelScene.setOnKeyReleased(new EventHandler<KeyEvent>(){
			public void handle(KeyEvent key) {
				Keys.keySet(key, false);
			}
		});
	}
	

	public static void main(String[] args) {
		launch(args);
	}
}
