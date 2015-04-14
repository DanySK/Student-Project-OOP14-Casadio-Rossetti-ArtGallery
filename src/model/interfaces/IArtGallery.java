package model.interfaces;

import java.util.List;

/**
 * Interface for the art gallery.
 * @author Elisa Casadio
 *
 */

public interface IArtGallery {

	/**
	 * Adds the exhibit to the list of exhibits of the art gallery.
	 * 
	 * @param the exhibit.
	 */
	void addExhibit(final IExhibit exhibit);
	
	/**
	 * Return the list of all exhibits of the art gallery.
	 * 
	 * @return the list of exhibits.
	 */
	List<IExhibit> getExhibit();
	
}
