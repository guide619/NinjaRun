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

	private List<IRenderable> entities;
	private Comparator<IRenderable> comparator;
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
	static {
		loadResource();
	}

	public RenderableHolder() {
		entities = new ArrayList<IRenderable>();
		comparator = (IRenderable o1, IRenderable o2) -> {
			if (o1.getZ() > o2.getZ())
				return 1;
			return -1;
		};
	}

	public static RenderableHolder getInstance() {
		return instance;
	}

	
	public static void loadResource() {
		String Logo = "file:Resources/Logo/GT.png";
		String BGmenu = "file:Resources/bg/bgmenu.jpg";

		String bgover = "file:Resources/bg/over.png";
		String bgover1 = "file:Resources/bg/over1.png";
		String press = "file:Resources/bg/press.png";
		String BG = "file:Resources/Map/bgplaystart.jpg";
		String BG1 = "file:Resources/Map/bgplay1.jpg";

		String bgpause = "file:Resources/bg/bgpjpg";
		String background = "file:Resources/Map/bgplay.jpg";
		String instruction = "file:Resources/Map/bgplaystart.png";
		String scoreAndHealth = "file:Resources/Map/bgplay2.png";
		String deathbackground = "file:Resources/Map/bgdead.jpg";
		String Land = "file:Resources/bg/bg1.png";
		String BGmenu1 = "file:Resources/bg/bgmenu1.jpg";
		String BGmenu2 = "file:Resources/bg/bgmenu2.jpg";
		String ultimate = "file:Resources/Ultimate.png";
		String ultimateready = "file:Resources/UltimateReady.png";
		
		
		String sprite = "file:Resources/spite/sprite.png";
		String sprite1 = "file:Resources/spite/sprite1.png";
		String sprite2 = "file:Resources/spite/sprite2.png";
		String sprite3 = "file:Resources/spite/sprite3.png";
		String sprite4 = "file:Resources/spite/sprite4.png";
		String sprite1_blink = "file:Resources/spite/sprite1_blink.png";
		String sprite3_blink = "file:Resources/spite/sprite3_blink.png";
		String sprite5_blink = "file:Resources/spite/sprite5_blink.png";
		String spriteDead = "file:Resources/spite/spritedead.png";
		String heal = "file:Resources/items/sushi.png";
		String speed = "file:Resources/items/scroll.png";
		
		String enemy1 = "file:Resources/Enemy/enemy1.png";
		String enemy2 = "file:Resources/Enemy/enemy2.png";
		String enemy3 = "file:Resources/Enemy/enemy3.png";
		String enemy4 = "file:Resources/Enemy/enemy4.png";
		String enemy5 = "file:Resources/Enemy/enemy5.png";
		String enemy6 = "file:Resources/Enemy/enemy6.png";
		String badshuriken = "file:Resources/Enemy/badshuriken.png";
		String badshuriken2 = "file:Resources/Enemy/badshuriken2.png";
		String badshuriken3 = "file:Resources/Enemy/badshuriken3.png";
		String badshuriken4 = "file:Resources/Enemy/badshuriken4.png";
		
		String shuriken = "file:Resources/spite/shuriken.png";
		String shuriken2 = "file:Resources/spite/shuriken2.png";
		String kunai = "file:Resources/spite/kunai.png";
		
		String cloud1 = "file:Resources/cloud/cloud1.png";
		String cloud2 = "file:Resources/cloud/cloud2.png";
		String cloud3 = "file:Resources/cloud/cloud3.png";
		String cloud4 = "file:Resources/cloud/cloud4.png";
		String gamePlay = "file:Resources/sound/gameplay1.mp3";
		String Collect = "file:Resources/sound/collect.wav";
		String select = "file:Resources/sound/tilechange.wav";
		String damage = "file:Resources/sound/damage.wav";
		String warp = "file:Resources/sound/warp.mp3";

		String temple = "file:Resources/items/temple.png";
		String house = "file:Resources/items/house.png";

		String mark = "file:Resources/spite/Mark.png";
		String spike = "file:Resources/spite/Spike.png";
		String tan = "file:Resources/spite/Tan.png";
		String onfire = "file:Resources/spite/fire.png";
		
		String smoke1 = "file:Resources/smoke/smoke1.png";
		String smoke2 = "file:Resources/smoke/smoke2.png";
		String smoke3 = "file:Resources/smoke/smoke3.png";
		String smoke4 = "file:Resources/smoke/smoke4.png";
		String boom = "file:Resources/smoke/boom.png";

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
		String scrollp1 =  "file:Resources/bg/puase1.png";
		pausescroll1 = new Image(scrollp1);
		String scrollp2 =  "file:Resources/bg/puase2.png";
		pausescroll2 = new Image(scrollp2 );
		Ultimate = new Image(ultimate);
		UltimateReady = new Image(ultimateready);
		Press = new Image(press);
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
		
		Cloud1 = new Image(cloud1);
		Cloud2 = new Image(cloud2);
		Cloud3 = new Image(cloud3);
		Cloud4 = new Image(cloud4);
		
		Mark = new Image(mark);
		Spike = new Image(spike);
		Tan = new Image(tan);
		
		String ultimateSound = "file:Resources/sound/Ultimate.mp3" ;
		UltimateSound = new AudioClip(ultimateSound);
		String shurikenSound = "file:Resources/sound/shurikenThrow.mp3" ;
		ShurikenSound = new AudioClip(shurikenSound);
		String explode = "file:Resources/sound/Explosion.wav";
		EnemyHitSound = new AudioClip(explode);
		
		gameplay = new AudioClip(gamePlay);
		collect = new AudioClip(Collect);
		Select = new AudioClip(select);
		Damage = new AudioClip(select);
		String healsound = "file:Resources/sound/heal.wav" ;
		Healsound = new AudioClip(healsound );
		String diesound = "file:Resources/sound/die.wav" ;
		DieSound = new AudioClip(diesound);
		String jumpsound = "file:Resources/sound/jump.wav" ;
		jumpSound = new AudioClip(jumpsound);
		String ninjasound = "file:Resources/sound/ninja.wav" ;
		ninjaSound = new AudioClip(ninjasound);
		String logo = "file:Resources/sound/logo.wav" ;
		Logosound = new AudioClip(logo);
		
		item1=new Image(temple);
		item2=new Image(house);
		String sakura = "file:Resources/items/sakura.png";
		item3 = new Image(sakura);
		String bamboo= "file:Resources/items/bamboo.png";
		item4 = new Image(bamboo);
		warpSound = new AudioClip(warp);
		String ghost= "file:Resources/spite/ghost.png";
		Ghost = new Image(ghost);
		
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

	public void add(IRenderable entity) {
		System.out.println("add");
		entities.add(entity);
		Collections.sort(entities, comparator);
		
	}

	public void update() {
		for (int i = entities.size() - 1; i >= 0; i--) {
			if (entities.get(i).isDestroyed())
				entities.remove(i);
		}
	}

	public List<IRenderable> getEntities() {
		return entities;
	}
}