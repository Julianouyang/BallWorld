package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JComboBox;
import java.awt.Insets;

/**
 * the GUIWindow view class
 */
public class BallWorldView<TStrategyDropListItem, TPaintDropListItem> extends JFrame {
	/**
	 * serialization id
	 */
	private static final long serialVersionUID = 3250273989453494365L;

	/**
	 * the content panel
	 */
	private final JPanel centerPanel = new JPanel() {
		private static final long serialVersionUID = 8303517520311377718L;

		public void paintComponent(Graphics g) {
			super.paintComponent(g); // Do everything normally done first, e.g. clear the screen.
			_view2ModelPaintAdapter.paint(g);
		}//call back to the model to paint the balls
	};
	/**
	 * the north control panel, containing the ball kind input, the load and clear buttons 
	 */
	private final JPanel northPanel = new JPanel();

	/**
	 * create sub panel
	 */
	private final JPanel panelA = new JPanel();
	private final JPanel panelB = new JPanel();
	private final JPanel panelC = new JPanel();
	private final JPanel panelD = new JPanel();
	private final JPanel panelE = new JPanel();

	/**
	 * the clear all ball button
	 */
	private final JButton btnClear = new JButton("Clear");
	/**
	 * the ball kind input text field
	 */
	private final JTextField txtAddtolist = new JTextField();
	/**
	 * the view to controller adapter to load a new ball and clear all balls methods.
	 */
	private IView2ModelControlAdapter<TStrategyDropListItem, TPaintDropListItem> _view2ModelControlAdapter;
	/**
	 * the view to controller adapter to paint the ball on canvas
	 */
	private IView2ModelPaintAdapter _view2ModelPaintAdapter = IView2ModelPaintAdapter.NULL_OBJECT;

	/**
	 * define combo boxes
	 */
	private final JComboBox<TStrategyDropListItem> _list1DL = new JComboBox<TStrategyDropListItem>();
	private final JComboBox<TStrategyDropListItem> _list2DL = new JComboBox<TStrategyDropListItem>();
	private final JComboBox<TPaintDropListItem> _paintDL = new JComboBox<TPaintDropListItem>();
	/**
	 * define all buttons
	 */
	private final JButton btnAddtolist = new JButton("Add To List");
	private final JButton btnMSB = new JButton("Make Selected Ball");
	private final JButton btnMS = new JButton("Make Switcher");
	private final JButton btnSwitch = new JButton("Switch");
	private final JButton btnCombine = new JButton("Combine!");
	private final JButton btnAdd = new JButton("Add");
	/**
	 * define the text field
	 */
	private final JTextField txtPaintStrategy = new JTextField();

	/**
	 * start to show the frame.
	 */
	public void start() {
		setVisible(true);
	}

	/**
	 * repaint myPanel.
	 */
	public void update() {
		centerPanel.repaint();
	}

	/**
	 * Create the frame.
	 * @param view2ModelPaintAdapter the view to model adapter on painting.
	 * @param iView2ModelControlAdapter the view to model adapter on controlling.
	 */
	public BallWorldView(IView2ModelPaintAdapter view2ModelPaintAdapter,
			IView2ModelControlAdapter<TStrategyDropListItem, TPaintDropListItem> iView2ModelControlAdapter) {
		this._view2ModelPaintAdapter = view2ModelPaintAdapter;
		this._view2ModelControlAdapter = iView2ModelControlAdapter;
		initGUI();
	}

