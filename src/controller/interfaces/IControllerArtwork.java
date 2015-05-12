package controller.interfaces;

import javax.swing.JFrame;

/**
 * Interface for {@link ControllerArtwork}.
 * @author Elisa Casadio
 *
 */
public interface IControllerArtwork {

	/**
	 * Adds a new artwork.
	 * 
	 * @param frame
	 * 			the frame of the view.
	 */
	void commandAdd(final JFrame frame);
	
	/**
	 * Changes the selected artwork.
	 * 
	 * @param index
	 * 			the position of the artwork in the list.
	 * @param frame
	 * 			the frame of the view.
	 */
	void commandEdit(final int index, final JFrame frame);
	
	/**
	 * Shows the selected artwork.
	 * 
	 * @param index
	 * 			the position of the artwork in the list.
	 * @param frame
	 * 			the frame of the view.
	 */
	void commandShow(final int index, final JFrame frame);
	
	/**
	 * Deletes the selected artwork.
	 * 
	 * @param index
	 * 			the position of the artwork in the list.
	 * @param frame
	 * 			the frame of the view.
	 */
	void commandDelete(final int index, final JFrame frame);
	
	/**
	 * Closes the frame and saves the model in to a file.
	 * 
	 * @param frame
	 * 			the frame of the view.
	 */
	void commandClose(final JFrame frame);
	
}
