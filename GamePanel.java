/* Group B
 * This class is the panel where the player plays the game
 */
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;


public class GamePanel extends JPanel {
	
	private final JButton smartHangMan;
	private JButton letter;
	private final JLabel guessesLabel;
	private JLabel underscores;
	private JLabel lossLabel;
	private int PANEL_WIDTH = 400;
	private int PANEL_HEIGHT = 600;
	char[] alpha = new char[26];
	private HangmanGraphicsPanel hangmanGraphics = new HangmanGraphicsPanel();
	private String word;
	private int guesses = 0;
	private int length = 0;
	private boolean gameOver = false;
	private boolean correct = false;
	ArrayList<JButton> letterButtons = new ArrayList<JButton>();
	static int serverOpener = 0;
	public serverRunner serverThread;
	public clientRunner clientThread;
	public GamePanel(){
		super();
		
		setLayout(new BorderLayout());
		
		//gets the word, difficulty and guesses
		HangmanManager manager = new HangmanManager(HangmanMenu.getDifficulty(), length, guesses);
		word = manager.getSecretWord();
		
		//If the game has not been started before, here it starts for the first time.
		if (serverOpener == 0){
		serverThread = new serverRunner(word);
		clientThread = new clientRunner("127.0.0.1");
		serverThread.start();
		clientThread.start();		
		serverOpener++;
		} //end if
		
		//Below is the code that restarts the game and servers.
		else { 
		serverThread = new serverRunner(word);
		clientThread = new clientRunner("127.0.01");
		serverThread.getServer().resetGame();
		clientThread.getClient().resetGame();
		serverThread.getServer().sendData("The Set Options are...");
		serverThread.getServer().sendData("Length of Word: " + HangmanMenu.getplayerLength());
		serverThread.getServer().sendData("Number of Guesses: " + HangmanMenu.getGameGuesses());
		
		if (HangmanMenu.getDifficulty() == true){
			serverThread.getServer().sendData("Difficulty: Easy");
		}//end if
		else {
			serverThread.getServer().sendData("Difficulty: Hard");
		}//end else
		
		
		} //end reset else
		
		
		System.out.println(word);
		JPanel lossPanel = new JPanel();
		lossLabel = new JLabel("You lost a ___________", SwingConstants.CENTER);
		lossLabel.setFont(new Font("Serif", Font.PLAIN,28));
		lossLabel.setVisible(false);
		lossPanel.add(lossLabel);
		
		
		//panel for the left side of the gamePanel
		JPanel gameSide = new JPanel();
		GridLayout sideLayout = new GridLayout(3,1);
		gameSide.setLayout(sideLayout);
		length = HangmanMenu.getplayerLength();
		underscores = new JLabel("", SwingConstants.CENTER);
		underscores.setFont(new Font("Serif", Font.BOLD,14));
		for (int i = 1; i <= length; i++){
			
		underscores.setText(underscores.getText() + "_ ");
		}
		//System.out.println(underscores);
		//for loop to draw the underscores for the number of letters the words has. 
		gameSide.add(lossPanel);
		JPanel hangman = new JPanel();
		//add hangman graphics
		hangman.add(hangmanGraphics);
		hangman.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		gameSide.add(hangman);
		gameSide.add(underscores);
		
		
		
		//right side panels
		JPanel panel = new JPanel();
		GridLayout panelGrid = new GridLayout(2,1);
		//panelGrid.setVgap(10);
		panel.setLayout(panelGrid);
		guesses = HangmanMenu.getGameGuesses();
		guessesLabel = new JLabel("Guesses Remaining : " + guesses, SwingConstants.CENTER);
		guessesLabel.setFont(new Font("Serif", Font.PLAIN,28));
		panel.add(guessesLabel);
		
		
		
		
	    
		

		JPanel lettersPanel = new JPanel();
		GridLayout letterGrid = new GridLayout(6,5);
		//sets up the letter buttons
		lettersPanel.setLayout(letterGrid);
		int k = 0;
		for(int i = 0; i < 26; i++){
			alpha[i] = (char)(97 + (k++));
			String tempLetter =  "" + alpha[i];
			letter = new JButton(tempLetter);
			letter.setFont(new Font("Serif", Font.PLAIN,20));
			//System.out.println(alpha[i]);
			letterButtons.add(letter);
			lettersPanel.add(letter);	 
		}
		//disables the letter after it is clicked and checks the guess compared to the secret word
		panel.add(lettersPanel);
		for(final JButton l: letterButtons){
		l.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				l.setEnabled(false);
				clientThread.getClient().guess(l.getText());
				correct = false;
				if(gameOver == false){
					//System.out.println(word.length());
					//System.out.println(l.getText().trim());
					char letter = l.getText().charAt(0);
					//System.out.println(letter);
					for(int i = 0; i < word.length(); i++){
						if(letter == word.charAt(i)){
							correct = true;
							String temp = underscores.getText();
							temp = temp.substring(0, 2*i) + letter + temp.substring(2*i+1);
							underscores.setText(temp);
							//System.out.println(guesses);
							serverThread.getServer().sendData("Guess Was True");
						}
					}
				}
				if(guesses != 0 && correct == false){
					System.out.println(guesses);
					guesses--;
					guessesLabel.setText("Guesses Remaining : " + guesses);
					System.out.println(guesses);
					HangmanMenu.setGameGuesses(guesses);
					repaint();
					serverThread.getServer().sendData("Guess Was Incorrect");
				}
				if (underscores.getText().replaceAll("\\s+","").contains(word)){
					gameOver = true;
					guessesLabel.setText("YOU WIN!");
					serverThread.getServer().sendData("Game Has Ended");
					disableLetters();
					smartHangMan.setText("Restart?");
					smartHangMan.setEnabled(true);
					
					}
				//checks win	
				else if(guesses == 0){
						gameOver = true;
						guessesLabel.setText("Game Over");
						serverThread.getServer().sendData("Game Has Ended");
						disableLetters();
						smartHangMan.setText("Restart?");
						smartHangMan.setEnabled(true);
						
						}
				}
		});
		
		
		
		}
		
		
		
		//Jbutton at the time for the game title
		smartHangMan = new JButton("Smart Hangman");
		smartHangMan.setFont(new Font("Serif", Font.PLAIN,28));
		smartHangMan.setPreferredSize(new Dimension(200,75));
		smartHangMan.setEnabled(false);
		panel.setPreferredSize(new Dimension(PANEL_WIDTH,PANEL_HEIGHT));
		gameSide.setPreferredSize(new Dimension(PANEL_WIDTH,PANEL_HEIGHT));
		this.add(gameSide, BorderLayout.WEST);
		this.add(smartHangMan,BorderLayout.NORTH);
		this.add(panel, BorderLayout.EAST);
		
		smartHangMan.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				HangmanMenu.setGameState(0);
				int temp;
				temp = HangmanMenu.getGameState();
				System.out.println(HangmanMenu.getGameState());
			
				
			}
		});
		
		
		
	
}//end constructor
	//disbles the letter
	public void disableLetters(){
		for(JButton l: letterButtons){
			l.setEnabled(false);
		}
	}
}//end class
