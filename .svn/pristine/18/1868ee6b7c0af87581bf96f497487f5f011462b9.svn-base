package model.paint;

import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import model.*;

/**
 * The top-level affine transform-based paint strategy that provides services for its subclasses, plus default behaviors and abstract behaviors.
 *
 */
public abstract class APaintStrategy implements IPaintStrategy {

	/**
	 * The affine transform used by this paint strategy to translate, scale and rotate the image.
	 */
	private AffineTransform at;

	/**
	 * Constructor that initializes the strategy with an affine transform
	 * @param _at The AffineTransform for this paint strategy to use.
	 */
	public APaintStrategy(AffineTransform _at) {
		at = _at;
	}

	/**
	 * Protected accessor for the internal affine transform
	 * @return This instance's affine transform
	 */
	protected java.awt.geom.AffineTransform getAT() {
		return this.at;
	}

	/**
	 * By default, do nothing for initialization.
	 */
	public void init(Ball context) {
	}

	/**
	 * Paints on the given graphics context using the color, scale and direction provided by the host.
	 * @param g The Graphics context that will be paint on
	 * @param host The host Ball that the required information will be pulled from.
	 */
	public void paint(Graphics g, Ball host) {
		double scale = host.getRadius();
		at.setToTranslation(host.getLocation().x, host.getLocation().y);
		at.scale(scale, scale);
		at.rotate(host.getVelocity().x, host.getVelocity().y);
		g.setColor(host.getColor());
		paintCfg(g, host);
		paintXfrm(g, host, at);
	}

	/**
	 * Used for doing additional configurations by a subclass.
	 * This method is called before the paint method's final delegation to paintXfrm.
	 * @param g The Graphics context that will be drawn upon.
	 * @param host The Ball to be painted.
	 */
	protected void paintCfg(Graphics g, Ball host) {
	}

	/**
	 * Paints the host onto the given Graphics context. The image is translated, scaled and rotated as determined by the given affine transformation. 
	 * This method is intended to be called either by a class's (or superclass's) own paint method or by another paint strategy who is sharing the affine transform. 
	 * This allows the same transformation to be shared amongst multiple paint strategies.
	 * @param g The graphics context to draw upon.
	 * @param host The host ball.
	 * @param at The affine transform to use.
	 */
	public abstract void paintXfrm(Graphics g, Ball host, AffineTransform at);
}
