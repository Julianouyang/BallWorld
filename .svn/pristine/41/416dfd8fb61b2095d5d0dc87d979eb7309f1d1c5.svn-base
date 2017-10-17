package model.strategy;

import util.IDispatcher;
import util.Randomizer;
import model.*;

/**
 * The color changing strategy class.
 */
public class Color<TDispMsg> implements IUpdateStrategy<TDispMsg> {

	@Override
	public void init(Ball context) {
		// TODO Auto-generated method stub

	}

	/**
	 * Change the color of the ball to a random value
	 * @see ball.IUpdateStrategy#updateState(ball.Ball)
	 */
	@Override
	public void updateState(Ball context, IDispatcher<TDispMsg> dispatcher) {
		// TODO Auto-generated method stub
		context.setColor(Randomizer.Singleton.randomColor());
	}

}
