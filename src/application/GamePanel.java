package application;

import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;
import Manager.GameStateManager;
import Manager.Keys;

public class GamePanel extends Canvas implements Runnable {
	
	public static final int WIDTH = 500;
	public static final int HEIGHT = 175;
	public static final int SCALE = 2;
	
	// game loop stuff
	private Thread thread;
	private boolean running;
	private final int FPS = 30;
	private final int TARGET_TIME = 1000 / FPS;
	
	// game state manager
	private GameStateManager gsm;
	
	
	// constructor
	public GamePanel() {
		super(WIDTH*SCALE,HEIGHT*SCALE);
		requestFocus();
		startAnimation();
		//System.out.println("kuy");
		
	}
	
	public void startAnimation() {
		thread = new Thread(this::run, "Game Animation Thread");
		running = true;
		thread.start();
	}

	public void stopAnimation() {
		running = false;
	}

	// run new thread
	public void run() {
		//System.out.println("kuy");
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
		gsm.draw();
	}
	
	// key event
	public void keyTyped(KeyEvent key) {}
	public void keyPressed(KeyEvent key) {
		Keys.keySet(key, true);
	}
	public void keyReleased(KeyEvent key) {
		Keys.keySet(key, false);
	}
	public GameStateManager getGameStateManager() {
		return gsm;
		
	}
	
}