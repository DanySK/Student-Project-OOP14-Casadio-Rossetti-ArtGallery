package model.classes;

import java.io.Serializable;

import model.interfaces.IArtwork;

/**
 * This class models a generic artwork.
 * @author Elisa Casadio
 *
 */

public class Artwork implements IArtwork, Serializable {

	private static final long serialVersionUID = -152183137102222118L;
	
	private final Long code;
	private final String title;
	private final String author;
	private final int year;
	private final String artisticDiscipline;
	private final String technique;
	private final double height;
	private final double width;
	private final double depth;
	private final String description;
	
	/**
	 * Constructor.
	 * @param newCode
	 * 			the artwork's code.
	 * @param newTitle
	 * 			the artwork's title.
	 * @param newAuthor
	 * 			the author's name of this artwork.
	 * @param newYear
	 * 			the year of the artwork.
	 * @param newArtDiscipline
	 * 			the type of artistic discipline of the artwork.
	 * @param newTechnique
	 * 			the painting technique or material of the artwork.
	 * @param newHeight
	 * 			the height of artwork.
	 * @param newWidth
	 * 			the width of artwork.
	 * @param newDepth
	 * 			the depth of artwork.
	 * @param newDescription
	 * 			the description of artwork.
	 */
	public Artwork(final Long newCode, final String newTitle,
			final String newAuthor, final int newYear,
			final String newArtDiscipline, final String newTechnique,
			final double newHeight, final double newWidth,
			final double newDepth, final String newDescription) {
		this.code = newCode;
		this.title = newTitle;
		this.author = newAuthor;
		this.year = newYear;
		this.artisticDiscipline = newArtDiscipline;
		this.technique = newTechnique;
		this.height = newHeight;
		this.width = newWidth;
		this.depth = newDepth;
		this.description = newDescription;
	}
	
	@Override
	public Long getCode() {
		return this.code;
	}
	
	@Override
	public String getTitle() {
		return this.title;
	}
	
	@Override
	public String getAuthor() {
		return this.author;
	}
	
	@Override
	public int getYear() {
		return this.year;
	}
	
	@Override
	public String getArtisticDiscipline() {
		return this.artisticDiscipline;
	}
	
	@Override
	public String getTechnique() {
		return this.technique;
	}
	
	@Override
	public double getHeight() {
		return this.height;
	}
	
	@Override
	public double getWidth() {
		return this.width;
	}
	
	@Override
	public double getDepth() {
		return this.depth;
	}
	
	@Override
	public String getDescription() {
		return this.description;
	}
	
	@Override
	public String toString() {
		return "Artwork[code=" + this.code + ", title=" + this.title 
				+ ", author=" + this.author + ", year=" + this.year 
				+ ", artistic discipline=" + this.artisticDiscipline 
				+ ", technique=" + this.technique + ", height=" + this.height 
				+ ", width=" + this.width + ", depth=" + this.depth 
				+ ", description=" + this.description + "]";
	}

}
