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
	private Image enemy3;
	private Random rand;
	
	private int wave;
	private int NumberOfEnemy;
	private int wait;
	
	private List<Character> enemies;
	private Ninja ninja;
	private int count;
	
	public EnemiesManager(Ninja	ninja) {
		rand = new Random();
		enemy1 = RenderableHolder.Mark;
		enemy2 = RenderableHolder.Guide;
		enemy3 = RenderableHolder.Tan;
		int count = 0;
		wave = 1;
		enemies = new ArrayList<Character>();
		this.ninja = ninja;
		enemies.add(createEnemy());
		NumberOfEnemy = 1;
		wait=0;
		
	}
	
	public void update() {
		for(Character e : enemies) {
			e.update();
		}
		if (count< this.NumberOfEnemy) {
			Character enemy = enemies.get(0);
			if(enemy.isOutOfScreen()) {
				ninja.upScore();
				enemies.remove(0);
				count++;
			}
		}else{
			enemies.clear();
			count=0;
			NumberOfEnemy=0;
			if(wait == 10) {
				for(int i =0 ;i<wave;i++) {
					enemies.add(createEnemy());
					NumberOfEnemy++;
					System.out.println(NumberOfEnemy);
				}wave++;
			}else wait++;
			
		}
	}
	
	public void draw(Canvas game) {
		for(Character e : enemies) {
			e.draw(game);
		}
	}
	
	private Character createEnemy() {
		int gap = 50+rand.nextInt(10)*rand.nextInt(20);
		int type = rand.nextInt(3);
		if(type == 0) {
			return new Bird(ninja, 1000+gap, (int)enemy1.getWidth() - 10, (int)enemy1.getHeight() - 10, enemy1);
		} else if (type ==1) {
			return new Obstruct(ninja, 1000+gap, (int)enemy2.getWidth() - 10, (int)enemy2.getHeight() - 10, enemy2);
		}else {
			return new Enemy(ninja, 1000+gap, (int)enemy3.getWidth() - 10, (int)enemy3.getHeight() - 10, enemy3);
		}
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
