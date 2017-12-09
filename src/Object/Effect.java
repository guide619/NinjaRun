package Object;

import Manager.Animation;
import SharedObject.RenderableHolder;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class Effect {
	public static final int DURATION = 10;
	
	public int posX;
	public int posY;
	public float speedX;
	public int count;
	
	private Animation boomAnim;
	
	public Effect(int posX, int posY, float speedX) {
		this.posX = posX;
		this.posY = posY;
		this.speedX = speedX;
		boomAnim = RenderableHolder.boomAnim;
		count = 0;
		System.out.println("CHECK");
	}
	public void update() {
		posX -= speedX;
		boomAnim.updateFrame();
		count++;
	}
	public void draw(Canvas game) {
		if (count<DURATION) {
			GraphicsContext gc = game.getGraphicsContext2D();
			gc.drawImage(boomAnim.getFrame(), posX, posY);
		}
	}
	public boolean isDestroy() {
		if (count>=DURATION) return true;
		return false;
	}
	
}
