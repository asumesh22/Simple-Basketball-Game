package basketball;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.swing.*;

public class panel extends JPanel {
	
	private BufferedImage myImage;
	private Graphics myBuffer;
	private Ball bball;
	private Timer t;
	private Bumper back, prevent1, prevent2;
	private int points;
	private polkadot pd;
	private JLabel score;
	private JButton resetScore, resetBall;
	private JPanel buttons;
	
	private static final int FRAME = 800;
	private static final Color BACKGROUND = new Color(251, 177, 88);
	
	public panel() {
		myImage =  new BufferedImage(FRAME, FRAME, BufferedImage.TYPE_INT_RGB);
        myBuffer = myImage.getGraphics();
        myBuffer.setColor(BACKGROUND);
        myBuffer.fillRect(0, 0, FRAME,FRAME);
		
		bball = new Ball();
		bball.setdx(0);
		bball.setdy(0);
		bball.setColor(Color.CYAN);
		
		back = new Bumper(780, 160, 35, 700, Color.gray);
		prevent1 = new Bumper(670, 330, 1, 95, Color.black);
		prevent2 = new Bumper(675, 425, 90, 1, Color.black);
		
		pd = new polkadot(725, 325, 50, Color.black);
		
		
		score = new JLabel();
		score.setFont(new Font("Times", Font.BOLD, 40));
		add(score);
		
		addMouseListener(new Mouse());
		
		buttons = new JPanel();
		
		resetScore = new JButton("Reset Score");
		resetBall = new JButton("Reset Ball");
		resetScore.addActionListener(new Listener1());
		resetBall.addActionListener(new Listener2());
		buttons.add(resetScore);
		buttons.add(resetBall);
		add(buttons);
		
		t = new Timer(5, new Listener());
        t.start();  
	}
	
	public void paintComponent(Graphics g) {
	   	g.drawImage(myImage, 0, 0, getWidth(), getHeight(), null);
	   	
	   	// rim
        g.setColor(Color.red);
        g.fillRect(650, 300, 150, 15);
        // net
        g.setColor(Color.white);
        for (int i = 0; i < 20; i++) {
			g.drawLine(660+5*i, 315, 660+5*i, 400);
		}
        g.drawLine(660, 400, 750, 400);
        
        // Score
        g.setColor(Color.blue);
        g.setFont(new Font("Times", Font.BOLD, 40));
   }
	
	private class Listener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			myBuffer.setColor(BACKGROUND); 
			myBuffer.fillRect(0,0,FRAME,FRAME);
			
			BumperCollision.collide(back, bball);
			BumperCollision.collide(prevent1, bball);
			BumperCollision.collide(prevent2, bball);
			
			//prevent1.draw(myBuffer);
			//prevent2.draw(myBuffer);
			
			bball.setdy(bball.getdy()+0.15);
			if(bball.getdx() > 0)
				bball.setdx(bball.getdx()-0.002);
			else
				bball.setdx(bball.getdx()+0.002);
			bball.move(FRAME,FRAME);
			bball.draw(myBuffer);
			back.draw(myBuffer);
			collide(bball, pd);
			
			if(bball.getY() + bball.getRadius() == FRAME) {
				if(bball.getdx() > 0)
					bball.setdx(bball.getdx()-0.2);
				else
					bball.setdx(bball.getdx()+0.2);
			}
			
			//pd.draw(myBuffer);
			
			score.setText("Score: " + points);

			repaint();
		}
    }
	
	private class Listener1 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			points = 0;
		}
    }
	
	private class Listener2 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			bball.setX(400);
			bball.setY(400);
		}
    }
	
	private void collide(Ball b, polkadot pd) {
	     double d = distance(b.getX(), b.getY(), pd.getX(), pd.getY());  
		 if(d <= b.getRadius()+pd.getRadius()) {
			 points++;
			 b.setX(400);
			 b.setY(400);
			 b.setdx(0);
			 b.setdy(0);
		 }
			  
	}
	
	private double distance(double x1, double y1, double x2, double y2) {
	      return Math.sqrt(Math.pow(x2-x1, 2) + Math.pow(y2-y1, 2));
	}

	
	private class Mouse extends MouseAdapter {

		public void mousePressed(MouseEvent e) {
			Point p = e.getPoint();
			// the magic
			double px = p.getX();
			double py = p.getY();
			double bx = bball.getX();
			double by = bball.getY();
			
			bball.setdx((px-bx)/20);
			bball.setdy((py-by)/20);
		}
		
	}
}
