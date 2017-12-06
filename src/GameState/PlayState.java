package GameState;



import Manager.GameStateManager;
import Manager.Keys;
import SharedObject.RenderableHolder;
import application.GamePanel;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import Object.Land;

public class PlayState extends GameState {
	//int i = 0;
	private static Land land;

	public PlayState(GameStateManager gsm) {
		super(gsm);
		land = new Land(GamePanel.WIDTH);
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
		//i++;
		
	}

	@Override
	public void draw(Canvas game) {
		// TODO Auto-generated method stub
		land.draw(game);
		//System.out.println(i);
		
	}

	@Override
	public void addKeyEventHandler() {
		// TODO Auto-generated method stub
		if (Keys.isPressed(Keys.ESCAPE)) gsm.setPaused(true);
		
	}

	

}
