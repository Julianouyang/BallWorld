package util;

public class SetDispatcherParallel<TDispMsg> extends ASetDispatcher<TDispMsg> {

	@Override
	/**
	 * provided
	 * A CopyOnWriteArraySet-based IDispatcher that dispatches to its IObservers in parallel.
	 */
	public void dispatch(TDispMsg msg) {
		// TODO Auto-generated method stub
		getCollection().parallelStream().forEach(o -> {
			o.update(this, msg);
		});
	}

}
