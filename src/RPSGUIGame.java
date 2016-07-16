import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/*
 * Andrew Chow
 * CS 110B
 * 7/14/2016
 * The purpose of this GUI program is to represent a rock-paper-scissors game with images
 * and allow the user to play by inputting either rock ,paper or scissors using buttons.
 * The user is also able to bet money if she/he chooses so. The gui updates every round of play
 * talling wins/losses/ties
 */

public class RPSGUIGame extends JFrame {

	// buttons for the user to enter their move
	private JButton rockButton, paperButton, scissorsButton;

	// labels to display the number of wins/losses/ties
	private JLabel statusC, statusU, statusT, statusB;

	// images and labels to display the computer and user's moves and the outcome of a match-up
	private ImageIcon rockImage, paperImage, scissorsImage;
	private JLabel compPlay, userPlay;
	private JLabel outcome;
	// Bet confirmationa after user says 
	private int confirm;
	
	// the game object
	private RPSGame game;

	public RPSGUIGame() {

		// initializes the window
		super("Rock, Paper, Scissors, Go!");
		setSize(350, 300);
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());
		contentPane.setBackground(Color.black);
		setResizable(false);

		// creates the game object
		
		confirm = JOptionPane.showConfirmDialog(null,"Do you want to bet?", "Query..", JOptionPane.YES_NO_OPTION);		
		if(confirm == 1)
			game = new RPSGame();	
		
		else if(confirm == 0)
		{	

			String betMoney = JOptionPane.showInputDialog("How much money do you have?");
			String betAmount = JOptionPane.showInputDialog("How much money bet each round?");		
			game = new RPSGame(Integer.parseInt(betMoney),Integer.parseInt(betAmount));	
			
		}
		
		// creates the labels for displaying the computer and user's move;
		// the images for the moves and the outcome of a match-up will be displayed
		// in a single panel
		rockImage = new ImageIcon("rock.jpg");
		paperImage = new ImageIcon("paper.jpg");
		scissorsImage = new ImageIcon("scissors.jpg");

		compPlay = new JLabel(rockImage);
		compPlay.setVerticalTextPosition(SwingConstants.BOTTOM);
		compPlay.setHorizontalTextPosition(SwingConstants.CENTER);
		compPlay.setBorder(BorderFactory.createLineBorder(Color.black, 5));
		compPlay.setForeground(Color.cyan);
		
		userPlay = new JLabel(rockImage);
		userPlay.setVerticalTextPosition(SwingConstants.BOTTOM);
		userPlay.setHorizontalTextPosition(SwingConstants.CENTER);
		userPlay.setBorder(BorderFactory.createLineBorder(Color.black, 5));
		userPlay.setForeground(Color.cyan);
		
		outcome = new JLabel("");
		outcome.setHorizontalAlignment(SwingConstants.CENTER);
		outcome.setForeground(Color.white);
		
		JPanel imageOutcomePanel = new JPanel();
		imageOutcomePanel.setBackground(Color.black);
		imageOutcomePanel.setLayout(new BorderLayout());
		imageOutcomePanel.add(compPlay, BorderLayout.EAST);
		imageOutcomePanel.add(userPlay, BorderLayout.WEST);
		imageOutcomePanel.add(outcome, BorderLayout.SOUTH);
		
		// creates the labels for the status of the game (number of wins, losses, and ties);
		// the status labels will be displayed in a single panel
		statusC = new JLabel("Computer Wins: " + game.getCWins());
		statusU = new JLabel("User Wins: " + game.getUWins());
		statusT = new JLabel("Ties: " + game.getTies());		
		if (confirm == 0)
		{
		statusB = new JLabel("Bet Money: " + game.getBetMoney());
		statusB.setForeground(Color.white);
		}
		
		statusC.setForeground(Color.white);
		statusU.setForeground(Color.white);
		statusT.setForeground(Color.white);

		
		JPanel statusPanel = new JPanel();
		statusPanel.setBackground(Color.black);
		statusPanel.add(statusU);
		statusPanel.add(statusC);
		statusPanel.add(statusT);
		
		if (confirm == 0)
		statusPanel.add(statusB);

		// the play and status panels are nested in a single panel
		JPanel gamePanel = new JPanel();
		gamePanel.setPreferredSize(new Dimension(250, 250));
		gamePanel.setBackground(Color.white);
		gamePanel.add(imageOutcomePanel);
		gamePanel.add(statusPanel);
		
		// creates the buttons and displays them in a control panel;
		// one listener is used for all three buttons
		rockButton = new JButton("Play Rock");
		rockButton.addActionListener(new GameListener());
		paperButton = new JButton("Play Paper");
		paperButton.addActionListener(new GameListener());
		scissorsButton = new JButton("Play Scissors");
		scissorsButton.addActionListener(new GameListener());

		JPanel controlPanel = new JPanel();
		controlPanel.add(rockButton);
		controlPanel.add(paperButton);
		controlPanel.add(scissorsButton);
		controlPanel.setBackground(Color.white);

		// the gaming and control panel are added to the window
		contentPane.add(gamePanel, BorderLayout.CENTER);
		contentPane.add(controlPanel, BorderLayout.SOUTH);
		
		setVisible(true);
	}

	/* determines which button was clicked and updates the game accordingly */
	private class GameListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			// Keeps track of compMove for image purposes
			int compMove = game.generateComputerPlay();	
			String winnerString;
			
			//Each of these updates images/labels depending on user input and cpumove
			// Should I have done conditionals for updating the textlabels for wins/ties?
			if (event.getSource() == rockButton)
			{
				userPlay.setIcon(rockImage);
				winnerString = game.findWinner(0, compMove);
				outcome.setText(winnerString);
				if(confirm == 0)
					game.betResult(winnerString);
			}			
			if(event.getSource() == paperButton)
			{
				userPlay.setIcon(paperImage);
				winnerString = game.findWinner(1, compMove);
				outcome.setText(winnerString);
				if(confirm == 0)
					game.betResult(winnerString);
			}
			
			if (event.getSource() == scissorsButton)
			{
				userPlay.setIcon(scissorsImage);
				winnerString = game.findWinner(2, compMove);
				outcome.setText(winnerString);
				if(confirm == 0)
					game.betResult(winnerString);
			}		
			if(compMove == 0)
				compPlay.setIcon(rockImage);
			else if (compMove == 1)
				compPlay.setIcon(paperImage);			
			else if (compMove == 2)
				compPlay.setIcon(scissorsImage);		
			statusU.setText("User Wins: " + game.getUWins());
			statusC.setText("Computer Wins: " + game.getCWins());
			statusT.setText("Ties: " + game.getTies());
			
			if(confirm == 0)
			{
				if (game.getBetMoney() <= 0)
				{
					statusB.setText("Bet Money: " + game.getBetMoney());
					JOptionPane.showMessageDialog (null, "You ain't got no more money.", "Get outta here", JOptionPane.INFORMATION_MESSAGE);
					System.exit(0);
				}
				
				statusB.setText("Bet Money: " + game.getBetMoney());
			}
			
		}
	}

	
	public static void main(String args[]) {
		// create an object of your class
		// I don't know why the betting inplementation and the JOptions should be
		// in the main function, you already call the game class within RPSGUIGAME.
		
		RPSGUIGame frame = new RPSGUIGame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
