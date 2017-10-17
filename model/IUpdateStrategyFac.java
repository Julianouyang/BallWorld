package model;

import util.IDispatcher;

/**
 * An interface that defines a factory that instantiates
 * a specific IUpdateStrategy
 */
public interface IUpdateStrategyFac<TDispMsg> {
	/**
	   * Instantiate the specific IUpdateStrategy for which this factory is defined.
	   * @return An IUpdateStrategy instance.
	   */
	public IUpdateStrategy<TDispMsg> make();

	/**
	 * A factory for a typed null strategy object.
	 *
	 * @param <TDispMsg>
	 */
	public static final class NullFactory<TDispMsg> implements IUpdateStrategyFac<TDispMsg> {

		@Override
		/**
		 * Update the state of the context Ball.
		 */
		public IUpdateStrategy<TDispMsg> make() {
			// TODO Auto-generated method stub
			return new IUpdateStrategy<TDispMsg>() {
				@Override
				public void init(Ball context) {
					// TODO Auto-generated method stub
				}

				@Override
				public void updateState(Ball context, IDispatcher<TDispMsg> dispatcher) {
					// TODO Auto-generated method stub
				}
			};
		}

		/**
		 * makeFactory function
		 * @return
		 */
		public IUpdateStrategyFac<TDispMsg> makeFactory() {
			return new IUpdateStrategyFac<TDispMsg>() {
				@Override
				public IUpdateStrategy<TDispMsg> make() {
					// TODO Auto-generated method stub
					return new IUpdateStrategy<TDispMsg>() {
						@Override
						public void init(Ball context) {
							// TODO Auto-generated method stub
						}

						@Override
						public void updateState(Ball context, IDispatcher<TDispMsg> dispatcher) {
							// TODO Auto-generated method stub
						}
					};
				}
			};
		}
	}
}
