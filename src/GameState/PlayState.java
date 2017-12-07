package GameState;


import Object.Ninja;
import Object.Clouds;
import Object.EnemiesManager;

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
import javafx.scene.text.Font;
import Object.Land;

public class PlayState extends GameState{
	
	private static Land land;
	int dx ;
	Ninja ninja ;
	private static final int GAME_PLAYING_STATE = 1;
	private static final int GAME_OVER_STATE = 2;
	private int gameState = GAME_PLAYING_STATE;
	private static final Font SCORE_TIME_FONT = new Font("Monospace", 30);
	private EnemiesManager enemiesManager;
	int i = 0;
	private Clouds clouds;


	public PlayState(GameStateManager gsm) {
		super(gsm);
		// TODO Auto-generated constructor stub
		land = new Land(GamePanel.WIDTH);
		ninja = new Ninja();
		clouds = new Clouds(1000,ninja);
		ninja.setSpeedX(4);
		enemiesManager = new EnemiesManager(ninja);
		
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		addKeyEventHandler();
		clouds.update();
		land.update();
		ninja.update();
		enemiesManager.update();
		//if (enemiesManager.isCollision()) {
			//mainCharacter.playDeadSound();
		//	gameState = GAME_OVER_STATE;
		//	ninja.dead(true);
		//}
		
		
	}

	@Override
	public void draw(Canvas game) {
		// TODO Auto-generated method stub
		GraphicsContext gc = game.getGraphicsContext2D();
		gc.setFont(SCORE_TIME_FONT);
		gc.setFill(Color.WHITE);
		gc.drawImage(RenderableHolder.bgplay, 0, 0);
		gc.fillText("Scroe " + ninja.score, 850, 40);
		gc.drawImage(RenderableHolder.Cloud1, 1000, 50);
		switch (gameState) {
		case GAME_PLAYING_STATE:
			clouds.draw(game);
			land.draw(game);
			enemiesManager.draw(game);
			ninja.draw(game);
			
			break;
		case GAME_OVER_STATE:
			gsm.setState(GameStateManager.GAMEOVER);
			//clouds.draw(g);
			//land.draw(g);
			//enemiesManager.draw(this);
			//g.setColor(Color.BLACK);
			//if (gameState == GAME_OVER_STATE) {
			//	g.drawImage(gameOverButtonImage, 200, 30, null);
			//	g.drawImage(replayButtonImage, 283, 50, null);
				
			//}
			break;
		}
		
	}
	private void resetGame() {
		enemiesManager.reset();
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
