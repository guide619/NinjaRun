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
	private String[] options = {"START","QUIT"};
	
	private static final Font TEXT_FONT = new Font("Fipps", 80);
	public MenuState(GameStateManager gsm) {
		super(gsm);
	}

	public void init() {
		
	}

	public void update() {
		addKeyEventHandler();
	}

	public void draw(Canvas g) {
		//Draw Background and Options
		GraphicsContext gc = g.getGraphicsContext2D();
		gc.drawImage(RenderableHolder.bg1, 0, 0);
		gc.drawImage(RenderableHolder.land1, 0, 300);
		Ninja ninja = new Ninja();
		Clouds cloud = new Clouds(1000,ninja);
		BackgroundItem bgi = new BackgroundItem(1000,ninja);
		
		//Draw Pointer in front of options
		if(currentOption == 0) {
			cloud.draw(g);
			bgi.draw(g);
			ninja.draw(g);
			gc.drawImage(RenderableHolder.bg1, 0, 0);
			gc.drawImage(RenderableHolder.land1, 0, 300);
		}
		else if(currentOption == 1) {
			cloud.draw(g);
			bgi.draw(g);
			ninja.draw(g);
			gc.drawImage(RenderableHolder.bg2, 0, 0);
			gc.drawImage(RenderableHolder.land1, 0, 300);
		}
		
	}
	public void addKeyEventHandler() {
		if(Keys.isPressed(Keys.DOWN) ) {
			//JukeBox.play("menuoption");
			RenderableHolder.collect.play();
			currentOption++;
			currentOption= currentOption%options.length;
		}
		if(Keys.isPressed(Keys.UP)) {
			RenderableHolder.collect.play();
			currentOption--;
			currentOption= (-1)*(currentOption%options.length);
		}
		if(Keys.isPressed(Keys.ENTER) || Keys.isPressed(Keys.SPACE)) {
			//JukeBox.play("collect");
			selectOption();
		}
		
	}

	protected void selectOption() {
		// TODO Auto-generated method stub
		if(currentOption == 0) {
			RenderableHolder.Select.play();
			gsm.setState(GameStateManager.PLAY);
			//System.out.println("play");
		}
		if(currentOption ==1) {
			RenderableHolder.Select.play();
			Platform.exit();
		}
		
	}

	
}
