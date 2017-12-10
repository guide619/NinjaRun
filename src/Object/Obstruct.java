package Object;

import SharedObject.RenderableHolder;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

public class Obstruct extends Character {
	
	public static final int Y_LAND = 300;
	
	private int posX;
	
	private Image image;
	private Ninja ninja;
	
	private Rectangle rectBound;
	
	public Obstruct(Ninja ninja , int posX){
		this.posX = posX;
		image = RenderableHolder.Spike;
		this.ninja = ninja;
		rectBound = new Rectangle();
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		posX -= ninja.getSpeedX();
		
	}

	@Override
	public void draw(Canvas game) {
		// TODO Auto-generated method stub
		GraphicsContext gc = game.getGraphicsContext2D();
		gc.drawImage(image, posX, Y_LAND - image.getHeight());
		
	}

	@Override
	public Rectangle getBound() {
		// TODO Auto-generated method stub
		rectBound = new Rectangle();
		rectBound.setX(posX+20);
		rectBound.setY(Y_LAND - image.getHeight() +10);
		rectBound.setWidth(image.getWidth()-10);
		rectBound.setHeight(image.getHeight());
		return rectBound;
	}

	@Override
	public boolean isOutOfScreen() {
		if(posX < -image.getWidth()) {
			return true;
		}
		return false;
	}

	@Override
	public int getPosX() {
		// TODO Auto-generated method stub
		return posX;
	}

	@Override
	public int getPosY() {
		// TODO Auto-generated method stub
		return Y_LAND;
	}

	

}
