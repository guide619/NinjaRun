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
				resetGame();
			}
			break;
		}
		
		
	}

	@Override
	public void draw(Canvas game) {
		// TODO Auto-generated method stub
		GraphicsContext gc = game.getGraphicsContext2D();
		gc.setFont(SCORE_TIME_FONT);
		gc.setFill(Color.WHITE);
		gc.drawImage(RenderableHolder.bg, 0, 0);
		switch (gameState) {
		case START_GAME_STATE :
			gc.drawImage(RenderableHolder.bg, 0, 0);
			clouds.draw(game);
			bgi.draw(game);
			land.draw(game);
			ninja.draw(game);
			break;
		
		case GAME_PLAYING_STATE:
			gc.drawImage(RenderableHolder.bgplay, 0, 0);
			clouds.draw(game);
			bgi.draw(game);
			land.draw(game);
			gc.fillText(""+ ninja.getHealth(),920 , 80);
			gc.fillText(""+ ninja.score, 900, 40);
			//gc.fillText("Chakra", 30, 40);
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
		case GAME_OVER_STATE:
			if (Keys.isPressed(Keys.ENTER)) {
				gsm.setState(GameStateManager.GAMEOVER,ninja.score);
			}
			break;
		}
		
	}

}
