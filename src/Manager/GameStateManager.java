package Manager;

import java.awt.Graphics2D;

import GameState.GameOverState;
import GameState.GameState;
import GameState.IntroState;
import GameState.MenuState;
import GameState.PauseState;
import GameState.PlayState;
import javafx.scene.canvas.GraphicsContext;


	public class GameStateManager {
		
		private boolean paused;
		private PauseState pauseState;
		
		private GameState[] gameStates;
		private int currentState;
		private int previousState;
		
		public static final int NUM_STATES = 4;
		public static final int INTRO = 0;
		public static final int MENU = 1;
		public static final int PLAY = 2;
		public static final int GAMEOVER = 3;
		
		public GameStateManager() {
			
			
			paused = false;
			pauseState = new PauseState(this);
			
			gameStates = new GameState[NUM_STATES];
			setState(INTRO);
			
		}
		
		public void setState(int i) {
			previousState = currentState;
			unloadState(previousState);
			currentState = i;
			if(i == INTRO) {
				gameStates[i] = new IntroState(this);
				gameStates[i].init();
			}
			else if(i == MENU) {
				gameStates[i] = new MenuState(this);
				gameStates[i].init();
			}
			else if(i == PLAY) {
				gameStates[i] = new PlayState(this);
				gameStates[i].init();
			}
			else if(i == GAMEOVER) {
				gameStates[i] = new GameOverState(this);
				gameStates[i].init();
			}
		}
		
		public void unloadState(int i) {
			gameStates[i] = null;
		}
		
		public void setPaused(boolean b) {
			paused = b;
		}
		
		public void update() {
			if(paused) {
				pauseState.update();
			}
			else if(gameStates[currentState] != null) {
				gameStates[currentState].update();
			}
		}
		
		public void draw(GraphicsContext g) {
			if(paused) {
				pauseState.draw(g);
			}
			else if(gameStates[currentState] != null) {
				gameStates[currentState].draw(g);
			}
		}
	}

