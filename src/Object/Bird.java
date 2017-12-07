package Object;

import java.util.Random;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

public class Bird extends Character {
	
	public static final int Y_LAND = 325;
	
	private Random rand;
	private int posX;
	private int width;
	private int height;
	private int posY;
	
	private Image image;
	private Ninja ninja;
	
	private Rectangle rectBound;

	
	public Bird (Ninja ninja , int posX, int width , int height , Image image) {
		rand = new Random();
		this.posX = posX;
		this.width = width;
		this.height = height;
		this.image = image;
		this.ninja = ninja;
		rectBound = new Rectangle();
		this.posY = rand.nextInt(15)*10+ninja.getHeight();

	}
	@Override
	public void update() {
		// TODO Auto-generated method stub
		posX -= ninja.getSpeedX()+5;
		
	}

	@Override
	public void draw(Canvas game) {
		// TODO Auto-generated method stub
		GraphicsContext gc = game.getGraphicsContext2D();
		gc.drawImage(image, posX, Y_LAND - (image.getHeight()+posY));
		
	}

	@Override
	public Rectangle getBound() {
		// TODO Auto-generated method stub
		rectBound = new Rectangle();
		rectBound.setX(posX+10);
		rectBound.setY(Y_LAND - (image.getHeight()+posY));
		rectBound.setWidth(width);
		rectBound.setHeight(height-20);
		return rectBound;
	}

	@Override
	public boolean isOutOfScreen() {
		// TODO Auto-generated method stub
		if(posX < -image.getWidth()) {
			return true;
		}
		return false;
	}

}
