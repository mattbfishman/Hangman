import javax.swing.JFrame;
/* Group B
 *  this class is the main driver for the class
 */


public class GameDriver extends JFrame {
	
	public GameDriver(){
		super("Hangman Game");
		while(true){
		this.setVisible(true);
		HangmanMenu menu = new HangmanMenu();
		this.add(menu);
		this.pack();	
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setSize(400,600);;
		
		while(menu.getGameState() == 0){
			this.setVisible(true);
			menu.setVisible(true);
		}
		GamePanel game = new GamePanel();
		this.setSize(800,1200);
		this.add(game);
		game.setVisible(false);
		while(menu.getGameState() == 1){
			game.setVisible(true);
			this.remove(menu);
		}
		
		this.setVisible(true);
		this.remove(game);
		}
	}
	public static void main(String[] args){
		new GameDriver();
	}
}
