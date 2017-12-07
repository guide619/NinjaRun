package Object;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import SharedObject.RenderableHolder;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class EnemiesManager {
	
	private Image enemy1;
	private Image enemy2;
	private Random rand;
	
	private List<Character> enemies;
	private Ninja ninja;
	
	public EnemiesManager(Ninja	ninja) {
		rand = new Random();
		enemy1 = RenderableHolder.Mark;
		enemy2 = RenderableHolder.Pichu;
		
		enemies = new ArrayList<Character>();
		this.ninja = ninja;
		enemies.add(createEnemy());
	}
	
	public void update() {
		for(Character e : enemies) {
			e.update();
		}
		Character enemy = enemies.get(0);
		if(enemy.isOutOfScreen()) {
			ninja.upScore();
			enemies.clear();
			enemies.add(createEnemy());
		}
	}
	
	public void draw(Canvas game) {
		for(Character e : enemies) {
			e.draw(game);
		}
	}
	
	private Character createEnemy() {
		// if (enemyType = getRandom)
		int type = rand.nextInt(2);
		//if(type == 0) {
			return new obstruct(ninja, 1100, (int)enemy1.getWidth() - 10, (int)enemy1.getHeight() - 10, enemy1);
		//} else {
		//	return new obstruct(ninja, 1100, (int)enemy2.getWidth() - 10, (int)enemy2.getHeight() - 10, enemy2);
		//}
	}
	
	public boolean isCollision() {
		for(Character e : enemies) {
			if (ninja.getBound().intersects(e.getBound().getBoundsInLocal())) {
				return true;
			}
		}
		return false;
	}
	
	public void reset() {
		enemies.clear();
		enemies.add(createEnemy());
	}
	
}
