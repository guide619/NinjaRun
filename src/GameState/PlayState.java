package GameState;


import Object.Ninja;

import java.awt.event.KeyEvent;

import Manager.GameStateManager;
import Manager.Keys;
import SharedObject.RenderableHolder;
import application.GamePanel;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import Object.Land;

public class PlayState extends GameState{
	
	private static Land land;
	int dx ;
	Ninja ninja ;
	private static final int GAME_PLAYING_STATE = 1;
	private static final int GAME_OVER_STATE = 2;
	private int gameState = GAME_PLAYING_STATE;

	int i = 0;


	public PlayState(GameStateManager gsm) {
		super(gsm);
		// TODO Auto-generated constructor stub
		land = new Land(GamePanel.WIDTH);
		ninja = new Ninja();
		ninja.setSpeedX(4);
		
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		addKeyEventHandler();
		land.update();
		ninja.update();
		
		
	}

	@Override
	public void draw(Canvas game) {
		// TODO Auto-generated method stub
		land.draw(game);
		GraphicsContext gc = game.getGraphicsContext2D();
		gc.setFill(Color.WHITE);
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
		if(Keys.isPressed(Keys.ESCAPE)) {
			gsm.setPaused(true);
		}
		if (Keys.isPressed(Keys.SPACE)) {
			ninja.jump();
		}
		
	}

}
