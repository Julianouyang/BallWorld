package model.paint;

import java.awt.Graphics;
import java.awt.geom.AffineTransform;

import model.Ball;

/**
 * A composite design pattern extension of APaintStrategy that paints a set of paint strategies.
 *
 */
public abstract class MultiPaintStrategy extends APaintStrategy {

	/**
	 * The set of paint strategies to paint
	 */
	private APaintStrategy[] pstrats;

	/**
	 * Constructor that takes the paint strategies that will part of the composite.
	 * @param _at The AffineTransform to use.
	 * @param _pstrats Vararg parameter that are the paint strategies that will make up the composite.
	 */
	public MultiPaintStrategy(AffineTransform _at, APaintStrategy... _pstrats) {
		super(_at);
		pstrats = _pstrats;
	}

	/**
	 * Constructor that takes the paint strategies that will part of the composite. An AffineTransform is instantiated for internal use.
	 * @param _pstrats Vararg parameter that are the paint strategies that will make up the composite.
	 */
	public MultiPaintStrategy(APaintStrategy... _pstrats) {
		this(new AffineTransform(), _pstrats);
	}

	/**
	 * Delegates to all the IPaintStrategies in the composite. Used to initialize the paint strategies.
	 * @param host The host Ball
	 */
	public void init(Ball host) {

	}

	/**
	 * Delegates to all the IPaintStrategies in the composite. 
	 * Paints using given Graphics context using the supplied AffineTransform. 
	 * Called by the inherited paint method.
	 * @param g The Graphics context to paint upon.
	 * @param host The host Ball
	 * @param at The AffineTransform to use
	 */
	@Override
	public void paintXfrm(Graphics g, Ball host, AffineTransform at) {
		// TODO Auto-generated method stub
		for (APaintStrategy ps : pstrats) {
			ps.paintXfrm(g, host, at);
		}
	}

}
