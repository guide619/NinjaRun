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
			cloud2 = RenderableHolder.Cloud2;
			listCloud = new ArrayList<ImageCloud>();
			ImageCloud imageCloud = new ImageCloud();
			imageCloud.posX = 0;
			imageCloud.posY = 100;
			listCloud.add(imageCloud);
			imageCloud = new ImageCloud();
			imageCloud.posX = 50;
			imageCloud.posY = 120;
			listCloud.add(imageCloud);
			imageCloud = new ImageCloud();
			imageCloud.posX = 125;
			imageCloud.posY = 150;
			listCloud.add(imageCloud);
			imageCloud = new ImageCloud();
			imageCloud.posX = 175;
			imageCloud.posY = 110;
			listCloud.add(imageCloud);
			imageCloud = new ImageCloud();
			imageCloud.posX = 200;
			imageCloud.posY = 100;
			listCloud.add(imageCloud);
			imageCloud = new ImageCloud();
			imageCloud.posX = 225;
			imageCloud.posY = 50;
			listCloud.add(imageCloud);
			imageCloud = new ImageCloud();
			imageCloud.posX = 275;
			imageCloud.posY = 110;
			listCloud.add(imageCloud);
			
			imageCloud = new ImageCloud();
			imageCloud.posX = 500;
			imageCloud.posY = 50;
			listCloud.add(imageCloud);
			imageCloud = new ImageCloud();
			imageCloud.posX = 530;
			imageCloud.posY = 125;
			listCloud.add(imageCloud);
			imageCloud = new ImageCloud();
			imageCloud.posX = 570;
			imageCloud.posY = 50;
			listCloud.add(imageCloud);
			
			imageCloud = new ImageCloud();
			imageCloud.posX = 750;
			imageCloud.posY = 100;
			listCloud.add(imageCloud);
			
			imageCloud = new ImageCloud();
			imageCloud.posX = 1000;
			imageCloud.posY = 125;
			listCloud.add(imageCloud);
			
			
			
		}
		
		public void update(){
			Iterator<ImageCloud> itr = listCloud.iterator();
			ImageCloud firstElement = itr.next();
			firstElement.posX -= ninja.getSpeedX()/8;
			while(itr.hasNext()) {
				ImageCloud element = itr.next();
				element.posX -= ninja.getSpeedX()/8;
			}

			if(firstElement.posX < -cloud2.getWidth()) {
				listCloud.remove(firstElement);
				firstElement.posX = 1000;
				listCloud.add(firstElement);
			}
		
		}

		public void draw(Canvas game) {
			GraphicsContext g = game.getGraphicsContext2D();
			for(ImageCloud imgLand : listCloud) {

				//System.out.println("11111111");
				g.drawImage(cloud2, (int) imgLand.posX, imgLand.posY);
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


