package Object;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import SharedObject.RenderableHolder;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class backgroundItem {
	private List<Imageitem> listitem;
	private Image item1;
	
	private Ninja ninja;
	private Image item2;
	private Image item3;
	private Image item4;
	//int type = 0;
	
	public backgroundItem(int width, Ninja ninja) {
		this.ninja = ninja;
		item1 = RenderableHolder.item1;
		item2 = RenderableHolder.item2;
		item3 = RenderableHolder.item3;
		item4 = RenderableHolder.item4;
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
		firstElement.posX -= 2*ninja.getSpeedX();
		while(itr.hasNext()) {
			Imageitem element = itr.next();
			element.posX -= 2*ninja.getSpeedX();
		}
		if(firstElement.posX < -firstElement.image.getWidth()) {
			listitem.remove(firstElement);
			firstElement.posX = 1000;
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
