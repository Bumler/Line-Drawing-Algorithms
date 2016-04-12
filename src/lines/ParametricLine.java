package lines;

import java.awt.Color;
import java.awt.Graphics2D;

//this is just a basic holder the api's line drawing algorithm 
//it holds the variables and draws the line yellow
public class ParametricLine {
	int x1;
	int y1;
	int x2;
	int y2;
	double t;
	
	public ParametricLine (int x1, int y1, int x2, int y2, double steps){
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		this.t = steps;
	}
	
	public void render (Graphics2D g2d){
		g2d.setColor(Color.BLACK);
		
		for (double i = 0; i < 1; i += t){
			int x =(int) (x1 + (x2-x1) * i);
			int y =(int) (y1 + (y2-y1) * i);
			g2d.drawOval(x, y, 1, 1);
		}
	}
}
