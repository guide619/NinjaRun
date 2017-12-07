package Object;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import SharedObject.RenderableHolder;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;



public class Clouds {

		private List<ImageCloud> listCloud;
		private Image cloud1;
		
		private Ninja ninja;
		private Image cloud2;
		private Image cloud3;
		private Image cloud4;
		
		public Clouds(int width, Ninja ninja) {
			this.ninja = ninja;
			cloud1 = RenderableHolder.Cloud1;
			cloud2 = RenderableHolder.Cloud2;
			cloud3 = RenderableHolder.Cloud3;
			cloud4 = RenderableHolder.Cloud4;
			listCloud = new ArrayList<ImageCloud>();
			for(int i =0 ; i < 4 ; i++) {
				ImageCloud imageLand = new ImageCloud();
				setImageLand(imageLand);
				listCloud.add(imageLand);
			
			}
			
		}
		
		public void update(){
			Iterator<ImageCloud> itr = listCloud.iterator();
			ImageCloud firstElement = itr.next();
			firstElement.posX -= ninja.getSpeedX();
			while(itr.hasNext()) {
				ImageCloud element = itr.next();
				element.posX -= ninja.getSpeedX();
			}
			if(firstElement.posX < -cloud1.getWidth()) {
				listCloud.remove(firstElement);
				firstElement.posX = 1000;
				listCloud.add(firstElement);
			}
		}
		private void setImageLand(ImageCloud imgLand) {
			int typeLand = getTypeOfLand();
			if(typeLand == 1) {
				imgLand.image = cloud1;
			} else if(typeLand == 2) {
				imgLand.image = cloud2;
			}  else if(typeLand == 3) {
				imgLand.image = cloud3;
			} 
			else {
				imgLand.image = cloud4;
			}
		}
		private int getTypeOfLand() {
			Random rand = new Random();
			int type = rand.nextInt(3);
			if(type == 0) {
				return 1;
			} else if(type == 1) {
				return 2;
			} else if(type == 2) {
				return 3;
			} 
			else {
				return 4;
			}
		}
		
		public void draw(Canvas game) {
			GraphicsContext g = game.getGraphicsContext2D();
			for(ImageCloud imgLand : listCloud) {
				System.out.println("11111111");
				g.drawImage(imgLand.image, (int) imgLand.posX, imgLand.posY);
			}
		}
		
		private class ImageCloud {
			Image image;
			float posX=1000;
			int posY = 100;
		}

}

