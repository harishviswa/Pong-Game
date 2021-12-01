import java.awt.Color;
import java.awt.Graphics;

public class Ball {

	// 1. Declaration of Variables
	
	private double x;			//x-coordinate of the center of the ball
	private double y;			//y-coordinate of the center of the ball
	private double diameter;	//diameter of the ball
	private double radius;		//radius of the ball = diameter/2
	private Color color;		//color of the ball
	private double xSpeed;		//x-speed = change in x-position
	private double ySpeed;		//y-speed = change in y-position
	private int Player1Collisions;
	private int Player2Collisions;

	
	// 2. Create a default constructor
	/**
	 * Default Constructor
	 * Creates a red ball at (0, 0) with a diameter of 25.  
	 * The default speed is 0.
	 */
	public Ball() {
		
		this.x = 0;
		this.y = 0; 
		this.diameter = 25;
		this.radius = diameter/2;
		this.color = Color.RED;
		this.xSpeed = 0;
		this.ySpeed = 0;

	}

	// 3. Write a constructor that allows the user to input the parameters (x, y, diameter, Color)
	// and sets the x and y-speed = 0.  Comment with javadoc.
	
	/**
	 * 4 parameter constructor that allows the user to input the parameters (x, y, diameter, Color)
	 * and sets the x and y-speed = 0
	 * @param x - x location of the center of the ball
	 * @param y - y location of the center of the ball
	 * @param diameter - diameter of the ball
	 * @param color - color of the ball
	 */
	public Ball(double x, double y, double diameter, Color color) {
		
		this.x = x;
		this.y = y; 
		this.diameter = diameter;
		this.radius = diameter/2;
		this.color = color;
		this.xSpeed = 0;
		this.ySpeed = 0;

	}



	// 4. Create getters and setters for all private variables
	// Comment with javadoc
	
	
	//Setters
	
	/**
	 * Sets X location of center of the ball
	 * @param x
	 */
	public void setX(double x) {
		this.x = x;
	}
	
	/**
	 * Sets Y location of center of the ball
	 * @param y
	 */
	public void setY (double y) {
		this.y = y;
	}
	
	/**
	 * Sets diameter of the ball and calculates radius
	 * @param diameter
	 */
	public void setDiameter(double diameter) {
		this.diameter = diameter;
		this.radius = diameter/2;
	}
	/**
	 * Sets radius of the ball and calculates diameter
	 * @param radius
	 */
	public void setRadius(double radius) {
		this.radius = radius;
		this.diameter = radius*2;
	}
	
	/**
	 * Sets color of the ball
	 * @param color
	 */
	public void setColor (Color color) {
		this.color = color;
	}
	/**
	 * Sets x-speed of the ball
	 * @param xSpeed
	 */
	public void setXSpeed (double xSpeed) {
		this.xSpeed = xSpeed;
	}
	
	/**
	 * Sets y-speed of the ball
	 * @param ySpeed
	 */
	public void setYSpeed (double ySpeed) {
		this.ySpeed = ySpeed;
	}
	
	//Getters
	
	/**
	 * Gets X location of center of the ball
	 * @return x
	 */
	public double getX() {
		return x;
	}
	
	/**
	 * Gets y location of center of the ball
	 * @return y
	 */
	public double getY() {
		return y;
	}
	
	/**
	 * Gets diameter of the ball 
	 * @return diameter
	 */
	public double getDiameter() {
		return diameter;
	}
	
	/**
	 * Gets radius of the ball
	 * @return radius
	 */
	public double getRadius() {
		return radius;
	}
	
	/**
	 * gets color of the ball
	 * @return color
	 */
	public Color getColor () {
		return color;
	}
	
	/**
	 * gets xSpeed
	 * @return x speed
	 */
	public double getXSpeed () {
		return xSpeed;
	}
	
	/**
	 * gets ySpeed
	 * @return ySpeed
	 */
	public double getYSpeed () {
		return ySpeed;
	}
	
	//getter for Player 1 Score
	public int getPlayer1Score() {
		return Player1Collisions;
	}
	
	//getter for Player 2 Score
	public int getPlayer2Score() {
		return Player2Collisions;
	}
	public void setxSpeed(double xSpeed) {
		this.xSpeed = xSpeed;
	}

	public void setySpeed(double ySpeed) {
		this.ySpeed = ySpeed;
	}

	public void setPlayer1Collisions(int player1Collisions) {
		Player1Collisions = player1Collisions;
	}

	public void setPlayer2Collisions(int player2Collisions) {
		Player2Collisions = player2Collisions;
	}

	// 5. Finish the following methods
	// 6. Test using BouncingBallTester.java
	/**
	 * Draws the ball with (x, y) as the center and diameter
	 * @param g
	 */
	public void draw(Graphics g) {
		
		g.setColor(color);
		g.fillOval((int)(x-radius), (int)(y-radius), (int)diameter, (int)diameter);

	}
	
	
	
	/**
	 * Sets the center location of the ball
	 * @param x
	 * @param y
	 */
	public void setLocation(double x, double y) {
		
		this.x = x;
		this.y = y;
	
		
	}
	
	
	/**
	 * Generates a speed between -<code>maxSpeed<code>
	 * and <code>maxSpeed<code>
	 * @param maxSpeed
	 */
	public void setRandomSpeed(double maxSpeed) {

		this.xSpeed = Math.random()*(maxSpeed*2+1) - maxSpeed;
		this.ySpeed = Math.random()*(maxSpeed*2+1) - maxSpeed;
	}

	
	//7. Write the move method to make the ball move around the screen
	// and bounce of the edges.
	/**
	 * Comment....
	 * @param rightEdge
	 * @param bottomEdge
	 */
	public void move(int rightEdge, int bottomEdge) {
		
		x += xSpeed;
		y += ySpeed;
		
		//rightEdge
		if(x>=rightEdge - radius) {
			x = rightEdge - radius;
			xSpeed = xSpeed*-1;
			setLocation(rightEdge/2, bottomEdge/2);
			Player1Collisions++;
		}
		
		//leftEdge
		if(x<=radius) {
			x = radius;
			xSpeed = xSpeed*-1;
			setLocation(rightEdge/2, bottomEdge/2);
			Player2Collisions++;
		}
		
		//bottomeEdge
		if(y>=bottomEdge - radius) {
			y = bottomEdge - radius;
			ySpeed = ySpeed*-1;
		}
		
		//topEdge
		if(y<=radius) {
			y = radius;
			ySpeed = ySpeed*-1;
		}
	
		
	}
}

