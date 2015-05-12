package controller.classes;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import controller.interfaces.ITicketOfficeController;
import model.classes.Discounts;
import model.classes.SalesManagement;
import model.interfaces.IArtGallery;
import model.interfaces.IExhibit;
import view.classes.TicketOfficeView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Calendar;
import java.util.ListIterator;

/**
 * This is the controller of the TicketOfficeView.
 * @author Sofia Rosetti
 *
 */
public class TicketOfficeController implements ITicketOfficeController {

	private SalesManagement model;
	private final TicketOfficeView parentComponent;
	private final IArtGallery gallery;
	
	private static final String FILE_PATH = System.getProperty("user.home") + "/SalesManagement.bms";
	private static final String[] PROPS = new String[] { "Esposizione", "Prezzo base (€)" };
	private static final double PRICE = 10;
	private static final String ERROR = "Errore";
	
	/**
	 * Constructor.
	 * @param v
	 * 			the TicketOfficeView
	 * @param newGallery
	 * 			the ArtGallery
	 * @throws FileNotFoundException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public TicketOfficeController(final TicketOfficeView v, final IArtGallery newGallery) throws FileNotFoundException, ClassNotFoundException, IOException {
		this.model = new SalesManagement();
		this.parentComponent = v;
		this.gallery = newGallery;
		this.load();
		this.checkNewExPresent();
		this.checkDate();
		//System.out.println(this.model.toString());
	}
	
	/**
	 * This method checks if it's necessary to add a new exhibit to the map which stores all the sales data.
	 */
	private void checkNewExPresent() {
		final ListIterator<IExhibit> it = this.gallery.getExhibit().listIterator();
		while (it.hasNext()) {
			final IExhibit ex = it.next();
			if (!this.model.isExPresent(ex)) {
				this.model.addExhibit(ex);
			}
		}
	}

	/**
	 * This method saves the model in the specified file.
	 */
	private void save() {
		try {
			final ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_PATH));
			out.writeObject(this.model);
			out.close();
		} catch (IOException ex) {
			JOptionPane.showMessageDialog(this.parentComponent, "Errore nel salvataggio", ERROR, JOptionPane.ERROR_MESSAGE);
		}	
	}

	/**
	 * This method loads the model from the specified file.
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	private void load() throws FileNotFoundException, IOException, ClassNotFoundException {
		try {
			if (this.isDataFilePresent(FILE_PATH)) {
				final ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_PATH));
				this.model = (SalesManagement) in.readObject();
				in.close();
				//System.out.println(this.model.toString());
			}
		} catch (IOException | ClassNotFoundException ex) {
			JOptionPane.showMessageDialog(this.parentComponent, "Errore nel caricamento", ERROR, JOptionPane.ERROR_MESSAGE);
		}
	}
	
	@Override
	public void addView(final TicketOfficeView v) {
		v.attachController(this);
		v.createTab();
	}
	
	@Override
	public void commandClose() throws Exception {
		this.save();
	}
	
	@Override
	public void commandConfirm(final String ex, final String percentage, final String number, final double price) {
		try {
			double percSelected = 0;
			for (final Discounts d: Discounts.values()) {
				if (percentage.equals(d.getDescription())) {
					percSelected = d.getPercentage();
				}
			}
			if (number.startsWith("-")) {
				throw new NumberFormatException();
			} else {
				final int tickNum = Integer.parseInt(number);
				final ListIterator<IExhibit> it = this.gallery.getExhibit().listIterator();
				while (it.hasNext()) {
					final IExhibit exb = it.next();
					if (exb.getTitleExhibit().equals(ex)) {
						if (tickNum > this.model.getAvailableTickets(exb)) {
							throw new IllegalArgumentException();
						}
						if (tickNum == 0) {
							throw new NumberFormatException();
						}
						this.parentComponent.updateTotal(this.model.purchase(exb, percSelected, tickNum, price));
					}
				}				
			}
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this.parentComponent, "Inserisci un numero di biglietti maggiore di 0", ERROR, JOptionPane.ERROR_MESSAGE);
		} catch (IllegalArgumentException e) {
			JOptionPane.showMessageDialog(this.parentComponent, "Non ci sono abbastanza biglietti disponibili" , ERROR, JOptionPane.ERROR_MESSAGE);
		}
	}
	
	@Override
	public DefaultTableModel uploadExhibits() {
		final DefaultTableModel tm = new DefaultTableModel(new Object[][] {}, PROPS);
		final ListIterator<IExhibit> it = this.gallery.getExhibit().listIterator();
		while (it.hasNext()) {
			final IExhibit ex = it.next();
			final Calendar date = Calendar.getInstance();
			date.add(Calendar.DAY_OF_MONTH, +1);
			if (date.after(ex.getBeginning()) && date.before(ex.getEnd())) {
				tm.addRow(new Object[] {ex.getTitleExhibit(), PRICE});
			}		
		}
		return tm;
	}
	
	/**
	 * This method checks if the date of the last purchase is different from the current date.
	 * If so, it calls the model method "resetTickets" in order to reset the number of available tickets for the current day.
	 */
	private void checkDate() {
		final Calendar date = Calendar.getInstance();
		if (date.after(this.model.getLastDate()) && date.get(Calendar.DAY_OF_MONTH) != this.model.getLastDate().get(Calendar.DAY_OF_MONTH)) {
				this.model.resetTickets();
		}
	}
	
	/**
	 * This method checks if the saving file is already present in the specified path.
	 * @param path
	 * 				the path of the file
	 * @return true, if the file is present
	 */
	private boolean isDataFilePresent(final String path) {
		final File data = new File(path);
		return data.exists();
	}
	
	
}
