package Main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class GameMain extends Application {
	private static Scene gm = new Scene(new Pane (new GamePanel()));

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		primaryStage.setScene(gm);
		primaryStage.show();
	}
	public static void main(String[] args) {
		launch(args);
	}
	

}
