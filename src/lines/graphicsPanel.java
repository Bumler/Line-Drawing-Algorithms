package lines;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JPanel;

public class graphicsPanel extends JPanel {
	
	/**
	 * Create the panel.
	 */
	int mode = -1;
	int count = 0;
	int x1, y1;
	frame frame = null;
	
	//we will create three array lists one for each line type
	ArrayList<ParametricLine> parametricList = new ArrayList<ParametricLine>();
	ArrayList<BresLine> bresenhamList = new ArrayList<BresLine>();
	ArrayList<APILine> apiList = new ArrayList<APILine>();
	public graphicsPanel(frame f) {
		this.frame = f;
		this.addMouseListener(new MouseListener(){
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				System.out.println("CLICK");
					int x = e.getX();
					int y = e.getY();
				if (e.getButton() == MouseEvent.BUTTON1) {
						if (count == 0){
							x1 = x;
							y1 = y;
							count++;
						}
						else{
							hub(x1,y1,x,y);
							count = 0;
						}
					}
					repaint();
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}
			
		});
	}
	
	public void hub(int x1, int y1, int x2, int y2){
		switch (mode){
		case -1: 
			break;
		
		//Parametric
		case 0:
			ParametricLine pline = new ParametricLine (x1,y1,x2,y2,frame.getSteps());
			parametricList.add(pline);
			break;
			
		//Brasenham's
		case 1:
			BresLine bLine = new BresLine (x1,y1,x2,y2);
			bresenhamList.add(bLine);
			break;
			
		//API
		case 2:
			APILine aline = new APILine(x1,y1,x2,y2);
			apiList.add(aline);
			break;
		}
	}
	
	//sets the mode that chooses what type of line will be drawn
	//0 = parametric, 1 = Bresenham's, 2 = API
	//by default mode is -1
	public void setMode(int modeIN){
		mode = modeIN;
		count = 0;
	}
	
	//paint goes through each array list rendering all the lines
	public void paint (Graphics g){
		super.paint(g);
		Graphics2D g2d = (Graphics2D)g;
		for (ParametricLine l : parametricList){
			l.render(g2d);
		}
		for (BresLine l : bresenhamList){
			l.render(g2d);
		}
		for (APILine l: apiList){
			l.render(g2d);
		}
	}

	//clears each array list
	public void erase() {
		parametricList = new ArrayList<ParametricLine>();
		bresenhamList = new ArrayList<BresLine>();
		apiList = new ArrayList<APILine>();
		repaint();
	}

}