package model.strategy;

import model.*;
import util.IDispatcher;

/**
 * The combing strategy class.
 */
public class MultiStrategy<TDispMsg> implements IUpdateStrategy<TDispMsg> {

	/**
	 * the first strategy to be combined
	 */
	private IUpdateStrategy<TDispMsg> s1;
	/**
	 * the second strategy to be combined
	 */
	private IUpdateStrategy<TDispMsg> s2;

	/**
	 * generate a new MultiStrategy with two strategies
	 * @param _s1 the first strategy to be combined
	 * @param _s2 the second strategy to be combined
	 */
	public MultiStrategy(IUpdateStrategy<TDispMsg> _s1, IUpdateStrategy<TDispMsg> _s2) {
		s1 = _s1;
		s2 = _s2;
	}

	/**
	 * Change the both the s1 strategy and s2 strategy of the ball
	 */
	//	@Override
	//	public void updateState(Ball context, Dispatcher dispatcher) {
	//		// TODO Auto-generated method stub
	//		s1.updateState(context, dispatcher);
	//		s2.updateState(context, dispatcher);
	//	}

	@Override
	public void init(Ball context) {
		// TODO Auto-generated method stub
		//		context.setInteractStrategy(new MultiInteractStrategy((IInteractStrategy)s1, (IInteractStrategy)s2));
		s1.init(context);
		s2.init(context);
		System.out.println("multiStrategy inited");

	}

	@Override
	public void updateState(Ball context, IDispatcher<TDispMsg> dispatcher) {
		// TODO Auto-generated method stub
		s1.updateState(context, dispatcher);
		s2.updateState(context, dispatcher);

	}

}
