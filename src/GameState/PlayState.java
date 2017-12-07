package GameState;


import Object.Ninja;
import Object.Clouds;
import Object.EnemiesManager;

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
	private static final int START_GAME_STATE =0;
	private static final int GAME_PLAYING_STATE = 1;
	private static final int GAME_OVER_STATE = 2;
	private int gameState = START_GAME_STATE;
	private static final Font SCORE_TIME_FONT = new Font("Monospace", 30);
	private EnemiesManager enemiesManager;
	private Clouds clouds;
	


	public PlayState(GameStateManager gsm) {
		super(gsm);
		// TODO Auto-generated constructor stub
		ninja = new Ninja();
		land = new Land(GamePanel.WIDTH,ninja);
		clouds = new Clouds(1000,ninja);
		ninja.setSpeedX(10);
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
		if(gameState== this.GAME_PLAYING_STATE) {
			clouds.update();
			land.update();
			ninja.update();
			enemiesManager.update();
		}
		if (enemiesManager.isCollision()) {
			System.out.println("BOOM");
			//mainCharacter.playDeadSound();
			gameState = GAME_OVER_STATE;
			ninja.dead(true);
		}
		
		
	}

	@Override
	public void draw(Canvas game) {
		// TODO Auto-generated method stub
		GraphicsContext gc = game.getGraphicsContext2D();
		gc.setFont(SCORE_TIME_FONT);
		gc.setFill(Color.WHITE);
		gc.drawImage(RenderableHolder.bgplay, 0, 0);
		gc.fillText("Score " + ninja.score, 850, 40);
		gc.drawImage(RenderableHolder.Cloud1, 1000, 50);
		land.draw(game);
		clouds.draw(game);
		ninja.draw(game);
		switch (gameState) {
		case START_GAME_STATE:
			break;
		case GAME_PLAYING_STATE:
			clouds.draw(game);
			land.draw(game);
			enemiesManager.draw(game);
			ninja.draw(game);
			
			break;
		case GAME_OVER_STATE:
			RenderableHolder.gameplay.stop();
			gsm.setState(GameStateManager.GAMEOVER);
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
		switch (gameState) {
		case START_GAME_STATE :
			if (Keys.isPressed(Keys.SPACE)) {
				gameState = GAME_PLAYING_STATE;
				ninja.jump();
				ninja.isStart = true;
			}
			break;
		
		case GAME_PLAYING_STATE:
		if(Keys.isPressed(Keys.ESCAPE)) {
			gsm.setPaused(true);
			RenderableHolder.gameplay.stop();
		}
		if (Keys.isPressed(Keys.SPACE)) {
			ninja.jump();
		}break;
		}
		
	}

}
