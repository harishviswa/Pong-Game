import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class BumperAnimation extends JPanel {

	private static final int WIDTH = 1600;
	private static final int HEIGHT = 1250;
	private static final Color LIGHT_BLUE = new Color(108, 210, 247);
	
	private BufferedImage image;
	private Graphics g;
	private Timer timer;
	
	// declare stuff
	private Ball ball;
	private Bumper blueBumper;
	private Bumper redBumper;
	
	public BumperAnimation() {
		
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		g = image.getGraphics();
		
		g.setColor(LIGHT_BLUE);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		// instantiate stuff
		ball = new Ball(200, 100, 200, Color.BLACK);
		ball.setRandomSpeed(6);
		
		blueBumper = new Bumper();
		blueBumper.setLocation(100, 1000);
		blueBumper.setWidth(150);
		blueBumper.setHeight(400);
		
		redBumper = new Bumper(1000, 500, 200, 300, Color.RED);
		
		timer = new Timer(5, new TimerListener());
		timer.start();
	}
	
	private class TimerListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			// draw background / clear screen
			g.setColor(LIGHT_BLUE);
			g.fillRect(0, 0, WIDTH, HEIGHT);

			// move stuff / update locations
			ball.move(WIDTH, HEIGHT);
			BumperCollision.collide(blueBumper, ball);
			BumperCollision.collide(redBumper, ball);
			
			// draw stuff
			ball.draw(g);
			blueBumper.draw(g);
			redBumper.draw(g);
				
			repaint();
		}
		
	}
	
	
	public void paintComponent(Graphics g) {
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
	}

	public static void main(String[] args) {

		JFrame frame = new JFrame("Bumper!!!");
		frame.setSize(1200, 900);
		frame.setLocation(200, 50);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(new BumperAnimation());
		frame.setVisible(true);
	}
}