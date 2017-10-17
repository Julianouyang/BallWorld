package controller;

import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Graphics;

import model.*;
import view.IView2ModelPaintAdapter;
import view.IView2ModelControlAdapter;
import view.BallWorldView;

/**
 * The Controller class
 */
public class BallWorldController {
	/**
	 * the ball world model
	 */
	private BallWorldModel ballWorldModel;
	/**
	 * the GUI Window view.
	 */
	private BallWorldView<IUpdateStrategyFac<IBallCmd>, IPaintStrategyFac> ballWorldView;

	/**
	 * Controller constructor builds the system
	 */
	public BallWorldController() {

		// set the view field
		ballWorldView = new BallWorldView<IUpdateStrategyFac<IBallCmd>, IPaintStrategyFac>(
				new IView2ModelPaintAdapter() {
					@Override
					/**
					 * Pass the update request to the model.
					 * @param g The Graphics object the balls use to draw themselves.
					 */
					public void paint(Graphics g) {
						ballWorldModel.update(g);
					}
				}, new IView2ModelControlAdapter<IUpdateStrategyFac<IBallCmd>, IPaintStrategyFac>() {
					@Override
					/**
					* Returns an IStrategyFac that can instantiate the strategy specified
					* by classname. Returns null if classname is null. The toString() of
					* the returned factory is the classname.
					* @param classname  Shortened name of desired strategy 
					* @return A factory to make that strategy
					*/
					public IUpdateStrategyFac<IBallCmd> addStrategy(String classname) {
						//System.out.println("add");
						return ballWorldModel.makeStrategyFac(classname);
					}

					@Override
					/**
					* Add a ball to the system with a strategy as given by the given IStrategyFac
					* @param selectedItem The fully qualified name of the desired strategy.
					*/
					public void loadBall(IUpdateStrategyFac<IBallCmd> selectedItem1, IPaintStrategyFac selectedItem2) {
						if (selectedItem1 == null) {
							return;
						}
						//				System.out.println("controller loading the ball");
						ballWorldModel.loadBall(selectedItem1.make(), selectedItem2.make());
					}

					@Override
					/**
					* Returns an IStrategyFac that can instantiate a MultiStrategy with the
					* two strategies made by the two given IStrategyFac objects. Returns
					* null if either supplied factory is null. The toString() of the
					* returned factory is the toString()'s of the two given factories,
					* concatenated with "-".             * 
					* @param selectedItem1 An IStrategyFac for a strategy
					* @param selectedItem2 An IStrategyFac for a strategy
					* @return An IStrategyFac for the composition of the two strategies
					*/
					public IUpdateStrategyFac<IBallCmd> combineStrategies(IUpdateStrategyFac<IBallCmd> selectedItem1,
							IUpdateStrategyFac<IBallCmd> selectedItem2) {
						return ballWorldModel.combineStrategyFacs(selectedItem1, selectedItem2);
					}

					/**
					* 
					* @see view.IView2ModelControlAdapter#switchStrategy(java.lang.Object)
					*/
					public void switchStrategy(IUpdateStrategyFac<IBallCmd> selectedItem) {
						ballWorldModel.switchSwitcherStrategy(selectedItem.make());
					}

					/**
					* Clear all the balls in the model
					* @see view.IView2ModelControlAdapter#clearAllBalls()
					*/
					@Override
					public void clearAllBalls() {
						ballWorldModel.clearBalls();
					}

					/**
					 * make a switcher ball
					 */
					@Override
					public void makeSwitcherBall(IPaintStrategyFac selectedItem) {
						ballWorldModel.makeSwitcherBall(selectedItem.make());
					}

					/**
					 * add a paint strategy factory to the droplist
					 */
					@Override
					public IPaintStrategyFac addPaintStrategy(String text) {
						//System.out.println("add");
						return ballWorldModel.makePaintStrategyFac(text);
					}
				});

		// set the model field
		ballWorldModel = new BallWorldModel(new IModel2ViewAdapter() {

			@Override
			/**
			 * tell GUI to update
			 */
			public void update() {
				ballWorldView.update();
			}

			@Override
			/**
			 * get the canvas of the ball 
			 */
			public Container getCanvas() {
				return ballWorldView.getCanvas();
			}

		});

	}

	/**
	 * Start the system
	 */
	public void start() {
		ballWorldModel.start(); // It is usually better to start the model first but not always.
		ballWorldView.start();
	}

	/**
	 * Launch the application.
	 * @param args Arguments given by the system or command line.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() { // Java specs say that the system must be constructed on the GUI event thread.
			public void run() {
				try {
					BallWorldController controller = new BallWorldController(); // instantiate the system
					controller.start(); // start the system
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
