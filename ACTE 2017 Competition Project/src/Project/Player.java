package Project;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class Player extends GameObject{

	/**
	 * Create a player
	 * @param xPos x position
	 * @param yPos y position
	 * @param img image of player
	 * @param height height of player
	 * @param width width of player
	 * @param health health of player
	 */
	public Player (int xPos, int yPos, int width, int height, int health, String img) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.height = height;
		this.width = width;
		this.img = getImage(img);
		this.health = health;
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
}
