package lines;

import java.awt.Color;
import java.awt.Graphics2D;

//this is just a basic holder the api's line drawing algorithm 
//it holds the variables and draws the line yellow
public class APILine {
	int x1;
	int y1;
	int x2;
	int y2;
	
	public APILine (int x1, int y1, int x2, int y2){
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}
	
	public void render (Graphics2D g2d){
		g2d.setColor(Color.YELLOW);
		g2d.drawLine(x1, y1, x2, y2);
	}
}
