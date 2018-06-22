package DodgeThis;

import java.awt.Graphics;
import java.awt.Image;

public class Player extends GameObject{
	
	private static final int SPEED = 5;
	private static final int START_HEALTH = 10;
	
	public boolean up;
	public boolean down;
	public int health;
	public int startX;
	public int startY;

	public Player(int x, int y, int width, int height, Image img) {
		super(x, y, width, height, img);
		
		this.startX = x;
		this.startY = y;
		this.health = START_HEALTH;
	}

	@Override
	public void update() {
		if (up) {
			y -= SPEED;
			rect.y -= SPEED;
		}
		
		if (down) {
			y += SPEED;
			rect.y += SPEED;
		}
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(img, x, y, width, height, null);
	}
	
	public void reset() {
		this.x = startX;
		this.y = startY;
		this.rect.x = startX;
		this.rect.y = startY;
		this.health = START_HEALTH;
	}

}
