package model.strategy;

import java.awt.Rectangle;
import util.IDispatcher;
import util.Randomizer;
import model.*;

/**
 * The wander strategy class.
 */
public class Wander<TDispMsg> implements IUpdateStrategy<TDispMsg> {
	/**
	 * initiate a ball
	 */
	@Override
	public void init(Ball context) {
		// TODO Auto-generated method stub

	}

	/**
	 * Change the velocity of the ball to a random value
	 * @see ball.IUpdateStrategy#updateState(ball.Ball)
	 */
	@Override
	public void updateState(Ball context, IDispatcher<TDispMsg> dispatcher) {
		// TODO Auto-generated method stub
		context.setVelocity(Randomizer.Singleton.randomVel(new Rectangle(3, 3)));
	}

}
