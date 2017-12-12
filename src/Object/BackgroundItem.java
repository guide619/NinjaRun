package Object;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


import SharedObject.RenderableHolder;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class BackgroundItem {
	private List<Imageitem> listitem;
	private Image item1;
	
	private Ninja ninja;
	private Image item2;

	
	
	public BackgroundItem(int width, Ninja ninja) {
		this.ninja = ninja;
		item1 = RenderableHolder.item1;
		item2 = RenderableHolder.item2;
		listitem = new ArrayList<Imageitem>();
		Imageitem imageCloud = new Imageitem();
		imageCloud.posX = 100;
		imageCloud.image = item1;
		listitem.add(imageCloud);
		
		imageCloud = new Imageitem();
		imageCloud.posX = 900;
		imageCloud.image = item2;
		listitem.add(imageCloud);
	}
		
	
	public  void update(){
		Iterator<Imageitem> itr = listitem.iterator();
		Imageitem firstElement = itr.next();
		firstElement.posX -= ninja.getSpeedX()/4;
		while(itr.hasNext()) {
			Imageitem element = itr.next();
			element.posX -= ninja.getSpeedX()/4;
		}
		if(firstElement.posX < -firstElement.image.getWidth()) {
			listitem.remove(firstElement);
			firstElement.posX = 1000;//+rand.nextInt(3000);
			listitem.add(firstElement);
		}
	}
	
	public void draw(Canvas game) {
		GraphicsContext g = game.getGraphicsContext2D();
		for(Imageitem imgLand : listitem) {
			g.drawImage(imgLand.image, (int) imgLand.posX, imgLand.posY);
		}
	}
	
	private class Imageitem {
		Image image;
		float posX=1000;
		int posY = 155;
	}


}
