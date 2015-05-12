package controller.classes;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Map;
import java.util.NoSuchElementException;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import controller.interfaces.IBalanceController;
import view.classes.BalanceView;
import model.classes.Artwork;
import model.classes.Balance;
import model.classes.Exhibit;
import model.classes.SalesManagement;
import model.interfaces.IExhibit;

/**
 * This is the controller of the BalanceView.
 * @author Sofia Rosetti
 *
 */
public class BalanceController implements IBalanceController {

	private static final String FILE_PATH = System.getProperty("user.home") + "/SalesManagement.bms";
	private static final String ERROR = "Errore";
	private static final int FIRST_YEAR = 2000;
	private static final int LAST_YEAR = 2014;
	private static final String[] PROPS = new String[] { "Esposizione", "Incasso (€)" };
	private Balance model;
	private BalanceView parentComponent;
	private SalesManagement salesData;
	private final Map<IExhibit, Double> map = new HashMap<IExhibit, Double>();
	
	/**
	 * Constructor.
	 * @param v
	 * 			the BalanceView
	 * @throws FileNotFoundException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public BalanceController(final BalanceView v) throws FileNotFoundException, ClassNotFoundException, IOException {
		this.model = new Balance();
		this.parentComponent = v;
		this.salesData = new SalesManagement();
		this.load();
	}
	
	@Override
	public void addView(final BalanceView v) {
		v.attachController(this);
		v.createTab();
	}
	
	/**
	 * This method loads the SalesManagement model from the specified file.
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	private void load() throws FileNotFoundException, IOException, ClassNotFoundException {
		try {
			if (this.isDataFilePresent(FILE_PATH)) {
				final ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_PATH));
				this.salesData = (SalesManagement) in.readObject();
				in.close();
				//System.out.println(this.model.toString());
			}
		} catch (IOException | ClassNotFoundException ex) {
			JOptionPane.showMessageDialog(this.parentComponent, "Errore nel caricamento", ERROR, JOptionPane.ERROR_MESSAGE);
		}
	}
	
	@Override
	public DefaultTableModel uploadTable() {
		final Iterator<IExhibit> it1 = this.salesData.getData().keySet().iterator();
		while (it1.hasNext()) {
			final IExhibit ex = it1.next();
			this.map.put(ex, this.salesData.getData().get(ex).getIncome());
		}
		
		final DefaultTableModel tm = new DefaultTableModel(new Object[][] {}, PROPS);
		final Iterator<IExhibit> it = this.map.keySet().iterator();
		while (it.hasNext()) {
			final IExhibit ex = it.next();
			tm.addRow(new Object[] {ex.getTitleExhibit(), this.map.get(ex)});
		}
		return tm;
	}
	
	@Override
	public double computeProfit() {
		return this.model.profit(this.map);
	}
	
	@Override
	public void commandSearch(final String year) {
		try {
			if (year.startsWith("-")) {
				throw new NumberFormatException();
			} else {
				final int chosenYear = Integer.parseInt(year);
				if (chosenYear > LAST_YEAR || chosenYear < FIRST_YEAR) {
					throw new NumberFormatException();
				}
				this.model.getPreviousIncome(chosenYear);
			}
		} catch (NoSuchElementException e) {
			JOptionPane.showMessageDialog(this.parentComponent, "L'anno " + year + " non è presente", ERROR, JOptionPane.ERROR_MESSAGE);
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this.parentComponent, "Inserisci un anno tra " + FIRST_YEAR + " e " + LAST_YEAR, ERROR, JOptionPane.ERROR_MESSAGE);
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
