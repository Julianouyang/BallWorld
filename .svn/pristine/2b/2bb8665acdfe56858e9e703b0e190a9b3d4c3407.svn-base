package model.paint.shape;

import java.awt.Point;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.geom.AffineTransform;

/**
 * Concrete IShapeFactory that provides the invariant behavior to instantiate a Shape that is a Polygon.
 *
 */
public class PolygonFactory implements IShapeFactory {

	/**
	 * The Polygon shape to use as the prototype.
	 */
	private Polygon poly = new Polygon();

	/**
	 * The AffineTransform used for internal calculations
	 */
	private AffineTransform at;

	/**
	 * Scale factor that scales the integer Point-defined Polygon to a unit size, which requires doubles.
	 */
	private double scaleFactor = 1.0;

	/**
	 * Constructor that uses an externally defined AffineTransform for internal use 
	 * plus takes the defining points of the prototype Polygon and a scale factor to scale the given points to the desired unit size.
	 * @param _at            The AffineTransform to use.
	 * @param _scaleFactor   The ratio of the desired unit size to the defined size of the prototype Polygon.
	 * @param pts            Vararg parameters that are the Points that define the Polygon around the origin as its center.
	 */
	PolygonFactory(AffineTransform _at, double _scaleFactor, Point... pts) {
		at = _at;
		scaleFactor = _scaleFactor;
		for (Point point : pts) {
			poly.addPoint(point.x, point.y);
		}
	}

	/**
	 * Instantiates a Shape object that is the prototype Polygon translated by the given (x, y) point 
	 * and scaled by given (xScale, yScale) factor times the internal scaleFactor. 
	 * @param x x-coordinate of the center of the resultant Polygon
	 * @param y y-coordinate of the center of the resultant Polygon
	 * @param xScale The x-dimension of the polygon, usually the x-radius.
	 * @param yScale The y-dimension of the polygon, usually the y-radius.
	 * @return A Shape object that is the scaled prototype Polygon.
	 */
	@Override
	public Shape makeShape(double x, double y, double xScale, double yScale) {
		// TODO Auto-generated method stub
		at.setToTranslation(x, y);
		at.scale(xScale * scaleFactor, yScale * scaleFactor); // optional rotation can be added as well
		return at.createTransformedShape(poly);
	}

}
