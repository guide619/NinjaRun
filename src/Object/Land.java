package Object;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import SharedObject.RenderableHolder;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Land {
	public static final int LAND_POSY = 0;
	final int speed = 100;
	
	private static List<ImageLand> listLand;
	private Image land1;
	private Image land2;
	private Image land3;
	private Image land4;
	private Image land5;
	private Image land6;
	private Image land7;
	private Image land8;
	private Image land9;
	private Image land0;
	
	public Land(int width) {
		land1 = RenderableHolder.land1;
		land2 = RenderableHolder.land2;
		land3 = RenderableHolder.land3;
		land4 = RenderableHolder.land4;
		land5 = RenderableHolder.land5;
		land6 = RenderableHolder.land6;
		land7 = RenderableHolder.land7;
		land8 = RenderableHolder.land8;
		land9 = RenderableHolder.land9;
		land0 = RenderableHolder.land0;
		int numberOfImageLand = (int) (width / land1.getWidth() + 2);
		listLand = new ArrayList<ImageLand>();
		for(int i = 0; i < numberOfImageLand; i++) {
			ImageLand imageLand = new ImageLand();
			imageLand.posX = (float) (i * land1.getWidth());
			setImageLand(imageLand);
			listLand.add(imageLand);
		}
	}
	
	public void update(){
		Iterator<ImageLand> itr = listLand.iterator();
		ImageLand firstElement = itr.next();
		firstElement.posX -= this.speed;
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
	
	public static void draw(Canvas game) {
		GraphicsContext gc = game.getGraphicsContext2D();
		//printList();
		for(ImageLand imgLand : listLand) {
			gc.drawImage(imgLand.image, (int) imgLand.posX, LAND_POSY);
			//System.out.println(imgLand.type);
		}	
	}
	
	private void setImageLand(ImageLand imgLand) {
		int typeLand = getTypeOfLand();
		//System.out.println(typeLand);
		switch(typeLand) {
		case 1:
			imgLand.image = land1;
			imgLand.type = 1;
			break;
		case 2:
			imgLand.image = land2;
			imgLand.type = 2;
			break;
		case 3 :
			imgLand.image = land3;
			imgLand.type = 3;
			break;
		case 4:
			imgLand.image = land4;
			imgLand.type = 4;
			break;
		case 5:
			imgLand.image = land5;
			imgLand.type = 5;
			break;
		case 6:
			imgLand.image = land6;
			imgLand.type = 6;
			break;
		case 7:
			imgLand.image = land7;
			imgLand.type = 7;
			break;
		case 8:
			imgLand.image = land8;
			imgLand.type = 8;
			break;
		case 9:
			imgLand.image = land9;
			imgLand.type = 9;
			break;
		case 0:
			imgLand.image = land0;
			imgLand.type = 0;
			break;
		}
	}
	private int getTypeOfLand() {
		Random rand = new Random();
		int type = rand.nextInt(10);
		return type;
	}
	private static void printList() {
		System.out.print("[");
		for (ImageLand l : listLand) {
			System.out.print(l.type+", ");
		}System.out.println("]");
	}
	
	private class ImageLand {
		float posX;
		Image image;
		int type;
	}
}
