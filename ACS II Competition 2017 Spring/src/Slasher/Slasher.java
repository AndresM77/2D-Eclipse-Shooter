package Slasher;

import javax.swing.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.Timer;

public class Slasher extends Canvas implements Runnable, KeyListener {
	private static final long serialVersionUID = 1L;
	
	final private int WIDTH = 600;
	final private int HEIGHT = 600;
	
	private Thread thread;
	private BufferStrategy bs = null;
	private Graphics graphics = null;
	
	private Player player1;
	
	private boolean running = false;
	
	private boolean gameOver = false;
	
	private List<Bullet> bullets1 = new CopyOnWriteArrayList<Bullet>();
	private List<Bullet> bullets2 = new CopyOnWriteArrayList<Bullet>();
	private List<Slime> slimes = new CopyOnWriteArrayList<Slime>();
	
	public static Slasher INSTANCE;
	
	public Slasher() {
		JFrame frame = new JFrame("Slasher!");
		frame.setSize(WIDTH, HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		
		new BasicCache();
		
		player1 = new Player(10, 150, 20, 90, BasicCache.player1);
		
		frame.add(this);
		
		thread = new Thread(this);
		
		frame.setBackground(Color.black);
		
		frame.setVisible(true);
		
		addKeyListener(this);
	}
	
	public void paint(Graphics g) {
		if(bs == null) {
			createBufferStrategy(2);
			bs = getBufferStrategy();
			graphics = bs.getDrawGraphics();
			
			thread.start();
			running = true;
		}
	}
	
	public void update() {
		if(!gameOver) {
			
		}
	}
	
	public void render() {
		player1.draw(graphics);
		
	}
	
	@Override
	public void run() {
		//Game loop
		while(running) {
			update();
			render();
			
			bs.show();
			
			Thread.currentThread();
			try {
				Thread.sleep(10);
			} catch(InterruptedException e) {
				
			}
		}
	}
	
	public void resetGame() {
		player1.reset();
		slimes.clear();
		
		bullets1.clear();
		bullets2.clear();
		gameOver = false;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(!gameOver) {
			if(e.getKeyCode() == KeyEvent.VK_W) {
				player1.up = true;
			} else if(e.getKeyCode() == KeyEvent.VK_S) {
				player1.down = true;
			}
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_W) {
			player1.up = false;
		} else if(e.getKeyCode() == KeyEvent.VK_S) {
			player1.down = false;
		}
		
		if(!gameOver) {
			if(bullets1.size() < 6) { //limits number of bullets
				//player 1 bullet
				if(e.getKeyCode() == KeyEvent.VK_SPACE) {
				Bullet bullet1 = new Bullet(player1.x + player1.width, player1.y +player1.height/2, 6, 4,BasicCache.bullets);
				bullet1.deltaX = 6;
				
				
				bullets1.add(bullet1);
				}
			}
	
			if(bullets2.size() < 6) { //limits number of bullets
				//player 2 bullet
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
				Bullet bullet2 = new Bullet(player1.x-15 + player1.width, player1.y +player1.height/2, 6, 4,BasicCache.bullets);
				bullet2.deltaX = -6;
					
				bullets2.add(bullet2);
				}
			}
		}
		
	}
	
	public static Slasher getInstance() {
		return INSTANCE;
	}
	
	public Player getPlayer1() {
		return player1;
	}
	
	public static void main(String args[]) {
		INSTANCE = new Slasher();
	}

	public List<Bullet> getBullets1() {
		return bullets1;
	}
	public List<Bullet> getBullets2() {
		return bullets2;
	}

	public List<Slime> getSlimes() {
		return slimes;
	}

}
