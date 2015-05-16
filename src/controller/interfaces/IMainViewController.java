package controller.interfaces;

import view.classes.MainView;

/**
 * The interface for the {@link MainViewController}.
 * @author Elisa Casadio
 * @author Sofia Rosetti
 *
 */

public interface IMainViewController {

	/**
	 * Adds a new main view to this controller.
	 * @param v
	 * 			the new view.
	 */
	void addView(final MainView v);
	
	/**
	 * Creates a new model for the archive of the art gallery or loads it from 
	 * a file and shows the view of the artworks. Shows an error message if is 
	 * occurred a problem during the open of the file.
	 */
	void artworkCmd();
	
	/**
	 * Creates a new model for the archive of the art gallery or loads it from
	 * a file and shows the view of the exhibits. Shows an error message if is
	 * occurred a problem during the open of the file or if there aren't saved
	 * artworks in the archive.
	 */
	void exhibitCmd();
	
	/**
	 * 
	 */
	void ticketOfficeCmd();
	
	/**
	 * 
	 */
	void balanceCmd();
	
	/**
	 * 
	 */
	void classificationCmd();
	
	/**
	 * Closes the program.
	 */
	void commandQuit();
	
}
