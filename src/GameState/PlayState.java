package GameState;


import Object.ninja;
import Manager.GameStateManager;
import Manager.Keys;
import SharedObject.RenderableHolder;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class PlayState extends GameState {
	int dx ;
	ninja ninja ;
	public PlayState(GameStateManager gsm) {
		super(gsm);
		// TODO Auto-generated constructor stub
		ninja = new ninja();
		
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
		
	}

	@Override
	public void addKeyEventHandler() {
		// TODO Auto-generated method stub
		if(Keys.isPressed(Keys.ESCAPE)) {
			gsm.setPaused(true);
		}
	
		if(Keys.isDown(Keys.SPACE) ) {
			//JukeBox.play("menuoption");
			ninja.jump();;
		}
	
		
	}

	

}
