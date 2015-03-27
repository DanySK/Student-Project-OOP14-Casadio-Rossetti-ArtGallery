package model.classes;

import java.util.*;

import model.interfaces.IExhibit;

/**
 * This class models a generic exhibit of the art gallery.
 * @author Elisa Casadio
 *
 */

public class Exhibit implements IExhibit {

	private final String titleExhibit;
	private final String curator;
	private final Calendar beginning;
	private final Calendar end;
	private final List<Artwork> artworks;
	private final double costExhibit;
	
	/**
	 * Constructor.
	 * @param title
	 * 			the exhibit's title.
	 * @param curator
	 * 			the curator's name of this exhibit.
	 * @param beginning
	 * 			the commencement date of this exhibit.
	 * @param end
	 * 			the end date of this exhibit.
	 * @param artworks
	 * 			the list of artworks of this exhibit.
	 * @param costExhibit
	 * 			the cost of this exhibit.
	 */
	public Exhibit(final String title, final String curator, 
			final Calendar beginning, final Calendar end, 
			final List<Artwork> artworks, final double costExhibit) {
		this.titleExhibit = title;
		this.curator = curator;
		this.beginning = beginning;
		this.end = end;
		this.artworks = artworks;
		this.costExhibit = costExhibit;
	}
	
	@Override
	public String getTitleExhibit() {
		return this.titleExhibit;
	}
	
	@Override
	public String getCurator() {
		return this.curator;
	}
	
	@Override
	public Calendar getBeginning() {
		return this.beginning;
	}
	
	@Override
	public Calendar getEnd() {
		return this.end;
	}
	
	@Override
	public List<Artwork> getArtworks() {
		return this.artworks;
	}
	
	@Override
	public int getNumPieces() {
		return this.artworks.size();
	}
	
	@Override
	public double getCostExhibit() {
		return this.costExhibit;
	}
	
	@Override
	public String toString() {
		return "Exhibit[title=" + this.titleExhibit + ", curators=" + 
				this.curator + ", beginning=" + this.beginning.getTime() +
				", end=" + this.end.getTime() + ", number of pieces=" +
				this.getNumPieces() + ", artworks=" + this.artworks + 
				", cost exhibit=" + this.costExhibit + "]";
	}

}
