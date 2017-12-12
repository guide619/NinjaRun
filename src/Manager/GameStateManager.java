package Manager;


import GameState.GameOverState;
import GameState.GameState;
import GameState.IntroState;
import GameState.MenuState;
import GameState.PauseState;
import GameState.PlayState;
import javafx.scene.canvas.Canvas;

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
		
		public void setState(int state) {
			previousState = currentState;
			unloadState(previousState);
			currentState = state;
			if(state == INTRO) {
				gameStates[state] = new IntroState(this);
				gameStates[state].init();
			}
			else if(state == MENU) {
				gameStates[state] = new MenuState(this);
				gameStates[state].init();
			}
			else if(state == PLAY) {
				gameStates[state] = new PlayState(this);
				gameStates[state].init();
			}
		}
		public void setState(int state,int score) {
			previousState = currentState;
			unloadState(previousState);
			currentState = state;
			gameStates[state] = new GameOverState(this,score);
			gameStates[state].init();
		}
		
		public void unloadState(int state) {
			gameStates[state] = null;
		}
		
		public void setPaused(boolean b) {
			pauseState.init();
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