	/**
	 * initialize the GUI
	 */
	private void initGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 500, 750, 600);
		setResizable(true);

		/**
		 * set up center panel
		 */
		centerPanel.setBackground(new Color(249, 244, 244));
		getContentPane().add(centerPanel, BorderLayout.CENTER);
		centerPanel.setLayout(new BorderLayout(2, 2));

		/**
		 * set up northPanel
		 */
		northPanel.setBackground(new Color(66, 215, 244));
		getContentPane().add(northPanel, BorderLayout.NORTH);

		/**
		 * set up panelA
		 */
		panelA.setBackground(new Color(66, 215, 244));
		panelA.setBorder(BorderFactory.createLineBorder(Color.white));
		northPanel.add(panelA);
		GridBagLayout gbl_panelA = new GridBagLayout();
		gbl_panelA.columnWidths = new int[] { 0, 0 };
		gbl_panelA.rowHeights = new int[] { 0, 0, 0 };
		gbl_panelA.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_panelA.rowWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		panelA.setLayout(gbl_panelA);

		/**
		 * set up text field: add to list
		 */
		txtAddtolist.setText("Straight");
		GridBagConstraints gbc_txtAddtolist = new GridBagConstraints();
		gbc_txtAddtolist.insets = new Insets(0, 0, 5, 0);
		gbc_txtAddtolist.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtAddtolist.gridx = 0;
		gbc_txtAddtolist.gridy = 0;
		panelA.add(txtAddtolist, gbc_txtAddtolist);
		txtAddtolist.setToolTipText("Type in the strategy you want for the ball");

		/**
		 * set up button: add strategy
		 */
		GridBagConstraints gbc_btnAddtolist = new GridBagConstraints();
		gbc_btnAddtolist.gridx = 0;
		gbc_btnAddtolist.gridy = 1;
		btnAddtolist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String strategyName = txtAddtolist.getText();
				if (strategyName == null)
					return;
				TStrategyDropListItem o = _view2ModelControlAdapter.addStrategy(strategyName);
				_list1DL.insertItemAt(o, 0);
				_list1DL.setSelectedItem(o);
				_list2DL.insertItemAt(o, 0);
				_list2DL.setSelectedItem(o);
			}
		});
		panelA.add(btnAddtolist, gbc_btnAddtolist);
		btnAddtolist.setToolTipText("Add any of following strategy to list: "
				+ "					Breath, Color, Dancing, Spiral, and Curve.");

		/**
		*  set up panelB
		*/
		panelB.setBackground(new Color(66, 215, 244));
		panelB.setBorder(BorderFactory.createLineBorder(Color.white));
		northPanel.add(panelB);

		GridBagLayout gbl_panelB = new GridBagLayout();
		gbl_panelB.columnWidths = new int[] { 0, 0 };
		gbl_panelB.rowHeights = new int[] { 0, 0, 0, 0, 0 };
		gbl_panelB.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_panelB.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panelB.setLayout(gbl_panelB);

		/**
		*	set up button: make selected ball
		*/
		GridBagConstraints gbc_btnMSB = new GridBagConstraints();
		gbc_btnMSB.insets = new Insets(0, 0, 5, 0);
		gbc_btnMSB.gridx = 0;
		gbc_btnMSB.gridy = 0;
		btnMSB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TStrategyDropListItem item1 = _list1DL.getItemAt(_list1DL.getSelectedIndex());
				TPaintDropListItem item2 = _paintDL.getItemAt(_paintDL.getSelectedIndex());
				if (item1 == null || item2 == null)
					return;
				_view2ModelControlAdapter.loadBall(item1, item2);
			}
		});
		panelB.add(btnMSB, gbc_btnMSB);
		btnMSB.setToolTipText("Make a ball with the strategy in the upper droplist");

		/**
		*   add combo boxes to canvas
		*/
		GridBagConstraints gbc_list1DL = new GridBagConstraints();
		gbc_list1DL.insets = new Insets(0, 0, 5, 0);
		gbc_list1DL.fill = GridBagConstraints.HORIZONTAL;
		gbc_list1DL.gridx = 0;
		gbc_list1DL.gridy = 1;
		panelB.add(_list1DL, gbc_list1DL);

		GridBagConstraints gbc_list2DL = new GridBagConstraints();
		gbc_list2DL.insets = new Insets(0, 0, 5, 0);
		gbc_list2DL.fill = GridBagConstraints.HORIZONTAL;
		gbc_list2DL.gridx = 0;
		gbc_list2DL.gridy = 2;
		panelB.add(_list2DL, gbc_list2DL);

		/**
		*	set up button: combine two strategies
		*/
		GridBagConstraints gbc_btnCombine = new GridBagConstraints();
		gbc_btnCombine.gridx = 0;
		gbc_btnCombine.gridy = 3;
		btnCombine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TStrategyDropListItem o1 = _list1DL.getItemAt(_list1DL.getSelectedIndex());
				TStrategyDropListItem o2 = _list2DL.getItemAt(_list2DL.getSelectedIndex());
				if (o1 == null || o2 == null)
					return;
				TStrategyDropListItem oCombine = _view2ModelControlAdapter.combineStrategies(o1, o2);
				_list1DL.insertItemAt(oCombine, 0);
				_list2DL.insertItemAt(oCombine, 0);
			}
		});
		panelB.add(btnCombine, gbc_btnCombine);
		btnCombine.setToolTipText("Combine two selected strategies and make a new one");

		/**
		 * set up panel C
		 */
		panelC.setBackground(new Color(66, 215, 244));
		panelC.setBorder(BorderFactory.createLineBorder(Color.white));
		northPanel.add(panelC);
		GridBagLayout gbl_panelC = new GridBagLayout();
		gbl_panelC.columnWidths = new int[] { 0, 0 };
		gbl_panelC.rowHeights = new int[] { 0, 0, 0 };
		gbl_panelC.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
		gbl_panelC.rowWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		panelC.setLayout(gbl_panelC);

		/**
		 * set up button: Make Switcher
		 */
		GridBagConstraints gbc_btnMS = new GridBagConstraints();
		gbc_btnMS.insets = new Insets(0, 0, 5, 0);
		gbc_btnMS.gridx = 0;
		gbc_btnMS.gridy = 0;
		btnMS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TPaintDropListItem item = _paintDL.getItemAt(_paintDL.getSelectedIndex());
				_view2ModelControlAdapter.makeSwitcherBall(item);
			}
		});
		panelC.add(btnMS, gbc_btnMS);
		btnMS.setToolTipText("Add a switcher Ball");

		/**
		 * set up switch button
		 */
		GridBagConstraints gbc_btnSwitch = new GridBagConstraints();
		gbc_btnSwitch.gridx = 0;
		gbc_btnSwitch.gridy = 1;
		btnSwitch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TStrategyDropListItem o = _list1DL.getItemAt(_list1DL.getSelectedIndex());
				if (o == null)
					return;
				_view2ModelControlAdapter.switchStrategy(o);
			}
		});
		panelC.add(btnSwitch, gbc_btnSwitch);
		btnSwitch.setToolTipText("Switch the ball");

		/**
		 * set up 4th panel
		 */
		panelD.setBackground(new Color(66, 215, 244));
		panelD.setBorder(BorderFactory.createLineBorder(Color.white));
		northPanel.add(panelD);

		GridBagLayout gbl_panelD = new GridBagLayout();
		gbl_panelD.columnWidths = new int[] { 0, 0 };
		gbl_panelD.rowHeights = new int[] { 0, 0 };
		gbl_panelD.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
		gbl_panelD.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		panelD.setLayout(gbl_panelD);

		/**
		 * set up button: Clear All Balls
		 */
		GridBagConstraints gbc_btnClear = new GridBagConstraints();
		gbc_btnClear.gridx = 0;
		gbc_btnClear.gridy = 0;
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				_view2ModelControlAdapter.clearAllBalls();
			}
		});
		btnClear.setBackground(UIManager.getColor("Button.background"));
		panelD.add(btnClear, gbc_btnClear);
		btnClear.setToolTipText("Clear all balls");

		/**
		 * set up 5th panel
		 */
		panelE.setBackground(new Color(66, 215, 244));
		panelE.setBorder(BorderFactory.createLineBorder(Color.white));
		northPanel.add(panelE);

		GridBagLayout gbl_panelE = new GridBagLayout();
		gbl_panelE.columnWidths = new int[] { 0, 0 };
		gbl_panelE.rowHeights = new int[] { 0, 0 };
		gbl_panelE.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
		gbl_panelE.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		panelE.setLayout(gbl_panelE);

		/**
		*  	set up text field: get paint strategy
		*/
		txtPaintStrategy.setText("BallPaint");
		GridBagConstraints gbc_txtPaintStrategy = new GridBagConstraints();
		gbc_txtPaintStrategy.insets = new Insets(0, 0, 5, 0);
		gbc_txtPaintStrategy.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtPaintStrategy.gridx = 0;
		gbc_txtPaintStrategy.gridy = 0;
		panelE.add(txtPaintStrategy, gbc_txtPaintStrategy);
		txtPaintStrategy.setToolTipText("Type in the paint strategy you want");

		/**
		 * set up button: add paint strategy
		 */
		GridBagConstraints gbc_btnAdd = new GridBagConstraints();
		gbc_btnAdd.gridx = 0;
		gbc_btnAdd.gridy = 1;
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String paintStrategy = txtPaintStrategy.getText();
				if (paintStrategy == null)
					return;
				@SuppressWarnings("unchecked")
				TPaintDropListItem o = (TPaintDropListItem) _view2ModelControlAdapter
						.addPaintStrategy(txtPaintStrategy.getText());
				_paintDL.insertItemAt(o, 0);
				_paintDL.setSelectedItem(o);
			}
		});
		btnAdd.setBackground(UIManager.getColor("Button.background"));
		panelE.add(btnAdd, gbc_btnAdd);
		btnAdd.setToolTipText("Add a paint strategy");

		/**
		* add a combo box for paint strategy
		*/
		GridBagConstraints gbc_paintDL = new GridBagConstraints();
		gbc_paintDL.insets = new Insets(0, 0, 5, 0);
		gbc_paintDL.fill = GridBagConstraints.HORIZONTAL;
		gbc_paintDL.gridx = 0;
		gbc_paintDL.gridy = 2;
		panelE.add(_paintDL, gbc_paintDL);
	}

	/**
	 * get current canvas
	 * @return canvas
	 */
	public Container getCanvas() {
		return centerPanel;
	}
}
