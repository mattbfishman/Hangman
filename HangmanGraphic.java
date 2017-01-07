/* Group B
 * class that makes the hangman graphics
 */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;


public class HangmanGraphic {
	
	private int x, y;
	private int width;
	HangmanGraphicsPanel panel;
	public HangmanGraphic(int x, int y, int width, HangmanGraphicsPanel panel){
		this.x = x;
		this.y = y;
		this.width = width;
		this.panel = panel;
		
		
	}
	//makes the body based on the guesses 
	public void makeBody(Graphics g){
		int guesses = HangmanMenu.getGameGuesses();
		if(guesses <= 5){
			g.drawOval((int)x, (int)y, (int) width/3, (int) width/2);
		}
		if(guesses <= 4){
		g.drawLine(74,58,74,121);
		}
		if(guesses <= 3){
		g.drawLine(74, 90, 54, 64);
		}
		if(guesses <= 2){
		g.drawLine(74, 90, 94, 64);
		}
		if(guesses <= 1){
		g.drawLine(74,121,59,140);
		}
		if(guesses <= 0){
		g.drawLine(74,121,89,140);
		}
	}
	
	//makes everything but the person 
	public void makeHang(Graphics g){
		g.drawLine(74,31,74,15);
		g.drawLine(72, 15, 20, 15);
		g.drawLine(20,15,20,170);
		g.drawLine(5,171,35,171);
	}
	//draws the components
	public void draw(Graphics g) {
		this.makeBody(g);
		this.makeHang(g);
		Graphics2D brush = (Graphics2D) g;
	}
}
