package GameState;



import javax.imageio.ImageIO;

import Manager.GameStateManager;
import Manager.Keys;
import application.GamePanel;
import application.Main;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


	public class IntroState extends GameState {
  
	private Image logo;
  
	private double alpha;
	private int ticks;
  
	private final int FADE_IN = 60;
	private final int LENGTH = 60;
	private final int FADE_OUT = 60;
  
	public IntroState(GameStateManager gsm) {
		super(gsm);
	}
  
	public void init() {
		ticks = 0;
		try {
			String image_path = "file:Resources/Logo/GT.png";
			logo = new Image(image_path);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
  
	public void update() {
		ticks++;
		addKeyEventHandler();
		if(ticks < FADE_IN) {
			alpha = (double) (1 -  (1.0 * ticks / FADE_IN));
			if(alpha < 0) alpha = 0;
		}
		if(ticks > FADE_IN + LENGTH) {
			alpha = (double) (1.0 * ticks - FADE_IN - LENGTH) / FADE_OUT;
			if(alpha > 1) alpha = 1;
		}
		if(ticks > FADE_IN + LENGTH + FADE_OUT) {
			gsm.setState(GameStateManager.MENU);
		}
	}
  
	public void draw(Canvas game) {
		GraphicsContext g = game.getGraphicsContext2D();
		g.setFill(Color.WHITE);
		g.fillRect(0, 0, GamePanel.WIDTH,GamePanel.HEIGHT);
		g.drawImage(logo,0,0);
		g.setFill((new Color(0, 0, 0, alpha)));
		g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
		
	}
		
		public void addKeyEventHandler() {
			if(Keys.isPressed(Keys.ENTER)) {
				gsm.setState(GameStateManager.MENU);
			}
		}
		
}
