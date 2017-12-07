package Object;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

public class Obstruct extends Character {
	
	public static final int Y_LAND = 325;
	
	private int posX;
	private int width;
	private int height;
	
	private Image image;
	private Ninja ninja;
	
	private Rectangle rectBound;
	
	public Obstruct(Ninja ninja , int posX, int width , int height , Image image){
		this.posX = posX;
		this.width = width;
		this.height = height;
		this.image = image;
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
		rectBound.setWidth(width-10);
		rectBound.setHeight(height);
		return rectBound;
	}

	@Override
	public boolean isOutOfScreen() {
		if(posX < -image.getWidth()) {
			return true;
		}
		return false;
	}

}
