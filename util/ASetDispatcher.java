package util;

import java.util.concurrent.CopyOnWriteArraySet;

/**
 *  A Collection-based Dispatcher that uses a CopyOnWriteArraySet.
 *  @param <TDispMsg>
 */
public abstract class ASetDispatcher<TDispMsg> extends ACollectionDispatcher<TDispMsg> {
	/**
	 * The constructor for the class that supplies a CopyOnWriteArraySet 
	 * instance to the superclass constructor.
	 */
	public ASetDispatcher() {
		super(new CopyOnWriteArraySet<>());
		// TODO Auto-generated constructor stub
	}

}
