package model.paint.shape;

import java.awt.Shape;
import java.awt.geom.Ellipse2D;

/**
 * Concrete shape factory that instantiates Ellipse2D.Double shapes.
 *
 */
public class EllipseShapeFactory implements IShapeFactory {

	/**
	 * Singleton pattern
	 */
	public static EllipseShapeFactory Singleton = new EllipseShapeFactory();

	private EllipseShapeFactory() {
	}

	/**
	 * Instantiates an ellipse
	 */
	@Override
	public Shape makeShape(double x, double y, double xScale, double yScale) {
		// TODO Auto-generated method stub
		return new Ellipse2D.Double(x - xScale, y - yScale, 2 * xScale, 2 * yScale);
	}

}
