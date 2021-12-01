import java.awt.BasicStroke;
import java.awt.Color;


import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.Scanner;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Pong extends JPanel{
		
	//creation of private variables
		private static final int WIDTH = 1600;
		private static final int HEIGHT = 1250;
		
		private BufferedImage image;
		private Graphics g;
		private Timer timer;
	
		private Ball ball;
		//array of paddles
		private Paddle paddles[] = new Paddle[2];
		//boolean variables for key strokes (will be used later)
		private boolean upPressed, downPressed, wPressed, sPressed;
		
		public Pong() {
			
			image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
			g = image.getGraphics();
			
			//background
			g.setColor(Color.black);
			g.fillRect(0, 0, WIDTH, HEIGHT);
			
			// instantiate ball
			ball = new Ball(500, 500, 50, Color.white);
		    //set default ball speed 
			ball.setXSpeed(6);
			ball.setYSpeed(6);
			//instantiate paddles
			paddles[0] = new Paddle(1450, 500, 50, 300, Color.white);
			paddles[1] = new Paddle(100,500, 50, 300, Color.white);
			
			//set boolean values to false
			upPressed = false;
			downPressed = false;
			//start timer
			timer = new Timer(5, new TimerListener());
			timer.start();
			//KeyListener
			addKeyListener(new Keyboard());
			setFocusable(true);
		}

		//movements for key inputs
		public void updateKeyInput(){
			if(upPressed == true) {
				paddles[0].setY(paddles[0].getY() - 5);
			}
			if(downPressed == true) {
				paddles[0].setY(paddles[0].getY() + 5);
			}
			if(wPressed == true) {
				paddles[1].setY(paddles[1].getY() - 5);
			}
			if(sPressed == true) {
				paddles[1].setY(paddles[1].getY() + 5);
			}
		}
		private class Keyboard implements KeyListener{

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			//setting boolean statements to true so that lines 70-84 run
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				//setting boolean values to true so that they would run in the
				//updateKeyInput() method
				if(e.getKeyCode() == KeyEvent.VK_UP) {
					upPressed = true;
				}
				if(e.getKeyCode() == KeyEvent.VK_DOWN) {
					downPressed = true;
				}
				if(e.getKeyCode() == KeyEvent.VK_W) {
					wPressed = true;
				}
				if(e.getKeyCode() == KeyEvent.VK_S) {
					sPressed = true;
				}
				if(e.getKeyCode() == KeyEvent.VK_SPACE) {
					timer.stop();
				}
				
			}
			

			//setting boolean values to false so that lines 70-84 stop running
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				if(e.getKeyCode() == KeyEvent.VK_UP) {
					upPressed = false;
				}
				if(e.getKeyCode() == KeyEvent.VK_DOWN) {
					downPressed = false;
				}
				if(e.getKeyCode() == KeyEvent.VK_W) {
					wPressed = false;
				}
				if(e.getKeyCode() == KeyEvent.VK_S) {
					sPressed = false;
				}
				if(e.getKeyCode() == KeyEvent.VK_SPACE) {
					timer.stop();
				}
				
			}
			
		}
		
		
	
		private class TimerListener implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {

				// draw background / clear screen
				g.setColor(Color.black);
				g.fillRect(0, 0, WIDTH, HEIGHT);
				//key inputs
				updateKeyInput();
				
				// move stuff / update locations
				ball.move(WIDTH, HEIGHT);
				PaddleCollision.hit(paddles[0], ball);
				PaddleCollision.hit(paddles[1], ball);
				
				// draw ball and paddles
				ball.draw(g);
				paddles[0].draw(g);
				paddles[1].draw(g);
				//display of score
				g.setColor(Color.white);
				g.setFont(new Font("", Font.BOLD, 200));
				//g.drawString("Player 1 Score: ", 50, 250);
			//	g.drawString("Player 2 Score:", 1100, 250);
				g.drawString(String.valueOf(ball.getPlayer1Score()), 320, 250);
				g.drawString(String.valueOf(ball.getPlayer2Score()), 1200, 250);
				g.setFont(new Font("", Font.BOLD, 50));
				g.setColor(Color.red);
				g.drawString("First to 5 wins!", 630, 50);
				//if statements to end game
				if(ball.getPlayer1Score() == 5) {
					g.setColor(Color.red);
					g.setFont(new Font("", Font.BOLD, 50));
					g.drawString("Player 1 Wins!", 650, 450);
					timer.stop();
				}
				if(ball.getPlayer2Score() == 5) {
					g.setColor(Color.red);
					g.setFont(new Font("", Font.BOLD, 50));
					g.drawString("Player 2 Wins!", 650, 450);
					timer.stop();
				}
				repaint();
			}
			
		}
	
	
	
	
	
	public void paintComponent(Graphics g) {
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		
		//drawing dashed line in the middle of the screen (for aesthetic)
		// Creating a copy of the Graphics instance
		  Graphics2D g2d = (Graphics2D) g.create();

		  // Setting the stroke of the copy
		  Stroke dashed = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL,0, new float[]{9}, 0);
		  g2d.setColor(Color.white);
		  g2d.setStroke(dashed);

		  // Draw the copy
		  g2d.drawLine(600, 800, 600, 40);

		  // Get rid of the copy
		  g2d.dispose();
	}

	public static void main(String[] args) {
		
		JFrame frame = new JFrame("Pong");
		frame.setSize(1200, 900);
		frame.setLocation(200, 50);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(new Pong());
		frame.setVisible(true);
	}
}
