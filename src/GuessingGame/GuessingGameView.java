package GuessingGame;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

/**
 * Create the GUI view for the game
 * 
 * @author Emma Nguyen
 * @version April 21, 2018
 */
public class GuessingGameView extends JPanel implements ActionListener {

	// a guessing game application
	private GuessingGameApplication game;

	// a label to hold the current question
	private JLabel question;

	// a controller for the game
	private GuessingGameController controller;

	// a card layout for the GUI
	private final CardLayout layout;

	// a panel to hold the card layout elements
	private final JPanel windows;

	/**
	 * Construct a guessing game view
	 * 
	 * @param game
	 *            the game whose game is being built
	 */
	public GuessingGameView(GuessingGameApplication game) {

		// call the superclass constructor to set the view in border layout
		super(new BorderLayout());

		// set the game
		this.game = game;

		// create a controller for the game
		this.controller = new GuessingGameController(this);

		// instantiate a card layout
		layout = new ResizableCardLayout();

		// instantiate the panel to hold the cards
		windows = new JPanel(layout);

		// add the welcome window to be one card
		windows.add(createWelcomeWindow());

		// add the game window to be one card
		windows.add(createGameWindow());

		// add the cards to the center of the game
		add(windows, BorderLayout.CENTER);

		// play the music
		playMusic("support/music/luc-moi-yeu.wav");
	}

	/**
	 * Create the welcome window
	 * 
	 * @return a panel that holds the welcome window
	 */
	private JPanel createWelcomeWindow() {

		// create a panel to hold the welcome window
		JPanel window = new JPanel();

		// set the window's layout to be box layout
		window.setLayout(new BoxLayout(window, BoxLayout.PAGE_AXIS));

		// add the header
		window.add(createHeader());

		// add an empty are between the header and elements (for aesthetic purpose)
		window.add(Box.createRigidArea(new Dimension(getWidth(), 10)));

		// add the elements (things)
		window.add(createElements());

		// return the panel
		return window;
	}

	/**
	 * Create the header of the welcome window
	 * 
	 * @return a panel that holds the header
	 */
	private JPanel createHeader() {

		// create a panel to hold the header in border layout
		JPanel topPanel = new JPanel(new BorderLayout());

		// create a panel to hold the text
		JPanel textPanel = new JPanel(new GridLayout(2, 1));

		// create the welcome label
		JLabel welcome = new JLabel();

		// set the welcome label's text
		welcome.setText("Welcome to Emma's Salon!");

		// set the label's font
		welcome.setFont(new Font("Garamond", Font.BOLD + Font.ITALIC, 30));

		// set the label's text color
		welcome.setForeground(new Color(66, 128, 244));

		// set the label's alignment
		welcome.setHorizontalTextPosition(JLabel.CENTER);

		// add the label to the text panel
		textPanel.add(welcome);

		// create a label to hold the instructions
		JLabel instructions = new JLabel(
				"<html> Ever dream of amazing skin? No salons know what you want? <br/>Choose one below. Think of it. We'll ask you Yes-No questions and within 20 questions, we'll know what you want!<br/>If you wonder what each image is, hover over the image for some seconds and you'll see what it is<br/></html>");

		// add the instructions to the text panel
		textPanel.add(instructions);

		// add the text panel to the top panel
		topPanel.add(textPanel, BorderLayout.CENTER);

		// create a button to start the game
		JButton start = new JButton("Let's go!");

		// set the button's action command
		start.setActionCommand("start");

		// set the button's size
		start.setPreferredSize(new Dimension(330, 40));

		// add action listener to the button
		start.addActionListener(this);

		// add the button to the top panel
		topPanel.add(start, BorderLayout.EAST);

		// return the panel holding the header
		return topPanel;
	}

	/**
	 * Create the elements, which are the prespecified items for the player to
	 * choose
	 * 
	 * @return a panel that holds the elements
	 */
	private JPanel createElements() {

		// create a panel in grid layout to hold the elements
		JPanel dataPanel = new JPanel(new GridLayout(3, 6, 2, 2));

		// create an array of labels to hold the elements
		JLabel[] elements = new JLabel[18];

		// get the names of the elements
		ArrayList<String> labels = createElementsNames();

		// loop through the elements
		for (int i = 0; i < elements.length; i++) {

			// instantiate the label
			elements[i] = new JLabel();

			// set the element's alignment
			elements[i].setHorizontalTextPosition(JLabel.CENTER);

			// set the element's font
			elements[i].setFont(new Font("Book Antiqua", Font.BOLD, 12));

			// set the element's text color
			elements[i].setForeground(new Color(66, 128, 244));

			// when the player hovers the mouse on the elements, its name appears
			elements[i].setToolTipText(labels.get(i));

			// add the element to the panel
			dataPanel.add(elements[i]);
		}

		// get the images of the elements
		BufferedImage[] images = getImages();

		// get images requires exception handling
		try {

			// loop through the elements
			for (int i = 0; i < elements.length; i++) {

				// set each element's image
				elements[i].setIcon(new ImageIcon(images[i]));
			}
		} catch (Exception e) {

			// print out the exception
			e.printStackTrace();
		}

		// return the panel holding the elements
		return dataPanel;
	}

