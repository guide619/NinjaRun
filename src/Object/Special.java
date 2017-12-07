package Object;

import SharedObject.RenderableHolder;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

public class Special extends Character  {
	
	private static final double Y_LAND = 220;
	private int posX =1000;
	private int width;
	private Image image;
	private int height;
	private Ninja ninja;
	private Rectangle rectBound;

	public Special (Ninja ninja , int posX, int width , int height , Image image ) {
		this.ninja = ninja;
		this.posX = posX;
		this.width = width;
		this.height = height;
		this.image = image;
		this.ninja = ninja;
		rectBound = new Rectangle();
	}

	public void update() {
		// TODO Auto-generated method stub
		posX -= ninja.getSpeedX()*2;
		isOutOfScreen();
	}

	public void draw(Canvas game) {
		// TODO Auto-generated method stub
		GraphicsContext gc = game.getGraphicsContext2D();
		gc.drawImage(image, posX, Y_LAND - image.getHeight());
		
	}

	public Rectangle getBound() {
		// TODO Auto-generated method stub
		rectBound = new Rectangle();
		rectBound.setX(posX+10);
		rectBound.setY(Y_LAND - image.getHeight() +5);
		rectBound.setWidth(width);
		rectBound.setHeight(height);
		return rectBound;
	}

	public boolean isOutOfScreen() {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				if(posX < -image.getWidth()) {
					reset();
					return true;
				}
				return false;
	}
	public void reset() {
		posX = 5000;
		
	}

}
