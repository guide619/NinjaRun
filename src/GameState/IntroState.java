package GameState;



import javax.imageio.ImageIO;

import Manager.GameStateManager;
import Manager.Keys;
import application.GamePanel;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;


 public class IntroState extends GameState {
  
  private Image logo;
  public static final int INTRO_WIDTH = 128;
  public static final int INTRO_HEIGHT = 144;
  
  private int alpha;
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
    //logo = new Image(ClassLoader.getSystemResourceAsStream("GT.png").toString());
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
    alpha = (int) (255 - 255 * (1.0 * ticks / FADE_IN));
    if(alpha < 0) alpha = 0;
   }
   if(ticks > FADE_IN + LENGTH) {
    alpha = (int) (255 * (1.0 * ticks - FADE_IN - LENGTH) / FADE_OUT);
    if(alpha > 255) alpha = 255;
   }
   if(ticks > FADE_IN + LENGTH + FADE_OUT) {
    gsm.setState(GameStateManager.MENU);
   }
  }
  
  public void draw() {
	  GraphicsContext g = this.getGraphicsContext2D();
   g.setFill(Color.WHITE);
   g.fillRect(0, 0, this.INTRO_WIDTH,this.INTRO_HEIGHT);
   g.drawImage(logo,0, 0, this.INTRO_WIDTH, this.INTRO_HEIGHT);
   g.setFill((new Color(0, 0, 0, alpha)));
   g.fillRect(0, 0, this.INTRO_WIDTH, this.INTRO_HEIGHT);
  }
		
		public void addKeyEventHandler() {
			if(Keys.isPressed(Keys.ENTER)) {
				gsm.setState(GameStateManager.MENU);
			}
		}
		
}
