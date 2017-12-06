package Object;


import java.awt.Rectangle;


import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Enemy {
	public static final int Y_LAND = 220;
	
	private int posX;
	private int width;
	private int height;
	
	private Image image;
	private Ninja ninja;
	
	private Rectangle rectBound;

	public Enemy(Ninja ninja, int posX, double width, double height, Image enemy) {
		// TODO Auto-generated constructor stub
		this.posX = posX;
		this.width = (int) width;
		this.height = (int) height;
		this.image = image;
		this.ninja=ninja;
		rectBound = new Rectangle();
	}

	public void update() {
		// TODO Auto-generated method stub
		posX -=ninja.getSpeedX();
		
	}

	public boolean isOutOfScreen() {
		// TODO Auto-generated method stub
		if(posX < 0) {
			return true;
		}
		return false;
	}

	public Rectangle getBound() {
		// TODO Auto-generated method stub
		rectBound = new Rectangle();
		rectBound.x = (int) ( posX + (image.getWidth() - width)/2);
		rectBound.y = (int) (Y_LAND - image.getHeight() + (image.getHeight() - height)/2);
		rectBound.width = width;
		rectBound.height = height;
		return rectBound;
	}

	public void draw(Canvas game) {
		// TODO Auto-generated method stub
		GraphicsContext g = game.getGraphicsContext2D();
		g.drawImage(image, posX, Y_LAND );
		
	}


}
