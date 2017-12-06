package GameState;


import Object.ninja;

import java.awt.event.KeyEvent;

import Manager.GameStateManager;
import Manager.Keys;
import SharedObject.RenderableHolder;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class PlayState extends GameState implements Runnable {

	int dx ;
	ninja ninja ;
	private Thread thread;
	private static final int GAME_PLAYING_STATE = 1;
	private static final int GAME_OVER_STATE = 2;
	private boolean isKeyPressed =false;
	private int gameState = GAME_PLAYING_STATE;

	int i = 0;


	public PlayState(GameStateManager gsm) {
		super(gsm);
		// TODO Auto-generated constructor stub
		ninja = new ninja();
		ninja.setSpeedX(4);
		
	}
	public void startGame() {
		thread = new Thread(this);
		thread.start();
	}
	public void gameUpdate() {
		//if (gameState == GAME_PLAYING_STATE) {
			//clouds.update();
			//land.update();
			addKeyEventHandler();
			ninja.update();
			//enemiesManager.update();
			//if (enemiesManager.isCollision()) {
			//	mainCharacter.playDeadSound();
			//	gameState = GAME_OVER_STATE;
			//	mainCharacter.dead(true);
			//}
		//}
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
		
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		addKeyEventHandler();

		
	}

	@Override
	public void draw(Canvas game) {
		// TODO Auto-generated method stub
		GraphicsContext gc = game.getGraphicsContext2D();
		gc.setFill(Color.WHITE);
		gc.drawImage(RenderableHolder.bg1, 0, 0,1000,350);
		gc.fillText("HI " + ninja.score, 800, 200);
		switch (gameState) {
		case GAME_PLAYING_STATE:
			ninja.draw(game);
			break;
		case GAME_OVER_STATE:
			//clouds.draw(g);
			//land.draw(g);
			//enemiesManager.draw(g);
			ninja.draw(this);
			
			//g.setColor(Color.BLACK);
			//if (gameState == GAME_OVER_STATE) {
			//	g.drawImage(gameOverButtonImage, 200, 30, null);
			//	g.drawImage(replayButtonImage, 283, 50, null);
				
			//}
			break;
		}
		
	}
	private void resetGame() {
		//enemiesManager.reset();
		ninja.dead(false);
		ninja.reset();
	}

	@Override
	public void addKeyEventHandler() {
		// TODO Auto-generated method stub
		System.out.println(this.isKeyPressed);
			
		
		if(Keys.isPressed(Keys.ESCAPE)) {
			gsm.setPaused(true);
		}
		if (Keys.isPressed(Keys.SPACE)) {
			System.out.println("1111111");
			ninja.jump();
			System.out.println("22222");
		}
		
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		int fps = 100;
		long msPerFrame = 1000 * 1000000 / fps;
		long lastTime = 0;
		long elapsed;
		
		int msSleep;
		int nanoSleep;

		long endProcessGame;
		long lag = 0;

		while (true) {
			draw(this);
			gameUpdate();
			endProcessGame = System.nanoTime();
			elapsed = (lastTime + msPerFrame - System.nanoTime());
			msSleep = (int) (elapsed / 1000000);
			nanoSleep = (int) (elapsed % 1000000);
			if (msSleep <= 0) {
				lastTime = System.nanoTime();
				continue;
			}
			try {
				Thread.sleep(msSleep, nanoSleep);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			lastTime = System.nanoTime();
		}
		
	}

	

}
