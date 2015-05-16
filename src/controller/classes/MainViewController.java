package controller.classes;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.swing.JOptionPane;

import model.classes.ArtGallery;
import model.interfaces.IArtwork;
import model.interfaces.IExhibit;
import view.classes.ArtworkView;
import view.classes.ExhibitView;
import view.classes.MainView;
import view.interfaces.IArtworkView;
import view.interfaces.IExhibitView;
import controller.interfaces.IMainViewController;

/**
 * This is the controller of the {@link MainView}.
 * @author Sofia Rosetti
 * @author Elisa Casadio
 *
 */

public class MainViewController implements IMainViewController {

	private static final String ARCHIVE_FILE = System.getProperty("file.separator")
			+ "Archive.agm";
	private static final String ERROR = "ERRORE";
	private static final String LOAD_ERROR = "Errore nel caricamento.";
	private static final String OPEN_EX_ERROR = "Impossibile vedere le "
			+ "esposizioni, perchè non ci sono opere d'arte nell'archivio.";
	
	private final MainView view;
	private final String path;
	
	/**
	 * Constructor.
	 * 
	 * @param newView
	 * 			the view.
	 * @param newPath
	 * 			the path of the folder where the models are saved.
	 */
	public MainViewController(final MainView newView, final String newPath) {
		this.view = newView;
		this.path = newPath;
	}
	
	@Override
	public void addView(final MainView v) {
		//v.attachViewObserver(this);
	}

	@Override
	public void artworkCmd() {
		final IArtworkView artView = new ArtworkView();
		ArtGallery model = new ArtGallery();
		if (this.isFilePresent(this.path + ARCHIVE_FILE)) {
			try {
				final ObjectInputStream in = new ObjectInputStream(
						new FileInputStream(this.path + ARCHIVE_FILE));
				model = (ArtGallery) in.readObject();
				in.close();
				for (final IArtwork art : model.getArtwork()) {
					if (art.getYear() < 0) {
						this.addDataTable(artView, art, -1, " A.C.");
					} else {
						this.addDataTable(artView, art, 1, " D.C.");
					}
				}
			} catch (IOException | ClassNotFoundException e) {
				JOptionPane.showMessageDialog(this.view, LOAD_ERROR, ERROR,
						JOptionPane.ERROR_MESSAGE);
			}
		}
		new ControllerArtwork(model, artView, this.view, this.path 
				+ ARCHIVE_FILE);
		this.view.setVisible(false);
	}

	@Override
	public void exhibitCmd() {
		ArtGallery model = new ArtGallery();
		if (this.isFilePresent(this.path + ARCHIVE_FILE)) {
			try {
				final ObjectInputStream in = new ObjectInputStream(
						new FileInputStream(this.path + ARCHIVE_FILE));
				model = (ArtGallery) in.readObject();
				in.close();
				
			} catch (IOException | ClassNotFoundException e) {
				JOptionPane.showMessageDialog(this.view, LOAD_ERROR, ERROR,
						JOptionPane.ERROR_MESSAGE);
			}
		}
		if (model.getArtwork().size() == 0) {
			JOptionPane.showMessageDialog(this.view, OPEN_EX_ERROR, ERROR,
					JOptionPane.ERROR_MESSAGE);
		} else {
			final IExhibitView exView = new ExhibitView();
			for (final IExhibit exhibit : model.getExhibit()) {
				exView.addData(exhibit.getTitleExhibit(), exhibit.getCurator(),
						exhibit.getBeginning(), exhibit.getEnd(), 
						exhibit.getNumPieces(), exhibit.getCostExhibit(),
						exhibit.getCostTicket());
			}
			new ControllerExhibit(model, exView, this.view, this.path + ARCHIVE_FILE);
			this.view.setVisible(false);
		}
	}

	@Override
	public void ticketOfficeCmd() {

	}

	@Override
	public void balanceCmd() {

	}

	@Override
	public void classificationCmd() {

	}

	@Override
	public void commandQuit() {
		System.exit(0);
	}
	
	private boolean isFilePresent(final String currentPath) {
		final File file = new File(currentPath);
		return file.exists();
	}
	
	private void addDataTable(final IArtworkView v, final IArtwork art,
			final int one, final String dating) {
		v.addData(new Object[] {art.getCode(), art.getTitle(), art.getAuthor(),
				art.getYear() * one + dating, art.getArtisticDiscipline(),
				art.getTechnique(), art.getHeight() + "x" + art.getWidth() 
				+ "x" + art.getDepth()});
	}

}
