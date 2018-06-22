package Project;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JFrame;

public class Shooter extends JFrame {

	private static final long serialVersionUID = 1L;
	private Player player1;
	private Player player2;
	private Image image;
	private Graphics graphics;
	
	public Shooter() {
		setTitle("Dodge This");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600,400);
		setBackground(Color.BLACK);
		setResizable(false);
		
		setVisible(true);
		
		player1 = new Player(10, 150, 15, 30, 10, "Images/ThePlayer1.gif");
	}
	
	public void paint(Graphics g) {
		//g.clearRect(0, 0, getWidth(), getHeight());
		//g.setColor(Color.WHITE);
		//g.drawString("HI", 50, 50);
		
		image = createImage(getWidth(), getHeight());
		graphics = image.getGraphics();
	
		paintComponent(graphics);
		g.drawImage(image, 0, 0, null);
		repaint();
		
	}
	
	public void paintComponent(Graphics g) {
		player1.draw(g);
	}
	
	public Player getPlayer1() {
		return player1;
	}

	public void setPlayer1(Player player1) {
		this.player1 = player1;
	}

	public Player getPlayer2() {
		return player2;
	}

	public void setPlayer2(Player player2) {
		this.player2 = player2;
	}
	
	public static void main(String[] args) {
		//allows program to appear
		new Shooter();
	}

}
