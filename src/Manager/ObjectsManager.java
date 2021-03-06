package Manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import Object.FlyingObject;
import Object.Speed;
import Object.Character;
import Object.Effect;
import Object.Enemy;
import Object.Ninja;
import Object.Obstruct;
import Object.Shuriken;
import Object.Heal;
import SharedObject.RenderableHolder;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;

public class ObjectsManager {
	
	private static final int MAX_CHURIKEN = 50;
	
	private Random rand;
	
	private int wait;
	public static int wave;
	
	private List<Character> enemies;
	private List<Shuriken> shurikens;
	private List<Effect> booms;
	private Ninja ninja;
	private Heal heal;
	private Speed speed;
	private int shurikencount;
	
	
	public ObjectsManager(Ninja	ninja) {
		this.ninja = ninja;
		rand = new Random();
		enemies = new ArrayList<Character>();
		shurikens = new ArrayList<Shuriken>();
		booms = new ArrayList<Effect>();
		enemies.add(createEnemy(1));
		wave = 0;
		wait=0;
		shurikencount = MAX_CHURIKEN;
		heal= new Heal(ninja,4000);
		speed= new Speed(ninja,1000);
		
		
	}
	
	public void update() {
		//System.out.println(wave);
		enemyupdate();
		shurikenupdate();
		boomupdate();
		if(!shurikens.isEmpty())checkFire();
		heal.update();
		speed.update();
		if(shurikencount<=MAX_CHURIKEN) shurikencount++;

	}
	public void draw(Canvas game) {
		heal.draw(game);
		speed.draw(game);
		for(Character e : enemies) {
			e.draw(game);
		}
		for(Shuriken s : shurikens) {
			s.draw(game);
		}for(Effect b : booms) {
			b.draw(game);
		}
	}
	public void boomupdate() {
		if(booms.size()!=0) {
		}for(int i = 0 ; i<booms.size();i++) {
			Effect s =booms.get(i);
			s.update();
			if (s.isDestroy()) {
				booms.remove(i);
				i--;
			}
		}
	}
	public void shurikenupdate() {
		if(shurikens.size()!=0) {
		}for(int i = 0 ; i<shurikens.size();i++) {
			Shuriken s =shurikens.get(i);
			s.update((int)ninja.getSpeedX());
			if (s.isOutOfScreen()) {
				shurikens.remove(i);
				i--;
			}
		}
	}
	public void enemyupdate(){
		if(enemies.size()==0) {
			if (wait==10) spawnEnemy();
			else wait++;
		}else{
			for(int i = 0; i <enemies.size();i++) {
				Character e = enemies.get(i);
				e.update();
				if (e.isOutOfScreen()) {
					destroy(e);
					i--;
				}
			}
		}
		
	}
	private Character createEnemy(int i) {
		int gap = 1000+i*50;
		int type = rand.nextInt(11);
		//int type =11;
		if(type >4 && type <=7) {
			gap+= rand.nextInt(20)*100;
			return new FlyingObject(ninja, gap);
		} else if (type <=4) {
			return new Obstruct(ninja, gap);
		}else {
			gap+= rand.nextInt(10)*100;
			return new Enemy(ninja, gap);
		}
	}
	public void createShuriken() {
		if(ninja.getState() != ninja.WARP && shurikencount > 0) {
			shurikens.add(new Shuriken((int)(ninja.getPosX()-10),(int)ninja.getPosY()));
			RenderableHolder.ShurikenSound.play();
			shurikencount-=30;
			//System.out.println(shurikencount);
		}
	}
	
	public boolean isCollision() {
		if(ninja.getState() == ninja.WARP || ninja.getState() == ninja.ULTIMATE) return false;
		for(Character e : enemies) {
			if (ninja.getBound().intersects(e.getBound().getBoundsInLocal())){
				return true;
			}
		}
		return false;
	}
	public void checkFire() {
		if(enemies.isEmpty())return;
		for(Character e : enemies) {
			if (e instanceof Enemy ){
					if(shurikens.get(0).getBound().intersects(e.getBound().getBoundsInLocal()) ) {
						booms.add(new Effect(e.getPosX(),e.getPosY(),ninja.getSpeedX()));
						destroy(e);
						RenderableHolder.EnemyHitSound.play();
						shurikens.remove(0);
						return;
					}
			}
		}
	}
	public boolean isSpCollision() {
		if(ninja.getState() == ninja.WARP || ninja.getState() == ninja.ULTIMATE) return false;
		if (ninja.getBound().intersects(heal.getBound().getBoundsInLocal())) {
			RenderableHolder.Healsound.play();
			ninja.increaseHealth();
			heal.reset();
			return true;	
		}
		if (ninja.getBound().intersects(speed.getBound().getBoundsInLocal())) {
			speed.reset();
			RenderableHolder.Healsound.play();
			ninja.powerObtain();
			return true;
		}
		return false;
	}
	
	public void reset() {
		enemies.clear();
		enemies.add(createEnemy(1));
	}

	public int getWave() {
		return wave;
	}
	public void increaseWave() {
		//System.out.println("wave = "+wave);
		wave++;
		if(wave>=15 && wave<25 && wave%2 == 0) ninja.increaseNormalSpeedX(1);
		//else if(wave>=20) ninja.increaseNormalSpeedX(2);
		//else if (wave>=30)ninja.setSpeedX(30);
	}

	public void spawnEnemy() {
		wait=0;
		//System.out.println("Check");
		increaseWave();
		int j = wave+rand.nextInt(2);
		if(wave>=7) j=5+rand.nextInt(10);
		if (wave>=15) j=10+rand.nextInt(10);
		for(int i =0 ;i<j;i++) {
			enemies.add(createEnemy(i));
		}
	
	}
	public void destroy(Character c) {
		enemies.remove(c);
		ninja.upScore();
	}
	
}
