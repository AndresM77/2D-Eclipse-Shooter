package DodgeThis;
//BEFORE YOU START PLAYING MAKE SURE YOU HIT ECLIPSE AND THEN THE GUI AGAIN
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent; 
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.JFrame;

public class DodgeThis extends Canvas implements Runnable, KeyListener {
	
	private static DodgeThis INSTANCE;
	
	private static final long serialVersionUID = 1L;
	private static final int WIDTH = 600;
	private static final int HEIGHT = 600;
	
	private Thread thread;
	private BufferStrategy bs = null;
	private Graphics graphics = null;
	
	private boolean running = false;
	private boolean start = false;
	
	private Player player1;
	private Player player2;
	private boolean gameOver = false;
	private int winner;
	
	private List<Bullet> bullets1 = new CopyOnWriteArrayList<Bullet>();
	private List<Bullet> bullets2 = new CopyOnWriteArrayList<Bullet>();
	
	public DodgeThis() {
		JFrame frame = new JFrame("Dodge This!");
		frame.setSize(WIDTH, HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		
		// setting up
		new BasicCache();
		
		player1 = new Player(10, 150, 20, 90, BasicCache.player1);
		player2 = new Player(570, 150, 20, 90, BasicCache.player2);
		
		
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
			player1.update();
			player2.update();
			
			for(Bullet bullet : bullets1) {
				bullet.update();
			}
			for(Bullet bullet : bullets2) {
				bullet.update();
			}
		}
		
		if(player1.health <= 0) {
			//player 2 has won
			winner = 2;
			gameOver = true;
		}
		if(player2.health <= 0) {
			//player 1 has won
			winner = 1;
			gameOver = true;
		}
		
	}
	
	public void render() {
		graphics.drawImage(BasicCache.background, 0, 0, WIDTH, HEIGHT, null);
		
		graphics.setColor(Color.white);
		graphics.drawString("Health: " + player1.health, 25, 50);
		graphics.drawString("Health: " + player2.health, 520, 50);
		
		//graphics.drawString("Bullets: " + bullets1.size(), 50, 30);
		//graphics.drawString("Bullets: " + bullets2.size(), 100, 30);
		
		if(gameOver) {
			graphics.drawString("Grame Over", 250, 60);
			graphics.drawString("Player " + winner +" has won!", 250, 75);
			
			graphics.drawString("Press escape to reset the game", 250, 30);
		}
		
		player1.draw(graphics);
		player2.draw(graphics);
		
		for(Bullet bullet : bullets1) {
			bullet.draw(graphics);
		}
		for(Bullet bullet : bullets2) {
			bullet.draw(graphics);
		}
		
		if(!start) {
			//graphics.setColor(Color.black);
			//graphics.clearRect(0, 0, WIDTH, HEIGHT);
			//graphics.setColor(Color.black);
			//graphics.fillRect(0, 0, WIDTH, HEIGHT);
			graphics.drawImage(BasicCache.introduction, 0, 0, WIDTH, HEIGHT, null);
			//graphics.setColor(Color.red);
			//graphics.drawString("Welcome to Dodge This!", 50, 50);
			graphics.setColor(Color.white);
			graphics.drawString("This is a competitive multiplayer game, the first person to reduce", 50, 100);
			graphics.drawString("their oppenents health to 0 wins!", 50, 115);
			graphics.drawString("Player 1 Instructions: use W to move up, S to move down, and space to shoot.", 50, 150);
			graphics.drawString("Player 2 Instructions: use up key to move up, down key to move down, and enter to shoot.", 50, 200);
			graphics.drawString("Press P to start!", 50, 250);
		}
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
	
	public static void main(String[] args) {
		INSTANCE = new DodgeThis();
	}
	


	@Override
	public void keyPressed(KeyEvent e) {
		if(!gameOver) {
			if(e.getKeyCode() == KeyEvent.VK_W) {
				player1.up = true;
			} else if(e.getKeyCode() == KeyEvent.VK_S) {
				player1.down = true;
			}
			
			if(e.getKeyCode() == KeyEvent.VK_UP) {
				player2.up = true;
			} else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
				player2.down = true;
			}
		} else {
			//game reset
			if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
				resetGame();
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
		
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			player2.up = false;
		} else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			player2.down = false;
		}
		
		
			if(!gameOver) {
				if(bullets1.size() < 6) { //limits number of bullets
					//player 1 bullet
					if(e.getKeyCode() == KeyEvent.VK_SPACE) {
					Bullet bullet1 = new Bullet(player1.x + player1.width, player1.y +player1.height/2, 6, 4,BasicCache.bullet1);
					bullet1.deltaX = 6;
					
					
					bullets1.add(bullet1);
					}
				}
		
				if(bullets2.size() < 6) { //limits number of bullets
					//player 2 bullet
					if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					Bullet bullet2 = new Bullet(player2.x-15 + player2.width, player2.y +player1.height/2, 6, 4,BasicCache.bullet2);
					bullet2.deltaX = -6;
						
					bullets2.add(bullet2);
					}
				}
			}
		
		
		if(!start) {
			if(e.getKeyCode() == KeyEvent.VK_P) {
				start = true;
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}
	
	public void resetGame() {
		player1.reset();
		player2.reset();
		
		bullets1.clear();
		bullets2.clear();
		gameOver = false;
	}
	
	public Player getPlayer1() {
		return player1;
	}
	
	public Player getPlayer2() {
		return player2;
	}
	
	public List<Bullet> getBullets1() {
		return bullets1;
	}
	
	public List<Bullet> getBullets2() {
		return bullets2;
	}
	
	public static DodgeThis getInstance() {
		return INSTANCE;
	}
}
