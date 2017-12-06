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
	
	private List<Enemy> enemies;
	private Ninja ninja;
	
	public EnemiesManager(Ninja	ninja) {
		rand = new Random();
		enemy1 = RenderableHolder.spite1;
		enemy2 = RenderableHolder.spite;
		
		enemies = new ArrayList<Enemy>();
		this.ninja = ninja;
		enemies.add(createEnemy());
	}
	
	public void update() {
		for(Enemy e : enemies) {
			e.update();
		}
		Enemy enemy = enemies.get(0);
		if(enemy.isOutOfScreen()) {
			ninja.upScore();
			enemies.clear();
			enemies.add(createEnemy());
		}
	}
	
	public void draw(Canvas game) {
		for(Enemy e : enemies) {
			e.draw(game);
		}
	}
	
	private Enemy createEnemy() {
		// if (enemyType = getRandom)
		int type = rand.nextInt(2);
		if(type == 0) {
			return new Enemy(ninja, 800, enemy1.getWidth() - 10, enemy1.getHeight() - 10, enemy1);
		} else {
			return new Enemy(ninja, 800, enemy2.getWidth() - 10, enemy2.getHeight() - 10, enemy2);
		}
	}
	
	public boolean isCollision() {
		for(Enemy e : enemies) {
			if (ninja.getBound().intersects(e.getBound())) {
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
