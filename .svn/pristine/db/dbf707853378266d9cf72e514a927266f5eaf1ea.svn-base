package model.strategy;

import model.Ball;
import model.IBallCmd;
import model.IUpdateStrategy;
import model.strategy.interact.IInteractStrategy;
import model.strategy.interact.MultiInteractStrategy;
import util.IDispatcher;
import util.Randomizer;

/**
 * A strategy that change a ball's color when colliding with other ball
 * Must use with collide strategy
 * @param <TDispMsg>
 */
public class ChangeColor<TDispMsg> implements IUpdateStrategy<TDispMsg> {

	@Override
	public void init(Ball context) {
		// TODO Auto-generated method stub
		context.setInteractStrategy(new MultiInteractStrategy(context.getInteractStrategy(), new IInteractStrategy() {
			@Override
			public void interact(Ball context, Ball target, IDispatcher<IBallCmd> disp) {
				// TODO Auto-generated method stub
				target.setColor(Randomizer.Singleton.randomColor());
			}
		}));

	}

	@Override
	public void updateState(Ball context, IDispatcher<TDispMsg> dispatcher) {
		// TODO Auto-generated method stub
	}

}
