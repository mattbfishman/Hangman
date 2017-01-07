/* Group B
 *  Makes the panel with the hangman graphics
 */
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;


public class HangmanGraphicsPanel  extends JPanel {

	private HangmanGraphic hangman;
	private int PANEL_WIDTH = 133;
	private int PANEL_HEIGHT = 200;
	
	public HangmanGraphicsPanel(){
		super();
		this.setPreferredSize(new java.awt.Dimension(PANEL_WIDTH, PANEL_HEIGHT));
		this.setSize(new java.awt.Dimension(PANEL_WIDTH, PANEL_HEIGHT));
		HangmanGraphicsPanel hangmanGraphicsPanel = this;
		 hangman = new HangmanGraphic(PANEL_HEIGHT/3, PANEL_WIDTH/4 , 50, this);
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D brush = (Graphics2D) g;
		hangman.draw(brush);
	}
}
