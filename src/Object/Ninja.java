package Object;

import Manager.Animation;
import Manager.Keys;
import SharedObject.RenderableHolder;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;


public class Ninja {

	public static final int LAND_POSY = 220;
	public static final float GRAVITY = 0.4f;

	private static final int NORMAL_RUN = 0;
	private static final int JUMPING = 1;
	private static final int DOWN_RUN = 2;
	private static final int DEATH = 3;
	
	private float posY;
	private float posX;
	private float speedX;
	private float speedY;
	private Rectangle rectBound;
	
	private Animation normalRunAnim;
	private Image jumping;

	public int score = 0;
	int count = 0;
	
	private int state = NORMAL_RUN;
	private Image deathImage;
	
	 public Ninja() {
		posX = 300;
		posY = LAND_POSY;
		rectBound = new Rectangle();
		normalRunAnim = new Animation(90);
		normalRunAnim.addFrame(RenderableHolder.spite);
		normalRunAnim.addFrame(RenderableHolder.spite1);
		normalRunAnim.addFrame(RenderableHolder.spite2);
		normalRunAnim.addFrame(RenderableHolder.spite3);
		normalRunAnim.addFrame(RenderableHolder.spite4);
		jumping = RenderableHolder.spite;
			
			
	 }
	 public float getSpeedX() {
		 return speedX;
	 }
	 public void setSpeedX(int speedX) {
			this.speedX = speedX;
		}
	 public void  draw(Canvas game) {
		 GraphicsContext g = game.getGraphicsContext2D();
		 switch(state) {
			case NORMAL_RUN:
				g.drawImage(normalRunAnim.getFrame(), (int) posX, (int) posY);
				break;
			case JUMPING:
				g.drawImage(jumping, (int) posX, (int) posY);
				break;
			case DEATH:
				g.drawImage(deathImage, (int) posX, (int) posY);
				break;
		}
	 }
	 public void update() {
		normalRunAnim.updateFrame();
		count++;
		if(count>=10) {score++;count=0;}
		if(posY >= LAND_POSY) {
			posY = LAND_POSY;
			if(state != DOWN_RUN) {
				state = NORMAL_RUN;
			}
		} else {
			speedY += GRAVITY;
			posY += speedY;
		}
	}
	 public void jump() {
			if(posY >= LAND_POSY) {
				/*if(jumpSound != null) {
					jumpSound.play();
				}*/
				speedY = -7.5f;
				posY += speedY;
				state = JUMPING;
			}
		}
	 
	 public Rectangle getBound() {
			rectBound = new Rectangle();
			rectBound.setX((int) posX+5);
			rectBound.setY((int) posY);
			rectBound.setWidth((int) (normalRunAnim.getFrame().getWidth() - 10));
			rectBound.setHeight((int) normalRunAnim.getFrame().getHeight());
			return rectBound;
		}
	 
	 
	 public void dead(boolean isDeath) {
			if(isDeath) {
				state = DEATH;
			} else {
				state = NORMAL_RUN;
			}
	}
		public void reset() {
			posY = LAND_POSY;
		}
		public void playDeadSound() {
			//deadSound.play();
		}
		
		public void upScore() {
			score += 20;
			if(score % 100 == 0) {
				//scoreUpSound.play();
			}
		}
		public boolean isOutOfScreen() {
			// TODO Auto-generated method stub
			return false;
		}
		
	 

}