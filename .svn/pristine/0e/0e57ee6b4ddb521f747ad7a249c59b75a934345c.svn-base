package model;

/**
 * An interface that defines a factory that instantiates a specific IUpdateStrategy
 *
 */
public interface IPaintStrategyFac {
	/**
	 * Instantiate the specific IUpdateStrategy for which this factory is defined.
	 * @return n IUpdateStrategy instance.
	 */
	public IPaintStrategy make();

	/**
	 * no-op
	 */
	public static final IPaintStrategyFac NULL_OBJECT = new IPaintStrategyFac() {
		public IPaintStrategy make() {
			return null;
		}
	};
}
