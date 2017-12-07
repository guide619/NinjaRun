package GameState;

import java.awt.Graphics2D;

import Manager.GameStateManager;
import Manager.Keys;
import application.GamePanel;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class GameOverState extends GameState {
	private int score;
	private static final Font TEXT_FONT = new Font("Fipps", 80);

	public GameOverState(GameStateManager gsm) {
		// TODO Auto-generated constructor stub
		super(gsm);
		score = 0;
	}
	public GameOverState(GameStateManager gsm, int score) {
		super(gsm);
		this.score = score;
		//System.out.println(score);
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		//System.out.println("CHECK");

	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		this.addKeyEventHandler();
		//System.out.println("CHECK");
		

	}

	@Override
	public void draw(Canvas g) {
		// TODO Auto-generated method stub
		GraphicsContext gc = g.getGraphicsContext2D();
		gc.setFill(Color.RED);
		gc.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
		//System.out.println("CHECK");
		
		gc.setFill(Color.WHITE);
		gc.setFont(TEXT_FONT);
		gc.fillText("High Score: "+score, 300, 125);
		

	}

	@Override
	public void addKeyEventHandler() {
		// TODO Auto-generated method stub
		if(Keys.anyKeyPress()) {
			gsm.setState(GameStateManager.MENU);
		}
	}

}
