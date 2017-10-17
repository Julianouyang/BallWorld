package model;

import util.IDispatcher;

/**
 * Interface that represents commands sent through the dispatcher to process the balls
 */
public interface IBallCmd {
	/**
	 * The method run by the ball's update method which is called 
	 * when the ball is updated by the dispatcher.
	 * @param context The ball that is calling this method.   
	 * @param disp The Dispatcher that sent the command out.
	 */
	public abstract void apply(Ball context, IDispatcher<IBallCmd> dispatcher);

	/**
	 * No-op "null" adapter
	 */
	public static final IBallCmd NULL_OBJECT = new IBallCmd() {
		@Override
		public void apply(Ball context, IDispatcher<IBallCmd> dispatcher) {
			// TODO Auto-generated method stub	
		}
	};

}
