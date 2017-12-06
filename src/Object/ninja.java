package Object;

import java.applet.Applet;
import java.awt.Rectangle;
import java.net.MalformedURLException;
import java.net.URL;

import Manager.Keys;
import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;


public class ninja {

	public static final int LAND_POSY = 80;
	public static final float GRAVITY = 0.4f;

	private static final int NORMAL_RUN = 0;
	private static final int JUMPING = 1;
	private static final int DOWN_RUN = 2;
	private static final int DEATH = 3;
	 int dx;
	private float posY;
	 float posX;
	private float speedX;
	private float speedY;
	private Rectangle rectBound;
	
	private AnimationTimer normalRunAnim;
	private Image jumping;

	public int score = 0;
	
	private int state = NORMAL_RUN;
	 public ninja() {
		 posX = 50;
			posY = LAND_POSY;
			
			
	 }
	 public void move() {
		 posX+=dx;
		 
	 }
	 public void addKeyEventHandler() {
			// TODO Auto-generated method stub
			
			if(Keys.isDown(Keys.LEFT) ) {
				//JukeBox.play("menuoption");
				dx=-1;
			}
			if(Keys.isDown(Keys.RIGHT) ) {
				//JukeBox.play("menuoption");
				dx=1;
				
			}
			if(!Keys.isDown(Keys.LEFT)) {
				dx=0;
			}
			if(!Keys.isDown(Keys.RIGHT)) {
				dx=0;
			}
			
			
		}
}
