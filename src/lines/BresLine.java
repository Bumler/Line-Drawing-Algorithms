package lines;

import java.awt.Color;
import java.awt.Graphics2D;

//this is just a basic holder the api's line drawing algorithm 
//it holds the variables and draws the line yellow
public class BresLine {
	int x1;
	int y1;
	int x2;
	int y2;
	int deltaX;
	int deltaY;
	int Pk;
	
	boolean yAlg = false;
	boolean xAlg = false;
	
	boolean bigYfirst = false;
	boolean bigXfirst = false;
	
	public BresLine (int x1, int y1, int x2, int y2){
		//makes sure that x1 is less than x2
			this.x1 = x1;
			this.y1 = y1;
			this.x2 = x2;
			this.y2 = y2;

			//we need to organize our points by comparing the delta X and Y
			deltaX = Math.abs(x2 - x1);
			deltaY = Math.abs(y2 - y1);
			if (deltaX < deltaY){
				//once we compare our delta values we need to order our points
				yAlg = true;
				if (this.y1 > this.y2){
					this.x1 = x2;
					this.y1 = y2;
					this.x2 = x1;
					this.y2 = y1;
				}
			}
			else if (deltaY < deltaX){
				xAlg = true;
				if (this.x1 > x2){
					this.x1 = x2;
					this.y1 = y2;
					this.x2 = x1;
					this.y2 = y1;
				}
			}
			
			//the big Y first allowed us to plot points on the graphs 1st and 3rd quadrant
			//the big X first allows to plot points between 45 and 90 deg on the 3rd and 4th quadrant
			//assuming your looking at a standard graph
			//this may not be the most optimal fix and could be inefficient
			if (this.y1-this.y2 > 0){
				bigYfirst = true;
			}
			if (this.x1-this.x2 > 0){
				bigXfirst = true;
			}
			
			// this is our initial parameter
			// 2deltay - deltaX
			if (xAlg) {Pk = (2*deltaY) - deltaX;}
			else {Pk = (2*deltaX) - deltaY;}
			
			//deltaX and Y are best stored like this
			deltaX *= 2;
			deltaY *= 2;
	}
	
	//https://www.youtube.com/watch?v=TRbwu17oAYY
	//helped explaining the algorithm
	public void render (Graphics2D g2d){
		g2d.setColor(Color.RED);
		//plot our starting point
		g2d.drawOval(x1, y1, 1, 1);
			
			int x = x1;
			int y = y1;
			//when the y is larger in front only the x version of the algorithm is possible
			//after that we have a few more if statements to cover every other possible algorithm
			if (bigYfirst){
				if (xAlg){
					while (x < x2) {
						if (Pk < 0) {
							x++;
							g2d.drawOval(x, y, 1, 1);
							Pk = Pk + deltaY;
						} else {
							x++;
							y--;
							g2d.drawOval(x, y, 1, 1);
							Pk = Pk + deltaY - deltaX;
						}
					}	
					}
				}
			else{
				if (xAlg){
				while (x < x2) {
					if (Pk < 0) {
						x++;
						g2d.drawOval(x, y, 1, 1);
						Pk = Pk + deltaY;
					} else {
						x++;
						y++;
						g2d.drawOval(x, y, 1, 1);
						Pk = Pk + deltaY - deltaX;
					}
				}	
				}
				else if (bigXfirst){
					while (y < y2) {
						if (Pk < 0) {
							y++;
							g2d.drawOval(x, y, 1, 1);
							Pk = Pk + deltaX;
						} else {
							x--;
							y++;
							g2d.drawOval(x, y, 1, 1);
							Pk = Pk + deltaX - deltaY;
						}
					}	
				}
				else
					while (y < y2) {
						if (Pk < 0) {
							y++;
							g2d.drawOval(x, y, 1, 1);
							Pk = Pk + deltaX;
						} else {
							x++;
							y++;
							g2d.drawOval(x, y, 1, 1);
							Pk = Pk + deltaX - deltaY;
						}
					}	
			}
	}
}
