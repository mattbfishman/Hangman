import javax.swing.JFrame;


class serverRunner extends Thread {
	public static String secretWord;
	public static HangmanServer gameServer;
	
	public serverRunner(String secretWord){
		this.secretWord = secretWord;
	}
	public void run(){
		gameServer = new HangmanServer(secretWord);
		gameServer.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
	    gameServer.runServer(); // run server application
		System.out.println(secretWord);
		}
	public HangmanServer getServer(){
		return gameServer;
	}
	
	}



class clientRunner extends Thread {
	private static String socketInt; 
	public static HangmanClient gameClient;
	
	public clientRunner(String socketInt){
		this.socketInt = socketInt;
	}
	public void run(){
			gameClient = new HangmanClient(socketInt);
		    gameClient.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		    gameClient.runClient(); // run client application
			}
	public HangmanClient getClient(){
		return gameClient;
	}
	
} 

