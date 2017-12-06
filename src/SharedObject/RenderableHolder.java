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
	public static Image spite;
	public static Image spite1;
	public static AudioClip  explosionSound;

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
		String sprite = "file:Resources/spite/sprite.png";
		String sprite1 = "file:Resources/spite/sprite1.png";
		logo = new Image(Logo);
		bgs = new Image(BGscroll);
		bg1 = new Image(BG1);
		bg2 = new Image(BG2);
		spite = new Image(sprite);
		spite1 = new Image(sprite1);
		
		
		
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