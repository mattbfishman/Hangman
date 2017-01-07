/* Group B
 *  Makes the hangman menu at the start of the game
 */ 
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;

public class HangmanMenu extends JPanel {

	private final JButton smartHangMan, easyButton, hardButton;;
	private final JLabel guessesLabel, wordLengthLabel;
	private boolean gameOver = false;
	private static int gameState = 0;
	private static int playerLength = 0;
	private static int playerGuesses = 0;
	private static boolean easy = true;
	public HangmanMenu(){
		super();
		
		
		setLayout(new BorderLayout());
		
		//panel that makes the logo
		JPanel logoPanel = new JPanel();
		smartHangMan = new JButton("Smart Hangman");
		smartHangMan.setPreferredSize(new Dimension(300,150));
		smartHangMan.setFont(new Font("Serif", Font.PLAIN,28));
		smartHangMan.setEnabled(false);
		logoPanel.add(smartHangMan);
	
		//panel that makes the sliders
		JPanel sliderPanel = new JPanel();
		GridLayout sliderGrid = new GridLayout(4,1);
		sliderGrid.setVgap(10);
		sliderPanel.setLayout(sliderGrid);
		
		guessesLabel = new JLabel("Number of Guesses : ", SwingConstants.CENTER);
		guessesLabel.setFont(new Font("Serif", Font.PLAIN,24));
		sliderPanel.add(guessesLabel);
		final JSlider guesses = new JSlider(1,6);
		guesses.setMajorTickSpacing(1);
		guesses.setLabelTable(guesses.createStandardLabels(1));
		guesses.setPaintTicks(true);
		guesses.setPaintLabels(true);
		sliderPanel.add(guesses);
		wordLengthLabel = new JLabel("Length of Word : ", SwingConstants.CENTER);
		wordLengthLabel.setFont(new Font("Serif", Font.PLAIN,24));
		sliderPanel.add(wordLengthLabel);
		final JSlider wordLength = new JSlider(2,29);
		wordLength.setMajorTickSpacing(2);
		wordLength.setLabelTable(wordLength.createStandardLabels(2));
		wordLength.setPaintTicks(true);
		wordLength.setPaintLabels(true);
		sliderPanel.add(wordLength);
		
		//panel that makes the easy and hard buttons
		JPanel difficultyPanel = new JPanel();
		FlowLayout buttonFlow = new FlowLayout();
		buttonFlow.setHgap(25);
		buttonFlow.setVgap(25);
		difficultyPanel.setLayout(buttonFlow);
		easyButton = new JButton("Easy");
		easyButton.setPreferredSize(new Dimension(150,75));
		easyButton.setFont(new Font("Serif", Font.PLAIN,28));
		difficultyPanel.add(easyButton);
		hardButton = new JButton("Hard");
		hardButton.setPreferredSize(new Dimension(150,75));
		hardButton.setFont(new Font("Serif", Font.PLAIN,28));
		difficultyPanel.add(hardButton);
		
		
		this.add(logoPanel, BorderLayout.NORTH);
		this.add(sliderPanel, BorderLayout.CENTER);
		this.add(difficultyPanel, BorderLayout.SOUTH);
		
		
		easyButton.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent event){
			if(wordLength.getValue() != 27){
			setGameState(1);
			easy = true;
			playerLength = wordLength.getValue();
			//System.out.println(wordLength.getValue());
			playerGuesses = guesses.getValue();
			}
			else
			smartHangMan.setText("NOT 27!");
		}
	});
		
		hardButton.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent event){
			if(wordLength.getValue() != 27){
			setGameState(1);
			easy = false;
			playerLength = wordLength.getValue();
			playerGuesses = guesses.getValue();
			//System.out.println(playerGuesses);
			}
			else
			smartHangMan.setText("NOT 27!");
		}
	});
		
	}//end constructor 
	
	//static methods to get game state, word length, guesses
	public static int getGameState() {
		return gameState;
	}
	public static void setGameState(int gameState) {
		HangmanMenu.gameState = gameState;
	}
	
	public static int getGameGuesses() {
		return playerGuesses;
	}
	public static void setGameGuesses(int playerGuesses) {
		HangmanMenu.playerGuesses = playerGuesses;
	}
	
	public static int getplayerLength() {
		return playerLength;
	}
	public static boolean getDifficulty() {
		return easy;
	}
}//end class
