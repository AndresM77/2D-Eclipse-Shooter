package Project;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class Bullet extends GameObject {
	
	private int deltaX;
	
	
	public Bullet (int xPos, int yPos, int width, int height, String img) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.height = height;
		this.width = width;
		this.img = getImage(img);
	}
	
	@Override
	public void draw(Graphics g) {
		g.drawImage(img, xPos, yPos, width, height, null);
	}

	
	@Override
	void update() {
		
	}
	
	@Override
	Image getImage(String img) {
		return Toolkit.getDefaultToolkit().getImage(img);
	}

	public int getDeltaX() {
		return deltaX;
	}

	public void setDeltaX(int deltaX) {
		this.deltaX = deltaX;
	}
}
