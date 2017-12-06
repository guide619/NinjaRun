package GameState;

import Manager.GameStateManager;
import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import Manager.Keys;
import application.GamePanel;

public class PauseState extends GameState {
	private static final Font TEXT_FONT = new Font("Fipps", 80);
	
	private int currentOption = 0;
	private String[] options = {"RESTART","MENU"};

	public PauseState(GameStateManager gsm) {
		// TODO Auto-generated constructor stub
		super(gsm);
	}

	public void draw(Canvas g) {
		// TODO Auto-generated method stub
		GraphicsContext gc = g.getGraphicsContext2D();
		gc.setFill(Color.GREEN);
		gc.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
		gc.setFill(Color.BLACK);
		gc.setFont(TEXT_FONT);
		gc.fillText("RESTART", 300, 125);
		gc.fillText("MENU", 300, 225);
		
		if(currentOption == 0) gc.fillText("-",250,125);
		else if(currentOption == 1) gc.fillText("-", 250,225);
		
	}

	public void update() {
		// TODO Auto-generated method stub
		this.addKeyEventHandler();
		
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void addKeyEventHandler() {
		if(Keys.isPressed(Keys.DOWN) ) {
			//JukeBox.play("menuoption");
			currentOption++;
			currentOption= currentOption%options.length;
		}
		if(Keys.isPressed(Keys.UP)) {
			//JukeBox.play("menuoption");
			currentOption--;
			currentOption= (-1)*(currentOption%options.length);
		}
		if(Keys.isPressed(Keys.ENTER)) {
			//JukeBox.play("collect");
			//System.out.println(currentOption);
			selectOption();
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
