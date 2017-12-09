package GameState;

import java.awt.Graphics2D;

import Manager.GameStateManager;
import Manager.Keys;
import SharedObject.RenderableHolder;
import application.GamePanel;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class GameOverState extends GameState {
	private int score;
	private static final Font TEXT_FONT = new Font("Fipps", 80);
	private int i;

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
		i++;

	}

	@Override
	public void draw(Canvas g) {
		// TODO Auto-generated method stub
		GraphicsContext gc = g.getGraphicsContext2D();
		if(i%10<6) {
			gc.drawImage(RenderableHolder.Over, 0, 0);
			gc.drawImage(RenderableHolder.Ghost, 450, 150);
			gc.setFill(Color.WHITE);
			gc.setFont(TEXT_FONT);
			gc.fillText(""+score, 500, 110);
			gc.setStroke(Color.BLACK);
			gc.strokeText(""+score, 500, 110);
		}
		if(i%10>6) {
			gc.drawImage(RenderableHolder.Over1, 0, 0);
			gc.drawImage(RenderableHolder.Ghost, 450, 150);
			gc.setFill(Color.WHITE);
			gc.setFont(TEXT_FONT);
			gc.fillText(""+score, 500, 110);
			gc.setStroke(Color.BLACK);
			gc.strokeText(""+score, 500, 110);

			;
		}

	}

	@Override
	public void addKeyEventHandler() {
		// TODO Auto-generated method stub
		if(Keys.isPressed(Keys.ENTER)) {
			gsm.setState(GameStateManager.MENU);
		}
	}

}
