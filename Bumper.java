package basketball;

import java.awt.*;

public class Bumper {
	 private int myX, myY, myWidth, myHeight;
	 private Color myColor;
	 
   public Bumper() {
   	myX = 0;
   	myY = 0;
   	myWidth = 50;
   	myHeight = 100;
   	myColor = Color.BLACK;
   }
   public Bumper(int x, int y, int w, int h, Color c) {
   	myX = x;
   	myY = y;
   	myWidth = w;
   	myHeight = h;
   	myColor = c;
   }
  
   public int getX() {
   	return myX;
   }
   public int getY() {
   	return myY;
   }
   public int getWidth() {
   	return myWidth;
   }
   public int getHeight() {
   	return myHeight;
   }
   public Color getColor() {
   	return myColor;
   }
 
   public void setX(int x) {
   	myX = x;
   }
   public void setY(int y) {
   	myY = y;
   }
   public void setWidth(int w) {
   	myWidth = w;
   }
   public void setHeight(int h) {
   	myHeight = h;
   }
   public void setColor(Color c) {
   	myColor = c;
   }
 
    public void jump(int rightEdge, int bottomEdge) {
	 myX = (int) (Math.random()* (rightEdge-myWidth) + myWidth);
     myY = (int) (Math.random()* (bottomEdge-myHeight) + myHeight);
    }
  
   public void draw(Graphics myBuffer) {
     myBuffer.setColor(getColor());
     myBuffer.fillRect(getX(), getY(), getWidth(), getHeight());
   }   
   
   public boolean inBumper(polkadot dot) {
     for(int x = getX(); x <= getX() + getWidth(); x++)   //starts at upper left corner(x,y)
        for(int y = getY(); y <= getY() + getHeight(); y++)
           if(distance(x, y, dot.getX(), dot.getY()) <= dot.getRadius() ) //checks every point on the bumper
              return true;            
     return false;
   }  

   private double distance(double x1, double y1, double x2, double y2) {
     return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
  }	
}
