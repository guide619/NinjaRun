package GameState;

import java.awt.Graphics2D;

import Manager.GameStateManager;
import Manager.Keys;
import application.GamePanel;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class GameOverState extends GameState {
	private int score;

	public GameOverState(GameStateManager gsm) {
		// TODO Auto-generated constructor stub
		super(gsm);
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		this.addKeyEventHandler();

	}

	@Override
	public void draw(Canvas g) {
		// TODO Auto-generated method stub
		GraphicsContext gc = g.getGraphicsContext2D();
		gc.setFill(Color.RED);
		gc.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);

	}

	@Override
	public void addKeyEventHandler() {
		// TODO Auto-generated method stub
		if(Keys.anyKeyPress()) {
			gsm.setState(GameStateManager.MENU);
		}
	}

}
