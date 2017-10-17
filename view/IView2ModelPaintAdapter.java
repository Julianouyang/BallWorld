package view;

import java.awt.Graphics;

/**
 * The view to model adapter interface
 */
public interface IView2ModelPaintAdapter {

	/**
	 * paint on g.
	 * @param g the graphic object to paint on.
	 */
	public void paint(Graphics g);

	/**
	 * the view to model adapter null object.
	 */
	public static final IView2ModelPaintAdapter NULL_OBJECT = new IView2ModelPaintAdapter() {
		@Override
		public void paint(Graphics g) {
		}
	};

}
