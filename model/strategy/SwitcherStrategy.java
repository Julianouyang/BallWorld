package model.strategy;

import model.*;
import util.IDispatcher;

/**
 * The switch strategy class.
 */
public class SwitcherStrategy<TDispMsg> implements IUpdateStrategy<TDispMsg> {

	/**
	 * the strategy of switcher, default set to straight
	 */
	private IUpdateStrategy<TDispMsg> strategy = new Straight<TDispMsg>();

	/**
	 * Set the strategy of the switcher
	 * @param _strategy the new strategy of the switcher
	 */
	public void setStrategy(IUpdateStrategy<TDispMsg> _strategy) {
		strategy = _strategy;
	}

	/**
	 * Get the strategy of switcher
	 * @return the strategy of switcher
	 */
	public IUpdateStrategy<TDispMsg> getStrategy() {
		return this.strategy;
	}

	/**
	 * initiate a ball
	 */
	@Override
	public void init(Ball context) {
		// TODO Auto-generated method stub

	}

	/**
	 * make the strategy of ball to the switcher's strategy
	 */
	@Override
	public void updateState(Ball context, IDispatcher<TDispMsg> dispatcher) {
		// TODO Auto-generated method stub
		strategy.updateState(context, dispatcher);

	}
}
