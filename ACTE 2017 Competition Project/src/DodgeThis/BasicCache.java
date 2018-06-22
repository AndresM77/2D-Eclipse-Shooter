package DodgeThis;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BasicCache {
	
	private static final String IMAGE_DIR = "images/";
	public static Image player1;
	public static Image player2;
	public static Image bullet1;
	public static Image bullet2;
	public static Image background;
	public static Image introduction;
	
	public BasicCache() {
		load();
	}
	
	public void load() {
		BasicCache.player1 = loadImage("player1.png");
		BasicCache.player2 = loadImage("player2.png"); //add in second image when made
		BasicCache.bullet1 = loadImage("bullet2.png");
		BasicCache.bullet2 = loadImage("bullet1.png");
		BasicCache.background = loadImage("background.png");
		BasicCache.introduction = loadImage("introduction (1).png");
	}
	
	private Image loadImage(String img) {
		try {
			return ImageIO.read(new File(IMAGE_DIR + img));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
}
