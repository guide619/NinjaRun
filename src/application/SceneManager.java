package application;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public final class SceneManager {

	private static Stage primaryStage;
	private static Canvas panelCanvas = new GamePanel();
	private static Scene panelScene = new Scene(new Pane(panelCanvas));

	public static void initialize(Stage stage) {
		primaryStage = stage;
		primaryStage.show();
	}

	public static void gotoMainMenu() {
		//TODO Fill Code
		primaryStage.setScene(panelScene);
		panelCanvas.requestFocus();
	}

	public static void gotoSceneOf(Canvas canvas) {
		//TODO Fill Code
		Scene scene = new Scene(new Pane(canvas));
		canvas.requestFocus();
		primaryStage.setScene(scene);
	}
}
