package GuessingGame;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;

/**
 * Create a card layout that automatically resizes itself based on its content
 * 
 * @author Emma Nguyen
 * @version April 28, 2018
 */
public class ResizableCardLayout extends CardLayout {

	/**
	 * Set the preferred layout size based on the container
	 */
	@Override
	public Dimension preferredLayoutSize(Container container) {

		// find the current container of the card layout
		Component current = findCurrentComponent(container);

		// if there is a container
		if (current != null) {

			// get the container's insets
			Insets insets = container.getInsets();

			// get the container's dimension
			Dimension preferredSize = current.getPreferredSize();

			// set the preferred width and height
			preferredSize.width += insets.left + insets.right;
			preferredSize.height += insets.top + insets.bottom;

			// return the preferred size
			return preferredSize;
		}

		// call the super class's method on the container
		return super.preferredLayoutSize(container);
	}

	/**
	 * Find the current component of the container
	 * 
	 * @param container
	 *            the container whose element is to be found
	 * @return the current component
	 */
	public Component findCurrentComponent(Container container) {

		// loop through the container's components
		for (Component comp : container.getComponents()) {

			// if a component is visible
			if (comp.isVisible()) {

				// return the component
				return comp;
			}
		}

		// if no components of the container is visible, return null
		return null;
	}

}
