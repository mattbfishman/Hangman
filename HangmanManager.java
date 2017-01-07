/* Group B
 *  This class gets the word from the hashmap
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HangmanManager {
	Boolean playerDifficulty;
	int wordLength = 1;
	int numGuesses;
	ArrayList<String> guesses = new ArrayList<String>();
	Dictionary myBook;
	String secretWord;
	String guessesMade;
	int guessesUsed = 0;
	
	
	public HangmanManager(Boolean playerDifficulty, int wordLength, int numGuesses){
		this.playerDifficulty = playerDifficulty;
		this.wordLength = wordLength;
		this.numGuesses = numGuesses;
		myBook = new Dictionary("dictionary.txt");
		this.newSecretWord();
	}//end basic constructor
	
	public Boolean guess(String letter){// a method for determining if a letter is in the secret word string 
		letter = letter .trim();
		if (guessesUsed >= numGuesses) return false;
		else {
		if (guessesMade.contains(letter)){
		return true;		
		}
		if (secretWord.contains(letter)){
			guessesUsed++;
			return true;
		}
		else {
			guessesUsed++;
			return false;
		}
	}
	}// end guesses used
	
	public void prepForRound(int wordLength, int numGuesses, Boolean playerDifficulty){
		this.playerDifficulty = playerDifficulty;
		this.wordLength = wordLength;
		this.numGuesses = numGuesses;
	}//prep for round
	
	
	
	public String getGuessesMade(){
		return guessesMade;
	}

	
	public String newSecretWord(){
		Collections.shuffle((List<?>) myBook.getMap().get(HangmanMenu.getplayerLength()));
		secretWord = myBook.getFirstWord(HangmanMenu.getplayerLength());
		if (vowelCount(secretWord) == playerDifficulty){
			secretWord = secretWord;
		}
		else {
			this.newSecretWord();
		}
		//System.out.println(secretWord);
		return secretWord;
	}

	public String getSecretWord(){
		return secretWord;
	}//getsecret
	
	public boolean vowelCount(String givenWord){ //easy word true, hard word false
		double counter = 0;
		for (int i = 0; i < HangmanMenu.getplayerLength(); i++){
			if ((givenWord.charAt(i) == 'a') || (givenWord.charAt(i) == 'e') || (givenWord.charAt(i) == 'i') || (givenWord.charAt(i) == 'o') || (givenWord.charAt(i) == 'u')) {
				counter++;
			}
		}//end for
		double vowelRatio = (counter/HangmanMenu.getplayerLength());
		if (vowelRatio <= .50) {
			System.out.println("false");
			return false;
		}
		else{
			System.out.println("true");
			return true;
		}
	}//end vowelCount
	

	
	
}
