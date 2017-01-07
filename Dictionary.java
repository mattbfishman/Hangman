import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Collections;

//The Dictionary Class By Group B
//The Main Purpose of this class is to load in a txt file and generate a hashmap with key values assigned to arraylists.
//Each array list contains all words in the txt file of a certain length.
//This assists with the hashmanager class.
public class Dictionary {

	private String readWord;
	private String readLineDo;
	private Scanner webster;
	public int wordLength;
	public HashMap<Integer, ArrayList<String>> myMap;
	private int keyVal;
	private String arrayName;
	

	
	public Dictionary(String fileName){
		System.out.println("Attempting to load the File.");
      myMap = new HashMap();
      
      for(int i= 1; i <=40; i++ ){
  		myMap.put(i, new ArrayList<String>() );
  	} //creates 40 empty arrays for words of length up to 40. If your game has words longer than 40 letters, im not playing with you.
		this.load(fileName);
    	
    	
	
    	
    	
	} //end of dictionary constructor
	
	public void load(String input){
    	File inFile = new File(input.toString());
    	try {
        	System.out.println("testing the try");
             webster = new Scanner(inFile);
            while (webster.hasNextLine()) { //while there is still more file to read
            	 readWord = webster.nextLine(); 
            	 wordLength = readWord.length(); //reads the length of the word.
            	 myMap.get(wordLength).add(readWord); //adds the word to the appropriate array.
             }
            System.out.println(myMap.size()); //debugging tool for the console.
    	} // end try
    	catch(FileNotFoundException e) { //file not found, debug line.
    		System.out.println(e);
    		System.out.println("OOPS");
    		System.exit(1);
    	}
	} //end load
	
	public void printAll(int bucketNum){ //prints all the words in a single bucket, useful for testing.
		for (String printFor: myMap.get(bucketNum)){
			System.out.println(printFor);
		}
	}//end printAll
	
	public ArrayList<String> getWordList(int numLetters){  
		return myMap.get(numLetters); //returns the entire array list of certain word lengths. not necessary but could simplify certain code.
	}
	
	
	
	public HashMap getMap(){
		return myMap;
	}
	
	public String getFirstWord(int number){
		return myMap.get(number).get(1);
	}
	
	public boolean vowelCount(String givenWord){ //easy word true, hard word false
		int tempSize = givenWord.length();
		int counter = 0;
		for (int i = 0; i < givenWord.length(); i++){
			if ((givenWord.charAt(i) == 'a') || (givenWord.charAt(i) == 'e') || (givenWord.charAt(i) == i) || (givenWord.charAt(i) == 'o') || (givenWord.charAt(i) == 'u')) {
				counter++;
			}
		}//end for
		double vowelRatio = (counter/givenWord.length());
		if (vowelRatio <= .50) return false;
		else return true;
	}//end vowelCount
	
	
}
