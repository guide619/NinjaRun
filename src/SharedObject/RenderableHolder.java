package SharedObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import Manager.Animation;
import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;


public class RenderableHolder {
	private static final RenderableHolder instance = new RenderableHolder();

	public static Image Instruction;
	public static Image logo;
	public static Image bg1;
	public static Image bg2;
	public static Image spite;
	public static Image spite1;
	public static Image spite1_blink;
	public static Image spite2;
	public static Image spite3;
	public static Image spite3_blink;
	public static Image spite4;
	public static Image spite5_blink;
	public static Image Spitedead;
	public static AudioClip  explosionSound;
	public static Image land1;
	public static Image Shuriken;
	public static Image Shuriken2;
	public static Image Kunai;
	public static Image Cloud1;
	public static Image Cloud2;
	public static Image Cloud3;
	public static Image Cloud4;
	
	public static Image Ultimate;
	public static Image UltimateReady;
	
	public static Image Smoke1;
	public static Image Smoke2;
	public static Image Smoke3;
	public static Image Smoke4;
	public static Image Boom;
	
	public static Image Enemy1;
	public static Image Enemy2;
	public static Image Enemy3;
	public static Image Enemy4;
	public static Image Enemy5;
	public static Image Enemy6;
	public static Image BadShuriken;
	public static Image BadShuriken2;
	public static Image BadShuriken3;
	public static Image BadShuriken4;

	public static AudioClip gameplay;
	public static AudioClip collect;
	public static AudioClip Select;
	public static AudioClip Damage;
	public static Image bgplay;


	public static Image item1;
	public static Image item2;
	public static Image item3;
	public static Image item4;

	public static Animation normalRunAnim;
	public static Animation cooldownRunAnim;
	public static Animation cooldownJumpAnim;
	public static Animation warpAnim;
	public static Animation enemyAnim;
	public static Animation badShuAnim;
	public static Animation boomAnim;

	public static Image Mark;
	public static Image Spike;
	public static Image Tan;
	public static Image Heal;
	public static Image fire;

	public static Image Speed;

	public static AudioClip Healsound;
	public static AudioClip SpeedSound;
	public static AudioClip DieSound;
	public static AudioClip jumpSound;
	public static AudioClip ninjaSound;
	
	public static AudioClip UltimateSound;
	public static AudioClip ShurikenSound;
	public static AudioClip EnemyHitSound;

	public static Image Ghost;

	public static AudioClip Logosound;

	public static Image pausebg;

	public static Image pausescroll1;
	public static Image pausescroll2;

	public static AudioClip warpSound;


	public static Image Over;
	public static Image Over1;


	public static Image ScoreAndHealth;
	public static Image Background;
	public static Image deathBackground;
	public static Image Press;
	public static Image PressEnter;

	public static Image pauseInstruct;

	public static Animation tanAnim;
	static {
		loadResource();
	}

	

