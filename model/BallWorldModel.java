package model;

import util.*;

import java.awt.Container;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.lang.reflect.Constructor;
import javax.swing.Timer;
import model.strategy.MultiStrategy;
import model.strategy.SwitcherStrategy;

/**
 * the ball world model class
 */
public class BallWorldModel {

	/**
	 * model to view adapter.
	 */
	private IModel2ViewAdapter _model2ViewAdpt = IModel2ViewAdapter.NULL_OBJECT; // Insures that the adapter is always valid

	/**
	 * the dispatcher.
	 */
	private IDispatcher<IBallCmd> _dispatcher = new SetDispatcherSequential<IBallCmd>();

	/**
	 * Randomizer utility object, used for random values for new balls
	 */
	private Randomizer _randomizer = Randomizer.Singleton;

	/**
	 * define velocity of a ball
	 */
	private Rectangle _maxVel = new Rectangle(15, 15, 15, 15);

	/**
	 * initialize the canvas
	 */
	private Container _canvas = null;

	/**
	 * The one switcher strategy instance in the system.
	 */
	private SwitcherStrategy<IBallCmd> switcher = new SwitcherStrategy<IBallCmd>();

	/**
	 * setup the timer.
	 */
	private int timeSlice = 50; //inform view to update every 50 milliseconds
	private Timer timer = new Timer(timeSlice, (e) -> _model2ViewAdpt.update());

	/**
	 * constructor for ball world model.
	 * @param model2ViewAdpt the model to view adapter
	 */
	public BallWorldModel(IModel2ViewAdapter model2ViewAdpt) {
		_model2ViewAdpt = model2ViewAdpt;
	}

	/**
	 * This is the method that is called by the view's adapter to the model, i.e. is called by IView2ModelAdapter.paint().
	 * This method will update the sprites's painted locations by painting all the sprites
	 * onto the given Graphics object.
	 * @param g The Graphics object from the view's paintComponent() call.
	 */
	public void update(Graphics g) {
		_dispatcher.dispatch(new IBallCmd() {
			@Override
			/**
			 * using command dispatcher to update the ball
			 */
			public void apply(Ball context, IDispatcher<IBallCmd> dispatcher) {
				// TODO Auto-generated method stub
				context.updateState(dispatcher);
				context.move();
				context.bounce();
				context.paintBall(g);
			}
		});
	}

	/**
	 * start the timer
	 */
	public void start() {
		_canvas = this._model2ViewAdpt.getCanvas();
		timer.start();
	}

	/**
	 * This method was used to load ball;
	*/
	private Ball makeBall(IUpdateStrategy<IBallCmd> updateStrategy, IPaintStrategy paintStrategy) {
		Ball newBall = new Ball(_randomizer.randomInt(5, 25), _randomizer.randomLoc(_canvas.getSize()),
				_randomizer.randomVel(_maxVel), _canvas, _randomizer.randomColor(), updateStrategy, paintStrategy);
		System.out.println("new Ball is " + newBall);
		return newBall;
	}

	/**
	 * load a ball with the strategy
	 * @param strategy the strategy for a ball
	*/
	public void loadBall(IUpdateStrategy<IBallCmd> updateStrategy, IPaintStrategy paintStrategy) {
		_dispatcher.addObserver(makeBall(updateStrategy, paintStrategy));
	}

	/**
	 * clear all balls in the Observer.
	 */
	public void clearBalls() {
		_dispatcher.deleteObservers();
	}

	/**
	 * Change the switcher strategy's decoree to the supplied strategy
	 * @param strat the new decoree for the switcher
	 */
	public void switchSwitcherStrategy(IUpdateStrategy<IBallCmd> strat) {
		switcher.setStrategy(strat);
	}

	/**
	 * make switcher ball
	 * @param Pstrat paint strategy for the ball
	 */
	public void makeSwitcherBall(IPaintStrategy Pstrat) {
		loadBall(switcher, Pstrat);
	}

	/**
	 * Returns an IStrategyFac that can instantiate the strategy specified by
	 * classname. Returns a factory for a beeping error strategy if classname is null. 
	 * The toString() of the returned factory is the classname.
	 * 
	 * @param classname  Shortened name of desired strategy
	 * @return A factory to make that strategy
	 */
	public IUpdateStrategyFac<IBallCmd> makeStrategyFac(String classname) {
		if (null == classname)
			return new IUpdateStrategyFac.NullFactory<IBallCmd>().makeFactory();
		return new IUpdateStrategyFac<IBallCmd>() {
			/**
			 * Instantiate a strategy corresponding to the given class name.
			 * @return An IUpdateStrategy instance
			 */
			public IUpdateStrategy<IBallCmd> make() {
				return loadUpdateStrategy(classname);
			}

			/**
			 * Return the given class name string
			 */
			public String toString() {
				return classname;
			}
		};
	}

