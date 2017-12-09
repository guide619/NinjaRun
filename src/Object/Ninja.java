package Object;

import java.util.List;

import Manager.Animation;
import Manager.Keys;
import SharedObject.RenderableHolder;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;


public class Ninja {
	public static final int MAX_SPEED = 25;
	public static final int LAND_POSY = 220;
	public static final float GRAVITY = 0.98f;
	public static final int MAX_HEALTH = 5;
	public static final int HIT_COOL_DOWN = 20;
	public static final int WARP_COOL_DOWN = 10;
	public static final int WARP_TIME = 25;
	public static final int ULTIMATE_TIME = 75;

	public static final int NORMAL_RUN = 0;
	public static final int JUMPING = 1;
	public static final int COOLDOWN_RUN = 2;
	public static final int COOLDOWN_JUMP = 3;
	public static final int DOWN_RUN = 4;
	public static final int DEATH = 5;
	public static final int WARP = 6;
	public static final int ULTIMATE = 7;
	
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
	private int currentstate;
	private int powerobtain;
	private int ultimateTime;
	private int ghost;
	
	private Animation normalRunAnim;
	private Animation cooldownRunAnim;
	private Animation cooldownJumpAnim;
	private Animation warpAnim;
	private Image jumping;

	public int score = 0;
	int count = 0;
	
	private int state = NORMAL_RUN;
	private int warpcount;
	
	 public Ninja() {
		 
		posX = 250;
		posY = LAND_POSY;
		rectBound = new Rectangle();
		normalRunAnim = RenderableHolder.normalRunAnim;
		cooldownRunAnim = RenderableHolder.cooldownRunAnim;
		cooldownJumpAnim = RenderableHolder.cooldownJumpAnim;
		warpAnim = RenderableHolder.warpAnim;
		jumping = RenderableHolder.spite1;
		isStart = false;
		jumpcount = 0;
		health = MAX_HEALTH;
		timewarp = WARP_TIME;
		coolDown = 0;
		warpCoolDown = 0;
		warpcount =0;
		currentstate = NORMAL_RUN;
		ultimateTime = ULTIMATE_TIME;
		ghost = 0;
			
	 }
	 public float getSpeedX() {
		 return speedX;
	 }
	 public void setSpeedX(float speedX) {
		 if(speedX<=25)
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
				if(posY>=LAND_POSY)g.drawImage(RenderableHolder.Ghost, (int) posX+ghost/2+50, (int) posY-(20+ghost));
				g.drawImage(RenderableHolder.Spitedead, posX, posY+20);
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
			case ULTIMATE:
				g.drawImage(RenderableHolder.fire, (int) posX-110, (int)posY-25);
				g.drawImage(normalRunAnim.getFrame(), (int) posX, (int) posY);
				break;
		}
	 }
	 public void update() {
		if(!isStart) {
			normalRunAnim.updateFrame();
			return;
		}else if (state == DEATH) {
			if(posY<LAND_POSY)posY+=15;
			else ghost+=1;
			return;
		}
		updateScore();
		if(warpCoolDown>0)warpCoolDown--;
		if(state!=WARP) {
			if(coolDown>0) {
				coolDown--;
				if(posY >= LAND_POSY) {
					posY = LAND_POSY;
					setState(COOLDOWN_RUN);	
				}else setState(COOLDOWN_JUMP);
			}
		}
		switch(state) {
		case ULTIMATE:
			if(ultimateTime<=0) {
				setState(this.currentstate+2);
				speedX =normalspeedX;
				coolDown = HIT_COOL_DOWN+10;
				ultimateTime=ULTIMATE_TIME;
				powerobtain = 0;
			}else {
				speedX=normalspeedX*50;
				ultimateTime--;
				normalRunAnim.updateFrame();
			}
			break;
		case DOWN_RUN:
			normalRunAnim.updateFrame();
			speedX=(float) (normalspeedX*2);
			if(!Keys.isDown(Keys.DOWN)) {
				setState(NORMAL_RUN);
				speedX =normalspeedX;
			}
			break;
		case WARP:
			if(timewarp>0) {
				timewarp--;
				warpAnim.updateFrame();
			}
			else {
				setState(currentstate);
				timewarp = WARP_TIME;
				warpAnim.resetFrame();
				speedY=0;
			}break;
		case COOLDOWN_RUN:
			cooldownRunAnim.updateFrame();
			warpcount=0;
			jumpcount=0;
			if(coolDown<=0)setState(NORMAL_RUN);
			break;
		case COOLDOWN_JUMP:
			speedY += 1.8*GRAVITY;
			posY += speedY;
			cooldownJumpAnim.updateFrame();
			if(coolDown<=0)setState(JUMPING);
			break;
		case JUMPING:
			speedY += 1.8*GRAVITY;
			posY += speedY;
			if(posY >= LAND_POSY) {
				posY = LAND_POSY;
				setState(NORMAL_RUN);
			}
			break;
		case NORMAL_RUN:
			normalRunAnim.updateFrame();
			warpcount=0;
			jumpcount=0;
			break;
		}	
	}
	 public void jump() {
		 if(state<=3) {
			 if(jumpcount<2) {
				 speedY = -20f;
				 RenderableHolder.jumpSound.play();
				 jumpcount++;
				 posY += speedY;
				 setState(JUMPING);
			 }
		 }
	}
	
	 public void down() {
		 if(state == JUMPING) {
			 if (speedY >= 0) {
				 posY+=speedY+10;
			 }
		 }else if(state == NORMAL_RUN)
			 setState(DOWN_RUN);
	 }
	 public void warp() {
		 if (warpCoolDown<=0 && warpcount<1 && state <= 4) {
			 currentstate = state;
			 speedX = normalspeedX;
			 setState(WARP);
			 warpcount++;
			 warpCoolDown = WARP_COOL_DOWN;
			 RenderableHolder.warpSound.play();
		 }
		 
	 }public void ultimate() {
		 if(isUltimateReady() && coolDown<=0) {
			 currentstate = state;
			 setState(ULTIMATE);
			 RenderableHolder.UltimateSound.play();
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
				setState(DEATH);
				RenderableHolder.DieSound.play();
			}
	}
	 
	public void reset() {
			posY = LAND_POSY;
			resetHealth();
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
	}
	
	public void takeDamage() {
		if(state!=WARP && state!=ULTIMATE) {
			if(coolDown<=0) {
				RenderableHolder.Select.setVolume(1000);
				RenderableHolder.Select.play();
				decreaseHealth();
				coolDown = HIT_COOL_DOWN;
			}
		}
	}
	
	public void decreaseHealth() {
			health--;
	}
	
	public void increaseHealth() {
		if(health<MAX_HEALTH) {
			health++;
			RenderableHolder.Healsound.play();
		}
	}
	public int getHealth() {
			return health;
		}
	public void resetHealth() {
			health=MAX_HEALTH;
		}
	public void powerObtain() {
			if(powerobtain<3) powerobtain++;
		}
	public boolean isUltimateReady() {
			 if(powerobtain>=3) return true;
			 else return false;
		}
	public boolean isOutOfScreen() {
			return false;
		}
	public int getState() {
			return state;
		}
	public void setState(int state) {
			this.state = state;
		}
	public float getPosY() {
			return posY;
		}
	public float getPosX() {
			return posX;
		}
	public void increaseNormalSpeedX(int i) {
		normalspeedX += i;
	}
 

}