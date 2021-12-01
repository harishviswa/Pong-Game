
public class PaddleCollision {
	
//adapted from BumperCollision
	
	private static double nearestX;	// approximates where Paddle was hit
	   private static double nearestY;  
	   private static int collisions;

	   /**
	    * If the Ball collides with the Paddle, the ball will bounce off the Paddle at the same angle
	    * @param paddle the Bumper
	    * @param ball the Ball
	    */
	   public static void hit(Paddle paddle, Ball ball)
	   {
	      if(paddle.inPaddle(ball))
	      {	   	
	         while(paddle.inPaddle(ball))
	         {
	            ball.setX(ball.getX() - ball.getXSpeed()/10.0);
	            ball.setY(ball.getY() - ball.getYSpeed()/10.0);
	         }
	         
	         // find the point on the edge of the paddle closest to the ball
	         ImpactPoint(paddle, ball);
	      	
	         double ux = nearestX - ball.getX();
	         double uy = nearestY - ball.getY();
	         double ur = Math.sqrt(ux * ux + uy * uy);
	         ux /= ur;
	         uy /= ur;
	         int dx = (int)ball.getXSpeed();
	         int dy = (int)ball.getYSpeed();
	         double dot_1 = ux * dx + uy * dy;
	         double dot_2 = -uy * dx + ux * dy;
	         dot_1 *= -1; 
	         double[] d = new double[2];
	         d[0] = dot_1 * ux - dot_2 * uy;      
	         d[1] = dot_1 * uy + dot_2 * ux;      
	         dx = (int)Math.round(d[0]);
	         dy = (int)Math.round(d[1]);
	         ball.setXSpeed(dx);
	         ball.setYSpeed(dy);
	         collisions++;
	      }
	   }
	   
	   /**
	    * Finds the point of impact between the Paddle and the Ball and updates to that (x, y) coordinate
	    * @param paddle the Paddle
	    * @param ball the Ball
	    */
	   private static void ImpactPoint(Paddle paddle, Ball ball)
	   {
	      
	      nearestX = paddle.getX() - paddle.getWidth()/2;  
	      nearestY = paddle.getY() - paddle.getHeight()/2;
	      
	      for (int x = paddle.getX() - paddle.getWidth()/2; x <= paddle.getX() + paddle.getWidth()/2; x++) 
	      {
	         updateLocation(x, paddle.getY() - paddle.getHeight()/2, ball);
	         updateLocation(x, paddle.getY() + paddle.getHeight()/2, ball);
	      }
	      for (int y = paddle.getY() - paddle.getWidth()/2; y <= paddle.getY() + paddle.getHeight()/2; y++) 
	      {
	         updateLocation(paddle.getX() - paddle.getWidth()/2, y, ball);
	         updateLocation(paddle.getX() + paddle.getWidth()/2, y, ball);
	      }
	   }
	      
	   /**
	    * Makes (x,y) the nearest point if it is closer to the ball
	    * @param x the x-coordinate
	    * @param y the y-coordinate
	    * @param b the Ball object
	    */
	   private static void updateLocation(int x, int y, Ball b)
	   {
	      if(distance(x, y, b.getX(), b.getY()) < distance(nearestX, nearestY, b.getX(), b.getY()))
	      {
	         nearestX = x;
	         nearestY = y;
	      }
	   }
	       
	   /**
	    * Calculates the distance between (x1, y1) and (x2, y2).
	    * @param x1 the x-coordinate of the first object
	    * @param y1 the y-coordinate of the first object
	    * @param x2 the x-coordinate of the second object
	    * @param y2 the y-coordinate of the second object
	    * @return the distance between (x1, y1) and (x2, y2).
	    */
	   private static double distance(double x1, double y1, double x2, double y2)
	   {
	      return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
	   }	
	}

