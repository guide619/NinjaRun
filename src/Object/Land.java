package Object;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


import SharedObject.RenderableHolder;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Land {
	public static final int LAND_POSY = 300;
	
	private static List<ImageLand> listLand;
	private Image land1;

	
	private Ninja ninja;
	
	public Land(int width, Ninja ninja) {
		land1 = RenderableHolder.land1;
		this.ninja = ninja;
		listLand = new ArrayList<ImageLand>();
		for(int i = 0; i < 3; i++) {
			ImageLand imageLand = new ImageLand();
			imageLand.posX = (float) (i * land1.getWidth());
			setImageLand(imageLand);
			listLand.add(imageLand);
		}
	}
	
	public void update(){
		Iterator<ImageLand> itr = listLand.iterator();
		ImageLand firstElement = itr.next();
		firstElement.posX -= ninja.getSpeedX() ;
		float previousPosX = firstElement.posX;
		while(itr.hasNext()) {
			ImageLand element = itr.next();
			element.posX = (float) (previousPosX + land1.getWidth());
			previousPosX = element.posX;
		}
		if(firstElement.posX < -land1.getWidth()) {
			listLand.remove(firstElement);
			firstElement.posX = (float) (previousPosX + land1.getWidth());
			setImageLand(firstElement);
			listLand.add(firstElement);
		}
	}
	
	public void draw(Canvas game) {
		GraphicsContext gc = game.getGraphicsContext2D();
		//printList();
		for(ImageLand imgLand : listLand) {
			gc.drawImage(imgLand.image, (int) imgLand.posX, LAND_POSY);
			//System.out.println(imgLand.type);
		}	
	}
	
	private void setImageLand(ImageLand imgLand) {
			imgLand.image = land1;
	}


	
	private class ImageLand {
		float posX;
		Image image;
	}
}
