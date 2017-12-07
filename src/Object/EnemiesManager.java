package Object;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import Object.Bird;
import Object.Enemy;

import SharedObject.RenderableHolder;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class EnemiesManager {
	
	private Image enemy1;
	private Image enemy2;
	private Image enemy3;
	private Image special;
	private Random rand;
	
	private int NumberOfEnemy;
	private int wait;
	private int wave;
	
	private List<Character> enemies;
	private Ninja ninja;
	private int count;
	private Special Special;
	
	public EnemiesManager(Ninja	ninja) {
		rand = new Random();
		enemy1 = RenderableHolder.Mark;
		enemy2 = RenderableHolder.Guide;
		enemy3 = RenderableHolder.Tan;
		special = RenderableHolder.pichu;
		int count = 0;
		enemies = new ArrayList<Character>();
		this.ninja = ninja;
		enemies.add(createEnemy(0));
		NumberOfEnemy = 1;
		wait=0;
		Special = new Special(ninja,1000,(int)enemy2.getWidth() - 10, (int)enemy2.getHeight() - 10, special);
		
	}
	
	public void update() {
		//System.out.println(wave);
		Special.update();
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
				increaseWave();
				int j = wave+rand.nextInt(3);
				for(int i =0 ;i<j;i++) {
					enemies.add(createEnemy(i));
					NumberOfEnemy++;
					//System.out.println(NumberOfEnemy);
				}wait=0;
			}else wait++;
			
		}
	}
	
	public void draw(Canvas game) {
		Special.draw(game);
		for(Character e : enemies) {
			e.draw(game);
		}
	}
	
	private Character createEnemy(int i) {
		int gap = 50+rand.nextInt(20)*rand.nextInt(20);
		int type = rand.nextInt(3);
		if(type == 0) {
			return new Bird(ninja, 1000+gap+i*10, (int)enemy1.getWidth() - 10, (int)enemy1.getHeight() - 10, enemy1);
		} else if (type ==1) {
			return new Obstruct(ninja, 1000+gap+i*10, (int)enemy2.getWidth() - 10, (int)enemy2.getHeight() - 10, enemy2);
		}else {
			return new Enemy(ninja, 1000+gap+i*10, (int)enemy3.getWidth() - 10, (int)enemy3.getHeight() - 10, enemy3);
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
	public boolean isSpCollision() {
			if (ninja.getBound().intersects(Special.getBound().getBoundsInLocal())) {
				System.out.println("YEAHHHHH");
				Special.reset();
				
				return true;
		}
		return false;
	}
	
	public void reset() {
		enemies.clear();
		enemies.add(createEnemy(0));
	}

	public int getWave() {
		return wave;
	}

	public void increaseWave() {
		wave++;
	}
	
}
