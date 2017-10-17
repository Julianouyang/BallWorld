package model.strategy;

import model.Ball;
import model.IBallCmd;
import model.IUpdateStrategy;
import model.strategy.interact.IInteractStrategy;
import model.strategy.interact.MultiInteractStrategy;
import util.IDispatcher;

/**
 * 
 * kills whomever the ball collides with and increases its area by the killed ball's area.  
 * @param <TDispMsg>
 */
public class Eat<TDispMsg> implements IUpdateStrategy<TDispMsg> {

	@Override
	public void init(Ball context) {
		// TODO Auto-generated method stub
		context.setInteractStrategy(new MultiInteractStrategy(context.getInteractStrategy(), new IInteractStrategy() {

			@Override
			public void interact(Ball context, Ball target, IDispatcher<IBallCmd> disp) {
				// TODO Auto-generated method stub
				context.setRadius(context.getRadius() + target.getRadius());
				disp.deleteObserver(target);
			}
		}));

	}

	@Override
	public void updateState(Ball context, IDispatcher<TDispMsg> dispatcher) {
		// TODO Auto-generated method stub

	}

}
