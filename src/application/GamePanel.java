package application;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import Manager.GameStateManager;
import Manager.Keys;

public class GamePanel extends Canvas implements Runnable {
	
	private static Stage primaryStage;
	public static final int WIDTH = 1000;
	public static final int HEIGHT = 350;
	
	// game loop stuff
	private Thread thread;
	private static boolean running;
	private final int FPS = 30;
	private final int TARGET_TIME = 1000 / FPS;
	
	// game state manager
	private GameStateManager gsm;
	
	
	// constructor
	public GamePanel() {
		super(WIDTH,HEIGHT);
		requestFocus();
		startAnimation();
		//System.out.println("kuy");
		
	}
	
	public void startAnimation() {
		thread = new Thread(this::run, "Game Animation Thread");
		running = true;
		thread.start();
	}

	public static void stopAnimation() {
		running = false;
	}

	// run new thread
	public void run() {
		init();
		
		long start;
		long elapsed;
		long wait;
		
		// game loop
		while(running) {
			
			start = System.nanoTime();
			update();
			draw();
			
			
			
			elapsed = System.nanoTime() - start;
			
			wait = TARGET_TIME - elapsed / 1000000;
			if(wait < 0) wait = TARGET_TIME;
			
			try {
				Thread.sleep(wait);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			
		}
		
	}
	
	// initializes fields
	private void init() {
		running = true;
		gsm = new GameStateManager();
	}
	
	// updates game
	private void update() {
		gsm.update();
		Keys.update();
	}
	
	// draws game
	private void draw() {
		gsm.draw(this);
		
		
		
	}
	

	
}