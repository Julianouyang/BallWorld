package model.paint;

import java.awt.*;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.awt.image.ImageObserver;

import model.Ball;

/**
 * Paint strategy that paints an image from a file, scaled to the host Ball's radius. 
 *
 */
public class ImagePaintStrategy extends APaintStrategy {

	/**
	 * The percentage of the average of the width and height of the image that defines a unit radius for the image.
	 */
	private double fillFactor;

	/**
	 * The image to paint
	 */
	private Image image;

	/**
	 * ImageObserver needed for some image operations
	 */
	private ImageObserver imageObs;

	/**
	 * A local affine transform used to transform the image into its unit size and location.
	 */
	protected AffineTransform localAT = new AffineTransform();

	/**
	 * Ratio of the unit radius circle to the effective radius size of the image.
	 */
	private double scaleFactor;

	protected AffineTransform tempAT;

	/**
	 * Constructor that takes an external AffineTransform, the filename of the image to paint and a fill factor of the image.
	 * @param _at The AffineTransform to use internally.
	 * @param _filename The filename of the image file to use.
	 * @param _fillFactor The ratio of the desired average diameter of the image to the actual average of the image's width and height.
	 */
	public ImagePaintStrategy(AffineTransform _at, String _filename, double _fillFactor) {

		super(_at);

		fillFactor = _fillFactor;

		try {
			image = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource(_filename));
		} catch (Exception e) {
			System.err.println("ImagePaintStrategy: Error reading file: " + _filename + "\n" + e);
		}
	}

	/**
	 * Constructor that takes the image filename and fill factor. 
	 * @param _filename The filename of the image file to use.
	 * @param _fillFactor The ratio of the desired average radius of the image to the actual average of the image's width and height.
	 */
	public ImagePaintStrategy(String _filename, double _fillFactor) {

		this(new AffineTransform(), _filename, _fillFactor);

	}

	/**
	 * Initializes the internal ImageObserver reference from the host Ball Also calculates the net scale factor for the image.
	 * @param host The host Ball
	 */
	public void init(Ball host) {

		imageObs = host.getContainer();

		MediaTracker mt = new MediaTracker(host.getContainer());
		mt.addImage(image, 1);
		try {
			mt.waitForAll();
		} catch (Exception e) {
			System.out.println("ImagePaintStrategy.init(): Error waiting for image.  Exception = " + e);
		}

		scaleFactor = 2.0 / (fillFactor * (image.getWidth(imageObs) + image.getHeight(imageObs)) / 2.0);
	}

	/**
	 * Draws the image on the given Graphics context using the given affine transform in combination with the local affine transform.
	 * @param g The graphics context to paint on
	 * @param host The host Ball
	 * @param at The AffineTransform to use
	 */
	@Override
	public void paintXfrm(Graphics g, Ball host, AffineTransform at) {
		// TODO Auto-generated method stub
		localAT.setToScale(scaleFactor, scaleFactor);
		localAT.translate(-image.getWidth(imageObs) / 2.0, -image.getHeight(imageObs) / 2.0);
		localAT.preConcatenate(at);
		((Graphics2D) g).drawImage(image, localAT, imageObs);
	}

}
