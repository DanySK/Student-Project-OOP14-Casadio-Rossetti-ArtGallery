package model.classes;

import model.interfaces.IArtwork;

/**
 * This class models a generic artwork.
 * @author Elisa Casadio
 *
 */

public class Artwork implements IArtwork {

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
	 * @param title
	 * 			the artwork's title.
	 * @param author
	 * 			the author's name of this artwork.
	 * @param year
	 * 			the year of the artwork.
	 * @param artisticDiscipline
	 * 			the type of artistic discipline of the artwork.
	 * @param technique
	 * 			the painting technique or material of the artwork.
	 * @param height
	 * 			the height of artwork.
	 * @param width
	 * 			the width of artwork.
	 * @param depth
	 * 			the depth of artwork.
	 * @param description
	 * 			the description of artwork.
	 */
	public Artwork(final String title, final String author, final int year,
			final String artisticDiscipline, final String technique,
			final double height, final double width,
			final double depth, final String description) {
		this.title = title;
		this.author = author;
		this.year = year;
		this.artisticDiscipline = artisticDiscipline;
		this.technique = technique;
		this.height = height;
		this.width = width;
		this.depth = depth;
		this.description = description;
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
		return "Artwork[title=" + this.title + ", author=" + this.author +
				", year=" + this.year + ", artistic discipline=" 
				+ this.artisticDiscipline + ", technique=" + this.technique +
				", height=" + this.height + ", width=" + this.width +
				", depth=" + this.depth + ", description=" + this.description + "]";
	}

}
