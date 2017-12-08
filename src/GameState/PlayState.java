package GameState;


import Object.Ninja;
import Object.Special;
import Object.Clouds;
import Object.EnemiesManager;
import Object.BackgroundItem;

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
	private int gameState = 1;
	private static final Font SCORE_TIME_FONT = new Font("Monospace", 30);
	private EnemiesManager enemiesManager;
	private Clouds clouds;

	BackgroundItem bgi;


	public PlayState(GameStateManager gsm) {
		super(gsm);
		// TODO Auto-generated constructor stub
		ninja = new Ninja();
		land = new Land(GamePanel.WIDTH,ninja);
		clouds = new Clouds(1000,ninja);
		ninja.setSpeedX(10);
		bgi = new BackgroundItem(1000,ninja);
		enemiesManager = new EnemiesManager(ninja);
		
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		RenderableHolder.gameplay.play();
		
		
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
			bgi.update();}
		if(enemiesManager.isCollision()) {
			ninja.takeDamage();
		}
		if (enemiesManager.isSpCollision() && ninja.getState() != 6) {
			ninja.increaseHealth();
		}
		if (ninja.getHealth()<=0) {
			//System.out.println("BOOM");
			gameState = GAME_OVER_STATE;
			ninja.dead(true);
			RenderableHolder.gameplay.stop();
			gsm.setState(GameStateManager.GAMEOVER,ninja.score);
			resetGame();
		}
		
		
	}

	@Override
	public void draw(Canvas game) {
		// TODO Auto-generated method stub
		GraphicsContext gc = game.getGraphicsContext2D();
		gc.setFont(SCORE_TIME_FONT);
		gc.setFill(Color.WHITE);
		gc.drawImage(RenderableHolder.bgplay, 0, 0);
		//gc.drawImage(RenderableHolder.Cloud1, 1000, 50);
		if(gameState == this.START_GAME_STATE) {
			land.draw(game);
			clouds.draw(game);
			bgi.draw(game);
			land.draw(game);
			ninja.draw(game);
		}
		if(gameState == this.GAME_PLAYING_STATE) {
			gc.fillText("Health"+ninja.getHealth(),800 , 80);
			gc.fillText("Score " + ninja.score, 800, 40);
			clouds.draw(game);
			bgi.draw(game);
			land.draw(game);
			enemiesManager.draw(game);
			ninja.draw(game);
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
			if (Keys.isPressed(Keys.SPACE)||Keys.isPressed(Keys.UP)) {
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
		if (Keys.isPressed(Keys.SPACE)|| Keys.isPressed(Keys.UP)) {
			ninja.jump();
		}
		if (Keys.isDown(Keys.DOWN)) {
			ninja.down();
			}
		if (Keys.isPressed(Keys.RIGHT)) {
			ninja.warp();
			}
		break;
		}
		
	}

}
