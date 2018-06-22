package DodgeThis;

import java.awt.Graphics;
import java.awt.Image;

public class Bullet extends GameObject{

	public int deltaX;
	
	public Bullet(int x, int y, int width, int height, Image img) {
		super(x, y, width, height, img);
	}

	@Override
	public void update() {
		x += deltaX;
		rect.x += deltaX;
		
		if(DodgeThis.getInstance().getPlayer1().rect.contains(this.rect)) {
			// Player 1
			DodgeThis.getInstance().getPlayer1().health--;
			
			removeBullet2();	
		} else if(DodgeThis.getInstance().getPlayer2().rect.contains(this.rect)){
			// Player 2
			DodgeThis.getInstance().getPlayer2().health--;
			
			removeBullet1();		}
		
		
		if(x > DodgeThis.getInstance().getWidth() + 50) {
				removeBullet1();
				removeBullet2();
		} else if (x < -50) { //player 2 shooting
				removeBullet2();
				removeBullet1();
		}
			
		
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(img, x, y, width, height, null);
	}
	
	private void removeBullet1() {
		DodgeThis.getInstance().getBullets1().remove(this);
	}
	
	private void removeBullet2() {
		DodgeThis.getInstance().getBullets2().remove(this);
	}
}
