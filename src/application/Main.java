package application;

import GameState.IntroState;
import Manager.GameStateManager;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			GameStateManager gsm = new GameStateManager();
			gsm.setState(GameStateManager.INTRO);
			primaryStage.setTitle("NINJA RUN");
			primaryStage.centerOnScreen();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void stop() throws Exception {
		// TODO Auto-generated method stub
		
	}

	public static void main(String[] args) {
		launch(args);
	}

}