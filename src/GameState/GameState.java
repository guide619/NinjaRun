package GameState;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import Manager.GameStateManager;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

public abstract class GameState extends Canvas {
	
	protected GameStateManager gsm;
	
	public GameState(GameStateManager gsm) {
		this.gsm = gsm;
	}
	
	public abstract void init();
	public abstract void update();
	public abstract void draw(Canvas game);
	public abstract void addKeyEventHandler();
	


}
