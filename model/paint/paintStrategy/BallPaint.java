package model.paint.paintStrategy;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.*;

import model.Ball;
import model.IPaintStrategy;

/**
 * Paint strategy that paints a filled circle with the Ball's radius.
 * The class demonstrates a direct implementation of IPaintStrategy.
 *
 */
public class BallPaint implements IPaintStrategy {

	/**
	 * The AffineTransformed used for internal calculations.
	 */
	private AffineTransform at;

	/**
	 * Unit sized circle used as a prototype
	 */
	private Ellipse2D.Double ball;

	/**
	 * No parameter constructor that creates a 1 pixel radius ball at the origin.
	 */
	public BallPaint() {
		//ball = new Ellipse2D.Double(-1, -1, 2, 2);
		this(new AffineTransform(), 0, 0, 1, 1);
	}

	/**
	 * Constructor that allows one to create the prototype ball of arbitrary dimension and location. 
	 * The AffineTransform is externally supplied.
	 * @param at The AffineTransform to use for internal calculations
	 * @param x floating point x-coordinate of center of circle
	 * @param y floating point y-coordinate of center of circle
	 * @param width floating point x-radius of the circle (ellipse)
	 * @param height floating point y-radius of the circle (ellipse)
	 */
	public BallPaint(AffineTransform at, double x, double y, double width, double height) {
		this.at = at;
		ball = new Ellipse2D.Double(x - width, y - height, width * 2, height * 2);
	}

	/**
	 * By default, do nothing for initialization.
	 */
	public void init(Ball context) {
	}

	/**
	 * Paints on the given graphics context using the color, scale and direction provided by the host. 
	 * @param g The graphics context to draw upon.
	 * @param host The host ball.
	 */
	public void paint(Graphics g, Ball host) {
		double scale = host.getRadius();
		at.setToTranslation(host.getLocation().x, host.getLocation().y);
		at.scale(scale, scale);

		g.setColor(host.getColor());

		paintXfrm(g, host, at);
	}

	/**
	 * Paints a transformed circle, as per the given AffineTransform Uses color already set in Graphics context 
	 * @param g The Graphics context to paint on
	 * @param host The Ball host
	 * @param at the AffineTransform to use
	 */
	public void paintXfrm(Graphics g, Ball host, AffineTransform at) {
		((Graphics2D) g).fill(at.createTransformedShape(ball));
	}
}
