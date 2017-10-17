package model.paint.shape;

import java.awt.Point;
import java.awt.geom.AffineTransform;

/**
 * Concrete PolygonFactory that creates fish-shaped Polygons
 *
 */
public class FishPolygonFactory extends PolygonFactory {

	/**
	 * Constructor that calls the PolygonFactory superclass constructor with the scale factor and polygon points that define the fish shape.
	 */
	public FishPolygonFactory() {
		super(new AffineTransform(), 1.0 / 10.0, new Point(5, 3), new Point(-5, -3), new Point(-12, 2),
				new Point(-16, 0), new Point(-16, 4), new Point(-12, 2), new Point(-5, 7), new Point(5, 3));
	}
}
