package util;

/**
 * provided
 * A CopyOnWriteArraySet-based IDispatcher that dispatches to its IObservers in parallel.
 * @param <TDispMsg>
 */
public class SetDispatcherSequential<TDispMsg> extends ASetDispatcher<TDispMsg> {

	@Override
	public void dispatch(TDispMsg msg) {
		// TODO Auto-generated method stub
		getCollection().forEach(o -> {
			o.update(this, msg);
		});
	}

}
