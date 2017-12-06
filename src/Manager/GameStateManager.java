package Manager;


import GameState.GameOverState;
import GameState.GameState;
import GameState.IntroState;
import GameState.MenuState;
import GameState.PauseState;
import GameState.PlayState;
import application.Main;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;


	public class GameStateManager {
		
		private boolean paused;
		private PauseState pauseState;
		
		private static GameState[] gameStates;
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
			//System.out.println("...........");
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
		
		public void draw(Canvas game) {
			if(paused) {
				pauseState.draw(game);
			}
			else if(gameStates[currentState] != null) {
				gameStates[currentState].draw(game);
			}
		}

		public GameState getCurrentState() {
			return gameStates[currentState];
		}
		
	}

