package Slasher;

import java.awt.Graphics;
import java.awt.Image;

public class Slime extends GameObject {
	
	private static final int SPEED = 3;
	private static final int START_HEALTH = 5;
	
	public boolean up;
	public boolean down;
	public int health;
	public int startX;
	public int startY;
	
	public Slime(int x, int y, int width, int height, Image img) {
		super(x, y, width, height, img); 
		
		this.startX = x;
		this.startY = y;
		this.health = START_HEALTH;
	}
	
	@Override
	public void update() {
		if(Slasher.getInstance().getPlayer1().y > y) {
			y += SPEED;
			rect.y += SPEED;
		} else {
			y -= SPEED;
			rect.y -= SPEED;
		}
		
		if(Slasher.getInstance().getPlayer1().x > x) {
			x += SPEED;
			rect.x += SPEED;
		} else {
			x -= SPEED;
			rect.x -= SPEED;
		}
		
		if(Slasher.getInstance().getPlayer1().rect.contains(this.rect)) {
			// Player 1
			Slasher.getInstance().getPlayer1().health--;
			
			removeSlime();	
		} 
		//if(this.rect.contains(Slasher.getInstance().getBullets1().rect)) {
			
		//}
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(img, x, y, width, height, null);
	}
	
	private void removeSlime() {
		Slasher.getInstance().getSlimes().remove(this);
	}
	
	public void reset() {
		this.x = startX;
		this.y = startY;
		this.rect.x = startX;
		this.rect.y = startY;
		this.health = START_HEALTH;
	}

}
