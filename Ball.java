package basketball;

import java.awt.*;

public class Ball extends polkadot {
  private double dx;       
  private double dy;
  private double angle;

   public Ball() {
     super(400, 400, 50, Color.BLACK);
     dx = 0;          
     dy = 0;
     angle = Math.atan(dy/dx);
   }
   public Ball(double x, double y, double dia, Color c) {
     super(x, y, dia, c);
     dx = Math.random()* 12 - 6;
     dy = Math.random() * 12 - 6;
   }
  
 
   public void setdx(double x) {
     dx = x;
   }
   public void setdy(double y) {
     dy = y;
   }
  

   public double getdx() {
     return dx;
  }
   public double getdy() {
     return dy;
  }

   public double getAngle() {
	   angle = Math.atan(dy/dx);
	   return angle;
   }
  

   public void move(double rightEdge, double bottomEdge) {
	   setX(getX()+ dx);                 
	   setY(getY()+ dy+3);
	   
      if(getX() >= rightEdge - getRadius()) {
         setX(rightEdge - getRadius());
         dx = dx * -1; 
      }
      
      if(getX() <= getRadius()) {
         setX(getRadius());
         dx = dx * -1; 
      }
    
      /*
      if(getY() <= -50) {
    	  setdx(0);
    	  setdy(0);
    	  setX(400);
    	  setY(400);
      }
      */
      
      if(getY() >= bottomEdge-getRadius()) {
    	  setY(bottomEdge-getRadius());
    	  if(dy > 0) {
    		  dy = dy-1;
    	  } else if(dy < 0) {
    		  dy = dy+1;
    	  }
    	  dy = dy * -1;
      } 
  }
}
