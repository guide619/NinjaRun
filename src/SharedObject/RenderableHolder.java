package SharedObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;


public class RenderableHolder {
	private static final RenderableHolder instance = new RenderableHolder();

	private List<IRenderable> entities;
	private Comparator<IRenderable> comparator;
	public static Image bg;
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
	public static AudioClip  explosionSound;
	public static Image land1;
	public static Image Shuriken;
	public static Image Cloud1;
	public static Image Cloud2;
	public static Image Cloud3;
	public static Image Cloud4;
	
	public static Image Smoke1;
	public static Image Smoke2;
	public static Image Smoke3;
	public static Image Smoke4;

	public static AudioClip gameplay;
	public static AudioClip collect;
	public static AudioClip Select;
	public static Image bgplay;


	public static Image item1;

	public static Image item2;

	public static Image item3;

	public static Image item4;

	

	public static Image Mark;
	public static Image Guide;
	public static Image Tan;
	public static Image Heal;
	public static Image fire;

	

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
		String BG3 = "file:Resources/Map/bgplay.jpg";
		String Land = "file:Resources/bg/bg1.png";
		String BGmenu1 = "file:Resources/bg/bgmenu1.jpg";
		String BGmenu2 = "file:Resources/bg/bgmenu2.jpg";
		
		
		String sprite = "file:Resources/spite/sprite.png";
		String sprite1 = "file:Resources/spite/sprite1.png";
		String sprite2 = "file:Resources/spite/sprite2.png";
		String sprite3 = "file:Resources/spite/sprite3.png";
		String sprite4 = "file:Resources/spite/sprite4.png";
		String sprite1_blink = "file:Resources/spite/sprite1_blink.png";
		String sprite3_blink = "file:Resources/spite/sprite3_blink.png";
		String sprite5_blink = "file:Resources/spite/sprite5_blink.png";
		String heal = "file:Resources/items/sushi.png";
		
		String shuriken = "file:Resources/spite/shuriken.png";
		
		String cloud1 = "file:Resources/cloud/cloud1.png";
		String cloud2 = "file:Resources/cloud/cloud2.png";
		String cloud3 = "file:Resources/cloud/cloud3.png";
		String cloud4 = "file:Resources/cloud/cloud4.png";
		String gamePlay = "file:Resources/sound/gameplay.mp3";
		String Collect = "file:Resources/sound/collect.wav";
		String select = "file:Resources/sound/tilechange.wav";

		String temple = "file:Resources/items/temple.png";
		String house = "file:Resources/items/house.png";

		String mark = "file:Resources/spite/Mark.png";
		String guide = "file:Resources/spite/Guide.png";
		String tan = "file:Resources/spite/Tan.png";
		String onfire = "file:Resources/spite/fire.png";
		
		String smoke1 = "file:Resources/smoke/smoke1.png";
		String smoke2 = "file:Resources/smoke/smoke2.png";
		String smoke3 = "file:Resources/smoke/smoke3.png";
		String smoke4 = "file:Resources/smoke/smoke4.png";

		logo = new Image(Logo);
		bg = new Image(BGmenu);
		bg1 = new Image(BGmenu1);
		bg2 = new Image(BGmenu2);
		bgplay = new Image(BG3);
		fire = new Image(onfire);
		land1 = new Image(Land);
		
		
		spite = new Image(sprite);
		spite1 = new Image(sprite1);
		spite2 = new Image(sprite2);
		spite3 = new Image(sprite3);
		spite4 = new Image(sprite4);
		spite1_blink = new Image(sprite1_blink);
		spite3_blink = new Image(sprite3_blink);
		spite5_blink = new Image(sprite5_blink);
		
		Smoke1= new Image(smoke1);
		Smoke2= new Image(smoke2);
		Smoke3= new Image(smoke3);
		Smoke4= new Image(smoke4);


		Heal= new Image(heal);
		Shuriken = new Image(shuriken);
		
		Cloud1 = new Image(cloud1);
		Cloud2 = new Image(cloud2);
		Cloud3 = new Image(cloud3);
		Cloud4 = new Image(cloud4);
		
		Mark = new Image(mark);
		Guide = new Image(guide);
		Tan = new Image(tan);
		
		gameplay = new AudioClip(gamePlay);
		collect = new AudioClip(Collect);
		Select = new AudioClip(select);
		
		item1=new Image(temple);
		item2=new Image(house);
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