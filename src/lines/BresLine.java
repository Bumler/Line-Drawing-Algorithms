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
			
			if (this.y1-this.y2 > 0){
				bigYfirst = true;
			}
			if (this.x1-this.x2 > 0){
				bigXfirst = true;
			}

			System.out.println ("X1: "+this.x1+ " Y1: "+this.y1+" X2: "+this.x2+" Y2: "+this.y2);
			System.out.println ("XAlg: " +xAlg+ " YAlg: "+yAlg);
			getAngle();
	}
	
	//http://stackoverflow.com/questions/9970281/java-calculating-the-angle-between-two-points-in-degrees
	public float getAngle() {
		System.out.println(Math.toDegrees(Math.atan2(x2 - x1, y2 - y1)));
	    return (float) Math.toDegrees(Math.atan2(x2 - x1, y2 - y1));
	}
	
	//https://www.youtube.com/watch?v=TRbwu17oAYY
	//helped explaining the algorithm
	public void render (Graphics2D g2d){
		g2d.setColor(Color.RED);
		//plot our starting point
		g2d.drawOval(x1, y1, 5, 5);
		
			// this is our initial parameter
			// 2deltay - deltaX
			
			int Pk;
			//we may need to recalculate the delta x and y

			if (xAlg) {Pk = (2*deltaY) - deltaX;}
			else {Pk = (2*deltaX) - deltaY;}
			
			deltaX *= 2;
			deltaY *= 2;
			
			int x = x1;
			int y = y1;
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
				else {
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
