package Manager;


import javafx.scene.input.*;

public class Keys {
	
	public static final int NUM_KEYS = 8;
	
	public static boolean keyState[] = new boolean[NUM_KEYS];
	public static boolean prevKeyState[] = new boolean[NUM_KEYS];
	
	public static int UP = 0;
	public static int LEFT = 1;
	public static int DOWN = 2;
	public static int RIGHT = 3;
	public static int SPACE = 4;
	public static int ENTER = 5;
	public static int ESCAPE = 6;

	
	public static void keySet(KeyEvent k , boolean b) {
		if(k.getCode().equals(KeyCode.UP)) keyState[UP] = b;
		else if(k.getCode().equals(KeyCode.LEFT)) keyState[LEFT] = b;
		else if(k.getCode().equals(KeyCode.DOWN)) keyState[DOWN] = b;
		else if(k.getCode().equals(KeyCode.RIGHT)) keyState[RIGHT] = b;
		else if(k.getCode().equals(KeyCode.SPACE)) keyState[SPACE] = b;
		else if(k.getCode().equals(KeyCode.ENTER)) keyState[ENTER] = b;
		else if(k.getCode().equals(KeyCode.ESCAPE)) keyState[ESCAPE] = b;
		
	}
	
	public static void update() {
		for(int i = 0; i < NUM_KEYS; i++) {
			prevKeyState[i] = keyState[i];
		}
	}
	
	public static boolean isPressed(int key) {
		return keyState[key] && !prevKeyState[key];
	}
	
	public static boolean isDown(int key) {
		return keyState[key];
	}
	
	public static boolean anyKeyDown() {
		for(int i = 0; i < NUM_KEYS; i++) {
			if(keyState[i]) return true;
		}
		return false;
	}
	
	public static boolean anyKeyPress() {
		for(int i = 0; i < NUM_KEYS; i++) {
			if(keyState[i] && !prevKeyState[i]) return true;
		}
		return false;
	}
	
}
