package controller.interfaces;

import javax.swing.JFrame;

/**
 * The interface for the {@link ControllerExhibit}.
 * @author Elisa Casadio
 *
 */

public interface IControllerExhibit {

	/**
	 * Adds a new exhibit.
	 * 
	 * @param frame
	 * 			the frame of the view.
	 */
	void commandAdd(final JFrame frame);
	
	/**
	 * Changes the selected exhibit.
	 * 
	 * @param index
	 * 			the position of the exhibit in the list.
	 * @param frame
	 * 			the frame of the view.
	 * 			
	 */
	void commandEdit(final int index, final JFrame frame);
	
	/**
	 * Deletes the selected exhibit.
	 * 
	 * @param index
	 * 			the position of the exhibit in the list.
	 * @param frame
	 * 			the frame of the view.
	 */
	void commandDelete(final int index, final JFrame frame);
	
	/**
	 * Displays the artwork of the selected exhibit.
	 * 
	 * @param index
	 * 			the position of the exhibit in the list.
	 * @param frame
	 * 			the frame of the view.
	 */
	void commandArtwork(final int index, final JFrame frame);
	
	/**
	 * Closes the frame and saves the model in to a file.
	 * 
	 * @param frame
	 * 			the frame of the view.
	 */
	void commandClose(final JFrame frame);
	
}
