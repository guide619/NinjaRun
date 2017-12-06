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
	public static Image bgs;
	public static Image logo;
	public static Image bg1;
	public static Image bg2;
	public static AudioClip  explosionSound;
	public static Image land1;
	public static Image land2;
	public static Image land3;
	public static Image land4;
	public static Image land5;
	public static Image land6;
	public static Image land7;
	public static Image land8;
	public static Image land9;
	public static Image land0;
	

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
		String BGscroll = "file:Resources/Menu/BG.jpg";
		String BG1 = "file:Resources/bg/bg1.jpg";
		String BG2 = "file:Resources/bg/bg2.jpg";
		String Land1 = "file:Resources/Map/test1.png";
		String Land2 = "file:Resources/Map/test2.png";
		String Land3 = "file:Resources/Map/test3.png";
		String Land4 = "file:Resources/Map/test4.png";
		String Land5 = "file:Resources/Map/test5.png";
		String Land6 = "file:Resources/Map/test6.png";
		String Land7 = "file:Resources/Map/test7.png";
		String Land8 = "file:Resources/Map/test8.png";
		String Land9 = "file:Resources/Map/test9.png";
		String Land0 = "file:Resources/Map/test0.png";
		logo = new Image(Logo);
		bgs = new Image(BGscroll);
		bg1 = new Image(BG1);
		bg2 = new Image(BG2);
		land1 = new Image(Land1);
		land2 = new Image(Land2);
		land3 = new Image(Land3);
		land4 = new Image(Land4);
		land5 = new Image(Land5);
		land6 = new Image(Land6);
		land7 = new Image(Land7);
		land8 = new Image(Land8);
		land9 = new Image(Land9);
		land0 = new Image(Land0);
		
		
		
		
		//explosionSound = new AudioClip(ClassLoader.getSystemResource("Explosion.wav").toString());
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