package model;

import java.awt.Container;

/**
 * the IModel2ViewAdapter interface.
 */
public interface IModel2ViewAdapter {
	/**
	 * The method that tells the view to update
	 */
	public void update();

	/**
	 * Get the canvas in the view
	 * @return the canvas in the view
	 */
	public Container getCanvas();

	/**
	 * No-op "null" adapter
	 * 
	 */
	public static final IModel2ViewAdapter NULL_OBJECT = new IModel2ViewAdapter() {
		/**
		 * Do nothing when update
		 * @see model.IModel2ViewAdapter#update()
		 */
		public void update() {
		}

		/**
		 * Return a null canvas
		 * @see model.IModel2ViewAdapter#getCanvas()
		 */
		public Container getCanvas() {
			return null;
		}
	};

}