	/**
	 * Returns an IStrategyFac that can instantiate a MultiStrategy with the two
	 * strategies made by the two given IStrategyFac objects. Returns null if
	 * either supplied factory is null. The toString() of the returned factory
	 * is the toString()'s of the two given factories, concatenated with "-". 
	 * If either factory is null, then a factory for a beeping error strategy is returned.
	 * 
	 * @param stratFac1 An IStrategyFac for a strategy
	 * @param stratFac2 An IStrategyFac for a strategy
	 * @return An IStrategyFac for the composition of the two strategies
	 */
	public IUpdateStrategyFac<IBallCmd> combineStrategyFacs(final IUpdateStrategyFac<IBallCmd> stratFac1,
			final IUpdateStrategyFac<IBallCmd> stratFac2) {
		if (null == stratFac1 || null == stratFac2)
			return new IUpdateStrategyFac.NullFactory<IBallCmd>().makeFactory();//still not work.
		return new IUpdateStrategyFac<IBallCmd>() {
			/**
			 * Instantiate a new MultiStrategy with the strategies from the given strategy factories
			 * @return A MultiStrategy instance
			 */
			public IUpdateStrategy<IBallCmd> make() {
				return new MultiStrategy<IBallCmd>(stratFac1.make(), stratFac2.make());
			}

			/**
			 * Return a string that is the toString()'s of the given strategy factories concatenated with a "-"
			 */
			public String toString() {
				return stratFac1.toString() + "-" + stratFac2.toString();
			}
		};
	}

	/**
	 * Uses dynamic class loading to load and instantiate an IUpdateStrategy implementation, given its class name. 
	 * If the classname is invalid, a beeping error strategy instance is returned.
	 * @param classname The fully qualified classname of a strategy
	 * @return An IUpdateStrategy instance
	 */
	@SuppressWarnings("unchecked")
	public IUpdateStrategy<IBallCmd> loadUpdateStrategy(String classname) {
		try {
			Object[] args = new Object[] {}; //match the constructors
			Constructor<?> cs[] = Class.forName("model.strategy." + classname).getConstructors(); //get all the constructors
			Constructor<?> c = null;
			for (int i = 0; i < cs.length; i++) { // find the first constructor with the right number of input parameters
				if (args.length == (cs[i]).getParameterTypes().length) {
					c = cs[i];
					break;
				}
			}
			return (IUpdateStrategy<IBallCmd>) c.newInstance(args); // Call the constructor. Will throw a null pointer exception if no constructor with the right number of input parameters was found.
		} catch (Exception ex) {
			System.err.println("Class " + classname + " failed to load. \nException = \n" + ex);
			ex.printStackTrace(); // print the stack trace to help in debugging.
			return null;
		}
	}

	/**
	 * Returns an IPaintStrategyFac that can instantiate the strategy specified by classname.
	 * @param classname Shortened name of desired strategy
	 * @return A factory to make that strategy
	 */
	public IPaintStrategyFac makePaintStrategyFac(String classname) {
		if (null == classname)
			return IPaintStrategyFac.NULL_OBJECT;
		return new IPaintStrategyFac() {
			/**
			 * Instantiate a strategy corresponding to the given class name.
			 * @return An IUpdateStrategy instance
			 */
			public IPaintStrategy make() {
				return loadPaintStrategy(classname);
			}

			/**
			 * Return the given class name string
			 */
			public String toString() {
				return classname;
			}
		};
	}

	/**
	 * Uses dynamic class loading to load and instantiate an IUpdateStrategy implementation, given its class name. 
	 * If the classname is invalid, a beeping error strategy instance is returned.
	 * @param classname The fully qualified classname of a strategy
	 * @return An IUpdateStrategy instance
	 */
	public IPaintStrategy loadPaintStrategy(String classname) {
		try {
			Object[] args = new Object[] {}; //match the constructors
			Constructor<?> cs[] = Class.forName("model.paint.paintStrategy." + classname).getConstructors(); //get all the constructors
			Constructor<?> c = null;
			for (int i = 0; i < cs.length; i++) { // find the first constructor with the right number of input parameters
				if (args.length == (cs[i]).getParameterTypes().length) {
					c = cs[i];
					break;
				}
			}
			return (IPaintStrategy) c.newInstance(args); // Call the constructor. Will throw a null pointer exception if no constructor with the right number of input parameters was found.
		} catch (Exception ex) {
			System.err.println("Class " + classname + " failed to load. \nException = \n" + ex);
			ex.printStackTrace(); // print the stack trace to help in debugging.
			return null;
		}
	}

}
