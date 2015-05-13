package controller.classes;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Calendar;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import model.classes.ArtGallery;
import model.classes.Artwork;
import view.classes.ExhibitForm;
import view.classes.ExhibitView;
import view.classes.ManageArtworkExhibit;
import view.interfaces.IExhibitView;
import controller.interfaces.IControllerExhibit;
import exceptions.IllegalOperationException;

/**
 * The controller for the {@link ExhibitView}.
 * @author Elisa Casadio
 *
 */

public class ControllerExhibit implements IControllerExhibit {

	private static final String ERROR = "ERRORE";
	private static final String WRONG_COMMAND = "Impossibile modificare/"
			+ "cancellare la mostra selezionata, perché già iniziata.";
	private static final String DELETE = "ELIMINA";
	private static final String CONFIRM_DELETE = "Sei sicuro di voler eliminare"
			+ " questa esposizione?";
	private static final String ERROR_SAVING = "Errore nel salvataggio del file.";
	
	private final IExhibitView view;
	private final ArtGallery model;
	private final String path;
	
	/**
	 * Constructor.
	 * 
	 * @param newModel
	 * 			the model.
	 * @param newView
	 * 			the view.
	 * @param newPath
	 * 			the data path where the model is saved.
	 */
	public ControllerExhibit(final ArtGallery newModel, 
			final IExhibitView newView, final String newPath) {
		this.model = newModel;
		this.view = newView;
		this.view.attachController(this);
		this.path = newPath;
	}
	
	@Override
	public void commandAdd(final JFrame frame) {
		final ExhibitForm exhibit = new ExhibitForm(frame);
	    exhibit.reinit();
	    exhibit.setVisible(true);
	    new ControllerExhibitForm(this.model, exhibit, frame, this.view, -1);
	}
	
	@Override
	public void commandEdit(final int index, final JFrame frame) {
		try {
			if (this.model.getExhibit().get(index).getBeginning()
					.before(Calendar.getInstance())) {
				throw new IllegalOperationException(WRONG_COMMAND);
			} else {
				final ExhibitForm exhibit = new ExhibitForm(frame);
				exhibit.setForm(index, this.model);
				exhibit.setVisible(true);
				new ControllerExhibitForm(this.model, exhibit, frame, 
						this.view, index);
			}
		} catch (IllegalOperationException e) {
			JOptionPane.showMessageDialog(frame, e.getMessage(), ERROR,
					JOptionPane.ERROR_MESSAGE);
		}
	}
	
	@Override
	public void commandDelete(final int index, final JFrame frame) {
		try {
			if (this.model.getExhibit().get(index).getBeginning()
					.before(Calendar.getInstance())) {
				throw new IllegalOperationException(WRONG_COMMAND);
			} else {
				final int n = JOptionPane.showConfirmDialog(frame, 
						CONFIRM_DELETE, DELETE, JOptionPane.YES_NO_OPTION);
				if (n == JOptionPane.YES_OPTION) {
					this.view.clearData(index);
					this.model.getExhibit().remove(index);
				}
			}
		} catch (IllegalOperationException e) {
			JOptionPane.showMessageDialog(frame, e.getMessage(), ERROR,
					JOptionPane.ERROR_MESSAGE);
		}
	}
	
	@Override
	public void commandArtwork(final int index, final JFrame frame) {
		final ManageArtworkExhibit mae = new ManageArtworkExhibit(frame);
		new ControllerManageArtworkExhibit(this.model, index, mae, frame, 
				this.view);
		for (final Artwork art : this.model.getArtwork()) {
			mae.addData(true, art.getCode(), art.getTitle(), art.getAuthor(),
					art.getArtisticDiscipline(), art.getTechnique());
		}
		for (final Long artE : this.model.getExhibit().get(index).getArtworks()) {
			for (final Artwork artA : this.model.getArtwork()) {
				if (Long.compare(artA.getCode(), artE) == 0) {
					mae.addData(false, artA.getCode(), artA.getTitle(),
							artA.getAuthor(), artA.getArtisticDiscipline(),
							artA.getTechnique());
				}
			}
		}
		mae.setVisible(true);
	}
	
	@Override
	public void commandClose(final JFrame frame) {
		try {
			final ObjectOutputStream out = new ObjectOutputStream(
					new FileOutputStream(this.path));
			out.writeObject(this.model);
			out.close();
			frame.setVisible(false);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(frame, ERROR_SAVING, ERROR,
					JOptionPane.ERROR_MESSAGE);
		}
	}

}
