package controller.classes;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import controller.interfaces.IClassificationController;
import view.classes.ClassificationView;
import model.classes.Classification;
import model.classes.SalesManagement;
import model.interfaces.IExhibit;

/**
 * This is the controller of the ClassificationView.
 * @author Sofia Rosetti
 *
 */
public class ClassificationController implements IClassificationController {
	
	private static final String FILE_PATH = System.getProperty("user.home") + "/SalesManagement.bms";
	private static final String ERROR = "Errore";
	private static final String[] PROPS = new String[] { "Esposizione", "Incasso (€)" };
	final private Classification model;
	private SalesManagement salesData;
	private final Map<IExhibit, Double> map = new HashMap<IExhibit, Double>();
	final private ClassificationView parentComponent;
	
	/**
	 * Constructor.
	 * @param v
	 * 			the ClassificationView
	 * @throws FileNotFoundException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public ClassificationController(final ClassificationView v) throws FileNotFoundException, ClassNotFoundException, IOException {
		this.model = new Classification();
		this.parentComponent = v;
		this.salesData = new SalesManagement();
		this.load();
	}
	
	@Override
	public void addView(final ClassificationView v) {
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
	public void createClassificationStructure() {
		final Iterator<IExhibit> it = this.salesData.getData().keySet().iterator();
		while (it.hasNext()) {
			final IExhibit ex = it.next();
			this.map.put(ex, this.salesData.getData().get(ex).getIncome());
		}
		this.model.sortMap(map);
	}
	
	@Override
	public DefaultTableModel uploadClassification() {
		final DefaultTableModel tm = new DefaultTableModel(new Object[][] {}, PROPS);
		final ListIterator<Entry<IExhibit, Double>> it = this.model.getList().listIterator();
		while (it.hasNext()) {
			final Entry<IExhibit, Double> e = it.next();
			tm.addRow(new Object[] {e.getKey().getTitleExhibit(), e.getValue()});
		}
		return tm;
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
