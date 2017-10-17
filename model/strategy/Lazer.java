package model.strategy;

import model.Ball;
import model.IBallCmd;
import model.IUpdateStrategy;
import model.strategy.interact.IInteractStrategy;
import model.strategy.interact.MultiInteractStrategy;
import util.IDispatcher;

public class Lazer<TDispMsg> implements IUpdateStrategy<TDispMsg> {

	@Override
	public void init(Ball context) {
		// TODO Auto-generated method stub
		context.setInteractStrategy(new MultiInteractStrategy(context.getInteractStrategy(), new IInteractStrategy() {

			@Override
			public void interact(Ball context, Ball target, IDispatcher<IBallCmd> disp) {
				// TODO Auto-generated method stub
				disp.deleteObserver(target);
			}

		}));
	}

	@SuppressWarnings("unchecked")
	@Override
	public void updateState(Ball context, IDispatcher<TDispMsg> dispatcher) {
		// TODO Auto-generated method stub
		dispatcher.dispatch((TDispMsg) new IBallCmd() {

			@Override
			public void apply(Ball other, IDispatcher<IBallCmd> dispatcher) {
				// TODO Auto-generated method stub
				if (other != context) {
					if ((context.getLocation().getY() - other.getLocation().getY())
							/ (context.getLocation().getX() - other.getLocation().getX()) == context.getVelocity()
									.getY() / context.getVelocity().getX()) {
						context.getInteractStrategy().interact(context, other, dispatcher);
					}
				}

			}

		});
	}

}
