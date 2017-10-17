package model.paint;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;

import model.*;

/**
 * Concrete class that defines invariant painting behaviors to paint Shape objects for all its subclasses. 
 *
 */
public class ShapePaintStrategy extends APaintStrategy {

	/**
	 * The Shape to be painted
	 */
	private Shape shape;

	/**
	 * Constructor that uses a supplied AffineTransform for internal use.
	 * @param _at The AffineTransform to use
	 * @param _shape The Shape to be painted.
	 */
	public ShapePaintStrategy(AffineTransform _at, Shape _shape) {

		super(_at);
		shape = _shape;

	}

	/**
	 * Constructor that allocates a new AffineTransform for internal use.
	 * @param _shape The Shape to be painted.
	 */
	public ShapePaintStrategy(Shape _shape) {
		this(new AffineTransform(), _shape);

	}

	/**
	 * Paints the shape on the given Graphics context using the supplied AffineTransform. 
	 * @param g The Graphics context to paint upon.
	 * @param host The Graphics context to paint upon.
	 * @param at The AffineTransform to use
	 */
	public void paintXfrm(Graphics g, Ball host, AffineTransform at) {
		((Graphics2D) g).fill(at.createTransformedShape(shape));
	}

}
