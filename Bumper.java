import java.awt.Color;
import java.awt.Graphics;

public class Bumper {

	//private fields
	private int x;
	private int y;
	private int width;
	private int height;
	private Color color;
	
// default constructor
	public Bumper() {
		
		this.x = 0;
		this.y = 0; 
		this.width = 50;
		this.height = 100;
		this.color = Color.RED;
	}
//5 Parameter constructor
	public Bumper(int x, int y, int width, int height, Color color) {
		this.x = x;
		this.y = y; 
		this.width = width;
		this.height = height;
		this.color = color;

	}
//getter methods
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public Color getColor() {
		return color;
	}
	
//setter methods
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}

//setting location method
	public void setLocation(int x, int y) {
		this.x = x;
		this.y = y;
	}

//drawing the rectangle/bumper
	public void draw(Graphics g) {
		g.setColor(color);
		g.fillRect(x, y, width, height);
	}
	
	
	
	/**
	 * Returns true if any part of the Ball is inside the bumper
	 * @param ball the Ball
	 * @return true if any part of the Ball is inside the bumper, false otherwise
	 */
	public boolean inBumper(Ball ball) {
		for (int x = getX() - getWidth()/2; x <= getX() + getWidth()/2; x++) {
			for (int y = getY() - getHeight()/2; y <= getY() + getHeight()/2; y++) {
				if (getDistance(x, y, ball.getX(), ball.getY()) <= ball.getRadius()) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Calculates the distance between (x1, y1) and (x2, y2)
	 * @param x1 the x-coordinate of the first point
	 * @param y1 the y-coordinate of the first point
	 * @param x2 the x-coordinate of the second point
	 * @param y2 the y-coordinate of the second point
	 * @return the distance between (x1, y1) and (x2, y2)
	 */
	private double getDistance(double x1, double y1, double x2, double y2) {
		return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
	}
	
	
}