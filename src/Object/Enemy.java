package Object;

import Manager.Animation;
import SharedObject.RenderableHolder;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Rectangle;

public class Enemy extends Character {
	
	public static final int Y_LAND = 325;
	
	private int posX;
	private int posY;
	
	private Animation enemyAnim;
	private Ninja ninja;
	
	private Rectangle rectBound;
	
	public Enemy (Ninja ninja , int posX) {
		this.posX = posX;
		this.posY=220;
		this.enemyAnim = RenderableHolder.enemyAnim;
		this.ninja = ninja;
		rectBound = new Rectangle();
	}
	@Override
	public void update() {
		// TODO Auto-generated method stub
		posX -= ninja.getSpeedX()*2;
		enemyAnim.updateFrame();
		
	}

	@Override
	public void draw(Canvas game) {
		// TODO Auto-generated method stub
		GraphicsContext gc = game.getGraphicsContext2D();
		gc.drawImage(enemyAnim.getFrame(), posX, posY);
		
	}

	@Override
	public Rectangle getBound() {
		// TODO Auto-generated method stub
		rectBound = new Rectangle();
		rectBound.setX(posX+5);
		rectBound.setY(posY+5);
		rectBound.setWidth(enemyAnim.getFrame().getWidth()-10);
		rectBound.setHeight(enemyAnim.getFrame().getHeight()-10);
		return rectBound;
	}

	@Override
	public boolean isOutOfScreen() {
		// TODO Auto-generated method stub
		if(posX < -enemyAnim.getFrame().getWidth()) {
			return true;
		}
		return false;
	}
	public int getPosX() {
		return posX;
	}
	public int getPosY() {
		return posY;
	}
	

}
