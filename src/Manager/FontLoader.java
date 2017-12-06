package Manager;

import java.io.File;

import GameState.FileinputStream;
import javafx.scene.text.Font;

public class FontLoader {
	Font f = Font.createFont(Font.TRUETYPE_FONT,new FileinputStream(new File (""))).deriveFont(Font.PLAIN,24);
}
