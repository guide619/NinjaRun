package Object;

import java.util.List;
import java.util.Random;

import Manager.Animation;
import SharedObject.RenderableHolder;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

public class Shuriken extends Character {
	
	private Animation shuAnim;
	private float posY;
	private float posX;
	private int width;
	private int height;
	private Rectangle rectBound;
	private Image boom;
	private Random rand;
	
	public Shuriken(int posX , int posY) {
		this.posX = posX;
		this.posY = posY+30;
		rand = new Random();
		shuAnim = new Animation(10);
		int i = rand.nextInt(2);
		if(i==0) {
			shuAnim.addFrame(RenderableHolder.Shuriken);
			shuAnim.addFrame(RenderableHolder.Shuriken2);
		}else shuAnim.addFrame(RenderableHolder.Kunai);
		boom = RenderableHolder.Boom;
		this.width =(int) shuAnim.getFrame().getWidth();
		this.height = (int)shuAnim.getFrame().getHeight();
		rectBound = new Rectangle();
		
	}
	
	@Override
	public void update() {
		shuAnim.updateFrame();
		posX+=15;
	}
	public void update(int currentSpeed) {
		// TODO Auto-generated method stub
		shuAnim.updateFrame();
		posX+=20+currentSpeed;
		
	}
	public void drawboom(Canvas game) {
		GraphicsContext gc = game.getGraphicsContext2D();
		gc.drawImage(boom, posX,posY);
	}
	@Override
	public void draw(Canvas game) {
		// TODO Auto-generated method stub
		GraphicsContext gc = game.getGraphicsContext2D();
		gc.drawImage(shuAnim.getFrame(), posX,posY);
		
	}
	@Override
	public Rectangle getBound() {
		// TODO Auto-generated method stub
		rectBound = new Rectangle();
		rectBound.setX(posX);
		rectBound.setY(posY);
		rectBound.setWidth(width*1.25);
		rectBound.setHeight(height*1.25);
		return rectBound;
	}
	@Override
	public boolean isOutOfScreen() {
		// TODO Auto-generated method stub
		if(posX > 1000) {
			return true;
		}
		return false;
	
	}
	

}
