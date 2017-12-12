package Object;

import java.util.Random;

import Manager.Animation;
import SharedObject.RenderableHolder;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

public class FlyingObject extends Character {
	
	public static final int Y_LAND = 325;
	
	private Random rand;
	private double posX;
	private int posY;
	private int width;
	private int height;
	
	private Animation badShuriken;
	private Ninja ninja;
	
	private Rectangle rectBound;

	
	public FlyingObject (Ninja ninja , int posX) {
		rand = new Random();
		this.posX = posX;
		this.ninja = ninja;
		this.badShuriken = RenderableHolder.badShuAnim;
		this.width = (int)badShuriken.getFrame().getWidth()-10;
		this.height = (int)badShuriken.getFrame().getHeight()-30;
		rectBound = new Rectangle();
		this.posY = rand.nextInt(15)*10+ninja.getHeight();

	}
	@Override
	public void update() {
		// TODO Auto-generated method stub
		posX -= ninja.getSpeedX()*2.5;
		badShuriken.updateFrame();
		width = (int)badShuriken.getFrame().getWidth()-10;
		height = (int)badShuriken.getFrame().getHeight()-30;
		
	}

	@Override
	public void draw(Canvas game) {
		// TODO Auto-generated method stub
		GraphicsContext gc = game.getGraphicsContext2D();
		gc.drawImage(badShuriken.getFrame(), posX, Y_LAND - (height+posY));
		
	}

	@Override
	public Rectangle getBound() {
		// TODO Auto-generated method stub
		rectBound.setX(posX+10);
		rectBound.setY(Y_LAND - (height+posY));
		rectBound.setWidth(width);
		rectBound.setHeight(height);
		return rectBound;
	}

	@Override
	public boolean isOutOfScreen() {
		// TODO Auto-generated method stub
		if(posX < -badShuriken.getFrame().getWidth()) {
			return true;
		}
		return false;
	}
	@Override
	public int getPosX() {
		// TODO Auto-generated method stub
		return (int)posX;
	}
	@Override
	public int getPosY() {
		// TODO Auto-generated method stub
		return (int)posY;
	}
}
