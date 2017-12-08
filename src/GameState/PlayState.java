package GameState;


import Object.Ninja;
import Object.Heal;
import Object.Clouds;
import Object.BackgroundItem;
import Manager.ObjectsManager;
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
	private int gameState = 0;
	private static final Font SCORE_TIME_FONT = new Font("Monospace", 30);
	private ObjectsManager objectsManager;
	private Clouds clouds;

	BackgroundItem bgi;


	public PlayState(GameStateManager gsm) {
		super(gsm);
		// TODO Auto-generated constructor stub
		ninja = new Ninja();
		land = new Land(GamePanel.WIDTH,ninja);
		clouds = new Clouds(1000,ninja);
		ninja.setSpeedX(8);
		bgi = new BackgroundItem(1000,ninja);
		objectsManager = new ObjectsManager(ninja);
		
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		//RenderableHolder.gameplay.play();
		
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		addKeyEventHandler();
		if(gameState== this.GAME_PLAYING_STATE) {
			clouds.update();
			land.update();
			ninja.update();
			objectsManager.update();
			bgi.update();
		}
		if(objectsManager.isCollision()) {
			ninja.takeDamage();
		}if(objectsManager.getWave()==15) ninja.setSpeedX(10);
		else if (objectsManager.getWave()==25) ninja.setSpeedX(20);
		objectsManager.isSpCollision() ;
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
			//clouds.draw(game);
			//bgi.draw(game);
			gc.fillText(" = HEAL",400 , 140);
			gc.fillText(" = SPEED",550 , 140);
			gc.fillText("SPEACE BAR = ATTACK",350 , 180);
			gc.fillText("RIGHT = INVISIBLE",350 , 220);
			gc.fillText("UP = JUMP",350 , 260);
			gc.fillText("DOWN = SPRINT",350 , 300);
			gc.drawImage(RenderableHolder.Heal, 350, 105);
			gc.drawImage(RenderableHolder.Speed, 520, 105);
			land.draw(game);
			ninja.draw(game);
		}
		if(gameState == this.GAME_PLAYING_STATE) {
			clouds.draw(game);
			bgi.draw(game);
			land.draw(game);
			gc.fillText("Health"+ninja.getHealth(),800 , 80);
			gc.fillText("Score " + ninja.score, 800, 40);
			gc.fillText("Chakra", 30, 40);
			gc.strokeRect(30, 50, 150, 10);
			gc.setFill(Color.DARKCYAN);
			gc.fillRect(30, 50, 150-ninja.warpCoolDown*15, 10);
			gc.setFill(Color.RED);
			gc.setStroke(Color.BLACK);
			gc.strokeRect(800, 90, 100, 10);
			gc.fillRect(800,90,20*ninja.getHealth(),10);
			if (ninja.isUltimateReady()) gc.drawImage(RenderableHolder.UltimateReady, 350, -50);
			else gc.drawImage(RenderableHolder.Ultimate, 350, -50);
			objectsManager.draw(game);
			ninja.draw(game);
	}
	}
	private void resetGame() {
		objectsManager.reset();
		ninja.dead(false);
		ninja.reset();
	}

	@Override
	public void addKeyEventHandler() {
		// TODO Auto-generated method stub
		switch (gameState) {
		case START_GAME_STATE :
			if (Keys.isPressed(Keys.UP)) {
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
		if (Keys.isPressed(Keys.UP)) {
			ninja.jump();
		}
		if (Keys.isDown(Keys.DOWN)) {
			ninja.down();
			}
		if (Keys.isPressed(Keys.RIGHT)) {
			ninja.warp();
			}
		if(Keys.isPressed(Keys.SPACE))
			objectsManager.createShuriken();
		if(Keys.isPressed(Keys.LEFT)) {
			ninja.ultimate();
		}
		break;
		}
		
	}

}
