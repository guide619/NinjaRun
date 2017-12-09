package Object;

import java.util.Random;

import SharedObject.RenderableHolder;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

public class Heal extends Character  {
	
	private int posy = 200 ;
	private int posX =500;
	private int width;
	private Image image;
	private int height;
	private Ninja ninja;
	private Rectangle rectBound;

	public Heal (Ninja ninja , int posX, int width , int height , Image image ) {
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
		gc.drawImage(image, posX, posy);
		
	}

	public Rectangle getBound() {
		// TODO Auto-generated method stub
		rectBound = new Rectangle();
		rectBound.setX(posX);
		rectBound.setY(posy);
		rectBound.setWidth(width*1.2);
		rectBound.setHeight(height*1.2);
		return rectBound;
	}

	public boolean isOutOfScreen() {
			if(posX < -image.getWidth()) {
				reset();
				return true;
			}
			return false;
	}
	public void reset() {
		Random rand = new Random();
		int x = 5000+1000*rand.nextInt(5);
		int y =(int) (200 - rand.nextInt(150));
		posX = x;
		posy = y;
		
	}

}