	public static RenderableHolder getInstance() {
		return instance;
	}

	
	public static void loadResource() {
		System.out.println(ClassLoader.getSystemResource("Logo/GT.png").toString());
		String Logo = ClassLoader.getSystemResource("Logo/GT.png").toString();
		//String BGmenu = ClassLoader.getSystemResource("bg/bgmenu.jpg").toString();

		String bgover = ClassLoader.getSystemResource("bg/over.png").toString();
		String bgover1 = ClassLoader.getSystemResource("bg/over1.png").toString();
		String press = ClassLoader.getSystemResource("bg/press.png").toString();
		String pressen = ClassLoader.getSystemResource("bg/pressEnter.png").toString();
		//String BG = ClassLoader.getSystemResource("Map/bgplaystart.jpg").toString();
		//String BG1 = ClassLoader.getSystemResource("Map/bgplay1.jpg").toString();

	
		String background = ClassLoader.getSystemResource("Map/bgplay.jpg").toString();
		String instruction = ClassLoader.getSystemResource("Map/bgplaystart.png").toString();
		String scoreAndHealth = ClassLoader.getSystemResource("Map/bgplay2.png").toString();

		String scrollp1 =  ClassLoader.getSystemResource("bg/puase1.png").toString();
		String scrollp2 =   ClassLoader.getSystemResource("bg/puase2.png").toString();
		String pauseinstruct =   ClassLoader.getSystemResource("bg/puaseinstruct.png").toString();

		String deathbackground = ClassLoader.getSystemResource("Map/bgdead.jpg").toString();

		String Land = ClassLoader.getSystemResource("bg/bg1.png").toString();
		String BGmenu1 = ClassLoader.getSystemResource("bg/bgmenu1.jpg").toString();
		String BGmenu2 = ClassLoader.getSystemResource("bg/bgmenu2.jpg").toString();
		String ultimate = ClassLoader.getSystemResource("Ultimate.png").toString();
		String ultimateready = ClassLoader.getSystemResource("UltimateReady.png").toString();
		
		
		String sprite =  ClassLoader.getSystemResource("spite/sprite.png").toString();
		String sprite1 = ClassLoader.getSystemResource("spite/sprite1.png").toString();
		String sprite2 = ClassLoader.getSystemResource("spite/sprite2.png").toString();
		String sprite3 = ClassLoader.getSystemResource("spite/sprite3.png").toString();
		String sprite4 = ClassLoader.getSystemResource("spite/sprite4.png").toString();
		String sprite1_blink =ClassLoader.getSystemResource("spite/sprite1_blink.png").toString(); 
		String sprite3_blink = ClassLoader.getSystemResource("spite/sprite3_blink.png").toString(); 
		String sprite5_blink = ClassLoader.getSystemResource("spite/sprite5_blink.png").toString(); 
		String spriteDead = ClassLoader.getSystemResource("spite/spritedead.png").toString(); 
		String heal = ClassLoader.getSystemResource("items/sushi.png").toString(); 
		String speed = ClassLoader.getSystemResource("items/scroll.png").toString(); 
		
		String enemy1 = ClassLoader.getSystemResource("Enemy/enemy1.png").toString(); 
		String enemy2 = ClassLoader.getSystemResource("Enemy/enemy2.png").toString(); 
		String enemy3 = ClassLoader.getSystemResource("Enemy/enemy3.png").toString(); 
		String enemy4 = ClassLoader.getSystemResource("Enemy/enemy4.png").toString(); 
		String enemy5 = ClassLoader.getSystemResource("Enemy/enemy5.png").toString(); 
		String enemy6 = ClassLoader.getSystemResource("Enemy/enemy6.png").toString(); 
		String badshuriken = ClassLoader.getSystemResource("Enemy/badshuriken.png").toString(); 
		String badshuriken2 = ClassLoader.getSystemResource("Enemy/badshuriken2.png").toString(); 
		String badshuriken3 =  ClassLoader.getSystemResource("Enemy/badshuriken3.png").toString(); 
		String badshuriken4 =  ClassLoader.getSystemResource("Enemy/badshuriken4.png").toString(); 
		
		String shuriken =  ClassLoader.getSystemResource("spite/shuriken.png").toString(); 
		String shuriken2 =  ClassLoader.getSystemResource("spite/shuriken2.png").toString();
		String kunai =  ClassLoader.getSystemResource("spite/kunai.png").toString(); 
		String ghost=  ClassLoader.getSystemResource("spite/ghost.png").toString(); 
		
		String cloud2 =  ClassLoader.getSystemResource("cloud/cloud2.png").toString(); 
		
		
	
		String Collect =  ClassLoader.getSystemResource("sound/collect.wav").toString(); 
		String select =  ClassLoader.getSystemResource("sound/tilechange.wav").toString(); 
		String warp =  ClassLoader.getSystemResource("sound/Warp.mp3").toString();
		String healsound =  ClassLoader.getSystemResource("sound/Heal.wav").toString(); 

		String temple =  ClassLoader.getSystemResource("items/temple.png").toString();  
		String house = ClassLoader.getSystemResource("items/house.png").toString();  

		//String mark =  ClassLoader.getSystemResource("Enemy/badshuriken.png").toString(); "file:Resources/spite/Mark.png";
		String spike = ClassLoader.getSystemResource("spite/Spike.png").toString();  
		//String tan = ClassLoader.getSystemResource("Enemy/badshuriken.png").toString();  "file:Resources/spite/Tan.png";
		String onfire =  ClassLoader.getSystemResource("spite/fire.png").toString(); 
		
		String smoke1 = ClassLoader.getSystemResource("smoke/smoke1.png").toString();  
		String smoke2 =  ClassLoader.getSystemResource("smoke/smoke2.png").toString();  
		String smoke3 =  ClassLoader.getSystemResource("smoke/smoke3.png").toString();  
		String smoke4 =  ClassLoader.getSystemResource("smoke/smoke4.png").toString();  
		String boom =  ClassLoader.getSystemResource("smoke/boom.png").toString(); 
		

		Background = new Image(background);
		deathBackground = new Image(deathbackground);
		logo = new Image(Logo);
		Instruction = new Image(instruction);
		bg1 = new Image(BGmenu1);
		bg2 = new Image(BGmenu2);
		ScoreAndHealth = new Image(scoreAndHealth);
		fire = new Image(onfire);
		land1 = new Image(Land);
		Over = new Image(bgover);
		Over1 = new Image(bgover1);
		
		pausescroll1 = new Image(scrollp1);
		pausescroll2 = new Image(scrollp2 );
		pauseInstruct = new Image(pauseinstruct);
		Ultimate = new Image(ultimate);
		UltimateReady = new Image(ultimateready);
		Press = new Image(press);
		PressEnter = new Image(pressen);
		spite = new Image(sprite);
		spite1 = new Image(sprite1);
		spite2 = new Image(sprite2);
		spite3 = new Image(sprite3);
		spite4 = new Image(sprite4);
		spite1_blink = new Image(sprite1_blink);
		spite3_blink = new Image(sprite3_blink);
		spite5_blink = new Image(sprite5_blink);

		Spitedead = new Image(spriteDead);
		

		Smoke1= new Image(smoke1);
		Smoke2= new Image(smoke2);
		Smoke3= new Image(smoke3);
		Smoke4= new Image(smoke4);
		Boom = new Image(boom);
		
		Enemy1= new Image(enemy1);
		Enemy2= new Image(enemy2);
		Enemy3= new Image(enemy3);
		Enemy4= new Image(enemy4);
		Enemy5= new Image(enemy5);
		Enemy6= new Image(enemy6);
		BadShuriken = new Image(badshuriken);
		BadShuriken2 = new Image(badshuriken2);
		BadShuriken3 = new Image(badshuriken3);
		BadShuriken4 = new Image(badshuriken4);

		Speed = new Image(speed);
		Heal= new Image(heal);
		Shuriken = new Image(shuriken);
		Shuriken2 = new Image(shuriken2);
		Kunai = new Image(kunai);
		Cloud2 = new Image(cloud2);
		
		Spike = new Image(spike);
		Ghost = new Image(ghost);
		item1 = new Image(house);
		item2 = new Image(temple);
		
		String ultimateSound =  ClassLoader.getSystemResource("sound/Ultimate.mp3").toString(); 
		UltimateSound = new AudioClip(ultimateSound);
		String shurikenSound =  ClassLoader.getSystemResource("sound/shurikenThrow.mp3").toString(); 
		ShurikenSound = new AudioClip(shurikenSound);
		String explode =  ClassLoader.getSystemResource("sound/Explosion.wav").toString(); 
		EnemyHitSound = new AudioClip(explode);
		
		//gameplay = new AudioClip(gamePlay);
		collect = new AudioClip(Collect);
		Select = new AudioClip(select);
		Damage = new AudioClip(select);
		Healsound = new AudioClip(healsound );
		String diesound =  ClassLoader.getSystemResource("sound/die.wav" ).toString(); 
		DieSound = new AudioClip(diesound);
		String jumpsound =  ClassLoader.getSystemResource("sound/jump.wav" ).toString();  
		jumpSound = new AudioClip(jumpsound);
		String ninjasound =  ClassLoader.getSystemResource("sound/ninja.wav" ).toString(); 
		ninjaSound = new AudioClip(ninjasound);
		System.out.println(ClassLoader.getSystemResource("sound/logo.wav").toString());
		String logo = ClassLoader.getSystemResource("sound/logo.wav").toString();
		
		Logosound = new AudioClip(logo);
		warpSound = new AudioClip(warp);

		
		normalRunAnim = new Animation(50);
		normalRunAnim.addFrame(RenderableHolder.spite);
		normalRunAnim.addFrame(RenderableHolder.spite1);
		normalRunAnim.addFrame(RenderableHolder.spite2);
		normalRunAnim.addFrame(RenderableHolder.spite3);
		normalRunAnim.addFrame(RenderableHolder.spite4);
		cooldownRunAnim = new Animation(90);
		cooldownRunAnim.addFrame(RenderableHolder.spite);
		cooldownRunAnim.addFrame(RenderableHolder.spite1_blink);
		cooldownRunAnim.addFrame(RenderableHolder.spite2);
		cooldownRunAnim.addFrame(RenderableHolder.spite3_blink);
		cooldownRunAnim.addFrame(RenderableHolder.spite4);
		cooldownRunAnim.addFrame(RenderableHolder.spite5_blink);
		cooldownJumpAnim = new Animation(90);
		cooldownJumpAnim.addFrame(RenderableHolder.spite1);
		cooldownJumpAnim.addFrame(RenderableHolder.spite1_blink);
		warpAnim = new Animation(70);
		warpAnim.addFrame(RenderableHolder.spite1);
		warpAnim.addFrame(RenderableHolder.Smoke1);
		warpAnim.addFrame(RenderableHolder.Smoke2);
		warpAnim.addFrame(RenderableHolder.Smoke3);
		warpAnim.addFrame(RenderableHolder.Smoke4);
		warpAnim.addFrame(RenderableHolder.Smoke4);
		warpAnim.addFrame(RenderableHolder.Smoke4);
		warpAnim.addFrame(RenderableHolder.Smoke4);
		warpAnim.addFrame(RenderableHolder.Smoke4);
		warpAnim.addFrame(RenderableHolder.Smoke1);
		warpAnim.addFrame(RenderableHolder.Smoke2);
		warpAnim.addFrame(RenderableHolder.Smoke3);
		warpAnim.addFrame(RenderableHolder.spite1);
		enemyAnim = new Animation(50);
		enemyAnim.addFrame(RenderableHolder.Enemy1);
		enemyAnim.addFrame(RenderableHolder.Enemy2);
		enemyAnim.addFrame(RenderableHolder.Enemy3);
		enemyAnim.addFrame(RenderableHolder.Enemy4);
		enemyAnim.addFrame(RenderableHolder.Enemy5);
		enemyAnim.addFrame(RenderableHolder.Enemy6);
		badShuAnim = new Animation(50);
		badShuAnim.addFrame(BadShuriken);
		badShuAnim.addFrame(BadShuriken2);
		badShuAnim.addFrame(BadShuriken3);
		badShuAnim.addFrame(BadShuriken4);
		boomAnim = new Animation(70);
		boomAnim.addFrame(RenderableHolder.Smoke1);
		boomAnim.addFrame(RenderableHolder.Smoke2);
		boomAnim.addFrame(RenderableHolder.Smoke3);
		boomAnim.addFrame(RenderableHolder.Smoke4);
		
	}


}