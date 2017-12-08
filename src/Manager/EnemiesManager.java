package Manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import Object.Bird;
import Object.Character;
import Object.Enemy;
import Object.Ninja;
import Object.Obstruct;
import Object.Shuriken;
import Object.Special;
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
	
	private int wait;
	private int wave;
	
	private List<Character> enemies;
	private List<Shuriken> shurikens;
	private Ninja ninja;
	private Special Special;
	
	public EnemiesManager(Ninja	ninja) {
		rand = new Random();
		enemy1 = RenderableHolder.Mark;
		enemy2 = RenderableHolder.Guide;
		enemy3 = RenderableHolder.Tan;
		special = RenderableHolder.pichu;
		enemies = new ArrayList<Character>();
		shurikens = new ArrayList<Shuriken>();
		this.ninja = ninja;
		enemies.add(createEnemy(0));
		wave = 0;
		wait=0;
		Special = new Special(ninja,1000,(int)enemy2.getWidth() - 10, (int)enemy2.getHeight() - 10, special);
		
	}
	
	public void update() {
		//System.out.println(wave);
		Special.update();
		enemyupdate();
		shurikenupdate();
		checkFire();
		
	}
	public void draw(Canvas game) {
		Special.draw(game);
		for(Character e : enemies) {
			e.draw(game);
		}
		for(Shuriken s : shurikens) {
			s.draw(game);
		}
	}
	public void shurikenupdate() {
		if(shurikens.size()!=0) {
		for(Shuriken s : shurikens) {
			s.update((int)ninja.getSpeedX());
		}if(shurikens.get(0).isOutOfScreen()) shurikens.remove(0);
	
		}
	}
	public void enemyupdate(){
		if(enemies.size()==0) {
			if (wait==10) spawnEnemy();
			else wait++;
		}else{
			for(Character e : enemies) {
				e.update();
				if (e.isOutOfScreen()) {
					destroy(e);
					break;
				}
			}
		}
		
	}
	private Character createEnemy(int i) {
		int gap = 1000+i*100;
		gap+= rand.nextInt(10)*100;
		int type = rand.nextInt(11);
		//int type = 10;
		if(type >4 && type <=7) {
			return new Bird(ninja, gap, (int)enemy1.getWidth() - 10, (int)enemy1.getHeight() - 10, enemy1);
		} else if (type <=4) {
			return new Obstruct(ninja, gap, (int)enemy2.getWidth() - 10, (int)enemy2.getHeight() - 10, enemy2);
		}else {
			return new Enemy(ninja, gap, (int)enemy3.getWidth() - 10, (int)enemy3.getHeight() - 10, enemy3);
		}
	}
	public void createShuriken() {
		shurikens.add(new Shuriken((int)(ninja.getPosX()),(int)ninja.getPosY()));
	}
	
	public boolean isCollision() {
		for(Character e : enemies) {
			if (ninja.getBound().intersects(e.getBound().getBoundsInLocal()) && (ninja.getState() != 6) ){
				return true;
			}
		}
		return false;
	}
	public void checkFire() {
		if(enemies.size()==0 || shurikens.size()==0)return;
		for(Character e : enemies) {
			if (shurikens.get(0).getBound().intersects(e.getBound().getBoundsInLocal())  ){
					if(e instanceof Enemy) {
						destroy(e);
						shurikens.remove(0);
						return;
					}
			}
		}
	}
	public boolean isSpCollision() {
			if (ninja.getBound().intersects(Special.getBound().getBoundsInLocal())) {
				if(ninja.getState() != 6) {
					ninja.setSpeedX((int) (ninja.getSpeedX()+1));
					Special.reset();}
				
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
		//System.out.println("wave = "+wave);
		wave++;
	}

	public void spawnEnemy() {
		wait=0;
		//System.out.println("Check");
		increaseWave();
		int j = wave+rand.nextInt(2);
		if(wave>=7) j=5+rand.nextInt(2);
		for(int i =0 ;i<j;i++) {
			enemies.add(createEnemy(i));
		}
	
	}
	public void destroy(Character c) {
		enemies.remove(c);
		ninja.upScore();
	}
	
}
