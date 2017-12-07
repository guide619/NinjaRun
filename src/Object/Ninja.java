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
	public static final float GRAVITY = 0.98f;
	public static final int MAX_HEALTH = 3;
	public static final int HIT_COOL_DOWN = 20;
	public static final int WARP_COOL_DOWN = 10;
	public static final int WARP_TIME = 25;

	private static final int NORMAL_RUN = 0;
	private static final int JUMPING = 1;
	private static final int DOWN_RUN = 2;
	private static final int DEATH = 3;
	private static final int COOLDOWN_RUN = 4;
	private static final int COOLDOWN_JUMP = 5;
	private static final int WARP = 6;
	
	public boolean isStart;
	public int coolDown;
	public int warpCoolDown;
	public int timewarp;
	private float posY;
	private float posX;
	private float speedX;
	private float normalspeedX;
	private float speedY;
	private Rectangle rectBound;
	private int jumpcount;
	private int health;
	
	private Animation normalRunAnim;
	private Animation cooldownRunAnim;
	private Animation cooldownJumpAnim;
	private Animation warpAnim;
	private Image jumping;

	public int score = 0;
	int count = 0;
	
	private int state = NORMAL_RUN;
	private Image deathImage;
	
	 public Ninja() {
		 
		posX = 250;
		posY = LAND_POSY;
		rectBound = new Rectangle();
		normalRunAnim = new Animation(50);
		normalRunAnim.addFrame(RenderableHolder.spite);
		normalRunAnim.addFrame(RenderableHolder.spite1);
		normalRunAnim.addFrame(RenderableHolder.spite2);
		normalRunAnim.addFrame(RenderableHolder.spite3);
		normalRunAnim.addFrame(RenderableHolder.spite4);
		cooldownRunAnim = new Animation(90);
		cooldownRunAnim.addFrame(RenderableHolder.spite);
		cooldownRunAnim.addFrame(RenderableHolder.spite1_blink);
		cooldownRunAnim.addFrame(RenderableHolder.spite2);
		cooldownRunAnim.addFrame(RenderableHolder.spite3_blink);
		cooldownRunAnim.addFrame(RenderableHolder.spite4);
		cooldownRunAnim.addFrame(RenderableHolder.spite5_blink);
		jumping = RenderableHolder.spite;
		cooldownJumpAnim = new Animation(90);
		cooldownJumpAnim.addFrame(RenderableHolder.spite1);
		cooldownJumpAnim.addFrame(RenderableHolder.spite1_blink);
		warpAnim = new Animation(60);
		warpAnim.addFrame(RenderableHolder.spite1);
		warpAnim.addFrame(RenderableHolder.Smoke1);
		warpAnim.addFrame(RenderableHolder.Smoke2);
		warpAnim.addFrame(RenderableHolder.Smoke3);
		warpAnim.addFrame(RenderableHolder.Smoke4);
		warpAnim.addFrame(RenderableHolder.Smoke4);
		warpAnim.addFrame(RenderableHolder.Smoke4);
		warpAnim.addFrame(RenderableHolder.Smoke4);
		warpAnim.addFrame(RenderableHolder.Smoke4);
		warpAnim.addFrame(RenderableHolder.Smoke1);
		warpAnim.addFrame(RenderableHolder.Smoke2);
		warpAnim.addFrame(RenderableHolder.Smoke3);
		warpAnim.addFrame(RenderableHolder.spite1);
		
		
		
		isStart = false;
		jumpcount = 0;
		health = this.MAX_HEALTH;
		timewarp = this.WARP_TIME;
		coolDown = 0;
		warpCoolDown = 0;
			
	 }
	 public float getSpeedX() {
		 return speedX;
	 }
	 public void setSpeedX(int speedX) {
		 	this.normalspeedX = speedX;
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
			case COOLDOWN_RUN:
				g.drawImage(cooldownRunAnim.getFrame(), (int)posX, (int)posY);
				break;
			case COOLDOWN_JUMP:
				g.drawImage(cooldownJumpAnim.getFrame(), (int)posX, (int)posY);
				break;
			case DOWN_RUN:
				g.drawImage(normalRunAnim.getFrame(), (int) posX, (int) posY);
				break;
			case WARP:
				g.drawImage(warpAnim.getFrame(), (int) posX, (int) posY);
				break;
		}
	 }
	 
	 public void update() {
		updateScore();
		if(state== DOWN_RUN) speedX=(float) (normalspeedX*2);
		else speedX=normalspeedX;
		if(state == WARP) {
			if(timewarp>0) {
				timewarp--;
				warpAnim.updateFrame();
			}
			else {
				setState(JUMPING);
				timewarp = this.WARP_TIME;
				warpAnim.resetFrame();
			}
		}
		else {
			if(posY >= LAND_POSY) {
				posY = LAND_POSY;
				normalRunAnim.updateFrame();
				cooldownRunAnim.updateFrame();
				jumpcount = 0;
				if (coolDown>0) {
					setState(this.COOLDOWN_RUN);
					coolDown--;
				}else setState(this.NORMAL_RUN);
			} else {
				speedY += 1.8*GRAVITY;
				posY += speedY;
				cooldownJumpAnim.updateFrame();
				if (coolDown>0) {
					setState(this.COOLDOWN_JUMP);
					coolDown--;
				}else this.setState(this.JUMPING);}
			warpCoolDown--;
		}
	}
	 public void jump() {
			if(jumpcount <= 1) {
				/*if(jumpSound != null) {
					jumpSound.play();
				}*/
				jumpcount++;
				speedY = -25f;
				posY += speedY;
				state = JUMPING;
			}
		}
	 public void down() {
		 if(state == JUMPING) {
			 if (speedY >= 0) {
				 posY+=speedY+10;
		 
			 }
		 }else if(state == this.NORMAL_RUN)
			 setState(DOWN_RUN);
	 }
	 public void warp() {
		 if (posY < LAND_POSY && warpCoolDown<=0) {
			 this.setState(WARP);
			 warpCoolDown = this.WARP_COOL_DOWN;
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
	 public int getHeight() {
		 return (int)normalRunAnim.getFrame().getHeight();
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
			resetHealth();
		}
		public void playDeadSound() {
			//deadSound.play();
		}
		public void updateScore() {
			if(isStart) {
				count++;
				if(count>=10) {
					score++;count=0;
				}
			}
		}
		
		public void upScore() {
			score += 20;
			if(score % 100 == 0) {
				//scoreUpSound.play();
			}
		}
		public void takeDamage() {
			if(state!=this.WARP) {
				if(coolDown==0) {
					decreaseHealth();
					coolDown = this.HIT_COOL_DOWN;
				}
			}
		}
		
		public void decreaseHealth() {
			health--;
		}
		public void increaseHealth() {
			health++;
		}
		public int getHealth() {
			return health;
		}
		public void resetHealth() {
			health=this.MAX_HEALTH;
		}
		public boolean isOutOfScreen() {
			// TODO Auto-generated method stub
			return false;
		}
		public int getState() {
			return state;
		}
		public void setState(int state) {
			this.state = state;
		}
		
	 

}