package Manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import Object.Bird;
import Object.Speed;
import Object.Character;
import Object.Enemy;
import Object.Ninja;
import Object.Obstruct;
import Object.Shuriken;
import Object.Heal;
import SharedObject.RenderableHolder;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class ObjectsManager {
	
	private static final int MAX_CHURIKEN = 3;
	
	private Random rand;
	
	private int wait;
	private int wave;
	
	private List<Character> enemies;
	private List<Shuriken> shurikens;
	private Ninja ninja;
	private Heal heal;
	private Image Heal;
	private Image Speed;
	private Speed speed;
	
	private int shurikencount;
	
	
	public ObjectsManager(Ninja	ninja) {
		rand = new Random();
		Heal = RenderableHolder.Heal;
		Speed = RenderableHolder.Speed;
		enemies = new ArrayList<Character>();
		shurikens = new ArrayList<Shuriken>();
		this.ninja = ninja;
		enemies.add(createEnemy(0));
		wave = 0;
		wait=0;
		shurikencount = MAX_CHURIKEN;
		heal= new Heal(ninja,4000,(int)Heal.getWidth() - 10, (int)Heal.getHeight() - 10, Heal);
		speed= new Speed(ninja,500,(int)Speed.getWidth() - 10, (int)Speed.getHeight() - 10, Speed);
		
	}
	
	public void update() {
		//System.out.println(wave);
		enemyupdate();
		shurikenupdate();
		checkFire();
		heal.update();
		speed.update();
		

	}
	public void draw(Canvas game) {
		heal.draw(game);
		speed.draw(game);
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
		}if(shurikens.get(0).isOutOfScreen()) {
			shurikens.remove(0);
			shurikencount++;
		}
	
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
		int gap = 1000+i*50;
		int type = rand.nextInt(11);
		//int type = 7;
		if(type >4 && type <=7) {
			gap+= rand.nextInt(10)*100;
			return new Bird(ninja, gap);
		} else if (type <=4) {
			return new Obstruct(ninja, gap);
		}else {
			gap+= rand.nextInt(10)*100;
			return new Enemy(ninja, gap);
		}
	}
	public void createShuriken() {
		if(ninja.getState() != 6 && shurikencount > 0) {
			shurikens.add(new Shuriken((int)(ninja.getPosX()),(int)ninja.getPosY()));
			RenderableHolder.ShurikenSound.play();
			shurikencount--;
		}
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
			if (e instanceof Enemy ){
					if(shurikens.get(0).getBound().intersects(e.getBound().getBoundsInLocal()) ) {
						destroy(e);
						RenderableHolder.EnemyHitSound.play();
						shurikens.remove(0);
						shurikencount++;
						return;
					}
			}
		}
	}
	public boolean isSpCollision() {
			if (ninja.getBound().intersects(heal.getBound().getBoundsInLocal())) {
				if(ninja.getState() != 6) {
					RenderableHolder.Healsound.play();
					ninja.increaseHealth();
					heal.reset();
					return true;
					}
			}
			if (ninja.getBound().intersects(speed.getBound().getBoundsInLocal())) {
				if(ninja.getState() != 6) {
					speed.reset();
					RenderableHolder.Healsound.play();
					ninja.powerObtain();
					return true;
					}
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
		if(wave>=7) j=5+rand.nextInt(10);
		for(int i =0 ;i<j;i++) {
			enemies.add(createEnemy(i));
		}
	
	}
	public void destroy(Character c) {
		enemies.remove(c);
		ninja.upScore();
	}
	
}
