package GameState;



import Manager.GameStateManager;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import Manager.Keys;
import Object.BackgroundItem;
import Object.Clouds;
import Object.Ninja;
import SharedObject.RenderableHolder;

import java.io.*;

public class MenuState extends GameState {
	boolean isKeyPressed;
	static Image bg ;
	private int currentOption = 0;
	

	public MenuState(GameStateManager gsm) {
		super(gsm);
	}

	public void init() {
		//RenderableHolder.gameplay.play();
	}

	public void update() {
		addKeyEventHandler();
	}

	public void draw(Canvas g) {
		//Draw Background and Options
		GraphicsContext gc = g.getGraphicsContext2D();
		gc.drawImage(RenderableHolder.bg1, 0, 0);
		gc.drawImage(RenderableHolder.land1, 0, 300);
		//Ninja ninja = new Ninja();
		//Clouds cloud = new Clouds(1000,ninja);
		//BackgroundItem bgi = new BackgroundItem(1000,ninja);
		
		//Draw Pointer in front of options
		if(currentOption == 0) {
			//cloud.draw(g);
			//bgi.draw(g);
		//	ninja.draw(g);
			gc.drawImage(RenderableHolder.bg1, 0, 0);
			gc.drawImage(RenderableHolder.land1, 0, 300);
		}
		else if(currentOption == 1) {
		//	cloud.draw(g);
		//	bgi.draw(g);
		//	ninja.draw(g);
			gc.drawImage(RenderableHolder.bg2, 0, 0);
			gc.drawImage(RenderableHolder.land1, 0, 300);
		}
		
	}
	public void addKeyEventHandler() {
		if(Keys.isPressed(Keys.DOWN) ) {
			RenderableHolder.collect.play();
			currentOption++;
			currentOption= currentOption%2;
		}
		if(Keys.isPressed(Keys.UP)) {
			RenderableHolder.collect.play();
			currentOption--;
			currentOption= (-1)*(currentOption%2);
		}
		if(Keys.isPressed(Keys.ENTER) || Keys.isPressed(Keys.SPACE)) {
			selectOption();
			RenderableHolder.ninjaSound.play();
		}
		
	}

	protected void selectOption() {
		// TODO Auto-generated method stub
		if(currentOption == 0) {
			gsm.setState(GameStateManager.PLAY);

		}
		if(currentOption ==1) {
			RenderableHolder.Select.play();
			Platform.exit();
		}
		
	}

	
}
