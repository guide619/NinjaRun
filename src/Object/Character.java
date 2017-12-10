package Object;



import javafx.geometry.Bounds;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

public abstract class Character {
	public abstract void update();
	public abstract void draw(Canvas game);
	public abstract Rectangle getBound();
	public abstract boolean isOutOfScreen();
	public abstract int getPosX();
	public abstract int getPosY();
}
