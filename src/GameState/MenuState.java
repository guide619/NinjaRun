package GameState;



import Manager.GameStateManager;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import Manager.Keys;
import java.io.*;

public class MenuState extends GameState {
	boolean isKeyPressed;
	static Image bg ;
	private int currentOption = 0;
	private String[] options = {
			"START",
			"QUIT"
		};
	
	private static final Font TEXT_FONT = new Font("Fipps", 80);
	public MenuState(GameStateManager gsm) {
		super(gsm);
	}

	public void init() {
		String image_path = "file:Resources/Menu/BG.jpg";
		bg = new Image(image_path);
		
	}

	public void update() {
		addKeyEventHandler();
	}

	public void draw(Canvas g) {
		//Draw Background and Options
		GraphicsContext gc = g.getGraphicsContext2D();
		gc.drawImage(bg, 0, 0);
		gc.setFill(Color.BLACK);
		gc.setFont(TEXT_FONT);
		gc.fillText(options[0], 300, 125);
		gc.fillText(options[1], 300, 225);
		
		//Draw Pointer in front of options
		if(currentOption == 0) gc.fillText("-",250,125);
		else if(currentOption == 1) gc.fillText("-", 250,225);
		
	}
	public void addKeyEventHandler() {
		if(Keys.isPressed(Keys.DOWN) && currentOption < options.length - 1) {
			//JukeBox.play("menuoption");
			currentOption++;
		}
		if(Keys.isPressed(Keys.UP) && currentOption > 0) {
			//JukeBox.play("menuoption");
			currentOption--;
		}
		if(Keys.isPressed(Keys.ENTER)) {
			//JukeBox.play("collect");
			selectOption();
		}
		
	}

	protected void selectOption() {
		// TODO Auto-generated method stub
		if(currentOption == 0) {
			gsm.setState(GameStateManager.PLAY);
		}
		if(currentOption ==1) {
			Platform.exit();
		}
		
	}

	
}
