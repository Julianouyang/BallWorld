package model.paint;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.AffineTransform;

import model.Ball;

/**
 * Decorator paint strategy that decorates another IPaintStrategy and causes the painted color to always be a specified, fixed color. 
 *
 */
public class FixedColorDecoratorPaintStrategy extends ADecoratorPaintStrategy {

	/**
	 * The color that will be painted.
	 */
	private Color color;

	/**
	 * Constructor that takes the fixed color and the decoree strategy
	 * @param color The fixed color to use
	 * @param decoree The decoree strategy whose color is overridden
	 */
	public FixedColorDecoratorPaintStrategy(Color color, APaintStrategy decoree) {
		super(decoree);
		this.color = color;
	}

	/**
	 * Default behavior is to simply delegate to the decoree's paintXfrm method
	 * @param g The graphics context which is passed to the decoree
	 * @param host The host Ball which is passed to the decoree.
	 * @param at The affine transform which is passed to the decoree.
	 */
	public void paintXfrm(Graphics g, Ball host, AffineTransform at) {
		g.setColor(color);
		super.paintXfrm(g, host, at);
	}
}
