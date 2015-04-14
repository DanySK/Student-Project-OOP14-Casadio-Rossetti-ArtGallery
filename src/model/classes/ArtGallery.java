package model.classes;

import java.util.*;

import model.interfaces.IArtGallery;
import model.interfaces.IExhibit;

/**
 * This class models a generic art gallery.
 * @author Elisa Casadio
 *
 */

public class ArtGallery implements IArtGallery {

private final List<IExhibit> exhibits;
	
	/**
	 * Constructor.
	 */
	public ArtGallery(){
		this.exhibits = new ArrayList<>();
	}
	
	@Override
	public void addExhibit(final IExhibit exhibit){
		this.exhibits.add(exhibit);
	}
	
	@Override
	public List<IExhibit> getExhibit(){
		return this.exhibits;
	}

}
