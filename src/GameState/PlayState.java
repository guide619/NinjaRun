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
	public int wave;
	public static Ninja ninja ;
	private static final int START_GAME_STATE =0;
	private static final int GAME_PLAYING_STATE = 1;
	private static final int GAME_OVER_STATE = 2;
	public static int gameState;
	private static final Font SCORE_TIME_FONT = new Font("Monospace", 30);
	private ObjectsManager objectsManager;
	private Clouds clouds;
	private int countintro =0;

	BackgroundItem bgi;


	public PlayState(GameStateManager gsm) {
		super(gsm);
		// TODO Auto-generated constructor stub
		gameState = START_GAME_STATE;
		ninja = new Ninja();
		land = new Land(GamePanel.WIDTH,ninja);
		clouds = new Clouds(1000,ninja);
		bgi = new BackgroundItem(1000,ninja);
		objectsManager = new ObjectsManager(ninja);
		
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		//RenderableHolder.gameplay.play();
		ninja.setSpeedX(8);
		
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		addKeyEventHandler();
		countintro++;
		wave = objectsManager.wave;
		switch(gameState) {
		case START_GAME_STATE :
			land.update();
			ninja.update();
			bgi.update();
			clouds.update();
			break;
		case GAME_PLAYING_STATE :
			clouds.update();
			land.update();
			ninja.update();
			objectsManager.update();
			bgi.update();
			if(objectsManager.isCollision()) ninja.takeDamage();
			//System.out.println(wave);
			objectsManager.isSpCollision() ;
			if (ninja.getHealth()<=0) {
				//System.out.println("BOOM");
				gameState = GAME_OVER_STATE;
				ninja.dead(true);
				RenderableHolder.gameplay.stop();
			}
			break;
		case GAME_OVER_STATE:
			ninja.update();
			break;
		}
		
		
	}

	@Override
	public void draw(Canvas game) {
		// TODO Auto-generated method stub
		GraphicsContext gc = game.getGraphicsContext2D();
		gc.setFont(SCORE_TIME_FONT);
		gc.setFill(Color.WHITE);
		gc.drawImage(RenderableHolder.Background, 0, 0);
		switch (gameState) {
		case START_GAME_STATE :
			clouds.draw(game);
			bgi.draw(game);
			land.draw(game);
			ninja.draw(game);
			gc.drawImage(RenderableHolder.Instruction, 0, 0);
			if(countintro%10<6) gc.drawImage(RenderableHolder.Press, 0, 0);
			break;
		
		case GAME_PLAYING_STATE:
			clouds.draw(game);
			bgi.draw(game);
			land.draw(game);
			if (ninja.isUltimateReady()) gc.drawImage(RenderableHolder.UltimateReady, 350, -50);
			else gc.drawImage(RenderableHolder.Ultimate, 350, -50);
			gc.drawImage(RenderableHolder.ScoreAndHealth, 0, 0);
			gc.fillText(""+ ninja.score, 880, 42);
			gc.setStroke(Color.WHITE);
			gc.strokeRect(127, 64, 200, 17);
			gc.setFill(Color.DARKCYAN);
			gc.fillRect(127, 64, 200-ninja.warpCoolDown*20, 17);
			gc.setFill(Color.RED);
			gc.strokeRect(127, 22, 200, 17);
			gc.fillRect(127,22,40*ninja.getHealth(),17);
			objectsManager.draw(game);
			ninja.draw(game);
			break;
		case GAME_OVER_STATE:
			gc.drawImage(RenderableHolder.deathBackground, 0, 0);
			land.draw(game);
			gc.drawImage(RenderableHolder.ScoreAndHealth, 0, 0);
			gc.fillText(""+ ninja.score, 880, 42);
			gc.setStroke(Color.WHITE);
			gc.strokeRect(127, 64, 200, 17);
			gc.setFill(Color.DARKCYAN);
			gc.fillRect(127, 64, 200-ninja.warpCoolDown*20, 17);
			gc.setFill(Color.RED);
			gc.strokeRect(127, 22, 200, 17);
			gc.fillRect(127,22,0,17);
			objectsManager.draw(game);
			ninja.draw(game);
			break;
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
			if (Keys.anyKeyPress()) {
				gameState = GAME_PLAYING_STATE;
				ninja.isStart = true;
			}
			break;
		
		case GAME_PLAYING_STATE:
		if(Keys.isPressed(Keys.ESCAPE)) {
			gsm.setPaused(true);
			RenderableHolder.gameplay.stop();
		}
		if (Keys.isPressed(Keys.UP)) {
			if(ninja.getState()!=ninja.ULTIMATE) ninja.jump();
		}
		if (Keys.isDown(Keys.DOWN)) {
			if(ninja.getState()!=ninja.ULTIMATE) ninja.down();
		}
		if (Keys.isPressed(Keys.RIGHT)) {
			if(ninja.getState()!=ninja.ULTIMATE) ninja.warp();
			}
		if(Keys.isPressed(Keys.SPACE))
			if(ninja.getState()!=ninja.ULTIMATE) objectsManager.createShuriken();
		if(Keys.isPressed(Keys.LEFT)) {
			ninja.ultimate();
		}
		break;
		case GAME_OVER_STATE:
			if (Keys.isPressed(Keys.ENTER)) {
				resetGame();
				gsm.setState(GameStateManager.GAMEOVER , ninja.score);
			}
			break;
		}
		
	}

}