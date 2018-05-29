package GuessingGame;

import javax.swing.JFrame;

/**
 * Create a guessing game application
 * 
 * @author Emma Nguyen
 * @version April 22, 2018
 */
public class GuessingGameApplication extends JFrame {

	/**
	 * Construct a guessing game application
	 */
	public GuessingGameApplication() {

		// call the superclass constructor
		super("Emma's Guessing Salon");

		// create a new view
		GuessingGameView view = new GuessingGameView(this);

		// add the view to the pane
		getContentPane().add(view);

		// pack the frame to fit the size of its content (automatically resize based on
		// its content)
		pack();

		// set the frame to be visible
		setVisible(true);

		// set default close operation
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * Main method
	 * 
	 * @param args
	 *            runtime parameters
	 */
	public static void main(String[] args) {

		// create a new guessing game application
		new GuessingGameApplication();
	}
}