	/**
	 * Create the name for each element
	 * 
	 * @return a list of each element's name
	 */
	private ArrayList<String> createElementsNames() {

		// get the elements' names from the tree' leaf nodes (the things) and return the
		// names
		return (controller.getLeafNodes());
	}

	/**
	 * Get the images for each element
	 * 
	 * @return a list of images
	 */
	private BufferedImage[] getImages() {

		// create an array of buffered image
		BufferedImage[] imageArray = new BufferedImage[18];

		// load images require exception handling
		try {

			// get the images for each element
			imageArray[0] = ImageIO.read(new File("support/images/face-sunscreen.png"));
			imageArray[1] = ImageIO.read(new File("support/images/eye-cream.png"));
			imageArray[2] = ImageIO.read(new File("support/images/lip-balm.png"));
			imageArray[3] = ImageIO.read(new File("support/images/sheet-mask.png"));
			imageArray[4] = ImageIO.read(new File("support/images/face-mask.png"));
			imageArray[5] = ImageIO.read(new File("support/images/anti-aging.png"));
			imageArray[6] = ImageIO.read(new File("support/images/face-serum.png"));
			imageArray[7] = ImageIO.read(new File("support/images/face-oil.png"));
			imageArray[8] = ImageIO.read(new File("support/images/moisturizer.png"));
			imageArray[9] = ImageIO.read(new File("support/images/night-cream.png"));
			imageArray[10] = ImageIO.read(new File("support/images/body-sunscreen.png"));
			imageArray[11] = ImageIO.read(new File("support/images/hair-removal.png"));
			imageArray[12] = ImageIO.read(new File("support/images/cleansing-device.png"));
			imageArray[13] = ImageIO.read(new File("support/images/teeth-whitening.png"));
			imageArray[14] = ImageIO.read(new File("support/images/makeup-remover.png"));
			imageArray[15] = ImageIO.read(new File("support/images/exfoliator.png"));
			imageArray[16] = ImageIO.read(new File("support/images/face-wash.png"));
			imageArray[17] = ImageIO.read(new File("support/images/toner.png"));
		} catch (Exception e) {

			// print out the exception
			e.printStackTrace();
		}

		// loop through the array of images
		for (int i = 0; i < imageArray.length; i++) {

			// update each image (resize the image)
			imageArray[i] = updateImage(imageArray[i], 200, 200);
		}

		// return the array of images
		return imageArray;
	}

	/**
	 * Update an image (resize the image)
	 * 
	 * @param original
	 *            the original image to be updated
	 * @param width
	 *            width of the updated image
	 * @param height
	 *            height of the updated image
	 * @return the updated image
	 */
	private BufferedImage updateImage(BufferedImage original, int width, int height) {

		// create a new image with the desired width and height
		BufferedImage updated = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

		// create graphics for the new image
		Graphics2D g = updated.createGraphics();

		// set the image's opacity - for future references
		// g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));

		// draw the new image from the original image, with the desired width and height
		g.drawImage(original, 0, 0, width, height, null);

		// dispose the image
		g.dispose();

		// return the updated image
		return updated;
	}

	/**
	 * Play music during the game
	 * 
	 * @param musicFile
	 *            the musc file from which audio is to be extracted
	 */
	private void playMusic(String musicFile) {

		// load music requires exception handling
		try {

			// get the audio from the music file
			AudioInputStream audio = AudioSystem.getAudioInputStream(new File(musicFile));

			// create a clip
			Clip clip = AudioSystem.getClip();

			// open the audio
			clip.open(audio);

			// start running the audio
			clip.start();
		} catch (Exception e) {

			// print the exceptions
			e.printStackTrace();
		}
	}

