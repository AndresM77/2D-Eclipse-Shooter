package Slasher;


import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BasicCache {
	
	private static final String IMAGE_DIR = "images/";
	public static Image player1;
	public static Image bullets;
	 
	public BasicCache() {
		load();
	}
	
	public void load() {
		BasicCache.player1 = loadImage("player1 (1).png");
		BasicCache.bullets = loadImage("bulet2 (1).png");
		
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
