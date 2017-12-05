package GameState;


import Manager.GameStateManager;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class MenuState extends GameState {
	boolean isKeyPressed;
	static Image bg ;
	private int currentOption = 0;
	public MenuState(GameStateManager gsm) {
		// TODO Auto-generated constructor stub
		super(gsm);
	}

	public void init() {
		// TODO Auto-generated method stub
		bg = new Image(ClassLoader.getSystemResource("BG").toString());
		
		
	}

	public void update() {
		// TODO Auto-generated method stub
		addKeyEventHandler();
	}

	public void draw(GraphicsContext g) {
		// TODO Auto-generated method stub
		g.drawImage(bg, 0, 0);
		g.fillText("START", 44, 90);
		g.fillText("Quit", 48, 100);
		
	}
	public void addKeyEventHandler() {
		//TODO Fill Code
		this.setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent key) {
				if(key.getCode().equals(KeyCode.DOWN) && currentOption < 1) {
					currentOption ++;
					}
				if(key.getCode().equals(KeyCode.UP) && currentOption > 0) {
					currentOption ++;
				}
				if(key.getCode().equals(KeyCode.ENTER))
					selectOption();
			}
		});
		
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
