package model.interfaces;

import java.util.*;

/**
 * Interface for the exhibit of the art gallery.
 * @author Elisa Casadio
 *
 */

public interface IExhibit {
	
	/**
	 * Return the title of the exhibit.
	 * 
	 * @return the title of the exhibit.
	 */
	String getTitleExhibit();
	
	/**
	 * Return the name of the curator or curators of this exhibit.
	 * 
	 * @return the name of the curators.
	 */
	String getCurator();
	
	/**
	 * Return the commencement date of the exhibit.
	 * 
	 * @return the commencement date of the exhibit.
	 */
	Calendar getBeginning();
	
	/**
	 * Return the end date of the exhibit.
	 * 
	 * @return the end date of the exhibit.
	 */
	Calendar getEnd();
	
	/**
	 * Adds the artwork to the list of exhibit's artworks.
	 * 
	 * @param the artwork.
	 */
	void addArtwork(final IArtwork artwork);
	
	/**
	 * Return the list of artworks of this exhibit.
	 * 
	 * @return the list of artworks.
	 */
	List<IArtwork> getArtworks();
	
	/**
	 * Return the number of pieces of the artworks of this exhibit.
	 * 
	 * @return the number of pieces of the artworks.
	 */
	int getNumPieces();
	
	/**
	 * Return the cost of this exhibit in Euros.
	 * 
	 * @return the cost of this exhibit.
	 */
	double getCostExhibit();

}
