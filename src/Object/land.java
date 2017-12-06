package Object;

import java.util.List;

import SharedObject.RenderableHolder;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class land {
	private List<Image> listLand;
	final int landpos = 100;
	
	public land() {
	}
	public void draw(Canvas game) {
		GraphicsContext gc = game.getGraphicsContext2D();
		gc.drawImage(RenderableHolder.bg1, 0, 0, 1000, 350);
		
		
	}
}
