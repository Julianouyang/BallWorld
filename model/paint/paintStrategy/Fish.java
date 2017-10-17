package model.paint.paintStrategy;

import java.awt.geom.AffineTransform;

import model.paint.ShapePaintStrategy;
import model.paint.shape.FishPolygonFactory;

/**
 * Paint strategy that paints a fish-shaped Polygon generated by a FishPolyfonFactory.
 */
public class Fish extends ShapePaintStrategy {

	/**
	 * Constructor that takes an external AffineTransform
	 * @param at The AffineTransform to use for internal use.
	 */
	public Fish(AffineTransform at) {
		super(at, (new FishPolygonFactory()).makeShape(0, 0, 1, 1));
	}

	/**
	 * No-parameter constructor that instantiates the AffineTransform for internal use.
	 */
	public Fish() {
		this(new AffineTransform());
	}
}