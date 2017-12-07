package Manager;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;

public class Animation {

	private List<Image> list;
	private long deltaTime;
	private int currentFrame = 0;
	private long previousTime;

	public Animation(int deltaTime) {
		this.deltaTime = deltaTime;
		list = new ArrayList<Image>();
		previousTime = 0;
	}

	public void updateFrame() {
		if (System.currentTimeMillis() - previousTime >= deltaTime) {
			currentFrame++;
			if (currentFrame >= list.size()) {
				currentFrame = 0;
			}
			previousTime = System.currentTimeMillis();
		}
	}
	public void resetFrame() {
		currentFrame=0;
	}

	public void addFrame(Image image) {
		list.add(image);
	}

	public Image getFrame() {
		return list.get(currentFrame);
	}

}
