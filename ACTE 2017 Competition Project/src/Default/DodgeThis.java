package Default;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
public class DodgeThis extends JFrame implements ActionListener{
	//serial ID of the program
	public JButton startButton = new JButton("Start Game");
	private static final long serialVersionUID = 1L;
	//Runs the program
	public static void main(String[] args) {
		new DodgeThis().setVisible(true);
	}
	
	//Constructor which creates the window
	public DodgeThis() {
		super("DodgeThis");
		setSize(600,600);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		//Layout which best fits the function of this program
		
		
		//setting action command for startButton
		startButton.setActionCommand("start");
		startButton.addActionListener(this);
		
		add(startButton, BorderLayout.SOUTH);
		Game();
	}
	
	public void Game(){
		//creating items for JMenu
		JMenuBar bar = new JMenuBar();
		JMenu file = new JMenu("File");
		JMenuItem newMenuItem = new JMenuItem("New");
		JMenuItem save = new JMenuItem("Save");
		JMenuItem close = new JMenuItem("Exit");
		close.addActionListener(this);
		
		
	
		//adding the items under file
		file.add(newMenuItem);
		file.add(save);
		file.add(close);
		file.addSeparator();
		file.add(close);
		
		bar.add(file);
		//makes the bar visible
		setJMenuBar(bar);
		System.out.println("The game has been run!");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//retrieves the ActionCommand of the button to identify it
		String name  = e.getActionCommand();
		//commands for each separate button
		if(name.equals("start")) {
			System.out.println("The game has been started!");
			
		} else if (name.equals("Exit")){
			//closes program
			System.out.println("Closed");
			System.exit(0);
		} else {
			System.out.println("This input is unidentified.");
		}
		
	}

}