	/**
	 * Create the game window
	 * 
	 * @return a panel that holds the game window
	 */
	private JPanel createGameWindow() {

		// create a panel to hold the window
		JPanel window = new JPanel();

		// set the panel's layout
		window.setLayout(new BoxLayout(window, BoxLayout.PAGE_AXIS));

		// add the buttons to the panel
		window.add(createButtonPanel());

		// create the label that holds the questions
		createQuestionLabel();

		// add the question label to the panel
		window.add(question);

		// create a panel to hold the buttons
		JPanel buttonPanel = new JPanel();

		// set the button panel's layout
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));

		// add the button yes to the button panel
		buttonPanel.add(createAnswerButton("yes", "support/images/yes.png"));

		// add the button no to the button panel
		buttonPanel.add(createAnswerButton("no", "support/images/no.png"));

		// add the button panel to the window panel
		window.add(buttonPanel);

		// return the window panel
		return window;
	}

	/**
	 * Create a panel that hold the back and restart buttons in the game window
	 * 
	 * @return the panel that hold the command buttons
	 */
	private JPanel createButtonPanel() {

		// create a panel that holds the buttons
		JPanel buttonPanel = new JPanel();

		// set the button panel's panel
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));

		// create a back button
		JButton back = new JButton("Back");

		// set the button's action command
		back.setActionCommand("back");

		// set the button's alignment
		back.setAlignmentX(Component.CENTER_ALIGNMENT);

		// add action listener to the button
		back.addActionListener(this);

		// add the button to the button panel
		buttonPanel.add(back);

		// create a restart button
		JButton restart = new JButton("Restart");

		// set the buttons' action command
		restart.setActionCommand("restart");

		// set the button's alignment
		restart.setAlignmentX(Component.CENTER_ALIGNMENT);

		// add action listener to the button
		restart.addActionListener(this);

		// add the button to the button panel
		buttonPanel.add(restart);

		// return the button panel
		return buttonPanel;
	}

	/**
	 * Create the label the hold the questions
	 */
	private void createQuestionLabel() {

		// initiate the question label
		question = new JLabel();

		// set the label's text
		question.setText(controller.getCurrentElem().getData());

		// create a border for the label and set the border's label
		Border border = question.getBorder();
		Border margin = new EmptyBorder(20, 20, 20, 20);
		question.setBorder(new CompoundBorder(border, margin));

		// set the label's alignment
		question.setAlignmentX(Component.CENTER_ALIGNMENT);

		// set the label's font
		question.setFont(new Font("Book Antiqua", Font.BOLD, 14));

		// set the label's text color
		question.setForeground(new Color(66, 128, 244));
	}

	/**
	 * Create an answer button
	 * 
	 * @param answer
	 *            the answer that the button holds
	 * @param buttonImage
	 *            the image of the button
	 * @return the answer button
	 */
	private JButton createAnswerButton(String answer, String buttonImage) {

		// create a button
		JButton button = new JButton();

		// load image requires exception handling
		try {

			// load the button's image
			BufferedImage pic = ImageIO.read(new File(buttonImage));

			// update the image
			pic = updateImage(pic, 300, 300);

			// set the button's image
			button.setIcon(new ImageIcon(pic));
		} catch (Exception e) {

			// print the exception
			e.printStackTrace();
		}

		// add action listener to the button
		button.addActionListener(this);

		// set the button's action command
		button.setActionCommand(answer);

		// set the button's tool tip text
		button.setToolTipText("Click to choose " + answer);

		// add action listener to the button
		button.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				// play the ding sound as the button is clicked
				playMusic("support/music/ding.wav");
			}
		});

		// return the button
		return button;
	}

	/**
	 * Show the thing that the player chose
	 * 
	 * @param thing
	 *            the thing that the player chose
	 */
	public void showThing(String thing) {

		// cause the runnable to have its run method called in the dispatch thread of
		// the system EventQueue
		EventQueue.invokeLater(new Runnable() {

			/**
			 * To be executed by a thread
			 */
			@Override
			public void run() {

				// an image
				BufferedImage image;

				// load image requires exception handling
				try {

					// set the UI look and feel to be the system's look and feel
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

					// read in the image file
					image = ImageIO.read(new File("support/images/got-it.jpg"));

					// create an image icon with the image
					ImageIcon icon = new ImageIcon(image);

					// show the thing in a option pane with the image icon
					JOptionPane.showMessageDialog(null, thing, "Emma's Guessing Salon", JOptionPane.INFORMATION_MESSAGE,
							icon);
				} catch (Exception e) {

					// print the exception
					e.printStackTrace();
				}

			}

		});
	}

	/**
	 * Get the label that holds question
	 * 
	 * @return the current question
	 */
	public JLabel getQuestion() {

		// return the question label
		return question;
	}

	/**
	 * Define what happens when the player clicks on a button
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		// get the action command of the action event
		String action = e.getActionCommand();

		// if the action is start
		if (action.equals("start")) {

			// flip to next card
			layout.next(windows);

			// resize the frame
			game.pack();
		}

		// if the action is restart
		else if (action.equals("restart")) {

			// create a new controller
			controller = new GuessingGameController(this);

			// set the initial question to be the current node's data
			question.setText(controller.getCurrentElem().getData());
		}

		// if the action is back
		else if (action.equals("back")) {

			// flip to the previous card
			layout.previous(windows);

			// resize the fame
			game.pack();
		}

		// if the action is the yes button
		else if (action.equals("yes")) {

			// choose yes
			controller.chooseYes();

			// refresh the GUI
			repaint();
		}

		// if the action is the no button
		else if (action.equals("no")) {

			// choose no
			controller.chooseNo();

			// refresh the GUI
			repaint();
		}
	}

}
