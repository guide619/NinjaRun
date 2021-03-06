package GameState;

import Manager.GameStateManager;
import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import Manager.Keys;
import SharedObject.RenderableHolder;
import application.GamePanel;

public class PauseState extends GameState {
	private int currentOption ;

	public PauseState(GameStateManager gsm) {
		// TODO Auto-generated constructor stub
		super(gsm);
	}

	public void draw(Canvas g) {
		// TODO Auto-generated method stub
		GraphicsContext gc = g.getGraphicsContext2D();
		gc.drawImage(RenderableHolder.pausebg, 0, 0);
		gc.drawImage(RenderableHolder.pauseInstruct, 0, 0);
		
		if(currentOption == 0) {
			gc.setFill(Color.BLACK);
			//gc.fillRect(300, 50, 400, 200);
			gc.drawImage(RenderableHolder.pausescroll1, 300, 40);

		}
		else if(currentOption == 1) {
			gc.setFill(Color.BLACK);
			//gc.fillRect(300, 50, 400, 200);
			gc.drawImage(RenderableHolder.pausescroll2, 300, 40);
		}	
	}

	public void update() {
		// TODO Auto-generated method stub
		this.addKeyEventHandler();
		
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		currentOption = 0;
		
	}


	@Override
	public void addKeyEventHandler() {
		if(Keys.isPressed(Keys.DOWN) ) {
			//JukeBox.play("menuoption");
			currentOption++;
			currentOption= currentOption%2;
			RenderableHolder.collect.play();
		}
		if(Keys.isPressed(Keys.UP)) {
			//JukeBox.play("menuoption");
			currentOption--;
			currentOption= (-1)*(currentOption%2);
			RenderableHolder.collect.play();
		}
		if(Keys.isPressed(Keys.ENTER)) {
			//JukeBox.play("collect");
			//System.out.println(currentOption);
			selectOption();
		}if(Keys.isPressed(Keys.ESCAPE)) {
			gsm.setPaused(false);
			
		}
		
	}
	protected void selectOption() {
		// TODO Auto-generated method stub
		if(currentOption == 0) {
			gsm.setPaused(false);
			gsm.setState(GameStateManager.PLAY);
		}
		if(currentOption ==1) {
			gsm.setPaused(false);
			gsm.setState(GameStateManager.MENU);
		}
		
	}

}
